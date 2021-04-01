package base.service.api;

import java.util.LinkedList;

import base.service.api.dto.IPriorityListEntry;

/**
 * API interface for base service that is  used to retrieve add priority list provided by external system via {@link
 * #updatePriorityList(LinkedList)} method. Service also executes additional post filtering of the list based on
 * provided criteria and by request provides it to other endpoints via {@link #getPriorityList(int, String)} method.
 *
 * @author Elvisa Alibasic
 * @since 1.0.0
 */
public interface IBaseService
{
    int getRecommendedSDK(String platform, String osVersion, String appName, String appVersion, String countryCode)
        throws BaseServiceException;

    /**
     * TODO
     *
     * @return
     */
    LinkedList<IPriorityListEntry> getPriorityList(String countryCode) throws BaseServiceException;

    /**
     * Method used to update existing priority list, if there is none method creates it.
     *
     * @param priorityList A required {@link LinkedList} of data to be stored.
     * @return TODO
     */
    LinkedList<IPriorityListEntry> updatePriorityList(LinkedList<IPriorityListEntry> priorityList) throws Exception;
}
