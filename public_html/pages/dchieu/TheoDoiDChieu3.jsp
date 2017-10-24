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
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/lov.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery.jec-1.3.2.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/lov.js"></script>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.LTTDiResource" />
<fmt:setBundle basename="com.seatech.ttsp.resource.DoichieuResource"/>

<%
 //String kb_huyen = request.getAttribute("kb_huyen")==null?"":request.getAttribute("kb_huyen").toString();
//  String ngan_hang = request.getAttribute("ngan_hang")==null?"":request.getAttribute("ngan_hang").toString();
  String qthttw = request.getAttribute("QTHTTW")==null?"":request.getAttribute("QTHTTW").toString();
  String idxKB = request.getAttribute("idxKB")==null?"":request.getAttribute("idxKB").toString();
  String idxNH = request.getAttribute("idxNH")==null?"":request.getAttribute("idxNH").toString();
 // String idxTK = request.getAttribute("idxTK")==null?"":request.getAttribute("idxTK").toString();
  //String sum_lai = request.getAttribute("sum_lai")==null?"":request.getAttribute("sum_lai").toString();
  //String sum_sdu = request.getAttribute("sum_sdu")==null?"":request.getAttribute("sum_sdu").toString();
  
  String ngaydc = request.getAttribute("ngaydc")==null?"":request.getAttribute("ngaydc").toString();
%>

<div class="app_error">
  <html:errors/>
</div>


<div class="box_common_conten">
  <html:form action="theoDoiDChieu3.do" method="post" >
  <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
          <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
            <span class=title2>Tổng hợp đối chiếu quyết toán toàn quốc </span>
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
        <legend><font color="blue">&#272;i&#7873;u ki&#7879;n bảng k&#234; </font></legend>
        <div>
            <table  class="data-grid" id="data-grid" 
                                              style="width:100%" border="0"
                                             cellspacing="0" cellpadding="1" >
              <tr>
                <td width="15%" align="right" bordercolor="#e1e1e1">
                  <fmt:message key="doi_chieu.page.label.tracuu.kbtinh"/>&nbsp;
                </td>
                <td width="22%">
                  <html:select property="nhkb_tinh" styleId="nhkb_tinh" style="font-size:12px;width:100%"  onchange="getTenKhoBacDC('','')"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"> 
                
                           <%if(request.getAttribute("QTHTTW") != null){
                           %>
                              <html:option value="">---<fmt:message key="doi_chieu.page.label.tracuu.default"/>---</html:option>
                          <%}%>                   
                      <html:optionsCollection  name="dmuckb_tinh" value="id_cha" label="kb_tinh"/>                
                  </html:select>
                  </td>
                <td width="15%" align="right" bordercolor="#e1e1e1">
                  Ng&#226;n h&#224;ng &nbsp;
                </td>
                <td width="22%">
                   <html:select property="ngan_hang" styleId="ngan_hang" style="font-size:12px;width:100%" onchange="nhangval();"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                      <html:option value="">-----<fmt:message key="doi_chieu.page.label.tracuu.default"/>-----</html:option>
                       <html:optionsCollection  name="dmucnh" value="ma_dv" label="ten_nh"/> 
                  </html:select>
                 
                </td>
                <td  align="right" width="25%" style="padding-right:20px">
                 <button type="button" onclick="check('find');" class="ButtonCommon" accesskey="x" >
                          <span class="sortKey">T</span>ra cứu
                  </button>&nbsp;&nbsp;&nbsp;
                  <button type="button" onclick="check('print');" class="ButtonCommon" accesskey="i" >
                          <span class="sortKey">I</span>n 
                  </button>  
               </td>              
              </tr>
              <tr>
              <td align="right" bordercolor="#e1e1e1">
                  
                   <fmt:message key="doi_chieu.page.label.tracuu.kbhuyen"/>&nbsp;
                  </td>
                  <td >
                   <html:select property="nhkb_huyen" styleId="nhkb_huyen" style="font-size:12px;width:100%" onchange="nhkb_huyenval();"
                                onkeydown="if(event.keyCode==13) event.keyCode=9;">               
                              <html:option value="">---<fmt:message key="doi_chieu.page.label.tracuu.default"/>---</html:option>                             
                  </html:select>
                    
                </td>
                 <td align="right" bordercolor="#e1e1e1">
                    <fmt:message key="doi_chieu.page.lable.ngaydc"/>&nbsp;
                  </td>
                  <td >
                      <html:text property="ngayDC" styleId="ngayDC" styleClass="fieldText" 
                        onkeypress="return numbersonly(this,event,true) "
                       onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('tu_ngay');"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width:155px"
                       tabindex="107" />
                  <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="ngay_dc_btn"
                     style="vertical-align:middle;width:20"/>   
                    <script type="text/javascript">
                      Calendar.setup( {
                          inputField : "ngayDC", // id of the input field
                          ifFormat : "%d/%m/%Y", // the date format
                          button : "ngay_dc_btn"// id of the button
                      });
                    </script>                     
                </td>
                <td colspan="2" align="center">
                <button type="button" style="width:50%" onclick="callLov();" class="ButtonCommon" accesskey="t" >
                  <span class="sortKey">D</span>anh m&#7909;c KB
                 </button>
                </td>
              </tr>
              <tr>
                <td align="right">
                    <fmt:message key="doi_chieu.page.lable.tthai"/>&nbsp;đối chiếu&nbsp;
                </td>
                <td>
                    <html:select property="trangThaiDC" styleId="trangThaiDC" style="font-size:12px;width:100%" 
                                onkeydown="if(event.keyCode==13) event.keyCode=9;">               
                              <html:option value="">---<fmt:message key="doi_chieu.page.label.tracuu.default"/>---</html:option>  
                              <html:option value="00">Chưa đối chiếu</html:option> 
                              <html:option value="01">Chênh lệch</html:option> 
                              <html:option value="02">Khớp đúng</html:option> 
                  </html:select>
                </td>
                <td align="right">
                    Loại tiền
                </td>
                <td>
                    <html:select property="loaiTien" styleId="loaiTien" style="font-size:12px;width:100%" 
                                onkeydown="if(event.keyCode==13) event.keyCode=9;">               
                              <html:option value="">---<fmt:message key="doi_chieu.page.label.tracuu.default"/>---</html:option> 
                              <html:optionsCollection name="tienTe" value="ma" label="ma"/>
                    </html:select>
                  </td>
                  <td>
                                  
                  </td>
              </tr>
              <%
        com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
      int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
      %>
      
      <logic:present name="lisBangKe">
        <logic:notEmpty name="lisBangKe">
      <tr>
         <td colspan="5">
          <fieldset>
          <legend><font color="Blue">Chi ti&#7871;t bảng k&#234;</font></legend>
            <div><b>I. các đơn vị hoàn thành quy trình đối chiếu, quyết toán</b> </div>
            <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(Tổng số đơn vị hoàn thành/ tổng số đơn vị TTSP)</div>
            <%=request.getAttribute("dvdc")%>
            <div><b>II. các đơn vị chưa hoàn thành quy trình đối chiếu, quyết toán</b> </div>
            <table  class="data-grid" id="data-grid" 
                                                border="1"
                                               cellspacing="0" cellpadding="0"                                  
                                               width="100%">

                  <tr>
                    <th width="3%">
                      STT
                    </th>
                    <th width="5%">
                      NH
                    </th>
                    <th width="5%">
                     Mã KB
                    </th>
                    <th width="20%">
                      Tên KBNN
                    </th>                   
                    <th width="10%">
                      Đối chiếu TTSP
                    </th> 
                    <th width="10%">
                      Đối chiếu TCS
                    </th>
                    <th width="10%">
                      Trạng thái Đề nghị QT
                    </th>
                    <th width="11%">
                      Tình trạng quyết toán
                    </th>
                    
                    <%if(!ngaydc.equals("") ){%>
                        <th width="11%">
                          Trạng thái điện ĐNQT
                        </th>
                    <%}%>
                  </tr>
                 
                   
                        <logic:iterate id="items" name="lisBangKe" indexId="stt">
                         <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                            <td align="center" > 
                              <%=stt+1+rowBegin%>
                            </td>
                            <td align="center">
                              <bean:write name="items" property="bi_danh"/>
                            </td>                            
                            <td align="center">
                              <bean:write name="items" property="ma_kb"/>
                            </td>
                            <td align="left">
                              <bean:write name="items" property="ten_huyen"/>
                            </td> 
                            <td align="center">
                              <bean:write name="items" property="tt_dc1"/>
                            </td>
                             <td align="center">
                              <bean:write name="items" property="tt_dc_pht"/>
                            </td>
                            <td align="center">
                              <bean:write name="items" property="tt_dnqt"/>
                            </td>
                             <td align="center">
                              <bean:write name="items" property="tt_qtoan"/>
                            </td>
                             <%if(!ngaydc.equals("") ){%>
                              <td align="center">
                                <bean:write name="items" property="tthai_dxn_thop"/>
                              </td>
                            <%}%>
                         </tr>
                        </logic:iterate>
                        <tr>
                         <%if(!ngaydc.equals("") ){%>
                            <td colspan="9" align="right"> 
                          <%}else{%> 
                            <td colspan="8" align="right"> 
                          <%}%>
                           <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean,"#0000ff") %>
                            </td>
                            
                        </tr>
                                  
              </table>
           </fieldset>
          </td>
      </tr>
    </logic:notEmpty>
  </logic:present>     
           </table>
        
        
        </div>
        </fieldset>
       </td>
      </tr>
      
  </table>
   
   
   
 <html:hidden property="pageNumber" styleId="pageNumber"/>
  <input type="hidden" name ="idxKB"/>
   <input type="hidden" name ="idxNH"/>
  </html:form>
</div>

<div id="dialog-form-lov-dm" title="Tra c&#7913;u danh m&#7909;c Kho b&#7841;c">
  <p class="validateTips"></p>
    <%@include file="/pages/lov/lovDMKBTCUU.jsp" %>
</div>

<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
getTenKhoBacDC('','');

//load dialog tra cuu kho bac
function callLov(){      
      jQuery("#loai_lov").val("DMKBTCUU");
      jQuery("#ma_field_id_lov").val("ma_nhkb_nhan");
      jQuery("#ten_field_id_lov").val("ten_nhkb_nhan");
      jQuery("#id_field_id_lov").val("id_nhkb_huyen");
      jQuery("#id_cha_field_id_lov").val("id_nhkb_tinh");
      jQuery("#dialog-form-lov-dm").dialog( "open" );
      
    }

//open dialog tra cuu kho bac
 jQuery("#dialog-form-lov-dm").dialog({
      autoOpen: false,resizable : false,
      maxHeight: "700px",
      width: "550px",
      modal: true
    });
  
//lay gia tri kb huyen va ngan hang  
function nhkb_huyenval() {
      var nhkb_huyen;
      nhkb_huyen=  jQuery('#nhkb_huyen').val();     
      return nhkb_huyen;
  }
  function nhangval() {
      var ngan_hang;
      ngan_hang=jQuery('#ngan_hang').val();     
      return ngan_hang;
  }
  
  //Lay ten kho bac
function getTenKhoBacDC(id,id_cha) { 
    var TTTT="<%=qthttw%>";
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
    var kb_huyen="<%=idxKB%>";   
    var strTinh="<%=qthttw%>";
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
                         // getTenNHang(id);
                        }
                    }else if(strTinh!=null && strTinh !=''){
                       if(document.getElementById('nhkb_huyen').options.length==2){ // select dong thu 2 neu select box co 2 value voi user cap tinh
                            jQuery("#nhkb_huyen option:eq(1)").attr('selected', true);
                          //getTenNHang(id);
                       }
                      else if(kb_huyen=='0'||kb_huyen==null||''==kb_huyen){
                      jQuery('#ngan_hang option:eq(0)').attr('selected', true);
                          //getTenNHang(id);
                      }
                      else if(kb_huyen!='0'){
                      jQuery('#nhkb_huyen option:eq('+kb_huyen+')').attr('selected', true);
                         // getTenNHang(id);
                      }
                    }
                }
            }
              if (id!=null && ''!=id ){                        
                  jQuery('#nhkb_huyen').val(id);
                  
              }
        },
        error : function (textstatus) {
            alert(textstatus);
        }
    });
    }
}

//Clear cac option o combobox
function removeOptions(selectboxName, vitri)
{
    var selectbox = document.getElementById(selectboxName);
    var i;
    for(i = selectbox.options.length - 1 ; i >= vitri ; i--)
    {
        selectbox.remove(i);
    }
}

//lay ten ngan hang
  function getTenNHang() {
      //document.getElementById('ngan_hang').options.length = 1;// clear du lieu option cu
     removeOptions('ngan_hang', 1);
      var nhkb_id = document.getElementById("nhkb_huyen").value;//document.forms[0].nhkb_huyen.value;" +
       var strTinh="<%=qthttw%>";
       var ngan_hang="<%=idxNH%>";

      if (nhkb_id !=null && ""!=nhkb_id){
      jQuery.ajax( {
          type : "POST", url : "getDMucNHangHuyenDC.do", data :  {
              "nhkb_id" : nhkb_id
          },
          success : function (data, textstatus) {
              if (textstatus != null && textstatus == 'success') {
                  if (data != null) {
                      jQuery.each(data, function (i, objectDM) {
                          document.getElementById('ngan_hang').options.add(new Option(objectDM.ten, objectDM.ma_nh))

                      });
                      }
                     if( strTinh==null || strTinh ==''){  // request set dftinh ==null
                          if(document.getElementById('ngan_hang').options.length==2){
                            jQuery("#ngan_hang option:eq(0)").remove();
                            //getTK_NH_KB()
                          }
                      }else if(strTinh!=null && strTinh !=''){
                         if(document.getElementById('ngan_hang').options.length==2){ // select dong thu 2 neu select box co 2 value voi user cap tinh
                              jQuery("#ngan_hang option:eq(1)").attr('selected', true);
                            //  getTK_NH_KB();
//                              jQuery("#ngan_hang").attr("disabled", "disabled");
                            }
                        else if(ngan_hang=='0'||ngan_hang==null||''==ngan_hang){
                        jQuery('#ngan_hang option:eq(0)').attr('selected', true);
                       // getTK_NH_KB()
                        }
                        else if(ngan_hang!='0'){
                        jQuery('#ngan_hang option:eq('+ngan_hang+')').attr('selected', true);
                       // getTK_NH_KB()
                        }
                      }
                  }   
                   if(ngan_hang != null && ngan_hang != '')
                  jQuery('#ngan_hang option:eq('+ngan_hang+')').attr('selected', true);
          },
          error : function (textstatus) {
              alert(textstatus);
          }
      });
      }
  }
  
  //Function cho button
  function check(type) { 
   var f = document.forms[0];
     if (type == 'find') {
        if(!validForm())
          return false;
        var idxKB = jQuery('#nhkb_huyen option:selected').index();
        var idxNH = jQuery('#ngan_hang option:selected').index() ;
        f.pageNumber.value = 1;
        document.getElementsByName('idxKB')[0].value = idxKB;
        document.getElementsByName('idxNH')[0].value = idxNH;
        //thuongdt-20170713 kiem tra tra cuu ket qua tong hop doi chieu phai nho hon hoac bang ngay hien tai
         // muc dich de tong hop ket qua tra cuu co so lieu chenh lech theo ngay doi chieu trong qua khu begin         
        var vngaydc = document.getElementsByName('ngayDC')[0].value; 
        var date =   new Date();
        var vngaydcTemp = vngaydc.split('/');       
        var date2= new Date(vngaydcTemp[2],(Number(vngaydcTemp[1]) -1),vngaydcTemp[0]); 
        if(date2>date)
        {
          alert('Ngày đối chiếu nhỏ hơn hoặc bằng ngày hiện tại.');
          return false;
        }  
        //thuongdt-20170713 kiem tra tra cuu ket qua tong hop doi chieu phai nho hon hoac bang ngay hien tai
        // muc dich de tong hop ket qua tra cuu co so lieu chenh lech theo ngay doi chieu trong qua khu end
        f.target='';
        f.action = 'viewTheodoiDChieu3.do'; 
     }
     if (type == 'print') {
        if(!validForm())
          return false;
         
         //thuongdt-20170713 kiem tra tra cuu ket qua tong hop doi chieu phai nho hon hoac bang ngay hien tai
         // muc dich de tong hop ket qua tra cuu co so lieu chenh lech theo ngay doi chieu trong qua khu begin         
        var vngaydc = document.getElementsByName('ngayDC')[0].value; 
        var date =   new Date();
        var vngaydcTemp = vngaydc.split('/');       
        var date2= new Date(vngaydcTemp[2],(Number(vngaydcTemp[1]) -1),vngaydcTemp[0]); 
        if(date2>date)
        {
          alert('Ngày đối chiếu nhỏ hơn hoặc bằng ngày hiện tại.');
          return false;
        }  
        //thuongdt-20170713 kiem tra tra cuu ket qua tong hop doi chieu phai nho hon hoac bang ngay hien tai
        // muc dich de tong hop ket qua tra cuu co so lieu chenh lech theo ngay doi chieu trong qua khu end
          
        f.action = 'printTheodoiDChieu3.do';
        var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
        newDialog = window.open('', '_form', params);  
        f.target='_form';
     }
     if (type == 'close') {
        f.action = 'mainAction.do';      
     } 
     f.submit();
  }
  
  //Check valid cua form
  function validForm(){
    var nhkb_tinh=jQuery("#nhkb_tinh").val();
    var nhkb_huyen=jQuery("#nhkb_huyen").val();
    var ngan_hang=jQuery("#ngan_hang").val();
    var ngayDC=jQuery("#ngayDC").val();
    
    
     if(ngayDC == null || ngayDC == ""){
      alert('Chưa nhập ngày đối chiếu.');
      ngayDC.focus();
      return false;
    }
    return true;
  }
  
  //Di den trang
  function goPage(page) {
  var f = document.forms[0];
      f.pageNumber.value = page;
      var idxKB = jQuery('#nhkb_huyen option:selected').index();
        var idxNH = jQuery('#ngan_hang option:selected').index() ;
         document.getElementsByName('idxKB')[0].value = idxKB;
        document.getElementsByName('idxNH')[0].value = idxNH;      
      f.action = 'viewTheodoiDChieu3.do';
      f.submit();
  }
  </script>