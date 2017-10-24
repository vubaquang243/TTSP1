package com.seatech.framework.datamanager;

import com.seatech.framework.exception.ResultSetCallbackException;

import java.sql.ResultSet;

import java.util.Collection;
import java.util.Vector;


/**
 * @author cuongnd
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class GetSeqRSCallback implements IResultSetCallback {

	/**
	 * @see IResultSetCallback#notifyResultSet(ResultSet, String)
	 */
	public Collection notifyResultSet(ResultSet rs, String s)
		throws ResultSetCallbackException 
	{
        Collection returnCollection = new Vector();
        String seqReturn = "0";
        if(rs == null)
            throw new ResultSetCallbackException("GetSeqRSCallback:notifyResultSet - null ResultSet parameter.");
        try
        {
            while (rs.next())
            {
            	seqReturn = rs.getString(1);
            	returnCollection.add(seqReturn);
            }
        }
        catch(Exception e)
        {
            throw new ResultSetCallbackException("GetSeqRSCallback:notifyResultSet - not detected." + e);
        }
        return returnCollection;
	}

}
