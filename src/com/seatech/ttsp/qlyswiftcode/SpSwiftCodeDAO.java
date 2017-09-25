package com.seatech.ttsp.qlyswiftcode;

import com.seatech.framework.datamanager.AppDAO;

import com.seatech.framework.datamanager.Parameter;

import com.seatech.framework.exception.DAOException;
import com.seatech.ttsp.qlyphi.QuanLyPhiVO;

import java.sql.Connection;

import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

public class SpSwiftCodeDAO  extends AppDAO {
    
    private static final String SP_SWIFT_CODE_VO = "com.seatech.ttsp.qlyswiftcode.SpSwiftCodeVO";
    private static final String SP_SWIFT_CODE_DAO = "com.seatech.ttsp.qlyswiftcode.SpSwiftCodeDAO";
    private Connection conn;
    
    public SpSwiftCodeDAO(Connection conn) {
        this.conn = conn;
    }


    public Collection getSwiftCode_phanTrang(String swiftCodeCondition,
                                                Vector v_param, int currentPage,
                                                int numberRowOnPage, Integer[] totalCount) throws Exception {
        String query = "/* Formatted on 6/26/2015 4:15:04 PM (QP5 v5.126) */ " + 
        "  SELECT  a.id, " +
        "          a.country_name, " + 
        "          a.city_heading, " + 
        "          a.institution_name, " + 
        "          a.bic_code, " + 
        "          a.branch_code " + 
        "    FROM  sp_dm_swift_code a " + 
        "   WHERE  a.tinh_trang = '1' " + swiftCodeCondition +
        " ORDER BY  a.country_name ";
        return executeSelectWithPaging(conn, query.toString(), v_param, SP_SWIFT_CODE_VO, currentPage, numberRowOnPage, totalCount);
    }
    
  public int insertSwiftCode(SpSwiftCodeForm swiftCodeVO) throws Exception {
      Vector v_param = new Vector();
      StringBuilder s1 = new StringBuilder();
      StringBuilder s2 = new StringBuilder();
      
      s1.append("INSERT INTO SP_DM_SWIFT_CODE (ID ");
      long id = getSeqNextValue("sp_dm_ngan_hang_seq",conn); //lay next id
      s2.append(") values (? ");
      v_param.add(new Parameter(id,true));
      if (swiftCodeVO.getBic_code() != null && !"".equals(swiftCodeVO.getBic_code())) {
          s1.append(", bic_code");
          s2.append(", ?");
          v_param.add(new Parameter(swiftCodeVO.getBic_code().toUpperCase(), true));
      }
      if (swiftCodeVO.getBranch_code() != null && !"".equals(swiftCodeVO.getBranch_code())) {
          s1.append(", branch_code");
          s2.append(", ?");
          v_param.add(new Parameter(swiftCodeVO.getBranch_code().toUpperCase(), true));
      }
      if (swiftCodeVO.getInstitution_name() != null && !"".equals(swiftCodeVO.getInstitution_name())) {
          s1.append(", institution_name");
          s2.append(", ?");
          v_param.add(new Parameter(swiftCodeVO.getInstitution_name().toUpperCase(), true));
      }
      if (swiftCodeVO.getBranch_information() != null && !"".equals(swiftCodeVO.getBranch_information())) {
          s1.append(", branch_information");
          s2.append(", ?");
          v_param.add(new Parameter(swiftCodeVO.getBranch_information().toUpperCase(), true));
      }
      if (swiftCodeVO.getCity_heading() != null && !"".equals(swiftCodeVO.getCity_heading())) {
          s1.append(", city_heading");
          s2.append(", ?");
          v_param.add(new Parameter(swiftCodeVO.getCity_heading().toUpperCase(), true));
      }
      if (swiftCodeVO.getSubtype_indication() != null && !"".equals(swiftCodeVO.getSubtype_indication())) {
          s1.append(", subtype_indication");
          s2.append(", ?");
          v_param.add(new Parameter(swiftCodeVO.getSubtype_indication().toUpperCase(), true));
      }
      if (swiftCodeVO.getValue_added_services() != null && !"".equals(swiftCodeVO.getValue_added_services())) {
          s1.append(", value_added_services");
          s2.append(", ?");
          v_param.add(new Parameter(swiftCodeVO.getValue_added_services().toUpperCase(), true));
      }
      if (swiftCodeVO.getExtra_info() != null && !"".equals(swiftCodeVO.getExtra_info())) {
          s1.append(", extra_info");
          s2.append(", ?");
          v_param.add(new Parameter(swiftCodeVO.getExtra_info().toUpperCase(), true));
      }
      if (swiftCodeVO.getPhysical_address_1() != null && !"".equals(swiftCodeVO.getPhysical_address_1())) {
          s1.append(", physical_address_1");
          s2.append(", ?");
          v_param.add(new Parameter(swiftCodeVO.getPhysical_address_1().toUpperCase(), true));
      }
      if (swiftCodeVO.getPhysical_address_2() != null && !"".equals(swiftCodeVO.getPhysical_address_2())) {
          s1.append(", physical_address_2");
          s2.append(", ?");
          v_param.add(new Parameter(swiftCodeVO.getPhysical_address_2().toUpperCase(), true));
      }
      if (swiftCodeVO.getPhysical_address_3() != null && !"".equals(swiftCodeVO.getPhysical_address_3())) {
          s1.append(", physical_address_3");
          s2.append(", ?");
          v_param.add(new Parameter(swiftCodeVO.getPhysical_address_3().toUpperCase(), true));
      }
      if (swiftCodeVO.getPhysical_address_4() != null && !"".equals(swiftCodeVO.getPhysical_address_4())) {
          s1.append(", physical_address_4");
          s2.append(", ?");
          v_param.add(new Parameter(swiftCodeVO.getPhysical_address_4().toUpperCase(), true));
      }
      if (swiftCodeVO.getLocation() != null && !"".equals(swiftCodeVO.getLocation())) {
          s1.append(", location");
          s2.append(", ?");
          v_param.add(new Parameter(swiftCodeVO.getLocation().toUpperCase(), true));
      }
      if (swiftCodeVO.getCountry_name() != null && !"".equals(swiftCodeVO.getCountry_name())) {
          s1.append(", country_name");
          s2.append(", ?");
          v_param.add(new Parameter(swiftCodeVO.getCountry_name().toUpperCase(), true));
      }
      if (swiftCodeVO.getPob_number() != null && !"".equals(swiftCodeVO.getPob_number())) {
          s1.append(", pob_number");
          s2.append(", ?");
          v_param.add(new Parameter(swiftCodeVO.getPob_number().toUpperCase(), true));
      }
      if (swiftCodeVO.getPob_location() != null && !"".equals(swiftCodeVO.getPob_location())) {
          s1.append(", pob_location");
          s2.append(", ?");
          v_param.add(new Parameter(swiftCodeVO.getPob_location().toUpperCase(), true));
      }
    if (swiftCodeVO.getPob_country_name() != null && !"".equals(swiftCodeVO.getPob_country_name())) {
        s1.append(", pob_country_name");
        s2.append(", ?");
        v_param.add(new Parameter(swiftCodeVO.getPob_country_name().toUpperCase(), true));
    }
    if (swiftCodeVO.getTinh_trang() != null && !"".equals(swiftCodeVO.getTinh_trang())) {
        s1.append(", tinh_trang");
        s2.append(", ?");
        v_param.add(new Parameter(swiftCodeVO.getTinh_trang(), true));
    }
      s1.append(s2);s1.append(")");
      return executeStatement(s1.toString(), v_param, conn);
  }

    public int updateSwiftCode(SpSwiftCodeForm swiftCodeVO) throws Exception {
        Vector v_param = new Vector();
        StringBuilder s1 = new StringBuilder();
        
        s1.append("update SP_DM_SWIFT_CODE set ");
        if (swiftCodeVO.getBic_code() != null && !"".equals(swiftCodeVO.getBic_code())) {
            s1.append(" bic_code = ? ");
            v_param.add(new Parameter(swiftCodeVO.getBic_code().toUpperCase(), true));
        }
        if (swiftCodeVO.getBranch_code() != null && !"".equals(swiftCodeVO.getBranch_code())) {
            s1.append(", branch_code = ? ");
            v_param.add(new Parameter(swiftCodeVO.getBranch_code().toUpperCase(), true));
        }
        if (swiftCodeVO.getInstitution_name() != null && !"".equals(swiftCodeVO.getInstitution_name())) {
            s1.append(", institution_name = ? ");
            v_param.add(new Parameter(swiftCodeVO.getInstitution_name().toUpperCase(), true));
        }
        if (swiftCodeVO.getBranch_information() != null && !"".equals(swiftCodeVO.getBranch_information())) {
            s1.append(", branch_information = ? ");
            v_param.add(new Parameter(swiftCodeVO.getBranch_information().toUpperCase(), true));
        }
        if (swiftCodeVO.getCity_heading() != null && !"".equals(swiftCodeVO.getCity_heading())) {
            s1.append(", city_heading = ? ");
            v_param.add(new Parameter(swiftCodeVO.getCity_heading().toUpperCase(), true));
        }
        if (swiftCodeVO.getSubtype_indication() != null && !"".equals(swiftCodeVO.getSubtype_indication())) {
            s1.append(", subtype_indication = ? ");
            v_param.add(new Parameter(swiftCodeVO.getSubtype_indication().toUpperCase(), true));
        }
        if (swiftCodeVO.getValue_added_services() != null && !"".equals(swiftCodeVO.getValue_added_services())) {
            s1.append(", value_added_services = ? ");
            v_param.add(new Parameter(swiftCodeVO.getValue_added_services().toUpperCase(), true));
        }
        if (swiftCodeVO.getExtra_info() != null && !"".equals(swiftCodeVO.getExtra_info())) {
            s1.append(", extra_info = ? ");
            v_param.add(new Parameter(swiftCodeVO.getExtra_info().toUpperCase(), true));
        }
        if (swiftCodeVO.getPhysical_address_1() != null && !"".equals(swiftCodeVO.getPhysical_address_1())) {
            s1.append(", physical_address_1 = ? ");
            v_param.add(new Parameter(swiftCodeVO.getPhysical_address_1().toUpperCase(), true));
        }
        if (swiftCodeVO.getPhysical_address_2() != null && !"".equals(swiftCodeVO.getPhysical_address_2())) {
            s1.append(", physical_address_2 = ? ");
            v_param.add(new Parameter(swiftCodeVO.getPhysical_address_2().toUpperCase(), true));
        }
        if (swiftCodeVO.getPhysical_address_3() != null && !"".equals(swiftCodeVO.getPhysical_address_3())) {
            s1.append(", physical_address_3 = ? ");
            v_param.add(new Parameter(swiftCodeVO.getPhysical_address_3().toUpperCase(), true));
        }
        if (swiftCodeVO.getPhysical_address_4() != null && !"".equals(swiftCodeVO.getPhysical_address_4())) {
            s1.append(", physical_address_4 = ? ");
            v_param.add(new Parameter(swiftCodeVO.getPhysical_address_4().toUpperCase(), true));
        }
        if (swiftCodeVO.getLocation() != null && !"".equals(swiftCodeVO.getLocation())) {
            s1.append(", location = ? ");
            v_param.add(new Parameter(swiftCodeVO.getLocation().toUpperCase(), true));
        }
        if (swiftCodeVO.getCountry_name() != null && !"".equals(swiftCodeVO.getCountry_name())) {
            s1.append(", country_name = ? ");
            v_param.add(new Parameter(swiftCodeVO.getCountry_name().toUpperCase(), true));
        }
        if (swiftCodeVO.getPob_number() != null && !"".equals(swiftCodeVO.getPob_number())) {
            s1.append(", pob_number = ? ");
            v_param.add(new Parameter(swiftCodeVO.getPob_number().toUpperCase(), true));
        }
        if (swiftCodeVO.getPob_location() != null && !"".equals(swiftCodeVO.getPob_location())) {
            s1.append(", pob_location = ? ");
            v_param.add(new Parameter(swiftCodeVO.getPob_location().toUpperCase(), true));
        }
        if (swiftCodeVO.getPob_country_name() != null && !"".equals(swiftCodeVO.getPob_country_name())) {
          s1.append(", pob_country_name = ? ");
          v_param.add(new Parameter(swiftCodeVO.getPob_country_name().toUpperCase(), true));
        }
        if (swiftCodeVO.getTinh_trang() != null && !"".equals(swiftCodeVO.getTinh_trang())) {
          s1.append(", tinh_trang = ? ");
          v_param.add(new Parameter(swiftCodeVO.getTinh_trang(), true));
        }
        s1.append(" where id = ? ");
        v_param.add(new Parameter(swiftCodeVO.getId(), true));
        return executeStatement(s1.toString(), v_param, conn);
    }
    
    public SpSwiftCodeVO findById(String condition, Vector params)throws Exception{
        SpSwiftCodeVO vo = null;
        StringBuilder query = new StringBuilder();
        try{
            query.append(" SELECT a.id, a.bic_code, a.branch_code, a.institution_name, " + 
            "       a.branch_information, a.city_heading, a.subtype_indication, " + 
            "       a.value_added_services, a.extra_info, a.physical_address_1, " + 
            "       a.physical_address_2, a.physical_address_3, a.physical_address_4, " + 
            "       a.location, a.country_name, a.pob_number, a.pob_location, " + 
            "       a.pob_country_name, a.tinh_trang " + 
            "  FROM sp_dm_swift_code a ");
            if (condition != null & !condition.isEmpty()) {
                query.append(" WHERE " + condition);
            }
            vo = (SpSwiftCodeVO) findByPK(query.toString(), params, SP_SWIFT_CODE_VO, conn);
        }catch(Exception e){
            DAOException daoEx = new DAOException(SP_SWIFT_CODE_DAO + ".getPhi: " + e.getMessage());
            daoEx.printStackTrace();
            throw daoEx;
        }
        return vo;
    }
}
