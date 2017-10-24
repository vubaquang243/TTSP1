package com.seatech.ttsp.dvgiaodich;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;
import java.sql.ResultSet;

import java.text.SimpleDateFormat;

import java.util.Collection;
import java.util.Vector;


public class DvGiaoDichDAO extends AppDAO {
    private String CLASS_NAME_VO = "com.seatech.ttsp.dvgiaodich.DvGiaoDichVO";
    private String CLASS_NAME_DAO =
        "com.seatech.ttsp.dvgiaodich.DvGiaoDichDAO";
    //    private static final String STRING_EMPTY = "";
    //    private static String DATE_FORMAT_NGAY_GIO = "dd-MM-yyyy HH:mm:ss";
    private transient Connection conn = null;

    public DvGiaoDichDAO(Connection conn) {
        this.conn = conn;
    }

    private void setConn(Connection conn) {
        this.conn = conn;
    }


    /**
     * @see:
     * @param: id
     * @return:
     * */
    public DvGiaoDichVO getDVGD(Long lKB) throws Exception {
        //        DvGiaoDichVO vo = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            Vector params = null;
            strSQL.append(" SELECT a.id, a.kb_id, a.lido_ngat ");
            strSQL.append(" from sp_kb_ngat_knoi a inner join sp_kb_ngat_knoi_lsu b ");
            strSQL.append("on a.kb_id = b.kb_id where a.kb_id = ");
            if (lKB != null && !lKB.equals("")) {
                strSQL.append(lKB);
            }
            return (DvGiaoDichVO)findByPK(strSQL.toString(), params,
                                          CLASS_NAME_VO, conn);

        } catch (Exception ex) {
//            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO + ".getDTS(): " +
                                   ex.getMessage(), ex);
        }

    }


    /**
     * @see: Get list KTVTabmis ko phan trang
     * */
    public Collection getListNgatDvGD(String strWhere,
                                      Vector v_param) throws Exception {

        try {
            StringBuffer strSQL = new StringBuffer();
            strSQL.append("SELECT  a.id ,a.kb_id,c.ma, a.tu_ngay, a.den_ngay, a.nguoi_ngat, a.lido_ngat,");
            strSQL.append("a.nguoi_noi, a.lido_noi ");
            strSQL.append("FROM  sp_dm_htkb c,sp_kb_ngat_knoi_lsu a ");
            strSQL.append("INNER JOIN sp_kb_ngat_knoi b ON a.kb_id = b.kb_id and a.nguoi_noi is null ");
            strSQL.append("where c.id = a.kb_id ");
            if (strWhere != null && !strWhere.equals("")) {
                strSQL.append(strWhere);
            }
            return executeSelectStatement(strSQL.toString(), v_param,
                                          CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getListDvGD(strWhere, v_param): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }

    }

    /**
     * @see:
     * @param: id
     * @return:
     * */
    public Long getIdKnLSu(Long lKB) throws Exception {
        //        Long lID = null;

        try {
            Vector params = null;
            StringBuffer strSQL = new StringBuffer();
            strSQL.append(" SELECT max(a.id) as test");
            strSQL.append("  from sp_kb_ngat_knoi_lsu a inner join  sp_kb_ngat_knoi c    ");
            strSQL.append("on a.kb_id = c.kb_id ");
            if (lKB != null) {
                strSQL.append("and c.kb_id= ? ");
                params = new Vector();
                params.add(new Parameter(lKB, true));
            }
            ResultSet rs =
                executeSelectStatement(strSQL.toString(), params, conn);
            if (rs.next()) {
                return rs.getLong(1);
            }
            return null;

        } catch (Exception ex) {
//            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO + ".getIdKnLSu(): " +
                                   ex.getMessage(), ex);
        }

    }

    /**
     * @param: Menh de where va vector param
     * @return:
     * @see:
     * */
    public Collection getListDvGD(String whereClause, Vector vParam,
                                  Integer page, Integer count,
                                  Integer[] totalCount) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT  a.id, a.kb_id, a.tu_ngay, a.den_ngay, a.nguoi_ngat, a.lido_ngat,");
            strSQL.append("a.nguoi_noi, a.lido_noi");
            strSQL.append("FROM sp_kb_ngat_knoi_lsu a");
            strSQL.append("INNER JOIN sp_kb_ngat_knoi b ON a.kb_id = b.kb_id and a.nguoi_noi is null ");
            if (whereClause != null && !whereClause.equalsIgnoreCase("")) {
                strSQL.append(" WHERE 1=1 " + whereClause);
            }
            strSQL.append(" ORDER BY a.id DESC ");
            reval =
                    executeSelectWithPaging(conn, strSQL.toString(), vParam, CLASS_NAME_VO,
                                            page, count, totalCount);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getListDvGD(): " +
                                   ex.getMessage(), ex);
        }

        return reval;
    }


    /**
     * @param: DvGiaoDichVO
     * @return: 1: them moi thanh cong; 0: them moi khong thanh cong
     * @see: Them moi Kb Ngat ket noi
     * * */
    public int insert(DvGiaoDichVO vo) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        //Lay id tu seq
        Long id = getSeqNextValue("sp_kb_ngat_knoi_seq", conn);
        strSQL.append("insert into sp_kb_ngat_knoi (id ");
        strSQL2.append(") values (? ");
        v_param.add(new Parameter(id, true));
        if (vo.getKb_id() != null) {
            strSQL.append(", kb_id");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getKb_id(), true));
        }
        if (vo.getLido_ngat() != null) {
            strSQL.append(", lido_ngat");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getLido_ngat(), true));
        }
        strSQL.append(strSQL2);
        strSQL.append(")");
        return executeStatement(strSQL.toString(), v_param, conn);
    }


    /**
     * @param: Long
     * @return: 1: xoa thanh cong; 0: Xoa khong thanh cong
     * @see: Xoa Kb Ngat ket noi
     * */
    public int delete(Long id) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append(" delete sp_kb_ngat_knoi where kb_id = ?");
        int nExc = 0;
        if (id != null) {
            v_param.add(new Parameter(id, true));
            nExc = executeStatement(strSQL.toString(), v_param, conn);
        }
        return nExc;
    }

    /**
     * @param:
     * @return: 1: update thanh cong; 0: Update khong thanh cong
     * @see: Sua ten
     * */
    public int update(DvGiaoDichVO vo) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append("update sp_kb_ngat_knoi_lsu set ");
        int nExc = 0;
        strSQL.append(" den_ngay = sysdate ");
        if (vo.getLido_noi() != null) {
            strSQL.append(" ,lido_noi = ?  ");
            v_param.add(new Parameter(vo.getLido_noi(), true));
        }
        if (vo.getNguoi_noi() != null) {
            strSQL.append(" ,nguoi_noi = ? ");
            v_param.add(new Parameter(vo.getNguoi_noi(), true));
        }
        strSQL.append(" where id = ? ");
        v_param.add(new Parameter(vo.getId(), true));
        nExc = executeStatement(strSQL.toString(), v_param, conn);

        return nExc;
    }

    /**
     * @param: DvGiaoDichVO
     * @return: 1: them moi thanh cong; 0: them moi khong thanh cong
     * @see: Them moi Kb Ngat ket noi
     * * */
    public int insertLsu(DvGiaoDichVO vo) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        //Lay id tu seq
        Long id = getSeqNextValue("sp_kb_ngat_knoi_lsu_seq", conn);
        strSQL.append("insert into sp_kb_ngat_knoi_lsu (id ");
        strSQL2.append(") values (? ");
        v_param.add(new Parameter(id, true));
        SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyyy");
        if (vo.getKb_id() != null) {
            strSQL.append(", kb_id");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getKb_id(), true));
        }
        strSQL.append(", tu_ngay");
        strSQL2.append(",sysdate ");

        if (!"".equals(vo.getDen_ngay()) && vo.getDen_ngay() != null) {
            strSQL.append(", den_ngay");
            strSQL2.append(",? ");
            v_param.add(new DateParameter(fm.parse(vo.getDen_ngay()), true));
        }
        if (vo.getNguoi_ngat() != null) {
            strSQL.append(", nguoi_ngat");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getNguoi_ngat(), true));
        }
        if (vo.getLido_ngat() != null) {
            strSQL.append(", lido_ngat");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getLido_ngat(), true));
        }
        if (vo.getNguoi_noi() != null) {
            strSQL.append(", nguoi_noi");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getNguoi_noi(), true));
        }
        if (vo.getLido_noi() != null) {
            strSQL.append(", lido_noi");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getLido_noi(), true));
        }

        strSQL.append(strSQL2);
        strSQL.append(")");
        return executeStatement(strSQL.toString(), v_param, conn);
    }

    /**
     * @param: Menh de where va vector param
     * @return:
     * @see:
     * */
    public Collection getListLSuNgat(String whereClause, Vector vParam,
                                     Integer page, Integer count,
                                     Integer[] totalCount) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {

            //strSQL.append("SELECT a.id, a.kb_id, a.tu_ngay, a.den_ngay, a.nguoi_ngat,(b.ten_nsd)ten_ng_ngat, a.lido_ngat, ");
            // strSQL.append(" a.nguoi_noi,(b2.ten_nsd)ten_ng_noi, a.lido_noi ");
            // strSQL.append(" FROM sp_kb_ngat_knoi_lsu a ");
            // strSQL.append(" LEFT JOIN sp_nsd b  ");
            // strSQL.append(" on b.id = a.nguoi_ngat ");
            // strSQL.append(" LEFT JOIN sp_nsd b2  ");
            // strSQL.append(" on b2.id = a.nguoi_noi ");
            // if (whereClause != null && !whereClause.equalsIgnoreCase("")) {
            //     strSQL.append("where " + whereClause);
            // }

            strSQL.append("SELECT b.ma,b.ten, c.ten_nsd, a.id, a.kb_id, a.tu_ngay, a.den_ngay, a.nguoi_ngat, a.lido_ngat,");
            strSQL.append("a.nguoi_noi, a.lido_noi,c.ma_nsd ");
            strSQL.append(" from sp_kb_ngat_knoi_lsu a,  sp_dm_htkb b, sp_nsd c  ");
            strSQL.append(" where a.kb_id = b.id and a.nguoi_ngat =c.id(+) ");
            if (whereClause != null && !whereClause.equalsIgnoreCase("")) {
                strSQL.append(whereClause);
            }
            strSQL.append(" ORDER BY a.id DESC ");
            reval =
                    executeSelectWithPaging(conn, strSQL.toString(), vParam, CLASS_NAME_VO,
                                            page, count, totalCount);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getListLSuNgat(): " +
                                   ex.getMessage(), ex);
        }

        return reval;
    }

    public ResultSet getRSLSuNgat(String whereClause,
                                  Vector vParam) throws Exception {
        ResultSet reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT b.ma,b.ten, a.id, a.kb_id, to_char(a.tu_ngay,'DD/MM/YYYY HH24:MI:SS') tu_ngay, ");
            strSQL.append("to_char(a.den_ngay,'DD/MM/YYYY HH24:MI:SS') den_ngay, a.nguoi_ngat, nvl(a.lido_ngat,' ') lido_ngat, ");
            strSQL.append("a.nguoi_noi, nvl(a.lido_noi,' ') lido_noi, nvl(c.ma_nsd,' ') ma_nguoi_ngat, nvl(c.ten_nsd,' ') ten_nguoi_ngat, ");
            strSQL.append("nvl(c.ma_nsd,' ') ma_nguoi_noi, nvl(c.ten_nsd,' ') ten_nguoi_noi ");
            strSQL.append("from sp_kb_ngat_knoi_lsu a,  sp_dm_htkb b, sp_nsd c, sp_nsd d ");
            strSQL.append("where a.kb_id = b.id and a.nguoi_ngat =c.id(+) and a.nguoi_noi = d.id(+) ");

            if (whereClause != null && !whereClause.equalsIgnoreCase("")) {
                strSQL.append(whereClause);
            }
            strSQL.append(" ORDER BY a.id DESC ");
            reval = executeSelectStatement(strSQL.toString(), vParam, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getRSLSuNgat(): " +
                                   ex.getMessage(), ex);
        }

        return reval;
    }
}
