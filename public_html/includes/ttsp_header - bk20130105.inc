
<%@page import="java.util.*"%>
<%@page  import="com.seatech.framework.AppConstants"%>
<%@page  import="com.seatech.ttsp.chucnang.ChucNangVO"%>
<%@page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>

<HTML>
  <HEAD>
    <META content="text/html; charset=UTF-8" http-equiv="Content-Type"></META>
    <LINK rel="stylesheet" type="text/css" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/oracle.css" media="screen"/>
    <LINK rel="stylesheet" type="text/css" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/blue.css"/>
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
%>

<SCRIPT type="text/javascript">
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
		if (confirm("Bạn có thực sự muốn thoát ra kh�?i hệ thống?"))
		{
			var sFeatures = 'resizable=yes,status=yes,toolbar=no,menubar=no,scrollbars=yes,width=800px,height=600px,top=0,left=0';
			var newWindow = window.open("/logout.do?reLog=false","_parent",sFeatures);	
		}
	}
	/**
	  *  Thoát hẳn ra kh�?i ứng dụng
	  */
	function exitApp(){
		if (confirm("B?n c� ch?c ch?n mu?n tho�t kh?i h? th?ng?"))
		{
			var sFeatures = 'resizable=yes,status=yes,toolbar=no,menubar=no,scrollbars=yes,width=800px,height=600px,top=0,left=0';
			var newWindow = window.open("/logout.do","_parent",sFeatures);	
		}
	}
</SCRIPT>

<TABLE border=0 cellSpacing=0 cellPadding=0 width=985 align=center>
  <TBODY>
  <TR>
    <TD height=70 background=<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/banner1.jpg>&nbsp; </TD></TR>
  <TR>
    <TD height=26 background=<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_menu.jpg>
      <SCRIPT type="text/javascript">    
  stm_bm(["menu142b",430,"","<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/blank.gif",0,"","",0,0,0,0,50,1,1,1,"","",0],this);
	stm_bp("p0",[0,4,0,0,0,5,7,7,100,"",-2,"",-2,90,2,1,"#333333","transparent","",3,0,0,"#FFFFFF"]);






<%
  Collection colMenu = (Collection)request.getSession().getAttribute(AppConstants.APP_MENU_LIST_SESSION);
  ChucNangVO cnvo = null;
  Iterator iter = colMenu.iterator();
  int nCap1Counter = 0;
  int nCap2Counter = 0;
  int nCap3Counter = 0;
  int nMaCNang = 0;
  boolean nFlagStm_bpx = true;
  boolean nFlagStm_bpx2 = true;  
  
  while (iter.hasNext()) {
    cnvo = (ChucNangVO)iter.next();
    nMaCNang = Integer.parseInt(cnvo.getMa_cnang());
    //Xu ly menu cap 1
    if (nMaCNang % 10000 == 0) {      
      //Check xem co phai chuc nang dau tien cua menu ko?
      if (nCap1Counter == 0) {
        %>
        stm_ai("p0i0",[0,"<b> <%=cnvo.getTen_cnang()%> </b>","","",-1,-1,0,"#","_self","","","","",0,0,0,"styles/images/icon1.gif","styles/images/icon2.gif",7,7,0,0,1,"#FFFFFF",1,"#FFFFFF",1,"","styles/images/bg1.gif",3,3,0,0,"#FFFFFF","#FFFFFF","#FFFFFF","#000000","10pt Tahoma","10pt Tahoma",0,0]);  
        <%
      }else{        
        if(nCap2Counter > 0){
          %>
          stm_ep();
          <%
          }if(nCap3Counter > 0){
          %>
          stm_ep();
          <%
          }
          %>
          
        stm_aix("p0i<%=nCap1Counter%>","p0i0",[0,"<b><%=cnvo.getTen_cnang()%></b>","","",-1,-1,0,""]);               
        <%
      }
      nCap1Counter++;
      nCap2Counter = 0;
      nCap3Counter = 0;
      nFlagStm_bpx = true;
      //Xu ly menu cap 2
    }else if (nMaCNang % 100 == 0) {      
      //Kiem tra co phai menu cap 2  dau tien hay ko?
      if(nFlagStm_bpx && nCap1Counter > 1)
      {
%>
        stm_bpx("p<%=nCap1Counter%>","p<%=nCap1Counter-1%>",[1,4,0,1]);
<%
        nFlagStm_bpx = false;
      //Kiem tra co phai menu cap 2  dau tien hay ko?
      }else if(nFlagStm_bpx && nCap1Counter == 1) {
%>
        stm_bpx("p1","p0",[1,4,0,0,0,3,0,7,100,"progid:DXImageTransform.Microsoft.Wipe(GradientSize=1.0,wipeStyle=1,motion=forward,enabled=0,Duration=0.00)",5,"progid:DXImageTransform.Microsoft.Wipe(GradientSize=1.0,wipeStyle=1,motion=reverse,enabled=0,Duration=0.00)",4,50]);	
<%
        nFlagStm_bpx = false;
      }
      //Neu Action = null -> la menu cap2 cha
      if(cnvo.getTen_action() == null || "".equals(cnvo.getTen_action())){
      //Check neu la menu cap 2 dau tien
        if(nCap3Counter > 0){
%>
          stm_ep();
<%          
        }
%>
        stm_aix("p<%=nCap1Counter%>i<%=nCap2Counter%>","p0i0",[0,"<%=nCap2Counter+1%>. <%=cnvo.getTen_cnang()%>","","",-1,-1,0,"#",
                 "_self","","","","",0,0,0,"styles/images/icon1.gif","styles/images/icon2.gif",7,7,0,0,1,bgcolor1,0,bgcolor2,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor1,fontcolor2]);
<%      
      }else{
        if(nCap3Counter > 0){
%>
          stm_ep();
<%
        }
%>        
        stm_aix("p<%=nCap1Counter%>i<%=nCap2Counter%>","p0i0",[0,"<%=nCap2Counter+1%>. <%=cnvo.getTen_cnang()%>","","",-1,-1,0,"<%=cnvo.getTen_action()%>",
                "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor1,0,bgcolor2,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor1,fontcolor1]);        
<%        
      }
      nCap2Counter++;
      nCap3Counter = 0;
      nFlagStm_bpx2 = true;
    }else if (nMaCNang % 10 == 0) {
      if(nFlagStm_bpx2)
      {
        nFlagStm_bpx2 = false;
%>
        stm_bpx("p<%=nCap1Counter%>","p<%=nCap1Counter-1%>",[1,2,0,1,0,3,0,0]);     
<%
      }
      if(cnvo.getTen_action() == null || "".equals(cnvo.getTen_action())){      
%>
        stm_aix("p<%=nCap2Counter%>i<%=nCap3Counter%>","p1i0",[0,"<%=nCap3Counter+1%>. <%=cnvo.getTen_cnang()%>","","",-1,-1,0,"#",
                 "_self","","","","",0,0,0,"styles/images/icon1.gif","styles/images/icon2.gif",7,7,0,0,1,bgcolor1,0,bgcolor2,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor1,fontcolor2]);
        stm_bpx("p<%=nCap2Counter+1%>","p<%=nCap2Counter%>",[1,2,0,1,0,3,0,0]);   
<%
      }else{
%>
        stm_aix("p<%=nCap2Counter%>i<%=nCap3Counter%>","p1i0",[0,"<%=nCap3Counter+1%>. <%=cnvo.getTen_cnang()%>","","",-1,-1,0,"<%=cnvo.getTen_action()%>",
                "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor1,0,bgcolor2,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor1,fontcolor1]);
<%
      }
      nCap3Counter++;
    }   
  }  
%>
  stm_em();


 </SCRIPT>
    </TD></TR></TBODY></TABLE>
    
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
          <TD class=title2 background=<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/navi2.jpg 
          width="42%"><%=request.getSession().getAttribute("ten_kb")%> : <%=request.getSession().getAttribute("ma_kb")%> </TD>
          <TD width=50><IMG src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/navi3.jpg" width=46 
            height=25></TD>
          <TD background=<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/navi22.jpg width="25%">
            <SPAN class=textlink3>Ng&#224;y l&#224;m vi&#7879;c: 
              <script type="text/javascript">
              var currentTime = new Date()
              var month = currentTime.getMonth() + 1
              var day = currentTime.getDate()
              var year = currentTime.getFullYear()
              document.write(day + "/" + month + "/" + year)
              </script>
            </SPAN> 
          <TD background=<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/navi22.jpg width="32%"><SPAN 
            class=textlink3>Ng&#432;&#7901;i s&#7917; d&#7909;ng: <%=request.getSession().getAttribute("ten_nsd")%></SPAN> </TD>            
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

