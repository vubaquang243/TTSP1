package com.seatech.ttsp.qlyswiftcode;

import com.seatech.framework.strustx.AppForm;

import org.apache.struts.upload.FormFile;

public class SpSwiftCodeForm extends AppForm {
    private long id;
    private String bic_code;
    private String branch_code;
    private String institution_name;
    private String branch_information;
    private String city_heading;
    private String subtype_indication;
    private String value_added_services;
    private String extra_info;
    private String physical_address_1;
    private String physical_address_2;
    private String physical_address_3;
    private String physical_address_4;
    private String location;
    private String country_name;
    private String pob_number;
    private String pob_location;
    private String pob_country_name;
    private String tinh_trang;
        
    private String pageNumber;

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setBic_code(String bic_code) {
        this.bic_code = bic_code;
    }

    public String getBic_code() {
        return bic_code;
    }

    public void setBranch_code(String branch_code) {
        this.branch_code = branch_code;
    }

    public String getBranch_code() {
        return branch_code;
    }

    public void setCity_heading(String city_heading) {
        this.city_heading = city_heading;
    }

    public String getCity_heading() {
        return city_heading;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setInstitution_name(String institution_name) {
        this.institution_name = institution_name;
    }

    public String getInstitution_name() {
        return institution_name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setBranch_information(String branch_information) {
        this.branch_information = branch_information;
    }

    public String getBranch_information() {
        return branch_information;
    }

    public void setSubtype_indication(String subtype_indication) {
        this.subtype_indication = subtype_indication;
    }

    public String getSubtype_indication() {
        return subtype_indication;
    }

    public void setValue_added_services(String value_added_services) {
        this.value_added_services = value_added_services;
    }

    public String getValue_added_services() {
        return value_added_services;
    }

    public void setExtra_info(String extra_info) {
        this.extra_info = extra_info;
    }

    public String getExtra_info() {
        return extra_info;
    }

    public void setPhysical_address_1(String physical_address_1) {
        this.physical_address_1 = physical_address_1;
    }

    public String getPhysical_address_1() {
        return physical_address_1;
    }

    public void setPhysical_address_2(String physical_address_2) {
        this.physical_address_2 = physical_address_2;
    }

    public String getPhysical_address_2() {
        return physical_address_2;
    }

    public void setPhysical_address_3(String physical_address_3) {
        this.physical_address_3 = physical_address_3;
    }

    public String getPhysical_address_3() {
        return physical_address_3;
    }

    public void setPhysical_address_4(String physical_address_4) {
        this.physical_address_4 = physical_address_4;
    }

    public String getPhysical_address_4() {
        return physical_address_4;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setPob_number(String pob_number) {
        this.pob_number = pob_number;
    }

    public String getPob_number() {
        return pob_number;
    }

    public void setPob_location(String pob_location) {
        this.pob_location = pob_location;
    }

    public String getPob_location() {
        return pob_location;
    }

    public void setPob_country_name(String pob_country_name) {
        this.pob_country_name = pob_country_name;
    }

    public String getPob_country_name() {
        return pob_country_name;
    }

    public void setTinh_trang(String tinh_trang) {
        this.tinh_trang = tinh_trang;
    }

    public String getTinh_trang() {
        return tinh_trang;
    }
    
    
    private FormFile file;
    
    public FormFile getFile() {
      return file;
    }

    public void setFile(FormFile file) {
      this.file = file;
    }

}
