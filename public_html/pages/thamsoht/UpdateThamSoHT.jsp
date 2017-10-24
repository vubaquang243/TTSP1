<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ include file="/includes/ttsp_header.inc"%>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<div class="app_error">
  <html:errors/>
</div>
<fmt:setBundle basename="com.seatech.ttsp.resource.ThamSoHTResource"/>
<div class="box_common_conten">
  <html:form action="ThamSoHTUpdateExcAction" method="post">
    <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
         <tr>
              <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
              <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
                <span class=title2><fmt:message key="QuanLyTSHT.listQLTSHT.title"/></span>
              </td>
              <td width=62><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg" width=62 height=30/></td>
              <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg" width="20%">&nbsp;</td>
              <td width=4><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg" width=4 height=30/></td>
            </tr> 
      </tbody>
    </table>
    <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="100%" align="left">
      <tbody>
        <tr>
          <td class="title" colspan="6" bordercolor="#e1e1e1"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="24">
            <span>&nbsp;&nbsp;&nbsp;&nbsp;<font color="Gray">
                <fmt:message key="QuanLyTSHT.listQLTSHT.title.sua"/>
              </font></span>
          </td>
        </tr>
      </tbody>
       
      <tbody>
        <tr>
          <td>
            <br/>
            <table width="80%" cellspacing="0" cellpadding="1"
                   bordercolor="#e1e1e1" border="0" align="center"
                   style="BORDER-COLLAPSE: collapse">
              <tr>
                <td width="20%" align="right" bordercolor="#e1e1e1">
                  <fmt:message key="QuanLyTSHT.listQLTSHT.lable.mats"/>
                </td>
                <td width="29%" align="left" bordercolor="#e1e1e1">
                  <html:text property="ten_ts" disabled="true"
                             onfocus="this.style.backgroundColor='#ffffb5'"
                             size="60%"
                             onblur="this.style.backgroundColor='#ffffff'"
                             styleclass="promptText"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                </td>
              </tr>
               
              <tr>
                <td width="20%" align="right" bordercolor="#e1e1e1">
                  <fmt:message key="QuanLyTSHT.listQLTSHT.lable.giatri"/>
                </td>
                <td width="50%" align="left" bordercolor="#e1e1e1">
                  <html:hidden property="giatri_ts"
                               onfocus="this.style.backgroundColor='#ffffb5'"
                               size="60%"
                               onblur="this.style.backgroundColor='#ffffff'"
                               styleclass="promptText"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                   
                  <html:text property="giatri_ts_moi"
                             onfocus="this.style.backgroundColor='#ffffb5'"
                             size="60%"
                             onblur="this.style.backgroundColor='#ffffff'"
                             styleclass="promptText"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                </td>
              </tr><tr></tr><tr>
                <td width="20%" align="right" bordercolor="#e1e1e1">
                  <fmt:message key="QuanLyTSHT.listQLTSHT.lable.mota"/>
                </td>
                <td width="50%" height="5%" align="left" bordercolor="#e1e1e1">
                  <html:textarea property="mo_ta"
                                 onfocus="this.style.backgroundColor='#ffffb5'"
                                 size="60%"
                                 onblur="this.style.backgroundColor='#ffffff'"
                                 styleclass="promptText"
                                 onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                </td>
              </tr>
            </table>
            <br/>
          </td>
        </tr>
      </tbody>
       
      <tbody align="center" bordercolor="#e1e1e1">
        <tr>
          <td bordercolor="#e1e1e1">
            <button type="button" onclick="sbbut('save')" accesskey="g"
                    class="ButtonCommon">
              <span class="sortKey">G</span>hi
            </button>
             
            <button type="button" onclick="sbbut('close')" accesskey="o"
                    class="ButtonCommon">
              Th<span class="sortKey">o</span>&aacute;t
            </button>
          </td>
        </tr>
      </tbody>
    </table>
    <html:hidden property="id"/>
    <html:hidden property="cho_phep_sua"/>
    <html:hidden property="ten_ts"/>
    <br/>
  </html:form>
</div>
<script type="text/javascript">
  document.getElementById("giatri_ts_moi").focus();

  function goPage(page) {
      var f = document.forms[0];
      f.submit();
  }

  function sbbut(type) {
      var f = document.forms[0];
      if (type == 'close') {
          //     f.ten_ts.value[0] =null;
          f.ten_ts[1].value = "";
          f.ten_ts[0].value = "";
          f.giatri_ts_moi.value = "";
          f.id.value = "";
          f.giatri_ts.value = "";
          f.mo_ta.value = "";
          f.action = 'ThamSoHTListAction.do';
      }
      if (type == 'save') {
        if (f.giatri_ts_moi.value == null || f.giatri_ts_moi.value =="")
        {
        alert('Không được để trống trường giá trị');
        document.getElementById("giatri_ts_moi").focus();
              return;
        }
        if (f.giatri_ts_moi.value.length >= 100) {
              alert('Trường giá trị nhập không quá 100 kí tự');
              document.getElementById("giatri_ts_moi").focus();
              return;
          }
        if (f.mo_ta.value == null || f.mo_ta.value =="")
        {
        alert('Không được để trống trường mô tả');
        document.getElementById("mo_ta").focus();
              return;
        }
          if (f.mo_ta.value.length >= 300) {
              alert('Trường Mô tả nhập không quá 300 kí tự');
              document.getElementById("mo_ta").focus();
              return;
          }
          
          else {
              f.action = 'ThamSoHTUpdateExcAction.do';
          }
      }
      f.submit();
  }
</script>
<%@ include file="/includes/ttsp_bottom.inc"%>