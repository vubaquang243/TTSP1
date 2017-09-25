package com.seatech.ttsp.denghiqt.action;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.denghiqt.DeNghiQuyetToanDAO;
import com.seatech.ttsp.denghiqt.DeNghi_QuyetToanVO;

import com.seatech.ttsp.tknhkb.TKNHKBacDAO;

import java.io.PrintWriter;

import java.lang.reflect.Type;

import java.math.BigDecimal;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.util.Collection;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/*
 * Class dùng cho việc tạo mới đề nghị quyết toán
 * */
public class DeNghiQuyetToanAction extends AppAction {

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
            String strQuery = "and a.kb_id = " + NHKBChuyen;
            dmNH = tkbhkbDAO.getNH_KB(strQuery, params);
            request.setAttribute("dmNH", dmNH);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        Connection conn = null;
        JsonObject jsonObj = null;
        long qToanID = 0;
        try {
            jsonObj = new JsonObject();
            conn = getConnection();
            HttpSession session = request.getSession();
            DeNghiQuyetToanDAO denghiDAO = new DeNghiQuyetToanDAO(conn);

            DeNghi_QuyetToanVO qtoan = intializeQT(request, session);

            qToanID = denghiDAO.insert(qtoan);
            if (qToanID > 0) {
                // saveVisitLog(conn, session,AppConstants.LTT_DI_CHUC_NANG_DUYET,"Day lai");
                conn.commit();
                response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                PrintWriter out = response.getWriter();
                out.flush();
                out.close();
            } else {
                throw new Exception("S\u1EEDa l\u1EA1i tham s\u1ED1 quy\u1EBFt to\u00E1n b\u00F9 b\u1ECB l\u1ED7i.");
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
        String trangThai = "01";

        qtoan.setMaKB(NHKBChuyen);
        qtoan.setNganHang(NHKBNhan);
        qtoan.setNguoiTao(nguoiTao);
        qtoan.setNgayQuyetToan(ngayQToan);
        qtoan.setLoaiQuyetToan(loaiQToan);
        qtoan.setLoaiTien(loaiTien);
        if (loaiTien.equals("VND")) {
            QToanChi = QToanChi.replace(".", "");
            QToanBu = QToanBu.replace(".", "");
        } else {
            QToanChi = QToanChi.replace(",", "");
            QToanBu = QToanBu.replace(",", "");
        }
        try {
            new BigDecimal(QToanChi);
            new BigDecimal(QToanBu);
        } catch (Exception e) {
          throw new Exception(e.getMessage());
        }
        qtoan.setQuyetToanChi(QToanChi);
        qtoan.setQuyetToanThu(QToanBu);
        qtoan.setNoiDung(noiDungQToan);
        qtoan.setTrangThai_066(trangThai);

        return qtoan;
    }

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

    public ActionForward ycTraCuu(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
        Connection conn = null;
        JsonObject jsonObj = new JsonObject();
        String strJson = "";
        Gson gson = null;
        Vector params = null;
        String p_type = "";
        try {
            conn = getConnection();
            params = new Vector();
            HttpSession session = request.getSession();
            String ma_nh =
                request.getParameter("maNH") != null ? request.getParameter("maNH") :
                "";
            String NHKBChuyen =
                session.getAttribute(AppConstants.APP_KB_ID_SESSION) == null ?
                "" :
                session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
            String type =
                request.getParameter("loaiQT") != null ? request.getParameter("loaiQT") :
                "";
            String strSQL =
                "{? = call SP_TINH_SO_QTOAN_LAP_MOI.fnc_get_qtoan_chi(?,?,?)}";
            if (type.equals("05"))
                p_type = "0";
            if (type.equals("04"))
                p_type = "1";
            CallableStatement statement = conn.prepareCall(strSQL);
            statement.registerOutParameter(1, java.sql.Types.INTEGER);
            statement.setString(2, p_type);
            statement.setString(3, ma_nh);
            statement.setString(4, NHKBChuyen);
            statement.executeUpdate();
            int result = statement.getInt(1);
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

}
