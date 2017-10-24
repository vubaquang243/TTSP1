package com.seatech.ttsp.dmmaquy;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;

import java.util.Vector;


public class DMMaQuyDAO extends AppDAO{
    private static String CLASS_NAME_VO = "com.seatech.ttsp.dmmaquy.DMMaQuyVO";
    private static String CLASS_NAME_DAO = "com.seatech.ttsp.dmmaquy.DMMaQuyDAO";
    
    public DMMaQuyDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * @see: Lay ra thong tin Ma Quy
     * @param whereClause
     * @param params
     * @return maQuyVO
     * @throws Exception
     */
    public DMMaQuyVO getDMMaQuy(String whereClause, Vector params) throws Exception{
        DMMaQuyVO maQuyVO = null;
        StringBuffer strSQL = new StringBuffer();
        
        try{
            strSQL.append("SELECT m.ma, m.ten, m.tinhtrang, m.id, m.ghi_chu ");
            strSQL.append(" FROM sp_dm_ma_quy m ");
            if(whereClause != null & !whereClause.isEmpty()){
                strSQL.append(" WHERE "+whereClause);
            }
            maQuyVO = (DMMaQuyVO) findByPK(strSQL.toString(), params, CLASS_NAME_VO, conn);
            
        }catch(Exception ex){
            DAOException daoEx = new DAOException(CLASS_NAME_DAO + ".getTKKT(strCode): "+ex.getMessage());
//            daoEx.printStackTrace();
            throw daoEx;
        }
        
        return maQuyVO;
    }
}
