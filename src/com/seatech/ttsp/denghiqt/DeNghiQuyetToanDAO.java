package com.seatech.ttsp.denghiqt;

import com.seatech.framework.datamanager.AppDAO;


import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;
import com.seatech.framework.exception.DatabaseConnectionFailureException;
import com.seatech.framework.exception.SelectStatementException;
import com.seatech.framework.utils.TTSPUtils;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collection;
import java.util.Vector;

public class DeNghiQuyetToanDAO extends AppDAO {
    private Connection conn;
    private String strValueObjectVO ="com.seatech.ttsp.denghiqt.DeNghi_QuyetToanVO";
    public DeNghiQuyetToanDAO(Connection conn) {
        this.conn = conn;
    }

    public int insert(DeNghi_QuyetToanVO vo) throws Exception {
        int iReturn = 0;
        Vector params = new Vector();
        TTSPUtils ttspUtils = new TTSPUtils(conn);
        String idQToan = "";       
            idQToan = ttspUtils.getMT_ID("066", null);       
        String query =
            "INSERT INTO SP_066 (ID,NHKB_CHUYEN,NHKB_NHAN,NGUOI_TAO,NGAY_TAO,NGAY_QTOAN,LOAI_QTOAN, " +
            "QTOAN_THU,QTOAN_CHI,NDUNG_QTOAN,TRANG_THAI,LOAI_TIEN,NGAY_BU_CHI) " +
            "VALUES (?,(select ma_nh from SP_DM_HTKB a, SP_DM_MANH_SHKB b where a.ma = b.SHKB and id = ?),?,?,SYSDATE,to_date(?,'DD/MM/YYYY'),?,?,?,?,?,?,to_date(?,'dd/mm/yyyy'))";

        params.add(new Parameter(idQToan, true));
        params.add(new Parameter(vo.getMaKB(), true));
        params.add(new Parameter(vo.getNganHang(), true));
        params.add(new Parameter(vo.getNguoiTao(), true));
        params.add(new Parameter(vo.getNgayQuyetToan(), true));
        params.add(new Parameter(vo.getLoaiQuyetToan(), true));
        params.add(new Parameter(vo.getQuyetToanThu(), true));
        params.add(new Parameter(vo.getQuyetToanChi(), true));
        params.add(new Parameter(vo.getNoiDung(), true));
        params.add(new Parameter(vo.getTrangThai_066(), true));
        params.add(new Parameter(vo.getLoaiTien(), true));
        params.add(new Parameter(vo.getNgay_loi(), true));
        iReturn = executeStatement(query, params, conn);
        return iReturn;
    }

    public boolean checkNgayNghi(String ngay) throws DatabaseConnectionFailureException,
                                                     SelectStatementException,
                                                     SQLException {
        boolean bReturn = false;
        String strSQL =
            "select 1 from sp_ngay_nghi where to_date(ngay,'yyyymmdd')= TO_DATE('" +
            ngay + "','dd/mm/yyyy')";
        System.out.printf(strSQL);
        ResultSet rs = executeSelectStatement(strSQL, null, conn);
        bReturn = rs.next();
        return bReturn;
    }
  public Collection getNgayLoiList() throws Exception {

      Collection reval = null;
      try {

          String strSQL = "";
          strSQL +=
        "select TO_CHAR(a.ngay_gd,'DD/MM/YYYY') ngay_loi FROM (" + 
                    "select DISTINCT ngay_gd from sp_tso_cutoftime where ngay_gd <trunc(sysdate) " + 
                    "and to_char(ngay_gd, 'YYYYmmDD') not in (select ngay from sp_ngay_nghi) " + 
                    "order by ngay_gd desc) a where rownum < 8";

        
          reval =
                  executeSelectStatement(strSQL.toString(), null, strValueObjectVO,
                                         conn);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(strValueObjectVO + ".getBKeDChieu4CTiet(): " +
                               ex.getMessage(), ex); 
          throw daoEx;
      }
      return reval;
  }  
}
