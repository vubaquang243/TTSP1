<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/includes/ttsp_header.inc"%>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ page import="com.seatech.ttsp.user.UserVO"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.PhanNhomNSDResource"/>
<!--20171124 thuongdt them id kb cua sesion user -->
<%   String id_kb = session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
    //20171206 thuongdt them nhomnv
    String nhomnv = request.getAttribute("nhomnv")==null?"":request.getAttribute("nhomnv").toString();
    
 %>
<script type="text/javascript">
    var arrFunAllow = new Array();
    var arrFunDeny = new Array();       
   <%        
        Collection collNSDTrongNhomBanDau = (Collection) request.getAttribute("colNSDThuocNhom");
        Collection collNSDNgoaiNhomBanDau = (Collection) request.getAttribute("colNSDNgoaiNhom");         
        UserVO userVO = null;        
        Iterator ir = null;
        try{
            ir = collNSDTrongNhomBanDau.iterator(); 
            int countTrongNhom = 0;
            while (ir.hasNext()){
                userVO = (UserVO)ir.next();
                out.println("arrFunAllow['"+countTrongNhom+"'] = '"+ userVO.getId()+"'");
                ++countTrongNhom;
            }
        }catch(Exception ex){
        }
        try{
            ir = collNSDNgoaiNhomBanDau.iterator();
            int countNgoaiNhom = 0;
            while (ir.hasNext()){
                userVO = (UserVO)ir.next();
                out.println("arrFunDeny['"+countNgoaiNhom+"'] = '"+ userVO.getId()+"'");
                ++countNgoaiNhom;
            }            
        }catch(Exception ex){
        }
    %>
</script> 

<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="/phanNhomAction.do" method="post">
    <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
              <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
              <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
                <span class=title2> <fmt:message key="phannhom.title"/></span>
              </td>
              <td width=62><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg" width=62 height=30/></td>
              <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg" width="20%">&nbsp;</td>
              <td width=4><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg" width=4 height=30/></td>
            </tr>
      </tbody>
    </table>
    <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
      <tr>
        <td>
          <br/>
          <table width="80%" cellspacing="0" cellpadding="1"
                 bordercolor="#e1e1e1" border="0" align="center"
                 style="BORDER-COLLAPSE: collapse">
            <tr>
              <td width="25%" align="right" bordercolor="#e1e1e1">
                <fmt:message key="phannhom.label.khobac"/>
              </td>
              <td width="50%" align="left" bordercolor="#e1e1e1">
                <html:text property="ma_kb" styleId="ma_kb_id"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           size="4%"
                           onblur="this.style.backgroundColor='#ffffff'; getTenKhoBac('ma_kb_id','ten_kb_id','kb_id_id','phanNhomLoadMaKBAction.do');checkquyen();"   
                           styleclass="promptText"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                <html:text property="ten_kb" readonly="true" styleId="ten_kb_id"
                           styleClass="fieldTextTrans" onmouseout="UnTip()"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           style="WIDTH: 168px; charset=utf-8 ;"/>
                <html:hidden property="kb_id" styleId="kb_id_id"/>
              </td>              
            </tr>
            <tr>              
              <td width="25%" align="right" bordercolor="#e1e1e1">
                <fmt:message key="phannhom.label.nhom"/>
              </td>
              <td width="50%" align="left" bordercolor="#e1e1e1">
                 <html:select property="nhom_id" styleId="nhom_id_id" onkeydown="if(event.keyCode==13) event.keyCode=9;" styleclass="promptText" >
                  <html:option value="">Lựa chọn</html:option>  
                  <logic:notEmpty name="colNhomNSD">                              
                    <html:optionsCollection name="colNhomNSD" value="id" label="ten_nhom"/>                    
                  </logic:notEmpty>
                </html:select>
              </td>
              <td width="25%" align="left"  bordercolor="#e1e1e1" rowspan="2">                
                <button type="button" onclick="phannhomSubmit('tracuu')"
                  class="ButtonCommon" style="width:100" accesskey="t">
                  <span class="sortKey">T</span>ra c&#7913;u
                </button>
              </td>
            </tr>
          </table>
          <br/>
        </td>
      </tr>
    </table>
    <table style="BORDER-COLLAPSE: collapse" cellspacing="0"
           bordercolor="#999999" width="100%">
     
      <tr>      
        <td style="text-align:center; width:auto" nowrap="nowrap" width="40%" align="center">
        <br/>
          <!-- DS NSD khong thuoc nhom-->
          <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="400px">
          <tr>
          <td class="title" colspan="6"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="24" align="left">
            <font color="Gray">
              <fmt:message key="phannhom.title.ds_nsd_ngoai_nhom"/>
            </font>
          </td>
          </tr>
          <tr>
          <td align="center">          
          <div style="width:400px; height: auto; overflow: auto; " align="left">
            <table class="navigateable focused" cellspacing="0" cellpadding="1"
                   bordercolor="#e1e1e1" border="1" align="center" width="98%"
                   style="BORDER-COLLAPSE: collapse">
              <thead>
                <th class="promptText" bgcolor="#f0f0f0" width="10px">
                  STT
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="80px">
                    <fmt:message key="phannhom.label.mansd"/>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="150px">
                    <fmt:message key="phannhom.label.tennsd"/>
                </th>                
                <th bgcolor="#f0f0f0" width="10px">
                  <input type="checkbox" id="checkAllFun" onclick="checkAllF('tbdListAddFun','checkAllFun')"/>                  
                </th>
                
              </thead>
            </table>            
          </div>
          <div style="width:400px; height: 250px; overflow: auto;"  align="left">
            <table class="navigateable focused" cellspacing="0" cellpadding="1"
                   bordercolor="#e1e1e1" border="1" align="center" width="98%"
                   style="BORDER-COLLAPSE: collapse">
              <tbody class="navigateable focused" cellspacing="0"
                     cellpadding="1" bordercolor="#e1e1e1" id="tbdListAddFun">
                <logic:notEmpty name="colNSDNgoaiNhom">
                  <logic:iterate id="NSDNgoaiNhom" name="colNSDNgoaiNhom" indexId="stt">
                    <tr>
                      <td align="left"  width="10px">
                        <script type="text/javascript">document.write(eval(<bean:write name="stt"/>+1))</script>
                      </td>
                      <td align="left"  width="80px">
                        <bean:write name="NSDNgoaiNhom" property="ma_nsd"/>
                      </td>
                      <td  width="150px">
                        <bean:write name="NSDNgoaiNhom" property="ten_nsd"/>
                      </td>
                      <td align="center" width="8px">
                        <input type="checkbox" value="<bean:write name="NSDNgoaiNhom" property="id"/>" name="dnAddFun" onclick="ktCheckAllFun(this,'tbdListAddFun','checkAllFun')"/>
                      </td>
                    </tr>
                  </logic:iterate>
                </logic:notEmpty>                
              </tbody>
            </table>
          </div>
          </td>
          </tr>
          </table>
          <!--Danh sach nsd ngoai nhom-->
          
          
        </td>
        <td width="20%" align="center">
          <table border="0" cellpadding="5" cellspacing="0">
            <tr>
              <td style="text-align:center; width:20px"><input type="button" value=">>" id="btnThemCNGroup" onclick="addorRemove('tbdListAddFunAllow', 'tbdListAddFun','add')"/></input> </td>
            </tr>
            <tr>
              <td style="text-align:center; width:20px"><input type="button" value="<<" id="btnChuyenCNGroup" onclick="addorRemove('tbdListAddFunAllow', 'tbdListAddFun', 'remove')"/></td>
            </tr>
          </table>
        </td>
        <td width="40%">
        <!--Danh sach nsd thuoc nhom-->
        <br/>
        <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="400px">
          <tr>
          <td class="title" colspan="6"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="24" align="left">
            <font color="Gray">
              <fmt:message key="phannhom.title.ds_nsd_trong_nhom"/>
            </font>
          </td>
          </tr>
          <tr>
          <td align="center">          
          <div style="width:400px; height: auto; overflow: auto;" align="left">
            <table class="navigateable focused" cellspacing="0" cellpadding="1"
                   bordercolor="#e1e1e1" border="1" align="center" width="98%"
                   style="BORDER-COLLAPSE: collapse">
              <thead>
                <th class="promptText" bgcolor="#f0f0f0" width="10px">
                  STT
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="80px">
                    <fmt:message key="phannhom.label.mansd"/>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="150px">
                    <fmt:message key="phannhom.label.tennsd"/>
                </th>                
                <th bgcolor="#f0f0f0" width="10px">                  
                  <input type="checkbox" id="checkAllFunAllow" onclick="checkAllF('tbdListAddFunAllow','checkAllFunAllow')"/>
                </th>
                
              </thead>
            </table>            
          </div>
          <div style="width:400px; height: 250px; overflow: auto;"  align="left">
            <table class="navigateable focused" cellspacing="0" cellpadding="1"
                   bordercolor="#e1e1e1" border="1" align="center" width="98%"
                   style="BORDER-COLLAPSE: collapse" >
              <tbody class="navigateable focused" cellspacing="0"
                     cellpadding="1" bordercolor="#e1e1e1" id="tbdListAddFunAllow">
                <logic:notEmpty name="colNSDThuocNhom">
                  <logic:iterate id="NSDThuocNhom" name="colNSDThuocNhom" indexId="stt">
                    <tr>
                      <td align="left"  width="10px">
                        <script type="text/javascript">document.write(eval(<bean:write name="stt"/>+1))</script>
                      </td>
                      <td align="left"  width="80px">
                        <bean:write name="NSDThuocNhom" property="ma_nsd"/>
                      </td>
                      <td  width="150px">
                        <bean:write name="NSDThuocNhom" property="ten_nsd"/>
                      </td>
                      <td align="center" width="8px">
                        <input type="checkbox" value="<bean:write name="NSDThuocNhom" property="id"/>" name="dnAddFun" onclick="ktCheckAllFun(this,'tbdListAddFunAllow','checkAllFun')"/>
                      </td>
                    </tr>
                  </logic:iterate>
                </logic:notEmpty>                
              </tbody>
            </table>
          </div>
          </td>
          </tr>
          </table>
        <!--Danh sach nsd thuoc nhom-->
        </td>
      </tr>   
    </table>
    <br/>
    <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
      <tr>
        <td align="center">
        <br/>
          <button type="button" onclick="phannhomSubmit('ghi')" class="ButtonCommon" style="width:50" accesskey="g">
            <span class="sortKey">G</span>hi
          </button>
          <button type="button" onclick="phannhomSubmit('thoat')" class="ButtonCommon" accesskey="o">
            Th<span class="sortKey">o</span>&#225;t
          </button>
        <br/>
        &nbsp 
        </td>
      </tr>
    </table>
    <input type="hidden" name="arrAddF" id="arrAddF" />
    <input type="hidden" name="arrRemoveF" id="arrRemoveF" />
  </html:form>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript" >
    document.getElementById("ma_kb_id").focus();
    arrAddFun = new Array();
    arrRemoveFun = new Array();
    tbdListAddFunAlow = document.getElementById("tbdListAddFunAllow");
    tbdListAddFunDeny = document.getElementById("tbdListAddFun");
    arrButtonAlow = tbdListAddFunAlow!=null?tbdListAddFunAlow.getElementsByTagName("INPUT"):new Array();
    arrButtonDeny = tbdListAddFunDeny!=null?tbdListAddFunDeny.getElementsByTagName("INPUT"):new Array();    
    frm = document.forms[0];
    try{
        document.getElementById("dnApp").onchange = function(){
            arrAction = [];
            frm = document.forms[0];
            arrAction[0] = "loadListFun";
            frm.actions.value = arrAction;
            frm.submit();
        }
    }catch(ex){
    }
    
    function getArrAddorRemove(){
        arrRemoveFun = arrButtonAlow.length<=arrFunAllow.length?getArray(arrFunAllow, getArrDnAddorRemove(arrButtonDeny)):getArray(getArrDnAddorRemove(arrButtonDeny), arrFunAllow);
        arrAddFun = arrButtonDeny.length<=arrFunDeny.length?getArray(arrFunDeny, getArrDnAddorRemove(arrButtonAlow)):getArray(getArrDnAddorRemove(arrButtonAlow), arrFunDeny);
    }
    
    function getArray(arr1, arr2){
        arrTemp = new Array();
        for(i=0; i<arr1.length; ++i){
            flag = false;
            for(j=0; j<arr2.length; ++j){
                if(arr1[i]==arr2[j]){
                    flag = true;
                    break;
                }
            }
            if(flag){
                arrTemp[arrTemp.length] = "~"+arr1[i];
            }
        }
        return arrTemp;
    }
    
    
    function getArrDnAddorRemove(arr){
        arrTemp = new Array();
        for(i=0; i<arr.length; ++i){
            arrTemp[i] = arr[i].parentNode.parentNode.getElementsByTagName("TD")[3].getElementsByTagName("INPUT")[0].value;
        }
        return arrTemp;
    }
    
    try{
        document.getElementById("findFun").onclick = function(){
            arrAction = [];
            text = document.getElementById("cnGroupTemp");
            if(''==text.value){
                alert("Phải nhập tên nhóm người sử dụng!");
                setTimeout("text.focus()",1);
                return false;
            }
            frm.cnGroup.value = text.value;
            frm = document.forms[0];
            arrAction[0] = "loadListFunAllow";        
            frm.actions.value = arrAction;
            frm.submit();
        }
    }catch(ex){
    }
    try{
        function checkAllF(tbdId, checkAllId){
            var checkAll = document.getElementById(checkAllId);
            var tbdListAddFun = document.getElementById(tbdId);
            arrButton = tbdListAddFun.getElementsByTagName("input");
            if(checkAll.checked==true){
                for(i=0; i<arrButton.length; ++i){                    
                    arrButton[i].checked = true;
                    arrButton[i].parentNode.parentNode.style.backgroundColor = "#ffffb5";
                }
            }else{
                for(i=0; i<arrButton.length; ++i){                    
                    arrButton[i].checked = false;
                    arrButton[i].parentNode.parentNode.style.backgroundColor = "";
                }
            }
        }
    }catch(ex){
    }
    
    function update(){
        getArrAddorRemove();
        var arrAddF = document.getElementById("arrAddF");
        var arrRemoveF = document.getElementById("arrRemoveF");
        frm = document.forms[0];
        arrAction = [];    
        if(0<arrAddFun.length){
          arrAddFun[0] = arrAddFun[0].replace("~","");
        }
        if(0<arrRemoveFun.length){
          arrRemoveFun[0] = arrRemoveFun[0].replace("~","");
        }    
        arrAddF.value = arrAddFun;
        arrRemoveF.value = arrRemoveFun;
        arrAction[2] = "updateListFun"; 
        frm.actions.value = arrAction;    
        frm.submit();
    }
    
    function addorRemove(tbdIdAlow, tbdIdDeny, action){
        try{
            frm = document.forms[0];
            checkAllFun = document.getElementById("checkAllFun");
            checkAllFunAllow = document.getElementById("checkAllFunAllow");
           
            arrInput = new Array();
            flagAdd = true;
            flagRemove = true;
            if(arrButtonDeny!=null){         
                for(i=0; i<arrButtonDeny.length; ++i){              
                    arrInput[arrInput.length] = arrButtonDeny[i];
                    if(action == "remove"){
                        arrButtonDeny[i].checked = false;
                        arrButtonDeny[i].parentNode.parentNode.style.backgroundColor = "";
                        checkAllFunAllow.checked = false;
                        checkAllFun.checked = false;                    
                    }else if(arrButtonDeny[i].checked == true){
                        flagAdd = false;
                    }                
                }
            }
            
            if(arrButtonAlow!=null){            
                for(i=0; i<arrButtonAlow.length; ++i){                
                    arrInput[arrInput.length] = arrButtonAlow[i];
                    if(action == "add"){                    
                        arrButtonAlow[i].checked = false;
                        arrButtonAlow[i].parentNode.parentNode.style.backgroundColor = "";
                        checkAllFun.checked = false;
                        checkAllFunAllow.checked = false;
                    }else if(arrButtonAlow[i].checked == true){
                        flagRemove = false;
                    }                
                }
            }           
            //
            for(i=0; i<arrInput.length; ++i){
                if(action == "add"){
                    if(flagAdd){
                        alert("Bạn chưa chọn chức năng nào!");                        
                        if(null!=arrButtonDeny[0]){                        
                            setTimeout("arrButtonDeny[0].focus()",1);
                        }                        
                        return;
                    }
                    checkAllFun.checked = false;
                    try{
                        if(arrInput[i].checked == true){
                            flag = false;
                            arrInput[i].name = "dnRemoveFun";
                            arrInput[i].checked = false;
                            arrInput[i].onclick = ktCheckAllFunAdd;
                            arrInput[i].parentNode.parentNode.style.backgroundColor = "";
                            tbdListAddFunAlow.appendChild(arrInput[i].parentNode.parentNode);                       
                        }
                    }catch(ex){
                        
                    }                
                }else if(action == "remove"){
//                    if(frm.cnGroup.value == ''){
//                        alert("Chưa chọn nhóm sử dụng!");
//                        setTimeout("frm.cnGroup.focus()",1);
//                        return;
//                    }
                    
                    if(flagRemove){
                        alert("Bạn chưa chọn chức năng nào!");
                        if(null!=arrButtonAlow[0]){
                            setTimeout("arrButtonAlow[0].focus()",1);
                        }
                        return;
                    }
                    try{
                        checkAllFunAllow.checked = false;
                        if(arrInput[i].checked == true){
                            arrInput[i].name = "dnAddFun";
                            arrInput[i].checked = false;
                            arrInput[i].onclick = ktCheckAllFunRemove;
                            arrInput[i].parentNode.parentNode.style.backgroundColor = "";
                            tbdListAddFunDeny.appendChild(arrInput[i].parentNode.parentNode);
                        }                   
                    }catch(ex){
                        
                    }         
                }
            }
            assignSTT(arrButtonAlow);
            assignSTT(arrButtonDeny);
    
        }catch(ex){     
            alert("Danh sách chức năng trống!");
        }
    }
    function assignSTT(arrInput){
        if(0<arrInput.length){
            for(i=0; i<arrInput.length; ++i){
                trPanrent = arrInput[i].parentNode;
                while(trPanrent.nodeName!="TR"){
                    trPanrent = trPanrent.parentNode;
                }
                tdChildNode = trPanrent.getElementsByTagName("TD")[0];
                j = i;
                tdChildNode.innerHTML = "<b>"+ ++j +"<\/b>";
            }
        }
    }
    
    function ktCheckAllFunAdd(){  
        ktCheckAllFun(this,'tbdListAddFunAllow','checkAllFunAllow');
    }
    function ktCheckAllFunRemove(){
        ktCheckAllFun(this,'tbdListAddFun','checkAllFun');
    }
    
    function ktCheckAllFun(check, tbdId, checkAllId){
        if(check.checked == true){
            check.parentNode.parentNode.style.backgroundColor = "#ffffb5";
        }else {
            check.parentNode.parentNode.style.backgroundColor = "";
        }
        var tbdListAddFun = document.getElementById(tbdId);
        arrButton = tbdListAddFun.getElementsByTagName("input");
        var checkAllFun = document.getElementById(checkAllId);
        var flag = true;
        for(i=0; i<arrButton.length; ++i){                    
            if(arrButton[i].checked == false){
                flag = false;
            }
        }
        if(flag){
            checkAllFun.checked = true;
        }else checkAllFun.checked = false;
    }
    function findStaffGroupUser(imgs){        
        window.open('<pdk-html:rewrite page="/pcquyenungdung.do?actions=,,,openFormFindStaffGroup"/>', "FindStaffGroup",'width=900,height=600,left=0,top=100,screenX=0,screenY=100,center:true,scrollbars=1');

    }
    function phannhomSubmit(type){
    var frm = document.forms[0];
      if( type == "tracuu"){
        if(frm.ma_kb.value.trim() == '' || frm.ma_kb.value.trim() == 'null'){
        alert('Bạn phải nhập mã kho bạc.');
        document.getElementById("ma_kb_id").focus();
        return;
        }else if(frm.nhom_id.value.trim() == '' || frm.nhom_id.value.trim() == 'null'){
        alert('Bạn phải chọn nhóm NSD.');
        document.getElementById('nhom_id_id').fucus();
        return;
        }
        frm.action = "phanNhomAction.do";
      }else if( type == "ghi"){            
        getArrAddorRemove();
        var arrAddF = document.getElementById("arrAddF");
        var arrRemoveF = document.getElementById("arrRemoveF");
        frm = document.forms[0];        
        if(0<arrAddFun.length){
          arrAddFun[0] = arrAddFun[0].replace("~","");
        }
        if(0<arrRemoveFun.length){
          arrRemoveFun[0] = arrRemoveFun[0].replace("~","");
        }    
        arrAddF.value = arrAddFun;
        arrRemoveF.value = arrRemoveFun;
        
        frm.action = "phanNhomExcAction.do";
      }else if( type == "thoat"){      
        frm.action = "mainAction.do";
      }
      frm.submit();
    }
    
    // 20171124 thuongdt kiem tra chi cho phep qthttw duoc phan quyen cho cac don vi: 0001, 0002, 0003
    checkquyen();
    function checkquyen(){
      var kb_id = '<%=id_kb%>';
     var vshkb = document.getElementById("ma_kb_id").value; 
     var vnhom_id_id = document.getElementById("nhom_id_id").value; 
     
     if(kb_id == '1'){
       if((vshkb == '0001' || vshkb == '0002' || vshkb == '0003') || vnhom_id_id == '341'){
        document.getElementById("btnThemCNGroup").style.display = "block";
        document.getElementById("btnChuyenCNGroup").style.display = "block";
       }else{
        document.getElementById("btnThemCNGroup").style.display = "none";
        document.getElementById("btnChuyenCNGroup").style.display = "none";
       }
     }
    }
    
    //20171206 thuongdt bo sung them kiem tra phan quyen nhom
    checkNhom();
    function checkNhom(){
      var nhom = '<%=nhomnv%>';
      if(nhom != ''){
        alert('Đã tồn tại nhóm quyền chức năng user: '+nhom+' không thể phân nhóm tiếp.');
      
      }
    }
</script>
    