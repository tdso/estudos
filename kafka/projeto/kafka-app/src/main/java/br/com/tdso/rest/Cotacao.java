package br.com.tdso.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.jboss.resteasy.reactive.RestStreamElementType;
import org.reactivestreams.Publisher;
import io.smallrye.mutiny.Multi;

@Path("/cot")
public class Cotacao {

    @Inject
    @Channel("words-in")
    Multi<String> word;

    @GET
    // @Produces(MediaType.SERVER_SENT_EVENTS)
    @RestStreamElementType(MediaType.TEXT_PLAIN)
    public Publisher<String> stream() {
        return word;
    }

}
