package com.seatech.ttsp.DongBoHoa;

/**
 * @modify: ThuongDT
 * @modify-date: 12/04/2017
 * @see: bo sung them truong date_stop dap ung theo doi thoi diem ngung job
 * @find: thuongdt-20170412
 **/
public class DongBoHoaVO {
  private  Long id;           
  private  Long user_id;      
  private  String thread_name;
  private  Long time_start;   
  private  Long time_stop;    
  private  Long time_sleeping;
  private  String class_name; 
  private  String log_info;   
  private  String log_error;  
  private  Long log_max_size; 
  private  Long status;       
  private  String description;
  private  String update_date;
  
  //thuongdt-20170412 them truong date_stop
  private String date_stop;
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setThread_name(String thread_name) {
        this.thread_name = thread_name;
    }

    public String getThread_name() {
        return thread_name;
    }

    public void setTime_start(Long time_start) {
        this.time_start = time_start;
    }

    public Long getTime_start() {
        return time_start;
    }

    public void setTime_stop(Long time_stop) {
        this.time_stop = time_stop;
    }

    public Long getTime_stop() {
        return time_stop;
    }

    public void setTime_sleeping(Long time_sleeping) {
        this.time_sleeping = time_sleeping;
    }

    public Long getTime_sleeping() {
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

    public void setLog_max_size(Long log_max_size) {
        this.log_max_size = log_max_size;
    }

    public Long getLog_max_size() {
        return log_max_size;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getStatus() {
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

    public void setDate_stop(String date_stop) {
        this.date_stop = date_stop;
    }

    public String getDate_stop() {
        return date_stop;
    }
}
