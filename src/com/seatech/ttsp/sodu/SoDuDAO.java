package com.seatech.ttsp.sodu;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.util.Collection;
import java.util.Vector;


public class SoDuDAO extends AppDAO {
    Connection conn = null;
    private static String CLASS_NAME_DAO =
        "com.seatech.ttsp.sodu.SoDuDAO";
    private static String CLASS_NAME_VO =
        "com.seatech.ttsp.sodu.SoDuVO";

    public SoDuDAO(Connection conn) {
        this.conn = conn;
    }
    //Lay danh sach so du co phan trang
    public Collection getLstSoDu(String strWhere, Vector vParam, Integer page,
                                 Integer count,
                                 Integer[] totalCount) throws Exception {
        try {

            String strSQL =
                "select id, ma_kb, ma_nh, TO_CHAR(ngay_gd,'dd/mm/yyyy') ngay_gd, so_du, TO_CHAR(insert_date,'dd/mm/yyyy') insert_date, so_du_cot, loai_tien from sp_so_du where 1=1 ";
            strSQL += strWhere + " ORDER BY ma_nh";
            return executeSelectWithPaging(conn, strSQL.toString(), vParam,
                                           CLASS_NAME_VO, page, count,
                                           totalCount);

        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getLstSoDu(): " +
                                 ex.getMessage(), ex);
            throw daoEx;
        }
    }
    //Lay danh sach so du khong phan trang
    public Collection getLstSoDu(String strWhere,
                                 Vector vParam) throws Exception {
        try {

            String strSQL =
          "select id, ma_kb, ma_nh, TO_CHAR(ngay_gd,'dd/mm/yyyy') ngay_gd, so_du, TO_CHAR(insert_date,'dd/mm/yyyy') insert_date, so_du_cot, loai_tien from sp_so_du where 1=1 ";
            strSQL += strWhere + " ORDER BY ma_nh";
            return executeSelectStatement(strSQL.toString(), vParam,
                                          CLASS_NAME_VO, conn);

        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getLstSoDu(): " +
                                 ex.getMessage(), ex);
            throw daoEx;
        }
    }
    
      //    Insert so du

      public long insert(SoDuVO vo) throws Exception {
          long sd = 0;
          String strSQL = "";
          try {
              strSQL =
                      "insert into sp_so_du (ma_kb, ma_nh, ngay_gd, so_du, insert_date, so_du_cot, loai_tien, id, loai_tk) values (?,?, TO_DATE(?,'dd/mm/yy'), ?, SYSDATE, ?, ?, sp_so_du_seq.nextval,?)";
              PreparedStatement pstt = conn.prepareStatement(strSQL);
              pstt.setString(1, vo.getMa_kb());
              pstt.setString(2, vo.getMa_nh());
              pstt.setString(3, vo.getNgay_gd());
              pstt.setString(4, vo.getSo_du());
              pstt.setString(5, vo.getSo_du_cot());
              pstt.setString(6, vo.getLoai_tien());
              pstt.setString(7, vo.getLoai_tai_khoan());
              sd = pstt.executeUpdate();
          } catch (Exception e) {
              DAOException daoEx =
                  new DAOException(CLASS_NAME_DAO + " getLstSoDu(): " +
                                   e.getMessage(), e);
              throw daoEx;
          }
          return sd;
      }
      //Update số dư

      public long update(SoDuVO vo) throws Exception {
          long sd = 0;
          String strSQL = "";
          try {
              strSQL =
                      "update sp_so_du set so_du = ?, so_du_cot = ?, insert_date = SYSDATE  where ma_kb =? and ma_nh = ? and ngay_gd = to_date(?,'dd/mm/yyyy') and loai_tien = ?";
              PreparedStatement pstt = conn.prepareStatement(strSQL);
              pstt.setString(1, vo.getSo_du());
              pstt.setString(2, vo.getSo_du_cot());
              pstt.setString(3, vo.getMa_kb());
              pstt.setString(4, vo.getMa_nh());
              pstt.setString(5, vo.getNgay_gd());
              pstt.setString(6, vo.getLoai_tien());
              sd = pstt.executeUpdate();
          } catch (Exception ex) {
              throw new Exception(ex.getMessage() + "error at : " + strSQL);
          }
          return sd;
      }
      
      //xóa số dư
      public long delete(SoDuVO vo) throws Exception {
          long sd = 0;
          String strSQL = "";
          try {
              strSQL = "delete from sp_so_du where ma_kb = ? and ma_nh = ? and ngay_gd = to_date(?, 'dd/mm/yyyy') and loai_tien = ?";
              PreparedStatement pstt = conn.prepareStatement(strSQL);
              pstt.setString(1, vo.getMa_kb());
              pstt.setString(2, vo.getMa_nh());
              pstt.setString(3, vo.getNgay_gd());
              pstt.setString(4, vo.getLoai_tien());

              sd = pstt.executeUpdate();
          } catch (Exception e) {
              throw new Exception(e.getMessage() + "error at : " + strSQL);
          }
          return sd;
      }
    }
