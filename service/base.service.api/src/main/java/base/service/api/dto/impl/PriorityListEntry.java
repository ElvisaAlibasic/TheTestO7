package base.service.api.dto.impl;

import java.io.Serializable;

import base.service.api.dto.IPriorityListEntry;

/**
 * Implementation of {@link IPriorityListEntry}.
 *
 * @author Elvisa Alibasic
 * @since 1.0.0
 */
public class PriorityListEntry implements IPriorityListEntry, Serializable
{
    private int skdIdentifier;
    private int adTypeIdentifier;
    private String countryCode;
    private int score;

    public PriorityListEntry(int skdIdentifier, int adTypeIdentifier, String countryCode, int score)
    {
        this.skdIdentifier = skdIdentifier;
        this.adTypeIdentifier = adTypeIdentifier;
        this.countryCode = countryCode;
        this.score = score;
    }

    public PriorityListEntry()
    { }

    @Override
    public int getSkdIdentifier()
    {
        return this.skdIdentifier;
    }

    @Override
    public int getAdTypeIdentifier()
    {
        return this.adTypeIdentifier;
    }

    @Override
    public String getCountryCode()
    {
        return this.countryCode;
    }

    @Override
    public int getScore()
    {
        return this.score;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("PriorityListEntry{");
        sb.append("skdIdentifier=").append(skdIdentifier);
        sb.append(", adTypeIdentifier=").append(adTypeIdentifier);
        sb.append(", countryCode='").append(countryCode).append('\'');
        sb.append(", score=").append(score);
        sb.append('}');
        return sb.toString();
    }
}
