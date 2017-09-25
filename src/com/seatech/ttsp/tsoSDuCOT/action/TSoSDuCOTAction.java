package com.seatech.ttsp.tsoSDuCOT.action;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.FontUtil;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DNQTVO;
import com.seatech.ttsp.dchieu.form.XNDCTHop1Form;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.tknhkb.TKNHKBacDAO;
import com.seatech.ttsp.tknhkb.TKNHKBacVO;
import com.seatech.ttsp.tknhkb.form.TKNHKBacForm;
import com.seatech.framework.utils.StringUtil;
import com.seatech.ttsp.dmtiente.DMTienTeDAO;
import com.seatech.ttsp.tsoSDuCOT.TSoKhoBacVO;
import com.seatech.ttsp.tsoSDuCOT.TSoSDuCOTDAO;
import com.seatech.ttsp.tsoSDuCOT.TSoSDuCOTVO;
import com.seatech.ttsp.tsoSDuCOT.form.TSoSDuCOTForm;

import com.seatech.ttsp.ttthanhtoan.TTThanhToanDAO;

import com.seatech.ttsp.ttthanhtoan.TTThanhToanVO;

import java.io.PrintWriter;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class TSoSDuCOTAction extends AppAction {
    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        try{
            conn = getConnection();
            DMTienTeDAO tienTeDAO = new DMTienTeDAO(conn);
            List tienTe = (List)tienTeDAO.getDMTienTeList(" TINH_TRANG = '1' ",null);
            
            String strCurDate = StringUtil.getCurrentDate(); 
            request.setAttribute("strCurDate",strCurDate);
            request.setAttribute("tienTe", tienTe);
            saveToken(request);
            
        } catch (Exception e) {
            throw e;
        }finally{
            close(conn);
        }
        return mapping.findForward(AppConstants.SUCCESS);
    }

    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        Connection conn = null;
        conn = getConnection(request);
        try {
            if (isTokenValid(request)) {  
                TSoSDuCOTForm frm = (TSoSDuCOTForm)form;
                TSoSDuCOTDAO dao = new TSoSDuCOTDAO(conn);
                TSoSDuCOTVO vo = null;
                String p_ma_nh_kb = frm.getMa_kb();
                String p_ma_nh = frm.getMa_nh();
                String p_ngay_trien_khai = null;
                String type = frm.getLoai_ttin();
                
                if("COT".equalsIgnoreCase(type)){
                    p_ngay_trien_khai = frm.getTu_ngay_cot();
                    CallableStatement cs = null;
                    cs = conn.prepareCall("{call sp_init_pkg.proc_thiet_lap_cutoftime(?,?,?,?)}");
                    cs.setString(1, p_ma_nh_kb);
                    cs.setString(2, p_ma_nh);
                    cs.setLong(3, 1325);
                    cs.setString(4, p_ngay_trien_khai);
                    cs.execute();
                } else if ("SO_DU".equalsIgnoreCase(type)){
                    Long so_du= Long.parseLong(frm.getSo_du().trim().replace(".", ""));
                    p_ngay_trien_khai = frm.getTu_ngay_sdu();
                    vo= new TSoSDuCOTVO();
                    vo.setMa_nh_kb(p_ma_nh_kb);
                    vo.setMa_nh(p_ma_nh);
                    vo.setNgay_gd(p_ngay_trien_khai);
                    vo.setSo_du(so_du);
                    vo.setLoai_tien(frm.getLoai_tien());
                    dao.insertSDU(vo);
                }
                conn.commit();
                request.setAttribute("thanhcong", "thanhcong");
            }       
        } catch (Exception e) {
            throw e;
        } finally {
          resetToken(request);
          saveToken(request);
            close(conn);
        }

        return mapping.findForward("success");
    }
    
  public ActionForward update(ActionMapping mapping, ActionForm form,
                           HttpServletRequest request,
                           HttpServletResponse response) throws Exception {
      Connection conn = null;
      HttpSession session = request.getSession();
      conn = getConnection(request);
      try {
          if (isTokenValid(request)) {  
              TSoSDuCOTForm frm = (TSoSDuCOTForm)form;
              TSoSDuCOTDAO dao = new TSoSDuCOTDAO(conn);
              TSoSDuCOTVO vo = null;
              String p_ma_nh_kb = frm.getMa_kb();
              String p_ma_nh = frm.getMa_nh();
              String p_ngay_trien_khai = frm.getTu_ngay_sdu();
            
              String type= frm.getLoai_ttin();
              if ("SO_DU".equalsIgnoreCase(type)){
                  Long so_du= Long.parseLong(frm.getSo_du().trim().replace(".", ""));
                  vo= new TSoSDuCOTVO();
                  vo.setMa_nh_kb(p_ma_nh_kb);
                  vo.setMa_nh(p_ma_nh);
                  vo.setNgay_gd(p_ngay_trien_khai);
                  vo.setSo_du(so_du);
                  dao.updateSDU(vo);
              } 
              conn.commit();
              request.setAttribute("thanhcong", "thanhcong");
          }       
      } catch (Exception e) {
          throw e;
      } finally {
        resetToken(request);
        saveToken(request);
          close(conn);
      }

      return mapping.findForward("success");
  }
    
  public ActionForward list(ActionMapping mapping, ActionForm form,
                            HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
      Connection conn = null;
      conn = getConnection(request);
      HttpSession session = request.getSession();

      try {
          TSoSDuCOTDAO dao = new TSoSDuCOTDAO(conn);
          TSoSDuCOTForm frm = (TSoSDuCOTForm)form;
//          TSoSDuCOTVO vo = new TSoSDuCOTVO();
          Collection colChkTso = null;
          String strJSON = null;
          String id_kb =
              session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();

          String ma_kb = frm.getMa_kb();
          String ma_nh = frm.getMa_nh();
          String type= frm.getLoai_ttin();
        String ngay_gd="";
        String strTS ="";
          if("COT".equals(type)){
           ngay_gd = frm.getTu_ngay_cot();
           strTS +=" SELECT count(0) as ts_cot FROM sp_tso_cutoftime  where  ma_nh_kb='"+ma_kb +"' And ma_nh='" + ma_nh +"' AND to_date(ngay_gd,'DD/MM/YYYY') = to_date(sp_util_pkg.fnc_get_ngay_lam_viec_truoc(to_date('"+ngay_gd+"','DD/MM/YYYY') -1),'DD/MM/YYYY')";
//           strTS +="select 1 as so_du from dual";
          }else if ("SO_DU".equals(type)){
            ngay_gd = frm.getTu_ngay_sdu();
            strTS +=" SELECT count(1) as ts_sdu FROM sp_so_du where  ma_kb='"+ma_kb +"' And ma_nh='" + ma_nh +"' AND to_date(ngay_gd,'DD/MM/YYYY') = to_date(sp_util_pkg.fnc_get_ngay_lam_viec_truoc(to_date('"+ngay_gd+"','DD/MM/YYYY') -1),'DD/MM/YYYY')";
          }

          colChkTso = dao.getChkGhi(strTS,null);
        String strCurDate = StringUtil.getCurrentDate(); 
        request.setAttribute("strCurDate",strCurDate);
          //            String cho_phep_sua =dcVO.getCho_phep_sua();

          java.lang.reflect.Type ChkGhi =
              new TypeToken<Collection<TSoSDuCOTVO>>() {
          }.getType();
          strJSON = new Gson().toJson(colChkTso, ChkGhi);

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

// muonn de chuyen page chuc nang nho thiet lap tham so
    @Override
    public ActionForward delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        return mapping.findForward("success");
    }

//muon de thiet lap tham so
    @Override
    public ActionForward addExc(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        Connection conn = null;
        try{
            conn = getConnection(request);
            TSoSDuCOTForm frm = (TSoSDuCOTForm)form;
            TSoSDuCOTDAO dao = new TSoSDuCOTDAO(conn);
            List tSoKhoBac = null;
            TTThanhToanDAO ttdao = new TTThanhToanDAO(conn);
            TTThanhToanVO vo = ttdao.getCap(" and ma = "+frm.getShkb(), null);
            
            boolean flag1,flag2,flag3,flag4,flag5;
            flag1 = flag2 = flag3 = flag4 = flag5 = true;
            
            String whereClause = " AND KB_ID = ? ";
            Vector params = new Vector();
            params.add(new Parameter(vo.getId(),true));
            tSoKhoBac = (List)dao.getTSoKhoBac(whereClause, params);
            TSoKhoBacVO tSoKhoBacVO = new TSoKhoBacVO();
            tSoKhoBacVO.setKb_id(vo.getId());
            
            if(tSoKhoBac == null){
                  dao.updateTSoKhoBac(tSoKhoBacVO,"CHO_PHEP_CHON_DC_KHOP_DUNG");
                  dao.updateTSoKhoBac(tSoKhoBacVO,"CHO_PHEP_NHAN_QTOAN_THU_CONG");
                  dao.updateTSoKhoBac(tSoKhoBacVO,"CHO_PHEP_NHAP_THU_CONG");
                  dao.updateTSoKhoBac(tSoKhoBacVO,"CHO_PHEP_QUYET_TOAN_TAM");
                  dao.updateTSoKhoBac(tSoKhoBacVO,"HACH_TOAN_TABMIS_THEO_NGAY_KS_NH");
                  flag1 = flag2 =flag3 =flag4 = flag5 = false;
                  request.setAttribute("status", "2");
            }else{
                for(int i = 0; i < tSoKhoBac.size(); i++ ){
                    TSoKhoBacVO tSoKBVO = (TSoKhoBacVO)tSoKhoBac.get(i);
                    if(tSoKBVO.getTen_ts().equalsIgnoreCase("CHO_PHEP_CHON_DC_KHOP_DUNG")){
                        flag1 = false;
                    }else if(tSoKBVO.getTen_ts().equalsIgnoreCase("CHO_PHEP_NHAN_QTOAN_THU_CONG")){
                        flag2 = false;
                    }else if(tSoKBVO.getTen_ts().equalsIgnoreCase("CHO_PHEP_NHAP_THU_CONG")){
                        flag3 = false;
                    }else if(tSoKBVO.getTen_ts().equalsIgnoreCase("CHO_PHEP_QUYET_TOAN_TAM")){
                        flag4 = false;
                    }else if(tSoKBVO.getTen_ts().equalsIgnoreCase("HACH_TOAN_TABMIS_THEO_NGAY_KS_NH")){
                        flag5 = false;
                    }
                }
                
                if(flag1){
                    dao.updateTSoKhoBac(tSoKhoBacVO,"CHO_PHEP_CHON_DC_KHOP_DUNG");
                }
                if(flag2){
                    dao.updateTSoKhoBac(tSoKhoBacVO,"CHO_PHEP_NHAN_QTOAN_THU_CONG");
                }
                if(flag3){
                    dao.updateTSoKhoBac(tSoKhoBacVO,"CHO_PHEP_NHAP_THU_CONG");
                }
                if(flag4){
                    dao.updateTSoKhoBac(tSoKhoBacVO,"CHO_PHEP_QUYET_TOAN_TAM");
                }
                if(flag5){
                    dao.updateTSoKhoBac(tSoKhoBacVO,"HACH_TOAN_TABMIS_THEO_NGAY_KS_NH");
                }
                conn.commit();
                request.setAttribute("status", "2");
            }
            
        }catch(Exception e){
            request.setAttribute("status", "1");
            e.printStackTrace();
        }finally{
            close(conn);
        }
        return mapping.findForward("success");
    }
}
