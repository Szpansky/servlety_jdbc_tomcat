
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContext implements ServletContextListener {
    ServletContext context;
    public void contextInitialized(ServletContextEvent contextEvent) {
        System.out.println("Servlet created");
        JDBC.connectToDB();
    }
    public void contextDestroyed(ServletContextEvent contextEvent) {
        System.out.println("Servlet destroyed");
        JDBC.closeConnection();
    }
}