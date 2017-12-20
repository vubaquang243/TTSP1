package com.seatech.framework.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DChieu1VO;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.dmnh.DMNHangDAO;
import com.seatech.ttsp.dmnh.DMNHangVO;
import com.seatech.ttsp.tknhkb.TKNHKBacDAO;
import com.seatech.ttsp.tknhkb.TKNHKBacVO;

import java.io.PrintWriter;

import java.lang.reflect.Type;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TTSPLoadDMuc {
    private final static String MATTTT = "0002";
    private final static String MAKBNN = "0001";
    private final static String MASGD = "0003";
    private final static String CAP_TINH = "5";
    Connection conn = null;

    public TTSPLoadDMuc(Connection conn) {
        this.conn = conn;
    }

    public void getformTraCuu(String nhkb_tinh, String nhkb_huyen,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        String strWhereclause = "";
        Vector vParam = null;
        TKNHKBacDAO tknhkbDAO = null;
        DMKBacDAO kbDAO = null;
        DMNHangDAO dmnhDAO = null;
        DMKBacVO kbVO = null;
        ArrayList<DMKBacVO> lstDSKBTinh = null;
        ArrayList<DMKBacVO> lstDSKBHuyen = null;
        ArrayList<DMNHangVO> lstDSNHang = null;
        ArrayList<TKNHKBacVO> lstTKNHang = null;
        kbDAO = new DMKBacDAO(conn);
        tknhkbDAO = new TKNHKBacDAO(conn);
        dmnhDAO = new DMNHangDAO(conn);
        HttpSession session = request.getSession();

        String strJson = "";
        Gson gson = null;
        JsonObject jsonObj = new JsonObject();


        String strFind_KB_Huyen =
            request.getParameter(AppConstants.REQUEST_ACTION);
        if (strFind_KB_Huyen != null &&
            strFind_KB_Huyen.equalsIgnoreCase("Find_KB_Huyen")) {
            String pMa_Kb_Tinh = request.getParameter("nhkb_tinh");
            if (pMa_Kb_Tinh != null && !"".equals(pMa_Kb_Tinh)) {
                if (pMa_Kb_Tinh.equals(MASGD) || pMa_Kb_Tinh.equals(MATTTT)) {
                    strWhereclause = " a.ma = ?";
                } else {
                    strWhereclause = " a.ma_cha = ?";
                }
                vParam = new Vector();
                vParam.add(new Parameter(pMa_Kb_Tinh, true));
                lstDSKBHuyen =
                        (ArrayList<DMKBacVO>)kbDAO.getDMKBList(strWhereclause,
                                                               vParam);
                if (lstDSKBHuyen != null) {
                    strWhereclause = " and b.ma=? and a.trang_thai = '01'";
                    vParam = new Vector();
                    vParam.add(new Parameter(lstDSKBHuyen.get(0).getMa(),
                                             true));
                    lstDSNHang =
                            (ArrayList<DMNHangVO>)dmnhDAO.getListNHSaoKeTK(strWhereclause,
                                                                           vParam);
                    if (lstDSNHang.size() > 0) {
                        strWhereclause =
                                " and a.trang_thai=? and a.kb_id=? and b.ma_nh=?";
                        String strTrang_Thai = "01";
                        vParam = new Vector();
                        vParam.add(new Parameter(strTrang_Thai, true));
                        vParam.add(new Parameter(lstDSNHang.get(0).getId(),
                                                 true));
                        vParam.add(new Parameter(lstDSNHang.get(0).getMa_nh(),
                                                 true));
                        lstTKNHang =
                                (ArrayList<TKNHKBacVO>)tknhkbDAO.getTK_NH_KB(strWhereclause,
                                                                             vParam);
                    }
                }
                Type lstDSKBHuyenType = new TypeToken<ArrayList<DMKBacVO>>() {
                }.getType();
                gson = new GsonBuilder().
                        //.registerTypeAdapter(Vector.class, new JsonVectorAdapter())
                        setVersion(1.1).create();
                strJson = gson.toJson(lstDSKBHuyen, lstDSKBHuyenType);
                jsonObj.addProperty("lstDMKBHuyen", strJson);

                Type lstDSNHangType = new TypeToken<ArrayList<DMNHangVO>>() {
                }.getType();
                gson = new GsonBuilder().
                        //.registerTypeAdapter(Vector.class, new JsonVectorAdapter())
                        setVersion(1.1).create();
                strJson = gson.toJson(lstDSNHang, lstDSNHangType);
                jsonObj.addProperty("lstDSNHang", strJson);

                Type lstTKNHangType = new TypeToken<ArrayList<TKNHKBacVO>>() {
                }.getType();
                gson = new GsonBuilder().
                        //.registerTypeAdapter(Vector.class, new JsonVectorAdapter())
                        setVersion(1.1).create();
                strJson = gson.toJson(lstTKNHang, lstTKNHangType);
                jsonObj.addProperty("lstTKNHang", strJson);


                JsonArray jsonArr = new JsonArray();
                JsonElement jsonEle = jsonObj.get("lstDMKBHuyen");
                jsonArr.add(jsonEle);
                jsonEle = jsonObj.get("lstDSNHang");
                jsonArr.add(jsonEle);
                jsonEle = jsonObj.get("lstTKNHang");
                jsonArr.add(jsonEle);
                response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                PrintWriter out = response.getWriter();
                out.println(jsonArr.getAsJsonArray().toString());
                out.flush();
                out.close();
            }
        } else if (strFind_KB_Huyen != null &&
                   strFind_KB_Huyen.equalsIgnoreCase("Find_NH")) {
            String pMa_Kb_huyen = request.getParameter("nhkb_huyen");
            strWhereclause = " and b.ma=? and a.trang_thai = '01'";
            vParam = new Vector();
            vParam.add(new Parameter(pMa_Kb_huyen, true));
            lstDSNHang =
                    (ArrayList<DMNHangVO>)dmnhDAO.getListNHSaoKeTK(strWhereclause,
                                                                   vParam);
            if (lstDSNHang.size() > 0) {
                strWhereclause =
                        " and a.trang_thai=? and a.kb_id=? and b.ma_nh=?";
                String strTrang_Thai = "01";
                vParam = new Vector();
                vParam.add(new Parameter(strTrang_Thai, true));
                vParam.add(new Parameter(lstDSNHang.get(0).getId(), true));
                vParam.add(new Parameter(lstDSNHang.get(0).getMa_nh(), true));
                lstTKNHang =
                        (ArrayList<TKNHKBacVO>)tknhkbDAO.getTK_NH_KB(strWhereclause,
                                                                     vParam);
            }


            Type lstDSNHangType = new TypeToken<ArrayList<DMNHangVO>>() {
            }.getType();
            Type lstTKNHangType = new TypeToken<ArrayList<TKNHKBacVO>>() {
            }.getType();
            gson = new GsonBuilder().
                    //.registerTypeAdapter(Vector.class, new JsonVectorAdapter())
                    setVersion(1.1).create();
            strJson = gson.toJson(lstDSNHang, lstDSNHangType);
            jsonObj.addProperty("lstDSNHang", strJson);
            strJson = gson.toJson(lstTKNHang, lstTKNHangType);
            jsonObj.addProperty("lstTKNHang", strJson);

            JsonArray jsonArr = new JsonArray();
            JsonElement jsonEle = jsonObj.get("lstDSNHang");
            jsonArr.add(jsonEle);
            jsonEle = jsonObj.get("lstTKNHang");
            jsonArr.add(jsonEle);

            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(jsonArr.getAsJsonArray().toString());
            out.flush();
            out.close();
        } else if (strFind_KB_Huyen != null &&
                   strFind_KB_Huyen.equalsIgnoreCase("Find_TK")) {
            String pMa_nh = request.getParameter("manh");
            String pMa_kb = request.getParameter("makb");
            strWhereclause = " and a.trang_thai=? and c.ma=? and b.ma_nh=?";
            String strTrang_Thai = "01";

            vParam = new Vector();
            vParam.add(new Parameter(strTrang_Thai, true));
            vParam.add(new Parameter(pMa_kb, true));
            vParam.add(new Parameter(pMa_nh, true));
            lstTKNHang =
                    (ArrayList<TKNHKBacVO>)tknhkbDAO.getTK_NH_KB(strWhereclause,
                                                                 vParam);

            Type lstTKNHangType = new TypeToken<ArrayList<TKNHKBacVO>>() {
            }.getType();
            gson = new GsonBuilder().
                    //.registerTypeAdapter(Vector.class, new JsonVectorAdapter())
                    setVersion(1.1).create();
            strJson = gson.toJson(lstTKNHang, lstTKNHangType);
            jsonObj.addProperty("lstTKNHang", strJson);

            JsonArray jsonArr = new JsonArray();
            JsonElement jsonEle = jsonObj.get("lstTKNHang");
            jsonArr.add(jsonEle);
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(jsonArr.getAsJsonArray().toString());
            out.flush();
            out.close();
        } else {
            // Truong hop bat dau khi vao form
            String strMa_kb =
                (String)session.getAttribute(AppConstants.APP_KB_CODE_SESSION);
            if (strMa_kb.equals(MAKBNN) || strMa_kb.equals(MATTTT)) {
                // hien thi tat ca kho bac tinh
                strWhereclause = " a.cap=? or a.ma=? or a.ma=?";
                vParam = new Vector();
                vParam.add(new Parameter(CAP_TINH, true));
                vParam.add(new Parameter(MASGD, true));
                vParam.add(new Parameter(MATTTT, true));
                lstDSKBTinh =
                        (ArrayList<DMKBacVO>)kbDAO.getDMKBList(strWhereclause,
                                                               vParam);
                vParam = new Vector();
                lstDSKBHuyen = new ArrayList<DMKBacVO>();
                if (nhkb_tinh != null) {
                    strWhereclause = "a.ma_cha=? or ma=? ";
                    vParam = new Vector();
                    vParam.add(new Parameter(nhkb_tinh, true));
                    vParam.add(new Parameter(nhkb_tinh, true));
                    lstDSKBHuyen =
                            (ArrayList<DMKBacVO>)kbDAO.getDMKBList(strWhereclause,
                                                                   vParam);
                } else {
                    strWhereclause = " AND a.ma=? ";
                    vParam.add(new Parameter(MASGD, true));
                    kbVO = kbDAO.getDMKB(strWhereclause, vParam);
                    lstDSKBHuyen.add(kbVO);
                }
                // hien thi ngan hang cua sgd
                strWhereclause = " and b.ma=? and a.trang_thai = '01'";
                vParam = new Vector();
                if (nhkb_huyen != null) {
                    vParam.add(new Parameter(nhkb_huyen, true));
                } else {
                    vParam.add(new Parameter(kbVO.getMa(), true));
                }
                lstDSNHang =
                        (ArrayList<DMNHangVO>)dmnhDAO.getListNHSaoKeTK(strWhereclause,
                                                                       vParam);
                if (lstDSNHang.size() > 0) {
                    // hien thi so tai khoan
                    strWhereclause =
                            " and a.trang_thai=? and a.kb_id=? and b.ma_nh=?";
                    String strTrang_Thai = "01";
                    vParam = new Vector();
                    vParam.add(new Parameter(strTrang_Thai, true));
                    vParam.add(new Parameter(lstDSNHang.get(0).getId(), true));
                    vParam.add(new Parameter(lstDSNHang.get(0).getMa_nh(),
                                             true));
                    lstTKNHang =
                            (ArrayList<TKNHKBacVO>)tknhkbDAO.getTK_NH_KB(strWhereclause,
                                                                         vParam);
                }
            } else {
                // Xac dinh kho bac dang nhap la tinh hay huyen
                strWhereclause = " AND a.ma=?";
                vParam = new Vector();
                vParam.add(new Parameter(strMa_kb, true));
                kbVO = kbDAO.getDMKB(strWhereclause, vParam);
                if (kbVO.getCap().equals(CAP_TINH)) {
                    lstDSKBTinh = new ArrayList<DMKBacVO>();
                    lstDSKBTinh.add(kbVO);
                }
                // Neu la kho bac cap huyen thi set mac dinh cap huyen la kho bac so tai dong thoi xac dinh kho bac ma kho bac huyen truc thuoc
                else {
                    lstDSKBHuyen = new ArrayList<DMKBacVO>();
                    lstDSKBHuyen.add(kbVO);
                    DMKBacVO kbTinhVO = new DMKBacVO();
                    if (strMa_kb.equals(MASGD)) {
                        strWhereclause = " AND a.ma = ? ";
                        vParam = new Vector();
                        vParam.add(new Parameter(kbVO.getMa_cha(), true));

                    } else {
                        strWhereclause = " AND a.ma = ? and a.cap=?";
                        vParam = new Vector();
                        vParam.add(new Parameter(kbVO.getMa_cha(), true));
                        vParam.add(new Parameter(CAP_TINH, true));
                    }
                    kbTinhVO = kbDAO.getDMKB(strWhereclause, vParam);

                    lstDSKBTinh = new ArrayList<DMKBacVO>();
                    lstDSKBTinh.add(kbTinhVO);

                    // hien thi ngan hang cua sgd
                    strWhereclause = " and b.ma=? and a.trang_thai = '01'";
                    vParam = new Vector();
                    vParam.add(new Parameter(kbVO.getMa(), true));
                    lstDSNHang =
                            (ArrayList<DMNHangVO>)dmnhDAO.getListNHSaoKeTK(strWhereclause,
                                                                           vParam);
                    // hien thi so tai khoan
                    strWhereclause =
                            " and a.trang_thai=? and a.kb_id=? and b.ma_nh=?";
                    String strTrang_Thai = "01";
                    vParam = new Vector();
                    vParam.add(new Parameter(strTrang_Thai, true));
                    vParam.add(new Parameter(lstDSNHang.get(0).getId(), true));
                    vParam.add(new Parameter(lstDSNHang.get(0).getMa_nh(),
                                             true));
                    lstTKNHang =
                            (ArrayList<TKNHKBacVO>)tknhkbDAO.getTK_NH_KB(strWhereclause,
                                                                         vParam);
                }
            }
            request.setAttribute("lstDSKBTinh", lstDSKBTinh);
            request.setAttribute("lstDSKBHuyen", lstDSKBHuyen);
            request.setAttribute("lstDSNHang", lstDSNHang);
            request.setAttribute("lstTKNHang", lstTKNHang);
        }
    }

    public void getDanhMucKB(HttpServletRequest request,
                             HttpServletResponse response,
                             String strMaKBTinh) throws Exception {
        String strCapTinh = "5";
        String strMaSGD = "0003";
        String strCapTW = "1";
        String strWhereClauseKB = null;
        Vector vParamsKB = null;
        // xac dinh user dang nhap thuoc tinh hay huyen
        HttpSession session = request.getSession();
        String ma_kb_so_tai =
            (String)session.getAttribute(AppConstants.APP_KB_CODE_SESSION);

        DMKBacDAO kbDAO = new DMKBacDAO(conn);
        ArrayList<DMKBacVO> lstKBTinh = new ArrayList<DMKBacVO>();
        ArrayList<DMKBacVO> lstKBHuyen = new ArrayList<DMKBacVO>();

        try {
            // xac dinh user dang nhap thuoc tinh hay huyen

            session = request.getSession();


            vParamsKB = new Vector();
            strWhereClauseKB = "a.ma=? ";
            vParamsKB.add(new Parameter(ma_kb_so_tai, true));
            DMKBacVO dmkbVO = kbDAO.getDMKBNH(strWhereClauseKB, vParamsKB);
            // Neu user thuoc cap tinh. Set kb tinh va kb huyen
            if (dmkbVO.getCap().equals(strCapTW)) {
                vParamsKB = new Vector();
                strWhereClauseKB =
                        " a.ma_cha=? and (a.ma<>'0002' and a.ma<>'0003') or a.ma='0001'";
                vParamsKB.add(new Parameter(dmkbVO.getMa(), true));
                lstKBTinh =
                        (ArrayList<DMKBacVO>)kbDAO.getDMNHKBList1(strWhereClauseKB,
                                                                  vParamsKB);
                if (strMaKBTinh != null) {
                    vParamsKB = new Vector();
                    strWhereClauseKB = " a.ma_cha=? ";
                    vParamsKB.add(new Parameter(strMaKBTinh, true));
                    lstKBHuyen =
                            (ArrayList<DMKBacVO>)kbDAO.getDMNHKBList1(strWhereClauseKB,
                                                                      vParamsKB);
                }
            } else if (dmkbVO.getCap().equals(strCapTinh)) {
                lstKBTinh.add(dmkbVO);
                vParamsKB = new Vector();
                strWhereClauseKB = " a.ma_cha=? and a.ma='0001'";
                vParamsKB.add(new Parameter(dmkbVO.getMa(), true));
                lstKBHuyen =
                        (ArrayList<DMKBacVO>)kbDAO.getDMNHKBList1(strWhereClauseKB,
                                                                  vParamsKB);
            } else if (dmkbVO.getCap().equals("2")) {
                vParamsKB = new Vector();
                strWhereClauseKB = " a.ma=? ";
                vParamsKB.add(new Parameter(dmkbVO.getMa_cha(), true));
                lstKBTinh.add(kbDAO.getDMKBNH(strWhereClauseKB, vParamsKB));
                vParamsKB = new Vector();
                strWhereClauseKB = " a.ma_cha=? ";
                vParamsKB.add(new Parameter(dmkbVO.getMa_cha(), true));
                lstKBHuyen =
                        (ArrayList<DMKBacVO>)kbDAO.getDMNHKBList1(strWhereClauseKB,
                                                                  vParamsKB);
            } else {
                lstKBHuyen.add(dmkbVO);
                vParamsKB = new Vector();
                strWhereClauseKB = " a.ma=? ";
                vParamsKB.add(new Parameter(dmkbVO.getMa_cha(), true));
                lstKBTinh.add(kbDAO.getDMKBNH(strWhereClauseKB, vParamsKB));
            }

            if (lstKBTinh == null) {
                lstKBTinh = new ArrayList<DMKBacVO>();
            }
            if (lstKBHuyen == null) {
                lstKBHuyen = new ArrayList<DMKBacVO>();
            }

            request.setAttribute("capuser", dmkbVO.getCap());
            request.setAttribute("lstKBTinh", lstKBTinh);
            request.setAttribute("lstKBHuyen", lstKBHuyen);
        } catch (Exception ex) {
            throw new Exception("Tra Cuu LTT: " + ex);
        }
    }

    public void getDMTinh(Connection conn,
                          HttpServletRequest request) throws Exception {
        List cacKhoBacTinh = null;
        DChieu1DAO dao = new DChieu1DAO(conn);
        DChieu1VO vo = new DChieu1VO();
        HttpSession session = request.getSession();
        String kbCode =
            session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
        vo = dao.getCap(" and ma = " + kbCode, null);
        String cap = vo.getCap();

        if ("0001".equals(kbCode) || "0002".equals(kbCode)) {
            cacKhoBacTinh = (List)dao.getDMucKB_Tinh("", null);
            request.setAttribute("dftinh", "dftinh");
            request.setAttribute("tctinh", "tctinh");
        } else if ("0003".equals(kbCode)) {
            String whereClause = " AND a.ma = '0003' ";
            cacKhoBacTinh = (List)dao.getDMucKB_Tinh(whereClause, null);
        } else if ("5".equals(cap)) {
            String whereClause = " AND a.ma = " + kbCode;
            cacKhoBacTinh = (List)dao.getDMucKB_Tinh(whereClause, null);
        } else {
            String whereClause =
                " and a.id_cha in (select id_cha from sp_dm_htkb where ma = " +
                kbCode + ")";
            cacKhoBacTinh = (List)dao.getDMucKB_Tinh(whereClause, null);
        }
        request.setAttribute("dmuckb_tinh", cacKhoBacTinh);
    }

    //Get danh muc kho bac

    public void getDanhMucKB(HttpServletRequest request,
                             String strMaKBTinh) throws Exception {
        String strCapTinh = "5";
        String strMaSGD = "0003";
        String strCapTW = "1";
        String strWhereClauseKB = null;
        Vector vParamsKB = null;
        // xac dinh user dang nhap thuoc tinh hay huyen
        HttpSession session = request.getSession();
        String ma_kb_so_tai =
            (String)session.getAttribute(AppConstants.APP_KB_CODE_SESSION);

        DMKBacDAO kbDAO = new DMKBacDAO(conn);
        ArrayList<DMKBacVO> lstKBTinh = new ArrayList<DMKBacVO>();
        ArrayList<DMKBacVO> lstKBHuyen = new ArrayList<DMKBacVO>();

        try {

            // xac dinh user dang nhap thuoc tinh hay huyen
            session = request.getSession();
            vParamsKB = new Vector();
            strWhereClauseKB = "a.ma=? ";
            vParamsKB.add(new Parameter(ma_kb_so_tai, true));
            DMKBacVO dmkbVO = kbDAO.getDMKBNH(strWhereClauseKB, vParamsKB);
            // Neu user thuoc cap tinh. Set kb tinh va kb huyen
            if (strMaKBTinh.equals("0001") || strMaKBTinh.equals("0002")) {
                vParamsKB = new Vector();
                strWhereClauseKB =
                        " a.ma_cha='0001' and (a.ma<>'0002' and a.ma<>'0003') or a.ma='0001'";
                lstKBTinh =
                        (ArrayList<DMKBacVO>)kbDAO.getDMNHKBList1(strWhereClauseKB,
                                                                  null);
            } else {
                if (dmkbVO.getCap().equals("5")) {
                    lstKBTinh.add(dmkbVO);
                    vParamsKB = new Vector();
                    strWhereClauseKB = " a.ma_cha=? ";
                    vParamsKB.add(new Parameter(dmkbVO.getMa(), true));
                    lstKBHuyen =
                            (ArrayList<DMKBacVO>)kbDAO.getDMNHKBList1(strWhereClauseKB,
                                                                      vParamsKB);
                }
                if (dmkbVO.getCap().equals("2")) {
                    vParamsKB = new Vector();
                    strWhereClauseKB = " a.ma=? ";
                    vParamsKB.add(new Parameter(dmkbVO.getMa_cha(), true));
                    lstKBTinh.add(kbDAO.getDMKBNH(strWhereClauseKB,
                                                  vParamsKB));
                    vParamsKB = new Vector();
                    strWhereClauseKB = " a.ma_cha=? ";
                    vParamsKB.add(new Parameter(dmkbVO.getMa_cha(), true));
                    lstKBHuyen =
                            (ArrayList<DMKBacVO>)kbDAO.getDMNHKBList1(strWhereClauseKB,
                                                                      vParamsKB);
                } else {
                  lstKBHuyen.add(dmkbVO);
                  vParamsKB = new Vector();
                  strWhereClauseKB = " a.ma=? ";
                  vParamsKB.add(new Parameter(dmkbVO.getMa_cha(), true));
                  lstKBTinh.add(kbDAO.getDMKBNH(strWhereClauseKB, vParamsKB));
                }
            }

            if (lstKBTinh == null) {
                lstKBTinh = new ArrayList<DMKBacVO>();
            }
            if (lstKBHuyen == null) {
                lstKBHuyen = new ArrayList<DMKBacVO>();
            }
          
            request.setAttribute("strMaKBTinh", strMaKBTinh);
            request.setAttribute("capuser", dmkbVO.getCap());
            request.setAttribute("lstKBTinh", lstKBTinh);
            request.setAttribute("lstKBHuyen", lstKBHuyen);
        } catch (Exception ex) {
            throw new Exception("Tra Cuu LTT: " + ex);
        }
    }
}
