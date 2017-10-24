package com.seatech.ttsp.dchieu.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.exception.TTSPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DChieu1VO;
import com.seatech.ttsp.dchieu.DChieu2DAO;
import com.seatech.ttsp.dchieu.DChieu2VO;
import com.seatech.ttsp.dchieu.DuyetKQDCVO;
import com.seatech.ttsp.dchieu.KQDChieu1VO;
import com.seatech.ttsp.dchieu.form.DChieu2Form;
import com.seatech.ttsp.thamsokb.ThamSoKBDAO;
import com.seatech.ttsp.thamsokb.ThamSoKBVO;

import java.io.InputStream;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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


public class DChieu2Action extends AppAction {

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "DCHIEU.DC2")) {
            return mapping.findForward("notRight");
        }
        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            DChieu1DAO daoDC = new DChieu1DAO(conn);
            DChieu2DAO daoDC2 = new DChieu2DAO(conn);
            DChieu2VO dcV0 = new DChieu2VO();
          DChieu1VO dcV01 = new DChieu1VO();
           KQDChieu1VO kq1vo = new KQDChieu1VO();
            DChieu2Form dc2From = (DChieu2Form)form;
            Collection colTHBKDC = new ArrayList();
//            Collection colDC2 = new ArrayList();
            Collection colSum = new ArrayList();

            String kb_nhan =
                session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
            String id_kb =
                session.getAttribute(AppConstants.APP_NHKB_ID_SESSION).toString();
          String kb_code =
              session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();

            //String strUD = " AND (a.trang_thai = '00' or (to_date(a.ngay_thien_dc) = to_date(sysdate) and a.trang_thai <> '00')) and a.receive_bank= " + kb_nhan + " ORDER BY ngay_dc";

            String strUD =
                " AND ( a.trang_thai IS NULL OR a.trang_thai = '00' OR c.trang_thai ='01' OR  (c.trang_thai <> '00' " +
                " AND TO_DATE (a.ngay_thien_dc) = TO_DATE (SYSDATE)) OR (c.ket_qua='1'  and tthai_dmo='0' AND c.id IN (select max(id) from sp_kq_dc2  where send_bank = '" +
                kb_nhan + "' GROUP BY ngay_dc))) AND (a.receive_bank = '" +
                kb_nhan + "' OR a.receive_bank IS null)";
            String strNHKB =
                " nhkb_nhan=" + id_kb + " OR nhkb_chuyen=" + id_kb;

            ArrayList<DChieu2VO> colDChieu =
                (ArrayList<DChieu2VO>)daoDC2.getListBK(strUD, kb_nhan, strNHKB,
                                                       null);
            if (colDChieu.isEmpty()) {
                return mapping.findForward("success");
            }
            request.setAttribute("colDChieu", colDChieu);

            String rowSelected = request.getParameter("rowSelected");
            if (null == rowSelected || "".equals(rowSelected)) {
                dcV0 = colDChieu.get(0);
                BeanUtils.copyProperties(dc2From, dcV0);
                request.setAttribute("rowSelected", "row_qt_0");
            } else {

                request.setAttribute("rowSelected", rowSelected);
            }
            String nhkb_chuyen = dc2From.getSend_bank();
            String ngay_dc = dc2From.getNgay_dc();
            String bk_id = dc2From.getBk_id();
            dcV01= daoDC.checkSHKB(nhkb_chuyen, null);
          ThamSoKBVO kbVO = new ThamSoKBVO();
          ThamSoKBDAO kbDAO = new ThamSoKBDAO(conn);
          String strKB = "";
          strKB =
                  " and e.ten_ts='CHO_PHEP_DE_NGHI_QTOAN_TCONG_KHI_CHUA_DOI_CHIEU' AND a.kb_id=" +
                  id_kb + " AND b.ma_nh='" + nhkb_chuyen + "'";
          kbVO = kbDAO.getLoai_GD(strKB, null);

          String loai_gd = kbVO.getLoai_gd();
            
            if ("02".equals(loai_gd)||"03".equals(loai_gd)) {
                String strW =
                "select max(id) from sp_065 a where a.receive_bank = '" + nhkb_chuyen +
                "' and a.send_bank='"+kb_nhan+"' and to_char(a.ngay_dc,'DD-MM-RRRR') = '" + ngay_dc +
                "' AND APP_TYPE='TTSP' and a.tthai_dxn_thop='02' and a.ket_qua_dxn_thop='0' " +
                 " and to_date(ngay_dc)=trunc(ngay_thuc_hien_dc) ";
            colTHBKDC = daoDC.getTTSP_PHT(strW, null);
            ArrayList<KQDChieu1VO> colThuChi =
                (ArrayList<KQDChieu1VO>)daoDC.getThuChi(strW, null);
            if(colThuChi.isEmpty()){
              request.setAttribute("thu_chi", null);
            }
              else {
                String chkdate =
                    StringUtil.DateToString(new Date(), "dd-MM-yyyy");
                  if ((ngay_dc).equals(chkdate)|| "01-01".equals(StringUtil.DateToString(new Date(), "dd-MM"))){
                    kq1vo=colThuChi.get(0);
                    double thu = kq1vo.getKet_chuyen_thu().doubleValue();
                    double chi = kq1vo.getKet_chuyen_chi().doubleValue();
                    if (thu!=0 && chi !=0){
                      request.setAttribute("thu_chi", "thu_chi");
                    }else if ((thu!=0 && chi ==0)||(thu==0 && chi !=0)){
                      request.setAttribute("thu_or_chi", "thu_or_chi");
                    }
                  }
              }
          }else if ("01".equals(loai_gd)) {
            String strW =
            "select max(id) from sp_065 a where a.receive_bank = '" + nhkb_chuyen +
             "' and to_char(a.ngay_dc,'DD-MM-RRRR') = '" + ngay_dc +
             "' and a.send_bank='"+kb_nhan+"' AND a.APP_TYPE='TTSP' AND a.pht_id IS not null  and a.tthai_dxn_thop='02' and a.ket_qua_dxn_thop='0' " + 
             " and to_date(ngay_dc)=trunc(ngay_thuc_hien_dc) group by app_type ";            
              
            colTHBKDC = daoDC.getTTSP_PHT(strW, null);
                          
            ArrayList<KQDChieu1VO> colThuChi =
                (ArrayList<KQDChieu1VO>)daoDC.getThuChi(strW, null);
            
            if(colThuChi.isEmpty()){
              request.setAttribute("thu_chi", null);
            }
            else {
              String chkdate =
                  StringUtil.DateToString(new Date(), "dd-MM-yyyy");
                if ((ngay_dc).equals(chkdate)|| "01-01".equals(StringUtil.DateToString(new Date(), "dd-MM"))){
                  kq1vo=colThuChi.get(0);
                  double thu = kq1vo.getKet_chuyen_thu().doubleValue();
                  double chi = kq1vo.getKet_chuyen_chi().doubleValue();
                  if (thu!=0 && chi !=0){
                    request.setAttribute("thu_chi", "thu_chi");
                  }else if ((thu!=0 && chi ==0)||(thu==0 && chi !=0)){
                    request.setAttribute("thu_or_chi", "thu_or_chi");
                  }
                }
            }
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
              
              if (dc2From.getBk_id()!=null&&!"".equals(dc2From.getBk_id())){
                request.setAttribute("colThuChi", null);
                request.setAttribute("ko_qtoan", "ko_qtoan");
              }
              
            }else if ( colDC2.size() > 0){
              request.setAttribute("colThuChi", colDC2);
              dcV0=colDC2.get(0);
              String lai_phi=dcV0.getLai_phi();
            
            
            if("03".equals(lai_phi)){
              request.setAttribute("colTHBKDC", null);           
              }else{
              request.setAttribute("colTHBKDC", colTHBKDC); 
              }
            }
//            request.setAttribute("colTHBKDC", colTHBKDC);
            
            request.setAttribute("colSum", colSum);

          
            
        } catch (TTSPException e) {
            throw e;
        } finally {
          saveToken(request);
            conn.close();
        }
        return mapping.findForward(AppConstants.SUCCESS);
    }

    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "DCHIEU.DC2")) {
            return mapping.findForward("notRight");
        }

        Connection conn = null;
        try {
          if (isTokenValid(request)) {
            conn = getConnection(request);
            HttpSession session = request.getSession();

            DChieu2DAO dao = new DChieu2DAO(conn);
            DChieu1DAO dao1 = new DChieu1DAO(conn);
            DChieu2Form dc2Form = (DChieu2Form)form;
            DChieu2VO dcVO = new DChieu2VO();
            
          long thu_nh = dc2Form.getKet_chuyen_thu_nh();
          long chi_nh = dc2Form.getKet_chuyen_chi_nh();
          long thu_kb = dc2Form.getSo_ketchuyen();
          long chi_kb = dc2Form.getPs_chi();

            String kb_nhan =
                session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();

            String user_id =
                session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            String id_kb =
                session.getAttribute(AppConstants.APP_NHKB_ID_SESSION).toString();
            String nhkb_chuyen = dc2Form.getSend_bank();
            String ngay_dc = dc2Form.getNgay_dc();

            String strChkTT =
            " AND d1.ma_nh = '" + nhkb_chuyen +"' AND d1.ma_nh_kb = '" + kb_nhan +"' AND trunc(d1.ngay_gd+1) < TO_DATE ('" + ngay_dc + "', 'DD/MM/YYYY') AND TRUNC (d1.ngay_gd+1) >" + 
            " TRUNC (SYSDATE - 130) AND TO_CHAR (d1.ngay_gd+1, 'YYYYMMDD') NOT IN " + 
            " (SELECT ngay FROM sp_ngay_nghi)) AND send_bank='" +
            kb_nhan + "' AND receive_bank='" + nhkb_chuyen + "')";
            ArrayList<DChieu2VO> chkcol =
                (ArrayList<DChieu2VO>)dao.getCheckTThai(strChkTT, null);
            //manhnv-20150423***********************************************
//            if (chkcol.isEmpty()) {
//                DChieu1VO vo = new DChieu1VO();
//                String chkNgayBDau =               
//              " AND d1.ma_nh='" + nhkb_chuyen + "' and d1.ma_nh_kb='"+kb_nhan+"' AND TRUNC (d1.ngay_gd+1) < TO_DATE ('" +  ngay_dc + "', 'DD-MM-YYYY')" +
//              " AND TO_CHAR (d1.ngay_gd+1, 'YYYYMMDD') NOT IN (SELECT  ngay FROM sp_ngay_nghi)";
//
//                ArrayList<DChieu1VO> chkNgay =
//                    (ArrayList<DChieu1VO>)dao1.getNgayBDau(chkNgayBDau, null);
              
                if (chkcol.isEmpty()) {
                    DChieu1VO vo = new DChieu1VO();

                    ArrayList<DChieu1VO> chkNgay = (ArrayList<DChieu1VO>)dao1.getNgayBDau(kb_nhan, nhkb_chuyen, ngay_dc);
                //manhnv-20150423***********************************************
                vo = chkNgay.get(0);
                
              String strChkTT_bk =
              " AND d1.ma_nh = '" + nhkb_chuyen +"' AND d1.ma_nh_kb = '" + kb_nhan +"' AND trunc(d1.ngay_gd+1) < TO_DATE ('" + ngay_dc + "', 'DD/MM/YYYY') AND TRUNC (d1.ngay_gd+1) >" + 
              " TRUNC (SYSDATE - 130) AND TO_CHAR (d1.ngay_gd+1, 'YYYYMMDD') NOT IN " + 
              " (SELECT ngay FROM sp_ngay_nghi)) AND send_bank='" +
              nhkb_chuyen + "' AND receive_bank='" + kb_nhan + "'";
              if (!"".equals(vo.getNgay()) && vo.getNgay() != null){
              ArrayList<DChieu2VO> chkcolbk =
                  (ArrayList<DChieu2VO>)dao.getCheckTThai_bk2(strChkTT_bk, null);
                     dcVO = chkcolbk.get(0);
              }
                if ("".equals(vo.getNgay()) || vo.getNgay() == null||"98".equals(dcVO.getTrang_thai())) {
                    String bk_id = dc2Form.getBk_id();
                  dcVO = new DChieu2VO();
                    String strLan =
                        " AND to_char(insert_date,'YYYYMMDD') = to_char(SYSDATE,'YYYYMMDD') AND receive_bank='" +
                        nhkb_chuyen + "' and send_bank='"+kb_nhan+"'";
                    dcVO = dao.getLanGui(strLan, null);
                    
                    int chenh_time=   Integer.parseInt(dcVO.getChenh_time());
                    if(chenh_time <=1){
                      request.setAttribute("dxn_ltiep", "dxn_ltiep");
                      return mapping.findForward("success");
                    }
                    

                    long so_du_kb = dc2Form.getSo_du();

                    long sdu_dngay = dc2Form.getSo_du_dau_ngay();
                    long thu_chenh = Math.abs(thu_kb - thu_nh);
                    long chi_chenh = Math.abs(chi_kb - chi_nh);
                    long sdu_cngay = Math.abs(sdu_dngay - thu_nh - chi_nh);
                    long sdu_clech = Math.abs(so_du_kb - sdu_cngay);

                    dcVO.setBk_id(bk_id);
                    dcVO.setChenh_chi(chi_chenh);
                    dcVO.setChenh_thu(thu_chenh);
                    dcVO.setKet_chuyen_chi(chi_kb);
                    dcVO.setKet_chuyen_thu(thu_kb);
                    dcVO.setKet_chuyen_thu_nh(thu_nh);
                    dcVO.setKet_chuyen_chi_nh(chi_nh);
                    dcVO.setSdu_cngay(sdu_cngay);
                    dcVO.setNhkb_chuyen(nhkb_chuyen);
                    dcVO.setNhkb_nhan(kb_nhan);
                    dcVO.setNgay_dc(ngay_dc);
                    dcVO.setCreator(user_id);
                    dcVO.setSdu_clech(sdu_clech);


                    if (thu_chenh == 0 && chi_chenh == 0) {
                        //            update trang_thai_dc =2
                        dao.updateTThaiDC2(dcVO);
                        dcVO.setTthai_dmo("1"); // set trang thai dong
                        dao.insert(dcVO);
//                        conn.commit();
                        request.setAttribute("khopdung", "khopdung");
                    } else if (thu_chenh != 0 || chi_chenh != 0) {
                        //            update trang_thai_dc=1
                        dao.updateTThaiDC2(dcVO);
                        dcVO.setTthai_dmo("0");  // set trang thai mo                       
                        dao.insert(dcVO);
//                        conn.commit();
                        request.setAttribute("chenhlech", "chenhlech");
                    }
                } else {
                    request.setAttribute("kocobangke", "kocobangke");
                    return mapping.findForward("success");
                }
            } else {
                DChieu2VO vo1 = chkcol.get(0);
                if (!"02".equals(vo1.getTrang_thai())) {
                    request.setAttribute("chuataodxn", "chuataodxn");
                    return mapping.findForward("success");
                } else {
                    dcVO = new DChieu2VO();
                    String bk_id = dc2Form.getBk_id();

                    String strLan =
                        " AND to_char(insert_date,'YYYYMMDD') = to_char(SYSDATE,'YYYYMMDD') AND receive_bank='" +
                        nhkb_chuyen + "' and send_bank='"+kb_nhan+"'";
                  dcVO = dao.getLanGui(strLan, null);
                  int chenh_time=   Integer.parseInt(dcVO.getChenh_time());
                  if(chenh_time <=1){
                    request.setAttribute("dxn_ltiep", "dxn_ltiep");
                    return mapping.findForward("success");
                  }
                    long so_du_kb = dc2Form.getSo_du();

                    long sdu_dngay = dc2Form.getSo_du_dau_ngay();
                    long thu_chenh = Math.abs(thu_kb - thu_nh);
                    long chi_chenh = Math.abs(chi_kb - chi_nh);
                    long sdu_cngay = Math.abs(sdu_dngay - thu_nh - chi_nh);
                    long sdu_clech = Math.abs(so_du_kb - sdu_cngay);

                    dcVO.setBk_id(bk_id);
                    dcVO.setChenh_chi(chi_chenh);
                    dcVO.setChenh_thu(thu_chenh);
                    dcVO.setKet_chuyen_chi(chi_kb);
                    dcVO.setKet_chuyen_thu(thu_kb);
                    dcVO.setKet_chuyen_thu_nh(thu_nh);
                    dcVO.setKet_chuyen_chi_nh(chi_nh);
                    dcVO.setSdu_cngay(sdu_cngay);
                    dcVO.setNhkb_chuyen(nhkb_chuyen);
                    dcVO.setNhkb_nhan(kb_nhan);
                    dcVO.setNgay_dc(ngay_dc);
                    dcVO.setCreator(user_id);
                    dcVO.setSdu_clech(sdu_clech);


                    if (thu_chenh == 0 && chi_chenh == 0) {
                        //            update trang_thai_dc =2
                        dao.updateTThaiDC2(dcVO);
                      dcVO.setTthai_dmo("1"); // set trang thai dong
                        dao.insert(dcVO);
//                        conn.commit();
                        request.setAttribute("khopdung", "khopdung");
                    } else if (thu_chenh != 0 || chi_chenh != 0) {
                        //            update trang_thai_dc=1
                        dao.updateTThaiDC2(dcVO);
                      dcVO.setTthai_dmo("1"); // set trang thai mo
                        dao.insert(dcVO);
//                        conn.commit();
                        request.setAttribute("chenhlech", "chenhlech");
                    }
                }
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
  public static final String fileName = "/rpt_Bke_Dchieu_Thop_2";

  public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

      Connection conn = null;
      StringBuffer sbSubHTML = new StringBuffer();
      InputStream reportStream = null;
      try {
          conn = getConnection(request);
           DChieu2Form f = (DChieu2Form)form;
          DChieu1DAO dao = new DChieu1DAO(conn);

          DuyetKQDCVO vo = new DuyetKQDCVO();
          String bk_id = f.getBk_id();
          if (bk_id != null && !"".equals(bk_id)) {
              String strWhere = "";
              strWhere += " AND d.bk_id='"+bk_id+"'";
              vo = dao.getTSoDC2(strWhere, null);
              String p_nh = vo.getSend_bank();
              String p_kb = vo.getReceive_bank();
              String p_lan = vo.getLan_dc();
              String p_ngay = vo.getNgay_dc().replace("/", "-");
              String p_ten_kb= vo.getTen_nhang();
              String p_ten_nh= vo.getTen_huyen();


              sbSubHTML.append("<input type=\"hidden\" name=\"bk_id\" value=\"" +
                               bk_id + "\" id=\"bk_id\"></input>");
              sbSubHTML.append("<input type=\"hidden\" name=\"p_kb\" value=\"" +
                               p_kb + "\" id=\"p_kb\"></input>");
              sbSubHTML.append("<input type=\"hidden\" name=\"p_nh\" value=\"" +
                               p_nh + "\" id=\"p_nh\"></input>");
              sbSubHTML.append("<input type=\"hidden\" name=\"p_lan\" value=\"" +
                               p_lan + "\" id=\"p_lan\"></input>");
              sbSubHTML.append("<input type=\"hidden\" name=\"p_ngay\" value=\"" +
                               p_ngay + "\" id=\"p_ngay\"></input>");
              sbSubHTML.append("<input type=\"hidden\" name=\"p_ten_nh\" value=\"" +
                               p_ten_nh + "\" id=\"p_ten_nh\"></input>");
              sbSubHTML.append("<input type=\"hidden\" name=\"p_ten_kb\" value=\"" +
                               p_ten_kb + "\" id=\"p_ten_kb\"></input>");
              JasperPrint jasperPrint = null;
              HashMap parameterMap = new HashMap();
              ReportUtility rpUtilites = new ReportUtility();
              reportStream =
                      getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY +
                                                                                              fileName +
                                                                                              ".jasper");
              parameterMap.put("p_ID_BK", bk_id);
              parameterMap.put("p_MA_KB", p_kb);
              parameterMap.put("p_MA_NH", p_nh);
              parameterMap.put("p_TEN_KB", p_ten_kb);
              parameterMap.put("p_TEN_NH", p_ten_nh);
              parameterMap.put("p_LAN", p_lan);
              parameterMap.put("p_NGAY", p_ngay);

            parameterMap.put("REPORT_LOCALE",
                                           new java.util.Locale("vi", "VI"));
              jasperPrint =
                      JasperFillManager.fillReport(reportStream, parameterMap,
                                                   conn);
            
              String strTypePrintAction =
                  request.getParameter(AppConstants.REQUEST_ACTION) == null ?
                  "" :
                  request.getParameter(AppConstants.REQUEST_ACTION).toString();
              String strActionName = "printBKeDChieu2Action.do";
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
