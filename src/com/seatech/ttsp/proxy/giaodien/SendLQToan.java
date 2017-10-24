package com.seatech.ttsp.proxy.giaodien;


import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.utils.StringUtil;
import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.dmnh.DMNHangDAO;
import com.seatech.ttsp.dmnh.DMNHangHOVO;
import com.seatech.ttsp.dmthamsohachtoan.DmTSoHToanDAO;
import com.seatech.ttsp.dmthamsohachtoan.DmTSoHToanVO;
import com.seatech.ttsp.proxy.giaodien.mq.QueueManager;
import com.seatech.ttsp.quyettoan.QuyetToanDAO;
import com.seatech.ttsp.quyettoan.QuyetToanVO;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;


public class SendLQToan {
    Connection conn = null;
    QueueManager queueManager = null;
    private String APP_SEND_CODE = AppConstants.APP_SEND_CODE;
    private String APP_SEND_NAME = AppConstants.APP_SEND_NAME;
    private String APP_RECIEVE_CODE = AppConstants.TABMIS_CODE;
    private String APP_RECIEVE_NAME = AppConstants.TABMIS_NAME;
    private String TRAN_CODE_QTOAN_NO = "900";
    private String TRAN_CODE_QTOAN_CO = "910";

    private String GL_SOURCE_MODULE = AppConstants.GL_SOURCE_MODULE;

    //    private String MQ_HOSTNAME;
    //    private String MQ_CHANEL;
    //    private String MQ_PORT;
    //    private String MQ_MANAGER_NAME;
    //    private String MQ_NAME;

    public SendLQToan(Connection conn, QueueManager queueManager) {
        this.conn = conn;
        this.queueManager = queueManager;
    }

    /**
     * @see: Ham build message va gui vao truc tich hop -> gui vao tabmis
     * @param: strType: Hach toan theo bang ke hay hach toan theo chung tu ("BK"/"CT"); strID: ID cua lenh qtoan hoac ID bang ke
     * @return: String msg_id
     * */
    public String sendMessage(String strID, String strType, String strMaKB,
                              String strHachToanTheoNgayKSNH) throws Exception {
        StringBuffer sbBody = new StringBuffer();
        StringBuffer sbMsg = new StringBuffer();
        String strLoaiQT = "";
        String strLoaiHToan = "";
        String strMsgID = "";
        String strWhere = " AND qtoan_dvi = ? ";


        Vector vParam = new Vector();
        if (strType.equalsIgnoreCase("BK")) {
            strWhere += " AND q.so_bk = ?";
            vParam.add(new Parameter("N", true));
        } else {
            strWhere += " AND q.id = ?";
            vParam.add(new Parameter("Y", true));
        }
        vParam.add(new Parameter(strID, true));

        int iCounter = 0;
        //Lay danh sach lenh quyet toan can hach toan tabmis
        QuyetToanDAO qtDAO = new QuyetToanDAO(conn);
        ArrayList list =
            (ArrayList)qtDAO.getQTListForHachToan(strWhere, vParam);

        //Lay tham so he thong cho hach toan tabmis
        //        setThamSoHeThong();

        //Sinh msg_id
        TTSPUtils ttspUtils = new TTSPUtils(conn);
        strMsgID =
                ttspUtils.getMsgLTTID(this.APP_SEND_CODE, this.APP_RECIEVE_CODE);


        //KT ngay dau thang
        String strNgayHienTai =
            StringUtil.DateToString(new Date(), "yyyyMMdd");
        String strNgayLamViecDauThang =
            StringUtil.DateToString(new Date(), "yyyyMM") + "01";
        strNgayLamViecDauThang =
                ttspUtils.getNgayLamViec(strNgayLamViecDauThang);
        if (strNgayHienTai.equals(strNgayLamViecDauThang)) {
            strHachToanTheoNgayKSNH = "Y";
        }
        //------------

        Iterator iter = list.iterator();
        QuyetToanVO qtVO = null;

        sbBody.append("<BODY>");
        sbBody.append("<MASTER>");
        String strDBHC = "";
        String strLoaiLaiPhi = "";
        String strDVSDNSLaiPhi = "";
        //        String strLoaiTien = "";
        while (iter.hasNext()) {
            qtVO = (QuyetToanVO)iter.next();

            if ("bk".equalsIgnoreCase(strType)) {
                Vector vtParam = new Vector();
                vtParam.add(new Parameter(qtVO.getMa_kb(), true));
                DMKBacDAO htkbDAO = new DMKBacDAO(conn);
                DMKBacVO htkbVO = htkbDAO.getDMKB(" AND a.ma = ?", vtParam);
                if (htkbVO.getMa_h() == null || "".equals(htkbVO.getMa_h())) {
                    if (htkbVO.getMa_t() == null ||
                        "".equals(htkbVO.getMa_t())) {
                        throw new Exception("Khong tim duoc ma dia ban cua KB " +
                                            qtVO.getMa_kb() +
                                            "(Can thiet lap ma tinh/huyen trong DM_HTKB)");
                    } else {
                        strDBHC =
                                htkbVO.getMa_t().replace("T", "").replace("H",
                                                                          "");
                    }
                } else {
                    strDBHC =
                            htkbVO.getMa_h().replace("T", "").replace("H", "");
                }
            }

            //Lay thong tin NH nhan
            DMNHangDAO dmDAO = new DMNHangDAO(conn);
            String strMa3SoNH = qtVO.getNhkb_chuyen().substring(2, 5);
            String strWhereClause = " and a.ma_dv = ? ";
            Vector parameters = new Vector();
            parameters.add(new Parameter(strMa3SoNH, true));
            DMNHangHOVO nhHOvo =
                dmDAO.getDMNHangHO(strWhereClause, parameters);

            if (nhHOvo == null) {
                throw new Exception("Kh&#244;ng t&#236;m th&#7845;y m&#227; &#7913;ng d&#7909;ng nh&#7853;n t&#432;&#417;ng &#7913;ng v&#7899;i m&#227; ng&#226;n h&#224;ng nh&#7853;n\n Duy&#7879;t l&#7879;nh kh&#244;ng th&#224;nh c&#244;ng.");
            }

            DmTSoHToanVO dmTSoHToanVO_no = null;
            DmTSoHToanVO dmTSoHToanVO_co = null;
            DmTSoHToanDAO dmTSoHToanDAO = new DmTSoHToanDAO(conn);

            strLoaiQT = qtVO.getLoai_qtoan();
            strLoaiHToan = qtVO.getLoai_hach_toan();
            strLoaiLaiPhi = qtVO.getLai_phi();

            String strKHLoaiHachToanNo = "";
            String strKHLoaiHachToanCo = "";
            //            if(!"VND".equalsIgnoreCase(qtVO.getMa_nt()) && qtVO.getMa_nt() != null){
            //              strLoaiTien = qtVO.getMa_nt().toUpperCase();
            //            }
            if ("bk".equalsIgnoreCase(strType)) {
                if (strLoaiHToan.equalsIgnoreCase("T")) {
                    strKHLoaiHachToanNo = "HACH_TOAN_DUNG_QTOAN_TQUOC_NO";
                    strKHLoaiHachToanCo = "HACH_TOAN_DUNG_QTOAN_TQUOC_CO";
                } else {
                    strKHLoaiHachToanNo = "HACH_TOAN_CXLY_QTOAN_TQUOC_NO";
                    strKHLoaiHachToanCo = "HACH_TOAN_CXLY_QTOAN_TQUOC_CO";
                }
            } else {
                if ("02".equals(strLoaiLaiPhi) || "06".equals(strLoaiLaiPhi)) {
                    DMKBacDAO htkbDAO = new DMKBacDAO(conn);
                    if ("D".equalsIgnoreCase(strLoaiQT)) {
                        if (strLoaiHToan.equalsIgnoreCase("T")) {
                            strDVSDNSLaiPhi =
                                    htkbDAO.getMaDVSDNSFromMaKB(strMaKB, "LAI_PHI");
                            strKHLoaiHachToanNo =
                                    "HACH_TOAN_DUNG_QTOAN_PHI_NO";
                            strKHLoaiHachToanCo =
                                    "HACH_TOAN_DUNG_QTOAN_PHI_CO";
                        } else if (strLoaiHToan.equalsIgnoreCase("P")) {
                            strKHLoaiHachToanNo =
                                    "HACH_TOAN_CXLY_QTOAN_PHI_NO";
                            strKHLoaiHachToanCo =
                                    "HACH_TOAN_CXLY_QTOAN_PHI_CO";
                        } else if (strLoaiHToan.equalsIgnoreCase("1339")) {
                            strDVSDNSLaiPhi =
                                    htkbDAO.getMaDVSDNSFromMaKB(strMaKB, "LAI_PHI");
                            strKHLoaiHachToanNo =
                                    "HACH_TOAN_1339_QTOAN_PHI_NO";
                            strKHLoaiHachToanCo =
                                    "HACH_TOAN_1339_QTOAN_PHI_CO";
                        } else if (strLoaiHToan.equalsIgnoreCase("3999")) {
                            strDVSDNSLaiPhi =
                                    htkbDAO.getMaDVSDNSFromMaKB(strMaKB, "LAI_PHI");
                            strKHLoaiHachToanNo =
                                    "HACH_TOAN_3999_QTOAN_LAI_NO";
                            strKHLoaiHachToanCo =
                                    "HACH_TOAN_3999_QTOAN_LAI_CO";
                        }
                    } else {
                        //TH tk thanh toans
                        if ("02".equals(qtVO.getLoai_tk())) {
                            if (strLoaiHToan.equalsIgnoreCase("T")) {
                                strDVSDNSLaiPhi =
                                        htkbDAO.getMaDVSDNSFromMaKB(strMaKB, "LAI_PHI");
                                strKHLoaiHachToanNo =
                                        "HACH_TOAN_DUNG_QTOAN_LAI_NO";
                                strKHLoaiHachToanCo =
                                        "HACH_TOAN_DUNG_QTOAN_LAI_CO";
                            } else if (strLoaiHToan.equalsIgnoreCase("3999")) {
                                strDVSDNSLaiPhi =
                                        htkbDAO.getMaDVSDNSFromMaKB(strMaKB, "LAI_PHI");
                                strKHLoaiHachToanNo =
                                        "HACH_TOAN_3999_QTOAN_LAI_NO";
                                strKHLoaiHachToanCo =
                                        "HACH_TOAN_3999_QTOAN_LAI_CO";
                            } else if (strLoaiHToan.equalsIgnoreCase("P")) {
                                strKHLoaiHachToanNo =
                                        "HACH_TOAN_CXLY_QTOAN_LAI_NO";
                                strKHLoaiHachToanCo =
                                        "HACH_TOAN_CXLY_QTOAN_LAI_CO";
                            }
                            //TH chuyen thu
                        } else {
                            if (strLoaiHToan.equalsIgnoreCase("T")) {
                                strDVSDNSLaiPhi =
                                        htkbDAO.getMaDVSDNSFromMaKB(strMaKB, "LAI_PHI");
                                strKHLoaiHachToanNo =
                                        "HACH_TOAN_CHUYEN_THU_DUNG_QTOAN_LAI_NO";
                                strKHLoaiHachToanCo =
                                        "HACH_TOAN_CHUYEN_THU_DUNG_QTOAN_LAI_CO";
                            } else if (strLoaiHToan.equalsIgnoreCase("3999")) {
                                strDVSDNSLaiPhi =
                                        htkbDAO.getMaDVSDNSFromMaKB(strMaKB, "LAI_PHI");
                                strKHLoaiHachToanNo =
                                        "HACH_TOAN_CHUYEN_THU_3999_QTOAN_LAI_NO";
                                strKHLoaiHachToanCo =
                                        "HACH_TOAN_CHUYEN_THU_3999_QTOAN_LAI_CO";
                            } else if (strLoaiHToan.equalsIgnoreCase("P")) {
                                strKHLoaiHachToanNo =
                                        "HACH_TOAN_CHUYEN_THU_CXLY_QTOAN_LAI_NO";
                                strKHLoaiHachToanCo =
                                        "HACH_TOAN_CHUYEN_THU_CXLY_QTOAN_LAI_CO";
                            }
                        }
                    }
                } else if ("04".equals(strLoaiLaiPhi)) {

                    if (strLoaiHToan.equalsIgnoreCase("T")) {
                        strKHLoaiHachToanNo = "HACH_TOAN_DUNG_CLECH_TGIA_NO";
                        strKHLoaiHachToanCo = "HACH_TOAN_DUNG_CLECH_TGIA_CO";
                    } else {
                        strKHLoaiHachToanNo = "HACH_TOAN_CXLY_CLECH_TGIA_NO";
                        strKHLoaiHachToanCo = "HACH_TOAN_CXLY_CLECH_TGIA_CO";
                    }

                }  else if ("05".equals(strLoaiLaiPhi)) {

                    if (strLoaiHToan.equalsIgnoreCase("T")) {
                      DMKBacDAO htkbDAO = new DMKBacDAO(conn);
                      strDVSDNSLaiPhi =
                              htkbDAO.getMaDVSDNSFromMaKB(strMaKB, "THU_POS");
                        strKHLoaiHachToanNo = "HACH_TOAN_DUNG_THU_POS_NO";
                        strKHLoaiHachToanCo = "HACH_TOAN_DUNG_THU_POS_CO";
                    } else {
                        strKHLoaiHachToanNo = "HACH_TOAN_CXLY_THU_POS_NO";
                        strKHLoaiHachToanCo = "HACH_TOAN_CXLY_THU_POS_CO";
                    }

//                } else if ("06".equals(strLoaiLaiPhi)) {
//
//                    if (strLoaiHToan.equalsIgnoreCase("T")) {
//                        strKHLoaiHachToanNo = "HACH_TOAN_DUNG_PHI_POS_NO";
//                        strKHLoaiHachToanCo = "HACH_TOAN_DUNG_PHI_POS_CO";
//                    } else {
//                        strKHLoaiHachToanNo = "HACH_TOAN_CXLY_PHI_POS_NO";
//                        strKHLoaiHachToanCo = "HACH_TOAN_CXLY_PHI_POS_CO";
//                    }

                }else {
                    if ("0003".equals(strMaKB)) {
                        if (strLoaiHToan.equalsIgnoreCase("T")) {
                            strKHLoaiHachToanNo =
                                    "HACH_TOAN_DUNG_QTOAN_SGD_NO";
                            strKHLoaiHachToanCo =
                                    "HACH_TOAN_DUNG_QTOAN_SGD_CO";
                        } else {
                            strKHLoaiHachToanNo =
                                    "HACH_TOAN_CXLY_QTOAN_SGD_NO";
                            strKHLoaiHachToanCo =
                                    "HACH_TOAN_CXLY_QTOAN_SGD_CO";
                        }
                    } else {
                        //Xac dinh co phan don vi la chuyen thu hay ko
                        //                        TKNHKBacDAO tknhkbdao = new TKNHKBacDAO(conn);
                        //                        Vector vParam2 = new Vector();
                        //                        vParam2.add(new Parameter(strMaKB, true));
                        //                        TKNHKBacVO tknhkbvo =
                        //                            tknhkbdao.getTK_NH_KB_VO(" and c.ma = ? ",
                        //                                                     vParam2);
                        //Truong hop chuyen thu
                        if ("03".equals(qtVO.getLoai_tk())) {
                            if (strLoaiHToan.equalsIgnoreCase("T")) {
                                strKHLoaiHachToanNo =
                                        "HACH_TOAN_CHUYEN_THU_QTOAN_DUNG_NO";
                                strKHLoaiHachToanCo =
                                        "HACH_TOAN_CHUYEN_THU_QTOAN_DUNG_CO";
                            } else {
                                strKHLoaiHachToanNo =
                                        "HACH_TOAN_CHUYEN_THU_QTOAN_CXL_NO";
                                strKHLoaiHachToanCo =
                                        "HACH_TOAN_CHUYEN_THU_QTOAN_CXL_CO";

                            }
                            //Truong hop thanh toan
                        } else {
                            if (strLoaiHToan.equalsIgnoreCase("T")) {
                                strKHLoaiHachToanNo =
                                        "HACH_TOAN_DUNG_QTOAN_HUYEN_NO";
                                strKHLoaiHachToanCo =
                                        "HACH_TOAN_DUNG_QTOAN_HUYEN_CO";
                            } else {
                                strKHLoaiHachToanNo =
                                        "HACH_TOAN_CXLY_QTOAN_HUYEN_NO";
                                strKHLoaiHachToanCo =
                                        "HACH_TOAN_CXLY_QTOAN_HUYEN_CO";

                            }
                        }
                    }
                }
            }
            //ManhNV-20141112:Sua hach toan quyet toan cho ngoai te ************************
            if (((!"VND".equalsIgnoreCase(qtVO.getMa_nt()) &&
                qtVO.getMa_nt() != null)) || "04".equals(strLoaiLaiPhi)) {
                strKHLoaiHachToanNo = "NGOAI_TE_" + strKHLoaiHachToanNo;
                strKHLoaiHachToanCo = "NGOAI_TE_" + strKHLoaiHachToanCo;
            }
            //******************************************************************************
            String whereClause = "and ma_nh=? and loai_htoan = ?";
            Vector params = new Vector();
            params.add(new Parameter(strMa3SoNH, true));
            params.add(new Parameter(strKHLoaiHachToanNo, true));
            dmTSoHToanVO_no = dmTSoHToanDAO.getDmTSoHToan(whereClause, params);
            if (dmTSoHToanVO_no == null) {
                throw new Exception("Chua khai bao danh muc tai khoan hach toan tabmis. Hay lien he voi KBNN TW de kiem tra.");
            }
            params = new Vector();
            params.add(new Parameter(strMa3SoNH, true));
            params.add(new Parameter(strKHLoaiHachToanCo, true));
            dmTSoHToanVO_co = dmTSoHToanDAO.getDmTSoHToan(whereClause, params);
            if (dmTSoHToanVO_co == null) {
                throw new Exception("Chua khai bao danh muc tai khoan hach toan tabmis. Hay lien he voi KBNN TW de kiem tra.");
            }
            ////////
            if ("".equalsIgnoreCase(strMaKB) || strMaKB == null) {
                strMaKB = qtVO.getMa_kb();
            }

            //ROW 1
            sbBody.append("<ROW>");
            sbBody.append("<ID>");
            sbBody.append(qtVO.getId() + "001");
            sbBody.append("</ID>");
            sbBody.append("<FILE_NAME>");
            sbBody.append("</FILE_NAME>");
            sbBody.append("<SOURCE_MODULE>");
            sbBody.append(GL_SOURCE_MODULE);
            sbBody.append("</SOURCE_MODULE>");
            sbBody.append("<INTER_TRY_DESTINATION>");
            sbBody.append(strMaKB);
            sbBody.append("</INTER_TRY_DESTINATION>");
            sbBody.append("<DESTINATION_JOURNAL_NAME>");
            if ("bk".equalsIgnoreCase(strType)) {
                sbBody.append(strID.substring(0, 4) + strID.substring(10, 14) +
                              qtVO.getId());
            } else {
                sbBody.append(strMaKB +
                              AppConstants.APP_NAME_HACH_TOAN_TABMIS +
                              qtVO.getId_ref());
            }
            sbBody.append("</DESTINATION_JOURNAL_NAME>");
            sbBody.append("<CATEGORY>");
//            sbBody.append("LNH");
            sbBody.append("TTSP");
            sbBody.append("</CATEGORY>");
            sbBody.append("<DESCRIPTION>");
            sbBody.append(StringUtil.xmlSpeReplace(qtVO.getNdung_tt()));
            sbBody.append("</DESCRIPTION>");
            sbBody.append("<ACCOUNTING_DATE>");
            if ("Y".equalsIgnoreCase(strHachToanTheoNgayKSNH)) {
                sbBody.append(qtVO.getNgay_htoan());
            } else {
                sbBody.append(qtVO.getNgay_insert().substring(0, 10));
            }
            sbBody.append("</ACCOUNTING_DATE>");
            sbBody.append("<TRANACTION_DATE>");
            sbBody.append(StringUtil.DateToString(new Date(),
                                                  AppConstants.DATE_FORMAT_SEND_ORDER));
            sbBody.append("</TRANACTION_DATE>");
            sbBody.append("<CURRENCY>");
            sbBody.append(qtVO.getMa_nt());
            sbBody.append("</CURRENCY>");
            sbBody.append("<LINE_NUMBER>");
            sbBody.append("1");
            sbBody.append("</LINE_NUMBER>");
            sbBody.append("<FUND_TYPE>");
            sbBody.append(dmTSoHToanVO_no.getQuy());
            sbBody.append("</FUND_TYPE>");
            sbBody.append("<NATURAL_ACCOUNT>");
            sbBody.append(dmTSoHToanVO_no.getTktn());
            sbBody.append("</NATURAL_ACCOUNT>");
            sbBody.append("<ECONOMIC_CODE>");
            sbBody.append(dmTSoHToanVO_no.getNdkt());
            sbBody.append("</ECONOMIC_CODE>");
            sbBody.append("<BUDGET_LEVEL>");
            sbBody.append(dmTSoHToanVO_no.getCap_ns());
            sbBody.append("</BUDGET_LEVEL>");
            sbBody.append("<ORGANISATION>");
            //BEGIN:20151123-Sua ma qhns cho hanh toan thu POS
            if ("05".equals(strLoaiLaiPhi)) {
                sbBody.append(strDVSDNSLaiPhi);
            } else {
            if ("D".equalsIgnoreCase(strLoaiQT) &&
                !"".equalsIgnoreCase(strDVSDNSLaiPhi)) {
                sbBody.append(strDVSDNSLaiPhi);
            } else {
                sbBody.append(dmTSoHToanVO_no.getDvsdns());
            }
            }
            //          if ("D".equalsIgnoreCase(strLoaiQT) &&
            //              !"".equalsIgnoreCase(strDVSDNSLaiPhi)) {
            //              sbBody.append(strDVSDNSLaiPhi);
            //          } else {
            //              sbBody.append(dmTSoHToanVO_no.getDvsdns());
            //          }
            //END:20151123
            sbBody.append("</ORGANISATION>");
            sbBody.append("<LOCATION>");
            sbBody.append(dmTSoHToanVO_no.getDbhc());
            sbBody.append("</LOCATION>");
            sbBody.append("<CHAPTER_LEVEL>");
            sbBody.append(dmTSoHToanVO_no.getChuong());
            sbBody.append("</CHAPTER_LEVEL>");
            sbBody.append("<FUNCTION_CODE>");
            sbBody.append(dmTSoHToanVO_no.getNganh_kt());
            sbBody.append("</FUNCTION_CODE>");
            sbBody.append("<TARGETED_PROG>");
            sbBody.append(dmTSoHToanVO_no.getCtmt());
            sbBody.append("</TARGETED_PROG>");
            sbBody.append("<TREASURY>");
            sbBody.append(strMaKB);
            sbBody.append("</TREASURY>");
            sbBody.append("<SOURCE>");
            sbBody.append(dmTSoHToanVO_no.getNguon());
            sbBody.append("</SOURCE>");
            sbBody.append("<SPARED>");
            sbBody.append(dmTSoHToanVO_no.getDu_phong());
            sbBody.append("</SPARED>");
            sbBody.append("<DEBIT_AMOUNT>");
            if (strLoaiLaiPhi.equals("02")) {
                sbBody.append(qtVO.getSo_tien());
            } else {
                if (strLoaiQT.equalsIgnoreCase("D")) {
                    sbBody.append(qtVO.getSo_tien());
                }
            }
            sbBody.append("</DEBIT_AMOUNT>");
            sbBody.append("<CREDIT_AMOUNT>");
            if (strLoaiLaiPhi.equals("02")) {
                //
            } else {
                if (strLoaiQT.equalsIgnoreCase("C")) {
                    sbBody.append(qtVO.getSo_tien());
                }
            }
            sbBody.append("</CREDIT_AMOUNT>");
            sbBody.append("<PROCESS_CODE>");
            sbBody.append("</PROCESS_CODE>");
            sbBody.append("<STATUS>");
            sbBody.append("</STATUS>");
            sbBody.append("<DATA_LINE>");
            sbBody.append("</DATA_LINE>");
            sbBody.append("<ERROR_CODE>");
            sbBody.append("</ERROR_CODE>");
            sbBody.append("<ERROR_DESCRIPTION>");
            sbBody.append("</ERROR_DESCRIPTION>");
            sbBody.append("<ACK_SENT>");
            sbBody.append("</ACK_SENT>");
            sbBody.append("<PROCESS_DATE>");
            sbBody.append("</PROCESS_DATE>");
            sbBody.append("<SOURCE_REFER_NUMBER>");
            sbBody.append("</SOURCE_REFER_NUMBER>");
            sbBody.append("<MSG_ID>");
            sbBody.append(strMsgID);
            sbBody.append("</MSG_ID>");
            sbBody.append("<TABMIS_PROCESS_DATE>");
            sbBody.append(StringUtil.DateToString(new Date(),
                                                  AppConstants.DATE_TIME_FORMAT_SEND_ORDER));
            sbBody.append("</TABMIS_PROCESS_DATE>");
            sbBody.append("<OLD_STATUS>");
            sbBody.append("</OLD_STATUS>");
            sbBody.append("<BANK_CODE_SENDER>");
            sbBody.append(qtVO.getNhkb_chuyen());
            sbBody.append("</BANK_CODE_SENDER>");
            sbBody.append("<BANK_CODE_RECEIVER>");
            sbBody.append(qtVO.getNhkb_nhan());
            sbBody.append("</BANK_CODE_RECEIVER>");
            sbBody.append("</ROW>");
            //ROW 2
            sbBody.append("<ROW>");
            sbBody.append("<ID>");
            sbBody.append(qtVO.getId() + "002");
            sbBody.append("</ID>");
            sbBody.append("<FILE_NAME>");
            sbBody.append("</FILE_NAME>");
            sbBody.append("<SOURCE_MODULE>");
            sbBody.append(GL_SOURCE_MODULE);
            sbBody.append("</SOURCE_MODULE>");
            sbBody.append("<INTER_TRY_DESTINATION>");
            sbBody.append(strMaKB);
            sbBody.append("</INTER_TRY_DESTINATION>");
            sbBody.append("<DESTINATION_JOURNAL_NAME>");
            if ("bk".equalsIgnoreCase(strType)) {

                sbBody.append(strID.substring(0, 4) + strID.substring(10, 14) +
                              qtVO.getId());
            } else {
                sbBody.append(strMaKB +
                              AppConstants.APP_NAME_HACH_TOAN_TABMIS +
                              qtVO.getId_ref());
            }
            sbBody.append("</DESTINATION_JOURNAL_NAME>");
            sbBody.append("<CATEGORY>");
//            sbBody.append("LNH");
            sbBody.append("TTSP");
            sbBody.append("</CATEGORY>");
            sbBody.append("<DESCRIPTION>");
            sbBody.append(StringUtil.xmlSpeReplace(qtVO.getNdung_tt()));
            sbBody.append("</DESCRIPTION>");
            sbBody.append("<ACCOUNTING_DATE>");
            if ("Y".equalsIgnoreCase(strHachToanTheoNgayKSNH)) {
                sbBody.append(qtVO.getNgay_htoan());
            } else {
                sbBody.append(qtVO.getNgay_insert().substring(0, 10));
            }
            sbBody.append("</ACCOUNTING_DATE>");
            sbBody.append("<TRANACTION_DATE>");
            sbBody.append(StringUtil.DateToString(new Date(),
                                                  AppConstants.DATE_FORMAT_SEND_ORDER));
            sbBody.append("</TRANACTION_DATE>");
            sbBody.append("<CURRENCY>");
            sbBody.append(qtVO.getMa_nt());
            sbBody.append("</CURRENCY>");
            sbBody.append("<LINE_NUMBER>");
            sbBody.append("2");
            sbBody.append("</LINE_NUMBER>");
            sbBody.append("<FUND_TYPE>");
            sbBody.append(dmTSoHToanVO_co.getQuy());

            sbBody.append("</FUND_TYPE>");
            sbBody.append("<NATURAL_ACCOUNT>");

            sbBody.append(dmTSoHToanVO_co.getTktn());

            sbBody.append("</NATURAL_ACCOUNT>");
            sbBody.append("<ECONOMIC_CODE>");
            sbBody.append(dmTSoHToanVO_co.getNdkt());

            sbBody.append("</ECONOMIC_CODE>");
            sbBody.append("<BUDGET_LEVEL>");
            sbBody.append(dmTSoHToanVO_co.getCap_ns());
            sbBody.append("</BUDGET_LEVEL>");
            sbBody.append("<ORGANISATION>");
            //BEGIN:20151123-Sua ma QHNS cho thu POS
            if ("05".equals(strLoaiLaiPhi)) {
              sbBody.append(dmTSoHToanVO_co.getDvsdns());
            }else {
            if ("C".equalsIgnoreCase(strLoaiQT) &&
                !"".equalsIgnoreCase(strDVSDNSLaiPhi)) {
                sbBody.append(strDVSDNSLaiPhi);
            } else {
                sbBody.append(dmTSoHToanVO_co.getDvsdns());
            }
            }
            //          if ("C".equalsIgnoreCase(strLoaiQT) &&
            //              !"".equalsIgnoreCase(strDVSDNSLaiPhi)) {
            //              sbBody.append(strDVSDNSLaiPhi);
            //          } else {
            //              sbBody.append(dmTSoHToanVO_co.getDvsdns());
            //          }
            //END:20151123
            sbBody.append("</ORGANISATION>");
            sbBody.append("<LOCATION>");
            if (!"".equals(strDBHC)) {
                sbBody.append(strDBHC);
            } else {
                sbBody.append(dmTSoHToanVO_co.getDbhc());
            }
            sbBody.append("</LOCATION>");
            sbBody.append("<CHAPTER_LEVEL>");
            sbBody.append(dmTSoHToanVO_co.getChuong());
            sbBody.append("</CHAPTER_LEVEL>");
            sbBody.append("<FUNCTION_CODE>");
            sbBody.append(dmTSoHToanVO_co.getNganh_kt());

            sbBody.append("</FUNCTION_CODE>");
            sbBody.append("<TARGETED_PROG>");
            sbBody.append(dmTSoHToanVO_co.getCtmt());

            sbBody.append("</TARGETED_PROG>");
            sbBody.append("<TREASURY>");
            sbBody.append(strMaKB);
            sbBody.append("</TREASURY>");
            sbBody.append("<SOURCE>");
            sbBody.append(dmTSoHToanVO_co.getNguon());

            sbBody.append("</SOURCE>");
            sbBody.append("<SPARED>");
            sbBody.append(dmTSoHToanVO_co.getDu_phong());
            sbBody.append("</SPARED>");
            sbBody.append("<DEBIT_AMOUNT>");
            if (strLoaiLaiPhi.equals("02")) {
                //
            } else {
                if (strLoaiQT.equalsIgnoreCase("C")) {
                    sbBody.append(qtVO.getSo_tien());
                }
            }
            sbBody.append("</DEBIT_AMOUNT>");
            sbBody.append("<CREDIT_AMOUNT>");
            if (strLoaiLaiPhi.equals("02")) {
                sbBody.append(qtVO.getSo_tien());
            } else {
                if (strLoaiQT.equalsIgnoreCase("D")) {
                    sbBody.append(qtVO.getSo_tien());
                }
            }
            sbBody.append("</CREDIT_AMOUNT>");
            sbBody.append("<PROCESS_CODE>");
            sbBody.append("</PROCESS_CODE>");
            sbBody.append("<STATUS>");
            sbBody.append("</STATUS>");
            sbBody.append("<DATA_LINE>");
            sbBody.append("</DATA_LINE>");
            sbBody.append("<ERROR_CODE>");
            sbBody.append("</ERROR_CODE>");
            sbBody.append("<ERROR_DESCRIPTION>");
            sbBody.append("</ERROR_DESCRIPTION>");
            sbBody.append("<ACK_SENT>");
            sbBody.append("</ACK_SENT>");
            sbBody.append("<PROCESS_DATE>");
            sbBody.append("</PROCESS_DATE>");
            sbBody.append("<SOURCE_REFER_NUMBER>");
            sbBody.append("</SOURCE_REFER_NUMBER>");
            sbBody.append("<MSG_ID>");
            sbBody.append(strMsgID);
            sbBody.append("</MSG_ID>");
            sbBody.append("<TABMIS_PROCESS_DATE>");
            sbBody.append(StringUtil.DateToString(new Date(),
                                                  AppConstants.DATE_TIME_FORMAT_SEND_ORDER));
            sbBody.append("</TABMIS_PROCESS_DATE>");
            sbBody.append("<OLD_STATUS>");
            sbBody.append("</OLD_STATUS>");
            sbBody.append("<BANK_CODE_SENDER>");
            sbBody.append(qtVO.getNhkb_chuyen());
            sbBody.append("</BANK_CODE_SENDER>");
            sbBody.append("<BANK_CODE_RECEIVER>");
            sbBody.append(qtVO.getNhkb_nhan());
            sbBody.append("</BANK_CODE_RECEIVER>");
            sbBody.append("</ROW>");

            iCounter++;
        }

        sbBody.append("</MASTER>");
        sbBody.append("</BODY>");

        if (iCounter == 0) {
            throw new Exception("Khong tim thay chung tu co ID = " + strID);
        }
        //Tao msg header
        MsgHeader msgHeader = new MsgHeader();
        msgHeader.setVersion(AppConstants.VERSION_MSG);
        msgHeader.setSender_code(APP_SEND_CODE);
        msgHeader.setSender_name(APP_SEND_NAME);
        msgHeader.setReveiver_code(APP_RECIEVE_CODE);
        msgHeader.setReceiver_name(APP_RECIEVE_NAME);
        if (strLoaiQT.equalsIgnoreCase("D")) {
            msgHeader.setTran_code(TRAN_CODE_QTOAN_NO);
        } else {
            msgHeader.setTran_code(TRAN_CODE_QTOAN_CO);
        }
        msgHeader.setMsg_id(strMsgID);
        msgHeader.setMsg_refid("");
        //Noi header va body -> tao msg
        sbMsg.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        sbMsg.append("<DATA>");
        sbMsg.append(msgHeader.getHeader());
        sbMsg.append(sbBody).toString();
        sbMsg.append("<SECURITY>");
        sbMsg.append("<SIGNATURE>");
        sbMsg.append("</SIGNATURE>");
        sbMsg.append("</SECURITY>");
        sbMsg.append("</DATA>");

        //Build log msg
        MsgDAO msgDAO = new MsgDAO(conn);
        MsgVO msg = new MsgVO();

        msg.setMsg_content(sbMsg.toString());
        msg.setMsg_id(strMsgID);
        msg.setMsg_type(strLoaiQT.equalsIgnoreCase("D") ? TRAN_CODE_QTOAN_NO :
                        TRAN_CODE_QTOAN_CO);
        msg.setSender(APP_SEND_CODE);
        msg.setReciever(APP_RECIEVE_CODE);

        //Put msg to queue
        queueManager.putMQ(sbMsg.toString());
        //Ghi log
        try {
            msgDAO.insert(msg);
        } catch (Exception e) {
            Exception ex =
                new Exception("Loi ghi log(com.seatech.ttsp.proxy.giaodien.bank.SendLQToan.sendMessage()): " +
                              e.getMessage());
            ex.printStackTrace();
            throw ex;
        }

        return strMsgID;
    }

    /**
     * @see: Lay tham so quyet toan tu CSDL
     * @param: strType: Loai hach toan theo bang ke hay theo chung tu
     * */
    //    private void setThamSoHeThong() throws Exception {
    //        ThamSoHThongDAO tshtDAO = new ThamSoHThongDAO(conn);
    //        String strWhereClauseTS = "";
    //
    //        strWhereClauseTS = " and (ten_ts in (?,?,?,?,?, ?))";
    //
    //        Vector vtParam = new Vector();
    //        vtParam.add(new Parameter("GL_SOURCE_MODULE", true));
    //        vtParam.add(new Parameter("MQ_HOSTNAME", true));
    //        vtParam.add(new Parameter("MQ_CHANEL", true));
    //        vtParam.add(new Parameter("MQ_PORT", true));
    //        vtParam.add(new Parameter("MQ_MANAGER_NAME", true));
    //        vtParam.add(new Parameter("MQ_NAME", true));
    //
    //        Collection tsList = tshtDAO.getThamSoList(strWhereClauseTS, vtParam);
    //        Iterator iter = tsList.iterator();
    //        ThamSoHThongVO tsVO = null;
    //        String strTenTS = "";
    //        while (iter.hasNext()) {
    //            tsVO = (ThamSoHThongVO)iter.next();
    //            strTenTS = tsVO.getTen_ts();
    //            if (strTenTS.equalsIgnoreCase("GL_SOURCE_MODULE")) {
    //                GL_SOURCE_MODULE = tsVO.getGiatri_ts();
    //            } else if (strTenTS.equalsIgnoreCase("MQ_HOSTNAME")) {
    //                MQ_HOSTNAME = tsVO.getGiatri_ts();
    //            } else if (strTenTS.equalsIgnoreCase("MQ_CHANEL")) {
    //                MQ_CHANEL = tsVO.getGiatri_ts();
    //            } else if (strTenTS.equalsIgnoreCase("MQ_PORT")) {
    //                MQ_PORT = tsVO.getGiatri_ts();
    //            } else if (strTenTS.equalsIgnoreCase("MQ_NAME")) {
    //                MQ_NAME = tsVO.getGiatri_ts();
    //            } else if (strTenTS.equalsIgnoreCase("MQ_MANAGER_NAME")) {
    //                MQ_MANAGER_NAME = tsVO.getGiatri_ts();
    //            } else if (strTenTS.equalsIgnoreCase("GL_SOURCE_MODULE")) {
    //                GL_SOURCE_MODULE = tsVO.getGiatri_ts();
    //            }
    //        }
    //    }

    public static void main(String[] args) {
        try {
            //            AppDAO dao = new AppDAO();
            //            Connection conn = dao.getConnectionTest();
            //            SendLQToan sendLQToan = new SendLQToan(conn);
            //                         sendLQToan.sendMessage("1320290000010278", "CT","0015","Y");
            //            sendLQToan.sendMessage("0003120621TTSP244", "BK", "0003");
            //            String s = "xxxxxxxxxx";
            //            while (true) {
            //                try {
            //                    s += s;
            //                    System.out.println("Size: " + s.length());
            //                } catch (Exception ex) {
            //                    s += "x";
            //                }
            //            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
