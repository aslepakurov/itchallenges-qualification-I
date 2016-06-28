package me.itchallenges.pathfinder.web;

import me.itchallenges.pathfinder.AppConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Date: 06/08/2016 5:01 PM
 */
public class PathfinderWebInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext context) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(AppConfig.class);
        ctx.setServletContext(context);
        ServletRegistration.Dynamic servlet = context.addServlet(
                "dispatcher", new DispatcherServlet(ctx));
        FilterRegistration fr = context.addFilter("CorsFilter", CORSFilter.class);
        fr.addMappingForUrlPatterns(null, true, "/*");
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
    }
}
