package com.seatech.framework.common;


import com.seatech.framework.AppParam;
import com.seatech.framework.utils.CertUtil;

import java.security.cert.X509CRL;

import java.util.Calendar;
import java.util.Date;

import javax.management.timer.Timer;

public class AppDownloadCRLsTask {
  public static final Date DOWNLOAD_CRLS_START_DATE = new Date();
  
  private Timer timer;
  private Integer notifID;
  
  private String crlUrl;
  private String crlFileDir;
  private String rootCertDir;
  private String trustedCertDir;
  private String proxyHost;
  private String proxyPort;
  private X509CRL crl;

  public static void scheduleTask(Timer timer, AppParam tso) {
//    String[] crlUrls = tso.get(AppParam.CRL_URL_LIST).split(";");
//    String crlFileDir = tso.get(AppParam.CRL_FILE_DIR);
//    String rootCertDir = tso.get(AppParam.ROOT_CERT_DIR_PATH);
//    String trustedCertDir = tso.get(AppParam.TRUSTED_CERT_DIR_PATH);
//    String proxyHost = null;
//    String proxyPort = null;
//    Integer notifID;
//    for (int i = 0; i < crlUrls.length; ++i) {
//      AppDownloadCRLsTask downloadCRLTask = new AppDownloadCRLsTask();
//      downloadCRLTask.setTimer(timer);
//      downloadCRLTask.setCrlUrl(crlUrls[i]);
//      downloadCRLTask.setCrlFileDir(crlFileDir);
//      downloadCRLTask.setRootCertDir(rootCertDir);
//      downloadCRLTask.setTrustedCertDir(trustedCertDir);
//      if(proxyHost != null) {
//        downloadCRLTask.setProxyHost(proxyHost);
//        downloadCRLTask.setProxyPort(proxyPort);
//      }
//      downloadCRLTask.setTimer(timer);
//      notifID = timer.addNotification (AppNotificationListener.DOWNLOAD_CRLS_NOTIF_TYPE, null, downloadCRLTask, downloadCRLTask.DOWNLOAD_CRLS_START_DATE);
//      downloadCRLTask.setNotifID(notifID);
//    }
  }
  public void downloadCRL() {
    Calendar cal;
    Date nextDate;
    Date failureDate;

    try {
        crl = CertUtil.downloadCRLFile(crlUrl, crlFileDir, rootCertDir, trustedCertDir, proxyHost, proxyPort);
        nextDate = crl.getNextUpdate();
        if(nextDate.before(Calendar.getInstance().getTime())) {
            throw new Exception("CLRs issued by " + crl.getIssuerDN().getName() + " is invalid: Next update date is before current date.");
        }
        timer.removeNotification(notifID);
        notifID = timer.addNotification (AppNotificationListener.DOWNLOAD_CRLS_NOTIF_TYPE, null, this, nextDate);
    } catch(Exception ex) {
        failureDate = new Date();
        cal = Calendar.getInstance();
        cal.setTime(failureDate);
        cal.add(Calendar.DATE, 1);
        try {
          timer.removeNotification(notifID);
          notifID = timer.addNotification (AppNotificationListener.DOWNLOAD_CRLS_NOTIF_TYPE, null, this, cal.getTime());
        } catch (Exception e) {
          e.printStackTrace();
        }
        ex.printStackTrace();
    }
  }
  public void setTimer(Timer timer) {
    this.timer = timer;
  }

  public void setCrlUrl(String crlUrl) {
    this.crlUrl = crlUrl;
  }

  public void setCrlFileDir(String crlFileDir) {
    this.crlFileDir = crlFileDir;
  }

  public void setRootCertDir(String rootCertDir) {
    this.rootCertDir = rootCertDir;
  }

  public void setTrustedCertDir(String trustedCertDir) {
    this.trustedCertDir = trustedCertDir;
  }

  public void setProxyHost(String proxyHost) {
    this.proxyHost = proxyHost;
  }

  public void setProxyPort(String proxyPort) {
    this.proxyPort = proxyPort;
  }

  public void setNotifID(Integer notifID) {
    this.notifID = notifID;
  }
}

