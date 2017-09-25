package com.seatech.ttsp.chucnang;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.Vector;


public class PhanQuyenDAO extends AppDAO {
    Connection conn = null;

    public PhanQuyenDAO(Connection conn) {
        this.conn = conn;
    }

    public int themQuyen(Long nNhomID, Long nCNangID) throws Exception {
        try {
            Long nID = getSeqNextValue("sp_cnang_nhom_seq", conn);
            String strSQL =
                "INSERT INTO  sp_cnang_nhom(id, cnang_id, nhom_id) " +
                "VALUES(?,?,?) ";
            Vector vParam = new Vector();
            vParam.add(new Parameter(nID, true));
            vParam.add(new Parameter(nCNangID, true));
            vParam.add(new Parameter(nNhomID, true));

            return executeStatement(strSQL, vParam, conn);
        } catch (Exception e) {
            throw new DAOException("PhanQuyenDAO.themQuyen(" + nNhomID + "," +
                                   nCNangID + "): " + e.getMessage());


        }
    }

    public int xoaQuyen(Long nNhomID, Long nCNangID) throws Exception {
        try {
            String strSQL =
                "DELETE sp_cnang_nhom where cnang_id = ? and nhom_id = ? ";

            Vector vParam = new Vector();
            vParam.add(new Parameter(nCNangID, true));
            vParam.add(new Parameter(nNhomID, true));

            return executeStatement(strSQL, vParam, conn);
        } catch (Exception e) {
            throw new DAOException("PhanQuyenDAO.xoaQuyen(" + nNhomID + "," +
                                   nCNangID + "): " + e.getMessage());


        }

    }

    public int checkExist(Long nNhomID, Long nCNangID) throws Exception {
        int counter = 0;
        try {
            String strSQL =
                "select count(*) counter from sp_cnang_nhom where cnang_id = ? and nhom_id = ? ";

            Vector vParam = new Vector();
            vParam.add(new Parameter(nCNangID, true));
            vParam.add(new Parameter(nNhomID, true));


            ResultSet rs = executeSelectStatement(strSQL, vParam, conn);

            if (rs.next())
                counter = rs.getInt("counter");
        } catch (Exception e) {
            throw new DAOException("PhanQuyenDAO.checkExist(" + nNhomID + "," +
                                   nCNangID + "): " + e.getMessage());


        }
        return counter;

    }

    public int checkExistCNangCon(Long nNhomID,
                                  Long nCNangID) throws Exception {
      int counter = 0;
      try {
          String strSQL =
              "select count(a.id) counter from sp_cnang_nhom a where a.nhom_id = ? and a.cnang_id in "+
              "( select b.id from sp_chucnang b where b.cnang_cha = ? )";

          Vector vParam = new Vector();
          vParam.add(new Parameter(nNhomID, true));
          vParam.add(new Parameter(nCNangID, true));
          
          ResultSet rs = executeSelectStatement(strSQL, vParam, conn);

          if (rs.next())
              counter = rs.getInt("counter");
      } catch (Exception e) {
          throw new DAOException("PhanQuyenDAO.checkExistCNangCon(" + nNhomID + "," +
                                 nCNangID + "): " + e.getMessage());


      }
      return counter;
    }
    
  public int deleteNhom_id(Long nNhomID) throws Exception {
      try {
          String strSQL =
              "DELETE sp_cnang_nhom where nhom_id = ? ";
          Vector vParam = new Vector();
          vParam.add(new Parameter(nNhomID, true));
                  return executeStatement(strSQL, vParam, conn);
      } catch (Exception e) {
          throw new DAOException("PhanQuyenDAO.deleteNhom_id(" + nNhomID + "): " + e.getMessage());


      }

  }
}
