package com.seatech.framework.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.dmnh.DMNHangDAO;
import com.seatech.ttsp.dmnh.DMNHangVO;
import com.seatech.ttsp.tknhkb.TKNHKBacDAO;
import com.seatech.ttsp.tknhkb.TKNHKBacVO;

import java.io.PrintWriter;

import java.lang.reflect.Type;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TTSPLoadDMuc {
  private final static String MATTTT = "0002";
  private final static String MAKBNN = "0001";
  private final static String MASGD = "0003";  
  private final static String CAP_TINH = "5";
    Connection conn = null;
    public TTSPLoadDMuc(Connection conn) {
      this.conn = conn;
    }
    public void getformTraCuu(String nhkb_tinh,String nhkb_huyen, HttpServletRequest request,HttpServletResponse response) throws Exception {
      String strWhereclause = "";
      Vector vParam = null;
      TKNHKBacDAO tknhkbDAO = null;
      DMKBacDAO kbDAO = null;
      DMNHangDAO dmnhDAO = null;
      DMKBacVO kbVO = null;
      ArrayList<DMKBacVO> lstDSKBTinh = null;
      ArrayList<DMKBacVO> lstDSKBHuyen = null;
      ArrayList<DMNHangVO> lstDSNHang = null;
      ArrayList<TKNHKBacVO> lstTKNHang = null;
      kbDAO = new DMKBacDAO(conn);
      tknhkbDAO = new TKNHKBacDAO(conn);
      dmnhDAO = new DMNHangDAO(conn);
      
      String strJson = "";
      Gson gson = null;
      JsonObject jsonObj = new JsonObject();
      
      HttpSession session = request.getSession();
      String strFind_KB_Huyen =    request.getParameter(AppConstants.REQUEST_ACTION);
      if (strFind_KB_Huyen != null &&
          strFind_KB_Huyen.equalsIgnoreCase("Find_KB_Huyen")) {
          String pMa_Kb_Tinh = request.getParameter("nhkb_tinh");
          if (pMa_Kb_Tinh != null && !"".equals(pMa_Kb_Tinh)) {
              if (pMa_Kb_Tinh.equals(MASGD) || pMa_Kb_Tinh.equals(MATTTT)) {
                  strWhereclause = " a.ma = ?";
              }else {
                  strWhereclause = " a.ma_cha = ?";
              }
              vParam = new Vector();
              vParam.add(new Parameter(pMa_Kb_Tinh, true));
              lstDSKBHuyen =
                      (ArrayList<DMKBacVO>)kbDAO.getDMKBList(strWhereclause,
                                                             vParam);
              if (lstDSKBHuyen != null) {
                  strWhereclause = " and b.ma=? and a.trang_thai = '01'";
                  vParam = new Vector();
                  vParam.add(new Parameter(lstDSKBHuyen.get(0).getMa(),
                                           true));
                  lstDSNHang =
                          (ArrayList<DMNHangVO>)dmnhDAO.getListNHSaoKeTK(strWhereclause,
                                                                  vParam);
                  if (lstDSNHang.size() > 0) {
                      strWhereclause =
                              " and a.trang_thai=? and a.kb_id=? and b.ma_nh=?";
                      String strTrang_Thai = "01";
                      vParam = new Vector();
                      vParam.add(new Parameter(strTrang_Thai, true));
                      vParam.add(new Parameter(lstDSNHang.get(0).getId(),
                                               true));
                      vParam.add(new Parameter(lstDSNHang.get(0).getMa_nh(),
                                               true));
                      lstTKNHang =
                              (ArrayList<TKNHKBacVO>)tknhkbDAO.getTK_NH_KB(strWhereclause,
                                                                           vParam);
                  }
              }
              Type lstDSKBHuyenType =
                  new TypeToken<ArrayList<DMKBacVO>>() {
              }.getType();
              gson = new GsonBuilder().
                      //.registerTypeAdapter(Vector.class, new JsonVectorAdapter())
                      setVersion(1.1).create();
              strJson = gson.toJson(lstDSKBHuyen, lstDSKBHuyenType);
              jsonObj.addProperty("lstDMKBHuyen", strJson);

              Type lstDSNHangType =
                  new TypeToken<ArrayList<DMNHangVO>>() {
              }.getType();
              gson = new GsonBuilder().
                      //.registerTypeAdapter(Vector.class, new JsonVectorAdapter())
                      setVersion(1.1).create();
              strJson = gson.toJson(lstDSNHang, lstDSNHangType);
              jsonObj.addProperty("lstDSNHang", strJson);

              Type lstTKNHangType =
                  new TypeToken<ArrayList<TKNHKBacVO>>() {
              }.getType();
              gson = new GsonBuilder().
                      //.registerTypeAdapter(Vector.class, new JsonVectorAdapter())
                      setVersion(1.1).create();
              strJson = gson.toJson(lstTKNHang, lstTKNHangType);
              jsonObj.addProperty("lstTKNHang", strJson);


              JsonArray jsonArr = new JsonArray();
              JsonElement jsonEle = jsonObj.get("lstDMKBHuyen");
              jsonArr.add(jsonEle);
              jsonEle = jsonObj.get("lstDSNHang");
              jsonArr.add(jsonEle);
              jsonEle = jsonObj.get("lstTKNHang");
              jsonArr.add(jsonEle);
              response.setContentType(AppConstants.CONTENT_TYPE_JSON);
              PrintWriter out = response.getWriter();
              out.println(jsonArr.getAsJsonArray().toString());
              out.flush();
              out.close();
          }
      } else if (strFind_KB_Huyen != null &&
                 strFind_KB_Huyen.equalsIgnoreCase("Find_NH")) {
          String pMa_Kb_huyen = request.getParameter("nhkb_huyen");
          strWhereclause = " and b.ma=? and a.trang_thai = '01'";
          vParam = new Vector();
          vParam.add(new Parameter(pMa_Kb_huyen, true));
          lstDSNHang =
                  (ArrayList<DMNHangVO>)dmnhDAO.getListNHSaoKeTK(strWhereclause,
                                                          vParam);
          if (lstDSNHang.size() > 0) {
              strWhereclause =
                      " and a.trang_thai=? and a.kb_id=? and b.ma_nh=?";
              String strTrang_Thai = "01";
              vParam = new Vector();
              vParam.add(new Parameter(strTrang_Thai, true));
              vParam.add(new Parameter(lstDSNHang.get(0).getId(), true));
              vParam.add(new Parameter(lstDSNHang.get(0).getMa_nh(),
                                       true));
              lstTKNHang =
                      (ArrayList<TKNHKBacVO>)tknhkbDAO.getTK_NH_KB(strWhereclause,
                                                                   vParam);
          }


          Type lstDSNHangType = new TypeToken<ArrayList<DMNHangVO>>() {
          }.getType();
          Type lstTKNHangType = new TypeToken<ArrayList<TKNHKBacVO>>() {
          }.getType();
          gson = new GsonBuilder().
                  //.registerTypeAdapter(Vector.class, new JsonVectorAdapter())
                  setVersion(1.1).create();
          strJson = gson.toJson(lstDSNHang, lstDSNHangType);
          jsonObj.addProperty("lstDSNHang", strJson);
          strJson = gson.toJson(lstTKNHang, lstTKNHangType);
          jsonObj.addProperty("lstTKNHang", strJson);

          JsonArray jsonArr = new JsonArray();
          JsonElement jsonEle = jsonObj.get("lstDSNHang");
          jsonArr.add(jsonEle);
          jsonEle = jsonObj.get("lstTKNHang");
          jsonArr.add(jsonEle);

          response.setContentType(AppConstants.CONTENT_TYPE_JSON);
          PrintWriter out = response.getWriter();
          out.println(jsonArr.getAsJsonArray().toString());
          out.flush();
          out.close();
      } else if (strFind_KB_Huyen != null &&
                 strFind_KB_Huyen.equalsIgnoreCase("Find_TK")) {
          String pMa_nh = request.getParameter("manh");
          String pMa_kb = request.getParameter("makb");
          strWhereclause =
                  " and a.trang_thai=? and c.ma=? and b.ma_nh=?";
          String strTrang_Thai = "01";

          vParam = new Vector();
          vParam.add(new Parameter(strTrang_Thai, true));
          vParam.add(new Parameter(pMa_kb, true));
          vParam.add(new Parameter(pMa_nh, true));
          lstTKNHang =
                  (ArrayList<TKNHKBacVO>)tknhkbDAO.getTK_NH_KB(strWhereclause,
                                                               vParam);

          Type lstTKNHangType = new TypeToken<ArrayList<TKNHKBacVO>>() {
          }.getType();
          gson = new GsonBuilder().
                  //.registerTypeAdapter(Vector.class, new JsonVectorAdapter())
                  setVersion(1.1).create();
          strJson = gson.toJson(lstTKNHang, lstTKNHangType);
          jsonObj.addProperty("lstTKNHang", strJson);

          JsonArray jsonArr = new JsonArray();
          JsonElement jsonEle = jsonObj.get("lstTKNHang");
          jsonArr.add(jsonEle);
          response.setContentType(AppConstants.CONTENT_TYPE_JSON);
          PrintWriter out = response.getWriter();
          out.println(jsonArr.getAsJsonArray().toString());
          out.flush();
          out.close();
      } else {
          // Truong hop bat dau khi vao form
          String strMa_kb =
              (String)session.getAttribute(AppConstants.APP_KB_CODE_SESSION);
          if (strMa_kb.equals(MAKBNN) ||strMa_kb.equals(MATTTT)) {    
              // hien thi tat ca kho bac tinh
              strWhereclause = " a.cap=? or a.ma=? or a.ma=?";             
              vParam = new Vector();
              vParam.add(new Parameter(CAP_TINH, true));
              vParam.add(new Parameter(MASGD, true));
              vParam.add(new Parameter(MATTTT, true));
              lstDSKBTinh =
                      (ArrayList<DMKBacVO>)kbDAO.getDMKBList(strWhereclause,
                                                             vParam);
              vParam = new Vector();
              lstDSKBHuyen = new ArrayList<DMKBacVO>();
              if (nhkb_tinh != null) {
                  strWhereclause = "a.ma_cha=? or ma=? ";
                  vParam = new Vector();
                  vParam.add(new Parameter(nhkb_tinh, true));
                  vParam.add(new Parameter(nhkb_tinh, true));
                  lstDSKBHuyen =
                          (ArrayList<DMKBacVO>)kbDAO.getDMKBList(strWhereclause,
                                                                 vParam);
              } else {
                  strWhereclause = " AND a.ma=? ";
                  vParam.add(new Parameter(MASGD, true));
                  kbVO = kbDAO.getDMKB(strWhereclause, vParam);
                  lstDSKBHuyen.add(kbVO);
              }
              // hien thi ngan hang cua sgd
              strWhereclause = " and b.ma=? and a.trang_thai = '01'";
              vParam = new Vector();
              if (nhkb_huyen != null) {
                  vParam.add(new Parameter(nhkb_huyen, true));
              } else {
                  vParam.add(new Parameter(kbVO.getMa(), true));
              }
              lstDSNHang =
                      (ArrayList<DMNHangVO>)dmnhDAO.getListNHSaoKeTK(strWhereclause,
                                                              vParam);
              if (lstDSNHang.size() > 0) {
                  // hien thi so tai khoan
                  strWhereclause =
                          " and a.trang_thai=? and a.kb_id=? and b.ma_nh=?";
                  String strTrang_Thai = "01";
                  vParam = new Vector();
                  vParam.add(new Parameter(strTrang_Thai, true));
                  vParam.add(new Parameter(lstDSNHang.get(0).getId(),
                                           true));
                  vParam.add(new Parameter(lstDSNHang.get(0).getMa_nh(),
                                           true));
                  lstTKNHang =
                          (ArrayList<TKNHKBacVO>)tknhkbDAO.getTK_NH_KB(strWhereclause,
                                                                       vParam);
              }
          } else {
              // Xac dinh kho bac dang nhap la tinh hay huyen
              strWhereclause = " AND a.ma=?";
              vParam = new Vector();
              vParam.add(new Parameter(strMa_kb, true));
              kbVO = kbDAO.getDMKB(strWhereclause, vParam);
              if (kbVO.getCap().equals(CAP_TINH)) {
                  lstDSKBTinh = new ArrayList<DMKBacVO>();
                  lstDSKBTinh.add(kbVO);
              }
              // Neu la kho bac cap huyen thi set mac dinh cap huyen la kho bac so tai dong thoi xac dinh kho bac ma kho bac huyen truc thuoc
              else {
                  lstDSKBHuyen = new ArrayList<DMKBacVO>();
                  lstDSKBHuyen.add(kbVO);
                  DMKBacVO kbTinhVO = new DMKBacVO();
                  if(strMa_kb.equals(MASGD)){
                  strWhereclause = " AND a.ma = ? ";
                  vParam = new Vector();
                  vParam.add(new Parameter(kbVO.getMa_cha(), true));
                 
                  }else{
                    strWhereclause = " AND a.ma = ? and a.cap=?";
                    vParam = new Vector();
                    vParam.add(new Parameter(kbVO.getMa_cha(), true));
                    vParam.add(new Parameter(CAP_TINH, true));
                  }
                  kbTinhVO = kbDAO.getDMKB(strWhereclause, vParam);
                  
                  lstDSKBTinh = new ArrayList<DMKBacVO>();
                  lstDSKBTinh.add(kbTinhVO);

                  // hien thi ngan hang cua sgd
                  strWhereclause = " and b.ma=? and a.trang_thai = '01'";
                  vParam = new Vector();
                  vParam.add(new Parameter(kbVO.getMa(), true));                
                  lstDSNHang =
                          (ArrayList<DMNHangVO>)dmnhDAO.getListNHSaoKeTK(strWhereclause,
                                                                  vParam);               
                  // hien thi so tai khoan
                  strWhereclause =
                          " and a.trang_thai=? and a.kb_id=? and b.ma_nh=?";
                  String strTrang_Thai = "01";
                  vParam = new Vector();
                  vParam.add(new Parameter(strTrang_Thai, true));
                  vParam.add(new Parameter(lstDSNHang.get(0).getId(),
                                           true));
                  vParam.add(new Parameter(lstDSNHang.get(0).getMa_nh(),
                                           true));
                  lstTKNHang =
                          (ArrayList<TKNHKBacVO>)tknhkbDAO.getTK_NH_KB(strWhereclause,
                                                                       vParam);
              }
          }
          request.setAttribute("lstDSKBTinh", lstDSKBTinh);
          request.setAttribute("lstDSKBHuyen", lstDSKBHuyen);
          request.setAttribute("lstDSNHang", lstDSNHang);
          request.setAttribute("lstTKNHang", lstTKNHang);
      }
        
    }
}
