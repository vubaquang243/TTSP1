package com.seatech.ttsp.dchieu.action;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.exception.TTSPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.FontUtil;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DChieu1VO;
import com.seatech.ttsp.dchieu.DChieu2DAO;
import com.seatech.ttsp.dchieu.DChieu2VO;
import com.seatech.ttsp.dchieu.QLyDChieuDAO;
import com.seatech.ttsp.dchieu.QLyDChieuVO;
import com.seatech.ttsp.dchieu.form.QLyDChieuForm;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanDAO;

import java.io.PrintWriter;

import java.lang.reflect.Type;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class QLyDChieuAction extends AppAction {
  public ActionForward list(ActionMapping mapping, ActionForm form,
                            HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
    Connection conn=null;
    conn = getConnection(request);
    HttpSession session = request.getSession();
    String kb_code =
        session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
    
    
    try{
    QLyDChieuDAO qldao = new QLyDChieuDAO(conn);
    QLyDChieuForm frm = (QLyDChieuForm)form;
    DChieu1DAO dao = new DChieu1DAO(conn);
        
      int phantrang = AppConstants.APP_NUMBER_ROW_ON_PAGE;
      String page = frm.getPageNumber();
      if (page == null)
          page = "1";
      Integer currentPage = new Integer(page);
      Integer numberRowOnPage = phantrang;
      Integer totalCount[] = new Integer[15];
        
    List dmuckb_cha = null;  
        
    
    String strWhere = "";//" AND a.ma='0003' ";
    dmuckb_cha = (List)dao.getDMucKB_cha(strWhere, null);
    request.setAttribute("dmuckb_tinh", dmuckb_cha);
    
    TTThanhToanDAO TTdao = new TTThanhToanDAO(conn);
    List dmucNH = null;
    dmucNH = (List)TTdao.getDMucNH(null,null);
    request.setAttribute("dmucNH", dmucNH);
      Collection lstSearch= null;
      String sw= " AND (	 a.trang_thai = '00' OR a.trang_thai='98' OR (a.trang_thai <> '00' AND b.trang_thai <> '02') OR (a.trang_thai <> '00' AND b.trang_thai <> '02') OR a.trang_thai IS NULL)";
      lstSearch = qldao.getKQuaTKiem_PTrang(sw, null, currentPage,
                                                   numberRowOnPage,
                                                   totalCount);
      
      request.setAttribute("lstSearch", lstSearch);
      PagingBean pagingBean = new PagingBean();
  
        pagingBean.setCurrentPage(currentPage);
          pagingBean.setNumberOfRow(totalCount[0].intValue());
          pagingBean.setRowOnPage(15);
          request.setAttribute("PAGE_KEY", pagingBean);
          request.setAttribute("initAction", "initAction");

    } catch (TTSPException e) {
        throw e;
    } finally {
        conn.close();
    }
    return mapping.findForward("success");
  }
  public ActionForward search(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
    Connection conn=null;
    conn = getConnection(request);   
    HttpSession session = request.getSession();
    try{
   QLyDChieuDAO qldao = new QLyDChieuDAO(conn);
        TTThanhToanDAO TTdao = new TTThanhToanDAO(conn);
        List dmucNH = null;
        dmucNH = (List)TTdao.getDMucNH(null,null);
        request.setAttribute("dmucNH", dmucNH);
        
        
        DChieu1DAO dao = new DChieu1DAO(conn);
        List dmuckb_cha = null;  
        
        String strWhere = "";//" AND a.ma='0003' ";
        dmuckb_cha = (List)dao.getDMucKB_cha(strWhere, null);
        request.setAttribute("dmuckb_tinh", dmuckb_cha);
        
    QLyDChieuForm frm = (QLyDChieuForm)form;
        
        int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
        String page = frm.getPageNumber();
        if (page == null)
            page = "1";
        Integer currentPage = new Integer(page);
        Integer numberRowOnPage = phantrang;
        Integer totalCount[] = new Integer[15];
        
    String ngan_hang=frm.getNhkb_nhan();
    String kb= frm.getNhkb_tinh();
    String tthai= frm.getTrang_thai();
    String ngay_dc=frm.getNgay_qt();
    Collection lstSearch= null;
    String sw= "";
    if (kb != null && !"".equals(kb)) {
            sw += " and  c.id_kb_tinh =" + kb;
    }
    if (ngan_hang != null && !"".equals(ngan_hang)) {
        sw += " and substr(a.send_bank,3,3) = '" + ngan_hang.substring(2, 5) + "'";
    }
    if (ngay_dc != null && !"".equals(ngay_dc)) {
        sw += " AND to_char(c.ngay,'DD/MM/RRRR')= '" + ngay_dc + "'";
    }
     if (tthai != null && !"".equals(tthai)) {
            if ("00".equals(tthai)) { // chua thuc hien
                sw += " and (a.trang_thai = '00' or (a.trang_thai<>'00' and b.trang_thai<>'02') OR a.trang_thai IS NULL)";
            } else if ("01".equals(tthai)){ // chenh lech
                sw += " and (b.ket_qua = '1' and b.trang_thai='02')";
            } else if ("02".equals(tthai)) {  // khop dung
                sw += " and (b.ket_qua = '0' and b.trang_thai='02')";
            }
     }
    lstSearch = qldao.getKQuaTKiem_PTrang(sw, null,currentPage,
                                                   numberRowOnPage,
                                                   totalCount);
        request.setAttribute("lstSearch", lstSearch);
        
        PagingBean pagingBean = new PagingBean();
        pagingBean.setCurrentPage(currentPage);
        pagingBean.setNumberOfRow(totalCount[0].intValue());
        pagingBean.setRowOnPage(numberRowOnPage);
        request.setAttribute("PAGE_KEY", pagingBean);
        
      } catch (TTSPException e) {
          throw e;
      } finally {
          conn.close();
      }
  return mapping.findForward("success");
  }
  

  
  public ActionForward update(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
    Connection conn=null;
    conn = getConnection(request);   
    HttpSession session = request.getSession();
    
    try{
    QLyDChieuDAO qldao = new QLyDChieuDAO(conn);
      QLyDChieuVO qlvo = new QLyDChieuVO();
    QLyDChieuForm frm = (QLyDChieuForm)form;
    String strJSON;
      String kq_id=frm.getKq_id_dia();
      String close_ldo=frm.getClose_ldo();
      String close_tthai=frm.getClose_trang_thai();
        
      qlvo.setKq_id(kq_id);
      qlvo.setGhi_chu(close_ldo);
      qlvo.setTthai_dmo(close_tthai);      
      
      qldao.updateClose(qlvo);
      conn.commit();
      
      Collection colSearch= null;
      String sw= " and b.id='"+kq_id+"'";
      colSearch = qldao.getKQuaTKiem(sw, null);
            
        
    java.lang.reflect.Type lstSearch =
        new TypeToken<Collection<QLyDChieuVO>>() {
    }.getType();
    strJSON = new Gson().toJson(colSearch, lstSearch);

    response.setContentType(AppConstants.CONTENT_TYPE_JSON);
    PrintWriter out = response.getWriter();
    out.println(strJSON.toString());
    out.flush();
    out.close();
    } catch (Exception e) {
    JSONObject jsonRes = new JSONObject();
    jsonRes.put("executeError",
                FontUtil.unicodeEscape("Lỗi: " + e.getMessage()));

    response.setHeader("X-JSON", jsonRes.toString());
  } finally {
      conn.close();
  }
  return mapping.findForward("success");
  }
  // cap nhat dong moi voi truong hop chua co bang ke
  public ActionForward updateExc(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
    Connection conn=null;
    conn = getConnection(request);   
    HttpSession session = request.getSession();
    
    try{
    QLyDChieuDAO qldao = new QLyDChieuDAO(conn);
      QLyDChieuVO qlvo = new QLyDChieuVO();
    QLyDChieuForm frm = (QLyDChieuForm)form;
    String strJSON;
      String bk_id=frm.getBk_id();
      String close_ldo=frm.getClose_ldo();
      String close_tthai=frm.getClose_trang_thai();
      String send_bank=frm.getSend_bank();
      String receive_bank=frm.getReceive_bank();
      String ngay_dc=frm.getNgay_dc();
        if("1".equals(close_tthai) && ("".equals(bk_id)||bk_id==null)){
//          qlvo.setKq_id(kq_id);
          qlvo.setGhi_chu(close_ldo);
          qlvo.setLan_dc("2.1");
          qlvo.setSend_bank(send_bank);
          qlvo.setReceive_bank(receive_bank);
          qlvo.setNgay_dc(ngay_dc);
          qlvo.setTrang_thai("98");
           qldao.insert_bk(qlvo);
           conn.commit();
                //          qlvo.setTthai_dmo(close_tthai);
        }else if("0".equals(close_tthai) && (!"".equals(bk_id)&&bk_id!=null)){
          qldao.delete_bk2(bk_id);
          conn.commit();
          bk_id=null;
        }
      
//      if(close_tthai)
//      qldao.updateClose(qlvo);
//        conn.commit();
      String sw="";
      Collection colSearch= null;
        if(!"".equals(bk_id)&&bk_id!=null){
       sw += " and a.bk_id='"+bk_id+"'";
//      colSearch = qldao.getKQuaTKiem(sw, null);
        }else if("".equals(bk_id)||bk_id==null){
           sw+= " and a.ngay_dc= to_date('" + ngay_dc+"','DD/MM/YYYY') and a.lan_dc='2.1' and a.send_bank='"+send_bank+"' and a.receive_bank='"+receive_bank+"'";
        }
      colSearch = qldao.getKQuaTKiem(sw, null);
        
    java.lang.reflect.Type lstSearch =
        new TypeToken<Collection<QLyDChieuVO>>() {
    }.getType();
    strJSON = new Gson().toJson(colSearch, lstSearch);

    response.setContentType(AppConstants.CONTENT_TYPE_JSON);
    PrintWriter out = response.getWriter();
    out.println(strJSON.toString());
    out.flush();
    out.close();
    } catch (Exception e) {
    JSONObject jsonRes = new JSONObject();
    jsonRes.put("executeError",
                FontUtil.unicodeEscape("Lỗi: " + e.getMessage()));

    response.setHeader("X-JSON", jsonRes.toString());
  } finally {
      conn.close();
  }
  return mapping.findForward("success");
  }
  

  public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {

    Connection conn = null;
    try {
        String strMaKB;
        String strJSON="";
        JsonObject jsonObj = new JsonObject();
        String strJson;
        Gson gson = null;
        conn = getConnection(request);  
        
        QLyDChieuDAO qldao = new QLyDChieuDAO(conn);
        DChieu2DAO dc2dao = new DChieu2DAO(conn);
        QLyDChieuForm frm = (QLyDChieuForm)form;

        Collection colKQDC1 = null;
        Collection colKQDC2 = null;
        Collection colCoNo = null;
        HttpSession session = request.getSession();
        String send_bank= frm.getSend_bank();
        String receive_bank=frm.getReceive_bank();
        String ngay_dc=frm.getNgay_dc();
        

        String strW =
        " AND TO_CHAR (ngay_dc, 'DD/MM/YYYY') = '"+ngay_dc+ "' AND send_bank = '"+receive_bank+"' AND receive_bank ='"+send_bank+"'";
                                                
        colKQDC1 = qldao.getKQDChieu1(strW, null);
        
        if(colKQDC1.size()!=0){
          String strW2 =
          " AND TO_CHAR (a.ngay_dc, 'DD/MM/YYYY') = '"+ngay_dc+ "' AND a.send_bank = '"+send_bank+"' AND a.receive_bank ='"+receive_bank+"'";
          String strThuChi=" AND a.id IN (SELECT a.qtoan_id FROM  sp_bk_dc2 a WHERE 1=1 " + strW2 +")";
          colCoNo = dc2dao.getChiThu(strThuChi, null);
          String lan_dc=frm.getLan_dc()==null?"":frm.getLan_dc();
          if(lan_dc!=null&&!"".equals(lan_dc)){
            strW2+=" AND a.lan_dc='"+lan_dc+"'";
          }
          colKQDC2 = qldao.getKQDChieu2(strW2, null);        
        }
      
               
        Type listTypeKQDC2 = new TypeToken<ArrayList<QLyDChieuVO>>() {
        }.getType();

        gson = new GsonBuilder().setVersion(1.2).create();
        strJson = gson.toJson(colKQDC2, listTypeKQDC2);
        jsonObj.addProperty("test", strJson);
        
        Type listTypeCoNo = new TypeToken<Collection<DChieu2VO>>() {
        }.getType();
        gson = new GsonBuilder(). setVersion(1.2).create();
        strJson = gson.toJson(colCoNo, listTypeCoNo);
        jsonObj.addProperty("test2", strJson);
        
        Type listTypeKQDC1 = new TypeToken<Collection<DChieu1VO>>() {
        }.getType();
        gson = new GsonBuilder(). setVersion(1.2).create();  
        strJson = gson.toJson(colKQDC1, listTypeKQDC1);
        jsonObj.addProperty("test3", strJson);


        JsonArray jsonArr = new JsonArray();
        JsonElement jsonEle = jsonObj.get("test");
        jsonArr.add(jsonEle);

        jsonEle = jsonObj.get("test2");
        jsonArr.add(jsonEle);

        jsonEle = jsonObj.get("test3");
        jsonArr.add(jsonEle);
        
        response.setContentType(AppConstants.CONTENT_TYPE_JSON);
        PrintWriter out = response.getWriter();
        out.println(jsonArr.getAsJsonArray().toString());
        out.flush();
        out.close();
  
      } catch (Exception e) {
      JSONObject jsonRes = new JSONObject();
      jsonRes.put("executeError",
                  FontUtil.unicodeEscape("Lỗi: " + e.getMessage()));
    
      response.setHeader("X-JSON", jsonRes.toString());
      } finally {
        conn.close();
      }
      return mapping.findForward("success");
      } 
}
