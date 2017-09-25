package com.seatech.framework;

import com.seatech.ttsp.error.AppErrorDAO;
import com.seatech.ttsp.error.AppErrorVO;

import java.sql.Connection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;


public class AppError {
    public HashMap getAppErrorList(Connection conn) throws Exception {
        AppErrorDAO appErrorDAO = new AppErrorDAO();
        AppErrorVO appErrorVO;
        Collection loiCol = appErrorDAO.getAppErrorList(conn);
        Iterator ito = loiCol.iterator();
        HashMap errorMap = new HashMap();
        while (ito.hasNext()) {
            appErrorVO = (AppErrorVO)ito.next();
            errorMap.put(appErrorVO.getError_id(), appErrorVO.getError_contents());
        }
        return errorMap;
    }
}
