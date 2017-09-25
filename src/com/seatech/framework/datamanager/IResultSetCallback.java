package com.seatech.framework.datamanager;


import com.seatech.framework.exception.ResultSetCallbackException;

import java.sql.ResultSet;

import java.util.Collection;

public interface IResultSetCallback
{

    public abstract Collection notifyResultSet(ResultSet resultset, String s)
        throws ResultSetCallbackException;
}
