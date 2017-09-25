package com.seatech.framework.exception;

import java.sql.SQLException;


public class SelectStatementException extends BaseException 
{
  public SelectStatementException()
  {
  }
      public SelectStatementException(String strMessageIn, SQLException exc)
    {
        super(strMessageIn, exc);
    }
}