package com.seatech.ttsp.dts.action;


import com.google.gson.JsonObject;

import com.seatech.framework.AppConstants;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.dts.DTSoatDAO;
import com.seatech.ttsp.dts.DTSoatVO;
import com.seatech.ttsp.dts.form.DTSoatForm;

import java.io.PrintWriter;

import java.sql.Connection;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class NhapDTSoatTraLoiAction extends AppAction {
    public NhapDTSoatTraLoiAction() {
        super();
    }

    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        try {
            if (!checkPermissionOnFunction(request,
                                           "DTS.DEN.NHTM_HOI.TRLOI")) {
                return mapping.findForward(AppConstants.FAILURE);
            }
            HttpSession session = request.getSession();
            if (session != null &&
                (session.getAttribute(AppConstants.APP_USER_ID_SESSION) !=
                 null &&
                 !session.getAttribute(AppConstants.APP_USER_ID_SESSION).equals(""))) {
                DTSoatForm dtsForm = (DTSoatForm)form;
                dtsForm.setTtv_nhan(session.getAttribute("id_nsd").toString());
                dtsForm.setMa_nsd(session.getAttribute("ma_nsd").toString());
                dtsForm.setNgay_nhan(StringUtil.DateToString(new Date(),
                                                             "dd/MM/yyyy"));

                dtsForm.setThong_tin_khac(dtsForm.getNoidung() + "/" +
                                          dtsForm.getThong_tin_khac());

                //         String nhkbnhan = dtsForm.getNhkb_nhan();
                //         String tennhkbnhan = dtsForm.getTen_don_vi_nhan_tra_soat();
                //         String manhkbnhan = dtsForm.getMa_don_vi_nhan_tra_soat();
                //         if(dtsForm.getNhkb_chuyen() != null && !dtsForm.getNhkb_chuyen().toString().equals(session.getAttribute("id_nhkb").toString()))
                //         {
                //             dtsForm.setNhkb_nhan(dtsForm.getNhkb_chuyen());
                //             dtsForm.setNhkb_chuyen(nhkbnhan);
                //             dtsForm.setTen_don_vi_nhan_tra_soat(dtsForm.getTen_don_vi_tra_soat());
                //             dtsForm.setMa_don_vi_nhan_tra_soat(dtsForm.getMa_don_vi_tra_soat());
                //             dtsForm.setMa_don_vi_tra_soat(manhkbnhan);
                //             dtsForm.setTen_don_vi_tra_soat(tennhkbnhan);
                //         } else
                //         {
                //             dtsForm.setNhkb_nhan(nhkbnhan);
                //             dtsForm.setNhkb_chuyen(dtsForm.getNhkb_chuyen());
                //         }

            } else {
                return mapping.findForward(AppConstants.FAILURE);
            }
        } catch (Exception e) {
//            e.printStackTrace();
        } finally {

        }
        return mapping.findForward(AppConstants.SUCCESS);
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

    public ActionForward addExc(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "DTS.DI.TRLOI.THEM")) {
            return mapping.findForward(AppConstants.FAILURE);
        }
        Connection conn = null;
        JsonObject jsonObj = new JsonObject();
        response.setContentType(AppConstants.CONTENT_TYPE_JSON);
        PrintWriter out = response.getWriter();
        try {
            DTSoatForm dtsForm = (DTSoatForm)form;
            conn = getConnection(request);
            DTSoatVO dtsoatVO = new DTSoatVO();
            DTSoatDAO dtsoatDAO = new DTSoatDAO(conn);
            dtsoatVO.setTrang_thai(AppConstants.SP_DTS_HOI_NH_STATE_13.trim());
            dtsoatVO.setTtv_nhan(new Long(dtsForm.getTtv_nhan()));
            dtsoatVO.setNgay_nhan(StringUtil.DateToString(new Date(),
                                                          "dd/MM/yyyy HH:mm:ss"));
            dtsoatVO.setId(dtsForm.getId());

            //select before for update
            String stateReq = dtsForm.getTrang_thai();
            String stateAfterSelect =
                TTSPUtils.getStateBU(request, response, conn);
            if ((stateReq != null && stateReq != null) &&
                stateReq.equalsIgnoreCase(stateAfterSelect)) {
                //Update trang thai dien hoi
                dtsoatDAO.update(dtsoatVO);
                //**************************************************************
                dtsoatVO = dtsoatDAO.getDTS(" AND a.id = "+dtsForm.getId()+" ", null);
                String strLoaiTien = dtsoatVO.getLoai_tien();
                //**************************************************************
                dtsoatVO = new DTSoatVO();
                BeanUtils.copyProperties(dtsoatVO, dtsForm);
                dtsoatVO.setNhkb_nhan(new Long(dtsForm.getNhkb_chuyen()));
                dtsoatVO.setNhkb_chuyen(new Long(dtsForm.getNhkb_nhan()));
                dtsoatVO.setTrang_thai(AppConstants.SP_DTS_HOI_NH_STATE_14.trim());
                dtsoatVO.setNgay_nhan(StringUtil.DateToString(new Date(),
                                                              "dd/MM/yyyy HH:mm:ss"));
                dtsoatVO.setTtv_nhan(new Long(dtsForm.getTtv_nhan()));
                dtsoatVO.setLoai_tien(strLoaiTien);
                //Insert dien tra loi
                String strReturn =
                    dtsoatDAO.insert(dtsoatVO, AppConstants.DTS_TRA_LOI_TYPE);
                if (strReturn != null &&
                    !"-1".equalsIgnoreCase(strReturn.trim())) {
                    //set log
                    saveVisitLog(conn, request.getSession(),
                                 "DTS.DI.TRLOI.THEM", "");
                    jsonObj.addProperty(AppConstants.SUCCESS, strReturn);
                } else {
                    jsonObj.addProperty(AppConstants.ERROR, "TTSP-ERROR-0001");
                }
                conn.commit();
            } else {
                jsonObj.addProperty(AppConstants.ERROR, "TTSP-ERROR-0002");
            }
        } catch (Exception e) {
            conn.rollback();
//            e.printStackTrace();
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            jsonObj.addProperty(AppConstants.ERROR, e.getMessage());
        } finally {
            close(conn);
            if (out != null) {
                out.println(jsonObj.toString());
                out.flush();
                out.close();
            }
        }

        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

}
