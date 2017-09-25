package com.seatech.framework.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

public class BaseException extends Exception
{

    public BaseException()
    {
        m_PreviousThrowable = null;
    }

    public BaseException(String strMessageIn)
    {
        super(strMessageIn);
        m_PreviousThrowable = null;
    }

    public BaseException(Throwable exceptionIn)
    {
        m_PreviousThrowable = null;
        m_PreviousThrowable = exceptionIn;
    }

    public BaseException(String strMessageIn, Throwable exceptionIn)
    {
        super(strMessageIn);
        m_PreviousThrowable = null;
        m_PreviousThrowable = exceptionIn;
    }

    public void printStackTrace()
    {
        super.printStackTrace();
        if(m_PreviousThrowable != null)
            m_PreviousThrowable.printStackTrace();
    }

    public void printStackTrace(PrintStream printStreamIn)
    {
        super.printStackTrace(printStreamIn);
        if(m_PreviousThrowable != null)
            m_PreviousThrowable.printStackTrace(printStreamIn);
    }

    public void printStackTrace(PrintWriter printWriterIn)
    {
        super.printStackTrace(printWriterIn);
        if(m_PreviousThrowable != null)
            m_PreviousThrowable.printStackTrace(printWriterIn);
    }

    public Throwable getChainedException()
    {
        return m_PreviousThrowable;
    }

    public BaseException containsBaseExceptionTypeInChain(String sClassName)
    {
        BaseException returnBaseException = null;
        BaseException tempBaseException = null;
        if(m_PreviousThrowable != null && (m_PreviousThrowable instanceof BaseException))
        {
            tempBaseException = (BaseException)m_PreviousThrowable;
            if(tempBaseException.getClass().getName().equalsIgnoreCase(sClassName))
                returnBaseException = tempBaseException;
            else
                returnBaseException = tempBaseException.containsBaseExceptionTypeInChain(sClassName);
        }
        return returnBaseException;
    }

    private Throwable m_PreviousThrowable;

}
