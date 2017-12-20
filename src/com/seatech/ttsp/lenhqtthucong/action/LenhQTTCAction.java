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
import com.seatech.ttsp.thamsokb.ThamSoKBDAO;
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

            String strQuery = "and a.kb_id = " + NHKBChuyen +" and a.TRANG_THAI = '01' ";
            Collection dmNH = tkbhkbDAO.getNH_KB(strQuery, params);
            request.setAttribute("dmNH", dmNH);
            request.setAttribute("nhkbNhan_id", "01701035");
            request.setAttribute("nhkbNhan_name", "Cục KTNN - KBNN ");

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
                if (!dao.checkLQT(lenhQT.getSoLenhQuyetToan())) {
                    int result = dao.insert(lenhQT, params);
                    if (result > 0) {
                        String id_kb =
                            session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
                        String strThamSo = "CHO_PHEP_NHAP_LQT_TU_CTU_GIAY";
                        String maNH = lenhQT.getMaNHKBChuyen();
                        StringBuffer strbf = new StringBuffer();
                        strbf.append(" AND kb_id= '" + id_kb + "'");
                        strbf.append(" AND ma_nh= '" + maNH + "'");
                        strbf.append(" AND ten_ts= '" + strThamSo + "' ");
                        ThamSoKBDAO thamSoKBDAO = new ThamSoKBDAO(conn);
                        thamSoKBDAO.update(" giatri_ts = 'N'",
                                           strbf.toString());
                        conn.commit();
                        request.setAttribute("msg", "Thêm mới thành công");
                    } else {
                        request.setAttribute("msg",
                                             "Thêm mới không thành công ! Vui lòng kiểm tra lại dữ liệu");
                    }
                } else {
                    request.setAttribute("msg", "Đã tồn tại lệnh quyết toán.");
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

            String strQuery = "and a.kb_id = " + NHKBChuyen+" and a.TRANG_THAI = '01' ";
            Collection dmNH = tkbhkbDAO.getNH_KB(strQuery, params2);
            request.setAttribute("dmNH", dmNH);
            request.setAttribute("nhkbNhan_id", "01701035");
            request.setAttribute("nhkbNhan_name", "Cục KTNN - KBNN ");
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
            String strQuery = "and a.kb_id = " + NHKBChuyen+" and a.TRANG_THAI = '01' ";
            Collection dmNH = tkbhkbDAO.getNH_KB(strQuery, params2);
            request.setAttribute("dmNH", dmNH);
            request.setAttribute("nhkbNhan_id", "01701035");
            request.setAttribute("nhkbNhan_name", "Cục KTNN - KBNN ");
            throw e; //new Exception(e.getMessage());
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
            HttpSession session = request.getSession();
            String ma_nh = request.getParameter("id").toString();
            String ma_nh_kb_chuyen = request.getParameter("ma_nh_kb_chuyen").toString();
            String action = request.getParameter("action_type").toString();
            String kb_id =
                session.getAttribute(AppConstants.APP_KB_CODE_SESSION) ==
                null ? "" :
                session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
            String id_kb =
                session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
            String strWhere =
                "AND c.ma='" + kb_id + "' and b.ma_nh='" + ma_nh + "' ";
            String strThamSo = "CHO_PHEP_NHAP_LQT_TU_CTU_GIAY";
            ThamSoKBDAO thamSoKBDAO = new ThamSoKBDAO(conn);
            if(action.equals("update") && ma_nh.equals(ma_nh_kb_chuyen)){
              Collection result = dao.getNameChiNhanhNH(strWhere, params);
              if (result.size() > 0) {
                  gson = new GsonBuilder().setVersion(1.0).create();
                  strJson = gson.toJson(result);
                  jsonObj.addProperty("strJson", strJson);
                  JsonArray jsonArr = new JsonArray();
                  JsonElement jsonEle = jsonObj.get("strJson");
                  jsonArr.add(jsonEle);
                  response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                  PrintWriter out = response.getWriter();
                  out.println(jsonArr.getAsJsonArray().toString());
                  out.flush();
                  out.close();
              } else {
                  gson = new GsonBuilder().setVersion(1.0).create();
                  strJson = gson.toJson(result.size());
                  jsonObj.addProperty("strJson", strJson);
                  JsonArray jsonArr = new JsonArray();
                  JsonElement jsonEle = jsonObj.get("strJson");
                  jsonArr.add(jsonEle);
                  response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                  PrintWriter out = response.getWriter();
                  out.println(jsonArr.getAsJsonArray().toString());
                  out.flush();
                  out.close();
              }
            }else{
              if (thamSoKBDAO.checkThamSo(id_kb, ma_nh, strThamSo, "Y")) {
                  Collection result = dao.getNameChiNhanhNH(strWhere, params);
                  if (result.size() > 0) {
                      gson = new GsonBuilder().setVersion(1.0).create();
                      strJson = gson.toJson(result);
                      jsonObj.addProperty("strJson", strJson);
                      JsonArray jsonArr = new JsonArray();
                      JsonElement jsonEle = jsonObj.get("strJson");
                      jsonArr.add(jsonEle);
                      response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                      PrintWriter out = response.getWriter();
                      out.println(jsonArr.getAsJsonArray().toString());
                      out.flush();
                      out.close();
                  } else {
                      gson = new GsonBuilder().setVersion(1.0).create();
                      strJson = gson.toJson(result.size());
                      jsonObj.addProperty("strJson", strJson);
                      JsonArray jsonArr = new JsonArray();
                      JsonElement jsonEle = jsonObj.get("strJson");
                      jsonArr.add(jsonEle);
                      response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                      PrintWriter out = response.getWriter();
                      out.println(jsonArr.getAsJsonArray().toString());
                      out.flush();
                      out.close();
                  }
              } else {
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
              }
            }
        } catch (Exception e) {
            throw e;
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
            session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION) == null ?
            "" :
            session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
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
        String phuongAnHachToan = "T";
        // request.getParameter("phuongAnHachToan").toString();
        String tenNHPhatLenh =
            request.getParameter("tenNHPhatLenh").toString();

        String tenNHNhanLenh =
            request.getParameter("tenNHNhanLenh").toString();
        String ttvChuyenKS =
            session.getAttribute(AppConstants.APP_USER_ID_SESSION) != null ?
            session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString() :
            "";
        String loaitk = request.getParameter("loaiTaiKhoan").toString();
        // String lyDoHachToan = "";
        //  if (phuongAnHachToan.equals("P")) {
        //    lyDoHachToan = request.getParameter("ly_do_hach_toan");
        // }
        try {
            if (loaiTien.equals("VND")) {
                soTien = soTien.replace(".", "");
            } else {
                soTien = soTien.replace(".", "").replace(",", ".");
            }

            String strWhere =
                " AND d.ma_nh = " + maNHKBNhan + " AND a.ma_nt = '" +
                loaiTien + "' AND c.ma_nh = " + maNHKBChuyen + "";

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
            lenhQT.setNhapThuCong("Y");
            lenhQT.setTrangThai("01");
            lenhQT.setLaiPhi("03");
            lenhQT.setTrangThaiDC("00");
            lenhQT.setMaKB(maKB);
            lenhQT.setIdREF(soLenhQuyetToan);
            lenhQT.setTenNHChuyen(tenNHPhatLenh);
            lenhQT.setTenNHNhanLenh(tenNHNhanLenh);
            lenhQT.setTenKHChuyen(tenNHPhatLenh);
            lenhQT.setTenKHNhan(tenNHNhanLenh);
            lenhQT.setQtDonVi("Y");
            lenhQT.setTTVChuyenKS(ttvChuyenKS);
            //            lenhQT.setLdo_hach_toan(lyDoHachToan);
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
    /*
     * Quang VB
     * Update lệnh quyết toán
     * 
     */
    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            LenhQTTCForm f = (LenhQTTCForm)form;
            HttpSession session = request.getSession();
            TKNHKBacDAO tkbhkbDAO = new TKNHKBacDAO(conn);
            String so_tien = f.getSoTien();
            LenhQTTCDAO dao = new LenhQTTCDAO(conn);
            
            
//            if (f.getLoaiTien().equals("VND"))
//                so_tien = so_tien.replace(".", "");
//            else
//                so_tien = so_tien.replace(",", "");
//            
            so_tien = so_tien.replace(".", "");
            so_tien = so_tien.replace(",", ".");
            
            String strQuery =
                "Update sp_quyet_toan set nhkb_chuyen='" + f.getMaNHKBChuyen() +
                "',nhkb_nhan = '" + f.getMaNHKBNhan() + "'," +
                "ngay_htoan = to_date('" + f.getNgayHachToan() +
                "','dd/mm/yyyy'), ngay_qtoan=to_date('" +
                f.getNgayQuyetToan() + "','dd/mm/yyyy'), ";
            if (f.getLoaiQuyetToan().equals("900")) {
                strQuery += "loai_qtoan='D',";
            } else {
                strQuery += "loai_qtoan='C',";
            }
            strQuery +=
                    "so_tchieu='" + f.getSoThamChieuLienQuan() + "'" +
                   ", nguoi_nhap_nh='" +   f.getNguoiNhap() + "'" ;
                if(f.getNgayNhap() != null && !"".equals(f.getNgayNhap()))
                  strQuery +=  ", ngay_nhap_nh = to_date('" + f.getNgayNhap() + "','dd/mm/yyyy hh24:mi:ss')";
            
                    strQuery +=  ", nguoi_ks_nh = '" + f.getNguoiKiemSoat()+"'" ;
                if(f.getNgayNhap() != null && !"".equals(f.getNgayNhap()))               
                   strQuery += ",ngay_ks_nh= to_date('" + f.getNgayKiemSoat() +
                    "','dd/mm/yyyy hh24:mi:ss'),ndung_tt = '" +
                    f.getNoiDungThanhToan() + "'," + "so_tien='" + so_tien +
                    "', ma_nt='" + f.getLoaiTien() + "',tk_chuyen='" +
                    f.getTaiKhoanPhatLenh() + "',ma_nh_chuyen='" +
                    f.getMaNHPhatLenh() + "'," + "ten_nh_chuyen='" +
                    f.getTenNHPhatLenh() + "',ten_kh_chuyen='" +
                    f.getTenTaiKhoanPhatLenh() + "',tk_nhan='" +
                    f.getTaiKhoanNhanLenh() + "'," + "ma_nh_nhan='" +
                    f.getMaNHNhanLenh() + "',ten_nh_nhan='" +
                    f.getTenNHNhanLenh() + "',ten_kh_nhan='" +
                    f.getTenTaiKhoanNhanLenh() + "'," + "loai_hach_toan='" +
                    f.getPhuongAnHachToan() + "',ma_tchieu_gd='" +
                    f.getSoThamChieuGiaoDich() + "'";
            strQuery +=
                    ",trang_thai='01',id='" + f.getSoLenhQuyetToan() + "',id_ref='" +
                    f.getSoLenhQuyetToan() + "' " + "where id='" +
                    f.getId_ref() + "'";
            int result = dao.updateData(strQuery, null);
            if (result > 0) {
                conn.commit();
                request.setAttribute("msg1", "Sửa thành công !");
            } else {
                request.setAttribute("msg",
                                     "Sửa không thành công ! Vui lòng kiểm tra lại dữ liệu");
            }
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

            String strQuery1 = "and a.kb_id = " + NHKBChuyen+" and a.TRANG_THAI = '01' ";;
            Collection dmNH = tkbhkbDAO.getNH_KB(strQuery1, null);
            request.setAttribute("dmNH", dmNH);
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

    public ActionForward approvedLttExc(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {
        Connection conn = null;
        JsonObject jsonObj = new JsonObject();
        String strJson = "";
        Gson gson = null;
        try {
            conn = getConnection();
            String loaiQuyetToan =
                request.getParameter("loaiQuyetToan") == null ? "" :
                request.getParameter("loaiQuyetToan").toString();

            if (!"".equals(loaiQuyetToan)) {
                TTSPUtils ttspUtils = new TTSPUtils(conn);
                String idQToan = "";
                idQToan = ttspUtils.getSoLTT(loaiQuyetToan);

                gson = new GsonBuilder().setVersion(1.0).create();
                strJson = gson.toJson(idQToan);
                jsonObj.addProperty("result", strJson);
                JsonArray jsonArr = new JsonArray();
                JsonElement jsonEle = jsonObj.get("result");
                jsonArr.add(jsonEle);
                response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                PrintWriter out = response.getWriter();
                out.println(jsonArr.getAsJsonArray().toString());
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            gson = new GsonBuilder().setVersion(1.0).create();
            strJson =
                    gson.toJson("Co loi xay ra khi sinh ID lenh quyet toan: " +
                                e.getMessage());
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
        return mapping.findForward("success");
    }
}
