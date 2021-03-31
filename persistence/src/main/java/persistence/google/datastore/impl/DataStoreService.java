package persistence.google.datastore.impl;

import java.util.LinkedList;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.CompositeFilter;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;

import base.service.api.dto.IPriorityListEntry;
import base.service.api.dto.impl.PriorityListEntry;
import persistence.google.datastore.IDataStoreClient;

public final class DataStoreService implements IDataStoreClient
{
    private static final String PRIORITY_LIST_ENTRY_KIND = "PriorityListEntry";
    private static final String PROPERTY_SDK_ID = "sdkId";
    private static final String PROPERTY_AD_TYPE_ID = "adTypeId";
    private static final String PROPERTY_COUNTRY_CODE = "countryCode";
    private static final String PROPERTY_SCORE = "score";

    private final Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    private final KeyFactory keyFactory = datastore.newKeyFactory().setKind(PRIORITY_LIST_ENTRY_KIND);

    @Override
    public int updatePriorityList(LinkedList<IPriorityListEntry> priorityList)
    {

        for (IPriorityListEntry entry : priorityList)
        {

            Query<Entity> query = Query.newEntityQueryBuilder().setKind(PRIORITY_LIST_ENTRY_KIND).setFilter(
                CompositeFilter.and(PropertyFilter.eq(PROPERTY_AD_TYPE_ID, entry.getAdTypeIdentifier()),
                    PropertyFilter.eq(PROPERTY_COUNTRY_CODE, entry.getCountryCode()),
                    PropertyFilter.eq(PROPERTY_SDK_ID, entry.getSkdIdentifier()))).setOrderBy(
                OrderBy.desc(PROPERTY_SCORE)).build();

            Entity priorityListEntry = null;
            QueryResults<Entity> queryResultEntityList = datastore.run(query);

            if (queryResultEntityList.hasNext())
            {
                priorityListEntry = queryResultEntityList.next();
            }

            Entity task;

            if (priorityListEntry == null)
            {
                /* we create the entry if it doesn't exist */

                // The Cloud Datastore key for the new entity
                Key taskKey = datastore.allocateId(keyFactory.newKey());

                // Prepares the new entity
                task = Entity.newBuilder(taskKey).set(PROPERTY_SDK_ID, entry.getSkdIdentifier()).set(
                    PROPERTY_AD_TYPE_ID, entry.getAdTypeIdentifier()).set(PROPERTY_COUNTRY_CODE,
                    entry.getCountryCode()).set(PROPERTY_SCORE, entry.getScore()).build();

            }
            else
            {
                //we update existing one
                task = Entity.newBuilder(priorityListEntry.getKey()).set(PROPERTY_SDK_ID,
                    priorityListEntry.getProperties().get(PROPERTY_SDK_ID)).set(PROPERTY_AD_TYPE_ID,
                    priorityListEntry.getProperties().get(PROPERTY_AD_TYPE_ID)).set(PROPERTY_COUNTRY_CODE,
                    priorityListEntry.getProperties().get(PROPERTY_COUNTRY_CODE)).set(PROPERTY_SCORE,
                    entry.getScore()).build();

            }

            // Saves the entity
            datastore.put(task);
        }

        return 1;
    }

    @Override
    public LinkedList<IPriorityListEntry> getPriorityList(int adTypeIdentifier, String countryCode)
    {
        LinkedList<IPriorityListEntry> resultList = new LinkedList<>();

        Query<Entity> query = Query.newEntityQueryBuilder().setKind(PRIORITY_LIST_ENTRY_KIND).setFilter(
            CompositeFilter.and(PropertyFilter.eq(PROPERTY_AD_TYPE_ID, adTypeIdentifier),
                PropertyFilter.eq(PROPERTY_COUNTRY_CODE, countryCode))).setOrderBy(
            OrderBy.desc(PROPERTY_SCORE)).build();

        QueryResults<Entity> queryResults = datastore.run(query);

        while (queryResults.hasNext())
        {
            Entity listEntity = queryResults.next();
            resultList.add(new PriorityListEntry(
                Integer.parseInt(listEntity.getProperties().get(PROPERTY_SDK_ID).get().toString()),
                Integer.parseInt(listEntity.getProperties().get(PROPERTY_AD_TYPE_ID).get().toString()),
                listEntity.getProperties().get(PROPERTY_COUNTRY_CODE).get().toString(),
                Integer.parseInt(listEntity.getProperties().get(PROPERTY_SCORE).get().toString())));
        }
        return resultList;
    }

}
