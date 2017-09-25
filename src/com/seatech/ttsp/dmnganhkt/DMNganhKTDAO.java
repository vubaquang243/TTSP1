package com.seatech.ttsp.dmnganhkt;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;

import java.util.Vector;


public class DMNganhKTDAO extends AppDAO{
    private static String CLASS_NAME_VO = "com.seatech.ttsp.dmnganhkt.DMNganhKTVO";
    private static String CLASS_NAME_DAO = "com.seatech.ttsp.dmnganhkt.DMNganhKTDAO";
    
    public DMNganhKTDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * @see: Lay ra thong tin NganhKT
     * @param whereClause
     * @param params
     * @return nganhKTVO
     * @throws Exception
     */
    public DMNganhKTVO getDMNganhKT(String whereClause, Vector params) throws Exception{
        DMNganhKTVO nganhKTVO = null;
        StringBuffer strSQL = new StringBuffer();
        
        try{
            strSQL.append("SELECT n.ma, n.ten, n.tinhtrang, n.id, n.id_cha, n.ma_cha, n.loai ");
            strSQL.append(" FROM sp_dm_nganhkt n ");
            if(whereClause != null & !whereClause.isEmpty()){
                strSQL.append(" WHERE "+whereClause);
            }
            return (DMNganhKTVO)findByPK(strSQL.toString(), params, CLASS_NAME_VO, conn);
            
        }catch(Exception ex){
            DAOException daoEx = new DAOException(CLASS_NAME_DAO + ".getTKKT(strCode): "+ex.getMessage());
//            daoEx.printStackTrace();
            throw daoEx;
        }
    }
}
