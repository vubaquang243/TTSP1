package com.seatech.ttsp.trienkhai.action;

import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;

import com.seatech.framework.utils.DateUtils;
import com.seatech.framework.utils.StringUtil;
import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.cutoftime.TSoCutOfTimeDAO;
import com.seatech.ttsp.cutoftime.TSoCutOfTimeVO;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.dmnh.DMNHangDAO;
import com.seatech.ttsp.dmnh.DMNHangVO;
import com.seatech.ttsp.dmnhkb.DMNHKBacDAO;
import com.seatech.ttsp.dmnhkb.DMNHKBacVO;
import com.seatech.ttsp.qlydmsohieukbmanh.dmsohieukbmadvsdns.DMSoHieuKBMaDVSDNSDAO;
import com.seatech.ttsp.qlydmsohieukbmanh.dmsohieukbmadvsdns.DMSoHieuKBMaDVSDNSVO;
import com.seatech.ttsp.tknhkb.TKNHKBacDAO;
import com.seatech.ttsp.tknhkb.TKNHKBacVO;
import com.seatech.ttsp.trienkhai.form.TrienKhaiForm;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * @modify: thuongdt
 * @date: 20/03/3017
 * @see: lay thong tin gio COT thay vi fig cung trong code
 * @find: thuongdt-20170320
 * */

 /**
  * @modify: thuongdt
  * @date: 18/04/3017
  * @see: cap nhat gio cot theo form
  * @find: thuongdt-20170418
  * */
public class TrienKhaiAction extends AppAction {
    private static String SUCCESS = "success";

    public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        Connection conn = null;
        String strStep = "";
        TrienKhaiForm tkForm = null;
        PreparedStatement pstt = null;
        ResultSet rs = null;
        String strNSD = "";
        try {
            conn = getConnection();
            tkForm = (TrienKhaiForm)form;
            strStep = tkForm.getStep();
            String strType = tkForm.getType();
            String strTypeSrv = tkForm.getType_srv();
            tkForm.setExeption("");
            //STEP 1: DM_HTKB
            if ("1".equals(strStep)) {
                DMKBacDAO dmDAO = new DMKBacDAO(conn);
                if ("check".equals(strType)) {
                    String strMa = tkForm.getMa();
                    String strWhere = "a.ma = ? and a.tinhtrang = '1'";
                    Vector vParam = new Vector();
                    vParam.add(new Parameter(strMa, true));
                    Collection colKBList = dmDAO.getDMKBList(strWhere, vParam);
                    DMKBacVO dmVO = null;
                    if (colKBList != null) {
                        Iterator iter = colKBList.iterator();
                        while (iter.hasNext()) {
                            dmVO = (DMKBacVO)iter.next();
                        }
                    }
                    if (dmVO != null) {
                        tkForm.setShkb_id(dmVO.getId());
                        tkForm.setShkb(dmVO.getMa());
                        tkForm.setTen(dmVO.getTen());
                        tkForm.setMa_cha(dmVO.getMa_cha());
                        tkForm.setId_cha(dmVO.getId_cha());
                        tkForm.setMa_t(dmVO.getMa_t());
                        tkForm.setMa_h(dmVO.getMa_h());
                        tkForm.setCap(dmVO.getCap());
                        tkForm.setType_srv("UPDATE");
                    } else {
                        tkForm.setShkb_id(null);
                        tkForm.setShkb(tkForm.getMa());
                        tkForm.setTen(null);
                        tkForm.setMa_cha(null);
                        tkForm.setId_cha(null);
                        tkForm.setMa_t(null);
                        tkForm.setMa_h(null);
                        tkForm.setCap(null);
                        tkForm.setType_srv("INSERT");
                    }
                } else if ("save".equals(strType)) {
                    DMKBacVO dmVO = new DMKBacVO();
                    dmVO.setMa(tkForm.getShkb());
                    dmVO.setTen(tkForm.getTen());
                    dmVO.setMa_cha(tkForm.getMa_cha());
                    dmVO.setId_cha(tkForm.getId_cha());
                    dmVO.setMa_t(tkForm.getMa_t());
                    dmVO.setMa_h(tkForm.getMa_h());
                    dmVO.setCap(tkForm.getCap());
                    if ("INSERT".equalsIgnoreCase(strTypeSrv)) {
                        dmVO.setTinhtrang("1");
                        dmDAO.insert(dmVO);
                        tkForm.setType_srv("UPDATE");
                    } else if ("UPDATE".equalsIgnoreCase(strTypeSrv)) {
                        dmVO.setId(tkForm.getShkb_id());
                        dmDAO.update(dmVO);
                    }
                } else if ("next".equals(strType)) {
//**********************************************************Cap nhat tham so****
                    String strSQLSelectTSo = "SELECT count(0) FROM sp_thamso_kb WHERE kb_id = "+ tkForm.getShkb_id();
                    pstt = conn.prepareStatement(strSQLSelectTSo);
                    rs = pstt.executeQuery();
                    int rownum = 0;
                    while (rs.next()) {
                        rownum = rs.getInt(1);
                    }
                    if (rownum == 0) {                    
                      String strSQLCapNhatThamSo = "INSERT INTO sp_thamso_kb(ten_ts, giatri_ts, mo_ta, cho_phep_sua, kb_id, "+
                             "ngay_cap_nhat, dvi_sua, ma_nsd) "+
                             "SELECT a.ten_ts, 'N', a.mo_ta, a.cho_phep_sua, " + tkForm.getShkb_id() + ", "+
                             "SYSDATE, a.dvi_sua, '" + strNSD + "' "+
                             "FROM sp_thamso_kb a where a.kb_id = 3 ";
  
                      pstt = conn.prepareStatement(strSQLCapNhatThamSo);
                      pstt.executeUpdate();
                    }
//******************************************************************************
                    tkForm.setType_srv(null);
                    tkForm.setMa(null);
                    tkForm.setTen(null);
                    strStep = "2";
                }
            } else if ("2".equals(strStep)) {
                //STEP 2: DM MANH KB
                DMNHangDAO dmDAO = new DMNHangDAO(conn);
                if ("check".equals(strType)) {
                    String strMa = tkForm.getMa();
                    String strWhere = "a.ma_nh = ? and a.tinh_trang = '1'";
                    Vector vParam = new Vector();
                    vParam.add(new Parameter(strMa, true));
                    DMNHangVO dmVO = dmDAO.getDMNH(strWhere, vParam);

                    if (dmVO != null) {
                        tkForm.setNh_id(dmVO.getId());
                        tkForm.setMa_nh(dmVO.getMa_nh());
                        tkForm.setTen(dmVO.getTen());
                        tkForm.setType_srv("UPDATE");
                    } else {
                        tkForm.setNh_id(null);
                        tkForm.setMa_nh(tkForm.getMa());
                        tkForm.setTen(null);
                        tkForm.setType_srv("INSERT");
                    }
                } else if ("save".equals(strType)) {
                    DMNHangVO dmVO = new DMNHangVO();
                    dmVO.setMa_nh(tkForm.getMa_nh());
                    dmVO.setTen(tkForm.getTen());
                    if ("INSERT".equalsIgnoreCase(strTypeSrv)) {
                        dmVO.setTinh_trang("1");
                        dmDAO.insert(dmVO);
                        tkForm.setType_srv("UPDATE");
                    } else if ("UPDATE".equalsIgnoreCase(strTypeSrv)) {
                        dmVO.setId(tkForm.getNh_id());
                        dmDAO.update(dmVO);
                    }
                } else if ("next".equals(strType)) {
                    //Kiem tra DM_MANH_SHKB
                    DMNHKBacDAO dao = new DMNHKBacDAO(conn);
                    String strWhere = " a.ma_nh = ? AND a.shkb = ? ";
                    Vector vParam = new Vector();
                    vParam.add(new Parameter(tkForm.getMa_nh(), true));
                    vParam.add(new Parameter(tkForm.getShkb(), true));
                    DMNHKBacVO vo = dao.getDMNHKBac(strWhere, vParam);
                    if (vo != null) {
                        tkForm.setType_srv("UPDATE");
                    } else {
                        tkForm.setType_srv("INSERT");
                    }
                    //
                    tkForm.setMa(null);
                    strStep = "3";
                }
            } else if ("3".equals(strStep)) {
                //STEP 3: MANH_SHKB
                if ("save".equals(strType)) {
                    DMNHKBacDAO dmDAO = new DMNHKBacDAO(conn);
                    String strWhere = " a.ma_nh = ? OR a.shkb = ? ";
                    Vector vParam = new Vector();
                    vParam.add(new Parameter(tkForm.getMa_nh(), true));
                    vParam.add(new Parameter(tkForm.getShkb(), true));
                    Collection coll = dmDAO.getDMNHKBacList(strWhere, vParam);

                    if (coll != null) {
                        if (coll.size() > 0) {
                            throw new Exception("Them DM lien he(SP_DM_MANH_SHKB) khong thanh cong. Ma NH hoac SHKB nay da duoc ghep voi cac ma tuong ung khac. Lien he quan tri tin hoc kiem tra.");
                        }
                    }

                    DMNHKBacVO dmVO = new DMNHKBacVO();
                    dmVO.setMa_nh(tkForm.getMa_nh());
                    dmVO.setShkb(tkForm.getShkb());
                    dmVO.setTen(tkForm.getTen());
                    if ("INSERT".equalsIgnoreCase(strTypeSrv)) {
                        dmDAO.insert(dmVO);
                        tkForm.setType_srv("UPDATE");
                    }
                } else if ("next".equals(strType)) {
                    DMSoHieuKBMaDVSDNSDAO dao =
                        new DMSoHieuKBMaDVSDNSDAO(conn);
                    String strWhere = " ma_kb = ? ";
                    Vector vParam = new Vector();
                    vParam.add(new Parameter(tkForm.getShkb(), true));
                    DMSoHieuKBMaDVSDNSVO vo =
                        dao.getDMSoHieuKBMaDVSDNS(strWhere, vParam);
                    if (vo != null) {
                        tkForm.setMa_dvsdns(vo.getMa_dvsdns());
                        tkForm.setType_srv("UPDATE");
                    } else {
                        tkForm.setType_srv("INSERT");
                    }

                    tkForm.setMa(null);
                    strStep = "4";
                }
            } else if ("4".equals(strStep)) {
                //STEP 4: SHKB-DVSDNS
                if ("save".equals(strType)) {
                    DMSoHieuKBMaDVSDNSDAO dao =
                        new DMSoHieuKBMaDVSDNSDAO(conn);

                    DMSoHieuKBMaDVSDNSVO vo = new DMSoHieuKBMaDVSDNSVO();
                    vo.setMa_kb(tkForm.getShkb());
                    vo.setMa_dvsdns(tkForm.getMa_dvsdns());
                    if ("INSERT".equalsIgnoreCase(strTypeSrv)) {
                        dao.insert(vo);
                        tkForm.setType_srv("UPDATE");
                    } else if ("UPDATE".equalsIgnoreCase(strTypeSrv)) {
                        dao.update(vo);
                    }
                } else if ("next".equals(strType)) {
                    tkForm.setType_srv(null);
                    tkForm.setMa(null);
                    strStep = "4_";
                }
            } else if ("4_".equals(strStep)) {
                //STEP 4_:MA CN NH
                DMNHangDAO dmDAO = new DMNHangDAO(conn);
                if ("check".equals(strType)) {
                    String strMa = tkForm.getMa();
                    String strWhere = "a.ma_nh = ? and a.tinh_trang = '1'";
                    Vector vParam = new Vector();
                    vParam.add(new Parameter(strMa, true));
                    DMNHangVO dmVO = dmDAO.getDMNH(strWhere, vParam);

                    if (dmVO != null) {
                        tkForm.setNh_id(dmVO.getId());
                        tkForm.setMa_cn_nh(dmVO.getMa_nh());
                        tkForm.setTen(dmVO.getTen());
                        tkForm.setType_srv("UPDATE");
                    } else {
                        tkForm.setNh_id(null);
                        tkForm.setMa_cn_nh(tkForm.getMa());
                        tkForm.setTen(null);
                        tkForm.setType_srv("INSERT");
                    }
                } else if ("save".equals(strType)) {
                    DMNHangVO dmVO = new DMNHangVO();
                    dmVO.setMa_nh(tkForm.getMa_cn_nh());
                    dmVO.setTen(tkForm.getTen());
                    if ("INSERT".equalsIgnoreCase(strTypeSrv)) {
                        dmVO.setTinh_trang("1");
                        dmDAO.insert(dmVO);
                        tkForm.setType_srv("UPDATE");
                    } else if ("UPDATE".equalsIgnoreCase(strTypeSrv)) {
                        dmVO.setId(tkForm.getNh_id());
                        dmDAO.update(dmVO);
                    }
                } else if ("next".equals(strType)) {
                    tkForm.setType_srv(null);
                    tkForm.setMa(tkForm.getMa_cn_nh());
                    strStep = "5";
                }
            } else if ("5".equals(strStep)) {
                //STEP 5: TKNHKB
                TKNHKBacDAO dao = new TKNHKBacDAO(conn); 
                TTSPUtils spUtil = new TTSPUtils(conn);
                TKNHKBacVO vo = null;
                if ("check".equals(strType)) {
                    String strWhere =
                        " AND b.ma_nh = ? AND c.ma = ? AND a.ma_nt = ? AND a.trang_thai = ? AND a.loai_tk = ? AND a.loai_gd = ?"; //chinh s?a ng�y 12/3 th�m t�i kho?n
                    Vector vParam = new Vector();
                    vParam.add(new Parameter(tkForm.getMa(), true));
                    vParam.add(new Parameter(tkForm.getShkb(), true));
                    vParam.add(new Parameter(tkForm.getLoai_tien_kt().toUpperCase(), true));
                    vParam.add(new Parameter("01", true));
                    vParam.add(new Parameter(tkForm.getLoai_tk(),true)); //chinh sua ng�y 12/3 th�m t�i khoan
                    vParam.add(new Parameter(tkForm.getLoai_gd(),true)); //chinh sua ng�y 14/3 th�m loai gd
                    
                    Collection coll = dao.getTK_NH_KB(strWhere, vParam);
                    if (coll != null) {
                        Iterator iter = coll.iterator();
                        while (iter.hasNext()) {
                            vo = (TKNHKBacVO)iter.next();
                        }
                    }
                    if (vo != null) {
                        tkForm.setMa_cn_nh(tkForm.getMa());
                        tkForm.setLoai_tien(tkForm.getLoai_tien_kt().toUpperCase());
                        tkForm.setId(vo.getId());
                        tkForm.setHan_muc_co(vo.getHan_muc_co());
                        tkForm.setHan_muc_no(vo.getHan_muc_no());
                        tkForm.setLoai_gd(vo.getLoai_gd());
                        tkForm.setLoai_tk(vo.getLoai_tk());
                        tkForm.setQuyet_toan(vo.getQuyet_toan());
                        tkForm.setLoai_gd(vo.getLoai_gd());
                        tkForm.setType_srv("UPDATE");
                        tkForm.setSo_tk(vo.getSo_tk());
                    } else {
                        tkForm.setMa_cn_nh(tkForm.getMa());
                        tkForm.setLoai_tien(tkForm.getLoai_tien_kt());
                        tkForm.setSo_tk("");
                        tkForm.setHan_muc_co(0L);
                        tkForm.setHan_muc_no(0L);
                        tkForm.setType_srv("INSERT");
                    }
                } else if ("save".equals(strType)) {
                    vo = new TKNHKBacVO();
                    vo.setLoai_gd(tkForm.getLoai_gd());
                    vo.setLoai_tk(tkForm.getLoai_tk());
                    vo.setHan_muc_co(tkForm.getHan_muc_co());
                    vo.setHan_muc_no(tkForm.getHan_muc_no());
                    vo.setTrang_thai("01");
                    vo.setQuyet_toan(tkForm.getQuyet_toan());
                    vo.setSo_tk(tkForm.getSo_tk());
                    vo.setMa_nt(tkForm.getLoai_tien());
                    if ("INSERT".equalsIgnoreCase(strTypeSrv)) {
                        //Lay SHKB_ID
                        DMKBacDAO dmDAO = new DMKBacDAO(conn);
                        
                        String strSHKB = tkForm.getShkb();
                        String strWhere = "a.ma = ? and a.tinhtrang = '1'";
                        Vector vParam = new Vector();
                        vParam.add(new Parameter(strSHKB, true));
                        Collection colKBList =
                            dmDAO.getDMKBList(strWhere, vParam);
                        DMKBacVO shkbVO = null;
                        if (colKBList != null) {
                            Iterator iter = colKBList.iterator();
                            while (iter.hasNext()) {
                                shkbVO = (DMKBacVO)iter.next();
                            }
                        }
                        if (shkbVO != null) {
                            vo.setKb_id(shkbVO.getId());
                        }
                        //----------
                        //Lay NH_ID
                        DMNHangDAO nhDAO = new DMNHangDAO(conn);

                        String strCNNH = tkForm.getMa_cn_nh();
                        strWhere = "a.ma_nh = ? and a.tinh_trang = '1'";
                        vParam = new Vector();
                        vParam.add(new Parameter(strCNNH, true));
                        DMNHangVO nhVO = nhDAO.getDMNH(strWhere, vParam);
                        if (nhVO != null) {
                            vo.setNh_id(nhVO.getId());
                        }
                        //----------
                        

                        dao.insert(vo);
                        tkForm.setType_srv("UPDATE");
                    } else if ("UPDATE".equalsIgnoreCase(strTypeSrv)) {
                        vo.setId(tkForm.getId());
                        dao.update(vo);
                    }
                } else if ("next".equals(strType)) {
                    //Chinh sua ng�y 12/3 lay ra sp_ngay_trien_khai d� khai roi
                    String sqlSelectTrienKhai = "select to_char(ngay_trien_khai,'dd/mm/yyyy') from sp_ngay_trien_khai where ma_kb = ? and ma_nh = ? and ma_nt = ?";
                    pstt = conn.prepareStatement(sqlSelectTrienKhai);
                    pstt.setString(1, tkForm.getMa_nh());
                    pstt.setString(2, tkForm.getMa_cn_nh());
                    pstt.setString(3, tkForm.getLoai_tien());
                    rs = pstt.executeQuery();
                    if(rs.next()){
                        tkForm.setNgay_tk(rs.getString(1));
                        //khi da co ngay tk mo nut next cho nguoi dung
                        tkForm.setType_srv("UPDATE");
                    }else{
                        tkForm.setType_srv("INSERT");
                    }
                    tkForm.setMa(null);
                    strStep = "6";
                }
            } else if ("6".equals(strStep)) {                
                //STEP 6-Ngay trien khai
                if ("save".equals(strType)) {
                    if ("INSERT".equalsIgnoreCase(strTypeSrv) || "UPDATE".equalsIgnoreCase(strTypeSrv)) {
                        String strSQLDelNgayTK =
                            "delete sp_ngay_trien_khai where ma_kb = ? and ma_nh = ? and ma_nt =? and TO_CHAR(ngay_trien_khai,'yyyyMMdd') >= ? ";
                        pstt = conn.prepareStatement(strSQLDelNgayTK);
                        pstt.setString(1, tkForm.getMa_nh());
                        pstt.setString(2, tkForm.getMa_cn_nh());
                        pstt.setString(3, tkForm.getLoai_tien());
                        pstt.setLong(4,
                                     DateUtils.DateToLong(tkForm.getNgay_tk()));
                        pstt.executeUpdate();

                        String strSQL =
                            "insert into sp_ngay_trien_khai(id, ma_kb, ma_nh, ma_nt, ngay_trien_khai, ghi_chu) VALUES(sp_ngay_trien_khai_seq.nextval,?,?,?,TO_DATE(?,'dd/mm/yyyy'),?)";
                        pstt = conn.prepareStatement(strSQL);
                        pstt.setString(1, tkForm.getMa_nh());
                        pstt.setString(2, tkForm.getMa_cn_nh());
                        pstt.setString(3, tkForm.getLoai_tien());
                        pstt.setString(4, tkForm.getNgay_tk());
                        pstt.setString(5,
                                       "Thiet lap trien khai ngay " + StringUtil.DateToString(new Date(),
                                                                                              "dd/MM/yyyy"));
                        pstt.executeUpdate();
                        tkForm.setType_srv("UPDATE");
                        }
                        

                        
                    
                } else if ("next".equals(strType)) {
                    TTSPUtils spUtil = new TTSPUtils(conn);
                    String strDateTK = StringUtil.previousDateString(tkForm.getNgay_tk(),"dd/MM/yyyy","yyyyMMdd");
                    strDateTK = spUtil.getNgayLamViecTruoc(strDateTK);
                    tkForm.setNgay_du_cuoi(StringUtil.DateToString(StringUtil.StringToDate(strDateTK, "yyyyMMdd"), "dd/MM/yyyy"));
                    //Chinh 2015/03/14 lay so du
                    String strSQL = "select so_du from sp_so_du where ma_kb = ? and ma_nh = ? and loai_tien =? and ngay_gd = TO_DATE(?,'dd/mm/yyyy')";
                    pstt = conn.prepareStatement(strSQL);
                    pstt.setString(1, tkForm.getMa_nh());
                    pstt.setString(2, tkForm.getMa_cn_nh());
                    pstt.setString(3, tkForm.getLoai_tien());
                    pstt.setString(4, tkForm.getNgay_du_cuoi());
                    rs = pstt.executeQuery();
                    if(rs.next()){
                        tkForm.setSo_du(rs.getBigDecimal(1));
                    }
                    tkForm.setType_srv("INSERT");
                    tkForm.setMa(null);
                    strStep = "7";                   
       
                }
            } else if ("7".equals(strStep)) {
                //STEP 7: SO DU
                if ("save".equals(strType)) {
                    if ("INSERT".equalsIgnoreCase(strTypeSrv)) {
                        String strSQL =
                            "select count(0) from sp_so_du where ma_kb = ? and ma_nh = ? and loai_tien =? and ngay_gd = TO_DATE(?,'dd/mm/yyyy')";
                        pstt = conn.prepareStatement(strSQL);
                        pstt.setString(1, tkForm.getMa_nh());
                        pstt.setString(2, tkForm.getMa_cn_nh());
                        pstt.setString(3, tkForm.getLoai_tien());
                        pstt.setString(4, tkForm.getNgay_du_cuoi());
                        rs = pstt.executeQuery();
                        int rownum = 0;
                        while (rs.next()) {
                            rownum = rs.getInt(1);
                        }
                        if (rownum > 0) {
                            strSQL =
                                    "update sp_so_du set so_du =?, so_du_cot = ? where ma_kb = ? and ma_nh = ? and loai_tien =? and ngay_gd = TO_DATE(?,'dd/mm/yyyy')";
                            pstt = conn.prepareStatement(strSQL);
                            pstt.setBigDecimal(1, tkForm.getSo_du());
                            pstt.setBigDecimal(2, tkForm.getSo_du());
                            pstt.setString(3, tkForm.getMa_nh());
                            pstt.setString(4, tkForm.getMa_cn_nh());
                            pstt.setString(5, tkForm.getLoai_tien());
                            pstt.setString(6, tkForm.getNgay_du_cuoi());
                        } else {

                            strSQL =
                                    "insert into sp_so_du(id, ma_kb, ma_nh, ngay_gd, so_du, insert_date, so_du_cot, loai_tien)" +
                                    " values(sp_so_du_seq.nextval,?,?,TO_DATE(?,'dd/mm/yyyy'),?,SYSDATE,?,?)";
                            pstt = conn.prepareStatement(strSQL);
                            pstt.setString(1, tkForm.getMa_nh());
                            pstt.setString(2, tkForm.getMa_cn_nh());
                            pstt.setString(3, tkForm.getNgay_du_cuoi());
                            pstt.setBigDecimal(4, tkForm.getSo_du());
                            pstt.setBigDecimal(5, tkForm.getSo_du());
                            pstt.setString(6, tkForm.getLoai_tien());
                        }

                        pstt.executeUpdate();

                        tkForm.setType_srv("UPDATE");
                    }
                } else if ("next".equals(strType)) {                  
                    // thuongdt-20170320 lay lai gio COT theo gio pho bien trong he thng begin                    
                    String gio_cot = "16:00"; 
                    String ma_nh = tkForm.getMa_cn_nh().substring(2, 5);
                    String whereClause = " and to_char(a.ngay_gd,'dd/MM/yyyy') = '"+tkForm.getNgay_tk()+"' and ma_nh like '__"+ma_nh+"%'";
                    TSoCutOfTimeDAO tsDAO = new TSoCutOfTimeDAO(conn);
                    TSoCutOfTimeVO tsVO = new TSoCutOfTimeVO();
                    tsVO = tsDAO.getTSoCTONormal(whereClause,null); 
                    if(tsVO != null && tsVO.getCut_of_time() != null && !"".equals(tsVO.getCut_of_time()))
                        gio_cot = tsVO.getCut_of_time();                    
                  // thuongdt-20170320 lay lai gio COT theo gio pho bien trong he thng end    
                    tkForm.setCut_of_time(gio_cot);
                    tkForm.setType_srv("INSERT");
                    tkForm.setMa(null);
                    strStep = "8";
                }
            } else if ("8".equals(strStep)) {                
                if ("save".equals(strType)) {
                    if ("INSERT".equalsIgnoreCase(strTypeSrv)) {
                      //thuongdt-20170418 cap nhat gio cot theo form begin
                        CallableStatement cs = null;
                        cs = conn.prepareCall("{call sp_init_pkg.proc_thiet_lap_cutoftime(?,?,?,?,?)}");
                        cs.setString(1, tkForm.getMa_nh());
                        cs.setString(2, tkForm.getMa_cn_nh());
                        cs.setLong(3, 1325);
                        cs.setString(4, tkForm.getNgay_tk());
                        cs.setString(5, tkForm.getCut_of_time());
                        cs.execute();
                      //thuongdt-20170418 cap nhat gio cot theo form end
                    }
                    strStep = "9";
                }
            }
            conn.commit();
        } catch (Exception ex) {
            if (tkForm != null) {
                tkForm.setExeption(ex.getMessage());
            } else {
                throw ex;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstt != null) {
                pstt.close();
            }
            close(conn);
        }
        if (strStep == null) {
            strStep = "1";
        }
        return mapping.findForward("step_" + strStep);
    }
}
