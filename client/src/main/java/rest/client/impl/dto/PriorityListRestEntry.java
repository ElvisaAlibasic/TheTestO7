package rest.client.impl.dto;

import java.io.Serializable;

public class PriorityListRestEntry implements Serializable
{

    private String skdName;
    private String adTypeName;
    private String countryCode;
    private int score;

    public PriorityListRestEntry(String skdName, String adTypeName, String countryCode, int score)
    {
        this.skdName = skdName;
        this.adTypeName = adTypeName;
        this.countryCode = countryCode;
        this.score = score;
    }

    public PriorityListRestEntry()
    {
    }

    public String getSkdName()
    {
        return this.skdName;
    }

    public String getAdTypeName()
    {
        return this.adTypeName;
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
        sb.append("skdName=").append(skdName);
        sb.append(", adTypeName=").append(adTypeName);
        sb.append(", countryCode='").append(countryCode).append('\'');
        sb.append(", score=").append(score);
        sb.append('}');
        return sb.toString();
    }
}
