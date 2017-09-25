function divPrint2() {
    // Some logic determines which div should be printed...
    // This example uses div3.
    $("#div_print").addClass("printable");
    window.print();
}

function divPrint() {
    // Some logic determines which div should be printed...
    // This example uses div3.
    //$("#div_print").printElement();//cach cu
    var divTopBar = document.getElementById("top-bar");
    divTopBar.style.visibility = "hidden";
    printPreview();
    divTopBar.style.visibility = "visible";
}

function submitSaveRpt(action, type, requestParam) {
    var f = document.forms[0];
    actionUrl = action + "?action=" + type
    if (requestParam != null && requestParam != "")
        actionUrl += "&" + requestParam;
    f.action = actionUrl;
    f.submit();
}

function printPreview() {

    try {
        var OLECMDID = 7;
        /* OLECMDID values:
         * 6 - print
         * 7 - print preview
         * 1 - open window
         * 4 - Save As
                */
        var PROMPT = 2;// 2 DONTPROMPTUSER  
        var WebBrowser = '<OBJECT ID="WebBrowser1" CLASSID="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></OBJECT>';
        document.body.insertAdjacentHTML('beforeEnd', WebBrowser);
        WebBrowser1.ExecWB(OLECMDID, PROMPT);
        WebBrowser1.outerHTML = "";
        //        divTopBar.style.visibility = "visible";
        //      window.print();
    }
    catch (e) {
        //        //alert(e);
        //        divTopBar.style.visibility = "visible";
        //        $("#div_print").printElement();
    }
}