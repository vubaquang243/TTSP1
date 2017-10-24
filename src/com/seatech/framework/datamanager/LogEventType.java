package com.seatech.framework.datamanager;

public class LogEventType extends BaseObject
{

    private LogEventType()
    {
        m_LogEventType = 1;
    }

    protected LogEventType(int eventType)
    {
        m_LogEventType = 1;
        m_LogEventType = eventType;
    }

    public static LogEventType getFrameworkLogEventType(int eventType)
        throws IllegalArgumentException
    {
        LogEventType type = null;
        if(eventType == 1)
            type = INFO_LOG_EVENT_TYPE;
        else
        if(eventType == 2)
            type = WARNING_LOG_EVENT_TYPE;
        else
        if(eventType == 3)
            type = ERROR_LOG_EVENT_TYPE;
        else
        if(eventType == 0)
            type = DEBUG_LOG_EVENT_TYPE;
        else
            throw new IllegalArgumentException("LogEventType:getFrameworkLogEventType() - Invalid argument");
        return type;
    }

    public boolean isInfoLogEventType()
    {
        boolean bIs = false;
        if(m_LogEventType == 1)
            bIs = true;
        return bIs;
    }

    public boolean isWarningLogEventType()
    {
        boolean bIs = false;
        if(m_LogEventType == 2)
            bIs = true;
        return bIs;
    }

    public boolean isErrorLogEventType()
    {
        boolean bIs = false;
        if(m_LogEventType == 3)
            bIs = true;
        return bIs;
    }

    public boolean isDebugLogEventType()
    {
        boolean bIs = false;
        if(m_LogEventType == 0)
            bIs = true;
        return bIs;
    }

    public String toString()
    {
        StringBuffer sBuf = new StringBuffer("");
        if(isInfoLogEventType())
            sBuf.append("INFO");
        else
        if(isWarningLogEventType())
            sBuf.append("WARNING");
        else
        if(isErrorLogEventType())
            sBuf.append("ERROR");
        else
        if(isDebugLogEventType())
            sBuf.append("DEBUG");
        return sBuf.toString();
    }

    protected int m_LogEventType;
    protected static final int DEBUG = 0;
    protected static final int INFO = 1;
    protected static final int WARNING = 2;
    protected static final int ERROR = 3;
    public static final LogEventType DEBUG_LOG_EVENT_TYPE = new LogEventType(0);
    public static final LogEventType INFO_LOG_EVENT_TYPE = new LogEventType(1);
    public static final LogEventType WARNING_LOG_EVENT_TYPE = new LogEventType(2);
    public static final LogEventType ERROR_LOG_EVENT_TYPE = new LogEventType(3);

}