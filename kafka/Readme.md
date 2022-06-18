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

## Conceitos

- quando rodamos o programa que simula o consumer (listener) faltou configurar o grupo - quando criamos um consumer, temos que criar um grupo - dentro do grupo apenas uma instância receberá as mensagens.
