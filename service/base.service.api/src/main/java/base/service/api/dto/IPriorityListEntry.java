package base.service.api.dto;

/**
 * DTO used to carry priority list entry information, it represents {@link Integer score} based on {@link Integer
 * SDKIdentifier}, {@link Integer adTypeIdentifier} and {@link String countryCode} parameters.
 * <p>
 * Data is provided by external data pipeline system.
 *
 * @author Elvisa Alibasic
 * @since 1.0.0
 */
public interface IPriorityListEntry
{
    /**
     * Method used to retrieve SDK identifier.
     *
     * @return {@link Integer identifier}.
     */
    int getSkdIdentifier();

    /**
     * Method used to retrieve ad type identifier.
     *
     * @return {@link Integer identifier}.
     */
    int getAdTypeIdentifier();

    /**
     * Method used to retrieve country code.
     *
     * @return {@link Integer code}.
     */
    String getCountryCode();

    /**
     * Method used to retrieve score of entry.
     *
     * @return {@link Integer score}.
     */
    int getScore();

}
