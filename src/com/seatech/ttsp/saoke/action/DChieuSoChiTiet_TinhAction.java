package com.seatech.ttsp.saoke.action;

import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;

import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DChieu1VO;
import com.seatech.ttsp.saoke.form.DChieuSoChiTiet_TinhForm;

import com.seatech.ttsp.saoke.saoketkDAO;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;

import java.util.List;
import java.util.Map;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DChieuSoChiTiet_TinhAction extends AppAction {

    @Override
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        boolean execute = request.getParameter("execute") == null ? false : true;
        try{
            conn = getConnection();
            HttpSession session = request.getSession();
            DChieu1DAO dcDAO = new DChieu1DAO(conn);
            saoketkDAO saoKeDAO = new saoketkDAO(conn);
            DChieu1VO dcVO = new DChieu1VO();
            
            String kb_code = session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
            DChieuSoChiTiet_TinhForm f = (DChieuSoChiTiet_TinhForm) form;
            
            Collection listSoChiTiet = null;
            
            dcVO = dcDAO.getCap(" and ma=" + kb_code, null);
            String cap = dcVO.getCap();
            String kbWhereCondition = getRoleCondition(kb_code, cap);
            Collection listKBTinh = dcDAO.getDMucKB_cha(kbWhereCondition, null);
            
            request.setAttribute("listKBTinh", listKBTinh);
            
            if(execute){
                String ma_kb = dcDAO.getMaKB(f.getKb_tinh());
                Map result = saoKeDAO.runDoiChieuSoChiTietTinh(f,ma_kb);
                if(!result.containsKey("success")){
                    request.setAttribute("error", result.get("error"));
                }else{
                    String condition = "and a.NGAY_DC = to_date(?,'DD/MM/YYYY') and id_cha = ? ";
                    Vector params = new Vector();
                    params.add(new Parameter(f.getNgay(),true));params.add(new Parameter(f.getKb_tinh(),true));
                    listSoChiTiet = saoKeDAO.getDChieuSaoKeTW(condition,params);
                }
            }
            request.setAttribute("listSoChiTiet", listSoChiTiet);
        }catch(Exception e){
            conn.rollback();
//            e.printStackTrace();
            throw e;
        }finally{
            close(conn);
        }
        return mapping.findForward("success");
    }
    
    private String getRoleCondition(String kb_code, String cap) {
        String kbWhereCondition = " ";
        if ("0001".equals(kb_code) || "0002".equals(kb_code)) {
            kbWhereCondition = " ";
        }else if ("0003".equals(kb_code)) {
            kbWhereCondition = " AND a.ma='0003' ";
        }else if ("5".equals(cap)) {
            kbWhereCondition = " and a.ma_cha=" + kb_code;
        }else {
            kbWhereCondition = " and a.ma =" + kb_code;
        }
        return kbWhereCondition;
    }
}
