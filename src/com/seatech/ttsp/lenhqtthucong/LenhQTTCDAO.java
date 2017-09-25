package com.seatech.ttsp.lenhqtthucong;

import com.seatech.framework.datamanager.AppDAO;

import com.seatech.framework.datamanager.Parameter;

import java.sql.Connection;

import java.util.Collection;
import java.util.Vector;

public class LenhQTTCDAO extends AppDAO{
    Connection conn;
    
  private String strValueObject = "com.seatech.ttsp.lenhqtthucong.LenhQTTCVO";
    public LenhQTTCDAO(Connection conn){
        this.conn = conn;
    }
    
    public int insert(LenhQTTCVO lenhQT, Vector params) throws Exception{
        try{
            String sqlQuery = "INSERT into sp_quyet_toan (ID,NHKB_CHUYEN,NHKB_NHAN,NGAY_HTOAN,NGAY_QTOAN," +
                "NGAY_INSERT,LOAI_QTOAN,QTOAN_DVI,NGUOI_NHAP_NH,NGAY_NHAP_NH,NGUOI_KS_NH,NGAY_KS_NH," +
                "NDUNG_TT,SO_TIEN,MA_NT,TTV_CHUYEN_KS,NGAY_CHUYEN_KS,TK_CHUYEN,MA_NH_CHUYEN,TEN_NH_CHUYEN,TEN_KH_CHUYEN," +
                "TK_NHAN,MA_NH_NHAN,TEN_NH_NHAN,TEN_KH_NHAN,TRANG_THAI,LOAI_HACH_TOAN,NGAY_GUI,MA_TCHIEU_GD," +
                "MA_KB,ID_REF,TRANG_THAI_DC,LAI_PHI,LOAI_TK,NHAP_THU_CONG) VALUES (?,?,?,TO_DATE(?,'DD/MM/YYYY'),TO_DATE(?,'DD/MM/YYYY')," +
                "SYSDATE,?,?,?,TO_DATE(?,'DD/MM/YYYY HH24:MI:SS'),?,TO_DATE(?,'DD/MM/YYYY HH24:MI:SS'),?,?,?,?,SYSDATE,?,?,?,?,?,?,?,?,?,?,SYSDATE,?," +
                "?,?,?,?,?,?)";
            params.add(new Parameter(lenhQT.getSoLenhQuyetToan(),true));
            params.add(new Parameter(lenhQT.getMaNHKBChuyen(), true));
            params.add(new Parameter(lenhQT.getMaNHKBNhan(),true));
            params.add(new Parameter(lenhQT.getNgayHachToan(),true));
            params.add(new Parameter(lenhQT.getNgayQuyetToan(),true));
            params.add(new Parameter(lenhQT.getLoaiQuyetToan(),true));
            params.add(new Parameter(lenhQT.getQtDonVi(),true));
            params.add(new Parameter(lenhQT.getNguoiNhap(),true));
            params.add(new Parameter(lenhQT.getNgayNhap(),true));
            params.add(new Parameter(lenhQT.getNguoiKiemSoat(),true));
            params.add(new Parameter(lenhQT.getNgayKiemSoat(),true));
            params.add(new Parameter(lenhQT.getNoiDungThanhToan(),true));
            params.add(new Parameter(lenhQT.getSoTien(),true));
            params.add(new Parameter(lenhQT.getLoaiTien(),true));
            params.add(new Parameter(lenhQT.getTTVChuyenKS(),true));
            params.add(new Parameter(lenhQT.getTaiKhoanPhatLenh(),true));
            params.add(new Parameter(lenhQT.getMaNHPhatLenh(),true));
            params.add(new Parameter(lenhQT.getTenTaiKhoanPhatLenh(),true));
            params.add(new Parameter(lenhQT.getTenKHChuyen(),true));
            params.add(new Parameter(lenhQT.getTenTaiKhoanNhanLenh(),true));
            params.add(new Parameter(lenhQT.getMaNHNhanLenh(),true));
            params.add(new Parameter(lenhQT.getTenNHNhanLenh(),true));
            params.add(new Parameter(lenhQT.getTenKHNhan(),true));
            params.add(new Parameter(lenhQT.getTrangThai(),true));            
            params.add(new Parameter(lenhQT.getPhuongAnHachToan(),true));          
            params.add(new Parameter(lenhQT.getSoThamChieuGiaoDich(),true));
            params.add(new Parameter(lenhQT.getMaKB(),true));
            params.add(new Parameter(lenhQT.getIdREF(),true));
            params.add(new Parameter(lenhQT.getTrangThaiDC(),true));
            params.add(new Parameter(lenhQT.getLaiPhi(),true));
            params.add(new Parameter(lenhQT.getLoaiTK(),true));
            params.add(new Parameter(lenhQT.getNhapThuCong(),true));
            
            return (int)executeStatement(sqlQuery, params, conn) > 0 ? 1 : 0;
        }catch(Exception e){
            throw e;
        }
    }
    
    public Collection getNguoiKiemSoat(String strWhere, Vector params) throws Exception{
        try{
            String strSQL = "SELECT c.ten tenkb,a.so_tk sotk,b.ma_nh manh, b.ten tennh FROM sp_tknh_kb a, sp_dm_ngan_hang b, sp_dm_htkb c " +
                "where a.nh_id=b.id and a.kb_id = c.id AND c.ma = '0003' and a.trang_thai = '01' and a.loai_tk = '01' " + strWhere;
            return executeSelectStatement(strSQL, params, strValueObject, conn);
        }catch(Exception e){
            throw e;
        }
    }
    public Collection getNameChiNhanhNH(String strWhere, Vector params) throws Exception{
        try{
            String strSQL = "SELECT ten FROM sp_dm_ngan_hang WHERE ma_nh = " + strWhere;
            return executeSelectStatement(strSQL, params, strValueObject, conn);
        }catch(Exception e){
            throw e;
        }
    }
    
    public Collection getLoaiTK(String strWhere, Vector params) throws Exception{
        try{
            String strSQL = "SELECT a.loai_tk loaitk FROM sp_tknh_kb a, sp_dm_htkb b, sp_dm_ngan_hang c, sp_dm_manh_shkb d " + 
                            "WHERE a.kb_id = b.id " +
                            "AND a.nh_id = c.id " + 
                            "AND b.ma=d.shkb " + 
                            "AND a.loai_tk <> '01' " + 
                            "AND a.trang_thai = '01' " + 
                            "AND ROWNUM = 1 " + strWhere;
          return executeSelectStatement(strSQL, params, strValueObject, conn);
        }catch(Exception e){
            throw e;
        }
    }
}
