package com.seatech.framework.exception;

// Referenced classes of package com.framework.exception:
//            BaseException

public class ProcessingException extends BaseException
{

    public ProcessingException()
    {
    }

    public ProcessingException(String strMessageIn)
    {
        super(strMessageIn);
    }

    public ProcessingException(String strMessageIn, Throwable exceptionIn)
    {
        super(strMessageIn, exceptionIn);
    }
}
