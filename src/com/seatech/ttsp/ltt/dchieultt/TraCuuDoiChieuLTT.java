package com.seatech.ttsp.ltt.dchieultt;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.DateUtils;

import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DChieu1VO;
import com.seatech.ttsp.dchieu.form.TCuuTTinDChieuForm;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.dmtiente.DMTienTeDAO;
import com.seatech.ttsp.ltt.LTTVO;
import com.seatech.ttsp.thamchieu.MaThamChieuDAO;

import com.seatech.ttsp.ttthanhtoan.TTThanhToanDAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.Statement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/* @creator: HungBM 
 * 17/11/2016
 * @see: Class tra cuu va xem chi tiet cho LTT cua TABMIS
 * */

public class TraCuuDoiChieuLTT extends AppAction {
    private static String SUCCESS = "success";
    private static String STRING_EMPTY = "";
    private static String TRACUU_LTT = "LTT.TRACUU";
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
            loadForm(request, conn);
            LTTdoichieuTabmis lttDCForm = (LTTdoichieuTabmis)form;
            String page = lttDCForm.getPageNumber();
            if (page == null || "".equals(page)) {
                page = "1";
            }
            Integer currentPage = new Integer(page);
            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            //Integer totalCount[] = new Integer[1];
            Integer numberRowOnPage = new Integer(15);
            //pagingBean.setNumberOfRow(totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);

        } catch (Exception ex) {
            throw new Exception("Tra Cuu DC LTT: " + ex);
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

  /* HungBM Load form dieu kien tra cuu
   * */
    public void loadForm(HttpServletRequest request,
                         Connection conn) throws Exception {
        List dmuckb_cha = null;
        String strMaSGD = "0003";
        String strCapTinh = "5";
        String strCapTW = "1";
        String strWhereClauseKB = null;
        Vector vParamsKB = null;
        // xac dinh user dang nhap thuoc tinh hay huyen
        HttpSession session = request.getSession();
        String kb_code =
            session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
        //Lay Danh sach kho bac
        // DMKBacDAO dmKBDAO = new DMKBacDAO(conn);
        LTTDChieuDAO lttDao = new LTTDChieuDAO(conn);
        DMTienTeDAO tienTeDAO = new DMTienTeDAO(conn);
        DChieu1DAO dao = new DChieu1DAO(conn);
        DChieu1VO vo = new DChieu1VO();
        String strCap = " and ma=" + kb_code;
        vo = dao.getCap(strCap, null);

        String cap = vo.getCap();
        if ("0001".equals(kb_code) || "0002".equals(kb_code)) {
            String strWhere = " ";
            // strWhere = " (a.cap='"+cap+"' or a.ma='" + kb_code + "' ) ";
            dmuckb_cha = (List)lttDao.getDMucKB_cha(strWhere, null);
            // dmuckb_cha = (ArrayList<DMKBacVO>)dmKBDAO.getDMKBList(strWhere, null);
            request.setAttribute("lstKBTinh", dmuckb_cha);
            request.setAttribute("QTHTTW", "QTHTTW");
        } else if ("0003".equals(kb_code)) {
            String strWhere = " AND a.ma='0003' ";
            dmuckb_cha = (List)lttDao.getDMucKB_cha(strWhere, null);
            request.setAttribute("lstKBTinh", dmuckb_cha);
        } else if ("5".equals(cap)) {
            String strWhere = "";
            strWhere += " and c.ma=" + kb_code;
            dmuckb_cha = (List)lttDao.getDMucKB_cha(strWhere, null);

            request.setAttribute("lstKBTinh", dmuckb_cha);
        } else {
            String strWhere = "";
            strWhere +=
                    " and c.id in (select id_cha from sp_dm_htkb where ma=" +
                    kb_code + ")";
            dmuckb_cha = (List)lttDao.getDMucKB_cha(strWhere, null);
           request.setAttribute("lstKBTinh", dmuckb_cha); 
        }
        List tienTe = tienTeDAO.simpleMaNgoaiTe("", null);
        request.setAttribute("tienTe", tienTe);
    }

  /*HungBM: Xem chi tiet doi chieu
   */
    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        LTTdoichieuTabmis dsForm = (LTTdoichieuTabmis)form;
        String idxKB = dsForm.getIdxKB() == null ? "" : dsForm.getIdxKB();
        String idxNH = dsForm.getIdxNH() == null ? "" : dsForm.getIdxNH();
        Connection conn = null;
        Collection lstTraCuu = new ArrayList();
        //ArrayList<LTTDChieuVO> lstTraCuu = new ArrayList<LTTDChieuVO>();
        try {
            //Get paramerter from form
            //HungBM edit: them cac gia tri lay tu form de hien thi cho Quyet Toan
            HttpSession session = request.getSession();
            String kb_code = session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
            String bk_id = dsForm.getId_kq();
            String ma_kb = dsForm.getMa_nguon();
            String ngay_dc = dsForm.getNgay_dc();
            String qtoan_dvi ="";
            
          //Kiem tra xem Tai Khoan co phai Qtoan Dvi ko  
          if ("0003".equals(kb_code)) {
            qtoan_dvi="";
          }
          else {
            qtoan_dvi="AND QTOAN_DVI='Y' ";
          }
            
            String id = (String)request.getParameter("id");
            bk_id = bk_id==null?id:bk_id;
            conn = getConnection(request);
            StringBuffer szWhereClause = new StringBuffer();
            Vector v_Param_ltt = new Vector();
            Parameter param_lttdc = null;
            LTTDChieuDAO dao = new LTTDChieuDAO(conn);
            
            szWhereClause.append(" and (ma_kb, shkb,trunc(ngay_dc)) in (select ma_kb,  shkb,trunc(ngay_dc) from sp_kq_dc_tab where id = ? ) ");
            v_Param_ltt.add( new Parameter(bk_id, true));
            szWhereClause.append(" ORDER BY ID DESC ");
            lstTraCuu =
                    dao.getLTTDChieuCtiet(szWhereClause.toString(), v_Param_ltt);



            //Lay Tong Hop Ket Qua Doi Chieu:
            if (bk_id != null && !"".equalsIgnoreCase(bk_id)) {
                Vector v_Param_THKQ = new Vector();
                Parameter param_THKQ = null;
                StringBuffer szWhereClauseTHKQ = new StringBuffer();
                //HungBM Thay b.BKE_ID =>b.id
                szWhereClauseTHKQ.append(" and b.id = ? ");
                param_THKQ = new Parameter(bk_id, true);
                v_Param_THKQ.add(param_THKQ);
                Collection colTHKQDC = new ArrayList();
                colTHKQDC =
                        dao.getLTTDChieuKQCtiet(szWhereClauseTHKQ.toString(), v_Param_THKQ);
                
                request.setAttribute("colTHKQDC", colTHKQDC);
            }


            //lay Thong tin tong hop bang ke
            //HungBM edit: them input de lay ra Lenh QT
            Collection colTTTHBK = new ArrayList();
            colTTTHBK = dao.getTTinTHopBKe(bk_id,ma_kb,ngay_dc,qtoan_dvi);
            if (!colTTTHBK.isEmpty()) {
                request.setAttribute("colTTTHBK", colTTTHBK);
            }

            //Lay ma so ngan hang va set collection cho LTT di va den
            String ngan_hang = "";
            LTTDChieuVO ngan_hangVO = null;

            Collection colLTTCtietDi = new ArrayList();
            colLTTCtietDi = dao.getLTT_KQ_Ctiet(bk_id,"DI");
            if (!colLTTCtietDi.isEmpty()) {
                request.setAttribute("colLTTCtietDi", colLTTCtietDi);               
            }
          //HungBM edit:18/11/2016  Chi can nhap parameter DEN
            Collection colLTTCtietDen = new ArrayList();
            colLTTCtietDen = dao.getLTT_KQ_Ctiet(bk_id,"DEN");
            if (!colLTTCtietDen.isEmpty()) {
                request.setAttribute("colLTTCtietDen", colLTTCtietDen);
                
            }
           //HungBM edit:18/11/2016  Lay ra cac ket qua la quyet toan (loai_lenh=QT) 
          Collection colLQT = new ArrayList();
          colLQT = dao.getLTT_KQ_Ctiet(bk_id,"QT");
          if (!colLQT.isEmpty()) {
              request.setAttribute("colLQT", colLQT);
              
          }
            
            
          String rowSelected = (String)request.getParameter("rowSelected");
          request.setAttribute("rowSelected", rowSelected);
            request.setAttribute("id", bk_id);
           // request.setAttribute("ngay_dc", ngay_dc);
            request.setAttribute("ngan_hang", ngan_hang);
            request.setAttribute("lstTraCuu", lstTraCuu);
        } catch (Exception ex) {
            throw new Exception("Tra Cuu DC LTT view: " + ex);
        } finally {
            close(conn);
        }
        request.setAttribute("idxKB", idxKB);
        request.setAttribute("idxNH", idxNH);

        return mapping.findForward("success");
    }

    /*HungBM: Cap nhat bang ke
     */
    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        Connection conn = null;
        HttpSession session = request.getSession();
        conn = getConnection(request);
        String strDoiChieu = request.getParameter(AppConstants.REQUEST_ACTION);
      LTTdoichieuTabmis dsForm = (LTTdoichieuTabmis)form;
        try {
            //if (strDoiChieu != null &&
            //    strDoiChieu.equalsIgnoreCase("XacNhanChenhLech")) {
                String id_bk = dsForm.getId();
                String ly_do = dsForm.getLydo();
                String trang_thaiHolder = "04";
                LTTDChieuDAO dao = new LTTDChieuDAO(conn);
                int a = dao.updateChenhLechKQ(id_bk,ly_do);
                dsForm.setId(id_bk);
                
                if (a>0){
                    conn.commit();
                  request.setAttribute("trang_thai", trang_thaiHolder);
                  
                  view( mapping,  dsForm, request, response);
                  return mapping.findForward("success");
                  
                }
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }
	//Thuc hien doi chieu
    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        Connection conn = null;       
        conn = getConnection(request);
        LTTdoichieuTabmis dsForm = (LTTdoichieuTabmis)form;
        try {           
                String id_bk = request.getParameter("id_bk");
                String ngayDC = request.getParameter("ngayDC");
                String loai_dc = "THUCONG";
                CallableStatement cs = null;
                cs =
                conn.prepareCall("{call sp_doi_chieu_tabmis_pkg.proc_dc_tabmis(?,?,?,?)}");
                cs.setString(1, id_bk);
                cs.setString(2, ngayDC);
                cs.setString(3, loai_dc);
                cs.registerOutParameter(4, java.sql.Types.VARCHAR);
                cs.execute();
                dsForm.setId_kq(id_bk); 
                dsForm.setLydo("Đối chiếu thành công");
                view( mapping,  dsForm, request, response);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
      return mapping.findForward("success");
    }


  /*HungBM Liet ke ket qua tra cuu
   * */
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        LTTdoichieuTabmis lttDCForm = (LTTdoichieuTabmis)form;
        Connection conn = null;
        Statement stmt = null;
        String idxKB =
            lttDCForm.getIdxKB() == null ? "" : lttDCForm.getIdxKB();
        String idxNH =
            lttDCForm.getIdxNH() == null ? "" : lttDCForm.getIdxNH();
        List<LTTDChieuVO> lstTraCuu = new ArrayList<LTTDChieuVO>();
        HttpSession session = request.getSession();
        String id_kb_nsd =
            session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
        try {
            conn = getConnection(request);

          DChieu1DAO dao123 = new DChieu1DAO(conn);
          DChieu1VO vo123 = new DChieu1VO();
          String strCap = " and ma=" + id_kb_nsd;
          vo123 = dao123.getCap(strCap, null);

          String cap = vo123.getCap();


            StringBuffer szWhereClause = new StringBuffer();
            Vector v_Param_ltt = new Vector();
            Parameter param_lttdc = null;
            LTTDChieuDAO dao = new LTTDChieuDAO(conn);
            List<LTTDChieuVO> lstLTTDC = new ArrayList();
            String page = lttDCForm.getPageNumber();
            if (page == null || "".equals(page)) {
                page = "1";
            }
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = new Integer(15);
            Integer totalCount[] = new Integer[1];
            String idkb_tinh = lttDCForm.getKb_tinh();
            String idkb_huyen = lttDCForm.getKb_huyen();
            String tthai_doichieu = lttDCForm.getTthai_doichieu();
            String ngay_dc = lttDCForm.getNgay_dc();
            
            
            
            

            if (idkb_tinh != null && !"".equals(idkb_tinh)) {
                szWhereClause.append("AND c.ID_CHA= ?");
                param_lttdc = new Parameter(idkb_tinh, true);
                v_Param_ltt.add(param_lttdc);
            }
            if (idkb_huyen != null && !"".equals(idkb_huyen)) {
                szWhereClause.append("AND c.id= ? ");
                param_lttdc = new Parameter(idkb_huyen, true);
                v_Param_ltt.add(param_lttdc);
            }
            if (tthai_doichieu != null && !"".equals(tthai_doichieu)) {
                szWhereClause.append("AND b.TRANG_THAI=?");
                param_lttdc = new Parameter(tthai_doichieu, true);
                v_Param_ltt.add(param_lttdc);
            }

            //HungBM: loai bo toan bo cac lien ket den BK
            if (ngay_dc != null && !"".equals(ngay_dc)) {
                szWhereClause.append("AND TO_CHAR(b.NGAY_DC,'DD/MM/YYYY') = ? ");
                param_lttdc = new Parameter(ngay_dc, true);
                v_Param_ltt.add(param_lttdc);
            }

            lstLTTDC =
                    (List<LTTDChieuVO>)dao.getLTTDChieuListWithPading(szWhereClause.toString(),
                                                                      v_Param_ltt,
                                                                      currentPage,
                                                                      numberRowOnPage,
                                                                      totalCount);


            ArrayList<LTTDChieuVO> lstLTTTmp = new ArrayList<LTTDChieuVO>();
            for (LTTDChieuVO vo : lstLTTDC) {
                lstLTTTmp.add(vo);
            }
            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow(totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);
            loadForm(request, conn);
            int tong_row = totalCount[0].intValue();
            request.setAttribute("tong_row", tong_row);
            request.setAttribute("lstTraCuu", lstLTTTmp);
            request.setAttribute("idxKB", idxKB);
            request.setAttribute("idxNH", idxNH);
        } catch (Exception ex) {
            throw new Exception("Tra Cuu Doi Chieu LTT: " + ex);
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }
}
