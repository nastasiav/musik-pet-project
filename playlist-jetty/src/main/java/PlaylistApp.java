import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.servlet.ServletHolder;
import org.eclipse.jetty.server.Server;
import org.my.edu.config.WebConfig;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

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
    contextHandler.addServlet(servletHolder, "/");

    server.setHandler(contextHandler);

    IO.println("Starting Jetty server on port " + port);
    server.start();
    IO.println("Jetty server started successfully!");
    server.join();
}
