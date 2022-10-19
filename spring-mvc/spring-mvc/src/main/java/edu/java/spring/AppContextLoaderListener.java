package edu.java.spring;

import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;

public class AppContextLoaderListener extends ContextLoaderListener {
    private final static Logger LOGGER = Logger.getLogger(AppContextLoaderListener.class);
    @Override
    public void contextInitialized(ServletContextEvent event) {
        LOGGER.info("--------------->Da khoi tao ung dung");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        LOGGER.info("---------------->Da huy ung dung");
    }
}
