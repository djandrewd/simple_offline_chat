package ua.goit.offline5.chat;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Application
 * <p>
 * Created by andreymi on 4/21/2017.
 */
public class ChatApplication implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        XmlWebApplicationContext context = new XmlWebApplicationContext();
        context.setConfigLocation("WEB-INF/application-context.xml");

        DispatcherServlet servlet = new DispatcherServlet(context);
        ServletRegistration.Dynamic dynamic = servletContext
                .addServlet("dispatcher", servlet);
        dynamic.setLoadOnStartup(1);
        dynamic.addMapping("/");

        DelegatingFilterProxy filter =
                new DelegatingFilterProxy("springSecurityFilterChain");
        filter.setContextAttribute("org.springframework.web.servlet.FrameworkServlet.CONTEXT.dispatcher");
        servletContext.addFilter("springSecurityFilterChain", filter)
                .addMappingForUrlPatterns(null, false, "/*");
    }
}
