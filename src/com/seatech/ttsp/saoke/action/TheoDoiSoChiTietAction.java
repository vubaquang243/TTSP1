package com.seatech.ttsp.saoke.action;

import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;

import com.seatech.ttsp.saoke.RowHeader;
import com.seatech.ttsp.saoke.form.TheoDoiSoChiTietForm;

import com.seatech.ttsp.saoke.saoketkDAO;
import com.seatech.ttsp.ttthanhtoan.DMucNHVO;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanDAO;

import java.sql.Connection;

import java.sql.ParameterMetaData;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class TheoDoiSoChiTietAction extends AppAction{

    @Override
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        try{
            conn = getConnection(request);
            TTThanhToanDAO TTdao = new TTThanhToanDAO(conn);
            TheoDoiSoChiTietForm f = (TheoDoiSoChiTietForm)form;
            
            List<RowHeader> rowHeader = null;
            List dmNganHang = (List)TTdao.getDMucNH(null,null);
            List<String> trangThai = new ArrayList<String>();
            
            if(f.getFrom_date()!=null&&f.getTo_date()!=null
               &&!"".equals(f.getFrom_date().trim())&&!"".equals(f.getTo_date())){
                rowHeader = getListHeader(f.getFrom_date(), f.getTo_date(),dmNganHang, conn);
            }
            
            for(int i=0;i<dmNganHang.size()*2;i++){
                if(i%2==0)trangThai.add("\u0110\u00E3 nh\u1EADn");
                else trangThai.add("Ch\u01B0a nh\u1EADn");
            }
            
            request.setAttribute("form", f);
            request.setAttribute("trangThai", trangThai);
            request.setAttribute("dmNganHang", dmNganHang);
            request.setAttribute("colName", rowHeader);
        }catch(Exception ex){
            throw ex;
        }finally{
            close(conn);
        }
        return mapping.findForward("success");
    }
    
    
    private List<RowHeader> getListHeader(String from_date, String to_date,List dmNganHang,Connection conn) throws ParseException {
        List<RowHeader> listDate = new ArrayList<RowHeader>();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
                  
        calendar1.setTime(format.parse(from_date));
        calendar2.setTime(format.parse(to_date));
                  
        while (calendar1.compareTo(calendar2)<=0) {
            String s = calendar1.get(Calendar.DAY_OF_MONTH)+"/"+(calendar1.get(Calendar.MONTH)+1)+"/"+calendar1.get(Calendar.YEAR);
            RowHeader row = new RowHeader();
            row.setDate(s);
            row.setResultRow(getResultRow(s,dmNganHang,conn));
            listDate.add(row);
            calendar1.add(Calendar.DAY_OF_MONTH, 1);
        }
        return listDate;
    }

    private List<String> getResultRow(String s, List dmNganHang, Connection conn){
        List<String> listResult = new ArrayList<String>();
        List<String> temp = new ArrayList<String>();
        saoketkDAO dao = new saoketkDAO(conn);
        for(Object obj : dmNganHang){
            DMucNHVO nhVO = (DMucNHVO)obj;
            String maDV = "__"+nhVO.getMa_dv()+"%";
            List v = new ArrayList();
            v.add(s); v.add(maDV); v.add(maDV); v.add(s);
            temp.add(dao.getResultRow(v));
        }
          
        for(String obj : temp){
            String[] arrTemp = obj.split("/");
            listResult.add(obj);
            int i = Integer.valueOf(arrTemp[1]) - Integer.valueOf(arrTemp[0]);
            listResult.add(String.valueOf(i)+"/"+arrTemp[1]);
        }
        return listResult;
    }


    @Override
    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        try{
            conn = getConnection();
            TTThanhToanDAO TTdao = new TTThanhToanDAO(conn);
            saoketkDAO skDAO = new saoketkDAO(conn);
            List dmNganHang = (List)TTdao.getDMucNH(null,null);
            String date = null;
            String index = null;
            String type = null;
            List existSaoKeTK = null;
            List notExistSaoKeTK = null;
            DMucNHVO vo = null;
            
            if(request.getParameter("date")!=null && !"".equals(request.getParameter("date"))){
                date = request.getParameter("date") instanceof String ? (String)request.getParameter("date") : null;
            }
            if(request.getParameter("type")!=null && !"".equals(request.getParameter("type"))){
                type = request.getParameter("type") instanceof String ? (String)request.getParameter("type") : null;
            }
            if(request.getParameter("index")!=null && !"".equals(request.getAttribute("index"))){
                index = request.getParameter("index") instanceof String ? (String)request.getParameter("index") : null;
            }
            if(date != null && index !=null){
                vo = (DMucNHVO)dmNganHang.get(Integer.valueOf(index)/2);
                String ma_dv = "__"+vo.getMa_dv()+"%";
                Vector params = new Vector();
                params.add(new Parameter(date,true)); params.add(new Parameter(ma_dv,true));
                existSaoKeTK = skDAO.getListExistSaoKe(params);
                
                params.clear(); params.add(new Parameter(ma_dv,true)); params.add(new Parameter(date,true)); params.add(new Parameter(date,true)); params.add(new Parameter(ma_dv,true));
                notExistSaoKeTK = skDAO.getListNotExistSaoKe(params);
            }
            if(type == null){
                existSaoKeTK = null;
            }
            
            request.setAttribute("nganHang", vo.getTen_nh());
            request.setAttribute("existSaoKeTK", existSaoKeTK);
            request.setAttribute("notExistSaoKeTK", notExistSaoKeTK);
        }catch(Exception ex){
            throw ex;
        }finally{
            close(conn);
        }
        return mapping.findForward("success");
    }
}
