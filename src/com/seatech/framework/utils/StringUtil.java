package com.seatech.framework.utils;

import java.math.BigDecimal;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;


public class StringUtil {

    public static String IntToStr(int number) {
        return Integer.toString(number);
    }

    public static int StrToInt(String str) {
        Integer in = new Integer(str);
        return in.intValue();
    }

    public static boolean stringToBoolean(String context) {
        boolean booleanValue = false;
        if ((context != null) && (context.trim().length() > 0)) {
            if ((context.equalsIgnoreCase("yes")) ||
                (context.equalsIgnoreCase("T"))) {
                booleanValue = true;
            } else if ((context.equalsIgnoreCase("no")) ||
                       (context.equalsIgnoreCase("F"))) {
                booleanValue = false;
            } else {
                //  System.out.println("Unable to convert: defaulting to false");
                booleanValue = false;
            }
        }
        return booleanValue;
    }

    public static String booleanToString(boolean booleanValue) {
        if (booleanValue)
            return "yes";
        return "no";
    }

    public static int booleanStringToInt(String context) {
        int value = 0;
        if ((context != null) && (context.trim().length() > 0)) {
            if (context.equalsIgnoreCase("yes")) {
                value = 1;
            } else if (context.equalsIgnoreCase("no")) {
                value = 0;
            } else {
                value = 0;
            }
        }
        return value;
    }

    public static String intToBooleanString(int value) {
        if (value == 1)
            return "yes";
        return "no";
    }

    public static String charToBooleanString(String context) {
        String value = "no";
        if ((context != null) && (context.trim().length() > 0)) {
            if (context.equalsIgnoreCase("Y")) {
                value = "yes";
            }
        }
        return value;
    }

    public static String booleanStringToChar(String context) {
        String value = "N";
        if ((context != null) && (context.trim().length() > 0)) {
            if (context.equalsIgnoreCase("YES")) {
                value = "Y";
            } else if (context.equalsIgnoreCase("CHECKED")) {
                value = "Y";
            }
        }
        return value;
    }

    public static String DateToString(Date d, String formatter) {
        if (d == null) {
            return null;
        } else {
            SimpleDateFormat fm = new SimpleDateFormat(formatter);
            String dateString;
            dateString = fm.format(d);
            return dateString;
        }
    }

    public static Date StringToDate(String myString, String formatter) {
        if (myString == null) {
            return null;
        } else {
            SimpleDateFormat fm = new SimpleDateFormat(formatter);
            ParsePosition pos = new ParsePosition(0);
            Date d = fm.parse(myString, pos);
            return d;
        }
    }

    public static String getCurrentDate() {
        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateNow = formatter.format(currentDate.getTime());
        return dateNow;
    }

    public static String getPreviousNextDate(int numberDay) {
        int MILLIS_IN_DAY = 1000 * 60 * 60 * 24;
        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //        String prevDate = dateFormat.format(date.getTime() - MILLIS_IN_DAY);
        //        String currDate = dateFormat.format(date.getTime());
        //        String nextDate = dateFormat.format(date.getTime() + MILLIS_IN_DAY);
        String resultDate = "";
        if (numberDay != 0) {
            resultDate =
                    dateFormat.format(currentDate.getTimeInMillis() + (MILLIS_IN_DAY *
                                                                       numberDay));
        } else if (numberDay == 0) {
            resultDate = dateFormat.format(currentDate.getTime());
        }
        return resultDate;
    }
    /**
     * Ham lay next/previous date
     * */
    public static String getPreviousNextDate(Calendar date,int numberDay) {
      int MILLIS_IN_DAY = 1000 * 60 * 60 * 24;

      SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      String resultDate = "";
      if (numberDay != 0) {
          resultDate =
                  dateFormat.format(date.getTimeInMillis() + (MILLIS_IN_DAY *
                                                                     numberDay));
      } else if (numberDay == 0) {
          resultDate = dateFormat.format(date.getTime());
      }
      return resultDate;
   }
    public static String formatCurrency(String inValue) {

        if (inValue == null) {
            return "";
        }
        if (inValue.equals("")) {
            return "";
        }
        int i = inValue.indexOf(",");
        String outValue = "";
        if (i != -1) {
            outValue = inValue.substring(i);
            inValue = inValue.substring(0, i);
        }
        while (inValue.length() > 3) {
            outValue =
                    "." + inValue.substring(inValue.length() - 3, inValue.length()) +
                    outValue;
            inValue = inValue.substring(0, inValue.length() - 3);
        }
        if (inValue.equals("") || inValue.equals("-")) {
            outValue = inValue + outValue.substring(1);
        } else {
            outValue = inValue + outValue;
        }
        return outValue;
    }
    //KhangDQ

    public static String formatCurrency(String inValue, int nt_id) {
        if (inValue == null) {
            return "";
        }
        if (inValue.equals("")) {
            return "";
        }
        int i = -1;
        if (nt_id == 177) {
            i = inValue.indexOf(",");
        } else {
            i = inValue.indexOf(".");
        }
        String outValue = "";
        if (i != -1) {
            outValue = inValue.substring(i);
            inValue = inValue.substring(0, i);
        }
        while (inValue.length() > 3) {
            if (nt_id == 177)
                outValue =
                        "." + inValue.substring(inValue.length() - 3, inValue.length()) +
                        outValue;
            else
                outValue =
                        "," + inValue.substring(inValue.length() - 3, inValue.length()) +
                        outValue;
            inValue = inValue.substring(0, inValue.length() - 3);
        }
        if (!inValue.equals("")) {
            outValue = inValue + outValue;
        } else {
            if (outValue.length() > 0)
                outValue = inValue + outValue.substring(1);
            else
                outValue = inValue;
        }

        return outValue;
    }
  public static String formatCurrencyByCode(String inValue, String nt_id) {
      if (inValue == null) {
          return "";
      }
      if (inValue.equals("")) {
          return "";
      }
      int i = -1;
      if ("177".equals(nt_id) || "VND".equals(nt_id)) {
          i = inValue.indexOf(",");
      } else {
          i = inValue.indexOf(".");
      }
      String outValue = "";
      if (i != -1) {
          outValue = inValue.substring(i);
          inValue = inValue.substring(0, i);        
      }else{
          if (!"177".equals(nt_id) && !"VND".equals(nt_id)){
              outValue = ".00";
          }
      }
      while (inValue.length() > 3) {
            if ("177".equals(nt_id) || "VND".equals(nt_id)) 
              outValue =
                      "." + inValue.substring(inValue.length() - 3, inValue.length()) +
                      outValue;
          else
              outValue =
                      "," + inValue.substring(inValue.length() - 3, inValue.length()) +
                      outValue;
          inValue = inValue.substring(0, inValue.length() - 3);
      }
      
      if (!inValue.equals("")) {
          outValue = inValue + outValue;
      } else {
          if (outValue.length() > 0)
              outValue = inValue + outValue.substring(1);
          else
              outValue = inValue;
      }
      return outValue;
  }

    // Added by developers
    // HanPD

    /**
     * Chuyen mot arraylist thanh xau chua cac
     * thanh phan duoc phan cach boi dau "'"
     *
     * @author    Phung Duy Han
     * @param     coll - Danh sach cac id cua cac header duoc chon
     * @return        Collection - Danh sach cac xau thanh phan
     * @since     04/01/2003
     */
    public static String chuyenThanhXau(ArrayList coll) {
        StringBuffer returnBuffer = new StringBuffer("");
        Iterator iter = coll.iterator();
        while (iter.hasNext()) {
            returnBuffer.append((String)iter.next() + ",");
        }
        // Chuyen sang xau
        String returnString = returnBuffer.toString();
        // Bo dau "," o cuoi
        if (returnString.length() > 0) {
            returnString =
                    returnString.substring(0, returnString.length() - 1);
        }
        return (returnString.toString());
    }


    public static String ConvertSize(String strLength) {
        float intLength = StrToInt(strLength);
        String strSize = "";
        if (intLength <= 500F) {
            strSize = intLength + "&nbsp;Kb";
        } else {
            strSize = "&nbsp;" + intLength / 1024F;
            if (intLength % 1024F == 0.0F)
                strSize =
                        strSize.substring(0, strSize.indexOf(".")) + "&nbsp;Mb";
            else
                strSize =
                        strSize.substring(0, strSize.indexOf(".") + 2) + "&nbsp;Mb";
        }
        return strSize;
    }

    /**
     * Chuyen so double bieu dien so tien sang dang xau
     *
     * @author    Phung Duy Han
     * @param     dSo_tien - So tien dang so
     * @return        String - So tien dang xau
     * @since     04/01/2003
     */
    public static String doubleToString(double dSo_tien) {
        String sSo_tien = String.valueOf(dSo_tien);
        String sGia_tri_tra_ve = "";
        int iEPos = sSo_tien.indexOf("E");
        int iCommaPos = sSo_tien.indexOf(".");
        int iSo_mu = 0;
        if (dSo_tien == 0.0D)
            return "0";
        if (sSo_tien == null)
            return "";
        if (iEPos <= 0) {
            if (sSo_tien.indexOf(".") > 0)
                sGia_tri_tra_ve = sSo_tien.substring(0, sSo_tien.indexOf("."));
            else
                sGia_tri_tra_ve = sSo_tien;
            sGia_tri_tra_ve = formatCurrency(sGia_tri_tra_ve);
            return sGia_tri_tra_ve;
        }
        iSo_mu =
                Integer.parseInt(sSo_tien.substring(iEPos + 1, sSo_tien.length()));
        if (iCommaPos <= 0) {
            sGia_tri_tra_ve = sSo_tien.substring(0, iEPos);
            for (int i = 0; i < iSo_mu; i++)
                sGia_tri_tra_ve = sGia_tri_tra_ve + "0";

        } else {
            int iSo_sau_cham = iEPos - (iCommaPos + 1);
            int iHieu_so = iSo_mu - iSo_sau_cham;
            if (iHieu_so == 0)
                sGia_tri_tra_ve =
                        sSo_tien.substring(0, iCommaPos) + sSo_tien.substring(iCommaPos +
                                                                              1,
                                                                              iEPos);
            else if (iHieu_so > 0) {
                sGia_tri_tra_ve =
                        sSo_tien.substring(0, iCommaPos) + sSo_tien.substring(iCommaPos +
                                                                              1,
                                                                              iEPos);
                for (int i = 0; i < iHieu_so; i++)
                    sGia_tri_tra_ve = sGia_tri_tra_ve + "0";

            } else {
                sGia_tri_tra_ve =
                        sSo_tien.substring(0, iCommaPos) + sSo_tien.substring(iCommaPos +
                                                                              1,
                                                                              iCommaPos +
                                                                              1 +
                                                                              iSo_mu) +
                        "." +
                        sSo_tien.substring(iCommaPos + iSo_mu + 1, iEPos);
            }
        }
        sGia_tri_tra_ve = formatCurrency(sGia_tri_tra_ve);
        return sGia_tri_tra_ve;
    }

    /**
     * Chuyen mot arraylist thanh xau chua cac
     * thanh phan duoc phan cach boi dau "'"
     *
     * @author    ManhNV
     * @param  coll (Danh sach cac id cua cac header duoc chon)
     * @return        Collection - Danh sach cac xau thanh phan
     * @since     07/01/2004
     */
    public static String chuyenThanhChuoiXau(ArrayList coll) {
        StringBuffer returnBuffer = new StringBuffer();
        Iterator iter = coll.iterator();
        while (iter.hasNext()) {
            returnBuffer.append("'" + (String)iter.next() + "'" + ",");
        }
        // Chuyen sang xau
        String returnString = returnBuffer.toString();
        if (returnString.length() > 0)
            returnString =
                    returnString.substring(0, returnString.length() - 1);
        // Bo dau "," o cuoi
        return (returnString);
    }

    public static String pronounceVietnameseNumber(long lNumber) {
        String strUnit[] =
        { "", "ngh\354n", "tri\u1EC7u", "t\u1EF7", "ngh\354n t\u1EF7",
          "tri\u1EC7u t\u1EF7", "ngh\354n tri\u1EC7u t\u1EF7",
          "t\u1EF7 t\u1EF7" };
        byte btDecimalNumber[] = new byte[30];
        byte btDecimalCount = 0;
        boolean bNegative = lNumber < 0L;
        if (bNegative)
            lNumber = -lNumber;
        while (lNumber > 0L) {
            byte btValue = (byte)(int)(lNumber - 10L * (lNumber / 10L));
            lNumber /= 10L;
            btDecimalNumber[btDecimalCount++] = btValue;
        }
        String strReturn = "";
        for (int iUnitIndex = 0;
             iUnitIndex < strUnit.length && iUnitIndex * 3 < btDecimalCount;
             iUnitIndex++) {
            String str =
                pronounceVietnameseNumber(btDecimalNumber[iUnitIndex * 3],
                                          btDecimalNumber[iUnitIndex * 3 + 1],
                                          btDecimalNumber[iUnitIndex * 3 + 2],
                                          iUnitIndex * 3 + 2 < btDecimalCount);
            if (str.length() <= 0)
                continue;
            if (strReturn.length() > 0)
                strReturn = str + " " + strUnit[iUnitIndex] + " " + strReturn;
            else
                strReturn = str + " " + strUnit[iUnitIndex];
        }

        if (bNegative)
            strReturn = "\342m " + strReturn;
        return strReturn;
    }

    private static String pronounceVietnameseNumber(byte bUnit, byte bTen,
                                                    byte bHundred,
                                                    boolean bMax) {
        if (bUnit == 0 && bTen == 0 && bHundred == 0)
            return "";
        String strUnitSuffix[] =
        { "", "m\u1ED9t", "hai", "ba", "t\u01B0", "l\u0103m", "s\341u",
          "b\u1EA3y", "t\341m", "ch\355n" };
        String strUnitTen[] =
        { "", "m\u01B0\u1EDDi m\u1ED9t", "m\u01B0\u1EDDi hai",
          "m\u01B0\u1EDDi ba", "m\u01B0\u1EDDi b\u1ED1n",
          "m\u01B0\u1EDDi l\u0103m", "m\u01B0\u1EDDi s\341u",
          "m\u01B0\u1EDDi b\u1EA3y", "m\u01B0\u1EDDi t\341m",
          "m\u01B0\u1EDDi ch\355n" };
        String strUnit[] =
        { "", "m\u1ED9t", "hai", "ba", "b\u1ED1n", "n\u0103m", "s\341u",
          "b\u1EA3y", "t\341m", "ch\355n" };
        String strTenFirst[] =
        { "", "m\u01B0\u1EDDi m\u1ED9t", "hai m\u01B0\u01A1i m\u1ED1t",
          "ba m\u01B0\u01A1i m\u1ED1t", "b\u1ED1n m\u01B0\u01A1i m\u1ED1t",
          "n\u0103m m\u01B0\u01A1i m\u1ED1t", "s\341u m\u01B0\u01A1i m\u1ED1t",
          "b\u1EA3y m\u01B0\u01A1i m\u1ED1t", "t\341m m\u01B0\u01A1i m\u1ED1t",
          "ch\355n m\u01B0\u01A1i m\u1ED1t" };
        String strTen[] =
        { "", "m\u01B0\u1EDDi", "hai m\u01B0\u01A1i", "ba m\u01B0\u01A1i",
          "b\u1ED1n m\u01B0\u01A1i", "n\u0103m m\u01B0\u01A1i",
          "s\341u m\u01B0\u01A1i", "b\u1EA3y m\u01B0\u01A1i",
          "t\341m m\u01B0\u01A1i", "ch\355n m\u01B0\u01A1i" };
        String strHundred[] =
        { "kh\364ng tr\u0103m", "m\u1ED9t tr\u0103m", "hai tr\u0103m",
          "ba tr\u0103m", "b\u1ED1n tr\u0103m", "n\u0103m tr\u0103m",
          "s\341u tr\u0103m", "b\u1EA3y tr\u0103m", "t\341m tr\u0103m",
          "ch\355n tr\u0103m" };
        String strReturn = "";
        if (bMax || bHundred > 0)
            strReturn = strHundred[bHundred];
        if (bTen > 0) {
            if (strReturn.length() > 0)
                strReturn = strReturn + " ";
            if (bUnit > 0) {
                if (bTen == 1)
                    strReturn = strReturn + strUnitTen[bUnit];
                else if (bUnit == 1)
                    strReturn = strReturn + strTenFirst[bTen];
                else
                    strReturn =
                            strReturn + strTen[bTen] + " " + strUnitSuffix[bUnit];
            } else {
                strReturn = strReturn + strTen[bTen];
            }
        } else if (bUnit > 0)
            if (strReturn.length() > 0)
                strReturn = strReturn + " linh " + strUnit[bUnit];
            else
                strReturn = strUnit[bUnit];
        return strReturn;
    }

    public static String align(String str, int iAlignment, int iLength,
                               char c) {
        if (str == null)
            return null;
        if (str.length() > iLength)
            return str.substring(0, iLength);
        StringBuffer buf = new StringBuffer();
        if (iAlignment == 0) {
            str = lpad(str, str.length() + (iLength - str.length()) / 2, c);
            return rpad(str, iLength, c);
        }
        if (iAlignment == 2)
            return lpad(str, iLength, c);
        if (iAlignment == 1)
            return rpad(str, iLength, c);
        else
            return buf.toString();
    }

    public static String align(String str, int iAlignment, int iLength) {
        return align(str, iAlignment, iLength, ' ');
    }

    public static String lpad(String str, int iLength) {
        return lpad(str, iLength, ' ');
    }

    public static String rpad(String str, int iLength) {
        return rpad(str, iLength, ' ');
    }

    public static String lpad(String str, int iLength, char c) {
        if (str == null)
            return null;
        int iCount = iLength - str.length();
        if (iCount > 0)
            return createMonoString(c, iCount) + str;
        else
            return str;
    }

    public static String rpad(String str, int iLength, char c) {
        if (str == null)
            return null;
        int iCount = iLength - str.length();
        if (iCount > 0)
            return str + createMonoString(c, iCount);
        else
            return str;
    }

    public static String createMonoString(char c, int iLength) {
        StringBuffer buf = new StringBuffer();
        for (int iIndex = 0; iIndex < iLength; iIndex++)
            buf.append(c);

        return buf.toString();
    }

    public static String join(String[] items, String delim) {
        if (items == null || items.length == 0)
            return "";
        StringBuffer sbuf = new StringBuffer();
        for (int i = 0; i < items.length; i++) {
            sbuf.append(items[i]);
            if (i < items.length - 1)
                sbuf.append(delim);
        }

        return sbuf.toString();
    }

    public static void main(String[] args) {
        try {
            String date = "BV Da khoa huyen Cho Moi chuyen \\PC phau thuat va truc CMYT qua the ATM";
            System.out.println(xmlSpeReplace(date));
//            Date d = new Date();
//            String dateToString = DateToString(d, "dd/MM/yyyy HH:mm:ss");
//            String dateToString2 = DateToString(d, "dd/MM/yyyy hh:mm:ss");
//            System.out.println(dateToString);
//            System.out.println(dateToString2);
//            
//            BigDecimal b1 = new BigDecimal("123456.01");
//            StringUtil.formatCurrencyByCode("1234562", "USD");

            //          Date d2 = StringToDate("12/06/2012 23:10:10", "dd/MM/yyyy HH:mm:ss");
            //           System.out.println(d2);
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
    }

    public static String xmlSpeReplace(String strParam) {
        if (strParam == null) {
            return strParam;
        }
        strParam = strParam.replace("&", "&amp;");
        strParam = strParam.replace("<", "&lt;");
        strParam = strParam.replace(">", "&gt;");
        //Begin 20150821
//      strParam = strParam.replace("\"", "&quot;");
        strParam = strParam.replace("\\", "&quot;");
        //End 20150821
        strParam = strParam.replace("'", "&apos;");
        return strParam;
    }

    public static BigDecimal convertCurrencyToNumber(String strTien,
                                                     String strLoaiTien) {
        strTien = strTien.replace(" ", "");
        if ("VND".equalsIgnoreCase(strLoaiTien) || "".equals(strLoaiTien) ||
            strLoaiTien == null || "177".equals(strLoaiTien)) {
            strTien = strTien.replace(".", "");
        } else {
            strTien = strTien.replace(",", "");
        }
        BigDecimal rs = new BigDecimal(strTien);
        return rs;
    }
    public static String previousDateString(String dateString, String formatInput, String formatOutput) 
              throws Exception {
          // Create a date formatter using your format string
          DateFormat dateFormatInput = new SimpleDateFormat(formatInput);
          DateFormat dateFormatOutput = new SimpleDateFormat(formatOutput);

          // Parse the given date string into a Date object.
          // Note: This can throw a ParseException.
          Date myDate = dateFormatInput.parse(dateString);

          // Use the Calendar class to subtract one day
          Calendar calendar = Calendar.getInstance();
          calendar.setTime(myDate);
          calendar.add(Calendar.DAY_OF_YEAR, -1);

          // Use the date formatter to produce a formatted date string
          Date previousDate = calendar.getTime();
          String result = dateFormatOutput.format(previousDate);

          return result;
      }

}
