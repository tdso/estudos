package br.com.tdso.rest;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import br.com.tdso.model.Carteira;
import br.com.tdso.service.CarteiraService;

@Path("/ctr")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CarteiraRest {

    @Inject
    CarteiraService carteiraService;

    @GET
    public List<Carteira> getCarteira() {

        return carteiraService.getCarteira();
    }

    @PUT
    public Carteira putCarteira(Carteira c) {

        return carteiraService.putCarteira(c);
    }

    @POST
    public Carteira postCarteira(Carteira c) {

        return carteiraService.postCarteira(c);
    }



}
