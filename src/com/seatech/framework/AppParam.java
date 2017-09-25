package com.seatech.framework;


import com.seatech.framework.common.IParameter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AppParam implements IParameter {
    public static final String PARAM_NUMBER_ROW_ON_PAGE_LOW =
        "PARAM_NUMBER_ROW_ON_PAGING_LOW";
    public static final String PARAM_NUMBER_ROW_ON_PAGE =
        "PARAM_NUMBER_ROW_ON_PAGING";
    public static final String PARAM_NUMBER_ROW_ON_PAGE_HIGHT =
        "PARAM_NUMBER_ROW_ON_PAGING_HIGHT";


    private String VALUE_NUMBER_ROW_ON_PAGE_LOW;
    private String VALUE_NUMBER_ROW_ON_PAGE;
    private String VALUE_NUMBER_ROW_ON_PAGE_HIGHT;

    public AppParam(Connection conn) {
        Statement stm;
        ResultSet rs = null;

        String param_name;
        String param_value;
        try {
            stm = conn.createStatement();
            String strSQL = "select TEN_TS, GIATRI_TS from SP_THAMSO_HT ";
            rs = stm.executeQuery(strSQL);
            while (rs.next()) {
                param_name = rs.getString("TEN_TS").toLowerCase();
                param_value = rs.getString("GIATRI_TS");
                if (PARAM_NUMBER_ROW_ON_PAGE_LOW.equals(param_name)) {
                    VALUE_NUMBER_ROW_ON_PAGE_LOW = param_value;
                } else if (PARAM_NUMBER_ROW_ON_PAGE.equals(param_name)) {
                    VALUE_NUMBER_ROW_ON_PAGE = param_value;
                } else if (PARAM_NUMBER_ROW_ON_PAGE_HIGHT.equals(param_name)) {
                    VALUE_NUMBER_ROW_ON_PAGE_HIGHT = param_value;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public String get(String paramName) {
        if (PARAM_NUMBER_ROW_ON_PAGE_LOW.equals(paramName.toLowerCase())) {
            return VALUE_NUMBER_ROW_ON_PAGE_LOW;
        } else if (PARAM_NUMBER_ROW_ON_PAGE.equals(paramName.toLowerCase())) {
            return VALUE_NUMBER_ROW_ON_PAGE;
        } else if (PARAM_NUMBER_ROW_ON_PAGE_HIGHT.equals(paramName.toLowerCase())) {
            return VALUE_NUMBER_ROW_ON_PAGE_HIGHT;
        }

        return null;
    }
}
