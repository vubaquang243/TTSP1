package com.seatech.ttsp.ltt.action;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.FontUtil;
import com.seatech.framework.utils.StringUtil;
import com.seatech.ttsp.dmdvsdns.DMDonViSdnsVO;
import com.seatech.ttsp.dmnh.DMNHangDAO;
import com.seatech.ttsp.dmnh.DMNHangVO;
import com.seatech.ttsp.ltt.LTTCTietDAO;
import com.seatech.ttsp.ltt.LTTCTietVO;
import com.seatech.ttsp.ltt.LTTDAO;
import com.seatech.ttsp.ltt.LTTVO;
import com.seatech.ttsp.ltt.TKeVO;
import com.seatech.ttsp.user.UserDAO;
import com.seatech.ttsp.user.UserVO;

import java.io.PrintWriter;

import java.lang.reflect.Type;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class LoadLTTDiAction extends AppAction {
    private static String SUCCESS = "success";

    protected ActionForward executeAction(ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
        Connection conn = null;

        try {
            conn = getConnection(request);
            //Lay lenh thanh toan
            String strSoYCauTT = request.getParameter("so_yctt");
            LTTDAO lttDao = new LTTDAO(conn);
            String whereClause = "t.so_yctt like ?";
            Vector v_param = new Vector();
            Parameter param = new Parameter(strSoYCauTT, true);
            v_param.add(param);
            LTTVO lttVo = lttDao.getLTTDi(whereClause, v_param);

            DMNHangDAO dmnhDAO = new DMNHangDAO(conn);
            String strKBacID = "";
            DMNHangVO dmnhVO = null;
            whereClause = " a.id=? ";
            Vector params = new Vector();
            param = new Parameter(lttVo.getNhkb_nhan().toString(), true);
            params.add(param);
            dmnhVO = dmnhDAO.getDMNH(whereClause, params);

            Collection listDMNH = new ArrayList();
            if (dmnhVO.getId() != null) {
                strKBacID = dmnhVO.getId().toString();
            }
            //Lay ra danh sach cac ngan hang ma kho bac mo tai khoan
            String strWhere = " and c.id = ? ";
            Vector vtParam = new Vector();
            vtParam.add(new Parameter(strKBacID, true));


            listDMNH = dmnhDAO.getDMNHListByKBId(strWhere, vtParam);
            request.setAttribute("colDMNH", listDMNH);

            //Gan ma_nhkb_chuyen_cm, ma_nhkb_nhan_cm vao lttVO
            if (request.getSession().getAttribute(AppConstants.APP_NHKB_CODE_SESSION) !=
                null)
                lttVo.setMa_nhkb_chuyen_cm(request.getSession().getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString());
            lttVo.setMa_nhkb_chuyen(lttVo.getMa_nhkb_chuyen_cm());
            lttVo.setMa_nhkb_nhan_cm(dmnhVO.getMa_nh());
            lttVo.setMa_nhkb_nhan(dmnhVO.getMa_nh());

            //Lay ten Nguoi Nhan neu co
            UserVO userVO = null;
            if (lttVo.getTtv_nhan() != null && lttVo.getTtv_nhan() != 0) {
                UserDAO userDao = new UserDAO(conn);
                whereClause = " a.id=? ";
                params = new Vector();
                param = new Parameter(lttVo.getTtv_nhan(), true);
                params.add(param);
                userVO = userDao.getUser(whereClause, params);
            }

            String strRootElementClose = "</ltt>";
            //convert lenh thanh toan to xml
            StringBuffer strXmlLTT = new StringBuffer();
            //strXmlLTT.append("<?xml version = '1.0' encoding = 'UTF-8'?>");
            strXmlLTT.append(populateBeanToXml(lttVo,
                                               "com.seatech.ttsp.ltt.LTTVO",
                                               "ltt").replace(strRootElementClose,
                                                              "").replace("<id>",
                                                                          "<ltt_di_id>").replace("</id>",
                                                                                                 "</ltt_di_id>"));

            if (request.getSession().getAttribute(AppConstants.APP_KB_NAME_SESSION) !=
                null)
                strXmlLTT.append("<ten__nhkb__chuyen__cm>" +
                                 request.getSession().getAttribute(AppConstants.APP_KB_NAME_SESSION).toString() +
                                 "</ten__nhkb__chuyen__cm>");

            strXmlLTT.append("<ten__nhkb__nhan__cm>" + dmnhVO.getTen() +
                             "</ten__nhkb__nhan__cm>");
            if (userVO != null && userVO.getTen_nsd() != null &&
                !"".equalsIgnoreCase(userVO.getTen_nsd())) {
                strXmlLTT.append("<ten__nsd>" + userVO.getTen_nsd() +
                                 "</ten__nsd>");
            }
            //Lay chi tiet thanh toan
            whereClause = "t.ltt_id = ?";
            //            whereClause = "t.ltt_id = ? m.tinhtrang='1' and k.tinhtrang='1' and n.tinhtrang='1' and c.tinhtrang='1' " +
            //                "and nkt.tinhtrang='1' and ndkt.tinh_trang='1' and dbhc.tinhtrang='1' and ctmt.tinhtrang='1' and dp.tinhtrang='1'";
            v_param = new Vector();
            param = new Parameter(lttVo.getId(), true);
            v_param.add(param);

            LTTCTietDAO lttCTietDAO = new LTTCTietDAO(conn);
            Collection collLttDtl =
                lttCTietDAO.getLTTDiCTietList(whereClause, v_param);
            Iterator iter = collLttDtl.iterator();
            while (iter.hasNext()) {
                LTTCTietVO lttCTietVO = (LTTCTietVO)iter.next();
                strXmlLTT.append(populateBeanToXml(lttCTietVO,
                                                   "com.seatech.ttsp.ltt.LTTCTietVO",
                                                   "detail").replace("<id>",
                                                                     "<id_ctiet>").replace("</id>",
                                                                                           "</id_ctiet>"));
            }
            strXmlLTT.append(strRootElementClose);

            response.setContentType("text/xml");
            PrintWriter out = response.getWriter();
            //out.println(FontUtil.unicodeEscape(strXmlLTT.toString()));
            String strXMLLTTDi = "";
            strXMLLTTDi = strXmlLTT.toString();
            strXMLLTTDi =
                    strXMLLTTDi.replace("<ltt_di_id>", "<id>").replace("</ltt_di_id>",
                                                                       "</id>");
            out.println(strXMLLTTDi);
            out.flush();
            out.close();

            request.setAttribute("lastAction", "LoadLTTDi");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        String strJson = "";
        String strTongSoMon = "";
        String strTongSoTien = "";
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            String nt_id = request.getParameter("nt_id");
            String type = request.getParameter("type");
            if ("getTongMonTongTienAction.do".equalsIgnoreCase(type)) {
                String whereClauseTongSo =
                    "AND a.nhkb_chuyen =? AND a.nt_id = " + nt_id + " ";
                String strNHKB_Tong =
                    session.getAttribute(AppConstants.APP_NHKB_ID_SESSION) !=
                    null ?
                    session.getAttribute(AppConstants.APP_NHKB_ID_SESSION).toString() :
                    "";
                Vector param_Tong = new Vector();

                param_Tong.add(new Parameter(strNHKB_Tong, true));
                // ends
                LTTDAO lttDAO = new LTTDAO(conn);
                strTongSoMon =
                        lttDAO.getTongSoMon(whereClauseTongSo, param_Tong);
                strTongSoTien =
                        lttDAO.getTongSoTien(whereClauseTongSo, param_Tong);

            } else {
                String whereClauseTongSo = "AND a.nhkb_nhan =? ";
                String strNHKB_Tong =
                    session.getAttribute(AppConstants.APP_NHKB_ID_SESSION) !=
                    null ?
                    session.getAttribute(AppConstants.APP_NHKB_ID_SESSION).toString() :
                    "";
                Vector param_Tong = new Vector();
                String strDate = StringUtil.getPreviousNextDate(-1);
                param_Tong.add(new Parameter(strDate, true));
                param_Tong.add(new Parameter(strNHKB_Tong, true));
                LTTDAO lttDAO = new LTTDAO(conn);

                strTongSoMon =
                        lttDAO.getTongSoMonLTTDen(whereClauseTongSo, param_Tong);
                strTongSoTien =
                        lttDAO.getTongSoTienLTTDen(whereClauseTongSo, param_Tong);
            }

            TKeVO vo = new TKeVO();
            vo.setTong_so_mon(strTongSoMon);
            vo.setTong_so_tien(strTongSoTien);

            Type listType = new TypeToken<TKeVO>() {
            }.getType();
            strJson = new Gson().toJson(vo, listType);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn);
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(strJson.toString());
            out.flush();
            out.close();
        }
        if (!response.isCommitted())
            return mapping.findForward(SUCCESS);
        else
            return null;
    }
}
