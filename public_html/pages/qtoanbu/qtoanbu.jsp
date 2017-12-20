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
<link rel="stylesheet" type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet" type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet" type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<style type="text/css">
     .right { text-align: right; } 
     TH {background-color:#dfeffc}
     .row-active {background-color : #dfeffc}
</style>
<script type="text/javascript">

    jQuery.noConflict();
    //************************************ LOAD PAGE **********************************
    jQuery(document).init(function () {
        jQuery("#tableLeft tr").click(function () {
            jQuery('#tableLeft tr').removeClass("row-active");
            jQuery(this).addClass("row-active");
        });
        jQuery("#tableLeft tr:first").click();
    });

    function print(){
        alert("This feature not available")
    }
    
    function ky(type){
        try {
            if("Y" == strChkKy && type == "2"){
                var cert = jQuery("#eSign")[0];
                var noi_dung_ky = jQuery("#noiDungKy").val();
                cert.InitCert();   
                
                // 20171120 thuongdt bo sung canh bao han su dung CTS
             var strdomain = '<%=strdomain%>';
            var struser_name = '<%=struser_name%>';
           var strcheckcts = '<%=strcheckcts%>';           
            if(!checkCTSdate(cert,strdomain+'/'+struser_name,strcheckcts)){
                 return false;
                }
                
                var serial = cert.Serial;
                var sign = cert.Sign(noi_dung_ky);
                jQuery("#certSerial").val(serial);
                jQuery("#signature").val(sign);   
            }
        return true;
        }catch (e) {
            alert(e.description);
            return false;
        }
    }
    
    function kttDuyetHuy(type,id){
        if(ky(type)){
            var certSerial = jQuery("#certSerial").val();
            var signature = jQuery("#signature").val();
            var noiDungKy = jQuery("#noiDungKy").val();
            jQuery.ajax({
                type:"post",
                url:"duyetDeNghiQToanBu.do",
                data:{
                    "type":type,
                    "id":id,
                    "certSerial":certSerial,
                    "signature":signature,
                    "noiDungKy":noiDungKy
                },
                success:function(data,textstatus){
                    if(textstatus != null && textstatus == 'success'){
                        if(data.error != null){
                            alert(data.error);
                        }else{
                            rowPhiClickEvent(data.ngay_qtoan,data.ma_nh,data.loai_tien);
                        }
                    }
                },
                error:function(textstatus){
                    alert(textstatus);
                }
            });
        }else{
            alert("Ký không thành công");
        }
    }
    function loadDanhSachDeNghiQToan(maNH,ngayDC,loaiTien){
        jQuery("#tableQToanBu").find("tr").remove().end();;
        jQuery.ajax({
            type:"post",
            url:"loadDeNghiQToanBu.do",
            data:{
                "maNH":maNH,
                "ngayDC":ngayDC,
                "loaiTien":loaiTien
            },
            success:function(data,textstatus){
                if(textstatus != null && textstatus == 'success'){
                    if(data.error != null){
                        alert(data.error);
                    }else{
                        for(var i = 0;i<data.length ;i++){
                            var temp1 = '<tr><td align="center">'+data[i].ngay_qtoan+'</td>' +
                                            '<td align="center">'+data[i].id_066+'</td>' +
                                            '<td align="right">'+CurrencyFormatted(data[i].qtoan_thu,data[i].loai_tien)+'</td>' +
                                            '<td align="right">'+CurrencyFormatted(data[i].qtoan_chi,data[i].loai_tien)+'</td>' +
                                            '<td align="center">'+data[i].loai_tien+'</td>';
                            var temp2;
                            if(data[i].trang_thai_066 == '01'){
                                temp2 = '<td align="center">Chờ duyệt</td>';
                            }else if(data[i].trang_thai_066 == '02'){
                                temp2 = '<td align="center" style="color:green">Đã duyệt</td>';
                            }else if(data[i].trang_thai_066 == '03'){
                                temp2 = '<td align="center" style="color:red">Hủy</td>';
                            }else if(data[i].trang_thai_066 == '04'){
                                temp2 = '<td align="center" style="color:green">Gửi NH thành công</td>';
                            }else if(data[i].trang_thai_066 == '05'){
                                temp2 = '<td align="center" style="color:red">Gửi NH không thành công</td>';
                            }else if(data[i].trang_thai_066 == '06'){
                                temp2 = '<td align="center" style="color:red">Hết hiệu lực</td>';
                            }
                            if(data[0].role_nsd == 'KTT' && data[i].trang_thai_066 == '01'){
                                temp2 += '<td><div align="center">'+
                                        '<span><input type="button" value="Duyệt" onclick="kttDuyetHuy(02,'+data[i].id_066+')"/></span>'+
                                        '<span><input type="button" value="Hủy" onclick="kttDuyetHuy(03,'+data[i].id_066+')"/></span>' +
                                        '<input type="hidden" id="noiDungKy" value="'+data[i].noi_dung_ky+'" />'
                                        '</div></td>';
                            }
                            temp1 += temp2;
                            temp1 += '</tr>';
                            jQuery("#tableQToanBu:last").append(temp1);
                        }
                    }
                }
            },
            error:function(textstatus){
                alert(textstatus);
            }
        });
    }
    
    function createOrderQToanBu(){
        var isOpenThamSo = jQuery("#isOpenThamSo").val() == 'true';
        if(isOpenThamSo){
            if(confirm("Bạn có muốn tạo điện quyết toán bù không ")){
                var maNH = jQuery("#maNH").val();
                var ngayDC = jQuery("#ngayDC").val();
                var soDeNghiQToanChiBu = jQuery("#soDeNghiQToanChiBu").val();
                var soDeNghiQToanThuBu = jQuery("#soDeNghiQToanThuBu").val();
                var soDuSauQToan = jQuery("#soDuSauQToan").val();
                var loaiTien = jQuery("#loaiTien").val();
                var hanMucNo = jQuery("#hanMucNo").val();
                
                jQuery.ajax({
                    type:"post",
                    url:"createOrderQToanBu.do",
                    data:{
                        "maNH":maNH,
                        "ngayDC":ngayDC,
                        "soDeNghiQToanChiBu":soDeNghiQToanChiBu,
                        "soDeNghiQToanThuBu":soDeNghiQToanThuBu,
                        "loaiTien":loaiTien,"soDuSauQToan":soDuSauQToan,
                        "hanMucNo":hanMucNo
                    },
                    success:function(data,textstatus){
                        if(textstatus != null && textstatus == 'success'){
                            if(data.error != null){
                                alert(data.error);
                            }else{
                                loadDanhSachDeNghiQToan(maNH,ngayDC,loaiTien);
                                jQuery("#btnCreateQToanBu").attr('disabled','disabled');
                            }
                        }
                    },
                    error:function(textstatus){
                        alert(textstatus);
                    }
                });
            }
        }else{
            alert("Chức năng này chưa được mở cho kho bạc.\n Xin vui lòng liên hệ lên trung ương để được giúp đỡ mở chức năng này.\n Nếu đã được mở xin vui lòng chạy lại trang này");
        }
    }
    
    function rowPhiClickEvent(ngayDC,maNH,loaiTien){
        eraserValLabel();
        jQuery.ajax({
            type:"post",
            url:"loadLabelQToanBu.do",
            data:{
                "ngayDC":ngayDC,
                "maNH":maNH,
                "loaiTien":loaiTien
            },
            success:function(data,textstatus){
                if(textstatus != null && textstatus == 'success'){
                    jQuery("#soDuCOT").val(CurrencyFormatted(data.so_du_cot,loaiTien));
                    jQuery("#soDuSauQToan_formart").val(CurrencyFormatted(data.so_du,loaiTien));
                    jQuery("#soDuSauQToan").val(data.so_du);
                    jQuery("#hanMucNo_formart").val(CurrencyFormatted(data.han_muc_no,loaiTien));
                    jQuery("#hanMucNo").val(data.han_muc_no);
                    jQuery("#soDeNghiQToanChiBu").val(CurrencyFormatted(data.de_nghi_chi_bu,loaiTien));
                    jQuery("#soDeNghiQToanThuBu").val(CurrencyFormatted(data.de_nghi_thu_bu,loaiTien));
                    jQuery("#qToanChi").val(CurrencyFormatted(data.qtoan_chi,loaiTien));
                    jQuery("#qToanThu").val(CurrencyFormatted(data.qtoan_thu,loaiTien));
                    jQuery("#maNH").val(maNH);
                    jQuery("#ngayDC").val(ngayDC);
                    jQuery("#loaiTien").val(loaiTien);
                    loadDanhSachDeNghiQToan(maNH,ngayDC,loaiTien)
                    
                    if(data.de_nghi_chi_bu == "0"){
                       jQuery("#soDeNghiQToanChiBu").attr('readonly', true);
                    }
                    if(data.de_nghi_thu_bu == "0"){
                       jQuery("#soDeNghiQToanThuBu").attr('readonly', true);
                    }
                }
            },
            error:function(textstatus){
                alert(textstatus);
            }
        });
    }
    function eraserValLabel(){
        jQuery("#soDuCOT").val("");
        jQuery("#qToanThu").val("");
        jQuery("#qToanChi").val("");
        jQuery("#soDuSauQToan_formart").val("");
        jQuery("#hanMucNo_formart").val("");
        jQuery("#soDeNghiQToanChiBu").val("");
        jQuery("#soDeNghiQToanThuBu").val("");
    }
    
    function blockWords(event){
        if(event.keyCode >= 48 && event.keyCode <= 57){
            return true;
        }
        return false;
    }
</script>
<html:errors />
<title>Quyết toán bù</title>
<html:form action="/listQtoanBu.do" method="post">
    <input type="hidden" id="certSerial"/>
    <input type="hidden" id="signature"/>
    <object id="eSign" name="eSign" height="31" width="177" classid="CLSID:7525E7C6-84C6-4180-AFA3-A5FED8C8A261" VIEWASTEXT codebase='VSTeTokenSetup.cab'></object>
    <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
        <tr>
        <td width="13">
          <img width="13" height="30"
               src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img>
        </td>
        <td width="75%"
            background="<%=request.getContextPath()%>/styles/images/T2.jpg">
          <span class="title2">Quyết toán bù</span>
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
    <div id="contentLeft" style="width:46%;float:left;height: 300px; overflow: auto">
        <table style="width:99%;margin-left:5px;border: 1px solid #dfeffc;">
            <thead>
              <tr>
                <th style="width:20%">Ngày đối chiếu</th>
                <th style="width:25%">Tên ngân hàng</th>
                <th style="width:20%">Mã ngân hàng</th>
                <th style="width:20%">Số tài khoản</th>
                <th style="width:15%">Loại tiền</th>
              </tr>
            </thead>
            <tbody id="tableLeft">
              <c:if test="${! empty(requestScope.listQToanBu)}">
                <c:forEach var="item" items="${requestScope.listQToanBu}">
                  <tr onclick="rowPhiClickEvent('<c:out value="${item.ngay_gd}"/>','<c:out value="${item.ma_nh}"/>','<c:out value="${item.loai_tien}"/>')">
                    <td align="center"><c:out value="${item.ngay_gd}"/></td>
                    <td align="center"><c:out value="${item.ten_nh}"/></td>
                    <td align="center"><c:out value="${item.ma_nh}"/></td>
                    <td align="center"><c:out value="${item.so_tk}"/></td>
                    <td align="center"><c:out value="${item.loai_tien}"/></td>
                  </tr>
                </c:forEach>
              </c:if>
            </tbody>
        </table>
    </div>
    <div id="contentRight" style="width:53%;float:right">
        <table style="width:99%;margin-right:5px;border: 1px solid #dfeffc;">
            <tbody id="tableRight">
              <tr>
                <td align="center" colspan="2" style="background-color:#dfeffc;font-size:1.2em;font-weight:700;line-height:20px">
                    Số liệu quyết toán ngày <%=com.seatech.framework.utils.StringUtil.getCurrentDate()%>
                </td>
              </tr>
              <tr>
                <td align="right" colspan="2">&nbsp;</td>
              </tr>
              <tr>
                <td align="right">Số dư tại thời điểm COT</td>
                <td><html:text property="soDuCOT" styleId="soDuCOT" readonly="true" styleClass="right" /></td>
              </tr>
              <tr>
                <td align="right">Quyết toán thu</td>
                <td><html:text property="quyetToanThu" styleId="qToanThu" readonly="true" styleClass="right"/></td>
              </tr>
              <tr>
                <td align="right">Quyết toán chi</td>
                <td><html:text property="quyetToanChi" styleId="qToanChi" readonly="true" styleClass="right"/></td>
              </tr>
              <tr>
                <td align="right">Số dư sau quyết toán</td>
                <td>
                  <input type="text" id="soDuSauQToan_formart" readonly="true" class="right"/>
                  <html:hidden property="soDuSauQToan" styleId="soDuSauQToan" styleClass="right"/>
                </td>
              </tr>
              <tr>
                <td align="right">Hạn mức dư nợ</td>
                <td>
                  <input type="text" id="hanMucNo_formart" readonly="true" class="right"/>
                  <html:hidden property="hanMucNo" styleId="hanMucNo" styleClass="right"/>
                </td>
              </tr>
              <tr>
                <td align="right">Số đề nghị quyết toán chi bù</td>
                <td>
                  <html:text property="soDeNghiQToanChiBu" styleId="soDeNghiQToanChiBu" styleClass="right" onkeypress="return blockWords(event)" />
                  <html:hidden property="soDeNghiQToanChiBu_old" styleId="soDeNghiQToanChiBu_old" styleClass="right"/>
                </td>
              </tr>
              <tr>
                <td align="right">Số đề nghị quyết toán thu bù</td>
                <td>
                  <html:text property="soDeNghiQToanThuBu" styleId="soDeNghiQToanThuBu" styleClass="right" onkeypress="return blockWords(event)" />
                  <html:hidden property="soDeNghiQToanThuBu_old" styleId="soDeNghiQToanThuBu_old" styleClass="right"/>
                </td>
              </tr>
              <tr>
                <td align="right" colspan="1"><input type="hidden" id="loaiTien"/></td>
                <td align="right" colspan="1"><input type="hidden" id="ngayDC"/></td>
                <td align="right" colspan="1"><input type="hidden" id="maNH"/></td>
              </tr>
            </tbody>
        </table>
        <div align="center" style="margin-top:20px">
          <c:if test="${sessionScope.role_list == 'TTV'}">
            <span>
              <input type="button" onclick="createOrderQToanBu()" value="Tạo đề nghị" id="btnCreateQToanBu"/>
              <input id="isOpenThamSo" type="hidden" value="<c:out value="${requestScope.isOpenThamSo}"/>"/>
            </span>
          </c:if>
          <!--<span><input type="button" onclick="print()" value="In"/></span>-->
        </div>
    </div>
    <div id="contentBottom" style="width:100%;margin-top:5px;float:left">
        <table style="width:99%;margin-left:5px;border: 1px solid #dfeffc;">
            <thead>
              <tr>
                <c:if test="${sessionScope.role_list == 'KTT'}"><th colspan="7">Danh sách điện đề nghị quyết toán</th></c:if>
                <c:if test="${sessionScope.role_list == 'TTV'}"><th colspan="6">Danh sách điện đề nghị quyết toán</th></c:if>
              </tr>
              <tr>
                <th style="width:10%">Ngày quyết toán</th>
                <c:if test="${sessionScope.role_list == 'KTT'}">
                  <th style="width:15%">Số điện</th>
                  <th style="width:20%">Quyết toán thu</th>
                  <th style="width:20%">Quyết toán chi</th>
                </c:if>
                <c:if test="${sessionScope.role_list == 'TTV'}">
                    <th style="width:20%">Số điện</th>
                    <th style="width:25%">Quyết toán thu</th>
                    <th style="width:25%">Quyết toán chi</th>
                </c:if>
                <th style="width:10%">Loại tiền</th>
                <th style="width:10%">Trạng thái</th>
                <c:if test="${sessionScope.role_list == 'KTT'}"><th style="width:15%">#</th></c:if>
              </tr>
            </thead>
            <tbody id="tableQToanBu">
            </tbody>
        </table>
    </div>
</html:form>
<%@ include file="/includes/ttsp_bottom.inc"%>