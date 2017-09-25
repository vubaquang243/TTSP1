package com.seatech.framework.utils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
/*
 * @creater: ThuongDT
 * @create date: 15/11/2016
 * @see: filter lai cac request loc bo cac ky tu lon
 * */
public class RequestWrapper extends HttpServletRequestWrapper {
    
    public RequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }
   
   //Loc bo metacharacters tu cac parameter value
    public String[] getParameterValues(String parameter) {
      String[] values = super.getParameterValues(parameter);
      if (values==null)  {
                  return null;
          }
      int count = values.length;
      String[] encodedValues = new String[count];
      for (int i = 0; i < count; i++) {
                 encodedValues[i] = cleanXSS(values[i]);
       }
      return encodedValues;
    }
    
  //Loc bo metacharacters tu cac parameter 
    public String getParameter(String parameter) {
          String value = super.getParameter(parameter);
          if (value == null) {
                 return null;
                  }
          return cleanXSS(value);
    }
  //Loc bo metacharacters tu cac URI 
    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (value == null)
            return null;
        return cleanXSS(value);
    }
  //Loc bo metacharacters tu cac URI 
    public String getRequestURI(){
      String value = super.getRequestURI();
      if (value == null)
          return null;
      return cleanXSSPath(value);
    }
    
  //Loc bo metacharacters tu cac URL 
    public StringBuffer getRequestURL(){
      StringBuffer value = super.getRequestURL();
      if (value == null)
          return null;
      return new StringBuffer(cleanXSSPath(value.toString()));
    }
    //Loc bo metacharacters tu servlet
    public String getServletPath(){
      String value = super.getServletPath();
      if (value == null)
          return null;
      return cleanXSSPath(value);
    }
    
  //Loc bo metacharacters
    private String cleanXSS(String value) {        
        value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
        value = value.replaceAll("([\r|\n]+)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replaceAll("script", "");
        value = value.replaceAll("alert", "");
        return value;
    }
    
  //Loc bo metacharacters  
  private String cleanXSSPath(String value) {        
        value = value.replaceAll("<script>", "");
        value = value.replaceAll("%3Cscript%3E", "");
        value = value.replaceAll("%3C/script%3E", "");
        value = value.replaceAll("alert", "");      
      return value;
  }
}