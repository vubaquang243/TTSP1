package com.seatech.framework.utils;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * The class <code>Mime2Java</code> is a convenience class which
 * handles conversions between Mime charset names and Java encoding names.
 * This class was inspired by a similar class in the IBM xml4j parser.
 * @version 	1.1, 11/09/2002
 * <br>
 * *****************************************************************************************
 * @author Created by cangnd <br>
 * If you use it, keep this notice intact to make friends with me
 * I will also appreciate any links you could give me. Thanks.
 * *****************************************************************************************
 **/

public final class Mime2Java {

  static private Hashtable table;
  /**
   * This static code loads the hashtable.
   */
  static {
    table = new Hashtable();
    //    <preferred Mime name>, <Java encoding name>
    table.put("UTF-8", "UTF8");
    table.put("US-ASCII",        "8859_1");
    table.put("ISO-8859-1",      "8859_1");
    table.put("ISO-8859-2",      "8859_2");
    table.put("ISO-8859-3",      "8859_3");
    table.put("ISO-8859-4",      "8859_4");
    table.put("ISO-8859-5",      "8859_5");
    table.put("ISO-8859-6",      "8859_6");
    table.put("ISO-8859-7",      "8859_7");
    table.put("ISO-8859-8",      "8859_8");
    table.put("ISO-8859-9",      "8859_9");
    table.put("ISO-2022-JP",     "JIS");
    table.put("SHIFT_JIS",       "SJIS");
    table.put("EUC-JP",          "EUCJIS");
    table.put("GB2312",          "GB2312");
    table.put("BIG5",            "Big5");
    table.put("EUC-KR",          "KSC5601");
    table.put("ISO-2022-KR",     "ISO2022KR");
    table.put("KOI8-R",          "KOI8_R");
    table.put("EBCDIC-CP-US",    "CP037");
    table.put("EBCDIC-CP-CA",    "CP037");
    table.put("EBCDIC-CP-NL",    "CP037");
    table.put("EBCDIC-CP-DK",    "CP277");
    table.put("EBCDIC-CP-NO",    "CP277");
    table.put("EBCDIC-CP-FI",    "CP278");
    table.put("EBCDIC-CP-SE",    "CP278");
    table.put("EBCDIC-CP-IT",    "CP280");
    table.put("EBCDIC-CP-ES",    "CP284");
    table.put("EBCDIC-CP-GB",    "CP285");
    table.put("EBCDIC-CP-FR",    "CP297");
    table.put("EBCDIC-CP-AR1",   "CP420");
    table.put("EBCDIC-CP-HE",    "CP424");
    table.put("EBCDIC-CP-CH",    "CP500");
    table.put("EBCDIC-CP-ROECE", "CP870");
    table.put("EBCDIC-CP-YU",    "CP870");
    table.put("EBCDIC-CP-IS",    "CP871");
    table.put("EBCDIC-CP-AR2",   "CP918");
    table.put("CP1252",   "Cp1252");
    table.put("CP850",   "Cp850");
    table.put("CP852",   "Cp852");
    table.put("CP855",   "Cp855");
    table.put("CP1250",   "Cp1250");
  }

  /**
   * Converts a name for a MIME charset name into a Java encoding name
   *
   * @param   mimeCharsetName   the MIME charset name
   * @return  the Java encoding name
   */
  public static String convert(String mimeCharsetName) {
    return (String)table.get(mimeCharsetName.toUpperCase());
  }

  /**
   * Gets all supported MIME charset names
   *
   * @return  an enumeration of Strings of all supported MIME charset names
   */
  public static Enumeration getMimeTypes() {
    return table.keys();
  }
}
