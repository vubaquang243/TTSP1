package com.seatech.framework.strustx;


import com.thoughtworks.xstream.XStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;

import javax.servlet.http.HttpServlet;

import javax.sql.DataSource;


public class AppServlet extends HttpServlet{
  protected Connection getConnection() throws Exception {
      InitialContext ctx = null;
      Connection conn = null;
      
          ctx = new InitialContext();
          DataSource ds = (javax.sql.DataSource)ctx.lookup(dataSourceJNDI);
          conn = ds.getConnection();
          if (conn != null) {
              conn.setAutoCommit(false);
          } 
      return conn;
  }
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
  protected String populateBeanToXml(Object vo, String strValueObject, String rootElement) throws Exception {

          XStream xstream = new XStream();

          xstream.alias(rootElement, Class.forName(strValueObject));

          String xml = xstream.toXML(vo);

          return xml;
  }
  
  private final String dataSourceJNDI = "jdbc/ttspDS";
}
