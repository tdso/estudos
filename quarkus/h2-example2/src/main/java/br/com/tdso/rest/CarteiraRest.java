package br.com.tdso.rest;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import br.com.tdso.client.ClientService2;
import br.com.tdso.model.Carteira;
import br.com.tdso.service.CarteiraService;

@Path("/ctr")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CarteiraRest {

    @Inject
    @RestClient
    ClientService2 service2;

    @Inject
    CarteiraService carteiraService;

    @GET
    public List<Carteira> getCarteira() {

        return carteiraService.getCarteira();
    }

    @Path("/ext/{a}/{b}")
    @GET
    public Response getDivision(@PathParam("a") int a, @PathParam("b") int b) {
        int c = a + b;
        System.out.println("Param c = " + c);
        System.out.println("Param a = " + a + " param b = " + b);
        return service2.getDivider(a, b);
    }

    /*
     * @GET
     * 
     * @Path("{id}") public Response getCarteiraById(@PathParam("id") Long id) { Carteira c =
     * carteiraService.getCarteiraById(id); if (c == null) { return
     * Response.status(Status.BAD_REQUEST).entity("Carteira nao encontrada !").build(); } return
     * Response.status(Status.OK).entity(c).build(); }
     */
    @PUT
    public Carteira putCarteira(Carteira c) {

        return carteiraService.putCarteira(c);
    }

    @POST
    public Carteira postCarteira(Carteira c) {

        return carteiraService.postCarteira(c);
    }



}
