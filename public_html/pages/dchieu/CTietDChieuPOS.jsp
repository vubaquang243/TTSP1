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
 //String kb_huyen = request.getAttribute("kb_huyen")==null?"":request.getAttribute("kb_huyen").toString();
//  String ngan_hang = request.getAttribute("ngan_hang")==null?"":request.getAttribute("ngan_hang").toString();
  String qthttw = request.getAttribute("QTHTTW")==null?"":request.getAttribute("QTHTTW").toString();
  String idxKB = request.getAttribute("idxKB")==null?"":request.getAttribute("idxKB").toString();
  String idxNH = request.getAttribute("idxNH")==null?"":request.getAttribute("idxNH").toString();
  String idxTT = request.getAttribute("idxTT")==null?"":request.getAttribute("idxTT").toString();
  //String sum_lai = request.getAttribute("sum_lai")==null?"":request.getAttribute("sum_lai").toString();
  //String sum_sdu = request.getAttribute("sum_sdu")==null?"":request.getAttribute("sum_sdu").toString();
%>

<div class="app_error">
  <html:errors/>
</div>


<div class="box_common_conten"> 
  <html:form action="tcuuDChieuPOS.do" method="post" >
  
 <fieldset>
          <legend><font color="Blue">Kết quả tra cứu</font></legend>
          <logic:empty name="kqvo">
          <div style="text-align:center"><h1>Không tìm thấy kết quả phù hợp</h1></div>
          </logic:empty>
          
          <logic:notEmpty name="kqvo">
          <br/>
           <div style="padding-left: 10px"><b> Kết quả đối chiếu: 
           <logic:equal name ="kqvo" property="trang_thai" value="01">
              <span  style="color:red;FONT-WEIGHT: bold"  id = "divTT">Chênh lệch</span>
           </logic:equal>
            <logic:equal name ="kqvo" property="trang_thai" value="02">
              <span  style="color:blue;FONT-WEIGHT: bold"  id = "divTT">Khớp đúng</span>
           </logic:equal>
            <logic:equal name ="kqvo" property="trang_thai" value="03">
              <span  style="color:red;FONT-WEIGHT: bold"  id = "divTT">Đã hủy</span>
           </logic:equal>
           <logic:equal name ="kqvo" property="trang_thai" value="04">
              <span  style="color:blue;FONT-WEIGHT: bold"  id = "divTT">Chênh lệch - Đã xác nhận</span>
           </logic:equal>
            <logic:equal name ="kqvo" property="trang_thai" value="00">
              <span  style="color:red;FONT-WEIGHT: bold"  id = "divTT">Chưa đối chiếu</span>
           </logic:equal>
            <logic:equal name ="kqvo" property="trang_thai" value="">
              <span  style="color:red;FONT-WEIGHT: bold"  id = "divTT">Chưa đối chiếu</span>
           </logic:equal>
           
           </b></div>
           <br/>
          <div align="center">
            <table  class="data-grid" id="data-grid" 
                                                border="1"
                                               cellspacing="0" cellpadding="0"                                  
                                               width="100%">

                 
                   <tr>
                    <th ></th>
                    <th >Tổng số giao dịch thu POS</th>
                    <th >Tổng số tiền</th>
                   </tr>
                    <tr>
                    <td align="left" style="padding-left:50px">Thu POS từ TCS</td>
                    <td align="right" style="padding-right:10px"><bean:write name="kqvo" property="so_lenh_pos"/></td>
                    <td align="right" style="padding-right:10px"><bean:write name="kqvo" property="tong_pos"/></td>
                   </tr>
                    <tr>
                    <td align="left" style="padding-left:50px">Lệnh báo có thu POS từ NHTM</td>
                    <td align="right" style="padding-right:10px"><bean:write name="kqvo" property="so_lenh_qt"/></td>
                    <td align="right" style="padding-right:10px"><bean:write name="kqvo" property="tong_qtoan"/></td>
                   </tr>
                   <tr>
                    <td align="left" style="padding-left:50px">Chênh lệch</td>
                    <td align="right" style="padding-right:10px"><bean:write name="kqvo" property="so_lenh_lech"/></td>
                    <td align="right" style="padding-right:10px"><bean:write name="kqvo" property="tong_lech"/></td>
                   </tr>                  
              </table>
              
               </div>
              
              
              <br/>
        
              
              <logic:present name="kqctiet">
        <logic:notEmpty name="kqctiet">     
          <fieldset>
          <legend><font color="Blue">Chi tiết chênh lệch</font></legend>
           
            <table  class="data-grid" id="data-grid" 
                                                border="1"
                                               cellspacing="0" cellpadding="0"                                  
                                               width="100%">

                  <tr>
                    <th width="3%">
                      STT
                    </th>
                    <th width="30%">
                     Kho bạc 
                    </th>
                    <th width="10%">
                      Ngân hàng
                    </th>
                    <th width="13%">
                     Ngày đối chiếu
                    </th>                    
                    <th width="10%">
                      Thu POS từ TCS
                    </th> 
                    <th width="10%">
                      Thu POS từ NH
                    </th> 
                    <th width="10%">
                      Số tham chiếu
                    </th> 
                  </tr>
                 
                   
                        <logic:iterate id="items" name="kqctiet" indexId="stt">
                         <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                            <td align="center" > 
                              <%=stt+1%>
                            </td>
                            <td align="center">
                              <bean:write name="items" property="ma_kb"/>
                            </td>                            
                            <td align="center">
                              <bean:write name="items" property="ma_nh"/>
                            </td>
                            <td align="center">
                              <bean:write name="items" property="ngay_dc"/>
                            </td>                           
                           
                           <td align="right" style="padding-right:5px">
                              <bean:write name="items" property="tong_pos"/>                              
                            </td> 
                            <td align="right" style="padding-right:5px">
                              <bean:write name="items" property="tong_qtoan"/>
                            </td> 
                            <td align="left" style="padding-left:5px">
                              <bean:write name="items" property="so_tham_chieu"/>
                            </td> 
                         </tr>
                        </logic:iterate>
                       
                                  
              </table>
           </fieldset>
    </logic:notEmpty>
  </logic:present>
              
              
              
              
              
              
              
             
               <br/>
               <div>Lý do xác nhận chênh lệch:  <html:text style = "width:900px" property="lydo"  styleId="lydo"/></div>
              <br/>
              <div align="right" style="padding-right: 5px">
                <%
                String roll = (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
                if(roll.indexOf("TTV")>=0){
                %>             
              <logic:notEqual name ="kqvo" property="trang_thai" value="01">
                <button type="button" onclick="check('xacnhan');" id= 'btnXacNhan' class="ButtonCommon" accesskey="x" disabled="disabled" > 
                          <span class="sortKey">X</span>ác nhận
               </button>              
              </logic:notEqual>
               <logic:equal name ="kqvo" property="trang_thai" value="01">
                 <button type="button" onclick="check('xacnhan');" id= 'btnXacNhan' class="ButtonCommon" accesskey="x">
                            <span class="sortKey">X</span>ác nhận
                 </button>
              </logic:equal>
               <logic:equal name ="kqvo" property="trang_thai" value="00">
                  <button type="button" onclick="check('doichieu');" id= 'btnDoiChieu' class="ButtonCommon" accesskey="x" >
                        <span class="sortKey">Đ</span>ối chiếu
                  </button>
              </logic:equal>
              <%}%> 
              <!-- <logic:equal name ="kqvo" property="trang_thai" value="01">
                <button type="button" onclick="check('doichieu');" id= 'btnDoiChieu' class="ButtonCommon" accesskey="x" >
                        <span class="sortKey">Đ</span>ối chiếu
                  </button>
              </logic:equal>   -->              
            
             <button type="button" onclick="check('thoat');" class="ButtonCommon" accesskey="x" >
                        <span class="sortKey">T</span>hoát
             </button>
            </div>
               </logic:notEmpty>
           </fieldset>
         
          <input type="hidden" name ="bkid" value="<bean:write name="kqvo" property="bk_id"/>"/>
           <input type="hidden" name ="kqid" value="<bean:write name="kqvo" property="id"/>"/>
           <html:hidden property="err_code" styleId = "err_code"/>
           <html:hidden property="err_desc" styleId = "err_desc"/>
            <html:hidden property="idxKB" styleId = "idxKB"/>
           <html:hidden property="idxNH" styleId = "idxNH"/>
            <html:hidden property="ngayDC" styleId = "ngayDC"/>            
           <html:hidden property="pageNumber" styleId = "pageNumber"/>           
           <html:hidden property="nhkb_tinh" styleId = "nhkb_tinh"/>
           <html:hidden property="nhkb_huyen" styleId = "nhkb_huyen"/>           
           <html:hidden property="ngan_hang" styleId = "ngan_hang"/>
           <html:hidden property="trangThaiDC" styleId = "trangThaiDC"/>
           <html:hidden property="loaiTien" styleId = "loaiTien"/>
    </html:form>
    
</div>

<%@ include file="/includes/ttsp_bottom.inc"%>

<script type="text/javascript">

  checkKetQua();
  function checkKetQua(){
    var verr_code = document.getElementsByName("err_code")[0];
    var verr_desc = document.getElementsByName("err_desc")[0];
    if(verr_code.value != null && verr_code.value != ''){    
      alert(verr_desc.value);
      verr_code.value = '';
      verr_desc.value = '';
    }
  }
 function check(type){
  var f = document.forms[0];
  
  var vkqid = document.getElementsByName("kqid")[0].value;
   if(type =='xacnhan'){ 
   var lydo = document.getElementById('lydo');  
    
      if(lydo.value != null && lydo.value != ''){
       $.ajax({
            type: "POST",
            url: "capNhatDChieuPOS.do",
            data: "ajkqid=" + vkqid +"&ajlydo=" + lydo.value,          
            success: function(DBR){
                var ketqua =strTrim(DBR);
                if(ketqua ==''){
                    alert('Cập nhật không thành công.');  
                }else{
                var str = "Chênh lệch - Xác nhận đúng";
                var result = str.fontcolor("blue");
                   document.getElementsByName('btnDoiChieu')[0].disabled = 'disabled';
                   document.getElementsByName('btnXacNhan')[0].disabled = 'disabled';
                   document.getElementsByName('divTT')[0].innerHTML = result;
                    alert('Cập nhật thành công.');  
                }
            },
            error:function (errorMsg) {
              alert('Lỗi khi truy vấn thông tin');
            }
        });
      }else{
        alert('Chưa nhập lý do xác nhận');
        lydo.focus();
      }
     
   }else if(type =='doichieu'){
      f.action = 'dchieuLaiPOS.do'; 
      f.submit();
   }else if(type =='thoat'){
      f.action = 'viewDChieuPOS.do'; 
      f.submit();
   }
 }
function strTrim(str){
    return str.replace(/^\s+|\s+$/g,''); 
  }
</script>