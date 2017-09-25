<%@ include file="/includes/ttsp_header.inc"%>
<html:form action="/themMoiUserAction/addExc">        
<table>
  <tr>
    <td>T&#234;n &#273;&#259;ng nh&#7853;p</td>
    <td><html:text property="username" styleId="username"  size="20"/></td>
  </tr>
  <tr>
    <td>M&#7853;t kh&#7849;u</td>
    <td><html:password property="password" styleId="password" size="20"/></td>
  </tr>
  <tr>
    <td><html:submit>Ghi</html:submit></td>
    <td></td>
  </tr>
</table>
</html:form>


<%@ include file="/includes/ttsp_bottom.inc"%>