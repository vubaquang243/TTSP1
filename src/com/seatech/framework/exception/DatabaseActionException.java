package com.seatech.framework.exception;

import java.sql.SQLException;

// Referenced classes of package com.framework.exception:
//            BaseException

public class DatabaseActionException extends BaseException
{

    public DatabaseActionException()
    {
        m_SQLException = null;
    }

    public DatabaseActionException(String strMessageIn, SQLException sqlExc)
    {
        super(strMessageIn, sqlExc);
        m_SQLException = null;
        m_SQLException = sqlExc;
    }

    public final SQLException getSQLException()
    {
        return m_SQLException;
    }

    private SQLException m_SQLException;
}
