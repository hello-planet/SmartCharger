package com.proc.admin;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.func.util.ServerManager;

/**
 * To wirte the config into the server class.<br>
 * Application Lifecycle Listener implementation class ServerInitial
 *
 */
@WebListener
public class ServerInitial implements ServletContextListener, ServletContextAttributeListener {

	/**
	 * Default constructor.
	 */
	public ServerInitial() {

	}

	/**
	 * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
	 */
	public void attributeAdded(ServletContextAttributeEvent scae) {

	}

	/**
	 * @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent)
	 */
	public void attributeRemoved(ServletContextAttributeEvent scae) {

	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {

	}

	/**
	 * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
	 */
	public void attributeReplaced(ServletContextAttributeEvent scae) {

	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {
		// initial server config
		ServerManager.getServerManager().initialServerConfig();
		// initial node address list
		ServerManager.getServerManager().initialNodeAddrList();
	}

}
