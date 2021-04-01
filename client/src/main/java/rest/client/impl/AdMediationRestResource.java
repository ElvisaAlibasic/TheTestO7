package rest.client.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import base.service.api.IPropertyFlags;
import base.service.api.dto.IPriorityListEntry;
import base.service.api.dto.impl.PriorityListEntry;
import base.service.impl.BaseManager;
import rest.client.IAdMediationRestClient;
import rest.client.impl.dto.PriorityListRestEntry;

@Path("/adMediation")
@Produces(MediaType.APPLICATION_JSON)
@Consumes({ MediaType.APPLICATION_JSON })
public class AdMediationRestResource implements IAdMediationRestClient
{
    // TODO this is wrong, service should be accessed via service orchestration
    BaseManager baseManager = new BaseManager();

    static HashMap<Integer, String> MAP_AD_ID_TO_NAME;
    static HashMap<String, Integer> MAP_AD_NAME_TO_ID;
    static HashMap<Integer, String> MAP_TYPE_ID_TO_TYPE_NAME;
    static HashMap<String, Integer> MAP_TYPE_NAME_TO_TYPE_ID;

    static
    {
        // TODO this should be in DB
        HashMap<Integer, String> adIdToNameMap = new HashMap<>();
        HashMap<String, Integer> adNameToIdMap = new HashMap<>();

        adIdToNameMap.put(IPropertyFlags.SDK_ADX_IDENTIFIER, IPropertyFlags.SDK_ADX_NAME);
        adIdToNameMap.put(IPropertyFlags.SDK_AD_MOB_IDENTIFIER, IPropertyFlags.SDK_AD_MOB_NAME);
        adIdToNameMap.put(IPropertyFlags.SDK_UNITY_ADS_IDENTIFIER, IPropertyFlags.SDK_UNITY_ADS_NAME);
        adIdToNameMap.put(IPropertyFlags.SDK_FACEBOOK_IDENTIFIER, IPropertyFlags.SDK_FACEBOOK_NAME);
        adIdToNameMap.put(IPropertyFlags.SDK_IRON_SOURCE_IDENTIFIER, IPropertyFlags.SDK_IRON_SOURCE_NAME);

        adNameToIdMap.put(IPropertyFlags.SDK_ADX_NAME, IPropertyFlags.SDK_ADX_IDENTIFIER);
        adNameToIdMap.put(IPropertyFlags.SDK_AD_MOB_NAME, IPropertyFlags.SDK_AD_MOB_IDENTIFIER);
        adNameToIdMap.put(IPropertyFlags.SDK_UNITY_ADS_NAME, IPropertyFlags.SDK_UNITY_ADS_IDENTIFIER);
        adNameToIdMap.put(IPropertyFlags.SDK_FACEBOOK_NAME, IPropertyFlags.SDK_FACEBOOK_IDENTIFIER);
        adNameToIdMap.put(IPropertyFlags.SDK_IRON_SOURCE_NAME, IPropertyFlags.SDK_IRON_SOURCE_IDENTIFIER);

        MAP_AD_ID_TO_NAME = new HashMap<>(adIdToNameMap);
        MAP_AD_NAME_TO_ID = new HashMap<>(adNameToIdMap);

        HashMap<Integer, String> typeIdToNameMap = new HashMap<>();
        HashMap<String, Integer> typeNameToIdMap = new HashMap<>();

        typeIdToNameMap.put(IPropertyFlags.AD_TYPE_BANNER_ID, IPropertyFlags.AD_TYPE_BANNER_NAME);
        typeIdToNameMap.put(IPropertyFlags.AD_TYPE_INTERSTITIAL_ID, IPropertyFlags.AD_TYPE_INTERSTITIAL_NAME);
        typeIdToNameMap.put(IPropertyFlags.AD_TYPE_REWARDED_VIDEO_ID, IPropertyFlags.AD_TYPE_REWARDED_VIDEO_NAME);

        typeNameToIdMap.put(IPropertyFlags.AD_TYPE_BANNER_NAME, IPropertyFlags.AD_TYPE_BANNER_ID);
        typeNameToIdMap.put(IPropertyFlags.AD_TYPE_INTERSTITIAL_NAME, IPropertyFlags.AD_TYPE_INTERSTITIAL_ID);
        typeNameToIdMap.put(IPropertyFlags.AD_TYPE_REWARDED_VIDEO_NAME, IPropertyFlags.AD_TYPE_REWARDED_VIDEO_ID);

        MAP_TYPE_ID_TO_TYPE_NAME = new HashMap<>(typeIdToNameMap);
        MAP_TYPE_NAME_TO_TYPE_ID = new HashMap<>(typeNameToIdMap);
    }

    @Override
    public String getRecommendedSDK(String platform, String osVersion, String appName, String appVersion,
        String countryCode)
    {
        return String.valueOf(baseManager.getRecommendedSDK(platform, osVersion, appName, appVersion, countryCode));
    }

    @Override
    public List<PriorityListRestEntry> getPriorityList(String countryCode)
    {
        List<PriorityListRestEntry> resultList = new LinkedList<>();

        LinkedList<IPriorityListEntry> priorityListEntries = baseManager.getPriorityList(countryCode);

        for (IPriorityListEntry entry : priorityListEntries)
        {
            resultList.add(new PriorityListRestEntry(MAP_AD_ID_TO_NAME.get(entry.getSkdIdentifier()),
                MAP_TYPE_ID_TO_TYPE_NAME.get(entry.getAdTypeIdentifier()), entry.getCountryCode(), entry.getScore()));
        }

        return resultList;
    }

    public Response updatePriorityList(List<PriorityListRestEntry> priorityList)
    {

        LinkedList<IPriorityListEntry> convertedList = new LinkedList<>();

        for (PriorityListRestEntry entry : priorityList)
        {
            convertedList.add(new PriorityListEntry(MAP_AD_NAME_TO_ID.get(entry.getSkdName()),
                MAP_TYPE_NAME_TO_TYPE_ID.get(entry.getAdTypeName()), entry.getCountryCode(), entry.getScore()));
        }

        try
        {
            LinkedList<IPriorityListEntry> list = baseManager.updatePriorityList(convertedList);
            return Response.status(202).build();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return Response.status(500).build();
        }

    }
}