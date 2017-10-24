package com.seatech.framework.utils;
/*
 * $Id:	FontUtil.java
 * Description:
 *
 *
 * Copyright:   Copyright (c) 2001 <p>
 * @author:
 * @version
 * Revisions:
 *
 */

public class FontUtil {

    public static final int UNICODE_TCVN3[][]={
                                        {193, 184 },
                                        {192, 181 },
                                        {7842,182 },
                                        {195, 183 },
                                        {7840,185 },
                                        {194, 169 },
                                        {7844,202 },
                                        {7846,199 },
                                        {7848,200 },
                                        {7850,201 },
                                        {7852,203 },
                                        {258, 168 },
                                        {7854,190 },
                                        {7856,187 },
                                        {7858,188 },
                                        {7860,189 },
                                        {7862,198 },
                                        {201, 208 },
                                        {200, 204 },
                                        {7866,206 },
                                        {7868,207 },
                                        {7864,209 },
                                        {202, 170 },
                                        {7870,213 },
                                        {7872,210 },
                                        {7874,211 },
                                        {7876,212 },
                                        {7878,214 },
                                        {272, 174 },
                                        {205, 221 },
                                        {204, 215 },
                                        {7880,216 },
                                        {296, 220 },
                                        {7882,222 },
                                        {211, 227 },
                                        {210, 223 },
                                        {7886,225 },
                                        {213, 226 },
                                        {7884,228 },
                                        {212, 171 },
                                        {7888,232 },
                                        {7890,229 },
                                        {7892,230 },
                                        {7894,231 },
                                        {7896,233 },
                                        {416, 172 },
                                        {7898,237 },
                                        {7900,234 },
                                        {7902,235 },
                                        {7904,236 },
                                        {7906,238 },
                                        {218 ,243 },
                                        {217, 239 },
                                        {7910,241 },
                                        {360, 242 },
                                        {7908,244 },
                                        {431, 173 },
                                        {7912,248 },
                                        {7914,245 },
                                        {7916,246 },
                                        {7918,247 },
                                        {7920,249 },
                                        {221, 253 },
                                        {7922,250 },
                                        {7926,251 },
                                        {7928,252 },
                                        {7924,254 }
                                      };
    static final int NUM_OF_CHARS = 67;
    public static String convertToTCVN3(String p_Str)
    {
      if (p_Str == null){
        return "";
      }
      int i,j,code,len,capcode;
      String oStr="";
      len = p_Str.length();
      for (i = 0; i< len; i++)
      {
        code = (int) p_Str.charAt(i);
        if (code>=128)
        {
          if (code==272)
            oStr = oStr.concat(String.valueOf((char) 167));
          else if (code==194)
            oStr = oStr.concat(String.valueOf((char) 162));
          else if (code==202)
            oStr = oStr.concat(String.valueOf((char) 167));
          else if (code==212)
            oStr = oStr.concat(String.valueOf((char) 164));
          else
          {
            if (code >=256)
              capcode=code-1;
            else
              capcode=code - 32;
            for (j=0; j < NUM_OF_CHARS ;j++)
            {
              if ((UNICODE_TCVN3[j][0]==code)||(UNICODE_TCVN3[j][0]==capcode))
              {
                oStr = oStr.concat(String.valueOf((char) UNICODE_TCVN3[j][1]));
//                System.out.println(code + " Test!!!!!!!!!!! " + capcode);
                break;
              }
            }
            if (j==NUM_OF_CHARS)
              oStr = oStr.concat(String.valueOf((char) code));
          }
        }
        else
          oStr = oStr.concat(String.valueOf((char) code));
      }
      //Convert "'" for SQL Statement: SONHT: 02 Jan 2002
      //oStr = stringConvert(oStr);
      return oStr;

    };
    public static String convertFromTCVN3(String p_Str)
    {
      if (p_Str == null){
        return "";
      }
      int i,j,code,len;
      String  oStr="";
      len=p_Str.length();
      for (i = 0; i<len;i++)
      {
        code = (int) p_Str.charAt(i);
        if (code>=128)
        {
          if (code==167)
            oStr=oStr + String.valueOf( (char) 272);
          else if (code==162)
            oStr=oStr + String.valueOf( (char) 194);
          else if (code==163)
            oStr=oStr + String.valueOf( (char) 202);
          else if (code==164)
            oStr=oStr + String.valueOf( (char) 212);
          else
          {
            for (j=0; j < NUM_OF_CHARS; j++)
            {
              if (UNICODE_TCVN3[j][1]==code)
              {
                oStr=oStr + String.valueOf((char) ((UNICODE_TCVN3[j][0]>=256)?UNICODE_TCVN3[j][0]+1:UNICODE_TCVN3[j][0]+32));
                break;
              }
            }
            if (j==NUM_OF_CHARS)
              //oStr.concat(String.valueOf((char) code));
              oStr=oStr +String.valueOf((char) code);
          }
        }
        else
          //oStr.concat(String.valueOf((char) code));
          oStr=oStr +String.valueOf((char) code);
      }
      return oStr;

    };
    public static String DecodeUnicode(String p_Str)
    {
      if (p_Str == null){
        return "";
      }
      int i,p,code,len;
      String oStr="";
      len=p_Str.length();
      for (i=0; i < len; )
      {
        if ((p_Str.charAt(i)==38) && (i < len-1))
        {
          if (p_Str.charAt(i + 1)==35)
          {
            p=p_Str.indexOf(";", i  + 2);
            if (p!=-1)
            {
              if (p - i <= 7)
              {
                try
                {
                  code = Integer.parseInt(p_Str.substring(i + 2, p ));
                  oStr = oStr.concat(String.valueOf((char) code));
                  i = p + 1;
                  continue;
                }
                catch (NumberFormatException e)
                {
                      e.printStackTrace();
                }
              }
            }
          }
        }
        oStr=oStr.concat(String.valueOf(p_Str.charAt(i)));
        i++;
      }
      return oStr;
    };

    public static String EncodeUnicode(String p_Str)
    {
      if (p_Str == null){
        return "";
      }
      int i,j;
      String oStr="";
      for (i=0; i < p_Str.length(); i++)
      {
        j = (int) p_Str.charAt(i);
        if (j < 32 || j > 127 || j==34 || j==39 )
        {
          oStr=oStr.concat("&#").concat(String.valueOf(j)).concat(";");
        }
        else
        {
          oStr=oStr.concat(String.valueOf(p_Str.charAt(i)));
        }
      }
      return oStr;
    };

    public static String correctErrorChar(String strData,char ch,String strNew)
    {
        if (strData == null)
        {
            return null;
        }
        String strResult = "";
        int iBegin = 0, iEnd;
        while ((iEnd = strData.indexOf(ch,iBegin))>-1)
        {
            strResult += strData.substring(iBegin,iEnd)+strNew;
            iBegin = iEnd+1;
        }
        iEnd = strData.length();
        strResult += strData.substring(iBegin,iEnd);
        return strResult;
    };
    public static String correctHTMLError(String strData)
    {
        char[] specChar = new char[] {'&','"','<','>'};
        String[] strNew = new String[] {"&#38;","&#34;","&#60;","&#62;"};
        for (int i=0;i<4;i++)
        {
            strData = correctErrorChar(strData,specChar[i],strNew[i]);
        }
        return strData;
    };
    public static String correctEnterAndHTMLError(String strData)
    {
        char[] specChar = new char[] {'&','"','<','>','\r','\n'};
        String[] strNew = new String[] {"&#38;","&#34;","&#60;","&#62;","<br>"," "};
        for (int i=0;i<6;i++)
        {
            strData = correctErrorChar(strData,specChar[i],strNew[i]);
        }
        return strData;
    };
    public static String stringConvert(String inString)
    {
      String strNew = "";
      if (inString!=null)
      {
        String strSearch = null;
        String cSearch = null;

        cSearch = "'";
        strNew = "";
        strSearch = inString;
        if (strSearch.indexOf(cSearch)>-1)
        {
          for (int nIndex=0x00; nIndex<strSearch.length(); nIndex++)
          {
            String strFound = strSearch.substring(nIndex, nIndex+1);
            if (strFound.equals(cSearch))
              strNew += strFound + strFound;
            else
              strNew += strFound;
          }
        }
        else
          strNew = strSearch;
      }
      return strNew;
    };
  private static final char[] hexChar =
  { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
    'E', 'F' };

  public static String unicodeEscape(String s) {
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < s.length(); i++) {
          char c = s.charAt(i);
          if ((c >> 7) > 0) {
              sb.append("\\u");
              sb.append(hexChar[(c >> 12) &
                        0xF]); // append the hex character for the left-most 4-bits
              sb.append(hexChar[(c >> 8) &
                        0xF]); // hex for the second group of 4-bits from the left
              sb.append(hexChar[(c >> 4) & 0xF]); // hex for the third group
              sb.append(hexChar[c &
                        0xF]); // hex for the last group, e.g., the right most 4-bits
          } else {
              sb.append(c);
          }
      }
      return sb.toString();
  }
}//class ConstUtil
