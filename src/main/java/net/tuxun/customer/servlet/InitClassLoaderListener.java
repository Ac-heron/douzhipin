package net.tuxun.customer.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.tuxun.core.base.ServiceManager;

public class InitClassLoaderListener implements ServletContextListener {

  @Override
  public void contextDestroyed(ServletContextEvent arg0) {

  }

  @Override
  public void contextInitialized(ServletContextEvent arg0) {
    ServiceManager.addService(ClassLoader.class, this.getClass().getClassLoader());
  }

}
