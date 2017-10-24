<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="java.util.*"%>
<%@page  import="com.seatech.framework.AppConstants"%>
<%@page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>


<HTML >
<HEAD>
<TITLE>KBNN-TTSP</TITLE>
</HEAD>
<BODY leftMargin="0" rightMargin="0" topMargin="0" marginheight="0" marginwidth="0" >

<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/common.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/hoverIntent.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/superfish.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/dropdown.js"></script>

<script type="text/javascript">
var headID = document.getElementsByTagName("head")[0];   
// add style
var cssNode = document.createElement('link');
cssNode.type = 'text/css';
cssNode.rel = 'stylesheet';
cssNode.href = '<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/reset.css';
cssNode.media = 'screen';
headID.appendChild(cssNode);
// ***
cssNode = document.createElement('link');
cssNode.type = 'text/css';
cssNode.rel = 'stylesheet';
cssNode.href = '<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/layout.css';
cssNode.media = 'screen';
headID.appendChild(cssNode);
// ***
cssNode = document.createElement('link');
cssNode.type = 'text/css';
cssNode.rel = 'stylesheet';
cssNode.href = '<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/superfish.css';
cssNode.media = 'screen';
headID.appendChild(cssNode);

// ***
var cssNode = document.createElement('link');
cssNode.type = 'text/css';
cssNode.rel = 'stylesheet';
cssNode.href = '<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/ihtkk.css';
cssNode.media = 'screen';
headID.appendChild(cssNode);

// ***
cssNode = document.createElement('link');
cssNode.type = 'text/css';
cssNode.rel = 'stylesheet';
cssNode.href = '<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/superfish-navbar.css';
cssNode.media = 'screen';
headID.appendChild(cssNode);
// ***
cssNode = document.createElement('link');
cssNode.type = 'text/css';
cssNode.rel = 'stylesheet';
cssNode.href = '<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/menu.css';
cssNode.media = 'screen';
headID.appendChild(cssNode);

function GetUnicode(iStr)
{
   for (i=0, oStr=''; i < iStr.length; )
   {
      if (iStr.charCodeAt(i)==38)
      {
         if (iStr.charCodeAt(i + 1)==35)
         {
            p=iStr.indexOf(';', i  + 2);
            if (p!=-1)
            {
               if (p - i <= 7)
               {
                  if (isFinite(iStr.substr(i + 2, p - i - 2)))
                  {
                     oStr = oStr.concat(String.fromCharCode(iStr.substr(i + 2, p - i - 2)));
                     i = p + 1;
                     continue;
                  }
               }
            }
         }
         else
         {
            p=iStr.indexOf(';', i  + 1);
            if (p!=-1)
            {
               switch (iStr.substr(i + 1, p - i - 1))
               {
               case 'amp':
                  oStr = oStr.concat('&');
                  i = p + 1;
                  break;
               case 'quot':
                  oStr = oStr.concat('"');
                  i = p + 1;
                  break;
               case 'lt':
                  oStr = oStr.concat('<');
                  i = p + 1;
                  break;
               case 'gt':
                  oStr = oStr.concat('>');
                  i = p + 1;
                  break;
               }
            }
         }
      }


      oStr=oStr.concat(iStr.charAt(i));
      i++;
   }
   
   return oStr;
}
  
</script>

<script type="text/javascript">
        function activeMenu(name) {
            $('#' + name).addClass('current');
        }
        $(document).ready(function(){ 
                $("ul.sf-menu").superfish({ 
                        pathClass:  'current' 
                });
        });
        
        //Hien thi loi
        function addError() {
            var elem = document.getElementById('addErrButton');
            var tblListPLuc = document.getElementById('tblListPLuc');    
            var divPLucObj = document.getElementById('div_err_detail');
            divPLucObj.style.display = 'inline';
            var divHeight = divPLucObj.offsetHeight;
            var btnHeight = elem.offsetHeight;
            var xPos = elem.offsetLeft;
            var yPos;
            var h;
            var tempEl = elem.offsetParent;
            while (tempEl != null) {
                    xPos += tempEl.offsetLeft;
                    tempEl = tempEl.offsetParent;
            }
            
            yPos = elem.offsetTop;
            tempEl = elem.offsetParent;
            while (tempEl != null) {
                    yPos += tempEl.offsetTop;
                    tempEl = tempEl.offsetParent;
            }
            yPos = yPos + btnHeight - divHeight;
            divPLucObj.style.top = yPos;
            divPLucObj.style.left = xPos;
            divPLucObj.style.width = tblListPLuc.width;
        }
        function cancelError() {
            var divPLucObj = document.getElementById('div_err_detail');    
            divPLucObj.style.display = 'none';
        }
</script>
<%
  if(request.getSession().getAttribute("username") == null){
    response.sendRedirect(request.getContextPath()+"/login.do");
  }
%>
<div id="mainWrapper">
		<!-- BEGIN: HEADER -->
		<div id="header">
    <h1 id="logo"><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/logo77.png" alt="" class="pngFix" /></h1>
		<h2 id="siteName"><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/siteName72.png" alt="Thu&#7871; Vi&#7879;t Nam T&#7893;ng c&#7909; thu&#7871; b&#7897; T&#224;i Ch&#237;nh N&#432;&#7899;c c&#7897;ng h&#242;a x&#227; h&#7897;i ch&#7911; ngh&#297;a Vi&#7879;t Nam" /></h2>
                       
			<div id="mdl_login">
				<!--<span><strong>Xin ch&#224;o: </strong><%=request.getSession().getAttribute("username")%></span>				
        <html:link page="/logoutAction.do"><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/btn_logout.gif" alt="logout" /></html:link>-->
        <html:link page="/logoutAction.do">&#272;&#259;ng xu&#7845;t</html:link> 
			</div>
      
		</div>
		<!-- END: HEADER -->
		
		<!-- BEGIN: GNB -->
		<div id="wrapper">
        <ul id="nav">
           <li>
				<a href="#">H&#7879; th&#7889;ng  |</a>
				<ul>
                   <li><a href="#">Qu&#7843;n l&#253; NSD</a></li>
				   <li><a href="#">Qu&#7843;n l&#253; nh&#243;m NSD</a></li>
				   <li><a href="#">Tham s&#7889; h&#7879; th&#7889;ng</a></li>				   
                   <li><a href="#">Ch&#7913;ng th&#432; s&#7889; &raquo;</a>
                        <ul>
                           <li><a href="#">&#272;&#259;ng k&#253; ch&#7913;ng th&#432; s&#7889;</a></li>
                           <li><a href="#">Duy&#7879;t &#273;&#259;ng k&#253; ch&#7913;ng th&#432; s&#7889;</a></li>
                           <li><a href="#">Tra c&#7913;u ch&#7913;ng th&#432; s&#7889;</a></li>                           
                        </ul>                   
                   </li>
				   <li><a href="#">Theo d&#245;i h&#7879; th&#7889;ng &raquo;</a>
                        <ul>
                           <li><a href="#">L&#7883;ch s&#7917; truy c&#7853;p</a></li>
                           <li><a href="#">L&#7883;ch s&#7917; t&#225;c nghi&#7879;p</a></li>
                           <li><a href="#">Ch&#7913;ng t&#7915; giao di&#7879;n</a></li>                           
						   
                        </ul>                   
                   </li>
                   <li><a href="#">Qu&#7843;n l&#253; t&#224;i kho&#7843;n KB/NH</a></li>
                   <li><a href="#">Tho&#225;t</a></li>
                </ul>
			</li>
           <li><a href="#">L&#7879;nh thanh to&#225;n  |</a>
                <ul>
                   <li><html:link page="/LTToanAction.do">X&#7917; l&#253; LTT &#273;i</html:link></li>
                   <li><a href="#">X&#7917; l&#253; LTT &#273;&#7871;n</a></li>
                   <li><a href="#">Ph&#226;n quy&#7873;n nh&#7853;p l&#7879;nh</a></li>
                   <li><html:link page="/dSachLTToanAction.do">Tra c&#7913;u LTT</html:link></li>                   
                </ul>
           
           </li>
           <li><a href="#">&#272;i&#7879;n tra so&#225;t  |</a>
                <ul>
                   <li><a href="#">X&#7917; l&#253; l&#7879;nh tra so&#225;t h&#7887;i</a></li>
                   <li><a href="#">X&#7917; l&#253; l&#7879;nh tra so&#225;t tr&#7843; l&#7901;i</a></li>
                   <li><a href="#">X&#225;c nh&#7853;n tra so&#225;t tr&#7843; l&#7901;i</a></li>
                </ul>
           </li>
           <li><a href="#">&#272;&#7889;i chi&#7871;u  |</a>
				<ul>
                   <li><a href="#">&#272;&#7889;i chi&#7871;u</a></li>
                   <li><a href="#">G&#7917;i l&#7841;i LTT</a></li>
                </ul>
		   </li>
           <li><a href="#">B&#225;o c&#225;o </a>
				<ul>
                   <li><a href="#">T&#7893;ng h&#7899;p k&#7871;t qu&#7843; &#273;&#7889;i chi&#7871;u</a></li>
                   <li><a href="#">B&#7843;ng k&#234; ch&#234;nh l&#7879;ch c&#225;c LTT</a></li>
				   <li><a href="#">K&#7871;t qu&#7843; &#273;&#7889;i chi&#7871;u &#272;TS</a></li>
				   <li><a href="#">B&#7843;ng k&#234; c&#225;c LTT ch&#432;a chuy&#7875;n</a></li>
				   <li><a href="#">B&#7843;ng k&#234; c&#225;c LTT h&#7911;y</a></li>
				   <li><a href="#">B&#7843;ng k&#234; c&#225;c &#272;TS h&#7911;y</a></li>
				   <li><a href="#">S&#7893; chi ti&#7871;t ng&#226;n h&#224;ng</a></li>
                </ul>
			</li>
        </ul>
    </div>
		<!-- END: GNB AREA -->
		
		<!-- BEGIN: CONTAINER -->
    
		<div id="container" class="marT10">			
			<!-- BEGIN: CONTENT -->
			<div id="content">