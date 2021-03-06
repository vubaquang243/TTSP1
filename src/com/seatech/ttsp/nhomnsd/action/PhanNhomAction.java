package com.seatech.ttsp.nhomnsd.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.nhomnsd.NhomNSDDAO;
import com.seatech.ttsp.nhomnsd.NhomNSDVO;
import com.seatech.ttsp.nhomnsd.PhanNhomDAO;
import com.seatech.ttsp.nhomnsd.PhanNhomVO;
import com.seatech.ttsp.nhomnsd.form.PhanNhomForm;
import com.seatech.ttsp.user.UserDAO;
import com.seatech.ttsp.user.UserHistoryDAO;
import com.seatech.ttsp.user.UserHistoryVO;

import com.seatech.ttsp.user.UserVO;

import java.sql.Connection;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class PhanNhomAction extends AppAction {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        Connection conn = null;
        String strWhere = null;
        Vector vParam = null;
        PhanNhomForm f = null;
        Collection colNSDNgoaiNhom = null;
        Collection colNSDThuocNhom = null;
        String quyen = null;
        String strKBId = "";
        int iCheck = 0;
        try {
            if (!checkPermissionOnFunction(request,
                                           "QLY_NSD.QLY_NHOM.PHAN_NHOM")) {
                return mapping.findForward(AppConstants.MAPING_NO_RIGHT);
            }
            conn = getConnection(request);
            f = (PhanNhomForm)form;
            //lay ra quyen su dung
            HttpSession session = request.getSession();
            quyen =
                    (session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION).toString());
            //Lay ra nhom NSD fill to select box
            NhomNSDDAO nhomDAO = new NhomNSDDAO(conn);

            String[] temp = quyen.split("\\|");
            StringBuffer str1 = null;
            StringBuffer str2 = null;
            String str3 = "";
            for (int i = 0; i < temp.length; i++) {
                if (temp[i].toString().equalsIgnoreCase("QTHT-TW")) {
                    // THI CAU LENH
                    str1 = new StringBuffer();
                    str3 = " and a.id not in (347) ";
                    str1.append("'QTHT-DV','CB-TTTT','CBPT-TTTT','TTV','KTT','GD'");
                }
                if (temp[i].toString().equalsIgnoreCase("CB-TTTT") ||
                    temp[i].toString().equalsIgnoreCase("CBPT-TTTT")) {
                    if (null != str1) {
                        str2 = new StringBuffer();
                        str2.append(",'KTT','GD'");
                        str1.append(str2);
                    } else {
                        str1 = new StringBuffer();
                        str1.append("'KTT','GD'");
                    }
                }
                if (temp[i].toString().equalsIgnoreCase("QTHT-DV")) {
                    strKBId =
                            session.getAttribute(AppConstants.APP_KB_ID_SESSION) ==
                            null ? "" :
                            session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString().trim();
                    if (f.getKb_id() != null) {
					//   08/11/2017 taidd x�a code lay danh muc KB theo cap user begin
                        if (!f.getKb_id().equals(strKBId)) {
                              DMKBacDAO dmkbDAO = new DMKBacDAO(conn);
                              Vector params = new Vector();
                              params.add(new Parameter(f.getKb_id(), true));
                              DMKBacVO dmkbVO =
                                  dmkbDAO.getDMKB(" AND  a.id = ?", params);
          // 15/11/2017 tdd th�thay ??i iCheck = 0001 khi ma_kb = 0001 => kh�ng select nsd                 
                              if( f.getMa_kb().equals("0001")){
                                iCheck = 1;
                              }else  if (!strKBId.equals(dmkbVO.getId_cha().trim())) {
                                iCheck = 1;
                              }
                          }
                        
                    }
                    
                    if (null != str1) {
                        str2 = new StringBuffer();
                        str2.append(",'TTV','GD','KTT'");
                        str1.append(str2);
                    } else {
                        str1 = new StringBuffer();
                        str1.append("'TTV','GD','KTT'");
                    }
                  str3 = " and a.id in (345, 346,347, 2000)";

                }
            }
            strWhere = "loai_nhom in (" + str1 + ")" + str3;
            Collection colNhomNSD = nhomDAO.getNhomNSDList(strWhere, vParam);

            UserDAO uDAO = new UserDAO(conn);
            if (f.getKb_id() != null && f.getNhom_id() != null && iCheck == 0) {
                //Lay ra danh sach NSD ngoai nhom
                strWhere = "  a.kb_id = ? ";
                strWhere += "and a.trang_thai = 01 ";

                strWhere +=
                        "and a.id not in ( select nsd_id from sp_nsd_nhom where nhom_id = ? )";
                vParam = new Vector();
                vParam.add(new Parameter(f.getKb_id(), true));
                vParam.add(new Parameter(f.getNhom_id(), true));

                colNSDNgoaiNhom = uDAO.getUserList(strWhere, vParam);

                //Lay ra danh sach NSD thuoc nhom
                strWhere = " a.kb_id = ? ";

                strWhere += "and a.trang_thai = 01 ";
                strWhere +=
                        "and a.id in ( select nsd_id from sp_nsd_nhom where nhom_id = ? )";
                vParam = new Vector();
                vParam.add(new Parameter(f.getKb_id(), true));
                vParam.add(new Parameter(f.getNhom_id(), true));
                colNSDThuocNhom = uDAO.getUserList(strWhere, vParam);
            }
            //Set attribute
            request.setAttribute("colNhomNSD", colNhomNSD);
            request.setAttribute("colNSDNgoaiNhom", colNSDNgoaiNhom);
            request.setAttribute("colNSDThuocNhom", colNSDThuocNhom);
            if(iCheck == 1){
              request.setAttribute("note", "M&#227; KBNN kh&#244;ng h&#7907;p l&#7879;, ph&#7843;i nh&#7853;p m&#227; KBNN huy&#7879;n tr&#7921;c thu&#7897;c t&#7881;nh");
            }else if(iCheck == 0){
              request.setAttribute("note", "");
            }
            resetToken(request);
            saveToken(request);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(AppConstants.SUCCESS);
    }

    public ActionForward addExc(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        Connection conn = null;
        String strNhanVienID = null;
        String strCurrentUserID = null;
        String whereClause = "";
        Collection cllNSD = null;
        String strnhomnv = "";
        boolean checkUser = false;
        try {
            if (!isTokenValid(request))
                return executeAction(mapping, form, request, response);
            HttpSession session = request.getSession();
            if (session == null)
                return mapping.findForward("login");
            //Lay phan nhom tren session
            strCurrentUserID =
                    session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            String strAddNSD = request.getParameter("arrAddF");
            String strRemoveNSD = request.getParameter("arrRemoveF");
            Object[] arrAddNSD = strAddNSD.split(",~");
            Object[] arrRemoveNSD = strRemoveNSD.split(",~");
            PhanNhomForm f = (PhanNhomForm)form;
            conn = getConnection(request);
            PhanNhomDAO phannhomDAO = new PhanNhomDAO(conn);
            NhomNSDDAO nhomDAO = new NhomNSDDAO(conn);
            UserDAO UsDAO = new UserDAO(conn);
            //Them NSD vao nhom
            PhanNhomVO phanNhomVO = null;
            UserHistoryVO userHisVO = null;
            UserHistoryDAO userHisDAO = new UserHistoryDAO(conn);
            for (int i = 0; i < arrAddNSD.length; i++) {
                if (arrAddNSD[i] == null)
                    continue;
                strNhanVienID = arrAddNSD[i].toString();
                if ("".equalsIgnoreCase(strNhanVienID.trim()))
                    continue;
                //Build VO
                phanNhomVO = new PhanNhomVO();
                phanNhomVO.setNsd_id(new Long(strNhanVienID));
                phanNhomVO.setNhom_id(new Long(f.getNhom_id()));
                phanNhomVO.setCreated_by(new Long(strCurrentUserID));
                
               //20171206 thuongdt kiem tra khong cho phep 1 user chi 1 quyen chuc nang, tru ttv va ktt begin                
                whereClause = " and a.LOAI_NHOM not in ('"+AppConstants.NSD_TTV+"','"+AppConstants.NSD_KTT+"') and b.nsd_id = '"+strNhanVienID+"' ";
                 cllNSD = nhomDAO.getNhomNSDListByUser(whereClause, null) ;
                
                if(cllNSD == null  || cllNSD.isEmpty()){
                 whereClause = " and a.LOAI_NHOM in ('"+AppConstants.NSD_TTV+"','"+AppConstants.NSD_KTT+"') and b.nsd_id = '"+strNhanVienID+"' ";
                 cllNSD = nhomDAO.getNhomNSDListByUser(whereClause, null) ;
                 
                 NhomNSDVO nhVO = nhomDAO.getNhomNSD(" id = '"+f.getNhom_id()+"' ",null);
                 if(cllNSD != null  && !cllNSD.isEmpty() ){
                 if(nhVO != null &&(nhVO.getLoai_nhom().equals(AppConstants.NSD_TTV) || nhVO.getLoai_nhom().equals(AppConstants.NSD_KTT))){
                    phannhomDAO.insertNsd_nhom(phanNhomVO);
                    // Luu lich su thay doi
                    String strNoiDung = "Them NSD vao nhom id = " + f.getNhom_id();
                    userHisVO = new UserHistoryVO();
                    userHisVO.setNguoi_tdoi(new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString()));
                    userHisVO.setNoi_dung_thaydoi(strNoiDung);
                    userHisVO.setNsd_id(new Long(strNhanVienID));
                    userHisDAO.insert(userHisVO);                     
                 }else{
                    UserVO uVO = UsDAO.getUser("id = '"+strNhanVienID+"'", null);
                       if(uVO != null && uVO.getMa_nsd() != null)
                           strnhomnv = strnhomnv+uVO.getMa_nsd()+",";
                  }                 
                 }else{
                   phannhomDAO.insertNsd_nhom(phanNhomVO);
                  // Luu lich su thay doi
                  String strNoiDung = "Them NSD vao nhom id = " + f.getNhom_id();
                  userHisVO = new UserHistoryVO();
                  userHisVO.setNguoi_tdoi(new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString()));
                  userHisVO.setNoi_dung_thaydoi(strNoiDung);
                  userHisVO.setNsd_id(new Long(strNhanVienID));
                  userHisDAO.insert(userHisVO);
                 }
              }else{
               UserVO uVO = UsDAO.getUser("id = '"+strNhanVienID+"'", null);
                  if(uVO != null && uVO.getMa_nsd() != null)
                      strnhomnv = strnhomnv+uVO.getMa_nsd()+",";
              }
              //20171206 thuongdt kiem tra khong cho phep 1 user chi 1 quyen chuc nang, tru ttv va ktt end
            }
            //Xoa NSD khoi nhom
            for (int i = 0; i < arrRemoveNSD.length; i++) {
                if (arrRemoveNSD[i] == null)
                    continue;
                strNhanVienID = arrRemoveNSD[i].toString();
                if ("".equalsIgnoreCase(strNhanVienID.trim()))
                    continue;
                phannhomDAO.delete(new Long(strNhanVienID),
                                   new Long(f.getNhom_id()));

                // Luu lich su thay doi
                String strNoiDung = "Xoa NSD khoi nhom id = " + f.getNhom_id();
                userHisVO = new UserHistoryVO();
                userHisVO.setNguoi_tdoi(new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString()));
                userHisVO.setNoi_dung_thaydoi(strNoiDung);
                userHisVO.setNsd_id(new Long(strNhanVienID));
                userHisDAO.insert(userHisVO);
            }
           //20171206 thuongdt tra thong bao user khong duoc phan quyen  
            request.setAttribute("nhomnv", strnhomnv);
            saveVisitLog(conn, session, "QLY_NSD.QLY_NHOM.PHAN_NHOM",
                         "Phan nhom cho NSD");

            conn.commit();
            resetToken(request);
            saveToken(request);
        } catch (Exception ex) {
            throw new Exception("PhanNhomAction: " + ex);
        } finally {
            close(conn);
        }
        return executeAction(mapping, form, request, response);
    }

}
