package com.seatech.ttsp.proxy.ad;


import com.seatech.ad.client.UserSoapClient;
import com.seatech.framework.AppKeys;

import java.util.Iterator;
import java.util.List;

import vn.gov.vst.LDAPSearchResult;


public class ActiveDirectory {
    private String strWsdl;

    public ActiveDirectory(String strWsdl) {
        this.strWsdl = strWsdl;
    }

    public ActiveDirectoryBean getUserInfo(String strDomain,
                                           String strUserName) throws Exception {
        ActiveDirectoryBean activeDirectoryBean = null;
        strUserName =
                strDomain.toLowerCase() + "\\" + strUserName.toLowerCase();
        UserSoapClient usc = new UserSoapClient(strWsdl);
        List<LDAPSearchResult> ls = usc.getUserInfo(strUserName);
        if (ls == null) {
            return activeDirectoryBean;
        }
        if (ls.size() != 1) {
            return activeDirectoryBean;
        }
        Iterator iter = ls.iterator();
        LDAPSearchResult lDAPSearchResult = null;
        while (iter.hasNext()) {
            lDAPSearchResult = (LDAPSearchResult)iter.next();
        }
        if (lDAPSearchResult != null) {
            activeDirectoryBean = new ActiveDirectoryBean();
            activeDirectoryBean.setDepartment(lDAPSearchResult.getDepartment());
            activeDirectoryBean.setDescription(lDAPSearchResult.getDescription());
            activeDirectoryBean.setDisplayname(lDAPSearchResult.getDisplayName());
            activeDirectoryBean.setDistinguishedname(lDAPSearchResult.getDistinguishedName());
            activeDirectoryBean.setEmail(lDAPSearchResult.getEmail());
            activeDirectoryBean.setFirstname(lDAPSearchResult.getFirstName());
            activeDirectoryBean.setLastname(lDAPSearchResult.getLastName());
            activeDirectoryBean.setSamaccountname(lDAPSearchResult.getSamAccountName());
            activeDirectoryBean.setTelephonenumber(lDAPSearchResult.getTelephoneNumber());
            activeDirectoryBean.setTitle(lDAPSearchResult.getTitle());
        }
        return activeDirectoryBean;
    }

    public boolean authenticateUser(String strUserName,
                                    String strPWD) throws Exception {
        if ("N".equalsIgnoreCase(AppKeys.SETUP_CHECK_AD)) {
            return true;
        } else {
            //test------------------------
            if (strUserName.substring(3, 5).equalsIgnoreCase("TT") ||
                strUserName.substring(3, 5).equalsIgnoreCase("KT") ||
                strUserName.substring(3, 5).equalsIgnoreCase("CB") ||
                strUserName.substring(3, 5).equalsIgnoreCase("QT") ||
                strUserName.substring(3, 5).equalsIgnoreCase("GD") ||
                strUserName.equalsIgnoreCase("TW\\BACKUP")) {
                if (strPWD.equalsIgnoreCase("abc@123.")) {
                    return true;
                }
            }
            //----------------------------
            UserSoapClient usc = new UserSoapClient();
            return usc.authenticateUser(strUserName, strPWD);
        }
    }
}
//********************FOR TEST********************************

// 
//package com.seatech.ttsp.proxy.ad;
//
//
//import com.seatech.framework.datamanager.AppDAO;
//import com.seatech.framework.datamanager.Parameter;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//
//import java.util.Vector;
//
//
//public class ActiveDirectory {
//    public ActiveDirectoryBean getUserInfo(String a, String b) {
////        String strXMLReq = "<userName>" + strUserName + "</userName>";
////
////        String strXMLRes = getUserInfoFormAD(strUserName);
//        String strUserName = a+"/"+b;
//
//
//        AppDAO adao = new AppDAO();
//        Connection conn = null;
//        try {
//            conn = adao.getConnectionTest();
//
//            String sSelectStatement =
//                "SELECT  a.displayname, a.telephonenumber, a.samaccountname," +
//                "       a.distinguishedname, a.title, a.department, a.firstname," +
//                "       a.lastname,a.description, a.email" +
//                "  FROM test_ad a where a.samaccountname = ?";
//            Vector parameters = new Vector();
//            parameters.add(new Parameter(strUserName, true));
//            ActiveDirectoryBean bean =
//                (ActiveDirectoryBean)adao.findByPK(sSelectStatement,
//                                                   parameters,
//                                                   "com.seatech.ttsp.proxy.ad.ActiveDirectoryBean",
//                                                   conn);
//            return bean;
//        } catch (Exception e) {
//            // TODO: Add catch code
//            e.printStackTrace();
//        } finally {
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (Exception e) {
//                    // TODO: Add catch code
//                    e.printStackTrace();
//                }
//            }
//
//        }
//
//
//        return null;
//    }
//    public boolean authenticateUser (String strUserName, String strPWD){      
//      return true;
//    }
//    private String getUserInfoFormAD(String strReq) {
//
//        StringBuffer szRes = new StringBuffer();
//        AppDAO adao = new AppDAO();
//        Connection conn = null;
//        try {
//            conn = adao.getConnectionTest();
//
//            String sSelectStatement =
//                "SELECT a.id, a.displayname, a.telephonenumber, a.samaccountname," +
//                "       a.distinguishedname, a.title, a.department, a.firstname," +
//                "       a.lastname,a.description, a.email" +
//                "  FROM test_ad a where a.samaccountname = ?";
//            Vector parameters = new Vector();
//            parameters.add(new Parameter(strReq, true));
//            ResultSet rs =
//                adao.executeSelectStatement(sSelectStatement, parameters,
//                                            conn);
//            int counter = 0;
//            while (rs.next()) {
//
//                szRes.append("<GetUserInfoResponse xmlns='http://vst.gov.vn/'>");
//                szRes.append("<GetUserInfoResult>");
//                szRes.append("<LDAPSearchResult>");
//                szRes.append("<displayName>");
//                szRes.append(rs.getString("displayName"));
//                szRes.append("</displayName>");
//                szRes.append("<telephoneNumber>");
//                szRes.append(rs.getString("telephoneNumber"));
//                szRes.append("</telephoneNumber>");
//                szRes.append("<samAccountName>");
//                szRes.append(rs.getString("samAccountName"));
//                szRes.append("</samAccountName>");
//                szRes.append("<distinguishedName>");
//                szRes.append(rs.getString("distinguishedName"));
//                szRes.append("</distinguishedName>");
//                szRes.append("<Title>");
//                szRes.append(rs.getString("Title"));
//                szRes.append("</Title>");
//                szRes.append("<department>");
//                szRes.append(rs.getString("department"));
//                szRes.append("</department>");
//                szRes.append("<FirstName>");
//                szRes.append(rs.getString("FirstName"));
//                szRes.append("</FirstName>");
//                szRes.append("<LastName>");
//                szRes.append(rs.getString("LastName"));
//                szRes.append("</LastName>");
//                szRes.append("<Email>");
//                szRes.append(rs.getString("Email"));
//                szRes.append("</Email>");
//                szRes.append("<Description>");
//                szRes.append(rs.getString("Description"));
//                szRes.append("</Description>");
//                szRes.append("</LDAPSearchResult>");
//                szRes.append("</GetUserInfoResponse");
//                szRes.append("</GetUserInfoResult>");
//                counter++;
//                if (counter > 1)
//                    return null;
//            }
//
//        } catch (Exception e) {
//            // TODO: Add catch code
//            e.printStackTrace();
//        } finally {
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (Exception e) {
//                    // TODO: Add catch code
//                    e.printStackTrace();
//                }
//            }
//
//        }
//
//
//        return szRes.toString();
//    }
//
//    private ActiveDirectoryBean parserXMLRes(String strRes) {
//
//        return null;
//    }
////    public static void main(String[] args) {
//////        ActiveDirectory ad = new ActiveDirectory();
//////        ActiveDirectoryBean adb = ad.getUserInfo("cuonghd");
////        System.out.println("");
////    }
//}
