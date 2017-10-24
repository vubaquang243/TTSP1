package com.seatech.ttsp.dchieu;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;
import com.seatech.framework.utils.StringUtil;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.Collection;
import java.util.Vector;


public class DChieu2DAO extends AppDAO {
    Connection conn = null;
    private String strValueObjectVO = "com.seatech.ttsp.dchieu.DChieu2VO";
    private String strValueObjectDuyetVO =
        "com.seatech.ttsp.dchieu.DuyetXNDChieu2VO";

    public DChieu2DAO(Connection conn) {
        this.conn = conn;
    }

  public Collection getListBK(String strWhere, String nhkb_nhan,
                                 String strNHKB,
                                 Vector vParam) throws Exception {

      Collection reval = null;
      try {

          String strSQL = "";

          strSQL +=
                  "SELECT distinct to_char(b.ngay,'DD/MM/YYYY') ngay, TO_CHAR (b.ngay, 'YYYYMMDD') ngay_order, a.bk_id, b.ma_nh send_bank , a.receive_bank, to_char(a.ngay_dc,'DD-MM-YYYY') ngay_dc, " +
                  " a.trang_thai, c.trang_thai trang_thai_kq, c.tthai_ttin, c.ket_qua,  a.lan_dc,c.tthai_dmo, TO_CHAR (a.NGAY_THIEN_DC, 'DD/MM/YYYY') NGAY_THIEN_DC " + 
                  " FROM  (SELECT DISTINCT TRUNC (ngay_dc) ngay_dc, bk_id, receive_bank, send_bank, ngay_thien_dc," + 
                  " trang_thai, lan_dc FROM    sp_bk_dc2 where receive_bank='" + nhkb_nhan +"') a," + 
                  " ( SELECT   DISTINCT TRUNC (ngay_gd+1) ngay, d3.ma_nh ma_nh, d3.ten ten" + 
                  " FROM sp_tso_cutoftime d1, sp_dm_ngan_hang d2, sp_dm_ngan_hang d3" + 
                  " WHERE d1.ma_nh_kb = d2.ma_nh AND d1.ma_nh = d3.ma_nh AND d1.ma_nh_kb = '" + nhkb_nhan +"'" + 
                  " AND TRUNC (d1.ngay_gd+1) <= TRUNC (SYSDATE) " + 
                  " AND TO_CHAR (ngay_gd+1, 'YYYYMMDD') NOT IN (  SELECT   ngay FROM sp_ngay_nghi)" + 
                  " ORDER BY   ngay) b, sp_kq_Dc2 c WHERE a.ngay_dc(+) = b.ngay" + 
                  " AND a.send_bank(+) = b.ma_nh AND b.ngay IS NOT NULL AND a.bk_id=c.bk_id(+)";

          strSQL += strWhere + "ORDER BY send_bank, ngay_order, lan_dc  DESC";
          reval =
                  executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO,
                                         conn);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(strValueObjectVO + ".getDChieuList(): " +
                               ex.getMessage(), ex);
          daoEx.printStackTrace();
          throw daoEx;
      }
      return reval;
  }


  public Collection getListBKTCuu(String strWhere, String nhkb_nhan,
                                 Vector vParam) throws Exception {

      Collection reval = null;
      try {

          String strSQL = "";

          strSQL +=
                  "SELECT distinct a.bk_id, c.id, a.send_bank, a.receive_bank, to_char(a.ngay_dc,'DD-MM-YYYY') ngay_dc, a.trang_thai, c.tthai_ttin, c.trang_thai trang_thai_kq, c.ket_qua,  a.lan_dc, TO_CHAR (a.NGAY_THIEN_DC, 'DD/MM/YYYY') NGAY_THIEN_DC " + 
                  " FROM  (SELECT DISTINCT TRUNC (ngay_dc) ngay_dc, bk_id, receive_bank, send_bank, ngay_thien_dc," + 
                  " trang_thai, lan_dc FROM sp_bk_dc2 where receive_bank='" + nhkb_nhan +"') a, sp_kq_dc2 c" + 
                  " WHERE 1=1 AND a.bk_id=c.bk_id(+)";

          strSQL += strWhere ;
          reval =
                  executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO,
                                         conn);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(strValueObjectVO + ".getDChieuList(): " +
                               ex.getMessage(), ex);
          daoEx.printStackTrace();
          throw daoEx;
      }
      return reval;
  }




    public Collection getChiThu(String strWhere,
                                Vector vParam) throws Exception {

        Collection reval = null;
        try {

            String strSQL = "";

            strSQL +=
                    " SELECT a.id qtoan_id, to_char(a.ngay_htoan,'DD/MM/YYYY') ngay_htoan, to_char(a.ngay_htoan,'DD/MM/YYYY') ngay_dc, to_char(a.ngay_qtoan,'DD/MM/YYYY') ngay_qtoan, decode(a.loai_qtoan,'C','Chi','D','Thu') loai_qtoan, a.nhkb_chuyen, a.nhkb_nhan, a.so_tien, a.lai_phi" +
                    " FROM   sp_quyet_toan a  WHERE 1=1 ";

            strSQL +=
                    strWhere + " ORDER BY a.loai_qtoan desc";
              reval =
                    executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO,
                                           conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectVO + ".getDChieuList(): " +
                                 ex.getMessage(), ex);
            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }
    
  public Collection getSumKChuyen(String strWhere,
                              Vector vParam) throws Exception {

      Collection reval = null;
      try {

          String strSQL = "";

          strSQL +=
                  " SELECT sum(ket_chuyen_chi_nh) ket_chuyen_chi_nh,sum(ket_chuyen_thu_nh) ket_chuyen_thu_nh FROM (" + 
                  " SELECT    nvl(SUM (a.so_tien),0) ket_chuyen_chi_nh, 0 AS ket_chuyen_thu_nh " + 
                  " FROM   sp_quyet_toan a" + 
                  " WHERE   1 = 1 AND loai_qtoan='C' " + strWhere + 
                  " GROUP BY   a.loai_qtoan " + 
                  " UNION all " + 
                  " SELECT   0 AS ket_chuyen_chi_nh, nvl(SUM (a.so_tien),0) ket_chuyen_thu_nh " + 
                  "	 FROM   sp_quyet_toan a " + 
                  "	WHERE   1 = 1 AND loai_qtoan='D' " + strWhere + 
                  " GROUP BY   a.loai_qtoan)";

            reval =
                  executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO,
                                         conn);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(strValueObjectVO + ".getDChieuList(): " +
                               ex.getMessage(), ex);
          daoEx.printStackTrace();
          throw daoEx;
      }
      return reval;
  }
    

    public int updateTThaiDC2(DChieu2VO vo) throws Exception {
        int exc = 0;
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append("update SP_BK_DC2 set ngay_thien_dc= sysdate ");

        if (vo.getChenh_chi() == 0 && vo.getChenh_thu() == 0) {
            strSQL.append(", trang_thai = '02' ");
        } else if (vo.getChenh_chi() != 0 || vo.getChenh_thu() != 0) {
            strSQL.append(", trang_thai = '01' ");
        }

        strSQL.append(" where trang_thai ='00'");
        strSQL.append(" and bk_id = ? ");
        v_param.add(new Parameter(vo.getBk_id(), true));

        exc = executeStatement(strSQL.toString(), v_param, conn);

        return exc;
    }

    public int insert(DChieu2VO vo) throws Exception {
        int nExc = 0;
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        strSQL.append(" Insert into sp_kq_dc2 (id ");
        strSQL2.append(" ) values (sp_doi_chieu_pkg.fnc_get_bk_id('067') ");
        //      v_param.add(new Parameter(, true));

        strSQL.append(", bk_id");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getBk_id(), true));
        if (vo.getChenh_chi() == 0 && vo.getChenh_thu() == 0) {
            strSQL.append(", ket_qua");
            strSQL2.append(", ?");
            v_param.add(new Parameter("0", true));
        } else if (vo.getChenh_chi() != 0 || vo.getChenh_thu() != 0) {
            strSQL.append(", ket_qua");
            strSQL2.append(", ?");
            v_param.add(new Parameter("1", true));
        }
        strSQL.append(", lan_dc");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getLan_dc(), true));

        strSQL.append(", send_bank");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getNhkb_nhan(), true));

        strSQL.append(", receive_bank");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getNhkb_chuyen(), true));

        strSQL.append(", created_date");
        strSQL2.append(", sysdate");
        //    v_param.add(new Parameter(vo.getNhkb_nhan(), true));

        strSQL.append(", creator");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getCreator(), true));

        strSQL.append(", manager");
        strSQL2.append(", ?");
        v_param.add(new Parameter(null, true));

        strSQL.append(", VERIFIED_DATE");
        strSQL2.append(", ?");
        v_param.add(new Parameter(null, true));

        strSQL.append(", SODU_CUOINGAY_KB");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getSdu_cngay(), true));

        strSQL.append(", sodu_clech");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getSdu_clech(), true));

        strSQL.append(", KETCHUYEN_THU_KB");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getKet_chuyen_thu(), true));

        strSQL.append(", KETCHUYEN_THU_NH");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getKet_chuyen_thu_nh(), true));

        strSQL.append(", KETCHUYEN_CHI_NH");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getKet_chuyen_chi_nh(), true));

        strSQL.append(", KETCHUYEN_THU_CLECH");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getChenh_thu(), true));

        strSQL.append(", KETCHUYEN_CHI_KB");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getKet_chuyen_chi(), true));

        strSQL.append(", KETCHUYEN_CHI_CLECH");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getChenh_chi(), true));
        

        strSQL.append(", insert_date");
        strSQL2.append(", sysdate");
        //    v_param.add(new Parameter(vo.getNhkb_nhan(), true));

        strSQL.append(", trang_thai");
        strSQL2.append(", ?");
        v_param.add(new Parameter("01", true));
        
        strSQL.append(", tthai_dmo");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getTthai_dmo(), true));
        
        strSQL.append(", ghi_chu");
        strSQL2.append(", ?");
        v_param.add(new Parameter(null, true));
        
        strSQL.append(", tthai_ttin");
        strSQL2.append(", ?");
        v_param.add(new Parameter("00", true));
          
        strSQL.append(", ngay_dc");
        strSQL2.append(", ?");
        v_param.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_dc(),
                                                            "dd/MM/yyyy"),
                                    true));
      
        strSQL.append(", ngay_thien_dc");
        strSQL2.append(", sysdate");

        strSQL.append(strSQL2);
        strSQL.append(")");

        nExc = executeStatement(strSQL.toString(), v_param, conn);

        return nExc;
    }

    public Collection getListKQDC2(String strWhere,
                                   Vector vParam) throws Exception {

        Collection reval = null;
        try {

            String strSQL = "";

            strSQL +=
                    " select a.id, a.bk_id, a.ket_qua, a.lan_dc, a.send_bank, a.receive_bank,  b.ten," +
                    " a.created_date, a.creator, a.manager, a.verified_date, a.tthai_ttin," +
                    " a.sodu_cuoingay_kb, a.sodu_clech, a.ketchuyen_thu_kb, a.ketchuyen_thu_nh," +
                    " a.ketchuyen_thu_clech, a.ketchuyen_chi_kb, a.ketchuyen_chi_nh, a.ketchuyen_chi_clech," +
                    " to_char(a.insert_date,'DD/MM/YYYY') insert_date, TO_CHAR (a.ngay_dc, 'DD/MM/YYYY') ngay_dc, a.trang_thai " +
                    " FROM sp_kq_dc2 a, sp_dm_ngan_hang b  where a.receive_bank=b.ma_nh ";

            strSQL += strWhere + " ORDER BY a.receive_bank, insert_date ";
            reval =
                    executeSelectStatement(strSQL.toString(), vParam, strValueObjectDuyetVO,
                                           conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectDuyetVO + ".getListKQDC2(): " +
                                 ex.getMessage(), ex);
            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }

    public int updateDuyet(DuyetXNDChieu2VO vo) throws Exception {
        int exc = 0;
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append("update SP_KQ_DC2 set ");
        strSQL.append(" trang_thai = '02', tthai_ttin ='01', ngay_thien_dc = sysdate, chuky_ktt = ? ");
        strSQL.append(", MSG_ID=?");
        v_param.add(new Parameter(vo.getChuky_ktt(), true));
        v_param.add(new Parameter(vo.getMsg_id(), true));
        
        strSQL.append(" where id = ? ");        
        v_param.add(new Parameter(vo.getId(), true));


        exc = executeStatement(strSQL.toString(), v_param, conn);

        return exc;
    }

   
    public DuyetXNDChieu2VO getKQDC2(String strWhere,
                                     Vector vParam) throws Exception {

        //      Collection reval = null;
        try {

            String strSQL = "";
            strSQL +=
                    "SELECT a.id, a.bk_id, a.ket_qua, a.lan_dc, a.send_bank, a.receive_bank," +
                    " a.created_date, a.creator, a.manager, a.verified_date," +
                    " a.sodu_cuoingay_kb, a.sodu_clech, a.ketchuyen_thu_kb," +
                    " a.ketchuyen_thu_clech, a.ketchuyen_chi_kb, a.ketchuyen_chi_clech," +
                    " to_char(a.insert_date,'DD-MM-YYYY HH24:mm:ss') insert_date, a.trang_thai, a.ketchuyen_thu_nh," +
                    " a.ketchuyen_chi_nh, b.ma_nsd " +
                    " FROM sp_kq_dc2 a, sp_nsd b where a.creator = b.id  " +
                    strWhere;
            DuyetXNDChieu2VO phtVO =
                (DuyetXNDChieu2VO)findByPK(strSQL.toString(), vParam,
                                           strValueObjectDuyetVO, conn);
            return phtVO;
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectDuyetVO + ".getKQDC2(): " +
                                 ex.getMessage(), ex);
            daoEx.printStackTrace();
            throw daoEx;
        }
    }




//    public String getLanGui(String strWhere, Vector vParam) throws Exception {
//        try {
//            String strSQL =
//                "select decode(nvl(max(lan_dc),0),'0','2.1',max(lan_dc)+0.1) lan_dc, max(ngay_thien_dc) ngay_thien_dc  from  SP_KQ_DC2  where 1=1 ";
//            strSQL += strWhere;
//            ResultSet rs = null;
//            Statement stm = null;
//            stm = conn.createStatement();
//            String lan_dc = null;
//            rs = stm.executeQuery(strSQL);
//            if (rs.next()) {
//                lan_dc = rs.getString("lan_dc");
//            }
//            rs.close();
//            stm.close();
//            //return executeStatement(strSQL.toString(), vParam, conn);
//            return lan_dc;
//
//        } catch (Exception e) {
//            throw e;
//        }
//    }
    
public DChieu2VO getLanGui(String strWhere,
                             Vector vParam) throws Exception {

     //      Collection reval = null;
     try {

         String strSQL = "";

         strSQL +=
                 "select decode(nvl(max(lan_dc),0),'0','2.1',max(lan_dc)+0.1) lan_dc, nvl(to_char(sysdate,'MMDDYYHH24MI') - to_char(max(ngay_thien_dc),'MMDDYYHH24MI'),5) chenh_time from  SP_KQ_DC2  where 1=1 " +
                 strWhere;

         DChieu2VO vo =
             (DChieu2VO)findByPK(strSQL.toString(), vParam,
                                   strValueObjectVO, conn);
         return vo;
     } catch (Exception ex) {
         DAOException daoEx =
             new DAOException(strValueObjectVO + ".getKQDC1(): " +
                              ex.getMessage(), ex);
         daoEx.printStackTrace();
         throw daoEx;
     }
 }    
    
    
    
    
    
  public ResultSet printDC2(String strWhere, String user_code,
                                 Vector vParam) throws Exception {
      try {
          String strSQL =
  
              "  SELECT   a.id, a.bk_id, a.ket_qua, a.lan_dc,a.ngay_dc, a.send_bank,(SELECT ten" + 
              " FROM    sp_dm_manh_shkb WHERE shkb =(SELECT ma_cha FROM sp_dm_htkb" + 
              " WHERE ma='"+user_code+"'))ten_tinh, (SELECT ten FROM sp_dm_manh_shkb" + 
              " WHERE ma_nh=a.send_bank ) ten_nhkb, a.receive_bank," + 
              " b.ten, a.created_date, a.creator, a.manager, a.verified_date," + 
              " a.sodu_cuoingay_kb, a.sodu_clech, a.ketchuyen_thu_kb," + 
              " a.ketchuyen_thu_nh, a.ketchuyen_thu_clech, a.ketchuyen_chi_kb," + 
              " a.ketchuyen_chi_nh, a.ketchuyen_chi_clech,to_char(sysdate,'DD') ngay, to_char(sysdate,'MM') thang, to_char(sysdate,'YYYY') nam," + 
              " TO_CHAR (a.insert_date, 'DD/MM/YYYY') insert_date, a.trang_thai" + 
              " FROM	sp_kq_dc2 a, sp_dm_ngan_hang b" + 
              " WHERE	a.receive_bank = b.ma_nh " ;
          if (strWhere != null && !"".equals(strWhere)) {
              strSQL += strWhere;
          }
          strSQL += " order by a.id";
          return  executeSelectStatement(strSQL.toString(), vParam,conn);
      } catch (Exception e) {
          throw e;
      }

  } 
  public Collection getCheckTThai(String whereClause,
                                  Vector params) throws Exception {
      Collection reval = null;
      try {
          String strSQL = "";
          strSQL +=
                  "SELECT id, trang_thai, ket_qua, to_char(ngay_dc,'DD/MM/YYYY') ngay_dc FROM  sp_kq_dc2 " +
                  " WHERE id = ( SELECT max(id) FROM sp_kq_dc2 WHERE  ngay_dc IN (SELECT   DISTINCT TRUNC (max(d1.ngay_gd + 1)) ngay " + 
                  " FROM   sp_tso_cutoftime d1 WHERE 	1 = 1 " + whereClause;
          reval =
                  executeSelectStatement(strSQL.toString(), params, strValueObjectVO,
                                         conn);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(strValueObjectVO + ".getCheckTThai(): " +
                               ex.getMessage(), ex);
          daoEx.printStackTrace();
          throw daoEx;
      }
      return reval;
  }  
  
  public Collection getCheckTThai_bk2(String whereClause,
                                  Vector params) throws Exception {
      Collection reval = null;
      try {
          String strSQL = "";
          strSQL +=
                  "SELECT	distinct trang_thai, TO_CHAR (ngay_dc, 'DD/MM/YYYY') ngay_dc" + 
                  " FROM	sp_bk_dc2 WHERE	ngay_dc = (SELECT	 DISTINCT TRUNC (MAX (d1.ngay_gd + 1)) ngay" + 
                  " FROM	 sp_tso_cutoftime d1 WHERE 1 = 1" + whereClause;
          reval =
                  executeSelectStatement(strSQL.toString(), params, strValueObjectVO,
                                         conn);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(strValueObjectVO + ".getCheckTThai_bk2(): " +
                               ex.getMessage(), ex);
          daoEx.printStackTrace();
          throw daoEx;
      }
      return reval;
  }  
  
}
