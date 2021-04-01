package base.service.impl;

import java.util.LinkedList;
import java.util.Objects;
import java.util.stream.Collectors;

import base.service.api.BaseServiceException;
import base.service.api.IBaseService;
import base.service.api.IPropertyFlags;
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

    // messages
    private final static String MSG_ERROR_REQUIRED_PARAMETER_IS_NULL_OR_EMPTY = ("Provided required parameter is null"
        + " or empty.");

    // this could be retrieved by external system, as there is always possibility for the ISO standard to change
    private final static String COUNTRY_CODE_CHINA = "CN";
    private final static String COUNTRY_CODE_CUBA = "CU";
    private final static String COUNTRY_CODE_IRAN = "IR";
    private final static String COUNTRY_CODE_BANGLADESH = "BD";
    private final static String COUNTRY_CODE_VIETNAM = "VN";

    public BaseManager()
    {
        // should be removed once service orchestration is implemented
    }

    DataStoreService dataStoreService = new DataStoreService();

    @Override
    public int getRecommendedSDK(String platform, String osVersion, String appName, String appVersion,
        String countryCode) throws BaseServiceException
    {
        checkStringNotNullOrEmpty(countryCode);
        checkStringNotNullOrEmpty(osVersion);
        checkStringNotNullOrEmpty(platform);
        checkStringNotNullOrEmpty(platform);
        checkStringNotNullOrEmpty(appName);

        LinkedList<IPriorityListEntry> priorityList = this.getPriorityList(countryCode);

        // filter out add opt out
        LinkedList<IPriorityListEntry> finalPriorityList = priorityList;
        priorityList.stream().filter(
            newEntity -> IPropertyFlags.SDK_AD_MOB_IDENTIFIER == newEntity.getSkdIdentifier()).findAny().ifPresent(
            adMobEntry -> removeAll(finalPriorityList, IPropertyFlags.SDK_AD_MOB_OPT_OUT_IDENTIFIER));

        // filter facebook adds for countries where facebook is banned
        if (countryCode.equals(COUNTRY_CODE_CHINA) || countryCode.equals(COUNTRY_CODE_BANGLADESH) || countryCode.equals(
            COUNTRY_CODE_CUBA) || countryCode.equals(COUNTRY_CODE_IRAN) || countryCode.equals(COUNTRY_CODE_VIETNAM))
        {
            priorityList = removeAll(priorityList, IPropertyFlags.SDK_FACEBOOK_IDENTIFIER);
        }

        // filter AdMob if appVersion is 9 (android)
        // in case we have 9.x AdMob is not filtered
        if (osVersion.equals("android") && Integer.parseInt(osVersion.split(".")[0]) == 9)
        {
            priorityList = removeAll(priorityList, IPropertyFlags.SDK_AD_MOB_IDENTIFIER);
        }

        return priorityList.get(0).getSkdIdentifier();
    }

    @Override
    public LinkedList<IPriorityListEntry> getPriorityList(String countryCode) throws BaseServiceException
    {
        checkStringNotNullOrEmpty(countryCode);
        return dataStoreService.getPriorityList(countryCode);
    }

    @Override
    public LinkedList<IPriorityListEntry> updatePriorityList(LinkedList<IPriorityListEntry> priorityList)
        throws BaseServiceException
    {
        checkListNotNullOrEmpty(priorityList);

        dataStoreService.updatePriorityList(priorityList);
        return priorityList;
    }


    /* Private methods*/

    private void checkStringNotNullOrEmpty(String parameter) throws BaseServiceException
    {
        if (parameter == null || parameter.isEmpty())
        {
            throw new BaseServiceException(MSG_ERROR_REQUIRED_PARAMETER_IS_NULL_OR_EMPTY);
        }
    }

    private void checkListNotNullOrEmpty(LinkedList<IPriorityListEntry> list) throws BaseServiceException
    {
        if (list == null || list.isEmpty())
        {
            throw new BaseServiceException(MSG_ERROR_REQUIRED_PARAMETER_IS_NULL_OR_EMPTY);
        }
    }

    LinkedList<IPriorityListEntry> removeAll(LinkedList<IPriorityListEntry> list, int id)
    {
        return (LinkedList<IPriorityListEntry>) list.stream().filter(
            e -> !Objects.equals(e.getAdTypeIdentifier(), id)).collect(Collectors.toList());
    }
}

