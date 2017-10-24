package com.seatech.ttsp.dchieungoaite.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.ttsp.dchieu.DChieu1VO;
import com.seatech.ttsp.dchieu.KQDChieu1VO;
import com.seatech.ttsp.dchieu.form.DChieu1Form;
import com.seatech.ttsp.dchieungoaite.DChieuNgoaiTeDAO;
import com.seatech.ttsp.thamso.ThamSoHThongDAO;
import com.seatech.ttsp.thamso.ThamSoHThongVO;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.Statement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/*
 * HungBM edit: add
 * Them code cap nhat loai doi chieu cho bang ke
 * Cap nhat loai doi chieu = THUCONG
 * 28/11/2016
 * Search: 20161128
 */
public class DChieuNgoaiTeAction extends AppAction {

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "DCHIEU.NT_DC1")) {
            return mapping.findForward("notRight");
        }

        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            DChieuNgoaiTeDAO dao = new DChieuNgoaiTeDAO(conn);
            DChieu1Form DCForm = (DChieu1Form)form;
            DChieu1VO vo = new DChieu1VO();
                       
            String kb_nhan = session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
            String id_kb = session.getAttribute(AppConstants.APP_NHKB_ID_SESSION).toString();

            ThamSoHThongVO tsVO= new ThamSoHThongVO();
            ThamSoHThongDAO tsDAO= new ThamSoHThongDAO(conn);
            String strTS = " id=123 and ten_ts='GIOI_HAN_NGAY_LIST_DSACH_DCHIEU'";
            tsVO = tsDAO.getThamSo(strTS, null);
            String giatri_ts= tsVO.getGiatri_ts();
            String tu_ngay ="";
            if(!"0".equals(giatri_ts)&&!"".equals(giatri_ts)&&giatri_ts!=null){
                tu_ngay= "And TRUNC (d1.ngay_gd+1) > TRUNC (SYSDATE -"+giatri_ts+")";
            }
  
//            ThamSoKBVO kbVO = new ThamSoKBVO();
//            ThamSoKBDAO kbDAO = new ThamSoKBDAO(conn);
//            String strKB = " and e.ten_ts='CHO_PHEP_QUYET_TOAN_TAM' AND a.kb_id=" +
//                            session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString() + " AND a.loai_gd='03'";
//            kbVO = kbDAO.getLoai_GD(strKB, null);
            String ma_nh=null;
//            if(kbVO!=null){
//                ma_nh=kbVO.getMa_nh();
//            }
            
            ArrayList<DChieu1VO> colDChieu = (ArrayList<DChieu1VO>)dao.getListDC(kb_nhan, id_kb,ma_nh,tu_ngay, null);
            if (colDChieu.isEmpty()) {
                return mapping.findForward("success");
            }

            request.setAttribute("colDChieu", colDChieu);

            String rowSelected = request.getParameter("rowSelected");

            if (null == rowSelected || "".equals(rowSelected)) {
                vo = colDChieu.get(0);
                BeanUtils.copyProperties(DCForm, vo);
                request.setAttribute("rowSelected", "row_qt_0");
            } else {
                request.setAttribute("rowSelected", rowSelected);
            }

            String mt_id = DCForm.getMt_id();
            if ((!"".equals(mt_id) && mt_id != null)) {
                String strUD2 = " AND a.loai_tien = b.loai_tien(+) and a.mt_id = '"+DCForm.getMt_id()+"' ";
                Collection colThop = new ArrayList();
                colThop = dao.getDChieuList(strUD2, null);
                request.setAttribute("colThop", colThop);
            }
            String kq_id = DCForm.getKq_id();
            if (!"".equals(kq_id) && kq_id != null) {
                String strWhere = kq_id;
                ArrayList colKQDC = new ArrayList();
                colKQDC = (ArrayList)dao.getKQDChieu(strWhere, null);
                ArrayList colTemp = new ArrayList();
                if(!colKQDC.isEmpty())
                  colTemp.add(colKQDC.get(0));
                
                request.setAttribute("colKQDC",colTemp);
                Collection colKQDCCT = new ArrayList();
                colKQDCCT = dao.getKQDCChiTiet(strWhere, null);
                request.setAttribute("colKQDCCT", colKQDCCT);
            }
            saveToken(request);
        } catch (Exception e) {
            throw e;

        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }


    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception {

        if (!checkPermissionOnFunction(request, "DCHIEU.NT_DC1")) {
            return mapping.findForward("notRight");
        }
        Connection conn = null;
        HttpSession session = request.getSession();
        conn = getConnection(request);
        String strUD = "";
        //        Collection chkcol = new ArrayList();
        try {
            Long nUserID = new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
            String kb_chuyen = session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();

            DChieu1Form DCForm = (DChieu1Form)form;
            DChieuNgoaiTeDAO dao = new DChieuNgoaiTeDAO(conn);
            KQDChieu1VO kqVO = new KQDChieu1VO();

            String mt_id = DCForm.getMt_id();

            if (mt_id == null || "".equals(mt_id) || "undefined".equals(mt_id)) {
                return mapping.findForward(AppConstants.SUCCESS);
            }

            String strNG = " AND mt_id='" + mt_id + "'";
            kqVO = dao.getNgay_dc(strNG, null);
            String ngay_dc = kqVO.getNgay_dc();
            String send_bank = DCForm.getSend_bank();

            CallableStatement cs = null;
            cs = conn.prepareCall("{call sp_doi_chieu_ngoai_te_pkg.pro_ktra_dc_ngay_truoc(?,?,?,?,?)}");
            cs.setString(1, kb_chuyen);
            cs.setString(2, send_bank);
            cs.setString(3, ngay_dc);
            cs.registerOutParameter(4, java.sql.Types.VARCHAR);
            cs.registerOutParameter(5, java.sql.Types.VARCHAR);
            
            cs.execute();
            String p_err_code = cs.getString(4);
            String p_err_desc = cs.getString(5);
            
            if(p_err_code.equals("00")){
                ngay_dc = StringUtil.DateToString(StringUtil.StringToDate(ngay_dc, "dd/MM/yyyy"),"dd/MM/yyyy");
                cs = null;
                cs = conn.prepareCall("{call sp_doi_chieu_ngoai_te_pkg.proc_dc_truyen_tin_ngoai_te(?,?,?,?,?,?)}");
                cs.setString(1, mt_id);
                cs.setString(2, ngay_dc);
                cs.setLong(3, nUserID);
                cs.registerOutParameter(4, java.sql.Types.VARCHAR);
                cs.registerOutParameter(5, java.sql.Types.VARCHAR);
                cs.registerOutParameter(6, java.sql.Types.VARCHAR);
                
                cs.execute();
                String p_kq_id = cs.getString(4);
                p_err_code = cs.getString(5);
                p_err_desc = cs.getString(6);
                             
                if (!"0".equals(p_err_code)) {
                    request.setAttribute("err", p_err_desc);
                } else {
                    //20161128-HungBM-cap nhat trang thai doi chieu la THUCONG
                    /* --------------------START---------------------- */
                    
                    if (p_kq_id != null && !"".equals(p_kq_id)){
                      Statement stmt = null;
                      String strUpdate = "update sp_065_ngoai_te set TT_DC_TU_DONG ='THUCONG' where mt_id ='"+p_kq_id+"'";
                      stmt = conn.createStatement();
                      int rowUpdated = stmt.executeUpdate(strUpdate);
                      if (rowUpdated>0){
                        conn.commit();
                      }
                      //update SP_BK_DC1 => da doi chieu
                      Statement stmtBK = null;
                      
                      String strUpdateBK = "update sp_bk_dc1_ngoai_te set TT_DC_TU_DONG ='02' where MT_ID = '"+mt_id+"' ";
                      stmtBK = conn.createStatement();
                      int rowUpdatedBK = stmtBK.executeUpdate(strUpdateBK);
                      if (rowUpdatedBK>0){
                        conn.commit();
                      }
                    }
                    
                    /* --------------------END------------------------ */
                    strUD += p_kq_id;
                    ArrayList col = new ArrayList();
                    col = (ArrayList)dao.getKQDChieu(strUD, null);
                    
                    ArrayList colTemp = new ArrayList();
                    if(!col.isEmpty())
                      colTemp.add(col.get(0));
                    
                    request.setAttribute("colKQDC", colTemp);
                    Collection colKQDCCT = new ArrayList();
                    colKQDCCT = dao.getKQDCChiTiet(strUD, null);
                    
                    request.setAttribute("colKQDCCT", colKQDCCT);
                    return mapping.findForward("success");
                }
            }else{
                request.setAttribute("err", p_err_desc);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }

        return mapping.findForward("success");
    }
    // Update TThai va KQua tao dien xac nhan

    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "DCHIEU.NT_DC1")) {
            return mapping.findForward("notRight");
        }

        Connection conn = null;
        try {
            if (isTokenValid(request)) {
                conn = getConnection(request);
                HttpSession session = request.getSession();
                
                DChieu1Form doiChieuForm = (DChieu1Form)form;
                DChieuNgoaiTeDAO doiChieuDAO = new DChieuNgoaiTeDAO(conn);
                KQDChieu1VO dcVO = null;
               
                String nhkb_chuyen = session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
                String ngay_dc = doiChieuForm.getNgay_dc();
                String mt_id = doiChieuForm.getMt_id();
                
                if (mt_id == null || "".equals(mt_id)) {
                    return mapping.findForward(AppConstants.SUCCESS);
                }
                
                // check trang thai ngay doi chieu sysdate-1
                String conditionTrangThai =
                    " AND d1.ma_nh_kb = '" + nhkb_chuyen + "' AND trunc(d1.ngay_gd+1) < TO_DATE ('" + ngay_dc + "', 'DD/MM/YYYY') " +
                   // "AND trunc(d1.ngay_gd+1) > sp_util_pkg.fnc_get_ngay_trien_khai('" + nhkb_chuyen + "', '"+doiChieuForm.getSend_bank()+"', 'NGOAI TE?')" +
                    "AND TO_CHAR (d1.ngay_gd+1, 'YYYYMMDD') NOT IN " +
                    " (SELECT ngay FROM sp_ngay_nghi) " +
                    " AND loai_tien in (select ma_nt from sp_tknh_kb where quyet_toan ='Y') " + 
                    "AND ngay_dc > sp_util_pkg.fnc_get_ngay_trien_khai(send_bank, receive_bank, loai_tien)) AND APP_type='TTSP' AND send_bank='" +
                    nhkb_chuyen + "' AND receive_bank='" + doiChieuForm.getSend_bank() + "')";
        
                //Can bo sung check theo ngay trien khai 
				
                ArrayList<DChieu1VO> resultTrangThai = (ArrayList<DChieu1VO>)doiChieuDAO.getCheckTThai(conditionTrangThai, null);

                // check tthai_dxn_thop
                Collection tThai_dxn_thop  = new ArrayList();
                String strKq = " AND send_bank= '" + nhkb_chuyen + "' AND receive_bank='" +
                                doiChieuForm.getSend_bank() + "' AND app_type='TTSP' ";
                tThai_dxn_thop = doiChieuDAO.getCheckKQDXN(strKq, null);

                if (resultTrangThai.isEmpty()) { // Truong hop 1 ngay sysdate - 1 khong co
                    // check lay thong tin ket qua va chenh lech thu cong
                    String conditionKQ_thuCong = " and a.bk_id='" + doiChieuForm.getMt_id() + "' and a.trang_thai='00'";
                    dcVO = new KQDChieu1VO();
                    dcVO = (KQDChieu1VO)((List)doiChieuDAO.getListSDThuCong(conditionKQ_thuCong, null)).get(0);

                    if (tThai_dxn_thop.size() != 0) { // ton tai tthai_dxn_thop=01
                        request.setAttribute("createF", "createF");
                        return mapping.findForward("failure");
                    } else if (tThai_dxn_thop.isEmpty()) { // ko ton tai
                        String tthai = dcVO.getTrang_thai_bkdc();
                        BeanUtils.copyProperties(form, dcVO); // copy value tu form vao vo
                        if ((mt_id == null || "".equals(mt_id)) || !"00".equals(tthai)) {
                            return mapping.findForward("failure");
                        }

                        long clech = dcVO.getChenh_lech();
                        String kq = dcVO.getKet_qua();
                        String kq_id = doiChieuForm.getId();

                        if (kq_id == null || "".equals(kq_id)) {
                            return mapping.findForward("failure");
                        }
                        
                        dcVO = new KQDChieu1VO();
                        dcVO.setBk_id(doiChieuForm.getBk_id());
                        dcVO.setMt_id(doiChieuForm.getMt_id());
                        dcVO.setKet_qua(kq);
                        dcVO.setChenh_lech(clech);
                        if(doiChieuDAO.updateKQ(dcVO) > 0){
                            if(doiChieuDAO.updateTTBK(dcVO) > 0) conn.commit();
                            else throw new Exception("updateTTBK update sp_bk_dc1_ngoai_te fail");
                        }else{
                            throw new Exception("updateKQ update sp_065_ngoai_te fail");
                        }
                        if ("1".equals(kq)) { // chenh lech
                            request.setAttribute("DXN_clech", "DXN_clech");
                        } else if ("0".equals(kq)) {
                            request.setAttribute("DXN_dung", "DXN_dung");
                        }
                        return mapping.findForward("success");
                    } else {
                        request.setAttribute("kocobangke", "kocobangke");
                        return mapping.findForward("success");
                    }
                } else { // else do
                    DChieu1VO vo = resultTrangThai.get(0);
                    if (!"02".equals(vo.getTthai_dxn_thop()) && !"03".equals(vo.getTthai_dxn_thop())) {
                        request.setAttribute("chuataodxn", "chuataodxn");
                        return mapping.findForward("success");
                    } else {
                        String conditionKQ_thuCong = " and a.bk_id='" + mt_id + "' and a.trang_thai='00'";
                        dcVO = new KQDChieu1VO();
                        dcVO = (KQDChieu1VO)((List)doiChieuDAO.getListSDThuCong(conditionKQ_thuCong, null)).get(0);
                        if ("".equals(dcVO) || dcVO == null) {
                            return mapping.findForward("failure");
                        }
  
                        if (tThai_dxn_thop.size() != 0) {
                            request.setAttribute("createF", "createF");
                            return mapping.findForward("failure");
                        } else if (tThai_dxn_thop.isEmpty()) {
                            String tthai = dcVO.getTrang_thai_bkdc();
                            BeanUtils.copyProperties(form, dcVO);
                            if ((mt_id == null || "".equals(mt_id)) || !"00".equals(tthai)) {
                                return mapping.findForward("failure");
                            }
  
                            long clech = dcVO.getChenh_lech();
                            String kq = dcVO.getKet_qua();
                            String kq_id = doiChieuForm.getId();
  
                            if (kq_id == null || "".equals(kq_id)){
                                return mapping.findForward("failure");
                            }
  
                            dcVO = new KQDChieu1VO();
                            dcVO.setBk_id(doiChieuForm.getBk_id());
                            dcVO.setMt_id(doiChieuForm.getMt_id());
                            dcVO.setKet_qua(kq);
                            dcVO.setChenh_lech(clech);
                            
                            if(doiChieuDAO.updateKQ(dcVO) > 0){
                                if(doiChieuDAO.updateTTBK(dcVO) > 0){ 
                                  conn.commit();
                                }else{ 
                                  throw new Exception("updateTTBK update sp_bk_dc1_ngoai_te fail");
                                }
                            }else{
                              throw new Exception("updateKQ update sp_065_ngoai_te fail");
                            }
                            
                            if ("1".equals(kq)) { // chenh lech
                                request.setAttribute("DXN_clech", "DXN_clech");
                            } else if ("0".equals(kq)) {
                                request.setAttribute("DXN_dung", "DXN_dung");
                            }
                        }
                    }
                }
            }
            resetToken(request);
            saveToken(request);
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

    // update lai trang thai <Duyet Lai>

    
}
