package com.seatech.framework.common;

import javax.management.Notification;
import javax.management.NotificationListener;

public class AppNotificationListener implements NotificationListener {
  public static final String DOWNLOAD_CRLS_NOTIF_TYPE = "DOWNLOAD_CRLS_NOTIF_TYPE";
  public void handleNotification(Notification notif, Object handback) {
      if(DOWNLOAD_CRLS_NOTIF_TYPE.equals(notif.getType())) {
        AppDownloadCRLsTask downloadCRLsTask = (AppDownloadCRLsTask) notif.getUserData();
        downloadCRLsTask.downloadCRL();
      }
  }
}