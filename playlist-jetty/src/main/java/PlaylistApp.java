import jakarta.servlet.http.HttpServletRequest;
import org.eclipse.jetty.ee10.servlet.ErrorHandler;
import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.servlet.ServletHolder;
import org.eclipse.jetty.server.Server;
import org.my.edu.config.WebConfig;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.IOException;
import java.io.Writer;

void main() throws Exception {
    int port = 8103;
    Server server = new Server(port);

    ServletContextHandler contextHandler = new ServletContextHandler(
            ServletContextHandler.SESSIONS
    );
    contextHandler.setContextPath("/");

    AnnotationConfigWebApplicationContext springContext =
            new AnnotationConfigWebApplicationContext();
    springContext.register(WebConfig.class);

    DispatcherServlet dispatcherServlet = new DispatcherServlet(springContext);
    ServletHolder servletHolder = new ServletHolder(dispatcherServlet);
    contextHandler.addServlet(servletHolder, "/*");

    server.setHandler(contextHandler);

    server.setErrorHandler(new ErrorHandler());

    try {
        server.start();
        IO.println("Starting Jetty server on port " + port);
        server.join();
    } catch (Exception e) {
        e.printStackTrace();
        throw e;
    }
}
