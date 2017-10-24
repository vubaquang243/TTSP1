
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    
    <SCRIPT type=text/javascript 
  src="http://localhost:7101/TTSP/styles/js/jquery.htm"></SCRIPT>
    
</head>
<body>
    <object id="eSign" name="eSign" height="31" width="177" classid="CLSID:7525E7C6-84C6-4180-AFA3-A5FED8C8A261" VIEWASTEXT codebase='VSTeTokenSetup.cab'></object>    
    <form id="form1">
    
    <input type="text" id="t1" name="t1" /><br/>
    <input type="text" id="t2" name="t2" /><br/>
    <input type="text" id="t3" name="t3" /><br/>
    <input type="text" id="t4" name="t4" /><br/>
    <input type="text" id="t5" name="t5" /><br/>
    <textarea cols="10" rows="10" id="noidung" name="noidung"></textarea><br/>
    
	<input type="button" onclick="getCertInf();" value="OK" />
    </form>
    
    <script type="text/javascript">
    	function getCertInf(){
    		
                try {
                    //var cert = document.getElementById("eSign").value;
                    var cert = $("#eSign")[0];
                    cert.InitCert();
                    document.getElementById("t1").value = cert.Serial;
                    document.getElementById("t2").value = cert.CertContent;
                    document.getElementById("t3").value = cert.IssuedTo;
                    document.getElementById("t4").value = cert.IssuedBy;
                    document.getElementById("t5").value = cert.ValidFrom;
                    //$("#t1").val(cert.Serial);
                    //$("#t2").val(cert.CertContent);
                    //$("#t3").val(cert.IssuedTo);
                    //$("#t4").val(cert.IssuedBy);
                    //$("#t5").val(cert.ValidFrom);
                }
                catch (e) {
                    txt = "Có lỗi khi lấy thông tin chứng thư số.\n\n";
                    txt += "Chi tiết lỗi: " + e.description + "\n\n";
                    alert(txt);
                } finally {
                    return false;
                }
    	}       
    </script>
    
</body>
</html>
