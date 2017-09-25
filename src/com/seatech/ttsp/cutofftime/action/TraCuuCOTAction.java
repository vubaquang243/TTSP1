package com.seatech.ttsp.cutofftime.action;


import com.google.gson.JsonObject;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.TTSPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.ttsp.cutoftime.TSoCutOfTimeDAO;
import com.seatech.ttsp.cutoftime.TSoCutOfTimeVO;
import com.seatech.ttsp.cutoftime.form.TSoCutOfTimeForm;
import com.seatech.ttsp.dchieu.DChieu1DAO;

import com.seatech.ttsp.dmnh.DMNHangDAO;

import java.io.PrintWriter;

import java.sql.Connection;

import java.sql.ParameterMetaData;

import java.util.Collection;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class TraCuuCOTAction extends AppAction {
    public TraCuuCOTAction() {
        super();
    }

    /**
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        Collection lstKBTinh = null;
        Collection lstDmNHHO = null;
        String whereClause = "";
        Vector params = new Vector();
        Collection listCOT = null;
        try {
            TSoCutOfTimeForm tscotForm = (TSoCutOfTimeForm)form;
            int phantrang = AppConstants.APP_NUMBER_ROW_ON_PAGE;
            // khai bao bien phan trang.
            String page = tscotForm.getPageNumber();
            if (page == null)
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[1];
            HttpSession session = request.getSession();
            
            if (conn == null || conn.isClosed())
                conn = getConnection(request);
            if (tscotForm.getTu_ngay_gd() == null &&
                tscotForm.getDen_ngay_gd() == null) {
                String today =
                    StringUtil.DateToString(new Date(), "dd/MM/yyyy");
                tscotForm.setTu_ngay_gd(today);
                tscotForm.setDen_ngay_gd(today);
            }
            if (tscotForm.getTu_ngay_gd() != null &&
                !tscotForm.getTu_ngay_gd().equals("")) {
                whereClause += " AND a.ngay_gd >=?";
                params.add(new DateParameter(StringUtil.StringToDate(tscotForm.getTu_ngay_gd(),
                                                                     "dd/MM/yyyy"),
                                             true));

            }
            if (tscotForm.getDen_ngay_gd() != null &&
                !tscotForm.getDen_ngay_gd().equals("")) {
                whereClause += " AND a.ngay_gd <=?";
                params.add(new DateParameter(StringUtil.StringToDate(tscotForm.getDen_ngay_gd(),
                                                                     "dd/MM/yyyy"),
                                             true));
            }
            if (tscotForm.getMa_nh_kb() != null &&
                !tscotForm.getMa_nh_kb().equals("")) {
                whereClause += " AND b.shkb =?";
                params.add(new Parameter(tscotForm.getMa_nh_kb(), true));
            }
            if (tscotForm.getMa_nh() != null &&
                !tscotForm.getMa_nh().equals("")) {
                whereClause += " AND a.ma_nh =?";
                params.add(new Parameter(tscotForm.getMa_nh(), true));
            }

            TSoCutOfTimeDAO tscotDAO = new TSoCutOfTimeDAO(conn);
            listCOT =
                    tscotDAO.getTSoCOTList_ptrang(whereClause, params, Integer.parseInt(page),
                                                  numberRowOnPage, totalCount);
            DChieu1DAO dao = new DChieu1DAO(conn);
            // danh sach kb tinh
            String kb_code =
                session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
            if ("0001".equals(kb_code) || "0002".equals(kb_code)) {
                whereClause = " ";
                lstKBTinh = dao.getDMucKB_cha(whereClause, null);
            } else {
                String strWhere = " AND a.ma='0003' ";
                lstKBTinh = dao.getDMucKB_cha(strWhere, null);
            }
            //ManhNV them
            DMNHangDAO dmnhDAO = new DMNHangDAO(conn);
            lstDmNHHO = dmnhDAO.getDMNHHOList("and ma_dv <> '701'", null);
            //-----
            if (listCOT == null) {
                PagingBean pagingBean = new PagingBean();
                pagingBean.setCurrentPage(currentPage);
                pagingBean.setNumberOfRow(0);
                pagingBean.setRowOnPage(numberRowOnPage);
                request.setAttribute("PAGE_KEY", pagingBean);

            } else {
                PagingBean pagingBean = new PagingBean();
                pagingBean.setCurrentPage(currentPage);
                pagingBean.setNumberOfRow(totalCount[0].intValue());
                pagingBean.setRowOnPage(numberRowOnPage);
                request.setAttribute("PAGE_KEY", pagingBean);

            }
        } catch (Exception e) {
//            e.printStackTrace();
        } finally {
            request.setAttribute("lstCOT", listCOT);
            request.setAttribute("lstKBTinh", lstKBTinh);
            request.setAttribute("lstDmNHHO", lstDmNHHO);
            close(conn);

        }
        return mapping.findForward(AppConstants.SUCCESS);

    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            TSoCutOfTimeForm tscotForm = (TSoCutOfTimeForm)form;
            if (conn == null || conn.isClosed())
                conn = getConnection(request);
            TSoCutOfTimeDAO tscotDAO = new TSoCutOfTimeDAO(conn);
            String whereClause = " a.id=?";
            Vector params = new Vector();
            params.add(new Parameter(tscotForm.getId(), true));
            BeanUtils.copyProperties(tscotForm,
                                     tscotDAO.getTSoCTO(whereClause, params));

        } catch (Exception e) {
            throw TTSPException.createException("list()", e.getMessage());
        } finally {
            close(conn);
        }
        return mapping.findForward(AppConstants.SUCCESS);
    }

    public ActionForward updateExc(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        Connection conn = null;
        response.setContentType(AppConstants.CONTENT_TYPE_JSON);
        JsonObject jsonObj = null;
        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession();

            String strCurDate = StringUtil.DateToString(new Date(), "dd-MM-yyyy");
            Date dateCurrent = new Date();

            TSoCutOfTimeForm tscotForm = (TSoCutOfTimeForm)form;
            if (conn == null || conn.isClosed())
                conn = getConnection(request);

            saveVisitLog(conn, session, "QLY_COT.TRACUU_EXC", "Cap nhat COT");

            TSoCutOfTimeDAO tscotDAO = new TSoCutOfTimeDAO(conn);
            TSoCutOfTimeVO vo = new TSoCutOfTimeVO();
            BeanUtils.copyProperties(vo, tscotForm);
            if (tscotForm.getId() != null && !tscotForm.getId().equals("")) {
                String hour = tscotForm.getCut_of_time().replace(":", "");
                //Kiem tra qua gio noi COT cho phep
                Vector params = new Vector();
                params.add(new Parameter(vo.getId(), true));
                TSoCutOfTimeVO voCheck = tscotDAO.getTSoCTO(" a.id = ? AND "+hour+ " > (select to_char(SYSDATE + 10/1440,'hh24mi') from dual)", params);
                if(voCheck != null){
                    Date dateCOT = StringUtil.StringToDate(strCurDate + voCheck.getCut_of_time().trim(), "dd-MM-yyyyHH:mm");
                    if (dateCOT.compareTo(dateCurrent) < 0) {
                        throw new Exception("&#272;&#227; qu&#225; gi&#7901; COT, h&#7879; th&#7889;ng kh&#244;ng cho ph&#233;p n&#7899;i gi&#7901;.");
                    }
                    //--
                    tscotDAO.update(vo);
                }else{
                    throw new Exception("Gi\u1EDD m\u1EDBi ph\u1EA3i l\u1EDBn h\u01A1n gi\u1EDD hi\u1EC7n t\u1EA1i c\u1EE7a m\u00E1y ch\u1EE7 c\u01A1 s\u1EDF d\u1EEF li\u1EC7u \u00EDt nh\u1EA5t 10 ph\u00FAt");
                }
            } else {
                String ma_nh = tscotForm.getMa_3so_nh();
                if (ma_nh == null || "".equals(ma_nh)) {
                    throw new Exception("Chua chon he thong ngan hang");
                }
                //Kiem tra qua gio noi COT cho phep
                Date dateCOT = tscotDAO.getMaxCOT(" and ngay_gd like sysdate and ma_nh like '__" + tscotForm.getMa_3so_nh() + 
                    "%' AND REPLACE(cut_of_time,':') > (select to_char(SYSDATE + 10/1440,'hh24mi') from dual) " +
                    " AND REPLACE('"+vo.getCut_of_time()+"',':') > (SELECT  TO_CHAR (SYSDATE + 10 / 1440, 'hh24mi') FROM DUAL)", null);
                if (dateCOT == null){
                    throw new Exception("Gi\u1EDD m\u1EDBi ph\u1EA3i l\u1EDBn h\u01A1n gi\u1EDD hi\u1EC7n t\u1EA1i c\u1EE7a m\u00E1y ch\u1EE7 c\u01A1 s\u1EDF d\u1EEF li\u1EC7u \u00EDt nh\u1EA5t 10 ph\u00FAt");
                }
                if (dateCOT.compareTo(dateCurrent) < 0) {
                    throw new Exception("&#272;&#227; qu&#225; gi&#7901; COT, h&#7879; th&#7889;ng kh&#244;ng cho ph&#233;p n&#7899;i gi&#7901;.");
                }
                //---
                String where = " WHERE ngay_gd =trunc(sysdate) and ma_nh like '__" + tscotForm.getMa_3so_nh() + "%'";
                tscotDAO.updateCondition(vo, where, null);
            }

            conn.commit();
            jsonObj = new JsonObject();
            jsonObj.addProperty(AppConstants.SUCCESS, AppConstants.SUCCESS);
            jsonObj.addProperty(AppConstants.FAILURE, "");
            out.println(jsonObj.toString());
        } catch (Exception e) {
//            e.printStackTrace();
            jsonObj = new JsonObject();
            jsonObj.addProperty(AppConstants.SUCCESS, AppConstants.FAILURE);
            jsonObj.addProperty(AppConstants.FAILURE, e.getMessage());
            out.println(jsonObj.toString());
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

    public static void main(String[] a) {
        try {
            AppDAO d = new AppDAO();
            Connection conn = d.getConnectionTest();
            TSoCutOfTimeDAO tscotDAO = new TSoCutOfTimeDAO(conn);
            Date dateCurrent = new Date();
            System.out.println(StringUtil.DateToString(dateCurrent, "dd/MM/yyyy HH:mm:ss"));
            Date dateCOT =
                tscotDAO.getMinCOT(" and ngay_gd like sysdate and ma_nh like '__" +
                                    "201%'", null);
          System.out.println(StringUtil.DateToString(dateCOT, "dd/MM/yyyy HH:mm:ss"));
            if (dateCOT.compareTo(dateCurrent) < 0) {
                
                throw new Exception("&#272;&#227; qu&#225; gi&#7901; COT, h&#7879; th&#7889;ng kh&#244;ng cho ph&#233;p n&#7899;i gi&#7901;.");
            }
        } catch (Exception e) {
        }
    }
}

