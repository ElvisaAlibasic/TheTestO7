package rest.client.impl.dto;

import java.io.Serializable;

public class PriorityListRestEntry implements Serializable
{

    private int skdIdentifier;
    private int adTypeIdentifier;
    private String countryCode;
    private int score;

    public PriorityListRestEntry(int skdIdentifier, int adTypeIdentifier, String countryCode, int score)
    {
        this.skdIdentifier = skdIdentifier;
        this.adTypeIdentifier = adTypeIdentifier;
        this.countryCode = countryCode;
        this.score = score;
    }

    public PriorityListRestEntry()
    {
    }

    public int getSkdIdentifier()
    {
        return this.skdIdentifier;
    }

    public int getAdTypeIdentifier()
    {
        return this.adTypeIdentifier;
    }

    public String getCountryCode()
    {
        return this.countryCode;
    }

    public int getScore()
    {
        return this.score;
    }

    public String toString()
    {
        final StringBuilder sb = new StringBuilder("PriorityListRestEntry{");
        sb.append("skdIdentifier=").append(skdIdentifier);
        sb.append(", adTypeIdentifier=").append(adTypeIdentifier);
        sb.append(", countryCode='").append(countryCode).append('\'');
        sb.append(", score=").append(score);
        sb.append('}');
        return sb.toString();
    }
}
