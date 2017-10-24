package com.seatech.ttsp.dmtkkt;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;

import java.util.Vector;


public class DMTKKTDAO extends AppDAO{
    private static String CLASS_NAME_VO = "com.seatech.ttsp.dmtkkt.DMTKKTVO";
    private static String CLASS_NAME_DAO = "com.seatech.ttsp.dmtkkt.DMTKKTDAO";
    
    public DMTKKTDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * @see: Lay ra thong tin Tai khoan ke toan(Tai khoan tu nhien)
     * @param whereClause
     * @param params
     * @return
     * @throws Exception
     */
    public DMTKKTVO getDMTKKT(String whereClause, Vector params) throws Exception{
        DMTKKTVO tkktVO = null;
        StringBuffer strSQL = new StringBuffer();
        
        try{
            strSQL.append("SELECT t.ma, t.ten, t.tinhtrang, t.id, t.loai, t.cap, t.id_cha, t.ma_cha, t.tktn_tg, t.ma_loai_dt ");
            strSQL.append(" FROM sp_dm_tkkt t ");
            if(whereClause != null & !whereClause.isEmpty()){
                strSQL.append(" WHERE "+whereClause);
            }
            tkktVO = (DMTKKTVO) findByPK(strSQL.toString(), params, CLASS_NAME_VO, conn);
            
        }catch(Exception ex){
            DAOException daoEx = new DAOException(CLASS_NAME_DAO + ".getTKKT(strCode): "+ex.getMessage());
//            daoEx.printStackTrace();
            throw daoEx;
        }
        
        return tkktVO;
    }
}
