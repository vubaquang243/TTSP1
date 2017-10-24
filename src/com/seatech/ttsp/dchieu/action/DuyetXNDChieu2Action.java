package com.seatech.ttsp.dchieu.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.exception.TTSPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DChieu2DAO;
import com.seatech.ttsp.dchieu.DChieu2VO;
import com.seatech.ttsp.dchieu.DuyetKQDCVO;
import com.seatech.ttsp.dchieu.DuyetXNDChieu2VO;
import com.seatech.ttsp.dchieu.form.DuyetXNDChieu2Form;
import com.seatech.ttsp.proxy.giaodien.SendKQDC1;
import com.seatech.ttsp.proxy.pki.PKIService;

import java.io.InputStream;

import java.sql.Connection;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class DuyetXNDChieu2Action extends AppAction {
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "DCHIEU.XNHANDCHIEU2")) {
            return mapping.findForward("notRight");
        }
        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            DChieu1DAO daoDC = new DChieu1DAO(conn);
            DChieu2DAO daoDC2 = new DChieu2DAO(conn);
            DuyetXNDChieu2VO dcV0 = new DuyetXNDChieu2VO();
          DChieu2VO vo = new  DChieu2VO();
            DuyetXNDChieu2Form dc2From = (DuyetXNDChieu2Form)form;
            Collection colTHBKDC = new ArrayList();
//            Collection colDC2 = new ArrayList();
            Collection colSum = new ArrayList();
            String kb_nhan =
                session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
            String kb_code =
                session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();

            String strUD =
                " AND (a.trang_thai = '01' or (a.trang_thai = '02' and to_date(ngay_thien_dc)= to_date(sysdate))) and a.send_bank= " +
                kb_nhan;

            ArrayList<DuyetXNDChieu2VO> colDChieu =
                (ArrayList<DuyetXNDChieu2VO>)daoDC2.getListKQDC2(strUD, null);

            if (colDChieu.isEmpty()) {
                return mapping.findForward("success");
            }
            request.setAttribute("colDChieu", colDChieu);

            String rowSelected = request.getParameter("rowSelected");
            if (null == rowSelected || "".equals(rowSelected) ||
                "row_qt_0".equals(rowSelected)) {
                dcV0 = colDChieu.get(0);
                BeanUtils.copyProperties(dc2From, dcV0);
                request.setAttribute("rowSelected", "row_qt_0");
            } else {
                request.setAttribute("rowSelected", rowSelected);
            }

            String id = dc2From.getId();
            if (id != null && !"".equals(id)) {
                TTSPUtils spUtils = new TTSPUtils(conn);
                String strNoiDungKy = spUtils.getNoiDungKy(id, "067", "Y");
                dc2From.setNoi_dung_ky(strNoiDungKy);
            }


            String nhkb_chuyen = dc2From.getReceive_bank();
            String ngay_dc = dc2From.getNgay_dc();
            String bk_id = dc2From.getBk_id();
            SimpleDateFormat fromUser = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
            ngay_dc = myFormat.format(fromUser.parse(ngay_dc));
            if ("0001".equals(kb_code) || "0002".equals(kb_code) ||
                "0003".equals(kb_code)) {
              String strW =
              "select max(id) from sp_065 a where a.receive_bank = '" + nhkb_chuyen +
              "' and to_char(a.ngay_dc,'DD-MM-RRRR') = '" + ngay_dc +
              "'  and send_bank='"+kb_nhan+"' AND APP_TYPE='TTSP' and a.tthai_dxn_thop='02' and a.ket_qua_dxn_thop='0' " +
               " and to_date(ngay_dc)=trunc(ngay_thuc_hien_dc) ";
              colTHBKDC = daoDC.getTTSP_PHT(strW, null);
            } else {
              String strW =
              "select max(id) from sp_065 a where a.receive_bank = '" + nhkb_chuyen +
               "' and to_char(a.ngay_dc,'DD-MM-RRRR') = '" + ngay_dc +
               "' and send_bank='"+kb_nhan+"' AND a.APP_TYPE='TTSP' AND a.pht_id IS not null  and a.tthai_dxn_thop='02' and a.ket_qua_dxn_thop='0' " + 
               " and to_date(ngay_dc)=trunc(ngay_thuc_hien_dc) group by app_type ";
                colTHBKDC = daoDC.getTTSP_PHT(strW, null);
            }

            String strDC2 = "";
            strDC2 =
                    " AND a.id IN (SELECT qtoan_id FROM  sp_bk_dc2 WHERE bk_id ='" +
                    bk_id + "')";

          ArrayList<DChieu2VO> colDC2 =
              (ArrayList<DChieu2VO>)daoDC2.getChiThu(strDC2, null);
            colSum = daoDC2.getSumKChuyen(strDC2, null);

            if (colTHBKDC.isEmpty() && colDC2.isEmpty()) {
                return mapping.findForward("success");
            }
              if ( colDC2.size()==0){
                request.setAttribute("colThuChi", null);
                
              }else if ( colDC2.size() > 0){
                request.setAttribute("colThuChi", colDC2);
                vo=colDC2.get(0);
                String lai_phi=dcV0.getLai_phi();     
                if("03".equals(lai_phi)){
                  request.setAttribute("colTHBKDC", null);           
                  }else{
                  request.setAttribute("colTHBKDC", colTHBKDC); 
                  }
              }

          //            request.setAttribute("colTHBKDC", colTHBKDC);
//            request.setAttribute("colThuChi", colDC2);
            request.setAttribute("colSum", colSum);

            saveToken(request);
        } catch (Exception e) {
            throw e;
        } finally {
            conn.close();
        }
        return mapping.findForward(AppConstants.SUCCESS);
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "DCHIEU.XNHANDCHIEU2")) {
            return mapping.findForward("notRight");
        }

        Connection conn = null;
        try {
            if (isTokenValid(request)) {
                conn = getConnection(request);
                HttpSession session = request.getSession();
                DChieu2DAO daoDC2 = new DChieu2DAO(conn);
                DuyetXNDChieu2VO dcV0 = new DuyetXNDChieu2VO();
                DuyetXNDChieu2Form dc2From = (DuyetXNDChieu2Form)form;

                //verify
                boolean bVerifySignature = false;
                String strWSDL = getThamSoHThong("WSDL_PKI", session);
                PKIService pkiService = new PKIService(strWSDL);
                String strUserName =
                    session.getAttribute(AppConstants.APP_DOMAIN_SESSION) +
                    "\\" +
                    session.getAttribute(AppConstants.APP_USER_CODE_SESSION);
                String strSign = dc2From.getChuky_ktt();
                String strContent = dc2From.getNoi_dung_ky();
                String strCertSerial = dc2From.getCertserial();
                String strAppID = getThamSoHThong("APP_ID", session);
                String[] arrResultKy =
                    pkiService.VerifySignature(strUserName, strCertSerial,
                                               strContent, strSign, strAppID);
                if (arrResultKy != null && arrResultKy.length == 2) {
                    if (arrResultKy[0].equalsIgnoreCase("VALID")) {
                        bVerifySignature = true;
                    } else if (arrResultKy[0].equalsIgnoreCase("INVALID")) {
                        throw TTSPException.createException("TTSP-3001",
                                                            arrResultKy[1]);
                    } else if (arrResultKy[0].equalsIgnoreCase("ERROR")) {
                        throw TTSPException.createException("TTSP-3001",
                                                            arrResultKy[1]);
                    }
                }
                if (bVerifySignature) {

                    SendKQDC1 send = new SendKQDC1(conn);
                    String id = dc2From.getKq_id();
                    String strMaNH = dc2From.getReceive_bank();
                    if (!"".equals(id) && id != null) {
                        dcV0.setId(id);
                        dcV0.setChuky_ktt(strSign);
                        String user_id =
                            session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString();
                        String msg_id = "";
                             msg_id =send.sendMessageDC2(id, user_id);
                        dcV0.setMsg_id(msg_id);
                        daoDC2.updateDuyet(dcV0);
                        conn.commit();
                        request.setAttribute("duyettcong", "duyettcong");
                    }else request.setAttribute("duyettcong", "");
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

    public static final String REPORT_DIRECTORY = "/report";
    public static final String strFontTimeRoman = "/font/times.ttf";
    

    public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {

        Connection conn = null;
        StringBuffer sbSubHTML = new StringBuffer();
        InputStream reportStream = null;
        try {
            conn = getConnection(request);
            DuyetXNDChieu2Form f = (DuyetXNDChieu2Form)form;
            DChieu1DAO dao = new DChieu1DAO(conn);
            // là ng dùng dang su dung   f.getG_nsd_id()
            //Khai bao bien find.
            DuyetKQDCVO vo = new DuyetKQDCVO();
//            HttpSession session = request.getSession();
            String strid = f.getKq_id();
            String ket_qua=f.getKet_qua();
//            String user_id =
//                session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            if (strid != null && !"".equals(strid)) {
                String strWhere = "";
                strWhere += " AND a.id='"+strid+"'";
                vo = dao.getTSoDC2(strWhere, null);
                String p_nh = vo.getSend_bank();
                String p_kb = vo.getReceive_bank();
                String p_lan = vo.getLan_dc();
                String p_ngay = vo.getNgay_dc().replace("/", "-");


                sbSubHTML.append("<input type=\"hidden\" name=\"kq_id\" value=\"" +
                                 strid + "\" id=\"kq_id\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"p_kb\" value=\"" +
                                 p_kb + "\" id=\"p_kb\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"p_nh\" value=\"" +
                                 p_nh + "\" id=\"p_nh\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"p_lan\" value=\"" +
                                 p_lan + "\" id=\"p_lan\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"p_ngay\" value=\"" +
                                 p_ngay + "\" id=\"p_ngay\"></input>");
              sbSubHTML.append("<input type=\"hidden\" name=\"ket_qua\" value=\"" +
                               ket_qua + "\" id=\"ket_qua\"></input>");
                JasperPrint jasperPrint = null;
                HashMap parameterMap = new HashMap();
              parameterMap.put("REPORT_LOCALE",
                                             new java.util.Locale("vi", "VI"));
                ReportUtility rpUtilites = new ReportUtility();
              String fileName="";
                if (ket_qua.equals("0")){
                  fileName = "/rpt_PHoi_Kqua_Dchieu_2_kd";
                }
                else if(ket_qua.equals("1")){
                  fileName = "/rpt_PHoi_Kqua_Dchieu_2_kl";
                }
                reportStream =
                        getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY +
                                                                                                fileName +
                                                                                                ".jasper");
                parameterMap.put("p_MA_KB", p_kb);
                parameterMap.put("p_ID_KQ", strid);
                parameterMap.put("p_MA_NH", p_nh);
                parameterMap.put("p_LAN", p_lan);
                parameterMap.put("p_NGAY", p_ngay);

                jasperPrint =
                        JasperFillManager.fillReport(reportStream, parameterMap,
                                                     conn);

                String strTypePrintAction =
                    request.getParameter(AppConstants.REQUEST_ACTION) == null ?
                    "" :
                    request.getParameter(AppConstants.REQUEST_ACTION).toString();
                String strActionName = "printDuyetXNDChieu2Action.do";
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
