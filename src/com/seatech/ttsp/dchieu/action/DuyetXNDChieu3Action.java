package com.seatech.ttsp.dchieu.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.TTSPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.dchieu.DChieu3DAO;
import com.seatech.ttsp.dchieu.DChieu3VO;
import com.seatech.ttsp.dchieu.KQDChieu3CTietDAO;
import com.seatech.ttsp.dchieu.KQDChieu3DAO;
import com.seatech.ttsp.dchieu.KQDChieu3VO;
import com.seatech.ttsp.dchieu.form.DuyetXNDChieu3Form;
import com.seatech.ttsp.proxy.giaodien.SendKQDC1;
import com.seatech.ttsp.proxy.pki.PKIService;
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


public class DuyetXNDChieu3Action extends AppAction {
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "DCHIEU.DUYETDCHIEU3")) {
            return mapping.findForward("notRight");
        }
        Connection conn = null;
        try {
            conn = getConnection(request);
//            HttpSession session = request.getSession();
            DChieu3DAO dao = new DChieu3DAO(conn);
            KQDChieu3CTietDAO dchieu3CtietDAO = new KQDChieu3CTietDAO(conn);
            DChieu3VO vo = new DChieu3VO();
            DuyetXNDChieu3Form f = (DuyetXNDChieu3Form)form;
            Collection colTHBK = new ArrayList();
            Collection TongKQBK = new ArrayList();
            Collection colMT910 = null;
            Collection colMT900 = null;
            Vector vParam = new Vector();
          DChieu3DAO dchieu3DAO = new DChieu3DAO(conn);
          vo = dchieu3DAO.getMaSGD(null, null);
//          String kb_nhan = vo.getMa_nh();
            TTThanhToanDAO TTdao = new TTThanhToanDAO(conn);
            List dmucNH = null;
            dmucNH = (List)TTdao.getDMucNH(null,null);
            request.setAttribute("dmucNH", dmucNH);

            String strWhere =
                " and (b.trang_thai = '01' or ( b.trang_thai <> '01' and to_date(b.ngay_thien_dc)= to_date(sysdate)))";

          String send_bank = f.getNhkb_nhan();
            if(send_bank!=null && ""!=send_bank && !"000".equals(send_bank)){
              strWhere +=" and a.send_bank='"+send_bank+"'";
            }
            ArrayList<DChieu3VO> colList =
                (ArrayList<DChieu3VO>)dao.getDuyetDChieu3List(strWhere, null);
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
            colTHBK = dao.getDuyetDChieu3List(strList, null);

            if (kq_id != null && !"".equals(kq_id)) {
                TTSPUtils spUtils = new TTSPUtils(conn);
                String strNoiDungKy = spUtils.getNoiDungKy(kq_id, "069", "Y");
                f.setNoi_dung_ky(strNoiDungKy);
            }

            //            String kq_id
            strWhere = " AND a.kq_id='" + kq_id + "' AND a.mt_type='900'";
            colMT900 = dchieu3CtietDAO.getKQDChieu3CTietList(strWhere, vParam);

            strWhere = " AND a.kq_id='" + kq_id + "' AND a.mt_type='910'";
            colMT910 = dchieu3CtietDAO.getKQDChieu3CTietList(strWhere, vParam);

            vParam.add(new Parameter(kq_id, true));
            vParam.add(new Parameter(kq_id, true));
            vParam.add(new Parameter(kq_id, true));
            vParam.add(new Parameter(kq_id, true));
            TongKQBK = dchieu3CtietDAO.getTongKQDChieu3(vParam);


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
        if (!checkPermissionOnFunction(request, "DCHIEU.DUYETDCHIEU3")) {
            return mapping.findForward("notRight");
        }

        Connection conn = null;
        try {
            if (isTokenValid(request)) {
                conn = getConnection(request);
                HttpSession session = request.getSession();
//                DChieu3DAO dao = new DChieu3DAO(conn);
                //        KQDChieu3CTietDAO dchieu3CtietDAO =  new KQDChieu3CTietDAO(conn);
//                DChieu3VO vo = new DChieu3VO();
                DuyetXNDChieu3Form f = (DuyetXNDChieu3Form)form;
                //verify
                boolean bVerifySignature = false;
//                String strWSDL = getThamSoHThong("WSDL_PKI", session);
//                PKIService pkiService = new PKIService(strWSDL);
//                String strUserName =
//                    session.getAttribute(AppConstants.APP_DOMAIN_SESSION) +
//                    "\\" +
//                    session.getAttribute(AppConstants.APP_USER_CODE_SESSION);
                String strSign = f.getChuky_ktt();
//                String strContent = f.getNoi_dung_ky();
//                String strCertSerial = f.getCertserial();
//                String strAppID = getThamSoHThong("APP_ID", session);
//                String[] arrResultKy =
//                    pkiService.VerifySignature(strUserName, strCertSerial,
//                                               strContent, strSign, strAppID);
                String[] arrResultKy = new String[2];
                arrResultKy[0]="VALID";
                
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
                    KQDChieu3DAO kqDChieu3DAO = new KQDChieu3DAO(conn);
                    KQDChieu3VO kqDChieu3VO = new KQDChieu3VO();
                    kqDChieu3VO.setId(kq_id);
                    kqDChieu3VO.setTrang_thai("02");
                    kqDChieu3VO.setTthai_ttin("01");
                    kqDChieu3VO.setChuky_ktt(strSign);
                    
//                    String strMaNH = f.getSend_bank();
                    String user_id =
                        session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString();
                    String msg_id =  send.sendMessageDC3(kq_id, user_id);
                  kqDChieu3VO.setMsg_id(msg_id);
                  kqDChieu3DAO.updateKQDC3(kqDChieu3VO);
                    conn.commit();
                    request.setAttribute("duyettcong", "duyettcong");
                }else request.setAttribute("duyettcong", "");
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
