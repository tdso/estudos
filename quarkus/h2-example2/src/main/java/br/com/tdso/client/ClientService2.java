package br.com.tdso.client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

// @ApplicationScoped
@RegisterRestClient
@Path("/ms")
public interface ClientService2 {

  @Path("/div/{a}/{b}")
  @GET
  public Response getDivider(@PathParam("a") int a, @PathParam("b") int b);

}
