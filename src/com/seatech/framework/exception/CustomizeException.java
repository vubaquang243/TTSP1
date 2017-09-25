package com.seatech.framework.exception;


import java.sql.SQLException;


/**
 * The class <code>CustomizeException</code> signals that an error related to
 * OrientSoft has occured. All Exceptions which may oocur during the excution
 * of OrientSoft are transformed into CustomizeExceptions. All exception are
 * clasified into categories indentified by their kind.
 *
 * @version 	1.1, 11/09/2002
 * <br>
 * *****************************************************************************************
 * @author Created by <b>Le Tran Thanh</b>. Email:ltthanh@vasc.com.vn <br>
 * If you use it, keep this notice intact to make friends with me
 * I will also appreciate any links you could give me. Thanks.
 * *****************************************************************************************
 */
public class CustomizeException extends BaseException  {

  public static final int UNKNOWN = 0;
  public static final int WRITEPROTECTED = 1;
  public static final int OTHERFILE = 2;
  public static final int NOOUTPUTFILE = 3;
  public static final int CLASSNOTFOUND = 4;
  public static final int SQLEXCEPTION = 5;
  public static final int DBNOTFOUND = 6;
  public static final int ILLEGALCOMMENT = 7;
  public static final int ILLEGALCHAR = 8;
  public static final int UNSUPPORTEDENCODING = 9;
  public static final int CONSTRAINTVIOLATION = 10;
  public static final int SYNTAXERROR = 11;
  public static final int PROPERTYFILENOTFOUND = 12;
  public static final int PROPERTYFILEREADERROR = 13;
  public static final int UNSUPPORTEDFEATURE = 14;
  public static final int IOEXCEPTION = 15;
  public static final int ILLEGALFORMAT = 16;
  public static final int UNKNOWNDBTYPE = 17;
  public static final int NOTIMPLEMENTED = 18;
  public static final int LAST = 18;

  /**
   * Array of default messages.
   **/
  public static final String mess[] = {
    "Unknown Exception",
    "File is write protected! Please specify other output file or select standard out!",
    "Please specify other output file or select standard out!",
    "No output file name specified! Please specify output file or select standard out!",
    "Class not found! Please specify other class class or check CLASSPATH!",
    "SQL exception",
    "Database not found",
    "Comment contains illegal string: --",
    "Illegal charater in string",
    "Unsupported encoding",
    "Vioalation of constraint",
    "Syntax error in query field (Missing character ])",
    "Property file not found",
    "Could not read property file",
    "Driver does not support feature",
    "Error during IO",
    "Illegal time or date format",
    "Unsupported DB type found: ",
    "Method not implemented"
  };

  private int kind;
  private Exception originalException;

  /**
   * Constructs a CustomizeException of a given kind with a given message.
   *
   * @param s    the message string
   * @param type the kind of the exception
   */
  public CustomizeException(String s, int type) {
    super(s);
    kind = type;
  }

  /**
   * Constructs a CustomizeException of a given kind. The message is the
   * default message for this kind.
   *
   * @param type the kind of the exception
   */
  public CustomizeException(int type) {
    super(mess[(type< 0 || type > LAST) ? UNKNOWN : type]);
    kind = (type< 0 || type > LAST) ? UNKNOWN : type;
  }

  /**
   * Constructs a CustomizeException from a SQLException. The kind is
   * <code>SQLEXCEPTION</code>
   *
   * @param e SQLException
   */
  public CustomizeException(SQLException e) {
    super(e.getMessage());
    kind = SQLEXCEPTION;
    originalException = e;
  }

  /**
   * Access method for the kind of the exception
   *
   * @return  the kind of the exception
   */
  public int getKind() {
    return kind;
  }

  /**
   * Access method for original exception
   *
   * @return  the original exception
   */
  public Exception getOriginalException() {
    return originalException;
  }

  /**
   * Access method for default message
   *
   * @param id the number of the exception
   * @return  the default message
   */
  static public String getDefaultMessage(int id) {
    return mess[id];
  }
}
