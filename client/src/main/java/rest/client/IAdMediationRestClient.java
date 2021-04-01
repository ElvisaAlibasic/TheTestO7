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

import static base.service.api.IPropertyFlags.QUERY_PARAM_APP_NAME;
import static base.service.api.IPropertyFlags.QUERY_PARAM_APP_VERSION;
import static base.service.api.IPropertyFlags.QUERY_PARAM_COUNTRY_CODE;
import static base.service.api.IPropertyFlags.QUERY_PARAM_OS_VERSION;
import static base.service.api.IPropertyFlags.QUERY_PARAM_PLATFORM;

public interface IAdMediationRestClient
{
    @GET
    @Path("/sdk")
    @Produces({ MediaType.APPLICATION_JSON })
    String getRecommendedSDK(@QueryParam(QUERY_PARAM_PLATFORM) String platform,
        @QueryParam(QUERY_PARAM_OS_VERSION) String osVersion, @QueryParam(QUERY_PARAM_APP_NAME) String appName,
        @QueryParam(QUERY_PARAM_APP_VERSION) String appVersion,
        @QueryParam(QUERY_PARAM_COUNTRY_CODE) String countryCode);

    @GET
    @Path("/getRawList")
    @Produces({ MediaType.APPLICATION_JSON })
    List<PriorityListRestEntry> getPriorityList(@QueryParam(QUERY_PARAM_COUNTRY_CODE) String countryCode);

    @POST
    @Path("/updateList")
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    Response updatePriorityList(List<PriorityListRestEntry> priorityList);

}
