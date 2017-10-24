package com.seatech.ttsp.qlyTyGia.action;

import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;

import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DChieu1VO;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.dmnh.DMNHangDAO;
import com.seatech.ttsp.dmnh.DMNHangVO;
import com.seatech.ttsp.dmtiente.DMTienTeDAO;
import com.seatech.ttsp.qlyLaiSuat.form.LaiSuatForm;
import com.seatech.ttsp.qlyTyGia.TyGiaDAO;

import com.seatech.ttsp.qlyTyGia.TyGiaVO;
import com.seatech.ttsp.qlyTyGia.form.TyGiaForm;
import com.seatech.ttsp.qlyphi.form.QuanLyPhiForm;

import java.sql.Connection;

import java.sql.ParameterMetaData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class TyGiaAction extends AppAction {
    private boolean isThanhToanVien;
    //Nhan tu form ben trang tinh phi hoac lai suat
    @Override
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        isThanhToanVien = false;
        try{
            conn = getConnection(request);
            TyGiaDAO tyGiaDAO = new TyGiaDAO(conn);
            DChieu1DAO dcDAO = new DChieu1DAO(conn);
            DMTienTeDAO tienTeDAO = new DMTienTeDAO(conn);
            TyGiaForm tyGiaForm = (TyGiaForm)form;
            HttpSession session = request.getSession();
            
            String nh_kb = session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION) == null ? "": session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
            String kb_code = session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString(); 
            String kb_id = session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
            
            DChieu1VO dcVO = dcDAO.getCap(" and ma=" + kb_code, null);
            String cap = dcVO.getCap();
            
            String condition = getConditionPerRole(kb_code, cap);
            Vector params = null;
            List tyGiaHienTai = null;
            if(tyGiaForm.getFromDate() != null && !tyGiaForm.getFromDate().equals("")){
                params = createTyGiaHienTaiParams(nh_kb,tyGiaForm.getMa_nh(),tyGiaForm.getMa_nt(),tyGiaForm.getFromDate(),tyGiaForm.getToDate());
                tyGiaHienTai = tyGiaDAO.listTyGia(params);
            }else {
                params = createTyGiaHienTaiParams(nh_kb,tyGiaForm.getMa_nh(),tyGiaForm.getMa_nt(),"","");
            }
            List listKhoBac = (List)dcDAO.getDMucKB_huyen(condition, null);
            List tienTe = tienTeDAO.simpleMaNgoaiTe(isThanhToanVien ? " and kb_id = " + kb_id : "", null);
            request.setAttribute("isThanhToanVien", isThanhToanVien);
            request.setAttribute("ma_nh_selected_", tyGiaForm.getMa_nh());
            request.setAttribute("tyGiaHienTai", tyGiaHienTai);
            request.setAttribute("listKhoBac", listKhoBac); 
            request.setAttribute("tienTe", tienTe);
        }catch(Exception e){
            conn.rollback();
            e.printStackTrace();
            throw e;
        }finally{
            close(conn);
        }
        return mapping.findForward("success");
    }

    private Vector createTyGiaHienTaiParams(String ma_kb,String ma_nh, String ma_nt, String tu_ngay,
                                String den_ngay) {
        Vector params = new Vector();
        params.add(new Parameter(ma_kb,true));
        params.add(new Parameter(ma_nh,true));
        params.add(new Parameter(ma_nt,true));
        params.add(new Parameter(tu_ngay,true));
        params.add(new Parameter(den_ngay,true));
        params.add(new Parameter(tu_ngay,true));
        params.add(new Parameter(den_ngay,true));
        params.add(new Parameter(tu_ngay,true));
        return params;
    }
    
    private String getConditionPerRole(String kb_code, String cap) {
        String kbWhereCondition = "";
        if ("0001".equals(kb_code) || "0002".equals(kb_code)) {
            kbWhereCondition = " ";
        }else if ("0003".equals(kb_code)) {
            kbWhereCondition = " AND a.ma='0003' ";
            isThanhToanVien = true;
        }else if ("5".equals(cap)) {
            kbWhereCondition = " and a.ma_cha=" + kb_code;
        }else {
            kbWhereCondition = " and a.ma =" + kb_code;
            isThanhToanVien = true;
        }
        return kbWhereCondition;
    }

    @Override
    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        Connection conn = null;
        try{
            conn = getConnection(request);
            TyGiaDAO tyGiaDAO = new TyGiaDAO(conn);
            String ma_kb = request.getSession().getAttribute("ma_nhkb").toString();
            String shkb = request.getSession().getAttribute("ma_kb").toString();
            TyGiaForm tyGiaForm = (TyGiaForm)form;
            Map parameterMap = request.getParameterMap();
            String[] ngayTyGia = (String[])parameterMap.get("ngay");
            String[] tyGia = (String[])parameterMap.get("tyGia");
            
            //Xóa tyGia c?
            String condition = " and ma_kb = ? and ma_nh = ? and ma_nt = ? and ngay_gd >= to_date(?,'DD/MM/YYYY') and ngay_gd <= to_date(?,'DD/MM/YYYY') ";
            Vector params = new Vector();
            params.add(new Parameter(ma_kb,true)); params.add(new Parameter(tyGiaForm.getMa_nh(),true));
            params.add(new Parameter(tyGiaForm.getMa_nt(),true)); params.add(new Parameter(ngayTyGia[0],true));
            params.add(new Parameter(ngayTyGia[ngayTyGia.length-1],true));
            tyGiaDAO.deleteTyGia(condition,params);
            
            //Insert m?i
            List<TyGiaVO> listTyGia = new ArrayList<TyGiaVO>();
            for(int i = 0; i < ngayTyGia.length; i++){
                TyGiaVO vo = new TyGiaVO();
                vo.setShkb(shkb);
                vo.setMa_kb(ma_kb);
                vo.setMa_nh(tyGiaForm.getMa_nh());
                vo.setNgay_gd(ngayTyGia[i]);
                vo.setMa_nt(tyGiaForm.getMa_nt());
                vo.setTy_gia(tyGia[i]);
                vo.setDescription(" Ty gia ngay " + ngayTyGia[i]);
                vo.setLoai_ty_gia(tyGiaForm.getLoaiTyGia() == null ? "LAI_PHI" : tyGiaForm.getLoaiTyGia());
                vo.setNguoi_tao(request.getSession().getAttribute("id_nsd").toString());
                listTyGia.add(vo);
            }
            tyGiaDAO.insertTyGia(listTyGia);
            conn.commit();
            request.setAttribute("status", "success");
        }catch (Exception e) {
            request.setAttribute("status", "error");
        }finally{
            close(conn);
        }
        return mapping.findForward("success");
    }
}
