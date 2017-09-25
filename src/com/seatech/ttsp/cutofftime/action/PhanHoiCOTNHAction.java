package com.seatech.ttsp.cutofftime.action;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.TTSPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.cutofftime.CutOffTimeDAO;
import com.seatech.ttsp.cutofftime.CutOffTimeVO;
import com.seatech.ttsp.cutofftime.form.CutOffTimeForm;
import com.seatech.ttsp.cutoftime.TSoCutOfTimeDAO;
import com.seatech.ttsp.cutoftime.TSoCutOfTimeVO;
//import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.proxy.giaodien.Send299;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanDAO;

import java.io.PrintWriter;

import java.lang.reflect.Type;

import java.sql.Connection;

import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class PhanHoiCOTNHAction extends AppAction {
    private static String SGD = "0003";
    private static String CAP_TINH = "5";

    public PhanHoiCOTNHAction() {
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
//        if (!checkPermissionOnFunction(request, "QLY_COT.XYLY_REPLY")) {
//            return mapping.findForward("errorQuyen");
//        }
        Collection colCOTDi = null;
        String whereClause = "";
        Vector params = null;
        Connection conn = null;
        String action = "";
        String strKBHuyen = null;
        String strListRole = "";
        //note
        Collection lstKBHuyen = null;
        Collection colDMNH = null;
        Collection lstKBTinh = null;
        try {
            CutOffTimeForm cotForm = (CutOffTimeForm)form;
            HttpSession session = request.getSession();
            action = request.getParameter(AppConstants.REQUEST_ACTION);
            if (session != null) {
                if (conn == null || conn.isClosed())
                    conn = getConnection(request);
                TTThanhToanDAO ttttDAO = new TTThanhToanDAO(conn);
                colDMNH = ttttDAO.getDMucNH(null,null);
                //                DChieu1DAO dao = new DChieu1DAO(conn);
                DMKBacDAO dao = new DMKBacDAO(conn);
                // danh sach kb tinh
                String kb_code =
                    session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
                if ("0001".equals(kb_code) || "0002".equals(kb_code)) {
                    whereClause = " ";
                    lstKBTinh = dao.getDMucKB_cha(whereClause, null);
                    lstKBHuyen = dao.getDMucKBHuyen(whereClause, null);
                } else {
                    String strWhere = " AND a.ma='0003' ";
                    lstKBTinh = dao.getDMucKB_cha(strWhere, null);
                    lstKBHuyen = dao.getDMucKBHuyen(whereClause, null);
                }
                //load KB Huyen
                Type listType2 = new TypeToken<Collection<DMKBacVO>>() {
                }.getType();
                strKBHuyen = new Gson().toJson(lstKBHuyen, listType2);
                strListRole =
                        (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
                params = new Vector();
                whereClause =
                        " a.nhkb_nhan=? AND a.ngay_nhan>=trunc(sysdate) AND a.cot_id is not null AND a.trang_thai=10";
                params = new Vector();
                params.add(new Parameter(AppConstants.KBNN_TW_BANK_CODE,
                                         true));
                //                    params.add(new Parameter(AppConstants.TRANG_THAI_COT_DA_DUYET,
                //                                             true));
                CutOffTimeDAO cotDAO = new CutOffTimeDAO(conn);
                colCOTDi = cotDAO.getCOTList(whereClause, params);
                //response danh sach dien tra soat
                if (request.getAttribute("getJson") != null ||
                    (action != null &&
                     (action.equalsIgnoreCase(AppConstants.ACTION_FIND) ||
                      action.equalsIgnoreCase(AppConstants.ACTION_REFRESH)))) {
                    Type listType = new TypeToken<Collection<CutOffTimeVO>>() {
                    }.getType();
                    String strJson = new Gson().toJson(colCOTDi, listType);
                    response.reset();
                    response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                    PrintWriter out = response.getWriter();
                    out.println(strJson.toString());
                    out.flush();
                    out.close();
                } else {
                    request.setAttribute("listCOTDi", colCOTDi);
                    request.setAttribute("chucdanh", strListRole);
                }
            } else {
                return mapping.findForward("timeout");
            }


        } catch (Exception e) {
//            e.printStackTrace();
            if (request.getAttribute("getJson") != null ||
                (action != null && (action.equalsIgnoreCase(AppConstants.ACTION_FIND) ||
                                    action.equalsIgnoreCase(AppConstants.ACTION_REFRESH)))) {
                response.reset();
                response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                PrintWriter out = response.getWriter();
                JsonObject jsonObj = new JsonObject();
                jsonObj.addProperty(AppConstants.ERROR, e.getMessage());
                out.println(jsonObj.toString());
                out.flush();
                out.close();

            } else {
                throw TTSPException.createException("list()", e.getMessage());
            }
        } finally {
            request.setAttribute("colDMNH", colDMNH);
            request.setAttribute("lstKBTinh", lstKBTinh);
            request.setAttribute("lstKBHuyen", strKBHuyen);
            close(conn);
        }

        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

    /**
     *@author hungpd
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        Vector params = null;
        JsonObject jsonObj = null;
        String strJson = "";
        Connection conn = null;
        response.setContentType(AppConstants.CONTENT_TYPE_JSON);
        Collection listKBNoiGio = null;
        PrintWriter out = response.getWriter();
        try {
//            if (!checkPermissionOnFunction(request, "QLY_COT.XYLY_REPLY")) {
//                jsonObj = new JsonObject();
//                jsonObj.addProperty(AppConstants.ERROR, AppConstants.FAILURE);
//            }
//            if (jsonObj == null) {
                conn = getConnection(request);
                String id = request.getParameter("id");
                CutOffTimeDAO cotDAO = new CutOffTimeDAO(conn);
                String whereClause = "  a.id=? ";
                params = new Vector();
                params.add(new Parameter(id, true));
                CutOffTimeVO cotVO = cotDAO.getCTO(whereClause, params);
            
                String listKBHuyen = cotVO.getMa_kb_huyen();
                if(listKBHuyen!=null){
                  listKBHuyen = "'"+listKBHuyen.trim().replace(",","','")+"'";
                  TSoCutOfTimeDAO tSoCOTDAO = new TSoCutOfTimeDAO(conn);
                  String temp[] = cotVO.getNhkb_chuyen().trim().split("");
                  String ma_dv = temp[3]+temp[4]+temp[5];
                  String where = " a.ma_nh_kb in ("+listKBHuyen+") " + 
                                 " and a.ngay_gd = trunc(sysdate) and a.ma_nh like '__"+ma_dv+"%'";
                  listKBNoiGio = tSoCOTDAO.getTSoCOTList(where,null);
                  cotVO.setList_ma_nh_kb_huyen(listKBNoiGio);
                }
            
                if (cotVO != null) {
                    Type listType = new TypeToken<CutOffTimeVO>() {
                    }.getType();
                    strJson = new Gson().toJson(cotVO, listType);
                } else {
                    jsonObj = new JsonObject();
                    strJson = jsonObj.toString();
                }

//            }
        } catch (Exception e) {
//            e.printStackTrace();
            jsonObj.addProperty(AppConstants.ERROR, e.getMessage());
        } finally {
            if (!strJson.equals("")) {
                out.println(strJson.toString());
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

    /**
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward updateExc(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        CutOffTimeVO cotVO = null;
        int i = 0;
        Connection conn = null;
        response.setContentType(AppConstants.CONTENT_TYPE_JSON);
        JsonObject jsonObj = null;
        PrintWriter out = response.getWriter();
        try {
            CutOffTimeForm cotForm = (CutOffTimeForm)form;
            HttpSession session = request.getSession();
            if (session == null)
                return mapping.findForward(AppConstants.FAILURE);
            conn = getConnection(request);
            CutOffTimeDAO cotDAO = new CutOffTimeDAO(conn);
            //select before for update
            String stateReq = cotForm.getTrang_thai();
            String stateAfterSelect =
                TTSPUtils.getStateBUCOT(request, response, conn);
            String userId =
                session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            if ((stateReq != null && stateAfterSelect != null) &&
                stateReq.equalsIgnoreCase(stateAfterSelect)) {
                cotVO = new CutOffTimeVO();
                cotVO.setId(cotForm.getId());
                cotVO.setTrang_thai(AppConstants.TRANG_THAI_COT_DA_XACNHAN);
                cotVO.setChuky_ktt(cotForm.getChuky_ktt());
                i = cotDAO.update(cotVO);
                if (i > 0 && cotForm.getDong_y().equals("Y")) {
                    TSoCutOfTimeDAO tdao = new TSoCutOfTimeDAO(conn);
                    TSoCutOfTimeVO vo = new TSoCutOfTimeVO();
                    vo.setCut_of_time(cotForm.getGio_cot_moi());
                    vo.setSo_yc_cot(cotForm.getCot_id());
                    String where = " WHERE ngay_gd =trunc(sysdate)";
                
                    if (cotForm.getLoai_cot().equals("01")) {
                        Vector paramTemp = new Vector();
                        paramTemp.add(new Parameter(cotForm.getId(),true));
                        CutOffTimeVO cotTempVO = cotDAO.getCTO(" a.id = ? ",paramTemp);
                        String kb_huyen = "'" + cotTempVO.getMa_kb_huyen().trim().replace(",","','") + "'";
                        where += " AND ma_nh_kb in ("+kb_huyen+")";
                    }
                    i = tdao.updateCondition(vo, where, null);
                      if(i>0){
                        //insert for tcs
                        CutOffTimeVO voTemp = getCutOffTimeVO(cotForm.getId(), cotDAO);
                        cotVO.setNgay_lap(new Date());
                        cotVO.setDong_y(AppConstants.ACTION_YES);
                        cotVO.setCot_id(cotForm.getId());
                        cotVO.setMa_kb_huyen(voTemp.getMa_kb_huyen());
                        cotVO.setCot_id(voTemp.getCot_id());
                        cotVO.setNhkb_nhan("01701001");
                        cotVO.setNhkb_chuyen(AppConstants.KBNN_TW_BANK_CODE);
                        cotVO.setLoai_cot(cotForm.getLoai_cot());
                        cotVO.setTrang_thai(AppConstants.TRANG_THAI_COT_DA_DUYET_DONGY);
                        cotVO.setNew_cot(cotForm.getGio_cot_moi());
                        Send299 send = new Send299(conn);
                        String user_id = session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString();
                        cotVO.setMa_nsd(user_id);
                        cotVO.setId(Long.valueOf(cotDAO.insert(cotVO, "299")));
                        //send message to tcs
                        
//                        String mstID = send.sendMessage(cotVO,"TCS_KBA", null, user_id);
//                        cotVO.setMsg_id(mstID);
                        cotDAO.update(cotVO);
                      }
                }
                request.setAttribute("getJson", true);
                list(mapping, form, request, response);
                conn.commit();
            } else {
                jsonObj = new JsonObject();
                jsonObj.addProperty(AppConstants.ERROR, "TTSP-ERROR-0001");
                out.println(jsonObj.toString());
            }
        } catch (Exception e) {
            conn.rollback();
//            e.printStackTrace();
            jsonObj = new JsonObject();
            jsonObj.addProperty(AppConstants.ERROR, e.getMessage());
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
    
  private CutOffTimeVO getCutOffTimeVO(Long id,
                                       CutOffTimeDAO cotDAO) throws Exception {
      CutOffTimeVO voTemp = null;
      String whereClause = " a.id = ? ";
      Vector vparam = new Vector();
      vparam.add(new Parameter(id, true));
      try {
          voTemp = cotDAO.getCTO(whereClause, vparam);
      } catch (Exception e) {
          e.printStackTrace();
      }
      return voTemp;
  }
}
