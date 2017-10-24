package com.seatech.ttsp.qlyTyGia;

import com.seatech.framework.datamanager.AppDAO;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.util.List;
import java.util.Vector;

public class TyGiaDAO extends AppDAO {
    private static final String CLASS_NAME_LAISUATVO = "com.seatech.ttsp.qlyTyGia.TyGiaVO";
    
    private Connection conn;
    
    public TyGiaDAO(Connection conn){
        this.conn = conn;
    }
//

    public List listTyGia(Vector params) throws Exception {
        StringBuilder query = new StringBuilder();
        query.append("/* Formatted on 3/12/2015 3:39:07 PM (QP5 v5.126) */ " + 
        "  SELECT  TO_CHAR (b.dt, 'DD/MM/YYYY') ngay_gd, " + 
        "          a.shkb, " + 
        "          a.ma_kb, " + 
        "          a.ma_nt, " + 
        "          DECODE (a.ty_gia, NULL, 0, a.ty_gia) ty_gia, " + 
        "          a.mo_ta, " + 
        "          a.ma_nh " + 
        "    FROM  (SELECT  * " + 
        "             FROM  sp_ty_gia a " + 
        "            WHERE      a.ma_kb = ? " + 
        "                   AND a.ma_nh = ? " + 
        "                   AND a.ma_nt = ? " + 
        "                   AND a.ngay_gd >= TO_DATE (?, 'DD/MM/YYYY') " + 
        "                   AND a.ngay_gd <= TO_DATE (?, 'DD/MM/YYYY')) a, " + 
        "          (    SELECT  TRUNC (TO_DATE (?, 'DD/MM/YYYY') - 1 + ROWNUM) dt " + 
        "                 FROM  dual " + 
        "           CONNECT BY  ROWNUM <= (TRUNC (TO_DATE (?, 'DD/MM/YYYY')) - TRUNC (TO_DATE (?, 'DD/MM/YYYY'))) + 1) b " + 
        "   WHERE  a.ngay_gd(+) = b.dt ");
        query.append(" ORDER BY  b.dt, a.ngay_gd, a.ma_nt ASC ");
        return (List)executeSelectStatement(query.toString(), params, CLASS_NAME_LAISUATVO);
    }
    
    public void deleteTyGia(String condition, Vector params) throws Exception {
        StringBuilder query = new StringBuilder();
        query.append(" DELETE sp_ty_gia where 1 = 1 ");
        query.append(condition);
        executeStatement(query.toString() , params, conn);
    }

    public void insertTyGia(List<TyGiaVO> list) throws Exception {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO sp_ty_gia(shkb, ma_kb, ngay_gd, ma_nt, ty_gia, mo_ta, ma_nh,nguoi_tao,loai_ty_gia)  VALUES (?,?,to_date(?,'DD/MM/YYYY'),?,?,?,?,?,?) ");
        for (TyGiaVO vo : list) {
            ps.setObject(1, vo.getShkb());
            ps.setObject(2, vo.getMa_kb());
            ps.setObject(3, vo.getNgay_gd());
            ps.setObject(4, vo.getMa_nt());
            ps.setObject(5, vo.getTy_gia());
            ps.setObject(6, vo.getDescription());
            ps.setObject(7, vo.getMa_nh());
            ps.setObject(8, vo.getNguoi_tao());
            ps.setObject(9, vo.getLoai_ty_gia());
            ps.addBatch();
        }
        ps.executeBatch();
        ps.close();
    }
}
