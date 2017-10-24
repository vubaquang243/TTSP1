
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.KTVTabmisResource"/>
<%@ include file="/includes/ttsp_header.inc"%>
  <body>
  <br/>
  <br/>
  
   <script type="text/javascript" language="javascript"> 
   function formAction(type,loai_tt) { 
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

    <%if(request.getAttribute("loai_tt").equals("LTT")){%>
    <H1>ĐANG TRUYỀN LẠI <font color="Red"><%=request.getAttribute("lstID")!=null?request.getAttribute("lstID").toString().split(",").length:0%></font> LỆNH THANH TOÁN</H1>
  <%}else if(request.getAttribute("loai_tt").equals("LQT")){%> 
    <H1>ĐANG TRUYỀN LẠI <font color="Red"><%=request.getAttribute("lstID")!=null?request.getAttribute("lstID").toString().split(",").length:0%></font> LỆNH QUYẾT TOÁN</H1>
  <%}else if(request.getAttribute("loai_tt").equals("BKLQT")){%> 
    <H1>ĐANG TRUYỀN LẠI <font color="Red"><%=request.getAttribute("lstID")!=null?request.getAttribute("lstID").toString().split(",").length:0%></font> BẢNG KÊ LỆNH QUYẾT TOÁN</H1>
  <%}else if(request.getAttribute("loai_tt").equals("DTS")){%>
    <H1>ĐANG TRUYỀN LẠI <font color="Red"><%=request.getAttribute("lstID")!=null?request.getAttribute("lstID").toString().split(",").length:0%></font> ĐIỆN TRA SOÁT</H1>

  <%}%>
    <i>(Chức năng này sử dụng khi cần truyền lại điện đi Ngân hàng hoặc TABMIS)</i>
  <br/>
  <br/>
  <html:form action="/resendLTTAction.do" styleId="frmTraCuuLTT">
  
  <table>
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
      <button id="OK" accesskey="i" type="button"
                        class="ButtonCommon"  value="btn_update_status" onclick="formAction('resend')">SEND</button>
    </td>
    </tr>
    </table>
  </html:form>
   <br/>
  <br/>
  <br/>
  <br/>
 </body>
<%@ include file="/includes/ttsp_bottom.inc"%>
