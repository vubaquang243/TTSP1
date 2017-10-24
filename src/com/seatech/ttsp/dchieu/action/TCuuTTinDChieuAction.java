package com.seatech.ttsp.dchieu.action;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.FontUtil;
import com.seatech.framework.utils.StringUtil;
import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DChieu1VO;
import com.seatech.ttsp.dchieu.DChieu2DAO;
import com.seatech.ttsp.dchieu.DChieu2VO;
import com.seatech.ttsp.dchieu.DChieu3DAO;
import com.seatech.ttsp.dchieu.DChieu3VO;
import com.seatech.ttsp.dchieu.DuyetKQDCVO;
import com.seatech.ttsp.dchieu.KQDChieu3CTietDAO;
import com.seatech.ttsp.dchieu.KQDChieu3DAO;
import com.seatech.ttsp.dchieu.KQDChieu3VO;
import com.seatech.ttsp.dchieu.form.TCuuTTinDChieuForm;
import com.seatech.ttsp.thamsokb.ThamSoKBDAO;
import com.seatech.ttsp.thamsokb.ThamSoKBVO;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanDAO;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanVO;

import java.io.InputStream;
import java.io.PrintWriter;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.util.Iterator;
import com.seatech.ttsp.dchieu.XNKQDCDataVO;
import com.seatech.ttsp.dchieungoaite.DChieuNgoaiTeDAO;
import com.seatech.ttsp.dmnhkb.DMHTKBacVO;
import com.seatech.ttsp.dmnhkb.DMNHKBacDAO;
import com.seatech.ttsp.dmtiente.DMTienTeDAO;

import com.seatech.ttsp.qtoanbu.QToanBuDAO;
import com.seatech.ttsp.qtoanbu.QToanBuVO;
import com.seatech.ttsp.tsoSDuCOT.TSoSDuCOTDAO;

import com.seatech.ttsp.tsoSDuCOT.TSoSDuCOTVO;

import java.math.BigDecimal;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Map;

/*HungBM edit: Sua tra cuu list
 * 
 * Thay doi code tra cuu
 * 24/11/2016
 * Find: 20161124
 */

 /*
 * @modify: ThuongDT
 * @modify date:13/02/2017
 * @see: lay quyet toan thu, chi tam bo sung them vao bao cao
 * @Find: ThuongDT-20170213
 */

 /*
 * @modify: ThuongDT
 * @modify date:15/02/2017
 * @see: lay them so du dau ngay bo sung them vao bao cao
 * @Find: ThuongDT-20170215
 */

 /*
 * @modify: ThuongDT
 * @modify date:03/03/2017
 * @see: cho phep va them chuc nang in bao cao doi voi tai khoan chuyen thu
 * @Find: ThuongDT-20170303
 */

 /*
 * @modify: HungBM
 * @modify date:20/03/2017
 * @see: Them tieu thuc tra cuu bang ke theo pham vi doi chieu
 * @Find: HungBM-20170320
 */

 
//HungBM edit list 20161124
public class TCuuTTinDChieuAction extends AppAction {
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {        
        Connection conn = null;
        //hungbm
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            TCuuTTinDChieuForm TTForm = (TCuuTTinDChieuForm)form;
            DMTienTeDAO tienTeDAO = new DMTienTeDAO(conn);
            DChieu1DAO dao = new DChieu1DAO(conn);
            TTThanhToanDAO TTdao = new TTThanhToanDAO(conn);
            DChieu1VO vo = new DChieu1VO();
            
            int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
            String page = TTForm.getPageNumber();
			//Bo sung kiem tra page bi trang
            if (page == null || "".equals(page))
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[15];

            String kb_code = session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
            String kb_id = session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();

            List dmucNH = (List)TTdao.getDMucNH(null,null);
            request.setAttribute("dmucNH", dmucNH);

            String strCap = " and ma=" + kb_code;
            vo = dao.getCap(strCap, null);
            String cap = vo.getCap();
            String strDC3 = "";
            String conditionKBCha = " ";
            String conditionDChieu = null;
            if ("0001".equals(kb_code) || "0002".equals(kb_code)) {
                conditionDChieu = " and (a.trang_thai NOT IN ('01', '02') OR a.trang_thai IS NULL)";

                request.setAttribute("dftinh", "dftinh");
                request.setAttribute("dchieu3", "dchieu3");
            } else if ("0003".equals(kb_code)) {
                conditionKBCha = " AND a.ma='0003' ";
                conditionDChieu = " and c.id_kb_huyen= " + kb_id + " and (a.trang_thai NOT IN ('01', '02') OR a.trang_thai IS NULL)";
            }else if ("0002".equals(kb_code)) {
                conditionKBCha = " AND a.ma='0002' ";
                conditionDChieu = " and c.id_kb_huyen= " + kb_id + " and (a.trang_thai NOT IN ('01', '02') OR a.trang_thai IS NULL)";
            } else if ("5".equals(cap)) {
                conditionKBCha = " and c.ma=" + kb_code;
                conditionDChieu = " and c.id_kb_tinh=" + kb_id + " and (a.trang_thai NOT IN ('01', '02') OR a.trang_thai IS NULL)";
            } else {
                conditionKBCha = " and c.id in (select id_cha from sp_dm_htkb where ma=" + kb_code + ")";
                conditionDChieu = "  and c.id_kb_huyen=" + kb_id + " AND (a.trang_thai NOT IN ('01', '02') OR a.trang_thai IS NULL)";
            }
            List dmucTienTe = tienTeDAO.simpleMaNgoaiTe(" AND a.MA_NT <> 'VND' ",null);
			//Lay danh muc tinh
            Collection dmuckb_cha = dao.getDMucKB_Tinh(conditionKBCha, null);
            Collection colTHBKDC = dao.getTCuuDChieu_ptrang(conditionDChieu, strDC3, null, currentPage, numberRowOnPage, totalCount);
            request.setAttribute("colTHBKDC", colTHBKDC);
            
            request.setAttribute("dmuckb_tinh", dmuckb_cha);
			//Bo sung kiem tra truong hop totalCount[0] = null
            if(totalCount[0]==null){
              totalCount[0]=0;
            }
            request.setAttribute("dmucTien", dmucTienTe);
        
            PagingBean pagingBean = new PagingBean();

            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow(totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);
            int tong_row = totalCount[0].intValue();
            request.setAttribute("tong_row", tong_row);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("initAction", "initAction");

        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

    protected ActionForward executeAction(ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {

        Connection conn = null;
        try {
            String strMaKB;
            String strJSON;
            String strWhereClause = "";
            conn = getConnection(request);
            DChieu1DAO ttdao = new DChieu1DAO(conn);
            Collection col = null;
            //            Collection colNH = null;
            HttpSession session = request.getSession();
            String kb_code =
                session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
            strMaKB = request.getParameter("kb_id").toString();
            DChieu1VO vo = new DChieu1VO();
            String strCap = " and ma=" + kb_code;
            vo = ttdao.getCap(strCap, null);
            String cap = vo.getCap();
            if ("0001".equals(kb_code) || "0002".equals(kb_code) ||
                "0003".equals(kb_code)) { // SGD TTTT
                if ("3".equals(strMaKB) || "1".equals(strMaKB)) {
                    strWhereClause += " and a.ma='0003'";
                } else  if ("2".equals(strMaKB) ) {
                      strWhereClause += " and a.ma='0002'";
                } else {
                    strWhereClause +=
                            " and a.id_cha = " + strMaKB + " and a.ma<>'0003'";
                }
                col = ttdao.getDMucKB_huyen(strWhereClause, null);
            } else {
                if ("5".equals(cap)) { // cap tinh
                    strWhereClause +=
                            " and a.id_cha = " + strMaKB; // + " and ma=" + kb_code;
					//Lay danh muc tinh
                    col = ttdao.getDMucKB_Tinh(strWhereClause, null);
                    //strWhereClause += " and a.id_cha=" + strMaKB;
                    col = ttdao.getDMucKB_huyen(strWhereClause, null);
                }else if("2".equals(cap)){
                    String whereHTKB = " ma = ?";
                    Vector vector = new Vector(); vector.add(new Parameter(kb_code,true));
                    DMHTKBacVO temp = new DMHTKBacVO();
                    DMNHKBacDAO tempDAO = new DMNHKBacDAO(conn);
                    temp = tempDAO.getDMHTKBac(whereHTKB,vector);
                    strWhereClause = " and a.ma_cha=" + temp.getMa_cha();
                    col = ttdao.getDMucKB_huyen(strWhereClause, null);
                }else {
                    strWhereClause += " and a.ma=" + kb_code;
                    col = ttdao.getDMucKB_huyen(strWhereClause, null);
                }
            }

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
            JSONObject jsonRes = new JSONObject();
            jsonRes.put("executeError",
                        FontUtil.unicodeEscape("Lỗi: " + e.getMessage()));

            response.setHeader("X-JSON", jsonRes.toString());
        } finally {
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }
    // Get DMuc NHang

    public ActionForward updateExc(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        Connection conn = null;
        try {
            String strMaKB;
            String strJSON;

            conn = getConnection(request);
            //            DMKBacDAO dmdao = new DMKBacDAO(conn);
            TTThanhToanDAO ttdao = new TTThanhToanDAO(conn);

            Collection colNH = null;
            HttpSession session = request.getSession();
            String kb_code =
                session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
            strMaKB = request.getParameter("nhkb_id").toString();
            TTThanhToanVO vo = new TTThanhToanVO();
            String strCap = " and ma=" + kb_code;
            vo = ttdao.getCap(strCap, null);
            String strNH = " and b.id=" + strMaKB;
            colNH = ttdao.getListNH(strNH, null);

            java.lang.reflect.Type listNH =
                new TypeToken<Collection<TTThanhToanVO>>() {
            }.getType();
            strJSON = new Gson().toJson(colNH, listNH);

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
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
      
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            String kb_code = session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
            
            TCuuTTinDChieuForm TTForm = (TCuuTTinDChieuForm)form;
            DChieu1DAO dcDao = new DChieu1DAO(conn);
            TTThanhToanDAO TTdao = new TTThanhToanDAO(conn);
            DMTienTeDAO tienTeDAO = new DMTienTeDAO(conn);
            DChieu1VO vo = new DChieu1VO();
            
            String strCap = " and ma=" + kb_code;
            vo = dcDao.getCap(strCap, null);
            String cap = vo.getCap();
            String conditionKBCha = " ";
            if ("0001".equals(kb_code) || "0002".equals(kb_code)) {
                request.setAttribute("kb_huyen", TTForm.getNhkb_huyen());
                request.setAttribute("dchieu3", "dchieu3");
                request.setAttribute("dftinh", "dftinh");
            } else if ("0003".equals(kb_code)) {
                conditionKBCha = " and a.ma=" + kb_code;
            } else if ("5".equals(cap)) {
                conditionKBCha = " and c.ma=" + kb_code;
                request.setAttribute("kb_huyen", TTForm.getNhkb_huyen());
                request.setAttribute("dftinh", "dftinh");
            } else {
                conditionKBCha = " and  c.id in (select id_cha from sp_dm_htkb where ma=" + kb_code + ")";
                request.setAttribute("huyen", "huyen");
            }
            List dmucTienTe = tienTeDAO.simpleMaNgoaiTe(" AND a.MA_NT <> 'VND' ",null);
            List dmucNH = (List)TTdao.getDMucNH(null,null);
			//Lay danh muc tinh
            List dmuckb_cha = (List)dcDao.getDMucKB_Tinh(conditionKBCha, null);
            
            request.setAttribute("dmucTien", dmucTienTe);
            request.setAttribute("dmuckb_tinh", dmuckb_cha);
            request.setAttribute("dmucNH", dmucNH);

            String inxtthai = request.getParameter("inxtthai");
            String lan_dc = TTForm.getLan_dc();
            request.setAttribute("inlan_dc", lan_dc);
            Integer currentPage;
            //hungbm-20161124- thay the cho dieu kien tim kiem phia duoi-begin            
            if (TTForm.getPageNumber() == null || "".equalsIgnoreCase(TTForm.getPageNumber())){
               currentPage=  new Integer("1");
            }
            else {
               currentPage=  new Integer(TTForm.getPageNumber());
            }                
            //Integer currentPage = TTForm.getPageNumber() == null ? new Integer("1") : new Integer(TTForm.getPageNumber());
            //hungbm-20161124- thay the cho dieu kien tim kiem phia duoi-end
            Integer totalCount[] = new Integer[15];
            Integer numberRowOnPage = AppConstants.APP_NUMBER_ROW_ON_PAGE;
            
            Collection colTHBKDC = new ArrayList();
            Collection colTHBKDC2 = new ArrayList();
            String strDC3 = "";
          //HungBM-20170320-Lay pham vi doi chieu tu form - BEGIN
            String pham_vi_doi_chieu = TTForm.getPham_vi_doi_chieu();
          //HungBM-20170320-Lay pham vi doi chieu tu form - END
			//Lay trang thai tai khoan tu form
            String trang_thai_tk = (String)request.getParameter("trang_thai_tk");
          Map mapConditionDChieu = null;
          if ("3".equals(pham_vi_doi_chieu))
           mapConditionDChieu = createConditionDC3VND(TTForm,inxtthai);
          else
            mapConditionDChieu = createConditionDCVND(TTForm,inxtthai);
            String conditionDChieu = mapConditionDChieu.get("conditionDC").toString();
          //hungbm lay lan doi chieu va nang cap tra cuu doi chieu cua VND va Ngoai Te
          //20161124
            //Begin
            if(mapConditionDChieu.get("lan_dc") != null)
                lan_dc = mapConditionDChieu.get("lan_dc").toString();
            if(TTForm.getMa_nt() != null && !"".equals(TTForm.getMa_nt())){
                if(TTForm.getMa_nt().equals("VND")){
                  //HungBM-20170320-Them pham vi doi chieu vao ham tra cuu - BEGIN
                    colTHBKDC = getKQuaTCuuDC(dcDao, strDC3, currentPage, numberRowOnPage, totalCount, lan_dc, conditionDChieu,trang_thai_tk,pham_vi_doi_chieu);
                  //HungBM-20170320-Them pham vi doi chieu vao ham tra cuu - END
                }else{
                    DChieuNgoaiTeDAO dchieuNgoaiTeDAO = new DChieuNgoaiTeDAO(conn);
					//Su dung ham moi, phuc vu trong tra cuu doi chieu
                    colTHBKDC = dchieuNgoaiTeDAO.getTCuuDChieu_nangcap_ptrang(conditionDChieu, strDC3, null, currentPage, numberRowOnPage, totalCount,trang_thai_tk);
                }
            }
            //END
            String inKB = request.getParameter("inKB");
            String inNH = request.getParameter("inNH");
            String tcuu =
                "&nhkb_tinh=" + TTForm.getNhkb_tinh() + "&nhkb_huyen=" + TTForm.getNhkb_huyen() +
                "&ma_dv=" + TTForm.getMa_dv() + "&inKB=" + inKB + "&inNH=" + inNH +
                "&inxtthai=" + inxtthai + "&tu_ngay=" + TTForm.getTu_ngay() +
                "&den_ngay=" + TTForm.getDen_ngay() + "&lan_dc=" + lan_dc +
                "&currentPage=" + currentPage;
            request.setAttribute("tcuu", tcuu);
            request.setAttribute("kb_huyen", inKB);
            request.setAttribute("ngan_hang", inNH);
            request.setAttribute("inxtthai", inxtthai);
            
            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
			//Bo sung kiem tra totalCount[0] truong hop = null
            if(totalCount[0]==null){
              totalCount[0]=0;
            }
            pagingBean.setNumberOfRow(totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);

            int tong_row = totalCount[0].intValue();
            request.setAttribute("tong_row", tong_row);
            request.setAttribute("currentPage", currentPage);

            if (colTHBKDC.isEmpty() && colTHBKDC2.isEmpty()) {
                return mapping.findForward("failure");
            }
            request.setAttribute("colTHBKDC", colTHBKDC);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }
  //HungBM-20170320-Them pham_vi_doi_chieu vao dieu kien tra cuu
  private Collection getKQuaTCuuDC(DChieu1DAO dcDao, String strDC3,
                                   Integer currentPage,
                                   Integer numberRowOnPage,
                                   Integer[] totalCount, String lan_dc,
                                   String conditionDChieu, String trang_thai_tk, String pham_vi_doi_chieu) throws Exception {//them bien dau vao trang_thai_tk de bo sung dieu kien tra cuu theo trang thai tai khoan
      Collection colTHBKDC = new ArrayList();

       //HungBM-20161124-Thay the cac ham tra cuu cu bang cac ham tra cuu moi-begin
       //HungBM-20170330-Bo sung tieu thuc tim kiem theo pham vi doi chieu - BEGIN
      
       if ("1".equals(pham_vi_doi_chieu)) {                         
           colTHBKDC = dcDao.getTCuu1DChieu_nangcap_ptrang(conditionDChieu, null, currentPage, numberRowOnPage, totalCount,trang_thai_tk);
       } else if ("2".equals(pham_vi_doi_chieu)) {                        
           colTHBKDC = dcDao.getTCuu2DChieu_nangcap_ptrang(conditionDChieu, null, currentPage, numberRowOnPage, totalCount,trang_thai_tk);
       } else if ("3".equals(pham_vi_doi_chieu)) {                       
           colTHBKDC = dcDao.getTCuu3DChieu_nangcap_ptrang(conditionDChieu, null, currentPage, numberRowOnPage, totalCount,trang_thai_tk);
       } else if ("".equals(pham_vi_doi_chieu) || pham_vi_doi_chieu == null) {         
           colTHBKDC = dcDao.getTCuuDChieu_nangcap_ptrang(conditionDChieu, strDC3, null, currentPage, numberRowOnPage, totalCount,trang_thai_tk);
       } 
        //HungBM-20170330-Bo sung tieu thuc tim kiem theo pham vi doi chieu - END
        
  //          if ("1".equals(lan_dc)) {
  //              colTHBKDC = dcDao.getTCuu1DChieu_ptrang(conditionDChieu, null, currentPage, numberRowOnPage, totalCount);
  //          } else if ("2".equals(lan_dc)) {
  //              colTHBKDC = dcDao.getTCuu2DChieu_ptrang(conditionDChieu, null, currentPage, numberRowOnPage, totalCount);
  //          } else if ("3".equals(lan_dc)) {
  //              colTHBKDC = dcDao.getTCuu3DChieu_ptrang(conditionDChieu, null, currentPage, numberRowOnPage, totalCount);
  //          } else if ("".equals(lan_dc) || lan_dc == null) {
  //              colTHBKDC = dcDao.getTCuuDChieu_ptrang(conditionDChieu, strDC3, null, currentPage, numberRowOnPage, totalCount);
  //          }
      //HungBM-20161124-Thay the cac ham tra cuu cu bang cac ham tra cuu moi-end
      
      return colTHBKDC;
  }
  private Map createConditionDC3VND(TCuuTTinDChieuForm formTraCuu, String inxtthai){
      StringBuilder conditionDC = new StringBuilder();
      String tthai = formTraCuu.getTthai_dxn_thop();
      String tu_ngay = formTraCuu.getTu_ngay();
      String den_ngay = formTraCuu.getDen_ngay();
      String lan_dc = null;
      String char_dngay = null;
      String char_tngay = null;
      if (den_ngay != null && !"".equals(den_ngay)) {
          char_dngay = StringUtil.DateToString(StringUtil.StringToDate(den_ngay, "dd/MM/yyyy"), "yyyy/MM/dd").replace("/", "");
      }
      if (tu_ngay != null && !"".equals(tu_ngay)) {
          char_tngay = StringUtil.DateToString(StringUtil.StringToDate(tu_ngay, "dd/MM/yyyy"), "yyyy/MM/dd").replace("/", "");
      }
      if (formTraCuu.getNhkb_tinh() != null && !"".equals(formTraCuu.getNhkb_tinh())) {
          conditionDC.append(" AND c.id_cha = " + formTraCuu.getNhkb_tinh() + " ");
      }
      if(formTraCuu.getMa_nt() != null && !"".equals(formTraCuu.getMa_nt()) && !"VND".equals(formTraCuu.getMa_nt())){
          conditionDC.append(" AND c.ma_nt = '"+formTraCuu.getMa_nt()+"' ");
      }
      if (formTraCuu.getNhkb_huyen() != null && !"".equals(formTraCuu.getNhkb_huyen())) {
          conditionDC.append(" and c.id = " + formTraCuu.getNhkb_huyen());
      }
      if (formTraCuu.getMa_dv() != null && !"".equals(formTraCuu.getMa_dv())) {
          conditionDC.append(" and substr(a.SEND_BANK,3,3) = '" + formTraCuu.getMa_dv() + "'");
      }
      if (tthai != null && !"".equals(tthai)) {
          if ("00".equals(tthai)) {
              conditionDC.append(" and ((a.trang_thai = '00' or a.trang_thai is null) or b.trang_thai = '00')");
          } else if ("0101".equals(tthai)) {
              conditionDC.append(" and a.trang_thai = '01' and b.trang_thai='01'");
          } else if ("0102".equals(tthai)) {
              conditionDC.append(" and a.trang_thai = '01' and b.trang_thai='02'");
          } else if ("0201".equals(tthai)) {
              conditionDC.append(" and a.trang_thai = '02' and b.trang_thai='01'");
          } else if ("0202".equals(tthai)) {
              conditionDC.append(" and a.trang_thai = '02' and b.trang_thai='02'");
          } else if ("0000".equals(tthai)) {
              lan_dc = "1";
              conditionDC.append(" and (b.tthai_dxn_thop = '00' or b.tthai_dxn_thop is null)");
          } else if ("0301".equals(tthai)) { // XNQT ch?a duyet
              lan_dc = "1";
              conditionDC.append(" and b.tthai_dxn_thop = '01'");
          } else if ("0302".equals(tthai)) { // XNQT da duyet
              lan_dc = "1";
              conditionDC.append(" and b.tthai_dxn_thop = '02'");
          }
      } else if ((tthai == null || "".equals(tthai)) || (inxtthai != null || !"".equals(inxtthai))) {
          if ("0".equals(inxtthai)) {
              conditionDC.append(" and (a.trang_thai = '00' or b.trang_thai = '00')");
          } else if ("1".equals(inxtthai)) {
              conditionDC.append(" and a.trang_thai = '01' and b.trang_thai='01'");
          } else if ("2".equals(inxtthai)) {
              conditionDC.append(" and a.trang_thai = '01' and b.trang_thai='02'");
          } else if ("3".equals(inxtthai)) {
              conditionDC.append(" and a.trang_thai = '02' and b.trang_thai='01'");
          } else if ("4".equals(inxtthai)) {
              conditionDC.append(" and a.trang_thai = '02' and b.trang_thai='02'");
          } else if ("5".equals(inxtthai)) {
              if ("1".equals(lan_dc) || "".equals(lan_dc)) {
                  lan_dc = "1";
              }
          } else if ("6".equals(inxtthai)) { // ch?a duyet XNQT
              lan_dc = "1";
              conditionDC.append(" and b.tthai_dxn_thop = '01'");
          } else if ("7".equals(inxtthai)) { // da duyet XNQT
              lan_dc = "1";
              conditionDC.append(" and b.tthai_dxn_thop in ('02','03')");
          }
      }
      if (tu_ngay != null && !"".equals(tu_ngay) && den_ngay != null && !"".equals(den_ngay)) {
          conditionDC.append(" and (a.NGAY_DC >=  to_date('" + char_tngay + "','yyyyMMdd') and a.NGAY_DC <=  to_date('" + char_dngay + "','yyyyMMdd') ) ");
      }
      if ((den_ngay == null || "".equals(den_ngay)) && tu_ngay != null && !"".equals(tu_ngay)) {
          conditionDC.append(" and (a.NGAY_DC <=  sysdate and a.NGAY_DC >=  to_date('" + char_tngay + "','yyyyMMdd')) ");
      } else if (den_ngay != null && !"".equals(den_ngay) && (tu_ngay == null || "".equals(tu_ngay))) {
          conditionDC.append(" and c.ngay_order <= '" + char_dngay + "'");
      }
      Map result = new HashMap();
      result.put("conditionDC", conditionDC.toString());
      result.put("lan_dc", lan_dc);
      return result;
    }
    
    private Map createConditionDCVND(TCuuTTinDChieuForm formTraCuu, String inxtthai){
        StringBuilder conditionDC = new StringBuilder();
        
        String tthai = formTraCuu.getTthai_dxn_thop();
        String tu_ngay = formTraCuu.getTu_ngay();
        String den_ngay = formTraCuu.getDen_ngay();
        
        String lan_dc = null;
        String char_dngay = null;
        String char_tngay = null;
        if (den_ngay != null && !"".equals(den_ngay)) {
            char_dngay = StringUtil.DateToString(StringUtil.StringToDate(den_ngay, "dd/MM/yyyy"), "yyyy/MM/dd").replace("/", "");
        }
        if (tu_ngay != null && !"".equals(tu_ngay)) {
            char_tngay = StringUtil.DateToString(StringUtil.StringToDate(tu_ngay, "dd/MM/yyyy"), "yyyy/MM/dd").replace("/", "");
        }
        if (formTraCuu.getNhkb_tinh() != null && !"".equals(formTraCuu.getNhkb_tinh())) {
            conditionDC.append(" AND (c.id_cha = " + formTraCuu.getNhkb_tinh() + " OR c.id_kb_tinh =" + formTraCuu.getNhkb_tinh() + ")");
        }
        if(formTraCuu.getMa_nt() != null && !"".equals(formTraCuu.getMa_nt()) && !"VND".equals(formTraCuu.getMa_nt())){
            conditionDC.append(" AND c.ma_nt = '"+formTraCuu.getMa_nt()+"' ");
        }
        if (formTraCuu.getNhkb_huyen() != null && !"".equals(formTraCuu.getNhkb_huyen())) {
            conditionDC.append(" and c.id_kb_huyen = " + formTraCuu.getNhkb_huyen());
        }
        if (formTraCuu.getMa_dv() != null && !"".equals(formTraCuu.getMa_dv())) {
            conditionDC.append(" and substr(c.ma_nh,3,3) = '" + formTraCuu.getMa_dv() + "'");
        }
        if (tthai != null && !"".equals(tthai)) {
            if ("00".equals(tthai)) {
                conditionDC.append(" and ((a.trang_thai = '00' or a.trang_thai is null) or b.trang_thai = '00')");
            } else if ("0101".equals(tthai)) {
                conditionDC.append(" and a.trang_thai = '01' and b.trang_thai='01'");
            } else if ("0102".equals(tthai)) {
                conditionDC.append(" and a.trang_thai = '01' and b.trang_thai='02'");
            } else if ("0201".equals(tthai)) {
                conditionDC.append(" and a.trang_thai = '02' and b.trang_thai='01'");
            } else if ("0202".equals(tthai)) {
                conditionDC.append(" and a.trang_thai = '02' and b.trang_thai='02'");
            } else if ("0000".equals(tthai)) {
                lan_dc = "1";
                conditionDC.append(" and (b.tthai_dxn_thop = '00' or b.tthai_dxn_thop is null)");
            } else if ("0301".equals(tthai)) { // XNQT ch?a duyet
                lan_dc = "1";
                conditionDC.append(" and b.tthai_dxn_thop = '01'");
            } else if ("0302".equals(tthai)) { // XNQT da duyet
                lan_dc = "1";
                conditionDC.append(" and b.tthai_dxn_thop = '02'");
            }
        } else if ((tthai == null || "".equals(tthai)) || (inxtthai != null || !"".equals(inxtthai))) {
            if ("0".equals(inxtthai)) {
                conditionDC.append(" and (a.trang_thai = '00' or b.trang_thai = '00')");
            } else if ("1".equals(inxtthai)) {
                conditionDC.append(" and a.trang_thai = '01' and b.trang_thai='01'");
            } else if ("2".equals(inxtthai)) {
                conditionDC.append(" and a.trang_thai = '01' and b.trang_thai='02'");
            } else if ("3".equals(inxtthai)) {
                conditionDC.append(" and a.trang_thai = '02' and b.trang_thai='01'");
            } else if ("4".equals(inxtthai)) {
                conditionDC.append(" and a.trang_thai = '02' and b.trang_thai='02'");
            } else if ("5".equals(inxtthai)) {
                if ("1".equals(lan_dc) || "".equals(lan_dc)) {
                    lan_dc = "1";
                }
            } else if ("6".equals(inxtthai)) { // ch?a duyet XNQT
                lan_dc = "1";
                conditionDC.append(" and b.tthai_dxn_thop = '01'");
            } else if ("7".equals(inxtthai)) { // da duyet XNQT
                lan_dc = "1";
                conditionDC.append(" and b.tthai_dxn_thop in ('02','03')");
            }
        }
        if (tu_ngay != null && !"".equals(tu_ngay) && den_ngay != null && !"".equals(den_ngay)) {
            conditionDC.append(" and (c.ngay_order >=  '" + char_tngay + "' and c.ngay_order <=  '" + char_dngay + "') ");
        }
        if ((den_ngay == null || "".equals(den_ngay)) && tu_ngay != null && !"".equals(tu_ngay)) {
            conditionDC.append(" and (c.ngay_order <=  to_char(sysdate,'YYYYMMDD') and c.ngay_order >=  '" + char_tngay + "') ");
        } else if (den_ngay != null && !"".equals(den_ngay) && (tu_ngay == null || "".equals(tu_ngay))) {
            conditionDC.append(" and c.ngay_order <= '" + char_dngay + "'");
        }
        Map result = new HashMap();
        result.put("conditionDC", conditionDC.toString());
        result.put("lan_dc", lan_dc);
        return result;
    }

    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "DCHIEU.TCUU_DCHIEU")) {
            return mapping.findForward("notRight");
        }

        Connection conn = null;
        try {
            conn = getConnection(request);
            DChieu1DAO dao = new DChieu1DAO(conn);
            DChieuNgoaiTeDAO ngoaiTeDAO = new DChieuNgoaiTeDAO(conn);
            DChieu1VO vo = new DChieu1VO();
            TCuuTTinDChieuForm DCForm = (TCuuTTinDChieuForm)form;

            Collection colKQDCCT = new ArrayList();
			//hungbm-20161124- Khong dung form phia tren, chuyen dung parameter trong link html- begin
//            String type = DCForm.getType();
//            String nhkb_tinh = DCForm.getNhkb_tinh() == null ? "" : DCForm.getNhkb_tinh();
//            String nhkb_huyen = DCForm.getNhkb_huyen() == null ? "" : DCForm.getNhkb_huyen();
//            String ngan_hang = DCForm.getMa_dv() == null ? "" : DCForm.getMa_dv();
//            String inxtthai = DCForm.getInxtthai() == null ? "" : DCForm.getInxtthai();
//            String tu_ngay = DCForm.getTu_ngay() == null ? "" : DCForm.getTu_ngay();
//            String den_ngay = DCForm.getDen_ngay() == null ? "" : DCForm.getDen_ngay();
//            String inKB = DCForm.getInKB() == null ? "" : DCForm.getInKB();
//            String inNH = DCForm.getInNH() == null ? "" : DCForm.getInNH();
//            String lan_dc = DCForm.getLan_dc() == null ? "" : DCForm.getLan_dc();
//            String currentPage = DCForm.getCurrentPage() == null ? "" : DCForm.getCurrentPage();
            String trang_thai_kq = DCForm.getTrang_thai_kq() == null ? "" : DCForm.getTrang_thai_kq();
           
            String type = (String)request.getParameter("type");
            String nhkb_tinh = (String)request.getParameter("nhkb_tinh");
            String nhkb_huyen= (String)request.getParameter("nhkb_huyen");
            String ngan_hang = (String)request.getParameter("ma_dc");
            String inxtthai = (String)request.getParameter("inxtthai");
            String tu_ngay = (String)request.getParameter("tu_ngay");
            String den_ngay = (String)request.getParameter("den_ngay");
            String inKB = (String)request.getParameter("inKB");
            String inNH = (String)request.getParameter("inNH");
            String lan_dc = (String)request.getParameter("lan_dc");
            String currentPage = (String)request.getParameter("currentPage");
            String ma_nt = (String)request.getParameter("ma_nt");
            String ngay_dc = (String)request.getParameter("ngay_dc");
            String bkq_id = (String)request.getParameter("bkq_id");
            String receive_bank = (String)request.getParameter("receive_bank");
            String send_bank = (String)request.getParameter("send_bank");
            String loai_tien = (String)request.getParameter("loai_tien");
			//hungbm-20161124-Khong dung form phia tren, chuyen dung parameter trong link html- end
            //Hungbm-20161124-edit lua chon giua ma_nt va loai_tien-begin            
            if ("".equals(ma_nt)){
              ma_nt= loai_tien;
            }
            //Hungbm-20161124-edit lua chon giua ma_nt va loai_tien-end
			//Hungbm-20161126-sua lay theo ten bien-begin
            if ("bk".equals(type)) {
                //hungbm edit //20161124
                //String bk_id = bkq_id;
                String bk_id = DCForm.getBk_id();
                
                String strUD;
                
                //hungbm 
                if ("VND".equals(ma_nt))
                //if (DCForm.getMa_nt().equals("VND"))
                    strUD = " and a.id='" + bk_id + "' AND c.trang_thai='"+trang_thai_kq+"'";
                else
                    //hungbm edit
                    strUD = " AND a.loai_tien = c.loai_tien and a.id='" + bk_id + "' AND c.trang_thai='"+trang_thai_kq+"' AND a.loai_tien = '"+ma_nt+"'";
                    //strUD = " AND a.loai_tien = c.loai_tien and a.id='" + bk_id + "' AND c.trang_thai='"+trang_thai_kq+"' AND a.loai_tien = '"+DCForm.getMa_nt()+"'";
                
                if("04".equals(trang_thai_kq)){
                    //hungbm edit
                    strUD+= " AND c.id='"+bkq_id+"'";
                    //strUD+= " AND c.id='"+DCForm.getBkq_id()+"'";
                }
                ArrayList<DChieu1VO> colDChieu =
                    //hungbm
                    (ArrayList<DChieu1VO>)(ma_nt.equals("VND") ? dao.getListViewCTietBCao(strUD, null) : ngoaiTeDAO.getListViewCTietBCao(strUD, null));
                    //(ArrayList<DChieu1VO>)(DCForm.getMa_nt().equals("VND") ? dao.getListViewCTietBCao(strUD, null) : ngoaiTeDAO.getListViewCTietBCao(strUD, null));
                if (colDChieu.isEmpty()) {
                    return mapping.findForward("failure");
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
                String strUD2;
                //hungbm 
                if (ma_nt.equals("VND")){
                //if (DCForm.getMa_nt().equals("VND")){
                  strUD2 =
                      " AND ( (b.trang_thai <> '04'" + " AND EXISTS (SELECT	 1 FROM	 sp_065" +
                      " WHERE	 bk_id = '" + bk_id +
                      "' AND trang_thai IN ('01', '02', '00')))" +
                      " OR (b.trang_thai = '04' AND not EXISTS" +
                      " (SELECT	1 FROM	sp_065 WHERE	bk_id ='" + bk_id +
                      "' AND trang_thai IN ('01', '02', '00'))) or b.trang_thai is null )" +
                      " AND  a.id= '" + bk_id + "'";
                }else{
                  strUD2 =
                      " AND ( (b.trang_thai <> '04'" + " AND EXISTS (SELECT  1 FROM  sp_065_ngoai_te" +
                      //hungbm
                      " WHERE  id = '" + bkq_id +
                      //" WHERE  id = '" + DCForm.getBkq_id() +
                      "' AND trang_thai IN ('01', '02', '00')))" +
                      " OR (b.trang_thai = '04' AND not EXISTS" +
                      //hungbm
                      " (SELECT 1 FROM  sp_065_ngoai_te WHERE  id ='" + bkq_id +
                      //" (SELECT 1 FROM  sp_065_ngoai_te WHERE  id ='" + DCForm.getBkq_id() +
                      "' AND trang_thai IN ('01', '02', '00'))) or b.trang_thai is null )" +
                      " AND  a.id= '" + bk_id + "'";
                }

                Collection colThop = new ArrayList();
                //hungbm
                colThop = ma_nt.equals("VND") ? dao.getDChieuList(strUD2, null) : ngoaiTeDAO.getDChieuList(strUD2, null);
                //colThop = DCForm.getMa_nt().equals("VND") ? dao.getDChieuList(strUD2, null) : ngoaiTeDAO.getDChieuList(strUD2, null);
                request.setAttribute("colThop", colThop);
                
                String kq_id;
                //hungbm
                if (ma_nt.equals("VND")){
                //if (DCForm.getMa_nt().equals("VND")){
                  kq_id = DCForm.getKq_id();
                }else{
                  //hungbm
                  kq_id = bkq_id;
                  //kq_id = DCForm.getBkq_id();
                }
                String strWhere = kq_id;

                Collection colKQDC = new ArrayList();
                //hungbm
                colKQDC = ma_nt.equals("VND") ? dao.getKQDChieu(strWhere, null) : ngoaiTeDAO.getKQDetailNgoaiTe(strWhere, null);
                //colKQDC = DCForm.getMa_nt().equals("VND") ? dao.getKQDChieu(strWhere, null) : ngoaiTeDAO.getKQDetailNgoaiTe(strWhere, null);
                request.setAttribute("colKQDC", colKQDC);
                
                //hungbm
                colKQDCCT = ma_nt.equals("VND") ? dao.getKQDCChiTiet(strWhere, null) : ngoaiTeDAO.getKQDCChiTiet(strWhere, null);
                //colKQDCCT = DCForm.getMa_nt().equals("VND") ? dao.getKQDCChiTiet(strWhere, null) : ngoaiTeDAO.getKQDCChiTiet(strWhere, null);
                request.setAttribute("colKQDCCT", colKQDCCT);

                String tcuu =
                    "?nhkb_tinh=" + nhkb_tinh + "&nhkb_huyen=" + nhkb_huyen +
                    "&ma_dv=" + ngan_hang + "&inKB=" + inKB + "&inNH=" + inNH +
                    "&inxtthai=" + inxtthai + "&tu_ngay=" + tu_ngay +
                    "&den_ngay=" + den_ngay + "&lan_dc=" + lan_dc +
                    //hungbm
                    "&currentPage=" + currentPage+"&loai_tien="+ma_nt+
                    "&ma_nt="+ma_nt;
                   // "&currentPage=" + currentPage+"&loai_tien="+DCForm.getMa_nt()+
                   // "&ma_nt="+DCForm.getMa_nt();
                request.setAttribute("tcuu", tcuu);

                request.setAttribute("view", "view");
                return mapping.findForward("successbk");
                
            } else if ("kq".equals(type)) {
                //hungbm //20161124
                String kq_id = bkq_id;
                //String kq_id = DCForm.getBkq_id();
                Collection colTTSP = new ArrayList();
                Collection colPHT = new ArrayList();
                Collection colTHDC = new ArrayList();
                Collection col066 = new ArrayList();
                ThamSoKBVO kbVO = new ThamSoKBVO();
                ThamSoKBDAO kbDAO = new ThamSoKBDAO(conn);
                //hungbm
                String frm_ma_nh = send_bank == null ? "" : DCForm.getSend_bank();
                String frm_ma_kb = receive_bank  == null ? "" : DCForm.getReceive_bank();
//                String frm_ma_nh = DCForm.getSend_bank() == null ? "" : DCForm.getSend_bank();
//                String frm_ma_kb = DCForm.getReceive_bank() == null ? "" : DCForm.getReceive_bank();

                String strKB = "";
                strKB = " and e.ten_ts='CHO_PHEP_QUYET_TOAN_TAM' AND c.ma_nh=" +
                        frm_ma_kb + " AND b.ma_nh='" + frm_ma_nh + "'";
                kbVO = kbDAO.getLoai_GD(strKB, null);

                String loai_gd = kbVO.getLoai_gd();
                //hungbm
                //String ngay_dc = DCForm.getNgay_dc();
                if ("02".equals(loai_gd)) {
                    request.setAttribute("notPHT", "notPHT");
                    request.setAttribute("pht_ttsp", "pht_ttsp");
                }
                if ("03".equals(loai_gd)) {
                    request.setAttribute("notTTSP", "notTTSP");
                    request.setAttribute("pht_ttsp", "pht_ttsp");
                }

                if (kq_id != null && !"".equals(kq_id)) {
                    String strUD = "AND a.id='" + kq_id + "'";
                    ArrayList<DuyetKQDCVO> colDChieu =
                        //hungbm
                        (ArrayList<DuyetKQDCVO>)(ma_nt.equals("VND") ? dao.getListXNDChieu(strUD, null) : ngoaiTeDAO.getListXNDChieu(strUD, null));
                        //(ArrayList<DuyetKQDCVO>)(DCForm.getMa_nt().equals("VND") ? dao.getListXNDChieu(strUD, null) : ngoaiTeDAO.getListXNDChieu(strUD, null));
                    if (colDChieu.isEmpty()) {
                        return mapping.findForward("failure");
                    }
                    String rowSelected = request.getParameter("rowSelected");
                    if (null == rowSelected || "".equals(rowSelected)) {
                        DuyetKQDCVO vo1 = new DuyetKQDCVO();
                        vo1 = colDChieu.get(0);
                        BeanUtils.copyProperties(DCForm, vo1);
                        request.setAttribute("rowSelected", "row_qt_0");
                    } else {
                        request.setAttribute("rowSelected", rowSelected);
                    }
                    request.setAttribute("colDChieu", colDChieu);


                    String strTTSP = "'" + kq_id + "'";
                    
                    //hungbm
                    String strPHT = ma_nt.equals("VND") ? " select decode(pht_id,null,'',pht_id) from sp_065 where id='" + kq_id + "'" : " select decode(pht_id,null,'',pht_id) from sp_065_ngoai_te where id='" + kq_id + "'";
                    //String strPHT = DCForm.getMa_nt().equals("VND") ? " select pht_id from sp_065 where id='" + kq_id + "'" : " select pht_id from sp_065_ngoai_te where id='" + kq_id + "'";

                    //hungbm
                    colTTSP = ma_nt.equals("VND") ? dao.getTTSP_PHT(strTTSP, null) : ngoaiTeDAO.getTTSP_PHT(strTTSP, null);
                    colPHT = ma_nt.equals("VND") ? dao.getTTSP_PHT(strPHT, null) : ngoaiTeDAO.getTTSP_PHT(strPHT,null);
                    colTHDC = ma_nt.equals("VND") ? dao.getXNTHData(strTTSP, null) : ngoaiTeDAO.getXNTHData(strTTSP,null);
                    
//                    colTTSP = DCForm.getMa_nt().equals("VND") ? dao.getTTSP_PHT(strTTSP, null) : ngoaiTeDAO.getTTSP_PHT(strTTSP, null);
//                    colPHT = DCForm.getMa_nt().equals("VND") ? dao.getTTSP_PHT(strPHT, null) : ngoaiTeDAO.getTTSP_PHT(strPHT,null);
//                    colTHDC = DCForm.getMa_nt().equals("VND") ? dao.getXNTHData(strTTSP, null) : ngoaiTeDAO.getXNTHData(strTTSP,null);

                    String str066 = 
                        " AND nhkb_chuyen= '" + kbVO.getMa_kb() + "' and nhkb_nhan='" + frm_ma_nh +
                        //hungbm
                        "' and to_char(ngay_qtoan,'DD/MM/RRRR')='" + ngay_dc + "' and loai_tien = '"+ma_nt+"' ";
                        //"' and to_char(ngay_qtoan,'DD/MM/RRRR')='" + ngay_dc + "' and loai_tien = '"+DCForm.getMa_nt()+"' ";
                    col066 = dao.getData066(str066, null);
                    request.setAttribute("col066", col066);
                    
                    String ngay_thien_dc = DCForm.getNgay_thuc_hien_dc();
                    String ngay_cuoi_nam = ngay_dc.substring(0, 5);
                    
                    if (!(ngay_dc).equals(ngay_thien_dc) && !"31/12".equals(ngay_cuoi_nam)) {
                        if (col066.size()>0){
                            request.setAttribute("colTHDC", colTHDC);
                            request.setAttribute("col066", col066);
                            request.setAttribute("colSoThuCong", null);
                        }else{
                            if(colTHDC == null){
                                colTHDC = new ArrayList();
                            }
                            if(colTHDC.isEmpty() == true){
                                colTHDC.add(new XNKQDCDataVO());
                            }
                                request.setAttribute("colSoThuCong", colTHDC);
                                request.setAttribute("colTHDC", null);
                                request.setAttribute("col066", null);
                        }                   
                    } else {
                        request.setAttribute("colSoThuCong", null);
                        request.setAttribute("colTHDC", colTHDC);
                        request.setAttribute("col066", col066);
                    }                    
                } else {
                    String strUD =
                        "AND TO_CHAR (b.ngay, 'DD/MM/YYYY')='" + ngay_dc + "'";
                    ArrayList<DuyetKQDCVO> colDChieu = (ArrayList<DuyetKQDCVO>)dao.getListView_066(strUD, kbVO.getMa_kb(), null);
                    if (colDChieu.isEmpty()) {
                        return mapping.findForward("failure");
                    }
                    String rowSelected = request.getParameter("rowSelected");
                    if (null == rowSelected || "".equals(rowSelected)) {
                        request.setAttribute("rowSelected", "row_qt_0");
                    } else {
                        request.setAttribute("rowSelected", rowSelected);
                    }
                    String str066 =
                        " AND nhkb_chuyen= '" + kbVO.getMa_kb() + "' and nhkb_nhan='" + frm_ma_nh +
                        "' and to_char(ngay_qtoan,'DD/MM/RRR R')='" + ngay_dc + "'";
                    
                    col066 = dao.getData066(str066, null);
                    request.setAttribute("col066", col066);
                    request.setAttribute("colDChieu", colDChieu);
                    
                    String ngay_cuoi_nam = ngay_dc.substring(0, 5);
                    if ( !"31/12".equals(ngay_cuoi_nam)) {
                        if (col066.size()>0){
                            request.setAttribute("colTHDC", colTHDC);
                            request.setAttribute("col066", col066);
                        }else{
                            request.setAttribute("colTHDC", null);
                            request.setAttribute("col066", null);
                        }         
                    } else {
                        request.setAttribute("colTHDC", colTHDC);
                        request.setAttribute("col066", col066);
                    }
                }
                request.setAttribute("colTTSP", colTTSP); 
                request.setAttribute("colPHT", colPHT);
                String tcuu =
                    "?nhkb_tinh=" + nhkb_tinh + "&nhkb_huyen=" + nhkb_huyen +
                    "&ma_dv=" + ngan_hang + "&inKB=" + inKB + "&inNH=" + inNH +
                    "&inxtthai=" + inxtthai + "&tu_ngay=" + tu_ngay +
                    "&den_ngay=" + den_ngay + "&lan_dc=" + lan_dc +
                    //hungbm
                    "&currentPage=" + currentPage+"&ma_nt="+ma_nt+"&trang_thai_kq=" + trang_thai_kq;
                    //"&currentPage=" + currentPage+"&ma_nt="+DCForm.getMa_nt()+"&trang_thai_kq=" + trang_thai_kq;
                //hungbm-lay tu form
                DCForm = (TCuuTTinDChieuForm)form;
                //end
                request.setAttribute("tcuu", tcuu);
                request.setAttribute("size", col066.size());
                request.setAttribute("view", "view");
                return mapping.findForward("success");   
				//Hungbm-20161126-sua lay theo ten bien-end
            }
        } catch (Exception e) {
            throw e;

        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

    public ActionForward search(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "DCHIEU.TCUU_DCHIEU")) {
            return mapping.findForward("notRight");
        }

        Connection conn = null;
        try {
            conn = getConnection(request);
//            HttpSession session = request.getSession();

            TCuuTTinDChieuForm DCForm = (TCuuTTinDChieuForm)form;
            Collection colTHBKDC = new ArrayList();
            String bk_id = DCForm.getId();
            String loai_dc = DCForm.getLoai_dc();

//            String kb_code =
//                session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();

            String receive_bank = DCForm.getReceive_bank();

            String nhkb_tinh =
                DCForm.getNhkb_tinh() == null ? "" : DCForm.getNhkb_tinh();
            String nhkb_huyen =
                DCForm.getNhkb_huyen() == null ? "" : DCForm.getNhkb_huyen();
            String ngan_hang =
                DCForm.getMa_dv() == null ? "" : DCForm.getMa_dv();
            String inxtthai =
                DCForm.getInxtthai() == null ? "" : DCForm.getInxtthai();
            String tu_ngay =
                DCForm.getTu_ngay() == null ? "" : DCForm.getTu_ngay();
            String den_ngay =
                DCForm.getDen_ngay() == null ? "" : DCForm.getDen_ngay();
            String lan_dc =
                DCForm.getLan_dc() == null ? "" : DCForm.getLan_dc();
            String currentPage =
                DCForm.getCurrentPage() == null ? "" : DCForm.getCurrentPage();
            String inKB = DCForm.getInKB() == null ? "" : DCForm.getInKB();
            String inNH = DCForm.getInNH() == null ? "" : DCForm.getInNH();


            if ("2".equals(loai_dc)) {

                DChieu2DAO daoDC2 = new DChieu2DAO(conn);
                DChieu1DAO daoDC = new DChieu1DAO(conn);
                DChieu2VO dcV0 = new DChieu2VO();
                DChieu1VO dcV01 = new DChieu1VO();
                //            Collection colDC2 = new ArrayList();
                Collection colSum = new ArrayList();


                String strUD = "  AND a.bk_id = '" + bk_id + "'";

                ArrayList<DChieu2VO> colDChieu =
                    (ArrayList<DChieu2VO>)daoDC2.getListBKTCuu(strUD,
                                                               receive_bank,
                                                               null);
                if (colDChieu.isEmpty()) {
                    return mapping.findForward("success");
                }
                request.setAttribute("colDChieu", colDChieu);

                String rowSelected = request.getParameter("rowSelected");
                if (null == rowSelected || "".equals(rowSelected)) {
                    dcV0 = colDChieu.get(0);
                    BeanUtils.copyProperties(DCForm, dcV0);
                    request.setAttribute("rowSelected", "row_qt_0");
                } else {

                    request.setAttribute("rowSelected", rowSelected);
                }
                String nhkb_chuyen = DCForm.getSend_bank();
                String nhkb_nhan = DCForm.getReceive_bank();
                String ngay_dc = DCForm.getNgay_dc();

                dcV01 = daoDC.checkSHKB(nhkb_nhan, null);

                //                dcV01.getCheck_shkb();


                if ("KO_PHT".equals(dcV01.getCheck_shkb())) {
                    String strW =
                        "select max(id) from sp_065 a where a.receive_bank = '" +
                        nhkb_chuyen + "' and send_bank='" + nhkb_nhan +
                        "' and to_char(a.ngay_dc,'DD-MM-RRRR') = '" + ngay_dc +
                        "' AND APP_TYPE='TTSP' ";
                    colTHBKDC = daoDC.getTTSP_PHT(strW, null);
                } else if ("CO_PHT".equals(dcV01.getCheck_shkb())) {
                    String strW =
                        "select max(id) from sp_065 a where a.receive_bank = '" +
                        nhkb_chuyen + "' and send_bank='" + nhkb_nhan +
                        "' and to_char(a.ngay_dc,'DD-MM-RRRR') = '" + ngay_dc +
                        "' AND APP_TYPE='TTSP' AND pht_id IS not null group by app_type";
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
                if (colDC2.size() == 0) {
                    request.setAttribute("colThuChi", null);
                } else if (colDC2.size() > 0) {
                    request.setAttribute("colThuChi", colDC2);
                    dcV0 = colDC2.get(0);
                    String lai_phi = dcV0.getLai_phi();
                    if ("03".equals(lai_phi)) {
                        request.setAttribute("colTHBKDC", null);
                    } else {
                        request.setAttribute("colTHBKDC", colTHBKDC);
                    }
                }
                request.setAttribute("colSum", colSum);

            } else if ("3".equals(loai_dc)) {


                DChieu3DAO dao = new DChieu3DAO(conn);
                KQDChieu3CTietDAO dchieu3CtietDAO =
                    new KQDChieu3CTietDAO(conn);
                DChieu3VO vo = new DChieu3VO();
                Collection colTHBK = new ArrayList();
                Collection TongKQBK = new ArrayList();
                Collection colMT910 = null;
                Collection colMT900 = null;
                Vector vParam = new Vector();

                String strWhere = "  AND a.id = '" + bk_id + "'";

                ArrayList<DChieu3VO> colList =
                    (ArrayList<DChieu3VO>)dao.getDuyetDChieu3List(strWhere,
                                                                  null);
                if (colList.isEmpty()) {
                    return mapping.findForward(AppConstants.SUCCESS);
                }
                request.setAttribute("colList", colList);

                String rowSelected = request.getParameter("rowSelected");
                if (null == rowSelected || "".equals(rowSelected)) {
                    vo = colList.get(0);
                    BeanUtils.copyProperties(DCForm, vo);
                    request.setAttribute("rowSelected", "row_qt_0");
                } else {
                    request.setAttribute("rowSelected", rowSelected);
                }

                String kq_id = DCForm.getKq_id();
                String strList = " AND b.id='" + kq_id + "'";
                colTHBK = dao.getDuyetDChieu3List(strList, null);

                //            String kq_id
                strWhere = " AND a.kq_id='" + kq_id + "' AND a.mt_type='900'";
                colMT900 =
                        dchieu3CtietDAO.getKQDChieu3CTietList(strWhere, vParam);

                strWhere = " AND a.kq_id='" + kq_id + "' AND a.mt_type='910'";
                colMT910 =
                        dchieu3CtietDAO.getKQDChieu3CTietList(strWhere, vParam);

                vParam.add(new Parameter(kq_id, true));
                vParam.add(new Parameter(kq_id, true));
                vParam.add(new Parameter(kq_id, true));
                vParam.add(new Parameter(kq_id, true));
                TongKQBK = dchieu3CtietDAO.getTongKQDChieu3(vParam);


                request.setAttribute("colTHBK", colTHBK);
                request.setAttribute("TongKQBK", TongKQBK);
                request.setAttribute("colMT900", colMT900);
                request.setAttribute("colMT910", colMT910);

            }

            String tcuu =
                "?nhkb_tinh=" + nhkb_tinh + "&nhkb_huyen=" + nhkb_huyen +
                "&ma_dv=" + ngan_hang + "&inKB=" + inKB + "&inNH=" + inNH +
                "&inxtthai=" + inxtthai + "&tu_ngay=" + tu_ngay +
                "&den_ngay=" + den_ngay + "&lan_dc=" + lan_dc +
                "&currentPage=" + currentPage;
            request.setAttribute("tcuu", tcuu);
        } catch (Exception e) {
            throw e;
        } finally {
            conn.close();
        }
        return mapping.findForward("success");
    }

	/* ThuongDT: Thay doi code de in bao cao theo yeu cau
	 * Date: 12/12/2016
	 * Track: 20161212
   * 
   * ThuongDT: cho phep va them chuc nang in bao cao doi voi tai khoan chuyen thu
   * Date: 03/03/2017
   * track: ThuongDT-20170303
	 */
	
    public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {

        Connection conn = null;
        StringBuffer sbSubHTML = new StringBuffer();
        InputStream reportStream = null;
        try {
            conn = getConnection();
            TCuuTTinDChieuForm f = (TCuuTTinDChieuForm)form;
            // l� ng d�ng dang su dung   f.getG_nsd_id()
            //Khai bao bien find.
            //            HttpSession session = request.getSession();
            String type = f.getType();
			//Bien lau so du COT
            Long so_du_cot ;
            if ("kqdc1".equals(type)) {
                String REPORT_DIRECTORY = "/report";
                String strFontTimeRoman = "/font/times.ttf";
                String ttsp_id = f.getTtsp_id();
                String pht_id = f.getPht_id();
                String kq_pht= f.getKet_qua_pht();
                String kq_ttsp= f.getKet_qua_ttsp();
                if(ttsp_id != null){
                DChieu1DAO dao = new DChieu1DAO(conn);
                DChieuNgoaiTeDAO ngoaiTeDAO = new DChieuNgoaiTeDAO(conn);
                DuyetKQDCVO vo = f.getMa_nt().equals("VND") ? dao.getTSoDC1(ttsp_id, null) : ngoaiTeDAO.getTSoDC1(ttsp_id, null);

                String ngay_dc = vo.getNgay_dc();
                String receive_bank = vo.getReceive_bank();
                String send_bank = vo.getSend_bank();
                String ten_tinh = vo.getTen_tinh();
                String ten_huyen = vo.getTen_huyen();
                String ten_nhang = vo.getTen_nhang();


                sbSubHTML.append("<input type=\"hidden\" name=\"ttsp_id\" value=\"" + ttsp_id + "\" id=\"ttsp_id\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"pht_id\" value=\"" + pht_id + "\" id=\"pht_id\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"ngay_dc\" value=\"" + ngay_dc + "\" id=\"ngay_dc\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"receive_bank\" value=\"" + receive_bank + "\" id=\"receive_bank\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"send_bank\" value=\"" + send_bank + "\" id=\"send_bank\"></input>"); 
                sbSubHTML.append("<input type=\"hidden\" name=\"ten_tinh\" value=\"" + ten_tinh + "\" id=\"ten_tinh\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"ten_huyen\" value=\"" + ten_huyen + "\" id=\"ten_huyen\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"ten_nhang\" value=\"" + ten_nhang + "\" id=\"ten_nhang\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"type\" value=\"" + type + "\" id=\"type\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"ket_qua_pht\" value=\"" + kq_pht + "\" id=\"ket_qua_pht\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"ket_qua_ttsp\" value=\"" + kq_ttsp + "\" id=\"ket_qua_ttsp\"></input>");

                JasperPrint jasperPrint = null;
                HashMap parameterMap = new HashMap();
                ReportUtility rpUtilites = new ReportUtility();
                String fileName = "";
                
              //ThuongDT-20170213 lay them so de nghi quyet toan tam thoi-begin 
              //ThuongDT-20170214 sua cau querry them dieu kien and loai_qtoan = '02'
                BigDecimal strQT_Thu = new BigDecimal(0);
                BigDecimal strQT_Chi = new BigDecimal(0);
               String whereClause = " and id in (select max(id) id FROM SP_066 where kq_dc_ttsp = ? ) and loai_qtoan = '02'";
                Vector vt = new Vector(); 
                vt.add(new Parameter(ttsp_id,true));
                QToanBuDAO qtoanDAO = new QToanBuDAO(conn);
                QToanBuVO qtoanVO = qtoanDAO.getQToanBu(whereClause, vt);
                if(qtoanVO != null){
                  strQT_Thu = qtoanVO.getQtoan_thu();
                  strQT_Chi = qtoanVO.getQtoan_chi();
                }
                parameterMap.put("p_qtoan_thu_tam", strQT_Thu);
                parameterMap.put("p_qtoan_chi_tam", strQT_Chi);
                //ThuongDT-20170213 lay them so de nghi quyet toan tam thoi- end
                
                
                
                //20161212
                String ngaytemp = ngay_dc.replaceAll("-", "/");                    
                TSoSDuCOTVO soDuVO = new TSoSDuCOTVO();
                soDuVO.setMa_nh(receive_bank);
                soDuVO.setMa_nh_kb(send_bank);
                soDuVO.setNgay_gd(ngaytemp);
                soDuVO.setLoai_tien(f.getMa_nt());
                TSoSDuCOTDAO tsDAO = new TSoSDuCOTDAO(conn);
                so_du_cot = tsDAO.getSoDuCOT(soDuVO);
                //ThuongDT-20170215 lay them so du dau ngay -begin
                    Long sodu = 0l;
                    TTSPUtils ttsp_util = new TTSPUtils(conn);
                    String ngaytruoc = ttsp_util.previousDay(ngaytemp);
                    soDuVO.setNgay_gd(ngaytruoc);
                    TSoSDuCOTVO sudu_VO = tsDAO.getSoDu(soDuVO);
                    if(sudu_VO != null)
                      sodu = sudu_VO.getSo_du();
                    parameterMap.put("P_SODU_DAUNGAY", sodu);
                    parameterMap.put("P_SODUCOT", so_du_cot!=null?so_du_cot:"0");
                //ThuongDT-20170215 lay them so du dau ngay -end
                
                //ThuongDT-20161212-Sua bao cao doi chieu dap theo mau moi-begin
                if (f.getMa_nt().equals("VND")){
                    if ("0".equals(kq_pht) && "0".equals(kq_ttsp)) {						
                        fileName = "/rpt_doi_chieu_1_kd112016";
                    } else if (!"0".equals(kq_pht) || !"0".equals(kq_ttsp)) {
                        fileName = "/rpt_doi_chieu_1_kl";
                    }
                    
                    parameterMap.put("REPORT_LOCALE", new java.util.Locale("vi", "VI"));
                  parameterMap.put("p_CURRENCY_FRMT_PARTERN", AppConstants.FORMAT_CURRENTCEY_PATERN_VND);
                }else{
                    
                    if ("0".equals(kq_pht) && "0".equals(kq_ttsp)) {
                        fileName = "/rpt_doi_chieu_1_kd_ngoai_te";
                    } else if (!"0".equals(kq_pht) || !"0".equals(kq_ttsp)) {
                        fileName = "/rpt_doi_chieu_1_kl_ngoai_te";
                    }
                    parameterMap.put("P_LOAI_TIEN", f.getMa_nt());
                    parameterMap.put("REPORT_LOCALE", new java.util.Locale("en", "US"));
                    parameterMap.put("p_CURRENCY_FRMT_PARTERN", AppConstants.FORMAT_CURRENTCEY_PATERN_NT);
                }  
                //ThuongDT-20161212-Sua bao cao doi chieu dap theo mau moi-end
                reportStream = getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY + fileName + ".jasper");
                
                parameterMap.put("p_ID_TTSP", ttsp_id);
                parameterMap.put("p_ID_PHT", pht_id);
                parameterMap.put("p_NGAY", ngay_dc);
                parameterMap.put("p_MA_NH", receive_bank);
                parameterMap.put("p_KB_HUYEN", send_bank);
                parameterMap.put("p_KB_TINH", ten_tinh);
                parameterMap.put("p_TEN_KB", ten_huyen);
                parameterMap.put("p_TEN_NH", ten_nhang);                
               
                jasperPrint = JasperFillManager.fillReport(reportStream, parameterMap, conn);
                String strTypePrintAction = request.getParameter(AppConstants.REQUEST_ACTION) == null ? "" : request.getParameter(AppConstants.REQUEST_ACTION).toString();
                String strActionName = "printTTinDChieuAction.do";
                String strParameter = "";
                String strPathFont = getServlet().getServletContext().getContextPath() + REPORT_DIRECTORY + strFontTimeRoman;
                rpUtilites.exportReport(jasperPrint, strTypePrintAction, response, fileName, strPathFont, strActionName, sbSubHTML.toString(), strParameter);
                }else{
                //ThuongDT-20170303 them truong hop bao cao cho tai khoan chuyen thu-begin  
                      DChieu1DAO dao = new DChieu1DAO(conn);
                      DChieuNgoaiTeDAO ngoaiTeDAO = new DChieuNgoaiTeDAO(conn);
                      DuyetKQDCVO vo = f.getMa_nt().equals("VND") ? dao.getTSoDC1(pht_id, null) : ngoaiTeDAO.getTSoDC1(pht_id, null);

                      String ngay_dc = vo.getNgay_dc();
                      String receive_bank = vo.getReceive_bank();
                      String send_bank = vo.getSend_bank();
                      String ten_tinh = vo.getTen_tinh();
                      String ten_huyen = vo.getTen_huyen();
                      String ten_nhang = vo.getTen_nhang();


                      sbSubHTML.append("<input type=\"hidden\" name=\"ttsp_id\" value=\"" + ttsp_id + "\" id=\"ttsp_id\"></input>");
                      sbSubHTML.append("<input type=\"hidden\" name=\"pht_id\" value=\"" + pht_id + "\" id=\"pht_id\"></input>");
                      sbSubHTML.append("<input type=\"hidden\" name=\"ngay_dc\" value=\"" + ngay_dc + "\" id=\"ngay_dc\"></input>");
                      sbSubHTML.append("<input type=\"hidden\" name=\"receive_bank\" value=\"" + receive_bank + "\" id=\"receive_bank\"></input>");
                      sbSubHTML.append("<input type=\"hidden\" name=\"send_bank\" value=\"" + send_bank + "\" id=\"send_bank\"></input>"); 
                      sbSubHTML.append("<input type=\"hidden\" name=\"ten_tinh\" value=\"" + ten_tinh + "\" id=\"ten_tinh\"></input>");
                      sbSubHTML.append("<input type=\"hidden\" name=\"ten_huyen\" value=\"" + ten_huyen + "\" id=\"ten_huyen\"></input>");
                      sbSubHTML.append("<input type=\"hidden\" name=\"ten_nhang\" value=\"" + ten_nhang + "\" id=\"ten_nhang\"></input>");
                      sbSubHTML.append("<input type=\"hidden\" name=\"type\" value=\"" + type + "\" id=\"type\"></input>");
                      sbSubHTML.append("<input type=\"hidden\" name=\"ket_qua_pht\" value=\"" + kq_pht + "\" id=\"ket_qua_pht\"></input>");
                      sbSubHTML.append("<input type=\"hidden\" name=\"ket_qua_ttsp\" value=\"" + kq_ttsp + "\" id=\"ket_qua_ttsp\"></input>");

                      JasperPrint jasperPrint = null;
                      HashMap parameterMap = new HashMap();
                      ReportUtility rpUtilites = new ReportUtility();
                      String fileName = "";
                      BigDecimal strQT_Thu = new BigDecimal(0);
                      BigDecimal strQT_Chi = new BigDecimal(0);
                      String whereClause = " and id in (select max(id) id FROM SP_066 where kq_dc_ttsp = ? ) and loai_qtoan = '02'";
                      Vector vt = new Vector(); 
                      vt.add(new Parameter(pht_id,true));
                      QToanBuDAO qtoanDAO = new QToanBuDAO(conn);
                      QToanBuVO qtoanVO = qtoanDAO.getQToanBu(whereClause, vt);
                      if(qtoanVO != null){
                        strQT_Thu = qtoanVO.getQtoan_thu();
                        strQT_Chi = qtoanVO.getQtoan_chi();
                      }
                      parameterMap.put("p_qtoan_thu_tam", strQT_Thu);
                      parameterMap.put("p_qtoan_chi_tam", strQT_Chi);
                    
                      String ngaytemp = ngay_dc.replaceAll("-", "/");                    
                      TSoSDuCOTVO soDuVO = new TSoSDuCOTVO();
                      soDuVO.setMa_nh(receive_bank);
                      soDuVO.setMa_nh_kb(send_bank);
                      soDuVO.setNgay_gd(ngaytemp);
                      soDuVO.setLoai_tien(f.getMa_nt());
                      TSoSDuCOTDAO tsDAO = new TSoSDuCOTDAO(conn);
                      so_du_cot = tsDAO.getSoDuCOT(soDuVO);
                     
                          Long sodu = 0l;
                          TTSPUtils ttsp_util = new TTSPUtils(conn);
                          String ngaytruoc = ttsp_util.previousDay(ngaytemp);
                          soDuVO.setNgay_gd(ngaytruoc);
                          TSoSDuCOTVO sudu_VO = tsDAO.getSoDu(soDuVO);
                          if(sudu_VO != null)
                            sodu = sudu_VO.getSo_du();
                          parameterMap.put("P_SODU_DAUNGAY", sodu);
                          parameterMap.put("P_SODUCOT", so_du_cot!=null?so_du_cot:"0");
                      
                      if (f.getMa_nt().equals("VND")){
                          if ("0".equals(kq_pht) && "0".equals(kq_ttsp)) {            
                              fileName = "/rpt_doi_chieu_1_kd_pht112016";
                          } else if (!"0".equals(kq_pht) || !"0".equals(kq_ttsp)) {
                              fileName = "/rpt_doi_chieu_1_kl_pht";
                          }
                          
                          parameterMap.put("REPORT_LOCALE", new java.util.Locale("vi", "VI"));
                        parameterMap.put("p_CURRENCY_FRMT_PARTERN", AppConstants.FORMAT_CURRENTCEY_PATERN_VND);
                      }else{
                          
                          if ("0".equals(kq_pht) && "0".equals(kq_ttsp)) {
                              fileName = "/rpt_doi_chieu_1_kd_ngoai_te";
                          } else if (!"0".equals(kq_pht) || !"0".equals(kq_ttsp)) {
                              fileName = "/rpt_doi_chieu_1_kl_ngoai_te";
                          }
                          parameterMap.put("P_LOAI_TIEN", f.getMa_nt());
                          parameterMap.put("REPORT_LOCALE", new java.util.Locale("en", "US"));
                          parameterMap.put("p_CURRENCY_FRMT_PARTERN", AppConstants.FORMAT_CURRENTCEY_PATERN_NT);
                      }  
                     
                      reportStream = getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY + fileName + ".jasper");
                      
                      parameterMap.put("p_ID_TTSP", ttsp_id);
                      parameterMap.put("p_ID_PHT", pht_id);
                      parameterMap.put("p_NGAY", ngay_dc);
                      parameterMap.put("p_MA_NH", receive_bank);
                      parameterMap.put("p_KB_HUYEN", send_bank);
                      parameterMap.put("p_KB_TINH", ten_tinh);
                      parameterMap.put("p_TEN_KB", ten_huyen);
                      parameterMap.put("p_TEN_NH", ten_nhang);                
                      
                      jasperPrint = JasperFillManager.fillReport(reportStream, parameterMap, conn);
                      String strTypePrintAction = request.getParameter(AppConstants.REQUEST_ACTION) == null ? "" : request.getParameter(AppConstants.REQUEST_ACTION).toString();
                      String strActionName = "printTTinDChieuAction.do";
                      String strParameter = "";
                      String strPathFont = getServlet().getServletContext().getContextPath() + REPORT_DIRECTORY + strFontTimeRoman;
                      rpUtilites.exportReport(jasperPrint, strTypePrintAction, response, fileName, strPathFont, strActionName, sbSubHTML.toString(), strParameter);   
                    
                    //ThuongDT-20170303 them truong hop bao cao cho tai khoan chuyen thu-end  
                    }
                
            } else if ("kqdc2".equals(type)) {

                String REPORT_DIRECTORY = "/report";
                String strFontTimeRoman = "/font/times.ttf";
                DChieu1DAO dao = new DChieu1DAO(conn);
                DuyetKQDCVO vo = new DuyetKQDCVO();
                String strid = f.getKq_id();
                String ket_qua = f.getKet_qua();

                if (strid != null && !"".equals(strid)) {
                    String strWhere = "";
                    strWhere += " AND a.id='" + strid + "'";
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
                    sbSubHTML.append("<input type=\"hidden\" name=\"type\" value=\"" +
                                     type + "\" id=\"type\"></input>");
                    sbSubHTML.append("<input type=\"hidden\" name=\"ket_qua\" value=\"" +
                                     ket_qua + "\" id=\"ket_qua\"></input>");
                    JasperPrint jasperPrint = null;
                    HashMap parameterMap = new HashMap();
                    ReportUtility rpUtilites = new ReportUtility();
                    String fileName = "";
                    if (ket_qua.equals("0")) {
                        fileName = "/rpt_PHoi_Kqua_Dchieu_2_kd";
                    } else if (ket_qua.equals("1")) {
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
                    parameterMap.put("REPORT_LOCALE",
                                     new java.util.Locale("vi", "VI"));
                    jasperPrint = JasperFillManager.fillReport(reportStream, parameterMap, conn);

                    String strTypePrintAction =
                        request.getParameter(AppConstants.REQUEST_ACTION) ==
                        null ? "" :
                        request.getParameter(AppConstants.REQUEST_ACTION).toString();
                    String strActionName = "printTTinDChieuAction.do";
                    String strParameter = "";
                    String strPathFont =
                        getServlet().getServletContext().getContextPath() +
                        REPORT_DIRECTORY + strFontTimeRoman;

                    rpUtilites.exportReport(jasperPrint, strTypePrintAction,
                                            response, fileName, strPathFont,
                                            strActionName,
                                            sbSubHTML.toString(),
                                            strParameter);

                }
            } else if ("kqdc3".equals(type)) {
                String REPORT_DIRECTORY = "/report";
                String strFontTimeRoman = "/font/times.ttf";
                String fileName = ""; //rpt_DChieu_KQua_TGui
                KQDChieu3DAO kqDChieu3DAO = new KQDChieu3DAO(conn);
                KQDChieu3VO kqDChieu3VO = new KQDChieu3VO();
                // l� ng d�ng dang su dung   f.getG_nsd_id()
                //Khai bao bien find.
                String ngay_dc = f.getNgay_dc();
                String kq_id = f.getKq_id();
                ngay_dc = ngay_dc.replace("/", "-");
                String send_bank = f.getSend_bank();
                String trang_thai = f.getTrang_thai_bk();
                String lan_dc = f.getLan_dc();
                String strW = "'" + send_bank + "'";
                kqDChieu3VO = kqDChieu3DAO.getTenNH(strW, null);
                String tenNH = kqDChieu3VO.getTen();

                if (ngay_dc != null && !"".equals(ngay_dc)) {
                    sbSubHTML.append("<input type=\"hidden\" name=\"lan_dc\" value=\"" +
                                     lan_dc + "\" id=\"lan_dc\"></input>");
                    sbSubHTML.append("<input type=\"hidden\" name=\"ngay_dc\" value=\"" +
                                     ngay_dc + "\" id=\"ngay_dc\"></input>");
                    sbSubHTML.append("<input type=\"hidden\" name=\"kq_id\" value=\"" +
                                     kq_id + "\" id=\"kq_id\"></input>");
                    sbSubHTML.append("<input type=\"hidden\" name=\"send_bank\" value=\"" +
                                     send_bank +
                                     "\" id=\"send_bank\"></input>");
                    sbSubHTML.append("<input type=\"hidden\" name=\"tenNH\" value=\"" +
                                     tenNH + "\" id=\"tenNH\"></input>");
                    sbSubHTML.append("<input type=\"hidden\" name=\"type\" value=\"" +
                                     type + "\" id=\"type\"></input>");
                    sbSubHTML.append("<input type=\"hidden\" name=\"trang_thai_bk\" value=\"" +
                                     trang_thai +
                                     "\" id=\"trang_thai_bk\"></input>");
                    JasperPrint jasperPrint = null;
                    HashMap parameterMap = new HashMap();
                    ReportUtility rpUtilites = new ReportUtility();
                    if (trang_thai.equals("02")) {
                        fileName = "/rpt_DChieu_KQua_TGui_kd";
                    } else if (trang_thai.equals("01")) {
                        fileName = "/rpt_DChieu_KQua_TGui_kl";
                    }
                    reportStream = getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY + fileName + ".jasper");
                    parameterMap.put("p_NGAY", ngay_dc);
                    parameterMap.put("p_LAN", lan_dc);
                    parameterMap.put("p_ID_KQ", kq_id);
                    parameterMap.put("p_MA_NH", send_bank);
                    parameterMap.put("p_TEN_NH", tenNH);
                    parameterMap.put("REPORT_LOCALE",
                                     new java.util.Locale("vi", "VI"));
                    jasperPrint = JasperFillManager.fillReport(reportStream, parameterMap, conn);

                    String strTypePrintAction =
                        request.getParameter(AppConstants.REQUEST_ACTION) ==
                        null ? "" :
                        request.getParameter(AppConstants.REQUEST_ACTION).toString();
                    String strActionName = "printTTinDChieuAction.do";
                    String strParameter = "";
                    String strPathFont =
                        getServlet().getServletContext().getContextPath() +
                        REPORT_DIRECTORY + strFontTimeRoman;

                    rpUtilites.exportReport(jasperPrint, strTypePrintAction,
                                            response, fileName, strPathFont,
                                            strActionName,
                                            sbSubHTML.toString(),
                                            strParameter);
                }


            }

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                reportStream.close();
            } catch (Exception e) {
                throw e;
            }
            close(conn);
        }

        return mapping.findForward("success");
    }

}
