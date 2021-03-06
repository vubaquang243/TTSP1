package com.seatech.ttsp.denghiqt.action;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DNQTVO;
import com.seatech.ttsp.denghiqt.DeNghiQuyetToanDAO;
import com.seatech.ttsp.denghiqt.DeNghi_QuyetToanVO;

import com.seatech.ttsp.thamsokb.ThamSoKBDAO;
import com.seatech.ttsp.thamsokb.ThamSoKBVO;
import com.seatech.ttsp.tknhkb.TKNHKBacDAO;

import java.io.PrintWriter;

import java.lang.reflect.Type;

import java.math.BigDecimal;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.Types;

import java.text.SimpleDateFormat;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/*
 * QuangVB
 * 
 * Class dùng cho việc tạo mới đề nghị quyết toán
 * */
public class DeNghiQuyetToanAction extends AppAction {
    /*
     *      
     * Quang VB
     * Vào màn hình 
     */
    public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        Connection conn = null;
        Collection dmNH = null;
        Vector params = null;
        try {
            params = new Vector();
            conn = getConnection();
            TKNHKBacDAO tkbhkbDAO = new TKNHKBacDAO(conn);
            HttpSession session = request.getSession();
            String NHKBChuyen =
                session.getAttribute(AppConstants.APP_KB_ID_SESSION) == null ?
                "" :
                session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
            String strQuery =
                "and a.kb_id = '" + NHKBChuyen + "' and a.loai_tk <>'01' and a.TRANG_THAI = '01'";
            dmNH = tkbhkbDAO.getNH_KB(strQuery, params);
            
                       
            DeNghiQuyetToanDAO deNghiQuyetToanDAO = new DeNghiQuyetToanDAO(conn);
            Collection collNgayLoi = deNghiQuyetToanDAO.getNgayLoiList();
            
            
            
            request.setAttribute("collNgayLoi", collNgayLoi);
            request.setAttribute("dmNH", dmNH);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }
    /*
     * 
     * Quang VB
     *Function dùng cho việc thêm mới 
     * 
     */
    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        Connection conn = null;
        JsonObject jsonObj = null;
        String strThamSo = "";
        StringBuffer strbf = new StringBuffer();
        int qToanID = 0;
        try {
            jsonObj = new JsonObject();
            conn = getConnection();
            HttpSession session = request.getSession();
            DeNghiQuyetToanDAO denghiDAO = new DeNghiQuyetToanDAO(conn);
            ThamSoKBDAO dao = new ThamSoKBDAO(conn);
            String id_kb =
                session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
            String NHKBNhan = request.getParameter("maNH");
            String loaiQToan = request.getParameter("loaiQT");
            if (loaiQToan.equals("04"))
                strThamSo = AppConstants.APP_LAP_DNQT_BU_CHI_NGAY_LOI_SESSION;
            else if (loaiQToan.equals("05"))
                strThamSo = AppConstants.APP_LAP_DNQT_THAU_CHI_SESSION;
            else if (loaiQToan.equals("06"))
                strThamSo = AppConstants.APP_LAP_DNQT_THU_NGAY_LOI_SESSION;
            else if (loaiQToan.equals("07"))
                strThamSo = AppConstants.APP_LAP_DNQT_LOAI_KHAC_SESSION;

            DeNghi_QuyetToanVO qtoan = intializeQT(request, session);

            qToanID = denghiDAO.insert(qtoan);
            if (qToanID > 0) {
                strbf.append(" AND kb_id= '" + id_kb + "'");
                strbf.append(" AND ma_nh= '" + NHKBNhan + "'");
                strbf.append(" AND ten_ts= '" + strThamSo + "' ");
                dao.update(" giatri_ts = 'N'", strbf.toString());
                conn.commit();
                response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                PrintWriter out = response.getWriter();
                out.flush();
                out.close();
            } else {
                throw new Exception("Không thể thêm mới ĐNQT");
            }
        } catch (Exception e) {
            conn.rollback();
            jsonObj.addProperty("error", e.getMessage());
            String strJson = jsonObj.toString();
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(strJson.toString());
            out.flush();
            out.close();
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }
    /*
     *QuangVB 
     * Lấy giá trị gửi từ form
     * và gán vào object
     * 
     */
    private DeNghi_QuyetToanVO intializeQT(HttpServletRequest request,
                                           HttpSession session) throws Exception {
        DeNghi_QuyetToanVO qtoan = new DeNghi_QuyetToanVO();
        String NHKBChuyen =
            session.getAttribute(AppConstants.APP_KB_ID_SESSION) == null ? "" :
            session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
        String NHKBNhan = request.getParameter("maNH");
        if (NHKBNhan.equals(""))
            throw new Exception("Kh\u00F4ng th\u1EC3 t\u1EA1o \u0111\u1EC1 ngh\u1ECB");
        String nguoiTao = session.getAttribute("id_nsd").toString();
        String ngayQToan = request.getParameter("ngayQT");
        String loaiQToan = request.getParameter("loaiQT");
        String QToanChi = request.getParameter("QToanChi");
        String QToanBu = request.getParameter("QToanBu");
        String noiDungQToan = request.getParameter("noiDungQToan");
        String loaiTien = request.getParameter("loaiTien");
        String ngayBuChi = request.getParameter("ngayBuChi");
        String trangThai = "01";

        qtoan.setMaKB(NHKBChuyen);
        qtoan.setNganHang(NHKBNhan);
        qtoan.setNguoiTao(nguoiTao);
        qtoan.setNgayQuyetToan(ngayQToan);
        qtoan.setLoaiQuyetToan(loaiQToan);
        qtoan.setLoaiTien(loaiTien);
        //20171204 thuong 
       // if (loaiTien.equals("VND")) {
            QToanChi = QToanChi.replace(".", "");
            QToanBu = QToanBu.replace(".", "");
//        } else {
//            QToanChi = QToanChi.replace(".", "").replace(",",".");
//            QToanBu = QToanBu.replace(".", "").replace(",",".");
//        }
        try {
            if ("".equals(QToanChi))
                QToanChi = "0";
            if ("".equals(QToanBu))
                QToanBu = "0";
            new BigDecimal(QToanChi);
            new BigDecimal(QToanBu);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        qtoan.setQuyetToanChi(QToanChi);
        qtoan.setQuyetToanThu(QToanBu);
        qtoan.setNoiDung(noiDungQToan);
        qtoan.setTrangThai_066(trangThai);
        qtoan.setNgay_loi(ngayBuChi);
        return qtoan;
    }
    /*
     * Load loại tiền
     * */
    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        JsonObject jsonObj = new JsonObject();
        String strJson = "";
        Gson gson = null;
        Vector params = null;
        try {
            conn = getConnection();
            params = new Vector();
            String ma_nh =
                request.getParameter("maNH") != null ? request.getParameter("maNH") :
                "";
            TKNHKBacDAO tkbhkbDAO = new TKNHKBacDAO(conn);
            HttpSession session = request.getSession();
            String NHKBChuyen =
                session.getAttribute(AppConstants.APP_KB_ID_SESSION) == null ?
                "" :
                session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
            String strQuery =
                "AND a.kb_id = " + NHKBChuyen + " AND b.ma_nh = " + ma_nh;
            Collection lstMaNT = tkbhkbDAO.getLT_NH_KB(strQuery, params);
            gson = new GsonBuilder().setVersion(1.0).create();
            strJson = gson.toJson(lstMaNT);
            jsonObj.addProperty("lstLoaiTien", strJson);
            JsonArray jsonArr = new JsonArray();
            JsonElement jsonEle = jsonObj.get("lstLoaiTien");
            jsonArr.add(jsonEle);
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(jsonArr.getAsJsonArray().toString());
            out.flush();
            out.close();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }
    /*
     * Load số tiền thấu chi và bù số chi
     * 
     * */
    public ActionForward ycTraCuu(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
        Connection conn = null;
        JsonObject jsonObj = new JsonObject();
        String strJson = "";
        Gson gson = null;
        Vector params = null;
        String p_type = "";
        String strThamSo = "";
        try {
            conn = getConnection();
            params = new Vector();
            HttpSession session = request.getSession();
            ThamSoKBDAO dao = new ThamSoKBDAO(conn);
            String id_kb =
                session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
            String ma_nh =
                request.getParameter("maNH") != null ? request.getParameter("maNH") :
                "";
            String loaiQToan =
                request.getParameter("loaiQT") == null ? "" : request.getParameter("loaiQT");
            if (loaiQToan.equals("04"))
                strThamSo = AppConstants.APP_LAP_DNQT_BU_CHI_NGAY_LOI_SESSION;
            else if (loaiQToan.equals("05"))
                strThamSo = AppConstants.APP_LAP_DNQT_THAU_CHI_SESSION;
            else if (loaiQToan.equals("06"))
                strThamSo = AppConstants.APP_LAP_DNQT_THU_NGAY_LOI_SESSION;
            else if (loaiQToan.equals("07"))
                strThamSo = AppConstants.APP_LAP_DNQT_LOAI_KHAC_SESSION;

            if (dao.checkThamSo(id_kb, ma_nh, strThamSo, "Y")) {
                String NHKBChuyen =
                    session.getAttribute(AppConstants.APP_NHKB_ID_SESSION) ==
                    null ? "" :
                    session.getAttribute(AppConstants.APP_NHKB_ID_SESSION).toString();
                String loai_tien =
                    request.getParameter("loaiTien") != null ? request.getParameter("loaiTien") :
                    "";
              String ngayBuChi =
                  request.getParameter("ngayBuChi") != null ? request.getParameter("ngayBuChi") :
                  "";
                String strSQL =
                    "{? = call SP_TINH_SO_QTOAN_LAP_MOI.fnc_get_qtoan_chi(?,?,?,?,?)}";
                if (loaiQToan.equals("05"))
                    p_type = "0";
                if (loaiQToan.equals("04"))
                    p_type = "1";
                CallableStatement statement = conn.prepareCall(strSQL);
                statement.registerOutParameter(1, Types.CHAR);
                statement.setString(2, p_type);
                statement.setString(3, NHKBChuyen);
                statement.setString(4, ma_nh);
                statement.setString(5, loai_tien);
                statement.setString(6, ngayBuChi);
                statement.executeUpdate();
                String result = String.valueOf(statement.getString(1));
                gson = new GsonBuilder().setVersion(1.0).create();
                strJson = gson.toJson(result);
                jsonObj.addProperty("getCurren", strJson);
                JsonArray jsonArr = new JsonArray();
                JsonElement jsonEle = jsonObj.get("getCurren");
                jsonArr.add(jsonEle);
                response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                PrintWriter out = response.getWriter();
                out.println(jsonArr.getAsJsonArray().toString());
                out.flush();
                out.close();
            } else {
                String result = "Liên hệ trung ương để được cho phép lập mới DNQT.";
                gson = new GsonBuilder().setVersion(1.0).create();
                strJson = gson.toJson(result);
                jsonObj.addProperty("getCurren", strJson);
                JsonArray jsonArr = new JsonArray();
                JsonElement jsonEle = jsonObj.get("getCurren");
                jsonArr.add(jsonEle);
                response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                PrintWriter out = response.getWriter();
                out.println(jsonArr.getAsJsonArray().toString());
                out.flush();
                out.close();
            }
        } catch (Exception e) {
          String result = "Lỗi " + e.getMessage();
          gson = new GsonBuilder().setVersion(1.0).create();
          strJson = gson.toJson(result);
          jsonObj.addProperty("getCurren", strJson);
          JsonArray jsonArr = new JsonArray();
          JsonElement jsonEle = jsonObj.get("getCurren");
          jsonArr.add(jsonEle);
          response.setContentType(AppConstants.CONTENT_TYPE_JSON);
          PrintWriter out = response.getWriter();
          out.println(jsonArr.getAsJsonArray().toString());
          out.flush();
          out.close();
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

}
