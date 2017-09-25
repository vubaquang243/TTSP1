package com.seatech.ttsp.ngaynghi;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;
import com.seatech.framework.utils.StringUtil;

import java.sql.Connection;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;


public class NgayNghiDAO extends AppDAO {
    Connection conn = null;
    private static String DD_MM_YYYY_HH_MI_SS = "dd/MM/yyyy HH:mm:ss";
    private String strValueObject = "com.seatech.ttsp.ngaynghi.NgayNghiVO";

    public NgayNghiDAO(Connection conn) {
        this.conn = conn;
    }

    public Collection getDSNgayNghiTrongThang(String strWhere,
                                              Vector vParam) throws Exception {
        try {
            String strSQL =
                "SELECT a.id, a.ngay, a.mo_ta, a.created_date, a.created_by" +
                "  FROM sp_ngay_nghi a where 1=1 ";
            if (strWhere != null) {
                strSQL += strWhere;
            }
            return executeSelectStatement(strSQL, vParam, strValueObject,
                                          conn);
        } catch (Exception e) {
            throw e;
        }

    }

    private boolean checkExsit(String strDate, Collection collNgayNghi) {
        Iterator iter = collNgayNghi.iterator();
        NgayNghiVO nnVO = null;
        long nDate = new Long(strDate).longValue();
        while (iter.hasNext()) {
            nnVO = (NgayNghiVO)iter.next();
            if (nDate == nnVO.getNgay().longValue()) {
                return true;
            }
        }
        return false;
    }

    public ArrayList getDSNgayTrongThang(String strNgay,String strThang,
                                         String strNam) throws Exception {
        ArrayList arrNgayTrongThang = new ArrayList();
       
        try {
            String strWhere = "and a.ngay >= ? and a.ngay <= ?";
            Vector vParam = new Vector();

            //Tham so truyen vao
            if (strThang.length()==1) {
              strThang="0" +strThang;
            }
            String strMonth = strThang;
            String strYear = strNam;
            //Khoi tao ngay dau tien trong thang
            String strDay = "01";
            String strDate = strYear + strMonth + strDay;

            DateFormat formatter;
            Date date;
            formatter = new SimpleDateFormat("yyyyMMdd");
            date = formatter.parse(strDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int nLastDayOfMonth = cal.getActualMaximum(Calendar.DATE);
            int nDay = 1;
            NgayNghiVO nnVO = null;

            //Set ngay min vao vector tham so
            vParam.add(new Parameter(new Long(strDate), true));
            //Set ngay max vao vector tham so
            if (nLastDayOfMonth < 10) {
                strDay = "0" + nLastDayOfMonth;
            } else
                strDay = "" + nLastDayOfMonth;
            vParam.add(new Parameter(new Long(strYear + strMonth + strDay),
                                     true));

            Collection collNgayNghi =
                getDSNgayNghiTrongThang(strWhere, vParam);
          if (strNgay!=null && (!strNgay.equals("")))
          {
            nDay=Integer.parseInt(strNgay);
            if(nDay <= nLastDayOfMonth)
            {
            if (nDay < 10) {
                strDay = "0" + nDay;
            } else
                strDay = "" + nDay;
            strDate = strYear + strMonth + strDay;
            date = formatter.parse(strDate);
            cal.setTime(date);
            int nDayOnWeek = cal.get(Calendar.DAY_OF_WEEK);
            nnVO = new NgayNghiVO();
            nnVO.setDay_of_week(new Long(nDayOnWeek));
            nnVO.setNgay(new Long(strDate));
            
            if (checkExsit(strDate, collNgayNghi)) {
                //Set trang thai = 1 la ngay nghi
                nnVO.setStatus("1");
            } else {
                nnVO.setStatus("0");
            }
            arrNgayTrongThang.add(nnVO);
            }
           
          } else {
            while (nDay <= nLastDayOfMonth) {
                if (nDay < 10) {
                    strDay = "0" + nDay;
                } else
                    strDay = "" + nDay;
                strDate = strYear + strMonth + strDay;
                date = formatter.parse(strDate);
                cal.setTime(date);
                int nDayOnWeek = cal.get(Calendar.DAY_OF_WEEK);
                nnVO = new NgayNghiVO();
                nnVO.setDay_of_week(new Long(nDayOnWeek));
                nnVO.setNgay(new Long(strDate));

                if (checkExsit(strDate, collNgayNghi)) {
                    //Set trang thai = 1 la ngay nghi
                    nnVO.setStatus("1");
                } else {
                    nnVO.setStatus("0");
                }
            
            
                arrNgayTrongThang.add(nnVO);

                nDay++;
            } 
          }
            return arrNgayTrongThang;
        } catch (ParseException e) {
            throw e;
        }
    }
  public int deleteNgayNghi(String id) throws Exception{
        Vector params = new Vector();
        StringBuffer sqlbuff=null;
        try {
            sqlbuff = new StringBuffer(" DELETE  sp_ngay_nghi a  WHERE a.ngay like (?)");
            params.add(new Parameter("%"+ id +"%",true));
            return executeStatement(sqlbuff.toString(), params, conn);
        } catch (Exception ex) {
          conn.rollback();
          ex.printStackTrace();
          throw new DAOException(".delete(): "+ ex.getMessage(), ex);
        }
  }    
      public String inertNgayNghi(NgayNghiVO vo) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        try {
          Long strID = getSeqNextValue("sp_ngay_nghi_seq", conn);
            if (strID !=null){
              strSQL.append("insert into sp_ngay_nghi (id ");
              strSQL2.append(" ) values ( ");
              strSQL2.append("? ");
              v_param.add(new Parameter(strID, true));
  
              if (vo.getNgay() != null && !"0".equals(vo.getNgay().toString())) {
                  strSQL.append(", ngay");
                  strSQL2.append(", ?");
                  v_param.add( new Parameter(vo.getNgay(), true));
              }
             
              if (vo.getCreated_date() != null) {
                   strSQL.append(", CREATED_DATE");
                   strSQL2.append(", ?");
                 String ngay_tao = vo.getCreated_date();
                 if (ngay_tao.length() == 10)
                    ngay_tao =ngay_tao +" 00:00:00";
                 v_param.add(new DateParameter(StringUtil.StringToDate(ngay_tao, DD_MM_YYYY_HH_MI_SS) , true));
               } 
              if (vo.getCreated_by() != null) {
                   strSQL.append(", CREATED_BY");
                   strSQL2.append(", ?");
                   v_param.add(new Parameter(vo.getCreated_by(), true));
               }
              if (vo.getMo_ta() != null) {
                   strSQL.append(", MO_TA");
                   strSQL2.append(", ?");
                   v_param.add(new Parameter(vo.getMo_ta(), true));
               }   
              strSQL.append(strSQL2.toString());
              strSQL.append(")");
  
              if (executeStatement(strSQL.toString(), v_param, conn) == 1)
                  return "1";
        } 
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DAOException( ".insert(): " +
                                   ex.getMessage(), ex);
        }
        return "-1";
    }

}

