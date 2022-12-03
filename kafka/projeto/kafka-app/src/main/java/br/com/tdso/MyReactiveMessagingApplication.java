package br.com.tdso;

import io.quarkus.runtime.StartupEvent;
import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.reactive.messaging.*;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.time.Duration;
import java.util.Collection;
import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class MyReactiveMessagingApplication {

    Logger logger = Logger.getLogger("MyReactiveMessagingApplication.class");

    @Inject
    @Channel("words-out")
    Emitter<String> emitter;

    private final Random random = new Random();

    /**
     * Sends message to the "words-out" channel, can be used from a JAX-RS resource or any bean of
     * your application. Messages are sent to the broker.
     **/
    void onStart(@Observes StartupEvent ev) {
        logger.info("Iniciou processamento ...");

        Multi.createFrom().ticks().every(Duration.ofSeconds(1))
                .map(sinal -> {
                    System.out.println("Generated number: " + sinal);
                    return emitter.send(this.random.nextInt(100) + "");
                })
                .subscribe().with(x -> System.out.println(x));
    }

    /**
     * Consume the message from the "words-in" channel, uppercase it and send it to the uppercase
     * channel. Messages come from the broker.
     **/
    @Incoming("words-in")
    @Outgoing("uppercase")
    public Message<String> toUpperCase(Message<String> message) {
        logger.info(("Recebendo da fila do kafka = " + message.getPayload()));
        return message.withPayload(message.getPayload().toUpperCase());
    }

    /**
     * Consume the uppercase channel (in-memory) and print the messages.
     **/
    @Incoming("uppercase")
    public void sink(String word) {
        logger.info(("Recebendo da fila IN MEMORY = " + word));
        System.out.println(">> " + word);
    }
}
