<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<SCRIPT type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery.htm"></SCRIPT>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/lov.js"></script>

<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery.table_navigation.htm"></script>
<LINK rel="stylesheet" type="text/css" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/oracle.css" media="screen"/>
<SCRIPT type="text/javascript">
          jQuery.tableNavigation({
            select_event: 'mouseover',
            selected_class: 'TR_Selected'
        });
        jQuery('#tblLovDM > tr').click( function(){
          alert('You clicked row '+ ($(this).index()+1) );
        });
        </SCRIPT>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title>lovDM</title>
  </head>
  <body>
    <div id="dialog-form-lov-dm" style="width:40%;height:60%; border-top: 1px solid black;
                                                                          border-right: 1px solid black;
                                                                          border-bottom: 1px solid black;
                                                                          border-left: 1px solid black;">
      <p class="validateTips"></p>
      <html:form action="lovAction.do" method="post">
        <table style="width:100%;height:100%;">
          <tr>
            <td>Mã</td>
            <td>
                <input type="text" name="ma_lov" id="ma_lov" size="10" />              
            </td>
            <td>T&ecirc;n </td>
            <td>
                <input type="text" name="ten_lov" id="ten_lov" size="40" />
                <input type="hidden" name="loai_lov" id="loai_lov"/>
                <input type="hidden" name="ma_field_id_lov" id="ma_field_id_lov"/>
                <input type="hidden" name="ten_field_id_lov" id="ten_field_id_lov"/>
            </td>
            <td>
              <button type="button" name="btnSearch" onclick="fillLovDM();">Tìm</button>
            </td>
          </tr>
        </table>
        <br>
        <div style="width:100%;height:450px;">
        <table class="navigateable focused" cellspacing="0" cellpadding="1"
                   bordercolor="#e1e1e1" border="1" align="center" width="98%"
                   style="BORDER-COLLAPSE: collapse"  id="tblLovDM">
          <thead>
          <tr>
            <th class="promptText" bgcolor="#f0f0f0" width="20%">M&atilde;</th>
            <th class="promptText" bgcolor="#f0f0f0">T&ecirc;n</th>
          </tr>          
          </thead>
          <tbody class='navigateable focused'>
          </tbody>
        </table>
        </div>
      </html:form>
    </div>
  </body>
</html>