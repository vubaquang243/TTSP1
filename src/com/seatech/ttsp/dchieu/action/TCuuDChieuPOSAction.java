package com.seatech.ttsp.dchieu.action;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;

import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DChieu1VO;

import com.seatech.ttsp.dchieu.DChieu3DAO;
import com.seatech.ttsp.dchieu.DChieuGDichPOSDAO;
import com.seatech.ttsp.dchieu.KQuaDChieuPOSVO;
import com.seatech.ttsp.dchieu.form.TCuuDChieuPOSForm;
import com.seatech.ttsp.dchieu.form.TheoDoiDChieu3Form;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.dmtiente.DMTienTeDAO;

import com.seatech.ttsp.phipos.BKePhiPOSDAO;

import com.seatech.ttsp.ttthanhtoan.form.DSachTKhoanNHKBForm;

import java.io.PrintWriter;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
   * @creater: ThuongDT
   * @creat date: 10/11/2016
   * @see: Lop TCuuDChieuPOSAction, thuc hien tra cuu doi chieu POS
   * */

public class TCuuDChieuPOSAction  extends AppAction{
 /**
   * @creater: ThuongDT
   * @creat date: 10/11/2016
   * @see: Ham load trang ban dau
   * */
  @Override
  protected ActionForward executeAction(ActionMapping mapping,
                                        ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
      Connection conn = null;
    
      try {
        conn = getConnection(request);
        loadForm( conn, request);
        PagingBean pagingBean = new PagingBean();
        request.setAttribute("PAGE_KEY", pagingBean);
      } catch (Exception e) {
          throw e;

      } finally {
          close(conn);
      }
      return mapping.findForward("success");
    }
  /**
   * @creater: ThuongDT
   * @creat date: 10/11/2016
   * @see: Ham thuc hien truy van CSDL va tra danh sach KQDC POS de hien thi tren JSP
   * */
  public ActionForward list(ActionMapping mapping, ActionForm form,
                            HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
    Connection conn = null;
    Collection lisBangKe = null;
    String strDVDC = "";
    try {
        conn = getConnection(request);
      loadForm( conn, request);
      DChieuGDichPOSDAO dchieuPOSDAO = new DChieuGDichPOSDAO(conn);
      String strBke = "";
      String strtong = "";
      TCuuDChieuPOSForm frm = (TCuuDChieuPOSForm)form;  
      String idxKB = frm.getIdxKB() == null ? "" : frm.getIdxKB();
      String idxNH = frm.getIdxNH() == null ? "" : frm.getIdxNH();
      String ngay_dc = frm.getNgayDC();
      String page = frm.getPageNumber();
      int phantrang = AppConstants.APP_NUMBER_ROW_ON_PAGE;
      if (page == null || "".equals(page))
          page = "1";
      Integer currentPage = new Integer(page);
      Integer numberRowOnPage = phantrang;
      Integer totalCount[] = new Integer[15];
      
      strBke = getWhereClause(frm); 
      //thuongdt-20170424 bo sung them querry so tham chieu null de lay thong tin doi chieu tong hop
      strBke = strBke+ " and d.so_tham_chieu is null ";  
      Vector vParam = new Vector();
      vParam.add(new Parameter(ngay_dc, true));
	  //Lay danh sach doi chieu POS
      lisBangKe = dchieuPOSDAO.getDChieuList(strBke,vParam,currentPage,numberRowOnPage, totalCount);
      request.setAttribute("idxKB", idxKB);
      request.setAttribute("idxNH", idxNH);
      request.setAttribute("dvdc", strDVDC);
      request.setAttribute("lisBangKe", lisBangKe);

      request.setAttribute("idxKB", idxKB);
      PagingBean pagingBean = new PagingBean();
      pagingBean.setCurrentPage(currentPage);
      pagingBean.setNumberOfRow(totalCount[0].intValue());
      pagingBean.setRowOnPage(numberRowOnPage);
      request.setAttribute("PAGE_KEY", pagingBean);
    } catch (Exception e) {
        throw e;

    } finally {
        close(conn);
    }
      
    return mapping.findForward("success");       
  }
   /**
   * @creater: ThuongDT
   * @creat date: 10/11/2016
   * @see: Ham show chi tiet ket qua DC POS
   * */
  public ActionForward view(ActionMapping mapping, ActionForm form,
                            HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
    Connection conn = null;
    KQuaDChieuPOSVO kqvo = null;
    String whereClause = "";
    Collection KQDCCtiet= null;
    try {      
      conn = getConnection(request);
      TCuuDChieuPOSForm frm = (TCuuDChieuPOSForm)form;
      DChieuGDichPOSDAO dchieuPOSDAO = new DChieuGDichPOSDAO(conn);
      String bk_id = frm.getBkid();
      String id = frm.getId();
      whereClause = " and id = '"+id+"'  ";
	  //Lay ket qua chi tiet va gan vao cac thong tin
      kqvo = dchieuPOSDAO.getDChieuChiTietVO(whereClause, null);
        if(kqvo == null || kqvo.getId() == null){
            kqvo = new KQuaDChieuPOSVO();
            
            kqvo.setBk_id(bk_id);
            kqvo.setMa_kb("");
            kqvo.setNgay_dc("");
            kqvo.setSo_lenh_pos( "0");
            kqvo.setSo_lenh_qt( "0");
            kqvo.setSo_lenh_lech( "0");
            kqvo.setTong_lech( "0");
            kqvo.setTong_pos("0");
            kqvo.setTong_qtoan("0");
            kqvo.setTrang_thai("00");
        }        
        if(!"02".equals(kqvo.getTrang_thai()) && !"".equals(kqvo.getMa_kb())){
          String strBke = "";
          Collection lisBangKe = null;
          //strBke = getWhereClause(frm); 
          //thuongdt-20170424 bo sung them querry so tham chieu null de lay thong tin doi chieu tong hop
          strBke = strBke+ " and so_tham_chieu is not null and ma_kb = '"+kqvo.getMa_kb()+"' and ma_nh = '"+kqvo.getMa_nh()+"' and to_char(ngay_dc,'dd/MM/yyyy') = '"+kqvo.getNgay_dc()+"'";  
          Vector vParam = new Vector();          
          //Lay danh sach doi chieu POS
          lisBangKe = dchieuPOSDAO.getDChieuChiTiet(strBke,vParam);
        
          request.setAttribute("kqctiet", lisBangKe);
        }
        else{
          request.setAttribute("kqctiet", null);
        }        
        request.setAttribute("kqvo", kqvo);
        
    } catch (Exception e) {
        throw e;
    } finally {
        close(conn);
    }
    return mapping.findForward("success");  
  }
   /**
   * @creater: ThuongDT
   * @creat date: 10/11/2016
   * @see: Ham thuc hien doi chieu thu cong
   * */
  public ActionForward addExc(ActionMapping mapping, ActionForm form,
                            HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
    Connection conn = null;
   String strKQ_DC = "";
   String[] strTemp ;
    try {      
      conn = getConnection(request);
      TCuuDChieuPOSForm frm = (TCuuDChieuPOSForm)form;
      DChieuGDichPOSDAO dchieuPOSDAO = new DChieuGDichPOSDAO(conn);
      String bk_id = frm.getBkid();
	  
	  //lay ket qua doi chieu 
      strKQ_DC = dchieuPOSDAO.DChieuLaiBK(bk_id);
      if(strKQ_DC.indexOf(";")>0)
      {
        strTemp = strKQ_DC.split(";");
        frm.setErr_code(strTemp[1]);
        frm.setErr_desc(strTemp[2]);
      }
      view( mapping,  frm,request, response);
    } catch (Exception e) {
      conn.rollback();
        throw e;
    } finally {
        close(conn);
    }
    return mapping.findForward("success");  
  }
   /**
   * @creater: ThuongDT
   * @creat date: 10/11/2016
   * @see: Ham thuc hien xac nhan cua TTV trong TH chenh lech
   * */
  public ActionForward update(ActionMapping mapping, ActionForm form,
                            HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
    Connection conn = null;
    KQuaDChieuPOSVO kqvo = null;
    String whereClause = "";
    String SetClause = "";
    int nKetQua= 0;
    try {      
      conn = getConnection(request);
      TCuuDChieuPOSForm frm = (TCuuDChieuPOSForm)form;
      String kq_id = request.getParameter("ajkqid").toString();
      String lydo = request.getParameter("ajlydo").toString();
      
      PrintWriter out = response.getWriter();   
      if(kq_id!= null){
        DChieuGDichPOSDAO dchieuPOSDAO = new DChieuGDichPOSDAO(conn);
       
        whereClause = " and id = '"+kq_id+"'";
        SetClause = " ldo_clech = '"+lydo+"', trang_thai = '04' ";   
		// Cap nhat ly do cho bang chi tiet`		
        nKetQua = dchieuPOSDAO.updateDChieuChiTiet(SetClause,whereClause, null);
      }
        conn.commit();
      try{       
        out.println(nKetQua);
      } 
      catch(Exception ex) {
      throw ex;
      }finally{
        conn.close();
        out.flush();
        out.close();
      }
    } catch (Exception e) {
        throw e;
    } finally {
        close(conn);
    }
    return null;
  }
   /**
   * @creater: ThuongDT
   * @creat date: 10/11/2016
   * @see: Ham build menh de where
   * */
  public String getWhereClause(TCuuDChieuPOSForm frm){   
    String strReturn = "";
    String strBke = " and 1=1 ";
    String nhkb_tinh =
        frm.getNhkb_tinh() == null ? "" : frm.getNhkb_tinh();
      String nhkb_huyen =
          frm.getNhkb_huyen() == null ? "" : frm.getNhkb_huyen();
      String ma_nh =
          frm.getNgan_hang() == null ? "" : frm.getNgan_hang();
      String ngay_dc =
          frm.getNgayDC() == null ? "" : frm.getNgayDC();
      String trang_thai_dc =
          frm.getTrangThaiDC() == null ? "" : frm.getTrangThaiDC();
    String loai_tien =
        frm.getLoaiTien() == null ? "" : frm.getLoaiTien();
        
    if(!"".equals(nhkb_tinh)){
      strBke = strBke+" and e.id_cha = '"+nhkb_tinh+"'";
    } 
      if(!"".equals(nhkb_huyen)){
        strBke = strBke+" and e.id = '"+nhkb_huyen+"'";
      } 
      if(!"".equals(ma_nh)){
        strBke = strBke+" and a.ma_nh = '"+ma_nh+"'";
      }
      if(!"".equals(ngay_dc)){
      strBke = strBke+" and trunc(a.ngay_bk) = to_date('"+ngay_dc+"', 'dd/MM/yyyy')";
      }
      if(!"".equals(trang_thai_dc)){
          if("00".equals(trang_thai_dc))
            strBke = strBke+" and (d.trang_thai = '"+trang_thai_dc+"' or d.trang_thai is null )";
          else
            strBke = strBke+" and d.trang_thai = '"+trang_thai_dc+"'";
      }
      // hien tai chi co VND nÃªn tam rao lai
    //  if(!"".equals(loai_tien)){
    //  strBke = strBke+" and a.loai_tien = '"+loai_tien+"' ";
   //   }
      strReturn = strBke;
     return strReturn;
  }
   /**
   * @creater: ThuongDT
   * @creat date: 10/11/2016
   * @see: Ham load form tra cuu
   * */
  public void loadForm(Connection conn, HttpServletRequest request) throws Exception {
    List dmuckb_cha = null;
    Collection dmucTienTe = null;
    HttpSession session = request.getSession();
    String kb_code =
        session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
    DChieu1DAO dao = new DChieu1DAO(conn);
    DMTienTeDAO tienTeDAO = new DMTienTeDAO(conn);
    DChieu1VO vo = new DChieu1VO();
    String strCap = " and ma=" + kb_code;
    vo = dao.getCap(strCap, null);
    String cap = vo.getCap();   
    if ("0001".equals(kb_code) || "0002".equals(kb_code)) {
        String strWhere = " ";
        dmuckb_cha = (List)dao.getDMucKB_Tinh(strWhere, null);
        request.setAttribute("dmuckb_tinh", dmuckb_cha);
        request.setAttribute("QTHTTW", "QTHTTW");
    } else if ("0003".equals(kb_code)) {
       String strWhere = " AND a.ma='0003' ";
       dmuckb_cha = (List)dao.getDMucKB_Tinh(strWhere, null);
       request.setAttribute("dmuckb_tinh", dmuckb_cha);
   } else if ("5".equals(cap)) {
       String strWhere = "";
       strWhere += " and c.ma=" + kb_code;
       dmuckb_cha = (List)dao.getDMucKB_Tinh(strWhere, null);

       request.setAttribute("dmuckb_tinh", dmuckb_cha);
   } else {
       String strWhere = "";
       strWhere += " and c.id in (select id_cha from sp_dm_htkb where ma=" + kb_code + ")";
       dmuckb_cha = (List)dao.getDMucKB_Tinh(strWhere, null);
       request.setAttribute("dmuckb_tinh", dmuckb_cha);
   }
    dmucTienTe = tienTeDAO.simpleMaNgoaiTe("", null);
    request.setAttribute("dmuctiente", dmucTienTe);    
  }
  
}
