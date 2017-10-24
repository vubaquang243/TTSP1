<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ include file="/includes/ttsp_header.inc"%>
<table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
  <tbody>
    <tr>
      <td width="13">
        <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg"
             width="13" height="30"/>
      </td>
      <td background="<%=request.getContextPath()%>/styles/images/T2.jpg"
          width="75%">
        <span class="title2">Trang chủ</span>
      </td>
      <td width="62">
        <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg"
             width="62" height="30"/>
      </td>
      <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg"
          width="20%">&nbsp;</td>
      <td width="4">
        <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg"
             width="4" height="30"/>
      </td>
    </tr>
  </tbody>
</table>
<html:form action="/thongkeTongHop.do">
  <div style="border:1px #e1e1e1 solid;min-height:200px;display: table;width:100%;">
      <div align="right"><a style="font-size:10pt" href='<html:rewrite page="/thongkeTongHop.do"/>'>Thống kê truyền tin</a></div>
      <logic:notEmpty name="news">
          <div style="width:44%;float:left;padding-right:10px;padding-left:10px;padding-bottom:10px;margin-left:20px;margin-top:10px;margin-bottom:10px;margin-right:20px;border:1px solid #e1e1e1;">
            <h2 style="color:#006699;background-image: url(/TTSP/styles/images/navi22.jpg);line-height:25px" align="center">Thông báo</h2>
            <ul>
              <logic:iterate id="new_item" name="news">
                <li style="color:#006699;"><h2><bean:write name="new_item" property="tieu_de"/></h2></li>
                <bean:define id="temp" name="new_item" property="noi_dung" type="java.lang.String"/>
                <dt style="font-size:10pt"><%=temp%></dt>
              </logic:iterate>
            </ul>
          </div>
      </logic:notEmpty>
     
  </div>
</html:form>
<%@ include file="/includes/ttsp_bottom.inc"%>