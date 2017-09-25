package com.seatech.framework.exception;

public class ResultSetCallbackException extends BaseException
{

    public ResultSetCallbackException()
    {
    }

    public ResultSetCallbackException(String strMessageIn)
    {
        super(strMessageIn);
    }

    public ResultSetCallbackException(String strMessageIn, Throwable exceptionIn)
    {
        super(strMessageIn, exceptionIn);
    }
}
