## Mensagens reativas e Streams

- Projeto: ws-quarkus/reatividade - ilustra os conceitos abaixo

- Extensão: Eclipse Microprofile Reactive Messaging > projetado para aplicações orientada a eventos, fluxos de dados e event-sourcing

- Arquitetura microserviços orientado a mensagens

  - Paradigma:
    - antes: qual microserviço vou chamar ?
    - agora: quais eventos meu ms processa ? quais eventos meu ms vai emitir ?

- Anotações:

  - @Outgoing - para enviar msgs
  - @Incoming - para receber msgs
  - @Broadcast - dispara msgs para vários assinantes
  - @Channel - indica qual canal deve ser injetado

- Quarkus Dev Services: starta automático o container Kafka quando roda no modo DEV

  - se o dev inclui uma extensão e não a configura, o Quarkus inicia automaticamente
    o serviço (nos bastidores usa o TestContainers) e conecta seu aplicativo para usar serviço.

- Lembrando que o Quarkus suporta o provisionamento automático de serviços não configurados no modo de desenv e teste por meio do recurso Dev Services.

# Análise do Projeto acima

- certificar-se das dependências corretas no pom
- aplication-properties
  - configurar as filas para o kafka > padrão:
    - fila para entrada (read)
    - fila para saida (write - gravamos)
- criamos uma classe que mapeia a fila de leitura e a fila de saida foi configurada
  in Memory - repare que a classe possui escopo de aplicação

- classe Rest - mapeia a fila in Memory para receber o dado @Channel e produz um MediaType Server_Sent_Events que envia dados para o front

- Ref: quarkus.io/guides/dev-services

# Observação

- Interface Publisher<T>
- Publish - provedor ilimitado de elementos sequenciados, publicando-os de acordo com a demanda recebida de seu Subscriber.
- método subscribe (Subscriber) - recebe um assinante - solicitação para o Publish iniciar a trasmissão dos dados.
