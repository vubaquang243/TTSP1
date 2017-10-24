<%@ page contentType="text/html; charset=UTF-8"%>


<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ include file="/includes/ttsp_header.inc"%>
<link type="text/css" rel="stylesheet"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/CSS/step.css"/>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"
        type="text/javascript">
</script>
<style type="text/css">
	/*body { margin:10px; padding:0; font-family:Arial, Helvetica, sans-serif;}
	#green_and_orange_step_menu { margin:0; padding:5px; display:inline-table; list-style:none; }
	#green_and_orange_step_menu li{ display:inline; float:left; background:url(<%=request.getContextPath()%>/styles/images/step_orange_green.gif) no-repeat -50px bottom; height:41px; line-height:41px; font-size:11px; padding:0 30px 0 25px; position:relative; }
	#green_and_orange_step_menu li span{ margin:0px; z-index:2000; background:url(<%=request.getContextPath()%>/styles/images/step_orange_green.gif) no-repeat left bottom; display:block; position:absolute; height:41px; line-height:41px; width:13px; top:0; left:-10px; padding:0;}
	#green_and_orange_step_menu li.inprogress { background-position:-50px 0px} 
	#green_and_orange_step_menu li.inprogress span, #green_and_orange_step_menu li.lastinprogress span{background-position:left 0px }
	#green_and_orange_step_menu li.done {background-position: -50px -41px}
	#green_and_orange_step_menu li.done span, #green_and_orange_step_menu li.ldone span {background-position:left -41px }
	#green_and_orange_step_menu li.frist, #green_and_orange_step_menu li.done, #green_and_orange_step_menu li.inprogress, #green_and_orange_step_menu li.ldone, #green_and_orange_step_menu li.lastinprogress  { color:#FFF;}
	#green_and_orange_step_menu li.frist {background-position: -20px 0px }
	#green_and_orange_step_menu li.fdone { background-position: -20px -41px  }
	#green_and_orange_step_menu li.last {background-position: right bottom }
	#green_and_orange_step_menu li.lastinprogress { background-position: right 0}
	#green_and_orange_step_menu li.ldone { background-position: right -41px}*/
</style>

<html:form styleId="frmTrienKhai" action="/trienKhaiAction.do">
    <table width="99%">
      <tbody>
        <tr>
          <td><font color="red"><html:errors/></font></td>
        </tr>
      </tbody>
    </table>
    <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
        <tbody>
          <tr>
            <td width="13"><img width="13" height="30" src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img></td>
            <td width="75%" background="<%=request.getContextPath()%>/styles/images/T2.jpg"><span class="title2">
          Thiết lập triển khai</span></td>
            <td width="62"><img width="62" height="30" src="<%=request.getContextPath()%>/styles/images/T3.jpg"></img></td>
            <td width="20%" background="<%=request.getContextPath()%>/styles/images/T4.jpg">&nbsp;</td>
            <td width="4"><img width="4" height="30" src="<%=request.getContextPath()%>/styles/images/T5.jpg"></img></td>
          </tr>
        </tbody>
    </table> 
    
    <table  class="tableBound">
      <tr>
        <td><!--<div style="clear:both;"></div>
          <ul id="green_and_orange_step_menu">
              <li class="frist fdone">Bước 1</li>
              <li class="done">Bước 2<span></span></li>
              <li class="done">Bước 3<span></span></li>
              <li class="done">Bước 4<span></span></li>
              <li class="done">Bước 5<span></span></li>
              <li class="done">Bước 6<span></span></li>
              <li class="done">Bước 7<span></span></li>
              <li class="last ldone">Bước 8<span></span></li>
          </ul> -->
           <div class="trienkhai-steps">
            <div class="completed-step"><a><span>1</span>SHKB</a></div>
            <div class="completed-step"><a><span>2</span>Mã NH KB</a></div>
            <div class="completed-step"><a><span>3</span>Mã NH-SHKB</a></div>
            <div class="completed-step"><a><span>4</span>SHKB-ĐVNS</a></div>
            <div class="completed-step"><a><span>5</span>CN NH</a></div>
            <div class="completed-step"><a><span>6</span>Tài khoản</a></div>
            <div class="completed-step"><a><span>7</span>Ngày TK</a></div>
            <div class="completed-step"><a><span>8</span>Số dư</a></div>
            <div class="completed-step"><a><span>9</span>COT</a></div>
          </div>
          <table class="tableTop">
            <tr>
              <td>
                <h3><i><B><u>THIẾT LẬP TRIỂN KHAI THÀNH CÔNG</u></B></i></h3>
              </td>
            </tr>
          </table>
          <table width="100%" style="font-size:9.5pt">                  
            <tr>
              <td align="right" width="15%"><i>Số hiệu KB: </i></td>
              <td align="left">
                <strong>
                  <bean:write name="TrienKhaiForm" property="shkb" />
                </strong>
              </td>
            </tr>
            <tr>
              <td align="right" width="15%"><i>Mã NH của KB: </i></td>
              <td align="left">
                <strong>
                  <bean:write name="TrienKhaiForm" property="ma_nh" />
                </strong>
              </td>
            </tr>
            <tr>
              <td align="right" width="15%"><i>Mã ĐVSDNS của KB: </i></td>
              <td align="left">
                <strong>
                  <bean:write name="TrienKhaiForm" property="ma_dvsdns" />
                </strong>
              </td>
            </tr>
            <tr>
              <td align="right" width="15%"><i>Mã CN NH: </i></td>
              <td align="left">
                <strong>
                  <bean:write name="TrienKhaiForm" property="ma_cn_nh" />
                </strong>
              </td>
            <tr>
              <td align="right" width="15%"><i>Số TK: </i></td>
              <td align="left">
                <strong>
                  <bean:write name="TrienKhaiForm" property="so_tk" />
                </strong>
              </td>
            </tr>
            <tr>
              <td align="right" width="15%"><i>Số dư: </i></td>
              <html:hidden property="loai_tien" styleId="loai_tien" />
              <td align="left">
                <strong>
                  <logic:equal value="VND" name="TrienKhaiForm" property="loai_tien">
                    <fmt:setLocale value="vi_VI"/>
                    <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                      <bean:write name="TrienKhaiForm" property="so_du" />
                    </fmt:formatNumber>
                  </logic:equal>
                  <logic:notEqual value="VND" name="TrienKhaiForm" property="loai_tien">
                    <fmt:formatNumber maxFractionDigits="2"  type="currency"  currencySymbol="">
                      <bean:write name="TrienKhaiForm" property="so_du" />
                    </fmt:formatNumber>
                  </logic:notEqual>
                </strong>
              </td>
            </tr>
            <tr>
              <td align="right" width="15%"><i>Ngày triển khai: </i></td>
              <td align="left">
                <strong>
                  <bean:write name="TrienKhaiForm" property="ngay_tk" />
                </strong>
              </td>
            </tr>
            <tr>
              <td></td>
              <td>
                    <button  id="btn_next"
                                accesskey="K" type="button" onclick="submmitTK('next');"
                              class="ButtonCommon" >
                        Làm tiếp
                    </button>
                    <button  id="btn_exit"
                                accesskey="K" type="button" onclick="submmitTK('exit');"
                              class="ButtonCommon" >
                        Thoát
                    </button>
              </td>
            </tr>
          </table>  
        </td>
      </tr>
    </table>
</html:form>

<%@ include file="/includes/ttsp_bottom.inc"%>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/doichieu.js" type="text/javascript"></script>
<script type="text/javascript">
  function submmitTK(type) {
    if(type == 'exit'){      
      document.forms[0].action = "thoatView.do";       
    }else{
      document.forms[0].action = "trienKhaiAction.do";
    }
    document.forms[0].submit();
  }
</script>