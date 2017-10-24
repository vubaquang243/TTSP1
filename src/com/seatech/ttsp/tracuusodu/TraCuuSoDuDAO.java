package com.seatech.ttsp.tracuusodu;

import com.seatech.framework.datamanager.AppDAO;

import java.io.PrintStream;

import java.sql.Connection;

import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

public class TraCuuSoDuDAO extends AppDAO {
    Connection conn;

    private String CLASS_NAME_VO = "com.seatech.ttsp.tracuusodu.TraCuuSoDuVO";

    public TraCuuSoDuDAO(Connection conn) {
        this.conn = conn;
    }

    public Collection traCuuSoDuList(String strWhere, Vector vParams,
                                     Integer page, Integer count,
                                     Integer[] totalCount) throws Exception {
        try {
            String strQuery =
                "select DISTINCT h.ma_nh ma_kb, c.ten ten_kb, b.ma_nh, b.ten ten_nh, TO_CHAR(d.ngay_gd,'dd/mm/yyyy') ngay_gd, " +
                "d.so_du, d.loai_tien, TO_CHAR(d.insert_date,'dd/mm/yyyy') insert_date, d.so_du_cot, a.han_muc_no, a.loai_tk, e.ten ten_kb_tinh " +
                "from sp_tknh_kb a, sp_dm_ngan_hang b, sp_dm_htkb c, sp_dm_manh_shkb h, sp_so_du d, sp_dm_htkb e " +
                "where a.nh_id = b.id and a.kb_id = c.id and d.ma_nh = b.ma_nh " +
                "and c.ma = h.shkb and d.ma_kb = h.ma_nh " +
                "and c.ma_cha = e.ma ";
            if (!strWhere.equals(""))
                strQuery += strWhere;
            strQuery += " order by h.ma_nh ";
            //System.out.print(strQuery);
            return executeSelectWithPaging(conn, strQuery, vParams,
                                           CLASS_NAME_VO, page, count,
                                           totalCount);
        } catch (Exception e) {
            throw e;
        }
    }

    public Collection getNHKBTinh(String strWhere,
                                  Vector vParams) throws Exception {
        try {
            String strQuery = "select ma, ten from sp_dm_htkb where cap = 5 OR cap = 1 order by ma asc";
            if (strWhere != "")
                strQuery += strWhere;
            return executeSelectStatement(strQuery, vParams, CLASS_NAME_VO,
                                          conn);
        } catch (Exception e) {
          throw e;
        }
    }

    public Collection getNHKBHuyen(String strWhere,
                                   Vector vParams) throws Exception {
        try {
            String strQuery = "select ma, ten from sp_dm_htkb where cap = 3 ";
            if (strWhere != "") {
                strQuery += strWhere;
            }
            return executeSelectStatement(strQuery, vParams, CLASS_NAME_VO,
                                          conn);
        } catch (Exception e) {
          throw e;
        }
    }

    public Collection getdmNganHang(String strWhere,
                                    Vector vParams) throws Exception {
        try {
            String strQuery =
                "select DISTINCT b.ma_nh, b.ten ten_nh " + 
                "from sp_tknh_kb a, sp_dm_ngan_hang b, sp_dm_htkb c, sp_dm_manh_shkb h, sp_so_du d " +
                "where a.nh_id = b.id and a.kb_id = c.id and d.ma_nh = b.ma_nh " +
                "and c.ma = h.shkb and d.ma_kb = h.ma_nh ";
            if (strWhere != "") {
                strQuery += strWhere;
            }
            return executeSelectStatement(strQuery, vParams, CLASS_NAME_VO,
                                          conn);
        } catch (Exception e) {
            throw e;
        }
    }

    public Collection getLoaiTien(String strWhere,
                                  Vector vParams) throws Exception {
        try {
            String strQuery =
                "select DISTINCT d.loai_tien " + 
                "from sp_tknh_kb a, sp_dm_ngan_hang b, sp_dm_htkb c, sp_dm_manh_shkb h, sp_so_du d " +
                "where a.nh_id = b.id and a.kb_id = c.id and d.ma_nh = b.ma_nh " +
                "and c.ma = h.shkb and d.ma_kb = h.ma_nh ";
            if (strWhere != "")
                strQuery += strWhere;
            return executeSelectStatement(strQuery, vParams, CLASS_NAME_VO,
                                          conn);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public int deleteRecord(String strWhere, Vector vParams)throws Exception{
        int iReturn = 0;
        try{
            String strQuery = "Delete sp_so_du where ";
            strQuery += strWhere;
            iReturn= executeStatement (strQuery, null, conn);
        }catch(Exception e){
          throw e;
        }
        return iReturn;
        }
}
