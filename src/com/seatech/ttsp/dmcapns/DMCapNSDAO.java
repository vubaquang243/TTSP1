package com.seatech.ttsp.dmcapns;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;

import java.util.Vector;


public class DMCapNSDAO extends AppDAO{
    private static String CLASS_NAME_VO = "com.seatech.ttsp.dmcapns.DMCapNSVO";
    private static String CLASS_NAME_DAO = "com.seatech.ttsp.dmcapns.DMCapNSDAO";
    
    public DMCapNSDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * @see: Lay ra thong tin Cap Ngan Sach
     * @param whereClause
     * @param params
     * @return capNSVO
     * @throws Exception
     */
    public DMCapNSVO getDMCapNS(String whereClause, Vector params) throws Exception{
        DMCapNSVO capNSVO = null;
        StringBuffer strSQL = new StringBuffer();
        
        try{
            strSQL.append("SELECT c.id, c.ma, c.ten, c.tinhtrang, c.ghi_chu ");
            strSQL.append(" FROM sp_dm_capns c ");
            if(whereClause != null & !whereClause.isEmpty()){
                strSQL.append(" WHERE "+whereClause);
            }
            capNSVO = (DMCapNSVO) findByPK(strSQL.toString(), params, CLASS_NAME_VO, conn);
            
        }catch(Exception ex){
            DAOException daoEx = new DAOException(CLASS_NAME_DAO + ".getTKKT(strCode): "+ex.getMessage());
//            daoEx.printStackTrace();
            throw daoEx;
        }
        
        return capNSVO;
    }
}
