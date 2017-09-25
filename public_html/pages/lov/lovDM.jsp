    
<div style="width:500px;height:550px; border-top: 1px solid green;
                                                                          border-right: 1px solid green;
                                                                          border-bottom: 1px solid green;
                                                                          border-left: 1px solid green;">
  <form action="lovAction.do" method="post">
    <table style="width:100%;height:100%;">
      <tr>
        <td>M&atilde;</td>
        <td>
          <input type="text" name="ma_lov" id="ma_lov" size="10" onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
        </td>
        <td>T&ecirc;n</td>
        <td>
          <input type="text" name="ten_lov" id="ten_lov" size="40" onkeydown="if(event.keyCode==13) fillLovDM();"/>
           
          <input type="hidden" name="loai_lov" id="loai_lov"/>
           
          <input type="hidden" name="ma_field_id_lov" id="ma_field_id_lov"/>
           
          <input type="hidden" name="ten_field_id_lov" id="ten_field_id_lov"/>
           
          <input type="hidden" name="id_field_id_lov" id="id_field_id_lov"/>
        </td>
        <td>
          <button type="button" name="btnSearch" onclick="fillLovDM();">T&igrave;m</button>
        </td>
      </tr>
    </table>
    <br></br>
    <div>
      <table cellspacing="0" width="100%">
        <thead>
          <tr>
            <th width="20%" class="ui-state-default ui-th-column">M&atilde;</th>
            <th width="77%" class="ui-state-default ui-th-column">T&ecirc;n</th>
            <th class="ui-state-default ui-th-column">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
          </tr>
        </thead>
      </table>
    </div>
    <div style="height:450px;" class="ui-jqgrid-bdiv ui-widget-content">
      <div>
        <table class="data-grid-lov" id="data-grid-lov"
                     style="BORDER-COLLAPSE: collapse;" cellspacing="0"
                     cellpadding="0" width="100%">
          <tbody id="tblLovDM"></tbody>
        </table>
      </div>
    </div>
  </form>
</div>