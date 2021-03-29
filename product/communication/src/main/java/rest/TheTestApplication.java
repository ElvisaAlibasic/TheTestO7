package rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import rest.client.impl.AdMediationRestResource;

@ApplicationPath("")
public class TheTestApplication extends Application
{
    public TheTestApplication() {}

    @Override
    public Set<Object> getSingletons() {
        HashSet<Object> set = new HashSet<Object>();
        set.add(new AdMediationRestResource());
        return set;
    }
}

