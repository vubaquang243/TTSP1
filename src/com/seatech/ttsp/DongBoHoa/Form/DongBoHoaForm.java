package com.seatech.ttsp.DongBoHoa.Form;

import org.apache.struts.action.ActionForm;


public class DongBoHoaForm extends ActionForm {

  private  String id;           
  private  String user_id;      
  private  String thread_name;
  private  String time_start;   
  private  String time_stop;    
  private  String time_sleeping;
  private  String class_name; 
  private  String log_info;   
  private  String log_error;  
  private  String log_max_size; 
  private  String status;       
  private  String description;
  private  String update_date;


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setThread_name(String thread_name) {
        this.thread_name = thread_name;
    }

    public String getThread_name() {
        return thread_name;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_stop(String time_stop) {
        this.time_stop = time_stop;
    }

    public String getTime_stop() {
        return time_stop;
    }

    public void setTime_sleeping(String time_sleeping) {
        this.time_sleeping = time_sleeping;
    }

    public String getTime_sleeping() {
        return time_sleeping;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setLog_info(String log_info) {
        this.log_info = log_info;
    }

    public String getLog_info() {
        return log_info;
    }

    public void setLog_error(String log_error) {
        this.log_error = log_error;
    }

    public String getLog_error() {
        return log_error;
    }

    public void setLog_max_size(String log_max_size) {
        this.log_max_size = log_max_size;
    }

    public String getLog_max_size() {
        return log_max_size;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public String getUpdate_date() {
        return update_date;
    }
}
