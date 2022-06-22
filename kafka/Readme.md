# Começando com o Kafka

## Instalação

1 - baixar o binário, descompactar na pasta desejada, já vem com zookeper

2 - rodar o zookeper (pre-req)
bin/zookeeper-server-start.sh config/zookeeper.properties

3- rodar o kafka
bin/kafka-server-start.sh config/server.properties

## Comandos Básicos

1 - criando topico chamado SINAL

- bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic SINAL

2 - listar topicos

- bin/kafka-topics.sh --list --bootstrap-server localhost:9092

3 - descrevendo os topicos

- bin/kafka-topics.sh --describe --bootstrap-server localhost:9092

4 - enviar msg's (usando o terminal do kafka)

- produtor > bin/kafka-console-producer.sh --broker-list localhost:9092 --topic SINAL
  > abre o terminal e cada linha digitada é uma mensagem

6 - receber msg's - 2 formas (consumer)

- comando abaixo vai consumir novas msg's

  - bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic SINAL

- comando abaixo vai consumir as msg's desde o inicio
  - bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic SINAL --from-beginning

7 - alterando o número de partições de um tópico

    - bin/kafka-topics.sh --alter --bootstrap-server localhost:9092 --topic B3_SINAL --partitions 4

8 - consultar os grupos de consumo

    - bin/kafka-consumer-groups.sh --all-groups --bootstrap-server localhost:9092 --describe

## Conceitos

- o Kafka é o processador das mensagens, de jogar de um lado para o outro, processador no sentido de conectar tudo;
- algumas informações básicas, o Kafka tem que armazenar em algum lugar e o lugar onde o Kafka armazena, por padrão, se chama zookeeper;
- zookeper controla o status dos nós do cluster kafka, acompanha tópicos, partições, armazena informações do estado do cluster, dados dos consumidores e o estado de outros intermediários;
- quando baixamos o binário do kafka, o zookeper está incluído;

Paralelizando o recebimento

- quando criamos um consumidor (consumer) temos que associá-lo a um grupo de consumo, o kafka enviará todas as mensagens para esse grupo, mas apenas uma instância dentro do grupo receberá a mensagem. Na situação de ter duas instâncias de um mesmo consumer rodando, e escutando o mesmo grupo, o kafka distribuirá as mensagens para cada instância, paralelizando o processamento.
- Para que o kafka consiga efetuar esse balanceamento das mensagens entre as instâncias, ele faz uso das partições. O tópico (ou a fila) tem que ser configurado para ter mais de uma partição. Por padrão, o arquivo server.properties está configurado para 1 partição para cada tópico.
- De nada adianta ter várias instâncias de um consumer rodando, e ter somente 1 partição. Uma instância do consumer será associado a uma partição e as outras duas instâncias não serão usadas para processamento. Para uma maior eficácia, o número de partições deve ser maior ou igual ao número de instâncias de consumer em execução.
- Detalhe importante: o kafka usa o id (key) da mensagem para distribui-las pelas partições existentes, então use um id diferente para cada mensagem para visualizar a distribuição das mesmas nas partições do tópico.
- Configuração importante: podemos definir a quantidade de mensagens que serão enviadas ao consumidor por vez. Em um cenário em que o kafka envie 1 mensagem de cada vez, o consumer recebe a mensagem, e após finalizar, envia a confirmação (commit) para o produtor que pode receber outra mensagem e uma nova mensagem é encaminhada para o consumer. Em nossos testes o essa configuração não estava para enviar 1 mensagem por vez, o consumer recebeu "X" mensagens, o que o deixou processando por um longo tempo, e antes de efetuar o commit, o kafka iniciou um rebalanceamento, e enviou novamente as mensagens para processamento. Para termos um commit rápido, setamos essa configuração (MAX_POLL_RECORDS_CONFIG) para 1.

## Questões
1 - não adianta ter um cluster de kafka e não ter do zookeper ...

## Bibliografia

- Kafka: The Definitive Guide - O'Reily
