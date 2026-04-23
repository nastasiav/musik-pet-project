package org.my.edu.libraryclean;

import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.servlet.ServletHolder;
import org.eclipse.jetty.server.Server;
import org.my.edu.libraryclean.exceptions.MyServerException;
import org.my.edu.libraryclean.web.MyGraphQLServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    static void main() {
        int port = 8102;
        Server server = new Server(port);

        ServletContextHandler context = new ServletContextHandler();

        context.addServlet(new ServletHolder(new MyGraphQLServlet()), "/graphql");

        context.setContextPath("/");
        server.setHandler(context);

        try {
            server.start();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new MyServerException("Server Jetty cannot started");
        }
        System.out.println("Server started on port 8102");
        try {
            server.join();
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            throw new MyServerException("Server Jetty error join");
        }
    }
}
