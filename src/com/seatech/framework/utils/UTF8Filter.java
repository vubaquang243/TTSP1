/*
 * @(#)UTF8Filter.java        
 */
package com.seatech.framework.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class UTF8Filter implements Filter {

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
	  response.setCharacterEncoding("UTF-8");
/**
 * - ManhNV
 * - 30/11/2016
 * - Disable ghi log "Cannot forward a response that is already committed", day la loi ko anh huong den ung dung nhung ton tai nguyen luu tru log
 * - Key tim kiem: 201161130-DISABLE_LOG-BEGIN
 * **/
//	  chain.doFilter(request, response);
    try{
      chain.doFilter(request, response);
    }catch(IOException ex){        
        throw ex;        
    }catch(ServletException ex){
        throw ex;
    }catch(Exception ex){
        if(!"Cannot forward a response that is already committed".equals(ex.getMessage())){
          ex.printStackTrace();
        }
    }
/**201161130-DISABLE_LOG-END**/
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
