package com.seatech.ttsp.theodoiht;


import com.ibm.mq.MQC;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.constants.MQConstants;

import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.exception.TTSPException;
import com.seatech.framework.utils.StringUtil;
import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.proxy.giaodien.MsgDAO;
import com.seatech.ttsp.proxy.giaodien.MsgVO;
import com.seatech.ttsp.thamso.ThamSoHThongVO;
import com.seatech.ttsp.theodoiht.form.TraCuuTTSPForm;
import com.seatech.ttsp.theodoiht.form.TraCuuTabmisForm;

import java.io.StringReader;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.InputSource;


public class TheoDoiHeThongDAO extends AppDAO {
    private String strEmpty = "";
    private static String response = null;
    private static Document doc = null;
    private static MQQueueManager qMgr;
    // tham so MQ
    String mqHostName, mqChannelName, mqManagerName, SOURCE_APP_CODE, TABMIS_CODE, mqInputName, mqOutputName;
    int mqPort;
    // tham so he thong header
    private String strData = "<DATA>";
    private String strCloseData = "</DATA>";
    private String strHeader = "<HEADER>";
    private String strCloseHeader = "</HEADER>";
    private String strVersion = "<VERSION>";
    private String strCloseVersion = "</VERSION>";
    private String strSender_code = "<SENDER_CODE>";
    private String strCloseSender_code = "</SENDER_CODE>";
    private String strSender_name = "<SENDER_NAME>";
    private String strCloseSender_name = "</SENDER_NAME>";
    private String strReceiver_code = "<RECEIVER_CODE>";
    private String strCloseReceiver_code = "</RECEIVER_CODE>";
    private String strReceiver_name = "<RECEIVER_NAME>";
    private String strCloseReceiver_name = "</RECEIVER_NAME>";
    private String strTran_code = "<TRAN_CODE>";
    private String strCloseTran_code = "</TRAN_CODE>";
    private String strTran_name = "<TRAN_NAME>";
    private String strCloseTran_name = "</TRAN_NAME>";
    private String strMsg_id = "<MSG_ID>";
    private String strCloseMsg_id = "</MSG_ID>";
    private String strMsg_reqId = "<MSG_REQID/>";
    private String strSend_date = "<SEND_DATE>";
    private String strCloseSend_date = "</SEND_DATE>";
    private String strOriginal_code = "<ORIGINAL_CODE>";
    private String strCloseOriginal_code = "</ORIGINAL_CODE>";
    private String strExport_date = "<EXPORT_DATE>";
    private String strCloseExport_date = "</EXPORT_DATE>";
    private String strTran_num = "<TRAN_NUM>";
    private String strCloseTran_num = "</TRAN_NUM>";
    private String strError_code = "<ERROR_CODE>";
    private String strError_DESC = "<ERROR_DESC>";
    private String strClose_Error_code = "</ERROR_CODE>";
    private String strClose_Error_DESC = "</ERROR_DESC>";

    // khai bao nut goc
    private String strBody = "<BODY>";
    private String strCloseBody = "</BODY>";
    // khai bao nut con
    private String strMaster = "<MASTER>";
    private String strCloseMaster = "</MASTER>";
    private String strRow = "<ROW>";
    private String strCloseRow = "</ROW>";
    // khai bao cac nut la dieu kien tim kiem
    private String strMaKBChuyen = "<MA_KB>";
    private String strSoYCTT = "<SO_YCTT>";
    private String strMaNHChuyen = "<MA_NH_CHUYEN>";
    private String strSoTien = "<SO_TIEN>";
    private String strMaNHNhan = "<MA_NH_NHAN >";
    private String strNgayThanhToan = "<NGAY_TT>";
    private String strTKNo = "<TK_NO>";
    private String strCloseMaKBChuyen = "</MA_KB>";
    private String strCloseSoYCTT = "</SO_YCTT>";
    private String strCloseMaNHChuyen = "</MA_NH_CHUYEN>";
    private String strCloseSoTien = "</SO_TIEN>";
    private String strCloseMaNHNhan = "</MA_NH_NHAN >";
    private String strCloseNgayThanhToan = "</NGAY_TT>";
    private String strCloseTKNo = "</TK_NO>";
    private String strSo_ltt = "<SO_LTT>";
    private String strCloseSo_ltt = "</SO_LTT>";
    private String strTu_ngay_tt = "<TU_NGAY_TT>";
    private String strDen_ngay_tt = "<DEN_NGAY_TT>";
    private String strCloseTu_ngay_tt = "</TU_NGAY_TT>";
    private String strCloseDen_ngay_tt = "</DEN_NGAY_TT>";
    // trang thai tabmis
    private final static String trangthai_tabmis_default = "00";
    private final static String trangthai_tabmis_chochay_giaodien = "01";
    private final static String trangthai_tabmis_dangchay_giaodien = "02";
    private final static String trangthai_tabmis_giaodien_thanhcong = "03";
    private final static String trangthai_tabmis_giaodien_thatbai = "04";
    private final static String trangthai_ttsp_chuagui = "01";
    private final static String trangthai_ttsp_thieu_coa = "02";
    private final static String trangthai_ttsp_thua_coa = "03";
    private final static String trangthai_ttsp_glDate_err = "04";
    private final static String trangthai_ttsp_default = "00";
    private final static String trangthai_ttsp_dagui = "05";

    public TheoDoiHeThongDAO(Connection conn) {
        this.conn = conn;
    }
    private static byte[] msgPutID;
    private Connection conn = null;

    public TheoDoiHeThongDAO(String mqHostName, String mqChannelName,
                             int mqPort, String mqManagerName,
                             String mqInputName, String mqOutputName,
                             int timeOut) throws MQException {
        //        msgPutID = null;
        MQEnvironment.hostname = mqHostName;
        MQEnvironment.channel = mqChannelName;
        MQEnvironment.port = mqPort; // 1414;
        qMgr = new MQQueueManager(mqManagerName);
        this.mqInputName = mqInputName;
        this.mqOutputName = mqOutputName;
    }

    /**
     * @see : Build msg tìm kiem theo chuan de put vào he thong tích hop goi sang tabmis
     * @param : msgID duoc tao o ham sendMessage, form TraCuu
     * */
    private String buildMessageForLookup(TraCuuTTSPForm form, String msgID) {
        if (form.getMakb_chuyen() == null) {
            form.setMakb_chuyen(strEmpty);
        }
        if (form.getManh_chuyen() == null) {
            form.setManh_chuyen(strEmpty);
        }
        if (form.getManh_nhan() == null) {
            form.setManh_nhan(strEmpty);
        }
        if (form.getNgaythanhtoan() == null) {
            form.setMakb_chuyen(strEmpty);
        }
        if (form.getNgaythanhtoan() == null) {
            form.setNgaythanhtoan(strEmpty);
        }
        if (form.getSo_yctt() == null) {
            form.setSo_yctt(strEmpty);
        }
        if (form.getSotien() == null) {
            form.setSotien(strEmpty);
        }
        if (form.getTaikhoanno() == null) {
            form.setTaikhoanno(strEmpty);
        }
        String strNgay_tt =
            StringUtil.DateToString(StringUtil.StringToDate(form.getNgaythanhtoan().toString(),
                                                            "dd/MM/yyyy"),
                                    AppConstants.DATE_FORMAT_SEND_ORDER);
        if (strNgay_tt == null) {
            strNgay_tt = strEmpty;
        }
        StringBuffer sbGenXML = new StringBuffer();
        // header XML
        sbGenXML.append(AppConstants.XML_VERSION);
        sbGenXML.append(strData);
        sbGenXML.append(strHeader);
        //content header
        sbGenXML.append(strVersion);
        sbGenXML.append("1.1");
        sbGenXML.append(strCloseVersion);

        sbGenXML.append(strSender_code);
        sbGenXML.append("TTSP_KB");
        sbGenXML.append(strCloseSender_code);

        sbGenXML.append(strSender_name);
        sbGenXML.append("TTSP Kho bac nha nuoc");
        sbGenXML.append(strCloseSender_name);

        sbGenXML.append(strReceiver_code);
        sbGenXML.append("TABMIS");
        sbGenXML.append(strCloseReceiver_code);

        sbGenXML.append(strReceiver_name);
        sbGenXML.append("Thanh toan song phuong");
        sbGenXML.append(strCloseReceiver_name);

        sbGenXML.append(strTran_code);
        sbGenXML.append("SP001");
        sbGenXML.append(strCloseTran_code);

        sbGenXML.append(strTran_name);
        sbGenXML.append("Lenh thanh toan");
        sbGenXML.append(strCloseTran_name);

        sbGenXML.append(strMsg_id);
        sbGenXML.append(msgID);
        sbGenXML.append(strCloseMsg_id);

        sbGenXML.append(strMsg_reqId);

        sbGenXML.append(strSend_date);

        sbGenXML.append(StringUtil.DateToString(new Date(),
                                                AppConstants.DATE_FORMAT_SEND_ORDER));
        sbGenXML.append(strCloseSend_date);

        sbGenXML.append(strOriginal_code);
        sbGenXML.append("Ngan hang TMCP CT Ha Noi");
        sbGenXML.append(strCloseOriginal_code);

        sbGenXML.append(strExport_date);
        sbGenXML.append(StringUtil.DateToString(new Date(),
                                                AppConstants.DATE_FORMAT_SEND_ORDER));
        sbGenXML.append(strCloseExport_date);

        sbGenXML.append(strTran_num);
        sbGenXML.append("1");
        sbGenXML.append(strCloseTran_num);

        sbGenXML.append(strError_code);
        sbGenXML.append(strClose_Error_code);
        sbGenXML.append(strError_DESC);
        sbGenXML.append(strClose_Error_DESC);
        sbGenXML.append(strCloseHeader);
        // Body XML

        sbGenXML.append(strBody);
        sbGenXML.append(strMaster);
        sbGenXML.append(strRow);
        // content body
        sbGenXML.append(strMaKBChuyen);
        sbGenXML.append(form.getMakb_chuyen());
        sbGenXML.append(strCloseMaKBChuyen);

        sbGenXML.append(strSoYCTT);
        sbGenXML.append(form.getSo_yctt());
        sbGenXML.append(strCloseSoYCTT);

        sbGenXML.append(strMaNHChuyen);
        sbGenXML.append(form.getManh_chuyen());
        sbGenXML.append(strCloseMaNHChuyen);

        sbGenXML.append(strSoTien);
        sbGenXML.append(form.getSotien());
        sbGenXML.append(strCloseSoTien);

        sbGenXML.append(strMaNHNhan);
        sbGenXML.append(form.getManh_nhan());
        sbGenXML.append(strCloseMaNHNhan);

        sbGenXML.append(strNgayThanhToan);

        sbGenXML.append(strNgay_tt);
        sbGenXML.append(strCloseNgayThanhToan);

        sbGenXML.append(strTKNo);
        sbGenXML.append(form.getTaikhoanno());
        sbGenXML.append(strCloseTKNo);

        sbGenXML.append(strCloseRow);
        sbGenXML.append(strCloseMaster);
        // detail va security
        //        sbGenXML.append(strDetail);
        //        sbGenXML.append(strSecurity);
        sbGenXML.append(strCloseBody);

        // dong the

        sbGenXML.append(strCloseData);
        return sbGenXML.toString();
    }

    private String buildMessageForLookupTabmis(TraCuuTabmisForm form,
                                               String msgID) {
        if (form.getMa_kb_nhan() == null) {
            form.setMa_kb_nhan(strEmpty);
        }
        if (form.getMa_nh_chuyen() == null) {
            form.setMa_nh_chuyen(strEmpty);
        }
        if (form.getSo_ltt() == null) {
            form.setSo_ltt(strEmpty);
        }
        if (form.getSo_tien() == null) {
            form.setSo_tien(strEmpty);
        }
        if (form.getTu_ngay_tt() == null) {
            form.setTu_ngay_tt(strEmpty);
        }
        if (form.getDen_ngay_tt() == null) {
            form.setDen_ngay_tt(strEmpty);
        }
        String strTu_Ngay =
            StringUtil.DateToString(StringUtil.StringToDate(form.getTu_ngay_tt().toString(),
                                                            "dd/MM/yyyy"),
                                    AppConstants.DATE_FORMAT_SEND_ORDER);
        if (strTu_Ngay == null) {
            strTu_Ngay = strEmpty;
        }
        String strDen_Ngay =
            StringUtil.DateToString(StringUtil.StringToDate(form.getTu_ngay_tt().toString(),
                                                            "dd/MM/yyyy"),
                                    AppConstants.DATE_FORMAT_SEND_ORDER);
        if (strDen_Ngay == null) {
            strDen_Ngay = strEmpty;
        }
        StringBuffer sbGenXML = new StringBuffer();
        // header XML
        sbGenXML.append(AppConstants.XML_VERSION);
        sbGenXML.append(strData);
        sbGenXML.append(strHeader);
        //content header
        sbGenXML.append(strVersion);
        sbGenXML.append("1.1");
        sbGenXML.append(strCloseVersion);

        sbGenXML.append(strSender_code);
        sbGenXML.append("TTSP_KB");
        sbGenXML.append(strCloseSender_code);

        sbGenXML.append(strSender_name);
        sbGenXML.append("TTSP Kho bac nha nuoc");
        sbGenXML.append(strCloseSender_name);

        sbGenXML.append(strReceiver_code);
        sbGenXML.append("TABMIS");
        sbGenXML.append(strCloseReceiver_code);

        sbGenXML.append(strReceiver_name);
        sbGenXML.append("Thanh toan song phuong");
        sbGenXML.append(strCloseReceiver_name);

        sbGenXML.append(strTran_code);
        sbGenXML.append("SP002");
        sbGenXML.append(strCloseTran_code);

        sbGenXML.append(strTran_name);
        sbGenXML.append("Lenh thanh toan");
        sbGenXML.append(strCloseTran_name);

        sbGenXML.append(strMsg_id);
        sbGenXML.append(msgID);
        sbGenXML.append(strCloseMsg_id);

        sbGenXML.append(strMsg_reqId);

        sbGenXML.append(strSend_date);

        sbGenXML.append(StringUtil.DateToString(new Date(),
                                                AppConstants.DATE_FORMAT_SEND_ORDER));
        sbGenXML.append(strCloseSend_date);

        sbGenXML.append(strOriginal_code);
        sbGenXML.append("Ngan hang TMCP CT Ha Noi");
        sbGenXML.append(strCloseOriginal_code);

        sbGenXML.append(strExport_date);
        sbGenXML.append(StringUtil.DateToString(new Date(),
                                                AppConstants.DATE_FORMAT_SEND_ORDER));
        sbGenXML.append(strCloseExport_date);

        sbGenXML.append(strTran_num);
        sbGenXML.append("1");
        sbGenXML.append(strCloseTran_num);

        sbGenXML.append(strError_code);
        sbGenXML.append(strClose_Error_code);
        sbGenXML.append(strError_DESC);
        sbGenXML.append(strClose_Error_DESC);
        sbGenXML.append(strCloseHeader);
        // Body XML

        sbGenXML.append(strBody);
        sbGenXML.append(strMaster);
        sbGenXML.append(strRow);
        // content body
        sbGenXML.append(strMaKBChuyen);
        sbGenXML.append(form.getMa_kb_nhan());
        sbGenXML.append(strCloseMaKBChuyen);

        sbGenXML.append(strMaNHChuyen);
        sbGenXML.append(form.getMa_nh_chuyen());
        sbGenXML.append(strCloseMaNHChuyen);

        sbGenXML.append(strSoTien);
        sbGenXML.append(form.getSo_tien());
        sbGenXML.append(strCloseSoTien);

        sbGenXML.append(strSo_ltt);
        sbGenXML.append(form.getSo_ltt());
        sbGenXML.append(strCloseSo_ltt);

        sbGenXML.append(strTu_ngay_tt);
        sbGenXML.append(strTu_Ngay);
        sbGenXML.append(strCloseTu_ngay_tt);

        sbGenXML.append(strDen_ngay_tt);
        sbGenXML.append(strDen_Ngay);
        sbGenXML.append(strCloseDen_ngay_tt);

        sbGenXML.append(strCloseRow);
        sbGenXML.append(strCloseMaster);
        // detail va security
        //        sbGenXML.append(strDetail);
        //        sbGenXML.append(strSecurity);
        sbGenXML.append(strCloseBody);

        // dong the

        sbGenXML.append(strCloseData);
        return sbGenXML.toString();
    }

    /**
     * get process
     * */

    public TheoDoiHeThongDAO getProcess() throws MQException {

        TheoDoiHeThongDAO process = new TheoDoiHeThongDAO(mqHostName, mqChannelName, mqPort, mqManagerName, mqInputName, mqOutputName, 90);
        return process;
    }

    /**
     * get message from Queue
     * */
    public List<TheoDoiHeThongVO> getMessage() throws Exception {
        List<TheoDoiHeThongVO> lstXML = null;
        try {
            //         kiem tra header neu error code ko co loi moi tien hanh
            if (response != null) {
                lstXML = getAllItem(response);
            }
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        } finally {

            disconnectMQ();
        }
        return lstXML;
    }

    public List<TheoDoiHeThongTabmisVO> getMessageTabmis() throws Exception {
        List<TheoDoiHeThongTabmisVO> lstXML = null;
        try {
            //         kiem tra header neu error code ko co loi moi tien hanh
            if (response != null) {
                lstXML = getAllItemTabmis(response);
            }
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        } finally {

            disconnectMQ();
        }
        return lstXML;
    }

    public String validError() throws MQException, Exception {
        boolean bCheck = false;
        String strError_code = "";
        String strError_des = "";
        try {

            TheoDoiHeThongDAO process = getProcess();
            response = process.getXMLMQ();
            if (response != null) {
                doc = returnDoc(response);
                //        Document doc = returnDoc("c:/xml1.xml");
                NodeList nodeList = doc.getElementsByTagName("ERROR");
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element element = (Element)nodeList.item(i);
                    NodeList nError_code =
                        element.getElementsByTagName("ERROR_CODE");
                    Element line = (Element)nError_code.item(0);
                    strError_code = getCharacterDataFromElement(line);
                    NodeList nError_des =
                        element.getElementsByTagName("ERROR_DESC");
                    line = (Element)nError_des.item(0);
                    strError_des = getCharacterDataFromElement(line);
                    // ERROR_CODE Va Error_DESC khong co du lieu thi hop le
                    if (strEmpty.equals(strError_code) ||
                        strError_code == null)
                        if (strEmpty.equals(strError_des) ||
                            strError_des == null)
                            bCheck = true;
                }
            }
        } catch (Exception ex) {
            throw new Exception("validError() :" + ex.getMessage());
        } finally {
            disconnectMQ();
        }
        if (bCheck) {
            return strEmpty;
        } else {
            if (strError_code != null && !strEmpty.equals(strError_code))
                return strError_code + "-" + strError_des;
            else
                return strEmpty;
        }
    }

    /**
     * @see : Send message to queue
     * */
    public String sendMessage(TraCuuTTSPForm form,
                              Collection collSysParam) throws TTSPException,
                                                              Exception {
        String strMsgID = null;
        MsgVO msg = null;
        MsgDAO MsgDAO = null;
        try {
            //            conn = getConnection();
            getThamSoHeThong(collSysParam);
            //Get msg_id
            TTSPUtils ttspUtils = new TTSPUtils(conn);
            strMsgID =
                    ttspUtils.getMsgLTTID(this.SOURCE_APP_CODE, this.TABMIS_CODE);
            //Build msg
            String strMsg = buildMessageForLookup(form, strMsgID);
            //Build log msg
            MsgDAO = new MsgDAO(conn);
            msg = new MsgVO();
            msg.setMsg_content(strMsg);
            msg.setMsg_id(strMsgID);
            //Put msg to queue
            TheoDoiHeThongDAO process = getProcess();
            if (process.putXMLMQ(strMsg)) {
                //Ghi log
                MsgDAO.insert(msg);
            }
            return strMsgID;
        } catch (Exception ex) {
            throw new Exception("com.seatech.ttsp.theodoiht.TheoDoiHeThongDAO.sendMessage(): " +
                                ex.getMessage());
        } finally {
            //            close(conn);
        }

    }

    /**
     * @see : Send message to queue
     * */
    public String sendMessageTabmis(TraCuuTabmisForm form,
                                    Collection collSysParam) throws TTSPException {
        String strMsgID = null;
        MsgVO msg = null;
        MsgDAO msgDAO = null;
        try {
            //            conn = getConnection();
            getThamSoHeThong(collSysParam);
            //Get msg_id
            TTSPUtils ttspUtils = new TTSPUtils(conn);
            strMsgID = ttspUtils.getMsgLTTID(this.SOURCE_APP_CODE, this.TABMIS_CODE); //lay msg ID
            //Build msg
            String strMsg = buildMessageForLookupTabmis(form, strMsgID); //build Message
            //Build log msg
            msgDAO = new MsgDAO(conn);
            msg = new MsgVO();
            msg.setMsg_content(strMsg);
            msg.setMsg_id(strMsgID);
            //Put msg to queue
            TheoDoiHeThongDAO process = getProcess();
            if (process.putXMLMQ(strMsg)) { //put Q
                //Ghi log
                msgDAO.insert(msg); //insert thanh cong
            }
            return strMsgID; //return mssID
        } catch (Exception ex) {
            ex.printStackTrace();
            throw TTSPException.createException("","com.seatech.ttsp.theodoiht.TheoDoiHeThongDAO.sendMessageTabmis(): " +ex.getMessage());
        } 
    }

    /**
     * @see : Tra lai 1 file xml
     * Doc String va tra lai 1 collection
     */
    private Document returnDoc(String xml) {
        Document doc = null;
        try {
            DocumentBuilderFactory builderFactory =
                DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(xml));
            doc = builder.parse(is);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return doc;
    }
    // lay ra gia tri cua 1 row voi tham so truyen vao la 1 node

    private String getValueItem(NodeList nlist) {
        Element line = (Element)nlist.item(0);
        return getCharacterDataFromElement(line);
    }

    /**
     * @see : lay ra tat ca item trong file XML
     * */
    private List<TheoDoiHeThongVO> getAllItem(String xml) throws TTSPException {
        List<TheoDoiHeThongVO> lstVO = new ArrayList<TheoDoiHeThongVO>();
        if (doc != null) {
            NodeList nodeList = doc.getElementsByTagName("ROW");
            int nodeListLength = nodeList.getLength();
            for (int i = 0; i < nodeListLength; i++) {
                Element element = (Element)nodeList.item(i);
                //treasury_code
                NodeList nManh_kb = element.getElementsByTagName("MA_KB");
                String treasury_code = getValueItem(nManh_kb);
                //treasury_name
                NodeList nTen_KB = element.getElementsByTagName("TEN_KB");
                String treasury_name = getValueItem(nTen_KB);
                //TREASURY_BANK_CODE
                NodeList nMa_nh_chuyen =
                    element.getElementsByTagName("MA_NHKB_CHUYEN");
                String treasury_bank_code = getValueItem(nMa_nh_chuyen);
                //TREASURY_BANK_NAME
                NodeList nTen_NH_Chuyen =
                    element.getElementsByTagName("TEN_NHKB_CHUYEN");
                String treasury_bank_name = getValueItem(nTen_NH_Chuyen);
                //SUPPLIER_BANK_CODE
                NodeList nMa_nh_nhan =
                    element.getElementsByTagName("MA_NH_NHAN");
                String supplier_bank_code = getValueItem(nMa_nh_nhan);
                //SUPPLIER_BANK_NAME
                NodeList nTen_NH_Nhan =
                    element.getElementsByTagName("TEN_NH_NHAN");
                String supplier_bank_name = getValueItem(nTen_NH_Nhan);
                //SUPPLIER_NAME
                NodeList nTen_nguoi_nhan =
                    element.getElementsByTagName("TEN_NGUOI_NHAN");
                String supplier_name = getValueItem(nTen_nguoi_nhan);
                //supplier_bank_number
                NodeList nSo_tk_nhan =
                    element.getElementsByTagName("SO_TK_NHAN");
                String supplier_bank_number = getValueItem(nSo_tk_nhan);
                //paper_payment_req_number
                NodeList nSo_ct_giay =
                    element.getElementsByTagName("SO_CT_GIAY");
                String so_ct_giay = getValueItem(nSo_ct_giay);
                //nPayment_err
                NodeList nPayment_error =
                    element.getElementsByTagName("SENT_TO_PAYMENT_ERROR");
                String payment_err = getValueItem(nPayment_error).trim();

                //nPayment_app
                NodeList nPayment_app =
                    element.getElementsByTagName("MA_NHKB_CHUYEN_APP");
                String payment_app = getValueItem(nPayment_app).trim();

                NodeList nError_code =
                    element.getElementsByTagName("ERROR_CODE");
                NodeList nError_des =
                    element.getElementsByTagName("ERROR_DESC");
                NodeList nSotien = element.getElementsByTagName("SO_TIEN");
                String so_tien = getValueItem(nSotien).trim();
                String strMota_trangthai = null;
                String strMo_ta_loi = null;
                if ((getValueItem(nError_code) != null &&
                     !"".equals(getValueItem(nError_code).trim())) ||
                    (getValueItem(nError_des) != null &&
                     !"".equals(getValueItem(nError_des).trim()))) {
                    strMo_ta_loi =
                            getValueItem(nError_code) + "-" + getValueItem(nError_des);
                }
                //tinh trang TTSP
                if ("".equals(payment_app) || "0".equals(payment_app)) {
                    if ("0".equals(payment_err) || "".equals(payment_err)) {
                        strMota_trangthai = trangthai_ttsp_chuagui;
                    }
                } else if ("1".equals(payment_app)) {
                    if ("0".equals(payment_err) || "".equals(payment_err)) {
                        strMota_trangthai = trangthai_ttsp_dagui;
                    } else if ("2".equals(payment_err)) {
                        strMota_trangthai = trangthai_ttsp_thieu_coa;
                    } else if ("1".equals(payment_err)) {
                        strMota_trangthai = trangthai_ttsp_thua_coa;
                    } else if ("3".equals(payment_err)) {
                        strMota_trangthai = trangthai_ttsp_glDate_err;
                    } else {
                        strMota_trangthai = trangthai_ttsp_default;
                    }
                } else {
                    strMota_trangthai = trangthai_ttsp_default;
                }
                // set gia tri vao VO
                TheoDoiHeThongVO vo = new TheoDoiHeThongVO();
                vo.setTreasury_code(treasury_code);
                vo.setTreasury_name(treasury_name);
                vo.setTreasury_bank_code(treasury_bank_code);
                vo.setTreasury_bank_name(treasury_bank_name);
                vo.setSupplier_bank_code(supplier_bank_code);
                vo.setSupplier_name(supplier_name);
                vo.setSupplier_bank_name(supplier_bank_name);
                vo.setSupplier_bank_number(supplier_bank_number);
                vo.setSo_chungtu(so_ct_giay);
                vo.setError_des(strMo_ta_loi);
                vo.setPayment_err(payment_err);
                vo.setPayment_app(payment_app);
                vo.setMota_trangthai(strMota_trangthai);
                vo.setSotien(so_tien);
                lstVO.add(vo);
            }
        }
        return lstVO;
    }

    private List<TheoDoiHeThongTabmisVO> getAllItemTabmis(String xml) throws TTSPException {
        List<TheoDoiHeThongTabmisVO> lstVO =
            new ArrayList<TheoDoiHeThongTabmisVO>();
        try {
            if (doc != null) {
                NodeList nodeList = doc.getElementsByTagName("ROW");
                int nodeListLength = nodeList.getLength();
                // kiem tra neu so node cua xml qua lon se ko thuc hien
                // maxLength = 10
                for (int i = 0; i < nodeListLength; i++) {
                    Element element = (Element)nodeList.item(i);
                    NodeList nTen_but_toan =
                        element.getElementsByTagName("TEN_BUT_TOAN");
                    NodeList nLoai_but_toan =
                        element.getElementsByTagName("LOAI_BUT_TOAN");
                    NodeList nMa_kb = element.getElementsByTagName("MA_KB");
                    NodeList nDien_giai =
                        element.getElementsByTagName("DIEN_GIAI");
                    NodeList nMa_nh_chuyen =
                        element.getElementsByTagName("MA_NH_CHUYEN");
                    NodeList nNgay_hieu_luc =
                        element.getElementsByTagName("NGAY_HIEU_LUC");
                    NodeList nNgay_gd =
                        element.getElementsByTagName("NGAY_GD");
                    NodeList nLoai_tien =
                        element.getElementsByTagName("LOAI_TIEN");
                    NodeList nSott_dong =
                        element.getElementsByTagName("STT_DONG");
                    NodeList nMa_quy = element.getElementsByTagName("MA_QUY");
                    NodeList nError_code =
                        element.getElementsByTagName("ERROR_CODE");
                    NodeList nError_des =
                        element.getElementsByTagName("ERROR_DESC");
                    NodeList nMa_xuly =
                        element.getElementsByTagName("MA_XULY");
                    NodeList nTrang_thai =
                        element.getElementsByTagName("TRANG_THAI");
                    //kiem tra node ton tai hay ko
                    // getvalue cua tung row
                    String strTen_buttoan = null;
                    String strLoai_buttoan = null;
                    String strMa_kb = null;
                    String strDien_giai = null;
                    String strMa_nh_chuyen = null;
                    String strNgay_hieu_luc = null;
                    String strNgay_gd = null;
                    String strLoai_tien = null;
                    String strSott_dong = null;
                    String strMa_quy = null;
                    String strMo_ta_loi = null;
                    String ma_xuly = null;
                    String trang_thai = null;
                    String mota_trangthai = null;
                    if (nTen_but_toan != null) {
                        strTen_buttoan = getValueItem(nTen_but_toan).trim();
                    }
                    if (nLoai_but_toan != null) {
                        strLoai_buttoan = getValueItem(nLoai_but_toan).trim();
                    }
                    if (nMa_kb != null) {
                        strMa_kb = getValueItem(nMa_kb).trim();
                    }
                    if (nDien_giai != null) {
                        strDien_giai = getValueItem(nDien_giai).trim();
                    }
                    if (nMa_nh_chuyen != null) {
                        strMa_nh_chuyen = getValueItem(nMa_nh_chuyen).trim();
                    }
                    if (nNgay_hieu_luc != null) {
                        strNgay_hieu_luc = getValueItem(nNgay_hieu_luc).trim();
                    }
                    if (nNgay_gd != null) {
                        strNgay_gd = getValueItem(nNgay_gd).trim();
                    }
                    if (nLoai_tien != null) {
                        strLoai_tien = getValueItem(nLoai_tien).trim();
                    }
                    if (nSott_dong != null) {
                        strSott_dong = getValueItem(nSott_dong).trim();
                    }
                    if (nMa_quy != null) {
                        strMa_quy = getValueItem(nMa_quy).trim();
                    }
                    if ((getValueItem(nError_code) != null &&
                         !"".equals(getValueItem(nError_code).trim())) ||
                        (getValueItem(nError_des) != null &&
                         !"".equals(getValueItem(nError_des).trim()))) {
                        strMo_ta_loi =
                                getValueItem(nError_code) + "-" + getValueItem(nError_des);
                    }
                    if (nMa_xuly != null) {
                        ma_xuly = getValueItem(nMa_xuly).trim();
                    }
                    if (nTrang_thai != null) {
                        trang_thai = getValueItem(nTrang_thai).trim();
                    }
                    // kiem tra ma xu ly va trang thai
                    // set gia tri cho mota_trangthai
                    if (strEmpty.equals(ma_xuly) &&
                        strEmpty.equals(trang_thai)) {
                        mota_trangthai = trangthai_tabmis_chochay_giaodien;
                    } else if (strEmpty.equals(ma_xuly)) {
                        if (trang_thai.equalsIgnoreCase("U")) {
                            mota_trangthai =
                                    trangthai_tabmis_dangchay_giaodien;
                        }
                    } else if (ma_xuly.equalsIgnoreCase("S") &&
                               trang_thai.equalsIgnoreCase("P")) {
                        mota_trangthai = trangthai_tabmis_giaodien_thanhcong;
                    } else if (ma_xuly.equalsIgnoreCase("E") &&
                               trang_thai.equalsIgnoreCase("R")) {
                        mota_trangthai = trangthai_tabmis_giaodien_thatbai;
                    } else {
                        mota_trangthai = trangthai_tabmis_default;
                    }
                    // set gia tri vao VO
                    TheoDoiHeThongTabmisVO vo = new TheoDoiHeThongTabmisVO();
                    vo.setTen_but_toan(strTen_buttoan);
                    vo.setLoai_but_toan(strLoai_buttoan);
                    vo.setMa_kb_nhan(strMa_kb);
                    vo.setDien_giai(strDien_giai);
                    vo.setMa_nh_chuyen(strMa_nh_chuyen);
                    vo.setNgay_hieu_luc(strNgay_hieu_luc);
                    vo.setngay_gd(strNgay_gd);
                    vo.setLoai_tien(strLoai_tien);
                    vo.setSott_dong(strSott_dong);
                    vo.setMa_quy(strMa_quy);
                    vo.setError_des(strMo_ta_loi);
                    vo.setMa_xuly(ma_xuly);
                    vo.setTrang_thai(trang_thai);
                    vo.setMota_trangthai(mota_trangthai);
                    lstVO.add(vo);
                }
            }
        } catch (Exception ex) {
            // TODO: Add catch code
            ex.printStackTrace();
            throw TTSPException.createException("",
                                                "com.seatech.ttsp.theodoiht.TheoDoiHeThongDAO.getAllItemTabmis(): " +
                                                ex.getMessage());
        }
        return lstVO;
    }
    // convert gia tri

    public static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData)child;
            return cd.getData();
        }
        return "";
    }

    public boolean putXMLMQ(String xmlRequest) throws TTSPException {
        boolean bCheck = false;
        try {
            int openInputOptions = MQConstants.MQOO_OUTPUT + MQConstants.MQOO_FAIL_IF_QUIESCING;

            MQQueue queueIn = qMgr.accessQueue(mqInputName, openInputOptions, null, null,
                                 null); // "EBANK.INPUT"
            MQPutMessageOptions putOptions = new MQPutMessageOptions();
            putOptions.options = MQC.MQPMO_NEW_MSG_ID | MQC.MQPMO_NEW_CORREL_ID;
            MQMessage mqPutMsg = new MQMessage();
            mqPutMsg.writeString(xmlRequest);
            queueIn.put(mqPutMsg, putOptions);

            msgPutID = mqPutMsg.messageId;
            bCheck = true;
            //            System.out.println(msgPutID);
            //            System.out.print("\n  Message ID from put 1:     ");
            //            dumpHexId(mqPutMsg.messageId);
            //            System.out.print("  Correlation ID from put 1: ");
            //            dumpHexId(mqPutMsg.correlationId);
            queueIn.close();
        } catch (Exception ex) {
            throw TTSPException.createException("",
                                                "com.seatech.ttsp.theodoiht.TheoDoiHeThongDAO.putXMLMQ(): " +
                                                ex.getMessage());
        }
        return bCheck;

    }

    public String getXMLMQ() throws Exception {
        String xmlResponse = null;
        try {
            int openOptions =
                MQConstants.MQOO_INQUIRE + MQConstants.MQOO_FAIL_IF_QUIESCING +
                MQConstants.MQGMO_WAIT;

            MQQueue queueOut = qMgr.accessQueue(mqOutputName, openOptions, null, null, null);

            boolean bExit = false;
            int readTime = 0;
            long totalTime = 0;
            long starttime = System.currentTimeMillis();
            long currentTime = 400000;
            int i = 0;
            do {
                try {
                    if (totalTime <= currentTime) {
                        // Nhan message ra tu queue out
                        i++;
                        MQGetMessageOptions getOptions = new MQGetMessageOptions();
                        getOptions.options = MQC.MQGMO_WAIT | MQC.MQGMO_FAIL_IF_QUIESCING;
                        //|MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_OUTPUT | MQC.MQOO_INQUIRE ;
                        //            getOptions.waitInterval = 600;

                        MQMessage mqGetMsg = new MQMessage();
                        //                    mqGetMsg.messageId = msgPutID; //???????????????????????????
                        //                    mqGetMsg.correlationId = msgPutID; //???????????????????????????
                        //                    mqGetMsg.correlationId = msgPutID;
                        mqGetMsg.messageId = msgPutID;
                        //                        dumpHexId(mqGetMsg.messageId);
                        //                        dumpHexId(mqGetMsg.correlationId);
                        queueOut.get(mqGetMsg, getOptions);
                        int msgLength = mqGetMsg.getTotalMessageLength();
                        xmlResponse = mqGetMsg.readString(msgLength);
                        if (xmlResponse != null) {
                            break;
                        }
                    } else {
                        bExit = true;
                        queueOut.close();
                    }

                } catch (MQException e) {
                    if (e.completionCode == 2 &&
                        e.reasonCode == MQException.MQRC_NO_MSG_AVAILABLE) {
                        MQException.logExclude(MQException.MQRC_NO_MSG_AVAILABLE);
                        e.getStackTrace();
                        long endtime = System.currentTimeMillis();
                        totalTime += endtime - starttime;
                        Thread.sleep(100L);
                        readTime++;
                        continue;
                    } else
                        break;
                }
            } while (!bExit);
            queueOut.close();
        } catch (Exception ex) {
            throw new Exception("com.seatech.ttsp.theodoiht.TheoDoiHeThongDAO.getXMLMQ(): " +
                                ex.getMessage());
        }
        return xmlResponse;
    }

    public void disconnectMQ() {
        try {
            if (qMgr != null && qMgr.isConnected()) {
                qMgr.disconnect();
            }
        } catch (MQException e) {
            e.printStackTrace();
        }
    }

    static void dumpHexId(byte[] myId) {
        System.out.print("X'");
        for (int i = 0; i < myId.length; i++) {
            char b = (char)(myId[i] & 0xFF);
            if (b < 0x10) {
                System.out.print("0");
            }
            System.out.print((String)(Integer.toHexString(b)).toUpperCase());
        }
        System.out.println("'");
    }

    /**
     *@see: Get tham so he thong
     * */
    public void getThamSoHeThong(Collection collThamSoList) throws Exception {
        Iterator iter = collThamSoList.iterator();
        ThamSoHThongVO tsVO = null;
        int nCount = 0;
        while (iter.hasNext()) {
            if (nCount >= 13)
                break;
            tsVO = (ThamSoHThongVO)iter.next();
            if (tsVO != null) {
                if (AppConstants.PARAM_SOURCE_APP_CODE.equalsIgnoreCase(tsVO.getTen_ts().trim())) {
                    this.SOURCE_APP_CODE = tsVO.getGiatri_ts();
                    nCount++;
                } else if (AppConstants.PARAM_MQ_MANAGER_NAME_GD.equalsIgnoreCase(tsVO.getTen_ts().trim())) {
                    this.mqManagerName = tsVO.getGiatri_ts();
                    nCount++;
                } else if (AppConstants.PARAM_MQ_PORT.equalsIgnoreCase(tsVO.getTen_ts().trim())) {
                    this.mqPort = Integer.parseInt(tsVO.getGiatri_ts());
                    nCount++;
                } else if (AppConstants.PARAM_APP_TABMIS_CODE.equalsIgnoreCase(tsVO.getTen_ts().trim())) {
                    this.TABMIS_CODE = tsVO.getGiatri_ts();
                    nCount++;
                } else if (AppConstants.PARAM_MQ_HOST_NAME_GD.equalsIgnoreCase(tsVO.getTen_ts().trim())) {
                    this.mqHostName = tsVO.getGiatri_ts();
                    nCount++;
                } else if (AppConstants.PARAM_MQ_CHANNEL_GD.equalsIgnoreCase(tsVO.getTen_ts().trim())) {
                    this.mqChannelName = tsVO.getGiatri_ts();
                    nCount++;
                } else if (AppConstants.PARAM_MQ_QUEUE_IN_GD.equalsIgnoreCase(tsVO.getTen_ts().trim())) {
                    this.mqInputName = tsVO.getGiatri_ts();
                    nCount++;
                } else if (AppConstants.PARAM_MQ_QUEUE_OUT_GD.equalsIgnoreCase(tsVO.getTen_ts().trim())) {
                    this.mqOutputName = tsVO.getGiatri_ts();
                    nCount++;
                }
            }
        }
    }
}
