package ggze.pub;
import ggze.loader.Loading;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
public class WebListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            new Loading();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
