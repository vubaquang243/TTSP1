<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ include file="/includes/ttsp_header.inc"%>
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
        
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.LTTDiResource" />
<fmt:setBundle basename="com.seatech.ttsp.resource.DoichieuResource"/>


<%
  String kb_huyen = request.getAttribute("kb_huyen")==null?"":request.getAttribute("kb_huyen").toString();
  String ngan_hang = request.getAttribute("ngan_hang")==null?"":request.getAttribute("ngan_hang").toString();
  String strTinh = request.getAttribute("dftinh")==null?"":request.getAttribute("dftinh").toString();
  String tcuu = request.getAttribute("tcuu")==null?"":request.getAttribute("tcuu").toString();
  String dchieu3 = request.getAttribute("dchieu3")==null?"":request.getAttribute("dchieu3").toString();
  String inlan_dc = request.getAttribute("inlan_dc")==null?"":request.getAttribute("inlan_dc").toString();
  String inxtthai = request.getAttribute("inxtthai")==null?"":request.getAttribute("inxtthai").toString();
  String initAction = request.getAttribute("initAction")==null?"":request.getAttribute("initAction").toString();
  String tong_row = request.getAttribute("tong_row")==null?"":request.getAttribute("tong_row").toString();
  String cur_page = request.getAttribute("currentPage")==null?"":request.getAttribute("currentPage").toString();
  

%>
<script type="text/javascript">
  jQuery.noConflict();
  //************************************ LOAD PAGE **********************************
  
  
  
  jQuery(document).init(function () {
    //defaultStateFormBK();
    var dchieu3="<%=dchieu3%>";
    var inlan_dc="<%=inlan_dc%>";
     var tong_row="<%=tong_row%>";
     var cur_page="<%=cur_page%>";
     var total_page = Math.ceil(parseInt(tong_row)/15);
//     alert(total_page);
       tthai_sel(inlan_dc);
      getTenKhoBacDC('','');
      jQuery('#tong_row').val(toFormatNumber(tong_row,0,'.'));
      jQuery('#currentPage').val(cur_page);
      jQuery('#total_page').val(total_page);
      
    jQuery("#dialog-form-lov-dm").dialog({
      autoOpen: false,resizable : false,
      maxHeight: "700px",
      width: "550px",
      modal: true
    });
      
 //HungBM Modify: sua Lan doi chieu - tra lai gia tri ban dau (truoc khi nang cap) - BEGIN     
        if(dchieu3!=null && ''!=dchieu3){
          var dataArr = [{'value':'','text':'Tất cả'},
                       {'value':'1','text':'Đối chiếu lần 1'},
//                       {'value':'2','text':'Đối chiếu lần 2'},
                       {'value':'3','text':'Đối chiếu lần 3'}];
 //HungBM Modify: sua Lan doi chieu - tra lai gia tri ban dau (truoc khi nang cap) - END                      
//                  // Removes all options for the select box
                  jQuery('#lan_dc option').remove();
                  // .each loops through the array
                  jQuery.each(dataArr, function(i){
                  jQuery('#lan_dc').append(jQuery("<option></option>").attr("value",dataArr[i]['value']).text(dataArr[i]['text']));
                  jQuery('#lan_dc option:eq('+inlan_dc+')').attr('selected', true);
                  });    
        }        
  });
</script>

<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="TCuuTTinDChieuAction.do" method="post" >
   <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
          <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
            <span class=title2> <fmt:message key="doi_chieu.page.title.tcuu"/></span>
          </td>
          <td width=62><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg" width=62 height=30/></td>
          <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg" width="20%">&nbsp;</td>
          <td width=4><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg" width=4 height=30/></td>
        </tr>
      </tbody>
   </table>

    <table style="BORDER-COLLAPSE: collapse" border="0" cellspacing="0" class="tableBound"
           bordercolor="#999999" width="100%">
      <tr>
       <td>
        <fieldset>
        <legend><font color="blue">&#272;i&#7873;u ki&#7879;n t&#236;m ki&#7871;m </font></legend>
        <div>
          <table  class="data-grid" id="data-grid" 
                                              style="width:100%" border="0"
                                             cellspacing="0" cellpadding="2" >
              <tr>
                <td width="15%" align="right" bordercolor="#e1e1e1">
                  <fmt:message key="doi_chieu.page.label.tracuu.kbtinh"/>&nbsp;
                </td>
                <td width="20%">
                  <html:select property="nhkb_tinh" styleId="nhkb_tinh" style="font-size:12px;width:100%"  onchange="getTenKhoBacDC('','')"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"> 
                          <%if(request.getAttribute("dftinh") != null){
                           %>
                              <html:option value="">---<fmt:message key="doi_chieu.page.label.tracuu.default"/>---</html:option>
                          <%}%>                      
                      <html:optionsCollection  name="dmuckb_tinh" value="id_cha" label="kb_tinh"/>                    
                  </html:select>
                  </td>
                <td width="15%" align="right" bordercolor="#e1e1e1">
                  <fmt:message key="doi_chieu.page.label.tracuu.kbhuyen"/>&nbsp;
                </td>
                <td width="30%">
                  <html:select property="nhkb_huyen" styleId="nhkb_huyen" style="font-size:12px;width:80%" onchange="nhkb_huyenval();"
                                onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                              
                              <html:option value="">---<fmt:message key="doi_chieu.page.label.tracuu.default"/>---</html:option>
                              
                </html:select>
                </td>
                <td  align="center" rowspan="3" width="15%">
                  <%if(request.getAttribute("dchieu3") != null){
                  %>
                  
                  <button type="button" onclick="callLov()" class="ButtonCommon" accesskey="t" >
                          <span class="sortKey">D</span>anh m&#7909;c KB
                  </button> <p/>
                  <%}%>
                  <button type="button" onclick="check('find');" class="ButtonCommon" accesskey="t" >
                          <span class="sortKey">T</span>&igrave;m kiếm
                  </button>               

               </td>
              </tr>
              <tr>
                <td align="right" bordercolor="#e1e1e1">
                  <fmt:message key="doi_chieu.page.label.tracuu.landc"/>&nbsp;
                </td>
                <td>
                  <html:select property="lan_dc" styleId="lan_dc" onchange="landcval()" style="width: 100%;font-size:12px"
                                onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                      <html:option value="">---Ch&#7885;n lo&#7841;i &#273;&#7889;i chi&#7871;u---</html:option>
                      <html:option value="1">Đối chiếu lần 1</html:option>
                      <!--<html:option value="2">Đối chiếu lần 2</html:option>-->
                  </html:select>
                </td>
                <td align="right" bordercolor="#e1e1e1">
                    <fmt:message key="doi_chieu.page.lable.qldc.htnh"/>
                  </td>
                  <td >
                    <html:select property="ma_dv" styleId="ngan_hang" onchange="nhangval()"
                             style="width: 80%;font-size:12px"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                      <html:option value="">-----Ch&#7885;n ng&#226;n h&#224;ng-----</html:option>
                      <html:optionsCollection  name="dmucNH" value="ma_dv" label="ten_nh"/>
                    </html:select>
                </td>      
              </tr>
              <tr>
              
              <td align="right" bordercolor="#e1e1e1">
                  Phạm vi đối chiếu &nbsp;
                </td>
                <td>
                  <html:select property="pham_vi_doi_chieu" styleId="pham_vi_doi_chieu" onchange="landcval()" style="width: 100%;font-size:12px"
                                onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                      <html:option value="">Tất cả</html:option>
                      <html:option value="1">Đối chiếu đơn vị</html:option>
                      <html:option value="3">Đối chiếu toàn quốc</html:option>
                  </html:select>
                </td>
              
                
                  <td align="right" bordercolor="#e1e1e1">
                    <fmt:message key="doi_chieu.page.label.tracuu.tthai"/>&nbsp;
                  </td>
                  <td>
                    <html:select property="tthai_dxn_thop" styleId="tthai_dxn_thop" style="font-size:12px;width:80%" onchange="tthaival()"
                                 onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                        <html:option value="00">TTSP: Ch&#432;a &#273;&#7889;i chi&#7871;u</html:option>
                        <html:option value="0101">TTSP: Ch&#234;nh l&#7879;ch - Ch&#7901; duy&#7879;t</html:option>
                        <html:option value="0102">TTSP: Ch&#234;nh l&#7879;ch - &#272;&#227; duy&#7879;t</html:option>
                        <html:option value="0201">TTSP: Kh&#7899;p &#273;&#250;ng - Ch&#7901; duy&#7879;t</html:option>
                        <html:option value="0202">TTSP: Kh&#7899;p &#273;&#250;ng - &#272;&#227; duy&#7879;t</html:option>
                        <html:option value="0000">ĐNQT: Ch&#432;a &#273;&#7889;i chi&#7871;u</html:option>
                        <html:option value="0301">ĐNQT: Ch&#7901; duy&#7879;t</html:option>
                        <html:option value="0302">ĐNQT: &#272;&#227; duy&#7879;t</html:option>
                        <html:option value="">T&#7845;t c&#7843;</html:option>
                    </html:select>
                  </td>
              </tr>
              <tr>
                  <td align="right" bordercolor="#e1e1e1">
                  Trạng thái tài khoản
                  </td>
                  <td>
                     <select id="trang_thai_tk" style="width: 50%;font-size:12px"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">
                             <option value="01">Hoạt Động</option>
                        <option value="">Tất cả</option>
                        <option value="02">Ngưng Hoạt Động</option>
                      </select>
                  
                  </td>
                  <td align="right" bordercolor="#e1e1e1">
                  T&#7915; <fmt:message key="doi_chieu.page.label.tracuu.ngay"/>&nbsp;
                  </td>
                  
                  <td >
                    
                <html:text property="tu_ngay" styleId="tu_ngay" styleClass="fieldText" 
                        onkeypress="return numbersonly(this,event,true) "
                       onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('tu_ngay');"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width:30%"
                       tabindex="107" />
                  <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="tu_ngay_btn"
                     style="vertical-align:middle;width:20"/>   
                    <script type="text/javascript">
                      Calendar.setup( {
                          inputField : "tu_ngay", // id of the input field
                          ifFormat : "%d/%m/%Y", // the date format
                          button : "tu_ngay_btn"// id of the button
                      });
                    </script> &nbsp;&nbsp;&nbsp;

                     
                  </td>
                  
              </tr>
              <tr>
              <td align="right">Loại tiền</td>
                  <td>
                      <html:select property="ma_nt" styleId="ma_nt" style="width: 40%;font-size:12px"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                        <html:option value="VND">VND</html:option>
                        <html:optionsCollection name="dmucTien" value="ma" label="ma"/>
                      </html:select>
                  </td>
                <td align="right" bordercolor="#e1e1e1">
                    &#272;&#7871;n  <fmt:message key="doi_chieu.page.label.tracuu.ngay"/>&nbsp;
                  </td>
                 <td>               
                <html:text property="den_ngay" styleId="den_ngay" styleClass="fieldText" 
                        onkeypress="return numbersonly(this,event,true) "
                       onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('den_ngay');"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width:30%"
                       tabindex="107" />
                  <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="den_ngay_btn"
                     style="vertical-align:middle;width:13"/>   
                    <script type="text/javascript">
                      Calendar.setup( {
                          inputField : "den_ngay", // id of the input field
                          ifFormat : "%d/%m/%Y", // the date format
                          button : "den_ngay_btn"// id of the button
                      });
                    </script>
                </td>
              </tr>
           </table>
         </div>
        </fieldset>
       </td>
      </tr>
      <%
        com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
      int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
      %>
      <tr>
        <td>
         <fieldset>
            <legend>K&#7871;t qu&#7843; t&#236;m ki&#7871;m</legend>
            <div>
              <table width="100%" cellspacing="0" cellpadding="2" class="navigateable focused"
                 bordercolor="#e1e1e1" border="1" align="center"
                 style="BORDER-COLLAPSE: collapse">
                <thead>
                <th class="promptText" bgcolor="#f0f0f0" width="3%">
                  <div align="center" >
                    STT
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="10%">
                  <div align="center" >
                    <fmt:message key="doi_chieu.page.label.tracuu.kbtinh"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="15%">
                  <div align="center">
                    <fmt:message key="doi_chieu.page.label.tracuu.kbhuyen"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="10%">
                  <div align="center">
                    <fmt:message key="doi_chieu.page.label.tracuu.nhang"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="5%">
                  <div align="center">
                    Phạm vi đối chiếu
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="5%">
                  <div align="center">
                    <fmt:message key="doi_chieu.page.label.tracuu.landc"/>
                  </div>
                </th>                
                <th class="promptText" bgcolor="#f0f0f0" width="10%">
                  <div align="center">
                    <fmt:message key="doi_chieu.page.label.tracuu.ngaydc"/>
                  </div>
                </th>                
                <th class="promptText" bgcolor="#f0f0f0" width="10%">
                  <div align="center">
                    <!-- <fmt:message key="doi_chieu.page.label.tracuu.ngaythien"/> -->
                    Trạng Thái TK
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="18%">
                  <div align="center">
                    <fmt:message key="doi_chieu.page.label.tracuu.tthai"/> TTSP
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="19%">
                  <div align="center">
                    <fmt:message key="doi_chieu.page.label.tracuu.tthai"/> ĐNQT
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="5%">
                  <div align="center" >
                    <fmt:message key="doi_chieu.page.label.tracuu.dxnthop"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="5%">
                  <div align="center" >
                    <fmt:message key="doi_chieu.page.label.tracuu.ttsp"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="5%">
                  <div align="center">
                    Loại đối chiếu
                  </div>
                </th>
              <tbody class="navigateable focused" cellspacing="0" style="width:100%" cellpadding="1" bordercolor="#e1e1e1">
              
              <logic:notEmpty name="colTHBKDC">
              <logic:present name="colTHBKDC" >          
                <logic:iterate id="items" name="colTHBKDC" indexId="stt">
                <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                  <td align="center"> 
                    <%=stt+1+rowBegin%>
                  </td>
                  <td>
                    <bean:write name="items" property="nhkb_tinh"/>
                  </td>
                  <td>
                  <div title="<bean:write name="items" property="nhkb_huyen"/>" style="text-overflow:ellipsis;white-space:nowrap;  width:100px; overflow:hidden; font-size:11px">
                    <bean:write name="items" property="nhkb_huyen"/>
                    </div>
                  </td>
                  <td>
                  <div title="<bean:write name="items" property="ten"/>" style="text-overflow:ellipsis;white-space:nowrap;  width:130px; overflow:hidden; font-size:11px">
                    <bean:write name="items" property="ten"/>
                  </div>
                  </td>                 
                  <td align="center">
                    <logic:equal value="1" property="pham_vi_doi_chieu" name="items">
                        Đơn vị
                      </logic:equal>
                      <logic:equal value="2" property="pham_vi_doi_chieu" name="items">
                        Toàn quốc
                      </logic:equal>
                      <logic:equal value="3" property="pham_vi_doi_chieu" name="items">
                        Toàn quốc
                      </logic:equal>                    
                    <logic:equal value="97" property="trang_thai_bk" name="items">
                        Đơn vị
                      </logic:equal>
                      <logic:equal value="98" property="trang_thai_bk" name="items">
                        Toàn quốc
                      </logic:equal>
                      <logic:equal value="99" property="trang_thai_bk" name="items">
                        Toàn quốc
                      </logic:equal>
                  </td>                 
                  <td align="center">
                  <bean:write name="items" property="lan_dc"/>
                    <logic:equal value="97" property="trang_thai_bk" name="items">
                        1
                      </logic:equal>
                      <logic:equal value="98" property="trang_thai_bk" name="items">
                        2
                      </logic:equal>
                      <logic:equal value="99" property="trang_thai_bk" name="items">
                        3
                      </logic:equal>

                    
                  </td>
                  <td align="center">
                    <bean:write name="items" property="ngay_dc"/>
                  </td>
                  <td align="center">
                    <!-- <bean:write name="items" property="ngay_thien_dc"/> -->
                    
                    <logic:equal value="01" property="trang_thai_tk" name="items">
                        Hoạt Động
                      </logic:equal>
                      <logic:equal value="02" property="trang_thai_tk" name="items">
                        Ngưng Hoạt động
                      </logic:equal>
                      <logic:equal value="" property="trang_thai_tk" name="items">
                         
                      </logic:equal>
                  </td>
                  <td align="center">
                    
                      <logic:equal value="99" property="trang_thai_bk" name="items">
                        <fmt:message key="doi_chieu.page.lable.null"/>
                      </logic:equal>
                      <logic:equal value="98" property="trang_thai_bk" name="items">
                        <fmt:message key="doi_chieu.page.lable.null"/>
                      </logic:equal>
                      <logic:equal value="97" property="trang_thai_bk" name="items">
                        <fmt:message key="doi_chieu.page.lable.null"/>
                      </logic:equal>
                    
                    <logic:equal value="00" property="trang_thai_kq" name="items">
                         <!-- <fmt:message key="doi_chieu.page.lable.00"/>  -->
                         <logic:notEqual value="04" property="trang_thai_kq" name="items">
                          <logic:equal value="00" property="trang_thai_bk" name="items">
                            <fmt:message key="doi_chieu.page.lable.00"/> 
                          </logic:equal>
                          <logic:equal value="01" property="trang_thai_bk" name="items">
                            <fmt:message key="doi_chieu.page.lable.bk01"/>- Chưa tạo ĐXN
                          </logic:equal>
                          <logic:equal value="02" property="trang_thai_bk" name="items">
                            <fmt:message key="doi_chieu.page.lable.bk02"/>- Chưa tạo ĐXN 
                              
                          </logic:equal>
                        </logic:notEqual>
                    </logic:equal>
                    <logic:notEqual value="00" property="trang_thai_kq" name="items">
                        <logic:notEqual value="04" property="trang_thai_kq" name="items">
                          <logic:equal value="00" property="trang_thai_bk" name="items">
                            <fmt:message key="doi_chieu.page.lable.00"/> 
                          </logic:equal>
                          <logic:equal value="01" property="trang_thai_bk" name="items">
                            <fmt:message key="doi_chieu.page.lable.bk01"/>- 
                          </logic:equal>
                          <logic:equal value="02" property="trang_thai_bk" name="items">
                            <fmt:message key="doi_chieu.page.lable.bk02"/>-
                          </logic:equal>
                        </logic:notEqual>
                      <logic:equal value="04" property="trang_thai_kq" name="items">
                         Kh&#244;ng duy&#7879;t
                     </logic:equal>
                    </logic:notEqual>
                    <logic:notEqual value="00" property="trang_thai_bk" name="items">
                        <logic:equal value="01" property="trang_thai_kq" name="items">
                          Ch&#7901; duy&#7879;t
                        </logic:equal>
                        <logic:equal value="02" property="trang_thai_kq" name="items">
                          &#272;&#227; duy&#7879;t
                        </logic:equal>
                        <!--<logic:equal value="04" property="trang_thai_kq" name="items">
                          Kh&#244;ng duy&#7879;t
                        </logic:equal>-->
                    </logic:notEqual>
                    
                  </td>
                  <td align="center"> 
                    <logic:equal name="items" property="loai_dc" value="1">
                      
                      <logic:equal name="items" property="tthai_dxn_thop" value="">
                        <fmt:message key="doi_chieu.page.lable.00"/> &#272;XN t&#7893;ng h&#7907;p 
                      </logic:equal>
                      <logic:equal name="items" property="tthai_dxn_thop" value="00">
                        <fmt:message key="doi_chieu.page.lable.00"/> &#272;XN t&#7893;ng h&#7907;p 
                      </logic:equal>
                      <logic:equal name="items" property="tthai_dxn_thop" value="01">
                        <!-- <bean:write name="items" property="ket_qua_dxn_thop"/> -->
                        <logic:equal name="items" property="ket_qua_dxn_thop" value="0">Đã xác nhận</logic:equal>
                        <logic:equal name="items" property="ket_qua_dxn_thop" value="1">Đã tạo điện 066</logic:equal>
                      </logic:equal>
                      <logic:equal name="items" property="tthai_dxn_thop" value="02">Đã duyệt điện 066</logic:equal>
                      <logic:equal name="items" property="tthai_dxn_thop" value="03">Đã duyệt xác nhận</logic:equal>
                      <logic:equal name="items" property="tthai_dxn_thop" value="04">NT yếu-Không có trạng thái</logic:equal> 
                    </logic:equal>
                  </td>
                  <td align="center">
                   <logic:equal value="1" property="loai_dc" name="items">
                    <logic:notEqual value="" property="kq_id" name="items">
                      <logic:notEqual value="00" property="tthai_dxn_thop" name="items">
                        <a href="<html:rewrite page="/ViewTTinDChieuAction.do"/>?bkq_id=<bean:write name="items" property="kq_id"/>&receive_bank=<bean:write name="items" property="receive_bank"/>&send_bank=<bean:write name="items" property="send_bank"/>&ngay_dc=<bean:write name="items" property="ngay_dc"/>&ma_nt=<bean:write name="items" property="ma_nt"/>&type=kq<%=tcuu%>&loai_tien=<bean:write name="items" property="loai_tien"/>">view</a> 
                      </logic:notEqual>
                    </logic:notEqual>
                   </logic:equal>
                   <logic:equal value="3" property="loai_dc" name="items">
                     <logic:notEqual value="" property="bk_id" name="items">
                      <a href="<html:rewrite page="/ViewTTinDChieu3Action.do"/>?id=<bean:write name="items" property="bk_id"/>&loai_dc=3&receive_bank=<bean:write name="items" property="receive_bank"/><%=tcuu%>&loai_tien=<bean:write name="items" property="loai_tien"/>">view</a> 
                     </logic:notEqual>
                   </logic:equal>
                  </td>
                  <td align="center">
                   <logic:equal value="1" property="loai_dc" name="items">
                    <logic:notEqual value="" property="bk_id" name="items">
                      <logic:notEqual value="" property="kq_id" name="items">
                        <a href="<html:rewrite page="/ViewTTinDChieuAction.do"/>?bk_id=<bean:write name="items" property="bk_id"/>&bkq_id=<bean:write name="items" property="kq_id"/>&trang_thai_kq=<bean:write name="items" property="trang_thai_kq"/>&receive_bank=<bean:write name="items" property="receive_bank"/>&ma_nt=<bean:write name="items" property="ma_nt"/>&type=bk<%=tcuu%>&loai_tien=<bean:write name="items" property="loai_tien"/>">view</a> 
                      </logic:notEqual>
                    </logic:notEqual>
                   </logic:equal>
                  </td>
                  <td align="center">               
                    <logic:equal value = "TUDONG" property="tt_dc_tu_dong" name="items">
                      Tự Động
                    </logic:equal>                    
                    <logic:equal  value = "THUCONG" property="tt_dc_tu_dong" name="items">
                      Thủ Công
                    </logic:equal>
                    <logic:equal  value = "" property="tt_dc_tu_dong" name="items">
                      
                    </logic:equal>
                  </td>
                </tr>
                </logic:iterate>
                </logic:present>
              </logic:notEmpty>
              <logic:empty name="colTHBKDC">
                <tr>
                <td colspan="13">
                  <font color="red"><fmt:message key="doi_chieu.page.label.tracuu.empty"/></font>
                </td>
                </tr>
               </logic:empty> 
              </tbody>
              </table>
            </div>
          </fieldset>
          </td>
      </tr>
      <tr>
          <td >
          T&#7893;ng s&#7889; b&#7843;n ghi: <html:text property="tong_row" readonly="true" style="text-align:left; border:0;width:140px;font-weight:bold;font-size:11px" value="0" styleId="tong_row"/>
          <html:text property="currentPage" onkeydown="arrowUpDownTCuuDC(event)" style="text-align:right; border:1;width:20px;font-weight:bold;font-size:11px" value="0" styleId="currentPage"/>/<html:text property="total_page" readonly="true" style="text-align:left; border:0;width:20px;font-weight:bold;font-size:11px" value="0" styleId="total_page"/>
          <a href="#" onclick="goPage()">&#272;&#7871;n</a>
          </td>
      </tr>
      <tr>
        <td align="right">
          <button type="button" onclick="check('close')" class="ButtonCommon" accesskey="o">
                          Th<span class="sortKey">o</span>&#225;t
          </button>
        </td>
      </tr>
      
    </table> 
    <html:hidden property="pageNumber" value="1"/>
    <html:hidden property="nhkb_huyen" styleId="a"/>

  </html:form>
</div>
<div id="dialog-form-lov-dm" title="Tra c&#7913;u danh m&#7909;c Kho b&#7841;c">
  <p class="validateTips"></p>
  <%@include file="/pages/lov/lovDMKBTCUU.jsp" %>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
  var f = document.forms[0];

  function check(type) {  
     if (type == 'find') {
//        var kbtinh = jQuery('#nhkb_tinh option:selected').val();
//        if (kbtinh =="" || kbtinh == null){
//          alert('Chọn kho bạc tỉnh cần tra cứu.');
//          return;
//        }

//        var kbhuyen = jQuery('#nhkb_huyen option:selected').val();
//        if (kbhuyen =="" || kbhuyen == null){
//          alert('Chọn kho bạc huyện cần tra cứu.');
//          return;
//        }
        var inKB = jQuery('#nhkb_huyen option:selected').index();        
        var temp = document.getElementById("trang_thai_tk");
        var trang_thai_tk = temp.options[temp.selectedIndex].value;
        var inNH = jQuery('#ngan_hang option:selected').index() ;
        var inxtthai = jQuery('#tthai_dxn_thop option:selected').index();
        var lan_dc = jQuery('#lan_dc option:selected').val();
        f.action = 'FindTTinDChieuAction.do?inKB='+inKB+"&inNH="+inNH+"&lan_dc="+lan_dc+"&inxtthai="+inxtthai+"&trang_thai_tk="+trang_thai_tk;
        
      }
     if (type == 'close') {
        f.action = 'mainAction.do';          
      } 
       f.submit();
    }
    function nhkb_huyenval() {
      var nhkb_huyen;
      nhkb_huyen=  document.getElementById("nhkb_huyen").value;     
      return nhkb_huyen;
  }
      function tthaival() {
      var tthai;
      tthai=document.getElementById("tthai_dxn_thop").value;
      return tthai;
  }
      function landcval() {
      var lan_dc;
      lan_dc=document.getElementById("lan_dc").value;
      tthai_sel(lan_dc);
      return lan_dc;
      
  }
      function nhangval() {
      var ngan_hang;
      ngan_hang=document.getElementById("ngan_hang").value;
      return ngan_hang;
  }
    function callLov(){      
      jQuery("#loai_lov").val("DMKBTCUU");
      jQuery("#ma_field_id_lov").val("ma_nhkb_nhan");
      jQuery("#ten_field_id_lov").val("ten_nhkb_nhan");
      jQuery("#id_field_id_lov").val("id_nhkb_huyen");
      jQuery("#id_cha_field_id_lov").val("id_nhkb_tinh");
      jQuery("#dialog-form-lov-dm").dialog( "open" );
      
    }
  
  
   function tthai_sel(lan_dc){
     var inxtthai="<%=inxtthai%>";
     if(lan_dc=='1'||lan_dc==''){
            var dataArr = [{'value':'00','text':'TTSP: Chưa đối chiếu'},
                 {'value':'0101','text':'TTSP: Chênh lệch - Chờ duyệt'},
                 {'value':'0102','text':'TTSP: Chênh lệch - Đã duyệt'},
                 {'value':'0201','text':'TTSP: Khớp đúng - Chờ duyệt'},
                 {'value':'0202','text':'TTSP: Khớp đúng - Đã duyệt'},
                 {'value':'0000','text':'ĐNQT: Chưa đối chiếu'},
                 {'value':'0301','text':'ĐNQT: Chờ duyệt'},
                 {'value':'0302','text':'ĐNQT:  Đã duyệt'},
                 {'value':'','text':'Tất cả'}];
            
            // Removes all options for the select box
            jQuery('#tthai_dxn_thop option').remove();
            
            // .each loops through the array
            jQuery.each(dataArr, function(i){
            jQuery('#tthai_dxn_thop').append(jQuery("<option></option>").attr("value",dataArr[i]['value']).text(dataArr[i]['text']));
            });    
        }else if(lan_dc=='2'||lan_dc=='3'){
            var dataArr = [{'value':'00','text':'TTSP: Chưa đối chiếu'},
                 {'value':'0101','text':'TTSP: Chênh lệch - Chờ duyệt'},
                 {'value':'0102','text':'TTSP: Chênh lệch - Đã duyệt'},
                 {'value':'0201','text':'TTSP: Khớp đúng - Chờ duyệt'},
                 {'value':'0202','text':'TTSP: Khớp đúng - Đã duyệt'},
                 {'value':'','text':'Tất cả'}];
            
            // Removes all options for the select box
            jQuery('#tthai_dxn_thop option').remove();
            
            // .each loops through the array
            jQuery.each(dataArr, function(i){
            jQuery('#tthai_dxn_thop').append(jQuery("<option></option>").attr("value",dataArr[i]['value']).text(dataArr[i]['text']));
            });
   }
      jQuery('#tthai_dxn_thop option:eq('+inxtthai+')').attr('selected', true);

}

function goPage() {
      //var f = document.forms[0];
      var page = parseInt(jQuery('#currentPage').val());
      var total_page = jQuery('#total_page').val();
      if(page<1 || page >total_page){
      alert(GetUnicode('C&#7847;n nh&#7853;p &#273;&#250;ng s&#7889; trang c&#7847;n t&#236;m.'));
      jQuery('#currentPage').focus();
      return false;
      } else if(page>=1 && page <=total_page){
      
      f.pageNumber.value = page;
      var inxtthai="<%=inxtthai%>";
      var initAction ="<%=initAction%>";
      
      if(initAction==null || ""==initAction){
        f.action = 'FindTTinDChieuAction.do?inxtthai='+inxtthai;   
      }else if(initAction!=null && ""!=initAction){
        f.action = 'TCuuTTinDChieuAction.do'; 
      }
      f.submit();
      }
  }
  function getTenKhoBacDC(id,id_cha) { 
    var dchieu3="<%=dchieu3%>";
      document.getElementById('nhkb_huyen').options.length = 1;// clear du lieu option cu
       var kb_id;
       if(dchieu3!=null && ''!=dchieu3){
//       alert('id='+id+'id_cha='+id_cha);
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
         }else if(dchieu3==null || ''==dchieu3){
          kb_id=document.forms[0].nhkb_tinh.value;
         }
      var kb_huyen="<%=kb_huyen%>";   
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
  
function arrowUpDownTCuuDC(e) {
    var keyCode = e.keyCode || e.charCode, arrow = {
        up : 38, down : 40, enter : 13, esc : 27
    };
     var page = jQuery('#currentPage').val();
     var total_page = jQuery('#total_page').val();
    switch (keyCode) {
        case arrow.up:
            
              page = parseInt(page) + 1;
              jQuery('#currentPage').val(page);            
              if(parseInt(jQuery('#currentPage').val())>parseInt(total_page)){
              jQuery('#currentPage').val(total_page);
              return false;
              }
              break;

        case arrow.down:
              page = page - 1;
              jQuery('#currentPage').val(page);
              if(jQuery('#currentPage').val()<1){
                jQuery('#currentPage').val('1');
                return false;
                }
            break;
        case arrow.enter:
            goPage();
            break;
//        case arrow.esc:
////            var idTRSelected = jQuery("tr[class='ui-row-ltr ui-state-highlight']").attr('id');
////            jQuery('#' + idTRSelected).click();
////            rowSelectedFocusDC(jQuery("tr[class='ui-row-ltr ui-state-highlight']").attr('id'));
//            break;
    }

}
  
  

</script>
