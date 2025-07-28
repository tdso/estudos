## Introdução
https://myfear.substack.com/p/reactive-java-quarkus-mutiny-tutorial

multi
    .onItem().call(i ->
        Uni.createFrom().voidItem()
            .onItem().delayIt().by(Duration.ofSeconds(1)
    )
);

https://www.the-main-thread.com/p/real-time-streaming-quarkus-java-reactive

