package com.seatech.ttsp.qlyphi.action;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;

import com.seatech.ttsp.qlyLaiSuat.LaiSuatVO;
import com.seatech.ttsp.qlyphi.QuanLyPhiDAO;
import com.seatech.ttsp.qlyphi.QuanLyPhiVO;
import com.seatech.ttsp.qlyphi.form.QuanLyPhiForm;

import com.seatech.ttsp.ttthanhtoan.TTThanhToanDAO;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class QuanLyPhiAction extends AppAction {


    @Override
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        try{
            conn = getConnection();
            QuanLyPhiForm f = (QuanLyPhiForm)form;
            QuanLyPhiDAO phiDAO = new QuanLyPhiDAO(conn);
            TTThanhToanDAO ttDAO= new TTThanhToanDAO(conn);
            
            Collection listNganHang = ttDAO.getDMucNH(null,null);
            Collection listPhi = new ArrayList();
            
            String pageNumber = f.getPageNumber();
            int currentPage = pageNumber == null ? 1 : Integer.parseInt(pageNumber);
            int rowOnPage = 20;
            Integer totalCount[] = new Integer[1];
            PagingBean pagingBean = new PagingBean();
            
            Vector params = new Vector();
            String phiCondition = getPhiCondition(f,params);
            listPhi = phiDAO.getPhi_phantrang(phiCondition, params, currentPage, rowOnPage, totalCount);
            
            request.setAttribute("listNganHang", listNganHang);
            request.setAttribute("listPhi", listPhi);
            
            pagingBean.setNumberOfRow(totalCount[0].intValue());
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setRowOnPage(rowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);
        }catch(Exception e){
            throw e;
//            e.printStackTrace();
        }finally{
            close(conn);
        }
        return mapping.findForward("success");
    }

    private String getPhiCondition(QuanLyPhiForm f, Vector params) {
        StringBuilder builder = new StringBuilder("");
        if(f.getHe_thong_ngan_hang()!=null && !"".equals(f.getHe_thong_ngan_hang())){
            builder.append(" AND HE_THONG_NH = ? ");
            params.add(new Parameter(f.getHe_thong_ngan_hang(),true));
        }
        if(f.getLoai_gd()!=null && !"".equals(f.getLoai_gd())){
            builder.append(" AND LOAI_GD = ? ");
            params.add(new Parameter(f.getLoai_gd(),true));
        }
        if(f.getLoai_phi()!=null && !"".equals(f.getLoai_phi())){
            builder.append(" AND LOAI_PHI = ? ");
            params.add(new Parameter(f.getLoai_phi(),true));
        }
        if(f.getLoai_tien()!=null && !"".equals(f.getLoai_tien())){
            builder.append(" AND LOAI_TIEN = ? ");
            params.add(new Parameter(f.getLoai_tien().toUpperCase(),true));
        }
        if(f.getTu_ngay()!=null && !"".equals(f.getTu_ngay())){
            builder.append(" AND TU_NGAY >= to_date(?,'DD/MM/YYYY') ");
            params.add(new Parameter(f.getTu_ngay(),true));
        }
        if(f.getDen_ngay()!=null && !"".equals(f.getDen_ngay())){
            builder.append(" AND DEN_NGAY <= to_date(?,'DD/MM/YYYY') ");
            params.add(new Parameter(f.getDen_ngay(),true));
        }
        return builder.toString();
    }


    @Override
    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        Connection conn = null;
        HttpSession session = request.getSession();
        String id_nsd = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
        boolean add = request.getParameter("add") == null ? false : true;
        try{
            conn = getConnection();
            QuanLyPhiForm f = (QuanLyPhiForm)form;
            f.setLoai_tien(f.getLoai_tien().toUpperCase());
            TTThanhToanDAO ttDAO = new TTThanhToanDAO(conn);
            
            Collection listNganHang = ttDAO.getDMucNH(null,null);
            request.setAttribute("listNganHang", listNganHang);
            
            if(add){
                QuanLyPhiDAO phiDAO = new QuanLyPhiDAO(conn);
                List<QuanLyPhiVO> listAdd = getAddList(request,f,id_nsd);
                if(listAdd.size() > 0){
                    boolean flag = true;
                    for(QuanLyPhiVO temp : listAdd){
                        Map result = phiDAO.callProcValidatePhi(temp);
                        if(result.containsKey("success")){
                            int success = phiDAO.insertPhi(temp);
                            if(success < 1){
                                flag = false;
                                break;
                            }
                        }else{
                            throw new Exception("Th\u00EAm m\u1EDBi l\u1ED7i - D\u1EEF li\u1EC7u th\u00EAm kh\u00F4ng \u0110\u00FAng quy t\u1EAFc");
                        }
                    }
                    if(flag){
                        request.setAttribute("success", "Th\u00E0nh c\u00F4ng");
                        conn.commit();
                    }else{
                        throw new Exception("Th\u00EAm m\u1EDBi l\u1ED7i");
                    }
                }
            }
        }catch(Exception e){
            conn.rollback();
            request.setAttribute("error", e.getMessage());
            e.printStackTrace();
        }finally{
            close(conn);
        }
        return mapping.findForward("success");
    }
    private List<QuanLyPhiVO> getAddList(HttpServletRequest request,QuanLyPhiForm f, String nsd) {
      Map map = request.getParameterMap();
      int totalRow = ((String[]) map.get("tu_ngay")).length;
      List<QuanLyPhiVO> result = new ArrayList<QuanLyPhiVO>();
      for(int i = 0; i<totalRow; i++){
          QuanLyPhiVO vo = new QuanLyPhiVO();
          vo.setHe_thong_nh(f.getHe_thong_ngan_hang());
          vo.setLoai_gd(f.getLoai_gd());
          vo.setLoai_phi(f.getLoai_phi());
          vo.setLoai_tien(f.getLoai_tien());
          vo.setNguoi_tao(nsd);
          
          vo.setGio_tu(((String[])map.get("gio_tu"))[i]);
          vo.setGio_den(((String[])map.get("den_gio"))[i]);
          vo.setGia_tri_tu(((String[])map.get("tien_tu"))[i]);
          vo.setGia_tri_den(((String[])map.get("den_tien"))[i]);
          vo.setMuc_phi(((String[])map.get("muc_phi"))[i]);
          vo.setVat(((String[])map.get("vat"))[i]);
          vo.setSan(((String[])map.get("gia_san"))[i]);
          vo.setTran(((String[])map.get("gia_tran"))[i]);
          vo.setTu_ngay(((String[])map.get("tu_ngay"))[i]);
          result.add(vo);
      }
      return result;
    }

    @Override
    public ActionForward delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        Connection conn = null;
        try{
            conn = getConnection();
            int id = Integer.parseInt(request.getParameter("id"));
            QuanLyPhiDAO phiDAO = new QuanLyPhiDAO(conn);
            String condition = " id = ? ";
            Vector params = new Vector();
            params.add(new Parameter(id,true));
            QuanLyPhiVO phiVO = phiDAO.getPhi(condition,params);
            //1. xoa neu chua su dung
            if(phiVO.getIs_use().equals("00")){
                if(phiDAO.removePhiByID(phiVO) < 1){
                    throw new Exception("Kh\u00F3a l\u1ED7i");
                }
            }else{//2. khoa neu da su dung
                if(phiDAO.updatePhi(phiVO,true) < 1){
                    throw new Exception("Kh\u00F3a l\u1ED7i");
                }
            }
            conn.commit();
            request.setAttribute("success","Kh\u00F3a th\u00E0nh c\u00F4ng");
        }catch(Exception e){
            conn.rollback();
            request.setAttribute("error", e.getMessage());
            e.printStackTrace();
        }finally{
            close(conn);
        }
        return mapping.findForward("success");
    }

    @Override
    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        Connection conn = null;
        boolean execute = request.getParameter("execute") == null ? false : true;
        String id = request.getParameter("id");
        try{
            conn = getConnection();
            QuanLyPhiForm f = (QuanLyPhiForm)form;
            TTThanhToanDAO ttDAO= new TTThanhToanDAO(conn);
            QuanLyPhiDAO phiDAO = new QuanLyPhiDAO(conn);
  
            Vector params = new Vector();
            String condition = " id = ? ";
            params.add(new Parameter(id,true));
            QuanLyPhiVO phiVO = phiDAO.getPhi(condition,params);
            
            Collection listNganHang = ttDAO.getDMucNH(null,null);
            request.setAttribute("listNganHang", listNganHang);
            if(execute){
                QuanLyPhiVO updateVO = new QuanLyPhiVO();
                copyFormToUpdatePhi(f, updateVO);
                Map result = phiDAO.callProcValidatePhi(updateVO);
                if(result.containsKey("success")){
                    if(phiDAO.updatePhi(updateVO, false) > 0){
                        request.setAttribute("success", "S\u1EEDa th\u00E0nh c\u00F4ng");
                        conn.commit();
                        return mapping.findForward("updateSuccess");
                    }else{
                        throw new Exception("S\u1EEDa l\u1ED7i");
                    }
                }else{
                    throw new Exception("S\u1EEDa l\u1ED7i - D\u1EEF li\u1EC7u S\u1EEDa kh\u00F4ng \u0110\u00FAng quy t\u1EAFc");
                }
            }else{
                copyPhiToForm(f, phiVO);
            }
        }catch(Exception e){
            conn.rollback();
            request.setAttribute("error", e.getMessage());
            e.printStackTrace();
        }finally{
            close(conn);
        }
        return mapping.findForward("success");
    }

    private void copyPhiToForm(QuanLyPhiForm f, QuanLyPhiVO phiVO) {
        f.setId(phiVO.getId());
        f.setMa_nh(phiVO.getHe_thong_nh());
        f.setLoai_gd(phiVO.getLoai_gd());
        f.setLoai_phi(phiVO.getLoai_phi());
        f.setLoai_tien(phiVO.getLoai_tien());
        f.setGio_tu(phiVO.getGio_tu());
        f.setDen_gio(phiVO.getGio_den());
        f.setTien_tu(phiVO.getGia_tri_tu());
        f.setDen_tien(phiVO.getGia_tri_den());
        f.setMuc_phi(phiVO.getMuc_phi());
        f.setVat(phiVO.getVat());
        f.setGia_san(phiVO.getSan());
        f.setGia_tran(phiVO.getTran());
        f.setTu_ngay(phiVO.getTu_ngay());
        f.setDen_ngay(phiVO.getDen_ngay());
    }

    private void copyFormToUpdatePhi(QuanLyPhiForm f, QuanLyPhiVO updateVO) {
        updateVO.setId(f.getId());
        updateVO.setHe_thong_nh(f.getMa_nh());
        updateVO.setLoai_gd(f.getLoai_gd());
        updateVO.setLoai_phi(f.getLoai_phi().trim());
        updateVO.setLoai_tien(f.getLoai_tien().trim());
        updateVO.setGio_tu(f.getGio_tu() != null ? f.getGio_tu().trim() : null);
        updateVO.setGio_den(f.getDen_gio() != null ? f.getDen_gio().trim() : null);
        updateVO.setGia_tri_tu(f.getTien_tu() != null ? f.getTien_tu().trim() : null);
        updateVO.setGia_tri_den(f.getDen_tien() != null ? f.getDen_tien().trim() : null);
        updateVO.setMuc_phi(f.getMuc_phi().trim());
        updateVO.setVat(f.getVat().trim());
        updateVO.setSan(f.getGia_san() != null ? f.getGia_san().trim() : null);
        updateVO.setTran(f.getGia_tran() != null ? f.getGia_tran().trim() : null);
        updateVO.setTu_ngay(f.getTu_ngay().trim());
        updateVO.setDen_ngay(f.getDen_ngay() != null ? f.getDen_ngay().trim() : null);
    }
}
