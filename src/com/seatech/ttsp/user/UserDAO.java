package com.seatech.ttsp.user;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;

import java.io.StringReader;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.Collection;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.InputSource;


public class UserDAO extends AppDAO {
    private String CLASS_NAME_VO = "com.seatech.ttsp.user.UserVO";
    private String CLASS_NAME_DAO = "com.seatech.ttsp.user.UserDAO";
    private Connection conn = null;

    public UserDAO(Connection conn) {
        this.conn = conn;
    }

  

    private void setConn(Connection conn) {
        this.conn = conn;
    }

    /**
     * @param: Menh de where va vector param
     * @return: Danh sach NSD
     * @see:
     * */
    public Collection getUserList(String whereClause,
                                  Vector params) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.id, a.kb_id, a.ten_nsd, a.ma_tabmis, a.chuc_danh, a.ma_nsd, ");
            strSQL.append("a.trang_thai, a.ngay_tao, a.nguoi_tao, a.ip_truycap, a.session_id, a.tgian_truycap, a.domain, a.login_failure, a.mac_address, a.ten_may_truycap, a.user_may_truycap ");
            strSQL.append("FROM sp_nsd a ");

            if (whereClause != null && !whereClause.equalsIgnoreCase("")) {
                strSQL.append(" WHERE" + whereClause);
            }
            strSQL.append(" ORDER BY a.id DESC ");
            reval =
                    executeSelectStatement(strSQL.toString(), params, CLASS_NAME_VO,
                                           conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getUserList(): " +
                                   ex.getMessage(), ex);
        }

        return reval;
    }

    /**
     * @param: Menh de where va vector param
     * @return: Danh sach NSD co phan trang
     * @see:
     * */
    public Collection getUserList_ptrang(String whereClause, Vector vParam,
                                         String nhom_id, Integer page,
                                         Integer count,
                                         Integer[] totalCount) throws Exception {
        Collection reval = null;
      
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.id,a.kb_id ,b.ma,b.ma_cha, a.ten_nsd, a.ma_tabmis, a.chuc_danh, a.ma_nsd, ");
            strSQL.append("(SELECT wm_concat(loai_nhom) FROM sp_nhom_nsd n1, sp_nsd_nhom n2 WHERE n1.id = n2.nhom_id AND n2.nsd_id = a.id) nhom, ");
            strSQL.append(" a.trang_thai, a.ngay_tao, a.nguoi_tao, a.ip_truycap, a.session_id, a.tgian_truycap,a.domain, a.login_failure, a.mac_address, a.ten_may_truycap, a.user_may_truycap ");

            strSQL.append(" FROM sp_nsd a ");
            strSQL.append(" inner JOIN sp_dm_htkb b ");
            strSQL.append(" on a.kb_id = b.id");
            if (whereClause != null && !whereClause.equalsIgnoreCase("")) {
                strSQL.append(" WHERE 1=1 " + whereClause);
            }
            strSQL.append(" ORDER BY b.ma, a.id DESC ");
            reval =
                    executeSelectWithPaging(conn, strSQL.toString(), vParam, CLASS_NAME_VO,
                                            page, count, totalCount);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getUserList(): " +
                                   ex.getMessage(), ex);
        }

        return reval;
    }


    /**
     * @param: Menh de where va vector param
     * @return: Danh sach NSD co phan trang dung voi hien thi nhom nsd
     * @see:
     * */
    public Collection getUserList_ptrang(String whereClause, Vector vParam,
                                         Integer page, Integer count,
                                         Integer[] totalCount) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.id,a.kb_id ,c.ma, a.ten_nsd, a.ma_tabmis, a.chuc_danh, a.ma_nsd, ");
            strSQL.append(" a.trang_thai, a.ngay_tao, a.nguoi_tao, a.ip_truycap, a.session_id, a.tgian_truycap,a.domain, a.login_failure, a.mac_address, a.ten_may_truycap, a.user_may_truycap ");
            strSQL.append(" FROM sp_nsd a , sp_nsd_nhom b , sp_dm_htkb c  ");
            if (whereClause != null && !whereClause.equalsIgnoreCase("")) {
                strSQL.append(" WHERE 1=1 " + whereClause);
            }
            strSQL.append(" ORDER BY a.id DESC ");
            reval =
                    executeSelectWithPaging(conn, strSQL.toString(), vParam, CLASS_NAME_VO,
                                            page, count, totalCount);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getUserList(): " +
                                   ex.getMessage(), ex);
        }

        return reval;
    }

    /**
     * @param: Menh de where va vector param
     * @return: Thong tin NSD
     * @see:
     * */
    public UserVO getUser(String whereClause, Vector params) throws Exception {
        UserVO userVO = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.id, a.kb_id, a.ten_nsd, a.ma_tabmis, a.chuc_danh, a.ma_nsd, ");
            strSQL.append("a.trang_thai, a.ngay_tao, a.nguoi_tao, a.ip_truycap, a.session_id, a.tgian_truycap,a.domain, a.login_failure, a.mac_address, a.ten_may_truycap, a.user_may_truycap ");
            strSQL.append("FROM sp_nsd a  ");

            if (whereClause != null && !whereClause.equalsIgnoreCase("")) {
                strSQL.append(" WHERE " + whereClause);
            }
            userVO =
                    (UserVO)findByPK(strSQL.toString(), params, CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getUser(): " +
                                   ex.getMessage(), ex);
        }
        return userVO;
    }

    /**
     * @param: UserVO
     * @return: 0: ko thanh cong; tra ve ID cua NSD vua dc tao neu thanh cong
     * @see:
     * */
    public Long insert(UserVO vo) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        //Lấy ID từ seq
        Long lID = getSeqNextValue("SP_NSD_SEQ", conn);

        strSQL.append("insert into SP_NSD (id ");
        strSQL2.append(") values (? ");
        v_param.add(new Parameter(lID, true));

        if (vo.getMa_nsd() != null) {
            strSQL.append(", ma_nsd");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getMa_nsd(), true));
        }
        if (vo.getTen_nsd() != null) {
            strSQL.append(", ten_nsd");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getTen_nsd(), true));
        }
        if (vo.getKb_id() != null) {
            strSQL.append(", kb_id");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getKb_id(), true));
        }
        if (vo.getMa_tabmis() != null) {
            strSQL.append(", ma_tabmis");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getMa_tabmis(), true));
        }
        if (vo.getDomain() != null) {
            strSQL.append(", domain");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getDomain(), true));
        }
        if (vo.getTrang_thai() != null) {
            strSQL.append(", trang_thai");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getTrang_thai(), true));
        }
        strSQL.append(", ngay_tao");
        strSQL2.append(", sysdate");
        if (vo.getNguoi_tao() != null) {
            strSQL.append(", nguoi_tao");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getNguoi_tao(), true));
        }
        if (vo.getChuc_danh() != null) {
            strSQL.append(", chuc_danh");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getChuc_danh(), true));
        }
        if (vo.getMac_address() != null) {
            strSQL.append(", mac_address");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getMac_address(), true));
        }
        if (vo.getUser_may_truycap() != null) {
            strSQL.append(", user_may_truycap");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getUser_may_truycap(), true));
        }
        if (vo.getTen_may_truycap() != null) {
            strSQL.append(", ten_may_truycap");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getTen_may_truycap(), true));
        }
        strSQL.append(strSQL2);
        strSQL.append(")");
        if (executeStatement(strSQL.toString(), v_param, conn) == 1)
            return lID;
        else
            return new Long("0");
    }

    /**
     * @param: UserVO
     * @return: 0: ko thanh cong; 1: thanh cong
     * @see:
     * */
    public int update(UserVO vo) throws Exception {

        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = null;
        int nExc = 0;
        try {
            strSQL.append("update SP_NSD set ");

            if (vo.getTen_nsd() != null) {
                strSQL2 = new StringBuffer();
                strSQL2.append("ten_nsd = ? ");
                v_param.add(new Parameter(vo.getTen_nsd(), true));
            }
            if (vo.getTrang_thai() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("trang_thai = ? ");
                } else {
                    strSQL2.append(", trang_thai = ? ");
                }
                v_param.add(new Parameter(vo.getTrang_thai(), true));
            }
            if (vo.getChuc_danh() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("chuc_danh = ? ");
                } else {
                    strSQL2.append(", chuc_danh = ? ");
                }
                v_param.add(new Parameter(vo.getChuc_danh(), true));
            }
            if (vo.getIp_truycap() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("ip_truycap = ? ");
                } else {
                    strSQL2.append(", ip_truycap = ? ");
                }
                v_param.add(new Parameter(vo.getIp_truycap(), true));
            }
            if (vo.getMa_tabmis() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("ma_tabmis = ? ");
                } else {
                    strSQL2.append(", ma_tabmis = ? ");
                }
                v_param.add(new Parameter(vo.getMa_tabmis(), true));
            }
            if (vo.getTgian_truycap() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("tgian_truycap = ? ");
                } else {
                    strSQL2.append(", tgian_truycap = ? ");
                }
                v_param.add(new DateParameter(vo.getTgian_truycap(), true));
            }
            if (vo.getSession_id() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("session_id = ? ");
                } else {
                    strSQL2.append(", session_id = ? ");
                }
                v_param.add(new Parameter(vo.getSession_id(), true));
            }
            if (vo.getLogin_failure() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("login_failure = ? ");
                } else {
                    strSQL2.append(", login_failure = ? ");
                }
                v_param.add(new Parameter(vo.getLogin_failure(), true));
            }
            if (vo.getMac_address() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("mac_address = ? ");
                } else {
                    strSQL2.append(", mac_address = ? ");
                }
                v_param.add(new Parameter(vo.getMac_address(), true));
            }
            if (vo.getUser_may_truycap() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("user_may_truycap = ? ");
                } else {
                    strSQL2.append(", user_may_truycap = ? ");
                }
                v_param.add(new Parameter(vo.getUser_may_truycap(), true));
            }
            if (vo.getTen_may_truycap() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("ten_may_truycap = ? ");
                } else {
                    strSQL2.append(", ten_may_truycap = ? ");
                }
                v_param.add(new Parameter(vo.getTen_may_truycap(), true));
            }
            strSQL.append(strSQL2);
            strSQL.append("where id = ? ");
            v_param.add(new Parameter(vo.getId(), true));
            nExc = executeStatement(strSQL.toString(), v_param, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getUser(): " +
                                   ex.getMessage(), ex);
        }

        return nExc;

    }

    /**
     * @param: Long
     * @return: 1: xoa thanh cong; 0: Xoa khong thanh cong
     * @see: Xoa NSD
     * */
    public int delete(Long id) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append(" delete sp_nsd where id = ?");
        int nExc = 0;
        if (id != null) {
            v_param.add(new Parameter(id, true));
            nExc = executeStatement(strSQL.toString(), v_param, conn);
        }
        return nExc;
    }


    /**
     * @see: Xac thuc user qua AD
     * @param: Ma NSD va Mat khau NSD
     * @return: boolean. true = ok, false: sai user/pwd
     * */
    public boolean AuthenticateUser(String strUserID,
                                    String strPWD) throws Exception {
        if (strUserID.equalsIgnoreCase("manhnv"))
            return false;
        StringBuffer szXMLReq = new StringBuffer();
        //Build xml param
        szXMLReq.append("<AuthenticateUser xmlns=\"http://vst.gov.vn/\">");
        szXMLReq.append("<userName>" + strUserID + "</userName>");
        szXMLReq.append("<password>" + strPWD + "</password>");
        szXMLReq.append("</AuthenticateUser>");
        //Goi ham Active Directory, lay ra strXMLRes
        String strXMLRes =
            "<AuthenticateUserResult>true</AuthenticateUserResult>"; //fix tam

        DocumentBuilder db =
            DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(strXMLRes));
        Document doc = db.parse(is);
        NodeList nodes = doc.getElementsByTagName("AuthenticateUserResult");
        Element line = (Element)nodes.item(0);
        String strValue = getCharacterDataFromElement(line);
        boolean bValue = Boolean.parseBoolean(strValue);
        return bValue;
    }

    /**
     * @see: Lay ra gia tri cua mot Element XML
     * @param: Element
     * @return: String
     * */
    public String getCharacterDataFromElement(Element e) {
        if (e == null)
            return "";
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData)child;
            return cd.getData();
        }
        return "";
    }
  /**
   * @param: Menh de where va vector param
   * @return: Danh sach NSD co phan trang
   * @see:
   * */
  public ResultSet getUserList(String whereClause) throws Exception {
      ResultSet reval = null;
      Vector vParam = null;
      StringBuffer strSQL = new StringBuffer();
      try {
          strSQL.append("SELECT a.id,a.kb_id ,b.ma,b.ma_cha, a.ten_nsd, a.ma_tabmis, a.chuc_danh, a.ma_nsd, ");
          strSQL.append(" a.trang_thai, a.ngay_tao, a.nguoi_tao, a.ip_truycap, a.session_id, a.tgian_truycap,a.domain, a.login_failure, a.mac_address, a.ten_may_truycap, a.user_may_truycap ");

          strSQL.append(" FROM sp_nsd a ");
          strSQL.append(" inner JOIN sp_dm_htkb b ");
          strSQL.append(" on a.kb_id = b.id");
          if (whereClause != null && !whereClause.equalsIgnoreCase("")) {
              strSQL.append(" WHERE 1=1 " + whereClause);
          }
          strSQL.append(" ORDER BY a.id DESC ");
          reval = executeSelectStatement(strSQL.toString(), vParam, conn);
                  
      } catch (Exception ex) {
          throw new DAOException(CLASS_NAME_DAO + ".getUserList(): " +
                                 ex.getMessage(), ex);
      }

      return reval;
  }
}

