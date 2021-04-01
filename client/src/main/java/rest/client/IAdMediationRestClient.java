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

import base.service.api.dto.IPriorityListEntry;
import rest.client.impl.dto.PriorityListRestEntry;

import static base.service.api.IPropertyFlags.QUERY_PARAM_APP_NAME;
import static base.service.api.IPropertyFlags.QUERY_PARAM_APP_VERSION;
import static base.service.api.IPropertyFlags.QUERY_PARAM_COUNTRY_CODE;
import static base.service.api.IPropertyFlags.QUERY_PARAM_OS_VERSION;
import static base.service.api.IPropertyFlags.QUERY_PARAM_PLATFORM;

/**
 * Resteasy client interface specifying possible operations to be forwarded to the system.
 * <p>
 * Data is provided to the system via {@link #updatePriorityList(List)} method. Method is meant to be used by an
 * external pipeline system via a batch job. Resource vise it is the most complex method in the system, thus is not
 * designed for frequent execution with large datasets.
 * <p>
 * Via the {@link #getPriorityList} client is provided with raw prioritised list as recommended by external pipeline
 * system. Additional filtering is not done by ANY business logic. It was mainly added for debugging purposes to analyze
 * filtering done by the system.
 * <p>
 * Recommended priority list is retrieved via {@link #getRecommendedSDK(String, String, String, String, String)}, where
 * raw list retrieved via {@link #getPriorityList(String)} is put through series of filtering in order to make the
 * prioritization as successful as possible. The final list is provided with a score, based on which client can decide
 * which SDK to show and how.
 *
 * @author Elvisa Alibasic
 * @since 1.0.0
 */
public interface IAdMediationRestClient
{
    /**
     * Retrieve prioritised list of possible SDKs and recommended ad type. Prioritising is done via {@link
     * IPriorityListEntry#getScore()} parameter.
     * <p>
     * Currently BL filters out SDKs that are  banned consequently banned for a provided {@code countryCode}. Also,BL
     * insures that selected SDK and ad type work on provided {@code osPlatform} and {@code osVersion}.
     *
     * @param platform    required {@link String} platform where the client is running. TODO add possible values to
     *                    {@link base.service.api.IPropertyFlags}.
     * @param osVersion   required {@link String} operation system version.
     * @param appName     required {@link String} application name which will based on method result display the
     *                    recommended add.
     * @param appVersion  required {@link String} version of the client app.
     * @param countryCode required ISO-3166 alpha 2 country code.
     * @return {@link Response} that contain prioritised list based on provided criteria.
     */
    @GET
    @Path("/sdk")
    @Produces({ MediaType.APPLICATION_JSON })
    Response getRecommendedSDK(@QueryParam(QUERY_PARAM_PLATFORM) String platform,
        @QueryParam(QUERY_PARAM_OS_VERSION) String osVersion, @QueryParam(QUERY_PARAM_APP_NAME) String appName,
        @QueryParam(QUERY_PARAM_APP_VERSION) String appVersion,
        @QueryParam(QUERY_PARAM_COUNTRY_CODE) String countryCode);

    /**
     * Method to retrieve raw list of recommended SDKs for certain country as proposed by the external pipeline system.
     * No additional filtering is done ba any business logic. Method added for development purposes.
     *
     * @param countryCode required ISO-3166 alpha 2 country code.
     * @return {@link Response} that contain prioritised list based on provided criteria.
     */
    @GET
    @Path("/rawList")
    @Produces({ MediaType.APPLICATION_JSON })
    Response getPriorityList(@QueryParam(QUERY_PARAM_COUNTRY_CODE) String countryCode);

    /**
     * Method intended to be used by 3rd party system to provide scoring of SDKs per ad type and country.
     *
     * @param priorityList required {@link List} to be stored and filtered in later BL
     * @return {@link Response} with status carrying information regarding operation success.
     */
    @POST
    @Path("/list")
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    Response updatePriorityList(List<PriorityListRestEntry> priorityList);

}
