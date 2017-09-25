package com.seatech.ttsp.proxy.dm;

import com.seatech.framework.AppConstants;
import com.seatech.khcdm.client.TABMISSoapClient;

public class DMCheo {
    private String strWsdl;
    public DMCheo(String strWsdl) {
        super();
        this.strWsdl = strWsdl;
    }
  
    public String[] checkKHCDM(String maQuy, String tkkt, String ndkt,
                               String capns, String dvqhns, String dbhc,
                               String chuong, String nganh, String ctmt,
                               String makb, String nguon,
                               String duphong, String strAppID) throws Exception {

        TABMISSoapClient tAMISSoapClient = new TABMISSoapClient(strWsdl);
        String strResult[] =
            tAMISSoapClient.kiemTraKHC(strAppID, maQuy, tkkt, ndkt,
                                       capns, dvqhns, dbhc, chuong, nganh,
                                       ctmt, makb, nguon, duphong);
//        String [] strResult = new String [2];
//       
//        strResult[0] = "VALID";
//        strResult[1] = "";
       
        return strResult;
    }
    public static void main(String[] args) {
      DMCheo d = new DMCheo("");
        try {
            d.checkKHCDM("", "", "", "", "", "", "", "", "", "", "", "", AppConstants.APP_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
