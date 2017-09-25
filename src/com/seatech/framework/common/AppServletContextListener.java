package com.seatech.framework.common;


import com.seatech.framework.AppKeys;

import java.util.Collection;
import java.util.HashMap;

import javax.management.timer.Timer;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import javax.sql.DataSource;


public abstract class AppServletContextListener implements ServletContextListener {
    protected ServletContext sc;
    protected Timer timer;

    public void contextInitialized(ServletContextEvent event) {
        sc = event.getServletContext();

        //Create tham so he thong ....
        createDataSource(sc);
        createAppParam(sc);
        createAppError(sc);

        scheduleTasks();
    }

    public void contextDestroyed(ServletContextEvent event) {
        stopTasks();
    }

    protected void createDataSource(ServletContext sc) {
        try {
            String dataSourceName = getDataSourceName();
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource)ic.lookup(dataSourceName);
            sc.setAttribute(AppKeys.DATASOURCE_APPLICATION_KEY, ds);
        } catch (NamingException ex) {
            ex.printStackTrace();
        }

    }

    protected void createAppError(ServletContext sc) {
        try {
            HashMap appError = getAppError();
            sc.setAttribute(AppKeys.ERROR_LIST_APPLICATION_KEY, appError);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected void createAppParam(ServletContext sc) {
        try {
            Collection appParam = getAppParam();
            sc.setAttribute(AppKeys.PARAM_LIST_APPLICATION_KEY, appParam);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected String getDataSourceName() {
        return "jdbc/ttspDS";
    }

    protected void scheduleTasks() {
        timer = new Timer();
        notifyTask(timer);
        timer.start();
    }

    protected void stopTasks() {
        timer.stop();
        timer.removeAllNotifications();
    }

    protected void notifyTask(Timer timer) {
    }

    protected abstract HashMap getAppError() throws Exception;

    protected abstract Collection getAppParam() throws Exception;
}
