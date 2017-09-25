package com.seatech.ttsp.tsotabmis.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DChieu1VO;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.dmnhkb.DMHTKBacVO;
import com.seatech.ttsp.dmnhkb.DMNHKBacDAO;
import com.seatech.ttsp.thamso.ThamSoHThongDAO;
import com.seatech.ttsp.thamso.ThamSoHThongVO;
import com.seatech.ttsp.tsotabmis.form.tsoTabmisForm;
import com.seatech.ttsp.tsotabmis.tsoTabmisDAO;
import com.seatech.ttsp.tsotabmis.tsoTabmisVO;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanDAO;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

import java.util.Set;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/* @Modifier: HungBM 
 * @see: Thay the ham hien thi danh sach DMKB cha 
 * @Modify date: 16/02/2017
 * @Track: 20170216
 */

public class tsoTabmisAction extends AppAction {
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        //      if (!checkPermissionOnFunction(request, "DCHIEU.NT_TCUU_DCHIEU")) {
        //          return mapping.findForward("notRight");
        //      }

        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            tsoTabmisForm frm = (tsoTabmisForm)form;
            
            TTThanhToanDAO TTdao = new TTThanhToanDAO(conn);
            tsoTabmisDAO tsDAO = new tsoTabmisDAO(conn);
            DChieu1DAO dcDAO = new DChieu1DAO(conn);
            DChieu1VO dcVO = new DChieu1VO();
            
            String kb_code = session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
            
            int phantrang = (20);
            String page = "";
            page = frm.getPageNumber();
            if (page == null || "".equals(page))
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[15];
            
            List dmuckb_cha = null;
            List dmucNH = null;
            Collection lstTSKB = null;
            List ten_ts = null;
            
            dcVO = dcDAO.getCap(" and ma=" + kb_code, null);
            String cap = dcVO.getCap();
            
            String kbWhereCondition = getRoleCondition(request, kb_code, cap);
            
            
            dmucNH = (List)TTdao.getDMucNH(null,null);
            //20170216 --------------------BEGIN--------------------------
            dmuckb_cha = (List)dcDAO.getDMucKB_Tinh(kbWhereCondition, null);
            //20170216 --------------------END----------------------------
            ten_ts = (List)tsDAO.getTenTSo(null);
            PagingBean pagingBean = new PagingBean();
            if(isTokenValid(request)){
                String whereForResult = getConditionForResult(frm);
                lstTSKB = tsDAO.getLstTSoKB_ptrang(whereForResult, null, currentPage, numberRowOnPage, totalCount);
                pagingBean.setNumberOfRow(totalCount[0].intValue());
                resetToken(request);
                saveToken(request);
            }else{
                saveToken(request);
            }
            String inKB = request.getParameter("inKB");
            
            request.setAttribute("dmuckb_tinh", dmuckb_cha);
            request.setAttribute("dmucNH", dmucNH);
            request.setAttribute("lstTSKB", lstTSKB);
            request.setAttribute("kb_huyen", inKB);
            request.setAttribute("ten_ts",ten_ts);

            pagingBean.setCurrentPage(currentPage);
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);
            request.setAttribute("tong_row", totalCount[0]);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("initAction", "initAction");
          
            } catch (Exception e) {
                throw e;
            } finally {
                close(conn);
            }
            return mapping.findForward("success");
    }

    private String getConditionForResult(tsoTabmisForm frm) {
        String nhkb_tinh = frm.getNhkb_tinh() == null ? "" : frm.getNhkb_tinh();
        String nhkb_huyen = frm.getNhkb_huyen() == null ? "" : frm.getNhkb_huyen();
        String ten_ts = frm.getTen_ts() == null ? "" : frm.getTen_ts();
        StringBuffer result = new StringBuffer("");
        if (nhkb_tinh != null && !"".equals(nhkb_tinh)) {
            result.append(" AND (d5.id_cha = " + nhkb_tinh + " OR d5.id =" + nhkb_tinh + ")");
        }
        if (nhkb_huyen != null && !"".equals(nhkb_huyen)) {
            result.append(" and d4.id = " + nhkb_huyen);
        }
        if (ten_ts != null && !"".equals(ten_ts)) {
            result.append(" and upper(ten_ts) like upper('%" + ten_ts + "%')");
        }
        if(frm.getGiatri_ts() != null && !"".equals(frm.getGiatri_ts())){
            result.append(" and a.GIATRI_TS = '"+frm.getGiatri_ts()+"' ");
        }
        if(frm.getMa_dv() != null && !"".equals(frm.getMa_dv())){
            result.append(" and d2.MA_NH like '__"+frm.getMa_dv()+"%' ");
        }
        return result.toString();
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {

        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            String kb_code = session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
            tsoTabmisForm frm = (tsoTabmisForm)form;
            
            DChieu1DAO dcDAO = new DChieu1DAO(conn);
            DChieu1VO dcVO = new DChieu1VO();
            TTThanhToanDAO TTdao = new TTThanhToanDAO(conn);
            tsoTabmisDAO TSdao = new tsoTabmisDAO(conn);
            
            List dmucNH = null;
            List dmuckb_cha = null;
            List lstTSo = null;
            List updateList = getUpdateList(request);//note*
            
            dcVO = dcDAO.getCap(" and ma=" + kb_code, null);
            String cap = dcVO.getCap();
            String kbWhereCondition = getRoleCondition(request, kb_code, cap);

            dmucNH = (List)TTdao.getDMucNH(null,null);
            //20170216 ----------------------BEGIN-------------------------
            dmuckb_cha = (List)dcDAO.getDMucKB_Tinh(kbWhereCondition, null);
            //20170216 -----------------------END--------------------------
            lstTSo = (List)TSdao.getLstTSo(null);
            
            String nhkb_tinh = frm.getNhkb_tinh() == null ? "" : frm.getNhkb_tinh();
            String nhkb_huyen = frm.getNhkb_huyen() == null ? "" : frm.getNhkb_huyen();
            String ten_ts = frm.getTen_ts() == null ? "" : frm.getTen_ts();
            
            String strTSKB = "";
            if (nhkb_tinh != null && !"".equals(nhkb_tinh)) {
                strTSKB += " AND (d5.id_cha = " + nhkb_tinh + " OR d5.id =" + nhkb_tinh + ")";
            }
            if (nhkb_huyen != null && !"".equals(nhkb_huyen)) {
                strTSKB += " and d4.id = " + nhkb_huyen;
            }
            if (ten_ts != null && !"".equals(ten_ts)) {
                strTSKB += " and upper(ten_ts) like upper('%" + ten_ts + "%')";
            }
            
            request.setAttribute("listKBUpdate", updateList);
            request.setAttribute("lstTSo", lstTSo);
            request.setAttribute("dmucNH", dmucNH);
            request.setAttribute("dmuckb_tinh", dmuckb_cha);
            request.setAttribute("kb_huyen",nhkb_huyen);
            request.setAttribute("kb_tinh",nhkb_tinh);
            saveToken(request);
        } finally {
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

    private String getRoleCondition(HttpServletRequest request, String kb_code,
                                  String cap) {
        String kbWhereCondition = " ";
        if ("0001".equals(kb_code) || "0002".equals(kb_code)) {
            kbWhereCondition = " ";
            request.setAttribute("dchieu3", "dchieu3");
            request.setAttribute("dftinh", "dftinh");
        }else if ("0003".equals(kb_code)) {
            kbWhereCondition = " AND a.ma='0003' ";
        }else if ("5".equals(cap)) {
            kbWhereCondition = " and a.ma_cha=" + kb_code;
        }else {
            kbWhereCondition = " and a.ma =" + kb_code;
        }
        return kbWhereCondition;
    }

    private List<tsoTabmisVO> getUpdateList(HttpServletRequest request) {
      Map map = request.getParameterMap();
      String[] itemIndex = (String[])map.get("index");
      List<tsoTabmisVO> updateList = new ArrayList<tsoTabmisVO>();
      if(itemIndex != null){
          for(String index : itemIndex){
              int i = Integer.parseInt(index);
              tsoTabmisVO vo = new tsoTabmisVO();
              vo.setTen_kb_huyen(((String[])map.get("kbHuyen"))[i]);
              vo.setTen_ts(((String[])map.get("tenThamSo"))[i]);
              vo.setGiatri_ts((String[])map.get("giaTriThamSo")==null?"":((String[])map.get("giaTriThamSo"))[i]);
              vo.setMo_ta((String[])map.get("moTa")==null?"":((String[])map.get("moTa"))[i]);
              updateList.add(vo);
          }
      }
      return updateList;
    }

    public ActionForward updateExc(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        Connection conn = null;
        try {
            if (isTokenValid(request)) {
                conn = getConnection(request);
                HttpSession session = request.getSession();              
                saveVisitLog(conn, session, "SYS.TS_HT.QLY_TS_KB", "Cap nhat tham so cac KB");
                tsoTabmisForm frm = (tsoTabmisForm)form;
                DChieu1DAO dao = new DChieu1DAO(conn);
                tsoTabmisDAO tsDAO = new tsoTabmisDAO(conn);
                ThamSoHThongDAO tshtDAO = new ThamSoHThongDAO(conn);
                ThamSoHThongVO tshtVO = new ThamSoHThongVO();
                DMKBacDAO dmDAO = new DMKBacDAO(conn);
                DMKBacVO dmVO = new DMKBacVO();
                tsoTabmisVO vo = new tsoTabmisVO();
                Collection lstTSKB = null;
    
    
                String strTSKB = "";
                String nhkb_tinh = frm.getNhkb_tinh() == null ? "" : frm.getNhkb_tinh();
                String nhkb_huyen = frm.getNhkb_huyen() == null ? "" : frm.getNhkb_huyen();
                //        String ten_ts = frm.getTen_ts()==null?"":frm.getTen_ts();
                String gtri_ts = frm.getGiatri_ts();
                String mta = frm.getMo_ta();
                String ten_ts = frm.getTen_ts();
                String ma_nsd = session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString();
                Long id_nsd = (Long)session.getAttribute(AppConstants.APP_USER_ID_SESSION);
    
                if (nhkb_tinh != null && !"".equals(nhkb_tinh)) {
                    if (nhkb_huyen != null && !"".equals(nhkb_huyen)) {
                        vo.setId_kb_huyen(nhkb_huyen);
                    }else if (nhkb_huyen == null || "".equals(nhkb_huyen)) {
                        vo.setId_kb_tinh(nhkb_tinh);
                    }
                }
                
                vo.setGiatri_ts(gtri_ts);
                vo.setMo_ta(mta);
                vo.setTen_ts(ten_ts);
                vo.setMa_nsd(ma_nsd);
                tsDAO.update(vo);
                
                String strKB=" AND  ( a.id = b.kb_id OR a.id IN (  SELECT	 id_cha FROM sp_dm_htkb))";
                  if (nhkb_tinh != null && !"".equals(nhkb_tinh)) {
                      if (nhkb_huyen != null && !"".equals(nhkb_huyen)) {
                        strKB +=" AND a.id ="+nhkb_huyen;
                      }else if (nhkb_huyen == null || "".equals(nhkb_huyen)) {
                        strKB +=" AND a.id="+nhkb_tinh;
                      }
                  }
               
               Collection colDMKB = new ArrayList();
               colDMKB = dmDAO.getDMKBTCUUList(strKB, null);
               String shkb="";
               String ten_kb =null;
               for (Iterator iter = colDMKB.iterator();
                    iter.hasNext(); ) {
                   if(iter.hasNext()){
                      dmVO= (DMKBacVO)iter.next();
                       shkb= dmVO.getMa_nh();
                       ten_kb=dmVO.getTen();                
                     }
                    }
               if (nhkb_tinh != null && !"".equals(nhkb_tinh)) {
                   if (nhkb_huyen != null && !"".equals(nhkb_huyen)) {
                     tshtVO.setShkb(shkb);
                     tshtVO.setKb_id(Long.parseLong(nhkb_huyen));
                   }else if (nhkb_huyen == null || "".equals(nhkb_huyen)) {
                     shkb += ten_kb;
                     tshtVO.setShkb(shkb);
                     tshtVO.setKb_id(Long.parseLong(nhkb_tinh));
                   }
               }else{
                   shkb += " Cap nhat toan bo he thong KB";
                   tshtVO.setShkb(shkb);
               }
               tshtVO.setGiatri_ts_moi(gtri_ts);
               tshtVO.setMo_ta(mta);         
               tshtVO.setNsd_id(id_nsd);
               tshtVO.setTen_ts(ten_ts);
               tshtDAO.insert(tshtVO);
               
               conn.commit();
           }
           resetToken(request);
           saveToken(request);
        } finally {
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }


    @Override //muon view de chay update co check
    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
      Connection conn = null;
      try {
          if (isTokenValid(request)) {
              conn = getConnection(request);
              tsoTabmisForm f = (tsoTabmisForm)form;
              
              HttpSession session = request.getSession();
              String ma_nsd = session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString();
              Long id_nsd = (Long)session.getAttribute(AppConstants.APP_USER_ID_SESSION);
              
              ThamSoHThongDAO tshtDAO = new ThamSoHThongDAO(conn);
              tsoTabmisDAO tsDAO = new tsoTabmisDAO(conn);
              DMNHKBacDAO dmkbDAO = new DMNHKBacDAO(conn);
              DMHTKBacVO dmkbVO;
              Vector params = new Vector();
              
              List<tsoTabmisVO> listUpdate = getUpdateList(request);
              
              //khoi tao day du thong tin de chuan bi update
              String dmkbCondition = " ten = ? ";
              boolean flag = true;
              for(int i = 0; i<listUpdate.size() && flag;i++){
                  tsoTabmisVO vo = listUpdate.get(i);
                  params.clear();
                  params.add(new Parameter(vo.getTen_kb_huyen(), true));
                  dmkbVO = dmkbDAO.getDMHTKBac(dmkbCondition,params);
                  //init gia tri
                  vo.setId_kb_huyen(dmkbVO.getId());
                  vo.setMa_nsd(ma_nsd);
                  vo.setMo_ta(f.getMo_ta());
                  vo.setGiatri_ts(f.getGiatri_ts());
                  //update database
                  flag = tsDAO.update(vo) == 1 ? true : false;
                  
                  DMNHKBacDAO dmhtkbDAO = new DMNHKBacDAO(conn);
                  String conditionDMHTKB = " id = ?";
                  Vector paramDMHTKB= new Vector();
                  paramDMHTKB.add(new Parameter(vo.getId_kb_huyen(),true));
                  DMHTKBacVO dmhtkbTempVo = dmhtkbDAO.getDMHTKBac(conditionDMHTKB,paramDMHTKB);
                  ThamSoHThongVO tshtVO = new ThamSoHThongVO();
                  
                  tshtVO.setKb_ma(dmhtkbTempVo.getMa());
                  tshtVO.setKb_id(Long.parseLong(vo.getId_kb_huyen()));
                  tshtVO.setGiatri_ts_moi(vo.getGiatri_ts());
                  tshtVO.setMo_ta(vo.getMo_ta());         
                  tshtVO.setNsd_id(id_nsd);
                  tshtVO.setTen_ts(vo.getTen_ts());
                  
                  tshtDAO.insert(tshtVO);
              }
              if(flag) conn.commit();
              else throw new Exception("C\u00F3 l\u1ED7i trong l\u00FAc s\u1EEDa");
          }
          resetToken(request);
          saveToken(request);
      } catch(Exception e) {
          conn.rollback();
          throw e;
      } finally {
          close(conn);
      }
      if (!response.isCommitted())
          return mapping.findForward(AppConstants.SUCCESS);
      else
          return null;
    }
}
