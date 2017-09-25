package com.seatech.ttsp.reports.flexible;


import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import java.util.Collection;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.jdbc.OracleTypes;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class FlexibleRptAction extends AppAction {
    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
            conn = getConnection(request);

            FlexibleRptDAO dao = new FlexibleRptDAO(conn);
            Collection col = dao.getFlexibleParamList("", null);
            request.setAttribute("rptType", col);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cstmt.close();
            conn.close();
            return mapping.findForward(AppConstants.SUCCESS);
        }
    }

    public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        Connection conn = null;
        CallableStatement cstmt = null;
        String strType = "";
        Vector arrRpt = new Vector();;
        try {
            conn = getConnection(request);
            FlexibleRptForm f = (FlexibleRptForm)form;
            FlexibleRptDAO dao = new FlexibleRptDAO(conn);
            strType =
                    request.getParameter("type") == null ? "" : request.getParameter("type").toString();
            //if ("param".equals(strType)) {
            Vector vParam = new Vector();
            vParam.add(new Parameter(f.getKey_rpt(), true));
            FlexibleRptVO flexibleRptVO =
                dao.getFlexibleParam(" and a.key_rpt = ?", vParam);
            if (flexibleRptVO != null) {
                BeanUtils.copyProperties(f, flexibleRptVO);
            }
            //} else
            if ("find".equals(strType)) {

                cstmt =
                        conn.prepareCall("begin " + f.getPkg_name() + "(?,?,?,?,?,?,?,?,?,?,?,?); end;");
                cstmt.setString(1, f.getKey_rpt());
                cstmt.setString(2, f.getParam1_val());
                cstmt.setString(3, f.getParam2_val());
                cstmt.setString(4, f.getParam3_val());
                cstmt.setString(5, f.getParam4_val());
                cstmt.setString(6, f.getParam5_val());
                cstmt.setString(7, f.getParam6_val());
                cstmt.setString(8, f.getParam7_val());
                cstmt.setString(9, f.getParam8_val());
                cstmt.setString(10, f.getParam9_val());
                cstmt.setString(11, f.getParam10_val());
                cstmt.registerOutParameter(12, OracleTypes.CURSOR);
                cstmt.execute();
                ResultSet rs = (ResultSet)cstmt.getObject(12);                
                Vector arrItem = null;

                int ncolumns = 0;
                if (rs != null) {
                    ResultSetMetaData metaData = rs.getMetaData();
                    ncolumns = metaData.getColumnCount();
                    //Header
                    arrItem = new Vector();
                    arrItem.add("STT");
                    for (int i = 1; i <= ncolumns; i++) {
                        arrItem.add((metaData.getColumnName(i)));
                    }
                    arrRpt.add(arrItem);
                    //Detail
                    int nStt = 0;
                    while (rs.next()) {
                        nStt++;
                        arrItem = new Vector();
                        arrItem.add(nStt);
                        for (int i = 1; i <= ncolumns; i++) {
                            arrItem.add(String.valueOf(rs.getObject(i)));
                        }
                        arrRpt.add(arrItem);
                    }
                }
            }
            request.setAttribute("arrRpt", arrRpt);

            Collection col = dao.getFlexibleParamList("", null);
            request.setAttribute("rptType", col);

        } catch (Exception e) {
            e.printStackTrace();            
        } finally {
            if (cstmt != null)
                cstmt.close();
            close(conn);
            return mapping.findForward(AppConstants.SUCCESS);
        }
    }
    //  public ActionForward executeAction(ActionMapping mapping, ActionForm form,
    //                                     HttpServletRequest request,
    //                                     HttpServletResponse response) throws Exception {
    //      Connection conn = null;
    //      CallableStatement cstmt = null;
    //      try {
    //            conn = getConnection(request);
    //            cstmt =
    //                    conn.prepareCall("begin sp_rpt_pkg.pro_flexible_rpt(?,?,?,?,?,?,?,?,?,?,?); end;");
    //            cstmt.setString(1, " ");
    //            cstmt.setString(2, " ");
    //            cstmt.setString(3, " ");
    //            cstmt.setString(4, " ");
    //            cstmt.setString(5, " ");
    //            cstmt.setString(6, " ");
    //            cstmt.setString(7, " ");
    //            cstmt.setString(8, " ");
    //            cstmt.setString(9, " ");
    //            cstmt.setString(10, " ");
    //            cstmt.registerOutParameter(11, OracleTypes.CURSOR);
    //            cstmt.execute();
    //            ResultSet rs = (ResultSet)cstmt.getObject(11);
    //          Vector arrRpt = new Vector();
    //            Vector arrItem = null;

    //            int ncolumns = 0;
    //            if (rs != null) {
    //                ResultSetMetaData metaData = rs.getMetaData();
    //                ncolumns = metaData.getColumnCount();
    //                //Header
    //                arrItem = new Vector();
    //                arrItem.add("STT");
    //                for (int i = 1; i <= ncolumns; i++) {
    //                    arrItem.add((metaData.getColumnName(i)));
    //                }
    //                arrRpt.add(arrItem);
    //                //Detail
    //                int nStt = 0;
    //                while (rs.next()) {
    //                    nStt ++;
    //                    arrItem = new Vector();
    //                    arrItem.add(nStt);
    //                    for (int i = 1; i <= ncolumns; i++) {
    //                        arrItem.add(String.valueOf(rs.getObject(i)));
    //                    }
    //                    arrRpt.add(arrItem);
    //                }
    //            }

    //          request.setAttribute("arrRpt", arrRpt);
    //
    //      } catch (Exception e) {
    //          e.printStackTrace();
    //      } finally {
    //          cstmt.close();
    //          conn.close();
    //          return mapping.findForward(AppConstants.SUCCESS);
    //      }
    //  }
}
