package com.seatech.framework.exception;

import java.sql.SQLException;

// Referenced classes of package com.framework.exception:
//            DatabaseActionException

public class ExecuteStatementException extends DatabaseActionException
{

    public ExecuteStatementException()
    {
    }

    public ExecuteStatementException(String strMessageIn, SQLException exc)
    {
        super(strMessageIn, exc);
    }
}
