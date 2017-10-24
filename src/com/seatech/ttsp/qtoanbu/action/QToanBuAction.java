package com.seatech.ttsp.qtoanbu.action;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;

import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DChieu1VO;
import com.seatech.ttsp.dchieu.DNQTVO;
import com.seatech.ttsp.proxy.giaodien.Msg066;
import com.seatech.ttsp.proxy.pki.PKIService;
import com.seatech.ttsp.qlyphi.QuanLyPhiDAO;
import com.seatech.ttsp.qtoanbu.QToanBuDAO;
import com.seatech.ttsp.qtoanbu.QToanBuVO;
import com.seatech.ttsp.qtoanbu.form.QToanBuForm;

import com.seatech.ttsp.tsoSDuCOT.TSoKhoBacVO;
import com.seatech.ttsp.tsoSDuCOT.TSoSDuCOTDAO;

import com.seatech.ttsp.tsotabmis.tsoTabmisDAO;
import com.seatech.ttsp.tsotabmis.tsoTabmisVO;

import java.io.PrintWriter;

import java.math.BigDecimal;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class QToanBuAction extends AppAction{

    @Override
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        try{
            conn = getConnection();
            QToanBuDAO qToanBuDAO = new QToanBuDAO(conn);
            
            HttpSession session = request.getSession();
            
            String kb_id = session.getAttribute("id_kb") == null ? "" : session.getAttribute("id_kb").toString();
            String nh_kb = session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION) == null ? "": session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
            
            String condition = " AND c.MA_NH = ? ";
            Vector params = new Vector();
            params.add(new Parameter(nh_kb,true));
            Collection qToanBu = qToanBuDAO.getListQToanBu(condition, params);
            
            request.setAttribute("isOpenThamSo", isOpenThamSo(conn,kb_id));
            request.setAttribute("listQToanBu", qToanBu);
        }finally{
            close(conn);
        }
        return mapping.findForward("success");
    }
/*
 * update view label value
 */
    @Override
    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        Connection conn = null;
        try{
            conn = getConnection();
            QToanBuDAO phiDAO = new QToanBuDAO(conn);
            HttpSession session = request.getSession();
            String ngayDC = request.getParameter("ngayDC");
            String maNH = request.getParameter("maNH");
            String loaiTien = request.getParameter("loaiTien");
            String nh_kb = session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION) == null ? "": session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
           
            //Lay du lieu cho list SoLieuQtoan
            Vector params = new Vector();
            String condition = " AND e.MA_KB = ? AND e.MA_NH = ? AND a.ma_nt = ? ";
            params.add(new Parameter(nh_kb,true));
            params.add(new Parameter(maNH,true)); params.add(new Parameter(loaiTien,true));
            QToanBuVO soLieuQToan = phiDAO.getSoLieuQuyetToan(condition, params);
            soLieuQToan.setDe_nghi_thu_bu(new BigDecimal(0));
            if(soLieuQToan.getDe_nghi_chi_bu().compareTo(new BigDecimal(0)) < 0){
                soLieuQToan.setDe_nghi_thu_bu(soLieuQToan.getDe_nghi_chi_bu().abs());
                soLieuQToan.setDe_nghi_chi_bu(new BigDecimal(0));
            }
            //Lay qtoan chi
            condition = " AND ma_nt = ? ";
            params.clear();
            params.add(new Parameter("C",true)); params.add(new Parameter(ngayDC,true)); params.add(new Parameter(nh_kb,true)); 
            params.add(new Parameter(maNH,true)); params.add(new Parameter(loaiTien,true));
            String qToanChi = phiDAO.getQToanThuChi(condition, params);
            
            //Lay qtoan thu
            params.set(0, new Parameter("D",true));
            String qToanThu = phiDAO.getQToanThuChi(condition, params);
            
            soLieuQToan.setQtoan_chi(new BigDecimal(qToanChi));
            soLieuQToan.setQtoan_thu(new BigDecimal(qToanThu));
            java.lang.reflect.Type QToanBuVOType = new TypeToken<QToanBuVO>() {
          }.getType();
            String valueJson = new Gson().toJson(soLieuQToan, QToanBuVOType);
            
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(valueJson.toString());
            out.flush();
            out.close();
        }catch(Exception e){
            throw e;
        }finally {
            close(conn);
        }
        return mapping.findForward("success");
    }
/*
* insert vao qtoan bu vao bang sp066
*/
    @Override
    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        Connection conn = null;
        JsonObject jsonObj = null;
        try{
            jsonObj = new JsonObject();
            conn = getConnection();
            QToanBuDAO qToanBuDAO = new QToanBuDAO(conn);
            QToanBuForm f = (QToanBuForm)form;
            HttpSession session = request.getSession();
            
            QToanBuVO qToanBu = initializeQToanBu(request,session);
            BigDecimal soDuSauQT = new BigDecimal(f.getSoDuSauQToan());
            BigDecimal hanMucNo = new BigDecimal(f.getHanMucNo());
            if(f.getSoDeNghiQToanThuBu().equals("0")){
                if(qToanBu.getDe_nghi_chi_bu().add(soDuSauQT).compareTo(hanMucNo) > 0){
                  throw new Exception("S\u1ED1 \u0111\u1EC1 ngh\u1ECB quy\u1EBFt to\u00E1n chi b\u00F9 \u0111\u00E3 v\u01B0\u1EE3t qu\u00E1 h\u1EA1n m\u1EE9c d\u01B0 n\u1EE3");
                }
            }else if(f.getSoDeNghiQToanChiBu().equals("0")){
              if(qToanBu.getDe_nghi_thu_bu().add(hanMucNo).compareTo(soDuSauQT) > 0){
                throw new Exception("S\u1ED1 \u0111\u1EC1 ngh\u1ECB quy\u1EBFt to\u00E1n thu b\u00F9 \u0111\u00E3 v\u01B0\u1EE3t qu\u00E1 h\u1EA1n m\u1EE9c d\u01B0 n\u1EE3");
              }
            }
            
            String nhkb_chuyen = qToanBu.getMa_kb();
            String nhkb_nhan = qToanBu.getMa_nh();
            String ngayQToan = qToanBu.getNgay_qtoan();
            //Check va insert qToanBu
            String checkTranThaiQToanCondition = "AND nhkb_chuyen = ? and nhkb_nhan = ? and ngay_qtoan = to_date(?,'dd/mm/yyyy') AND TRANG_THAI_QTOAN = ?";
            Vector params = new Vector();
            params.add(new Parameter(nhkb_chuyen,true));params.add(new Parameter(nhkb_nhan,true));
            params.add(new Parameter(ngayQToan,true));params.add(new Parameter("02",true));
            if(qToanBuDAO.checkTrangThaiQToan(checkTranThaiQToanCondition,params)){
                if(!qToanBuDAO.isDuplicate(qToanBu)){
                    long qToanBuID = qToanBuDAO.insertQToanBu(qToanBu);
                    if(qToanBuID > 0){
                        if (updateLaiThamSoQToanBu(conn,session)){
                            conn.commit();
                            qToanBu.setId_066(qToanBuID);
                            java.lang.reflect.Type qToanBuType = new TypeToken<QToanBuVO>() {
                            }.getType();
                            String valueJson = new Gson().toJson(qToanBu, qToanBuType);
                            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                            PrintWriter out = response.getWriter();
                            out.println(valueJson.toString());
                            out.flush();
                            out.close();
                        }else{
                            throw new Exception("S\u1EEDa l\u1EA1i tham s\u1ED1 quy\u1EBFt to\u00E1n b\u00F9 b\u1ECB l\u1ED7i.");
                        }
                    }else{
                        throw new Exception("Th\u00EAm quy\u1EBFt to\u00E1n b\u00F9 b\u1ECB l\u1ED7i");
                    }
                }else{
                    throw new Exception("Quy\u1EBFt to\u00E1n b\u00F9 n\u00E0y \u0111\u00E3 c\u00F3 kh\u00F4ng th\u1EC3 t\u1EA1o th\u00EAm.");
                }
            }else{
                throw new Exception("Quy\u1EBFt to\u00E1n n\u00E0y ch\u01B0a ph\u1EA3i quy\u1EBFt to\u00E1n \u0111\u1EE7.\n Vui l\u00F2ng g\u1ECDi \u0111i\u1EC7n l\u00EAn trung \u01B0\u01A1ng \u0111\u1EC3 \u0111\u01B0\u1EE3c tr\u1EE3 gi\u00FAp");
            }
        }catch(Exception e){
            conn.rollback();
            jsonObj.addProperty("error", e.getMessage());
            String strJson = jsonObj.toString();
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(strJson.toString());
            out.flush();
            out.close();
        }finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

    private QToanBuVO initializeQToanBu(HttpServletRequest request, HttpSession session) throws Exception {
        QToanBuVO qToanBu = new QToanBuVO();
        String NHKBChuyen = session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION) == null ? "": session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
        String NHKBNhan = request.getParameter("maNH");
        if("".equals(NHKBNhan)) 
            throw new Exception("Kh\u00F4ng th\u1EC3 t\u1EA1o \u0111\u1EC1 ngh\u1ECB");
        String nguoiTao = session.getAttribute("id_nsd").toString();
        String ngayQToan = request.getParameter("ngayDC");
        String loaiQToan = "03";
        String soDeNghiQToanChiBu = request.getParameter("soDeNghiQToanChiBu");
        String soDeNghiQToanThuBu = request.getParameter("soDeNghiQToanThuBu");
        String noiDungQToan = "De nghi quyet toan bu ngay "+ngayQToan;
        String loaiTien = request.getParameter("loaiTien");
        String trangThai = "01";
        
        qToanBu.setMa_kb(NHKBChuyen);
        qToanBu.setMa_nh(NHKBNhan);
        qToanBu.setNguoi_tao(nguoiTao); 
        qToanBu.setNgay_qtoan(ngayQToan); 
        qToanBu.setLoai_qtoan(loaiQToan);
        if(loaiTien.equals("VND")){
            soDeNghiQToanChiBu = soDeNghiQToanChiBu.replace(".", "").replace(",", ".");
            soDeNghiQToanThuBu = soDeNghiQToanThuBu.replace(".", "").replace(",", ".");
        }else{
            soDeNghiQToanChiBu = soDeNghiQToanChiBu.replace(",", "");
            soDeNghiQToanThuBu = soDeNghiQToanThuBu.replace(",", "");
        }
        qToanBu.setDe_nghi_chi_bu(new BigDecimal(soDeNghiQToanChiBu)); 
        qToanBu.setDe_nghi_thu_bu(new BigDecimal(soDeNghiQToanThuBu)); 
        qToanBu.setNoi_dung(noiDungQToan); 
        qToanBu.setLoai_tien(loaiTien); 
        qToanBu.setTrang_thai_066(trangThai); 
        
        return qToanBu;
    }
/*
 * Lay de nghi danh sach quyet toan bu
 * */
    @Override
    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        JsonObject jsonObj = null;
        try{
            jsonObj = new JsonObject();
            conn = getConnection();
            QToanBuDAO phiDAO = new QToanBuDAO(conn);
            HttpSession session = request.getSession();
            
            String ngayDC = request.getParameter("ngayDC");
            String maNH = request.getParameter("maNH");
            String loaiTien = request.getParameter("loaiTien");
            String nh_kb = session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION) == null ? "": session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
            String role =  session.getAttribute("role_list").toString();
            
            Vector params = new Vector();
            params.add(new Parameter(nh_kb,true));params.add(new Parameter(maNH,true));
            params.add(new Parameter(loaiTien,true));params.add(new Parameter(ngayDC,true));
            
            List<QToanBuVO> danhSachDeNghiQToanBu = (List<QToanBuVO>)phiDAO.getDanhSachDeNghiQToanBu(null,params);
            if(danhSachDeNghiQToanBu != null && !danhSachDeNghiQToanBu.isEmpty()){
                danhSachDeNghiQToanBu.get(0).setRole_nsd(role);
            }
            
            java.lang.reflect.Type danhSachDeNghiQToanBuType = new TypeToken<ArrayList>() {
                    }.getType();
            String valueJson = new Gson().toJson(danhSachDeNghiQToanBu, danhSachDeNghiQToanBuType);
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(valueJson.toString());
            out.flush();
            out.close();
        }catch(Exception e){
            jsonObj.addProperty("error", e.getMessage());
            String strJson = jsonObj.toString();
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(strJson.toString());
            out.flush();
            out.close();
        }finally {
            close(conn);
        }
        return mapping.findForward("success");
    }
/*
 * Duyet hoac huy
 */
    @Override
    public ActionForward updateExc(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        Connection conn = null;
        JsonObject jsonObj = null;
        try{
            jsonObj = new JsonObject();
            conn = getConnection();
            QToanBuDAO phiDAO = new QToanBuDAO(conn);
            HttpSession session = request.getSession();
            
            String userName = session.getAttribute(AppConstants.APP_DOMAIN_SESSION).toString() + "\\" +
                              session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString();
            String strWSDL = getThamSoHThong("WSDL_PKI", session);
            String strAppID = getThamSoHThong("APP_ID", session);
            PKIService pkiService = new PKIService(strWSDL);
            String[] resultPKI = pkiService.VerifySignature(userName,
                                         request.getParameter("certSerial"),
                                         request.getParameter("noiDungKy"),
                                         request.getParameter("signature"), strAppID);
            
            if (resultPKI != null && (resultPKI[0].equals(AppConstants.INVALID) || resultPKI[0].equals(AppConstants.ERROR))) {
                if (resultPKI[0].equalsIgnoreCase(AppConstants.INVALID))
                    throw new Exception("TTSP-ERROR-0002");
                else
                    throw new Exception("TTSP-ERROR-0003");
            }
            
            String actionType = "0"+request.getParameter("type");
            String soDien = request.getParameter("id");
            
            //SEND MSG-MANHNV-20150511******************************************
            String strMsgID = "";
            if(actionType.equals("02")){//02 = duyet
              Msg066 msg066 = new Msg066(conn);
              
              //Lay du lieu 066
              DChieu1DAO dc1DAO = new DChieu1DAO(conn);
              String strWhere = " and a.id = ?";
              Vector forSend066Param = new Vector();
              forSend066Param.add(new Parameter(soDien, true));
              DNQTVO data066 = dc1DAO.getData066SendBank(strWhere, forSend066Param);
              if(data066 == null){
                throw new Exception("Khong tim duoc du lieu dien 066 theo ID="+soDien);
              }
              if(data066.getLoai_tien() == null || "".equals(data066.getLoai_tien()) || "VND".equals(data066.getLoai_tien())){ 
                strMsgID = msg066.sendMessage(soDien, session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString());
              }else{
                strMsgID = msg066.sendMessageNgoaiTe(soDien, session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString());
              }
              
              if(strMsgID == null || "".equals(strMsgID)){
                throw new Exception("Gui msg khong thanh cong!");
              }
            }
            //******************************************************************
            Vector params = new Vector();
            params.add(new Parameter(actionType,true));
            params.add(new Parameter(session.getAttribute("id_nsd").toString(),true));
            params.add(new Parameter(request.getParameter("signature"),true));
            params.add(new Parameter(strMsgID,true));
            params.add(new Parameter(soDien,true));
            
            if(phiDAO.updateStatusQToanBu(params)){
                params.clear();
                params.add(new Parameter(soDien,true));
                QToanBuVO qToanBu = phiDAO.getQToanBuByID(params);
                
                java.lang.reflect.Type qToanBuType = new TypeToken<QToanBuVO>() {
                        }.getType();
                String valueJson = new Gson().toJson(qToanBu, qToanBuType);
                response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                PrintWriter out = response.getWriter();
                out.println(valueJson.toString());
                out.flush();
                out.close();
                conn.commit();
            }
        }catch(Exception e){
            conn.rollback();
            jsonObj.addProperty("error", e.getMessage());
            String strJson = jsonObj.toString();
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(strJson.toString());
            out.flush();
            out.close();
        }finally {
            close(conn);
        }
        return mapping.findForward("success");
    }
	   @Override
    public ActionForward search(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;

        HttpSession session = request.getSession();
        List cacKhoBacTinh = null;
        try {
            conn = getConnection();
            //Load DM KB tinh
            String kbCode =
                session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();

            DChieu1DAO dao = new DChieu1DAO(conn);
            DChieu1VO vo = new DChieu1VO();
            vo = dao.getCap(" and ma = " + kbCode, null);
            String cap = vo.getCap();

            if ("0001".equals(kbCode) || "0002".equals(kbCode)) {
                cacKhoBacTinh = (List)dao.getDMucKB_cha("", null);
                request.setAttribute("dftinh", "dftinh");
                request.setAttribute("dchieu3", "dchieu3");
            } else if ("0003".equals(kbCode)) {
                String whereClause = " AND a.ma = '0003' ";
                cacKhoBacTinh = (List)dao.getDMucKB_cha(whereClause, null);
            } else if ("5".equals(cap)) {
                String whereClause = " AND a.ma = " + kbCode;
                cacKhoBacTinh = (List)dao.getDMucKB_cha(whereClause, null);
            } else {
                String whereClause =
                    " and a.id_cha in (select id_cha from sp_dm_htkb where ma = " +
                    kbCode + ")";
                cacKhoBacTinh = (List)dao.getDMucKB_cha(whereClause, null);
            }
            request.setAttribute("dmuckb_tinh", cacKhoBacTinh);
            //-------
            
            QToanBuDAO qToanBuDAO = new QToanBuDAO(conn);
            QToanBuForm f = (QToanBuForm)form;
            String strKBHuyen = f.getNhkb_huyen();
            String strKBTinh = f.getNhkb_tinh();
            String strWhere = "";
            Vector vParam = new Vector();
            if(strKBHuyen != null && !"".equals(strKBHuyen)){
              strWhere = " AND b.id = ?";
              vParam.add(new Parameter(strKBHuyen, true));
            }else if(strKBTinh != null && !"".equals(strKBTinh)){
              strWhere = " AND b.id_cha = ?";
              vParam.add(new Parameter(strKBTinh, true));
            }
            
            Collection qToanBu = qToanBuDAO.getListQToanBu(strWhere, vParam);
            request.setAttribute("listQToanBu", qToanBu);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }
    private boolean isOpenThamSo(Connection conn,String kb_id) throws Exception {
        String thamSo = "CHO_PHEP_QUYET_TOAN_BU"; 
        TSoSDuCOTDAO thamSoKhoBacDAO = new TSoSDuCOTDAO(conn);
        
        String condition = " AND TEN_TS = ? AND GIATRI_TS = 'Y' AND KB_ID = ? ";
        Vector params = new Vector();
        params.add(new Parameter(thamSo,true));
        params.add(new Parameter(kb_id,true));
        
        List tSoKhoBac = (List)thamSoKhoBacDAO.getTSoKhoBac(condition,params);
        return !tSoKhoBac.isEmpty();
     }

    private boolean updateLaiThamSoQToanBu(Connection conn, HttpSession session) throws Exception {
        String kb_id = session.getAttribute("id_kb") == null ? "" : session.getAttribute("id_kb").toString();
        tsoTabmisVO tSoVO = new tsoTabmisVO();
        tSoVO.setGiatri_ts("N");
        tSoVO.setId_kb_huyen(kb_id);
        tSoVO.setTen_ts("CHO_PHEP_QUYET_TOAN_BU");
        tsoTabmisDAO tSoDAO = new tsoTabmisDAO(conn);
        return tSoDAO.update(tSoVO) > 0;
    }

}
