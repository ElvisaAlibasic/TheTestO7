package base.service.api;

import java.util.LinkedList;
import java.util.List;

import base.service.api.dto.IPriorityListEntry;

/**
 * API interface for base service that is  used to retrieve add priority list provided by external system via {@link
 * #updatePriorityList(LinkedList)} method. Service also executes additional post filtering of the list based on
 * provided criteria and by request provides it to other endpoints via {@link #getPriorityList(String)} method.
 *
 * @author Elvisa Alibasic
 * @since 1.0.0
 */
public interface IBaseService
{
    LinkedList<IPriorityListEntry> getRecommendedSDK(String platform, String osVersion, String appName,
        String appVersion, String countryCode) throws BaseServiceException;

    /**
     * Retrieve priority list based on provided {@code countryCode}.
     *
     * @param countryCode required {@link String} ISO-3166 alpha-2 country code.
     * @return {@link List} of {@link IPriorityListEntry entries} prioritised by {@link IPriorityListEntry#getScore()}
     * and filtered by proposed criteria.
     * @throws BaseServiceException in case of connection failure or invalid parameters.
     */
    LinkedList<IPriorityListEntry> getPriorityList(String countryCode) throws BaseServiceException;

    /**
     * Method used to update existing priority list, if there is none method creates it.
     *
     * @param priorityList A required {@link LinkedList} of data to be stored.
     * @throws BaseServiceException in case of connection failure or invalid parameters.
     */
    void updatePriorityList(LinkedList<IPriorityListEntry> priorityList) throws BaseServiceException;
}
