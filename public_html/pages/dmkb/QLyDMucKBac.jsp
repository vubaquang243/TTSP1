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
        
<fmt:setBundle basename="com.seatech.ttsp.resource.QuanLyTKNHKBResource"/>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%
  String kb_huyen = request.getAttribute("kb_huyen")==null?"":request.getAttribute("kb_huyen").toString();
  String ngan_hang = request.getAttribute("ngan_hang")==null?"":request.getAttribute("ngan_hang").toString();
  String strTinh = request.getAttribute("dftinh")==null?"":request.getAttribute("dftinh").toString();
  String tcuu = request.getAttribute("tcuu")==null?"":request.getAttribute("tcuu").toString();
  String TTTT = request.getAttribute("TTTT")==null?"":request.getAttribute("TTTT").toString();
%>

<script type="text/javascript">
  jQuery.noConflict();
  //************************************ LOAD PAGE **********************************  
  jQuery(document).init(function () {
      getTenKhoBacDC('','');     
    jQuery("#dialog-form-lov-dm").dialog({
      autoOpen: false,resizable : false,
      maxHeight: "700px",
      width: "550px",
      modal: true
    });    
  });
</script>

<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="/QLyDMucKBacAction.do" method="post" >
   <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
          <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
            <span class=title2><span class=title2><fmt:message key="tknhkb.page.title"/></span></span>
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
                                               cellspacing="0" cellpadding="" >
                <tr>
                  <td width="15%" align="right" bordercolor="#e1e1e1">
                    Kho bạc tỉnh
                  </td>
                  <td width="20%">
                    <html:select property="nhkb_tinh" styleId="nhkb_tinh" style="font-size:12px;width:100%"  onchange="getTenKhoBacDC('','')"
                                 onkeydown="if(event.keyCode==13) event.keyCode=9;"> 
                              <%if(request.getAttribute("TTTT") != null){
                           %>
                                <html:option value="">Ch&#7885;n th&#244;ng tin kho b&#7841;c t&#7881;nh</html:option>                  
                            <%}%>
                        <html:optionsCollection  name="dmuckb_tinh" value="id_cha" label="kb_tinh"/>                    
                    </html:select>
                    </td>
                  <td width="15%" align="right" bordercolor="#e1e1e1">
                    Kho bạc huyện
                  </td>
                  <td width="30%">
                    <html:select property="nhkb_huyen" styleId="nhkb_huyen" style="font-size:12px;width:80%" onchange="nhkb_huyenval();"
                                  onkeydown="if(event.keyCode==13) event.keyCode=9;">                               
                                <html:option value="">Ch&#7885;n th&#244;ng tin kho b&#7841;c huy&#7879;n</html:option>
                                
                  </html:select>
                  </td>
                    
                  <td  align="center" rowspan="2" width="15%">
                    <%if(request.getAttribute("TTTT") != null){
                    %>
                    <button type="button" onclick="callLov()" class="ButtonCommon" accesskey="d" >
                            <span class="sortKey">D</span>anh m&#7909;c KB
                    </button> 
                    <%}%>              
  
                 </td>
                </tr>
                <tr>
                  <td align="right" bordercolor="#e1e1e1">
                      H&#7879; th&#7889;ng ng&#226;n h&#224;ng
                    </td>
                    <td >
                      <html:select property="ma_dv" styleId="ngan_hang" onchange="nhangval()"
                               style="width: 80%;font-size:12px"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                      <html:option value="" >-----Ch&#7885;n ng&#226;n h&#224;ng-----</html:option>
                      <html:optionsCollection  name="dmucNH" value="ma_dv" label="ten_nh"/>
                  </html:select>
                  </td>
                </tr>
                <!--<tr>
                  <td align="center" colspan="5">
                    <br/>
                  <button type="button" onclick="check('add')" accesskey="t" >
                            <span class="sortKey">T</span>hêm mới
                    </button> &nbsp;&nbsp;
                    <button type="button" onclick="check('find')" class="ButtonCommon" accesskey="d" >
                            <span class="sortKey">T</span>&#236;m ki&#7871;m
                    </button>
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
                  style="BORDER-COLLAPSE: collapse;table-layout:fixed">
                <thead>
                <th class="promptText" bgcolor="#f0f0f0" width="3%">
                  <div align="center" >
                    STT 
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="9%">
                  <div align="center">
                    S&#7889; TK
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="4%">
                  <div align="center">
                    SHKB
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="15%">
                  <div align="center">
                    Kho b&#7841;c
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="5%">
                  <div align="center">
                    M&#227; NH
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="15%">
                  <div align="center">
                    T&#234;n ng&#226;n h&#224;ng
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="8%">
                  <div align="center">
                    H&#7841;n m&#7913;c QT
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="8%">
                  <div align="center" >
                    H&#7841;n m&#7913;c n&#7907;
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="8%">
                  <div align="center" >
                    Lo&#7841;i TK
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="6%">
                  <div align="center" >
                    Lo&#7841;i GD
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0"  width="6%"  >
                  <div align="center" >
                    Tr&#7841;ng th&#225;i
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0"  width="3%"  >
                  <div align="center" >
                     
                  </div>
                </th>
              </thead>
              <tbody class="navigateable focused" cellspacing="0" style="width:100%" cellpadding="1" bordercolor="#e1e1e1">
                <logic:notEmpty name="colLstSoTK">
                <logic:present name="colLstSoTK" >          
                  <logic:iterate id="items" name="colLstSoTK" indexId="stt">
                    <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                      <td align="center"> 
                        <%=stt+1+rowBegin%>
                      </td>
                      <td align="center">
                        <bean:write name="items" property="so_tk"/>
                      </td>
                      <td align="center">
                        <bean:write name="items" property="shkb"/>
                      </td>
                      <td align="left" title="<bean:write name="items" property="ten_kb"/>" style="text-overflow:ellipsis;white-space:nowrap;  width:100px; overflow:hidden; font-size:11px">
                        <bean:write name="items" property="ten_kb"/>
                      </td>
                      <td align="center">
                        <bean:write name="items" property="ma_nh"/>
                      </td>
                      <td align="left" title="<bean:write name="items" property="ten_nh"/>" style="text-overflow:ellipsis;white-space:nowrap;  width:100px; overflow:hidden; font-size:11px">
                          <bean:write name="items" property="ten_nh" />
                      </td>
                      <td align="right">
                        <b><fmt:setLocale value="vi_VI"/>
                         <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                         <bean:write name="items" property="han_muc_co"/>
                         </fmt:formatNumber></b>
                      </td>
                      <td align="right">
                        <b><fmt:setLocale value="vi_VI"/>
                         <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                         <bean:write name="items" property="han_muc_no"/>
                         </fmt:formatNumber></b>
                      </td>
                      <td align="center">
                          <logic:equal value="01" name="items" property="loai_tk">
                           TK ti&#7873;n g&#7917;i
                          </logic:equal>
                          <logic:equal value="02" name="items" property="loai_tk">
                            TK thanh to&#225;n
                          </logic:equal>
                          <logic:equal value="03" name="items" property="loai_tk">
                            TK chuy&#234;n thu
                          </logic:equal>
                      </td>
                      <td align="center">
                          <logic:equal value="01" name="items" property="loai_gd">
                           TTSP+PHT
                          </logic:equal>
                          <logic:equal value="02" name="items" property="loai_gd">
                            TTSP
                          </logic:equal>
                          <logic:equal value="03" name="items" property="loai_gd">
                            PHT
                          </logic:equal>
                      </td>
                      <td>
                        <logic:equal value="01" name="items" property="trang_thai">
                           Ho&#7841;t &#273;&#7897;ng
                          </logic:equal>
                          <logic:equal value="02" name="items" property="trang_thai">
                            Kh&#243;a
                          </logic:equal>
                      </td>
                      <td>                    
                      <!--<a href="<html:rewrite page="/themtaikhoan.do?action=EDIT"/>&id_tk=<bean:write name="items" property="id_tk"/>&shkb=<bean:write name="items" property="shkb"/>&p_loai_tk=<bean:write name="items" property="loai_tk"/>&p_loai_gd=<bean:write name="items" property="loai_gd"/>">Sửa</a>--> 
                      </td>
                    </tr>
                  </logic:iterate>
                  <tr>
                      <td colspan="12" >                 
                     <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean,"#0000ff") %>
                      </td>
                  </tr>
                </logic:present>   
                </logic:notEmpty>
              </tbody>
            </table>
          </div>
        </fieldset>
      </td>
    </tr>
 </table>
    <html:hidden property="pageNumber" value="1"/>
  </html:form>
</div>
<div id="dialog-form-lov-dm" title="Tra c&#7913;u danh m&#7909;c Kho b&#7841;c">
  <p class="validateTips"></p>
  <%@include file="/pages/lov/lovDMKBTCUU.jsp" %>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">

  var f = document.forms[0];
function goPage(page) {
 
      f.pageNumber.value = page;
      f.action = 'lstTKNHKB.do?pageNumber='+page;
      f.submit();
  }
  function check(type) { 
    if (type == 'find') {
     f.action = 'lstTKNHKB.do';  
    }else if (type == 'add') {
     f.action = 'themtaikhoan.do?action=ADD.do';  
    }else if (type == 'print') {
      ngay_qtoan= jQuery('#ngay_qtoan').val();
      if(ngay_qtoan.trim()==null || ""==ngay_qtoan.trim()){
         alert(GetUnicode('C&#7847;n nh&#7853;p th&#244;ng tin ng&#224;y quy&#7871;t to&#225;n'));
         jQuery('#ngay_qtoan').focus();
       return false;
      }else{
        f.action = 'printCTLTTTQ.do'; 
        var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
        newDialog = window.open('', '_form', params);  
        f.target='_form';
      }
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
  function nhangval() {
      var ngan_hang;
      ngan_hang=document.getElementById("ngan_hang").value;
      return ngan_hang;
  }

  function loai_qtVal() {
      var loai_qt;
      loai_qt=document.getElementById("loai_qtoan").value;
      return loai_qt;
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
    var TTTT="<%=TTTT%>";
    document.getElementById('nhkb_huyen').options.length = 1;// clear du lieu option cu
     var kb_id;
     if(TTTT!=null && ''!=TTTT){
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
       }else if(TTTT==null || ''==TTTT){
        kb_id=document.forms[0].nhkb_tinh.value;
       }
    var kb_huyen="<%=kb_huyen%>";   
    var strTinh="<%=strTinh%>";
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
                    if( strTinh==null || strTinh ==''){  // request set dftinh ==null
                        if(document.getElementById('nhkb_huyen').options.length==2){
                          jQuery("#nhkb_huyen option:eq(0)").remove();

                        }
                    }else if(strTinh!=null && strTinh !=''){
                       if(document.getElementById('nhkb_huyen').options.length==2){ // select dong thu 2 neu select box co 2 value voi user cap tinh
                            jQuery("#nhkb_huyen option:eq(1)").attr('selected', true);

                       }
                      else if(kb_huyen=='0'||kb_huyen==null||''==kb_huyen){
                      jQuery('#ngan_hang option:eq(0)').attr('selected', true);

                      }
                      else if(kb_huyen!='0'){
                      jQuery('#nhkb_huyen option:eq('+kb_huyen+')').attr('selected', true);

                      }
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

</script>
