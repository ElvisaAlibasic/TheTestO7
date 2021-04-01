package base.service.api;

/**
 * Public interface to hold values of constants and conversion maps.
 *
 * @author Elvisa Alibasic
 * @since 1.0.0
 */
public interface IPropertyFlags
{
    int SDK_ADX_IDENTIFIER = 1;
    int SDK_AD_MOB_IDENTIFIER = 2;
    int SDK_AD_MOB_OPT_OUT_IDENTIFIER = 3;
    int SDK_UNITY_ADS_IDENTIFIER = 4;
    int SDK_FACEBOOK_IDENTIFIER = 5;
    int SDK_IRON_SOURCE_IDENTIFIER = 6;

    String SDK_ADX_NAME = "Adx";
    String SDK_AD_MOB_NAME = "AdMob";
    String SDK_AD_MOB_OPT_OUT_NAME = "AdMob-OptOut";
    String SDK_UNITY_ADS_NAME = "UnityAds";
    String SDK_FACEBOOK_NAME = "Facebook";
    String SDK_IRON_SOURCE_NAME = "IronSource";

    int AD_TYPE_BANNER_ID = 1;
    int AD_TYPE_INTERSTITIAL_ID = 2;
    int AD_TYPE_REWARDED_VIDEO_ID = 3;

    String AD_TYPE_BANNER_NAME = "banner";
    String AD_TYPE_INTERSTITIAL_NAME = "interstitial";
    String AD_TYPE_REWARDED_VIDEO_NAME = "rewardedVideo";

    String QUERY_PARAM_AD_TYPE_IDENTIFIER = "adTypeIdentifier";
    String QUERY_PARAM_COUNTRY_CODE = "countryCode";
    String QUERY_PARAM_PLATFORM = "platform";
    String QUERY_PARAM_APP_VERSION = "appVersion";
    String QUERY_PARAM_OS_VERSION = "osVersion";
    String QUERY_PARAM_APP_NAME = "appName";
}
