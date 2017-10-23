package com.seatech.ttsp.reports.sketk;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;

import java.util.Collection;
import java.util.Vector;


public class SKeTKDAO extends AppDAO {
    Connection conn = null;

    public SKeTKDAO() {
        super();
    }

    public SKeTKDAO(Connection conn) {
        this.conn = conn;
    }
    private static String CLASS_NAME_DAO =
        "com.seatech.ttsp.reports.sketk.SKeTKDAO";
    private static String CLASS_NAME_VO =
        "com.seatech.ttsp.reports.sketk.SKeTKVO";
    private static String CLASS_NAME_VOCT =
        "com.seatech.ttsp.reports.sketk.SKeTKChiTietVO";

    private static String STRING_EMPTY = "";

    public SKeTKVO getSK(String whereClause, Vector params) throws Exception {
        SKeTKVO reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.nguoi_tao, a.nguoi_ks, a.send_bank, a.send_bank_name, a.receive_bank, ");
            strSQL.append("a.receive_bank_name, a.mt_type, a.stt_mt, a.send_date, ");
            strSQL.append("a.receive_date, a.loai_ctu, a.ky_hieu_mat, a.so_tham_chieu_gd, ");
            strSQL.append("a.so_tk, a.seq_number, a.so_tien_du_dau, a.tinh_chat_du_dau, ");
            strSQL.append("a.loai_tien_du_dau, a.ngay_du_dau, a.so_tien_du_cuoi, ");
            strSQL.append("a.tinh_chat_du_cuoi, a.loai_tien_du_cuoi, a.ngay_du_cuoi, ");
            strSQL.append("a.so_du_kha_dung, a.id, a.loai_sao_ke, to_char(a.ngay_ks,'dd/mm/yyyy hh24:mi') ngay_ks, to_char(a.insert_date,'dd/mm/yyyy hh24:mi') insert_date, ");
            strSQL.append("(select sum(so_tien) from sp_saoke_tk_ctiet where sao_ke_id = a.id and tinh_chat_ps = 'D') tong_ps_no, ");
            strSQL.append("(select sum(so_tien) from sp_saoke_tk_ctiet where sao_ke_id = a.id and tinh_chat_ps = 'C') tong_ps_co ");
            strSQL.append("FROM sp_saoke_tk a where 1=1 ");
            strSQL.append(" and a.id in (select max(a.id) from sp_saoke_tk a where 1=1 ");
            strSQL.append(whereClause);
            strSQL.append(")");
            reval =
                    (SKeTKVO)findByPK(strSQL.toString(), params, CLASS_NAME_VO, conn);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO +
                                   ".getSK(): " +
                                   ex.getMessage(), ex);
        }

        return reval;
    }

//    public Collection getSKChiTietListWithPading(String whereClause,
//                                                 Vector params, Integer page,
//                                                 Integer count,
//                                                 Integer[] totalCount) throws Exception {
//        Collection reval = null;
//        StringBuffer strSQL = new StringBuffer();
//        try {
//            
//            strSQL.append("SELECT a.id, a.sao_ke_id, a.ma_loai_gd, to_char(a.ngay_ps,'DD/MM/YYYY HH:MI:SS') ngay_ps, a.tinh_chat_ps, ");
//            strSQL.append("a.loai_tien, a.so_tien, a.tk_tham_chieu, a.noi_dung ");
//            strSQL.append("FROM sp_saoke_tk_ctiet a ");
//            if (whereClause != null &&
//                !STRING_EMPTY.equalsIgnoreCase(whereClause)) {
//                if (whereClause.toUpperCase().indexOf("WHERE") == -1)
//                    strSQL.append(" WHERE " + whereClause);
//                else
//                    strSQL.append(whereClause);
//            }
//            strSQL.append(" order by a.id,a.ngay_ps ");
//            reval =
//                    executeSelectWithPaging(conn, strSQL.toString(), params, CLASS_NAME_VOCT,
//                                            page, count, totalCount);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            throw new DAOException(CLASS_NAME_DAO +
//                                   ".getSKChiTietListWithPading(): " +
//                                   ex.getMessage(), ex);
//        }
//
//        return reval;
//    }
  public Collection getSKChiTietListWithPading(SKeTKVO skTKVO, Integer page,
                                               Integer count,
                                               Integer[] totalCount) throws Exception {
      Collection reval = null;
      StringBuffer strSQL = new StringBuffer();
      try {
          
          strSQL.append("SELECT c.id, c.sao_ke_id, c.ma_loai_gd, c.ngay_ps, c.tinh_chat_ps, \n" + 
          "c.loai_tien, c.so_tien, c.tk_tham_chieu, c.noi_dung, c.loai_ps, c.tong_ps_no, c.tong_ps_co  FROM (\n" + 
          "(SELECT NULL id ,a.id sao_ke_id, '0' ma_loai_gd, to_char(a.ngay_du_dau,'DD/MM/YYYY') ngay_ps, a.tinh_chat_du_dau tinh_chat_ps,\n" + 
          "a.loai_tien_du_dau loai_tien, a.so_tien_du_dau so_tien, a.so_tk tk_tham_chieu, NULL noi_dung, 'PS_DAU' loai_ps, NULL tong_ps_no, NULL tong_ps_co\n" + 
          "FROM sp_saoke_tk a where a.id = ?)\n" + 
          "UNION ALL\n" + 
          "(SELECT a.id, a.sao_ke_id, a.ma_loai_gd, to_char(a.ngay_ps,'DD/MM/YYYY') ngay_ps, a.tinh_chat_ps, \n" + 
          "a.loai_tien, a.so_tien, a.tk_tham_chieu, a.noi_dung, NULL loai_ps, NULL tong_ps_no, NULL tong_ps_co \n" + 
          "FROM sp_saoke_tk_ctiet a \n" + 
          "WHERE a.sao_ke_id=? )\n" + 
          "UNION ALL\n" + 
          "(SELECT NULL id, NULL sao_ke_id, 'zzzzzzzzy' ma_loai_gd, NULL ngay_ks, NULL tinh_chat_ps, \n" + 
          "'"+skTKVO.getLoai_tien_du_cuoi()+"' loai_tien, NULL so_tien, NULL tk_tham_chieu, NULL noi_dung, 'PS_TRONG' loai_ps,\n" + 
          "(select sum(b.so_tien) from sp_saoke_tk_ctiet b where b.tinh_chat_ps='D' and b.sao_ke_id = ?) tong_ps_no, \n" + 
          "(select sum(b.so_tien) from sp_saoke_tk_ctiet b where b.tinh_chat_ps='C' and b.sao_ke_id = ?) tong_ps_co FROM DUAL a  )\n" + 
          "UNION ALL\n" + 
          "(SELECT NULL id,a.id sao_ke_id, 'zzzzzzzzz' ma_loai_gd, to_char(a.ngay_du_cuoi,'DD/MM/YYYY') ngay_ps, a.tinh_chat_du_cuoi tinh_chat_ps,\n" + 
          "a.loai_tien_du_cuoi loai_tien, a.so_tien_du_cuoi so_tien, a.so_tk tk_tham_chieu, NULL noi_dung, 'PS_CUOI' loai_ps,  NULL tong_ps_no, NULL tong_ps_co\n" + 
          "FROM sp_saoke_tk a where a.id = ?)) c order by c.ma_loai_gd");
        
          Vector params = new Vector();
          params.add(new Parameter(skTKVO.getId(), true));
          params.add(new Parameter(skTKVO.getId(), true));
          params.add(new Parameter(skTKVO.getId(), true));
          params.add(new Parameter(skTKVO.getId(), true));
          params.add(new Parameter(skTKVO.getId(), true));
          
          reval =
                  executeSelectWithPaging(conn, strSQL.toString(), params, CLASS_NAME_VOCT,
                                          page, count, totalCount);
      } catch (Exception ex) {
          ex.printStackTrace();
          throw new DAOException(CLASS_NAME_DAO +
                                 ".getSKChiTietListWithPading(): " +
                                 ex.getMessage(), ex);
      }

      return reval;
  }
  
  public int updateTT_Chot_So(String sqlWhere, Vector params) throws Exception{
      try{
        String sqlQuery = "UPDATE sp_saoke_tk SET tt_chot_so = '01' WHERE 1=1 " +
            "AND id IN (select max(a.id) from sp_saoke_tk a where 1=1 ";
        sqlQuery += sqlWhere + ")";
          return executeStatement(sqlQuery, params, conn) > 0 ? 1 : 0;
      }catch(Exception e){
          throw e;
      }
  }
}