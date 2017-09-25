<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ include file="/includes/ttsp_header.inc"%>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.KTVTabmisResource"/>

<div class="app_error">
  <html:errors/>
</div>
  <html:form action="/DongBoHoaListAction.do" method="post">
    <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
      <tbody>
        <tr>
          <td width="13">
            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width="13" height="30"/>
          </td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
            <span class="title2" height="24" background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/bg_Title.jpg" colspan="6">
              <font size="2">
                Quản lý Thread tự động
              </font>
            </span>
          </td>
          <td width="62">
            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg" width="62" height="30"/>
          </td>
          <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg" width="20%">&nbsp;</td>
          <td width="4">
            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg" width="4" height="30"/>
          </td>
        </tr>
      </tbody>
    </table>
  <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
      <tbody>
        <tr>
          <td class="title" colspan="6"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="24" style="padding-left:25px;">
            <!--<span>&nbsp;&nbsp;&nbsp;&nbsp;<font color="Gray">
                <fmt:message key="lsungatketnoi.title.dieukien"/>
              </font></span>-->
              Danh sách Thread
          </td>
        </tr>
      </tbody>
       
      <tr>
        <td>
             <%-- Hiển thị list KTV--%>
              <table  class="navigateable focused" cellspacing="0"
                      cellpadding="1" bordercolor="#e1e1e1"
                      border="1" align="center" width="100%"
                      style="BORDER-COLLAPSE: collapse">
                      <thead>
                        <th height="22" bgcolor="#f0f0f0" width="5%" align="center">
                            <input type="checkbox" name="Checkall" value="yes" onclick="Check(this)"/>
                        </th>
                        <th  height="22" bgcolor="#f0f0f0" width="10%" align="center">
                          <label>T&#234;n</label>
                        </th>
                        <th  height="22" bgcolor="#f0f0f0" width="8%" align="center">
                          <label>Tr&#7841;ng th&#225;i</label>
                        </th>
                        
                        <th  bgcolor="#f0f0f0" width="11%" align="center">
                          <label>Thời gian bắt đầu</label>
                        </th>
                        <th  bgcolor="#f0f0f0" width="11%" align="center">
                          <label>Thời gian dừng</label>
                        </th>
                        <th  bgcolor="#f0f0f0" width="4%" align="center">
                          <label>Chu kỳ</label>
                        </th>
                       
                        <th  bgcolor="#f0f0f0" width="25%" align="center">
                          <label>M&#244; t&#7843;</label>
                        </th>
                        <!-- HungBM - 20170419 - Them column hien thi thoi gian update cuoi cung - BEGIN -->
                        <th  bgcolor="#f0f0f0" width="10%" align="center">
                          <label>Ngày thay đổi cuối</label>
                        </th>
                        <!-- HungBM - 20170419 - Them column hien thi thoi gian update cuoi cung - END -->
                         <th  bgcolor="#f0f0f0" width="5%" align="center">
                          <label>Xem log</label>
                        </th>
                        <th  bgcolor="#f0f0f0" width="5%" align="center">
                          <label>Xem log err</label>
                        </th>
                      </thead>
                      <tbody class="navigateable focused" cellspacing="0"
                              cellpadding="1" bordercolor="#e1e1e1">
                          <logic:present name="listDongBoNSD">
                            <logic:notEmpty name="listDongBoNSD">
                           <logic:iterate id="DongBoHoalist" name="listDongBoNSD" indexId="stt">
                            <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                              <td width="5%" align="center" >
                                <input type="checkbox" name="check" value='<bean:write name="DongBoHoalist" property="class_name"/>'>
                                  <param name="ts" id="ts" value='<bean:write name="DongBoHoalist" property="class_name"/>'/>
                                </input>
                               </td>
                              <td width="10%">
                                <a href='<html:rewrite page="/DongBoHoaUpdateAction.do"/>?longid=<bean:write name="DongBoHoalist" property="id"/>'>
                                  <bean:write name="DongBoHoalist" property="thread_name"/>
                                </a>
                              </td>
                              <td width="8%">
                                <logic:notEqual name="DongBoHoalist" property="status" value="0">Started</logic:notEqual>
                                <logic:notEqual name="DongBoHoalist" property="status" value="1">Stop</logic:notEqual>
                              </td>
                              
                              <td align="left" width="8%">
                                <bean:write name="DongBoHoalist"
                                            property="time_start"/>
                              </td>
                              <td align="left" width="8%">
                                <bean:write name="DongBoHoalist"
                                            property="time_stop"/>
                              </td>
                              <td align="left" width="8%">
                                <bean:write name="DongBoHoalist"
                                            property="time_sleeping"/>
                              </td>
                              
                              <td width="25%">
                                <bean:write name="DongBoHoalist"
                                            property="description"/>
                              </td>
                              <!-- HungBM - 20170419 - Them column hien thi thoi gian update cuoi cung - BEGIN -->
                              <td width="10%" align="center">
                                <bean:write name="DongBoHoalist"
                                            property="date_stop"/>
                              </td>
                              <!-- HungBM - 20170419 - Them column hien thi thoi gian update cuoi cung - END -->
                              <td align="center" width="5%">
                                <a href="javascript:void(0);" onclick="viewLog('<bean:write name="DongBoHoalist" property="log_info"/>')">
                                  <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/select.gif"  style="border-style: none"/>
                                </a>
                              </td>
                              <td align="center" width="5%">
                                <a href="javascript:void(1);" onclick="viewLog('<bean:write name="DongBoHoalist" property="log_error"/>')">
                                  <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/select.gif" style="border-style: none"/>
                                </a>
                              </td>                             
                            </tr>
                          </logic:iterate>
                        </logic:notEmpty>
                          </logic:present>
                        </tbody>
              </table>
   </table>
    <table class="buttonbar" border="0" cellspacing="0" cellpadding="0" width="100%" >
      <tr>
        <td align="center">
          <span>
            <button type="button" class="ButtonCommon" onclick="start('<bean:write name="DongBoHoalist" property="class_name"/>');">start</button>&nbsp;&nbsp;
          </span>
          <span>
            <button type="button" class="ButtonCommon" onclick="stop('<bean:write name="DongBoHoalist" property="class_name"/>');">stop</button>&nbsp;&nbsp;
          </span>
          <span>
            <button type="button" onclick="sbbut()" accesskey="o" class="ButtonCommon">
              Th<span class="sortKey">o</span>&aacute;t
            </button>
          </span>
        </td>
      </tr>
    </table>
    <input type="hidden" id="viewLogBeforeAction"/>
    <div id="logs" style="display:none;">
      <textarea cols="100%" rows="26"></textarea>
    </div>
    <%-- ************************************--%>
  </html:form>
  <script type="text/javascript">
  jQuery.noConflict();
  function Check(obj) {
      var checkObjs = document.getElementsByName('check');
      for (i = 0;i < checkObjs.length;i++) {
          checkObjs[i].checked = obj.checked;
          //alert(document.getElementsByName('ts')[i].value);
      }
  }
  //mainAction
  var f = document.forms[0];

  function stop(id) {
      f.action = 'DongBoHoaStartStopAction.do?action=0';
      f.submit();
  }

  function start(id) {
     
      var checkObjs = document.getElementsByName('check');
      for (i = 0;i < checkObjs.length;i++) {
          if(checkObjs[i].checked == true){
//            alert(document.getElementsByName('ts')[i].value); 
//             f.action = 'DongBoHoaStartStopAction.do?action=1';
              f.action = 'DongBoHoaStartStopAction.do';
          }
      }
      f.submit();

  }
  
  function sbbut(){
    f.action = 'thoatView.do';
      f.submit();
  }
  function viewLog(f) {
      jQuery("textarea").val('');
      if (jQuery("#viewLogBeforeAction").val() != null && jQuery("#viewLogBeforeAction").val() == f) {
          jQuery("#viewLogBeforeAction").val('');
          toggleLog();
      }
      else {
          jQuery("#viewLogBeforeAction").val(f);
          jQuery.ajax( {
              type : "POST", 
              //doc log/ action do log
              url : "DongBoHoaViewLogAction.do",
              data :{
                  "logFile" : f, "nd" : Math.random() * 100000
              },
              dataType : 'json', success : function (data, textstatus) {
                  if (textstatus != null && textstatus == 'success') {
                      jQuery("textarea").val(data.logdata);
                      jQuery("#logs").show();
                  }
              },
              error : function (textstatus) {
                  alert('Khong ket noi dc voi server!');
              }
          });
      }
      /**
 *
 */
      function toggleLog() {
          jQuery("#logs").toggle("slow");

      }
  }
</script>
<%@ include file="/includes/ttsp_bottom.inc"%>