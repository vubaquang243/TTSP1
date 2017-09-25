package com.seatech.ttsp.user.form;

import org.apache.struts.action.ActionForm;

public class ReSendForm extends ActionForm {
    private String username;
    private String password;
    private String status;
    private String error_desc;
    private String resend;
    private String success;
    private String username_data;
    private String password_data;
    private String host_data;
    private String port_data;
    private String sid_data;
    private String type;
  private String where;
  private String user;
  private String pass;

   
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setError_desc(String error_desc) {
        this.error_desc = error_desc;
    }

    public String getError_desc() {
        return error_desc;
    }

    public void setResend(String resend) {
        this.resend = resend;
    }

    public String getResend() {
        return resend;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getSuccess() {
        return success;
    }

    public void setUsername_data(String username_data) {
        this.username_data = username_data;
    }

    public String getUsername_data() {
        return username_data;
    }

    public void setPassword_data(String password_data) {
        this.password_data = password_data;
    }

    public String getPassword_data() {
        return password_data;
    }

    public void setHost_data(String host_data) {
        this.host_data = host_data;
    }

    public String getHost_data() {
        return host_data;
    }

    public void setPort_data(String port_data) {
        this.port_data = port_data;
    }

    public String getPort_data() {
        return port_data;
    }

    public void setSid_data(String sid_data) {
        this.sid_data = sid_data;
    }

    public String getSid_data() {
        return sid_data;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getWhere() {
        return where;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPass() {
        return pass;
    }
}
