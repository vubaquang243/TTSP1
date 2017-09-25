package com.seatech.ttsp.saoke.action;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.FontUtil;
import com.seatech.ttsp.saoke.form.DChieuSKeForm;
import com.seatech.ttsp.saoke.saoketkDAO;
import com.seatech.ttsp.saoke.saoketkVO;
import com.seatech.ttsp.tknhkb.TKNHKBacDAO;
import com.seatech.ttsp.tknhkb.TKNHKBacVO;
import com.seatech.ttsp.tknhkb.action.TKNHKBacAction;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanDAO;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanVO;

import java.io.PrintWriter;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DChieuSKeAction extends AppAction {
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        ArrayList colTKNHKB = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            DChieuSKeForm fr = (DChieuSKeForm)form;

            String kb_code = null;
            kb_code = fr.getShkb();
            if(kb_code==null || "".equals(kb_code)){
              kb_code = session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
            }

            saoketkDAO dao = new saoketkDAO(conn);
            TKNHKBacDAO tknhDAO = new TKNHKBacDAO(conn);
            
            String strNHKB = " AND c.ma='" + kb_code + "' and a.loai_tk <>'01'";
            colTKNHKB = (ArrayList)tknhDAO.getTK_NH_KB(strNHKB, null); //*
            if(request.getParameter("ma_nh")!=null&&!"".equals(request.getParameter("ma_nh").trim())){
                filterMultiBankRef(request.getParameter("ma_nh").trim(), colTKNHKB); // L?c b?t th?ng có nhi?u nh nh?ng t? tra c?u qua
            }
            request.setAttribute("colTKNHKB", colTKNHKB); //*

            String ma_nh  = null;
            ma_nh = fr.getMa_nh();
            if(ma_nh==null || "".equals(ma_nh)){
              ma_nh = fr.getNgan_hang();
            }
            String ma_kb =
                fr.getMa_kb() == null ? session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString() :
                fr.getMa_kb().trim();
            String ngay_dc = null;
             ngay_dc = fr.getNgay_dc();
            if(ngay_dc == null || "".equals(ngay_dc)){
                ngay_dc = fr.getNgay();
            }


            String strWhere =
                " AND a.ma_nh='" + ma_nh + "' and a.ma_kb='" + ma_kb +
                              "' and to_char(ngay_dc,'DD/MM/YYYY')='" + ngay_dc + "' and a.so_tk = '"+fr.getSo_tk()+"'";

            List colCTietCLech = null;
            List<saoketkVO> colCTiet = dao.getCTietDChieuSKe(strWhere, null);

            if (colCTiet.isEmpty()) {
                return mapping.findForward("success");
            }else{
//                String chenhLechCondition = " and a.id_ref = ? or b.id_ref = ?";
              String chenhLechCondition = " ";
                Vector params = new Vector();
                params.add(new Parameter(colCTiet.get(0).getId(),true));
                params.add(new Parameter(colCTiet.get(0).getId(),true));
                colCTietCLech = dao.getChenhLech(chenhLechCondition, params);
            }
            
            request.setAttribute("colCTiet", colCTiet);
            request.setAttribute("colCTietCLech", colCTietCLech);
            request.setAttribute("idxTK", request.getAttribute("idxTK"));

        } catch (Exception e) {
            throw e;

        } finally {
            saveToken(request);
            close(conn);
        }
        return mapping.findForward("success");
    }

    private void filterMultiBankRef(String ma_nh,
                                    ArrayList colTKNHKB) {
        for(int i = 0; i < colTKNHKB.size();i++){
            if(colTKNHKB.get(i) instanceof TKNHKBacVO){
                TKNHKBacVO tempVO = (TKNHKBacVO)colTKNHKB.get(i);
                if(!tempVO.getMa_nh().equals(ma_nh)){
                    colTKNHKB.remove(i);
                }
            }
       }
    }

    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            String strMaNH;
            String strJSON;

            conn = getConnection();
            //            DMKBacDAO dmdao = new DMKBacDAO(conn);
            TTThanhToanDAO ttdao = new TTThanhToanDAO(conn);

            Collection colNH = null;
            HttpSession session = request.getSession();
            DChieuSKeForm fr = (DChieuSKeForm)form;
            String kb_code = null;

            kb_code = fr.getShkb().trim();
            if (kb_code == null || "".equals(kb_code)) {
                kb_code = session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
            }
            strMaNH = request.getParameter("nh_id").toString();
            String strNH = " and b.ma=" + kb_code + " and c.ma_nh ='" + strMaNH + "'  and a.loai_tk <>'01'";

            colNH = ttdao.getListTKNHKB(strNH, null);

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
//          saveToken(request);
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            saoketkDAO dao = new saoketkDAO(conn);
            DChieuSKeForm frm = (DChieuSKeForm)form;
            String so_tk = frm.getSo_tk();
            String ngay = frm.getNgay();
            String ma_kb = session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
            String ma_nh = frm.getNgan_hang();
            if (isTokenValid(request)) {
                //                Collection colCHK = null;
                //                String strCHK =
                //                    " AND a.id = ( SELECT id FROM sp_dc_sao_ke WHERE ma_nh='" +
                //                    ma_nh + "' and ma_kb='" + ma_kb +
                //                    "' and to_char(ngay_dc,'DD/MM/YYYY')='" + ngay +  "' )";
                //
                //                colCHK = dao.getCTietDChieuSKe(strCHK, null);
                //                if (colCHK.isEmpty()) {
                //                    request.setAttribute("chkdchieu", "chkdchieu");
                //                  request.setAttribute("ngay", ngay);
                //                  request.setAttribute("ma_nh", ma_nh);
                //                    return mapping.findForward("success");
                //                }


                CallableStatement cs = null;
                cs = conn.prepareCall("{call sp_dc_sao_ke_pkg.proc_dc_sao_ke(?,?,?,?,?,?,?,?)}"); //*
                cs.setString(1, so_tk);
                cs.setString(2, ngay);
                cs.setString(3, ma_nh);
                cs.setString(4, ma_kb);
                cs.setString(5, "DP");

                cs.registerOutParameter(6, java.sql.Types.VARCHAR);
                cs.registerOutParameter(7, java.sql.Types.VARCHAR);
                cs.registerOutParameter(8, java.sql.Types.VARCHAR);
                cs.execute();
                String p_err_code = cs.getString(7);
                String p_err_desc = cs.getString(8);
                
                frm.setErr_code(p_err_code);
                frm.setErr_desc(p_err_desc);
                
                conn.commit();
            }
            resetToken(request);
            saveToken(request);
            request.setAttribute("ngay", ngay);
            request.setAttribute("ma_nh", ma_nh);
            request.setAttribute("idxTK", so_tk);

        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            saoketkDAO dao = new saoketkDAO(conn);
            saoketkVO vo = new saoketkVO();
            DChieuSKeForm frm = (DChieuSKeForm)form;
            String id = frm.getId() == null ? "" : frm.getId();
            String so_tk = frm.getSo_tk();
            String ngay = frm.getNgay();
            String ma_kb =
                session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
            String ma_nh = frm.getNgan_hang();
            if (isTokenValid(request)) {

                String noi_dung =
                    frm.getNoi_dung() == null ? "" : frm.getNoi_dung();
                vo.setNoi_dung(noi_dung);
                vo.setTrang_thai("01");
                vo.setMa_kb(ma_kb);
                vo.setMa_nh(ma_nh);
                vo.setSo_tk(so_tk);
                vo.setId(id);
                vo.setNgay_dc(ngay);
                int abc = dao.updateDCSK(vo);
                if (abc > 0) {
                    conn.commit();
                    request.setAttribute("tcong", "tcong");
                }
            }
            resetToken(request);
            saveToken(request);
            request.setAttribute("ngay", ngay);
            request.setAttribute("ma_nh", ma_nh);

        } catch (Exception e) {
            throw e;

        } finally {
//            saveToken(request);
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

            conn = getConnection(request);

            saoketkDAO dao = new saoketkDAO(conn);

            Collection colCHK = null;
            HttpSession session = request.getSession();
            String ma_kb = session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
            
            String ma_nh = request.getParameter("ma_nh").toString();
            String ngay_dc = request.getParameter("ngay_dc").toString();
            String so_tk = request.getParameter("so_tk").toString();
            colCHK = dao.getCHKDChieuSKe(ma_nh, ma_kb, ngay_dc, so_tk, null);

            java.lang.reflect.Type listCHK =
                new TypeToken<Collection<saoketkVO>>() {
            }.getType();
            String strJSON = new Gson().toJson(colCHK, listCHK);

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
//          saveToken(request);
            close(conn);
        }
//        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
//        else
//            return null;
    }
  
}

