var activeRowColor = '#ffffb5';
var normalRowColor = '#ffffff';
function TablePropertyObj(tableId, activeRow, numberRowBodyHeader, numberRowBodyFooter){
    this.tableId = tableId;
    this.activeRow = activeRow;
    this.numberRowBodyHeader = numberRowBodyHeader;
    this.numberRowBodyFooter = numberRowBodyFooter;
}
var tablePropertyArray = new Array();

function addRowTable(tableId) {
    var tableObj = document.getElementById(tableId);
    var tablePropObj = tablePropertyArray[tableId];
    var tbodyObj;
    var currentRowObj = tablePropObj.activeRow;
    var sampleCells = tablePropObj.activeRow.cells;
    var tdObj;
    var tdChildNotes;
    var tdChildNote;
    var trObj;
    var idx;
    var cellObj;
    var cellAtributes;

    tbodyObj = currentRowObj.parentNode;
    while (tbodyObj.tagName != 'TBODY') {
        tbodyObj = tbodyObj.parentNode;
    }

    trObj = tbodyObj.insertRow(currentRowObj.sectionRowIndex + 1);

    trObj.onclick = activeRowTable;
    trObj.onkeydown = activeRowTableByKey;
    trObj.onkeyup = activeRowTableByKeyTab;
    idx = trObj.rowIndex;

    for(i = 0; i < sampleCells.length; ++i) {
        cellObj = sampleCells[i];
        cellAtributes = cellObj.attributes;
        tdChildNotes = cellObj.childNodes;
        tdObj = trObj.insertCell(i);

        tdObj.style.cssText = cellObj.style.cssText;
        for(j = 0; j < cellAtributes.length; ++j){
            tdObj.setAttribute(cellAtributes[j].nodeName, cellAtributes[j].nodeValue);
        }
        for(k = 0; k < tdChildNotes.length; ++k){
            tdChildNote = tdChildNotes[k].cloneNode(true);
            if(tdChildNote.type != undefined && tdChildNote.type=='text' && tdChildNote.value != undefined) {
                tdChildNote.value = '';
            }
            tdObj.appendChild(tdChildNote);
        }
    }
    assignId(tableId);
    activeRow(tableId, trObj);

    return idx;
}
function assignId(tableId){
    var tableObj = document.getElementById(tableId);
    var tablePropObj = tablePropertyArray[tableId];
    var tbodyObjArray = tableObj.tBodies;
    var tbodyObj;
    var rowCol;
    var rowObj;
    var cellCol;
    var cellObj;
    var cellChildNotes;
    var cellChildNote;
    for(h = 0; h < tbodyObjArray.length; ++h) {
        tbodyObj = tableObj.tBodies[h];
        rowCol = tbodyObj.rows;
        for(i = 0; i < rowCol.length; ++i){
            rowObj = rowCol[i];
            cellCol = rowObj.cells;
            for(j = 0; j < cellCol.length; ++j){
                cellObj = cellCol[j];
                cellChildNotes = cellObj.childNodes;
                for(k = 0; k < cellChildNotes.length; ++k){
                    cellChildNote = cellChildNotes[k];
                    if(cellChildNote.name != undefined) {
                       
                            cellChildNote.id = cellChildNote.name + '_' + rowObj.rowIndex;
                        
                        if(cellChildNote.type != undefined && cellChildNote.type=='text') {
                            cellChildNote.onfocus = selectText;
                        }
                    }
                }
            }
        }
    }
}
function attachEvent(tableId){
    var tableObj = document.getElementById(tableId);
    var tablePropObj = tablePropertyArray[tableId];
    var tbodyObjArray = tableObj.tBodies;
    var tbodyObj;
    var rowCol;
    var rowObj;
    var cellCol;
    var cellChildNotes;
    var cellChildNote;

    for(h = 0; h < tbodyObjArray.length; ++h) {
        tbodyObj = tableObj.tBodies[h];
        rowCol = tbodyObj.rows;
        for(i = tablePropObj.numberRowBodyHeader; i < rowCol.length - tablePropObj.numberRowBodyFooter; ++i){
            rowObj = rowCol[i];
            rowObj.onclick = activeRowTable;
            rowObj.onkeydown = activeRowTableByKey;
            rowObj.onkeyup= activeRowTableByKeyTab;
        }
    }
}
function deleteRowTable(tableId){
    var tableObj = document.getElementById(tableId);
    var tablePropObj = tablePropertyArray[tableId];
    var tbodyObj;
    var currentRowObj = tablePropObj.activeRow;
    var rowCol;
    var rowIdx;

    tbodyObj = currentRowObj.parentNode;
    while (tbodyObj.tagName != 'TBODY') {
        tbodyObj = tbodyObj.parentNode;
    }
    rowCol = tbodyObj.rows;
    if(rowCol.length > 1 + tablePropObj.numberRowBodyHeader + tablePropObj.numberRowBodyFooter && currentRowObj != null) {
        rowIdx = currentRowObj.sectionRowIndex;
        tbodyObj.deleteRow(rowIdx);
        assignId(tableId);
        if(rowIdx == rowCol.length - tablePropObj.numberRowBodyFooter){
            rowIdx = rowIdx - 1;
        }
        activeRow(tableId, rowCol[rowIdx]);
    }
}
function activeRow(tableId, rowObj) {
    var tablePropObj = tablePropertyArray[tableId];
    var currentRowObj = tablePropObj.activeRow;
    var tbodyObj;
    var cellCol = rowObj.cells;
    if(currentRowObj != null) {
        deactiveRowTable(currentRowObj);
    }

    rowObj.style.backgroundColor = activeRowColor;
    for(i = 0; i < cellCol.length; ++i) {
        if(cellCol[i].style != undefined) {
            cellCol[i].style.backgroundColor = activeRowColor;
        }
        tdChildNotes = cellCol[i].childNodes;
        for(j = 0; j < tdChildNotes.length; ++j){
            if(tdChildNotes[j].style != undefined) {
                tdChildNotes[j].style.backgroundColor = activeRowColor;
            }
        }
    }
    tablePropObj.activeRow = rowObj;
    tbodyObj = rowObj.parentNode;
    while (tbodyObj.tagName != 'TBODY') {
        tbodyObj = tbodyObj.parentNode;
    }
}
function deactiveRowTable(rowObj) {
    var cellCol = rowObj.cells;
    rowObj.style.backgroundColor = normalRowColor;
    for(i = 0; i < cellCol.length; ++i) {
        if(cellCol[i].style != undefined) {
            cellCol[i].style.backgroundColor = normalRowColor;
        }
        tdChildNotes = cellCol[i].childNodes;
        for(j = 0; j < tdChildNotes.length; ++j){
            if(tdChildNotes[j].style != undefined) {
                tdChildNotes[j].style.backgroundColor = normalRowColor;
            }
        }
    }
}
function activeRowTableByKey() {
    var keyCode = window.event.keyCode;
    var tablePropObj;
    var obj;
    var objIdPrefix;
    var tabObj;
    var rowObj;
    var tbodyObj;
    var rowCol;
    var rowIdx;
    var colObj;
    var cellChildNotes;
    var cellChildNote;
    var colIdx;

    if (keyCode == 13||keyCode == 9) {
        obj = window.event.srcElement;
        objIdPrefix = obj.id;
        rowObj = obj.parentNode;
        while (rowObj.tagName != 'TR') {
            rowObj = rowObj.parentNode;
        }
        tabObj = rowObj;
        while (tabObj.tagName != 'TABLE') {
            tabObj = tabObj.parentNode;
        }
        tablePropObj = tablePropertyArray[tabObj.id];
        tbodyObj = rowObj.parentNode;
        while (tbodyObj.tagName != 'TBODY') {
            tbodyObj = tbodyObj.parentNode;
        }
        activeRow(tabObj.id, rowObj);         
    } else if (keyCode == 38 || keyCode == 40) {
        obj = window.event.srcElement;
        objIdPrefix = obj.id;
        rowObj = obj.parentNode;
        while (rowObj.tagName != 'TR') {
            rowObj = rowObj.parentNode;
        }
        tabObj = rowObj;
        while (tabObj.tagName != 'TABLE') {
            tabObj = tabObj.parentNode;
        }
        tablePropObj = tablePropertyArray[tabObj.id];
        rowIdx = rowObj.sectionRowIndex;
        tbodyObj = rowObj.parentNode;
        while (tbodyObj.tagName != 'TBODY') {
            tbodyObj = tbodyObj.parentNode;
        }
        rowCol = tbodyObj.rows;
        if(keyCode == 38) {
            if(rowIdx > tablePropObj.numberRowBodyHeader){
                rowIdx = rowIdx - 1;
            } else {
                rowIdx = rowCol.length - tablePropObj.numberRowBodyFooter - 1;
            }
        } else if(keyCode == 40) {
            if(rowIdx < rowCol.length - tablePropObj.numberRowBodyFooter - 1) {
                rowIdx = rowIdx + 1;
            } else {
                rowIdx = tablePropObj.numberRowBodyHeader;
            }
        }
        rowObj = rowCol[rowIdx]
        activeRow(tabObj.id, rowObj);    
        objIdPrefix = objIdPrefix.substring(0, objIdPrefix.lastIndexOf('_'));
        document.getElementById(objIdPrefix + '_' + rowObj.rowIndex).focus();
    }
}
function activeRowTableByKeyTab() {
    var keyCode = window.event.keyCode;
    var tablePropObj;
    var obj;
    var objIdPrefix;
    var tabObj;
    var rowObj;
    var tbodyObj;
    var rowCol;
    var rowIdx;
    var colObj;
    var cellChildNotes;
    var cellChildNote;
    var colIdx;

    if (keyCode == 13||keyCode == 9) {
        obj = window.event.srcElement;
        objIdPrefix = obj.id;
        rowObj = obj.parentNode;
        while (rowObj.tagName != 'TR') {
            rowObj = rowObj.parentNode;
        }
        tabObj = rowObj;
        while (tabObj.tagName != 'TABLE') {
            tabObj = tabObj.parentNode;
        }
        tablePropObj = tablePropertyArray[tabObj.id];
        tbodyObj = rowObj.parentNode;
        while (tbodyObj.tagName != 'TBODY') {
            tbodyObj = tbodyObj.parentNode;
        }
        activeRow(tabObj.id, rowObj);         
    }
}
function activeRowTable() {
    var obj = window.event.srcElement;
    var rowObj = obj.parentNode;
    var tablePropObj;
    var tabObj;
    var currentRowObj;
    while (rowObj.tagName != 'TR') {
        rowObj = rowObj.parentNode;
    }
    tabObj = rowObj;
    while (tabObj.tagName != 'TABLE') {
        tabObj = tabObj.parentNode;
    }
    tablePropObj = tablePropertyArray[tabObj.id];
    currentRowObj = tablePropObj.activeRow;
    if(rowObj != currentRowObj){
        activeRow(tabObj.id, rowObj);
    }
}
function initTable(tableId) {
    initMultiBodyTable(tableId, 0, 0);
}
function initMultiBodyTable(tableId, numberRowBodyHeader, numberRowBodyFooter){
    var tableObj;
    var tbodyObj;
    var currentRowObj;
    var tbPropObj;

    tableObj = document.getElementById(tableId);
    tbodyObj = tableObj.tBodies[0];
    currentRowObj = tbodyObj.rows[numberRowBodyHeader];
    tbPropObj = new TablePropertyObj(tableId, currentRowObj, numberRowBodyHeader, numberRowBodyFooter);
    tablePropertyArray[tableId] = tbPropObj;

    assignId(tableId);
    attachEvent(tableId);

    activeRow(tableId, currentRowObj);
}
function selectText() {
    var obj = window.event.srcElement;
    obj.select();
}
function assignSTT(tableId) {
    var tableObj = document.getElementById(tableId);
    var tablePropObj = tablePropertyArray[tableId];
    var tbodyObjArray = tableObj.tBodies;
    var tbodyObj;
    var rowCol;
    var rowObj;
    var cellCol;
    for(h = 0; h < tbodyObjArray.length; ++h){
        tbodyObj = tableObj.tBodies[h];
        rowCol = tbodyObj.rows;
        for(i = tablePropObj.numberRowBodyHeader; i < rowCol.length - tablePropObj.numberRowBodyFooter; ++i){
            rowObj = rowCol[i];
            cellCol = rowObj.cells;
            cellCol[0].innerText = i - tablePropObj.numberRowBodyHeader + 1;
        }
    }
}
function calculateVerticalSum(item_name, total_id) {
    var items = document.getElementsByName(item_name);
    var totalObj = document.getElementById(total_id);
    var total = 0;
    var itemValue;
    if(null==totalObj){
        totalObj = document.getElementsByName(total_id)[0];
    }
    for(i = 0; i < items.length; ++i) {
        itemValue = items[i].value;      
        //if(itemValue == '-') {
          //  itemValue = 0;
        //}
        var gtrNum = toNumber(itemValue);
        if(isNaN(gtrNum)) {
            gtrNum = 0;
        }
        total = total + gtrNum;
    }    
    totalObj.value = formatNumber(total.toString());
}
function calculateVerticalSumBigInt(item_name, total_id) {
    var items = document.getElementsByName(item_name);
    var totalObj = document.getElementById(total_id);
    var total = 0;
    var itemValue;
    if(null==totalObj){
        totalObj = document.getElementsByName(total_id)[0];
    }
    for(i = 0; i < items.length; ++i) {
        itemValue = items[i].value;   
        itemValue = ReplaceAll(itemValue,'.','');
        itemValue = itemValue.replace(',','.')     
        total = new BigNumber(total).add(itemValue);
    }    
    totalObj.value = formatNumber(total.toString());
}
function calculateVerticalFloatSum(item_name, total_id, param) {
    var items = document.getElementsByName(item_name);
    var totalObj = document.getElementById(total_id);
    var total = 0;
    var itemValue;
    if(null==totalObj){
        totalObj = document.getElementsByName(total_id)[0];
    }
    for(i = 0; i < items.length; ++i) {
        itemValue = items[i].value;
        total = total + toNumber(itemValue);
    }

//    var resultDe = toFormatNumberDe(total, 2);    
//    var pos = resultDe.indexOf(',');
//    var intPart = resultDe.substring(0,pos);
//    var decPart = resultDe.substring(pos+1);
//
//    if(intPart == ''){
//        intPart = '0';
//    }
//    if(decPart.length == 1){
//        decPart = decPart + '0';
//    }
//
//    totalObj.value = intPart + ',' + decPart;
    totalObj.value = toFormatNumberDe(total, param); 
}
function calculateTBodyVerticalSum(tableId, item_name, total_id) {
    var tablePropObj = tablePropertyArray[tableId];
    var tbodyObj;
    var beginIdx;
    var endIdx;
    var itemObj;
    var totalObj = document.getElementById(total_id);
    var totalTBodyObj;
    var total = 0;
    var itemValue;

    if(null==totalObj){
        totalObj = document.getElementsByName(total_id)[0];
    }
    
    tbodyObj = tablePropObj.activeRow.parentNode;
    while (tbodyObj.tagName != 'TBODY') {
        tbodyObj = tbodyObj.parentNode;
    }

    totalTBodyObj = totalObj.parentNode;    
    while (totalTBodyObj.tagName != 'TBODY') {
        totalTBodyObj = totalTBodyObj.parentNode;
    }
    if(totalTBodyObj == tbodyObj) {
        rowObj = tbodyObj.rows[0];
        beginIdx = rowObj.rowIndex + tablePropObj.numberRowBodyHeader;
        endIdx = rowObj.rowIndex + tbodyObj.rows.length - tablePropObj.numberRowBodyFooter;
        for(i = beginIdx; i < endIdx; ++i) {
            itemObj = document.getElementById(item_name + '_' + i);
            if(null==itemObj){
                itemObj = document.getElementsByName(item_name+'_'+i);
            }
            itemValue = itemObj.value;
            //if(itemValue == '-') {
              //  itemValue = 0;
            //}
            var gtrNum = toNumber(itemValue);
            if(isNaN(gtrNum)) {
                gtrNum = 0;
            }
            total = total + gtrNum;
        }
        totalObj.value = formatNumber(total.toString());
    }
}

function calculateTBodyVerticalSumFloatNumber(tableId, item_name, total_id) {
    var tablePropObj = tablePropertyArray[tableId];
    var tbodyObj;
    var beginIdx;
    var endIdx;
    var itemObj;
    var totalObj = document.getElementById(total_id);
    var totalTBodyObj;
    var total = 0;
    var itemValue;
    
    if(null==totalObj){
        totalObj = document.getElementsByName(total_id)[0];
    }
    
    tbodyObj = tablePropObj.activeRow.parentNode;
    while (tbodyObj.tagName != 'TBODY') {
        tbodyObj = tbodyObj.parentNode;
    }
    totalTBodyObj = totalObj.parentNode;
    while (totalTBodyObj.tagName != 'TBODY') {
        totalTBodyObj = totalTBodyObj.parentNode;
    }
    if(totalTBodyObj == tbodyObj) {
        rowObj = tbodyObj.rows[0];
        beginIdx = rowObj.rowIndex + tablePropObj.numberRowBodyHeader;
        endIdx = rowObj.rowIndex + tbodyObj.rows.length - tablePropObj.numberRowBodyFooter;
        for(i = beginIdx; i < endIdx; ++i) {
            itemObj = document.getElementById(item_name + '_' + i);
            itemValue = itemObj.value;
            total = total + toNumber(itemValue);
        }
        total = total.toFixed(2);
        totalObj.value = toFormatNumberDeFloat(total.toString(),2);
    }
}

function calculateHorizontalSum() {
    var srcObj = window.event.srcElement;
    var srcObjId = srcObj.id;
    var idSuffix = srcObjId.substring(srcObjId.lastIndexOf('_'));
    var argv = calculateHorizontalSum.arguments;
    var argc = argv.length;
    var itemName;
    var itemObj;
    var itemValue;
    var total = 0;
    var totalObj;
    var i;
    var sign;
    for (i = 0; i < argc - 1; i++) {
        itemName = argv[i];
        sign = itemName.substr(0, 1);
        if(sign == '-' || sign == '+') {
            itemName = itemName.substring(1);
        }
        itemObj = document.getElementById(itemName + idSuffix);
        itemValue = itemObj.value;
        
        if (sign == '-') {
            total = total - toNumber(itemValue);
        } else {
            total = total + toNumber(itemValue);
        }
    }
    total = total.toFixed(0);
    totalObj = document.getElementById(argv[i] + idSuffix);
    totalObj.value = formatNumber(total.toString());
}
function calculateHorizontalSumBigInt() {
    var srcObj = window.event.srcElement;
    var srcObjId = srcObj.id;
    var idSuffix = srcObjId.substring(srcObjId.lastIndexOf('_'));
    var argv = calculateHorizontalSumBigInt.arguments;
    var argc = argv.length;
    var itemName;
    var itemObj;
    var itemValue;
    var total = 0;
    var totalObj;
    var i;
    var sign;
    for (i = 0; i < argc - 1; i++) {
        itemName = argv[i];
        sign = itemName.substr(0, 1);
        if(sign == '-' || sign == '+') {
            itemName = itemName.substring(1);
        }
        itemObj = document.getElementById(itemName + idSuffix);
        itemValue = itemObj.value;
        itemValue = ReplaceAll(itemValue,'.','');
        itemValue = itemValue.replace(',','.')
        if (sign == '-') {
            total = new BigNumber(total).subtract(itemValue);
        } else {
            total = new BigNumber(total).add(itemValue);
        }
    }    
    totalObj = document.getElementById(argv[i] + idSuffix);
    totalObj.value = formatNumber(total.toString());
}
function calculateSum() {
    var argv = calculateSum.arguments;
    var argc = argv.length;
    var itemName;
    var itemObjs;
    var itemObj;
    var itemValue;
    var total = 0;
    var totalObjs;
    var totalObj;
    var i;
    var sign;
    for (i = 0; i < argc - 1; i++) {
        itemName = argv[i];
        sign = itemName.substr(0, 1);
        if(sign == '-' || sign == '+') {
            itemName = itemName.substring(1);
        }
        itemObjs = document.getElementsByName(itemName);
        itemObj = itemObjs[0];
        itemValue = itemObj.value;
        
        if (sign == '-') {
            total = total - toNumber(itemValue);
        } else {
            total = total + toNumber(itemValue);
        }
    }
    total = total.toFixed(0);
    totalObjs = document.getElementsByName(argv[i]);
    totalObj = totalObjs[0];
    totalObj.value = formatNumber(total.toString());
}
function calculateSumById() {
    var argv = calculateSumById.arguments;
    var argc = argv.length;
    var itemId;
    var itemObj;
    var itemValue;
    var total = 0;
    var totalObjs;
    var totalObj;
    var i;
    var sign;
    for (i = 0; i < argc - 1; i++) {
        itemId = argv[i];
        sign = itemId.substr(0, 1);
        if(sign == '-' || sign == '+') {
            itemId = itemId.substring(1);
        }
        itemObj = document.getElementById(itemId);
        itemValue = itemObj.value;
        
        if (sign == '-') {
            total = total - toNumber(itemValue);
        } else {
            total = total + toNumber(itemValue);
        }
    }
    total = total.toFixed(0);
    totalObj = document.getElementById(argv[i]);
    totalObj.value = formatNumber(total.toString());
}
function calculateSumFloatNumber() {
    var argv = calculateSumFloatNumber.arguments;
    var argc = argv.length;
    var itemName;
    var itemObjs;
    var itemObj;
    var itemValue;
    var total = 0;
    var totalObjs;
    var totalObj;
    var i;
    var sign;
    for (i = 0; i < argc - 1; i++) {
        itemName = argv[i];
        sign = itemName.substr(0, 1);
        if(sign == '-' || sign == '+') {
            itemName = itemName.substring(1);
        }
        itemObjs = document.getElementsByName(itemName);
        itemObj = itemObjs[0];
        itemValue = itemObj.value;
        
        if (sign == '-') {
            total = total - toNumber(itemValue);
        } else {
            total = total + toNumber(itemValue);
        }
    }
    total = total.toFixed(2);
    totalObjs = document.getElementsByName(argv[i]);
    totalObj = totalObjs[0];
    totalObj.value = toFormatNumberDeFloat(total.toString(),2);
}

function calculateHorizontalProduct() {
    var srcObj = window.event.srcElement;
    var srcObjId = srcObj.id;
    var idSuffix = srcObjId.substring(srcObjId.lastIndexOf('_'));
    var argv = calculateHorizontalProduct.arguments;
    var argc = argv.length;
    var itemName;
    var itemObj;
    var itemValue;
    var product = 1;
    var productObj;
    var i;
    for (i = 0; i < argc - 1; i++) {
        itemName = argv[i];
        if(itemName.indexOf('%') == 0) {
            itemObj = document.getElementById(itemName.substring(1) + idSuffix);
        } else {
            itemObj = document.getElementById(itemName + idSuffix);
        }
        itemValue = itemObj.value;
        itemValue = toNumber(itemValue);
        if(itemName.indexOf('%') == 0) {
            itemValue = itemValue / 100;
        }
        product = product * itemValue;
    }
    product = product.toFixed(0);
    productObj = document.getElementById(argv[i] + idSuffix);

    productObj.value = formatNumber(product.toString());
}
function ReplaceAll(Source,stringToFind,stringToReplace){
  var temp = Source;
    var index = temp.indexOf(stringToFind);
        while(index != -1){
            temp = temp.replace(stringToFind,stringToReplace);
            index = temp.indexOf(stringToFind);
        }
        return temp;
}
function calculateHorizontalProductBigInt() {
    var srcObj = window.event.srcElement;
    var srcObjId = srcObj.id;
    var idSuffix = srcObjId.substring(srcObjId.lastIndexOf('_'));
    var argv = calculateHorizontalProductBigInt.arguments;
    var argc = argv.length;
    var itemName;
    var itemObj;
    var itemValue;
    var product = 1;
    var productObj;
    var i;
    for (i = 0; i < argc - 1; i++) {
        itemName = argv[i];
        if(itemName.indexOf('%') == 0) {
            itemObj = document.getElementById(itemName.substring(1) + idSuffix);
        } else {
            itemObj = document.getElementById(itemName + idSuffix);
        }
        itemValue = itemObj.value;  
        itemValue = ReplaceAll(itemValue,'.','');
        itemValue = itemValue.replace(',','.')
        itemValue = new BigNumber(itemValue);
        if(itemName.indexOf('%') == 0) {
            itemValue = itemValue / 100;
        }
        product = new BigNumber(product).multiply(itemValue);
    }        
    productObj = document.getElementById(argv[i] + idSuffix);
    productObj.value = formatNumber(product.toString());
}
function checkRequire(tableId, objName, fieldName) {
    var objArr = document.getElementsByName(objName);
    var len = objArr.length;

    var tableObj;
    var tbodyObj;
    var invalidRow;
    var objVal;
    for(i = 0; i < len; ++i) {
        objVal = trim(objArr[i].value);
        if(objVal==null || objVal=='') {
            tableObj = document.getElementById(tableId);
            tbodyObj = tableObj.tBodies[0];
            invalidRow = tbodyObj.rows[i];
            objArr[i].focus();
            activeRow(tableId, invalidRow);
            alert('Phải nhập giá trị cho trường [' + fieldName + ']');
            return false;
        }
    }
    return true;
}
// Ham bo cac ky tu trang dau va cuoi xau
// Tham so: s: Xau can cat cac ky tu
function trim(s)
{
   var i;
   if (isNull(s)) return "";
   i = s.length-1;
  while ( i>=0 && s.charAt(i) == ' ' ) i--;
    s = s.substring( 0, i+1 );
    i = 0;
  while ( i< s.length && s.charAt(i) == ' ') i++;
    return s.substring(i);
}

function focusFirstTextElements(tableId) {
    var tableObj = document.getElementById(tableId);
    var tablePropObj = tablePropertyArray[tableId];
    var tbodyObj;
    var currentRowObj = tablePropObj.activeRow;
    var sampleCells = tablePropObj.activeRow.cells;
    var cellCol;
    var cellObj;
    var cellChildNotes;
    var cellChildNote;
    var foundOK = false ;

    tbodyObj = currentRowObj.parentNode;
    while (tbodyObj.tagName != 'TBODY') {
        tbodyObj = tbodyObj.parentNode;
    }
    cellCol = currentRowObj.cells;
    for(j = 0; j < cellCol.length; ++j){
        cellObj = cellCol[j];
        cellChildNotes = cellObj.childNodes;
        for(k = 0; k < cellChildNotes.length; ++k){
            cellChildNote = cellChildNotes[k];
            if(cellChildNote.name != undefined) {
                if(cellChildNote.type != undefined && cellChildNote.type=='text') {    
                    var objFocus = document.getElementById(cellChildNote.id);
                    objFocus.focus();
                    foundOK = true;
                    break;
                    
                }
            }
        }
        
        if(foundOK){
            break;
        }
    }   
}

function focusFirstTextTD(tdId) {
    var tdObj = document.getElementById(tdId);  
    tdObj.focus();
}

function focusFirstCheckbox(nameObj){
    document.getElementsById(nameObj).focus();        
}

function formatNumberForNewRow() {
    var argv = formatNumberForNewRow.arguments;
    var argc = argv.length;
    var tableId = argv[0];
    var tableObj = document.getElementById(tableId);
    var tablePropObj = tablePropertyArray[tableId];
    var tbodyObj;
    var currentRowObj = tablePropObj.activeRow;
    var itemName;
    var itemObj;
    var itemValue;
    var product = 1;
    var productObj;
    var i;
    var data_value;
    var idSuffix = currentRowObj.rowIndex;
    for (i = 1; i < argc; i++) {
        data_value = argv[i].split(':'); 
        itemObj = document.getElementById(data_value[0] +'_'+ idSuffix);
        if(itemObj != null){
            itemObj.value = data_value[1];
        }
    }
}
