package com.seatech.ttsp.lenhqtthucong.action;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import com.seatech.framework.AppConstants;
import com.seatech.framework.strustx.AppAction;

import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.lenhqtthucong.LenhQTTCDAO;
import com.seatech.ttsp.lenhqtthucong.LenhQTTCVO;

import com.seatech.ttsp.lenhqtthucong.form.LenhQTTCForm;
import com.seatech.ttsp.tknhkb.TKNHKBacDAO;

import java.io.PrintWriter;

import java.sql.Connection;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/*
 *
 * Lenh quyet toan thu cong
 *
 */
public class LenhQTTCAction extends AppAction {

    /*
     * Vao man hinh chinh
     */

    public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        Connection conn = null;
        Vector params = null;
        try {
            conn = getConnection();
            params = new Vector();
            HttpSession session = request.getSession();
            TKNHKBacDAO tkbhkbDAO = new TKNHKBacDAO(conn);

            String nhkbNhan_id =
                session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION) ==
                null ? "" :
                session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
            String nhkbNhan_name =
                session.getAttribute(AppConstants.APP_NHKB_NAME_SESSION) ==
                null ? "" :
                session.getAttribute(AppConstants.APP_NHKB_NAME_SESSION).toString();
            String NHKBChuyen =
                session.getAttribute(AppConstants.APP_KB_ID_SESSION) == null ?
                "" :
                session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();

            String strQuery = "and a.kb_id = " + NHKBChuyen;
            Collection dmNH = tkbhkbDAO.getNH_KB(strQuery, params);
            request.setAttribute("dmNH", dmNH);
            request.setAttribute("nhkbNhan_id", nhkbNhan_id);
            request.setAttribute("nhkbNhan_name", nhkbNhan_name);

        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(AppConstants.SUCCESS);
    }

    /*
      * Dung cho viec them moi
       */

    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        Connection conn = null;
        Vector params = null;
        Vector params1 = null;
        Vector params2 = null;
        try {
            conn = getConnection();
            params = new Vector();
            params1 = new Vector();
            params2 = new Vector();
            LenhQTTCDAO dao = new LenhQTTCDAO(conn);
            HttpSession session = request.getSession();
            LenhQTTCForm f = (LenhQTTCForm)form;
            LenhQTTCVO lenhQT = init(request, dao, session, params);
            if (lenhQT != null) {
                int result = dao.insert(lenhQT, params);
                if (result > 0) {
                    conn.commit();
                    TTSPUtils ttspUtils = new TTSPUtils(conn);
                    String idQToan =
                        ttspUtils.getMT_ID(lenhQT.getLoaiQuyetToan(), null);
                    lenhQT.setSoLenhQuyetToan(idQToan);
                    lenhQT.setQtDonVi("Y");
                    dao.insert(lenhQT, params1);
                    conn.commit();
                    request.setAttribute("msg", "Thêm mới thành công");
                } else {
                    request.setAttribute("msg",
                                         "Thêm mới không thành công ! Vui lòng kiểm tra lại dữ liệu");
                }
            } else {
                request.setAttribute("msg",
                                     "Thêm mới không thành công ! Vui lòng kiểm tra lại dữ liệu");
            }
            TKNHKBacDAO tkbhkbDAO = new TKNHKBacDAO(conn);
            String nhkbNhan_id =
                session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION) ==
                null ? "" :
                session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
            String nhkbNhan_name =
                session.getAttribute(AppConstants.APP_NHKB_NAME_SESSION) ==
                null ? "" :
                session.getAttribute(AppConstants.APP_NHKB_NAME_SESSION).toString();
            String NHKBChuyen =
                session.getAttribute(AppConstants.APP_KB_ID_SESSION) == null ?
                "" :
                session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();

            String strQuery = "and a.kb_id = " + NHKBChuyen;
            Collection dmNH = tkbhkbDAO.getNH_KB(strQuery, params2);
            request.setAttribute("dmNH", dmNH);
            request.setAttribute("nhkbNhan_id", nhkbNhan_id);
            request.setAttribute("nhkbNhan_name", nhkbNhan_name);
        } catch (Exception e) {
            conn.rollback();
            HttpSession session = request.getSession();
            TKNHKBacDAO tkbhkbDAO = new TKNHKBacDAO(conn);
            String NHKBChuyen =
                session.getAttribute(AppConstants.APP_KB_ID_SESSION) == null ?
                "" :
                session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
            String nhkbNhan_id =
                session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION) ==
                null ? "" :
                session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
            String nhkbNhan_name =
                session.getAttribute(AppConstants.APP_NHKB_NAME_SESSION) ==
                null ? "" :
                session.getAttribute(AppConstants.APP_NHKB_NAME_SESSION).toString();
            String strQuery = "and a.kb_id = " + NHKBChuyen;
            Collection dmNH = tkbhkbDAO.getNH_KB(strQuery, params2);
            request.setAttribute("dmNH", dmNH);
            request.setAttribute("nhkbNhan_id", nhkbNhan_id);
            request.setAttribute("nhkbNhan_name", nhkbNhan_name);
            throw new Exception(e.getMessage());
            //            request.setAttribute("msg", "Thêm mới không thành công");
        } finally {
            close(conn);
        }
        return mapping.findForward(AppConstants.SUCCESS);
    }

    //tra cuu ten chi nhanh ngan hang

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
            LenhQTTCDAO dao = new LenhQTTCDAO(conn);
            String id = request.getParameter("id").toString();
            Collection result = dao.getNameChiNhanhNH(id, params);
            gson = new GsonBuilder().setVersion(1.0).create();
            strJson = gson.toJson(result);
            jsonObj.addProperty("result", strJson);
            JsonArray jsonArr = new JsonArray();
            JsonElement jsonEle = jsonObj.get("result");
            jsonArr.add(jsonEle);
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(jsonArr.getAsJsonArray().toString());
            out.flush();
            out.close();
        } catch (Exception e) {
            String result = "fail";
            gson = new GsonBuilder().setVersion(1.0).create();
            strJson = gson.toJson(result);
            jsonObj.addProperty("result", strJson);
            JsonArray jsonArr = new JsonArray();
            JsonElement jsonEle = jsonObj.get("result");
            jsonArr.add(jsonEle);
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(jsonArr.getAsJsonArray().toString());
            out.flush();
            out.close();
        } finally {
            close(conn);
        }
        return mapping.findForward(AppConstants.SUCCESS);
    }

    /*tra cua nguoi kiem soat*/

    public ActionForward ycTraCuu(ActionMapping mapping, ActionForm form,
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
            LenhQTTCDAO dao = new LenhQTTCDAO(conn);
            String loaiTien = request.getParameter("loaiTien").toString();
            String maNH_chuyen =
                request.getParameter("maNHKBChuyen").toString();
            String maNH = maNH_chuyen.substring(2, 5);
            String strWhere =
                " AND b.ma_nh LIKE '__" + maNH + "%' AND a.ma_nt = '" +
                loaiTien + "'";
            Collection result = dao.getNguoiKiemSoat(strWhere, params);
            gson = new GsonBuilder().setVersion(1.0).create();
            strJson = gson.toJson(result);
            jsonObj.addProperty("result", strJson);
            JsonArray jsonArr = new JsonArray();
            JsonElement jsonEle = jsonObj.get("result");
            jsonArr.add(jsonEle);
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(jsonArr.getAsJsonArray().toString());
            out.flush();
            out.close();
        } catch (Exception e) {
            String result = "fail";
            gson = new GsonBuilder().setVersion(1.0).create();
            strJson = gson.toJson(result);
            jsonObj.addProperty("result", strJson);
            JsonArray jsonArr = new JsonArray();
            JsonElement jsonEle = jsonObj.get("result");
            jsonArr.add(jsonEle);
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(jsonArr.getAsJsonArray().toString());
            out.flush();
            out.close();
        } finally {
            close(conn);
        }
        return mapping.findForward(AppConstants.SUCCESS);
    }

    /* Ham dinh nghia gia tri dung cho insert data */

    public LenhQTTCVO init(HttpServletRequest request, LenhQTTCDAO dao,
                           HttpSession session,
                           Vector params) throws Exception {
        LenhQTTCVO lenhQT = new LenhQTTCVO();
        String maKB =
            session.getAttribute(AppConstants.APP_KB_ID_SESSION) == null ? "" :
            session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
        String maNHKBChuyen = request.getParameter("maNHKBChuyen").toString();
        String maNHKBNhan = request.getParameter("maNHKBNhan").toString();
        String ngayHachToan = request.getParameter("ngayHachToan").toString();
        String ngayQuyetToan =
            request.getParameter("ngayQuyetToan").toString();
        String loaiQuyetToan =
            request.getParameter("loaiQuyetToan").toString();
        String soThamChieuLienQuan =
            request.getParameter("soThamChieuLienQuan").toString();
        String soLenhQuyetToan =
            request.getParameter("soLenhQuyetToan").toString();
        String soThamChieuGiaoDich =
            request.getParameter("soThamChieuGiaoDich").toString();
        String soTien = request.getParameter("soTien").toString();
        String loaiTien = request.getParameter("loaiTien").toString();
        String noiDungThanhToan =
            request.getParameter("noiDungThanhToan").toString();
        String nguoiNhap = request.getParameter("nguoiNhap").toString();
        String ngayNhap = request.getParameter("ngayNhap").toString();
        String nguoiKiemSoat =
            request.getParameter("nguoiKiemSoat").toString();
        String ngayKiemSoat = request.getParameter("ngayKiemSoat").toString();
        String tenTaiKhoanPhatLenh =
            request.getParameter("tenTaiKhoanPhatLenh").toString();
        String tenTaiKhoanNhanLenh =
            request.getParameter("tenTaiKhoanNhanLenh").toString();
        String taiKhoanPhatLenh =
            request.getParameter("taiKhoanPhatLenh").toString();
        String taiKhoanNhanLenh =
            request.getParameter("taiKhoanNhanLenh").toString();
        String maNHPhatLenh = request.getParameter("maNHPhatLenh").toString();
        String maNHNhanLenh = request.getParameter("maNHNhanLenh").toString();
        String phuongAnHachToan =
            request.getParameter("phuongAnHachToan").toString();
        String tenNHPhatLenh =
            request.getParameter("tenNHPhatLenh").toString();

        String tenNHNhanLenh =
            request.getParameter("tenNHNhanLenh").toString();
        String ttvChuyenKS =
            session.getAttribute(AppConstants.APP_USER_ID_SESSION) != null ?
            session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString() :
            "";
        try {
            if (loaiTien.equals("VND")) {
                soTien = soTien.replace(".", "");
            } else {
                soTien = soTien.replace(",", "");
            }

            String strWhere =
                " AND d.ma_nh = " + maNHKBNhan + " AND a.ma_nt = '" +
                loaiTien + "' AND c.ma_nh = " + maNHPhatLenh + "";
            Collection listLoaiTK = dao.getLoaiTK(strWhere, params);
            Iterator<LenhQTTCVO> ite = listLoaiTK.iterator();
            String loaitk = "";
            while (ite.hasNext()) {
                loaitk = ite.next().getLoaitk();
            }
            lenhQT.setMaNHKBChuyen(maNHKBChuyen);
            lenhQT.setMaNHKBNhan(maNHKBNhan);
            lenhQT.setNgayHachToan(ngayHachToan);
            lenhQT.setNgayQuyetToan(ngayQuyetToan);
            if (loaiQuyetToan.equals("900"))
                lenhQT.setLoaiQuyetToan("D");
            if (loaiQuyetToan.equals("910"))
                lenhQT.setLoaiQuyetToan("C");
            lenhQT.setSoThamChieuLienQuan(soThamChieuLienQuan);
            lenhQT.setSoLenhQuyetToan(soLenhQuyetToan);
            lenhQT.setSoThamChieuGiaoDich(soThamChieuGiaoDich);
            lenhQT.setSoTien(soTien);
            lenhQT.setLoaiTien(loaiTien);
            lenhQT.setNoiDungThanhToan(noiDungThanhToan);
            lenhQT.setNguoiNhap(nguoiNhap);
            lenhQT.setNgayNhap(ngayNhap);
            lenhQT.setNguoiKiemSoat(nguoiKiemSoat);
            lenhQT.setNgayKiemSoat(ngayKiemSoat);
            lenhQT.setTenTaiKhoanPhatLenh(tenTaiKhoanPhatLenh);
            lenhQT.setTenTaiKhoanNhanLenh(tenTaiKhoanNhanLenh);
            lenhQT.setTaiKhoanPhatLenh(taiKhoanPhatLenh);
            lenhQT.setTaiKhoanNhanLenh(taiKhoanNhanLenh);
            lenhQT.setMaNHPhatLenh(maNHPhatLenh);
            lenhQT.setMaNHNhanLenh(maNHNhanLenh);
            lenhQT.setPhuongAnHachToan(phuongAnHachToan);
            lenhQT.setLoaiTK(loaitk);
            lenhQT.setNhapThuCong("N");
            lenhQT.setTrangThai("01");
            lenhQT.setLaiPhi("03");
            lenhQT.setTrangThaiDC("00");
            lenhQT.setMaKB(maKB);
            lenhQT.setIdREF(soLenhQuyetToan);
            lenhQT.setTenNHChuyen(tenNHPhatLenh);
            lenhQT.setTenNHNhanLenh(tenNHNhanLenh);
            lenhQT.setTenKHChuyen(tenNHPhatLenh);
            lenhQT.setTenKHNhan(tenNHNhanLenh);
            lenhQT.setQtDonVi("N");
            lenhQT.setTTVChuyenKS(ttvChuyenKS);

        } catch (Exception e) {
            throw e;
        }
        return lenhQT;
    }

    // Get Loai Tien

    public ActionForward search(ActionMapping mapping, ActionForm form,
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
                " AND a.kb_id = " + NHKBChuyen + " AND b.ma_nh = " + ma_nh;
            Collection lstLT = tkbhkbDAO.getLT_NH_KB(strQuery, params);
            gson = new GsonBuilder().setVersion(1.0).create();
            strJson = gson.toJson(lstLT);
            jsonObj.addProperty("result", strJson);
            JsonArray jsonArr = new JsonArray();
            JsonElement jsonEle = jsonObj.get("result");
            jsonArr.add(jsonEle);
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(jsonArr.getAsJsonArray().toString());
            out.flush();
            out.close();
        } catch (Exception e) {
            String jsonResult = "fail";
            gson = new GsonBuilder().setVersion(1.0).create();
            strJson = gson.toJson(jsonResult);
            jsonObj.addProperty("result", strJson);
            JsonArray jsonArr = new JsonArray();
            JsonElement jsonEle = jsonObj.get("result");
            jsonArr.add(jsonEle);
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(jsonArr.getAsJsonArray().toString());
            out.flush();
            out.close();
        } finally {
            close(conn);
        }
        return mapping.findForward(AppConstants.SUCCESS);
    }
}
