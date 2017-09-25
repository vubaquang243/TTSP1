package com.seatech.framework.exception;

public class DatabaseConnectionFailureException extends BaseException
{
  public DatabaseConnectionFailureException()
  {
  }
   public DatabaseConnectionFailureException(String strMessageIn)
    {
        super(strMessageIn);
    }

    public DatabaseConnectionFailureException(String strMessageIn, Throwable exceptionIn)
    {
        super(strMessageIn, exceptionIn);
    }
}