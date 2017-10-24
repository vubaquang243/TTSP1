<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/includes/ttsp_header.inc"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>

<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>

<%@ page import="java.util.Collection"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="com.seatech.ttsp.chucnang.ChucNangVO;"%>

<fmt:setBundle basename="com.seatech.ttsp.resource.PhanQuyenResource"/>
<script type="text/javascript">
  // Popup window code  
  jQuery.noConflict();
  function AddOrRemoveFun(tree_id, type){  
    arrFun = new Array();
    treeFun = document.getElementById(tree_id);    
    arrCheck = treeFun!=null?treeFun.getElementsByTagName("INPUT"):new Array();
    counter = 0;
    var frm = document.forms[0];
    for(i=0; i<arrCheck.length; ++i){
      if(arrCheck[i].checked){
        arrFun[counter] = arrCheck[i].value;
        counter ++;
      }
    }
    if(counter == 0){
      alert('Bạn chưa chọn chức năng nào.');
      return;
    }
    frm.arrAddFun.value = arrFun;
    frm.actionPQ.value = type;
    frm.action = "phanQuyenExcAction.do";
    frm.submit();
  }
  function phanquyenSubmit(type) {
    var frm = document.forms[0];    
    if( type == 'tracuu'){   
      if(frm.nhom_id.value.trim() == '' || frm.nhom_id.value.trim() == 'null'){
        alert('Bạn phải chọn nhóm NSD.');
        document.getElementById('nhom_id_id').fucus();
        return;
      }
    }else if( type == 'thoat'){
      frm.action = "mainAction.do";
    }
    frm.submit();
  }

</script> 
    <title>Phan quyen su dung chuc nang</title>
    <!--	<script type="text/javascript">

        var _gaq = _gaq || [];
        _gaq.push(['_setAccount', 'UA-10814140-5']);
        _gaq.push(['_trackPageview']);

        (function() {
            var ga = document.createElement('script');
            ga.type = 'text/javascript';
            ga.async = true;
            ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
            var s = document.getElementsByTagName('script')[0];
            s.parentNode.insertBefore(ga, s);
        })();

    </script>-->
    <!-- styles for demo purpose -->
    <style type="text/css">
        body {
            font-family: verdana, arial;
            font-size: 0.8em;
        }

        code {
            white-space: pre;
        }
    </style>

    <!-- start checkboxTree configuration -->
    <script type="text/javascript" src="pages/user/phanquyen/library/jquery-1.4.4.js"></script>
    <script type="text/javascript" src="pages/user/phanquyen/library/jquery-ui-1.8.12.custom/js/jquery-ui-1.8.12.custom.min.js"></script>
    <link rel="stylesheet" type="text/css"
          href="pages/user/phanquyen/library/jquery-ui-1.8.12.custom/css/smoothness/jquery-ui-1.8.12.custom.css"/>

    <script type="text/javascript" src="pages/user/phanquyen/jquery.checkboxtree.js"></script>
    <link rel="stylesheet" type="text/css" href="pages/user/phanquyen/jquery.checkboxtree.css"/>
    <!-- end checkboxTree configuration -->

    <script type="text/javascript" src="pages/user/phanquyen/library/jquery.cookie.js"></script>
    <script type="text/javascript">
        //<!--
        $(document).ready(function() {
            $('#tabs').tabs({
                cookie: { expires: 30 }
            });
            $('.jquery').each(function() {
                eval($(this).html());
            });
            $('.button').button();
        });
        //-->
    </script>
  
    <div style="visibility:hidden; display:none" class="jquery" lang="text/javascript">     
        $('#tree1').checkboxTree();
    </div>
    <div style="visibility:hidden; display:none" class="jquery" lang="text/javascript"> 
        $('#tree2').checkboxTree();
    </div>

<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="/phanQuyenAction.do" method="post">
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
            <span class="title" height="24"
                  background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/bg_Title.jpg"
                  colspan="6">
              <font color="#006699 " size="2">
                <fmt:message key="phanquyen.title"/>
              </font></span>
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
            <span>&nbsp;&nbsp;&nbsp;&nbsp;<font color="Gray">
                <fmt:message key="phanquyen.title.find"/>
              </font>
            </span>
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
              <td width="30%" align="right" bordercolor="#e1e1e1">
                <fmt:message key="phanquyen.label.nhom"/>
              </td>
              <td width="80%" align="left" bordercolor="#e1e1e1">
                 <html:select property="nhom_id" styleId="nhom_id_id" onkeydown="if(event.keyCode==13) event.keyCode=9;" styleclass="promptText" style="width:300px;">
                  <html:option value="">Lựa chọn</html:option>  
                  <logic:notEmpty name="colNhomNSD">                              
                    <html:optionsCollection name="colNhomNSD" value="id" label="ten_nhom"/>
                  </logic:notEmpty>
                </html:select>
              </td>              
            </tr>
            <tr>
              <td width="30%" align="right" bordercolor="#e1e1e1">
                <fmt:message key="phanquyen.label.cnang"/>
              </td>
              <td width="70%" align="left" bordercolor="#e1e1e1">
                 <html:select property="cnang_id" styleId="cnang_id_id" onkeydown="if(event.keyCode==13) event.keyCode=9;" styleclass="promptText" style="width:300px;">
                  <html:option value="">Lựa chọn</html:option>  
                  <logic:notEmpty name="colCNangCha">                              
                    <html:optionsCollection name="colCNangCha" value="ky_hieu_cnang" label="ten_cnang"/>
                  </logic:notEmpty>
                </html:select>
              </td>
            </tr>
            <tr>
              <td align="center"  bordercolor="#e1e1e1" colspan="2">                
                <br/>
                <button type="button" onclick="phanquyenSubmit('tracuu')"
                  class="ButtonCommon" style="width:70" accesskey="t">
                  <span class="sortKey">T</span>ra c&#7913;u
                </button>
              </td>
            </tr>
          </table>
          <br/>
        </td>
      </tr>
    </table>
    <table style="BORDER-COLLAPSE: collapse" cellspacing="0"
           bordercolor="#999999" width="100%">
     
      <tr>      
        <td style="text-align:center;" nowrap="nowrap" width="40%" align="center">
        <br/>
          <!-- DS NSD khong thuoc nhom-->
          <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="400px">
            <tr>
              <td class="title" colspan="6"
                  background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
                  height="24" align="left">
                <font color="Gray">
                  <fmt:message key="phanquyen.title.ds_chucnang_khong_quyen"/>
                </font>
              </td>
            </tr>
          <tr>
          <td align="center">          
            <div style="width:auto; height: 500px; overflow: auto;" align="left">           
                <ul id="tree1">
<%
if(request.getAttribute("colCnangKoQuyen") != null){
  Collection colCnangKoQuyen = (Collection)request.getAttribute("colCnangKoQuyen");
  ChucNangVO cnangvo = null;
  Iterator iterCNang =  colCnangKoQuyen.iterator();
  int nCap1= 0; 
  int nCap2 = 0;
  int nCap3 = 0;
  int nCap4 = 0;
  int nMaCN = 0;
  
  while (iterCNang.hasNext()) {
    cnangvo = (ChucNangVO)iterCNang.next();
    nMaCN = Integer.parseInt(cnangvo.getMa_cnang());
    //Xu ly menu cap 1
    if (nMaCN % 10000 == 0) {      
      //Check xem co phai chuc nang dau tien cua menu ko?
      if (nCap2 > 0) {
      %>
        </ul>
      <%
      }
      if (nCap3 > 0) {
      %>
        </ul>
      <%
      }
      if (nCap4 > 0) {
      %>
        </ul>
      <%
      }
      %>
        <li><input type="checkbox" value="<%=cnangvo.getId()%>"><label><%=cnangvo.getMa_cnang()%>-<%=cnangvo.getTen_cnang()%></label>
      <%
      nCap1++;
      nCap2 = 0;
      nCap3 = 0;
      nCap4 = 0;
      //Xu ly menu cap 2
    }else if (nMaCN % 100 == 0) {      
      //Kiem tra co phai menu cap 2  dau tien hay ko?
      if(nCap3 > 0){
      %>
          </ul>
      <%
      }
      if (nCap4 > 0) {
      %>
        </ul>
      <%
      }
      if(nCap2 == 0){
%>        
        <ul>
<%
      }
%> 
          <li><input type="checkbox" value="<%=cnangvo.getId()%>"><label><%=cnangvo.getMa_cnang()%>-<%=cnangvo.getTen_cnang()%></label>
<%    
      nCap2++;
      nCap3 = 0;
      nCap4 = 0;
    }else if (nMaCN % 10 == 0) {
      if(nCap4 > 0){
      %>
          </ul>
      <%
      } 
      if(nCap3 == 0){
%>
        <ul>
<%
      }
%>     
          <li><input type="checkbox" value="<%=cnangvo.getId()%>"><label><%=cnangvo.getMa_cnang()%>-<%=cnangvo.getTen_cnang()%></label>
<%
      nCap3++;
      nCap4 = 0;
    }else{
      if(nCap4 == 0)
      {
%>
        <ul>
<%
      }
%>        
          <li><input type="checkbox" value="<%=cnangvo.getId()%>"><label><%=cnangvo.getMa_cnang()%>-<%=cnangvo.getTen_cnang()%></label>
      
<%
      nCap4++;
    }
  }
%>

        </ul>
<%}%>

          
        </div>
      </td>
    </tr>
  </table>
          <!--Danh sach nsd ngoai nhom-->
          
          
        </td>
        <td width="20%" align="center">
          <table border="0" cellpadding="5" cellspacing="0">
            <tr>
              <td style="text-align:center; width:20px"><input type="button" value=">>" onclick="AddOrRemoveFun('tree1','add')"/></input> </td>
            </tr>
            <tr>
              <td style="text-align:center; width:20px"><input type="button" value="<<" onclick="AddOrRemoveFun('tree2','remove')"/></td>
            </tr>
          </table>
        </td>
        <td width="40%">
        <!--Danh sach nsd thuoc nhom-->
        <br/>
        <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="400px">
          <tr>
          <td class="title" colspan="6"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="24" align="left">
            <font color="Gray">
              <fmt:message key="phanquyen.title.ds_chucnang_co_quyen"/>
            </font>
          </td>
          </tr>
          <tr>
          <td align="center">          
           <div style="width:auto; height: 500px; overflow: auto;" align="left">
           
                <ul id="tree2">

<%
if(request.getAttribute("colCnangCoQuyen") != null){
  Collection colCnangCoQuyen = (Collection)request.getAttribute("colCnangCoQuyen");
  ChucNangVO cnangvo = null;
  Iterator iterCNang =  colCnangCoQuyen.iterator();
  int nCap1= 0; 
  int nCap2 = 0;
  int nCap3 = 0;
  int nCap4 = 0;
  int nMaCN = 0;
  
  while (iterCNang.hasNext()) {
    cnangvo = (ChucNangVO)iterCNang.next();
    nMaCN = Integer.parseInt(cnangvo.getMa_cnang());
    //Xu ly menu cap 1
    if (nMaCN % 10000 == 0) {      
      //Check xem co phai chuc nang dau tien cua menu ko?
      if (nCap2 > 0) {
      %>
        </ul>
      <%
      }
      if (nCap3 > 0) {
      %>
        </ul>
      <%
      }
      if (nCap4 > 0) {
      %>
        </ul>
      <%
      }
      %>
        <li><input type="checkbox" value="<%=cnangvo.getId()%>"><label><%=cnangvo.getMa_cnang()%>-<%=cnangvo.getTen_cnang()%></label>
      <%
      nCap1++;
      nCap2 = 0;
      nCap3 = 0;
      nCap4 = 0;
      //Xu ly menu cap 2
    }else if (nMaCN % 100 == 0) {      
      //Kiem tra co phai menu cap 2  dau tien hay ko?
      if(nCap3 > 0){
      %>
          </ul>
      <%
      } 
      if (nCap4 > 0) {
      %>
        </ul>
      <%
      }
      if(nCap2 == 0){
%>        
        <ul>
<%
      }
%> 
          <li><input type="checkbox" value="<%=cnangvo.getId()%>"><label><%=cnangvo.getMa_cnang()%>-<%=cnangvo.getTen_cnang()%></label>
<%    
      nCap2++;
      nCap3 = 0;
      nCap4 = 0;
    }else if (nMaCN % 10 == 0) {
      if(nCap4 > 0){
      %>
          </ul>
      <%
      } 
      if(nCap3 == 0){
%>
        <ul>
<%
      }
%>     
          <li><input type="checkbox" value="<%=cnangvo.getId()%>"><label><%=cnangvo.getMa_cnang()%>-<%=cnangvo.getTen_cnang()%></label>
<%
      nCap3++;
      nCap4 = 0;
    }else{
      if(nCap4 == 0)
      {
%>
        <ul>
<%
      }
%>        
          <li><input type="checkbox" value="<%=cnangvo.getId()%>"><label><%=cnangvo.getMa_cnang()%>-<%=cnangvo.getTen_cnang()%></label>
      
<%
      nCap4++;
    }
  }
%>

        </ul>
<%}%>
 
          </div>
          </td>
          </tr>
          </table>
        <!--Danh sach nsd thuoc nhom-->
        </td>
      </tr>   
    </table>
    <table class="buttonbar" align="center">
      <tr>
        <td>
        <span id="exit">      
          <button type="button" onclick="phanquyenSubmit('thoat')" class="ButtonCommon" style="width:50" accesskey="o">
            Th<span class="sortKey">o</span>&#225;t
          </button>
        </span>
        </td>
      </tr>
    </table>
  <input type="hidden" name="arrAddFun" id="addFun" />
  <input type="hidden" name="actionPQ" id="actionPQ" />
  </html:form>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>