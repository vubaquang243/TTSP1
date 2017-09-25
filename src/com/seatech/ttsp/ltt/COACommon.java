package com.seatech.ttsp.ltt;


import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.TTSPException;

import com.seatech.framework.utils.StringUtil;

import java.math.BigDecimal;

import java.sql.Connection;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;


public class COACommon {
    public COACommon() {
        super();
    }

    public void coaKhongHopLe(HttpServletRequest request, ActionForm f,
                              String strLastAction, String strLastForm,
                              String strMsg) {
        request.setAttribute("MsgDialogLTTDen", strMsg);
        request.setAttribute("LastAction", strLastAction);
        request.setAttribute("LastForm", strLastForm);
        request.setAttribute("lTTForm", f);
    }

    public String checkPhanDoanCOA(COAVO coaVOFRequest, String strMaKB,
                                   Connection conn) throws Exception {

        String whereClause = "";
        Vector params = null;
        Parameter param = null;
        boolean bResult = false;
        String strErrorAt = null;
        if (coaVOFRequest == null) {
            return "";
        }
        String[] arrTkkt_ma = coaVOFRequest.getArrTkkt_ma();
        String[] arrMa_quy = coaVOFRequest.getArrMa_quy();
        String[] arrDvsdns_ma = coaVOFRequest.getArrDvsdns_ma();
        String[] arrCapns_ma = coaVOFRequest.getArrCapns_ma();
        String[] arrChuongns_ma = coaVOFRequest.getArrChuongns_ma();
        String[] arrNganhkt_ma = coaVOFRequest.getArrNganhkt_ma();
        String[] arrNdkt_ma = coaVOFRequest.getArrNdkt_ma();
        String[] arrDbhc_ma = coaVOFRequest.getArrDbhc_ma();
        String[] arrCtmt_ma = coaVOFRequest.getArrCtmt_ma();
        String[] arrMa_nguon = coaVOFRequest.getArrMa_nguon();
        String[] arrDu_phong_ma = coaVOFRequest.getArrDu_phong_ma();
        String[] arrMa_kb = coaVOFRequest.getArrMa_kb();
        String[] arrSo_tien = coaVOFRequest.getArrSo_tien();
        if (arrTkkt_ma != null) {
            for (int i = 0; i < arrTkkt_ma.length; i++) {
                whereClause = " t.ma = ?";
                params = new Vector();
                param = new Parameter(arrTkkt_ma[i].toString(), true);
                params.add(param);
                if (!LTTCommon.checkExistTkkt(whereClause, params, conn)) {
                    bResult = true;
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
                        bResult = true;
                        strErrorAt = "ma_quy" + "-" + i;
                        break;
                    }
                }
                if (!"".equalsIgnoreCase(arrDvsdns_ma[i].toString()) &&
                    !"0000000".equalsIgnoreCase(arrDvsdns_ma[i].toString())) {
                    whereClause = " a.ma = ?";
                    params = new Vector();
                    param = new Parameter(arrDvsdns_ma[i].toString(), true);
                    params.add(param);
                    if (!LTTCommon.checkExistDvsdns(whereClause, params,
                                                    conn)) {
                        bResult = true;
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
                        bResult = true;
                        strErrorAt = "capns_ma" + "-" + i;
                        break;
                    }
                }
                if (!"".equalsIgnoreCase(arrChuongns_ma[i].toString()) &&
                    !"000".equalsIgnoreCase(arrChuongns_ma[i].toString())) {
                    whereClause = " c.ma = ?";
                    params = new Vector();
                    param = new Parameter(arrChuongns_ma[i].toString(), true);
                    params.add(param);
                    if (!LTTCommon.checkExistChuongns(whereClause, params,
                                                      conn)) {
                        bResult = true;
                        strErrorAt = "chuongns_ma" + "-" + i;
                        break;
                    }
                }
                if (!"".equalsIgnoreCase(arrNganhkt_ma[i].toString()) &&
                    !"000".equalsIgnoreCase(arrNganhkt_ma[i].toString())) {
                    whereClause = " n.ma = ?";
                    params = new Vector();
                    param = new Parameter(arrNganhkt_ma[i].toString(), true);
                    params.add(param);
                    if (!LTTCommon.checkExistNganhkt(whereClause, params,
                                                     conn)) {
                        bResult = true;
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
                    if (!LTTCommon.checkExistNdkt(whereClause, params, conn)) {
                        bResult = true;
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
                    if (!LTTCommon.checkExistDbhc(whereClause, params, conn)) {
                        bResult = true;
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
                    if (!LTTCommon.checkExistCtmt(whereClause, params, conn)) {
                        bResult = true;
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
                        bResult = true;
                        strErrorAt = "ma_nguon" + "-" + i;
                        break;
                    }
                }
                if (!"".equalsIgnoreCase(arrDu_phong_ma[i].toString())) {
                    whereClause = " a.ma = ?";
                    params = new Vector();
                    param = new Parameter(arrDu_phong_ma[i].toString(), true);
                    params.add(param);
                    if (!LTTCommon.checkExistDuPhong(whereClause, params,
                                                     conn)) {
                        bResult = true;
                        strErrorAt = "du_phong_ma" + "-" + i;
                        break;
                    }
                }
                if (!"".equalsIgnoreCase(arrMa_kb[i].toString())) {
                    if (arrMa_kb[i].equalsIgnoreCase(strMaKB)) {
                        whereClause = " AND a.ma = ?";
                        params = new Vector();
                        param = new Parameter(arrMa_kb[i].toString(), true);
                        params.add(param);
                        if (!LTTCommon.checkExistKhoBac(whereClause, params,
                                                        conn)) {
                            bResult = true;
                            strErrorAt = "kb_ma" + "-" + i;
                            break;
                        }
                    } else {
                        bResult = true;
                        strErrorAt = "kb_ma" + "-" + i;
                        break;
                    }
                }
                if ("".equalsIgnoreCase(arrSo_tien[i])) {
                    bResult = true;
                    strErrorAt = "so_tien" + "-" + i;
                    break;
                } else if (!"".equalsIgnoreCase(arrSo_tien[i])) {
                    String strSoTien = arrSo_tien[i];
                    if (strSoTien.contains("-")) {
                        bResult = true;
                        strErrorAt = "so_tien" + "-" + i;
                        break;
                    }
                }
            } //end for
        }

        //      if(bResult){
        //        taoLaiCOA(request);
        //      }


        return strErrorAt;
    }

    //    public void taoLaiCOA(HttpServletRequest request){
    //        if(arrTkkt_ma != null && arrTkkt_ma.length > 0){
    //          LTTCTietForm lttCTForm = null;
    //          Vector vLTTCTiet  = new Vector();
    //          for(int i=0; i<arrTkkt_ma.length; i++){
    //            lttCTForm = new LTTCTietForm();
    //            lttCTForm.setTkkt_ma(arrTkkt_ma[i]);
    //            lttCTForm.setMa_quy(arrMa_quy[i]);
    //            lttCTForm.setDvsdns_ma(arrDvsdns_ma[i]);
    //            lttCTForm.setCapns_ma(arrCapns_ma[i]);
    //            lttCTForm.setChuongns_ma(arrChuongns_ma[i]);
    //            lttCTForm.setNganhkt_ma(arrNganhkt_ma[i]);
    //            lttCTForm.setNdkt_ma(arrNdkt_ma[i]);
    //            lttCTForm.setDbhc_ma(arrDbhc_ma[i]);
    //            lttCTForm.setCtmt_ma(arrCtmt_ma[i]);
    //            lttCTForm.setMa_nguon(arrMa_nguon[i]);
    //            lttCTForm.setDu_phong_ma(arrDu_phong_ma[i]);
    //            lttCTForm.setKb_ma(arrMa_kb[i]);
    //            lttCTForm.setDien_giai(arrDien_giai[i]);
    //            lttCTForm.setSo_tien(arrSo_tien[i]);
    //            vLTTCTiet.add(lttCTForm);
    //          }//end for
    //          request.setAttribute("colLTTCTiet", vLTTCTiet);
    //        }
    //    }

    public LTTCTietVO getRowInCOAFromCode(int i, Long id,
                                          COAVO coaVOFRequest, String nt_id) throws Exception {
        LTTCTietVO lttCTietVO = null;
        try {
            String[] arrTkkt_ma = coaVOFRequest.getArrTkkt_ma();
            String[] arrMa_quy = coaVOFRequest.getArrMa_quy();
            String[] arrDvsdns_ma = coaVOFRequest.getArrDvsdns_ma();
            String[] arrCapns_ma = coaVOFRequest.getArrCapns_ma();
            String[] arrChuongns_ma = coaVOFRequest.getArrChuongns_ma();
            String[] arrNganhkt_ma = coaVOFRequest.getArrNganhkt_ma();
            String[] arrNdkt_ma = coaVOFRequest.getArrNdkt_ma();
            String[] arrDbhc_ma = coaVOFRequest.getArrDbhc_ma();
            String[] arrCtmt_ma = coaVOFRequest.getArrCtmt_ma();
            String[] arrMa_nguon = coaVOFRequest.getArrMa_nguon();
            String[] arrDu_phong_ma = coaVOFRequest.getArrDu_phong_ma();
            String[] arrMa_kb = coaVOFRequest.getArrMa_kb();
            String[] arrDien_giai = coaVOFRequest.getArrMa_kb();
            String[] arrSo_tien = coaVOFRequest.getArrSo_tien();
            lttCTietVO = new LTTCTietVO();
            lttCTietVO.setLtt_id(id);
            if (arrTkkt_ma[i] != "") {
                lttCTietVO.setTkkt_id(arrTkkt_ma[i].toString());
            }
            if (arrMa_quy[i] != "") {
                lttCTietVO.setMa_quy_id(arrMa_quy[i].toString());
            }
            if (arrDvsdns_ma[i] != "") {
                lttCTietVO.setDvsdns_id(arrDvsdns_ma[i].toString());
            }
            if (arrCapns_ma[i] != "") {
                lttCTietVO.setCapns_id(arrCapns_ma[i].toString());
            }
            if (arrChuongns_ma[i] != "") {
                lttCTietVO.setChuongns_id(arrChuongns_ma[i]);
            }
            if (arrNganhkt_ma[i] != "" &&
                !"000".equalsIgnoreCase(arrNganhkt_ma[i])) {
                lttCTietVO.setNganhkt_id(arrNganhkt_ma[i].toString());
            }
            if (arrNdkt_ma[i] != "" &&
                !"0000".equalsIgnoreCase(arrNdkt_ma[i])) {
                lttCTietVO.setNdkt_id(arrNdkt_ma[i].toString());
            }
            if (arrDbhc_ma[i] != "") {
                lttCTietVO.setDbhc_id(arrDbhc_ma[i].toString());
            }
            if (arrCtmt_ma[i] != "") {
                lttCTietVO.setCtmt_id(arrCtmt_ma[i].toString());
            }
            if (arrMa_nguon[i] != "") {
                lttCTietVO.setNguon_id(arrMa_nguon[i].toString());
            }
            if (arrDu_phong_ma[i] != "") {
                lttCTietVO.setDu_phong_id(arrDu_phong_ma[i].toString());
            }
            if (arrDien_giai[i] != "") {
                lttCTietVO.setDien_giai(arrDien_giai[i]);
            }
            if (!"".equals(arrSo_tien[i]) && arrSo_tien[i] != null) {
                String strSoTien = arrSo_tien[i].trim();
//                if (strSoTien.indexOf(" ") != -1)
//                    strSoTien = strSoTien.replace(" ", "");
//                if (strSoTien.indexOf(".") != -1)
//                    strSoTien = strSoTien.replace(".", "");
//                if (strSoTien.indexOf(",") != -1)
//                    strSoTien = strSoTien.replace(",", "");
                lttCTietVO.setSo_tien(StringUtil.convertCurrencyToNumber(strSoTien, nt_id));
            }
            if (arrMa_kb[i] != "") {
                lttCTietVO.setKb_id(arrMa_kb[i].toString());
            }
            int sott_dong = i + 1;
            lttCTietVO.setSott_dong(Long.parseLong(sott_dong + ""));
        } catch (Exception ex) {
            throw TTSPException.createException("TTSP-1010", ex.toString());
        }

        return lttCTietVO;
    }
}
