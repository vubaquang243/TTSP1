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
  String kb_tinh = request.getAttribute("kb_tinh")==null?"":request.getAttribute("kb_tinh").toString();
  String ma_dv = request.getAttribute("ma_dv")==null?"":request.getAttribute("ma_dv").toString();
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
var kb_tinh= '<%=kb_tinh%>';
var kb_huyen= '<%=kb_huyen%>';
//alert(kb_tinh+'---'+kb_huyen);
      getTenKhoBacDC(kb_huyen,kb_tinh);      
    jQuery("#dialog-form-lov-dm").dialog({
      autoOpen: false,resizable : false,
      maxHeight: "700px",
      width: "550px",
      modal: true
    }); 
    
    jQuery("#dialog-confirm1").dialog( {
        autoOpen: false,
                  resizable: false,
                  height:200,
                  width:380,
                  modal: true,
                  buttons:{
               "Không" : function () {   
                  jQuery(this).dialog("close");
              },
              "Có" : function () {    
                  document.forms[0].action = "excUpdateTSoTabmis.do";
                  document.forms[0].submit();   
                  jQuery(this).dialog("close");
              }
              
          }
      });
    
    
  });
</script>

<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="excUpdateTSoTabmisHaveCheck.do" method="post" >
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
        <legend><font color="blue">Nhập thông tin cần sửa</font></legend>
        <div>
          <table style="width:100%" border="0" cellspacing="0" cellpadding="2" >
              <logic:empty name="listKBUpdate">
              <tr>
                <td width="15%" align="right" bordercolor="#e1e1e1">
                  <fmt:message key="doi_chieu.page.label.tracuu.kbtinh"/>&nbsp;
                </td>
                <td width="30%">
                  <html:select property="nhkb_tinh" styleId="nhkb_tinh" style="font-size:12px;width:100%"  onchange="getTenKhoBacDC('','')"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"> 
                              <html:option value="">---Chọn KB tỉnh---</html:option>                  
                      <html:optionsCollection  name="dmuckb_tinh" value="id_cha" label="kb_tinh"/>                    
                  </html:select>
                  </td>
                <td width="10%" align="right" bordercolor="#e1e1e1">
                  <fmt:message key="doi_chieu.page.label.tracuu.kbhuyen"/>&nbsp;
                </td>
                <td width="20%">
                  <html:select property="nhkb_huyen" styleId="nhkb_huyen" style="font-size:12px;width:100%" onchange="nhkb_huyenval();"
                                onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                              <html:option value="">---Chọn KB huyện---</html:option>
                </html:select>
                </td>
                <%if(request.getAttribute("dchieu3") != null){%>
                <td>
                <button type="button" onclick="callLov()" accesskey="t" >
                          <span class="sortKey">D</span>anh m&#7909;c KB
                  </button>
                </td>
                <%}%>
              </tr>
              <tr>
              <td width="8%" align="right">
                   T&#234;n tham s&#7889;
              </td>
              <td width="20%">
                <html:select property="ten_ts" styleId="ten_ts" style="font-size:12px;width:100%"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">
                    <html:option value="">---Chọn tên tham số---</html:option>
                    <html:optionsCollection  name="lstTSo" value="ten_ts" label="ten_ts"/>                    
                </html:select>
              </td>
              <td width="8%" align="right">
                   Giá trị tham số
              </td>
              <td>
                 <html:select property="giatri_ts" styleId="giatri_ts" style="font-size:12px;width:20%"
                                onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                      <html:option value="Y">Y</html:option>
                      <html:option value="N">N</html:option>
                 </html:select>
              </td>
              <td>
              </td>
              </tr>
              <!--<tr>
                <td width="8%" align="right">
                   Mô tả
                  </td>
                  <td >
                      <html:textarea disabled="true" property="mo_ta" rows="2" style="width:100%" cols="60" value="" />
                  </td>
              
              </tr>-->
              </logic:empty>
              <logic:notEmpty name="listKBUpdate">
                 <html:hidden property="nhkb_tinh" value="<%=kb_tinh%>"/>
                  <html:hidden property="nhkb_huyen" value="<%=kb_huyen%>"/>
                  <tr>
                      <td width="5%" colspan="2" align="right">Giá trị tham số</td>
                      <td width="20%">
                          <html:select property="giatri_ts" styleId="giatri_ts" style="font-size:12px;width:20%"
                                onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                              <html:option value="Y">Y</html:option>
                              <html:option value="N">N</html:option>
                          </html:select>
                      </td>
                  </tr>
                  <tr>
                      <td colspan="4">&nbsp;</td>
                  </tr>
                  <tr>
                      <td colspan="4">
                          <table width="100%" cellspacing="0" cellpadding="2" class="navigateable focused"
                                 bordercolor="#e1e1e1" border="1" align="center"
                                 style="BORDER-COLLAPSE: collapse">
                            <thead>
                            <tr>
                              <th bgcolor="#f0f0f0" colspan="8">Danh sách kho bạc sửa giá trị tham số</th>
                            </tr>
                            <tr>
                              <th bgcolor="#f0f0f0" width="5%">
                                <div align="center" >
                                  STT
                                </div>
                              </th>
                               <th class="promptText" bgcolor="#f0f0f0" width="5%">
                                <div align="center" >
                                  SHKB
                                </div>
                              </th>
                              <th bgcolor="#f0f0f0" width="20%">
                                <div align="center">
                                  <fmt:message key="doi_chieu.page.label.tracuu.kbhuyen"/>
                                </div>
                              </th>
                              <th class="promptText" bgcolor="#f0f0f0" width="5%">
                                <div align="center" >
                                  Mã NH
                                </div>
                              </th>
                              <th class="promptText" bgcolor="#f0f0f0" width="14%">
                                <div align="center" >
                                  Tên NH
                                </div>
                              </th>
                              <th bgcolor="#f0f0f0" width="25%">
                                <div align="center">
                                  T&#234;n tham s&#7889;
                                </div>
                              </th>
                              <th bgcolor="#f0f0f0" width="5%">
                                <div align="center">
                                  Gi&#225; tr&#7883; TS
                                </div>
                              </th>
                              <th bgcolor="#f0f0f0" width="21%">
                                <div align="center">
                                  M&#244; t&#7843;
                                </div>
                              </th>
                            </tr>
                          </thead>
                          <tbody class="navigateable focused" cellspacing="0" style="width:100%" cellpadding="1" bordercolor="#e1e1e1">
                            <logic:present name="listKBUpdate" >          
                              <logic:iterate id="items" name="listKBUpdate" indexId="stt">
                              <tr>
                                  <td align="center"> 
                                    <%=stt+1%>
                                    <input type="hidden" name="index" value='<%=stt%>'/>
                                  </td>
                                  <td align="center">
                                    <bean:write name="items" property="ma"/>
                                     <input type="hidden" name="ma" value='<bean:write name="items" property="ma"/>'/>
                                  </td>
                                  <td>
                                    <bean:write name="items" property="ten_kb_huyen"/>
                                    <input type="hidden" name="kbHuyen" value='<bean:write name="items" property="ten_kb_huyen"/>'/>
                                  </td>
                                     <td align="center">
                                      <bean:write name="items" property="ma_nh"/>
                                       <input type="hidden" name="ma_nh" value='<bean:write name="items" property="ma_nh"/>'/>
                                    </td>  
                                    <td>
                                      <bean:write name="items" property="ten_ngan_hang"/>
                                       <input type="hidden" name="ten_ngan_hang" value='<bean:write name="items" property="ten_ngan_hang"/>'/>
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
                                </tr>
                                </logic:iterate>
                              </logic:present>
                            </tbody>
                          </table>
                      </td>
                  </tr>
              </logic:notEmpty>
           </table>
         </div>
        </fieldset>
       </td>
      </tr>
      <tr>
        <td align="right">
          <logic:notEmpty name="listKBUpdate">
            <button type="button" onclick="check('updateHaveCheck');" class="ButtonCommon" accesskey="t" >
                    <span class="sortKey">C</span>&#7853;p nh&#7853;t
            </button>
          </logic:notEmpty>
          <logic:empty name="listKBUpdate">
            <button type="button" onclick="check('update');" class="ButtonCommon" accesskey="t" >
                    <span class="sortKey">C</span>&#7853;p nh&#7853;t
            </button>
          </logic:empty>
            &nbsp;&nbsp;&nbsp;
          <button type="button" onclick="check('close')" class="ButtonCommon" accesskey="o">
                          Th<span class="sortKey">o</span>&#225;t
          </button>
        </td>
      </tr>
    </table> 

  </html:form>
  <div id="dialog-confirm1"
     title='<fmt:message key="doi_chieu.page.title.dialog_confirm"/>'>
  <p
  </p>
</div>
</div>
<logic:empty name="listKBUpdate">
  <div id="dialog-form-lov-dm" title="Tra c&#7913;u danh m&#7909;c Kho b&#7841;c">
    <p class="validateTips"></p>
    <%@include file="/pages/lov/lovDMKBTCUU.jsp" %>
  </div>
</logic:empty>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
  var f = document.forms[0];

    function check(type) { 
   
        if (type == 'update') {
//            var inKB = jQuery('#nhkb_huyen option:selected').index();
//            var inNH = jQuery('#ma_dv option:selected').index() ;
//            var inxtthai = jQuery('#tthai_dxn_thop option:selected').index();
//            var lan_dc = jQuery('#lan_dc option:selected').val();
            var nhkb_tinh = jQuery('#nhkb_tinh').val();
            var ten_ts = jQuery('#ten_ts').val();
             //20171016 thuongdt check lai if(ten_ts == null || '' == ten_ts)
             if(ten_ts == null || '' == ten_ts){
               alert("Cần chọn tên tham số.")
             }else if(nhkb_tinh==null ||''==nhkb_tinh){
                jQuery("#dialog-confirm1").html('Anh/Ch&#7883; c&#243; mu&#7889;n thi&#7871;t l&#7853;p tham s&#7889; cho t&#7845;t c&#7843; c&#225;c &#273;&#417;n v&#7883;');
                jQuery("#dialog-confirm1").dialog("open");
            }else if(nhkb_tinh!=null &&''!=nhkb_tinh){
                f.action = 'excUpdateTSoTabmis.do';
                f.submit();
            }
        }
        if(type=='updateHaveCheck'){
            f.submit();
        }
        if (type == 'close') {
            var inKB = jQuery('#nhkb_huyen option:selected').index();
            f.action = 'loadTSoTabmis.do?inKB='+inKB;   
            f.submit();
        }        
    }
    function nhkb_huyenval() {
        var nhkb_huyen;
        nhkb_huyen=  document.getElementById("nhkb_huyen").value;     
        return nhkb_huyen;
    }

    function callLov(){      
        jQuery("#loai_lov").val("DMKBTCUU");
        jQuery("#ma_field_id_lov").val("ma_nhkb_nhan");
        jQuery("#ten_field_id_lov").val("ten_nhkb_nhan");
        jQuery("#id_field_id_lov").val("id_nhkb_huyen");
        jQuery("#id_cha_field_id_lov").val("id_nhkb_tinh");
        jQuery("#dialog-form-lov-dm").dialog( "open" );
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
