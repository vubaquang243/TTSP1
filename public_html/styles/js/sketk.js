function fillSoTK() {
    getTenNganHang('manh', 'tennh', '');
    setTimeout(function () {
        var url = "skeTKAction.do";
        document.forms[0].action = url;
        document.forms[0].submit();
    },
300)
}

