package rest.client;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import rest.client.impl.dto.PriorityListRestEntry;

import static rest.client.impl.IPropertyFlags.QUERY_PARAM_AD_TYPE_IDENTIFIER;
import static rest.client.impl.IPropertyFlags.QUERY_PARAM_COUNTRY_CODE;

public interface IAdMediationRestClient
{
    @GET
    @Path("/getInfo")
    @Produces({ MediaType.APPLICATION_JSON })
    List<PriorityListRestEntry> getPriorityList(@QueryParam(QUERY_PARAM_AD_TYPE_IDENTIFIER) int adTypeIdentifier,
        @QueryParam(QUERY_PARAM_COUNTRY_CODE) String countryCode);

    @POST
    @Path("/updateList")
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    Response updatePriorityList(List<PriorityListRestEntry> priorityList);

}
