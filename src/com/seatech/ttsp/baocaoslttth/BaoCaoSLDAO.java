package com.seatech.ttsp.baocaoslttth;

import com.seatech.framework.datamanager.AppDAO;

import java.sql.Connection;

public class BaoCaoSLDAO extends AppDAO{
    
    Connection con;
    public BaoCaoSLDAO(Connection conn) {
        this.conn = conn;
    }
}
