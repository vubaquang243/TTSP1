package com.seatech.framework;

public class AppConstants {
    public static final String NNT_APP_CONTEXT_ROOT =
                "/TTSP";
    public static final String APP_REPORT_PATH =
        //        "C:\\Documents and Settings\\khanghoa\\Application Data\\JDeveloper\\system11.1.1.3.37.56.60\\o.j2ee\\drs\\KBNN\\TTSPWebApp.war/report/";
        "C:\\Documents and Settings\\Administrator\\Application Data\\JDeveloper\\system11.1.1.3.37.56.60\\o.j2ee\\drs\\KBNN\\TTSPWebApp.war/report/";
    //"/apps/domains/TCSTTDEV/servers/AdminServer/upload/TTSPWebApp.war/report/";
    public static final String APP_TEMPLATE_PATH =
        //        "C:\\Documents and Settings\\khanghoa\\Application Data\\JDeveloper\\system11.1.1.3.37.56.60\\o.j2ee\\drs\\KBNN\\TTSPWebApp.war/templates/";
        "C:\\Documents and Settings\\Administrator\\Application Data\\JDeveloper\\system11.1.1.3.37.56.60\\o.j2ee\\drs\\KBNN\\TTSPWebApp.war/templates/";
    //"/apps/domains/TCSTTDEV/servers/AdminServer/upload/TTSPWebApp.war/templates/";

    //Ten ung dung
    public static final String APP_SEND_CODE="TTSP_KBA";
    public static final String APP_SEND_NAME="Thanh toan song phuong dien tu tap trung KBNN";
    public static final String APP_NAME = "TTSPTT";
    public static final String APP_NAME_HACH_TOAN_TABMIS = "SP";
    public static final String APP_ID = "D2A17E4C31D147DA8ECA2F7EC33CB774";
    
    public static final String TABMIS_CODE="TABMIS";
    public static final String TABMIS_NAME="TABMIS";
    public static final String GL_SOURCE_MODULE = "GL";
    //trang thai LTT
    public static final String MA_THAM_CHIEU_TRANG_THAI_LTT =
        "SP_LTT.TRANG_THAI";
    public static final String MA_THAM_CHIEU_TRANG_THAI_DTS =
        "SP_DTS.TRANG_THAI";
    public static final String MA_THAM_CHIEU_TRANG_THAI_QT =
        "SP_QT.TRANG_THAI";
    public static final String MA_THAM_CHIEU_TRANG_THAI_DTS_TUDO =
      "SP_DTS";
    //********Sesion key********
    //Nguoi su dung
    public static final String APP_USER_ID_SESSION = "id_nsd";
    public static final String APP_USER_CODE_SESSION = "ma_nsd";
    public static final String APP_DOMAIN_SESSION = "domain_session";
    public static final String APP_USER_NAME_SESSION = "ten_nsd";
    public static final String APP_CHUC_NANG_LIST_SESSION = "chuc_nang_list";
    public static final String APP_IP_SESSION = "ip_truy_cap";
    public static final String APP_MAC_SESSION = "mac_truy_cap";
    public static final String APP_OS_USER_SESSION = "os_user_truy_cap";
    public static final String APP_COMPUTER_NAME_SESSION = "ten_may_truy_cap";
    public static final String APP_THAM_SO_SESSION = "APP_THAM_SO_SESSION";
    //Thong tin kho bac
    public static final String APP_KB_ID_SESSION = "id_kb";
    public static final String APP_KB_CODE_SESSION = "ma_kb";
    public static final String APP_KB_NAME_SESSION = "ten_kb";
    public static final String APP_KB_CAP_SESSION = "cap_kb";
    public static final String APP_CHO_PHEP_CHON_DC_KHOP_DUNG_SESSION = "CHO_PHEP_CHON_DC_KHOP_DUNG";
    public static final String APP_HACH_TOAN_TABMIS_THEO_NGAY_KS_NH_SESSION = "HACH_TOAN_TABMIS_THEO_NGAY_KS_NH";
    
    //20171017 thuongdt bo sung them
    public static final String APP_LAP_DNQT_BU_CHI_NGAY_LOI_SESSION = "CHO_PHEP_DNQT_BU_SO_CHI_NGAY_LOI";
    public static final String APP_LAP_DNQT_THAU_CHI_SESSION = "CHO_PHEP_DNQT_THAU_CHI";
    public static final String APP_LAP_DNQT_THU_NGAY_LOI_SESSION = "CHO_PHEP_DNQT_BU_SO_THU_NGAY_LOI";
    public static final String APP_LAP_DNQT_LOAI_KHAC_SESSION = "CHO_PHEP_DNQT_LOAI_KHAC";
   
    //Thong tin ngan hang kho bac
    public static final String APP_NHKB_ID_SESSION = "id_nhkb";
    public static final String APP_NHKB_CODE_SESSION = "ma_nhkb";
    public static final String APP_NHKB_NAME_SESSION =
        "ten_nhkb"; //Giong APP_KB_NAME_SESSION nhung ko dau
    public static final String APP_NH_SP_SESSION = "APP_NH_SP_SESSION"; 
    //Danh sach role
    public static final String APP_ROLE_LIST_SESSION = "role_list";
    public static final String APP_MENU_LIST_SESSION = "menu_list";
    //Cac tham so cho dieu chinh von
    public static final String APP_TK_CHI_CA_NHAN_VA_CSACH_AN_SINH_XH_SESSION = "TK_CHI_CA_NHAN_VA_CSACH_AN_SINH_XH";
    public static final String APP_NDKT_CHI_CA_NHAN_VA_CSACH_AN_SINH_XH_SESSION = "NDKT_CHI_CA_NHAN_VA_CSACH_AN_SINH_XH";
    public static final String APP_TK_CHI_TRA_NO_SESSION = "TK_CHI_TRA_NO";
    public static final String APP_HAN_MUC_DIEU_HANH_VON_BIDV_SESSION = "HAN_MUC_DIEU_HANH_VON_BIDV";
    public static final String APP_HAN_MUC_DIEU_HANH_VON_AGR_SESSION = "HAN_MUC_DIEU_HANH_VON_AGR";
    public static final String APP_HAN_MUC_DIEU_HANH_VON_VCB_SESSION = "HAN_MUC_DIEU_HANH_VON_VCB";
    public static final String APP_HAN_MUC_DIEU_HANH_VON_ICB_SESSION = "HAN_MUC_DIEU_HANH_VON_ICB";
    public static final String APP_HAN_MUC_CHAN_LTT_DI_TRONG_DHV_SESSION = "HAN_MUC_CHAN_LTT_DI_TRONG_DHV";
    public static final String APP_MA_DVQHNS_AN_NINH_SESSION = "MA_DVQHNS_AN_NINH";
    public static final String APP_KIEM_TRA_DIEU_HANH_VON_SESSION = "KIEM_TRA_DIEU_HANH_VON";
    //********So dong tren mot trang - phuc vu cho phan trang********
    public static final int APP_NUMBER_ROW_ON_PAGE = 15;
    //*************** Ma TTTT ******************
    public static final String MA_TTTT = "0002,0001";
    //*****Loai lenh*****//
    public static final String LTT_TYPE = "103";
    public static final String DTS_HOI_TYPE = "195";
    public static final String DTS_TRA_LOI_TYPE = "196";
  // Tin Tuc
  public static final String APP_TIN_TUC_SESSION = "tin_tuc";
    //****** Bien Dieu Huong *****//
    public static final String FAILURE = "failure";
    public static final String SUCCESS = "success";
    public static final String CHECKPERMISSION = "permission";
    public static final String MAPING_NO_RIGHT = "notRight";
    //****** Loai User *****//
    public static final String NSD_GD = "GD";
    public static final String NSD_KTT = "KTT";
    public static final String NSD_KTV = "KTV";
    public static final String NSD_TTV = "TTV";
    public static final String NSD_QTHT_DV = "QTHT-DV";
    public static final String NSD_QTHT_TW = "QTHT-TW";
    public static final String NSD_CB_TTTT = "CB-TTTT";
      public static final String NSD_CBPT_PTTT = "CBPT-TTTT";
    //*************************
   
    //****** ACTION BUTTON *****//
    public static final String ACTION_ACCEPT = "ACCEPT";
    public static final String ACTION_APPROVED = "APPROVED";
    public static final String ACTION_REJECT = "REJECT";
    public static final String ACTION_CANCEL = "CANCEL";
    public static final String ACTION_EDIT = "EDIT";
    public static final String ACTION_EXIT = "EXIT";
    public static final String ACTION_FIND = "FIND";
    public static final String ACTION_ADD = "ADD";
    public static final String ACTION_YES = "Y";
    public static final String ACTION_NO = "N";
    public static final String ACTION_WAIT_PROCESS = "WAITPROCESS";
    public static final String ACTION_VIEW_DETAIL = "VIEW_DETAIL";
    public static final String ACTION_VIEW_DETAIL_DTS_TUDO =
        "VIEW_DETAIL_TUDO";
    public static final String ACTION_VIEW_DETAIL_DTS_T4 =
        "VIEW_DTS_T4";
    public static final String ACTION_FILL_DATA_TO_FORM = "fillDataForm";
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String CONTENT_TYPE_JSON_UTF =
        "application/json ; charset=utf-8";
    public static final String ACTION_REFRESH = "REFRESH";
    //MA THAM CHIEU
    public static final String STATE_DTS_LTT_DI = "SP_DTS.TRANG_THAI";
    public static final String STATE_DTS_HOI_TU_NHTM = "SP_DTS_HOI_NH.STATE";
    //MA CHUC NANG
    public static final String SP_DTS_HOI_NH_STATE_10 = "10"; //KTT day lai
    public static final String SP_DTS_HOI_NH_STATE_11 = "11"; //cho xu ly
    public static final String SP_DTS_HOI_NH_STATE_12 = "12"; //da xu ly
    public static final String SP_DTS_HOI_NH_STATE_13 = "13"; //da tra loi
    public static final String SP_DTS_HOI_NH_STATE_14 = "14"; //cho ktt duyet
    public static final String SP_DTS_HOI_NH_STATE_15 = "15"; //da gui
    public static final String SP_DTS_HOI_NH_STATE_16 = "16"; //da xac nhan
    public static final String SP_DTS_HOI_NH_STATE_17 = "17"; //da huy
    public static final String SP_DTS_HOI_NH_STATE_18 = "18"; //da gui nh thanh cong
    public static final String SP_DTS_HOI_NH_STATE_19 = "19"; //da gui nh that bai

    public static final String REQUEST_ACTION = "action";
    public static final String TRANG_THAI_CHO_DUYET = "02";
    public static final String TRANG_THAI_DUYET = "03";
    public static final String TRANG_THAI_DAY_LAI = "01";
    public static final String TRANG_THAI_HUY = "04";
    public static final String TRANG_THAI_DA_XULY = "05";
    public static final String TRANG_THAI_CHO_XULY = "00";
    public static final String TRANG_THAI_HUY_DOICHIEU = "07";
    public static final String TRANG_THAI_CHO_XACHNHAN = "08";
    public static final String TRANG_THAI_DA_XACHNHAN = "09";
    //******** TRANH THAI QUYET TOAN ***************************//
    public static final String TRANG_THAI_QT_CHOHOANTHIEN = "00";
    public static final String TRANG_THAI_QT_CHODUYET = "01";
    public static final String TRANG_THAI_QT_DAYLAI = "02";
    public static final String TRANG_THAI_QT_DADUYET = "03";
    public static final String TRANG_THAI_QT_BKE = "04";
    public static final String TRANG_THAI_QT_CHOGD = "11";
    public static final String TRANG_THAI_QT_GDTHANHCONG = "12";
    public static final String TRANG_THAI_QT_GDKOTHANHCONG = "13";
    public static final String TRANG_THAI_QT_LOITRUYENTIN = "16";
    //******** TRANH THAI BK ***************************//
    public static final String TRANG_THAI_BK_CHODUYET = "01";
    public static final String TRANG_THAI_BK_DADUYET = "02";
    public static final String TRANG_THAI_BK_DAYLAI = "03";
    public static final String TRANG_THAI_BK_HUY = "04";
    // ********* TRANG THAI NOI GIO ****************************

    public static final String TRANG_THAI_COT_DAY_LAI = "00";
    public static final String TRANG_THAI_COT_CHO_HOAN_THIEN = "01";
    public static final String TRANG_THAI_COT_CHO_DUYET = "02";
    public static final String TRANG_THAI_COT_DA_DUYET = "03";
    public static final String TRANG_THAI_COT_HUY = "04";
    public static final String TRANG_THAI_COT_CHO_DONGY = "05";
    public static final String TRANG_THAI_COT_DA_DONGY = "06";
    public static final String TRANG_THAI_COT_KHONG_DONGY = "07";
    public static final String TRANG_THAI_COT_DA_DUYET_DONGY = "08";
    public static final String TRANG_THAI_COT_KHONG_DUYET_DONGY = "09";
    public static final String TRANG_THAI_COT_CHO_XACNHAN = "10";
    public static final String TRANG_THAI_COT_DA_XACNHAN = "11";

    //****** TEN THAM SO TRONG THAM SO HE THONG *****//
    //    public static final String PARAM_LUONG_TIEN_GD_DUYET_MAX =
    //        "GD_DUYET_LUONG_TIEN_MAX";
    //    public static final String PARAM_LUONG_TIEN_GD_DUYET_MIN =
    //        "GD_DUYET_LUONG_TIEN_MIN";
//    public static final String PARAM_LOAI_LTT_GD_DUYET = "GD_DUYET_LOAI_LTT";
    public static final String PARAM_GD_DUYET_TABMIS_LUONG_TIEN =
        "GD_DUYET_TABMIS_LUONG_TIEN";
    public static final String PARAM_GD_DUYET_THUCONG_LUONG_TIEN =
        "GD_DUYET_THUCONG_LUONG_TIEN";
    public static final String PARAM_SO_LAN_DANG_NHAP_SAI =
        "SO_LAN_DANG_NHAP_SAI_CHO_PHEP";
    //ten loai_truy_cap TTSP cua phan Tra cuu CSDL
    public static final String PARAM_LOAI_TRUY_CAP =
        "TTSP_DEVKratosMANHNV-PC192.168.1.157";
    public static final String PARAM_SOURCE_APP_CODE = "SOURCE_APP_CODE";
    public static final String PARAM_SOURCE_APP_NAME = "SOURCE_APP_NAME";
    public static final String PARAM_APP_TABMIS_CODE = "APP_TABMIS_CODE";
    public static final String PARAM_APP_TABMIS_NAME = "APP_TABMIS_NAME";
    public static final String PARAM_DES_APP_NAME = "APP_TTSP_NH_NAME";
    public static final String PARAM_DES_APP_CODE = "APP_TTSP_NH_CODE";
    public static final String PARAM_MSG_TYPE_CODE_LTT = "MSG_TYPE_CODE_LTT";
    public static final String PARAM_MSG_TYPE_NAME_LTT = "MSG_TYPE_NAME_LTT";
    public static final String PARAM_MSG_TYPE_CODE_DTS = "MSG_TYPE_CODE_DTS";
    public static final String PARAM_MSG_TYPE_NAME_DTS = "MSG_TYPE_NAME_DTS";
    public static final String PARAM_MSG_TYPE_CODE_HOI_TTHAI =
        "MSG_TYPE_CODE_HOI_TTHAI";
    public static final String PARAM_MSG_TYPE_NAME_HOI_TTHAI =
        "MSG_TYPE_NAME_HOI_TTHAI";
    public static final String PARAM_MSG_TYPE_CODE_DTS_TRALOI =
        "MSG_TYPE_CODE_DTS_TRALOI";
    public static final String PARAM_MSG_TYPE_NAME_DTS_TRALOI =
        "MSG_TYPE_NAME_DTS_TRALOI";
    //Param cho MQ
    public static final String PARAM_MQ_HOSTNAME = "MQ_HOSTNAME";
    public static final String PARAM_MQ_CHANEL = "MQ_CHANEL";
    public static final String PARAM_MQ_PORT = "MQ_PORT";
    public static final String PARAM_MQ_MANAGER_NAME = "MQ_MANAGER_NAME";
    public static final String PARAM_MQ_NAME = "MQ_NAME";
    //Param cho tham so cac truong trong thong tin ng nhan cua ltt di
    public static final String PARAM_LTT_ID_TT_NG_NHAN_SOTK =
        "LTT_ID.TT_NG_NHAN.SOTK";
    public static final String PARAM_LTT_ID_TT_NG_NHAN_NHKB =
        "LTT_ID.TT_NG_NHAN.NHKB";
    public static final String PARAM_LTT_ID_TT_NG_NHAN_TENTK =
        "LTT_ID.TT_NG_NHAN.TENTK";
    public static final String PARAM_LTT_ID_TT_NG_NHAN_TTTK =
        "LTT_ID.TT_NG_NHAN.TT_KH";

    //*****Cac tham so cho gui lenh
    public static final String XML_VERSION =
        "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";
    public static final String VERSION_MSG = "1.0";
    public static final String DATE_TIME_FORMAT_SEND_ORDER =
        "dd-MM-yyyy HH:mm:ss"; 
    public static final String DATE_FORMAT_SEND_ORDER =
      "dd-MM-yyyy";
    public static final String NUM_OF_TX = "1";
    public static final String INVALID = "INVALID";
    public static final String ERROR = "ERROR";


    //Cac tham so trong bang chuc nang
    public static final String APP_CNANG_CNANG_HE_THONG = "SYS";
    public static final String APP_CNANG_DANH_MUC = "DMUC";
    public static final String APP_CNANG_DOI_CHIEU = "DCHIEU";
    public static final String APP_CNANG_BAO_CAO = "BCAO";
    public static final String APP_CNANG_LENH_THANH_TOAN = "LTT";
    public static final String APP_CNANG_DIEN_TRA_SOAT = "DTS";

    //Lenh thanh toan
    public static final String LTT_TRANG_THAI_KTT_DUYET_DAY_LAI = "01";
    public static final String LTT_TRANG_THAI_CHO_HOAN_THIEN = "02";
    public static final String LTT_TRANG_THAI_CHO_KTT_DUYET = "03";
    public static final String LTT_TRANG_THAI_GD_DUYET_DAY_LAI = "04";
    public static final String LTT_TRANG_THAI_CHO_GD_DUYET = "05";
    public static final String LTT_TRANG_THAI_HUY = "06";
    public static final String LTT_TRANG_THAI_DA_DUYET = "07";
    public static final String LTT_TRANG_THAI_DA_GUI_NGAN_HANG = "08";
    // Note: LTT_TRANG_THAI_DA_DUYET va LTT_TRANG_THAI_DA_GUI_CHUA_VAO_GIAO_DIEN la mot
    public static final String LTT_TRANG_THAI_DA_GUI_CHUA_VAO_GIAO_DIEN = "07";
    public static final String LTT_TRANG_THAI_DA_GUI_CHO_CHAY_GIAO_DIEN = "11";
    public static final String LTT_TRANG_THAI_DA_GUI_GIAO_DIEN_THANH_CONG =
        "12";
    public static final String LTT_TRANG_THAI_DA_GUI_GIAO_DIEN_THAT_BAI = "13";
    public static final String LTT_TRANG_THAI_NGAN_HANG_DA_NHAN = "14";
    public static final String LTT_TRANG_THAI_DA_GUI_NGAN_HANG_XL_THANH_CONG =
        "16";
    public static final String LTT_TRANG_THAI_DA_GUI_NGAN_HANG_XL_THAT_BAI =
        "15";

    public static final String LTT_DI = "LTT.DI"; //cho chuc nang
    public static final String LTT_DI_CHUC_NANG_THEM = "LTT.DI.THEM";
    public static final String LTT_DI_CHUC_NANG_THEM_LAI = "LTT.DI.THEM.LAI";
    public static final String LTT_DI_CHUC_NANG_SUA = "LTT.DI.SUA";
    public static final String LTT_DI_CHUC_NANG_SUA_LAI = "LTT.DI.SUA.LAI";
    public static final String LTT_DI_CHUC_NANG_DUYET = "LTT.DI.DUYET";
    public static final String LTT_DI_CHUC_NANG_XOA = "LTT.DI.XOA";
    public static final String LTT_DI_CHUC_NANG_CHI_TIET = "LTT.DI.CHITIET";
  public static final String LTT_DI_CHUC_NANG_CHI_TIET_DUYETLO = "LTT.DI.CHITIET_DUYETLO";
  public static final String LTT_DI_CHUC_NANG_CHI_TIET_DHV = "LTT.DI.CHITIET_DHV";
    public static final String LTT_DI_CHUC_NANG_TIM_KIEM = "LTT.DI.TIMKIEM";

    public static final String LTT_DEN = "LTT.DEN"; //cho chuc nang
    public static final String LTT_DEN_CHUC_NANG_THEM = "LTT.DEN.THEM";
    public static final String LTT_DEN_CHUC_NANG_THEM_LAI = "LTT.DEN.THEM.LAI";
    public static final String LTT_DEN_CHUC_NANG_SUA = "LTT.DEN.SUA";
    public static final String LTT_DEN_CHUC_NANG_SUA_LAI = "LTT.DEN.SUA.LAI";
    public static final String LTT_DEN_CHUC_NANG_DUYET = "LTT.DEN.DUYET";
    public static final String LTT_DEN_CHUC_NANG_XOA = "LTT.DEN.XOA";
    public static final String LTT_DEN_CHUC_NANG_CHI_TIET = "LTT.DEN.CHITIET";
    public static final String LTT_DEN_CHUC_NANG_TIM_KIEM = "LTT.DEN.TIMKIEM";

    public static final String LTT_DEN_CHUC_NANG_QUYET_TOAN =
        "LTT.DEN.QUYET.TOAN";

    public static final String LTT_NGAT_KET_NOI = "NGAT_KET_NOI";
    public static final String LTT_KHONG_CO_QUYEN = "KHONG_CO_QUYEN";
    public static final String LTT_MA_KHONG_HOP_LE = "MA_KHONG_HOP_LE";
    public static final String LTT_LAST_FORM_ADD = "LAST.FORM.ADD";
    public static final String LTT_LAST_FORM_EDIT = "LAST.FORM.EDIT";
    public static final String LTT_LAST_FORM_TRUNG_SO_YCTT =
        "LAST.FORM.TRUNG.SOYCTT";
    public static final String LTT_TRUNG_SO_YCTT = "TRUNG_SO_YCTT";
    public static final String LTT_TRANG_THAI_DA_THAY_DOI =
        "TRANGTHAI_DA_THAY_DOI";
    public static final String LTT_KET_HOP_CHEO_INVALID = "KHC_INVALID";
    public static final String LTT_KET_HOP_CHEO_ERROR = "KHC_ERROR";

    public static final String PARAM_MA_QUY = "MA_QUY";
    public static final String PARAM_MA_DVSDNS = "MA_DVSDNS";
    public static final String PARAM_MA_CHUONG = "MA_CHUONG";
    public static final String PARAM_MA_CAPNS = "MA_CAPNS";
    public static final String PARAM_MA_DP = "MA_DP";
    public static final String PARAM_MA_DBHC = "MA_DBHC";
    public static final String PARAM_MA_NGANH_KT = "MA_NGANH_KT";
    public static final String PARAM_MA_NDKT = "MA_NDKT";
    public static final String PARAM_MA_CTMT = "MA_CTMT";
    public static final String PARAM_MA_NGUON = "MA_NGUON";
    public static final String PARAM_TK_CHO_XLY = "TK_CHO_XLY";
    public static final String PARAM_TK_THANH_TOAN = "TK_THANH_TOAN";
    public static final String PARAM_TK_CHO_XLY_QTOAN_NO =
        "TK_CHO_XLY_QTOAN_NO";
    public static final String PARAM_TK_THANH_TOAN_QTOAN_NO =
        "TK_THANH_TOAN_QTOAN_NO";
    public static final String PARAM_TK_CHO_XLY_QTOAN_CO =
        "TK_CHO_XLY_QTOAN_CO";
    public static final String PARAM_TK_THANH_TOAN_QTOAN_CO =
        "TK_THANH_TOAN_QTOAN_CO";
    public static final String PARAM_GL_SOURCE_MODULE = "GL_SOURCE_MODULE";

    // Log cua Thinh
    public static final String ACTION_VIEW_LOG_INFO = "DongBoHoaViewLogAction";
    public static final String ACTION_VIEW_LOG_ERROR = "VIEW_LOG_ERROR";
    public static final String MESSAGE_LOGGING = "MESSAGE_LOGGING";
    // THAM SO HE THONG TRA CUU CHUNG TU GIAO DIEN
    public static final String PARAM_MQ_HOST_NAME_GD =
        "MQ_HOST_NAME_TCUU_CTU_GDIEN";
    public static final String PARAM_MQ_CHANNEL_GD =
        "MQ_CHANNEL_TCUU_CTU_GDIEN";
    public static final String PARAM_MQ_QUEUE_IN_GD =
        "MQ_QUEUE_IN_TCUU_CTU_GDIEN";
    public static final String PARAM_MQ_QUEUE_OUT_GD =
        "MQ_QUEUE_OUT_TCUU_CTU_GDIEN";
    public static final String PARAM_MQ_MANAGER_NAME_GD =
        "MQ_MANAGER_NAME_TCUU_CTU_GDIEN";

    // cut of time
    public static final String PARAM_THAMSO_CUTOFTIME = "CUT_OF_TIME";
    // report
    public static final String FONT_FOR_REPORT = "/font/times.ttf";
    public static final String REPORT_DIRECTORY = "/report";
    
    public static final String TIMER_REFRESH="TIMER_REFRESH_TTINTTOAN_ONLINE";
    
    public static final String LTT_DI_CHUC_NANG_CHI_TIET_T4 = "LTT.DI.CHITIET.T4"; 
    
    public static final String KBNN_SGD_BANK_CODE = "01701004";
    public static final String KBNN_TW_BANK_CODE = "01701001";
    
    public static final String DIEU_HANH_VON = "DHV";
    public static final String FORMAT_CURRENTCEY_PATERN_VND = "#,###";
    public static final String FORMAT_CURRENTCEY_PATERN_NT = "#,##0.00";
	//format tien cho ngoai te
  //	public static final String FORMAT_CURRENTCEY_PATERN_NT_VND = "#,###.00";
    
    
    
    
}
