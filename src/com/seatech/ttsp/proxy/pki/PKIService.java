package com.seatech.ttsp.proxy.pki;


import com.seatech.framework.AppKeys;
import com.seatech.framework.utils.StringUtil;
import com.seatech.pki.client.PKISoapClient;
import com.seatech.utils.XMLGregorianCalendarUtils;

import java.util.Date;
import java.util.List;

import vn.gov.vst.CertInfo;


public class PKIService {
    private static final String parternDate = "dd/MM/yyyy";
    private String strWdsl;

    public PKIService(String strWdsl) {
        super();
        this.strWdsl = strWdsl;
    }

    /**
     * @see: Goi ws kiem tra tinh hop le cua chu ky
     * @return: String[] gom 2 phan tu, [1]: INVALID(ko hop le) or VALID(Hop le) or ERROR(Loi ky thuat)
     * *[2]: Thong tin trong truong hop [1] = INVALID or ERROR
     * */

    public String[] VerifySignature(String userName, String certSerial,
                                    String contenData, String signature,
                                    String strAppID) throws Exception {
        String[] strResult = null;
        if ("N".equalsIgnoreCase(AppKeys.SETUP_VERIFY)) {
            strResult = new String[2];
            strResult[0] = "VALID";
            strResult[1] = "";
        } else {
            PKISoapClient pKISoapClient = new PKISoapClient(strWdsl);
            strResult =
                    pKISoapClient.verifySignature(strAppID, userName, certSerial,
                                                  contenData, signature);
        }
        return strResult;
    }

    /**
     * @see: Goi ws de phe duyet chung thu so
     * @param: validFrom va validTo la 2 truong ngay theo dinh dang dd/mm/yyyy
     * @throws: Throws exception neu duyet khong thanh cong hoac co loi xay ra
     * */
    public void approveCert(String userName, String certSerial,
                            String validFrom, String validTo,
                            String approveUser,
                            String strAppID) throws Exception {
        PKISoapClient pKISoapClient = new PKISoapClient(strWdsl);
        Date dValidFrom = StringUtil.StringToDate(validFrom, parternDate);
        Date dValidTo = StringUtil.StringToDate(validTo, parternDate);
        pKISoapClient.approveCert(strAppID, userName, certSerial, dValidFrom,
                                  dValidTo, approveUser);
    }

    /**
     * @see: Goi ws lay danh sach chung thu so
     * @param: boolean, true( Danh sach CTS da dc phe duyet), false (DS CTS dang cho phe duyet)
     * @return: List<CertInfo>
     * */
    public List getCerts(boolean include, String strAppID) throws Exception {
        PKISoapClient pKISoapClient = new PKISoapClient(strWdsl);

        List<CertInfo> lsCert = pKISoapClient.getCert(strAppID, include);
        return lsCert;
    }

    /**
     * @see: Goi ws lay thong tin cua cert
     * @param: username, serial
     * @return: CertInfo
     * */
    public CertInfo getCertInfo(String userName, String serial,
                                String strAppID) throws Exception {
        PKISoapClient pKISoapClient = new PKISoapClient(strWdsl);

        CertInfo certInfo =
            pKISoapClient.getUserCertificate(strAppID, userName, serial);
        return certInfo;
    }

    /**
     * @see: Dang ky CTS
     * @param: validFrom va validTo la String date dd/mm/yyyy
     * @return:
     * @throws: Exception neu co loi xay ra khi upload, Dang ky CTS ko thanh cong
     * */
    public void uploadCertificate(String userName, String certSerial,
                                  String certContent, String validFrom,
                                  String validTo,
                                  String strAppID) throws Exception {
        PKISoapClient pKISoapClient = new PKISoapClient(strWdsl);
        Date dValidFrom = StringUtil.StringToDate(validFrom, parternDate);
        Date dValidTo = StringUtil.StringToDate(validTo, parternDate);
        pKISoapClient.uploadCertificate(strAppID, userName, certSerial,
                                        certContent, dValidFrom, dValidTo);
        
    }

    public PKIBean getCerInfo(CertInfo cInfo) {
        PKIBean pkibean = new PKIBean();
        pkibean.setApproveDate((cInfo.getApproveDate().getYear() != 1) ?
                               StringUtil.DateToString(XMLGregorianCalendarUtils.convertXMLGregorianCalendarToDate(cInfo.getApproveDate()),
                                                       parternDate) : null);
        pkibean.setApprovedUser(cInfo.getApprovedUser());
        pkibean.setCertContent(cInfo.getCertContent());
        pkibean.setCertSerial(cInfo.getCertSerial());
        pkibean.setIssuedBy(cInfo.getIssuedBy());
        pkibean.setIssuedTo(cInfo.getIssuedTo());
        pkibean.setLastUpdateDate((cInfo.getLastUpdateDate().getYear() > 1) ?
                                  StringUtil.DateToString(XMLGregorianCalendarUtils.convertXMLGregorianCalendarToDate(cInfo.getLastUpdateDate()),
                                                          parternDate) : null);
        pkibean.setLastUpdatedBy(cInfo.getLastUpdatedBy());
        pkibean.setRevokeDate((cInfo.getRevokeDate().getYear() > 1) ?
                              StringUtil.DateToString(XMLGregorianCalendarUtils.convertXMLGregorianCalendarToDate(cInfo.getRevokeDate()),
                                                      parternDate) : null);
        pkibean.setUploadDate((cInfo.getUploadDate().getYear() > 1) ?
                              StringUtil.DateToString(XMLGregorianCalendarUtils.convertXMLGregorianCalendarToDate(cInfo.getUploadDate()),
                                                      parternDate) : null);
        pkibean.setUploadedApplication(cInfo.getUploadedApplication());
        pkibean.setUserName(cInfo.getUserName().replace('\\', '/'));
        pkibean.setValidFrom((cInfo.getValidFrom().getYear() > 1) ?
                             StringUtil.DateToString(XMLGregorianCalendarUtils.convertXMLGregorianCalendarToDate(cInfo.getValidFrom()),
                                                     parternDate) : null);
        pkibean.setValidOnAppFrom((cInfo.getValidOnAppFrom().getYear() > 1) ?
                                  StringUtil.DateToString(XMLGregorianCalendarUtils.convertXMLGregorianCalendarToDate(cInfo.getValidOnAppFrom()),
                                                          parternDate) : null);
        pkibean.setValidOnAppTo((cInfo.getValidOnAppTo().getYear() > 1) ?
                                StringUtil.DateToString(XMLGregorianCalendarUtils.convertXMLGregorianCalendarToDate(cInfo.getValidOnAppTo()),
                                                        parternDate) : null);
        pkibean.setValidTo((cInfo.getValidTo().getYear() > 1) ?
                           StringUtil.DateToString(XMLGregorianCalendarUtils.convertXMLGregorianCalendarToDate(cInfo.getValidTo()),
                                                   parternDate) : null);
        return pkibean;
    }

    public static void main(String[] args) {
        String userName = "mb\\KTTHY2";
        String certSerial = "6AF732";
        String contenData =
            "[H]NHCT THUC HIEN TRA LOI TRA SOAT 195 DEN,1370119500003994,02,27/07/2013,,DD106050,,1220119600000338,1210619600000038,3620,,02/08/2012,1443,de nghi NH gui dien 196 de tra loi cho KB23-07-2012/VND/500000 ,02/08/2012,DD106050,100352,,02/08/2012[H]";
        String signature =
            "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";
        String strAppID = "D2A17E4C31D147DA8ECA2F7EC33CB774";
        PKIService p = new PKIService(null);
        try {
            p.VerifySignature(userName, certSerial, contenData, signature,
                              strAppID);
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }


    }
}
