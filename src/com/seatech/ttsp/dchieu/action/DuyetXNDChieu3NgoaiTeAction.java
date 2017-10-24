package com.seatech.ttsp.dchieu.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.TTSPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.dchieu.DChieu3NgoaiTeDAO;
import com.seatech.ttsp.dchieu.DChieu3NgoaiTeVO;
import com.seatech.ttsp.dchieu.KQDChieu3NgoaiTeCTietDAO;
import com.seatech.ttsp.dchieu.KQDChieu3NgoaiTeDAO;
import com.seatech.ttsp.dchieu.KQDChieu3NgoaiTeVO;
import com.seatech.ttsp.dchieu.form.DuyetXNDChieu3NgoaiTeForm;
import com.seatech.ttsp.proxy.giaodien.SendKQDC1;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanDAO;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class DuyetXNDChieu3NgoaiTeAction extends AppAction {
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "DCHIEU.DUYETDCHIEU4")) {
            return mapping.findForward("notRight");
        }
        Connection conn = null;
        try {
            conn = getConnection(request);
            //            HttpSession session = request.getSession();
            DChieu3NgoaiTeDAO dao = new DChieu3NgoaiTeDAO(conn);
            KQDChieu3NgoaiTeCTietDAO dchieu4CtietDAO = new KQDChieu3NgoaiTeCTietDAO(conn);
            DChieu3NgoaiTeVO vo = new DChieu3NgoaiTeVO();
            DuyetXNDChieu3NgoaiTeForm f = (DuyetXNDChieu3NgoaiTeForm)form;
            Collection colTHBK = new ArrayList();
            Collection TongKQBK = new ArrayList();
            Collection colMT910 = null;
            Collection colMT900 = null;
            Vector vParam = new Vector();
            DChieu3NgoaiTeDAO dchieu4DAO = new DChieu3NgoaiTeDAO(conn);
            vo = dchieu4DAO.getMaSGD(null, null);
            //          String kb_nhan = vo.getMa_nh();
            TTThanhToanDAO TTdao = new TTThanhToanDAO(conn);
            List dmucNH = null;
            dmucNH = (List)TTdao.getDMucNH(null, null);
            request.setAttribute("dmucNH", dmucNH);

            String strWhere =
                " and (b.trang_thai = '01' or ( b.trang_thai <> '01' and to_date(b.ngay_thien_dc)= to_date(sysdate)))";

            String send_bank = f.getNhkb_nhan();
            if (send_bank != null && "" != send_bank &&
                !"000".equals(send_bank)) {
                strWhere += " and a.send_bank='" + send_bank + "'";
            }
            ArrayList<DChieu3NgoaiTeVO> colList =
                (ArrayList<DChieu3NgoaiTeVO>)dao.getDuyetDChieu4List(strWhere, null);
            if (colList.isEmpty()) {
                return mapping.findForward(AppConstants.SUCCESS);
            }
            request.setAttribute("colList", colList);

            String rowSelected = request.getParameter("rowSelected");
            if (null == rowSelected || "".equals(rowSelected) ||
                "row_qt_0".equals(rowSelected)) {
                vo = colList.get(0);
                BeanUtils.copyProperties(f, vo);
                request.setAttribute("rowSelected", "row_qt_0");
            } else {
                request.setAttribute("rowSelected", rowSelected);
            }

            String kq_id = f.getKq_id();
            String strList = " AND b.id='" + kq_id + "'";
            colTHBK = dao.getDuyetDChieu4List(strList, null);

            if (kq_id != null && !"".equals(kq_id)) {
                TTSPUtils spUtils = new TTSPUtils(conn);
                String strNoiDungKy = spUtils.getNoiDungKy(kq_id, "069", "Y");
                f.setNoi_dung_ky(strNoiDungKy);
            }

            //            String kq_id
            strWhere = " AND a.kq_id='" + kq_id + "' AND a.mt_type='900'";
            colMT900 = dchieu4CtietDAO.getKQDChieu4CTietList(strWhere, vParam);

            strWhere = " AND a.kq_id='" + kq_id + "' AND a.mt_type='910'";
            colMT910 = dchieu4CtietDAO.getKQDChieu4CTietList(strWhere, vParam);

            vParam.add(new Parameter(kq_id, true));
            vParam.add(new Parameter(kq_id, true));
            vParam.add(new Parameter(kq_id, true));
            vParam.add(new Parameter(kq_id, true));
            TongKQBK = dchieu4CtietDAO.getTongKQDChieu4(vParam);


            request.setAttribute("colTHBK", colTHBK);
            request.setAttribute("TongKQBK", TongKQBK);
            request.setAttribute("colMT900", colMT900);
            request.setAttribute("colMT910", colMT910);

            saveToken(request);
        } catch (TTSPException e) {
            throw e;
        } finally {
            conn.close();
        }
        return mapping.findForward(AppConstants.SUCCESS);
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
       // if (!checkPermissionOnFunction(request, "DCHIEU.DUYETDCHIEU4")) {
        //    return mapping.findForward("notRight");
      //  }

        Connection conn = null;
        try {
            if (isTokenValid(request)) {
                conn = getConnection(request);
                HttpSession session = request.getSession();
                DuyetXNDChieu3NgoaiTeForm f = (DuyetXNDChieu3NgoaiTeForm)form;
                //verify
                boolean bVerifySignature = false;
                String strSign = f.getChuky_ktt();
                String[] arrResultKy = new String[2];
                arrResultKy[0] = "VALID";

                if (arrResultKy != null && arrResultKy.length == 2) {
                    if (arrResultKy[0].equalsIgnoreCase("VALID")) {
                        bVerifySignature = true;
                    } else if (arrResultKy[0].equalsIgnoreCase("INVALID")) {
                        throw TTSPException.createException("TTSP-3001",
                                                            arrResultKy[1]);
                    } else if (arrResultKy[0].equalsIgnoreCase("ERROR")) {
                        throw TTSPException.createException("TTSP-3001",
                                                            arrResultKy[1]);
                    }
                }
                if (bVerifySignature) {

                    SendKQDC1 send = new SendKQDC1(conn);
                    String kq_id = f.getKq_id();
                    KQDChieu3NgoaiTeDAO kqDChieu4DAO = new KQDChieu3NgoaiTeDAO(conn);
                    KQDChieu3NgoaiTeVO kqDChieu4VO = new KQDChieu3NgoaiTeVO();
                    kqDChieu4VO.setId(kq_id);
                    kqDChieu4VO.setTrang_thai("02");
                    kqDChieu4VO.setTthai_ttin("01");
                    kqDChieu4VO.setChuky_ktt(strSign);

                    String user_id =
                        session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString();
                    String msg_id = send.sendMessageDC3(kq_id, user_id);
                    kqDChieu4VO.setMsg_id(msg_id);
                    kqDChieu4DAO.updateKQDC4(kqDChieu4VO);
                    conn.commit();
                    request.setAttribute("duyettcong", "duyettcong");
                } else
                    request.setAttribute("duyettcong", "");
            }
            resetToken(request);
            saveToken(request);

        } catch (Exception e) {
            throw e;

        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

}
