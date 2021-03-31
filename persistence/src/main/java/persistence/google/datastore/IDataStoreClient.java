package persistence.google.datastore;

import java.util.LinkedList;

import base.service.api.dto.IPriorityListEntry;

/**
 * API representation of persistence layer.
 *
 * @author Elvisa Alibasic
 * @since 1.0.0
 */
public interface IDataStoreClient
{
    /**
     * Method used to update priority list. Existing entries are replaced by ones provided via {@code priorityList}
     * parameter. In case of creating a new entity, identifier is autogenerated by Datastore. Method returns {@link
     * Boolean#TRUE} to indicate that operation is completed successfully, {@link Boolean#FALSE} otherwise.
     *
     * @param priorityList required {@link LinkedList} of {@link IPriorityListEntry} data to be stored.
     * @return {@link Boolean} flag.
     */
    boolean updatePriorityList(LinkedList<IPriorityListEntry> priorityList);

    /**
     * Method used to retrieve priority list of particular SDK's depending on provided criteria. Priority is ranked in
     * descending order, sorting is done based on {@link IPriorityListEntry#getScore()} attribute.
     *
     * @param adTypeIdentifier required {@link Integer} identifier of ad type.
     * @param countryCode      required {@link String} ISO-3166 standard country code.
     * @return {@link LinkedList} of {@link IPriorityListEntry} prioritized list.
     */
    LinkedList<IPriorityListEntry> getPriorityList(int adTypeIdentifier, String countryCode);
}
