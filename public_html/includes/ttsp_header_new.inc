
<%@page import="java.util.*"%>
<%@page  import="com.seatech.framework.AppConstants"%>
<%@page  import="com.seatech.ttsp.chucnang.ChucNangVO"%>
<%@page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>

<HTML><HEAD>
<META content="text/html; charset=UTF-8" http-equiv="Content-Type"></META>
<LINK rel="stylesheet" type=text/css href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/oracle.css" media="screen">
<LINK rel="stylesheet" type="text/css" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css" media="screen">
<META content="no-cache" http-equiv="Pragma">
<META content="-1" http-equiv="Expires">
<META name="GENERATOR" content="MSHTML 8.00.7600.16821">

<link rel="stylesheet" type="text/css" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/blue.css"/>
<SCRIPT type=text/javascript 
  src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery.htm"></SCRIPT>

<SCRIPT type=text/javascript 
            src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/common.htm"></SCRIPT>
<script type="text/javascript"
	src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calendar.js"></script>
<script type="text/javascript"
	src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calendar-setup.js"></script>	
<script type="text/javascript"
	src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calendar-vi.js"></script>

</HEAD>
<BODY class=body>

<SCRIPT language=JavaScript1.2 type=text/javascript 
src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/stm31.htm"></SCRIPT>

<SCRIPT language=JavaScript1.2 type=text/javascript 
src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/tcs.htm"></SCRIPT>

<%
  if(request.getSession().getAttribute(AppConstants.APP_USER_ID_SESSION) == null){
    response.sendRedirect(request.getContextPath()+"/login.do");
  }  
%>

<SCRIPT type=text/javascript>
  function reLog()
	{
		if (showConfirm("SYS-0011") == 1){
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
      <SCRIPT type=text/javascript>    
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
    if (nMaCNang % 1000 == 0) {      
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
          }
          %>
          
        stm_aix("p0i<%=nCap1Counter%>","p0i0",[0,"<b><%=cnvo.getTen_cnang()%></b>","","",-1,-1,0,""]);               
        <%
      }
      nCap1Counter++;
      nCap2Counter = 0;
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
    }else{
      
    }    
  }  
%>
  stm_em();
/*
//---------------------------------------------------------- HE THONG  -----------------------------------------------------------  
 
  stm_ai("p0i0",[0,"<b> H&#7879; th&#7889;ng </b>","","",-1,-1,0,"#","_self","","","","",0,0,0,"styles/images/icon1.gif","styles/images/icon2.gif",7,7,0,0,1,"#FFFFFF",1,"#FFFFFF",1,"","styles/images/bg1.gif",3,3,0,0,"#FFFFFF","#FFFFFF","#FFFFFF","#000000","10pt Tahoma","10pt Tahoma",0,0]);
  
	stm_bpx("p1","p0",[1,4,0,0,0,3,0,7,100,"progid:DXImageTransform.Microsoft.Wipe(GradientSize=1.0,wipeStyle=1,motion=forward,enabled=0,Duration=0.00)",5,"progid:DXImageTransform.Microsoft.Wipe(GradientSize=1.0,wipeStyle=1,motion=reverse,enabled=0,Duration=0.00)",4,50]);	
  
  stm_aix("p1i0","p0i0",[0,"1.Qu&#7843;n l&#253; ng&#432;&#7901;i s&#7917; d&#7909;ng             ","","",-1,-1,0,"javascript:notPrivilege();",
                "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor1,0,bgcolor2,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor1,fontcolor2]);		
                
  stm_aix("p1i1","p1i0",[0,"2.Qu&#7843;n l&#253; nh&#243;m ng&#432;&#7901;i s&#7917; d&#7909;ng","","",-1,-1,0,"#",
	            "_self","","","","",0,0,0,"styles/images/icon1.gif","styles/images/icon2.gif",7,7,0,0,1,bgcolor1,0,bgcolor2,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor1,fontcolor2]);
              
  stm_bpx("p2","p1",[1,2,0,1,0,3,0,0]);  
  stm_aix("p2i3","p1i0",[0,"2.1. Danh s&#225;ch nh&#243;m ng&#432;&#7901;i s&#7917; d&#7909;ng ","","",-1,-1,0,"logoutAction.do",
            "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor3,0,bgcolor3,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor3,fontcolor4]);
  stm_aix("p2i2","p1i0",[0,"2.2. Ph&#226;n nh&#243;m             ","","",-1,-1,0,"javascript:notPrivilege();",
                "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor3,0,bgcolor3,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor3,fontcolor4]);
    stm_aix("p2i2","p1i0",[0,"2.3. Ph&#226;n quy&#7873;n             ","","",-1,-1,0,"",
                "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor3,0,bgcolor3,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor3,fontcolor4]);
  	stm_ep();   
    stm_aix("p2i0","p1i0",[0,"3. Qu&#7843;n l&#253; ch&#7913;ng th&#432; s&#7889;                 ","","",-1,-1,0,"#?function_id=010400",
	            "_self","","","","",0,0,0,"styles/images/icon1.gif","styles/images/icon2.gif",7,7,0,0,1,bgcolor1,0,bgcolor2,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor1,fontcolor2]);
  	stm_bpx("p2","p1",[1,2,0,1,0,3,0,0]);  
  	stm_aix("p2i3","p1i0",[0,"3.1. &#272;&#259;ng k&#253; ch&#7913;ng th&#432; s&#7889;              ","","",-1,-1,0,"javascript:notPrivilege();",
            "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor3,0,bgcolor3,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor3,fontcolor4]);
  	stm_aix("p2i2","p1i0",[0,"3.2. Duy&#7879;t ch&#7913;ng th&#432; s&#7889;             ","","",-1,-1,0,"javascript:notPrivilege();",
                "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor3,0,bgcolor3,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor3,fontcolor4]);
  	stm_ep();
    stm_aix("p1i4","p1i0",[0,"4. Tham s&#7889; h&#7879; th&#7889;ng           ","","",-1,-1,0,"",
                "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor1,0,bgcolor2,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor1,fontcolor2]);		
    stm_aix("p1i5","p1i0",[0,"5. T&#224;i kho&#7843;n c&#7911;a kho b&#7841;c t&#7841;i ng&#226;n h&#224;ng      ","","",-1,-1,0,"",
                "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor1,0,bgcolor2,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor1,fontcolor2]);
    stm_aix("p1i6","p1i0",[0,"6. K&#7871; to&#225;n vi&#234;n tabmis      ","","",-1,-1,0,"KTVTabmisListAction.do",
                "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor1,0,bgcolor2,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor1,fontcolor2]);		                 
    stm_aix("p1i7","p1i0",[0,"7. Ph&#226;n quy&#7873;n nh&#7853;p l&#7879;nh th&#7911; c&#244;ng","","",-1,-1,0,"TTVCoQNhapListAction.do",
                "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor1,0,bgcolor2,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor1,fontcolor2]);		                 
		stm_aix("p1i8","p1i0",[0,"7. Tho&#225;t             ","","",-1,-1,0,"logoutAction.do",
				"_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor1,0,bgcolor2,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor1,fontcolor2]);
	stm_ep();
//---------------------------------------------------------- DANH MUC  -----------------------------------------------------------
	stm_aix("p0i1","p0i0",[0,"<b>Danh m&#7909;c</b>","","",-1,-1,0,"#?function_id=020000"]);
	stm_bpx("p3","p1",[1,4,0,1]);
	stm_aix("p3i2","p1i0",[0,"1. Danh m&#7909;c Chuong","","",-1,-1,0,"getAllMChuongAction.do?function_id=020100",
                "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor1,0,bgcolor2,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor1,fontcolor1]);
	stm_aix("p3i2","p1i0",[0,"2. Danh m&#7909;c N&#7897;i dung Kinh t&#7871;","","",-1,-1,0,"getAllMaNoiDungKTAction.do?function_id=020300",
                "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor1,0,bgcolor2,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor1,fontcolor1]);
	stm_aix("p3i3","p1i0",[0,"3. Danh m&#7909;c T&#224;i kho&#7843;n t&#7921; nhi&#234;n","","",-1,-1,0,"getAllTcsDmCqtAction.do?function_id=020400",
                "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor1,0,bgcolor2,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor1,fontcolor1]);
	
	stm_aix("p3i5","p1i0",[0,"4. Danh m&#7909;c &#208;&#7883;a b&#224;n h&#224;nh ch&#237;nh","","",-1,-1,0,"getAllDbanHchinhAction.do?function_id=020600",
                "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor1,0,bgcolor2,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor1,fontcolor1]);
	stm_aix("p3i6","p1i0",[0,"5. Danh m&#7909;c M&#227; qu&#7929;                   ","","",-1,-1,0,"getAllDmNguoiNopThueAction.do?function_id=020700",
                "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor1,0,bgcolor2,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor1,fontcolor1]);
	stm_aix("p3i8","p1i0",[0,"6. Danh m&#7909;c Quan h&#7879; ng&#226;n s&#225;ch","","",-1,-1,0,"getAllDmSoThueAction.do?function_id=021700",
                "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor1,0,bgcolor2,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor1,fontcolor1]);
	stm_aix("p3i8","p1i0",[0,"7. Danh m&#7909;c C&#7845;p ng&#226;n s&#225;ch","","",-1,-1,0,"getAllDmToKhaiAction.do?function_id=021600",
            "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor1,0,bgcolor2,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor1,fontcolor1]);
	stm_aix("p3i8","p1i0",[0,"8. Danh d&#7921; ph&#242;ng ","","",-1,-1,0,"getAllLHThuAction.do?function_id=021400",
                "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor1,0,bgcolor2,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor1,fontcolor1]);
    stm_aix("p3i8","p1i0",[0,"9. Danh m&#7909;c M&#227; kho b&#7841;c","","",-1,-1,0,"getAllCqqdAction.do?function_id=022000",
                "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor1,0,bgcolor2,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor1,fontcolor1]);
	stm_aix("p3i8","p1i0",[0,"10. Danh m&#7909;c m&#227; ng&#226;n h&#224;ng c&#7911;a kho b&#7841;c","","",-1,-1,0,"getAllDThuAction.do?function_id=021900",
                "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor1,0,bgcolor2,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor1,fontcolor1]);
	stm_aix("p3i8","p1i0",[0,"11. Danh m&#7909;c k&#7871;t h&#7907;p ch&#233;o","","",-1,-1,0,"getAllTcsDmLoaithueAction.do?function_id=021300",
            "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor1,0,bgcolor2,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor1,fontcolor1]);
	stm_aix("p3i7","p1i0",[0,"12. Danh m&#7909;c Ng&#224;nh kinh t&#7871;                  ","","",-1,-1,0,"getAllTaiKhoanAction.do?function_id=020800",
            "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor1,0,bgcolor2,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor1,fontcolor1]);
	stm_aix("p3i8","p1i0",[0,"13. Danh m&#7909;c Ch&#432;&#417;ng tr&#236;nh m&#7909;c ti&#234;u (KTKB)","","",-1,-1,0,"getAllTaiKhoanCTAction.do?function_id=022100",
            "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor1,0,bgcolor2,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor1,fontcolor1]);
	stm_aix("p3i8","p1i0",[0,"14. Danh m&#7909;c M&#227; ngu&#7891;n","","",-1,-1,0,"getAllMapTaiKhoanAction.do?function_id=022900",
            "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor1,0,bgcolor2,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor1,fontcolor1]);
	
	stm_ep();
  
//----------------------------------------------------------  LENH THANH TOAN  -----------------------------------------------------------
	stm_aix("p0i2","p0i1",[0,"<b>L&#7879;nh thanh to&#225;n</b>","","",-1,-1,0,"#?function_id=030000"]);
	stm_bpx("p5","p3",[]);
  stm_aix("p5i0","p1i0",[0,"1. X&#7917; l&#253; l&#7879;nh thanh to&#225;n &#273;i","","",-1,-1,0,"listLttAdd.do",
            "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor1,0,bgcolor2,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor1,fontcolor1]);	
	stm_aix("p5i1","p1i0",[0,"2. X&#7917; l&#253; l&#7879;nh thanh to&#225;n &#273;&#7871;n ","","",-1,-1,0,"InitTcsTraCuuChungTu.do?function_id=030300",
            "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor1,0,bgcolor2,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor1,fontcolor1]);	
  stm_aix("p5i2","p1i0",[0,"3. Tra c&#7913;u l&#7879;nh thanh to&#225;n","","",-1,-1,0,"TraCuuLTT.do",
            "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor1,0,bgcolor2,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor1,fontcolor1]);	
  stm_aix("p5i3","p1i0",[0,"4. Ch&#7885;n KTV tabmis","","",-1,-1,0,"KTVTabmisListSelectAction.do",
            "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor1,0,bgcolor2,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor1,fontcolor1]);	
	stm_ep();	
//---------------------------------------------------------- SU LY DIEN TRA SOAT  -----------------------------------------------------------
  stm_aix("p0i3","p0i1",[0,"<b>X&#7917; l&#253; &#273;i&#7879;n tra so&#225;t</b>","","",-1,-1,0,"dtsoatlttdiView.do"]);
  stm_bpx("p1","p0",[1,4,0,0,0,3,0,7,100,"progid:DXImageTransform.Microsoft.Wipe(GradientSize=1.0,wipeStyle=1,motion=forward,enabled=0,Duration=0.00)",5,"progid:DXImageTransform.Microsoft.Wipe(GradientSize=1.0,wipeStyle=1,motion=reverse,enabled=0,Duration=0.00)",4,50]);
	
  stm_aix("p9i0","p1i0",[0,"1. X&#7917; l&#253; &#273;i&#7879;n tra so&#225;t g&#7855;n LTT","","",-1,-1,0,"#",
                 "_self","","","","",0,0,0,"styles/images/icon1.gif","styles/images/icon2.gif",7,7,0,0,1,bgcolor1,0,bgcolor2,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor1,fontcolor2]);
    stm_bpx("p2","p1",[1,2,0,1,0,3,0,0]);  
  	stm_aix("p2i3","p1i0",[0,"1.1. X&#7917; l&#253; &#273;i&#7879;n tra so&#225;t &#273;i","","",-1,-1,0,"dtsoatlttdiView.do",
            "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor3,0,bgcolor3,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor3,fontcolor4]);
  	stm_aix("p2i2","p1i0",[0,"1.2. T&#7841;o &#272;TS g&#7855;n v&#7899;i LTT","","",-1,-1,0,"TraCuuLTT.do?targetid=dts",
                "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor3,0,bgcolor3,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor3,fontcolor4]);
	stm_ep();  
	stm_aix("p9i1","p1i0",[0,"2. X&#7917; l&#253; tr&#7843; l&#7901;i tra so&#225;t","","",-1,-1,0,"TcsTraCuuBLai.do?function_id=040100",
                "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor1,0,bgcolor2,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor1,fontcolor1]);
	stm_aix("p9i2","p1i0",[0,"3. X&#225;c nh&#7853;n tra so&#225;t tr&#7843; l&#7901;i","","",-1,-1,0,"TcsGomBL.do?function_id=040400",
                "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor1,0,bgcolor2,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor1,fontcolor1]);
  stm_aix("p9i3","p1i0",[0,"4. &#272;i&#7879;n tra so&#225;t n&#7897;i b&#7897;","","",-1,-1,0,"TcsGomBL.do?function_id=040400",
                "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor1,0,bgcolor2,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor1,fontcolor1]);
  stm_aix("p9i4","p1i0",[0,"5. &#272;i&#7879;n tra so&#225;t t&#7921; do","","",-1,-1,0,"XuLyDTSoatTuDo.do",
                "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor1,0,bgcolor2,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor1,fontcolor1]);
  stm_aix("p9i5","p1i0",[0,"6. Tra c&#7913;u &#272;i&#7879;n tra so&#225;t","","",-1,-1,0,"tracuudts.do",
                "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor1,0,bgcolor2,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor1,fontcolor1]);
  stm_aix("p9i6","p1i0",[0,"7. Tra c&#7913;u &#272;i&#7879;n tra so&#225;t n&#7897;i b&#7897;","","",-1,-1,0,"TcsGomBL.do?function_id=040400",
                "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor1,0,bgcolor2,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor1,fontcolor1]);
	stm_ep();  

//---------------------------------------------------------- DOI CHIEU  -----------------------------------------------------------	

	stm_ai("p0i0",[0,"<b> &#272;&#7889;i chi&#7871;u </b> ","","",-1,-1,0,"#function_id=060000","_self","","","","",0,0,0,"styles/images/icon1.gif","styles/images/icon2.gif",7,7,0,0,1,"#FFFFFF",1,"#FFFFFF",1,"","styles/images/bg1.gif",3,3,0,0,"#FFFFFF","#FFFFFF","#FFFFFF","#000000","10pt Tahoma","10pt Tahoma",0,0]);
	stm_bpx("p1","p0",[1,4,0,0,0,3,0,7,100,"progid:DXImageTransform.Microsoft.Wipe(GradientSize=1.0,wipeStyle=1,motion=forward,enabled=0,Duration=0.00)",5,"progid:DXImageTransform.Microsoft.Wipe(GradientSize=1.0,wipeStyle=1,motion=reverse,enabled=0,Duration=0.00)",4,50]);
	
	
	stm_aix("p1i2","p1i0",[0,"1. &#272;&#7889;i chi&#7871;u             ","","",-1,-1,0,"openTdttCQThuFormAction.do?function_id=060500",
	        "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor1,0,bgcolor2,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor1,fontcolor1]);
	
	stm_ep();
//---------------------------------------------------------- BAO CAO  -----------------------------------------------------------	
	stm_ai("p0i0",[0,"<b> B&#225;o c&#225;o </b>","","",-1,-1,0,"#","_self","","","","",0,0,0,"styles/images/icon1.gif","styles/images/icon2.gif",7,7,0,0,1,"#FFFFFF",1,"#FFFFFF",1,"","styles/images/bg1.gif",3,3,0,0,"#FFFFFF","#FFFFFF","#FFFFFF","#000000","10pt Tahoma","10pt Tahoma",0,0]);
	stm_bpx("p1","p0",[1,4,0,0,0,3,0,7,100,"progid:DXImageTransform.Microsoft.Wipe(GradientSize=1.0,wipeStyle=1,motion=forward,enabled=0,Duration=0.00)",5,"progid:DXImageTransform.Microsoft.Wipe(GradientSize=1.0,wipeStyle=1,motion=reverse,enabled=0,Duration=0.00)",4,50]);
	stm_aix("p1i3","p1i0",[0,"1. B&#7843;ng k&#234; t&#7893;ng h&#7907;p thanh to&#225;n             ","","",-1,-1,0,"InitTcsBangKeChungTuAction.do?function_id=070600",
	                "_self","","","","",0,0,0,"","",0,0,0,0,1,bgcolor1,0,bgcolor2,0,"","",3,3,1,1,bordercolor1,bordercolor2,fontcolor1,fontcolor1]);
    
    stm_em();
*/

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
          <TD></META>
            <SCRIPT type=text/javascript 
            src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/tcs.htm"></SCRIPT>

            <SCRIPT type=text/javascript 
            src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/TcsLapChungtu.htm"></SCRIPT>

            <SCRIPT type=text/javascript 
            src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/wz_tooltip.htm"></SCRIPT>

            <SCRIPT type=text/javascript 
            src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/pupdate.htm"></SCRIPT>

            <SCRIPT type=text/javascript 
            src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/MessageResource.htm"></SCRIPT>

            

            <SCRIPT type=text/javascript 
            src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery.table_navigation.htm"></SCRIPT>

            <SCRIPT type=text/javascript 
            src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery.number_format.htm"></SCRIPT>

            <SCRIPT type=text/javascript 
            src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/actb.htm"></SCRIPT>

            

            <SCRIPT type=text/javascript 
            src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/json.htm"></SCRIPT>

            <SCRIPT type=text/javascript 
            src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/webtoolkit.utf8.htm"></SCRIPT>
                       
            
            <SCRIPT type=text/javascript 
            src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/prototype.js"></SCRIPT>

            <SCRIPT type=text/javascript>
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
    if (window.event.clientY < 0 && (window.event.clientX > (document.documentElement.clientWidth - 5) || window.event.clientX < 15) ) {
      if (confirm("Ban co chac chan muon thoat khoi he thong?"))
      {
        var sFeatures = 'resizable=yes,status=yes,toolbar=no,menubar=no,scrollbars=yes,width=800px,height=600px,top=0,left=0';
        var newWindow = window.open("logoutAction.do?relog=no");     
      }else{      //parent.location
        var sFeatures = 'resizable=yes,status=yes,toolbar=no,menubar=no,scrollbars=yes,width=800px,height=600px,top=0,left=0';
        window.open("main.jsp");
        //newPopup(parent.location);
      }
    }
  }
</script>
 
<script type="text/javascript">
  // Popup window code
  
  function newPopup(url) {
    alert(url);
      newwindow = window.open(url, 'popUpWindow', 'height=900,width=1024,left=10,top=10,resizable=yes,scrollbars=yes,toolbar=no,menubar=no,location=no,directories=no,status=yes');
      if (window.focus) {
          newwindow.focus()
      }  
  }

</script> 