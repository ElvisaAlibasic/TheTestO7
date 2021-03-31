package rest.client.impl;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    // this is wrong, service should be accessed via service orchestration
    BaseManager baseManager = new BaseManager();

    @Override
    public List<PriorityListRestEntry> getPriorityList()
    {
        List<PriorityListRestEntry> resultList = new LinkedList<>();

        LinkedList<IPriorityListEntry> priorityListEntries = baseManager.getPriorityList();

        for (IPriorityListEntry entry : priorityListEntries)
        {
            resultList.add(
                new PriorityListRestEntry(entry.getSkdIdentifier(), entry.getAdTypeIdentifier(), entry.getCountryCode(),
                    entry.getScore()));
        }

        return resultList;
    }

    public Response updatePriorityList(List<PriorityListRestEntry> priorityList)
    {

        LinkedList<IPriorityListEntry> convertedList = new LinkedList<>();

        for (PriorityListRestEntry entry : priorityList)
        {
            convertedList.add(
                new PriorityListEntry(entry.getSkdIdentifier(), entry.getAdTypeIdentifier(), entry.getCountryCode(),
                    entry.getScore()));
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

    public Response testRest(String blbla)
    {
        System.out.println(blbla);
        return Response.status(200).entity("ACK_TEST").build();

    }
}