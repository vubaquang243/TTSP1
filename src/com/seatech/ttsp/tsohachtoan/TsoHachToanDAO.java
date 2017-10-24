package com.seatech.ttsp.tsohachtoan;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.util.Collection;
import java.util.Vector;


public class TsoHachToanDAO extends AppDAO {
    Connection conn = null;
    private static String CLASS_NAME_DAO =
        "com.seatech.ttsp.tsohachtoan.TsoHachToanDAO";
    private static String CLASS_NAME_VO =
        "com.seatech.ttsp.tsohachtoan.TsoHachToanVO";

    public TsoHachToanDAO(Connection conn) {
        this.conn = conn;
    }
    //Lay danh sach tham số hạch toán có phân trang
    public Collection getLstTso(String strWhere, Vector vParam, Integer page,
                                 Integer count,
                                 Integer[] totalCount) throws Exception {
        try {

            String strSQL =
                "select id, ma_nh, loai_htoan, mo_ta, tktn, quy, dvsdns, cap_ns, chuong, nganh_kt, ndkt, dbhc, ctmt, nguon, ma_kb, du_phong, ghi_chu from sp_dm_tso_htoan where 1=1";
            strSQL += strWhere + " ORDER BY ma_nh";
            return executeSelectWithPaging(conn, strSQL.toString(), vParam,
                                           CLASS_NAME_VO, page, count,
                                           totalCount);

        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getLstTso(): " +
                                 ex.getMessage(), ex);
            throw daoEx;
        }
    }
    //Lấy danh sách tham số hạch toán không phân trang
    public Collection getLstTso(String strWhere,
                                 Vector vParam) throws Exception {
        try {

            String strSQL =
                "select id, ma_nh, loai_htoan, mo_ta, tktn, quy, dvsdns, cap_ns, chuong, nganh_kt, ndkt, dbhc, ctmt, nguon, ma_kb, du_phong, ghi_chu from sp_dm_tso_htoan where 1=1";
            strSQL += strWhere + " ORDER BY ma_nh";
            return executeSelectStatement(strSQL.toString(), vParam,
                                          CLASS_NAME_VO, conn);

        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getLstTso(): " +
                                 ex.getMessage(), ex);
            throw daoEx;
        }
    }
    
  //    Insert so du

  public long insert(TsoHachToanVO vo) throws Exception {
      long ts = 0;
      String strSQL = "";
      try {
          strSQL =
                  "insert into sp_dm_tso_htoan(ma_nh, loai_htoan, mo_ta, tktn, quy, dvsdns, cap_ns, chuong, nganh_kt, ndkt, dbhc, ctmt, nguon, ma_kb, du_phong, ghi_chu, id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sp_dm_tso_htoan_seq.nextval) ";
          PreparedStatement pstt = conn.prepareStatement(strSQL);
          pstt.setString(1, vo.getMa_nh());
          pstt.setString(2, vo.getLoai_htoan());
          pstt.setString(3, vo.getMo_ta());
          pstt.setString(4, vo.getTktn());
          pstt.setString(5, vo.getQuy());
          pstt.setString(6, vo.getDvsdns());
          pstt.setString(7, vo.getCap_ns());
          pstt.setString(8, vo.getChuong());
          pstt.setString(9, vo.getNganh_kt());
          pstt.setString(10,vo.getNdkt());
          pstt.setString(11,vo.getDbhc());
          pstt.setString(12,vo.getCtmt());
          pstt.setString(13,vo.getNguon());
          pstt.setString(14,vo.getMa_kb());
          pstt.setString(15,vo.getDu_phong());
          pstt.setString(16,vo.getGhi_chu());
          ts = pstt.executeUpdate();
      } catch (Exception e) {
          DAOException daoEx =
              new DAOException(CLASS_NAME_DAO + " getLstTso(): " +
                               e.getMessage(), e);
          throw daoEx;
      }
      return ts;
  }
      //Update số dư

      public long update(TsoHachToanVO vo) throws Exception {
          long ts = 0;
          String strSQL = "";
          try {
              strSQL =
            "update sp_dm_tso_htoan set ma_nh =?, loai_htoan = ?, mo_ta = ?, tktn = ?, quy = ?, dvsdns = ?, cap_ns = ?, chuong = ?, nganh_kt = ?, ndkt = ?, dbhc = ?, ctmt = ?, nguon = ?, ma_kb = ?, du_phong = ?, ghi_chu = ? where id = ?";
              PreparedStatement pstt = conn.prepareStatement(strSQL);
              pstt.setString(1, vo.getMa_nh());
              pstt.setString(2, vo.getLoai_htoan());
              pstt.setString(3, vo.getMo_ta());
              pstt.setString(4, vo.getTktn());
              pstt.setString(5, vo.getQuy());
              pstt.setString(6, vo.getDvsdns());
              pstt.setString(7, vo.getCap_ns());
              pstt.setString(8, vo.getChuong());
              pstt.setString(9, vo.getNganh_kt());
              pstt.setString(10,vo.getNdkt());
              pstt.setString(11,vo.getDbhc());
              pstt.setString(12,vo.getCtmt());
              pstt.setString(13,vo.getNguon());
              pstt.setString(14,vo.getMa_kb());
              pstt.setString(15,vo.getDu_phong());
              pstt.setString(16,vo.getGhi_chu());
              pstt.setString(17,vo.getId()); 
              ts = pstt.executeUpdate();
          } catch (Exception ex) {
              throw new Exception(ex.getMessage() + "error at : " + strSQL);
          }
          return ts;
      }
      //xóa số dư
      public long delete(TsoHachToanVO vo) throws Exception {
          long ts = 0;
          String strSQL = "";
          try {
              strSQL = "delete from sp_dm_tso_htoan where id = ?";
              PreparedStatement pstt = conn.prepareStatement(strSQL);
              pstt.setString(1, vo.getId());

              ts = pstt.executeUpdate();
          } catch (Exception e) {
              throw new Exception(e.getMessage() + "error at : " + strSQL);
          }
          return ts;
      }
      }
