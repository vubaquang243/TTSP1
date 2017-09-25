package com.seatech.framework.datamanager;


import com.seatech.framework.AppConstants;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.PrinterName;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.JRXmlExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporterParameter;
import net.sf.jasperreports.view.JasperViewer;


public class MultiReportUtility {
    public MultiReportUtility() {
        super();
    }
    // Khoi tao JRExporter
    // Xac dinh dinh dang Exporter
    // @param : dinh dang file

    /**
     * For report
     */
    public JasperViewer getJasperViewer(JasperPrint jPrint, String strTitle) {
        JasperViewer jViewer = new JasperViewer(jPrint, false);
        if (strTitle != null && !"".equalsIgnoreCase(strTitle)) {
            jViewer.setTitle(strTitle);
        }

        return jViewer;
    }

    public JRExporter getExporter(String format) {
        JRExporter exporter = null;
        if (exporter == null) {
            if (format.equalsIgnoreCase("csv"))
                exporter = new JRCsvExporter();
            else if (format.equalsIgnoreCase("html"))
                exporter = new JRHtmlExporter();
            else if (format.equalsIgnoreCase("excel"))
                exporter = new JRXlsExporter();
            else if (format.equalsIgnoreCase("rtf"))
                exporter = new JRRtfExporter();
            else if (format.equalsIgnoreCase("xml"))
                exporter = new JRXmlExporter();
            else if (format.equalsIgnoreCase("txt"))
                exporter = new JRTextExporter();
            else if (format.equalsIgnoreCase("pdf"))
                exporter = new JRPdfExporter();
            else
                exporter = new JRHtmlExporter();
        }

        return exporter;
    }

    public String getMIMEType(String format) {
        String mimeType = null;
        if (mimeType == null) {
            if (format.equalsIgnoreCase("csv"))
                mimeType = "text/plain";
            else if (format.equalsIgnoreCase("html"))
                mimeType = "text/html";
            else if (format.equalsIgnoreCase("excel"))
                mimeType = "application/vnd.ms-excel";
            else if (format.equalsIgnoreCase("rtf"))
                mimeType = "application / msword; charset = utf-8";
            else if (format.equalsIgnoreCase("xml"))
                mimeType = "text/xml";
            else if (format.equalsIgnoreCase("txt"))
                mimeType = "text/plain";
            else if (format.equalsIgnoreCase("pdf"))
                mimeType = "application/pdf";
            else if (format.equalsIgnoreCase("JPG"))
                mimeType = "application/jpeg";
            else if (format.equalsIgnoreCase("JPGBASE64"))
                mimeType = "text/plain";
            else
                mimeType = "text/html";
        }
        return mimeType;
    }
    // tao file .JRPrint

    public JasperPrint exportJasperPrint(InputStream reportStream, HashMap map,
                                         JRResultSetDataSource jrDataSource) throws JRException {

        JasperPrint jasPrint = null;
        jasPrint =
                JasperFillManager.fillReport(reportStream, map, jrDataSource);
        return jasPrint;
    }

    // export to format PDF report

    public void generalPDFReport(OutputStream outputStream,
                                 JRExporter exporter, String strPathFont,
                                 JasperPrint jasperPrint) throws JRException,
                                                                 IOException,
                                                                 ServletException {
        JRDesignStyle normalStyle = new JRDesignStyle();
        normalStyle.setPdfFontName(strPathFont);
        normalStyle.setPdfEncoding("Identity-H");
        normalStyle.setPdfEmbedded(true);
        normalStyle.setBlankWhenNull(true);
        jasperPrint.addStyle(normalStyle, true);
        Float zoom = new Float(1.2);
        exporter.setParameter(JRHtmlExporterParameter.ZOOM_RATIO, zoom);
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "LTT");
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
        exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
        try {
            exporter.exportReport();
        } catch (JRException e) {
            throw new ServletException(e);
        }
    }

    public void generalMultiPDFReport(OutputStream outputStream,
                                      JRExporter exporter,
                                      List<JasperPrint> jasperPrint) throws JRException,
                                                                            IOException,
                                                                            ServletException {
        Float zoom = new Float(1.2);
        exporter.setParameter(JRHtmlExporterParameter.ZOOM_RATIO, zoom);
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "LTT");
        exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST,
                              jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
        exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
        try {
            exporter.exportReport();
        } catch (JRException e) {
            throw new ServletException(e);
        }
    }
    // export to format excel report

    public void generalExcelReport(OutputStream outputStream,
                                   JRExporter exporter,
                                   JasperPrint jasperPrint) throws JRException,
                                                                   ServletException,
                                                                   IOException {
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
        exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
                              Boolean.FALSE);
        exporter.setParameter(JRHtmlExporterParameter.IS_WRAP_BREAK_WORD,
                              Boolean.TRUE);
        exporter.setParameter(JRXlsExporterParameter.IS_IGNORE_CELL_BORDER,
                              Boolean.FALSE);
        exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
                              Boolean.TRUE);
        exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN,
                              Boolean.TRUE);
        exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML, "");
        Float zoom = new Float(1.2);
        exporter.setParameter(JRHtmlExporterParameter.ZOOM_RATIO, zoom);
        exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,
                              Boolean.TRUE);
        try {
            exporter.exportReport();
        } catch (JRException e) {
            throw new ServletException(e);
        }
    }
    // export to format excel report

    public void generalMultiExcelReport(OutputStream outputStream,
                                        JRExporter exporter,
                                        List<JasperPrint> jasperPrint) throws JRException,
                                                                              ServletException,
                                                                              IOException {
        exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST,
                              jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
        exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
                              Boolean.FALSE);
        exporter.setParameter(JRHtmlExporterParameter.IS_WRAP_BREAK_WORD,
                              Boolean.TRUE);
        exporter.setParameter(JRXlsExporterParameter.IS_IGNORE_CELL_BORDER,
                              Boolean.FALSE);
        exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
                              Boolean.TRUE);
        exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN,
                              Boolean.TRUE);
        exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML, "");
        Float zoom = new Float(1.2);
        exporter.setParameter(JRHtmlExporterParameter.ZOOM_RATIO, zoom);
        exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,
                              Boolean.TRUE);
        try {
            exporter.exportReport();
        } catch (JRException e) {
            throw new ServletException(e);
        }
    }
    // export to format html report

    public void generalHtmlReport(OutputStream outputStream,
                                  JRExporter exporter, JasperPrint jasperPrint,
                                  String strActionName, String strSubHTML,
                                  String strRequest) throws JRException,
                                                            ServletException,
                                                            IOException {
        try {
            // set the other export params here
            String strHeader = getHeader(strActionName, strRequest);
            String strFooter = getFooter(strSubHTML);
            exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER,
                                  strHeader);
            exporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER,
                                  strFooter);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT,
                                  jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
                                  outputStream);
            //            exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML,
            //                                  "<div class='print'></div>");
            exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
                                  Boolean.FALSE);
            exporter.setParameter(JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
                                  Boolean.FALSE);
            exporter.setParameter(JRHtmlExporterParameter.FLUSH_OUTPUT,
                                  Boolean.TRUE);
            Float zoom = new Float(1.1);
            exporter.setParameter(JRHtmlExporterParameter.ZOOM_RATIO, zoom);
            exporter.exportReport();
        } catch (JRException e) {
            throw new ServletException(e);
        }
    }

    //    private void removeBlankPage(List<JRPrintPage> pages) {
    //
    //        for (Iterator<JRPrintPage> i = pages.iterator(); i.hasNext(); ) {
    //            JRPrintPage page = i.next();
    //            if (page.getElements().size() == 0)
    //                i.remove();
    //        }
    //    }
    // export to format html report

    public void generalMultiHtmlReport(OutputStream outputStream,
                                       JRExporter exporter,
                                       List<JasperPrint> jasperPrint,
                                       String strActionName, String strSubHTML,
                                       String strRequest) throws JRException,
                                                                 ServletException,
                                                                 IOException {
        try {
          // set the other export params here
          String strHeader = getHeader(strActionName, strRequest);
          String strFooter = getFooter(strSubHTML);
          exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER,
                                strHeader);
          exporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER,
                                strFooter);
          exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST,
                                jasperPrint);
          exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
                                outputStream);
                      exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML,"<P CLASS=\"breakhere\">");
//                                            "<div class='print'>-----------------------</div>");
          exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
                                Boolean.FALSE);
          exporter.setParameter(JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
                                Boolean.FALSE);
          exporter.setParameter(JRHtmlExporterParameter.FLUSH_OUTPUT,
                                Boolean.TRUE);
          Float zoom = new Float(1.0);
          exporter.setParameter(JRHtmlExporterParameter.ZOOM_RATIO, zoom);
          exporter.exportReport();
//            // set the other export params here
//            String strHeader = getHeader(strActionName, strRequest);
//            String strFooter = getFooter(strSubHTML);
//            exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER,
//                                  strHeader);
//            exporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER,
//                                  strFooter);
//            exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST,
//                                  jasperPrint);
//            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
//                                  outputStream);
//            exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
//                                  Boolean.FALSE);
//            exporter.setParameter(JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
//                                  Boolean.FALSE);
//            exporter.setParameter(JRHtmlExporterParameter.FLUSH_OUTPUT,
//                                  Boolean.TRUE);
//            //            exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML,
//            //                                  "<div class='print'></div>");
////            exporter.setParameter(JRHtmlExporterParameter.IS_WRAP_BREAK_WORD,
////                                  Boolean.TRUE);
//            exporter.setParameter(JRHtmlExporterParameter.SIZE_UNIT,
//                                  JRHtmlExporterParameter.SIZE_UNIT_PIXEL);
//            Float zoom = new Float(1.1);
//            exporter.setParameter(JRHtmlExporterParameter.ZOOM_RATIO, zoom);
//            exporter.exportReport();
        } catch (JRException e) {
            throw new ServletException(e);
        }
    }

    public void generalDocReport(OutputStream outputStream,
                                 JRExporter exporter,
                                 JasperPrint jasperPrint) throws JRException,
                                                                 IOException,
                                                                 ServletException {
        //        Map imagesMap = new HashMap();
        // set the other export params here
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
        exporter.setParameter(JRDocxExporterParameter.CHARACTER_ENCODING,
                              "UTF-8");
        exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
                              Boolean.FALSE);
        exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML, "");
        Float zoom = new Float(1.2);
        exporter.setParameter(JRHtmlExporterParameter.ZOOM_RATIO, zoom);
        try {
            exporter.exportReport();
        } catch (JRException e) {
            throw new ServletException(e);
        }
    }

    public void generalMultiDocReport(OutputStream outputStream,
                                      JRExporter exporter,
                                      List<JasperPrint> jasperPrints) throws JRException,
                                                                             IOException,
                                                                             ServletException {
        //        Map imagesMap = new HashMap();
        // set the other export params here
        exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST,
                              jasperPrints);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
        exporter.setParameter(JRDocxExporterParameter.CHARACTER_ENCODING,
                              "UTF-8");
        exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
                              Boolean.FALSE);
        exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML,
                              "<div class='print'></div>");
        Float zoom = new Float(1.2);
        exporter.setParameter(JRHtmlExporterParameter.ZOOM_RATIO, zoom);
        try {
            exporter.exportReport();
        } catch (JRException e) {
            throw new ServletException(e);
        }
    }

    public void generalHtmlReport(OutputStream outputStream,
                                  JRExporter exporter,
                                  JasperPrint jasperPrint) throws JRException,
                                                                  ServletException,
                                                                  IOException {
        try {
            // set the other export params here
            exporter.setParameter(JRExporterParameter.JASPER_PRINT,
                                  jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
                                  outputStream);
            exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
                                  Boolean.FALSE);
            exporter.setParameter(JRHtmlExporterParameter.FRAMES_AS_NESTED_TABLES,
                                  Boolean.TRUE);
            exporter.setParameter(JRHtmlExporterParameter.IMAGES_DIR_NAME,
                                  "/report/image");
            exporter.setParameter(JRHtmlExporterParameter.IMAGES_MAP,
                                  new HashMap());

            exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,
                                  "image?image=");
            exporter.setParameter(JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
                                  Boolean.FALSE);
            exporter.setParameter(JRHtmlExporterParameter.FLUSH_OUTPUT,
                                  Boolean.TRUE);
            exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML,
                                  "<div class='print'></div>");
            //                        exporter.setParameter(JRHtmlExporterParameter.IGNORE_PAGE_MARGINS,
            //                                              Boolean.TRUE);
            exporter.setParameter(JRHtmlExporterParameter.IS_WRAP_BREAK_WORD,
                                  Boolean.TRUE);
            exporter.setParameter(JRHtmlExporterParameter.SIZE_UNIT,
                                  JRHtmlExporterParameter.SIZE_UNIT_PIXEL);
            Float zoom = new Float(1.1);
            exporter.setParameter(JRHtmlExporterParameter.ZOOM_RATIO, zoom);
            exporter.exportReport();
        } catch (JRException e) {
            throw new ServletException(e);
        }
    }

  private String getHeader(String strAction, String strRequest) {
      StringBuffer sbHeader = new StringBuffer();
      if (strRequest == null)
          strRequest = "";
      else
          strRequest = strRequest.trim();
      sbHeader.append("<html>");
      sbHeader.append("<script type=\"text/javascript\" charset=\"utf-8\">");
      sbHeader.append("function load(){ ");
      sbHeader.append("divPrint()");
      sbHeader.append("} ");
      sbHeader.append("</script>");
      sbHeader.append("<head><style type=\"text/css\">" +
                      "    a {text-decoration: none}" + "  </style>");
      sbHeader.append("<title></title>");
      sbHeader.append("<script type=\"text/javascript\" src=\"" +
                      AppConstants.NNT_APP_CONTEXT_ROOT +
                      "/styles/js/jquery/1.3.2/jquery.min.js\"></script>");
      sbHeader.append("<script type=\"text/javascript\" src=\"" +
                      AppConstants.NNT_APP_CONTEXT_ROOT +
                      "/styles/js/jquery/1.3.2/jquery.printElement.js\"></script>");
      sbHeader.append("<script type=\"text/javascript\" src=\"" +
                      AppConstants.NNT_APP_CONTEXT_ROOT +
                      "/styles/js/report.js\"></script>");
      sbHeader.append("<link rel=\"stylesheet\" href=\"" +
                      AppConstants.NNT_APP_CONTEXT_ROOT +
                      "/styles/css/report.css\" media=\"screen\" type=\"text/css\" />");
      sbHeader.append("</head>");
      sbHeader.append("<body  onload=\"load()\">");
      //style break page when print
      sbHeader.append("<STYLE TYPE=\"text/css\">P.breakhere {page-break-before: always}</STYLE>");     
      
      sbHeader.append("<form name=\"rptForm\" action=\"rptAction.do\" method=\"post\">");
      sbHeader.append("<div id=\"top-bar\" class=\"top-bar\">");
      sbHeader.append("<div id=\"topbar-inner\" class=\"topbar-inner\">");
      sbHeader.append("<button type=\"button\" style=\"border: 0; background: transparent\" onclick=\"divPrint()\">");
      sbHeader.append("<img src=\"" + AppConstants.NNT_APP_CONTEXT_ROOT +
                      "/styles/images/icon_print.png\" width=\"30\" heght=\"20\" alt=\"In b&#225;o c&#225;o\" />");
      sbHeader.append("</button>");
      sbHeader.append("<button type=\"button\" style=\"border: 0; background: transparent\" onclick=\"submitSaveRpt('" +
                      strAction + "','rtf','" + strRequest + "')\">");
      sbHeader.append("<img src=\"" + AppConstants.NNT_APP_CONTEXT_ROOT +
                      "/styles/images/icon_word.png\" width=\"30\" heght=\"20\" alt=\"Xu&#7845;t b&#225;o c&#225;o theo &#273;&#7883;nh d&#7841;ng word\" />");
      sbHeader.append("</button>");
      sbHeader.append("<button type=\"button\" style=\"border: 0; background: transparent\" onclick=\"submitSaveRpt('" +
                      strAction + "','excel','" + strRequest + "')\">");
      sbHeader.append("<img src=\"" + AppConstants.NNT_APP_CONTEXT_ROOT +
                      "/styles/images/icon_excel.png\" width=\"30\" heght=\"20\" alt=\"Xu&#7845;t b&#225;o c&#225;o theo &#273;&#7883;nh d&#7841;ng excel\" />");
      sbHeader.append("</button>");
      sbHeader.append("<button type=\"button\" style=\"border: 0; background: transparent\" onclick=\"submitSaveRpt('" +
                      strAction + "','pdf','" + strRequest + "')\">");
      sbHeader.append("<img src=\"" + AppConstants.NNT_APP_CONTEXT_ROOT +
                      "/styles/images/icon_pdf.png\" width=\"30\" heght=\"20\" alt=\"Xu&#7845;t b&#225;o c&#225;o theo &#273;&#7883;nh d&#7841;ng pdf\" />");
      sbHeader.append("</button>");

      sbHeader.append("</div></div>");
      sbHeader.append("<div style='padding-top:6px;' id=\"div_print\">");
      return sbHeader.toString();
  }

    public String getFooter(String strSubHTML) {
        StringBuffer sbFooter = new StringBuffer("</div>\n" +
                strSubHTML + "\n</form></body></html>");
        return sbFooter.toString();
    }

    public void closeOutputStream(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.flush();
                outputStream.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void closePrintWriter(PrintWriter printWriter) {
        if (printWriter != null) {
            try {
                printWriter.flush();
                printWriter.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void printReport(JasperPrint jPrint) throws JRException {
        try {
            JasperPrintManager.printReport(jPrint, true);
        } catch (JRException e) {
            throw e;
        }
    }

    public void printWithServices(JasperPrint jasperPrint) throws PrinterException,
                                                                  JRException {

        PrinterJob pj = PrinterJob.getPrinterJob();
        if (pj.printDialog()) {
            PrintRequestAttributeSet printRequestAttributeSet =
                new HashPrintRequestAttributeSet();

            //printRequestAttributeSet.add(OrientationRequested.LANDSCAPE);

            //printRequestAttributeSet.add(MediaSizeName.ISO_A4);

            PrintServiceAttributeSet printServiceAttributeSet =
                PrinterJob.getPrinterJob().getPrintService().getAttributes();

            PrinterName printerName =
                new PrinterName("normal", Locale.getDefault());
            printServiceAttributeSet.add(printerName);


            JRPrintServiceExporter exporterjasper =
                new JRPrintServiceExporter();
            exporterjasper.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET,
                                        printServiceAttributeSet);
            exporterjasper.setParameter(JRExporterParameter.JASPER_PRINT,
                                        jasperPrint);

            exporterjasper.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET,
                                        printRequestAttributeSet);
            exporterjasper.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET,
                                        printServiceAttributeSet);
            exporterjasper.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG,
                                        Boolean.FALSE);
            exporterjasper.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG,
                                        Boolean.TRUE);


            try {
                exporterjasper.exportReport();
            } catch (JRException je) {
                System.out.println("Error printing report:\n" +
                        je.getMessage());
            }
        }
    }

    public void printReportToPage(JasperPrint jPrints) throws JRException {
        try {
            //add all three JasperPrints to the list below
            List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
            boolean add = jasperPrints.add(jPrints);
            if (add) {
                //create an exporter
                JRExporter exporter = new JRPrintServiceExporter();
                //add the JasperPrints to the exporter via the JASPER_PRINT_LIST param
                exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST,
                                      jasperPrints);
                exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG,
                                      Boolean.FALSE);
                exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG,
                                      Boolean.TRUE);
                //this one makes it so that the settings choosen in the first dialog will be applied to the
                //other documents in the list also
                exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG_ONLY_ONCE,
                                      Boolean.TRUE);
                exporter.exportReport();
            }

        } catch (JRException e) {
            throw e;
        }
    }

    public void exportReport(JasperPrint jasperPrint,
                             String strTypePrintAction,
                             HttpServletResponse response, String fileName,
                             String strPathFont, String strActionName,
                             String strSubHTML,
                             String strParameter) throws Exception {

        if (strTypePrintAction != null && !"".equals(strTypePrintAction)) {
            JRExporter exporter = null;
            // Khoi tao JRExporter
            exporter = getExporter(strTypePrintAction.trim());
            // lay font
            OutputStream outputStream = response.getOutputStream();
            if (jasperPrint != null) {
                if (strTypePrintAction.trim().equalsIgnoreCase("pdf")) {
                    try {
                        //                        outputStream = response.getOutputStream();
                        response.setContentType(getMIMEType(strTypePrintAction.trim()));
                        response.setHeader("Content-Disposition",
                                           "attachment; filename=" + fileName +
                                           ".pdf");
                        response.setHeader("Cache-Control", "no-cache");
                        generalPDFReport(outputStream, exporter, strPathFont,
                                         jasperPrint);
                    } catch (Exception ex) {
                        throw ex;
                    } finally {
                        closeOutputStream(outputStream);
                    }
                } else if (strTypePrintAction.trim().equalsIgnoreCase("excel")) {
                    try {
                        //                        outputStream = response.getOutputStream();
                        response.setContentType(getMIMEType(strTypePrintAction.trim()));
                        response.setHeader("Content-Disposition",
                                           "attachment; filename=" + fileName +
                                           ".xls");
                        response.setHeader("Cache-Control", "no-cache");
                        generalExcelReport(outputStream, exporter,
                                           jasperPrint);
                    } catch (Exception ex) {
                        throw ex;
                    } finally {
                        closeOutputStream(outputStream);
                    }
                } else if (strTypePrintAction.trim().equalsIgnoreCase("rtf")) {
                    try {
                        response.setContentType(getMIMEType(strTypePrintAction.trim()));
                        response.setHeader("Content-Disposition",
                                           "inline; filename=" + fileName +
                                           ".doc");
                        //                        outputStream = response.getOutputStream();
                        generalDocReport(outputStream, exporter, jasperPrint);
                    } catch (Exception ex) {
                        throw ex;
                    } finally {
                        closeOutputStream(outputStream);
                    }
                } else {
                    try {
                        exporter = getExporter("HTML");
                        //                        outputStream = response.getOutputStream();
                        response.setContentType("text/html; charset=UTF-8");
                        generalHtmlReport(outputStream, exporter, jasperPrint,
                                          strActionName, strSubHTML,
                                          strParameter);
                    } catch (Exception ex) {
                        throw ex;
                    } finally {
                        closeOutputStream(outputStream);
                    }
                }
            } else {
                throw new Exception("C&#243; l&#7895;i &#7903; file jasper report");
            }
        } else {
            OutputStream outputStream = response.getOutputStream();
            try {
                JRExporter exporter = null;
                exporter = getExporter("HTML");
                response.setContentType("text/html; charset=UTF-8");
                generalHtmlReport(outputStream, exporter, jasperPrint,
                                  strActionName, strSubHTML, strParameter);
            } catch (Exception ex) {
                throw ex;
            } finally {
                closeOutputStream(outputStream);
            }
        }
    }

    public void exportMultiReport(List<JasperPrint> jasperPrint,
                                  String strTypePrintAction,
                                  HttpServletResponse response,
                                  String fileName, String strPathFont,
                                  String strActionName, String strSubHTML,
                                  String strParameter) throws Exception {

        if (strTypePrintAction != null && !"".equals(strTypePrintAction)) {
            JRExporter exporter = null;
            // Khoi tao JRExporter
            exporter = getExporter(strTypePrintAction.trim());
            // lay font
            OutputStream outputStream = response.getOutputStream();
            if (jasperPrint != null) {
                if (strTypePrintAction.trim().equalsIgnoreCase("pdf")) {
                    try {
                        //                        outputStream = response.getOutputStream();
                        response.setContentType(getMIMEType(strTypePrintAction.trim()));
                        response.setHeader("Content-Disposition",
                                           "attachment; filename=" + fileName +
                                           ".pdf");
                        response.setHeader("Cache-Control", "no-cache");
                        generalMultiPDFReport(outputStream, exporter,
                                              jasperPrint);
                    } catch (Exception ex) {
                        throw ex;
                    } finally {
                        closeOutputStream(outputStream);
                    }
                } else if (strTypePrintAction.trim().equalsIgnoreCase("excel")) {
                    try {
                        //                        outputStream = response.getOutputStream();
                        response.setContentType(getMIMEType(strTypePrintAction.trim()));
                        response.setHeader("Content-Disposition",
                                           "attachment; filename=" + fileName +
                                           ".xls");
                        response.setHeader("Cache-Control", "no-cache");
                        generalMultiExcelReport(outputStream, exporter,
                                                jasperPrint);
                    } catch (Exception ex) {
                        throw ex;
                    } finally {
                        closeOutputStream(outputStream);
                    }
                } else if (strTypePrintAction.trim().equalsIgnoreCase("rtf")) {
                    try {
                        response.setContentType(getMIMEType(strTypePrintAction.trim()));
                        response.setHeader("Content-Disposition",
                                           "inline; filename=" + fileName +
                                           ".doc");
                        //                        outputStream = response.getOutputStream();
                        generalMultiDocReport(outputStream, exporter,
                                              jasperPrint);
                    } catch (Exception ex) {
                        throw ex;
                    } finally {
                        closeOutputStream(outputStream);
                    }
                } else {
                    try {
                        exporter = getExporter("HTML");
                        //                        outputStream = response.getOutputStream();
                        response.setContentType("text/html; charset=UTF-8");
                        generalMultiHtmlReport(outputStream, exporter,
                                               jasperPrint, strActionName,
                                               strSubHTML, strParameter);
                    } catch (Exception ex) {
                        throw ex;
                    } finally {
                        closeOutputStream(outputStream);
                    }
                }
            } else {
                throw new Exception("C&#243; l&#7895;i &#7903; file jasper report");
            }
        } else {
            OutputStream outputStream = response.getOutputStream();
            try {
                JRExporter exporter = null;
                exporter = getExporter("HTML");
                response.setContentType("text/html; charset=UTF-8");
                generalMultiHtmlReport(outputStream, exporter, jasperPrint,
                                       strActionName, strSubHTML,
                                       strParameter);
            } catch (Exception ex) {
                throw ex;
            } finally {
                closeOutputStream(outputStream);
            }
        }
    }
}

