<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ include file="/includes/ttsp_header.inc"%> 
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.ThamSoHTResource"/>
<link type="text/css" rel="stylesheet"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/tabber.css"/>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/tabber.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/doichieu.js"></script>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"
        type="text/javascript">
</script>
<script type="text/javascript" charset="utf-8" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery.jec-1.3.2.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/lov.js"></script>
<%
  String kb_huyen = request.getAttribute("kb_huyen")==null?"":request.getAttribute("kb_huyen").toString();
  String strTinh = request.getAttribute("dftinh")==null?"":request.getAttribute("dftinh").toString();
  String TTTT = request.getAttribute("TTTT")==null?"":request.getAttribute("TTTT").toString();
  String idxKB = request.getAttribute("idxKB")==null?"":request.getAttribute("idxKB").toString();
%>
<script type="text/javascript">
  jQuery.noConflict();
  
  jQuery(document).init(function () {
      getTenKhoBacDC('','');  
      //load form danh muc kho bac   
    jQuery("#dialog-form-lov-dm").dialog({
      autoOpen: false,resizable : false,
      maxHeight: "700px",
      width: "550px",
      modal: true
    });    
  });
  //Them Tra cuu Kho bac Tinh va Huyen
      
 function goPage(page) {
      var f = document.forms[0];
       var idxKB = jQuery('#nhkb_huyen option:selected').index();
       document.getElementsByName('idxKB')[0].value = idxKB;
      f.pageNumber.value = page;
      f.submit();
  }
  function check(type) {
      var f = document.forms[0];
      f.target='';
     if (type == 'close') {
          f.action = 'mainAction.do';
      }else if (type == 'find') {
          var idxKB = jQuery('#nhkb_huyen option:selected').index();
           document.getElementsByName('idxKB')[0].value = idxKB;
          f.action = 'ThamSoHTLSuListAction.do';
      }else if (type == 'print') {
          f.action = 'ThamSoHTLSuPrintAction.do';
          var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
          newDialog = window.open('', '_form', params);             
          f.target='_form';
      }
      
     f.submit();
  }
  
  
/*HungBM edit
*16/11/2016
*Them tra cuu theo danh muc kho bac
*/
/*****************START*******************/
//Mo form danh muc kho bac
function callLov(){      
      jQuery("#loai_lov").val("DMKBTCUU");
      jQuery("#ma_field_id_lov").val("ma_nhkb_nhan");
      jQuery("#ten_field_id_lov").val("ten_nhkb_nhan");
      jQuery("#id_field_id_lov").val("id_nhkb_huyen");
      jQuery("#id_cha_field_id_lov").val("id_nhkb_tinh");
      jQuery("#dialog-form-lov-dm").dialog( "open" );
      
    }
//Lay ten va id danh muc kho bac cha
function getTenKhoBacDC(id,id_cha) { 
    var TTTT="<%=TTTT%>";
    document.getElementById('nhkb_huyen').options.length = 1;// clear du lieu option cu
     var kb_id;
     if(TTTT!=null && ''!=TTTT){
          if (id==null || ''==id){       
                if(id_cha!=null&&''!=id_cha){               
                      kb_id=id_cha;
                      jQuery('#nhkb_tinh').val(id_cha);
                }else if (id_cha==null||''==id_cha){
                  kb_id=document.forms[0].nhkb_tinh.value;
                }
            }else if (id!=null && ''!=id){
                if(id_cha!=null&&''!=id_cha){                
                  kb_id=id_cha;
                  jQuery('#nhkb_tinh').val(id_cha);
                }else if (id_cha==null||''==id_cha){
                  kb_id=document.forms[0].nhkb_tinh.value;
                }
            }
       }else if(TTTT==null || ''==TTTT){
        kb_id=document.forms[0].nhkb_tinh.value;
       }
    var kb_huyen="<%=idxKB%>";   
    var strTinh="<%=strTinh%>";
   if (kb_id !=null && ""!=kb_id){
    jQuery.ajax( {
        type : "POST", url : "getDMucKBTHop.do", data :  {
            "kb_id" : kb_id
        },
        success : function (data, textstatus) {
            if (textstatus != null && textstatus == 'success') {
                if (data != null) {
                    jQuery.each(data, function (i, objectDM) {
                    // truong hop 1 - luc load khong co thang nao                  
                    document.getElementById('nhkb_huyen').options.add(new Option(objectDM.kb_huyen, objectDM.id));
                    });
                    if( strTinh==null || strTinh ==''){  // request set dftinh ==null
                        if(document.getElementById('nhkb_huyen').options.length==2){
                          jQuery("#nhkb_huyen option:eq(0)").remove();

                        }
                    }else if(strTinh!=null && strTinh !=''){
                       if(document.getElementById('nhkb_huyen').options.length==2){ // select dong thu 2 neu select box co 2 value voi user cap tinh
                            jQuery("#nhkb_huyen option:eq(1)").attr('selected', true);

                       }
                      else if(kb_huyen=='0'||kb_huyen==null||''==kb_huyen){
                      jQuery('#ngan_hang option:eq(0)').attr('selected', true);

                      }
                      else if(kb_huyen!='0'){
                      jQuery('#nhkb_huyen option:eq('+kb_huyen+')').attr('selected', true);

                      }
                    }
                }
            }
              if (id!=null && ''!=id){                        
                        jQuery('#nhkb_huyen').val(id);
                    }
        },
        error : function (textstatus) {
            alert(textstatus);
        }
    });
    }
}
/*****************END*******************/
</script>
<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="/ThamSoHTLSuListAction.do" method="post">
    <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
              <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
              <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
                <span class=title2>Tra cứu lịch sử tham số hệ thống</span>
              </td>
              <td width=62><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg" width=62 height=30/></td>
              <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg" width="20%">&nbsp;</td>
              <td width=4><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg" width=4 height=30/></td>
            </tr> 
      </tbody>
    </table>
    <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
      <tbody>
        <tr>
          <td class="title" colspan="6"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="24">
            <span>&nbsp;&nbsp;&nbsp;&nbsp;<font color="Gray">
         <fmt:message key="QuanLyTSHT.listQLTSHT.title.dieukientimkiem"/>
              </font></span>
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
                  <td width="15%" align="right" bordercolor="#e1e1e1">
                    Kho bạc tỉnh  &nbsp;
                  </td>
                  <td width="20%">
                    <html:select property="nhkb_tinh" styleId="nhkb_tinh" style="font-size:12px;width:100%"  onchange="getTenKhoBacDC('','')"
                                 onkeydown="if(event.keyCode==13) event.keyCode=9;"> 
                              <%if(request.getAttribute("TTTT") != null){
                           %>
                                <html:option value="">---Chọn kho bạc tỉnh---</html:option>                  
                            <%}%>
                        <html:optionsCollection  name="dmuckb_tinh" value="id_cha" label="kb_tinh"/>                    
                    </html:select>
                    </td>
                  <td width="15%" align="right" bordercolor="#e1e1e1">
                    Kho bạc huyện   &nbsp;
                  </td>
                  <td colspan="4" width="50%">
                    <html:select property="nhkb_huyen" styleId="nhkb_huyen" style="font-size:12px;width:80%"
                                  onkeydown="if(event.keyCode==13) event.keyCode=9;">                               
                                <html:option value="">---Chọn kho bạc huyện---</html:option>
                                
                  </html:select>
                  </td>
                  <td  align="center" rowspan="2" width="15%">
                    <button type="button" onclick="callLov()" class="ButtonCommon" accesskey="t" >
                            <span class="sortKey">D</span>anh m&#7909;c KB
                    </button> <p/>
                 </td>
                </tr>
            <tr>
              <td width="10%" align="right" bordercolor="#e1e1e1"><fmt:message key="QuanLyTSHT.listQLTSHT.lable.mats"/></td>
              <td width="15%">
                <html:text property="ten_ts" 
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"
                           size="30%"
                           styleclass="promptText"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
              </td>
              <!-- <td width="10%" align="right" bordercolor="#e1e1e1"></td>
              <td width="15%" align="left" bordercolor="#e1e1e1">
                
              </td> -->
              <td   align="right">Từ ngày sửa</td>
                <td   align="left" valign="middle">
                <html:text property="tu_ngay" styleId="tu_ngay"
                           styleClass="promptText" onmouseout="UnTip()"
                           onkeypress="dateBlockKey(event)"
                           onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('tu_ngay');textlostfocus(this);"
                           onfocus="textfocus(this);"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           style="WIDTH:45%"
                           value='<%=request.getParameter("sysdate")%>'/>    
                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="ngay" width="20"
                     style="vertical-align:middle;"/>
                <script type="text/javascript">
                  Calendar.setup( {
                      inputField : "tu_ngay", 
                      ifFormat : "%d/%m/%Y", 
                      button : "ngay"
                  });
                </script>
                
              </td>
               <td style="width:100px;"  align="right">Đến ngày sửa</td>
                <td  align="left" valign="middle">
                <html:text property="den_ngay" styleId="den_ngay"
                           styleClass="promptText" onmouseout="UnTip()"
                           onkeypress="dateBlockKey(event)"
                           onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('den_ngay');textlostfocus(this);"
                           onfocus="textfocus(this);"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           style="WIDTH:45%"
                           value='<%=request.getParameter("sysdate")%>'/>
                 
                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="ngay1" width="20"
                     style="vertical-align:middle;"/>
                <script type="text/javascript">
                  Calendar.setup( {
                      inputField : "den_ngay", 
                      ifFormat : "%d/%m/%Y", 
                      button : "ngay1"
                  });
                </script>
               
              </td>
              
          </table>
          <br/>
        </td>
      </tr>
    </table>
    <%-- hiển thị trạng thái thêm sửa xóa KTV--%>
    <%
    if(request.getAttribute("status") != null){
    String StrStatus = request.getAttribute("status").toString();
    String id = request.getAttribute("nsdID")==null?"":request.getAttribute("nsdID").toString();
    %>
    <font color="Red" dir="ltr">
      <fmt:message key="<%=StrStatus%>">
        <fmt:param>
          <%=id%>
        </fmt:param>
      </fmt:message>
    </font>
    <%}%>
    <%-- ************************************--%>
    <%-- ************************************--%>
    <%-- 2 nút tra cứu thoát--%>
    <table class="buttonbar" border="0" cellspacing="0" cellpadding="0" width="100%" >
      <tr>
        <td align="center">
        <span>
          <button type="button" onclick="check('find')" class="ButtonCommon"
                  accesskey="t">
            <span class="sortKey">T</span>ra cứu
          </button>
          </span>
          <span>
          <button type="button" onclick="check('print')" class="ButtonCommon"
                  accesskey="t">
            <span class="sortKey">I</span>n
          </button> 
          </span>
          <span>
          <button type="button" onclick="check('close')" class="ButtonCommon"
                  accesskey="o">
            Th<span class="sortKey">o</span>át
          </button>
          </span>
        </td>
      </tr>
    </table>
    <%-- ************************************--%>
    <%-- ************************************--%>
    <%-- Hiển thị list KTV--%>
    <table border="2" align="center" width="100%"
           style="BORDER-COLLAPSE: collapse">
      <tbody>
        <tr>
          <td class="title" colspan="6"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="24">
            <font color="Gray">
              <fmt:message key="QuanLyTSHT.listQLTSHT.title.ketquatimkiem"/>
            </font>
          </td>
        </tr>
        <tr>
          <td>
            <table class="navigateable focused" cellspacing="0" cellpadding="1"
                   bordercolor="#e1e1e1" border="1" align="center" width="100%"
                   style="BORDER-COLLAPSE: collapse">
              <thead>
                <th class="promptText" height="22" bgcolor="#f0f0f0">
                  <div align="center"><fmt:message key="QuanLyTSHT.listQLTSHT.lable.mats"/> </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center"><fmt:message key="QuanLyTSHT.listQLTSHT.lable.giatricu"/></div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center"><fmt:message key="QuanLyTSHT.listQLTSHT.lable.giatrimoi"/></div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">shkb</div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center"><fmt:message key="QuanLyTSHT.listQLTSHT.lable.mansd"/></div>
                </th>
                <th sclass="promptText" bgcolor="#f0f0f0">
                  <div align="center"><fmt:message key="QuanLyTSHT.listQLTSHT.lable.mota"/></div>
                </th>
                 <th sclass="promptText" bgcolor="#f0f0f0">
                  <div align="center">Ngày sửa</div>
                </th>
              </thead>
                 <%
      com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
      int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
 %>
              <tbody class="navigateable focused" cellspacing="0"
                     cellpadding="1" bordercolor="#e1e1e1">
                <logic:notEmpty name="listTS">
                  <logic:iterate id="TSlist" name="listTS" indexId="stt">
                    <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                      <td align="left" width="25%">
                        <bean:write name="TSlist" property="ten_ts"/>
                      </td>
                       <td align="center" width="5%">
                        <bean:write name="TSlist" property="giatri_ts"/>
                      </td>
                      <td align="center" width="5%">
                        <bean:write name="TSlist" property="giatri_ts_moi"/>
                      </td>
                      <td align="left" width="20%">
                        <bean:write name="TSlist" property="shkb"/>
                      </td>
                      <td align="center" width="10%">
                        <bean:write name="TSlist" property="ma_nsd"/>
                      </td>
                      <td align="left" width="35%">
                        <bean:write name="TSlist" property="mo_ta"/>
                      </td>
                      <td align="center" width="15%">
                        <bean:write name="TSlist" property="ngay_cap_nhat"/>
                      </td>
                      </tr>
                  </logic:iterate>
                </logic:notEmpty>
               <tr>
                  <td colspan="7">
                    <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean, "#0000ff")%>
                  </td>
                </tr>
              </tbody>
               
              <html:hidden property="pageNumber" value="1"/>
               <input type="hidden" name ="idxKB"/>
            </table>
          </td>
        </tr>
      </tbody>
    </table>
    <%-- ************************************--%>
  </html:form>
</div>
<div id="dialog-form-lov-dm" title="Tra c&#7913;u danh m&#7909;c Kho b&#7841;c">
  <p class="validateTips"></p>
  <%@include file="/pages/lov/lovDMKBTCUU.jsp" %>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
