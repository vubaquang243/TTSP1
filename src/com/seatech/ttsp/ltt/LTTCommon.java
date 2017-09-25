package com.seatech.ttsp.ltt;


import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.utils.DateUtils;
import com.seatech.ttsp.dmcapns.DMCapNSDAO;
import com.seatech.ttsp.dmcapns.DMCapNSVO;
import com.seatech.ttsp.dmchuong.DMChuongDAO;
import com.seatech.ttsp.dmchuong.DMChuongVO;
import com.seatech.ttsp.dmctmt.DMCTMTieuDAO;
import com.seatech.ttsp.dmctmt.DMCTMTieuVO;
import com.seatech.ttsp.dmdb.DMDiaBanDAO;
import com.seatech.ttsp.dmdb.DMDiaBanVO;
import com.seatech.ttsp.dmdp.DMDuPhongDAO;
import com.seatech.ttsp.dmdp.DMDuPhongVO;
import com.seatech.ttsp.dmdvsdns.DMDonViSdnsDAO;
import com.seatech.ttsp.dmdvsdns.DMDonViSdnsVO;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.dmmaquy.DMMaQuyDAO;
import com.seatech.ttsp.dmmaquy.DMMaQuyVO;
import com.seatech.ttsp.dmmn.DMMaNguonDAO;
import com.seatech.ttsp.dmmn.DMMaNguonVO;
import com.seatech.ttsp.dmndkt.DMNDKTeDAO;
import com.seatech.ttsp.dmndkt.DMNDKTeVO;
import com.seatech.ttsp.dmnganhkt.DMNganhKTDAO;
import com.seatech.ttsp.dmnganhkt.DMNganhKTVO;
import com.seatech.ttsp.dmnh.DMNHangDAO;
import com.seatech.ttsp.dmnh.DMNHangVO;
import com.seatech.ttsp.dmtkkt.DMTKKTDAO;
import com.seatech.ttsp.dmtkkt.DMTKKTVO;
import com.seatech.ttsp.proxy.dm.DMCheo;

import java.sql.Connection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;


public class LTTCommon {
    public LTTCommon() {
        super();
    }

    public static DMNHangVO getDMNHang(String whereClau, Vector vParams,
                                       Connection conn) {
        DMNHangVO dmnhVO = null;

        try {
            DMNHangDAO dmnhDao = new DMNHangDAO(conn);

            if (whereClau != null && !"".equalsIgnoreCase(whereClau) &&
                vParams != null) {
                dmnhVO = dmnhDao.getDMNH(whereClau, vParams);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return dmnhVO;
    }

    public static DMNHangVO getDMNHang(Long nhkbId, Connection conn) {
        DMNHangVO dmnhVO = null;
        Vector params = null;
        Parameter param = null;
        String whereClause = "";

        try {
            DMNHangDAO dmnhDao = new DMNHangDAO(conn);
            whereClause = " a.id=? ";
            params = new Vector();
            param = new Parameter(nhkbId, true);
            params.add(param);
            dmnhVO = dmnhDao.getDMNH(whereClause, params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return dmnhVO;
    }

    public static boolean checkExistTkkt(String whereClause, Vector params,
                                         Connection conn) {
        boolean bExist = false;
        try {
            DMTKKTDAO dmTkktDAO = new DMTKKTDAO(conn);
            DMTKKTVO dmTkktVO = null;
            dmTkktVO = dmTkktDAO.getDMTKKT(whereClause, params);
            if (dmTkktVO != null)
                bExist = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bExist;
    }

    public static boolean checkExistMaQuy(String whereClause, Vector params,
                                          Connection conn) {
        boolean bExist = false;
        try {
            DMMaQuyDAO dmMaQuyDAO = new DMMaQuyDAO(conn);
            DMMaQuyVO dmMaQuyVO = null;
            dmMaQuyVO = dmMaQuyDAO.getDMMaQuy(whereClause, params);
            if (dmMaQuyVO != null)
                bExist = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bExist;
    }

    public static boolean checkExistDvsdns(String whereClause, Vector params,
                                           Connection conn) {
        boolean bExist = false;
        try {
            DMDonViSdnsDAO dmDvsdnsDAO = new DMDonViSdnsDAO(conn);
            DMDonViSdnsVO dmDvsdnsVO = null;

            dmDvsdnsVO = dmDvsdnsDAO.getDMDonViSdns(whereClause, params);
            if (dmDvsdnsVO != null)
                bExist = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bExist;
    }

    public static boolean checkExistCapns(String whereClause, Vector params,
                                          Connection conn) {
        boolean bExist = false;
        try {
            DMCapNSDAO dmCapnsDAO = new DMCapNSDAO(conn);
            DMCapNSVO dmCapnsVO = null;

            dmCapnsVO = dmCapnsDAO.getDMCapNS(whereClause, params);
            if (dmCapnsVO != null)
                bExist = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bExist;
    }

    public static boolean checkExistChuongns(String whereClause, Vector params,
                                             Connection conn) {
        boolean bExist = false;
        try {
            DMChuongDAO dmChuongDAO = new DMChuongDAO(conn);
            DMChuongVO dmChuongVO = null;

            dmChuongVO = dmChuongDAO.getDMChuong(whereClause, params);
            if (dmChuongVO != null)
                bExist = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bExist;
    }

    public static boolean checkExistNganhkt(String whereClause, Vector params,
                                            Connection conn) {
        boolean bExist = false;
        try {
            DMNganhKTDAO dmNganhktDAO = new DMNganhKTDAO(conn);
            DMNganhKTVO dmNganhktVO = null;

            dmNganhktVO = dmNganhktDAO.getDMNganhKT(whereClause, params);
            if (dmNganhktVO != null)
                bExist = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bExist;
    }

    public static boolean checkExistNdkt(String whereClause, Vector params,
                                         Connection conn) {
        boolean bExist = false;

        try {
            DMNDKTeDAO dmNdktDAO = new DMNDKTeDAO(conn);
            DMNDKTeVO dmNdktVO = null;

            dmNdktVO = dmNdktDAO.getDMNDKTe(whereClause, params);
            if (dmNdktVO != null)
                bExist = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bExist;
    }

    public static boolean checkExistDbhc(String whereClause, Vector params,
                                         Connection conn) {
        boolean bExist = false;
        try {
            DMDiaBanDAO dmDiaBanDAO = new DMDiaBanDAO(conn);
            DMDiaBanVO dmDiabanVO = null;

            dmDiabanVO = dmDiaBanDAO.getDMDiaBan(whereClause, params);
            if (dmDiabanVO != null)
                bExist = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bExist;
    }

    public static boolean checkExistCtmt(String whereClause, Vector params,
                                         Connection conn) {
        boolean bExist = false;
        try {
            DMCTMTieuDAO dmCtmtDAO = new DMCTMTieuDAO(conn);
            DMCTMTieuVO dmCtmtVO = null;

            dmCtmtVO = dmCtmtDAO.getDMCTMTieu(whereClause, params);
            if (dmCtmtVO != null)
                bExist = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bExist;
    }

    public static boolean checkExistMaNguon(String whereClause, Vector params,
                                            Connection conn) {
        boolean bExist = false;
        try {
            DMMaNguonDAO dmMaNguonDAO = new DMMaNguonDAO(conn);
            DMMaNguonVO dmMaNguonVO = null;

            dmMaNguonVO = dmMaNguonDAO.getDMMaNguon(whereClause, params);
            if (dmMaNguonVO != null)
                bExist = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bExist;
    }

    public static boolean checkExistDuPhong(String whereClause, Vector params,
                                            Connection conn) {
        boolean bExist = false;
        try {
            DMDuPhongDAO dmDuPhongDAO = new DMDuPhongDAO(conn);
            DMDuPhongVO dmDuPhongVO = null;

            dmDuPhongVO = dmDuPhongDAO.getDMDuPhong(whereClause, params);
            if (dmDuPhongVO != null)
                bExist = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bExist;
    }

    public static boolean checkExistKhoBac(String whereClause, Vector params,
                                           Connection conn) {
        boolean bExist = false;
        try {
            DMKBacDAO dmKBacDAO = new DMKBacDAO(conn);
            DMKBacVO dmKBacVO = null;

            dmKBacVO = dmKBacDAO.getDMKB(whereClause, params);
            if (dmKBacVO != null)
                bExist = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bExist;
    }

    public static boolean checkExistSoYCTT(String whereClause, Vector params,
                                           Connection conn) {
        boolean bExist = false;
        try {
            LTTDAO lttDAO = new LTTDAO(conn);
            Collection colLTT = null;
            whereClause += " and d.rv_domain like 'SP_LTT%'";
            colLTT = lttDAO.getLTTDiList(whereClause, params);
            if (colLTT != null && colLTT.size() > 0)
                bExist = true;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bExist;
    }

    public static boolean checkSoYCTT(String whereClause, Vector params,
                                      Connection conn) {
        boolean bExist = false;
        try {
            LTTDAO lttDAO = new LTTDAO(conn);
            Collection colLTT = null;
            colLTT = lttDAO.getLTTDiList(whereClause, params);
            if (colLTT != null && colLTT.size() > 0)
                bExist = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bExist;
    }

    public static boolean isCurTimeLessThanCutOfTime(String strCutOfTime) {
        boolean bResult = false;
        try {
            int hour = 0;
            int minute = 0;
            String[] arrCutOfTime = strCutOfTime.split(":");
            if (arrCutOfTime.length >= 2) {
                hour = Integer.parseInt(arrCutOfTime[0]);
                minute = Integer.parseInt(arrCutOfTime[1]);
            }
            if (DateUtils.getHour() < hour) {
                bResult = true;
            } else if (DateUtils.getHour() == hour) {
                if (DateUtils.getMinute() < minute) {
                    bResult = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bResult;
    }

    public static String getStringAsArray(String strParam) {
        String strSpecialTKTNTmp = "";
        if (strParam != null && !"".equals(strParam)) {
            if (strParam.indexOf(",") != -1) {
                String[] arrSpecial = strParam.split(",");
                for (int i = 0; i < arrSpecial.length; i++) {
                    strSpecialTKTNTmp += ",'" + arrSpecial[i] + "',";
                }
                strSpecialTKTNTmp = strSpecialTKTNTmp.replace(",,", ",");
                strParam =
                        strSpecialTKTNTmp.substring(1, strSpecialTKTNTmp.length() -
                                                    1);
            } else {
                strParam = "'" + strParam + "'";
            }
        } else {
            strParam = "''";
        }

        return strParam;
    }
    // Chuc Them

    /**
     * Check Khi them moi, update cua LTT di
     * getArrayCOA
     * 1. CheckCOA
     * 2. Check SO_YCTT
     * */
    public HashMap getArraysInCOA(HttpServletRequest request) {
        HashMap v_COA = new HashMap();
        v_COA.put("tkkt_ma", request.getParameterValues("tkkt_ma"));
        v_COA.put("ma_quy", request.getParameterValues("ma_quy"));
        v_COA.put("dvsdns_ma", request.getParameterValues("dvsdns_ma"));
        v_COA.put("capns_ma", request.getParameterValues("capns_ma"));
        v_COA.put("chuongns_ma", request.getParameterValues("chuongns_ma"));
        v_COA.put("nganhkt_ma", request.getParameterValues("nganhkt_ma"));
        v_COA.put("ndkt_ma", request.getParameterValues("ndkt_ma"));
        v_COA.put("dbhc_ma", request.getParameterValues("dbhc_ma"));
        v_COA.put("ctmt_ma", request.getParameterValues("ctmt_ma"));
        v_COA.put("ma_nguon", request.getParameterValues("ma_nguon"));
        v_COA.put("du_phong_ma", request.getParameterValues("du_phong_ma"));
        v_COA.put("kb_ma", request.getParameterValues("kb_ma"));
        v_COA.put("dien_giai", request.getParameterValues("dien_giai"));
        v_COA.put("so_tien", request.getParameterValues("so_tien"));
        return v_COA;
    }
    //1

    public String checkCOA(Connection conn, HttpServletRequest request) {
        Parameter param = null;
        String strErrorAt = "";
        String whereClause = "";
        Vector params = null;
        try {
            // Check COA
            HashMap h_COA = getArraysInCOA(request);
            if (h_COA.get("tkkt_ma") != null) {
                String[] arrTkkt_ma = (String[])h_COA.get("tkkt_ma");
                String[] arrMa_quy = (String[])h_COA.get("ma_quy");
                String[] arrDvsdns_ma = (String[])h_COA.get("dvsdns_ma");
                String[] arrCapns_ma = (String[])h_COA.get("capns_ma");
                String[] arrChuongns_ma = (String[])h_COA.get("chuongns_ma");
                String[] arrNganhkt_ma = (String[])h_COA.get("nganhkt_ma");
                String[] arrNdkt_ma = (String[])h_COA.get("ndkt_ma");
                String[] arrDbhc_ma = (String[])h_COA.get("dbhc_ma");
                String[] arrCtmt_ma = (String[])h_COA.get("ctmt_ma");
                String[] arrMa_nguon = (String[])h_COA.get("ma_nguon");
                String[] arrDu_phong_ma = (String[])h_COA.get("du_phong_ma");
                String[] arrMa_kb = (String[])h_COA.get("kb_ma");
                String[] arrSo_tien = (String[])h_COA.get("so_tien");
                for (int i = 0; i < arrTkkt_ma.length; i++) {
                    whereClause = " t.ma = ?";
                    params = new Vector();
                    param = new Parameter(arrTkkt_ma[i].toString(), true);
                    params.add(param);
                    if (!LTTCommon.checkExistTkkt(whereClause, params, conn)) {
                        strErrorAt = "tkkt_ma" + "-" + i;
                        break;
                    }
                    if (!"".equalsIgnoreCase(arrMa_quy[i].toString()) &&
                        !"00".equalsIgnoreCase(arrMa_quy[i].toString())) {
                        whereClause = " m.ma = ?";
                        params = new Vector();
                        param = new Parameter(arrMa_quy[i].toString(), true);
                        params.add(param);
                        if (!LTTCommon.checkExistMaQuy(whereClause, params,
                                                       conn)) {
                            strErrorAt = "ma_quy" + "-" + i;
                            break;
                        }
                    }
                    if (!"".equalsIgnoreCase(arrDvsdns_ma[i].toString()) &&
                        !"0000000".equalsIgnoreCase(arrDvsdns_ma[i].toString())) {
                        whereClause = " a.ma = ?";
                        params = new Vector();
                        param =
                                new Parameter(arrDvsdns_ma[i].toString(), true);
                        params.add(param);
                        if (!LTTCommon.checkExistDvsdns(whereClause, params,
                                                        conn)) {
                            strErrorAt = "dvsdns_ma" + "-" + i;
                            break;
                        }
                    }
                    if (!"".equalsIgnoreCase(arrCapns_ma[i].toString()) &&
                        !"0".equalsIgnoreCase(arrCapns_ma[i].toString())) {
                        whereClause = " c.ma = ?";
                        params = new Vector();
                        param = new Parameter(arrCapns_ma[i].toString(), true);
                        params.add(param);
                        if (!LTTCommon.checkExistCapns(whereClause, params,
                                                       conn)) {
                            strErrorAt = "capns_ma" + "-" + i;
                            break;
                        }
                    }
                    if (!"".equalsIgnoreCase(arrChuongns_ma[i].toString()) &&
                        !"000".equalsIgnoreCase(arrChuongns_ma[i].toString())) {
                        whereClause = " c.ma = ?";
                        params = new Vector();
                        param =
                                new Parameter(arrChuongns_ma[i].toString(), true);
                        params.add(param);
                        if (!LTTCommon.checkExistChuongns(whereClause, params,
                                                          conn)) {
                            strErrorAt = "chuongns_ma" + "-" + i;
                            break;
                        }
                    }
                    if (!"".equalsIgnoreCase(arrNganhkt_ma[i].toString()) &&
                        !"000".equalsIgnoreCase(arrNganhkt_ma[i].toString())) {
                        whereClause = " n.ma = ?";
                        params = new Vector();
                        param =
                                new Parameter(arrNganhkt_ma[i].toString(), true);
                        params.add(param);
                        if (!LTTCommon.checkExistNganhkt(whereClause, params,
                                                         conn)) {
                            strErrorAt = "nganhkt_ma" + "-" + i;
                            break;
                        }
                    }
                    if (!"".equalsIgnoreCase(arrNdkt_ma[i].toString()) &&
                        !"0000".equalsIgnoreCase(arrNdkt_ma[i].toString())) {
                        whereClause = " a.ma = ?";
                        params = new Vector();
                        param = new Parameter(arrNdkt_ma[i].toString(), true);
                        params.add(param);
                        if (!LTTCommon.checkExistNdkt(whereClause, params,
                                                      conn)) {
                            strErrorAt = "ndkt_ma" + "-" + i;
                            break;
                        }
                    }
                    if (!"".equalsIgnoreCase(arrDbhc_ma[i].toString()) &&
                        !"00000".equalsIgnoreCase(arrDbhc_ma[i].toString())) {
                        whereClause = " a.ma = ?";
                        params = new Vector();
                        param = new Parameter(arrDbhc_ma[i].toString(), true);
                        params.add(param);
                        if (!LTTCommon.checkExistDbhc(whereClause, params,
                                                      conn)) {
                            strErrorAt = "dbhc_ma" + "-" + i;
                            break;
                        }
                    }
                    if (!"".equalsIgnoreCase(arrCtmt_ma[i].toString()) &&
                        !"00000".equalsIgnoreCase(arrCtmt_ma[i].toString())) {
                        whereClause = " a.ma = ?";
                        params = new Vector();
                        param = new Parameter(arrCtmt_ma[i].toString(), true);
                        params.add(param);
                        if (!LTTCommon.checkExistCtmt(whereClause, params,
                                                      conn)) {
                            strErrorAt = "ctmt_ma" + "-" + i;
                            break;
                        }
                    }
                    if (!"".equalsIgnoreCase(arrMa_nguon[i].toString()) &&
                        !"00".equalsIgnoreCase(arrMa_nguon[i].toString())) {
                        whereClause = " a.ma = ?";
                        params = new Vector();
                        param = new Parameter(arrMa_nguon[i].toString(), true);
                        params.add(param);
                        if (!LTTCommon.checkExistMaNguon(whereClause, params,
                                                         conn)) {
                            strErrorAt = "ma_nguon" + "-" + i;
                            break;
                        }
                    }
                    if (!"".equalsIgnoreCase(arrMa_kb[i].toString())) {
                        String strMaKB =
                            (String)request.getSession().getAttribute(AppConstants.APP_KB_CODE_SESSION);
                        if (arrMa_kb[i].equalsIgnoreCase(strMaKB)) {
                            whereClause = " AND a.ma = ?";
                            params = new Vector();
                            param =
                                    new Parameter(arrMa_kb[i].toString(), true);
                            params.add(param);
                            if (!LTTCommon.checkExistKhoBac(whereClause,
                                                            params, conn)) {
                                strErrorAt = "kb_ma" + "-" + i;
                                break;
                            }
                        } else {
                            strErrorAt = "kb_ma" + "-" + i;
                            break;
                        }
                    }
                    if (!"".equalsIgnoreCase(arrDu_phong_ma[i].toString())) {
                        whereClause = " a.ma = ?";
                        params = new Vector();
                        param =
                                new Parameter(arrDu_phong_ma[i].toString(), true);
                        params.add(param);
                        if (!LTTCommon.checkExistDuPhong(whereClause, params,
                                                         conn)) {
                            strErrorAt = "du_phong_ma" + "-" + i;
                            break;
                        }
                    }
                    if ("".equalsIgnoreCase(arrSo_tien[i])) {
                        strErrorAt = "so_tien" + "-" + i;
                        break;
                    } else if (!"".equalsIgnoreCase(arrSo_tien[i])) {
                        String strSoTien = arrSo_tien[i];
                        if (strSoTien.contains("-")) {
                            strErrorAt = "so_tien" + "-" + i;
                            break;
                        }
                    }
                } //end for
            }
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
        return strErrorAt;
    }
    // kiem tra DMCheo

    public String checkDMKHCheo(HttpServletRequest request, String strAppID,
                                String strWSDL) throws Exception {
        String[] arrTkkt_ma = null;
        String str_Error = "";
        HashMap h_COA = getArraysInCOA(request);

        if (h_COA.get("tkkt_ma") != null) {
            arrTkkt_ma = (String[])h_COA.get("tkkt_ma");
            String[] arrMa_quy = (String[])h_COA.get("ma_quy");
            String[] arrDvsdns_ma = (String[])h_COA.get("dvsdns_ma");
            String[] arrCapns_ma = (String[])h_COA.get("capns_ma");
            String[] arrChuongns_ma = (String[])h_COA.get("chuongns_ma");
            String[] arrNganhkt_ma = (String[])h_COA.get("nganhkt_ma");
            String[] arrNdkt_ma = (String[])h_COA.get("ndkt_ma");
            String[] arrDbhc_ma = (String[])h_COA.get("dbhc_ma");
            String[] arrCtmt_ma = (String[])h_COA.get("ctmt_ma");
            String[] arrMa_nguon = (String[])h_COA.get("ma_nguon");
            String[] arrDu_phong_ma = (String[])h_COA.get("du_phong_ma");
            String[] arrMa_kb = (String[])h_COA.get("kb_ma");
            if (arrTkkt_ma != null) {
                for (int i = 0; i < arrTkkt_ma.length; i++) {
                    //Check ket hop cheo (goi webservice)

                    DMCheo dmCheo = new DMCheo(strWSDL);
                    String[] arrResultKHC;
                    /////////////////////////////////////////////////////////////////////////////                    
/*                    strLog.append("Bat dau goi ws check ket hop cheo: " +
                                  StringUtil.DateToString(new Date(),
                                                          "dd/MM/yyyy HH:mm:ss") +
                                  "\n");*/
                    arrResultKHC =
                            dmCheo.checkKHCDM(arrMa_quy[i], arrTkkt_ma[i],
                                              arrNdkt_ma[i], arrCapns_ma[i],
                                              arrDvsdns_ma[i], arrDbhc_ma[i],
                                              arrChuongns_ma[i],
                                              arrNganhkt_ma[i], arrCtmt_ma[i],
                                              arrMa_kb[i], arrMa_nguon[i],
                                              arrDu_phong_ma[i], strAppID);
                    
 /*                   strLog.append("Ket thuc goi ws check ket hop cheo: " +
                                  StringUtil.DateToString(new Date(),
                                                          "dd/MM/yyyy HH:mm:ss") +
                                  "\n");*/
                    /////////////////////////////////////////////////////////////////////////////
                    if (arrResultKHC != null && arrResultKHC.length == 2) {
                        if ("INVALID".equalsIgnoreCase(arrResultKHC[0])) {
                            if (arrResultKHC[1] != null) {
                                str_Error =
                                        arrResultKHC[1].replace("\n", ". ");
                            } else {
                                str_Error = arrResultKHC[1];
                            }
                        } else if ("ERROR".equalsIgnoreCase(arrResultKHC[0])) {
                            if (arrResultKHC[1] != null) {
                                str_Error =
                                        arrResultKHC[1].replace("\n", ". ");
                            } else {
                                str_Error = arrResultKHC[1];
                            }
                        }
                    }
                } //end if cho add COA
            }
        }
/*      strLog.append("KET THUC: " +
                    StringUtil.DateToString(new Date(),
                                            "dd/MM/yyyy HH:mm:ss") +
                    "\n");
        log(strLog.toString());*/

        return str_Error;
    }
    //
    // Kiem tra so YCTT
    // Kiem tra trong truong hop add

    public boolean checkExist_So_YCTT(Connection conn, String dup_soyctt,
                                      String id_selected) {
        boolean bCheck = true;
        Vector params = null;
        Parameter param = null;
        try {
            // check duplicate so yctt
            if (params == null)
                params = new Vector();
            String whereClause = "";
            String strDup_soyctt = "";
            //            //Check trung So YCTT
            whereClause = " t.so_yctt = ? and t.trang_thai <> ? ";
            if (dup_soyctt != null)
                strDup_soyctt = dup_soyctt;
            param = new Parameter(strDup_soyctt, true);
            params.add(param);
            param = new Parameter(AppConstants.LTT_TRANG_THAI_HUY, true);
            params.add(param);
            if (!"".equals(id_selected)) {
                whereClause += " and t.id <> ? ";
                param = new Parameter(id_selected, true);
                params.add(param);
            }
            // neu SO_YCTT da ton tai
            // bcheck = false

            bCheck = LTTCommon.checkExistSoYCTT(whereClause, params, conn);
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
        return bCheck;
    }

    public boolean checkApproveYCTT(Connection conn, String dup_soyctt) {
        boolean bCheck = true;
        Vector params = null;
        Parameter param = null;
        try {
            // check duplicate so yctt
            String whereClause = "";
            String strDup_soyctt = "";
            //            //Check trung So YCTT
            whereClause = " t.so_yctt = ? and t.trang_thai= ? ";
            if (dup_soyctt != null)
                strDup_soyctt = dup_soyctt;

            params = new Vector();
            param = new Parameter(strDup_soyctt, true);
            params.add(param);
            param = new Parameter(AppConstants.LTT_TRANG_THAI_HUY, true);
            params.add(param);
            // neu SO_YCTT da ton tai
            // bcheck = false
            bCheck = LTTCommon.checkSoYCTT(whereClause, params, conn);

        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
        return bCheck;

    }
/*
    public void log(String args) {
        try {

            File file = new File("c:/logWS.txt");

            //if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            //true = append file
            FileWriter fileWritter = new FileWriter("c:/logWS.txt", true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write(args);
            bufferWritter.close();
          

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
//    public static void main(String[] args) {
//        LTTCommon l = new LTTCommon();
//        l.log("Nsdfdsfan Manh\n");
//      l.log("Ngusdfafdfdsfdsf\n");
//      l.log("Nsdfdsafds fdsfdsfds dsfdsf");
//        
//    }
}
