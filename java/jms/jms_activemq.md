# JMS e ActiveMQ

APL1 -------> Middleware <------> APL2
Ex e-commerce ------> broker <--------------> NF-e

- não dependo do desempenho de outra aplicação, no caso acima da NF-e
- não me interessa saber se o outro sistema está ON
- garantia de desacoplamento e assincronismo

ActiveMQ

- broker de mensageria (no projeto de testes usamos a versão 5.16.3-bin.zip)
- nas pasta bin:
    - sh activemq
    - sh activemq console (no console tem o ip do host), normalmente localhost:8161 (ou 127.0.0.1:8161), senha/password: admin/admin

## API JMS

<b>1 - Configuração Inicial</b>

 - adicionar no projeto, na pasta lib, a biblioteca activemq-all-5.16.3.jar - adicioná-lo no build path

 - adicionar o arquivo JNDI.properties na pasta resource (projeto maven)
 - no arquivo JNDI configuramos o nome da fila

 - no site ActiveMQ temos o exemplo de um arquivo JNDI, basta copiá-lo
    - temos que fazer algumas alterações nesse arquivo:
    - alterar a propriedade para apontar para localhost(em alguns casos usar 127.0.0.1 ao invés de localhost), manter a porta 61616;

<b>2 - criando a conexão</b>
    InitialContext context = new InitialContext();
    ConnectionFactory factory = context.lookup("ConnectionFactory");
    Connection connection = factory.createConnection();
    connection.start();

<b>4 - Definindo um consumidor</b>
    
    - primeiro criar uma Session
        Session session = conection.createSession(boolean, Session.Auto_Acknowledge);
            // boolean = false, não usa transação (não precisa session.commit() e session.rollback())
            // Session.Auto_Acknowledge = confirma recebimento automático
        
        **
        Acknowlegde: quando cria uma sessão podemos usar outros valores para confirma o recebimento da mensagem além do Session.Auto_Acknowledge:
        
        - Session.ClientAck = o consumer tem que confirmar manualmente o recebimento da mensagem;
        
        - Session.Transacted = para obtermos uma session com comportamento transacional, além disso o primeiro parâmetro desse método deve ser true.
        Nessa opção usaremos os métodos session.commit() e session.rollback().

        - Session.Dups_ok_Acknowledge = aceita ack para N mensagens, mas pode ocorrer o envio de mensagens duplicadas.

        **
    
    - Cria uma fila
        Destination fila = context.lookup("nome_fila");
            // nome_fila = conforme consta no arquivo JNDI
            // no arquivo JNDI o lado direito do sinal de igual aponta para o
            // nome físico de uma fila ou tópico no broker - ex:
            // queue.financeiro = fila.financeiro

    - Cria um Consumer
        - Consumer consumer = session.createConsumer(fila);

    - Cria objeto Message para receber a mensagem
        Message msg = consumer.receive(); // consome 1 mensagem
        // podemos fornecer como parâmetro no receive um valor para timeout, isto // é, se não receber nenhuma mensagem em X segundos, finaliza.

<b>5 - Fechando a conexão e o contexto</b>
    connection.close();
    context.close();

- Como vimos no exemplo acima o método receive() recebe uma mensagem ! Como podemos deixar o nosso processo sempre escutando o broker ? 
- Com um listener !! Podemos configurar o nosso listener no nosso consumidor:
```
    consumer.setMessageListener (new Message(){
        public void onMensage(Mensage msg){

        }
    })
```
<b>6 - Configurando o Produtor</b>
```
    Producer producer = session.createProducer(fila);
    TextMessage msg = session.createTextMensage(objeto/texto mensagem);
    producer.send();
```
- A fila entrega a mensagem apenas para 1 consumidor, se tiver mais de 1, o broker divide a carga mas uma mensagem será entregue a apenas 1 consumidor.

<b>7 - Tópicos e Assinaturas Duráveis</b>

- Tópicos: entrega a mensagem para todos os assinantes (listeners).

- Para usar tópicos no arquivo JNDI temos que incluir a configuração do Tópico:
    topic.loja = topico.loja (o lado direito tem que bater com o nome definido no arquivo JNDI)

- No código a mudança ocorre no lookup que deve apontar para o nome do tópico.

- Até agora vimos que o consumidor recebe as mensagens se estiver online, caso não esteja rodando vai perder as mensagens. Para o broker guardar as mensagens para o consumidor temos que configurar no Java:

    - setar um id para a conexão para que o broker registre que essa conexão é durável:
            InitialContext context = new InitialContext();
            ConnectionFactory factory = context.lookup("ConnectionFactory");
            Connection connection = factory.createConnection();
            connection.setCliendId("client-nf");
    
    - criar uma assinatura durável no MessageConsumer:
        MessageConsumer consumer = session.createDurableSubscriber(topico, nome_assinatura);

    - é necessário rodar 1 vez para o broker registrar a assinatura, em seguida interrompa a execução, rode o produtor, e agora rode o consumidor e verifique se a mensagem foi consumida.

Conclusões:
    1 - envio de mensagens para todos os consumidores (broadcast) - use tópicos
    2 - o broker guarda as mensagens para os consumidores registrados / identificados , ou seja, o broker tem que saber da existência do consumidor.

<b>8 - Seletores</b>

- propriedades da mensagem;
- independe do destino, se fila ou tópico;
- definimos critérios na assinatura do consumidor para desprezar algum tipo de mensagem;

- MessageSelector
    - não pode referenciar valores no corpo da mensagem;
    - os seletores são enviados no cabeçalho da mensagem, são como propriedades da mensagem;

- Consumidor
    - configuração:
    MessageConsumer consumer = session.createDurableSubscriber(topico, nome_assinatura, "ebook=false", boolean);
    // so vai receber mensagem que possui o atributo (seletor) ebook = false
    // e se não tiver o atributo, também, não recebe.
    // boolean = esse atributo indica se queremos usar a mesma conexão para 
    // enviar/receber mensagens, no nosso caso que temos um programa para receber // e outro para enviar, o valor é false;

- Produtor
    - setar um atributo na Messagem, ex:
        message.setBooleanProperty("ebook", false);

<b>9 - Object Message e DLQ</b>

- XML padrão comum criado noas trocas de mensagens;
- fazemos a conversão entre XML <> Objeto
- JAXB > java.xml.bind
- transformar uma classe em XML:
    - adicionamemos a anotação: @XmlRootElement para indicar que a classe vai ser a raiz do XML
    - e a anotação @XmlAccessorType(XmlAccessType.FIELD) por default o JAXB acessa os métodos get/set, mas se não os tiver acessa os atributos.
    - se a classe tiver um List devemos incluir as anotações para Lista:
        @XmlElementWrapper(nome="itens")
        @XmlElement(nome="item")
        private Set<Item> itens = new Link ...
        // cada item será referenciado como item no XML
        // a lista será referenciada como itens

- se o consumidor e o produtor forem Java, poderia enviar o objeto java para fila ou tópico ? sim

- mas atente que:
    - as classes devem ser serializadas;
    - use o método createObjectMessage(objeto) - ex:
        session.createObjectMessage(objeto)
    - o consumidor, também, deve estar apto para receber um objeto, use o método getObject() para recuperar o objeto.

- Fila DLQ
    - quando por algum motivo o brojer não consegue entregar a mensagem, por padrão ele tenta por 6x, o broker coloca essas mensagens da fila DLQ (dead letter queue);

    - podemos acessar essa fila como as demais, desde que configuradas no arquivo JNDI;
