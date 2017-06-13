package Main;

import Accounts.SessionService;
import DataBase.DBService;
import Servlets.AllRequestServlet;
import Servlets.SignInServlet;
import Servlets.SignUpServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Created by Alex Nevolinf on 22.12.2016.
 */
public class Main {
    public static void main(String[] args) throws Exception{

        DBService dbService = new DBService();


        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        SessionService sessionService = new SessionService();
        context.addServlet(new ServletHolder(new SignInServlet(sessionService, dbService)), "/signin");
        context.addServlet(new ServletHolder(new SignUpServlet(sessionService, dbService)), "/signup");
        context.addServlet(new ServletHolder(new AllRequestServlet(sessionService)), "/*");

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("publicHtml");
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        System.out.println("Server started");
        server.join();
    }
}
