package service.impl;

import rest.client.IAdMediationRestClient;

/**
 * Base service used to retrieve add priority list provided by external system. Service also executes additional post
 * filtering of the list based on provided criteria.
 *
 * @since 1.0.0
 */
public class BaseService implements IAdMediationRestClient
{
    public String getPriorityList(String imdbId)
    {
        return null;
    }

    public String updatePriorityList(String imdbId)
    {
        return null;
    }
}

