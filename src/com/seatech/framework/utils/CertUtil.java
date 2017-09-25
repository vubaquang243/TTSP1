package com.seatech.framework.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.URL;

import java.security.GeneralSecurityException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;

import java.util.ArrayList;
import java.util.Date;


public class CertUtil {
  public static final String X509_CERTIFICATE_TYPE = "X.509";
  public static final String CERT_CHAIN_ENCODING = "PkiPath";
  public static X509Certificate[] getCertificateList(String certDirPath) throws IOException, GeneralSecurityException {

      // Get a list of all files in the given directory
      File dir = new File(certDirPath);
      File[] fList = dir.listFiles();
      int count = fList.length;
      File rootCertFile;
      InputStream certStream;
      ArrayList certArr = new ArrayList();
      
      for(int i = 0; i < count; ++i) {
         rootCertFile = fList[i];
         if(!rootCertFile.isDirectory()) {
             certStream = new FileInputStream(rootCertFile);
             try {
                 X509Certificate trustedCertificate =  loadX509CertificateFromStream(certStream);
                 certArr.add(trustedCertificate);
             } finally {
                 certStream.close();
             }
         }
     }
     return (X509Certificate[])certArr.toArray(new X509Certificate[0]);
  }
  public static X509Certificate loadX509CertificateFromStream(InputStream aCertStream)   throws GeneralSecurityException {
      CertificateFactory cf = CertificateFactory.getInstance(X509_CERTIFICATE_TYPE);
      X509Certificate cert = (X509Certificate)cf.generateCertificate(aCertStream);
      return cert;
  }
  public static void verifyCRL(
      X509CRL crl,
      String rootCertDir, 
      String trustedCertDir) throws GeneralSecurityException, IOException{
      String errMsg;
      X509Certificate cert;
      X509Certificate[] aTrustedCertificates = getCertificateList(trustedCertDir);

      for (int i=0; i<aTrustedCertificates.length; i++) {
          cert = aTrustedCertificates[i];
          try {
              crl.verify(cert.getPublicKey());
              return;
          }
          catch (GeneralSecurityException ex) {
              //Try the next one
          }
      }
      X509Certificate[] aRootCertificates = getCertificateList(rootCertDir);
      for (int i=0; i<aRootCertificates.length; i++) {
          cert = aRootCertificates[i];
          try {
           crl.verify(cert.getPublicKey());
           return;
          }
          catch (GeneralSecurityException ex) {
           //Try the next one
          }
      }
      
      errMsg = new Date() + ": CLRs issued by " + crl.getIssuerDN() + " is invalid.";
      System.out.println(errMsg);
      throw new GeneralSecurityException(errMsg);
  }
  public static X509CRL downloadCRLFile(
      String crlUrl,
      String crlFileDir,
      String rootCertDir, 
      String trustedCertDir,
      String proxyHost,
      String proxyPort) throws Exception{

      CertificateFactory certFactory;
      URL url;
      InputStream crlInputStrem = null;
      X509CRL crl = null;
      byte[] buf = new byte[1024];
      int len;
      String[] issuerDNElem;
      String crlFileName;
      FileOutputStream out;
    
      certFactory = CertificateFactory.getInstance(X509_CERTIFICATE_TYPE);
      if(null != proxyHost) {
          System.setProperty("http.proxyHost", proxyHost);
          System.setProperty("http.proxyPort", proxyPort);
      }
      url = new URL(crlUrl);
      crlInputStrem = url.openStream();
      
      crl = (X509CRL)certFactory.generateCRL(crlInputStrem);
      crlInputStrem.close();
      verifyCRL(crl, rootCertDir, trustedCertDir);
      
      issuerDNElem = crl.getIssuerDN().getName().split(",");
      crlFileName = crlFileDir + File.separator + issuerDNElem[0].substring(issuerDNElem[0].indexOf("=") + 1).trim() + ".crl";
      crlInputStrem = new ByteArrayInputStream(crl.getEncoded());
      out = new FileOutputStream(crlFileName);
      // Transfer bytes from in to out
      while ((len = crlInputStrem.read(buf)) > 0) {
          out.write(buf, 0, len);
      }
      out.close();
      crlInputStrem.close();
      
      System.out.println((new Date() + ": \"" + crlFileName + "\" downloaded and valid. Next update date: " + crl.getNextUpdate()));
      
      return crl;
  }
}
