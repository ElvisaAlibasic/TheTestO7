package rest.client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("adMediation")
public interface IAdMediationRestClient
{
    @GET
    @Path("/getInfo")
    @Produces({ MediaType.APPLICATION_JSON })
    String getPriorityList(@QueryParam("id") String imdbId);

    @GET
    @Path("/updateList")
    @Produces({ MediaType.APPLICATION_JSON })
    String updatePriorityList(@QueryParam("id") String imdbId);
}
