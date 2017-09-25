function resetFormDMCheoKH() {
    jQuery("#tk_timkiem").attr( {
        maxlength : 4
    }).val('').focus();
}

function resetFormThemTK() {
    jQuery("#tk").attr( {
        maxlength : 4
    }).val('').focus();
}

function validTK() {
    var taikhoan = jQuery("#tk_timkiem").val();
    if (taikhoan.length >= 4) {
        alert('S? tài kho?n không ???c l?n h?n 4 kí t?');
    }

}

function fillDataEditForm() {
    if (jQuery("#tk").val() != "" && jQuery("#tk").val() != null) {
        alert('b');
        jQuery("#tk").val('<%=request.getAttribute("tk_field")%>');
        jQuery("#ma_chuon").val('<%=request.getAttribute("ma_chuong_field")%>');
        jQuery("#ma_ctmt").val('<%=request.getAttribute("ma_ctmt_field")%>');
        jQuery("#ma_diaban").val('<%=request.getAttribute("ma_diaban_field")%>');
        jQuery("#ma_dphong").val('<%=request.getAttribute("ma_dphong_field")%>');
        jQuery("#ma_dvsdns").val('<%=request.getAttribute("ma_dvsdns_field")%>');
        jQuery("#ma_ndkt").val('<%=request.getAttribute("ma_ndkt_field")%>');
        jQuery("#ma_nganh").val('<%=request.getAttribute("ma_nganh_field")%>');
        jQuery("#ma_nguon").val('<%=request.getAttribute("ma_nguon_field")%>');
        jQuery("#ma_quy").val('<%=request.getAttribute("ma_quy_field")%>');
        jQuery("#ma_cap").val('<%=request.getAttribute("ma_cap_field")%>');
    }

}