<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ include file="/includes/ttsp_header.inc"%>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%
  String kb_huyen = request.getAttribute("kb_huyen")==null?"":request.getAttribute("kb_huyen").toString();
  %>
<script type="text/javascript">
  jQuery.noConflict();
  //************************************ LOAD PAGE **********************************  
  jQuery(document).init(function () {
      getTenKhoBacDC('', '');
  });
</script>
<script type="text/javascript">
  function goPage(page) {
      var f = document.forms[0];
      f.pageNumber.value = page;
      f.submit();
  }

  function check(type) {
      var f = document.forms[0];
      f.target = '';
      if (type == 'close') {
          f.action = 'mainAction.do';
      }
      else if (type == 'find') {
          f.action = 'QuanLySoHieuKBMaNHListAction.do';
      }
      else if (type == 'addMaNH') {
          f.action = 'QuanLySoHieuKBMaNHAddAction.do';
      }
      else if (type == 'addMaDVSDNS') {
          f.action = 'QuanLySoHieuKBMaDVSDNSAddAction.do';
      }
      f.submit();
  }
  

  function blockKeySpecial001(e) {
      var code;
      if (!e)
          if (e.keyCode)
              code = e.keyCode;
          else if (e.which)
              code = e.which;
      var character = String.fromCharCode(code);
      var pattern = / [!@ # $ %  ^  &  * ()_ += '"\[\]\.\,\:\;\{\}\<\>\?\\]/;
      if (pattern.match(character)) {
          character.replace(character, "");
          return false;
      }
      else {
          return true;
      }
  }

  function getTenKhoBacDC(id, id_cha) {
      document.getElementById('nhkb_huyen').options.length = 1;// clear du lieu option cu
      var kb_id;

      if (id == null || '' == id) {
          if (id_cha != null && '' != id_cha) {
              kb_id = id_cha;
              jQuery('#nhkb_tinh').val(id_cha);
          }
          else if (id_cha == null || '' == id_cha) {
              kb_id = document.forms[0].nhkb_tinh.value;
          }
      }
      else if (id != null && '' != id) {
          if (id_cha != null && '' != id_cha) {
              kb_id = id_cha;
              jQuery('#nhkb_tinh').val(id_cha);
          }
          else if (id_cha == null || '' == id_cha) {
              kb_id = document.forms[0].nhkb_tinh.value;
          }
      }

      var kb_huyen = "<%=kb_huyen%>";
      if (kb_id != null && "" != kb_id) {
          jQuery.ajax( {
              type : "POST", url : "getDMucKBTHop.do", data :  {
                  "kb_id" : kb_id
              },
              success : function (data, textstatus) {
                  if (textstatus != null && textstatus == 'success') {
                      if (data != null) {
                          jQuery.each(data, function (i, objectDM) {
                              // truong hop 1 - luc load khong co thang nao                  
                              document.getElementById('nhkb_huyen').options.add(new Option(objectDM.kb_huyen, objectDM.id));
                          });
                      }
                  }
                  if (id != null && '' != id) {
                      jQuery('#nhkb_huyen').val(id);
                  }
              },
              error : function (textstatus) {
                  alert(textstatus);
              }
          });
      }
  }
</script>
<div class="box_common_conten">
  <html:form action="/QuanLySoHieuKBMaNHListAction.do" method="post">
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
            <span class="title2">Quản l&yacute; số hiệu kho bạc m&atilde;
                                 ng&acirc;n h&agrave;ng</span>
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
              height="100%">
            <span>&nbsp;&nbsp;&nbsp;&nbsp;<font color="Gray">Điều kiện
                                                             t&igrave;m kiếm</font></span>
          </td>
        </tr>
      </tbody>
       
      <tr>
        <td>
          <br/>
          <table width="80%" cellspacing="0" cellpadding="1"
                 bordercolor="#e1e1e1" border="0" align="center"
                 style="BORDER-COLLAPSE: collapse">
            <tr>
              <td width="10%" align="right" bordercolor="#e1e1e1">Kho bạc tỉnh</td>
              <td width="30%">
                <html:select property="nhkb_tinh" styleId="nhkb_tinh"
                             onchange="getTenKhoBacDC('','')" style="width:80%"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">
                  <html:option value="">Chọn kho bạc tỉnh</html:option>
                  <html:optionsCollection name="dmucKBTinh" value="id_cha"
                                          label="ten"/>
                </html:select>
              </td>
              <td width="15%" align="right" bordercolor="#e1e1e1">Kho bạc huyện</td>
              <td width="30%">
                <html:select property="nhkb_huyen" styleId="nhkb_huyen"
                             onchange="nhkb_huyenval();" style="width:100%"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">
                  <html:option value="">Chọn kho bạc huyện</html:option>
                </html:select>
              </td>
            </tr>
             
            <tr>
              <td align="right" bordercolor="#e1e1e1">M&atilde; ng&acirc;n
                                                      h&agrave;ng</td>
              <td>
                <html:text property="ma_nh" maxlength="8"
                           onkeypress="return numberBlockKey(event)"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
              </td>
              <td align="right" bordercolor="#e1e1e1">Số hiệu Kho Bạc</td>
              <td>
                <html:text property="shkb" maxlength="4"
                           onkeypress="return numberBlockKey(event)"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                        
              </td>
            </tr>
          </table>
          <br/>
        </td>
      </tr>
    </table>
    <table class="buttonbar" align="center">
      <tr>
        <td>
          <span id="tracuu">
            <button type="button" onclick="check('find')" class="ButtonCommon"
                    accesskey="t">
              <span class="sortKey">T</span>ra cứu
            </button></span>
           
          <span id="themMaNH"> 
            <button type="button" onclick="check('addMaNH')"
                    class="ButtonCommon" accesskey="m" style="width:152px">
              Thêm<span class="sortKey"> M</span>ới
            </button>
             </span>
          <span id="thoat"> 
            <button type="button" onclick="check('close')" class="ButtonCommon"
                    accesskey="o">
              Th<span class="sortKey">o</span>&aacute;t
            </button>
             </span>
        </td>
      </tr>
    </table>
    <font color="Red" dir="ltr">
      <%
        if(request.getAttribute("status") != null){
          int result = Integer.parseInt(request.getAttribute("status").toString());
          if(result == 1){
          out.print("Không thể sửa");
          }else if(result == 2){
          out.print("Sửa thành công!");   
          }else if(result == 3){
          out.print("Sửa không thành công!");   
          }
        }
      %>
    </font>
    <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
      <tbody>
        <tr>
          <td class="title" colspan="6"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="24">
            <font color="Gray">Kết quả t&igrave;m kiếm</font>
          </td>
        </tr>
        <tr>
          <td>
            <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
                   bordercolor="#999999" width="100%">
              <thead>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">SHKB</div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">M&atilde; ng&acirc;n h&agrave;ng</div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">T&ecirc;n kho bạc</div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">Cấp</div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">M&atilde; đơn vị sử dụng ng&acirc;n
                                      s&aacute;ch</div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">Mã tỉnh</div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">Mã huyện</div>
                </th>
                 <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">Cấp cha</div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">Sửa</div>
                </th>
              </thead>
               
              <%
  com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
      int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
 %>
               
              <tbody class="navigateable focused" cellspacing="0"
                     cellpadding="1" bordercolor="#e1e1e1"
                     style="font-size:1.2em">
                <logic:notEmpty name="dmSoHieuKBMaNHList">
                  <logic:iterate id="DMSoHieuKBMaNH" name="dmSoHieuKBMaNHList"
                                 indexId="stt">
                    <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                      <td align="center" width="4%">
                        <bean:write name="DMSoHieuKBMaNH" property="shkb"/>
                      </td>
                      <td align="center" width="10%">
                        <bean:write name="DMSoHieuKBMaNH" property="ma_nh"/>
                      </td>
                      <td align="left" width="31%">
                        <bean:write name="DMSoHieuKBMaNH" property="ten"/>
                      </td>
                      <logic:notEmpty name="DMSoHieuKBMaNH" property="cap">
                        <logic:equal value="1" name="DMSoHieuKBMaNH" property="cap">
                          <td style="font-weight:bold;" align="center" width="11%">
                            Trung ương
                          </td>
                        </logic:equal>
                        <logic:equal value="2" name="DMSoHieuKBMaNH" property="cap">
                          <td style="font-weight:bold;" align="center" width="11%">
                            VP tỉnh
                          </td>
                        </logic:equal>
                        <logic:equal value="3" name="DMSoHieuKBMaNH" property="cap">
                          <td style="font-weight:bold;" align="center" width="11%">
                            Huyện
                          </td>
                        </logic:equal>
                        <logic:equal value="5" name="DMSoHieuKBMaNH" property="cap">
                          <td style="font-weight:bold;" align="center" width="11%">
                            Tỉnh
                          </td>
                        </logic:equal>
                      </logic:notEmpty>
                      <logic:empty name="DMSoHieuKBMaNH" property="cap">
                        <td align="center" width="11%">Kh&ocirc;ng c&oacute;</td>
                      </logic:empty>
                      <logic:notEmpty name="DMSoHieuKBMaNH"
                                      property="ma_dvsdns">
                        <td style="font-weight:bold;" align="center"
                            width="17%">
                          <bean:write name="DMSoHieuKBMaNH"
                                      property="ma_dvsdns"/>
                        </td>
                      </logic:notEmpty>
                      <logic:empty name="DMSoHieuKBMaNH" property="ma_dvsdns">
                        <td align="center" width="17%">Kh&ocirc;ng c&oacute;</td>
                      </logic:empty>
                      <logic:notEmpty name="DMSoHieuKBMaNH"
                                      property="ma_t">
                        <td style="font-weight:bold;" align="center"
                            width="9%">
                          <bean:write name="DMSoHieuKBMaNH"
                                      property="ma_t"/>
                        </td>
                      </logic:notEmpty>
                      <logic:empty name="DMSoHieuKBMaNH" property="ma_t">
                        <td align="center" width="9%">Kh&ocirc;ng c&oacute;</td>
                      </logic:empty>
                      <logic:notEmpty name="DMSoHieuKBMaNH"
                                      property="ma_h">
                        <td style="font-weight:bold;" align="center"
                            width="9%">
                          <bean:write name="DMSoHieuKBMaNH"
                                      property="ma_h"/>
                        </td>
                      </logic:notEmpty>
                      <logic:empty name="DMSoHieuKBMaNH" property="ma_h">
                        <td align="center" width="9%">Kh&ocirc;ng c&oacute;</td>
                      </logic:empty>
                      <td align="center" width="6%">
                        <bean:write name="DMSoHieuKBMaNH" property="id_cha"/>
                      </td>
                      <td align="center" width="3%">
                        <a href='<html:rewrite page="/QuanLySoHieuKBMaNHUpdateAction.do"/>?shkb=<bean:write name="DMSoHieuKBMaNH" property="shkb"/>
            &ma_nh=<bean:write name="DMSoHieuKBMaNH" property="ma_nh"/>
            &ten=<bean:write name="DMSoHieuKBMaNH" property="ten"/>
            &ma_dvsdns=<bean:write name="DMSoHieuKBMaNH" property="ma_dvsdns"/>
            &ma_t=<bean:write name="DMSoHieuKBMaNH" property="ma_t"/>
            &ma_h=<bean:write name="DMSoHieuKBMaNH" property="ma_h"/>
            &cap=<bean:write name="DMSoHieuKBMaNH" property="cap"/>
            &id_cha=<bean:write name="DMSoHieuKBMaNH" property="id_cha"/>
            '>
                          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/ctu_00.gif"
                               style="border-style: none;"/></a>
                      </td>
                    </tr>
                  </logic:iterate>
                </logic:notEmpty>
                <tr>
                  <td colspan="10" align="center">
                    <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean,"#0000ff")%>
                    <html:hidden property="pageNumber" value="1"/>
                  </td>
                  
                </tr>
              </tbody>
            </table>
          </td>
        </tr>
      </tbody>
    </table>
  </html:form>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>