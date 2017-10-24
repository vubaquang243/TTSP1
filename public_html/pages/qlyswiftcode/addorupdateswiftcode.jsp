<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ include file="/includes/ttsp_header.inc"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<link type="text/css" rel="stylesheet"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<script type="text/javascript">
    jQuery.noConflict();
    //************************************ LOAD PAGE **********************************
    jQuery(document).init(function () {
    
    });
    function themMoi(){
      var f = document.forms[0];
      f.submit();
    }
    function thoat(){
        var f = document.forms[0];
        f.action = 'traCuuSpSwiftCode.do';
        f.submit();
    }
</script>
<title>Thêm swift code</title>
<html:form action="/addSpSwiftCode.do" method="post">
    <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
        <tr>
        <td width="13">
          <img width="13" height="30"
               src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img>
        </td>
        <td width="75%"
            background="<%=request.getContextPath()%>/styles/images/T2.jpg">
          <span class="title2">Thêm swift code</span>
        </td>
        <td width="62">
          <img width="62" height="30"
               src="<%=request.getContextPath()%>/styles/images/T3.jpg"></img>
        </td>
        <td width="20%"
            background="<%=request.getContextPath()%>/styles/images/T4.jpg">&nbsp;</td>
        <td width="4">
          <img width="4" height="30"
               src="<%=request.getContextPath()%>/styles/images/T5.jpg"></img>
        </td>
      </tr>
    </table>
    <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
      <tbody>
        <c:if test="${requestScope.error != null}">
          <tr>
            <td style="color:red"><c:out value="${requestScope.error}"/></td>
          </tr
        </c:if>
        <c:if test="${requestScope.success != null}">
          <tr>
            <td style="color:green"><c:out value="${requestScope.success}"/></td>
          </tr
        </c:if>
      <tr>
        <td>
          <br/>
          <table width="90%" cellspacing="0" cellpadding="1"
                 bordercolor="#e1e1e1" border="0" align="center"
                 style="BORDER-COLLAPSE: collapse">
            <tr>
              <td width="15%" align="right" bordercolor="#e1e1e1">Bic code</td>
              <td width="25%">
                <html:text property="bic_code"  style="width:100%"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"/>
              </td>
              <td width="15%" align="right" bordercolor="#e1e1e1">Branch code</td>
              <td width="25%">
                <html:text property="branch_code"  style="width:100%"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"/>
              </td>
            </tr>
            <tr>
              <td width="15%" align="right" bordercolor="#e1e1e1">Institution name</td>
              <td width="25%">
                <html:text property="institution_name"  style="width:100%"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"/>
              </td>
              <td width="15%" align="right" bordercolor="#e1e1e1">Branch information</td>
              <td width="25%">
                <html:text property="branch_information"  style="width:100%"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"/>
              </td>
            </tr>
            <tr>
              <td width="15%" align="right" bordercolor="#e1e1e1">City heading</td>
              <td width="25%">
                <html:text property="city_heading"  style="width:100%"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"/>
              </td>
              <td width="15%" align="right" bordercolor="#e1e1e1">Subtype indication</td>
              <td width="25%">
                <html:text property="subtype_indication"  style="width:100%"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"/>
              </td>
            </tr>
            <tr>
              <td width="15%" align="right" bordercolor="#e1e1e1">Value added services</td>
              <td width="25%">
                <html:text property="value_added_services"  style="width:100%"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"/>
              </td>
              <td width="15%" align="right" bordercolor="#e1e1e1">Extra info</td>
              <td width="25%">
                <html:text property="extra_info"  style="width:100%"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"/>
              </td>
            </tr>
            <tr>
              <td width="15%" align="right" bordercolor="#e1e1e1">Physical address 1</td>
              <td width="25%">
                <html:text property="physical_address_1"  style="width:100%"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"/>
              </td>
              <td width="15%" align="right" bordercolor="#e1e1e1">Physical address 2</td>
              <td width="25%">
                <html:text property="physical_address_2"  style="width:100%"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"/>
              </td>
            </tr>
            <tr>
              <td width="15%" align="right" bordercolor="#e1e1e1">Physical address 3</td>
              <td width="25%">
                <html:text property="physical_address_3"  style="width:100%"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"/>
              </td>
              <td width="15%" align="right" bordercolor="#e1e1e1">Physical address 4</td>
              <td width="25%">
                <html:text property="physical_address_4"  style="width:100%"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"/>
              </td>
            </tr>
            <tr>
              <td width="15%" align="right" bordercolor="#e1e1e1">Location</td>
              <td width="25%">
                <html:text property="location"  style="width:100%"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"/>
              </td>
              <td width="15%" align="right" bordercolor="#e1e1e1">Country name</td>
              <td width="25%">
                <html:text property="country_name"  style="width:100%"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"/>
              </td>
            </tr>
            <tr>
              <td width="15%" align="right" bordercolor="#e1e1e1">Pob number</td>
              <td width="25%">
                <html:text property="pob_number"  style="width:100%"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"/>
              </td>
              <td width="15%" align="right" bordercolor="#e1e1e1">Pob location</td>
              <td width="25%">
                <html:text property="pob_location"  style="width:100%"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"/>
              </td>
            </tr>
            <tr>
              <td width="15%" align="right" bordercolor="#e1e1e1">Pob country name</td>
              <td width="25%">
                <html:text property="pob_country_name"  style="width:100%"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"/>
              </td>
              <td width="15%" align="right" bordercolor="#e1e1e1">Status</td>
              <td width="25%">
                <html:select property="tinh_trang">
                  <html:option value="1">Hoạt động</html:option>
                  <html:option value="0">Không hoạt động</html:option>
                </html:select>
              </td>
              <html:hidden property="id" />
            </tr>
          </table>
          <br/>
        </td>
      </tr>
      </tbody>
    </table>
    <table class="buttonbar" align="right">
      <tr>
        <td>
          <span id="ghi">
            <button type="button" onclick="themMoi()" class="ButtonCommon"
                    accesskey="g" style="width:100px">
              <span class="sortKey">G</span>hi
            </button>
          </span>
          <span id="thoat">
            <button type="button" onclick="thoat()" class="ButtonCommon"
                    accesskey="o" style="width:100px">
              Th<span class="sortKey">o</span>át
            </button>
          </span>
        </td>
        <td>
          <c:if test="${requestScope.id eq null}">
            <input type="hidden" value="add" name="add" />
          </c:if>
          <c:if test="${requestScope.id != null}">
            <input type="hidden" value="update" name="update" />
          </c:if>
        </td>
      </tr>
    </table>
</html:form>
<%@ include file="/includes/ttsp_bottom.inc"%>