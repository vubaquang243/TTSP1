<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ include file="/includes/ttsp_header.inc"%>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<link rel="stylesheet" type="text/css" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet" type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet" type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet" type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/tabber.css"/>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/tabber.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/doichieu.js"></script>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js" type="text/javascript"></script>
<script type="text/javascript" charset="utf-8" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery.jec-1.3.2.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/lov.js"></script>

<fmt:setBundle basename="com.seatech.ttsp.resource.LTTDiResource" />
<fmt:setBundle basename="com.seatech.ttsp.resource.DoichieuResource"/>

<%
  String qthttw = request.getAttribute("QTHTTW")==null?"":request.getAttribute("QTHTTW").toString();
  String idxKB = request.getAttribute("idxKB")==null?"":request.getAttribute("idxKB").toString();
  String idxNH = request.getAttribute("idxNH")==null?"":request.getAttribute("idxNH").toString();
  String idxTK = request.getAttribute("idxTK")==null?"":request.getAttribute("idxTK").toString();
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
  
  $(document).on("pageload",function(){
    alert("pageload event fired!");
  });
</script>

<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="BKeTinhLaiNgoaiTeAction.do" method="post" >
    <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
      <tbody>
        <tr>
          <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
            <span class=title2> Bảng kê tính lãi ngoại tệ </span>
          </td>
          <td width=62><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg" width=62 height=30/></td>
          <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg" width="20%">&nbsp;</td>
          <td width=4><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg" width=4 height=30/></td>
        </tr>
      </tbody>
    </table>
    <table style="BORDER-COLLAPSE: collapse" border="0" cellspacing="0" class="tableBound" bordercolor="#999999" width="100%">
      <tr>
        <td>
          <fieldset>
            <legend><font color="blue">Điều kiện tìm bảng kê</font></legend>
            <div>
            <table class="data-grid" id="data-grid" style="width:100%" border="0" cellspacing="0" cellpadding="1" >
              <tr>
                <td width="15%" align="right" bordercolor="#e1e1e1"><fmt:message key="doi_chieu.page.label.tracuu.kbtinh"/>&nbsp;</td>
                <td width="30%">
                  <html:select property="nhkb_tinh" styleId="nhkb_tinh" style="font-size:12px;width:100%"  onchange="getTenKhoBacDC('','')" onkeydown="if(event.keyCode==13) event.keyCode=9;"> 
                    <logic:notEmpty name="QTHTTW">
                      <html:option value="">---<fmt:message key="doi_chieu.page.label.tracuu.default"/>---</html:option>
                    </logic:notEmpty>
                    <html:optionsCollection  name="dmuckb_tinh" value="id_cha" label="kb_tinh"/>                    
                  </html:select>
                </td>
                <td width="15%" align="right" bordercolor="#e1e1e1">
                  <fmt:message key="doi_chieu.page.label.tracuu.kbhuyen"/>&nbsp;
                </td>
                <td width="30%">
                  <html:select property="receiveBank" styleId="receiveBank" style="font-size:12px;width:100%" onchange="getTenNHang();"
                                onkeydown="if(event.keyCode==13) event.keyCode=9;">               
                      <html:option value="">---<fmt:message key="doi_chieu.page.label.tracuu.default"/>---</html:option>                             
                  </html:select>
                </td>
                <td align="center" rowspan="3" width="15%">
                  <logic:notEmpty name="QTHTTW">
                    <button type="button" onclick="callLov()" class="ButtonCommon" accesskey="k" >
                      Danh mục <span class="sortKey">K</span>B
                    </button>
                  </logic:notEmpty>
                </td>
              </tr>
              <tr>
                <td align="right" bordercolor="#e1e1e1">Ngân hàng &nbsp;</td>
                <td>
                  <html:select property="sendBank" styleId="sendBank" style="font-size:12px;width:100%" onchange=""
                               onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                    <html:option value="">---<fmt:message key="doi_chieu.page.label.tracuu.default"/>---</html:option>  
                  </html:select>
                </td>
                <!--<td align="right" bordercolor="#e1e1e1">Số TK &nbsp;</td>
                <td>
                  <html:select property="soTk" styleId="soTk" style="font-size:12px;width:100%" onchange=""
                               onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                    <html:option value="">---<fmt:message key="doi_chieu.page.label.tracuu.default"/>---</html:option>
                  </html:select>                          
                </td>-->
                <td align="right" bordercolor="#e1e1e1">Mã ngoại tệ</td>
                <td>
                  <html:select property="maNTGoc" styleId="maNTGoc" style="font-size:12px;width:41%" 
                               onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                    <html:option value="">---Chọn---</html:option>  
                    <html:optionsCollection  name="dmTienTe" value="ma" label="ma"/>
                  </html:select>
                </td>
              </tr>
              <tr>
                <td align="right">Phát sinh từ ngày &nbsp;</td>
                <td>
                  <html:text property="tuNgay" styleId="tuNgay" styleClass="fieldText" 
                             onkeypress="return numbersonly(this,event,true) "
                             onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('tuNgay');"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width:26%"
                             tabindex="107" />
                  <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="tuNgay_btn" style="vertical-align:middle;width:20"/>   
                  <script type="text/javascript">
                      Calendar.setup( {
                          inputField : "tuNgay", // id of the input field
                          ifFormat : "%d/%m/%Y", // the date format
                          button : "tuNgay_btn"// id of the button
                      });
                  </script> &nbsp;&nbsp;&nbsp;
                </td>
                <td align="right">Đến ngày phát sinh &nbsp;</td>
                <td>
                  <html:text property="denNgay" styleId="denNgay" styleClass="fieldText" 
                        onkeypress="return numbersonly(this,event,true) "
                        onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('denNgay');"
                        onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width:26%"
                        tabindex="107" />
                  <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="denNgay_btn"
                     style="vertical-align:middle;width:13"/>   
                  <script type="text/javascript">
                      Calendar.setup( {
                          inputField : "denNgay", // id of the input field
                          ifFormat : "%d/%m/%Y", // the date format
                          button : "denNgay_btn"// id of the button
                      });
                  </script>
                </td>  
              </tr>
              <!--<tr>
                <td align="right" bordercolor="#e1e1e1">Loại tiền gốc</td>
                <td>
                  <html:select property="maNTGoc" styleId="maNTGoc" style="font-size:12px;width:41%" 
                               onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                    <html:option value="">---Chọn---</html:option>  
                    <html:optionsCollection  name="dmTienTe" value="ma" label="ma"/>
                  </html:select>
                </td>
              </tr>-->
              <tr>
                <td colspan="5" align="center">
                  <button type="button" onclick="check('find');" class="ButtonCommon" accesskey="t" >
                    <span class="sortKey">T</span>ra cứu
                  </button>
                </td>
              </tr>
            </table>
            </div>
          </fieldset>
        </td>
      </tr>
      <% com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
         int rowBegin = (pagingBean.getCurrentPage() -1) * 15; %>
      <tr>
        <td>
          <fieldset>
            <legend><font color="Blue">Bảng kê tương ứng</font></legend>
            <fmt:setLocale value="vi_VI"/>
            <table class="data-grid" id="data-grid" border="1" cellspacing="0" cellpadding="0" width="100%">
              <tr>
                <th width="4%">STT</th>
                <th width="11%">Số bảng kê</th>
                <th width="11%">Số MSG</th>
                <th width="16%">Tên KB</th>
                <th width="16%">Tên NH</th>
                <th width="8%">Ngày nhận</th>
                <th width="10%">Số tài khoản</th>
                <th width="8%">Ký lãi</th>
                <th width="8%">Ngoại tệ tính</th>
                <th width="8%">In</th>
              </tr>
              <logic:notEmpty name="listLaiDetail">
                <logic:iterate id="item" name="listLaiDetail" indexId="stt">
                  <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                    <td align="center"><%=stt+1+rowBegin%></td>
                    <td align="center">
                      <bean:write name="item" property="mt_id" />
                    </td>
                    <td align="center">
                      <bean:write name="item" property="msg_id" />
                    </td>
                    <td align="center">
                      <bean:write name="item" property="ten_kb" />
                    </td>
                    <td align="center">
                      <bean:write name="item" property="ten_nh" />
                    </td>
                    <td align="center">
                      <bean:write name="item" property="insert_date" />
                    </td>
                    <td align="center">
                      <bean:write name="item" property="so_tk" />
                    </td>
                    <td align="center">
                      <bean:write name="item" property="ky_lai" />
                    </td>
                    <td align="center">
                      <bean:write name="item" property="ma_nt_goc" />
                    </td>
                    <td align="center">
                      <a href="printBKeTinhLaiNgoaiTe.do?mt_id=<bean:write name="item" property="mt_id" />" target="_blank" >In</a>
                    </td>
                  </tr>
                </logic:iterate>
              </logic:notEmpty>
            </table>
          </fieldset>
        </td>
      </tr>
    </table>
    <html:hidden property="pageNumber" styleId="pageNumber"/>
  </html:form>
</div>
<div id="dialog-form-lov-dm" title="Tra c&#7913;u danh m&#7909;c Kho b&#7841;c">
  <p class="validateTips"></p>
  <%@include file="/pages/lov/lovDMKBTCUU.jsp" %>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
  
  function callLov(){      
    jQuery("#loai_lov").val("DMKBTCUU");
    jQuery("#ma_field_id_lov").val("ma_nhkb_nhan");
    jQuery("#ten_field_id_lov").val("ten_nhkb_nhan");
    jQuery("#id_field_id_lov").val("id_receiveBank");
    jQuery("#id_cha_field_id_lov").val("id_nhkb_tinh");
    jQuery("#dialog-form-lov-dm").dialog( "open" );    
  }
  
  function getTenKhoBacDC(id,id_cha) { 
    var qthttw = "<%=qthttw%>";
    var kb_huyen = "<%=idxKB%>";
    var kb_id;
    document.getElementById('receiveBank').options.length = 1;// clear du lieu option cu
    //document.getElementById('soTk').options.length = 1;
    //Lay kb_id
    if(qthttw == null || '' == qthttw){
      kb_id = document.forms[0].nhkb_tinh.value;
    } else {
      if(id == null || '' == id){       
        if (id_cha == null || '' == id_cha){
          kb_id=document.forms[0].nhkb_tinh.value;
        }else {               
          kb_id = id_cha;
          jQuery('#nhkb_tinh').val(id_cha);
        }
      }else {
        if (id_cha == null || '' == id_cha){
          kb_id = document.forms[0].nhkb_tinh.value;
        }else {                
          kb_id = id_cha;
          jQuery('#nhkb_tinh').val(id_cha);
        }
      }
    }
    //**
    if (kb_id != null && "" != kb_id){
      jQuery.ajax({
        type : "POST", 
        url : "getDMucKBTHop.do", 
        data :{
          "kb_id" : kb_id
        },
        success : function (data, textstatus) {
          if (textstatus != null && textstatus == 'success') {
            if (data != null) {
              jQuery.each(data, function (i, objectDM) {
              // truong hop 1 - luc load khong co thang nao                  
                document.getElementById('receiveBank').options.add(new Option(objectDM.kb_huyen, objectDM.id));
              });
              if( qthttw == null || qthttw == ''){  // request set dftinh ==null
                if(document.getElementById('receiveBank').options.length == 2){
                  jQuery("#receiveBank option:eq(0)").remove();
                  getTenNHang();
                }else if(kb_huyen == '0' || kb_huyen == null || '' == kb_huyen){
                  jQuery('#sendBank option:eq(0)').attr('selected', true);
                  getTenNHang();
                }else if(kb_huyen != '0'){
                  jQuery('#receiveBank option:eq('+kb_huyen+')').attr('selected', true);
                  getTenNHang();
                }
              }else{
                if(document.getElementById('receiveBank').options.length==2){ // select dong thu 2 neu select box co 2 value voi user cap tinh
                  jQuery("#receiveBank option:eq(1)").attr('selected', true);
                  getTenNHang(); 
                }else if(kb_huyen=='0'||kb_huyen==null||''==kb_huyen){
                  jQuery('#sendBank option:eq(0)').attr('selected', true);
                  getTenNHang();
                }else if(kb_huyen!='0'){
                  jQuery('#receiveBank option:eq('+kb_huyen+')').attr('selected', true);
                  getTenNHang();
                }
              }
            }
          }
        },
        error : function (textstatus) {
          alert(textstatus);
        }
      });
    }
  }

  function getTenNHang() {
    document.getElementById('sendBank').options.length = 1;// clear du lieu option cu
    //document.getElementById('soTk').options.length = 1;
    var nhkb_id = document.getElementById("receiveBank").value;//document.forms[0].receiveBank.value;" +
    var strTinh = "<%=qthttw%>";
    var sendBank = "<%=idxNH%>";

    if (nhkb_id != null && "" != nhkb_id){
      jQuery.ajax({
        type: "POST", 
        url : "getDMucNHangHuyen.do", 
        data:{
          "nhkb_id" : nhkb_id
        },
        success : function (data, textstatus) {
          if(textstatus != null && textstatus == 'success') {
            if (data != null) {
              jQuery.each(data, function (i, objectDM) {
                document.getElementById('sendBank').options.add(new Option(objectDM.ten, objectDM.ma_nh))
              });
            }
            if(document.getElementById('sendBank').options.length == 2){
                jQuery("#sendBank option:eq(0)").remove();
                //getTK_NH_KB()
            }else if(document.getElementById('sendBank').options.length > 2){
                              
            }
          }          
        },
        error : function (textstatus) {
          alert(textstatus);
        }
      });
    }
  }

  function getTK_NH_KB() {
    document.getElementById('soTk').options.length = 1;// clear du lieu option cu
    var nh_id = document.getElementById("sendBank").value;//document.forms[0].receiveBank.value;" +
    var nhkb_id = document.getElementById("receiveBank").value;
    var soTk="<%=idxTK%>";
    if (nh_id !=null && ""!=nh_id){
      jQuery.ajax( {
        type : "POST", 
        url : "getTKNHKB.do", 
        data :  {
          "nh_id" : nh_id,"nhkb_id" : nhkb_id,"typeDisplay" : "ONLY_VND"
        },
        success : function (data, textstatus) {
          if (textstatus != null && textstatus == 'success') {
            if (data != null) {
              jQuery.each(data, function (i, objectDM) {
                document.getElementById('soTk').options.add(new Option(objectDM.so_tk, objectDM.so_tk))
              });
            }
            if(document.getElementById('soTk').options.length >=2 ){ // select dong thu 2 neu select box co 2 value voi user cap tinh
              jQuery("#soTk option:eq(1)").attr('selected', true);
            }else if(soTk=='0'||soTk==null||''==soTk){
              jQuery('#soTk option:eq(0)').attr('selected', true);
            }
            if(soTk!='0'){
              jQuery("#soTk").val(soTk);
            }
          }          
        },
        error : function (textstatus) {
          alert(textstatus);
        }
      });
    }
  }
  
  function check(type) { 
    var f = document.forms[0];
    f.submit();
  }
</script>