<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ include file="/includes/ttsp_header.inc"%>
<link type="text/css" rel="stylesheet"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"
        type="text/javascript">
</script>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/dcNoiBo.js"
        type="text/javascript">
</script>
<fmt:setBundle basename="com.seatech.ttsp.resource.DCNoiBoResource"/>
<script type="text/javascript">
  jQuery.noConflict();
  jQuery(document).ready(function () { 
   jQuery("#dialog:ui-dialog").dialog( "destroy" );
     jQuery("#btn_Thoat").click(function () {
          jQuery("#dialog-confirm").html('<fmt:message key="dcnbo.page.message_confirm.thoat"/>');
          jQuery("#dialog-confirm").dialog("open");
      });
     jQuery("#dialog-confirm").dialog( {
        autoOpen : false, modal : true, height : 200, width : 430, buttons :  {
              "Có" : function () {    
                  // thuc hien update trang thai
                  document.forms[0].action = "thoatView.do";
                  document.forms[0].submit();
                  jQuery(this).dialog("close");
              },
              "Không" : function () {    
                  // thuc hien update trang thai
                  jQuery(this).dialog("close");
              }
          }
      });      
      jQuery("#dialog_form_cth").dialog( {
          autoOpen : false,resizable:false, height : 200, width : 500, modal : true, buttons :  {
              "Thoát" : function () {
                  jQuery(this).dialog("close");
              }
          },
              "Đóng" : function () {
          }
      });
      //dialog form chi tiet 
      jQuery("#dialog_form_update_lydo").dialog( {
          autoOpen : false,resizable:false ,height : 200, width : 500, modal : true, buttons :  {
              "OK" : function () {
                  jQuery.ajax( {
                              type : "POST", url : "DCNoiBoUpdateAction.do", data :  {
                                  "id" : jQuery("#idForUpdate").val(),
                                  "ttDM":jQuery("#select_tt").val(),
                                  "ly_do_dong_update":jQuery("#ly_do_dong_mo_update").val(),
                                  "nd" : Math.random() * 100000
                              },
                              success : function (data, textstatus) {
                                      if (data != null) {
                                          if (data.logout != null && data.logout) {
                                              document.forms[0].action = 'loginAction.do?logout=true&ma_nsd=' + data.ma_nsd + '&ip_truycap=' + data.ip_truycap;
                                              document.forms[0].submit();
                                          }
                                          else {
                                            //dc chenh lech - 02
                                            if(data.update.toLowerCase()=='success'){
                                              jQuery("#dialog-message-check").html('Thay đổi trạng thái đóng mở '+data.id+' thành công !');
                                              jQuery("#dialog-message-check").dialog("open");
                                            }
                                            // chua thuc hien 03
                                            else {
                                              jQuery("#dialog-message-check").html('Thay đổi trạng thái đóng mở '+data.id+' thất bại !');
                                              jQuery("#dialog-message-check").dialog("open");
                                            }
                                          }
                                      }
                              },
                              error : function (textstatus) {
                                  jQuery("#dialog-message").html(textstatus);
                                  jQuery("#dialog-message").dialog("open");
                              }
                          });
                  jQuery(this).dialog("close");
              }
              ,"Thoát" : function () {
                  jQuery(this).dialog("close");
              }
          },
              "Đóng" : function () {
          }
      });
      jQuery("#dialog-message-check").dialog( {
        autoOpen : false, modal : true, height : 200, width : 430, buttons :  {
              Ok : function () {
                document.forms[0].action = "DCNoiBoListAction.do";
                document.forms[0].submit();
                jQuery(this).dialog("close");
              }
            }
      });
      //dialog confirm message	
      jQuery("#dialog-confirm").dialog( {
          autoOpen : false, resizable : false, height : 200, width : 380, modal : true, buttons :  {
              "Có" : function () {
                document.forms[0].action = "thoatView.do";
                document.forms[0].submit();
              },              
              "Không" : function () {                  
                  jQuery(this).dialog("close");
              }
          }
      });
      //dialog form chi tiet 
      jQuery("#dialog-form").dialog( {
          autoOpen : false,resizable:false ,height : 200, width : 500, modal : true, buttons :  {
              "Thoát" : function () {
                  jQuery(this).dialog("close");
              }
          },
              "Đóng" : function () {
          }
      });
      jQuery("#btnExit").click(function () { 
          jQuery("#dialog-confirm").html('<fmt:message key="XuLyDTSoatTuDo.page.message_confirm.thoat"/>');
          jQuery("#dialog-confirm").dialog("open");
      });
  });
</script>
<html:form styleId="frmDCNoiBo" action="/DCNoiBoAction.do">
  <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
    <tbody>
      <tr>
        <td width="13"><img width="13" height="30" src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img></td>
        <td width="75%" background="<%=request.getContextPath()%>/styles/images/T2.jpg"><span class="title2">
      <fmt:message key="dcnbo.page.title"/></span></td>
        <td width="62"><img width="62" height="30" src="<%=request.getContextPath()%>/styles/images/T3.jpg"></img></td>
        <td width="20%" background="<%=request.getContextPath()%>/styles/images/T4.jpg">&nbsp;</td>
        <td width="4"><img width="4" height="30" src="<%=request.getContextPath()%>/styles/images/T5.jpg"></img></td>
      </tr>
    </tbody>
  </table> 
  <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
    <tbody>
      <tr>
        <td class="title" height="24"
            background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
            colspan="8" style="text-align:left;">
          <fmt:message key="dcnbo.page.timkiem.dkien"/>
        </td>
      </tr>
   </tbody>    
  </table>
  <table class="tableBound">
    <tr>
      <td>
        <table width="99%">
          <tbody>
          <tr>
            <td><font color="red">
                <html:errors/>
              </font> 
            </td></tr>
          </tbody>
        </table>
        <table cellspacing="0" cellpadding="0" width="100%">
          <tbody>
            <tr>
              <td valign="top">
                <table class="bordertableChungTu" cellspacing="0" cellpadding="0"
                       width="100%">
                    <tbody>  
                      <tr>
                        <td width="100%">
                          <fieldset>
                            <legend>
                               <fmt:message key="dcnbo.page.timkiem.dkien.tk.thongke"/>
                            </legend>
                            <table width="100%">
                              <tr>
                                <td>
                                  <fmt:message key="dcnbo.page.timkiem.dkien.tk.thongke.tongso"/>
                                </td>
                                <td colspan="4" align="left">
                                  3
                                </td>
                              </tr>
                              <tr>
                                <th>
                                  <fmt:message key="dcnbo.page.timkiem.dkien.tk.thongke.ngayqt"/>
                                </th>
                                <th>
                                  <fmt:message key="dcnbo.page.timkiem.dkien.tk.thongke.chenhlech"/>
                                </th>
                                <th>
                                  <fmt:message key="dcnbo.page.timkiem.dkien.tk.thongke.khopdung"/>
                                </th>
                                <th>
                                  <fmt:message key="dcnbo.page.timkiem.dkien.tk.thongke.chuath"/>
                                </th>
                                <th>
                                  <fmt:message key="dcnbo.page.timkiem.dkien.tk.thongke.khonghd"/>
                                </th>
                              </tr>
                              <logic:present name="lstStatistic">
                                <logic:empty name="lstStatistic">
                                  <tr>
                                    <td colspan="9" align="center" style="color:red">Kh&ocirc;ng c&oacute;
                                                                                     kết quả thỏa
                                                                                     m&atilde;n !</td>
                                  </tr>
                                </logic:empty>
                                <logic:notEmpty name="lstStatistic">
                                  <logic:iterate id="items" name="lstStatistic" indexId="stt">
                                    <tr valign="top" class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                                      <td>
                                        <bean:write name="items" property="ngay_quyet_toan"/>
                                      </td>
                                      <td>
                                        <bean:write name="items" property="so_khop_dung"/>
                                      </td>
                                      <td>
                                        <bean:write name="items" property="so_chenh_lech"/>
                                      </td>
                                      <td>
                                        <bean:write name="items" property="so_chua_th"/>
                                      </td>
                                      <td>
                                        <bean:write name="items" property="so_khong_hd"/>
                                      </td>
                                    </tr>
                                  </logic:iterate>
                                </logic:notEmpty>
                              </logic:present>
                            </table>
                          </fieldset>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </td>
              </tr>
            </tbody>
          </table>
        
        <table cellspacing="0" cellpadding="0" width="100%">
          <tbody>
            <tr>
              <td valign="top">
                <table class="bordertableChungTu" cellspacing="0" cellpadding="0"
                       width="100%">
                    <tbody>                      
                      <tr>
                        <td width="50%">
                          <table>
                            <tr>
                              <td width="13%" style="text-align:right">
                                  <label for="HSC">
                                    <fmt:message key="dcnbo.page.timkiem.dkien.ngaythuchien"/>
                                  </label>
                              </td>
                              <td>
                                <html:text property="ngay_thuc_hien" styleId="ngay_thuc_hien" styleClass="fieldText"
                                  onkeypress="return numbersonly(this,event,true) "
                                  onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('ngay_thuc_hien');"
                                  onkeydown="if(event.keyCode==13) event.keyCode=9;"
                                 style="WIDTH: 85%;"/>
                                 <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                                       border="0" id="ngay_thuc_hien_btn" 
                                       style="vertical-align:middle;"/>
                                  <script type="text/javascript">
                                    Calendar.setup( {
                                        inputField : "ngay_thuc_hien", // id of the input field
                                        ifFormat : "%d/%m/%Y", // the date format
                                        button : "ngay_thuc_hien_btn"// id of the button
                                    });
                                  </script>
                              </td>                        
                            </tr>
                            <tr>
                              <td width="13%" style="text-align:right">
                                  <label for="HSC">
                                    <fmt:message key="dcnbo.page.timkiem.dkien.nh"/>
                                  </label>
                                </td>
                                <td width="20%">
                                  <html:select styleClass="selectBox" property="ngan_hang" styleId="ngan_hang" style="width:100%;height:20px" onkeydown="if(event.keyCode==13) event.keyCode=9;" >
                                  <html:option value="">
                                    <fmt:message key="dcnbo.page.timkiem.dkien.nh.default"/>
                                  </html:option>
                                    <html:optionsCollection label="ten" value="ma_nh" name="lstNganHang"/>
                                  </html:select>
                                </td>
                            </tr>
                            <tr>
                              <td width="13%" style="text-align:right">
                                  <label for="HSC">
                                    <fmt:message key="dcnbo.page.timkiem.dkien.kb"/>
                                  </label>
                                </td>
                                <td width="20%">
                                  <html:select styleClass="selectBox" property="kho_bac" styleId="kho_bac" style="width:100%;height:20px" onkeydown="if(event.keyCode==13) event.keyCode=9;" >
                                  <html:option value="">
                                    <fmt:message key="dcnbo.page.timkiem.dkien.kb.default"/>
                                  </html:option>
                                    <html:optionsCollection label="ten" value="ma" name="lstKBTinh"/>
                                  </html:select>
                                </td>
                            </tr>
                          </table>
                        </td>
                        <td width="50%">
                          <table width="100%">
                            <tr>
                              <td width="20%" style="text-align:right">
                                <fmt:message key="dcnbo.page.timkiem.dkien.tt"/>
                              </td>
                              <td width="80%">
                                <html:select styleClass="selectBox" property="trang_thai" styleId="trang_thai" style="width:100%;height:20px" onkeydown="if(event.keyCode==13) event.keyCode=9;" >
                                <html:option value="">
                                  <fmt:message key="dcnbo.page.timkiem.dkien.tt.default"/>
                                </html:option>
                                  <html:optionsCollection label="rv_meaning" value="rv_low_value" name="lstTThai"/>
                                </html:select>
                              </td>
                            </tr>
                          </table>
                        </td>
                      </tr>
                    </tbody>
                </table>
              </td>
            </tr>
          </tbody>
        </table>
      </td>
    </tr>
  </table>
  <table  class="buttonbar" align="center">
      <tr>
        <td>
          <span id="tracuu">
            <button  id="btn_timKiem"                 
                type="button" 
                onclick="findDC()"
                class="ButtonCommon" >
                 <fmt:message key="dcnbo.page.button.tracuu"/>
            </button>
            <!--<button  id="btn_timKiem" 
                tabindex="111"
                type="button" 
                class="ButtonCommon" >
                 <fmt:message key="dcnbo.page.button.tracuu"/>
            </button>-->
          </span>
          <span id="exit">
            <button  id="btn_Thoat"                 
                type="button"
                class="ButtonCommon" >
                 <fmt:message key="dcnbo.page.button.exit"/>
            </button>
          </span>
        </td>
      </tr>
    </table>
    <table style="BORDER-COLLAPSE: collapse" id="tblBlah" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
      <thead class="TR_Selected">
        <tr>
          <th width="2%">
            <fmt:message key="dcnbo.page.ketqua.STT"/>
          </th>
          <th width="15%">
            <fmt:message key="dcnbo.page.ketqua.kb"/>
          </th>
          <th width="15%">
            <fmt:message key="dcnbo.page.ketqua.kbhuyen"/>
          </th>
          <th width="15%">
            <fmt:message key="dcnbo.page.ketqua.nh"/>
          </th>
          <th width="15%">
            <fmt:message key="dcnbo.page.ketqua.ngayqt"/>
          </th>
          <th width="10%">
            <fmt:message key="dcnbo.page.ketqua.ngaydc"/>
          </th>
          <th width="15%">
            <fmt:message key="dcnbo.page.ketqua.tt"/>
          </th>          
          <%--<th width="10%">
            <fmt:message key="dcnbo.page.ketqua.dongmo"/>
          </th>
          <th width="25%">
            <fmt:message key="dcnbo.page.ketqua.ghichu"/>
          </th>--%>
        </tr>
      </thead>
      <tbody>
        <input type="hidden" id="rowSelected"/>
        <logic:present name="lstDCNBO">
          <logic:empty name="lstDCNBO">
              <tr>
                <td colspan="7" align="center" style="color:red">
                  <fmt:message key="dcnbo.page.ketqua.empty"/>
                </td>
              </tr>
            </logic:empty>
            <logic:notEmpty name="lstDCNBO">
             <%
              com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
              int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
            %>
              <logic:iterate id="items" name="lstDCNBO" indexId="stt">
                <tr id="trSelected_<bean:write name="items" property="id"/>"  valign="top" class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                      <td>
                        <%=stt+1%>
                      </td>
                      <td>
                        <bean:write name="items" property="ten_kb_tinh"/>
                      </td>
                      <td>
                        <bean:write name="items" property="ten_kb_huyen"/>
                      </td>
                      <td>
                        <bean:write name="items" property="ten_nh"/>
                      </td>
                      <td>
                        <bean:write name="items" property="ngay_dc"/>
                      </td>
                      <td>
                        <bean:write name="items" property="ngay_thien_dc"/>
                      </td>
                      <td id="col_<bean:write name="items" property="id"/>" onclick="rowSelectedFocus('<bean:write name="items" property="id"/>','<bean:write name="items" property="trang_thai"/>')">                         
                         <font color="Blue" style="text-decoration: underline;">
                         <logic:equal value="01" name="items" property="trang_thai">
                          <fmt:message key="dcnbo.page.ketqua.tt.khop"/>
                         </logic:equal>
                         <logic:equal value="02" name="items" property="trang_thai">
                          <fmt:message key="dcnbo.page.ketqua.tt.chenhlech"/>
                         </logic:equal>
                         <logic:equal value="03" name="items" property="trang_thai">
                          <fmt:message key="dcnbo.page.ketqua.tt.chuath"/>
                         </logic:equal>
                         <logic:equal value="04" name="items" property="trang_thai">
                          <fmt:message key="dcnbo.page.ketqua.tt.khonghd"/>
                         </logic:equal>
                         </font>
                      </td>                      
                      <%--<td id="col_tt_<bean:write name="items" property="id"/>" onclick="rowSelectedForUpdateTrangThai('<bean:write name="items" property="id"/>','<bean:write name="items" property="trang_thai"/>','<bean:write name="items" property="trang_thai_dong_mo"/>')">
                         <logic:equal value="D" name="items" property="trang_thai_dong_mo">
                          <fmt:message key="dcnbo.page.ketqua.dongmo.dong"/>
                         </logic:equal>
                         <logic:equal value="M" name="items" property="trang_thai_dong_mo">
                            <logic:equal value="02" name="items" property="trang_thai">
                              <font color="Blue" style="text-decoration: underline;">
                                <fmt:message key="dcnbo.page.ketqua.dongmo.mo"/>
                              </font>
                            </logic:equal>
                            <logic:notEqual value="02" name="items" property="trang_thai">
                              <fmt:message key="dcnbo.page.ketqua.dongmo.mo"/>
                            </logic:notEqual>
                            
                         </logic:equal>
                      </td>
                      <td></td>--%>
                </tr>
              </logic:iterate>
              <tr>
                    <td colspan="7">
                      <div style="float:right;padding-right:40">
                        <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean, "#0000ff")%>
                      </div>
                    </td>
              </tr>
                  <html:hidden property="pageNumber" value="1" styleId="pageNumber"/>
            </logic:notEmpty>
        </logic:present>
      </tbody>
    </table>
</html:form>
<div id="dialog_form_update_lydo"
     title='<fmt:message key="dcnbo.page.title.dialog_form"/>'>
  <p class="validateTips"></p>
    <table>
      <tr>
        <td>
          <fieldset>
              <legend style="vertical-align:middle">
                <fmt:message key="dcnbo.page.title.dialog_form_update_lydo.lydo"/>
              </legend>
              <table width="100%">
                <tr>
                  <td width="20%"><fmt:message key="dcnbo.page.title.dialog_form_update_lydo.tt"/></td>
                  <td>    
                    <input type="hidden" id="idForUpdate"/>
                    <select id="select_tt">
                      <option value="D">
                        <fmt:message key="dcnbo.page.ketqua.dongmo.dong"/>
                      </option>
                      <option value="M">
                        <fmt:message key="dcnbo.page.ketqua.dongmo.mo"/>
                      </option>
                    </select> 
                  </td>
                </tr>
                <tr>
                  <td width="20%"><fmt:message key="dcnbo.page.title.dialog_form_update_lydo.lydo"/></td>
                  <td>
                    <textarea cols="3"  rows="3"  style="width:99%" onkeydown="nextElementFocus(event);" name="ly_do_dong_mo_update" id="ly_do_dong_mo_update"
                           class="fieldText">
                    </textarea>
                  </td>
                </tr>
              </table>
            </fieldset>
        </td>
      </tr>
    </table>
</div>
<div id="dialog-message-check"
     title='<fmt:message key="dcnbo.page.title.dialog_message"/>'>
  <p>
    <span class="ui-icon ui-icon-circle-check"
          style="float:left; margin:0 7px 50px 0;"></span>
     
    <span id="message"></span>
  </p>
</div>
<div id="dialog-confirm"
     title='<fmt:message key="dcnbo.page.title.dialog_message"/>'>
  <p>
    <span class="ui-icon ui-icon-alert"
          style="float:left; margin:0 7px 20px 0;"></span>
     
    <span id="message_confirm"></span>
  </p>
</div>
<div id="dialog-form"
     title='<fmt:message key="dcnbo.page.title.dialog_form"/>'>
  <p class="validateTips"></p>
    <table width="100%">
      <tr>
        <td width="50%">
          <fieldset style="width:215px;height:auto">
              <legend style="vertical-align:middle">
                <fmt:message key="dcnbo.page.title.dialog_form.sgd"/>
              </legend>
              <table width="100%">
                <tr>
                  <td width="20%"><fmt:message key="dcnbo.page.title.dialog_form.sothu"/></td>
                  <td>
                    <input readonly="readonly" style="width:99%" onkeydown="nextElementFocus(event);" type="text" name="thu_sgd_ctiet" id="thu_sgd_ctiet"
                           class="fieldTextCode"/>
                  </td>
                </tr>
                 <tr>
                  <td width="20%"><fmt:message key="dcnbo.page.title.dialog_form.sochi"/></td>
                  <td>
                    <input readonly="readonly" style="width:99%" onkeydown="nextElementFocus(event);" type="text" name="chi_sgd_ctiet" id="chi_sgd_ctiet"
                           class="fieldTextCode"/>
                  </td>
                </tr>
              </table>
            </fieldset>
        </td>
        <td width="50%">
          <fieldset style="width:215px;height:auto">
              <legend style="vertical-align:middle">
                <fmt:message key="dcnbo.page.title.dialog_form.dv"/>
              </legend>
              <table width="100%">
                <tr>
                  <td width="20%"><fmt:message key="dcnbo.page.title.dialog_form.sothu"/></td>
                  <td>
                    <input readonly="readonly" style="width:99%" onkeydown="nextElementFocus(event);" type="text" name="thu_dv_ctiet" id="thu_dv_ctiet"
                           class="fieldTextCode"/>
                  </td>
                </tr>
                 <tr>
                  <td width="20%"><fmt:message key="dcnbo.page.title.dialog_form.sochi"/></td>
                  <td>
                    <input readonly="readonly" style="width:99%" onkeydown="nextElementFocus(event);" type="text" name="chi_dv_ctiet" id="chi_dv_ctiet"
                           class="fieldTextCode"/>
                  </td>
                </tr>
              </table>
            </fieldset>
        </td>
      </tr>
    </table>
</div>
<div id="dialog_form_cth"
     title='<fmt:message key="dcnbo.page.title.dialog_form"/>'>
  <p class="validateTips"></p>
    <table>
      <tr>
        <td width="50%">
          <fieldset>
              <legend style="vertical-align:middle">
                <fmt:message key="dcnbo.page.title.dialog_form_cth.dv"/>
              </legend>
              <table width="100%">
                <tr>
                  <td width="20%"><fmt:message key="dcnbo.page.title.dialog_form_cth.dv.tt"/></td>
                  <td>
                    <input readonly="readonly" style="width:99%" onkeydown="nextElementFocus(event);" type="text" name="ttdchieu_lan1_ctiet" id="ttdchieu_lan1_ctiet"
                           class="fieldTextCode"/>
                  </td>
                </tr>
              </table>
            </fieldset>
        </td>
      </tr>
    </table>
</div>

<%@ include file="/includes/ttsp_bottom.inc"%>