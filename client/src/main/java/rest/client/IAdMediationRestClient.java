package rest.client;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import rest.client.impl.dto.PriorityListRestEntry;

public interface IAdMediationRestClient
{
    @GET
    @Path("/getInfo")
    @Produces({ MediaType.APPLICATION_JSON })
    List<PriorityListRestEntry>  getPriorityList();

    @POST
    @Path("/updateList")
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    Response updatePriorityList(List<PriorityListRestEntry> priorityList);

}
