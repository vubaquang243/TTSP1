package com.seatech.ttsp.dchieu;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;

import java.sql.ResultSet;

import java.text.SimpleDateFormat;

import java.util.Collection;
import java.util.Date;
import java.util.Vector;

/*
 * @modify: Thuongdt
 * @modify date: 17/11/2016
 * @see: sua ket qua tra cuu tai ham: getDChieu3_tongDC
 * */

/*
 * @modify: Thuongdt
 * @modify date: 07/06/2017
 * @see: cau query : getDChieu3_tongDC,getDChieu3List_PTrang
 * */
public class DChieu3DAO extends AppDAO {


    String strValueObjectVO = "com.seatech.ttsp.dchieu.DChieu3VO";
    String strValueObjectVO2 = "com.seatech.ttsp.dchieu.thopDCQTTQuocVO";
    Connection conn = null;

    public DChieu3DAO(Connection conn) {
        this.conn = conn;
    }


    public Collection getDChieu3List(String strWhere,
                                     Vector vParam) throws Exception {

        Collection reval = null;
        try {

            String strSQL = "";
            strSQL +=
                    "  SELECT a.id, c.id kq_id, to_char(a.ngay_dc,'dd/MM/yyyy') as ngay_dc, a.send_bank, a.receive_bank, " + 
                    " a.creator, a.created_date, a.manager, a.verified_date, c.tthai_ttin, c.ket_qua, " + 
                    " a.sodu_daungay, a.tong_thu, a.tong_chi, a.so_du_cuoi_ngay,  " + 
                    " a.msg_id, a.trang_thai, a.lan_dc, to_char(a. ngay_thien_dc,'dd/MM/yyyy') as ngay_thien_dc,c.trang_thai trang_thai_kq " + 
                    " FROM sp_bk_dc3 a, sp_kq_dc3 c" + 
                    "	WHERE   a.id = c.bk_id(+)";

            strSQL +=
                    strWhere + " ORDER BY   a.send_bank, a.ngay_dc, a.lan_dc DESC";
            reval =
                    executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO,
                                           conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectVO + ".getDChieu3List(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }
	//ThuongDT-20161108-Sua dap ung doi chieu lan 3 tu dong-begin
// Lay Danh sach doi chieu da dc phan trang
  public Collection getDChieu3List_PTrang(String strWhere,String ngay_dc,String loaitien, String loai_tien,
                                   Vector vParam,
     Integer page, Integer count,
     Integer[] totalCount) throws Exception {

      Collection reval = null;
//    " decode(e.trang_thai,null,'Chưa có bảng kê','�?ã có bảng kê') tt_bk," + 
//    " decode(e.trang_thai,'00','Chưa đối chiếu','01','Chênh lệch','02','Khớp đúng','Chưa có') tt_dc1," + 
//    " decode(f.ket_qua_pht,'0','Chưa đối chiếu','1','Chênh lệch','2','Khớp đúng','Chưa có') tt_dc_pht," + 
//    " decode(g.trang_thai,'01','Ch�? duyệt','02','�?ã duyệt','03','Hủy','04','Gửi NH thành công','05','Gửi NH không thành công','Chưa có') tt_dnqt" + 
    
      try {
        String strSQL = "";
//        strSQL +=
//                " select  MA_KB, bi_danh,  ten_huyen,ma_nh, ma_nhkb," + 
//            " decode(e.trang_thai,null,'Chưa có bảng kê','Đã có bảng kê') tt_bk," + 
//           // " decode(e.trang_thai,'00','Chưa đối chiếu','01','Chênh lệch','02','Khớp đúng','Chưa có') tt_dc1," + 
//            " decode(loai_gd," + 
//            " '02',decode(e.trang_thai,'00','Chưa đối chiếu','01','Chênh lệch','02','Khớp đúng','Chưa có'), " + 
//            " '01',decode(e.trang_thai,'00','Chưa đối chiếu','01','Chênh lệch','02','Khớp đúng','Chưa có'),' ') tt_dc1,"+  
//           // " decode(f.ket_qua_pht,'0','Chưa đối chiếu','1','Chênh lệch','2','Khớp đúng','Chưa có') tt_dc_pht," + 
//            " decode(loai_gd, " + 
//            " '03',decode(f.ket_qua_pht,'0','Chưa đối chiếu','1','Chênh lệch','2','Khớp đúng','Chưa có'), " + 
//            " '01',decode(f.ket_qua_pht,'0','Chưa đối chiếu','1','Chênh lệch','2','Khớp đúng','Chưa có'),' ' ) tt_dc_pht,"+
//            " decode(g.trang_thai,'01','Chờ duyệt','02','Đã duyệt','03','Hủy','04','Gửi NH thành công','05','Gửi NH không thành công','06','Hết hiệu lực','Chưa có') tt_dnqt, " + 
//            " decode(g.trang_thai_qtoan,'01','Chưa quyết toán','02','Quyết toán thiếu','03','Quyết toán đủ','Chưa có')tt_qtoan"+    
//            " from " + 
//                " (select DISTINCT c.ma MA_KB,(select bi_danh from sp_dm_nh_ho where SUBSTR(b.ma_nh,3,3) = ma_dv and ROWNUM = 1) bi_danh, c.ten ten_huyen,b.ma_nh,h.ma_nh ma_nhkb," + 
//                " c.id_cha,c.id,loai_gd" + //a.ma_nt
//                " from sp_tknh_kb a, sp_dm_ngan_hang b, sp_dm_htkb c, sp_dm_manh_shkb h " + 
//                " where a.nh_id = b.id and a.kb_id = c.id  and a.trang_thai ='01' " + 
//                " and c.ma = h.shkb )ht," + 
//                "(select send_bank,receive_bank,trang_thai from "+(!loaitien.equals("VND")?"sp_bk_dc1_ngoai_te":"sp_bk_dc1")+"  where id in(select max(id) from "+(!loaitien.equals("VND")?"sp_bk_dc1_ngoai_te":"sp_bk_dc1")+" where trunc(ngay_dc) = to_date('"+ngay_dc+"','dd/MM/yyyy') "+loai_tien+" group by send_bank,receive_bank) ) e, " + 
//                " (select send_bank,receive_bank,ket_qua_pht from "+(!loaitien.equals("VND")?"sp_065_ngoai_te":"sp_065")+" where id in(select max(id) from "+(!loaitien.equals("VND")?"sp_065_ngoai_te":"sp_065")+" where app_type = 'PHT' and  trunc(ngay_dc) = to_date('"+ngay_dc+"','dd/MM/yyyy') "+loai_tien+" group by send_bank,receive_bank))f, " + 
//                " (select nhkb_chuyen,nhkb_nhan,trang_thai, trang_thai_qtoan from sp_066 where trunc(ngay_qtoan) = to_date('"+ngay_dc+"','dd/MM/yyyy') "+loai_tien+" ) g " + 
//                " where 1=1" + 
//                " and ht.ma_nh = e.send_bank(+) " + 
//                " and ht.ma_nhkb = e.receive_bank(+) " + 
//                " and ht.ma_nh = f.send_bank(+)" + 
//                " and ht.ma_nhkb = f.receive_bank(+)" + 
//                " and ht.ma_nh = g.nhkb_chuyen(+) " + 
//                " and ht.ma_nhkb = g.nhkb_nhan(+)"+   
//                "and ((loai_gd = '01' and (e.trang_thai <> '02' or f.ket_qua_pht <> '2' or e.trang_thai is null or f.ket_qua_pht is null)) " + 
//                "  or (loai_gd = '02' and (e.trang_thai <> '02' or e.trang_thai is null )) " + 
//                "  or (loai_gd = '03' and (f.ket_qua_pht <> '2' or f.ket_qua_pht is null))  " + 
//                "  or (g.trang_thai_qtoan <> '02' or g.trang_thai_qtoan is null ))";
//              strSQL += strWhere + " order by bi_danh";   
          
        strSQL +=
                " select  MA_KB, bi_danh,  ten_huyen,ma_nh, ma_nhkb, " + 
            " decode(trang_thai,null,'Chưa có bảng kê','Đã có bảng kê') tt_bk," + 
            " decode(loai_gd, '02',decode(trang_thai,'00','Chưa đối chiếu','01','Chênh lệch','02','Khớp đúng','Chưa có'),  '01',\n" + 
            " decode(trang_thai,'00','Chưa đối chiếu','01','Chênh lệch','02','Khớp đúng','Chưa có'),' ') tt_dc1,"+  
            " decode(loai_gd,  '03',decode(ket_qua_pht,'0','Khớp đúng','1','Chênh lệch','2','Chênh lệch số dư','Chưa có'),  '01',\n" + 
            " decode(ket_qua_pht,'0','Khớp đúng','1','Chênh lệch','2','Chênh lệch số dư','Chưa có'),' ' ) tt_dc_pht,"+
            " decode(trang_thai_qt,'01','Chờ duyệt','02','Đã duyệt','03','Hủy','04','Gửi NH thành công','05','Gửi NH không thành công','06','Hết hiệu lực','Chưa có') tt_dnqt, " + 
            " decode(trang_thai_qtoan_qt,'00','Chưa quyết toán','01','Quyết toán thiếu','02','Quyết toán đủ','Chưa có')tt_qtoan,"+   
            " decode(tthai_dxn_thop,'00','Chưa tạo điện xác nhận','01','Chờ KS','02','Đã tạo điện 066','03','Đã xác nhận QT ngày lỗi','04','Hủy',' ') tthai_dxn_thop"+
            " from " + 
                " (select DISTINCT c.ma MA_KB,(select bi_danh from sp_dm_nh_ho where SUBSTR(b.ma_nh,3,3) = ma_dv and ROWNUM = 1) bi_danh, c.ten ten_huyen,b.ma_nh,h.ma_nh ma_nhkb," + 
                " c.id_cha,c.id,loai_gd" + //a.ma_nt
                " from sp_tknh_kb a, sp_dm_ngan_hang b, sp_dm_htkb c, sp_dm_manh_shkb h " + 
                " where a.nh_id = b.id and a.kb_id = c.id  and a.trang_thai ='01' " + 
                " and c.ma = h.shkb )ht," +                 
                " (select  e.send_bank,e.receive_bank, e.trang_thai,f.ket_qua ket_qua_pht,g.trang_thai trang_thai_qt,g.trang_thai_qtoan trang_thai_qtoan_qt,tthai_dxn_thop from "+                
                "(select send_bank,receive_bank,trang_thai,id from "+(!loaitien.equals("VND")?"sp_bk_dc1_ngoai_te":"sp_bk_dc1")+"  where id in(select max(id) from "+(!loaitien.equals("VND")?"sp_bk_dc1_ngoai_te":"sp_bk_dc1")+" where trunc(ngay_dc) = to_date('"+ngay_dc+"','dd/MM/yyyy') "+loai_tien+" group by send_bank,receive_bank) ) e, " + 
                " (select send_bank,receive_bank,ket_qua,id,bk_id,tthai_dxn_thop from "+(!loaitien.equals("VND")?"sp_065_ngoai_te":"sp_065")+" where id in(select max(id) from "+(!loaitien.equals("VND")?"sp_065_ngoai_te":"sp_065")+" where  trunc(ngay_dc) = to_date('"+ngay_dc+"','dd/MM/yyyy') "+loai_tien+" group by send_bank,receive_bank,bk_id))f, " + 
                " (select nhkb_chuyen,nhkb_nhan,trang_thai, trang_thai_qtoan,kq_dc_ttsp from sp_066 where id in (select max(id) from sp_066 where trunc(ngay_qtoan) = to_date('"+ngay_dc+"','dd/MM/yyyy') "+loai_tien+" group by nhkb_chuyen,nhkb_nhan,kq_dc_ttsp)) g " +                 
                " where  e.id = f.bk_id(+) and f.id = g.kq_dc_ttsp(+) ) kq "+                
                " where 1=1" + 
                " and ht.ma_nh = kq.send_bank(+)  and ht.ma_nhkb = kq.receive_bank(+) ";                  
               // " and ((loai_gd = '01' and (trang_thai <> '02' or ket_qua_pht <> '0' or trang_thai is null or ket_qua_pht is null)) " + 
               // "  or (loai_gd = '02' and (trang_thai <> '02' or trang_thai is null )) " + 
               // "  or (loai_gd = '03' and (ket_qua_pht <> '0' or ket_qua_pht is null))  " + 
               // "  or (trang_thai_qtoan_qt <> '02' or trang_thai_qtoan_qt is null )) ";
              strSQL += strWhere + " order by bi_danh";   
        reval = executeSelectWithPaging(conn, strSQL.toString(), vParam,
                                        strValueObjectVO2, page, count,
                                       totalCount);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(strValueObjectVO + ".getDChieu3List_PTrang(): " +
                               ex.getMessage(), ex);
  //            daoEx.printStackTrace();
          throw daoEx;
      }
      return reval;
  }
  
  
  /*
   * @modify: Thuongdt
   * @modify date: 17/11/2016
   * @see: sua thong tin ket qua tra ra cua tra cuu
   * */
  /*
   * @modify: Thuongdt
   * @modify date: 20/07/2017
   * @see: sua thong tin ket qua tra ra cua tra cuu
   * @find: 20170720
   * */
  public String getDChieu3_tongDC(String strWhere,String ngay_dc,String loaitien,String loai_tien, String chr,
                                   Vector vParam) throws Exception {
      SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");//dd/MM/yyyy
      String strDate = sdfDate.format(new Date());
      String strNH = "";
      ResultSet reval = null;
      String tenNH = "";
      String strBiDanh = "";
      int iSTT = 1;
      int iTongDC = 0;
      int iTongNH = 0;
      
      try {
          String strSQL = "";
          String strSQL1 = "";
          String strSQL2 = "";
          strSQL1 =" select count(1) tong,bi_danh,ten_nh from (" +
              " select  MA_KB, bi_danh,  ten_huyen,ma_nh,ten_nh, ma_nhkb" + 
              " from " +
              " (select DISTINCT c.ma MA_KB,(select bi_danh from sp_dm_nh_ho where SUBSTR(b.ma_nh,3,3) = ma_dv and ROWNUM = 1) bi_danh,(select ten_nh from sp_dm_nh_ho where SUBSTR(b.ma_nh,3,3) = ma_dv and ROWNUM = 1) ten_nh, c.ten ten_huyen,b.ma_nh,h.ma_nh ma_nhkb," + 
              " c.id_cha,c.id" + //a.ma_nt
              " from sp_tknh_kb a, sp_dm_ngan_hang b, sp_dm_htkb c, sp_dm_manh_shkb h " + 
              " where a.nh_id = b.id and a.kb_id = c.id  and a.trang_thai ='01' " + 
              " and c.ma = h.shkb )ht," + 
              " (select  e.send_bank,e.receive_bank, e.trang_thai,f.ket_qua ket_qua_pht,g.trang_thai trang_thai_qt,g.trang_thai_qtoan trang_thai_qtoan_qt from "+                
              "(select send_bank,receive_bank,trang_thai,id from "+(!loaitien.equals("VND")?"sp_bk_dc1_ngoai_te":"sp_bk_dc1")+"  where id in(select max(id) from "+(!loaitien.equals("VND")?"sp_bk_dc1_ngoai_te":"sp_bk_dc1")+" where trunc(ngay_dc) = to_date('"+ngay_dc+"','dd/MM/yyyy') "+loai_tien+" group by send_bank,receive_bank) ) e, " + 
              " (select send_bank,receive_bank,ket_qua,id,bk_id from "+(!loaitien.equals("VND")?"sp_065_ngoai_te":"sp_065")+" where id in(select max(id) from "+(!loaitien.equals("VND")?"sp_065_ngoai_te":"sp_065")+" where  trunc(ngay_dc) = to_date('"+ngay_dc+"','dd/MM/yyyy') "+loai_tien+" group by send_bank,receive_bank,bk_id))f, " + 
              " (select nhkb_chuyen,nhkb_nhan,trang_thai, trang_thai_qtoan,kq_dc_ttsp from sp_066 where id in (select max(id) from sp_066 where trunc(ngay_qtoan) = to_date('"+ngay_dc+"','dd/MM/yyyy') "+loai_tien+" group by nhkb_chuyen,nhkb_nhan,kq_dc_ttsp)) g " +                 
              " where  e.id = f.bk_id(+) and f.id = g.kq_dc_ttsp(+) ) kq "+     
              
              " where 1=1" + 
              "  and ht.ma_nh = kq.send_bank(+)  and ht.ma_nhkb = kq.receive_bank(+) " + 
              strWhere +      
              ") group by bi_danh,ten_nh ";
        strSQL2 =
                "select count(1) tong, bi_danh from(" +
                " select  MA_KB, bi_danh,  ten_huyen,ma_nh, ma_nhkb" + 
                " from " +
                " (select DISTINCT c.ma MA_KB,(select bi_danh from sp_dm_nh_ho where SUBSTR(b.ma_nh,3,3) = ma_dv and ROWNUM = 1) bi_danh, c.ten ten_huyen,b.ma_nh,h.ma_nh ma_nhkb," + 
                " c.id_cha, c.id, loai_gd" + //a.ma_nt
                " from sp_tknh_kb a, sp_dm_ngan_hang b, sp_dm_htkb c, sp_dm_manh_shkb h " + 
                " where a.nh_id = b.id and a.kb_id = c.id  and a.trang_thai ='01' " + 
                " and c.ma = h.shkb )ht," + 
                
        " (select  e.send_bank,e.receive_bank, e.trang_thai,f.ket_qua ket_qua_pht,g.trang_thai trang_thai_qt,g.trang_thai_qtoan trang_thai_qtoan_qt,tthai_dxn_thop from "+                
        "(select send_bank,receive_bank,trang_thai,id from "+(!loaitien.equals("VND")?"sp_bk_dc1_ngoai_te":"sp_bk_dc1")+"  where id in(select max(id) from "+(!loaitien.equals("VND")?"sp_bk_dc1_ngoai_te":"sp_bk_dc1")+" where trunc(ngay_dc) = to_date('"+ngay_dc+"','dd/MM/yyyy') "+loai_tien+" group by send_bank,receive_bank) ) e, " + 
        " (select send_bank,receive_bank,ket_qua,id,bk_id,tthai_dxn_thop from "+(!loaitien.equals("VND")?"sp_065_ngoai_te":"sp_065")+" where id in(select max(id) from "+(!loaitien.equals("VND")?"sp_065_ngoai_te":"sp_065")+" where  trunc(ngay_dc) = to_date('"+ngay_dc+"','dd/MM/yyyy') "+loai_tien+" group by send_bank,receive_bank,bk_id))f, " + 
        " (select nhkb_chuyen,nhkb_nhan,trang_thai, trang_thai_qtoan,kq_dc_ttsp from sp_066 where id in (select max(id) from sp_066 where trunc(ngay_qtoan) = to_date('"+ngay_dc+"','dd/MM/yyyy') "+loai_tien+" group by nhkb_chuyen,nhkb_nhan,kq_dc_ttsp)) g " +                 
        " where  e.id = f.bk_id(+) and f.id = g.kq_dc_ttsp(+) ) kq "+                  
                
                " where 1=1" + 
                " and ht.ma_nh = kq.send_bank(+)  and ht.ma_nhkb = kq.receive_bank(+) " +                 
              
                strWhere ;
          //20170720
          if(strDate.equals(ngay_dc)){         
           strSQL2 =strSQL2+ " and ((loai_gd = '01' and trang_thai = '02' and ket_qua_pht = '0')" + 
                " or (loai_gd = '02' and trang_thai = '02') or (loai_gd = '03' and ket_qua_pht = '0'))" + 
                " and trang_thai_qt = '04' "+
                " and trang_thai_qtoan_qt = '02' ";
          }else{
            strSQL2 =strSQL2+ " and ((loai_gd = '01' and trang_thai in ('01','02') and ket_qua_pht in ('0','1'))" + 
                 " or (loai_gd = '02' and trang_thai in ('01','02')) or (loai_gd = '03' and ket_qua_pht in ('01','02')))" + 
                 //" and trang_thai_qt = '04' "+
                 //" and trang_thai_qtoan_qt in ('01','02') " +
                "and tthai_dxn_thop in ('02','03') ";   
          }       
        strSQL2 =strSQL2+ " )  group by bi_danh ";
        strSQL = "select a.tong tong_nh,a.bi_danh,a.ten_nh,decode(b.tong,null,0,b.tong) tong_dc from ("+strSQL1+") a, ("+strSQL2+") b where a.bi_danh = b.bi_danh(+) order by a.ten_nh"; 
        reval = executeSelectStatement(strSQL, vParam, conn);
        while(reval.next()){
          iTongDC = reval.getInt("tong_dc"); 
          tenNH = reval.getString("ten_nh");
          strBiDanh= reval.getString("bi_danh");
          iTongNH = reval.getInt("tong_nh"); 
          strNH += "<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+iSTT + ". "+tenNH +": "+iTongDC+"/"+iTongNH +"</b>"+chr; 
          iSTT++;   
        }
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(strValueObjectVO + ".getDChieu3_tongDC(): " +
                               ex.getMessage(), ex);
          throw daoEx;
      }
      return strNH;
  }
  	//ThuongDT-20161108-Sua dap ung doi chieu lan 3 tu dong-end

  public Collection getDuyetDChieu3List(String strWhere,
                                   Vector vParam) throws Exception {

      Collection reval = null;
      try {

          String strSQL = "";
          strSQL +=
                  " SELECT   a.id bk_id, b.id kq_id, a.lan_dc, b.tthai_ttin," + 
                  " TO_CHAR (a.ngay_dc, 'dd/MM/yyyy') AS ngay_dc, a.send_bank," + 
                  " a.receive_bank, a.creator, a.created_date, a.manager," + 
                  " a.verified_date, a.sodu_daungay, a.tong_thu, a.tong_chi," + 
                  " a.so_du_cuoi_ngay, a.trang_thai, b.ket_qua, a.trang_thai trang_thai_bk, a.msg_id," + 
                  " b.trang_thai trang_thai_kq, to_char(a.ngay_thien_dc,'dd/MM/yyyy') as ngay_thien_dc" + 
                  " FROM   sp_bk_dc3 a, sp_kq_dc3 b" + 
                  "	WHERE   a.id = b.bk_id(+) ";

          strSQL +=
                  strWhere + " ORDER BY   a.send_bank, a.ngay_dc, a.lan_dc DESC";
          reval =
                  executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO,
                                         conn);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(strValueObjectVO + ".getDChieu3List(): " +
                               ex.getMessage(), ex);
//          daoEx.printStackTrace();
          throw daoEx;
      }
      return reval;
  }





    public Collection getDChieu2List(String strWhere,
                                     Vector vParam) throws Exception {

        Collection reval = null;
        try {

            String strSQL = "";
            strSQL +=
                    " SELECT a.id, a.lan_dc, to_char(a.ngay_dc,'dd/MM/yyyy') as ngay_dc, a.send_bank, a.receive_bank,  " +
                    "       a.creator, a.created_date, a.manager, a.verified_date,  " +
                    "       a.sodu_daungay, a.tong_thu, a.tong_chi, a.so_du_cuoi_ngay,  " +
                    "       a.msg_id, a.trang_thai, b.ngay_ct, b.mt_id, b.so_tien, b.ma_kb   " +
                    "  FROM sp_bk_dc3 a inner join sp_bk_dc3_ctiet b on a.id=b.bk_id WHERE 1=1 ";

            strSQL +=
                    strWhere + " ORDER BY a.trang_thai asc , a.created_date,  a.lan_dc desc ";
            reval =
                    executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO,
                                           conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectVO + ".getDChieu3List(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }

    public Collection getKQDChieu2(String strWhere,
                                   Vector vParam) throws Exception {

        Collection reval = null;
        try {

            String strSQL = " SELECT   (SELECT   so_tien  " +
                "            FROM   sp_quyet_toan  " +
                "           WHERE       ngay_htoan =   ?  " +
                "                   AND ma_kb = ?  " +
                "                   AND ma_nh_chuyen = ?  " +
                "                   AND qtoan_dvi = 'Y'  " +
                "                   AND loai_qtoan = 'D')  " +
                "             AS mt900kbnn,  " +
                "         (SELECT   so_tien  " +
                "            FROM   sp_quyet_toan  " +
                "           WHERE       ngay_htoan =   ?  " +
                "                   AND ma_kb = ?  " +
                "                   AND ma_nh_chuyen = ?  " +
                "                   AND qtoan_dvi = 'Y'  " +
                "                   AND loai_qtoan = 'C')  " +
                "             AS mt900kbnn,  " +
                "         (SELECT   b.so_tien  " +
                "            FROM       sp_kq_dc3 a  " +
                "                   INNER JOIN  " +
                "                       sp_kq_dc3_ctiet b  " +
                "                   ON a.id = b.bk_id  " +
                "           WHERE       a.ngay_dc =   ?  " +
                "                   AND b.ma_kb = ?  " +
                "                   AND b.mt_type = '900')  " +
                "             AS mt900nhtm,  " +
                "         (SELECT   b.so_tien  " +
                "            FROM       sp_kq_dc3 a  " +
                "                   INNER JOIN  " +
                "                       sp_kq_dc3_ctiet b  " +
                "                   ON a.id = b.bk_id  " +
                "           WHERE       a.ngay_dc =   ?  " +
                "                   AND b.ma_kb = ?  " +
                "                   AND b.mt_type = '910')  " +
                "             AS mt910nhtm,  " +
                "         (SELECT   a.ps_thu  " +
                "            FROM   sp_bk_dc1 a  " +
                "           WHERE   a.app_type = 'TTSP'  " +
                "                   AND ngay_dc =  " +
                "                          (SELECT   MAX (ngay_dc)  " +
                "                             FROM   sp_bk_dc1  " +
                "                            WHERE   app_type = 'TTSP' AND trang_thai = '02')  " +
                "                   AND a.receive_bank = ?  " +
                "                   AND a.trang_thai = '02')  " +
                "             AS thukbnn,  " +
                "         (SELECT   a.ps_chi  " +
                "            FROM   sp_bk_dc1 a  " +
                "           WHERE   ROWNUM = 1 AND a.app_type = 'TTSP'  " +
                "                   AND ngay_dc =  " +
                "                          (SELECT   MAX (ngay_dc)  " +
                "                             FROM   sp_bk_dc1  " +
                "                            WHERE   app_type = 'TTSP' AND trang_thai = '02')  " +
                "                   AND a.receive_bank = ?  " +
                "                   AND a.trang_thai = '02')  " +
                "             AS chikbnn  " +
                "  FROM   DUAL WHERE 1=1 ";
            strSQL += strWhere;
            reval =
                    executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO,
                                           conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectVO + ".getDChieu3List(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }

    public int updateBKDC3(DChieu3VO vo) throws Exception {
        int exc = 0;
        Vector v_param = new Vector();
        StringBuffer sqlBuff = new StringBuffer();
        sqlBuff.append(" update SP_KQ_DC3 set ");
        if (vo.getLan_dc() != null) {
            sqlBuff.append(" LAN_DC=?,");
            v_param.add(new Parameter(vo.getLan_dc(), true));
        }
        if (vo.getNgay_dc() != null) {
            sqlBuff.append(" NGAY_DC=?,");
            v_param.add(new DateParameter(vo.getNgay_dc(), true));
        }
        if (vo.getSend_bank() != null) {
            sqlBuff.append(" SEND_BANK=?,");
            v_param.add(new Parameter(vo.getSend_bank(), true));
        }
        if (vo.getReceive_bank() != null) {
            sqlBuff.append(" RECEIVE_BANK=?,");
            v_param.add(new Parameter(vo.getReceive_bank(), true));
        }
        if (vo.getCreator() != null) {
            sqlBuff.append(" CREATOR=?,");
            v_param.add(new Parameter(vo.getCreator(), true));
        }
        if (vo.getCreated_date() != null) {
            sqlBuff.append(" CREATED_DATE=?,");
            v_param.add(new DateParameter(vo.getCreated_date(), true));
        }
        if (vo.getManager() != null) {
            sqlBuff.append(" MANAGER=?,");
            v_param.add(new Parameter(vo.getManager(), true));
        }
        if (vo.getVerified_date() != null) {
            sqlBuff.append(" VERIFIED_DATE=?,");
            v_param.add(new DateParameter(vo.getVerified_date(), true));
        }
        if (vo.getSodu_daungay() != null) {
            sqlBuff.append(" SODU_DAUNGAY=?,");
            v_param.add(new Parameter(vo.getSodu_daungay(), true));
        }
        if (vo.getTong_thu() != null) {
            sqlBuff.append(" TONG_THU=?,");
            v_param.add(new Parameter(vo.getTong_thu(), true));
        }
        if (vo.getTong_chi() != null) {
            sqlBuff.append(" TONG_CHI=?,");
            v_param.add(new Parameter(vo.getTong_chi(), true));
        }
        if (vo.getSo_du_cuoi_ngay() != null) {
            sqlBuff.append(" SO_DU_CUOI_NGAY=?,");
            v_param.add(new Parameter(vo.getSo_du_cuoi_ngay(), true));
        }
        if (vo.getMsg_id() != null) {
            sqlBuff.append(" MSG_ID=?,");
            v_param.add(new Parameter(vo.getMsg_id(), true));
        }
        if (vo.getTrang_thai() != null) {
            sqlBuff.append(" TRANG_THAI=?,");
            v_param.add(new Parameter(vo.getTrang_thai(), true));
        }
        if (vo.getNgay_thien_dc() != null) {
            sqlBuff.append(" NGAY_THIEN_DC=?");
            v_param.add(new DateParameter(new Date(), true));
        }
        if (vo.getId() != null) {
            sqlBuff.append(" WHERE ID=?");
            v_param.add(new Parameter(vo.getId(), true));
        }
        exc = executeStatement(sqlBuff.toString(), v_param, conn);

        return exc;
    }

  public DChieu3VO getMaSGD(String whereClause,
                              Vector params) throws Exception {
      try {
          String strSQL = "";
          strSQL +=
                  " SELECT a.ma_nh, a.shkb, a.ten" + 
                  "  FROM sp_dm_manh_shkb a where shkb='0003'";
          DChieu3VO vo =
              (DChieu3VO)findByPK(strSQL.toString(), params,
                                    strValueObjectVO, conn);
          return vo;
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(strValueObjectVO + ".getMaSGD(): " +
                               ex.getMessage(), ex);
//          daoEx.printStackTrace();
          throw daoEx;
      }
  }

}
