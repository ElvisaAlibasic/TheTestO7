package base.service.impl;

import java.util.LinkedList;

import base.service.api.IBaseService;
import base.service.api.dto.IPriorityListEntry;
import persistence.google.datastore.impl.DataStoreService;

/**
 * Base service used to retrieve add priority list provided by external system. Service also executes additional post
 * filtering of the list based on provided criteria.
 *
 * @author Elvisa Alibasic
 * @since 1.0.0
 */
public class BaseManager implements IBaseService
{
    DataStoreService dataStoreService = new DataStoreService();

    private LinkedList<IPriorityListEntry> priorityList;

    public BaseManager()
    {
        // should be removed once service orchestration is implemented
    }

    @Override
    public LinkedList<IPriorityListEntry> getPriorityList(int adTypeIdentifier, String countryCode)
    {
        return dataStoreService.getPriorityList(adTypeIdentifier, countryCode);
    }

    @Override
    public LinkedList<IPriorityListEntry> updatePriorityList(LinkedList<IPriorityListEntry> priorityList)
        throws Exception
    {
        this.priorityList = priorityList;

        dataStoreService.updatePriorityList(priorityList);
        return priorityList;
    }
}

