package com.seatech.ttsp.bketinhlai;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.exception.DAOException;

import com.seatech.ttsp.bketinhlai_ngoaite.BKeTinhLaiNgoaiTeVO;

import com.seatech.ttsp.bketinhphi.BKeTinhPhiDAO;
import com.seatech.ttsp.bketinhphi.BKeTinhPhiVO;

import java.sql.Connection;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * @modify: thuongdt
 * @modify-date: 05/12/2017
 * @see: sua cau querry lay max id dam bao nhan 070 khong bi trung du lieu
 * @find: 20171205
 * */
public class BKeTinhLaiDAO extends AppDAO {
    Connection conn = null;
    private String  bkeTinhLaiVO = "com.seatech.ttsp.bketinhlai.BKeTinhLaiVO";
    private static final String  BKETINHLAINGOAITEVO = "com.seatech.ttsp.bketinhlai_ngoaite.BKeTinhLaiNgoaiTeVO";

    public BKeTinhLaiDAO(Connection conn) {
        this.conn = conn;
    }
    public Collection getListBKeTinhLai_PTrang(String strWhere, Vector vParam,
                                               Integer page, Integer count,
                                               Integer[] totalCount) throws Exception {

        Collection reval = null;
        try {

            String strSQL = "";
            //20171205
            strSQL +=
                    "SELECT a.id, a.mt_id, a.send_bank, a.reveive_bank, a.send_date," +
                    " a.creator, a.create_date, a.manager, a.verified_date, to_char(a.ngay,'DD/MM/YYYY') ngay," +
                    " a.sodu_cuoingay, a.lai_suat, a.lai, a.ghi_chu, a.msg_id," +
                    " a.insert_date, a.so_tk" +
                    " from sp_bke_tinh_lai a where id in (select max(a.id)  FROM sp_bke_tinh_lai a , sp_dm_manh_shkb b, sp_dm_htkb c" +
                    " WHERE		b.shkb = c.ma and a.reveive_bank = b.ma_nh AND a.ma_nt_goc = 'VND'"; // chinh 'ma_nt_goc' should be 'vnd'
            strSQL += strWhere + " group by a.SEND_BANK,a.REVEIVE_BANK,a.NGAY,a.MA_NT,a.SO_TK) order by a.ngay desc ";
            return executeSelectWithPaging(conn, strSQL.toString(), vParam,
                                            bkeTinhLaiVO, page, count,
                                           totalCount);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException( bkeTinhLaiVO + ".getListTKNHKB(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
        //        return reval;
    }

    public BKeTinhLaiVO getSumBKeLai(String strWhere,
                                     Vector vParam) throws Exception {


        try {

            String strSQL = "";

            strSQL +=
                    "SELECT sum(sodu_cuoingay) sum_sdu, sum(lai) sum_lai " +
                    "  FROM sp_bke_tinh_lai a , sp_dm_manh_shkb b, sp_dm_htkb c" +
                    " WHERE   b.shkb = c.ma and a.reveive_bank=b.ma_nh AND a.ma_nt_goc = 'VND'"; // chinh 'ma_nt_goc' should be 'vnd'
            strSQL += strWhere;

            BKeTinhLaiVO bkVO =
                (BKeTinhLaiVO)findByPK(strSQL.toString(), null,  bkeTinhLaiVO,
                                       conn);
            return bkVO;
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException( bkeTinhLaiVO + ".getSumBKeLai(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }

    }
    
    
  public BKeTinhLaiVO getTSoBCao(String strWhere,
                                   Vector vParam) throws Exception {


      try {

          String strSQL = "";

          strSQL +=
                  " SELECT distinct b.ten ten_kb, d.ten ten_nh, a.send_bank, a.reveive_bank,  a.lai_suat,  a.so_tk, e.loai_tk" + 
                  "	 FROM   sp_bke_tinh_lai a, sp_dm_manh_shkb b, sp_dm_htkb c, sp_dm_ngan_hang d, sp_tknh_kb e " + 
                    "	WHERE b.shkb = c.ma AND a.reveive_bank = b.ma_nh and a.so_tk=e.so_tk AND a.ma_nt_goc = 'VND' " +  // chinh 'ma_nt_goc' should be 'vnd'
                    " and a.send_bank=d.ma_nh " + strWhere;

          BKeTinhLaiVO bkVO =
              (BKeTinhLaiVO)findByPK(strSQL.toString(), null,  bkeTinhLaiVO,
                                     conn);
          return bkVO;
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException( bkeTinhLaiVO + ".getTSoBCao(): " +
                               ex.getMessage(), ex);
//          daoEx.printStackTrace();
          throw daoEx;
      }

  }

    public List getListBKeTinhLaiNgoaiTe_PTrang(String condition,
                                                Vector vParam,
                                                Integer currentPage,
                                                Integer numberRowOnPage,
                                                Integer[] totalCount) throws Exception {
        String strSQL = 
              "  SELECT  DISTINCT a.mt_id, a.msg_id, " + 
              "                   b.ten ten_kb, " + 
              "                   c.ten ten_nh, " + 
              "                   to_char(a.insert_date,'DD/MM/YYYY') insert_date , " + 
              "                   a.so_tk, " + 
              "                   a.ky_lai, " + 
              "                   a.ma_nt_goc " + 
              "    FROM  sp_bke_tinh_lai a, sp_dm_manh_shkb b, sp_dm_ngan_hang c " + 
              "   WHERE      a.reveive_bank = b.ma_nh " + 
              "          AND a.send_bank = c.ma_nh " + 
              "          AND a.ma_nt_goc != 'VND' " + condition + " ORDER BY  to_date(insert_date,'DD/MM/YYYY') DESC ";
        return (List)executeSelectWithPaging(conn, strSQL.toString(), vParam, BKETINHLAINGOAITEVO, currentPage, numberRowOnPage, totalCount);
    }

    public BKeTinhLaiNgoaiTeVO findBKeLai(Vector vParam) throws Exception {
        String sql = 
        "  SELECT  DISTINCT a.mt_id, " + 
        "                   a.reveive_bank, " + 
        "                   a.so_tk, " + 
        "                   a.send_bank, MAX (a.ngay) " + 
        "    FROM  sp_bke_tinh_lai a " + 
        "   WHERE  a.mt_id = ?  " + 
        "GROUP BY  a.mt_id, " + 
        "          a.reveive_bank, " + 
        "          a.so_tk, " + 
        "          a.send_bank " + 
        "ORDER BY  MAX (a.ngay) ";
        return (BKeTinhLaiNgoaiTeVO)findByPK(sql, vParam, BKETINHLAINGOAITEVO  , conn);
    }
}
