<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ include file="/includes/ttsp_header.inc"%>
<link type="text/css" rel="stylesheet"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/tabber.css"/>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/tabber.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/doichieu.js"></script>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"
        type="text/javascript">
</script>
<script type="text/javascript" charset="utf-8" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery.jec-1.3.2.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/lov.js"></script>
        
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.LTTDiResource" />
<fmt:setBundle basename="com.seatech.ttsp.resource.DoichieuResource"/>


<%
  String kb_huyen = request.getAttribute("kb_huyen")==null?"":request.getAttribute("kb_huyen").toString();
  String ma_dv = request.getAttribute("ma_dv")==null?"":request.getAttribute("ma_dv").toString();
  String strTinh = request.getAttribute("dftinh")==null?"":request.getAttribute("dftinh").toString();
  String tcuu = request.getAttribute("tcuu")==null?"":request.getAttribute("tcuu").toString();
  String dchieu3 = request.getAttribute("dchieu3")==null?"":request.getAttribute("dchieu3").toString();
  String inlan_dc = request.getAttribute("inlan_dc")==null?"":request.getAttribute("inlan_dc").toString();
  String inxtthai = request.getAttribute("inxtthai")==null?"":request.getAttribute("inxtthai").toString();
  String initAction = request.getAttribute("initAction")==null?"":request.getAttribute("initAction").toString();
  String tong_row = request.getAttribute("tong_row")==null?"":request.getAttribute("tong_row").toString();
  String cur_page = request.getAttribute("currentPage")==null?"":request.getAttribute("currentPage").toString();
  

%>
<script type="text/javascript">
  jQuery.noConflict();
  //************************************ LOAD PAGE **********************************
  
  jQuery(document).init(function () {
    //defaultStateFormBK();
//    var dchieu3="<%=dchieu3%>";
//    var inlan_dc="<%=inlan_dc%>";
//     var tong_row="<%=tong_row%>";
//     var cur_page="<%=cur_page%>";
//     var total_page = Math.ceil(parseInt(tong_row)/15);
//     alert(total_page);
       
      getTenKhoBacDC('','');
//      jQuery('#tong_row').val(toFormatNumber(tong_row,0,'.'));
//      jQuery('#currentPage').val(cur_page);
//      jQuery('#total_page').val(total_page);
      
    jQuery("#dialog-form-lov-dm").dialog({
      autoOpen: false,resizable : false,
      maxHeight: "700px",
      width: "550px",
      modal: true
    }); 
    jQuery("#chklistAll").click(function(){
       jQuery("#colCheckList").find(':checkbox').attr('checked', this.checked);
    });
  });
</script>

<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="updateTSoTabmis.do" method="post" >
   <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
          <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
            <span class=title2> <fmt:message key="doi_chieu.page.title.tso.tit"/></span>
          </td>
          <td width=62><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg" width=62 height=30/></td>
          <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg" width="20%">&nbsp;</td>
          <td width=4><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg" width=4 height=30/></td>
        </tr>
      </tbody>
   </table>

    <table style="BORDER-COLLAPSE: collapse" border="0" cellspacing="0" class="tableBound"
           bordercolor="#999999" width="100%">
      <tr>
       <td>
        <fieldset>
        <legend><font color="blue">&#272;i&#7873;u ki&#7879;n t&#236;m ki&#7871;m </font></legend>
        <div>
          <table  class="data-grid" id="data-grid" 
                                              style="width:100%" border="0"
                                             cellspacing="0" cellpadding="2" >
              <tr>
                <td width="15%" align="right" bordercolor="#e1e1e1">
                  <fmt:message key="doi_chieu.page.label.tracuu.kbtinh"/>&nbsp;
                </td>
                <td width="20%">
                  <html:select property="nhkb_tinh" styleId="nhkb_tinh" style="font-size:12px;width:100%"  onchange="getTenKhoBacDC('','')"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"> 
                      <%if(request.getAttribute("dftinh") != null){%>
                      <html:option value="">---<fmt:message key="doi_chieu.page.label.tracuu.default"/>---</html:option>                  
                      <%}%>
                      <html:optionsCollection  name="dmuckb_tinh" value="id_cha" label="kb_tinh"/>                    
                  </html:select>
                  </td>
                <td width="15%" align="right" bordercolor="#e1e1e1">
                  <fmt:message key="doi_chieu.page.label.tracuu.kbhuyen"/>&nbsp;
                </td>
                <td width="30%">
                  <html:select property="nhkb_huyen" styleId="nhkb_huyen" style="font-size:12px;width:80%" onchange="nhkb_huyenval();"
                                onkeydown="if(event.keyCode==13) event.keyCode=9;">
                       <html:option value="">---<fmt:message key="doi_chieu.page.label.tracuu.default"/>---</html:option>
                  </html:select>
                </td>
                
              </tr>
              <tr>
                <td width="8%" align="right">
                   Hệ thống ngân hàng
                </td>
                <td align="left" width="17%"><!--ngan_hang-->
                    <html:select property="ma_dv" styleId="ma_dv" style="font-size:12px;width:100%"
                                onkeydown="if(event.keyCode==13) event.keyCode=9;">
                       <html:option value="">---<fmt:message key="doi_chieu.page.label.tracuu.default"/>---</html:option>
                       <html:optionsCollection  name="dmucNH" value="ma_dv" label="ten_nh"/>
                    </html:select>
                </td>
                <td width="8%" align="right">
                   T&#234;n tham s&#7889;
                </td>
                <td align="left" width="17%">
                    <html:select property="ten_ts" styleId="ten_ts" style="font-size:12px;width:80%" onchange="ten_tsval();"
                                onkeydown="if(event.keyCode==13) event.keyCode=9;">
                       <html:option value="">---<fmt:message key="doi_chieu.page.label.tracuu.default"/>---</html:option>
                       <html:optionsCollection  name="ten_ts" value="ten_ts" label="ten_ts"/>
                    </html:select>
                </td>
              </tr>
              <tr>
                <td width="8%" align="right">
                   Giá trị tham số
                </td>
                <td align="left" width="17%">
                    <html:select property="giatri_ts" styleId="giatri_ts" style="font-size:12px;width:30%"
                                onkeydown="if(event.keyCode==13) event.keyCode=9;">
                       <html:option value="">---Chọn</html:option>
                       <html:option value="Y">Y</html:option>
                       <html:option value="N">N</html:option>
                    </html:select>
                </td>
                <td  align="center" colspan="2" width="15%">
                  <%if(request.getAttribute("dchieu3") != null){%>
                    <button type="button" onclick="callLov()" accesskey="t" >
                            <span class="sortKey">D</span>anh m&#7909;c KB
                    </button> &nbsp;&nbsp;
                  <%}%>
                  <button type="button" onclick="check('find');" class="ButtonCommon" accesskey="t" >
                          <span class="sortKey">T</span>&igrave;m kiếm
                  </button>  &nbsp;&nbsp;
                  <button type="button" onclick="check('update');" class="ButtonCommon" accesskey="t" >
                          <span class="sortKey">S</span>&#7917;a
                  </button>

                </td>
              </tr>
              <!--<tr>
                <td align="right" bordercolor="#e1e1e1">
                    <fmt:message key="doi_chieu.page.lable.qldc.htnh"/>
                  </td>
                  <td >
                    <html:select property="ma_dv" styleId="ma_dv" onchange="nhangval()"
                             style="width: 80%;font-size:12px"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                    <html:option value="">-----Ch&#7885;n ng&#226;n h&#224;ng-----</html:option>
                    <html:optionsCollection  name="dmucNH" value="ma_dv" label="ten_nh"/>
                </html:select>
                </td>      
              </tr>
              <tr>
                <td colspan="2" align="center">
                    T&#7915; <fmt:message key="doi_chieu.page.label.tracuu.ngay"/>&nbsp;
                <html:text property="tu_ngay" styleId="tu_ngay" styleClass="fieldText" 
                        onkeypress="return numbersonly(this,event,true) "
                       onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('tu_ngay');"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width:16%"
                       tabindex="107" />
                  <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="tu_ngay_btn"
                     style="vertical-align:middle;width:20"/>   
                    <script type="text/javascript">
                      Calendar.setup( {
                          inputField : "tu_ngay", // id of the input field
                          ifFormat : "%d/%m/%Y", // the date format
                          button : "tu_ngay_btn"// id of the button
                      });
                    </script> &nbsp;&nbsp;&nbsp;

                     &#272;&#7871;n <fmt:message key="doi_chieu.page.label.tracuu.ngay"/>&nbsp;
                <html:text property="den_ngay" styleId="den_ngay" styleClass="fieldText" 
                        onkeypress="return numbersonly(this,event,true) "
                       onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('den_ngay');"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width:16%"
                       tabindex="107" />
                  <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="den_ngay_btn"
                     style="vertical-align:middle;width:13"/>   
                    <script type="text/javascript">
                      Calendar.setup( {
                          inputField : "den_ngay", // id of the input field
                          ifFormat : "%d/%m/%Y", // the date format
                          button : "den_ngay_btn"// id of the button
                      });
                    </script>
                  </td>
              </tr>-->
           </table>
         </div>
        </fieldset>
       </td>
      </tr>
      <%
        com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
      int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
      %>
      
      <tr>
        <td>
         <fieldset>
            <legend>K&#7871;t qu&#7843; t&#236;m ki&#7871;m</legend>
            <div>
              <table width="100%" cellspacing="0" cellpadding="2" class="navigateable focused"
                 bordercolor="#e1e1e1" border="1" align="center"
                 style="BORDER-COLLAPSE: collapse">
                <thead>
                <th class="promptText" bgcolor="#f0f0f0" width="3%">
                  <div align="center" >
                    STT
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="15%">
                  <div align="center" >
                    <fmt:message key="doi_chieu.page.label.tracuu.kbtinh"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="20%">
                  <div align="center">
                    <fmt:message key="doi_chieu.page.label.tracuu.kbhuyen"/>
                  </div>
                </th>
                
                <th class="promptText" bgcolor="#f0f0f0" width="20%">
                  <div align="center">
                    T&#234;n tham s&#7889;
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="5%">
                  <div align="center">
                    Gi&#225; tr&#7883; TS
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="27%">
                  <div align="center">
                    M&#244; t&#7843;
                  </div>
                </th>
                
                <th class="promptText" bgcolor="#f0f0f0" width="7%">
                  <div align="center">
                    Ng&#432;&#7901;i thay &#273;&#7893;i
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="3%">
                  <div align="center">
                      <input type="checkbox" name="chklistAll" value="" id="chklistAll"/>
                  </div>
                </th>
              <tbody id="colCheckList" class="navigateable focused" cellspacing="0" style="width:100%" cellpadding="1" bordercolor="#e1e1e1">
              
              <logic:notEmpty name="lstTSKB">
              <logic:present name="lstTSKB" >          
                <logic:iterate id="items" name="lstTSKB" indexId="stt">
                <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                    <td align="center"> 
                      <%=stt+1+rowBegin%>
                    </td>
                    <td>
                      <bean:write name="items" property="ten_kb_tinh"/>
                    </td>
                    <td>
                      <bean:write name="items" property="ten_kb_huyen"/>
                      <input type="hidden" name="kbHuyen" value='<bean:write name="items" property="ten_kb_huyen"/>'/>
                    </td>
                                    
                    <td align="left">
                      <bean:write name="items" property="ten_ts"/>
                      <input type="hidden" name="tenThamSo" value='<bean:write name="items" property="ten_ts"/>'/>
                    </td>
                    <td align="center">
                      <bean:write name="items" property="giatri_ts"/>
                      <input type="hidden" name="giaTriThamSo" value='<bean:write name="items" property="giatri_ts"/>'/>
                    </td>
                    <td align="left">
                      <bean:write name="items" property="mo_ta"/>
                      <input type="hidden" name="moTa" value='<bean:write name="items" property="mo_ta"/>'/>
                    </td>
                    <td>                 
                      <bean:write name="items" property="ma_nsd"/>
                    </td>
                    <td>
                      <input type="checkbox" name="index" value='<%=stt%>'/>
                    </td>
                </tr>
                </logic:iterate>
                </logic:present>
                <tr>
                  <td colspan="8">                 
                    <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean,"#0000ff") %>
                  </td>
               </tr>
              </logic:notEmpty>
              <logic:empty name="lstTSKB">
                <tr>
                <td colspan="6">
                  <font color="red"><fmt:message key="doi_chieu.page.label.tracuu.empty"/></font>
                </td>
                </tr>
               </logic:empty> 
              </tbody>
              </table>
            </div>
          </fieldset>
          </td>
      </tr>
      
      <tr>
        <td align="right">
          <button type="button" onclick="check('close')" class="ButtonCommon" accesskey="o">
                          Th<span class="sortKey">o</span>&#225;t
          </button>
        </td>
      </tr>
      
    </table> 
    <html:hidden property="pageNumber"/>
    <html:hidden property="nhkb_huyen" styleId="a"/>

  </html:form>
</div>
<div id="dialog-form-lov-dm" title="Tra c&#7913;u danh m&#7909;c Kho b&#7841;c">
  <p class="validateTips"></p>
  <%@include file="/pages/lov/lovDMKBTCUU.jsp" %>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
  var f = document.forms[0];

  function check(type) {  
     if (type == 'find') {
        var inKB = jQuery('#nhkb_huyen option:selected').index();
//        var inNH = jQuery('#ma_dv option:selected').index() ;
//        var inxtthai = jQuery('#tthai_dxn_thop option:selected').index();
//        var lan_dc = jQuery('#lan_dc option:selected').val();
        f.action = 'loadTSoTabmis.do?inKB='+inKB;
        
      }
     if (type == 'close') {
        f.action = 'mainAction.do';          
      } 
       f.submit();
    }
    function nhkb_huyenval() {
      var nhkb_huyen;
      nhkb_huyen=  document.getElementById("nhkb_huyen").value;     
      return nhkb_huyen;
    }
    function ten_tsval() {
      var ten_ts;
      ten_ts=  document.getElementById("ten_ts").value;     
      return ten_ts;
    }

      function nhangval() {
      var ma_dv;
      ma_dv=document.getElementById("ma_dv").value;
      return ma_dv;
  }
    function callLov(){      
      jQuery("#loai_lov").val("DMKBTCUU");
      jQuery("#ma_field_id_lov").val("ma_nhkb_nhan");
      jQuery("#ten_field_id_lov").val("ten_nhkb_nhan");
      jQuery("#id_field_id_lov").val("id_nhkb_huyen");
      jQuery("#id_cha_field_id_lov").val("id_nhkb_tinh");
      jQuery("#dialog-form-lov-dm").dialog( "open" );
      
    }
  
function goPage(page) {
      f.pageNumber.value = page;
      var tinh=jQuery("#nhkb_tinh").val();
      var huyen =jQuery("#nhkb_huyen").val();
//      if(initAction==null || ""==initAction){
        f.action = 'loadTSoTabmis.do?nhkb_tinh='+tinh+"&nhkb_huyen="+huyen+"&pageNumber="+page;   
//      }else if(initAction!=null && ""!=initAction){
////        f.action = 'TCuuTTinDChieuAction.do'; 
//      }
      f.submit();
//      }
  }
  function getTenKhoBacDC(id,id_cha) { 
      document.getElementById('nhkb_huyen').options.length = 1;// clear du lieu option cu
       var kb_id;
//       alert('id='+id+'id_cha='+id_cha);
            if (id==null || ''==id){       
                  if(id_cha!=null&&''!=id_cha){               
                        kb_id=id_cha;
                        jQuery('#nhkb_tinh').val(id_cha);
                  }else if (id_cha==null||''==id_cha){
                    kb_id=document.forms[0].nhkb_tinh.value;
                  }
              }else if (id!=null && ''!=id){
                  if(id_cha!=null&&''!=id_cha){                
                    kb_id=id_cha;
                    jQuery('#nhkb_tinh').val(id_cha);
                  }else if (id_cha==null||''==id_cha){
                    kb_id=document.forms[0].nhkb_tinh.value;
                  }
              }
      var kb_huyen="<%=kb_huyen%>";   
//      var strTinh="<%=strTinh%>";
      
     if (kb_id !=null && ""!=kb_id){
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
                      if(document.getElementById('nhkb_huyen').options.length==2){ // select dong thu 2 neu select box co 2 value voi user cap tinh
                              jQuery("#nhkb_huyen option:eq(1)").attr('selected', true);
                         }
                        else if(kb_huyen!='0'){
                        jQuery('#nhkb_huyen option:eq('+kb_huyen+')').attr('selected', true);
                        }
                  }
              }
                if (id!=null && ''!=id){
                          
                          jQuery('#nhkb_huyen').val(id);
                      }
          },
          error : function (textstatus) {
              alert(textstatus);
          }
      });
      }
  }
  
function arrowUpDownTCuuDC(e) {
    var keyCode = e.keyCode || e.charCode, arrow = {
        up : 38, down : 40, enter : 13, esc : 27
    };
     var page = jQuery('#currentPage').val();
     var total_page = jQuery('#total_page').val();
    switch (keyCode) {
        case arrow.up:
            
              page = parseInt(page) + 1;
              jQuery('#currentPage').val(page);            
              if(parseInt(jQuery('#currentPage').val())>parseInt(total_page)){
              jQuery('#currentPage').val(total_page);
              return false;
              }
              break;

        case arrow.down:
              page = page - 1;
              jQuery('#currentPage').val(page);
              if(jQuery('#currentPage').val()<1){
                jQuery('#currentPage').val('1');
                return false;
                }
            break;
        case arrow.enter:
            goPage();
            break;
    }

}

</script>
