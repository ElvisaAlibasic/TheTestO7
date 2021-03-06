package communication;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;

public class TheTestServlet
{
    static final String APPLICATION_PATH = "/api";
    static final String CONTEXT_ROOT = "/";

    public static void main(String[] args) throws Exception
    {
        final int port = 8080;
        final Server server = new Server(port);

        final ServletContextHandler context = new ServletContextHandler(server, CONTEXT_ROOT);

        final ServletHolder restEasyServlet = new ServletHolder(new HttpServletDispatcher());
        restEasyServlet.setInitParameter("resteasy.servlet.mapping.prefix", APPLICATION_PATH);
        restEasyServlet.setInitParameter("javax.ws.rs.Application", "rest.TheTestApplication");
        context.addServlet(restEasyServlet, APPLICATION_PATH + "/*");

        final ServletHolder defaultServlet = new ServletHolder(new DefaultServlet());
        context.addServlet(defaultServlet, CONTEXT_ROOT);

        server.start();
        server.join();
    }
}