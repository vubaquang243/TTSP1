package com.seatech.ttsp.dmnh.action;


import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.FontUtil;
import com.seatech.ttsp.dmnh.DMNHangDAO;
import com.seatech.ttsp.dmnh.DMNHangVO;

import java.sql.Connection;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class LoadTenNHangAction extends AppAction {
    protected ActionForward executeAction(ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
        Connection conn = null;

        try {
            String strMaNH;
            String strJSON;
            String strWhereClause;
            Vector vParam;

            conn = getConnection();
            strJSON = request.getParameter("strJSON");
            JSONObject jsonReq = JSONObject.fromObject(strJSON);
            strMaNH = jsonReq.get("ma").toString();

            DMNHangDAO dmnhDAO = new DMNHangDAO(conn);
            strWhereClause = " tinh_trang = 1 and ma_nh = ? ";
            vParam = new Vector();
            vParam.add(new Parameter(strMaNH, true));
            DMNHangVO dmnhVO = dmnhDAO.getDMNH(strWhereClause, vParam);
            JSONObject jsonRes = new JSONObject();

            if (dmnhVO != null && dmnhVO.getTen() != null) {
                jsonRes.put("ten", FontUtil.unicodeEscape(dmnhVO.getTen()));
                jsonRes.put("id", dmnhVO.getId());
            } else {
              dmnhVO = dmnhDAO.getDMNH_NN(" a.tinh_trang='1' and a.bic_code||a.branch_code = ? and rownum = 1 ", vParam);
              if (dmnhVO != null && dmnhVO.getTen() != null) {
                  jsonRes.put("ten", FontUtil.unicodeEscape(dmnhVO.getTen()));
                  jsonRes.put("id", dmnhVO.getId());
              } else {
                jsonRes.put("ten", "0");
              }
            }          
            response.setHeader("X-JSON", jsonRes.toString());
        } catch (Exception e) {
//            HashMap map = new HashMap();
//            map.put("executeError",FontUtil.unicodeEscape("Lỗi: "+e.getMessage()));
//
//            JSONObject jsonRes = JSONObject.fromObject(map);
            
          JSONObject jsonRes = new JSONObject();
          jsonRes.put("executeError", "0");
            response.setHeader("X-JSON", jsonRes.toString());

        }finally{
          close(conn);
        }

        return mapping.findForward("success");
    }    
}
