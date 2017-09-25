package com.seatech.ttsp.dmchuong;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;

import java.util.Vector;


public class DMChuongDAO extends AppDAO{
    private static String CLASS_NAME_VO = "com.seatech.ttsp.dmchuong.DMChuongVO";
    private static String CLASS_NAME_DAO = "com.seatech.ttsp.dmchuong.DMChuongDAO";
    
    public DMChuongDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * @see: Lay ra thong tin Chuong
     * @param whereClause
     * @param params
     * @return chuongVO
     * @throws Exception
     */
    public DMChuongVO getDMChuong(String whereClause, Vector params) throws Exception{
        DMChuongVO chuongVO = null;
        StringBuffer strSQL = new StringBuffer();
        
        try{
            strSQL.append("SELECT c.id, c.ma, c.ten, c.tinhtrang, c.cap, c.ghi_chu ");
            strSQL.append(" FROM sp_dm_chuong c ");
            if(whereClause != null & !whereClause.isEmpty()){
                strSQL.append(" WHERE "+whereClause);
            }
            chuongVO = (DMChuongVO) findByPK(strSQL.toString(), params, CLASS_NAME_VO, conn);
            
        }catch(Exception ex){
            DAOException daoEx = new DAOException(CLASS_NAME_DAO + ".getTKKT(strCode): "+ex.getMessage());
//            daoEx.printStackTrace();
            throw daoEx;
        }
        
        return chuongVO;
    }
}
