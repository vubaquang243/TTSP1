package com.seatech.ttsp.dchieungoaite.action;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.dchieu.TheoDoiDChieuDAO;
import com.seatech.ttsp.dchieu.TheoDoiDChieuVO;
import com.seatech.ttsp.dchieu.form.TheoDoiDChieuForm;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanDAO;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class TheoDoiDCNgoaiTeAction extends AppAction {
  public ActionForward view(ActionMapping mapping, ActionForm form,
                            HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
    Connection conn=null;
    conn = getConnection(request);   
    try{
      HttpSession session = request.getSession();
      TheoDoiDChieuDAO dao= new TheoDoiDChieuDAO(conn);
      TheoDoiDChieuForm frm = (TheoDoiDChieuForm)form;
      TheoDoiDChieuVO vo= new TheoDoiDChieuVO();
      Collection colSumTDoi=null;  
//      Collection colTongDV=null;  
      List dmucNH = null;
      TTThanhToanDAO TTdao = new TTThanhToanDAO(conn);
      dmucNH = (List)TTdao.getDMucNH(null,null);
      request.setAttribute("dmucNH", dmucNH);
        
      String ma_dv= frm.getMa_dv()==null?"":frm.getMa_dv();
      String ngay_tdoi = frm.getNgay_tdoi();
      String strWhere = "";
        String strDV="";
        String strQT="";
        String strTong="";
        if (ngay_tdoi==null || "".equals(ngay_tdoi)){
         strWhere+= " AND trunc (a.ngay_dc)=trunc(sysdate)" ;
          strQT+= " AND trunc (a.ngay_qtoan)=trunc(sysdate)" ;
        }
        if (ngay_tdoi!=null && !"".equals(ngay_tdoi)){
          strWhere+= " AND TO_CHAR (a.ngay_dc, 'dd/mm/yyyy')='"+ngay_tdoi+"'" ;
          strQT+= " AND TO_CHAR (a.ngay_qtoan, 'dd/mm/yyyy')='"+ngay_tdoi+"'" ;
        }
        if(ma_dv!=null && !"".equals(ma_dv)){
          strDV +=" AND substr(tk.ma_nh,3,3) = '" + ma_dv +"'" ; 
          strTong +=" AND substr(a.ma_nh,3,3) = '" + ma_dv +"'" ;
        }
      
      colSumTDoi= dao.getSumTinhHinh_066(strWhere, strDV, strQT, null);
        
      vo= dao.getTongDVTDoi(strTong, null);
 
      request.setAttribute("sum_dvi", vo.getSum_dvi());    
      request.setAttribute("colSumTDoi", colSumTDoi);
        

    } catch (Exception e) {
        throw e;
    } finally {
        conn.close();
    }
    return mapping.findForward("success");
  }
  
  public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
    Connection conn=null;
    conn = getConnection(request);   
    try{
      HttpSession session = request.getSession();
      TheoDoiDChieuDAO dao= new TheoDoiDChieuDAO(conn);
      TheoDoiDChieuForm frm = (TheoDoiDChieuForm)form;
      Collection colTDoi= new ArrayList();   
        
      int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
      String page = frm.getPageNumber();
      if (page == null)
          page = "1";
      Integer currentPage = new Integer(page);
      Integer numberRowOnPage = phantrang;
      Integer totalCount[] = new Integer[15];
        
      String strWhere = "" ;
        String bkid= frm.getBkid()==null?"":frm.getBkid();
        String ttsp= frm.getTtsp()==null?"":frm.getTtsp();
        String pht=frm.getPht()==null?"":frm.getPht();;
        String loaiqt=frm.getLoaiqt()==null?"":frm.getLoaiqt();
        String tthai_066=frm.getTthai_066()==null?"":frm.getTthai_066();       
        String ma_dv= frm.getMa_dv()==null?"":frm.getMa_dv();
        String ngay_tdoi = frm.getNgay_tdoi()==null?"":frm.getNgay_tdoi();
        if(ngay_tdoi ==null || "".equals(ngay_tdoi)){
          ngay_tdoi = " TO_CHAR (sysdate, 'dd/mm/yyyy')" ;
        }else if (ngay_tdoi !=null && !"".equals(ngay_tdoi)){
          ngay_tdoi = "'" + frm.getNgay_tdoi() + "'";
        }
        
        
        if(ma_dv!=null && !"".equals(ma_dv)){
          strWhere+=" AND substr(tk.ma_nh,3,3) = '" + ma_dv + "'"; 
        }
        if ("CHUA_CO_BKE".equals(bkid)){
          strWhere += " AND bk.id is null ";
        }
        if ("DA_CO_BKE".equals(bkid)){
          strWhere += " AND bk.id is not null";
        }
        if (ttsp!=null&&!"".equals(ttsp)){
          strWhere += " AND sp.ket_qua ='"+ttsp+"'";
        }
        else if (ttsp==null || "".equals(ttsp)){
          strWhere += " AND sp.ket_qua is null ";
        }
        if (pht!=null && !"".equals(pht)){
          strWhere += " AND pht.ket_qua ='"+pht+"'";
        }
        else if (pht==null || "".equals(pht)){
          strWhere += " AND pht.ket_qua is null";
        }
        if (tthai_066!=null&&!"".equals(tthai_066)){
          strWhere += " AND sp.tthai_dxn_thop ='"+tthai_066+"'";
        }
        if (tthai_066==null || "".equals(tthai_066)){
          strWhere += " AND sp.tthai_dxn_thop is null ";
        }
        if (loaiqt!=null&&!"".equals(loaiqt)){
          strWhere += " AND qt.lai_phi ='"+loaiqt+"'";
        }
        if (loaiqt==null || "".equals(loaiqt)){
          strWhere += " AND qt.lai_phi is null ";
        }
      
      
      colTDoi= dao.getListTinhHinh_066_PTrang(strWhere, ngay_tdoi, null, currentPage,
                                                 numberRowOnPage, totalCount);
      request.setAttribute("colTDoi", colTDoi);
      
//      String strGoPage = "bkid="+bkid+"&ttsp="+ttsp+"&pht="+pht+"&loaiqt="+loaiqt+"&tthai_066="+tthai_066+"&ma_dv="+ma_dv+"&ngay_tdoi="+frm.getNgay_tdoi()==null?"":frm.getNgay_tdoi();
//      request.setAttribute("strGoPage", strGoPage);
        
      PagingBean pagingBean = new PagingBean();

      pagingBean.setCurrentPage(currentPage);
      pagingBean.setNumberOfRow(totalCount[0].intValue());
      pagingBean.setRowOnPage(numberRowOnPage);
      request.setAttribute("PAGE_KEY", pagingBean);
    } catch (Exception e) {
        throw e;
    } finally {
        conn.close();
    }
    return mapping.findForward("success");
  }


}