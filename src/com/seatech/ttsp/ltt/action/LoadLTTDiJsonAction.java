package com.seatech.ttsp.ltt.action;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.dmnh.DMNHangDAO;
import com.seatech.ttsp.dmnh.DMNHangVO;
import com.seatech.ttsp.ktvtm.KTVTabmisDAO;
import com.seatech.ttsp.ltt.LTTCTietDAO;
import com.seatech.ttsp.ltt.LTTCTietVO;
import com.seatech.ttsp.ltt.LTTCommon;
import com.seatech.ttsp.ltt.LTTDAO;
import com.seatech.ttsp.ltt.LTTVO;
import com.seatech.ttsp.ltt.form.LTTForm;
import com.seatech.ttsp.user.UserVO;

import java.io.PrintWriter;

import java.lang.reflect.Type;

import java.sql.Connection;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class LoadLTTDiJsonAction extends AppAction {
    private static String SUCCESS = "success";
    private static String STRING_EMPTY = "";

    protected ActionForward executeAction(ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
        Connection conn = null;
        JsonObject jsonObj = new JsonObject();
        String strJson = "";
        Vector vParam = null;
        Parameter param = null;
        String whereClause = "";
        LTTVO lttVO = null;
        LTTForm lttForm = null;
        Gson gson = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            String strId = request.getParameter("idLoadDetail");
            String strUserType = request.getParameter("userType");
            String strKTVTanbmis = null;
            if (strId != null && !STRING_EMPTY.equalsIgnoreCase(strId)) {
                LTTDAO lttDAO = new LTTDAO(conn);
                whereClause = " t.id = ? ";
                vParam = new Vector();
                vParam.add(new Parameter(strId, true));

                lttVO = lttDAO.getLTTDi(whereClause, vParam);
                lttForm = new LTTForm();
                BeanUtils.copyProperties(lttForm, lttVO);
                // kiem tra trang thai = 15 de hien thi ly do loi
                if (lttVO.getTrang_thai().equalsIgnoreCase(AppConstants.LTT_TRANG_THAI_DA_GUI_NGAN_HANG_XL_THAT_BAI)) {
                    String error_code =
                        lttVO.getError_code() != null ? lttVO.getError_code() :
                        "";
                    String error_desc =
                        lttVO.getError_desc() != null ? lttVO.getError_desc() :
                        "";
                    if (!STRING_EMPTY.equals(error_code) ||
                        !STRING_EMPTY.equals(error_desc))
                        lttForm.setError_desc(error_code + " - " + error_desc);
                } else {
                    lttForm.setError_desc(STRING_EMPTY);
                }
                //
                if (strUserType.contains(AppConstants.NSD_KTT) ||
                    strUserType.contains(AppConstants.NSD_GD)) {
                    TTSPUtils spUtils = new TTSPUtils(conn);
                    String strNoiDungKy =
                        spUtils.getNoiDungKy(strId, "103", "Y");
                    lttForm.setNoi_dung_ky(strNoiDungKy);
                }
                if (lttVO != null) {
                    DMNHangDAO dmnhDAO = new DMNHangDAO(conn);
                    String strKBacID = "";
                    DMNHangVO dmnhVO = null;
                    dmnhVO = LTTCommon.getDMNHang(lttVO.getNhkb_nhan(), conn);
                    Collection listDMNH = null;

                    //                  if(dmnhVO.getId() !=null){
                    //                      strKBacID = dmnhVO.getId().toString();
                    //                  }
                    if (session.getAttribute(AppConstants.APP_KB_ID_SESSION) !=
                        null) {
                        strKBacID =
                                session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
                    }
                    // neu la xem chi tiet
                    if (!lttVO.getNhkb_nhan().equals(strKBacID)) {
                        DMNHangVO dmnh_nhan = null;
                        String whereClauseTK = " a.id=? ";
                        Vector params = new Vector();
                        params.add(new Parameter(lttVO.getNhkb_nhan(), true));
                        dmnh_nhan = dmnhDAO.getDMNH(whereClauseTK, params);
                        DMNHangVO dmnh_chuyen = null;
                        params = new Vector();
                        params.add(new Parameter(lttVO.getNhkb_chuyen(),
                                                 true));
                        dmnh_chuyen = dmnhDAO.getDMNH(whereClauseTK, params);
                        listDMNH = new ArrayList<DMNHangVO>();
                        //Lay ra danh sach cac ngan hang ma kho bac mo tai khoan
                      
                        String strWhere = " and c.id = ?";
                        Vector vtParam = new Vector();
                        vtParam.add(new Parameter(strKBacID, true));
  
                        listDMNH = dmnhDAO.getDMNHListByKBId(strWhere, vtParam);
                      
                        //                        listDMNH.add(dmnhVO);
                        lttForm.setMa_nhkb_chuyen_cm(dmnh_chuyen.getMa_nh());
                        lttForm.setTen_nhkb_chuyen_cm(dmnh_chuyen.getTen());
                        //                        lttForm.setMa_nhkb_chuyen(dmnh_chuyen.getMa_nh());
                        lttForm.setTen_nhkb_nhan_cm(dmnh_nhan.getTen());
                        lttForm.setMa_nhkb_nhan_cm(dmnh_nhan.getMa_nh());
                        //                        lttForm.setMa_nhkb_nhan(dmnh_nhan.getMa_nh());
                        //                        if (lttVO.getId_nhkb_nhan() != null) {
                        //                            DMNHangVO dmnhVO_nhan = null;
                        //                            dmnhVO_nhan =
                        //                                    LTTCommon.getDMNHang(lttVO.getId_nhkb_nhan(),
                        //                                                         conn);
                        //                            if (dmnhVO_nhan != null)
                        //                                lttForm.setMa_nhkb_nhan(dmnhVO_nhan.getMa_nh());
                        //                        }
                        if (lttVO.getId_nhkb_chuyen() != null) {
                            DMNHangVO dmnhVO_chuyen = null;
                            dmnhVO_chuyen =
                                    LTTCommon.getDMNHang(lttVO.getId_nhkb_chuyen(),
                                                         conn);
                            if (dmnhVO_chuyen != null) {
                                lttForm.setMa_nhkb_chuyen(dmnhVO_chuyen.getMa_nh());
                                lttForm.setTen_nhkb_chuyen(dmnhVO_chuyen.getTen());
                            }
                        }
                        if (lttVO.getId_nhkb_nhan() != null) {
                            dmnhVO = null;
                            dmnhVO =
                                    LTTCommon.getDMNHang(lttVO.getId_nhkb_nhan(),
                                                         conn);
                            if (dmnhVO != null) {
                                lttForm.setMa_nhkb_nhan(dmnhVO.getMa_nh());
                                if(!dmnhVO.getMa_nh().equals("99999999")){
                                  lttForm.setTen_nhkb_nhan(dmnhVO.getTen());
                                }
                            }else{
                              dmnhVO = dmnhDAO.getDMNH_NN(" a.id = "+lttVO.getId_nhkb_nhan(),null);
                              if (dmnhVO != null) {
                                  lttForm.setMa_nhkb_nhan(dmnhVO.getMa_nh());
                              }
                            }
                        }
                    } else {
                        //Lay ra danh sach cac ngan hang ma kho bac mo tai khoan
                      String strWhere = " and c.id = ?";
                      Vector vtParam = new Vector();
                      vtParam.add(new Parameter(strKBacID, true));
                      
                      listDMNH = dmnhDAO.getDMNHListByKBId(strWhere, vtParam);
                      
                        //Kiem tra neu NHKB nhan chua co trong listDMNH thi add them vao
                        boolean bExistInDMNH = true;
                        Iterator iter = listDMNH.iterator();
                        DMNHangVO dmnhvo = null;
                        while (iter.hasNext()) {
                            dmnhvo = (DMNHangVO)iter.next();
                            if (dmnhvo.getId().compareTo(lttVO.getNhkb_nhan()) !=
                                0) {
                                bExistInDMNH = false;
                                break;
                            }
                        }
                        if (!bExistInDMNH) {
                            listDMNH.add(dmnhVO);
                        }
                        //Gan ma_nhkb_chuyen_cm, ma_nhkb_nhan_cm vao lttVO
                        if (session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION) !=
                            null)
                            lttForm.setMa_nhkb_chuyen_cm(session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString());
                        //lttVO.setMa_nhkb_chuyen_cm(session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString());
                        if (session.getAttribute(AppConstants.APP_NHKB_NAME_SESSION) !=
                            null) {
                            lttForm.setTen_nhkb_chuyen_cm(session.getAttribute(AppConstants.APP_NHKB_NAME_SESSION).toString());
                        }
                        if (lttVO.getNhkb_nhan() != null) {
                            dmnhVO = null;
                            dmnhVO =
                                    LTTCommon.getDMNHang(lttVO.getNhkb_nhan(), conn);
                            if (dmnhVO != null) {
                                lttForm.setMa_nhkb_nhan_cm(dmnhVO.getMa_nh());
                                if (dmnhVO.getTen() != null) {
                                    lttForm.setTen_nhkb_nhan_cm(dmnhVO.getTen());
                                }
                            }
                        }
                        if (lttVO.getId_nhkb_chuyen() != null) {
                            DMNHangVO dmnhVO_chuyen = null;
                            dmnhVO_chuyen =
                                    LTTCommon.getDMNHang(lttVO.getId_nhkb_chuyen(),
                                                         conn);
                            if (dmnhVO_chuyen != null) {
                                lttForm.setMa_nhkb_chuyen(dmnhVO_chuyen.getMa_nh());
                                lttForm.setTen_nhkb_chuyen(dmnhVO_chuyen.getTen());
                            }
                        }
                        if (lttVO.getId_nhkb_nhan() != null) {
                            dmnhVO = null;
                            dmnhVO =
                                    LTTCommon.getDMNHang(lttVO.getId_nhkb_nhan(),
                                                         conn);
                            if (dmnhVO != null) {
                                lttForm.setMa_nhkb_nhan(dmnhVO.getMa_nh());
                                lttForm.setTen_nhkb_nhan(dmnhVO.getTen());
                            }
                        }
                    }
                    //request.removeAttribute("colDMNH");


                    //Lay ma, ten Nguoi Nhan neu co
                    UserVO userVO = null;
                    if (lttVO.getTtv_nhan() != null &&
                        lttVO.getTtv_nhan() != 0) {
                        userVO = getUserInfo(lttVO.getTtv_nhan().toString());
                        if (userVO != null && userVO.getTen_nsd() != null &&
                            !"".equalsIgnoreCase(userVO.getTen_nsd())) {
                            lttForm.setTen_ttv_nhan(userVO.getTen_nsd());
                            lttForm.setMa_ttv_nhan(userVO.getMa_nsd());
                        }
                    }
                    //Lay ma, ten Nguoi kiem soat neu co
                    if (lttVO.getKtt_duyet() != null &&
                        lttVO.getKtt_duyet() != 0) {
                        userVO = null;
                        userVO = getUserInfo(lttVO.getKtt_duyet().toString());
                        if (userVO != null) {
                            lttForm.setTen_ktt_duyet(userVO.getTen_nsd());
                            lttForm.setMa_ktt_duyet(userVO.getMa_nsd());
                        }
                    }
                    //Lay ma, ten Giam doc Duyet neu co
                    if (lttVO.getGd_duyet() != null &&
                        lttVO.getGd_duyet() != 0) {
                        userVO = null;
                        userVO = getUserInfo(lttVO.getGd_duyet().toString());
                        if (userVO != null) {
                            lttForm.setTen_gd_duyet(userVO.getTen_nsd());
                            lttForm.setMa_gd_duyet(userVO.getMa_nsd());
                        }
                    }


                    //Lay chi tiet thanh toan
                    whereClause = "t.ltt_id = ?";
                    //            whereClause = "t.ltt_id = ? and m.tinhtrang='1' and k.tinhtrang='1' and n.tinhtrang='1' and c.tinhtrang='1' " +
                    //                "and nkt.tinhtrang='1' and ndkt.tinh_trang='1' and dbhc.tinhtrang='1' and ctmt.tinhtrang='1' and dp.tinhtrang='1'";
                    vParam = new Vector();
                    param = new Parameter(lttVO.getId(), true);
                    vParam.add(param);

                    LTTCTietDAO lttCTietDAO = new LTTCTietDAO(conn);
                    Collection collLttDtl =
                        lttCTietDAO.getLTTDiCTietList(whereClause, vParam);

                    Type listTypeDetail =
                        new TypeToken<ArrayList<LTTCTietVO>>() {
                    }.getType();
                    //                  strJson = new Gson().toJson(collLttDtl, listTypeDetail);
                    //                  jsonObj.addProperty("details", strJson);

                    gson = new GsonBuilder().
                            //.registerTypeAdapter(Id.class, new IdInstanceCreator())
                            //.serializeNulls()
                            setDateFormat(DateFormat.LONG).
                            //.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                            //.setPrettyPrinting()
                            setVersion(1.0).create();
                    strJson = gson.toJson(collLttDtl, listTypeDetail);

                    jsonObj.addProperty("details", strJson);


                    Type listTypeDMNH = new TypeToken<ArrayList<DMNHangVO>>() {
                    }.getType();
                    //                  strJson = new Gson().toJson(listDMNH, listTypeDMNH);
                    //                  jsonObj.addProperty("dmnhNhan", strJson);

                    gson = new GsonBuilder().
                            //.registerTypeAdapter(Vector.class, new JsonVectorAdapter())
                            setVersion(1.1).create();
                    strJson = gson.toJson(listDMNH, listTypeDMNH);
                    jsonObj.addProperty("dmnhNhan", strJson);

                    // set hien thi button hien thi , huy
                    KTVTabmisDAO ktvTabmisDAO = new KTVTabmisDAO(conn);
                    strKTVTanbmis =
                            ktvTabmisDAO.getKTVTabmisListByNSD(lttVO.getTtv_nhan());
                    //                    if (strKTVTanbmis == null ||
                    //                        STRING_EMPTY.equalsIgnoreCase(strKTVTanbmis) ||
                    //                        "'null'".equalsIgnoreCase(strKTVTanbmis) ||
                    //                        "''".equalsIgnoreCase(strKTVTanbmis)) {
                    //                        strKTVTanbmis = "0";
                    //                    }
                    jsonObj.addProperty("strKTVTanbmis",
                                        strKTVTanbmis != null ? strKTVTanbmis :
                                        null);
                    //
                }
            }
            //Type listType = new TypeToken<LTTVO>() {}.getType();
            //strJson = new Gson().toJson(lttVO, listType);
            //            if(!STRING_EMPTY.equalsIgnoreCase(strTemp) && strTemp != null){
            //              if(strJson != null && strJson.contains("}")){
            //                strJson = strJson.replaceFirst("}", "");
            //                strJson = strJson + strTemp + "}";
            //              }
            //            }

            Type listType = new TypeToken<LTTForm>() {
            }.getType();
            //            strJson = new Gson().toJson(lttForm, listType);
            //            jsonObj.addProperty("master", strJson);

            gson = new GsonBuilder().setVersion(1.2).create();
            strJson = gson.toJson(lttForm, listType);
            jsonObj.addProperty("master", strJson);

            JsonArray jsonArr = new JsonArray();
            JsonElement jsonEle = jsonObj.get("master");
            jsonArr.add(jsonEle);

            jsonEle = jsonObj.get("details");
            jsonArr.add(jsonEle);

            jsonEle = jsonObj.get("dmnhNhan");
            jsonArr.add(jsonEle);
            if (strKTVTanbmis != null && !STRING_EMPTY.equals(strKTVTanbmis)) {
                jsonEle = jsonObj.get("strKTVTanbmis");
                jsonArr.add(jsonEle);
            }

            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(jsonArr.getAsJsonArray().toString());
            out.println(strJson.toString());
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(e.getMessage());
            out.flush();
            out.close();
        } finally {
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(SUCCESS);
        else
            return null;
    }
}
