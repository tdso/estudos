package br.com.tdso;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/hello")
public class GreetingResource {

    // passado cmo parametro no momento da execucao
    // mvn quarkus:dev -Dsenha=1234
    // @ConfigProperty(name = "senha")
    private String senha = "";

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy = " + senha;
    }
}
