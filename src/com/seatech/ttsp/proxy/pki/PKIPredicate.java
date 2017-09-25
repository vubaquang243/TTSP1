package com.seatech.ttsp.proxy.pki;


import com.seatech.ttsp.user.UserVO;
import com.seatech.utils.XMLGregorianCalendarUtils;

import java.text.SimpleDateFormat;

import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.collections.Predicate;

import vn.gov.vst.CertInfo;


public class PKIPredicate implements Predicate {
  private String serial, username,fromdate,todate;
  Collection colUserName;
      
   SimpleDateFormat format=null;
      public PKIPredicate(String serial,String username,String fromdate,String todate,Collection colUserName) {
        this.serial = serial;
        this.username = username;
        this.fromdate = fromdate;
        this.todate = todate;
        this.colUserName=colUserName;
    }


    public boolean evaluate(Object object) {
       boolean satisfies=false;
       boolean dk1=false;
       boolean dk2=false;
       boolean dk3=false;
       boolean dk4=false;
       boolean dk5=false;//dk kho bac
       if( object instanceof CertInfo) {
          try {
               CertInfo certInfoBean = (CertInfo)object;
                
                if ( (serial == null || serial.equalsIgnoreCase("")) || (serial != null && certInfoBean.getCertSerial().trim().indexOf(serial.trim()) >=0))
                    dk1 = true;
                //check ma_nsd or ten
                if ((username == null || username.equalsIgnoreCase("")) ||
                    (username != null &&
                     (certInfoBean.getUserName().trim().indexOf(username.trim()) >=0 ||
                      certInfoBean.getIssuedTo().trim().indexOf(username.trim()) >=0)))
                    dk2 = true;
                
                //check ma kho bac
                if(colUserName!=null && colUserName.size()>0){
                    for(Iterator iter=colUserName.iterator();iter.hasNext();){
                          UserVO userVo=(UserVO)iter.next();
                        if(certInfoBean.getUserName().trim().indexOf(userVo.getMa_nsd())>=0){
                          dk5=true;
                          break;
                        }
                    }
                }else if(colUserName==null){
                    dk5=true;
                }
                
                if (fromdate == null || fromdate.equalsIgnoreCase(""))
                    dk3 = true;
                else if(fromdate != null){
                    if(format==null)
                      format=new SimpleDateFormat("dd/mm/yyyy");
                    long dk_tungay= format.parse(fromdate).getTime();
                    long den_ngay=XMLGregorianCalendarUtils.convertXMLGregorianCalendarToDate(certInfoBean.getValidTo()).getTime();
                    if(dk_tungay<=den_ngay)
                      dk3 = true;
                }
                if (todate == null || todate.equalsIgnoreCase("")){
                     dk4 = true;
                }else if(todate != null){
                  if(format==null)
                    format=new SimpleDateFormat("dd/mm/yyyy");
                  long tu_ngay=XMLGregorianCalendarUtils.convertXMLGregorianCalendarToDate(certInfoBean.getValidFrom()).getTime();
                  long dk_den_ngay=format.parse(todate).getTime();
                  if(dk_den_ngay>=tu_ngay)
                      dk4 = true;
                }
                if (dk5 && (dk1 && (dk2 && (dk3 && dk4)))) {
                    satisfies = dk5;
                }
                
        }catch(Exception ex){
              ex.printStackTrace();
          }
        }
        return satisfies;
    }
   
}
