package persistence.google.datastore;

import java.util.LinkedList;

import base.service.api.dto.IPriorityListEntry;

public interface IDataStoreClient
{
    int updatePriorityList(LinkedList<IPriorityListEntry> priorityList);

    LinkedList<IPriorityListEntry> getPriorityList(int adTypeIdentifier, String countryCode);
}
