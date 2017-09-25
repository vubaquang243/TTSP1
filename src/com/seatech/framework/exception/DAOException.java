package com.seatech.framework.exception;

public class DAOException extends BaseException
{

    public DAOException()
    {
    }

    public DAOException(String strMessageIn)
    {
        super(strMessageIn);
    }

    public DAOException(String strMessageIn, Throwable exceptionIn)
    {
        super(strMessageIn, exceptionIn);
    }
}
