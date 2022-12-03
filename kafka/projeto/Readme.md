# Começando com o Quarkus e Kafka

## Dependências

- O quarkus oferece suporte ao kafka por meio de biblioteca smallrye-reactive-messaging.
- Instalando a dependência
  - ./mvnw quarkus:add-extension -Dextensions='smallrye-reactive-messaging-kafka'

## Conceitos

- Os aplicativos enviam e recebem mensagens . Uma mensagem envolve uma carga útil e pode ser estendida com alguns metadados. Com o conector Kafka, uma mensagem corresponde a um registro Kafka.

- As mensagens transitam nos canais. Os componentes do aplicativo se conectam a canais para publicar e consumir mensagens. O conector Kafka mapeia canais para tópicos Kafka.

- Os canais são conectados a back-ends de mensagens usando conectores. Os conectores são configurados para mapear mensagens de entrada para um canal específico (consumido pelo aplicativo) e coletar mensagens de saída enviadas para um canal específico. Cada conector é dedicado a uma tecnologia de mensagens específica. Por exemplo, o conector que lida com Kafka é denominado smallrye-kafka.

## Configuração Mínima

```
%prod.kafka.bootstrap.servers=kafka:9092
mp.messaging.incoming.prices.connector=smallrye-kafka
```

Propriedade :

- kafka.bootstrap.servers : url : porta onde o broker está rodando

  - No modo de desenvolvimento e ao executar testes, o Dev Services for Kafka inicia automaticamente um agente Kafka. Quando não fornecida, essa propriedade é padronizada como localhost:9092.

- mp.messaging.incoming.prices.connector : configura o conector para gerenciar o canal de preços - por padrão o nome do tópico é igual ao nome do canal. Podemos configurar o atributo tópico com o nome que desejarmos.

## Consumindo Mensagens

### Anotação @Incoming

```
import org.eclipse.microprofile.reactive.messaging.Incoming;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PriceConsumer {

    @Incoming("prices")
    public void consume(double price) {
        // process your price.
    }
}
```

- Outra forma de consumir mensagem:

```
@Incoming("prices")
public CompletionStage<Void> consume(Message<Double> msg) {
    // access record metadata
    var metadata = msg.getMetadata(IncomingKafkaRecordMetadata.class).orElseThrow();
    // process the message payload.
    double price = msg.getPayload();
    // Acknowledge the incoming message (commit the offset)
    return msg.ack();
}
```

- O Messagetipo permite que o método de consumo acesse os metadados da mensagem recebida e manipule a confirmação manualmente.

- Se você deseja acessar os objetos de registro Kafka diretamente, use:

```
@Incoming("prices")
public void consume(ConsumerRecord<String, Double> record) {
    String key = record.key(); // Can be `null` if the incoming record has no key
    String value = record.value(); // Can be `null` if the incoming record has no value
    String topic = record.topic();
    int partition = record.partition();
    // ...
}
```

- Outra abordagem mais simples consiste em usar Record. Record é um wrapper simples em torno da chave e da carga útil do registro Kafka de entrada:

```
@Incoming("prices")
public void consume(Record<String, Double> record) {
    String key = record.key(); // Can be `null` if the incoming record has no key
    String value = record.value(); // Can be `null` if the incoming record has no value
}
```

#### Anotação @Channel

Como alternativa, seu aplicativo pode injetar um Multiem seu bean e assinar seus eventos conforme o exemplo a seguir:

```
import io.smallrye.mutiny.Multi;
import io.smallrye.reactive.messaging.annotations.Channel;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestStreamElementType;

@Path("/prices")
public class PriceResource {

    @Inject
    @Channel("prices")
    Multi<Double> prices;

    @GET
    @Path("/prices")
    @RestStreamElementType(MediaType.TEXT_PLAIN)
    public Multi<Double> stream() {
        return prices;
    }
}
```

- Este é um bom exemplo de como integrar um consumidor Kafka com outro downstream, neste exemplo, expondo-o como um endpoint de eventos enviados pelo servidor.

  - Ao consumir mensagens com @Channel, o código do aplicativo é responsável pela assinatura. No exemplo acima, o endpoint RESTEasy Reactive lida com isso para você.

- Os seguintes tipos podem ser injetados como canais:

```
@Inject @Channel("prices") Multi<Double> streamOfPayloads;

@Inject @Channel("prices") Multi<Message<Double>> streamOfMessages;

@Inject @Channel("prices") Publisher<Double> publisherOfPayloads;

@Inject @Channel("prices") Publisher<Message<Double>> publisherOfMessages;
```

- Injetar @Channel("prices")ou ter @Incoming("prices") não configura automaticamente o aplicativo para consumir mensagens do Kafka. Você precisa configurar um conector de entrada mp.messaging.incoming.prices... ou ter um @Outgoing("prices")método em algum lugar de seu aplicativo (nesse caso, price sserá um canal na memória).

### Recebendo Registros em Lote

- Por padrão, os métodos recebidos recebem cada registro Kafka individualmente.
- No modo batch , seu aplicativo pode receber todos os registros retornados pela pesquisa do consumidor de uma só vez. Para conseguir isso, você precisa especificar um tipo de contêiner compatível para receber todos os dados:

```
@Incoming("prices")
public void consume(List<Double> prices) {
    for (double price : prices) {
        // process price
    }
}
```

- O método de entrada também pode receber tipos Message<List<Payload>>, KafkaRecordBatch<Key, Payload> e ConsumerRecords<Key, Payload>Eles dão acesso aos detalhes do registro, como deslocamento ou carimbo de data/hora:

```
@Incoming("prices")
public CompletionStage<Void> consumeMessage(KafkaRecordBatch<String, Double> records) {
    for (KafkaRecord<String, Double> record : records) {
        String payload = record.getPayload();
        String topic = record.getTopic();
        // process messages
    }
    // ack will commit the latest offsets (per partition) of the batch.
    return records.ack();
}
```

- Observe que o processamento bem-sucedido do lote de registro de entrada confirmará os deslocamentos mais recentes para cada partição recebida dentro do lote. A estratégia de confirmação configurada será aplicada apenas a esses registros.

- Por outro lado, se o processamento lançar uma exceção, todas as mensagens serão nacked , aplicando a estratégia de falha para todos os registros dentro do lote.

- O Quarkus detecta automaticamente os tipos de lote para canais de entrada e define a configuração do lote automaticamente. Você pode configurar o modo de lote explicitamente com a propriedade mp.messaging.incoming.$channel.batch .

## Enviando Mensagens para o Kafka

- A configuração dos canais de saída do conector Kafka é semelhante à dos canais de entrada:

```
%prod.kafka.bootstrap.servers=kafka:9092
mp.messaging.outgoing.prices-out.connector=smallrye-kafka
mp.messaging.outgoing.prices-out.topic=prices
```

- Por padrão, o nome do tópico é igual ao nome do canal. Você pode configurar o atributo do tópico para substituí-lo.
  - mp.messaging.outgoing.prices-out.topic=prices

> Importante: Dentro da configuração do aplicativo, os nomes dos canais são exclusivos. Portanto, se você quiser configurar um canal de entrada e saída no mesmo tópico, precisará nomear os canais de maneira diferente (como nos exemplos deste guia mp.messaging.incoming.pricese mp.messaging.outgoing.prices-out).

```
import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.time.Duration;
import java.util.Random;

@ApplicationScoped
public class KafkaPriceProducer {

    private final Random random = new Random();

    @Outgoing("prices-out")
    public Multi<Double> generate() {
        // Build an infinite stream of random prices
        // It emits a price every second
        return Multi.createFrom().ticks().every(Duration.ofSeconds(1))
            .map(x -> random.nextDouble());
    }

}
```

> Importante: Você não deve chamar métodos anotados com @Incominge/ou @Outgoingdiretamente do seu código. Eles são invocados pela estrutura. Ter o código do usuário invocando-os não teria o resultado esperado.

- Observe que o generatemétodo retorna um , que implementa a interface Multi<Double>Reactive Streams . PublisherEste editor será usado pela estrutura para gerar mensagens e enviá-las para o tópico Kafka configurado.

- Em vez de retornar um payload, você pode retornar um io.smallrye.reactive.messaging.kafka.Recordpara enviar pares chave/valor:

```
@Outgoing("out")
public Multi<Record<String, Double>> generate() {
    return Multi.createFrom().ticks().every(Duration.ofSeconds(1))
        .map(x -> Record.of("my-key", random.nextDouble()));
}
```

### Enviando Mensagens com Emitter

> Usando o Emittervocê está enviando mensagens de seu código imperativo para mensagens reativas. Essas mensagens são armazenadas em uma fila até serem enviadas. Se o cliente produtor do Kafka não conseguir acompanhar as mensagens que tentam ser enviadas para o Kafka, essa fila pode se tornar um devorador de memória e você pode até ficar sem memória. Você pode usar @OnOverflow para configurar a estratégia de contrapressão. Permite configurar o tamanho da fila (o padrão é 256) e a estratégia a ser aplicada quando o tamanho do buffer é atingido.

> Emitter é uma maneira imperativa de enviar mensagens. Lembre-se que quando usamos @Incoming e @Outgoing esses métodos não são invocodas pelo código, eles reagem a um evento. No caso do @Outgoing o método não possui parâmetros, lembre-se que é um método produtor de eventos, mas podemos ter que enviar uma mensagem para um stream após receber uma solicitação POST, esse é um exemplo do uso do Emitter.

```
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

@Path("/prices")
public class PriceResource {

    @Inject
    @Channel("price-create")
    Emitter<Double> priceEmitter;

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public void addPrice(Double price) {
        CompletionStage<Void> ack = priceEmitter.send(price);
    }
}
```

- Com a EmitterAPI, você também pode encapsular a carga de saída dentro de arquivos Message<T>. Assim como nos exemplos anteriores, Messagepermite que você trate os casos de ACK/NAK de forma diferente.

```
import java.util.concurrent.CompletableFuture;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

@Path("/prices")
public class PriceResource {

    @Inject @Channel("price-create") Emitter<Double> priceEmitter;

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public void addPrice(Double price) {
        priceEmitter.send(Message.of(price)
            .withAck(() -> {
                // Called when the message is acked
                return CompletableFuture.completedFuture(null);
            })
            .withNack(throwable -> {
                // Called when the message is nacked
                return CompletableFuture.completedFuture(null);
            }));
    }
}
```

> depreciação >> As classes io.smallrye.reactive.messaging.annotations.Emitter, io.smallrye.reactive.messaging.annotations.Channele io.smallrye.reactive.messaging.annotations.OnOverflowagora estão obsoletas e substituídas por:
> org.eclipse.microprofile.reactive.messaging.Emitter
> org.eclipse.microprofile.reactive.messaging.Channel
> org.eclipse.microprofile.reactive.messaging.OnOverflow
> O novo Emitter.sendmétodo retorna um CompletionStagecomplete quando a mensagem produzida é confirmada.

# Referência

https://quarkus.io/guides/kafka
