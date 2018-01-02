package com.seatech.ttsp.saoke.action;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.FontUtil;
import com.seatech.framework.utils.TTSPLoadDMuc;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DChieu1VO;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.dmnhkb.DMHTKBacVO;
import com.seatech.ttsp.dmnhkb.DMNHKBacDAO;
import com.seatech.ttsp.saoke.form.TCuuDChieuSoChiTietForm;
import com.seatech.ttsp.saoke.saoketkDAO;
import com.seatech.ttsp.saoke.saoketkVO;
import com.seatech.ttsp.tknhkb.TKNHKBacDAO;
import com.seatech.ttsp.tknhkb.TKNHKBacVO;
import com.seatech.ttsp.tracuusodu.TraCuuSoDuDAO;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanDAO;

import java.io.PrintWriter;

import java.lang.reflect.Type;

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


public class TCuuDChieuSoChiTietAction extends AppAction {
    @Override
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            TCuuDChieuSoChiTietForm tCuuForm = (TCuuDChieuSoChiTietForm)form;
            saoketkDAO saokeDAO = new saoketkDAO(conn);
            TKNHKBacDAO tkDAO = new TKNHKBacDAO(conn);
            //            saoketkVO vo = new saoketkVO();
            DChieu1DAO dao = new DChieu1DAO(conn);
            DChieu1VO vo = new DChieu1VO();

            int rowOnPage = 15;
            int currentPage =
                (tCuuForm.getPageNumber() == null) ? 1 : Integer.parseInt(tCuuForm.getPageNumber());
            Integer totalCount[] = new Integer[1];

            String kb_code =
                session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
            //String kb_id = session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();

            TTThanhToanDAO TTdao = new TTThanhToanDAO(conn);
            List dmucKB = null;
            List listTracuu = null;
            List dmNganHang = null;
            List TKList = null;

            vo = dao.getCap(" and ma=" + kb_code, null);
            String cap = vo.getCap();
            Vector v_param = new Vector();
            String whereCondition = null;
            String whereTK = "";
            String whereForResult = "";
            //String tempTrangThai = tCuuForm.getTrangthai();

            TKList = new ArrayList();
            if ("0001".equals(kb_code)) {
                whereCondition = " ";
                request.setAttribute("dchieu3", "dchieu3");
                request.setAttribute("dftinh", "dftinh");
                dmNganHang = (List)TTdao.getDMucNH(null, null);
                if (tCuuForm.getKb_huyen() != null &&
                    !"".endsWith(tCuuForm.getKb_huyen())) {
                    
                    whereTK = " AND c.id= '" + tCuuForm.getKb_huyen() + "' ";
                }
              
            } else if ("0003".equals(kb_code)) {
                whereCondition = " AND a.ma='0003' ";
                dmNganHang = (List)TTdao.getDMucNH(null, null);
                
                whereTK = " AND c.ma= '0003' ";
            } else if ("5".equals(cap)) {
                whereCondition = " and a.ma_cha='" + kb_code + "'";
                if (tCuuForm.getKb_huyen() != null &&
                    !"".endsWith(tCuuForm.getKb_huyen())) {
                    whereTK = " AND c.id= '" + tCuuForm.getKb_huyen() + "' ";
                } else {
                    whereTK = " AND c.ma_cha= '" + kb_code + "' ";
                }
            } else if ("2".equals(cap)) {
                String whereHTKB = " ma = ?";
                Vector vector = new Vector();
                vector.add(new Parameter(kb_code, true));
                DMHTKBacVO temp = new DMHTKBacVO();
                DMNHKBacDAO tempDAO = new DMNHKBacDAO(conn);
                temp = tempDAO.getDMHTKBac(whereHTKB, vector);
                whereCondition = " and a.ma_cha=" + temp.getMa_cha();
                if (tCuuForm.getKb_huyen() != null &&
                    !"".endsWith(tCuuForm.getKb_huyen())) {
                   
                    whereTK = " AND c.id= '" + tCuuForm.getKb_huyen() + "' ";
                }
            } else {
                whereCondition = " AND a.ma = '" + kb_code + "' ";
                whereTK = "AND c.ma = '" + kb_code + "' ";
            }            
            //List tai khoan
            if (tCuuForm.getNgan_hang() != null &&
                !"".equals(tCuuForm.getNgan_hang())) {
              if (tCuuForm.getNgan_hang().trim().length() == 3) {
                whereTK +=
                        " AND b.ma_nh like '%__" + tCuuForm.getNgan_hang().trim() + "%'";
              }else{
                whereTK +=
                        " AND b.ma_nh = '" + tCuuForm.getNgan_hang().trim() + "'";
              }
            }
            if(whereTK != null && !"".equals(whereTK)){
              TKList = (List)tkDAO.getTK_NH_KB(whereTK, null);
            }
            //*********
            
            whereForResult = getConditionForResult(tCuuForm, v_param);
            request.setAttribute("kb_huyen", tCuuForm.getKb_huyen());
            request.setAttribute("ngan_hang", tCuuForm.getNgan_hang());
            request.setAttribute("so_tk", tCuuForm.getSo_tk());
            PagingBean pagingBean = new PagingBean();
            if (isTokenValid(request)) {
                listTracuu =
                        (List)saokeDAO.getTCuuDChieuSoChiTiet_ptrang(whereForResult,
                                                                     v_param,
                                                                     currentPage,
                                                                     rowOnPage,
                                                                     totalCount);
                pagingBean.setNumberOfRow(totalCount[0].intValue());
                resetToken(request);
                saveToken(request);
                //                if (tempTrangThai != null) {
                //                    if (tempTrangThai.equals("02"))
                //                        tCuuForm.setTrangthai(tempTrangThai);
                //                }
            } else {
                saveToken(request);
            }
            dmucKB = (List)dao.getDMucKB_huyen(whereCondition, null);
            TTSPLoadDMuc ttspLoadDmuc = new TTSPLoadDMuc(conn); 
            ttspLoadDmuc.getDanhMucKB(request,kb_code);
            
            request.setAttribute("dmucKB", dmucKB);
            request.setAttribute("listTracuu", listTracuu);
            request.setAttribute("dmNganHang", dmNganHang);
            request.setAttribute("TKList", TKList);

            pagingBean.setCurrentPage(currentPage);
            pagingBean.setRowOnPage(rowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);
        } catch (Exception ex) {
            throw ex;
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

    private String getConditionForResult(TCuuDChieuSoChiTietForm tCuuForm,
                                         Vector v_param) {
        String whereForResult = "";
        if (tCuuForm.getKb_tinh() != null &&
            !"".equals(tCuuForm.getKb_tinh().trim())) {
            whereForResult += " and d.ma_cha = ? ";
            v_param.add(new Parameter(tCuuForm.getKb_tinh(), true));
        }
        if (tCuuForm.getKb_huyen() != null &&
            !"".equals(tCuuForm.getKb_huyen().trim())) {
            whereForResult += " and d.ma = ? ";
            v_param.add(new Parameter(tCuuForm.getKb_huyen(), true));
        }
        if (tCuuForm.getNgan_hang() != null &&
            !"".equals(tCuuForm.getNgan_hang().trim())) {
            if (tCuuForm.getNgan_hang().length() == 3) {
                tCuuForm.setNgan_hang("__" + tCuuForm.getNgan_hang() + "%");
            }
            whereForResult += " and a.ma_nh like ? ";
            v_param.add(new Parameter(tCuuForm.getNgan_hang(), true));
        }
        //        if (tCuuForm.getTrangthai() != null &&
        //            !"".equals(tCuuForm.getTrangthai())) {
        //            whereForResult += " and a.trang_thai = ? ";
        //            if (tCuuForm.getTrangthai().equals("01")) {
        //                tCuuForm.setKetqua("0");
        //            } else if (tCuuForm.getTrangthai().equals("02")) {
        //                tCuuForm.setTrangthai("01");
        //                tCuuForm.setKetqua("1");
        //            }
        //            v_param.add(new Parameter(tCuuForm.getTrangthai(), true));
        //        }
        if (tCuuForm.getFrom_date() != null &&
            !"".equals(tCuuForm.getFrom_date())) {
            whereForResult += " and a.ngay_dc >= to_date(?,'DD/MM/YYYY') ";
            v_param.add(new Parameter(tCuuForm.getFrom_date(), true));
        }

        if (tCuuForm.getTo_date() != null &&
            !"".equals(tCuuForm.getTo_date())) {
            whereForResult += " and a.ngay_dc <= to_date(?,'DD/MM/YYYY') ";
            v_param.add(new Parameter(tCuuForm.getTo_date(), true));
        }

        if (tCuuForm.getKetqua() != null && !"".equals(tCuuForm.getKetqua())) {
            whereForResult += " and a.ket_qua = ?";
            v_param.add(new Parameter(tCuuForm.getKetqua(), true));
        }
        if (tCuuForm.getSo_tk() != null && !"".equals(tCuuForm.getSo_tk())) {
            whereForResult += " and a.so_tk = ?";
            v_param.add(new Parameter(tCuuForm.getSo_tk(), true));
        }
        if (tCuuForm.getKetqua() != null && !"".equals(tCuuForm.getKetqua())) {
            whereForResult += " and a.ket_qua = ?";
            v_param.add(new Parameter(tCuuForm.getKetqua(), true));
        }

        return whereForResult;
    }


    @Override
    protected ActionForward executeAction(ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
        Connection conn = null;
        PrintWriter out = null;
        try {
            TCuuDChieuSoChiTietForm f = (TCuuDChieuSoChiTietForm)form;
            conn = getConnection(request);
            DChieu1DAO dao = new DChieu1DAO(conn);
            List dmucKB =
                (List)dao.getDMucKB_huyen(" and a.id =" + f.getKb_id(), null);

            JsonObject jsonObject = new JsonObject();
            response.setHeader("X-JSON", jsonObject.toString());

            Type listType = new TypeToken<List<saoketkVO>>() {
            }.getType();
            String json = new Gson().toJson(dmucKB, listType);
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            out = response.getWriter();
            out.println(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.flush();
            out.close();
            close(conn);
        }
        return null;
    }

    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {

        Connection conn = null;

        try {

            conn = getConnection(request);

            Collection colTK = null;

            String kb_id = request.getParameter("kb_id").toString();
            String nh_id = request.getParameter("nh_id").toString();

            TKNHKBacDAO tkDAO = new TKNHKBacDAO(conn);
            String strWhere = "";
            if (!"".equals(nh_id) && nh_id != null) {
                strWhere =
                        " AND c.ma = " + kb_id + " AND b.ma_nh = " + nh_id +
                        " ";
            } else {
                strWhere = " AND c.ma = " + kb_id + " ";
            }

            colTK = tkDAO.getTK_NH_KB(strWhere, null);

            java.lang.reflect.Type listCHK =
                new TypeToken<Collection<TKNHKBacVO>>() {
            }.getType();
            String strJSON = new Gson().toJson(colTK, listCHK);

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
        return mapping.findForward(AppConstants.SUCCESS);
    }
}
