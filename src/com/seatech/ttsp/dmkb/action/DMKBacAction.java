package com.seatech.ttsp.dmkb.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DChieu1VO;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanDAO;

import java.sql.Connection;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class DMKBacAction extends AppAction {

  public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                            HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
      Connection conn = null;
      try {
          conn = getConnection(request);
          HttpSession session = request.getSession();
          DChieu1DAO dao = new DChieu1DAO(conn);
          DChieu1VO vo = new DChieu1VO();

          String kb_code =
              session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
          String kb_id =
              session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();

          List dmucNH = null;
          TTThanhToanDAO TTdao = new TTThanhToanDAO(conn);
          dmucNH = (List)TTdao.getDMucNH(null,null);
          request.setAttribute("dmucNH", dmucNH);

          String strCap = " and ma=" + kb_code;
          vo = dao.getCap(strCap, null);
          List dmuckb_cha = null;
          String cap = vo.getCap();
          if ("0001".equals(kb_code) || "0002".equals(kb_code)) {
              String strWhere = " ";
              dmuckb_cha = (List)dao.getDMucKB_cha(strWhere, null);
              request.setAttribute("dmuckb_tinh", dmuckb_cha);
              request.setAttribute("dftinh", "dftinh");
              request.setAttribute("TTTT", "TTTT");
          } else if ("0003".equals(kb_code)) {
              String strWhere = " AND a.ma='0003' ";
              dmuckb_cha = (List)dao.getDMucKB_cha(strWhere, null);
              request.setAttribute("dmuckb_tinh", dmuckb_cha);
          } else if ("5".equals(cap)) {
              String strWhere = "";
              strWhere += " and c.ma=" + kb_code;
              dmuckb_cha = (List)dao.getDMucKB_cha(strWhere, null);

              request.setAttribute("dmuckb_tinh", dmuckb_cha);
          } else {
              String strWhere = "";
              strWhere +=
                      " and c.id in (select id_cha from sp_dm_htkb where ma= " +
                      kb_code + ")";
              dmuckb_cha = (List)dao.getDMucKB_cha(strWhere, null);
              request.setAttribute("dmuckb_tinh", dmuckb_cha);
          }
        PagingBean pagingBean = new PagingBean();
        request.setAttribute("PAGE_KEY", pagingBean);
      } catch (Exception e) {
          throw e;

      } finally {
          close(conn);
      }
      return mapping.findForward("success");
  }

}
