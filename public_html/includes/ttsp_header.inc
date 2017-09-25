<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.*"%>
<%@page  import="com.seatech.framework.AppConstants"%>
<%@page  import="com.seatech.ttsp.chucnang.ChucNangVO"%>
<%@page  import="com.seatech.ttsp.tintuc.TinTucVO"%>
<%@page  import="com.seatech.framework.utils.TTSPUtils"%>
<%@page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<HTML>
  <HEAD>
    <META http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
    <META content="text/html; charset=UTF-8" http-equiv="Content-Type"></META>
    <LINK rel="stylesheet" type="text/css" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/oracle.css" media="screen"/>
    <LINK rel="stylesheet" type="text/css" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/blue.css"/>
    <link rel="stylesheet" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/menu/css3menu1/style.css" type="text/css" /><style>._css3m{display:none}</style>
    <META content="no-cache" http-equiv="Pragma"></META>
    <META content="-1" http-equiv="Expires"></META>
    <META name="GENERATOR" content="MSHTML 8.00.7600.16821"></META>
    <SCRIPT type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/prototype.js"></SCRIPT>
    <SCRIPT type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery.htm"></SCRIPT>
    <SCRIPT type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/common.htm"></SCRIPT>
    <script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calendar.js"></script>
    <script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calendar-setup.js"></script>	
    <script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calendar-vi.js"></script>
    <SCRIPT type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/stm31.htm"></SCRIPT>
    <SCRIPT type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/tcs.htm"></SCRIPT>
</HEAD>
<BODY>

<%
  if(request.getSession().getAttribute(AppConstants.APP_USER_ID_SESSION) == null){
    response.sendRedirect(request.getContextPath()+"/loginAction.do");
  }
  String tin_tuc = request.getAttribute("tin_tuc")==null?"":request.getAttribute("tin_tuc").toString();
%>

<SCRIPT type="text/javascript">
  var strChkKy="N";
  function reLog()
	{
    
		if(showConfirm("SYS-0011")==1){
			var sFeatures = 'resizable=yes,status=yes,toolbar=no,menubar=no,scrollbars=yes,width=800px,height=600px,top=0,left=0';
			var newWindow = window.open("/reLog.do?reLog=true","_parent",sFeatures);	
		}
	}
	/**
	  *  Log ra trang màn hình chính
	  */
	function closeApp(){
		if (confirm("Bạn có thực sự muốn thoát ra khỏi hệ thống?"))
		{
			var sFeatures = 'resizable=yes,status=yes,toolbar=no,menubar=no,scrollbars=yes,width=800px,height=600px,top=0,left=0';
			var newWindow = window.open("/logout.do?reLog=false","_parent",sFeatures);	
		}
	}
	/**
	  *  Thoát hẳn ra kh�?i ứng dụng
	  */
	function exitApp(){
		if (confirm("Bạn có chắc chắn muốn thoát khỏi hệ thống?"))
		{
			var sFeatures = 'resizable=yes,status=yes,toolbar=no,menubar=no,scrollbars=yes,width=800px,height=600px,top=0,left=0';
			var newWindow = window.open("/logout.do","_parent",sFeatures);	
		}
	}
</SCRIPT>

<TABLE border=0 cellSpacing=0 cellPadding=0 width=985 align=center>
  <TBODY>
  <TR>
    <TD height=70><a href="loginAction.do">
      <IMG src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/banner_kbnn.png" width=985 
            height=70></a>
    </TD> 
  </TR>

  <TR>
    <TD>    
    <ul id="css3menu1" class="topmenu" style="width:100%">
<%
  Collection colMenu = (Collection)request.getSession().getAttribute(AppConstants.APP_MENU_LIST_SESSION);
  ChucNangVO cnvo = null;
  Iterator iter = colMenu.iterator();
  int nCap1Counter = 0;
  int nCap2Counter = 0;
  int nCap3Counter = 0;
  int nMaCNang = 0;
  boolean nFlagStart = true;
  boolean nFlagStart2 = true;  
  String strActionName = "#";
  String strModuleName = "";
  
  while (iter.hasNext()) {
    strActionName = "#";
    cnvo = (ChucNangVO)iter.next();
    nMaCNang = Integer.parseInt(cnvo.getMa_cnang());
    if(cnvo.getTen_action() != null && !"".equals(cnvo.getTen_action()) && !"#".equals(cnvo.getTen_action())){
      strActionName = cnvo.getTen_action().trim();
      if(strActionName.lastIndexOf(".do") == -1){
        strActionName = strActionName + ".do";
      }
    }
    //Xu ly menu cap 1
    if (nMaCNang % 10000 == 0) {           
      if (nCap1Counter == 0) {
        %>
        <li class="topfirst"><a href="<%=strActionName%>" style="height:15px;line-height:15px;"><span><%=cnvo.getTen_cnang()%></span></a>
        <%
      }else{      
          if(nCap3Counter > 0){
          %> 
          </ul></li>
          <%}else{
%>
          </li>
<%
          }
          if(nCap2Counter > 0){
          %>
          </ul></li>          
          <%
          }  
          if(nCap2Counter == 0){%>
          </li>
          <%}%>
        <li class="topmenu"><a href="<%=strActionName%>" style="height:15px;line-height:15px;"><span><%=cnvo.getTen_cnang()%></span></a>
        <%
      }
      nCap1Counter++;
      nCap2Counter = 0;
      nCap3Counter = 0;
      nFlagStart = true;
      //Xu ly menu cap 2
    }else if (nMaCNang % 100 == 0) {
      strModuleName = ((nCap2Counter+1)>=10?(nCap2Counter+1):"&nbsp;" + (nCap2Counter+1)) + "." + cnvo.getTen_cnang();
      if(strActionName.lastIndexOf("#") > -1){
        strModuleName = "<span>" + strModuleName + "</span>";
      }
      //Kiem tra co phai menu cap 2  dau tien hay ko?
      if(nFlagStart && nCap1Counter >= 1)
      {
%>       
        <ul>
          <li class="subfirst"><a href="<%=strActionName%>"><%=strModuleName%></a>
<%
        nFlagStart = false;      
      }else if(!nFlagStart){
        if(nCap3Counter > 0){
%>
          </ul></li>
<%
        }else{
%>
          </li>
<%
        }
%>
        <li><a href="<%=strActionName%>"><%=strModuleName%></a>
<%
      }    
      nCap2Counter++;
      nCap3Counter = 0;
      nFlagStart2 = true;
    }else if (nMaCNang % 10 == 0) {           
      if(nFlagStart2)
      {
        nFlagStart2 = false;
%>
       <ul> <li class="subfirst"><a href="<%=strActionName%>"><%=nCap3Counter+1%>. <%=cnvo.getTen_cnang()%></a></li>  
<%
      }else{
%>      
        <li><a href="<%=strActionName%>"><%=nCap3Counter+1%>. <%=cnvo.getTen_cnang()%></a></li>
<%
      }
      nCap3Counter++;
    }   
  }  
  if(nCap3Counter > 0){
%> 
          </ul></li>
<%
  }else{
%>
          </li>
<%
  }
  if(nCap2Counter > 0){
%>
          </ul></li>          
<%
 }  
 if(nCap2Counter == 0){
%>
          </li>
<%
  }
%>
  </ul>
  
  </TD>
  </TR>
    
      <% if (!"".equals(tin_tuc.trim())&& tin_tuc !=null){%>
      <tr>
        <td>
          <marquee behavior="scroll" scrollamount="2" style="font-size:14px" direction="left" onmouseover="stop()" onmouseout="start()"><span><%=tin_tuc%> </span></marquee>
        </td>
      </tr>
    <%}%>
  </TBODY>
</TABLE>
    
    <TABLE style="border-left: 1px #cdebe8 solid; border-right: 1px #cdebe8 solid; border-bottom: 1px #cdebe8 solid"  cellSpacing=0 cellPadding=0 width=985 
 align=center>
 

  <TBODY>
  <TR>
    <TD>
      <TABLE border=0 cellSpacing=0 cellPadding=0 width="100%">
        <TBODY>
        <TR>
          <TD width=16><IMG src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/navi1.jpg" width=16 
            height=25></TD>
          <TD class=title2 background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/navi2.jpg" 
          width="42%"  height=25><%=request.getSession().getAttribute("ten_kb")%> : <%=request.getSession().getAttribute("ma_kb")%> </TD>
          <TD width=50><IMG src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/navi3.jpg" width=46 
            height=25></TD>
          <TD background=<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/navi22.jpg width="25%">
            <SPAN class=textlink3>Ng&#224;y l&#224;m vi&#7879;c:             
              <%=com.seatech.framework.utils.StringUtil.getCurrentDate()%>
            </SPAN>
            <%="".equals(request.getAttribute("gio_cot_menu"))?"":"<font color='Red'>COT: "+request.getAttribute("gio_cot_menu")+"</font>"%>
          <TD background=<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/navi22.jpg width="32%"><SPAN 
            class=textlink3>Ng&#432;&#7901;i s&#7917; d&#7909;ng: <%=request.getSession().getAttribute("ten_nsd")%></SPAN> <a href="logoutAction.do" style="height:15px;line-height:15px;"><SPAN style="float:right; padding-right:5px">Tho&#225;t<SPAN></a></TD>                      
        <TR>
          <TD width=16></TD>
          <TD width="42%"></TD>
          <TD width=50></TD>
          <TD width="25%"><SPAN class=textlink3><!-- Chế độ làm việc với AD: </SPAN><FONT color="red"><SPAN class="textlink5">Online --></SPAN></FONT></TD></TR></TBODY></TABLE><BR>
      <TABLE border=0 cellSpacing=0 cellPadding=0 width="99.5%">
        <TBODY>
        <TR>
          <TD width=35><IMG src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/B1.jpg" width=35 
            height=15></TD>
          <TD background=<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/B2.jpg></TD>
          <TD width=12><IMG src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/B3.jpg" width=12 
            height=15></TD></TR>
        <TR>
          <TD background=<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/B4.jpg>&nbsp;</TD>
          <TD>
            
            <SCRIPT type="text/javascript" 
            src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/TcsLapChungtu.htm"></SCRIPT>

            <SCRIPT type="text/javascript" 
            src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/wz_tooltip.htm"></SCRIPT>

            <SCRIPT type="text/javascript" 
            src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/pupdate.htm"></SCRIPT>

            <SCRIPT type="text/javascript" 
            src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/MessageResource.htm"></SCRIPT>

            

            <SCRIPT type="text/javascript" 
            src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery.table_navigation.htm"></SCRIPT>

            <SCRIPT type="text/javascript" 
            src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery.number_format.htm"></SCRIPT>

            <SCRIPT type="text/javascript" 
            src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/actb.htm"></SCRIPT>

            

            <SCRIPT type="text/javascript" 
            src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/json.htm"></SCRIPT>

            <SCRIPT type="text/javascript" 
            src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/webtoolkit.utf8.htm"></SCRIPT>
            

            <SCRIPT type="text/javascript">
        jQuery.tableNavigation({
            select_event: 'mouseover',
            selected_class: 'TR_Selected'
        });
</SCRIPT>

<SCRIPT> 
	window.history.forward(1); 
	//document.attachEvent("onkeydown", my_onkeydown_handler_refresh); 
  
</SCRIPT>


<script language="JavaScript">

  window.onbeforeunload=function(){
    var x = window.event.clientX;
    var y = window.event.clientY;
  
    if (window.event.clientY < 0 && (window.event.clientX > (document.documentElement.clientWidth - 5) || window.event.clientX < 15) ) {
      if (confirm("Ban co chac chan muon thoat khoi he thong?"))
      {
        window.open('main.jsp?action=logoutAction.do', '', 'fullscreen=yes, scrollbars=auto');     
      }else{      //parent.location       
        window.open('main.jsp?action='+parent.location, '', 'fullscreen=yes, scrollbars=auto');        
      }
    }
  }
</script>