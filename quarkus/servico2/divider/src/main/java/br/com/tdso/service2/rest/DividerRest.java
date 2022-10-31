package br.com.tdso.service2.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/ms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DividerRest {

  @GET
  public String live() {
    return "ok";
  }

  @GET
  @Path("/div/{a}/{b}")
  // public Response getDivider(@PathParam("a") int a , @PathParam("b") int b) {
  public Response getDivider(@PathParam("a") int a, @PathParam("b") int b) {


    System.out.println(" ");
    System.out.println("chegou aqui no outro MS");

    if (a == 0 && b == 0) {
      return Response.status(Status.BAD_REQUEST).entity("Divisao nao permitida !").build();
    }
    return Response.status(Status.OK).entity(a / b).build();

  }

}
