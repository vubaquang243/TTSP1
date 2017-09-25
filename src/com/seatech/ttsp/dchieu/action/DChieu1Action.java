package com.seatech.ttsp.dchieu.action;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DChieu1VO;
import com.seatech.ttsp.dchieu.KQDChieu1VO;
import com.seatech.ttsp.dchieu.form.DChieu1Form;
import com.seatech.ttsp.dts.DTSoatDAO;
import com.seatech.ttsp.dts.DTSoatVO;
import com.seatech.ttsp.ltt.LTTDAO;
import com.seatech.ttsp.ltt.LTTVO;
import com.seatech.ttsp.thamso.ThamSoHThongDAO;
import com.seatech.ttsp.thamso.ThamSoHThongVO;

import java.io.PrintWriter;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.Statement;

import java.util.ArrayList;
import java.util.Collection;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
   * @modify: HungBM
   * @modify date: 05/11/2016   
   * @see: sua chuc nang doi chieu (thu cong) cho phu hop khi co doi chieu tu dong
   * @track: 201611-HUNGBM-DCTD
   * */
 /**
    * @modify: HungBM
    * @modify date: 04/05/2017   
    * @see: Update TT_DC_TU_DONG  = 02 chi khi Tao Dien Xac Nhan
    * @track: 20170504
    * */
public class DChieu1Action extends AppAction {

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "DCHIEU.DC1")) {
            return mapping.findForward("notRight");
        }

        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            DChieu1DAO dao = new DChieu1DAO(conn);
            DChieu1Form DCForm = (DChieu1Form)form;
            DChieu1VO vo = new DChieu1VO();
                       
            String kb_nhan = session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
            String id_kb = session.getAttribute(AppConstants.APP_NHKB_ID_SESSION).toString();

            ThamSoHThongVO tsVO= new ThamSoHThongVO();
            ThamSoHThongDAO tsDAO= new ThamSoHThongDAO(conn);
            String strTS = " id=123 and ten_ts='GIOI_HAN_NGAY_LIST_DSACH_DCHIEU'";
			//Lay tham so
            tsVO = tsDAO.getThamSo(strTS, null);
            String giatri_ts = tsVO.getGiatri_ts();
            String tu_ngay = "";
            if(!"0".equals(giatri_ts)&&!"".equals(giatri_ts)&&giatri_ts!=null){
                tu_ngay= "And TRUNC (d1.ngay_gd+1) > TRUNC (SYSDATE -"+giatri_ts+")";
            }
  

            String ma_nh = null;

            //Lay danh sach doi chieu
            ArrayList<DChieu1VO> colDChieu = (ArrayList<DChieu1VO>)dao.getListDC1(kb_nhan, id_kb,ma_nh,tu_ngay, null);
            if (colDChieu.isEmpty()) {
                return mapping.findForward("success");
            }

            request.setAttribute("colDChieu", colDChieu);

            String rowSelected = request.getParameter("rowSelected");

            if (null == rowSelected || "".equals(rowSelected)) {
                vo = colDChieu.get(0);
                BeanUtils.copyProperties(DCForm, vo);
                request.setAttribute("rowSelected", "row_qt_0");
            } else {
                request.setAttribute("rowSelected", rowSelected);
            }
			
            String id = DCForm.getId();
            if ((!"".equals(id) && id != null)) {
                String strUD2 =
                    " AND ( (b.trang_thai <> '04'" + " AND EXISTS (SELECT	 1 FROM	 sp_065" +
                    " WHERE	 bk_id = '" + id +
                    "' AND trang_thai IN ('01', '02', '00')))" +
                    " OR (b.trang_thai = '04' AND not EXISTS" +
                    " (SELECT	1 FROM	sp_065 WHERE	bk_id ='" + id + "'" +
                    " AND trang_thai IN ('01', '02', '00')) and b.id = (select max(id) from sp_065" +
                    " WHERE  bk_id ='" + id +
                    "' and b.trang_thai='04')) or b.trang_thai is null )" +
                    " AND  a.id= '" + id + "'";

                Collection colThop = new ArrayList();
				//Lay danh sach doi chieu tong hop
                colThop = dao.getDChieuList(strUD2, null);
                request.setAttribute("colThop", colThop);
            }
            String kq_id = DCForm.getKq_id();
            if (!"".equals(kq_id) && kq_id != null) {
                String strWhere = kq_id;
                Collection colKQDC = new ArrayList();
				//Lay ket qua doi chieu
                colKQDC = dao.getKQDChieu(strWhere, null);
                request.setAttribute("colKQDC", colKQDC);

                Collection colKQDCCT = new ArrayList();
				//Lay ket qua doi chieu chi tiet
                colKQDCCT = dao.getKQDCChiTiet(strWhere, null);
                request.setAttribute("colKQDCCT", colKQDCCT);
            }
            saveToken(request);
        } catch (Exception e) {
            throw e;

        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "DCHIEU.DC1")) {
            return mapping.findForward("notRight");
        }

        Connection conn = null;
        try {

            conn = getConnection(request);
            DChieu1DAO dao = new DChieu1DAO(conn);
            DChieu1Form DCForm = (DChieu1Form)form;
            Collection col = new ArrayList();

            String id = DCForm.getId();
            String strUD =
                " AND ( (b.trang_thai <> '04'" + " AND EXISTS (SELECT	 1 FROM	 sp_065" +
                " WHERE	 bk_id = '" + id +
                "' AND trang_thai IN ('01', '02', '00')))" +
                " OR (b.trang_thai = '04' AND not EXISTS" +
                " (SELECT	1 FROM	sp_065 WHERE	bk_id ='" + id + "'" +
                " AND trang_thai IN ('01', '02', '00'))) or b.trang_thai is null )" +
                " AND  a.id= '" + id + "'";

            String str_id = request.getParameter("row");
            //            DCForm.setRowSelected(str_id);
            request.setAttribute("rowSelected", str_id);
			
			//Lay danh sach doi chieu
            col = dao.getDChieuList(strUD, null);

            String strJSON;

            JSONObject jsonRes = new JSONObject();

            response.setHeader("X-JSON", jsonRes.toString());
            java.lang.reflect.Type listType =
                new TypeToken<Collection<DChieu1VO>>() {
            }.getType();
            strJSON = new Gson().toJson(col, listType);
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(strJSON.toString());
            out.flush();
            out.close();

        } catch (Exception e) {
            throw e;

        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }
    // xem ket qua Doi chieu

    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception {

        if (!checkPermissionOnFunction(request, "DCHIEU.DC1")) {
            return mapping.findForward("notRight");
        }
        Connection conn = null;
        HttpSession session = request.getSession();
        conn = getConnection(request);
        String strUD = "";
        //        Collection chkcol = new ArrayList();
        try {
            Long nUserID =
                new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
            String kb_chuyen =
                session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
            //            String id_kb =
            //                session.getAttribute(AppConstants.APP_NHKB_ID_SESSION).toString();
            //            if (isTokenValid(request)) {

            DChieu1Form DCForm = (DChieu1Form)form;
            DChieu1DAO dao = new DChieu1DAO(conn);
            KQDChieu1VO kqVO = new KQDChieu1VO();

            String id = DCForm.getId();


            if (id == null || "".equals(id) || "undefined".equals(id)) {
                return mapping.findForward(AppConstants.SUCCESS);
            }
            String strNG = " AND id='" + id + "'";
            kqVO = dao.getNgay_dc(strNG, null);
            String ngay_dc = kqVO.getNgay_dc();
			//201611-HUNGBM-DCTD Lay trang thai doi chieu tu dong 
            String tt_dc_tu_dong = kqVO.getTt_dc_tu_dong();

            //            }


            String send_bank = DCForm.getSend_bank();

            String strChkTT =
                " AND d1.ma_nh_kb = '" + kb_chuyen + "' AND trunc(d1.ngay_gd+1) < TO_DATE ('" +
                ngay_dc +
                "', 'DD/MM/YYYY') AND TO_CHAR (d1.ngay_gd+1, 'YYYYMMDD') NOT IN " +
                " (SELECT	ngay FROM	sp_ngay_nghi)) AND APP_type='TTSP' AND send_bank='" +
                kb_chuyen + "' AND receive_bank='" + send_bank + "')";
				//lay trang thai
            ArrayList<DChieu1VO> chkcol =
                (ArrayList<DChieu1VO>)dao.getCheckTThai(strChkTT, null);

            if (chkcol.isEmpty()) {
                DChieu1VO vo = new DChieu1VO();
                //manhnv-20150423***********************************************
//                String chkNgayBDau =
//                    " AND d1.ma_nh='" + send_bank + "' and d1.ma_nh_kb ='" +
//                    kb_chuyen + "'  AND TRUNC (d1.ngay_gd+1) < TO_DATE ('" +
//                    ngay_dc + "', 'DD-MM-YYYY')" +
//                    " AND TO_CHAR (d1.ngay_gd+1, 'YYYYMMDD') NOT IN (SELECT	 ngay FROM sp_ngay_nghi)";
                
                
                //HungBM - 19/10/2016 - Them dieu kien kiem tra xem ban ghi duoc doi chieu co dang Doi chieu TU dong ko
                if("00".equalsIgnoreCase(tt_dc_tu_dong)){
                  ArrayList<DChieu1VO> chkNgay =
                      (ArrayList<DChieu1VO>)dao.getNgayBDau(kb_chuyen, send_bank, ngay_dc);
                //manhnv-20150423***********************************************
                  vo = chkNgay.get(0);
                  if ("".equals(vo.getNgay()) || vo.getNgay() == null) {
                      ngay_dc =
                              StringUtil.DateToString(StringUtil.StringToDate(ngay_dc,
                                                                              "dd/MM/yyyy"),
                                                      "dd/MM/yyyy");
                      CallableStatement cs = null;
					//Goi procedure de doi chieu
                      cs =
   conn.prepareCall("{call sp_doi_chieu_pkg.proc_dc_truyen_tin(?,?,?,?,?,?)}");
                      cs.setString(1, id);
                      cs.setString(2, ngay_dc);
                      cs.setLong(3, nUserID);
                      cs.registerOutParameter(4, java.sql.Types.VARCHAR);
                      cs.registerOutParameter(5, java.sql.Types.VARCHAR);
                      cs.registerOutParameter(6, java.sql.Types.VARCHAR);
  
                      cs.execute();
  
                      String p_kq_id = cs.getString(4);
                      String p_err_code = cs.getString(5);
                      String p_err_desc = cs.getString(6);
                      
                      //201611-HUNGBM-DCTD
                      //Update loai doi chieu thu cong vao bang SP_065 (THUCONG)-begin
                      Statement stmt = null;
                      String strUpdate = "update SP_065 set TT_DC_TU_DONG ='THUCONG' where id='"+p_kq_id+"'";
                      stmt = conn.createStatement();
                      int rowUpdated = stmt.executeUpdate(strUpdate);
                      if (rowUpdated>0){
                        conn.commit();
                      }
					  //Update loai doi chieu thu cong vao bang SP_065 (THUCONG)-end
                      //update trang thai doi chieu tu dong trong bang SP_BK_DC1 (02=da doi chieu)-begin
//                      Statement stmtBK = null;
//                    
//                      String strUpdateBK = "update SP_BK_DC1 set TT_DC_TU_DONG ='02' where ID in (select MAX(a.id) from SP_BK_DC1 a, SP_065 b where  a.id=b.BK_ID AND b.ID='"+p_kq_id+"')";
//                      stmtBK = conn.createStatement();
//                      int rowUpdatedBK = stmtBK.executeUpdate(strUpdateBK);
//                      if (rowUpdatedBK>0){
//                        conn.commit();
//                      }
                      //update trang thai doi chieu tu dong trong bang SP_BK_DC1 (02=da doi chieu)-end
                      
                      if (!"0".equals(p_err_code)) {
                          request.setAttribute("err", p_err_desc);
                      } else {
                          strUD += p_kq_id;
                          Collection col = new ArrayList();
						  //Lay ket qua doi chieu
                          col = dao.getKQDChieu(strUD, null);
                          request.setAttribute("colKQDC", col);
  
                          Collection colKQDCCT = new ArrayList();
							//Lay ket qua doi chieu chi tiet
                          colKQDCCT = dao.getKQDCChiTiet(strUD, null);
                          request.setAttribute("colKQDCCT", colKQDCCT);
  
                          return mapping.findForward("success");
                      }
                  } else {
                      request.setAttribute("kocobangke", "kocobangke");					  
                      return mapping.findForward("success");
                  }
  				//hungBM: => Thong bao "da doi chieu tu dong" trong truong hop bang ke da dc d/c tu dong-begin
                }else {                    
                  request.setAttribute("da_dc_tu_dong", "da_dc_tu_dong");
                  return mapping.findForward("success");
                }
				//hungBM: => Thong bao "da doi chieu tu dong" trong truong hop bang ke da dc d/c tu dong-end
            } else {
                DChieu1VO vo = chkcol.get(0);
                if (!"02".equals(vo.getTthai_dxn_thop())&&!"03".equals(vo.getTthai_dxn_thop())) {
                    request.setAttribute("chuataodxn", "chuataodxn");
                    return mapping.findForward("success");
                } else {

                    ngay_dc =
                            StringUtil.DateToString(StringUtil.StringToDate(ngay_dc,
                                                                            "dd/MM/yyyy"),
                                                    "dd/MM/yyyy");
                    CallableStatement cs = null;
					//Thuc hien doi chieu
                    cs =
 conn.prepareCall("{call sp_doi_chieu_pkg.proc_dc_truyen_tin(?,?,?,?,?,?)}");
                    cs.setString(1, id);
                    cs.setString(2, ngay_dc);
                    cs.setLong(3, nUserID);
                    cs.registerOutParameter(4, java.sql.Types.VARCHAR);
                    cs.registerOutParameter(5, java.sql.Types.VARCHAR);
                    cs.registerOutParameter(6, java.sql.Types.VARCHAR);

                    cs.execute();

                    String p_kq_id = cs.getString(4);
                    String p_err_code = cs.getString(5);
                    String p_err_desc = cs.getString(6);

                    //201611-HUNGBM-DCTD
                    //Update loai doi chieu thu cong vao bang SP_065 (THUCONG)-begin
                    Statement stmt = null;
                    String strUpdate = "update SP_065 set TT_DC_TU_DONG ='THUCONG' where id='"+p_kq_id+"'";
                    stmt = conn.createStatement();
                    int rowUpdated = stmt.executeUpdate(strUpdate);                   
					//Update loai doi chieu thu cong vao bang SP_065 (THUCONG)-end	
                    conn.commit();

                    if (!"0".equals(p_err_code)) {
                        request.setAttribute("err", p_err_desc);
                    } else {
                        strUD += p_kq_id;
                        Collection col = new ArrayList();
						//Lay ket qua doi chieu
                        col = dao.getKQDChieu(strUD, null);
                        request.setAttribute("colKQDC", col);

                        Collection colKQDCCT = new ArrayList();
						//Lay ket qua doi chieu chi tiet
                        colKQDCCT = dao.getKQDCChiTiet(strUD, null);
                        request.setAttribute("colKQDCCT", colKQDCCT);
                    }
                }
            }
          
        } catch (Exception e) {
            throw e;

        } finally {
            close(conn);
        }

        return mapping.findForward("success");
    }
    // Update TThai va KQua tao dien xac nhan

    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "DCHIEU.DC1")) {
            return mapping.findForward("notRight");
        }

		
        //201611-HUNGBM-DCTD-sua chuc nang tao dien xac nhan - Begin
        boolean tuDong = true;        
        Connection conn = null;
        try {
            if (tuDong ==true){

              conn = getConnection(request);
              HttpSession session = request.getSession();
              DChieu1DAO dao = new DChieu1DAO(conn);
              DChieu1Form DCForm = (DChieu1Form)form;
              KQDChieu1VO dcVO = null;
              //                String id_kb =
              //                    session.getAttribute(AppConstants.APP_NHKB_ID_SESSION).toString();
              String nhkb_chuyen =
                  session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();

              String maNH = DCForm.getSend_bank();
              String ngay_dc = DCForm.getNgay_dc();
              String id = DCForm.getId();
              Collection dxncol = new ArrayList();
              if (id == null || "".equals(id)) {
                  return mapping.findForward(AppConstants.SUCCESS);
              }
              String strChkTT =
                  " AND d1.ma_nh_kb = '" + nhkb_chuyen + "' AND trunc(d1.ngay_gd+1) < TO_DATE ('" +
                  ngay_dc +
                  "', 'DD/MM/YYYY') AND TO_CHAR (d1.ngay_gd+1, 'YYYYMMDD') NOT IN " +
                  " (SELECT ngay FROM sp_ngay_nghi)) AND APP_type='TTSP' AND send_bank='" +
                  nhkb_chuyen + "' AND receive_bank='" + maNH + "')";
              ArrayList<DChieu1VO> chkcol =
                  (ArrayList<DChieu1VO>)dao.getCheckTThai(strChkTT,
                                                          null); // check trang thai ngay doi chieu sysdate-1

              if (chkcol.isEmpty()) { 
                  String strUD =
                      " and a.bk_id='" + id + "' and a.trang_thai='00'";
                  dcVO = new KQDChieu1VO();
                  dcVO = dao.getKQPHT(strUD, null);
                  // check lay thong tin ket qua va chenh lech thu cong

                  String send_bank = DCForm.getSend_bank();
                  String strKq =
                      " AND send_bank= '" + nhkb_chuyen + "' AND receive_bank='" +
                      send_bank + "' AND app_type='TTSP' ";
                  dxncol =
                          dao.getCheckKQDXN(strKq, null); // check tthai_dxn_thop

                  if (dxncol.size() != 0) { // ton tai tthai_dxn_thop=01
                      request.setAttribute("createF", "createF");
                      return mapping.findForward("failure");
                  } else if (dxncol.isEmpty()) { // ko ton tai

                      String tthai = dcVO.getTrang_thai_bkdc();
                      BeanUtils.copyProperties(form,
                                               dcVO); // copy value tu VO vao form
                      if ((id == null || "".equals(id)) )
                         //HungBM: Bay gio du chenh lech hay khop dung deu duoc tao dien xac nhan
                         // ||
                         // !"00".equals(tthai)) 
                      {
                          return mapping.findForward("failure");
                      }

                      long clech = dcVO.getChenh_lech();
                      String kq = dcVO.getKet_qua();
                      String kq_id = DCForm.getId();

                      if (kq_id == null || "".equals(kq_id)) {
                          return mapping.findForward("failure");
                      }
                      dcVO = new KQDChieu1VO();
                      dcVO.setBk_id(id);
                      dcVO.setId(kq_id);
                      dcVO.setKet_qua(kq);
                      dcVO.setChenh_lech(clech);

                      if ("1".equals(kq)) { // chenh lech
                          dao.updateKQ(dcVO);
                        //20170504 - BEGIN
                        dcVO.setTt_dc_tu_dong("02");
                        //20170504 - END
                          dao.updateTTBK(dcVO);
                          //Hungbm: update sp_065.tthai_dxn_thop => 01 de
                          //Sau khi bam "Tao Dien Xac Nhan" thi Colum "Trang thai" thanh : "Chenh lech - Cho Duyet"
                          dcVO.setTthai_dxn_thop("01");
                          dao.DuyetDCTHop(dcVO);
                          //end
                          conn.commit();
                          request.setAttribute("DXN_clech", "DXN_clech");
                      } else if ("0".equals(kq)) {
                          dao.updateKQ(dcVO);
                        //20170504 - BEGIN
                        dcVO.setTt_dc_tu_dong("02");
                        //20170504 - END
                          dao.updateTTBK(dcVO);
                          conn.commit();
                          request.setAttribute("DXN_dung", "DXN_dung");
                      }

                      //                        }
                      return mapping.findForward("success");
                  } else {
                      request.setAttribute("kocobangke", "kocobangke");
                      return mapping.findForward("success");
                  }
              } else {
                DChieu1VO vo = chkcol.get(0);                
                if (!"02".equals(vo.getTthai_dxn_thop())&&!"03".equals(vo.getTthai_dxn_thop())) {
                    request.setAttribute("chuataodxn", "chuataodxn");
                    return mapping.findForward("success");
                } else {
                    String strUD =
                        " and a.bk_id='" + id + "' and a.trang_thai='00'";
                    dcVO = new KQDChieu1VO();
                    dcVO = dao.getKQPHT(strUD, null);
                    if ("".equals(dcVO) || dcVO == null) {
                        return mapping.findForward("failure");
                    }
                    String send_bank = DCForm.getSend_bank();
                    String strKq =
                        " AND send_bank= '" + nhkb_chuyen + "' AND receive_bank='" +
                        send_bank + "' AND app_type='TTSP' ";
                    dxncol = dao.getCheckKQDXN(strKq, null);

                    if (dxncol.size() != 0) {
                        request.setAttribute("createF", "createF");
                        return mapping.findForward("failure");
                    } else if (dxncol.isEmpty()) {

                        String tthai = dcVO.getTrang_thai_bkdc();
                        BeanUtils.copyProperties(form, dcVO);
                        
                        //Hungbm: Theo yeu cau cua DungNT
                        //Khi bang ke chenh lech (sp_bk_dc1.trang_thai=01)
                        //thi van cho tao dien xac nhan => Du trang_thai la bn cung van cho gui
                        
                        if ((id == null || "".equals(id))
                          //  ||!"00".equals(tthai)
                        ) {
                            return mapping.findForward("failure");
                        }

                        long clech = dcVO.getChenh_lech();
                        String kq = dcVO.getKet_qua();
                        String kq_id = DCForm.getId();

                      if (kq_id == null || "".equals(kq_id)){
                          return mapping.findForward("failure");
                      }

                        dcVO = new KQDChieu1VO();

                         dcVO.setBk_id(id);
                         dcVO.setId(kq_id);
                        dcVO.setKet_qua(kq);
                        dcVO.setChenh_lech(clech);

                        if ("1".equals(kq)) { // chenh lech
                            //Hungbm: update sp_065.tthai_dxn_thop => 01 de
                            //Sau khi bam "Tao Dien Xac Nhan" thi Colum "Trang thai" thanh : "Chenh lech - Cho Duyet"
                            dcVO.setTthai_dxn_thop("01");
                            dao.DuyetDCTHop(dcVO);
                            //end
                            dao.updateKQ(dcVO);
                          //20170504 - BEGIN
                          dcVO.setTt_dc_tu_dong("02");
                          //20170504 - END
                            dao.updateTTBK(dcVO);
                             conn.commit();
                            request.setAttribute("DXN_clech", "DXN_clech");
                        } else if ("0".equals(kq)) {
                            dao.updateKQ(dcVO);
                          //20170504 - BEGIN
                          dcVO.setTt_dc_tu_dong("02");
                          //20170504 - END
                            dao.updateTTBK(dcVO);
                              conn.commit();
                            request.setAttribute("DXN_dung", "DXN_dung");
                        }

                    }
                  }
              }
            }else
			//201611-HUNGBM-DCTD-sua chuc nang tao dien xac nhan - End
            if (isTokenValid(request)) {
                conn = getConnection(request);
                HttpSession session = request.getSession();
                DChieu1DAO dao = new DChieu1DAO(conn);
                DChieu1Form DCForm = (DChieu1Form)form;
                KQDChieu1VO dcVO = null;
                //                String id_kb =
                //                    session.getAttribute(AppConstants.APP_NHKB_ID_SESSION).toString();
                String nhkb_chuyen =
                    session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();

                String maNH = DCForm.getSend_bank();
                String ngay_dc = DCForm.getNgay_dc();
                String id = DCForm.getId();
                Collection dxncol = new ArrayList();
                if (id == null || "".equals(id)) {
                    return mapping.findForward(AppConstants.SUCCESS);
                }

                String strChkTT =
                    " AND d1.ma_nh_kb = '" + nhkb_chuyen + "' AND trunc(d1.ngay_gd+1) < TO_DATE ('" +
                    ngay_dc +
                    "', 'DD/MM/YYYY') AND TO_CHAR (d1.ngay_gd+1, 'YYYYMMDD') NOT IN " +
                    " (SELECT ngay FROM sp_ngay_nghi)) AND APP_type='TTSP' AND send_bank='" +
                    nhkb_chuyen + "' AND receive_bank='" + maNH + "')";
                ArrayList<DChieu1VO> chkcol =
                    (ArrayList<DChieu1VO>)dao.getCheckTThai(strChkTT,
                                                            null); // check trang thai ngay doi chieu sysdate-1

                if (chkcol.isEmpty()) { 
                    String strUD =
                        " and a.bk_id='" + id + "' and a.trang_thai='00'";
                    dcVO = new KQDChieu1VO();
                    dcVO = dao.getKQPHT(strUD, null);
                    // check lay thong tin ket qua va chenh lech thu cong

                    String send_bank = DCForm.getSend_bank();
                    String strKq =
                        " AND send_bank= '" + nhkb_chuyen + "' AND receive_bank='" +
                        send_bank + "' AND app_type='TTSP' ";
                    dxncol =
                            dao.getCheckKQDXN(strKq, null); // check tthai_dxn_thop

                    if (dxncol.size() != 0) { // ton tai tthai_dxn_thop=01
                        request.setAttribute("createF", "createF");
                        return mapping.findForward("failure");
                    } else if (dxncol.isEmpty()) { // ko ton tai

                        String tthai = dcVO.getTrang_thai_bkdc();
                        BeanUtils.copyProperties(form,
                                                 dcVO); // copy value tu VO vao form
                        if ((id == null || "".equals(id)) ||
                            !"00".equals(tthai)) {
                            return mapping.findForward("failure");
                        }

                        long clech = dcVO.getChenh_lech();
                        String kq = dcVO.getKet_qua();
                        String kq_id = DCForm.getId();

                        if (kq_id == null || "".equals(kq_id)) {
                            return mapping.findForward("failure");
                        }
                        dcVO = new KQDChieu1VO();
                        dcVO.setBk_id(id);
                        dcVO.setId(kq_id);
                        dcVO.setKet_qua(kq);
                        dcVO.setChenh_lech(clech);

                        if ("1".equals(kq)) { // chenh lech
                            dao.updateKQ(dcVO);
                            dao.updateTTBK(dcVO);
                            conn.commit();
                            request.setAttribute("DXN_clech", "DXN_clech");
                        } else if ("0".equals(kq)) {
                            dao.updateKQ(dcVO);
                            dao.updateTTBK(dcVO);
                            conn.commit();
                            request.setAttribute("DXN_dung", "DXN_dung");
                        }

                        //                        }
                        return mapping.findForward("success");
                    } else {
                        request.setAttribute("kocobangke", "kocobangke");
                        return mapping.findForward("success");
                    }
                } else {
                  DChieu1VO vo = chkcol.get(0);
                  if (!"02".equals(vo.getTthai_dxn_thop())&&!"03".equals(vo.getTthai_dxn_thop())) {
                      request.setAttribute("chuataodxn", "chuataodxn");
                      return mapping.findForward("success");
                  } else {
                      String strUD =
                          " and a.bk_id='" + id + "' and a.trang_thai='00'";
                      dcVO = new KQDChieu1VO();
                      dcVO = dao.getKQPHT(strUD, null);
                      if ("".equals(dcVO) || dcVO == null) {
                          return mapping.findForward("failure");
                      }
                      String send_bank = DCForm.getSend_bank();
                      String strKq =
                          " AND send_bank= '" + nhkb_chuyen + "' AND receive_bank='" +
                          send_bank + "' AND app_type='TTSP' ";
                      dxncol = dao.getCheckKQDXN(strKq, null);

                      if (dxncol.size() != 0) {
                          request.setAttribute("createF", "createF");
                          return mapping.findForward("failure");
                      } else if (dxncol.isEmpty()) {

                          String tthai = dcVO.getTrang_thai_bkdc();
                          BeanUtils.copyProperties(form, dcVO);
                          if ((id == null || "".equals(id)) ||
                              !"00".equals(tthai)) {
                              return mapping.findForward("failure");
                          }

                          long clech = dcVO.getChenh_lech();
                          String kq = dcVO.getKet_qua();
                          String kq_id = DCForm.getId();

                        if (kq_id == null || "".equals(kq_id)){
                            return mapping.findForward("failure");
                        }

                          dcVO = new KQDChieu1VO();

                           dcVO.setBk_id(id);
                           dcVO.setId(kq_id);
                          dcVO.setKet_qua(kq);
                          dcVO.setChenh_lech(clech);

                          if ("1".equals(kq)) { // chenh lech
                              dao.updateKQ(dcVO);
                              dao.updateTTBK(dcVO);
                               conn.commit();
                              request.setAttribute("DXN_clech", "DXN_clech");
                          } else if ("0".equals(kq)) {
                              dao.updateKQ(dcVO);
                              dao.updateTTBK(dcVO);
                                conn.commit();
                              request.setAttribute("DXN_dung", "DXN_dung");
                          }

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

    // update lai trang thai <Duyet Lai>

    public ActionForward updateExc(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        //      if (!checkPermissionOnFunction(request, "DCHIEU.DC1")) {
        //          return mapping.findForward("notRight");
        //      }

        Connection conn = null;
        try {
            //          if (isTokenValid(request)) {
            //            String strMsgId = null;
            DChieu1Form frm = (DChieu1Form)form;
            conn = getConnection(request);
            LTTDAO lttDAO = new LTTDAO(conn);
            DTSoatDAO dtsDAO = new DTSoatDAO(conn);
            DTSoatVO dtsVO = new DTSoatVO();
            DChieu1DAO dao = new DChieu1DAO(conn);
            KQDChieu1VO kqVO = new KQDChieu1VO();
            LTTVO lttVO = new LTTVO();
            Long mt_id =
                Long.parseLong(frm.getMt_id() == null ? "" : frm.getMt_id());
            String type = frm.getType();
            lttVO.setId(mt_id);
            if ("LTT".equals(type)) {
                String strW = " AND id=" + mt_id;
                lttVO = lttDAO.checkGDDuyet(strW, null);
                Long gd_duyet = lttVO.getGd_duyet();
                if (gd_duyet != null && 0 != gd_duyet) {
                    lttVO.setId(mt_id);
                    lttVO.setTrang_thai("05");
                    lttDAO.update(lttVO);
                    //                            conn.commit();
                } else if (gd_duyet == null || 0 == gd_duyet) {
                    lttVO.setId(mt_id);
                    lttVO.setTrang_thai("03");
                    lttDAO.update(lttVO);
                    //                            conn.commit();
                }
            } else if ("DTS".equals(type)) {

                dtsVO.setId(frm.getMt_id());
                dtsVO.setTrang_thai("02");
                dtsDAO.update(dtsVO);
                //                      conn.commit();
            }
            kqVO.setMt_id(mt_id.toString());
            kqVO.setTthai_duyet("01");
            dao.updateTTCTiet(kqVO);
            conn.commit();

            //          }
            //            resetToken(request);
            //            saveToken(request);
        } catch (Exception e) {
            throw e;

        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }


}
