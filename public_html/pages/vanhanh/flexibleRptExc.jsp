
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.KTVTabmisResource"/>
<%@ include file="/includes/ttsp_header.inc"%>
<%@ page import="java.util.Vector"%>
  <body>
 
   <script type="text/javascript" language="javascript"> 
   function submitForm(type) {
      var f = document.forms[0];      
      if(type == 'param'){        
        f.action = "flexibleRptParamAction.do?type=param";       
      }else if(type == 'exit'){
        
      }else if(type == 'find'){
        f.action = "flexibleRptParamAction.do?type=find";
      }
      f.submit();       
    }
  </script>
  
  <html:form action="/flexibleRptAction.do">
  <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
         <tr>
              <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
              <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
                <span class=title2>Tra cứu tổng hợp</span>
              </td>
              <td width=62><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg" width=62 height=30/></td>
              <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg" width="20%">&nbsp;</td>
              <td width=4><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg" width=4 height=30/></td>
            </tr> 
      </tbody>
    </table>
    <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
             
      <tr>
        <td>
          <br/>
          <table width="100%" cellspacing="0" cellpadding="1"
                 bordercolor="#e1e1e1" border="0" align="center"
                 style="BORDER-COLLAPSE: collapse">
            <tr>
              <td width="10%" align="right">
                Loại báo cáo
              </td>
              <td width="15%">
                <html:select styleClass="selectBox" property="key_rpt" styleId="key_rpt" style="width:100%;height:20px" onkeydown="if(event.keyCode==13) event.keyCode=9;" onchange="submitForm('param');" >
                            <html:option value="">
                              Chọn loại báo cáo
                            </html:option>
                            <html:optionsCollection label="name_rpt" value="key_rpt" name="rptType"/>
                </html:select>
              </td>
              <td  width="10%" align="right">
              <bean:write name="FlexibleRptForm" property="param1"/> 
              </td>
              <td width="15%">
              <html:text property="param1_val"
                               onfocus="this.style.backgroundColor='#ffffb5'"
                               onblur="this.style.backgroundColor='#ffffff'"
                               style="width:90%;height:20px" styleclass="promptText"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
              </td>
              <td width="10%" align="right">
              <bean:write name="FlexibleRptForm" property="param2"/> 
              </td>
              <td width="15%">
              <html:text property="param2_val"
                               onfocus="this.style.backgroundColor='#ffffb5'"
                               onblur="this.style.backgroundColor='#ffffff'"
                               style="width:90%;height:20px" styleclass="promptText"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
              </td>  
              <td width="10%" align="right">
              <bean:write name="FlexibleRptForm" property="param3"/> 
              </td>
              <td width="15%">
              <html:text property="param3_val"
                               onfocus="this.style.backgroundColor='#ffffb5'"
                               onblur="this.style.backgroundColor='#ffffff'"
                               style="width:90%;height:20px" styleclass="promptText"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
              </td>
              
            </tr>
            <tr>
              <td  width="10%" align="right">
              <bean:write name="FlexibleRptForm" property="param4"/> 
              </td>
              <td width="15%">
              <html:text property="param4_val"
                               onfocus="this.style.backgroundColor='#ffffb5'"
                               onblur="this.style.backgroundColor='#ffffff'"
                               style="width:90%;height:20px" styleclass="promptText"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
              </td>
              <td  width="10%" align="right">
              <bean:write name="FlexibleRptForm" property="param5"/> 
              </td>
              <td width="15%">
              <html:text property="param5_val"
                               onfocus="this.style.backgroundColor='#ffffb5'"
                               onblur="this.style.backgroundColor='#ffffff'"
                               style="width:90%;height:20px" styleclass="promptText"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
              </td>
              <td width="10%" align="right">
              <bean:write name="FlexibleRptForm" property="param6"/> 
              </td>
              <td width="15%">
              <html:text property="param6_val"
                               onfocus="this.style.backgroundColor='#ffffb5'"
                               onblur="this.style.backgroundColor='#ffffff'"
                               style="width:90%;height:20px" styleclass="promptText"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
              </td>  
              <td width="10%" align="right">
              <bean:write name="FlexibleRptForm" property="param7"/> 
              </td>
              <td width="15%">
              <html:text property="param7_val"
                               onfocus="this.style.backgroundColor='#ffffb5'"
                               onblur="this.style.backgroundColor='#ffffff'"
                               style="width:90%;height:20px" styleclass="promptText"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
              </td>
              
            </tr>
            <tr>
              <td  width="10%" align="right">
              <bean:write name="FlexibleRptForm" property="param8"/> 
              </td>
              <td width="15%">
              <html:text property="param8_val"
                               onfocus="this.style.backgroundColor='#ffffb5'"
                               onblur="this.style.backgroundColor='#ffffff'"
                               style="width:90%;height:20px" styleclass="promptText"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
              </td>
              <td  width="10%" align="right">
              <bean:write name="FlexibleRptForm" property="param9"/> 
              </td>
              <td width="15%">
              <html:text property="param9_val"
                               onfocus="this.style.backgroundColor='#ffffb5'"
                               onblur="this.style.backgroundColor='#ffffff'"
                               style="width:90%;height:20px" styleclass="promptText"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
              </td>
              <td width="10%" align="right">
              <bean:write name="FlexibleRptForm" property="param10"/> 
              </td>
              <td width="15%">
              <html:text property="param10_val"
                               onfocus="this.style.backgroundColor='#ffffb5'"
                               onblur="this.style.backgroundColor='#ffffff'"
                               style="width:90%;height:20px" styleclass="promptText"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
              </td>  
              <td width="10%" align="right">
                <button type="button" class="ButtonCommon" accesskey="t"
                      onclick="submitForm('find')"> 
                  <span class="sortKey">T</span>ra cứu
                </button> 
              </td>
              <td width="15%">
               <button type="button" class="ButtonCommon" accesskey="t"
                      onclick="submitForm('exit')"> 
                <span class="sortKey">T</span>hoát
               </button>
              </td>
              
            </tr>
          </table>
          <html:hidden property="pkg_name"/>
          <br/>
        </td>
      </tr>
    </table>
  
  
  
    <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
      <tbody>
        <tr>
          <td class="title" colspan="6"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="24">
            <font color="Gray">
              Kết quả tra cứu
            </font>
          </td>
        </tr>
        <tr>
          <td>
            <table class="navigateable focused" cellspacing="0" cellpadding="1"
                   bordercolor="#e1e1e1" border="1" align="center" width="100%"
                   style="BORDER-COLLAPSE: collapse">
    <%
    try{
    if(request.getAttribute("arrRpt") != null){
    Vector vtRpt = (Vector)request.getAttribute("arrRpt");
    if(vtRpt !=null){
      for(int i = 0; i < vtRpt.size(); i++){        
        Vector vtItem = (Vector)vtRpt.get(i);
        if(i == 0){
          %><thead><%
        }else{
          %><tr class='<%=i % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'><%
        }
        for(int j = 0; j < vtItem.size(); j++){
          if(i == 0){
            %><th class="promptText" height="22" bgcolor="#f0f0f0"><%=vtItem.get(j).toString().toUpperCase()%></th><%
          }else{
            %><td><%=vtItem.get(j)%></th><%
          }
        }
        if(i == 0){
          %></tr><%
        }else{
          %>
          </thead>
          <tbody class="navigateable focused" cellspacing="0" cellpadding="1" bordercolor="#e1e1e1">
          <%
        }
      }
    }
    }
    }catch(Exception ex){
      ex.printStackTrace();
      %>
      <%=ex.getMessage()%>
      <%
    }
    %>
    </tbody>
    </table>
    </td>
    </tr>
    </tbody>
    </table>
  </html:form>
  
 </body>
<%@ include file="/includes/ttsp_bottom.inc"%>
