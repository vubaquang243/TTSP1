package com.seatech.ttsp.qlyLaiSuat.action;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.TTSPException;
import com.seatech.framework.strustx.AppAction;

import com.seatech.framework.utils.StringUtil;
import com.seatech.ttsp.qlyLaiSuat.LaiSuatDAO;
import com.seatech.ttsp.qlyLaiSuat.LaiSuatVO;
import com.seatech.ttsp.qlyLaiSuat.form.LaiSuatForm;

import com.seatech.ttsp.tsotabmis.tsoTabmisVO;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanDAO;

import java.sql.Connection;

import java.sql.ParameterMetaData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LaiSuatAction extends AppAction {

    @Override
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        HttpSession session = request.getSession();
        String kb_code = session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
        if ("0001".equals(kb_code) || "0002".equals(kb_code)) {
            try{
                conn = getConnection();
                LaiSuatForm f = (LaiSuatForm)form;
                LaiSuatDAO lsDAO = new LaiSuatDAO(conn);
                TTThanhToanDAO ttDAO= new TTThanhToanDAO(conn);
                
                Collection listNganHang = ttDAO.getDMucNH(null,null);
                Collection listLaiSuat = new ArrayList();
                
                String pageNumber = f.getPageNumber();
                int currentPage = pageNumber == null ? 1 : Integer.parseInt(pageNumber);
                int rowOnPage = 20;
                Integer totalCount[] = new Integer[1];
                PagingBean pagingBean = new PagingBean();
                
                Vector params = new Vector();
                String laiSuatCondition = getLaiSuatCondition(f,params);
                listLaiSuat = lsDAO.getLaiSuat_phantrang(laiSuatCondition, params, currentPage, rowOnPage, totalCount);
                
                request.setAttribute("listNganHang", listNganHang);
                request.setAttribute("listLaiSuat", listLaiSuat);
                
                pagingBean.setNumberOfRow(totalCount[0].intValue());
                pagingBean.setCurrentPage(currentPage);
                pagingBean.setRowOnPage(rowOnPage);
                request.setAttribute("PAGE_KEY", pagingBean);
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                close(conn);
            }
            return mapping.findForward(AppConstants.SUCCESS);
        }else{
            return mapping.findForward(AppConstants.ERROR);
        }
    }

    private String getLaiSuatCondition(LaiSuatForm f, Vector params) {
        StringBuilder builder = new StringBuilder("");
        if(f.getMa_dv()!=null && !"".equals(f.getMa_dv())){
            builder.append(" AND HE_THONG_NH = ? ");
            params.add(new Parameter(f.getMa_dv(),true));
        }
        if(f.getLoaiLaiSuat()!=null && !"".equals(f.getLoaiLaiSuat())){
            builder.append(" AND LOAI_LAI_SUAT = ? ");
            params.add(new Parameter(f.getLoaiLaiSuat(),true));
        }
        if(f.getLaiSuat()!=null && !"".equals(f.getLaiSuat())){
            builder.append(" AND LAI_SUAT = ? ");
            params.add(new Parameter(f.getLaiSuat(),true));
        }
        if(f.getLoaiTien()!=null && !"".equals(f.getLoaiTien())){
            builder.append(" AND LOAI_TIEN = ? ");
            params.add(new Parameter(f.getLoaiTien().toUpperCase(),true));
        }
        if(f.getFromDate()!=null && !"".equals(f.getFromDate())){
            builder.append(" AND NGAY_HIEU_LUC >= to_date(?,'DD/MM/YYYY') ");
            params.add(new Parameter(f.getFromDate(),true));
        }
        if(f.getToDate()!=null && !"".equals(f.getToDate())){
            builder.append(" AND NGAY_HIEU_LUC <= to_date(?,'DD/MM/YYYY') ");
            params.add(new Parameter(f.getToDate(),true));
        }
        return builder.toString();
    }

    @Override
    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        Connection conn = null;
        HttpSession session = request.getSession();
        String kb_code = session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
        String id_nsd = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
        String order = request.getParameter("order");
        boolean orderAdd = order != null;
        if ("0001".equals(kb_code) || "0002".equals(kb_code)) {
            try{
                conn = getConnection();
                LaiSuatForm f = (LaiSuatForm)form;
                LaiSuatDAO lsDAO = new LaiSuatDAO(conn);
                TTThanhToanDAO ttDAO= new TTThanhToanDAO(conn);
                
                Collection listNganHang = ttDAO.getDMucNH(null,null);
                request.setAttribute("listNganHang", listNganHang);
                
                if(orderAdd){
                    f.setLoaiTien(f.getLoaiTien().toUpperCase());
                    Vector params = new Vector();
                    params.add(new Parameter(f.getMa_dv(),true));params.add(new Parameter(f.getLoaiTien(),true));
                    if(lsDAO.checkExit(null, params)>0){
                        String checkDateCondition = " and ngay_hieu_luc < to_date(?,'DD/MM/YYYY') ";
                        params.add(new Parameter(f.getFromDate(),true));
                        int idLaiSuatCu = lsDAO.checkExit(checkDateCondition,params);
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(StringUtil.StringToDate(f.getFromDate(), "dd/MM/yyyy"));
                        if(idLaiSuatCu > 0){
                            params.clear();params.add(new Parameter(f.getFromDate(),true));params.add(new Parameter(idLaiSuatCu,true));
                            if(lsDAO.setEndDate(params) > 0){
                                insertLaiSuat(conn, id_nsd, f, lsDAO);
                                request.setAttribute("success", "Th\u00EAm m\u1EDBi l\u00E3i su\u1EA5t th\u00E0nh c\u00F4ng.");
                            }
                            else{
                                request.setAttribute("error", "L\u1ED7i th\u00EAm m\u1EDBi l\u00E3i su\u1EA5t - Do kh\u00F4ng thay \u0111\u1ED5i \u0111\u01B0\u1EE3c l\u00E3i su\u1EA5t c\u0169.");
                            }
                        }else{
                          request.setAttribute("error", "L\u1ED7i th\u00EAm m\u1EDBi l\u00E3i su\u1EA5t - Do ng\u00E0y nh\u1EADp v\u00E0o nh\u1ECF h\u01A1n ng\u00E0y c\u00F3 hi\u1EC7u l\u1EF1c c\u1EE7a l\u00E3i su\u1EA5t c\u0169.");
                        }
                    }else{
                        insertLaiSuat(conn, id_nsd, f, lsDAO);
                        request.setAttribute("success", "Th\u00EAm m\u1EDBi l\u00E3i su\u1EA5t th\u00E0nh c\u00F4ng.");
                    }
                }
            }catch(Exception e){
                conn.rollback();
                e.printStackTrace();
            }finally{
                close(conn);
            }
            return mapping.findForward(AppConstants.SUCCESS);
        }else{
            return mapping.findForward(AppConstants.ERROR);
        }
    }

    private void insertLaiSuat(Connection conn, String id_nsd, LaiSuatForm f,
                               LaiSuatDAO lsDAO) throws Exception {
      LaiSuatVO laiSuat = new LaiSuatVO();
      setValue(laiSuat, f);
      laiSuat.setNguoi_tao(id_nsd);
      if(lsDAO.insertLaiSuat(laiSuat) > 0) conn.commit(); 
      else conn.rollback();
    }
    
    private void setValue(LaiSuatVO vo, LaiSuatForm f){
        vo.setHe_thong_nh(f.getMa_dv());
        vo.setLoai_lai_suat(f.getLoaiLaiSuat());
        vo.setLoai_tien(f.getLoaiTien());
        vo.setLai_suat(f.getLaiSuat());
        vo.setGhi_chu(f.getGhiChu());
        vo.setNgay_hieu_luc(f.getFromDate());
    }


    @Override
    public ActionForward delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
      Connection conn = null;
      try{
          conn = getConnection();
          LaiSuatDAO lsDAO = new LaiSuatDAO(conn);
          String index = request.getParameter("index");
          Vector params = new Vector();
          LaiSuatVO laiSuatTagetVO = getUpdateList(request, index);
          
          String conditionFindLower = " b.TEN_NH = ? AND a.NGAY_HET_HIEU_LUC = (to_date(?,'DD/MM/YYYY')-1) ";
          params.clear(); params.add(new Parameter(laiSuatTagetVO.getHe_thong_nh(),true)); params.add(new Parameter(laiSuatTagetVO.getNgay_hieu_luc(),true));
          LaiSuatVO laiSuatLowerVO = lsDAO.getLaiSuat(conditionFindLower, params);
          
          if(laiSuatTagetVO.getNgay_het_hieu_luc() == null || "".equals(laiSuatTagetVO.getNgay_het_hieu_luc())){ //highest
              if(laiSuatLowerVO == null){
                  if(lsDAO.removeLaiSuat(laiSuatTagetVO) < 1){
                      throw new Exception("X\u00F3a l\u00E3i su\u1EA5t l\u1ED7i.");
                  }
              }else{
                  laiSuatLowerVO.setNgay_het_hieu_luc("");
                  if(lsDAO.removeLaiSuat(laiSuatTagetVO) > 0){
                      if(lsDAO.updateLaiSuat(laiSuatLowerVO) < 1){
                          throw new Exception("X\u00F3a l\u00E3i su\u1EA5t l\u1ED7i - S\u1EEDa l\u00E3i su\u1EA5t c\u0169 b\u1ECB l\u1ED7i.");
                      }
                  }else{
                      throw new Exception("X\u00F3a l\u00E3i su\u1EA5t l\u1ED7i.");
                  }
              }
          }else if(laiSuatLowerVO != null){//middle
              if(lsDAO.removeLaiSuat(laiSuatTagetVO) > 0){
                  laiSuatLowerVO.setNgay_het_hieu_luc(laiSuatTagetVO.getNgay_het_hieu_luc());
                  if(lsDAO.updateLaiSuat(laiSuatLowerVO) < 1){
                      throw new Exception("X\u00F3a l\u00E3i su\u1EA5t l\u1ED7i - S\u1EEDa l\u00E3i su\u1EA5t c\u0169 b\u1ECB l\u1ED7i.");
                  }
              }else{
                  throw new Exception("X\u00F3a l\u00E3i su\u1EA5t l\u1ED7i.");
              }
          }else{
              if(lsDAO.removeLaiSuat(laiSuatTagetVO) < 1){
                 throw new Exception("X\u00F3a l\u00E3i su\u1EA5t l\u1ED7i.");
              }
          }
          conn.commit();
          request.setAttribute("success", "X\u00F3a Th\u00E0nh c\u00F4ng.");
      }catch(NullPointerException ex){
          request.setAttribute("error", "L\u1ED7i f5 - Thi\u1EBFu d\u1EEF li\u1EC7u.");
      }catch(Exception e){
          request.setAttribute("error", e.getMessage());
          conn.rollback();
          e.printStackTrace();
      }finally{
          close(conn);
      }
      return mapping.findForward(AppConstants.SUCCESS);
    }
    
    private LaiSuatVO getUpdateList(HttpServletRequest request,String index) {
      Map map = request.getParameterMap();
      LaiSuatVO vo = new LaiSuatVO();
      if(index != null){
          int i = Integer.parseInt(index);
          vo.setHe_thong_nh(((String[])map.get("he_thong_nh"))[i]);
          vo.setLai_suat(((String[])map.get("lai_suat"))[i]);
          vo.setLoai_lai_suat(((String[])map.get("loai_lai_suat"))[i]);
          vo.setNgay_hieu_luc(((String[])map.get("ngay_hieu_luc"))[i]);
          vo.setNgay_het_hieu_luc((String[])map.get("ngay_het_hieu_luc")==null?"":((String[])map.get("ngay_het_hieu_luc"))[i]);
          vo.setLoai_tien(((String[])map.get("loai_tien"))[i]);
      }
      return vo;
    }

    @Override
    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        String index = request.getParameter("index");
        LaiSuatVO vo = getUpdateList(request,index);
        LaiSuatForm f = (LaiSuatForm)form;
        f.setMa_dv(vo.getHe_thong_nh());
        f.setFromDate(vo.getNgay_hieu_luc());
        f.setLaiSuat(vo.getLai_suat());
        f.setLoaiLaiSuat(vo.getLoai_lai_suat());
        f.setLoaiTien(vo.getLoai_tien());
        resetToken(request);
        saveToken(request);
        return mapping.findForward(AppConstants.SUCCESS);
    }

    @Override
    public ActionForward updateExc(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        return mapping.findForward(AppConstants.SUCCESS);
    }
}
