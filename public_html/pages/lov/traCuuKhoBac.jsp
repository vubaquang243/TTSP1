<div style="width:500px;height:550px; border-top: 1px solid green;
  border-right: 1px solid green;
  border-bottom: 1px solid green;
  border-left: 1px solid green;">
  <form action="traCuuKhoBac.do" method="post">
    <table style="width:100%;height:100%;">
      <tr>
        <td id="id_kho_bac" >Mã kho bạc</td>
        <td>
          <input type="text" name="ma_kb" id="ma_kb" size="10" onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
          <input type="hidden" id="id_kho_bac_tinh_1" />
          <input type="hidden" id="id_kho_bac_huyen_1" />
        </td>
        <td id="ten_kho_bac">Tên kho bạc</td>
        <td>
          <input type="text" name="ten_kb" id="ten_kb" size="40" onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
        </td>
        <td>
          <button type="button" name="btnSearch" onclick="traCuuKhoBac();">T&igrave;m</button>
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
                     cellpadding="1" width="100%">
          <tbody id="listKhoBac"></tbody>
        </table>
      </div>
    </div>
  </form>
</div>