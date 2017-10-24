package com.seatech.framework.datamanager;

import java.sql.Timestamp;

// Referenced classes of package com.framework.misc:
//            Parameter

public class DateParameter extends Parameter
{
    public DateParameter(Object date, boolean bInParameter)
    {
        super(date, 93, bInParameter);
        m_Parameter = getValue(date);
    }
    private Timestamp getValue(Object date) 
    {
      if(date == null)
        return null;
      if (date instanceof java.util.Date)
        return new Timestamp(((java.util.Date)date).getTime());
      if (date instanceof java.sql.Date)
        return new Timestamp(((java.sql.Date)date).getTime());
      if (date instanceof java.util.Calendar)
        return new Timestamp(((java.util.Calendar)date).getTimeInMillis());
      
      return null;
    }
}