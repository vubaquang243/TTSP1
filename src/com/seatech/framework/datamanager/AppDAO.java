package com.seatech.framework.datamanager;


import com.seatech.framework.exception.DAOException;
import com.seatech.framework.exception.DatabaseConnectionFailureException;
import com.seatech.framework.exception.ExecuteStatementException;
import com.seatech.framework.exception.ResultSetCallbackException;
import com.seatech.framework.exception.SelectStatementException;
import com.seatech.framework.exception.StoredProcedureException;
import com.seatech.framework.utils.FontUtil;

import java.io.InputStream;

import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;

import net.sf.jxls.report.ResultSetCollection;
import net.sf.jxls.transformer.Configuration;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.commons.beanutils.PropertyUtils;


public class AppDAO extends BaseObject {
    public AppDAO() {

    }

    /**
     * @param  database name, username,  password
     * @return Connection
     * @throws
     * @see
     * */
    public Connection getConnectionSQL(String database, String username,
                                       String password) {
        Connection conn = null;
        String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
        try {
            // Load the database driver
            Class.forName(driver);
            conn =
DriverManager.getConnection("jdbc:odbc:" + database, username, password);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return conn;
    }

    /**
     * @param
     * @return Connection
     * @throws DatabaseConnectionFailureException
     * @see
     * */
    protected Connection getConnection() throws DatabaseConnectionFailureException {
        InitialContext ctx = null;
        try {
            ctx = new InitialContext();
            DataSource ds = (javax.sql.DataSource)ctx.lookup(dataSourceJNDI);
            conn = ds.getConnection();
            if (conn != null) {
                conn.setAutoCommit(false);
            } else {
//                printMessage("FrameworkDAO:getConnection() - Using a datasource, the connection returned is null.");
                throw new DatabaseConnectionFailureException("FrameworkDAO:getConnection() - Using a datasource, the connection returned is null.");
            }
        } catch (NamingException e) {
            throw new DatabaseConnectionFailureException("FrameworkDAO:getConnection()-" +
                                                         e.getMessage());
        } catch (SQLException e) {
            throw new DatabaseConnectionFailureException("FrameworkDAO:getConnection()-" +
                                                         e.getMessage());
        }
        return conn;
    }

    /**
     * @param  dataSourceJNDI
     * @return Connection
     * @throws DatabaseConnectionFailureException
     * @see
     * */

    protected Connection getConnection(String dataSourceJNDI) throws DatabaseConnectionFailureException {
        InitialContext ctx = null;
        try {
            ctx = new InitialContext();
            DataSource ds = (javax.sql.DataSource)ctx.lookup(dataSourceJNDI);
            conn = ds.getConnection();
            if (conn != null) {
                conn.setAutoCommit(false);
            } else {
//                printMessage("FrameworkDAO:getConnection() - Using a datasource, the connection returned is null.");
                throw new DatabaseConnectionFailureException("FrameworkDAO:getConnection() - Using a datasource, the connection returned is null.");
            }
        } catch (NamingException e) {
            throw new DatabaseConnectionFailureException("FrameworkDAO:getConnection()-" +
                                                         e.getMessage());
        } catch (SQLException e) {
            throw new DatabaseConnectionFailureException("FrameworkDAO:getConnection()-" +
                                                         e.getMessage());
        }
        return conn;
    }


    /**
     * @param  sequenceName
     * @return
     * @throws DatabaseConnectionFailureException
     * @see
     * */
    public String generateUID(String sequenceName) throws Exception{
        String seqReturn = "0";
        String sSQLSelectValue = sequenceName + ".NEXTVAL";
        Collection coll = null;
        try {
            String strSQL = "SELECT " + sSQLSelectValue + " FROM DUAL";
            coll =
executeSelectStatement(strSQL, null, new GetSeqRSCallback());
        } catch (Exception exc) {
//            printMessage("FrameworkDAO:generateUID() - Error:." + exc);
            throw exc;
        }
        if (coll == null) {
//            printMessage("FrameworkDAO:generateUID() - Collection is empty.");
          throw new Exception("FrameworkDAO:generateUID() - Collection is empty.");
        } else {
            seqReturn = (String)coll.iterator().next();
        }
        return seqReturn;
    }

    /**
     *
     * @throws SelectStatementException
     * @throws DatabaseConnectionFailureException
     * @throws java.lang.IllegalArgumentException
     * @return
     * @param callback
     * @param sSelectStatement
     */
    public Collection executeSelectStatement(String sSelectStatement,
                                             IResultSetCallback callback) throws IllegalArgumentException,
                                                                                 DatabaseConnectionFailureException,
                                                                                 SelectStatementException, ResultSetCallbackException {
        if (sSelectStatement == null || sSelectStatement.length() == 0)
            throw new IllegalArgumentException("FrameworkDAO:executeSelectStatement() - invalid select statement");
        if (callback == null)
            throw new IllegalArgumentException("FrameworkDAO:executeSelectStatement() - null callback interface");
        Collection returnCollection = null;
        Statement stmt = null;
        Connection conn = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sSelectStatement);
            if (rs != null && callback != null) {
                try {
                    returnCollection =
                            callback.notifyResultSet(rs, sSelectStatement);
                } catch (ResultSetCallbackException rscbe) {
//                    printMessage("FrameworkDAO:executeSelectStatement() - " +
//                                 rscbe.getMessage());
                    throw rscbe;
                } finally {
                    close(rs);
                }
            }
        } catch (SQLException exc) {
            throw new SelectStatementException("FrameworkDAO:executeSelectStatement - " +
                                               exc.getMessage(), exc);
        } finally {
            close(stmt);
            close(conn);
        }
        return returnCollection;
    }


    /**
     *
     * @throws SelectStatementException
     * @throws DatabaseConnectionFailureException
     * @throws java.lang.IllegalArgumentException
     * @return
     * @param callback
     * @param parameters
     * @param sSelectStatement
     */
    public Collection executeSelectStatement(String sSelectStatement,
                                             Vector parameters,
                                             IResultSetCallback callback) throws IllegalArgumentException,
                                                                                 DatabaseConnectionFailureException,
                                                                                 SelectStatementException, ResultSetCallbackException {
        if (sSelectStatement == null || sSelectStatement.length() == 0)
            throw new IllegalArgumentException("FrameworkDAO:executeSelectStatement() - invalid select statement");
        if (callback == null)
            throw new IllegalArgumentException("FrameworkDAO:executeSelectStatement() - null callback interface");
        Collection returnCollection = null;
        PreparedStatement pstmt = null;
        Connection conn = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sSelectStatement);
            if (parameters != null) {
                Parameter param = null;
                int index = 1;
                Object o = null;
                for (Enumeration e = parameters.elements();
                     e.hasMoreElements(); ) {
                    param = (Parameter)e.nextElement();
                    if (param.isInParameter()) {
                        o = param.getParameter();
                        if (o != null)
                            pstmt.setObject(index, o);
                        else
                            pstmt.setObject(index, o, -1);
                    }
                    index++;
                }
            }
            ResultSet rs = pstmt.executeQuery();
            if (rs != null && callback != null) {
                try {
                    returnCollection =
                            callback.notifyResultSet(rs, sSelectStatement);
                } catch (ResultSetCallbackException rscbe) {
                    throw rscbe;
//                    printMessage("FrameworkDAO:executeSelectStatement() - " +
//                                 rscbe.getMessage());
                } finally {
                    this.close(rs);
                }
            }
        } catch (SQLException exc) {
            throw new SelectStatementException("FrameworkDAO:executeSelectStatement - " +
                                               exc.getMessage(), exc);
        } finally {
            this.close(pstmt);
            this.close(conn);
        }
        return returnCollection;
    }

    /**
     *
     * @throws SelectStatementException
     * @throws DatabaseConnectionFailureException
     * @throws java.lang.IllegalArgumentException
     * @return
     * @param strValueObject
     * @param parameters
     * @param sSQL
     */
    public Collection executeSelectStatement(String sSQL, Vector parameters,
                                             String strValueObject) throws IllegalArgumentException,
                                                                           DatabaseConnectionFailureException,
                                                                           SelectStatementException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        ArrayList retValList = new ArrayList();

        PreparedStatement pstmt = null;
        Connection conn = null;
        Object vo = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sSQL);
            //            pstmt =
            //                    conn.prepareStatement(sSQL, ResultSet.TYPE_SCROLL_INSENSITIVE,
            //                                          ResultSet.CONCUR_READ_ONLY);
            if (parameters != null) {
                Parameter param = null;
                int index = 1;
                Object o = null;
                for (Enumeration e = parameters.elements();
                     e.hasMoreElements(); ) {
                    param = (Parameter)e.nextElement();
                    if (param.isInParameter()) {
                        o = param.getParameter();
                        if (o != null)
                            pstmt.setObject(index, o);
                        else
                            pstmt.setObject(index, o, -1);
                    }
                    index++;
                }
            }
            ResultSet rs = null;
            try {
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    try {
                        vo = Class.forName(strValueObject).newInstance();
                        populateResultSetToBean(vo, rs);
                        retValList.add(vo);
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                        throw e;
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        throw e;
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        throw e;
                    }
                }
            } catch (SQLException exc) {
                throw new SelectStatementException("FrameworkDAO:executeSelectStatement - " +
                                                   exc.getMessage(), exc);
            } finally {
                this.close(rs);
            }
        } catch (SQLException exc) {
            throw new SelectStatementException("FrameworkDAO:executeSelectStatement - " +
                                               exc.getMessage(), exc);
        } finally {
            this.close(pstmt);
            this.close(conn);
        }
        return retValList;
    }

    public Collection executeSelectStatement(String sSQL, Vector parameters,
                                             String strValueObject,
                                             Connection conn) throws IllegalArgumentException,
                                                                     DatabaseConnectionFailureException,
                                                                     SelectStatementException, InstantiationException, IllegalAccessException, ClassNotFoundException { 
        ArrayList retValList = new ArrayList();
        PreparedStatement pstmt = null;
        Object vo = null;
        try {
            pstmt = conn.prepareStatement(sSQL);
            if (parameters != null) {
                Parameter param = null;
                int index = 1;
                Object o = null;
                for (Enumeration e = parameters.elements();
                     e.hasMoreElements(); ) {
                    param = (Parameter)e.nextElement();
                    if (param.isInParameter()) {
                        o = param.getParameter();
                        if (o != null)
                            pstmt.setObject(index, o);
                        else
                            pstmt.setObject(index, o, -1);
                    }
                    index++;
                }
            }
            ResultSet rs = null;
            try {
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    try {
                        vo = Class.forName(strValueObject).newInstance();
                        populateResultSetToBean(vo, rs);
                        retValList.add(vo);
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                        throw e;
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        throw e;
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        throw e;
                    }
                }
            } catch (SQLException exc) {
                throw new SelectStatementException("FrameworkDAO:executeSelectStatement - " +
                                                   exc.getMessage(), exc);
            } finally {
                this.close(rs);
            }
        } catch (SQLException exc) {
            throw new SelectStatementException("FrameworkDAO:executeSelectStatement - " +
                                               exc.getMessage(), exc);
        } finally {
            this.close(pstmt);
        }
        return retValList;
    }

    public Object findByPK(String sSQL,
                           String strValueObject) throws IllegalArgumentException,
                                                         DatabaseConnectionFailureException,
                                                         SelectStatementException, InstantiationException, IllegalAccessException, ClassNotFoundException { 
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection connection = null;
        Object retVal = null;
        try {
            connection = getConnection();
            pstmt =
                    connection.prepareStatement(sSQL, ResultSet.TYPE_SCROLL_INSENSITIVE,
                                                ResultSet.CONCUR_READ_ONLY);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                try {
                    retVal = Class.forName(strValueObject).newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                    throw e;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    throw e;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    throw e;
                }
                populateResultSetToBean(retVal, rs);
            }
        } catch (SQLException exc) {
            throw new SelectStatementException("FrameworkDAO:executeSelectStatement - " +
                                               exc.getMessage(), exc);
        } finally {
            close(rs);
            close(pstmt);
            this.close(connection);
        }
        return retVal;
    }

    public Object findByPK(String sSQL, String strValueObject,
                           Connection connection) throws IllegalArgumentException,
                                                         DatabaseConnectionFailureException,
                                                         SelectStatementException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Object retVal = null;
        try {
            pstmt =
                    connection.prepareStatement(sSQL, ResultSet.TYPE_SCROLL_INSENSITIVE,
                                                ResultSet.CONCUR_READ_ONLY);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                try {
                    retVal = Class.forName(strValueObject).newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                    throw e;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    throw e;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    throw e;
                }
                populateResultSetToBean(retVal, rs);
            }
        } catch (SQLException exc) {
            throw new SelectStatementException("FrameworkDAO:findByPK - " +
                                               exc.getMessage(), exc);
        } finally {
            close(rs);
            close(pstmt);
        }
        return retVal;
    }

    public Object findByPK(String sSQL, Vector parameters,
                           String strValueObject) throws IllegalArgumentException,
                                                         DatabaseConnectionFailureException,
                                                         SelectStatementException, InstantiationException, IllegalAccessException, ClassNotFoundException { 
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Connection connection = null;
        Object retVal = null;
        try {
            connection = getConnection();
            pstmt =
                    connection.prepareStatement(sSQL, ResultSet.TYPE_SCROLL_INSENSITIVE,
                                                ResultSet.CONCUR_READ_ONLY);
            if (parameters != null) {
                Parameter param = null;
                int index = 1;
                Object o = null;
                for (Enumeration e = parameters.elements();
                     e.hasMoreElements(); ) {
                    param = (Parameter)e.nextElement();
                    if (param.isInParameter()) {
                        o = param.getParameter();
                        if (o != null)
                            pstmt.setObject(index, o);
                        else
                            pstmt.setObject(index, o, -1);
                    }
                    index++;
                }
            }
            rs = pstmt.executeQuery();
            if (rs.next()) {
                try {
                    retVal = Class.forName(strValueObject).newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                    throw e;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    throw e;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    throw e;
                }
                populateResultSetToBean(retVal, rs);
            }
        } catch (SQLException exc) {
            throw new SelectStatementException("FrameworkDAO:executeSelectStatement - " +
                                               exc.getMessage(), exc);
        } finally {
            close(rs);
            close(pstmt);
            close(connection);
        }
        return retVal;
    }

    public Object findByPK(String sSQL, Vector parameters,
                           String strValueObject,
                           Connection connection) throws IllegalArgumentException,
                                                         DatabaseConnectionFailureException,
                                                         SelectStatementException, InstantiationException, IllegalAccessException, ClassNotFoundException { 
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Object retVal = null;
        try {
            pstmt =
                    connection.prepareStatement(sSQL, ResultSet.TYPE_SCROLL_INSENSITIVE,
                                                ResultSet.CONCUR_READ_ONLY);
            if (parameters != null) {
                Parameter param = null;
                int index = 1;
                Object o = null;
                for (Enumeration e = parameters.elements();
                     e.hasMoreElements(); ) {
                    param = (Parameter)e.nextElement();
                    if (param.isInParameter()) {
                        o = param.getParameter();
                        if (o != null)
                            pstmt.setObject(index, o);
                        else
                            pstmt.setObject(index, o, -1);
                    }
                    index++;
                }
            }
            rs = pstmt.executeQuery();
            if (rs.next()) {
                try {
                    retVal = Class.forName(strValueObject).newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                    throw e;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    throw e;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    throw e;
                }
                populateResultSetToBean(retVal, rs);
            }
        } catch (SQLException exc) {
            throw new SelectStatementException("FrameworkDAO:findByPK - " +
                                               exc.getMessage(), exc);
        } finally {
            close(rs);
            close(pstmt);
        }
        return retVal;
    }

    public ResultSet executeSelectStatement(String sSelectStatement,
                                            Vector parameters,
                                            Connection conn) throws DatabaseConnectionFailureException,
                                                                    SelectStatementException {
        if (sSelectStatement == null || sSelectStatement.length() == 0)
            throw new IllegalArgumentException("FrameworkDAO:executeSelectStatement() - invalid select statement");
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt =
                    conn.prepareStatement(sSelectStatement, ResultSet.TYPE_SCROLL_SENSITIVE,
                                          ResultSet.CONCUR_READ_ONLY);
            if (parameters != null) {
                Parameter param = null;
                int index = 1;
                Object o = null;
                for (Enumeration e = parameters.elements();
                     e.hasMoreElements(); ) {
                    param = (Parameter)e.nextElement();
                    if (param.isInParameter()) {
                        o = param.getParameter();
                        if (o != null)
                            pstmt.setObject(index, o);
                        else
                            pstmt.setObject(index, o, -1);
                    }
                    index++;
                }
            }
            rs = pstmt.executeQuery();
        } catch (SQLException exc) {
            throw new SelectStatementException("FrameworkDAO:executeSelectStatement - " +
                                               exc.getMessage(), exc);
        }
        return rs;
    }

    public ResultSet executeSelectStatement(String sSelectStatement,
                                            Vector parameters) throws DatabaseConnectionFailureException,
                                                                      SelectStatementException {
        if (sSelectStatement == null || sSelectStatement.length() == 0)
            throw new IllegalArgumentException("FrameworkDAO:executeSelectStatement() - invalid select statement");
        PreparedStatement pstmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sSelectStatement);
            if (parameters != null) {
                Parameter param = null;
                int index = 1;
                Object o = null;
                for (Enumeration e = parameters.elements();
                     e.hasMoreElements(); ) {
                    param = (Parameter)e.nextElement();
                    if (param.isInParameter()) {
                        o = param.getParameter();
                        if (o != null)
                            pstmt.setObject(index, o);
                        else
                            pstmt.setObject(index, o, -1);
                    }
                    index++;
                }
            }
            rs = pstmt.executeQuery();
        } catch (SQLException exc) {
            throw new SelectStatementException("FrameworkDAO:executeSelectStatement - " +
                                               exc.getMessage(), exc);
        } finally {
            this.close(pstmt);
            this.close(conn);
        }
        return rs;
    }

    public Collection executeStoredProcedure(String sProcedureName,
                                             Vector parameters,
                                             IResultSetCallback callback) throws IllegalArgumentException,
                                                                                 DatabaseConnectionFailureException,
                                                                                 StoredProcedureException {
        if (sProcedureName == null || sProcedureName.length() == 0)
            throw new IllegalArgumentException("FrameworkDAO:executeStoredProcedure()");
        Connection connection = null;
        Collection returnCollection = null;
        String sSQL = null;
        CallableStatement stmt = null;
        try {
            connection = getConnection();
            sSQL =
buildSQLForStoredProcedure(sProcedureName, parameters, true);
            stmt = prepareCallableStatementHelper(connection, sSQL);
            assignParametersToStatement(stmt, parameters, true);
            ResultSet rs = executeCallableStatement(stmt);
            if (callback != null && rs != null)
                try {
                    returnCollection =
                            callback.notifyResultSet(rs, sProcedureName);
                } catch (ResultSetCallbackException rscbe) {
                    throw new StoredProcedureException("FrameworkDAO:executeStoredProcedure() - " +
                                                       rscbe.getMessage(),
                                                       null);
                } finally {
                    close(rs);
                }
        } catch (SQLException exc) {
            throw new StoredProcedureException(exc.getMessage(), exc);
        } finally {
            close(stmt);
            this.close(connection);
        }
        return returnCollection;
    }

    public Collection executeStoredProcedure(String sProcedureName,
                                             Vector parameters,
                                             IResultSetCallback callback,
                                             Connection connection) throws IllegalArgumentException,
                                                                           DatabaseConnectionFailureException,
                                                                           StoredProcedureException {
        if (sProcedureName == null || sProcedureName.length() == 0)
            throw new IllegalArgumentException("FrameworkDAO:executeStoredProcedure()");
        Collection returnCollection = null;
        String sSQL = null;
        CallableStatement stmt = null;
        try {
            sSQL =
buildSQLForStoredProcedure(sProcedureName, parameters, true);
            stmt = prepareCallableStatementHelper(connection, sSQL);
            assignParametersToStatement(stmt, parameters, true);
            ResultSet rs = executeCallableStatement(stmt);
            if (callback != null && rs != null)
                try {
                    returnCollection =
                            callback.notifyResultSet(rs, sProcedureName);
                } catch (ResultSetCallbackException rscbe) {
                    throw new StoredProcedureException("FrameworkDAO:executeStoredProcedure() - " +
                                                       rscbe.getMessage(),
                                                       null);
                } finally {
                    close(rs);
                }
        } catch (SQLException exc) {
            throw new StoredProcedureException(exc.getMessage(), exc);
        } finally {
            close(stmt);
        }
        return returnCollection;
    }

    public Clob executeStoredProcedure(String sProcedureName,
                                       Vector parameters) throws IllegalArgumentException,
                                                                 DatabaseConnectionFailureException,
                                                                 StoredProcedureException {
        if (sProcedureName == null || sProcedureName.length() == 0)
            throw new IllegalArgumentException("FrameworkDAO:executeStoredProcedure()");
        Connection connection = null;
        Clob retVal = null;
        String sSQL = null;
        CallableStatement stmt = null;
        try {
            connection = getConnection();
            sSQL =
buildSQLForStoredProcedure(sProcedureName, parameters, false);
            stmt = prepareCallableStatementHelper(connection, sSQL);
            assignParametersToStatement(stmt, parameters, false);
            ResultSet rs = executeCallableStatement(stmt);
            if (rs != null)
                try {
                    retVal = rs.getClob(0);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    close(rs);
                }
        } catch (SQLException exc) {
            throw new StoredProcedureException(exc.getMessage(), exc);
        } finally {
            close(stmt);
            this.close(connection);
        }
        return retVal;
    }

    //    public Clob executeStoredProcedure(String sProcedureName,
    //                                       Vector parameters,
    //                                       Connection connection) throws IllegalArgumentException,
    //                                                                     DatabaseConnectionFailureException,
    //                                                                     StoredProcedureException {
    //        if (sProcedureName == null || sProcedureName.length() == 0)
    //            throw new IllegalArgumentException("FrameworkDAO:executeStoredProcedure()");
    //        Clob retVal = null;
    //        String sSQL = null;
    //        CallableStatement stmt = null;
    //        try {
    //            sSQL =
    //buildSQLForStoredProcedure(sProcedureName, parameters, false);
    //            stmt = prepareCallableStatementHelper(connection, sSQL);
    //            assignParametersToStatement(stmt, parameters, false);
    //            ResultSet rs = executeCallableStatement(stmt);
    //            if (rs != null)
    //                try {
    //                    retVal = rs.getClob(0);
    //                } catch (Exception e) {
    //                    e.printStackTrace();
    //                } finally {
    //                    close(rs);
    //                }
    //        } catch (SQLException exc) {
    //            throw new StoredProcedureException(exc.getMessage(), exc);
    //        } finally {
    //            close(stmt);
    //        }
    //        return retVal;
    //    }

    public void executeStoredProcedure(String sProcedureName,
                                       Vector parameters,
                                       Connection connection) throws IllegalArgumentException,
                                                                     DatabaseConnectionFailureException,
                                                                     StoredProcedureException {
        if (sProcedureName == null || sProcedureName.length() == 0)
            throw new IllegalArgumentException("FrameworkDAO:executeStoredProcedure()");
        String sSQL = null;
        CallableStatement stmt = null;
        try {
            sSQL =
buildSQLForStoredProcedure(sProcedureName, parameters, false);
            stmt = prepareCallableStatementHelper(connection, sSQL);
            assignParametersToStatement(stmt, parameters, false);
            ResultSet rs = executeCallableStatement(stmt);
        } catch (SQLException exc) {
            throw new StoredProcedureException(exc.getMessage(), exc);
        } finally {
            close(stmt);
        }

    }

    public int executeStatement(String sSQL, Vector parameters,
                                Connection conn) throws IllegalArgumentException,
                                                        DatabaseConnectionFailureException,
                                                        ExecuteStatementException {
        if (sSQL == null || sSQL.length() == 0)
            throw new IllegalArgumentException("FrameworkDAO:executeStatement()");
        int rowCount = -1;
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sSQL);
            if (parameters != null) {
                Parameter param = null;
                int index = 1;
                Object o = null;
                for (Enumeration e = parameters.elements();
                     e.hasMoreElements(); ) {
                    param = (Parameter)e.nextElement();
                  if (param.isInParameter()) {
                        o = param.getParameter();
                        if (o != null)
                            pstmt.setObject(index, o);
                        else
                            pstmt.setObject(index, o, -1);
                    }
                    index++;
                }

            }
            rowCount = pstmt.executeUpdate();
        } catch (SQLException exc) {
            throw new ExecuteStatementException("FrameworkDAO:executeStatement - " +
                                                exc.getMessage(), exc);
        } finally {
            this.close(pstmt);

        }
        return rowCount;
    }

    public Collection executeSelectWithPaging(String sSQL, Vector parameters,
                                              String strValueObject,
                                              Integer page, Integer count,
                                              Integer[] totalCount) throws IllegalArgumentException,
                                                                           DatabaseConnectionFailureException,
                                                                           SelectStatementException,
                                                                           SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        ArrayList retValList = new ArrayList();

        PreparedStatement pstmt = null;
        Connection conn = null;
        Object vo = null;
        try {
            conn = getConnection();
            pstmt =
                    conn.prepareStatement(sSQL, ResultSet.TYPE_SCROLL_INSENSITIVE,
                                          ResultSet.CONCUR_READ_ONLY);
            if (parameters != null) {
                Parameter param = null;
                int index = 1;
                Object o = null;
                for (Enumeration e = parameters.elements();
                     e.hasMoreElements(); ) {
                    param = (Parameter)e.nextElement();
                    if (param.isInParameter()) {
                        o = param.getParameter();
                        if (o != null)
                            pstmt.setObject(index, o);
                        else
                            pstmt.setObject(index, o, -1);
                    }
                    index++;
                }
            }
            ResultSet rs = null;
            rs = pstmt.executeQuery();
            int endRow;
            int startRow = 0;
            int totalRow = 0;

            endRow = Integer.MAX_VALUE;
            if (page != null && count != null) {
                startRow = (page.intValue() - 1) * count.intValue();
                endRow = startRow + count.intValue();
            }

            if (startRow == 0)
                rs.beforeFirst();
            else
                rs.absolute(startRow);

            while (rs.next() && rs.getRow() <= endRow) {

                try {
                    vo = Class.forName(strValueObject).newInstance();
                    populateResultSetToBean(vo, rs);
                    retValList.add(vo);                    
                } catch (InstantiationException e) {
                    e.printStackTrace();
                    throw e;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    throw e;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    throw e;
                }
            }

            if (totalCount != null) {
                try {
                    totalRow = rs.getInt("totalCount");
                } catch (Exception e) {
                    totalRow = 0;
                }
                if (totalRow > 0) {
                    totalCount[0] = new Integer(totalRow);
                } else {
                    rs.last();
                    totalCount[0] = new Integer(rs.getRow());
                }

            }
        } catch (SQLException exc) {
            throw new SelectStatementException("FrameworkDAO:executeSelectWithPaging - " +
                                               exc.getMessage(), exc);
        } finally {
            this.close(pstmt);
            this.close(conn);
        }
        return retValList;

    }

    public Collection executeSelectWithPaging(Connection conn, String sSQL,
                                              Vector parameters,
                                              String strValueObject,
                                              Integer page, Integer count,
                                              Integer[] totalCount) throws IllegalArgumentException,
                                                                           DatabaseConnectionFailureException,
                                                                           SelectStatementException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        ArrayList retValList = new ArrayList();

        PreparedStatement pstmt = null;

        Object vo = null;
        try {
            pstmt =
                    conn.prepareStatement(sSQL, ResultSet.TYPE_SCROLL_INSENSITIVE,
                                          ResultSet.CONCUR_READ_ONLY);
            if (parameters != null) {
                Parameter param = null;
                int index = 1;
                Object o = null;
                for (Enumeration e = parameters.elements();
                     e.hasMoreElements(); ) {
                    param = (Parameter)e.nextElement();
                    if (param.isInParameter()) {
                        o = param.getParameter();
                        if (o != null)
                            pstmt.setObject(index, o);
                        else
                            pstmt.setObject(index, o, -1);
                    }
                    index++;
                }
            }
            ResultSet rs = null;
            try {
                rs = pstmt.executeQuery();

                int startRow = 0;
                int endRow = Integer.MAX_VALUE;
                int totalRow = 0;
                if (page != null && count != null) {
                    startRow = (page.intValue() - 1) * count.intValue();
                    endRow = startRow + count.intValue();
                }

                if (startRow == 0)
                    rs.beforeFirst();
                else
                    rs.absolute(startRow);

                while (rs.next() && rs.getRow() <= endRow) {
                    try {
                        vo = Class.forName(strValueObject).newInstance();
                        populateResultSetToBean(vo, rs);
                        retValList.add(vo);
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                        throw e;
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        throw e;
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        throw e;
                    }
                }

                if (totalCount != null) {
                    try {
                        totalRow = rs.getInt("totalCount");
                    } catch (Exception e) {
                        totalRow = 0;
                    }
                    if (totalRow > 0) {
                        totalCount[0] = new Integer(totalRow);
                    } else {
                        rs.last();
                        totalCount[0] = new Integer(rs.getRow());
                    }

                }
            } catch (SQLException exc) {
                throw new SelectStatementException("FrameworkDAO:executeSelectWithPaging - " +
                                                   exc.getMessage(), exc);
            } finally {
                this.close(rs);
            }
        } catch (SQLException exc) {
            throw new SelectStatementException("FrameworkDAO:executeSelectWithPaging - " +
                                               exc.getMessage(), exc);
        } finally {
            this.close(pstmt);
        }
        return retValList;
    }

    public Collection executeSelectWithPaging(Connection conn, String sSQL,
                                              Vector parameters,
                                              String strValueObject,
                                              Integer page, Integer count,
                                              Integer[] totalCount,
                                              String templateFileName,
                                              String destFileName,
                                              Map map) throws Exception {
        ArrayList retValList = new ArrayList();

        PreparedStatement pstmt = null;

        Object vo = null;
        try {
            pstmt =
                    conn.prepareStatement(sSQL, ResultSet.TYPE_SCROLL_INSENSITIVE,
                                          ResultSet.CONCUR_READ_ONLY);
            if (parameters != null) {
                Parameter param = null;
                int index = 1;
                Object o = null;
                for (Enumeration e = parameters.elements();
                     e.hasMoreElements(); ) {
                    param = (Parameter)e.nextElement();
                    if (param.isInParameter()) {
                        o = param.getParameter();
                        if (o != null)
                            pstmt.setObject(index, o);
                        else
                            pstmt.setObject(index, o, -1);
                    }
                    index++;
                }
            }
            ResultSet rs = null;
            try {
                rs = pstmt.executeQuery();
                if (map == null) {
                    map = new HashMap();
                }
                ResultSetCollection rsc = new ResultSetCollection(rs, true);
                map.put("rs", rsc);
                createReport(map, templateFileName, destFileName);
                int startRow = 0;
                int endRow = Integer.MAX_VALUE;
                int totalRow = 0;
                if (page != null && count != null) {
                    startRow = (page.intValue() - 1) * count.intValue();
                    endRow = startRow + count.intValue();
                }

                if (startRow == 0)
                    rs.beforeFirst();
                else
                    rs.absolute(startRow);

                while (rs.next() && rs.getRow() <= endRow) {
                    try {
                        vo = Class.forName(strValueObject).newInstance();
                        populateResultSetToBean(vo, rs);
                        retValList.add(vo);
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }

                if (totalCount != null) {
                    try {
                        totalRow = rs.getInt("totalCount");
                    } catch (Exception e) {
                        totalRow = 0;
                    }
                    if (totalRow > 0) {
                        totalCount[0] = new Integer(totalRow);
                    } else {
                        rs.last();
                        totalCount[0] = new Integer(rs.getRow());
                    }

                }
            } catch (Exception exc) {
                throw new Exception("FrameworkDAO:executeSelectWithPaging - " +
                                    exc.getMessage(), exc);
            } finally {
                this.close(rs);
            }
        } catch (SQLException exc) {
            throw new SelectStatementException("FrameworkDAO:executeSelectWithPaging - " +
                                               exc.getMessage(), exc);
        } finally {
            this.close(pstmt);
        }
        return retValList;
    }

    protected void assignParametersToStatement(CallableStatement stmt,
                                               Vector parameters,
                                               boolean bResultsetFlag) throws SQLException {
        if (parameters != null && parameters.size() > 0) {
            int index = 1;
            Parameter param = null;
            Object o = null;
            if (bResultsetFlag)
                index = 2;
            for (Enumeration e = parameters.elements(); e.hasMoreElements();
            ) {
                param = (Parameter)e.nextElement();
                if (param.isInParameter()) {
                    o = param.getParameter();
                    if (o != null)
                        stmt.setObject(index, o);
                    else
                        stmt.setObject(index, o, -1);
                } else {
                    stmt.registerOutParameter(index, param.getType());
                }
                index++;
            }
        }
    }

    protected CallableStatement prepareCallableStatementHelper(Connection connection,
                                                               String sSQL) throws SQLException {
        CallableStatement cs = null;
        cs = connection.prepareCall(sSQL);
        return cs;
    }

    protected ResultSet executeCallableStatement(CallableStatement stmt) throws SQLException {
        ResultSet rs = null;
        if (stmt != null)
            rs = stmt.executeQuery();
        return rs;
    }

    protected String buildSQLForStoredProcedure(String sProcedureName,
                                                Vector parameters,
                                                boolean bResultsetFlag) {
        StringBuffer sSQL = new StringBuffer("BEGIN ");
        sSQL.append(sProcedureName);
        if (parameters != null && parameters.size() > 0 || bResultsetFlag) {
            sSQL.append("(");
            int size = 0;
            if (parameters != null)
                size = parameters.size();
            for (int i = 0; i < size; ) {
                sSQL.append("?");
                if (++i < size)
                    sSQL.append(", ");
            }
            if (bResultsetFlag) {
                if (size > 0)
                    sSQL.append(",?);");
                else
                    sSQL.append("?);");
            } else {
                sSQL.append(");");
            }
        }
        sSQL.append(" END;");
        return sSQL.toString();
    }

    protected Object fillToObject(ResultSet rs, Object retvalVO,
                                  int enc) throws SQLException {
        String columnName = null;
        String typeName = null;
        Object value = null;
        ResultSetMetaData metaData = rs.getMetaData();
        int ncolumns = metaData.getColumnCount();

        // Scroll to next record and pump into hashmap
        try {
            for (int i = 1; i <= ncolumns; i++) {
                columnName = (metaData.getColumnName(i)).toLowerCase();
                if (!"".equals(PropertyUtils.getPropertyType(retvalVO,
                                                             columnName)) &&
                    PropertyUtils.getPropertyType(retvalVO, columnName) !=
                    null) {
                    typeName =
                            PropertyUtils.getPropertyType(retvalVO, columnName).getName();
                    if (typeName.equalsIgnoreCase("long")) {
                        value = new Long(rs.getLong(i));
                    } else if (typeName.equalsIgnoreCase("double")) {
                        value = new Double(rs.getDouble(i));
                    } else if (typeName.equalsIgnoreCase("java.lang.String")) {
                        if (enc == ENCODING_TCVN3_TO_UTF8) {
                            value =
                                    FontUtil.convertFromTCVN3(getString(rs, i));
                        } else if (enc == ENCODING_UTF8_TO_TCVN3) {
                            value = FontUtil.convertToTCVN3(getString(rs, i));
                        } else {
                            value = rs.getString(i);
                        }
                    } else if (typeName.equalsIgnoreCase("java.sql.Date")) {
                        value = rs.getDate(i);
                    } else if (typeName.equalsIgnoreCase("java.lang.Long")) {
                        if (rs.getString(i) == null) {
                            value = null;
                        } else {
                            value = new Long(rs.getLong(i));
                        }
                    } else if (typeName.equalsIgnoreCase("java.lang.Double")) {
                        if (rs.getString(i) == null) {
                            value = null;
                        } else {
                            value = new Double(rs.getDouble(i));
                        }
                    } else {
                        value = rs.getObject(i);
                    }
                    PropertyUtils.setProperty(retvalVO, columnName, value);

                } else {

                }
            }
        } catch (Exception ex) {
            throw new SQLException(ex.toString());
        }
        return retvalVO;
    }

    protected Object fillToObject(ResultSet rs,
                                  Object vo) throws SQLException {
        return fillToObject(rs, vo, ENCODING_TCVN3_TO_UTF8);
    }

    protected String getString(ResultSet rs, int arg0) throws Exception {
        InputStream in;
        byte[] strByte;
        int idx;

        in = rs.getBinaryStream(arg0);

        if (in == null) {
            return null;
        }

        strByte = new byte[in.available()];
        idx = 0;
        while (in.available() != 0) {
            strByte[idx] = (byte)in.read();
            ++idx;
        }

        return new String(strByte);
    }

    public void setDataSource(Connection conn) {
        this.conn = conn;
    }

    public long getSeqNextValue(String seqName) throws SQLException {
        Statement stmt = null;
        ResultSet rs;
        StringBuffer sql;
        long seqNextValue = -1;

        sql = new StringBuffer("SELECT ");
        sql.append(seqName);
        sql.append(".NEXTVAL FROM DUAL");

        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql.toString());
        if (rs.next()) {
            seqNextValue = rs.getLong(1);
        }
        close(rs);
        close(stmt);

        return seqNextValue;
    }

    public long getSeqNextValue(String seqName,
                                Connection con) throws SQLException {
        Statement stmt = null;
        ResultSet rs;
        StringBuffer sql;
        long seqNextValue = -1;

        sql = new StringBuffer("SELECT ");
        sql.append(seqName);
        sql.append(".NEXTVAL FROM DUAL");

        stmt = con.createStatement();
        rs = stmt.executeQuery(sql.toString());
        if (rs.next()) {
            seqNextValue = rs.getLong(1);
        }
        close(rs);
        close(stmt);

        return seqNextValue;
    }

    public void setStatementParameter(PreparedStatement pstmt, int beginIdx,
                                      Vector args,
                                      int enc) throws SQLException {

        for (int i = 0; i < args.size(); ++i) {
            if (args.get(i) == null) {
                pstmt.setObject(i + beginIdx, args.get(i), -1);
            } else {
                if (args.get(i) instanceof java.lang.String) {
                    if (enc == ENCODING_TCVN3_TO_UTF8) {
                        pstmt.setObject(i + beginIdx,
                                        FontUtil.convertFromTCVN3((String)args.get(i)));
                    } else if (enc == ENCODING_UTF8_TO_TCVN3) {
                        pstmt.setObject(i + beginIdx,
                                        FontUtil.convertToTCVN3((String)args.get(i)));
                    } else {
                        pstmt.setObject(i + beginIdx, args.get(i));
                    }
                } else if (args.get(i) instanceof java.lang.Boolean) {
                    pstmt.setBoolean(i + beginIdx,
                                     ((Boolean)args.get(i)).booleanValue());
                } else {
                    pstmt.setObject(i + beginIdx, args.get(i));
                }
            }
        }
    }

    public void setStatementParameter(PreparedStatement pstmt, int beginIdx,
                                      Vector args) throws SQLException {

        setStatementParameter(pstmt, beginIdx, args, ENCODING_UTF8_TO_TCVN3);
    }

    public static void populateResultSetToBean(Object beanTO, ResultSet rs,
                                               int enc) throws SQLException {

        //StringBuffer logMsg = new StringBuffer("[populate]");
        ResultSetMetaData metaData = rs.getMetaData();
        int ncolumns = metaData.getColumnCount();

        String columnName = null;
        String typeName;
        Object value;
        // Scroll to next record and pump into hashmap
        
        try {
            for (int i = 1; i <= ncolumns; i++) {
                columnName = (metaData.getColumnName(i)).toLowerCase();
                if (PropertyUtils.getPropertyType(beanTO, columnName) !=
                    null &&
                    !PropertyUtils.getPropertyType(beanTO, columnName).equals("")) {
                    typeName =
                            PropertyUtils.getPropertyType(beanTO, columnName).getName();
                    if (typeName.equalsIgnoreCase("long")) {
                        value = new Long(rs.getLong(i));
                    } else if (typeName.equalsIgnoreCase("double")) {
                        value = new Double(rs.getDouble(i));
                    } else if (typeName.equalsIgnoreCase("java.lang.String")) {
                        if (enc == EncodeUtil.ENCODING_TCVN3_TO_UTF8) {
                            value =
                                    EncodeUtil.convertFromTCVN3(getBinaryStream(rs,
                                                                                i));
                        } else if (enc == EncodeUtil.ENCODING_UTF8_TO_TCVN3) {
                            value =
                                    EncodeUtil.convertToTCVN3(getBinaryStream(rs,
                                                                              i));
                        } else {
                            value = rs.getString(i);
                        }
                    } else if (typeName.equalsIgnoreCase("java.sql.Date")) {
                        value = rs.getDate(i);
                    } else if (typeName.equalsIgnoreCase("java.lang.Long")) {
                        if (rs.getString(i) == null) {
                            value = null;
                        } else {
                            value = new Long(rs.getLong(i));
                        }
                    } else if (typeName.equalsIgnoreCase("java.lang.Double")) {
                        if (rs.getString(i) == null) {
                            value = null;
                        } else {
                            value = new Double(rs.getDouble(i));
                        }
                    }

                    else {
                        value = rs.getObject(i);
                    }
                    PropertyUtils.setProperty(beanTO, columnName, value);
                } else {                    
                    System.out.println("{Khong co setter tuong ung: " + columnName + "}");
                }
            }             
           
        } catch (Exception ex) {
            throw new SQLException(ex.toString());
        }
    }

    public static void populateResultSetToBean(Object beanTO,
                                               ResultSet rs) throws SQLException {

        populateResultSetToBean(beanTO, rs, EncodeUtil.ENCODING_NO);
    }

    public static String getBinaryStream(ResultSet rs,
                                         int arg0) throws Exception {
        InputStream in;
        byte[] strByte;
        int idx;

        in = rs.getBinaryStream(arg0);

        if (in == null) {
            return null;
        }

        strByte = new byte[in.available()];
        idx = 0;
        while (in.available() != 0) {
            strByte[idx] = (byte)in.read();
            ++idx;
        }

        return new String(strByte);
    }
    // release Connection

    protected void close(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn = null;
        }
    }
    // close ResultSet

    protected void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                rs = null;
            }

        }
    }

    // close PreparedStatement

    protected void close(PreparedStatement pstmt) {
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                pstmt = null;
            }

        }
    }

    // close Statement

    protected void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                stmt = null;
            }
        }
    }

    public Object executeFunction(String sFunctionName,
                                  Vector parameters) throws IllegalArgumentException,
                                                            DAOException,
                                                            DatabaseConnectionFailureException,
                                                            StoredProcedureException {
        if (sFunctionName == null || sFunctionName.length() == 0)
            throw new IllegalArgumentException("FrameworkDAO:executeStoredProcedure()");

        Object returnValues = null;
        CallableStatement stmt = null;
        String sSQL = null;
        Connection connection = null;
        try {
            connection = getConnection();
            sSQL = buildSQLForFunction(sFunctionName, parameters);
            stmt = connection.prepareCall(sSQL);
            assignParametersToStatement(stmt, parameters, false);
            stmt.execute();
            returnValues = stmt.getObject(1);
        } catch (SQLException exc) {
            throw new StoredProcedureException("FrameworkDAO:executeFunction - " +
                                               exc.getMessage(), exc);
        } finally {
            close(stmt);
            close(connection);
        }
        return returnValues;
    }

    public Object executeFunction(String sFunctionName, Vector parameters,
                                  Connection connection) throws IllegalArgumentException,
                                                                DAOException,
                                                                DatabaseConnectionFailureException,
                                                                StoredProcedureException {
        if (sFunctionName == null || sFunctionName.length() == 0)
            throw new IllegalArgumentException("FrameworkDAO:executeStoredProcedure()");

        Object returnValues = null;
        CallableStatement stmt = null;
        String sSQL = null;
        try {
            sSQL = buildSQLForFunction(sFunctionName, parameters);
            stmt = connection.prepareCall(sSQL);
            assignParametersToStatement(stmt, parameters, false);
            stmt.execute();
            returnValues = stmt.getObject(1);
        } catch (SQLException exc) {
            throw new StoredProcedureException("FrameworkDAO:executeFunction - " +
                                               exc.getMessage(), exc);
        } finally {
            close(stmt);
        }
        return returnValues;
    }

    protected String buildSQLForFunction(String sFunctionName,
                                         Vector parameters) {
        StringBuffer sSQL = new StringBuffer("BEGIN ");
        sSQL.append("? := ");
        sSQL.append(sFunctionName);
        if (parameters != null && parameters.size() > 1) {
            sSQL.append("(");
            for (int i = 1; i < parameters.size(); ) {
                sSQL.append("?");
                if (++i < parameters.size())
                    sSQL.append(", ");
            }
            sSQL.append(")");
        }
        sSQL.append(";");
        sSQL.append("END;");
        return sSQL.toString();
    }

    public ResultSet executeSelectStatement(String sSelectStatement,
                                            Vector parameters, Connection conn,
                                            PreparedStatement pstmt) throws Exception {

        if (sSelectStatement == null || sSelectStatement.length() == 0) {
            throw new IllegalArgumentException("FrameworkDAO:executeSelectStatement() - invalid select statement");
        }

        pstmt = conn.prepareStatement(sSelectStatement);
        if (parameters != null) {
            Parameter param = null;
            int index = 1;
            Object o = null;
            for (Enumeration e = parameters.elements(); e.hasMoreElements();
            ) {
                param = (Parameter)e.nextElement();
                if (param.isInParameter()) {
                    o = param.getParameter();
                    if (o != null) {
                        pstmt.setObject(index, o);
                    } else {
                        pstmt.setObject(index, o, -1);
                    }
                }
                index++;
            }
        }
        return pstmt.executeQuery();
    }

    /**
     *Tao file bao cao tu Map
     * @param map
     * @param templateFileName
     * @param destFileName
     * @throws Exception
     */
    public void createReport(Map map, String templateFileName,
                             String destFileName) throws Exception {
        try {
            Configuration config = new Configuration();
            //            config.setUTF16(true);
            XLSTransformer transformer = new XLSTransformer(config);
            transformer.transformXLS(templateFileName, map, destFileName);
        } catch (Exception ex) {
            throw new Exception("FrameworkDAO:createReport() - " +
                                ex.getMessage());
        }
    }

    /**
     *Tao file bao cao tu Collection va ten Collection, ten nay duoc su dung trong file template
     * @param coll
     * @param collName
     * @param templateFileName
     * @param destFileName
     * @throws Exception
     */
    protected void createReport(Collection coll, String collName,
                                String templateFileName,
                                String destFileName) throws Exception {
        try {
            Map map = new HashMap();
            map.put(collName, coll);
            Configuration config = new Configuration();
            //          config.setUTF16(true);
            XLSTransformer transformer = new XLSTransformer(config);
            transformer.transformXLS(templateFileName, map, destFileName);
        } catch (Exception ex) {
            throw new Exception("FrameworkDAO:createReport() - " +
                                ex.getMessage());
        }
    }

    /**
     *Tao file bao cao tu resultSet va ten cua resultset, ten nay duoc su dung trong file template
     * @param resultSet
     * @param resultSetName
     * @param templateFileName
     * @param destFileName
     * @throws Exception
     */
    protected void createReport(ResultSet resultSet, String resultSetName,
                                String templateFileName,
                                String destFileName) throws Exception {
        Map beans = new HashMap();
        ResultSetCollection rsc = new ResultSetCollection(resultSet, true);
        beans.put(resultSetName, rsc);
        Configuration config = new Configuration();
        //          config.setUTF16(true);
        XLSTransformer transformer = new XLSTransformer(config);
        transformer.transformXLS(templateFileName, beans, destFileName);
    }

    public Connection getConnectionTest() throws DatabaseConnectionFailureException {
        try {
            String driverName = "oracle.jdbc.driver.OracleDriver";
            Class.forName(driverName);
            String serverName = "10.96.1.93";
            String portNumber = "1521";
            String sid = "ttsp";
            String url =
                "jdbc:oracle:thin:@" + serverName + ":" + portNumber + ":" +
                sid;

            String username = "ttsp_owner2";
            String password = "oracle1";
            conn = DriverManager.getConnection(url, username, password);

            if (conn != null) {
                conn.setAutoCommit(false);
            } else {
//                printMessage("FrameworkDAO:getConnection() - Using a datasource, the connection returned is null.");
                throw new DatabaseConnectionFailureException("FrameworkDAO:getConnection() - Using a datasource, the connection returned is null.");
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionFailureException("FrameworkDAO:getConnection()-" +
                                                         e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new DatabaseConnectionFailureException("FrameworkDAO:getConnection()-" +
                                                         e.getMessage());
        }
        return conn;
    }

    protected Connection conn = null;
    public static final int ENCODING_TCVN3_TO_UTF8 = 1;
    public static final int ENCODING_UTF8_TO_TCVN3 = 2;
    private static final String dataSourceJNDI = "jdbc/ttspDS";

    public static void main(String[] a) {
        try {
            boolean b =
                PropertyUtils.isReadable(Class.forName("com.seatech.ttsp.ltt.LTToanVO").newInstance(),
                                         "hhuiyui");
            System.out.println("OK" + b);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
