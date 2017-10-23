package com.seatech.ttsp.denghiqt;

import com.seatech.framework.datamanager.AppDAO;


import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.utils.TTSPUtils;

import java.sql.Connection;

import java.util.Collection;
import java.util.Vector;

public class DeNghiQuyetToanDAO extends AppDAO{
    private String CLASS_NAME_VO = "com.seatech.ttsp.denghiqt.DeNghi_QuyetToanVO";
    private Connection conn;
    
    public DeNghiQuyetToanDAO(Connection conn){
      this.conn = conn;
    }
      
    public int insert(DeNghi_QuyetToanVO vo) throws Exception{
        
      Vector params = new Vector();
      TTSPUtils ttspUtils = new TTSPUtils(conn);
      String idQToan =  ttspUtils.getMT_ID("066", null);
      String query = "INSERT INTO SP_066 (ID,NHKB_CHUYEN,NHKB_NHAN,NGUOI_TAO,NGAY_TAO,NGAY_QTOAN,LOAI_QTOAN, "+
                     "QTOAN_THU,QTOAN_CHI,NDUNG_QTOAN,TRANG_THAI,LOAI_TIEN) " + 
                     "VALUES (?,?,?,?,SYSDATE,to_date(?,'DD/MM/YYYY'),?,?,?,?,?,?)";
      
      params.add(new Parameter(idQToan, true));
      params.add(new Parameter(vo.getMaKB(), true));
      params.add(new Parameter(vo.getNganHang(),true));
      params.add(new Parameter(vo.getNguoiTao(), true));
      params.add(new Parameter(vo.getNgayQuyetToan(), true));
      params.add(new Parameter(vo.getLoaiQuyetToan(), true));
      params.add(new Parameter(vo.getQuyetToanThu(), true));
      params.add(new Parameter(vo.getQuyetToanChi(), true));
      params.add(new Parameter(vo.getNoiDung(), true));
      params.add(new Parameter(vo.getTrangThai_066(), true));
      params.add(new Parameter(vo.getLoaiTien(), true));
      
      return (int)(executeStatement(query, params, conn) > 0 ? Long.parseLong(idQToan) : 0);
    }
    
    public Collection checkNgayNghi(String strWhere) throws Exception{
        try{
            String strQuery = "select ngay from sp_ngay_nghi " + strWhere;
            return executeSelectStatement(strQuery, null, CLASS_NAME_VO, conn);
        }catch(Exception e){
            throw e;
        }
    }
}
