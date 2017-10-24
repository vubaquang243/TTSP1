package com.seatech.ttsp.ttthanhtoan.action;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.FontUtil;
import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.proxy.giaodien.Send071;
import com.seatech.ttsp.ttthanhtoan.DMucNHVO;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanDAO;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanVO;
import com.seatech.ttsp.ttthanhtoan.form.TTThanhToanForm;

import java.io.InputStream;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
public class TTThanhToanAction extends AppAction {
	//khoi tao form
    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {

        if (!checkPermissionOnFunction(request, "SYS.TTRANG_TTOAN")) {
            return mapping.findForward("errorQuyen");
        }
        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            TTThanhToanForm TTForm = (TTThanhToanForm)form;
            TTThanhToanDAO dao = new TTThanhToanDAO(conn);

            String kb_chuyen = "01701001";
            //                session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();

            String strUD = "";
            Collection col = new ArrayList();
            List dmucNH = null;

            dmucNH = (List)dao.getDMucNH(null,null);
            request.setAttribute("dmucNH", dmucNH);

            if (null != TTForm.getNhkb_nhan() &&
                !"".equals(TTForm.getNhkb_nhan())) {
                strUD +=
                        " and nhkb_chuyen ='" + kb_chuyen + "'" + " and nhkb_nhan ='" +
                        TTForm.getNhkb_nhan() + "'";
                if (null == TTForm.getNgay_tao() ||
                    "".equals(TTForm.getNgay_tao())) {
                    strUD += " and to_date(ngay_tao) = to_date(sysdate)";
                } else if (null != TTForm.getNgay_tao() ||
                           !"".equals(TTForm.getNgay_tao())) {
                    strUD +=
                            " and to_char(ngay_tao,'DD/mm/YYYY') ='" + TTForm.getNgay_tao().trim() +
                            "'";
                }
                col = dao.getKQTToan(strUD, null);
                request.setAttribute("lstUD", col);

                if (col.isEmpty()) {
                    request.setAttribute("status",
                                         "ttrangttoan.ttrangttoan.warning.ketqua.null");
                }
            }
            resetToken(request);
            saveToken(request);
        } catch (Exception e) {
            throw e;

        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

    public ActionForward addExc(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {

        if (!checkPermissionOnFunction(request, "SYS.TTRANG_TTOAN.YCAU")) {
            return mapping.findForward("errorQuyen");
        }
        Connection conn = null;
        try {
            if (isTokenValid(request)) {
                conn = getConnection(request);
                HttpSession session = request.getSession();
                String strIDKBChuyen = "01701001";
                //                    session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
                Long nUserID =
                    new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
                String strUserName =
                    session.getAttribute(AppConstants.APP_USER_NAME_SESSION).toString();

                TTThanhToanForm TTForm = (TTThanhToanForm)form;
                TTThanhToanDAO dao = new TTThanhToanDAO(conn);
                TTThanhToanVO vo = new TTThanhToanVO();
                TTSPUtils ttspUtils = new TTSPUtils(conn);
                DMucNHVO dmucvo = null;

                vo.setNguoi_tao(nUserID); // nguoi tao
                vo.setNhkb_chuyen(strIDKBChuyen); //NHKB_CHUYEN
                vo.setNhkb_nhan(TTForm.getNhkb_nhan()); // NHKB nhan
                String whereClause =
                    " and a.ma_nh='" + TTForm.getNhkb_nhan() + "'";

                dmucvo = dao.getMa_rcv(whereClause, null);

                vo.setId(ttspUtils.getMsgTTTTID(dmucvo.getMa_dv(),
                                                session.getAttribute

                            (AppConstants.APP_KB_ID_SESSION).toString())); // MSG_ID
                Send071 send = new Send071(conn);
                String strMsgID =
                    send.sendMessage(vo.getId(), vo.getNhkb_nhan(),
                                     vo.getNhkb_chuyen(), strUserName, strUserName);
                vo.setMsg_id(strMsgID);
                if (strMsgID != null && !"".equals(strMsgID)) {
                    dao.insert(vo);
                    conn.commit();
                }else{
                  throw new Exception("G&#7917;i ng&#226;n h&#224;ng kh&#244;ng th&#224;nh c&#244;ng");
                }

            }
            resetToken(request);
            saveToken(request);
        } catch (Exception e) {
            throw e;

        } finally {
            close(conn);
        }

        return mapping.findForward("success");
    }

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
			
        if (!checkPermissionOnFunction(request, "SYS.TTRANG_TTOAN")) {
            return mapping.findForward("errorQuyen");
        }
        Connection conn = null;


        try {
            int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
            conn = getConnection();
            TTThanhToanForm TTForm = (TTThanhToanForm)form;
            TTThanhToanDAO dao = new TTThanhToanDAO(conn);

            String page = TTForm.getPageNumber();
            if (page == null)
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[1];

            String strWhere = "";

            List dmuckb_cha = null;

            strWhere += " and cap = 5";
            dmuckb_cha = (List)dao.getDMucKB_cha(strWhere, null);
            request.setAttribute("dmuckb_tinh", dmuckb_cha);

            String strUD = "";
            Collection col = new ArrayList();

            //            if (TTForm.getId() != null && !"".equals(TTForm.getId())) {
            //                strUD += " and a.msg_refid ='" + TTForm.getId() + "'";
            //            }
            if (TTForm.getNhkb_nhan_cn() != null &&
                !"".equals(TTForm.getNhkb_nhan_cn())) {
                strUD +=
                        " and a.ma_kb in (select ma_nh from sp_dm_manh_shkb where shkb in (select ma from sp_dm_htkb where id = " +
                        TTForm.getNhkb_nhan_cn() + " ))";
            } else if (TTForm.getNhkb_nhan() != null &&
                       !"".equals(TTForm.getNhkb_nhan())) {
                strUD +=
                        " and a.ma_kb in (select ma_nh from sp_dm_manh_shkb where shkb in (select ma from sp_dm_htkb where id_cha = " +
                        TTForm.getNhkb_nhan() + " ))";
            }

            col =
dao.getTHopTToan_ptrang(strUD, null, currentPage, numberRowOnPage, totalCount);

            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow(totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);
            request.setAttribute("lstUD", col);

            if (col.isEmpty()) {
                request.setAttribute("status",
                                     "ttrangttoan.ttrangttoan.warning.ketqua.null");
            }
        } catch (Exception e) {
            throw e;

        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

    protected ActionForward executeAction(ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {

        Connection conn = null;
        try {
            String strMaKB;
            String strJSON;
            String strWhereClause = " (1=1) ";
            conn = getConnection();

            strMaKB = request.getParameter("kb_id").toString();

            DMKBacDAO dmdao = new DMKBacDAO(conn);

            strWhereClause += " and id_cha = " + strMaKB;

            Collection col = null;
            col = dmdao.getDMKBList(strWhereClause, null);

            JSONObject jsonRes = new JSONObject();

            response.setHeader("X-JSON", jsonRes.toString());
            java.lang.reflect.Type listType =
                new TypeToken<Collection<DMKBacVO>>() {
            }.getType();
            strJSON = new Gson().toJson(col, listType);
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(strJSON.toString());
            out.flush();
            out.close();
        } catch (Exception e) {
            JSONObject jsonRes = new JSONObject();
            jsonRes.put("executeError",
                        FontUtil.unicodeEscape("Lỗi: " + e.getMessage()));

            response.setHeader("X-JSON", jsonRes.toString());
        } finally {
            close(conn);

        }

        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

    public static final String REPORT_DIRECTORY = "/report";
    public static final String strFontTimeRoman = "/font/times.ttf";
    public static final String fileName = "/TongHopThanhToan";

    public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {

        Connection conn = null;
        StringBuffer sbSubHTML = new StringBuffer();
        InputStream reportStream = null;
        try {
            conn = getConnection();
            TTThanhToanForm f = (TTThanhToanForm)form;
            // là ng dùng dang su dung   f.getG_nsd_id()
            //Khai bao bien find.
            String strid = null;
            String strkb_nhan = null;
            String strkb_nhan_cn = null;
            String strid_kb = null;

            if (null != f) {
                strid = f.getId();
                strkb_nhan = f.getNhkb_nhan();
                strkb_nhan_cn = f.getNhkb_nhan_cn();
                strid_kb = f.getTen_kb();
                strid = f.getId();
            }

            String strWhere = "";

            //      if (f.getId() != null && !"".equals(f.getId())) {
            //          strWhere += " and a.msg_refid ='" + f.getId() + "'";
            //        sbSubHTML.append("<input type=\"hidden\" name=\"msg_refid\" value=\"" + strid + "\" id=\"strid\"></input>");
            //      }
            if (f.getNhkb_nhan_cn() != null &&
                !"".equals(f.getNhkb_nhan_cn())) {
                strWhere +=
                        " and a.ma_kb in ( SELECT	ma_nh FROM	sp_dm_manh_shkb c, sp_dm_htkb d WHERE	c.shkb = d.ma and id =" +
                        f.getNhkb_nhan_cn() + " )";
                sbSubHTML.append("<input type=\"hidden\" name=\"nhkb_nhan_cn\" value=\"" +
                                 strkb_nhan_cn +
                                 "\" id=\"strkb_nhan_cn\"></input>");
            } else if (f.getNhkb_nhan() != null &&
                       !"".equals(f.getNhkb_nhan())) {
                strWhere +=
                        " and a.ma_kb in ( SELECT	ma_nh FROM	sp_dm_manh_shkb c, sp_dm_htkb d WHERE	c.shkb = d.ma and id_cha = " +
                        f.getNhkb_nhan() + " )";
                sbSubHTML.append("<input type=\"hidden\" name=\"nhkb_nhan\" value=\"" +
                                 strkb_nhan + "\" id=\"strkb_nhan\"></input>");
            }
            TTThanhToanDAO ttoanDAO = new TTThanhToanDAO(conn);
            ResultSet lstTToan = null;

            lstTToan = ttoanDAO.getTHopTToanRS(strWhere, null);

            if (lstTToan == null) {
                request.setAttribute("status",
                                     "ktvtabmis.listktvtabmis.warning.nhap.khongdung");
            } else {
                JasperPrint jasperPrint = null;
                HashMap parameterMap = null;
                ReportUtility rpUtilites = new ReportUtility();
                reportStream =
                        getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY +
                                                                                                fileName +
                                                                                                ".jasper");
                JRDataSource jrDS = new JRResultSetDataSource(lstTToan);
                jasperPrint =
                        JasperFillManager.fillReport(reportStream, parameterMap,
                                                     jrDS);

                String strTypePrintAction =
                    request.getParameter(AppConstants.REQUEST_ACTION) == null ?
                    "" :
                    request.getParameter(AppConstants.REQUEST_ACTION).toString();
                String strActionName = "inTTToan.do";
                String strParameter = "";
                String strPathFont =
                    getServlet().getServletContext().getContextPath() +
                    REPORT_DIRECTORY + strFontTimeRoman;

                rpUtilites.exportReport(jasperPrint, strTypePrintAction,
                                        response, fileName, strPathFont,
                                        strActionName, sbSubHTML.toString(),
                                        strParameter);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                reportStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            close(conn);
        }


        return mapping.findForward("success");
    }
}

