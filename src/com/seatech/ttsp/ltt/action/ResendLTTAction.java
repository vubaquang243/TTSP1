package com.seatech.ttsp.ltt.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.DateUtils;
import com.seatech.framework.utils.StringUtil;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.dmnh.DMNHangDAO;
import com.seatech.ttsp.dmnh.DMNHangVO;
import com.seatech.ttsp.dmnhkb.DMHTKBacVO;
import com.seatech.ttsp.dmnhkb.DMNHKBacDAO;
import com.seatech.ttsp.ltt.LTTDAO;
import com.seatech.ttsp.ltt.LTTVO;
import com.seatech.ttsp.ltt.form.LTTForm;
import com.seatech.ttsp.proxy.giaodien.MsgDAO;
import com.seatech.ttsp.thamchieu.MaThamChieuDAO;
import com.seatech.ttsp.thamso.ThamSoHThongVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ResendLTTAction extends AppAction {
    private static String SUCCESS = "success";
    //    private static String STRING_EMPTY = "";
    private static String TRACUU_LTT = "LTT.TRACUU";

    public DMHTKBacVO checkValidTTTT(String codeKB,
                                     Connection conn) throws Exception {
        try {
            String whereClauseKB = null;
            Vector paramsKB = new Vector();
            DMHTKBacVO nhkbVO = new DMHTKBacVO();
            DMNHKBacDAO nhkbDAO = new DMNHKBacDAO(conn);
            whereClauseKB = "ma = ?";
            paramsKB.add(new Parameter(codeKB, true));
            nhkbVO = nhkbDAO.getDMHTKBac(whereClauseKB, paramsKB);
            return nhkbVO;
        } catch (Exception e) {
            throw new Exception("Tra Cuu LTT checkValidTTTT(): " + e);
        }
    }

    protected ActionForward executeAction(ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
        Connection conn = null;
        String strMaSGD = "0003";
        String strCapTinh = "5";
        String strCapTW = "1";
        String strWhereClauseKB = null;
        Vector vParamsKB = null;
        try {
            conn = getConnection(request);

            //            LTTForm lttForm = (LTTForm)form;

            /*
         * MaThamChieuDAO
         * lstTrangThai
         * luu tru cac gia tri cua trang thai
         * */
            List lstTrangThai = null;
            MaThamChieuDAO thamchieuDAO = new MaThamChieuDAO(conn);
            String strWhere = "a.rv_domain = ?";
            Vector vParam = new Vector();
            vParam.add(new Parameter(AppConstants.MA_THAM_CHIEU_TRANG_THAI_LTT,
                                     true));
            lstTrangThai =
                    (List)thamchieuDAO.getMaThamChieuList(strWhere, vParam);

            request.setAttribute("lstTrangThai", lstTrangThai);
            // xac dinh user dang nhap thuoc tinh hay huyen
            HttpSession session = request.getSession();
            String ma_kb_so_tai =
                (String)session.getAttribute(AppConstants.APP_KB_CODE_SESSION);
            DMKBacDAO kbDAO = new DMKBacDAO(conn);
            ArrayList<DMKBacVO> lstKBTinh = new ArrayList<DMKBacVO>();
            ArrayList<DMKBacVO> lstKBHuyen = new ArrayList<DMKBacVO>();
            if (ma_kb_so_tai.equals(strMaSGD)) {
                request.setAttribute("MAT4", ma_kb_so_tai);
                vParamsKB = new Vector();
                strWhereClauseKB = "a.cap=?  or a.ma='" + strMaSGD + "' ";
                vParamsKB.add(new Parameter(strCapTinh, true));
                lstKBTinh =
                        (ArrayList<DMKBacVO>)kbDAO.getDMNHKBList(strWhereClauseKB,
                                                                 vParamsKB);
            } else {
                vParamsKB = new Vector();
                strWhereClauseKB = "a.ma=? ";
                vParamsKB.add(new Parameter(ma_kb_so_tai, true));
                DMKBacVO dmkbVO = kbDAO.getDMKBNH(strWhereClauseKB, vParamsKB);
                // Neu user thuoc cap tinh. Set kb tinh va kb huyen
                if (dmkbVO.getCap().equals(strCapTinh) ||
                    dmkbVO.getCap().equals(strCapTW)) {
                    lstKBTinh.add(dmkbVO);
                    vParamsKB = new Vector();
                    strWhereClauseKB = " a.ma_cha=? ";
                    vParamsKB.add(new Parameter(dmkbVO.getMa(), true));
                    lstKBHuyen =
                            (ArrayList<DMKBacVO>)kbDAO.getDMNHKBList(strWhereClauseKB,
                                                                     vParamsKB);
                } else {
                    lstKBHuyen.add(dmkbVO);
                    vParamsKB = new Vector();
                    strWhereClauseKB = " a.ma=? ";
                    vParamsKB.add(new Parameter(dmkbVO.getMa_cha(), true));
                    lstKBTinh.add(kbDAO.getDMKBNH(strWhereClauseKB,
                                                  vParamsKB));
                }
            }
            request.setAttribute("lstKBTinh", lstKBTinh);
            request.setAttribute("lstKBHuyen", lstKBHuyen);
        } catch (Exception ex) {
            throw new Exception("Tra Cuu LTT: " + ex);
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection(request);

            LTTForm lttForm = (LTTForm)form;
           
            /*
             * MaThamChieuDAO
             * lstTrangThai
             * luu tru cac gia tri cua trang thai
             * */
            List lstTrangThai = null;
            MaThamChieuDAO thamchieuDAO = new MaThamChieuDAO(conn);
            String strWhere =
                "a.rv_domain = ? and rv_low_value in ('08','15','16','07','11','13')";
            Vector vParam = new Vector();
            vParam.add(new Parameter(AppConstants.MA_THAM_CHIEU_TRANG_THAI_LTT,
                                     true));
            lstTrangThai =
                    (List)thamchieuDAO.getMaThamChieuList(strWhere, vParam);

            request.setAttribute("lstTrangThai", lstTrangThai);
            //tra ve danh sach tim kiem va phan trang
            LTTDAO dao = new LTTDAO(conn);
            List<LTTVO> lstLTT = null;

            String page = lttForm.getPageNumber();
            if (page == null) {
                page = "1";
            }
            // khai bao cac bien phan trang
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = new Integer(30);
            Integer totalCount[] = new Integer[1];
            // khai bao cac bien tuong tac voi tang DAO
            StringBuffer szWhereClause = new StringBuffer();
            Vector v_Param_ltt = new Vector();
            Parameter param_ltt = null;


            /*
             * @sea:kiem tra gia tri cua form va set gia tri cho vector
             * ttv_nhan,trang_thai,loai_lenh_tt,tong_sotien,NH_chuyen,NH_nhan,ngay_nhap,so YCTT,SLTT
             *             String strFromDate = lttForm.getNgay_nhap_nh();
                            String strToDate = lttForm.getNgay_nhap_nh();
             * */

            if (lttForm.getTrang_thai() != null &&
                !"".equals(lttForm.getTrang_thai()) &&
                !lttForm.getTrang_thai().equalsIgnoreCase("00")) {
                szWhereClause.append("and t.trang_thai = ? ");
                param_ltt =
                        new Parameter(lttForm.getTrang_thai().trim(), true);
                v_Param_ltt.add(param_ltt);
            }
//            else{ 
//                szWhereClause.append("and t.trang_thai in ('08','15','16','07','11','13') ");
//            }
//            }
            //loai lenh
            HttpSession session = request.getSession();
            //            String strNHKBacID =
            //                session.getAttribute(AppConstants.APP_NHKB_ID_SESSION).toString();

            String loailenh_tt = lttForm.getLoai_lenh_tt();
            if (loailenh_tt == null || loailenh_tt.equals("")) {
                loailenh_tt = "00";
            }
            if (loailenh_tt.trim().equalsIgnoreCase("02")) {
              szWhereClause.append(" and t.id like '__20%' ");
              szWhereClause.append(" AND t.trang_thai in('08','15','16','07','11','13') ");
            } else {                
              szWhereClause.append(" and t.id like '__701103%' ");
              szWhereClause.append(" AND t.trang_thai in('08','15','16','07','11','13') ");
            }


            if (lttForm.getTong_sotien() != null &&
                !"".equals(lttForm.getTong_sotien())) {
                szWhereClause.append("and t.tong_sotien =? ");
                lttForm.setTong_sotien(lttForm.getTong_sotien().replace(".",
                                                                        ""));
                param_ltt =
                        new Parameter(lttForm.getTong_sotien().trim(), true);
                v_Param_ltt.add(param_ltt);
            }
            if (lttForm.getMsg_id() != null &&
                !"".equals(lttForm.getMsg_id())) {
                
                String strMsgIdList = "'"+lttForm.getMsg_id().replace(" ", "").replace("'", "").replace(",", "','")+"'";
                
                szWhereClause.append("and t.msg_id in (" + strMsgIdList + ") ");
            }
            
//          if (lttForm.getTu_ngay() == null ||
//              "".equals(lttForm.getTu_ngay())) {
//              lttForm.setTu_ngay(StringUtil.DateToString(new Date(),
//                                                         "dd/MM/yyyy"));
//          }
            
            if (lttForm.getTu_ngay() != null &&
                !"".equals(lttForm.getTu_ngay().trim())) {
                Long lTu_ngay = DateUtils.DateToLong(lttForm.getTu_ngay());
                szWhereClause.append("and t.ngay_tt = ? ");
                param_ltt = new Parameter(lTu_ngay, true);
                v_Param_ltt.add(param_ltt);
            }else {
                szWhereClause.append("and t.ngay_tt = to_char(sysdate,'yyyyMMdd') ");
                lttForm.setTu_ngay(StringUtil.DateToString(new Date(), "dd/MM/yyyy"));
            }
            //            if (lttForm.getDen_ngay() != null &&
            //                !"".equals(lttForm.getDen_ngay().trim())) {
            //                Long lDen_ngay = DateUtils.DateToLong(lttForm.getDen_ngay());
            //                szWhereClause.append("and t.ngay_tt <= ? ");
            //                param_ltt = new Parameter(lDen_ngay, true);
            //                v_Param_ltt.add(param_ltt);
            //            } else {
            //                szWhereClause.append("and t.ngay_tt <= to_char(sysdate,'yyyyMMdd') ");
            //            }

            //            Long ma_nhkb_chuyen = null;
            //            Long ma_nhkb_nhan = null;
            //            if (lttForm.getMa_nhkb_chuyen_cm() != null &&
            //                !"".equals(lttForm.getMa_nhkb_chuyen_cm())) {
            //                ma_nhkb_chuyen =
            //                        getMaDVKB(lttForm.getMa_nhkb_chuyen_cm(), conn);
            //                if (ma_nhkb_chuyen != null) {
            //                    szWhereClause.append("and t.nhkb_chuyen = ? ");
            //                    param_ltt = new Parameter(ma_nhkb_chuyen, true);
            //                    v_Param_ltt.add(param_ltt);
            //                } else {
            //                    szWhereClause.append("and t.nhkb_chuyen in (select n.id from sp_dm_ngan_hang n where n.ma_nh like (?) )");
            //                    param_ltt =
            //                            new Parameter("%" + lttForm.getMa_nhkb_chuyen_cm() +
            //                                          "%", true);
            //                    v_Param_ltt.add(param_ltt);
            //                }
            //            }
            //
            //            if (lttForm.getMa_nhkb_nhan_cm() != null &&
            //                !"".equals(lttForm.getMa_nhkb_nhan_cm())) {
            //                ma_nhkb_nhan = getMaDVKB(lttForm.getMa_nhkb_nhan_cm(), conn);
            //                if (ma_nhkb_nhan != null) {
            //                    szWhereClause.append("and t.nhkb_nhan = ? ");
            //                    param_ltt = new Parameter(ma_nhkb_nhan, true);
            //                    v_Param_ltt.add(param_ltt);
            //                } else {
            //                    szWhereClause.append("and t.nhkb_nhan in (select n.id from sp_dm_ngan_hang n where n.ma_nh like (?) )");
            //                    param_ltt =
            //                            new Parameter("%" + lttForm.getMa_nhkb_nhan_cm() +
            //                                          "%", true);
            //                    v_Param_ltt.add(param_ltt);
            //                }
            //            } else {
            //                //                ma_nhkb_nhan = new Long("10");
            //            }
            if (lttForm.getSo_yctt() != null &&
                !"".equals(lttForm.getSo_yctt())) {
                szWhereClause.append("and (t.so_yctt like (?) or t.so_tham_chieu_gd like (?) ) ");
                param_ltt =
                        new Parameter("%" + lttForm.getSo_yctt().trim() + "%",
                                      true);
                v_Param_ltt.add(param_ltt);
                v_Param_ltt.add(param_ltt);
            }
            if (lttForm.getId() != null && !"".equals(lttForm.getId())) {
                szWhereClause.append("and t.id in (" + lttForm.getId().trim() +
                                     ") ");
            }
            if (lttForm.getKb_tinh() != null &&
                !"".equals(lttForm.getKb_tinh())) {
                if (lttForm.getKb_huyen() != null &&
                    !"".equals(lttForm.getKb_huyen())) {
                    //                    szWhereClause.append("and (a.ma_nh = ? " +
                    //                                         "or b.ma_nh = ? )");

                    szWhereClause.append("and (a.ma_nh in (select ma_nh from sp_dm_manh_shkb where shkb in (select ma from sp_dm_htkb where ma = ?)) " +
                                         "or b.ma_nh in (select ma_nh from sp_dm_manh_shkb where shkb in (select ma from sp_dm_htkb where ma = ?)))");

                    param_ltt = new Parameter(lttForm.getKb_huyen(), true);
                    v_Param_ltt.add(param_ltt);
                    v_Param_ltt.add(param_ltt);
                } else {
                    szWhereClause.append("and (a.ma_nh in (select ma_nh from sp_dm_manh_shkb where shkb in (select ma from sp_dm_htkb where ma_cha = ? or ma = ?)) " +
                                         "or b.ma_nh in (select ma_nh from sp_dm_manh_shkb where shkb in (select ma from sp_dm_htkb where ma_cha = ? or ma = ?)))");
                    param_ltt = new Parameter(lttForm.getKb_tinh(), true);
                    v_Param_ltt.add(param_ltt);
                    v_Param_ltt.add(param_ltt);
                    v_Param_ltt.add(param_ltt);
                    v_Param_ltt.add(param_ltt);
                }
            }
            /**
             * Kiem tra TTTT
             * 1. Hien thi tat ca neu la TTTT
             * 2. Chi hien thi ban ghi don vi
             * @param:strNHKB_code
             * */


            lstLTT =
                    (List<LTTVO>)dao.getLTTDiListWithPading(szWhereClause.toString(),
                                                            v_Param_ltt,
                                                            currentPage,
                                                            numberRowOnPage,
                                                            totalCount);
            //            LTTVO voSum = new LTTVO();
            //            voSum = dao.getSumTongTien(szWhereClause.toString(), v_Param_ltt);
            //            request.setAttribute("tong_mon", voSum.getTong_mon());
            //            request.setAttribute("tong_tien", voSum.getTong_tien());

            //            ArrayList<LTTVO> lstLTTTmp = new ArrayList<LTTVO>();
            //            for (LTTVO vo : lstLTT) {
            //                if (vo.getNgay_tt() != null &&
            //                    !STRING_EMPTY.equals(vo.getNgay_tt())) {
            //                    vo.setNgay_tt_tmp(DateUtils.LongToStrDateDDMMYYYY(vo.getNgay_tt()));
            //                }
            //                lstLTTTmp.add(vo);
            //            }
            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow(totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);
            request.setAttribute("lstLTT", lstLTT);
            /*
             * LTTDAO
             * End
             * */
            saveVisitLog(conn, session, TRACUU_LTT, "");
            /*
             * lay lai thong tin kb tinh huyen
             *
             * */
            /** neu la trung tam thanh toan thi lay ra toan bo kb tinh de lua chon
              * Lay ra danh sach Kho bac tinh
              **/
            String strCapTinh = "5";
            String ma_kb_so_tai =
                (String)session.getAttribute(AppConstants.APP_KB_CODE_SESSION);
            String maSGD = "0003";
            Vector vParamsKB = null;
            String strWhereClauseKB = "";
            DMKBacDAO kbDAO = new DMKBacDAO(conn);
            ArrayList<DMKBacVO> lstKBTinh = new ArrayList<DMKBacVO>();
            ArrayList<DMKBacVO> lstKBHuyen = new ArrayList<DMKBacVO>();

            request.setAttribute("MAT4", ma_kb_so_tai);
            vParamsKB = new Vector();
            strWhereClauseKB = "a.cap=?  or a.ma='" + maSGD + "' ";
            vParamsKB.add(new Parameter(strCapTinh, true));
            lstKBTinh =
                    (ArrayList<DMKBacVO>)kbDAO.getDMNHKBList(strWhereClauseKB,
                                                             vParamsKB);
            vParamsKB = new Vector();
            strWhereClauseKB = " a.ma_cha=? ";
            vParamsKB.add(new Parameter(lttForm.getKb_tinh(), true));
            lstKBHuyen =
                    (ArrayList<DMKBacVO>)kbDAO.getDMNHKBList(strWhereClauseKB,
                                                             vParamsKB);

            request.setAttribute("lstKBTinh", lstKBTinh);
            request.setAttribute("lstKBHuyen", lstKBHuyen);
        } catch (Exception ex) {
            throw new Exception("Tra Cuu LTT: " + ex);
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public Long getMaDVKB(String maDVTS, Connection conn) throws Exception {
        Long id = null;
        String strWhereClause = " ma_nh = ?";
        Vector params = new Vector();
        params.add(new Parameter(maDVTS, true));
        DMNHangDAO dmnhDAO = new DMNHangDAO(conn);
        DMNHangVO vo = dmnhDAO.getDMNH(strWhereClause, params);
        if (vo != null) {
            id = vo.getId();
        }
        return id;
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        try {
            String lstID = request.getParameter("lstId");
            String type = request.getParameter("type");
            String loai_tt = request.getParameter("loai_tt");
            request.setAttribute("lstID", lstID);
            request.setAttribute("loai_tt", loai_tt);
            if (type.equalsIgnoreCase("btn_resend")) {
                return mapping.findForward("resend");
            } else {
                return mapping.findForward("update");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {

        }
    }

    public ActionForward updateExc(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        Connection conn = null;
        LTTForm lttForm = null;
        int iSent = 0;
        String checkSuccess = "FALSE";
        PreparedStatement pstmt = null;
        String lstID = "";
        String type = "";
        String user = "";
        String strSQL = "";
        String loai_tt = "";
        int msg_number = 0;
        try {
            //add all three JasperPrints to the list below
            conn = getConnection(request);
            lstID = request.getParameter("lstId");
            type = request.getParameter("type");
            loai_tt = request.getParameter("loai_tt");
            
          lttForm = (LTTForm)form;
          HttpSession session = request.getSession();

          user = lttForm.getUser();
          String pass = lttForm.getPass();

          String[] arrID = lstID.split(",");
          String strMaNSD =
              session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString();
          String strPassword =
              session.getAttribute("PASSWORD").toString();

          msg_number = arrID.length;
          if (!strMaNSD.equalsIgnoreCase(user) ||
              !strPassword.equals(pass)) {
              throw new Exception("Sai user hoac mat khau");
          }
             
            if (loai_tt.equals("LTT_DI") || loai_tt.equals("LTT_DEN")) {             
                if (lstID != null) {
                    if ("resend".equals(type)) {
                        type = "RESEND_103";
                        strSQL =
                                " msg_id in (select t.msg_id from sp_ltt t where t.id in (" +
                                lstID +
                                "0) and t.trang_thai in ('08','15','16','13','07','11')) and msg_type = '103'";

                        Collection colTS = getThamSoHThong(session);
                        Iterator iter = colTS.iterator();
                        String strHostName = "";
                        String strChanel = "";
                        String strPort = "";
                        String strQMName = "";
                        String QName = "";
                        ThamSoHThongVO tsVO = null;
                        while (iter.hasNext()) {
                            tsVO = (ThamSoHThongVO)iter.next();
                            if ("MQ_CHANEL".equalsIgnoreCase(tsVO.getTen_ts())) {
                                strChanel = tsVO.getGiatri_ts();
                            } else if ("MQ_HOSTNAME".equalsIgnoreCase(tsVO.getTen_ts())) {
                                strHostName = tsVO.getGiatri_ts();
                            } else if ("MQ_MANAGER_NAME".equalsIgnoreCase(tsVO.getTen_ts())) {
                                strQMName = tsVO.getGiatri_ts();
                            } else if ("MQ_NAME".equalsIgnoreCase(tsVO.getTen_ts())) {
                                QName = tsVO.getGiatri_ts();
                            } else if ("MQ_PORT".equalsIgnoreCase(tsVO.getTen_ts())) {
                                strPort = tsVO.getGiatri_ts();
                            }
                        }

                        MsgDAO msgDAO = new MsgDAO(conn);
                        iSent =
                                msgDAO.reSendLTT(strHostName, strChanel, strPort,
                                                 strQMName, QName, strSQL);
                        checkSuccess = "RESEND_SUCCESS";
                    } else {
                        type = "UPDATE_103";
                        if("02".equals(lttForm.getTrang_thai())){
	                        strSQL =
	                                "update sp_ltt set trang_thai = '" + lttForm.getTrang_thai() +
	                                "', ttv_nhan = null where id in (" + lstID + "0)";
                        }else{
                          strSQL =
                              "update sp_ltt set trang_thai = '" + lttForm.getTrang_thai() +
                              "' where id in (" + lstID + "0)";	
                        }
                        pstmt = conn.prepareStatement(strSQL);
                        iSent = pstmt.executeUpdate();
                        conn.commit();

                        checkSuccess = "UPDATE_SUCCESS";
                    }
                }
            } else if (loai_tt.equals("LQT")) {
              if (lstID != null) {
                  if ("resend".equals(type)) {
                      type = "RESEND_LQT";
                      strSQL =
                              " msg_id in (select msg_id from sp_quyet_toan where id in (" +
                              lstID +
                              "0) and trang_thai in ('16','13','11','03')) and msg_type in ('900','910')";

                      Collection colTS = getThamSoHThong(session);
                      Iterator iter = colTS.iterator();
                      String strHostName = "";
                      String strChanel = "";
                      String strPort = "";
                      String strQMName = "";
                      String QName = "";
                      ThamSoHThongVO tsVO = null;
                      while (iter.hasNext()) {
                          tsVO = (ThamSoHThongVO)iter.next();
                          if ("MQ_CHANEL".equalsIgnoreCase(tsVO.getTen_ts())) {
                              strChanel = tsVO.getGiatri_ts();
                          } else if ("MQ_HOSTNAME".equalsIgnoreCase(tsVO.getTen_ts())) {
                              strHostName = tsVO.getGiatri_ts();
                          } else if ("MQ_MANAGER_NAME".equalsIgnoreCase(tsVO.getTen_ts())) {
                              strQMName = tsVO.getGiatri_ts();
                          } else if ("MQ_NAME".equalsIgnoreCase(tsVO.getTen_ts())) {
                              QName = tsVO.getGiatri_ts();
                          } else if ("MQ_PORT".equalsIgnoreCase(tsVO.getTen_ts())) {
                              strPort = tsVO.getGiatri_ts();
                          }
                      }

                      MsgDAO msgDAO = new MsgDAO(conn);
                      iSent =
                              msgDAO.reSendLTT(strHostName, strChanel, strPort,
                                               strQMName, QName, strSQL);
                      checkSuccess = "RESEND_SUCCESS";
                  } else {
                      type = "UPDATE_LQT";
                      strSQL =
                              "update sp_quyet_toan set trang_thai = '" + lttForm.getTrang_thai() +
                              "' where id in (" + lstID + "0)";
                      pstmt = conn.prepareStatement(strSQL);
                      iSent = pstmt.executeUpdate();
                      conn.commit();

                      checkSuccess = "UPDATE_SUCCESS";
                  }
              }

            }else if (loai_tt.equals("BKLQT")) {
              if (lstID != null) {
                  if ("resend".equals(type)) {
                      type = "RESEND_BKLQT";
                    String lstBKLQT = "'"+lstID.replace(",", "','")+"'";
                      strSQL =
                              " msg_id in (select msg_id from sp_bke_qtoan where id in (" +
                              lstBKLQT +
                              ") and trang_thai = '02') and msg_type in ('900','910')";

                      Collection colTS = getThamSoHThong(session);
                      Iterator iter = colTS.iterator();
                      String strHostName = "";
                      String strChanel = "";
                      String strPort = "";
                      String strQMName = "";
                      String QName = "";
                      ThamSoHThongVO tsVO = null;
                      while (iter.hasNext()) {
                          tsVO = (ThamSoHThongVO)iter.next();
                          if ("MQ_CHANEL".equalsIgnoreCase(tsVO.getTen_ts())) {
                              strChanel = tsVO.getGiatri_ts();
                          } else if ("MQ_HOSTNAME".equalsIgnoreCase(tsVO.getTen_ts())) {
                              strHostName = tsVO.getGiatri_ts();
                          } else if ("MQ_MANAGER_NAME".equalsIgnoreCase(tsVO.getTen_ts())) {
                              strQMName = tsVO.getGiatri_ts();
                          } else if ("MQ_NAME".equalsIgnoreCase(tsVO.getTen_ts())) {
                              QName = tsVO.getGiatri_ts();
                          } else if ("MQ_PORT".equalsIgnoreCase(tsVO.getTen_ts())) {
                              strPort = tsVO.getGiatri_ts();
                          }
                      }

                      MsgDAO msgDAO = new MsgDAO(conn);
                      iSent =
                              msgDAO.reSendLTT(strHostName, strChanel, strPort,
                                               strQMName, QName, strSQL);
                      checkSuccess = "RESEND_SUCCESS";
                  } 
//                  else {
//                      type = "UPDATE_LQT";
//                      strSQL =
//                              "update sp_quyet_toan set trang_thai = '" + lttForm.getTrang_thai() +
//                              "' where id in (" + lstID + "0)";
//                      pstmt = conn.prepareStatement(strSQL);
//                      iSent = pstmt.executeUpdate();
//                      conn.commit();
//
//                      checkSuccess = "UPDATE_SUCCESS";
//                  }
              }

            }else if (loai_tt.equals("DTS")) {
              if (lstID != null) {
                  if ("resend".equals(type)) {
                      type = "RESEND_DTS";
                      strSQL =
                              " msg_id in (select msg_id from sp_dts where SUBSTR(id,6,3) in ('195','196','199') and id in (" +
                              lstID +
                              "0) and trang_thai in ('03','04','18','19')) and msg_type IN ('195', '196','199')";

                      Collection colTS = getThamSoHThong(session);
                      Iterator iter = colTS.iterator();
                      String strHostName = "";
                      String strChanel = "";
                      String strPort = "";
                      String strQMName = "";
                      String QName = "";
                      ThamSoHThongVO tsVO = null;
                      while (iter.hasNext()) {
                          tsVO = (ThamSoHThongVO)iter.next();
                          if ("MQ_CHANEL".equalsIgnoreCase(tsVO.getTen_ts())) {
                              strChanel = tsVO.getGiatri_ts();
                          } else if ("MQ_HOSTNAME".equalsIgnoreCase(tsVO.getTen_ts())) {
                              strHostName = tsVO.getGiatri_ts();
                          } else if ("MQ_MANAGER_NAME".equalsIgnoreCase(tsVO.getTen_ts())) {
                              strQMName = tsVO.getGiatri_ts();
                          } else if ("MQ_NAME".equalsIgnoreCase(tsVO.getTen_ts())) {
                              QName = tsVO.getGiatri_ts();
                          } else if ("MQ_PORT".equalsIgnoreCase(tsVO.getTen_ts())) {
                              strPort = tsVO.getGiatri_ts();
                          }
                      }

                      MsgDAO msgDAO = new MsgDAO(conn);
                      iSent =
                              msgDAO.reSendLTT(strHostName, strChanel, strPort,
                                               strQMName, QName, strSQL);
                      checkSuccess = "RESEND_SUCCESS";
                  } else {
                      type = "UPDATE_DTS";
                      strSQL =
                              "update sp_dts set trang_thai = '" + lttForm.getTrang_thai() +
                              "' where id in (" + lstID + "0)";
                      pstmt = conn.prepareStatement(strSQL);
                      iSent = pstmt.executeUpdate();
                      conn.commit();

                      checkSuccess = "UPDATE_SUCCESS";
                  }
              }

            }
        } catch (Exception e) {
            checkSuccess = e.getMessage();
            e.printStackTrace();
            throw e;
        } finally {
            try {
                String sqlLog =
                    "insert into sp_log_resend(id, msg_id, mt_id, type,user_code, more, insert_date, ";
                sqlLog +=
                        "send_number, sent_number) values (sp_log_resend_seq.nextval,";
                sqlLog +=
                        "null,'" + lstID + "','" + type + "','" + user + "','" +
                        strSQL.replace("'", "") + "',SYSDATE,";
                sqlLog += msg_number + "," + iSent + ")";
                pstmt = conn.prepareStatement(sqlLog);
                pstmt.executeUpdate();
                conn.commit();
            } catch (Exception ex) {
//                ex.printStackTrace();
            }

            close(conn);
            request.setAttribute("SO_DIEN_DA_CHON", iSent);
            request.setAttribute("SO_DIEN_DA_GUI_THANH_CONG", iSent);
            request.setAttribute("TRANG_THAI_THUC_HIEN", checkSuccess);
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
//                    e.printStackTrace();
                } finally {
                    pstmt = null;
                }

            }
            return mapping.findForward(SUCCESS);
        }
    }
}

