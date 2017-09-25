package com.seatech.framework.datamanager;

import java.math.BigDecimal;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import java.util.Calendar;
import java.util.Collection;

public class Parameter extends BaseObject
{
    public Parameter(Object o, boolean bInParameter)
    {
        m_bInParameter = true;
        m_Parameter = null;
        m_ParameterType = 1111;
        m_Parameter = o;
        m_bInParameter = bInParameter;
        m_ParameterType = getSQLType(o);
    }

    public Parameter(Object o, int type, boolean bInParameter)
    {
        m_bInParameter = true;
        m_Parameter = null;
        m_ParameterType = 1111;
        m_Parameter = o;
        m_bInParameter = bInParameter;
        m_ParameterType = type;
        
        if (o == null)
          m_ParameterType = 0;
    }

    public final Object getParameter()
    {
        return m_Parameter;
    }

    public final int getType()
    {
        return m_ParameterType;
    }

    protected final int getSQLType(Object o)
    {
        if(o instanceof String)
            return -1;
        if(o instanceof Long)
            return 4;
        if(o instanceof BigDecimal)
            return 2;
        if(o instanceof Boolean)
            return -7;
        if(o instanceof Integer)
            return 4;
        if(o instanceof Float)
            return 7;
        if(o instanceof Double)
            return 8;
        if(o instanceof byte[])
            return -4;
        if(o instanceof Date)
            return 91;
        if(o instanceof Time)
            return 92;
        if(o instanceof Timestamp)
            return 93;
        if(o instanceof java.util.Date)
            return 91;
        if(o instanceof Character)
            return 1;
        if(o instanceof Collection)
            return 12;
        if(o == null)
            return 0;
        return !(o instanceof Calendar) ? 1111 : 91;
    }

    public final boolean isInParameter()
    {
        return m_bInParameter;
    }

    public final boolean isOutParameter()
    {
        return !isInParameter();
    }

    protected void setParameter(Object object)
    {
        m_Parameter = object;
    }

    public String toString()
    {
        String sReturnString = null;
        sReturnString = "In Parameter: " + String.valueOf(m_bInParameter) + ", Parameter: " + m_Parameter + ", java.sql.Type: " + String.valueOf(m_ParameterType);
        return sReturnString;
    }

    protected boolean m_bInParameter;
    protected Object m_Parameter;
    protected int m_ParameterType;
    public static final boolean IN = true;
    public static final boolean OUT = false;

}
