package com.seatech.ttsp.qlyswiftcode;

import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;

import com.seatech.ttsp.qlyphi.QuanLyPhiDAO;
import com.seatech.ttsp.qlyphi.QuanLyPhiVO;

import com.seatech.ttsp.qlyphi.form.QuanLyPhiForm;

import java.io.File;

import java.io.FileOutputStream;
import java.io.InputStream;

import java.io.OutputStream;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.PreparedStatement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import org.w3c.tidy.Out;

public class SpSwiftCodeAction extends AppAction {

    @Override
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            SpSwiftCodeForm f = (SpSwiftCodeForm)form;
            SpSwiftCodeDAO swiftCodeDAO = new SpSwiftCodeDAO(conn);

            Collection swiftCodeList = new ArrayList();

            String pageNumber = f.getPageNumber();
            int currentPage =
                pageNumber == null ? 1 : Integer.parseInt(pageNumber);
            int rowOnPage = 20;
            Integer totalCount[] = new Integer[1];
            PagingBean pagingBean = new PagingBean();

            Vector params = new Vector();
            String swiftCodeCondition = getPhiCondition(f, params);
            swiftCodeList =
                    swiftCodeDAO.getSwiftCode_phanTrang(swiftCodeCondition,
                                                        params, currentPage,
                                                        rowOnPage, totalCount);

            request.setAttribute("swiftCodeList", swiftCodeList);

            pagingBean.setNumberOfRow(totalCount[0].intValue());
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setRowOnPage(rowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);
        } catch (Exception e) {
            throw e;
            //            e.printStackTrace();
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

    private String getPhiCondition(SpSwiftCodeForm f, Vector params) {
        StringBuilder builder = new StringBuilder("");

        if (f.getBic_code() != null && !"".equals(f.getBic_code())) {
            builder.append(" AND a.bic_code like '%" +
                           f.getBic_code().toUpperCase() + "%' ");
        }
        if (f.getBranch_code() != null && !"".equals(f.getBranch_code())) {
            builder.append(" AND a.branch_code like '%" +
                           f.getBranch_code().toUpperCase() + "%' ");
        }
        if (f.getCity_heading() != null && !"".equals(f.getCity_heading())) {
            builder.append(" AND a.city_heading like '%" +
                           f.getCity_heading().toUpperCase() + "%' ");
        }
        if (f.getCountry_name() != null && !"".equals(f.getCountry_name())) {
            builder.append(" AND a.country_name like '%" +
                           f.getCountry_name().toUpperCase() + "%' ");
        }
        if (f.getInstitution_name() != null &&
            !"".equals(f.getInstitution_name())) {
            builder.append(" AND a.institution_name like '%" +
                           f.getInstitution_name().toUpperCase() + "%' ");
        }

        return builder.toString();
    }


    @Override
    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        Connection conn = null;
        boolean add = request.getParameter("add") == null ? false : true;
        boolean update = request.getParameter("update") == null ? false : true;
        try {
            conn = getConnection();
            SpSwiftCodeForm f = (SpSwiftCodeForm)form;
            SpSwiftCodeDAO dao = new SpSwiftCodeDAO(conn);
            if (add) {
                if (dao.insertSwiftCode(f) > 0) {
                    request.setAttribute("success", "Th\u00E0nh c\u00F4ng");
                    conn.commit();
                } else {
                    throw new Exception("Th\u00EAm m\u1EDBi l\u1ED7i");
                }
            } else if (update) {
                if (dao.updateSwiftCode(f) > 0) {
                    request.setAttribute("success",
                                         "S\u1EEDa th\u00E0nh c\u00F4ng");
                    conn.commit();
                } else {
                    throw new Exception("S\u1EEDa l\u1ED7i");
                }
            }
        } catch (Exception e) {
            conn.rollback();
            request.setAttribute("error", e.getMessage());
            e.printStackTrace();
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

    @Override
    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        Connection conn = null;
        int idSwiftCode =
            request.getParameter("id") == null ? 0 : Integer.parseInt(request.getParameter("id"));
        try {
            conn = getConnection();
            SpSwiftCodeDAO dao = new SpSwiftCodeDAO(conn);
            Vector params = new Vector();
            params.add(new Parameter(idSwiftCode, true));
            SpSwiftCodeVO vo = dao.findById(" a.id = ? ", params);
            copySwiftCodeToForm((SpSwiftCodeForm)form, vo);
            request.setAttribute("id", idSwiftCode);
        } catch (Exception e) {
            conn.rollback();
            request.setAttribute("error", e.getMessage());
            e.printStackTrace();
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

    private void copySwiftCodeToForm(SpSwiftCodeForm f,
                                     SpSwiftCodeVO spSwiftCode) {
        f.setId(spSwiftCode.getId());
        f.setBic_code(spSwiftCode.getBic_code());
        f.setBranch_code(spSwiftCode.getBranch_code());
        f.setBranch_information(spSwiftCode.getBranch_information());
        f.setCity_heading(spSwiftCode.getCity_heading());
        f.setCountry_name(spSwiftCode.getCountry_name());
        f.setExtra_info(spSwiftCode.getExtra_info());
        f.setInstitution_name(spSwiftCode.getInstitution_name());
        f.setLocation(spSwiftCode.getLocation());
        f.setPhysical_address_1(spSwiftCode.getPhysical_address_1());
        f.setPhysical_address_2(spSwiftCode.getPhysical_address_2());
        f.setPhysical_address_3(spSwiftCode.getPhysical_address_3());
        f.setPhysical_address_4(spSwiftCode.getPhysical_address_4());
        f.setPob_country_name(spSwiftCode.getPob_country_name());
        f.setPob_location(spSwiftCode.getPob_location());
        f.setPob_number(spSwiftCode.getPob_number());
        f.setSubtype_indication(spSwiftCode.getSubtype_indication());
        f.setTinh_trang(spSwiftCode.getTinh_trang());
        f.setValue_added_services(spSwiftCode.getValue_added_services());
    }
    //Upload

    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;

        Workbook w = null;
        File file = null;
        PreparedStatement statement = null;
        String strLoopStatus = "";
        try {
            AppDAO dao = new AppDAO();
            conn = dao.getConnectionTest();
            SpSwiftCodeForm f = (SpSwiftCodeForm)form;
            FormFile formfile = f.getFile();

            // write the inputStream to a FileOutputStream
            inputStream = formfile.getInputStream();
            outputStream =
                    new FileOutputStream(new File("I:\\swift_code_temp.xls"));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

            System.out.println("Done!");
            //DELETE SWIFT CODE
            conn.createStatement().execute("delete sp_dm_swift_code_temp");
            conn.commit();


            file = new File("I:\\swift_code_temp.xls");
            w = Workbook.getWorkbook(file);
            // Get the first sheet
            Sheet sheet = w.getSheet(0);

            StringBuffer sbSQLHeader =
                new StringBuffer("INSERT INTO sp_dm_swift_code_temp(");
            StringBuffer sbSQLValues = new StringBuffer();
            StringBuffer sbSQL = new StringBuffer();
            for (int j = 0; j < sheet.getRows(); j++) {
                for (int i = 0; i < sheet.getColumns(); i++) {
                    Cell cell = sheet.getCell(i, j);
                    if (j == 0) {
                        if (i == 0) {
                            sbSQLHeader.append(cell.getContents().trim().replace(" ",
                                                                                 "_"));
                            sbSQLValues.append("?");
                        } else {
                            sbSQLHeader.append(" ," +
                                               cell.getContents().trim().replace(" ",
                                                                                 "_"));
                            sbSQLValues.append(" ,?");
                        }
                    } else {
                        strLoopStatus = "break";
                        break;
                    }
                }
                if ("break".equals(strLoopStatus)) {
                    break;
                }
            }
            sbSQL.append(sbSQLHeader);
            sbSQL.append(") VALUES (");
            sbSQL.append(sbSQLValues);
            sbSQL.append(")");
            statement = conn.prepareStatement(sbSQL.toString());
            int count = 0;
            for (int j = 0; j < sheet.getRows(); j++) {
                for (int i = 0; i < sheet.getColumns(); i++) {
                    Cell cell = sheet.getCell(i, j);
                    if (j > 0) {
                        statement.setObject(i + 1, cell.getContents());
                    }
                }
                if (j > 0) {
                    statement.addBatch();
                }
                if ((j + 1) % 1000 == 0) {
                    statement.executeBatch();
                    System.out.println("COMMIT");
                }
                count++;
            }
            System.out.println("count=" + count);
            statement.executeBatch();
            System.out.println("COMMIT");
            statement.clearBatch();
            statement.close();
            conn.commit();

        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            close(conn);
            try {
                outputStream.close();
                inputStream.close();
                w.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return mapping.findForward("success");
    }
    //dong bo danh muc

    public ActionForward addExc(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            AppDAO dao = new AppDAO();
            conn = dao.getConnectionTest();
            
          CallableStatement cs = null;

          cs =
          conn.prepareCall("{call proc_dongbo_swiftcode(?,?,?)}");
          cs.registerOutParameter(1, java.sql.Types.VARCHAR);
          cs.registerOutParameter(2, java.sql.Types.VARCHAR);
          cs.registerOutParameter(3, java.sql.Types.VARCHAR);

          cs.execute();

          String p_so_luong = cs.getString(1);
          String p_err_code = cs.getString(2);
          String p_err_desc = cs.getString(3);
          
          request.setAttribute("p_so_luong", p_so_luong);
          request.setAttribute("p_err_code", p_err_code);
          request.setAttribute("p_err_desc", p_err_desc);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }
}
