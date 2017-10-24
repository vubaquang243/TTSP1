package com.seatech.framework.utils;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.ttsp.cutofftime.CutOffTimeDAO;
import com.seatech.ttsp.cutofftime.CutOffTimeVO;
import com.seatech.ttsp.dmnh.DMNHangDAO;
import com.seatech.ttsp.dmnh.DMNHangVO;
import com.seatech.ttsp.dts.DTSoatDAO;
import com.seatech.ttsp.dts.DTSoatVO;

import com.seatech.ttsp.thamso.ThamSoHThongDAO;

import com.seatech.ttsp.thamso.ThamSoHThongVO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

  /**
   * @create: thuongdt
   * @create date: 15/02/2017   
   * @see: lay ngay hom truoc
   * @find: thuongdt-20170215
   * */
public class TTSPUtils extends AppDAO {
    private Connection conn = null;

    public TTSPUtils(Connection conn) {
        this.conn = conn;
    }

    /**
     * @param: Loai LTT (103)
     * @return: So LTT
     * @see: Ham lay ra so lenh thanh toan
     * */
    public String getSoLTT(String type) throws Exception {
        Vector v_param = new Vector();
        v_param.add(new Parameter("", false));
        v_param.add(new Parameter(type, true));
        String strFunctionName = "SP_UTIL_PKG.FNC_GET_SO_LTT";
        return executeFunction(strFunctionName, v_param, this.conn).toString();
    }

    /**
     * @param: Loai LTT (103)
     * @return: So LTT
     * @see: Ham lay ra so lenh thanh toan
     * */
    public String getSoBKeQtoan(String strMaKB) throws Exception {
        Vector v_param = new Vector();
        v_param.add(new Parameter("", false));
        v_param.add(new Parameter(strMaKB, true));
        //v_param.add(new Parameter(type, true));
        String strFunctionName = "SP_UTIL_PKG.FNC_GET_SO_BKE_QTOAN";
        return executeFunction(strFunctionName, v_param, this.conn).toString();
    }

    /**
     * @param: YYYYMMDD
     * @return: YYYYMMDD
     * @see: Ham tra ve ngay lam viec gan no nhat tinh tu ngay truyen vao tro di
     * */
    public String getNgayLamViec(String ngay) throws Exception {
        Vector v_param = new Vector();
        v_param.add(new Parameter("", false));
        v_param.add(new Parameter(ngay, true));
        String strFunctionName = "SP_UTIL_PKG.FNC_GET_NGAY_LAM_VIEC";
        return executeFunction(strFunctionName, v_param, this.conn).toString();
    }

    /**
     * @param: YYYYMMDD
     * @return: YYYYMMDD
     * @see: Ham tra ve ngay lam viec gan nhat tinh tu ngay truyen vao tro lai
     * */
    public String getNgayLamViecTruoc(String ngay) throws Exception {
        Vector v_param = new Vector();
        v_param.add(new Parameter("", false));
        v_param.add(new Parameter(ngay, true));
        String strFunctionName = "SP_UTIL_PKG.fnc_get_ngay_lam_viec_truoc2";
        return executeFunction(strFunctionName, v_param, this.conn).toString();
    }

    /**
     * @param: dd/mm/yyyy
     * @return: dd/MM/yyyy HH:mm:ss
     * @see: Ham tra ve ngay lam viec gan no nhat tinh tu ngay truyen vao tro di
     * */
    public Date getNgayGioTGTre(String strNgay, String strMaNHKB,
                                String strMaNH) throws Exception {
        Vector v_param = new Vector();
        v_param.add(new Parameter("", false));
        v_param.add(new Parameter(strNgay, true));
        v_param.add(new Parameter(strMaNHKB, true));
        v_param.add(new Parameter(strMaNH, true));
        String strFunctionName = "SP_UTIL_PKG.fnc_get_thoi_gian_tre";
        String strRs =
            executeFunction(strFunctionName, v_param, this.conn).toString();
        return StringUtil.StringToDate(strRs, "dd/MM/yyyy HH:mm:ss");
    }

    /**
     * @param: Loai lenh (195)
     * @return: So DTS
     * @see: Ham lay ra so dien tra soat
     * */
    public String getSoDTS(String type) throws Exception {
        Vector v_param = new Vector();
        v_param.add(new Parameter("", false));
        v_param.add(new Parameter(type, true));
        String strFunctionName = "SP_UTIL_PKG.FNC_GET_SO_DTS";
        return executeFunction(strFunctionName, v_param, this.conn).toString();
    }

    /**
     * @param: Loai lenh (196)
     * @return: So DTS tra loi
     * @see: Ham lay ra so dien tra soat tra loi
     * */
    public String getSoDTSTrLoi(String type) throws Exception {
        Vector v_param = new Vector();
        v_param.add(new Parameter("", false));
        v_param.add(new Parameter(type, true));
        String strFunctionName = "SP_UTIL_PKG.FNC_GET_SO_DTS_TRLOI";
        return executeFunction(strFunctionName, v_param, this.conn).toString();
    }

    /**
     * @param: Loai lenh (299)
     * @return: So COT
     * @see: Ham lay ra so cut off time
     * */
    public String getSoCOT(String type) throws Exception {
        Vector v_param = new Vector();
        v_param.add(new Parameter("", false));
        v_param.add(new Parameter(type, true));
        String strFunctionName = "SP_UTIL_PKG.FNC_GET_SO_COT";
        return executeFunction(strFunctionName, v_param, this.conn).toString();
    }

    /**
     * @param: Loai lenh (...)
     * @return: So MT_ID
     * @see: Ham lay ra MT_ID
     * */
    public String getMT_ID(String type, String duphong) throws Exception {
        Vector v_param = new Vector();
        v_param.add(new Parameter("", false));
        v_param.add(new Parameter(type, true));
        v_param.add(new Parameter(duphong, true));
        String strFunctionName = "SP_UTIL_PKG.FNC_GET_MT_ID";
        return executeFunction(strFunctionName, v_param, this.conn).toString();
    }

    /**
     * @param: Loai lenh (905)
     * @return: So lenh quyet toan
     * @see: Ham lay ra so lenh quyet toan
     * */
    public String getSoLenhQToan(String strType) throws Exception {
        Vector v_param = new Vector();
        v_param.add(new Parameter("", false));
        v_param.add(new Parameter(strType, true));
        String strFunctionName = "SP_UTIL_PKG.FNC_GET_SO_LENH_QTOAN";
        return executeFunction(strFunctionName, v_param, this.conn).toString();
    }

    /**
     * @param: Loai lenh (065, 067, 069)
     * @return: So bang ke ket qua doi chieu
     * @see: Ham lay ra so Bke KQ DC
     * */
    public String getSoBKeKQuaDC(String strType) throws Exception {
        Vector v_param = new Vector();
        v_param.add(new Parameter("", false));
        v_param.add(new Parameter(strType, true));
        String strFunctionName = "sp_doi_chieu_pkg.fnc_get_bk_id";
        return executeFunction(strFunctionName, v_param, this.conn).toString();
    }

    /**
     * @param: ten action và nsd ID
     * @return: bolean: true = co quyen; false = khong co quyen
     * @see: Ham kt quyen tren chuc nang
     * */
    public boolean checkPermisFunction(String strUserID,
                                       String strActionname) throws Exception {
        Vector v_param = new Vector();
        v_param.add(new Parameter("", false));
        v_param.add(new Parameter(strUserID, true));
        v_param.add(new Parameter(strActionname, true));

        String strFunctionName = "SP_UTIL_PKG.fnc_check_permistion";
        int nKetQua =
            Integer.parseInt(executeFunction(strFunctionName, v_param,
                                             this.conn).toString());
        if (nKetQua > 0)
            return true;
        else
            return false;
    }

    /**
     * @param: Kbac ID
     * @return: bolean: true = bi ngat ke noi; false = khong bi ngat ket noi
     * @see: Check xem KBac don vi co bi ngat ket noi hay ko? Neu bi ngat ket noi (=true) thi ko dc phep nhap va xu ly lenh.
     * */
    public boolean checkNgatKetNoi(String strKBacID) throws Exception {
        Vector v_param = new Vector();
        v_param.add(new Parameter(strKBacID, true));

        String strSQL =
            "SELECT a.id, a.kb_id FROM sp_kb_ngat_knoi a where a.kb_id = ?";
        ResultSet rs = executeSelectStatement(strSQL, v_param, conn);
        if (rs.next())
            return true;
        else
            return false;
    }

    /**
     * @param: NSD ID
     * @return: bolean: true = co quyen nhap; false = khong co quyen nhap
     * @see: Check xem NSD co quyen nhap lenh hay ko.
     * */
    public boolean checkQuyenNhapLTT(String strUserID) throws Exception {
        Vector v_param = new Vector();
        v_param.add(new Parameter(strUserID, true));

        String strSQL =
            "SELECT a.id, a.nsd_id FROM sp_nsd_co_qnhap a where a.nsd_id = ?";
        ResultSet rs = executeSelectStatement(strSQL, v_param, conn);
        if (rs.next())
            return true;
        else
            return false;
    }
    //        public static void main(String[] agr) {
    //            try {
    //                AppDAO a = new AppDAO();
    //
    //                TTSPUtils tu = new TTSPUtils(a.getConnectionTest());
    //                boolean va = tu.checkQuyenNhapLTT("1");
    //                System.out.println(va);
    //            } catch (Exception e) {
    //                System.out.println(e.getMessage());
    //            }
    //        }

    public static String getStateBU(HttpServletRequest request,
                                    HttpServletResponse response,
                                    Connection conn) {
        DTSoatVO dtsoatVO = null;
        Vector params = new Vector();
        String whereClause = "";
        try {
            DTSoatDAO dtsoatDAO = new DTSoatDAO(conn);
            whereClause = " WHERE a.id=?";
            params.add(new Parameter(request.getParameter("id"), true));
            dtsoatVO = dtsoatDAO.getDTSForUpdate(whereClause, params);
            return (dtsoatVO != null) ? dtsoatVO.getTrang_thai() : "";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }

    }

    public static String getStateBUCOT(HttpServletRequest request,
                                       HttpServletResponse response,
                                       Connection conn) {
        CutOffTimeVO cotVO = null;
        Vector params = new Vector();
        String whereClause = "";
        try {
            CutOffTimeDAO cotDAO = new CutOffTimeDAO(conn);
            whereClause = " WHERE a.id=?";
            params.add(new Parameter(request.getParameter("id"), true));
            cotVO = cotDAO.getCOTForUpdate(whereClause, params);
            return (cotVO != null) ? cotVO.getTrang_thai() : "";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }

    }

    /**
     *
     * @param id
     * @param conn
     * @return
     */
    public static DTSoatVO getDTSById(String id, String ma_tham_chieu,
                                      Connection conn) {
        DTSoatVO dtsoatVO = null;
        String whereClause = "";
        Vector params = new Vector();
        Collection colNHKB = null;
        DMNHangVO dmNHVO = null;
        try {
            whereClause = " AND  a.id=? ";
            whereClause += " AND c.rv_domain like (?)";
            if (id != null && !id.equals("")) {
                params = new Vector();
                params.add(new Parameter(id, true));
                params.add(new Parameter("%" + ma_tham_chieu + "%", true));
                DTSoatDAO dtsoatDAO = new DTSoatDAO(conn);
                dtsoatVO = dtsoatDAO.getDTS(whereClause, params);
                if (dtsoatVO != null) {
                    //lay ten ma danh muc ngan hang
                    whereClause = " id =? or id=?  ";
                    params = new Vector();
                    params.add(new Parameter(dtsoatVO.getNhkb_chuyen(), true));
                    params.add(new Parameter(dtsoatVO.getNhkb_nhan(), true));
                    DMNHangDAO dmNHangDAO = new DMNHangDAO(conn);
                    colNHKB = dmNHangDAO.getDMNHList(whereClause, params);
                    if (colNHKB != null && colNHKB.size() > 0) {
                        for (Iterator iter = colNHKB.iterator();
                             iter.hasNext(); ) {
                            dmNHVO = (DMNHangVO)iter.next();
                            if (dtsoatVO.getNhkb_chuyen().toString().equals(dtsoatVO.getNhkb_nhan().toString())) {
                                dtsoatVO.setMa_don_vi_tra_soat(dmNHVO.getMa_nh());
                                dtsoatVO.setTen_don_vi_tra_soat(dmNHVO.getTen());
                                dtsoatVO.setMa_don_vi_nhan_tra_soat(dmNHVO.getMa_nh());
                                dtsoatVO.setTen_don_vi_nhan_tra_soat(dmNHVO.getTen());
                            } else {
                                if (dtsoatVO.getNhkb_chuyen().toString().equals(dmNHVO.getId().toString())) {
                                    dtsoatVO.setMa_don_vi_tra_soat(dmNHVO.getMa_nh());
                                    dtsoatVO.setTen_don_vi_tra_soat(dmNHVO.getTen());
                                } else {
                                    dtsoatVO.setMa_don_vi_nhan_tra_soat(dmNHVO.getMa_nh());
                                    dtsoatVO.setTen_don_vi_nhan_tra_soat(dmNHVO.getTen());
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dtsoatVO;
    }

    /**
     * @param:
     * @return: String msg_ltt_id
     * @see:
     * */
    public String getMsgLTTID(String strSender,
                              String strReciever) throws Exception {
        String strMsgID = null;
        try {
            Long nSeqMsgID = getSeqNextValue("MSG_ID_SEQ", this.conn);
            String strSeqMsgID = LPAD(nSeqMsgID.toString(), 6);

            strMsgID =
                    strSender + StringUtil.DateToString(new Date(), "yyMMdd") +
                    strSeqMsgID;

        } catch (Exception e) {
            throw e;
        }
        return strMsgID;
    }

    public String getMsgTTTTID(String strSender,
                               String strRev) throws Exception {
        String strMsgID = null;
        try {
            Long nSeqMsgID = getSeqNextValue("MSG_ID_SEQ", this.conn);
            String strSeqMsgID = LPAD(nSeqMsgID.toString(), 6);

            strMsgID =
                    strSender + StringUtil.DateToString(new Date(), "yyMMdd") +
                    strSeqMsgID;
        } catch (Exception e) {
            throw e;
        }
        return strMsgID;
    }

    public String LPAD(String strSeq, int nLenght) {
        while (strSeq.length() < nLenght) {
            strSeq = "0" + strSeq;
        }
        return strSeq;
    }

    public void grantAccess() throws Exception {
//        CallableStatement cstmt =
//            conn.prepareCall("{ call TTSP_OWNER.GRANT_ACCESS() }");
//        cstmt.execute();
    }

    /**
     * @param: strNgay=DD/MM/YYYY; strMaNHKB=Ma 8 so cua KB; strMaNH = Ma 8 so NH
     * @return: Gio cutoftime HH24:MI
     * @see: Ham lay ra gio cut of time
     * */
    public String getGioCutOfTime(String strNgay, String strMaNHKB,
                                  String strMaNH) throws Exception {
        Vector v_param = new Vector();
        v_param.add(new Parameter("", false));
        v_param.add(new Parameter(strNgay, true));
        v_param.add(new Parameter(strMaNHKB, true));
        v_param.add(new Parameter(strMaNH, true));
        String strFunctionName = "SP_UTIL_PKG.FNC_GET_TSO_CUTOFTIME";
        return executeFunction(strFunctionName, v_param, this.conn).toString();
    }

    /**
     * @param: strNgay=DD/MM/YYYY; strMaNHKB=Ma 8 so cua KB; strMaNH = Ma 8 so NH
     * @return: Gio cutoftime HH24:MI
     * @see: Ham lay ra ngay gio cut of time
     * */
    public Date getNgayGioCutOfTime(String strNgay, String strMaNHKB,
                                    String strMaNH) throws Exception {
        TTSPUtils u = new TTSPUtils(conn);
        String strGioCOT = u.getGioCutOfTime(strNgay, strMaNHKB, strMaNH);
        String strCurrDate = StringUtil.getCurrentDate();
        return StringUtil.StringToDate(strCurrDate + strGioCOT,
                                       "dd/MM/yyyyHH:mm");
    }

    /**
     * @param: strMTID: so lenh; strLoaiDien: Loai dien (103, 195 ...); strIsLenhDi: co la lenh di ko (Y/N)
     * @return: Noi dung ky
     * @see: Ham lay noi dung ky
     * */
    public String getNoiDungKy(String strMTID, String strLoaiDien,
                               String strIsLenhDi) throws Exception {
        CallableStatement cs = null;
        cs =
 conn.prepareCall("{call sp_util_pkg.prc_lay_ndung_ky(?,?,?,?,?,?)}");
        cs.setString(1, strMTID);
        cs.setString(2, strLoaiDien);
        cs.setString(3, strIsLenhDi);
        cs.registerOutParameter(4, java.sql.Types.VARCHAR);
        cs.registerOutParameter(5, java.sql.Types.VARCHAR);
        cs.registerOutParameter(6, java.sql.Types.VARCHAR);

        cs.execute();

        String strNoiDungKy = cs.getString(4);
        String strErrorCode = cs.getString(5);
        String strErrorDesc = cs.getString(6);
        if ("00".equals(strErrorCode)) {
            return strNoiDungKy;
        } else {
            throw new Exception("TTSPUtils.getNoiDungKy: " + strErrorDesc);
        }
    }

    /**
     * @param:
     * @return:
     * @see: Lock cho viec update
     * */
    public String setLock(String strID, String strType,
                          Long strUserID) throws Exception {
        CallableStatement cs = null;
        cs =
 conn.prepareCall("{call sp_check_forupdate_pkg.proc_setlock(?,?,?)}");
        cs.setString(1, strID + strType);
        cs.setLong(2, strUserID);
        cs.registerOutParameter(3, java.sql.Types.VARCHAR);
        cs.execute();

        return cs.getString(3);
    }

    /**
     * @param:
     * @return:
     * @see: UNLock cho viec update
     * */
    public void unLock(String strID, String strType) throws Exception {
        CallableStatement cs = null;
        cs = conn.prepareCall("{call sp_check_forupdate_pkg.proc_unlock(?)}");
        cs.setString(1, strID + strType);
        cs.execute();
    }

    public Long getSoDuOnlineTKTheoHThongNH(String strMa3SoNH,
                                            String strDuPhong) throws Exception {

        Vector v_param = new Vector();
        v_param.add(new Parameter("", false));
        v_param.add(new Parameter(strMa3SoNH, true));
        v_param.add(new Parameter(strDuPhong, true));

        String strFunctionName = "SP_UTIL_PKG.fnc_get_tong_sodu";
        Long lSoDu =
            new Long(executeFunction(strFunctionName, v_param, this.conn).toString());
        if (lSoDu == 0) {
            throw new Exception("Khong lay duoc tong so du cac tk tai NH " +
                                strMa3SoNH);
        }
        return lSoDu;
    }

    /**
     * @param: 
     * @return: 
     * @see: Ham kiem tra tai khoan chi ngan sach ngoai te
     * */
    public String checkTKNSNgoaiTe(String strTKNS, String strMaNT) throws Exception {
//        Vector v_param = new Vector();
//        v_param.add(new Parameter("", false));
//        v_param.add(new Parameter(strTKNS, true));
//        v_param.add(new Parameter(strMaNT, true));
//        String strFunctionName = "SP_UTIL_PKG.fnc_check_tk_chi_ngoai_te";
//        return executeFunction(strFunctionName, v_param, this.conn).toString();        
        return "00";
    }
  /**
   * @param: 
   * @return: 
   * @see: Chan lenh ngay cuoi nam; Neu da qua gio cho phep tra ve gio chan, neu ko tra ve rong
   * */
  /*public String chanLenhDiNgayCuoiNam() throws Exception {
    String strCurDate = StringUtil.getCurrentDate();    
    String strCurHours = StringUtil.DateToString(new Date(), "HHmm");
    int nCurHours = Integer.parseInt(strCurHours);
    
      if(strCurDate != null){
          //Kiem tra do dai date hop le
          if(strCurDate.length() > 6){
            //Kiem tra ngay 31/12
            if("31/12".equals(strCurDate.substring(0, 5))){
              //Sau 17 gio moi thuc hien kiem tra      
              if(nCurHours > 1600){
              //Lay tham so ht  
              ThamSoHThongDAO tsDAO = new ThamSoHThongDAO(conn);
              ThamSoHThongVO tsVO = tsDAO.getThamSo("a.ten_ts = 'TGIAN_CHAN_LENH_DI_NGAY_CUOI_NAM'", null);                        
              if(tsVO != null){
                  if(tsVO.getGiatri_ts() != null){
                    int nGiaTriTS = Integer.parseInt(tsVO.getGiatri_ts().replace(":", ""));                  
                    //Kiem tra gio hien tai da qua gio chan chua
                    if(nCurHours > nGiaTriTS){
                      return tsVO.getGiatri_ts();
                    }
                  }
              }            
            }
          }
      }
    }
    return "";
  } */ 
  
  
  /**
   * @create: thuongdt
   * @create date: 15/02/2017
   * @param: ngay truyen vao dinh dang dd/mm/yyyy
   * @return:  tra ve ngay truoc 1 ngay cua ngay tham so truyen vao
   * @see: lay ngay hom truoc
   * @find: thuongdt-20170215
   * */
   public String previousDay(String date){
          String[] ymd = date.split("/");
          int year = Integer.parseInt(ymd[2]);
          int month = Integer.parseInt(ymd[1]);
          int day = Integer.parseInt(ymd[0]);
          
          String monthTemp = "";
          String dateTemp = "";
          String newDate = "";
          if (day > 1 & month > 1){
              dateTemp = (day-1)>9?""+(day-1):"0"+(day-1);
              monthTemp = (month)>9?""+(month):"0"+(month);
              newDate = (dateTemp)+"/"+monthTemp+"/"+year;
          }else if (day == 1 & month > 1) {
              Calendar calendar = new GregorianCalendar(year,month-1, 1);
              int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);              
              dateTemp = (daysInMonth)>9?""+(daysInMonth):"0"+(daysInMonth);
              monthTemp = (month-1)>9?""+(month-1):"0"+(month-1);
              newDate =dateTemp +"/"+monthTemp+"/"+ year;
          } else if (day == 1 & month == 1) {
              Calendar calendar = new GregorianCalendar(year,12, 1);
              int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
              dateTemp = (daysInMonth)>9?""+(daysInMonth):"0"+(daysInMonth);
              newDate = dateTemp +"/"+12+"/"+year;
          }
          return newDate;
      }
  
  
    public static void main(String[] args) {
        try {

          

            String str = StringUtil.DateToString(new Date(), "HHmm");


//            AppDAO a = new AppDAO(); //TTSP_KBA 130801 000006
//            Connection conn = a.getConnectionTest();
//            //
//            TTSPUtils u = new TTSPUtils(conn);
//            String s = u.checkTKNSNgoaiTe("1121", "USD");
//            //u.unLock("strID", "strType");
//            String mt_id = u.getMT_ID("066", "");
            //            Long sodu = u.getSoDuOnlineTKTheoHThongNH("202", "");
            //            System.out.println(sodu);


            //          String strNgayLamViec = StringUtil.DateToString(new Date(), "yyyyMM")+"01";
            //          strNgayLamViec = u.getNgayLamViec(strNgayLamViec);
            //          strNgayLamViec = strNgayLamViec.substring(6,2);


            //String ac = u.getNoiDungKy("1370119500003994", "196","N");
            //            System.out.println(ac);
            System.out.println("acv");
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }

    }
}
