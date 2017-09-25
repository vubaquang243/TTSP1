package com.seatech.framework.exception;

import java.sql.SQLException;

public class StoredProcedureException extends BaseException 
{
  public StoredProcedureException()
  {
  }
  
     public StoredProcedureException(String strMessageIn, SQLException exc)
    {
        super(strMessageIn, exc);
    }
}