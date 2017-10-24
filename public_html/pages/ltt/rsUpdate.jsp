<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.KTVTabmisResource"/>
<%@ include file="/includes/ttsp_header.inc"%>
  <body>
  <br/>
  <br/>
  <br/>
  
  <html:form action="/resendLTTAction.do" styleId="frmTraCuuLTT">
  
  <%if(request.getAttribute("loai_tt").equals("LTT_DI") || request.getAttribute("loai_tt").equals("LTT_DEN")){%>
  <H1>ĐANG CẬP NHẬT TRẠNG THÁI <font color="Red"><%=request.getAttribute("lstID")!=null?request.getAttribute("lstID").toString().split(",").length:0%></font> LỆNH THANH TOÁN</H1>
  <i>(Chức năng này dùng khi cần thay đổi trạng thái LTT do trạng thái trong CSDL không phản ánh đúng với thực tế truyền tin)</i>
  <%}else if(request.getAttribute("loai_tt").equals("LQT")){%> 
  <H1>ĐANG CẬP NHẬT TRẠNG THÁI <font color="Red"><%=request.getAttribute("lstID")!=null?request.getAttribute("lstID").toString().split(",").length:0%></font> LỆNH QUYẾT TOÁN</H1>
  <i>(Chức năng này dùng khi cần thay đổi trạng thái LQT do trạng thái trong CSDL không phản ánh đúng với thực tế truyền tin)</i>
  <%}else if(request.getAttribute("loai_tt").equals("DTS")){%>
  <H1>ĐANG CẬP NHẬT TRẠNG THÁI <font color="Red"><%=request.getAttribute("lstID")!=null?request.getAttribute("lstID").toString().split(",").length:0%></font> ĐIỆN TRA SOÁT</H1>
  <i>(Chức năng này dùng khi cần thay đổi trạng thái DTS do trạng thái trong CSDL không phản ánh đúng với thực tế truyền tin)</i>
  <%}%>
  <br/>
  <br/>
  <br/>
  <br/>  
  <table>
  <tr>
  <td>
  Trang thai can cap nhat
  </td>
  <td>
    <html:select onkeydown="if(event.keyCode==13) event.keyCode=9;"
                      tabindex="102"
                       styleClass="fieldText" style="width:145px;height:20px;"
                       property="trang_thai" styleId="trang_thai" >
            <html:option value="">
              --Chọn trạng thái cần cập nhật--
            </html:option>
            <% if(request.getAttribute("loai_tt").equals("LTT_DI")){%>
            <html:option value="14">
              Đã gửi NH thành công
            </html:option>            
            <html:option value="02">
              Chuyển TTV xử lý
            </html:option>
            <%}else if (request.getAttribute("loai_tt").equals("LTT_DEN")){%>
            <html:option value="12">
              Giao diện thành công
            </html:option>
            <html:option value="13">
              Giao diện không thành công
            </html:option>
            <%}else if (request.getAttribute("loai_tt").equals("LQT")){%>
            <html:option value="13">
              Giao diện không thành công
            </html:option>
            <html:option value="12">
              Giao diện thành công
            </html:option>
            <%}else if (request.getAttribute("loai_tt").equals("DTS")){%>
            <html:option value="04">
              Hủy
            </html:option>
            <html:option value="18">
              Gửi NH thành công
            </html:option>
            <%}%>
          </html:select>
    </td>
    </tr>
    <tr>
    <td>user</td>
    <td>
    <html:text styleId="user"
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"
                     styleClass="fieldText" style="width:150px;" maxlength="100"                     
                     tabindex="101"
                     property="user"/>
    </td>
    </tr>
    <tr>
    <td>pass</td>
    <td>
    <html:password styleId="pass"
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"
                     styleClass="fieldText" style="width:150px;" maxlength="100"                     
                     tabindex="101"
                     property="pass"/>
    </td>
    </tr>
    <tr>
    <td></td>
    <td>
      <button id="OK" accesskey="i" type="button" class="ButtonCommon"  value="btn_update_status" onclick="formAction('update')">UPDATE</button>
    </td>
    </tr>
    </table>
  </html:form>
   <br/>
  <br/>
  <br/>
  <br/>
 </body>
 <script type="text/javascript" language="javascript"> 
   function formAction(type) { 
        //Kiem tra chon trang thai can update
        var trang_thai = document.getElementById('trang_thai').value;
        if( trang_thai == '' || trang_thai == null){
          alert('Can chon trang thai de update');
          return;
        }
        
        var lstId = '<%=request.getAttribute("lstID")!=null?request.getAttribute("lstID").toString():""%>'; 
        if(lstId!=""){
          var f = document.forms[0];          
          var  loai_tt = '<%=request.getAttribute("loai_tt")%>';
          f.action = "resendLTTExcAction.do?type="+type+"&lstId="+lstId+"&loai_tt="+loai_tt;
          f.submit();
        }else{
          alert(GetUnicode('B&#7841;n ph&#7843;i ch&#7885;n &#237;t nh&#7845;t 1 l&#7879;nh thanh to&#225;n!'));
          return false;
        }  
    }
  </script>
  
<%@ include file="/includes/ttsp_bottom.inc"%>
