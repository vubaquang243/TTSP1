package com.seatech.ttsp.reports.sketk.actions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import com.jaspersoft.jrx.query.JRXPathQueryExecuterFactory;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.exception.TTSPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.dmnh.DMNHangDAO;
import com.seatech.ttsp.dmnh.DMNHangVO;
import com.seatech.ttsp.reports.sketk.SKeTKChiTietVO;
import com.seatech.ttsp.reports.sketk.SKeTKDAO;
import com.seatech.ttsp.reports.sketk.SKeTKVO;
import com.seatech.ttsp.reports.sketk.forms.SKeTKForm;
import com.seatech.ttsp.tknhkb.TKNHKBacDAO;
import com.seatech.ttsp.tknhkb.TKNHKBacVO;

import java.io.InputStream;
import java.io.PrintWriter;

import java.lang.reflect.Type;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @Modify: HungBM
 * @Modify date: 26/06/2017
 * @see: Doi function lay danh sach ngan hanng tu getListNH sang getListNHSaoKeTK
 * @find: 20170626
 * */

public class SKeTKAction extends AppAction {
    public SKeTKAction() {
        super();
    }
    private final static String MATTTT = "0002";
    private final static String MAKBNN = "0001";
    private final static String MASGD = "0003";
    private final static String CAP_TINH = "5";
    public static final String REPORT_DIRECTORY = "/report";
    public static final String strFontTimeRoman = "/font/times.ttf";
    public static final String STRING_EMPTY = "";

    protected ActionForward executeAction(ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {

        Connection conn = null;
        //        ArrayList<TKNHKBacVO> arrTKNHKB = null;
        String strWhereclause = "";
        TKNHKBacDAO tknhkbDAO = null;
        DMKBacDAO kbDAO = null;
        DMNHangDAO dmnhDAO = null;
        DMNHangVO dmnhVO = null;
        DMKBacVO kbVO = null;
        ArrayList<DMKBacVO> lstDSKBTinh = null;
        ArrayList<DMKBacVO> lstDSKBHuyen = null;
        ArrayList<DMNHangVO> lstDSNHang = null;
        ArrayList<TKNHKBacVO> lstTKNHang = null;
        Vector vParam = null;
        String strJson = "";
        Gson gson = null;
        JsonObject jsonObj = new JsonObject();
        //        String strTrang_Thai = "";
        //        String strKB_ID = "";a.receive_bank=?
        if (isCancelled(request)) {
            return mapping.findForward(AppConstants.FAILURE);
        }
        if (!checkPermissionOnFunction(request, "BCAO.SKE_TK")) {
            return mapping.findForward("errorQuyen");
        }
        try {
            conn = getConnection();
            SKeTKForm frm = (SKeTKForm)form;
            if (frm.getNhkb_tinh() != null || frm.getNhkb_huyen() != null ||
                frm.getManh() != null || frm.getSotk() != null ||
                frm.getTu_ngay() != null || frm.getDen_ngay() != null) {
                request.setAttribute("nhkb_tinh", frm.getNhkb_tinh());
                request.setAttribute("nhkb_huyen", frm.getNhkb_huyen());
                request.setAttribute("ma_nh", frm.getManh());
                request.setAttribute("so_tk", frm.getSotk());
                request.setAttribute("den_ngay", frm.getDen_ngay());
                request.setAttribute("tu_ngay", frm.getTu_ngay());
                request.setAttribute("loai_tien", frm.getLoai_tien());
            }
            //            Xac dinh kb la kb tinh hay huyen
            HttpSession session = request.getSession();
            kbDAO = new DMKBacDAO(conn);
            tknhkbDAO = new TKNHKBacDAO(conn);
            dmnhDAO = new DMNHangDAO(conn);
            // truong hop khi chon kho bac tinh
            String strFind_KB_Huyen =
                request.getParameter(AppConstants.REQUEST_ACTION);
            if (strFind_KB_Huyen != null &&
                strFind_KB_Huyen.equalsIgnoreCase("Find_KB_Huyen")) {
                String pMa_Kb_Tinh = request.getParameter("nhkb_tinh");
                if (pMa_Kb_Tinh != null && !"".equals(pMa_Kb_Tinh)) {
                    if (pMa_Kb_Tinh.equals(MASGD)) {
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
                        //HungBM - 20170626 - BEGIN
                        lstDSNHang =
                                (ArrayList<DMNHangVO>)dmnhDAO.getListNHSaoKeTK(strWhereclause,
                                                                               vParam);
                        //HungBM - 20170626 - END
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
                    Type lstDSKBHuyenType =
                        new TypeToken<ArrayList<DMKBacVO>>() {
                    }.getType();
                    gson = new GsonBuilder().
                            //.registerTypeAdapter(Vector.class, new JsonVectorAdapter())
                            setVersion(1.1).create();
                    strJson = gson.toJson(lstDSKBHuyen, lstDSKBHuyenType);
                    jsonObj.addProperty("lstDMKBHuyen", strJson);

                    Type lstDSNHangType =
                        new TypeToken<ArrayList<DMNHangVO>>() {
                    }.getType();
                    gson = new GsonBuilder().
                            //.registerTypeAdapter(Vector.class, new JsonVectorAdapter())
                            setVersion(1.1).create();
                    strJson = gson.toJson(lstDSNHang, lstDSNHangType);
                    jsonObj.addProperty("lstDSNHang", strJson);

                    Type lstTKNHangType =
                        new TypeToken<ArrayList<TKNHKBacVO>>() {
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
                //HungBM - 20170626 - BEGIN
                lstDSNHang =
                        (ArrayList<DMNHangVO>)dmnhDAO.getListNHSaoKeTK(strWhereclause,
                                                                       vParam);
                //HungBM - 20170626 - END
                if (lstDSNHang.size() > 0) {
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
                strWhereclause =
                        " and a.trang_thai=? and c.ma=? and b.ma_nh=?";
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
                if (strMa_kb.equals(MAKBNN) || strMa_kb.equals(MASGD) ||
                    strMa_kb.equals(MATTTT)) {
                    // hien thi tat ca kho bac tinh
                    strWhereclause = " a.cap=? or a.ma=? ";
                    vParam = new Vector();
                    vParam.add(new Parameter(CAP_TINH, true));
                    vParam.add(new Parameter(MASGD, true));
                    lstDSKBTinh =
                            (ArrayList<DMKBacVO>)kbDAO.getDMKBList(strWhereclause,
                                                                   vParam);
                    // hien thi mac dinh kho bac huyen la sgd

                    vParam = new Vector();
                    lstDSKBHuyen = new ArrayList<DMKBacVO>();
                    if (frm.getNhkb_tinh() != null) {
                        strWhereclause = "a.ma_cha=? or ma=? ";
                        vParam = new Vector();
                        vParam.add(new Parameter(frm.getNhkb_tinh(), true));
                        vParam.add(new Parameter(frm.getNhkb_tinh(), true));
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
                    if (frm.getNhkb_huyen() != null) {
                        vParam.add(new Parameter(frm.getNhkb_huyen(), true));
                    } else {
                        vParam.add(new Parameter(kbVO.getMa(), true));
                    }
                    //HungBM - 20170626 - BEGIN
                    lstDSNHang =
                            (ArrayList<DMNHangVO>)dmnhDAO.getListNHSaoKeTK(strWhereclause,
                                                                           vParam);
                    //HungBM - 20170626 - END
                    if (lstDSNHang.size() > 0) {
                        // hien thi so tai khoan
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
                } else {
                    // Xac dinh kho bac dang nhap la tinh hay huyen
                    strWhereclause = " AND a.ma=?";
                    vParam = new Vector();
                    vParam.add(new Parameter(strMa_kb, true));
                    kbVO = kbDAO.getDMKB(strWhereclause, vParam);
                    // Neu kho bac la kho bac tinh thi set mac dinh cap tinh la kho bac so tai
                    if (kbVO.getCap().equals(CAP_TINH)) {
                        lstDSKBTinh = new ArrayList<DMKBacVO>();
                        lstDSKBTinh.add(kbVO);
                    }
                    // Neu la kho bac cap huyen thi set mac dinh cap huyen la kho bac so tai dong thoi xac dinh kho bac ma kho bac huyen truc thuoc
                    else {
                        lstDSKBHuyen = new ArrayList<DMKBacVO>();
                        lstDSKBHuyen.add(kbVO);
                        DMKBacVO kbTinhVO = new DMKBacVO();
                        strWhereclause = " AND a.ma = ? and a.cap=?";
                        vParam = new Vector();
                        vParam.add(new Parameter(kbVO.getMa_cha(), true));
                        vParam.add(new Parameter(CAP_TINH, true));
                        kbTinhVO = kbDAO.getDMKB(strWhereclause, vParam);
                        lstDSKBTinh = new ArrayList<DMKBacVO>();
                        lstDSKBTinh.add(kbTinhVO);

                        // hien thi ngan hang cua sgd
                        strWhereclause = " and b.ma=? and a.trang_thai = '01'";
                        vParam = new Vector();
                        vParam.add(new Parameter(kbVO.getMa(), true));
                        //HungBM - 20170626 - BEGIN
                        lstDSNHang =
                                (ArrayList<DMNHangVO>)dmnhDAO.getListNHSaoKeTK(strWhereclause,
                                                                               vParam);
                        //HungBM - 20170626 - END
                        // hien thi so tai khoan
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
                request.setAttribute("lstDSKBTinh", lstDSKBTinh);
                request.setAttribute("lstDSKBHuyen", lstDSKBHuyen);
                request.setAttribute("lstDSNHang", lstDSNHang);
                request.setAttribute("lstTKNHang", lstTKNHang);
            }
        } catch (Exception e) {
            // TODO: Add catch code
            throw e;
        } finally {
            close(conn);
        }

        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        if (isCancelled(request)) {
            return mapping.findForward(AppConstants.FAILURE);
        }
        if (!checkPermissionOnFunction(request, "BCAO.SKE_TK")) {
            return mapping.findForward("errorQuyen");
        }
        Connection conn = null;
        SKeTKDAO skDAO = null;
        DMNHangDAO dmNHDAO = null;
        DMNHangVO dmNHVO = null;
        ArrayList<SKeTKChiTietVO> lstSKe = null;
        String strWhere = null;
        String whereClause = "";
        Vector vParam = null;
        Vector params = null;
        try {
            conn = getConnection();
            dmNHDAO = new DMNHangDAO(conn);
            SKeTKForm f = (SKeTKForm)form;
            skDAO = new SKeTKDAO(conn);
            // lay ra ban ghi sao ke
            String strwhereClause = " a.shkb=?";
            String strIDSK = f.getNhkb_huyen();
            Vector v_params = new Vector();
            v_params.add(new Parameter(strIDSK, true));
            dmNHVO = dmNHDAO.getDMNHSBySHKB(strwhereClause, v_params);
            if (dmNHVO != null) {
                params = new Vector();
                if (dmNHVO.getMa_nh() != null &&
                    !"".equals(dmNHVO.getMa_nh())) {
                    whereClause += " and a.receive_bank=? ";
                    params.add(new Parameter(dmNHVO.getMa_nh(), true));
                }
                if (f.getManh() != null && !"".equals(f.getManh())) {
                    whereClause += "and a.send_bank=? ";
                    params.add(new Parameter(f.getManh(), true));
                }
                if (f.getSotk() != null && !"".equals(f.getSotk())) {
                    whereClause += "and a.so_tk=? ";
                    params.add(new Parameter(f.getSotk(), true));
                }
                if (f.getTu_ngay() != null && !"".equals(f.getTu_ngay())) {
                    whereClause +=
                            "and trunc(a.ngay_du_cuoi)= to_date(?" + ",'DD/MM/YYYY') ";
                    params.add(new Parameter(f.getTu_ngay(), true));
                }
                if (f.getLoai_tien() != null && !"".equals(f.getLoai_tien())) {
                    whereClause += "a.loai_tien_du_dau='?' ";
                    params.add(new Parameter(f.getLoai_tien(), true));
                } else {
                    whereClause +=
                            "and trunc(a.ngay_du_cuoi)= trunc(sysdate) ";
                }
                SKeTKVO skVO = null;
                try {
                    skVO = skDAO.getSK(whereClause, params);
                } catch (Exception eSkVO) {
                    executeAction(mapping, form, request, response);
                    throw TTSPException.createException("TTSP-9999",
                                                        "L&#7895;i h&#7879; th&#7889;ng : - Kh&#244;ng t&#236;m th&#7845;y b&#7843;n sao k&#234; n&#224;o");
                }
                // neu tim thay ban sao ke tim cac ban sao ke chi tiet
                if (skVO != null) {
                    BeanUtils.copyProperties(f, skVO);
                    String page = f.getPageNumber();
                    if (page == null) {
                        page = "1";
                    }
                    // khai bao cac bien phan trang
                    Integer currentPage = new Integer(page);
                    Integer numberRowOnPage = new Integer(15);
                    Integer totalCount[] = new Integer[1];
                    strWhere = "a.sao_ke_id=? ";
                    vParam = new Vector();
                    vParam.add(new Parameter(skVO.getId(), true));
                    lstSKe =
                            (ArrayList<SKeTKChiTietVO>)skDAO.getSKChiTietListWithPading(skVO,
                                                                                        currentPage,
                                                                                        numberRowOnPage,
                                                                                        totalCount);

                    request.setAttribute("lstSKe",
                                         lstSKe != null ? lstSKe : new ArrayList<SKeTKVO>());
                    PagingBean pagingBean = new PagingBean();
                    pagingBean.setCurrentPage(currentPage);
                    pagingBean.setNumberOfRow(totalCount[0].intValue());
                    pagingBean.setRowOnPage(numberRowOnPage);
                    request.setAttribute("PAGE_KEY", pagingBean);
                } else {
                    request.setAttribute("lstSKe",
                                         lstSKe != null ? lstSKe : new ArrayList<SKeTKVO>());
                    PagingBean pagingBean = new PagingBean();
                    request.setAttribute("PAGE_KEY", pagingBean);
                }
                //----------------------------------------------------------------------------
                // Truong hop bat dau khi vao form
                String strWhereclause = "";
                TKNHKBacDAO tknhkbDAO = null;
                DMKBacDAO kbDAO = null;
                DMNHangDAO dmnhDAO = null;
                //                DMNHangVO dmnhVO = null;
                DMKBacVO kbVO = null;
                ArrayList<DMKBacVO> lstDSKBTinh = null;
                ArrayList<DMKBacVO> lstDSKBHuyen = null;
                ArrayList<DMNHangVO> lstDSNHang = null;
                ArrayList<TKNHKBacVO> lstTKNHang = null;
                HttpSession session = request.getSession();
                SKeTKForm frm = (SKeTKForm)form;
                //              Vector vParam = null;
                String strMa_kb =
                    (String)session.getAttribute(AppConstants.APP_KB_CODE_SESSION);
                kbDAO = new DMKBacDAO(conn);
                tknhkbDAO = new TKNHKBacDAO(conn);
                dmnhDAO = new DMNHangDAO(conn);
                if (strMa_kb.equals(MAKBNN) || strMa_kb.equals(MASGD) ||
                    strMa_kb.equals(MATTTT)) {
                    // hien thi tat ca kho bac tinh
                    strWhereclause = " a.cap=? or a.ma=? ";
                    vParam = new Vector();
                    vParam.add(new Parameter(CAP_TINH, true));
                    vParam.add(new Parameter(MASGD, true));
                    lstDSKBTinh =
                            (ArrayList<DMKBacVO>)kbDAO.getDMKBList(strWhereclause,
                                                                   vParam);
                    // hien thi mac dinh kho bac huyen la sgd

                    vParam = new Vector();
                    lstDSKBHuyen = new ArrayList<DMKBacVO>();
                    if (frm.getNhkb_tinh() != null) {
                        strWhereclause = "a.ma_cha=? or ma=? ";
                        vParam = new Vector();
                        vParam.add(new Parameter(frm.getNhkb_tinh(), true));
                        vParam.add(new Parameter(frm.getNhkb_tinh(), true));
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
                    if (frm.getNhkb_huyen() != null) {
                        vParam.add(new Parameter(frm.getNhkb_huyen(), true));
                    } else {
                        vParam.add(new Parameter(kbVO.getMa(), true));
                    }
                    //HungBM - 20170626 - BEGIN
                    lstDSNHang =
                            (ArrayList<DMNHangVO>)dmnhDAO.getListNHSaoKeTK(strWhereclause,
                                                                           vParam);
                    //HungBM - 20170626 - END
                    if (lstDSNHang.size() > 0) {
                        // hien thi so tai khoan
                        strWhereclause =
                                " and a.trang_thai=? and a.kb_id=? and b.ma_nh=?";
                        String strTrang_Thai = "01";
                        vParam = new Vector();
                        vParam.add(new Parameter(strTrang_Thai, true));
                        // kiem tra xem dang select len ngan hang nao
                        DMNHangVO voNHSelected = new DMNHangVO();
                        for (DMNHangVO voDMNH : lstDSNHang) {
                            if (voDMNH.getMa_nh().equalsIgnoreCase(frm.getManh())) {
                                voNHSelected = voDMNH;
                                break;
                            }
                        }
                        vParam.add(new Parameter(voNHSelected.getId(), true));
                        vParam.add(new Parameter(voNHSelected.getMa_nh(),
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
                    // Neu kho bac la kho bac tinh thi set mac dinh cap tinh la kho bac so tai
                    if (kbVO.equals(CAP_TINH)) {
                        lstDSKBTinh = new ArrayList<DMKBacVO>();
                        lstDSKBTinh.add(kbVO);
                    }
                    // Neu la kho bac cap huyen thi set mac dinh cap huyen la kho bac so tai dong thoi xac dinh kho bac ma kho bac huyen truc thuoc
                    else {
                        lstDSKBHuyen = new ArrayList<DMKBacVO>();
                        lstDSKBHuyen.add(kbVO);
                        DMKBacVO kbTinhVO = new DMKBacVO();
                        strWhereclause = " AND a.ma = ? and a.cap=?";
                        vParam = new Vector();
                        vParam.add(new Parameter(kbVO.getMa_cha(), true));
                        vParam.add(new Parameter(CAP_TINH, true));
                        kbTinhVO = kbDAO.getDMKB(strWhereclause, vParam);
                        lstDSKBTinh = new ArrayList<DMKBacVO>();
                        lstDSKBTinh.add(kbTinhVO);

                        // hien thi ngan hang cua sgd
                        strWhereclause = " and b.ma=? and a.trang_thai = '01'";
                        vParam = new Vector();
                        vParam.add(new Parameter(kbVO.getMa(), true));
                        //HungBM - 20170626 - BEGIN
                        lstDSNHang =
                                (ArrayList<DMNHangVO>)dmnhDAO.getListNHSaoKeTK(strWhereclause,
                                                                               vParam);
                        //HungBM - 20170626 - END
                        // hien thi so tai khoan
                        strWhereclause =
                                " and a.trang_thai=? and a.kb_id=? and b.ma_nh=?";
                        String strTrang_Thai = "01";
                        vParam = new Vector();
                        vParam.add(new Parameter(strTrang_Thai, true));
                        vParam.add(new Parameter(kbVO.getId(), true));
                        vParam.add(new Parameter(f.getManh(), true));
                        lstTKNHang =
                                (ArrayList<TKNHKBacVO>)tknhkbDAO.getTK_NH_KB(strWhereclause,
                                                                             vParam);
                    }
                }
                request.setAttribute("lstDSKBTinh", lstDSKBTinh);
                request.setAttribute("lstDSKBHuyen", lstDSKBHuyen);
                request.setAttribute("lstDSNHang", lstDSNHang);
                request.setAttribute("lstTKNHang", lstTKNHang);
            } else {
                throw TTSPException.createException("TTSP-9999",
                                                    "Kh&#244;ng t&#236;m th&#7845;y kho b&#7841;c!");
            }
        } catch (Exception e) {
            // TODO: Add catch code
            throw e;
        } finally {
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

    public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        if (isCancelled(request)) {
            return mapping.findForward(AppConstants.FAILURE);
        }
        if (!checkPermissionOnFunction(request, "BCAO.BKLTT")) {
            return mapping.findForward(AppConstants.FAILURE);
        }
        Connection conn = null;
        String reportName = "";
        InputStream reportStream = null;
        JasperPrint jasperPrint = null;
        HashMap parameterMap = new HashMap();
        StringBuffer sbSubHTML = new StringBuffer();
        DMNHangVO dmKBVO = null;
        DMKBacDAO dmKBDAO = null;
        SKeTKForm f = (SKeTKForm)form;
        SKeTKDAO skDAO = null;
        Vector params = null;
        SKeTKVO skVO = null;
        try {
            String strAction =
                request.getParameter(AppConstants.REQUEST_ACTION);
            conn = getConnection();
            dmKBDAO = new DMKBacDAO(conn);
            skDAO = new SKeTKDAO(conn);
            // lay ra ban ghi sao ke
            //String strwhereClause = " a.ma=?";
            String whereClause = "";
            String strIDSK = f.getNhkb_huyen();
            sbSubHTML.append("<input type=\"hidden\" name=\"nhkb_huyen\" value=\"" +
                             strIDSK + "\" id=\"nhkb_huyen\"></input>");

            Vector v_params = new Vector();
            v_params.add(new Parameter(strIDSK, true));
            String strMANH_KBHuyen = dmKBDAO.getMaKB8So(strIDSK);
            params = new Vector();
            if (strMANH_KBHuyen != null && !"".equals(strMANH_KBHuyen)) {
                whereClause += " and a.receive_bank=? ";
                params.add(new Parameter(strMANH_KBHuyen, true));
                sbSubHTML.append("<input type=\"hidden\" name=\"nhkb_huyen\" value=\"" +
                                 strIDSK + "\" id=\"manh\"></input>");
            }
            if (f.getManh() != null && !"".equals(f.getManh())) {
                whereClause += " and a.send_bank=? ";
                params.add(new Parameter(f.getManh(), true));
                sbSubHTML.append("<input type=\"hidden\" name=\"manh\" value=\"" +
                                 f.getManh() + "\" id=\"manh\"></input>");
            }
            if (f.getSotk() != null && !"".equals(f.getSotk())) {
                whereClause += " and a.so_tk=? ";
                params.add(new Parameter(f.getSotk(), true));
                sbSubHTML.append("<input type=\"hidden\" name=\"sotk\" value=\"" +
                                 f.getSotk() + "\" id=\"sotk\"></input>");
            }
            if (f.getTu_ngay() != null && !"".equals(f.getTu_ngay())) {
                whereClause +=
                        "and trunc(a.ngay_du_cuoi)= to_date(?" + ",'DD/MM/YYYY') ";
                params.add(new Parameter(f.getTu_ngay(), true));
                sbSubHTML.append("<input type=\"hidden\" name=\"tu_ngay\" value=\"" +
                                 f.getTu_ngay() +
                                 "\" id=\"tu_ngay\"></input>");
            } else {
                whereClause += "and trunc(a.ngay_du_cuoi)= trunc(sysdate) ";
                sbSubHTML.append("<input type=\"hidden\" name=\"tu_ngay\" value=\"\" id=\"tu_ngay\"></input>");
            }

            try {
                skVO = skDAO.getSK(whereClause, params);
            } catch (Exception eSkVO) {
                executeAction(mapping, form, request, response);
                throw TTSPException.createException("TTSP-9999",
                                                    "L&#7895;i h&#7879; th&#7889;ng : - Kh&#244;ng t&#236;m th&#7845;y b&#7843;n sao k&#234; n&#224;o");
            }
            if (skVO != null) {
                sbSubHTML.append("<input type=\"hidden\" name=\"id\" value=\"" +
                                 skVO.getId() + "\" id=\"id\"></input>");
            } else {
                throw TTSPException.createException("TTSP-9999",
                                                    "Ng&#226;n h&#224;ng ch&#432;a g&#7917;i s&#7893; chi ti&#7871;t. Li&#234;n h&#7879; v&#7899;i NH &#273;&#7875; ki&#7875;m tra");
            }
            // report
            if (request.getParameter("reportName") != null)
                reportName = request.getParameter("reportName");

            String strPathFont = AppConstants.FONT_FOR_REPORT;

            parameterMap.put("p_SO_TK", f.getSotk());
            parameterMap.put("p_MA_KB", strMANH_KBHuyen);
            parameterMap.put("p_MA_NH", f.getManh());
            parameterMap.put("P_KETOAN",
                             skVO != null ? skVO.getNguoi_tao() : "");
            parameterMap.put("P_KETOANTRUONG",
                             skVO != null ? skVO.getNguoi_ks() : "");
            // them tham so ngay NH gui, kb nhan
            parameterMap.put("p_nh_ngay",
                             skVO != null ? skVO.getNgay_ks() : "");
            parameterMap.put("p_kb_ngay",
                             skVO != null ? skVO.getInsert_date() : "");
            //
            if (f.getTu_ngay() == null || "".equals(f.getTu_ngay())) {
                Date date = new Date();
                parameterMap.put("p_NGAY",
                                 StringUtil.DateToString(date, "dd-MM-yyyy"));
            } else {
                parameterMap.put("p_NGAY", f.getTu_ngay().replace("/", "-"));
            }

            DMNHangVO dmNHVO = new DMNHangVO();
            DMNHangDAO dmNHDAO = new DMNHangDAO(conn);
            whereClause = " a.ma_nh=? ";
            params = new Vector();
            params.add(new Parameter(strMANH_KBHuyen, true));
            dmNHVO = dmNHDAO.getDMNH(whereClause, params);
            parameterMap.put("P_DV_KBNN", dmNHVO.getTen());
            dmNHVO = new DMNHangVO();
            whereClause = " a.ma_nh=? ";
            params = new Vector();
            params.add(new Parameter(f.getManh(), true));
            dmNHVO = dmNHDAO.getDMNH(whereClause, params);
            parameterMap.put("P_CN_NHTM", dmNHVO.getTen());
            parameterMap.put("P_NGAY_BC", f.getTu_ngay());
            parameterMap.put("P_MA_LT", skVO.getLoai_tien_du_dau());

            if ("VND".equalsIgnoreCase(skVO.getLoai_tien_du_dau())) {
                parameterMap.put("REPORT_LOCALE",
                                 new java.util.Locale("vi", "VI"));
            } else {
                parameterMap.put("REPORT_LOCALE",
                                 new java.util.Locale("en", "US"));
            }
            reportName = "/BCSaoKETK";
            reportStream =
                    getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY +
                                                                                            reportName +
                                                                                            ".jasper");
            jasperPrint =
                    JasperFillManager.fillReport(reportStream, parameterMap,
                                                 conn);
            String strTypePrintAction = strAction == null ? "" : strAction;
            String strActionName = "skeTKPrintAction.do";
            String strParameter = "";
            ReportUtility rpUtilites = new ReportUtility();
            rpUtilites.exportReport(jasperPrint, strTypePrintAction, response,
                                    reportName, strPathFont, strActionName,
                                    sbSubHTML.toString(), strParameter);
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
            throw e;
        } finally {
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }
    //update trang thai

    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        if (isCancelled(request)) {
            return mapping.findForward(AppConstants.FAILURE);
        }
        if (!checkPermissionOnFunction(request, "BCAO.SKE_TK")) {
            return mapping.findForward("errorQuyen");
        }
        Connection conn = null;
        SKeTKDAO skDAO = null;
        DMNHangDAO dmNHDAO = null;
        DMNHangVO dmNHVO = null;
        ArrayList<SKeTKChiTietVO> lstSKe = null;
        String strWhere = null;
        String whereClause = "";
        Vector vParam = null;
        Vector params = null;
        try {
            conn = getConnection();
            dmNHDAO = new DMNHangDAO(conn);
            SKeTKForm f = (SKeTKForm)form;
            skDAO = new SKeTKDAO(conn);
            String strwhereClause = " a.shkb=?";
            String strIDSK = f.getNhkb_huyen();
            Vector v_params = new Vector();
            v_params.add(new Parameter(strIDSK, true));
            dmNHVO = dmNHDAO.getDMNHSBySHKB(strwhereClause, v_params);
            if (dmNHVO != null) {
                params = new Vector();
                if (dmNHVO.getMa_nh() != null &&
                    !"".equals(dmNHVO.getMa_nh())) {
                    whereClause += " and a.receive_bank=? ";
                    params.add(new Parameter(dmNHVO.getMa_nh(), true));
                }
                if (f.getManh() != null && !"".equals(f.getManh())) {
                    whereClause += "and a.send_bank=? ";
                    params.add(new Parameter(f.getManh(), true));
                }
                if (f.getSotk() != null && !"".equals(f.getSotk())) {
                    whereClause += "and a.so_tk=? ";
                    params.add(new Parameter(f.getSotk(), true));
                }
                if (f.getTu_ngay() != null && !"".equals(f.getTu_ngay())) {
                    whereClause +=
                            "and trunc(a.ngay_du_cuoi)= to_date(?" + ",'DD/MM/YYYY') ";
                    params.add(new Parameter(f.getTu_ngay(), true));
                }
                if (f.getLoai_tien() != null && !"".equals(f.getLoai_tien())) {
                    whereClause += "a.loai_tien_du_dau='?' ";
                    params.add(new Parameter(f.getLoai_tien(), true));
                } else {
                    whereClause +=
                            "and trunc(a.ngay_du_cuoi)= trunc(sysdate) ";
                }
                int result = 0;
                SKeTKVO skVO = null;
                try {
                    skVO = skDAO.getSK(whereClause, params);
                    result = skDAO.updateTT_Chot_So(whereClause, params);
                } catch (Exception e) {
                    executeAction(mapping, form, request, response);
                    throw TTSPException.createException("TTSP-9999",
                                                        "L&#7895;i h&#7879; th&#7889;ng : - Kh&#244;ng t&#236;m th&#7845;y b&#7843;n sao k&#234; n&#224;o");
                }
                if (result > 0) {
                    conn.commit();
                    if (skVO != null) {
                        BeanUtils.copyProperties(f, skVO);
                        String page = f.getPageNumber();
                        if (page == null) {
                            page = "1";
                        }
                        // khai bao cac bien phan trang
                        Integer currentPage = new Integer(page);
                        Integer numberRowOnPage = new Integer(15);
                        Integer totalCount[] = new Integer[1];
                        strWhere = "a.sao_ke_id=? ";
                        vParam = new Vector();
                        vParam.add(new Parameter(skVO.getId(), true));
                        lstSKe =
                                (ArrayList<SKeTKChiTietVO>)skDAO.getSKChiTietListWithPading(skVO,
                                                                                            currentPage,
                                                                                            numberRowOnPage,
                                                                                            totalCount);

                        request.setAttribute("lstSKe",
                                             lstSKe != null ? lstSKe :
                                             new ArrayList<SKeTKVO>());
                        PagingBean pagingBean = new PagingBean();
                        pagingBean.setCurrentPage(currentPage);
                        pagingBean.setNumberOfRow(totalCount[0].intValue());
                        pagingBean.setRowOnPage(numberRowOnPage);
                        request.setAttribute("PAGE_KEY", pagingBean);
                    } else {
                        request.setAttribute("lstSKe",
                                             lstSKe != null ? lstSKe :
                                             new ArrayList<SKeTKVO>());
                        PagingBean pagingBean = new PagingBean();
                        request.setAttribute("PAGE_KEY", pagingBean);
                    }
                    String strWhereclause = "";
                    TKNHKBacDAO tknhkbDAO = null;
                    DMKBacDAO kbDAO = null;
                    DMNHangDAO dmnhDAO = null;
                    //                DMNHangVO dmnhVO = null;
                    DMKBacVO kbVO = null;
                    ArrayList<DMKBacVO> lstDSKBTinh = null;
                    ArrayList<DMKBacVO> lstDSKBHuyen = null;
                    ArrayList<DMNHangVO> lstDSNHang = null;
                    ArrayList<TKNHKBacVO> lstTKNHang = null;
                    HttpSession session = request.getSession();
                    SKeTKForm frm = (SKeTKForm)form;
                    //              Vector vParam = null;
                    String strMa_kb =
                        (String)session.getAttribute(AppConstants.APP_KB_CODE_SESSION);
                    kbDAO = new DMKBacDAO(conn);
                    tknhkbDAO = new TKNHKBacDAO(conn);
                    dmnhDAO = new DMNHangDAO(conn);
                    if (strMa_kb.equals(MAKBNN) || strMa_kb.equals(MASGD) ||
                        strMa_kb.equals(MATTTT)) {
                        // hien thi tat ca kho bac tinh
                        strWhereclause = " a.cap=? or a.ma=? ";
                        vParam = new Vector();
                        vParam.add(new Parameter(CAP_TINH, true));
                        vParam.add(new Parameter(MASGD, true));
                        lstDSKBTinh =
                                (ArrayList<DMKBacVO>)kbDAO.getDMKBList(strWhereclause,
                                                                       vParam);
                        // hien thi mac dinh kho bac huyen la sgd

                        vParam = new Vector();
                        lstDSKBHuyen = new ArrayList<DMKBacVO>();
                        if (frm.getNhkb_tinh() != null) {
                            strWhereclause = "a.ma_cha=? or ma=? ";
                            vParam = new Vector();
                            vParam.add(new Parameter(frm.getNhkb_tinh(),
                                                     true));
                            vParam.add(new Parameter(frm.getNhkb_tinh(),
                                                     true));
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
                        if (frm.getNhkb_huyen() != null) {
                            vParam.add(new Parameter(frm.getNhkb_huyen(),
                                                     true));
                        } else {
                            vParam.add(new Parameter(kbVO.getMa(), true));
                        }
                        //HungBM - 20170626 - BEGIN
                        lstDSNHang =
                                (ArrayList<DMNHangVO>)dmnhDAO.getListNHSaoKeTK(strWhereclause,
                                                                               vParam);
                        //HungBM - 20170626 - END
                        if (lstDSNHang.size() > 0) {
                            // hien thi so tai khoan
                            strWhereclause =
                                    " and a.trang_thai=? and a.kb_id=? and b.ma_nh=?";
                            String strTrang_Thai = "01";
                            vParam = new Vector();
                            vParam.add(new Parameter(strTrang_Thai, true));
                            // kiem tra xem dang select len ngan hang nao
                            DMNHangVO voNHSelected = new DMNHangVO();
                            for (DMNHangVO voDMNH : lstDSNHang) {
                                if (voDMNH.getMa_nh().equalsIgnoreCase(frm.getManh())) {
                                    voNHSelected = voDMNH;
                                    break;
                                }
                            }
                            vParam.add(new Parameter(voNHSelected.getId(),
                                                     true));
                            vParam.add(new Parameter(voNHSelected.getMa_nh(),
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
                        // Neu kho bac la kho bac tinh thi set mac dinh cap tinh la kho bac so tai
                        if (kbVO.equals(CAP_TINH)) {
                            lstDSKBTinh = new ArrayList<DMKBacVO>();
                            lstDSKBTinh.add(kbVO);
                        }
                        // Neu la kho bac cap huyen thi set mac dinh cap huyen la kho bac so tai dong thoi xac dinh kho bac ma kho bac huyen truc thuoc
                        else {
                            lstDSKBHuyen = new ArrayList<DMKBacVO>();
                            lstDSKBHuyen.add(kbVO);
                            DMKBacVO kbTinhVO = new DMKBacVO();
                            strWhereclause = " AND a.ma = ? and a.cap=?";
                            vParam = new Vector();
                            vParam.add(new Parameter(kbVO.getMa_cha(), true));
                            vParam.add(new Parameter(CAP_TINH, true));
                            kbTinhVO = kbDAO.getDMKB(strWhereclause, vParam);
                            lstDSKBTinh = new ArrayList<DMKBacVO>();
                            lstDSKBTinh.add(kbTinhVO);

                            // hien thi ngan hang cua sgd
                            strWhereclause =
                                    " and b.ma=? and a.trang_thai = '01'";
                            vParam = new Vector();
                            vParam.add(new Parameter(kbVO.getMa(), true));
                            //HungBM - 20170626 - BEGIN
                            lstDSNHang =
                                    (ArrayList<DMNHangVO>)dmnhDAO.getListNHSaoKeTK(strWhereclause,
                                                                                   vParam);
                            //HungBM - 20170626 - END
                            // hien thi so tai khoan
                            strWhereclause =
                                    " and a.trang_thai=? and a.kb_id=? and b.ma_nh=?";
                            String strTrang_Thai = "01";
                            vParam = new Vector();
                            vParam.add(new Parameter(strTrang_Thai, true));
                            vParam.add(new Parameter(kbVO.getId(), true));
                            vParam.add(new Parameter(f.getManh(), true));
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
            } else {
                throw TTSPException.createException("TTSP-9999",
                                                    "Kh&#244;ng t&#236;m th&#7845;y kho b&#7841;c!");
            }
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }
}