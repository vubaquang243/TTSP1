package com.seatech.framework.exception;

import java.util.Collection;

public class BusinessException extends BaseException 
{
  public BusinessException()
  {
  }
  public  BusinessException(String strMessageIn)
    {
        super(strMessageIn);
    }

    public  BusinessException(String strMessageIn, String strParam1)
    {
        super(strMessageIn);
        m_Param1 = strParam1; 
    }

    public  BusinessException(String strMessageIn, String strParam1, String strParam2)
    {
        super(strMessageIn);
        m_Param1 = strParam1; 
        m_Param2 = strParam2;        
    }
    
    public  BusinessException(String strMessageIn, String strParam1, String strParam2, Collection colParam3)
    {
        super(strMessageIn);
        m_Param1 = strParam1; 
        m_Param2 = strParam2;        
        m_Param3 = colParam3;        
    }    
    
    public  BusinessException(String strMessageIn, Throwable exceptionIn)
    {
        super(strMessageIn, exceptionIn);
    }
    
    public String m_Param1 = null;
    public String m_Param2 = null;    
    public Collection m_Param3 = null;
  
}