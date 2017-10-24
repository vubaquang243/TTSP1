<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ include file="/includes/ttsp_header.inc"%>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.TTrangTToanResource"/>
<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="THopTToanAction.do" method="post">
    <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
          <td width="13">
            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg"
                 width="13" height="30"/>
          </td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg"
              width="75%">
            <span class="title2">
              <fmt:message key="ttrangttoan.ttrangttoan.main"/></span>
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
    <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
      <tbody>
        <tr>
          <td class="title" colspan="6"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="24">
            <span>&nbsp;&nbsp;&nbsp;&nbsp;<font color="Gray">
                <fmt:message key="ttrangttoan.ttrangttoan.find"/>
              </font></span>
          </td>
        </tr>
      </tbody>
       
      <tr>
        <td>
          <br/>
          <table width="90%" cellspacing="0" cellpadding="1"
                 bordercolor="#e1e1e1" border="0" align="center"
                 style="BORDER-COLLAPSE: collapse">
                 
            <tr>
              <td width="20%" align="right" bordercolor="#e1e1e1">
                <fmt:message key="ttrangttoan.ttrangttoan.label.list.makbtinh"/> &nbsp;&nbsp; 
                <html:select property="nhkb_nhan" styleId="nhkb_nhan"
                             onchange="getTenKhoBac()"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">
                  <html:option value="">
                    -----<fmt:message key="ttrangttoan.ttrangttoan.dmuc.default"/>-----
                  </html:option>
                  <html:optionsCollection name="dmuckb_tinh" value="id" label="ten"/>
                </html:select>
              </td>
              <td width="30%" align="left" bordercolor="#e1e1e1">
                &nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="ttrangttoan.ttrangttoan.label.list.makbhuyen"/> &nbsp;&nbsp; 
                <html:select property="nhkb_nhan_cn" styleId="nhkb_nhan_cn" onchange="change2()"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">
                  <html:option value="">
                    -----<fmt:message key="ttrangttoan.ttrangttoan.dmuc.default"/>-----
                  </html:option>
                </html:select>
              </td>
            </tr>
            <tr >
              <td align="center" colspan="2">
              <br/>
                <button type="button" onclick="check('find')" class="ButtonCommon" accesskey="t">
                  <span class="sortKey">T</span>&igrave;m kiếm
                </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <button type="button" onclick="check('print')" class="ButtonCommon" accesskey="i">
                  <span class="sortKey">I</span>n
                </button>
                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                <button type="button" onclick="check('close')" class="ButtonCommon" accesskey="o">
                  Th<span class="sortKey">o</span>&aacute;t
                </button>
              </td>
            </tr>
          </table>
          <br/>
        </td>
      </tr>
    </table>
    <%-- ************************************--%>
    <%-- 3 nút Thêm mới, tra cứu , thoát--%>
    <table border="2" align="center" width="100%"
           style="BORDER-COLLAPSE: collapse">
      <tbody>
        <tr>
          <td class="title" colspan="6"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="24">
            <font color="Gray">
              <fmt:message key="ttrangttoan.ttrangttoan.findexc"/>
            </font>
          </td>
        </tr>
        <tr>
          <td>
            <table class="navigateable focused" cellspacing="0" cellpadding="1"
                   bordercolor="#e1e1e1" border="1" align="center" width="100%"
                   style="BORDER-COLLAPSE: collapse">
              <thead>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="ttrangttoan.ttrangttoan.label.list.stt"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="ttrangttoan.ttrangttoan.label.list.tenkbnn"/>
                  </div>
                </th>
                <th sclass="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="ttrangttoan.ttrangttoan.label.list.makbnn"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="ttrangttoan.ttrangttoan.label.list.loaitk"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="ttrangttoan.ttrangttoan.label.list.sotk"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="ttrangttoan.ttrangttoan.label.list.cnnh"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="ttrangttoan.ttrangttoan.label.list.duno"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="ttrangttoan.ttrangttoan.label.list.sodu"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="ttrangttoan.ttrangttoan.label.list.psthu"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="ttrangttoan.ttrangttoan.label.list.pschi"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="ttrangttoan.ttrangttoan.label.list.clech"/>
                  </div>
                </th>
              </thead>
   <%
      com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
      int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
 %>
              <tbody class="navigateable focused" cellspacing="0"
                     cellpadding="1" bordercolor="#e1e1e1">
                <logic:notEmpty name="lstUD">
                  <logic:iterate id="UDlist" name="lstUD" indexId="stt">
                    <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                      <td align="center" width="3%">
                        <%=stt + 1%>
                      </td>
                      <td align="left" width="15%">
                        &nbsp;<bean:write name="UDlist" property="ten_kb"/>
                      </td>
                      <td align="center" width="10%">
                        <bean:write name="UDlist" property="ma_kb"/>
                      </td>
                      <td align="center" width="8%">
                        <logic:equal name="UDlist" property="loai_tk"
                                     value="TT">
                          <fmt:message key="ttrangttoan.ttrangttoan.label.list.tktt"/>
                        </logic:equal>
                         
                        <logic:equal name="UDlist" property="loai_tk"
                                     value="TG">
                          <fmt:message key="ttrangttoan.ttrangttoan.label.list.tktg"/>
                        </logic:equal>
                         
                        <logic:equal name="UDlist" property="loai_tk"
                                     value="CT">
                          <fmt:message key="ttrangttoan.ttrangttoan.label.list.tkct"/>
                        </logic:equal>
                      </td>
                      <td align="center" width="5%">
                        <bean:write name="UDlist" property="so_tk"/>
                      </td>
                      <td align="center" width="8%">
                        <bean:write name="UDlist" property="ten_nh"/>
                      </td>
                      <td align="right" width="8%">
                        <bean:write name="UDlist" property="han_muc_no"/>
                      </td>
                      <td align="right" width="8%">
                        <bean:write name="UDlist" property="du_dau_ngay"/>
                      </td>
                      <td align="right" width="8%">
                        <bean:write name="UDlist" property="ps_thu"/>
                      </td>
                      <td align="right" width="8%">
                        <bean:write name="UDlist" property="ps_chi"/>
                      </td>
                      <td align="right" width="8%">
                        <bean:write name="UDlist" property="chenh_lech"/>
                      </td>
                    </tr>
                  </logic:iterate>
                </logic:notEmpty>
              </tbody>
            </table>
          </td>
        </tr>
        <tr>
          <td colspan="11">
          <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean,"#0000ff")%>
          </td>
        </tr>
      </tbody>     
      <html:hidden property="pageNumber" value="1"/>
    </table>
    <%-- ************************************--%>
  </html:form>
  
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
getTenKhoBac();
  var f = document.forms[0];
  //f.nhkb_nhan.value = "";
  f.target='';
  function check(type) {
      f.target='';
      if (type == 'find') {
          if (f.nhkb_nhan.value != null && f.nhkb_nhan.value != "") {
          
              f.action = 'THopTToanAction.do';
          }
          else if (f.nhkb_nhan.value == null || f.nhkb_nhan.value == "") {
              alert("Cần nhập thông tin mã kho bạc.");
              f.nhkb_nhan.focus();
              return false;
          }
      }
      else if (type == 'print') {
        f.action = 'inTTToan.do';
        var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
        newDialog = window.open('', '_form', params);  
        f.target='_form';
      }else if (type == 'close') {
         f.action = 'TTThanhToanMainAction.do';
      }
      f.submit();
  }
      function change2() {
      var ma;
      ma=document.getElementById("nhkb_nhan_cn").value;
      return ma;

  }
  
  function goPage(page) {
      //var f = document.forms[0];
      f.pageNumber.value = page;
      f.submit();
  
  }
  function getTenKhoBac() {
      document.getElementById('nhkb_nhan_cn').options.length = 1;// clear du lieu option cu
      var kb_id = document.forms[0].nhkb_nhan.value;
      if (kb_id !=null && ""!=kb_id){
      jQuery.ajax( {
          type : "POST", url : "getDMucKB.do", data :  {
              "kb_id" : kb_id
          },
          success : function (data, textstatus) {
              if (textstatus != null && textstatus == 'success') {
                  if (data != null) {
                      jQuery.each(data, function (i, objectDM) {
                          document.getElementById('nhkb_nhan_cn').options.add(new Option(objectDM.ten, objectDM.id))

                      });
                  }
              }

          },
          error : function (textstatus) {
              alert(textstatus);
          }
      });
      }
  }
  
</script>