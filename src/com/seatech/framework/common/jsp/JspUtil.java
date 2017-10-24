package com.seatech.framework.common.jsp;


public class JspUtil {
    public static String pagingHTML(PagingBean pagingBean, String color) {
        int numberOfRow = pagingBean.getNumberOfRow();
        int currentPage = pagingBean.getCurrentPage();
        int rowOnPage = pagingBean.getRowOnPage();
        int numberOfPage;
        int prePage;
        int nextPage;
        StringBuilder strHTML = new StringBuilder("");
        if (numberOfRow % rowOnPage != 0) {
            numberOfPage = numberOfRow / rowOnPage + 1;
        } else {
            numberOfPage = numberOfRow / rowOnPage;
        }
        if (numberOfPage == 1) {
            return "";
        } else {
            prePage = currentPage - 1;
            nextPage = currentPage + 1;
            strHTML.append(" <a onclick=\"goPage(" + prePage +
                           ")\" style=\"cursor: hand; color: " + color +
                           "; text-decoration: none\" ");
            strHTML.append(" onmouseover=\"this.style.color='#0000ff';this.style.textDecoration = 'underline'\" ");
            strHTML.append(" onmouseout=\"this.style.color='" + color +
                           "';this.style.textDecoration = 'none'\">\n << Trang tr&#432;&#7899;c\n </a>&nbsp;|&nbsp;\n ");
            strHTML.append(" Trang <select onchange=\"goPage(this.value)\" style=\"width: 45px; font-family: Tahoma; font-size:8pt\">\n");
            for (int i = 1; i <= numberOfPage; ++i) {
                strHTML.append(" <option value=\"" + i + "\">" + i +
                               "</option>\n");
            }
            strHTML.append("</select>\n");

            if (currentPage < numberOfPage) {
                strHTML.append(" &nbsp;|&nbsp;<a onclick=\"goPage(" +
                               nextPage + ")\" style=\"cursor: hand; color: " +
                               color + "; text-decoration: none\" ");
                strHTML.append(" onmouseover=\"this.style.color='#0000ff';this.style.textDecoration = 'underline'\" ");
                strHTML.append(" onmouseout=\"this.style.color='" + color +
                               "';this.style.textDecoration = 'none'\">\n");
                strHTML.append(" Trang ti&#7871;p >>\n");
                strHTML.append(" </a>\n");
            }
            String temp =
                strHTML.toString().replace("value=\"" + currentPage + "\"",
                                           "value=\"" + currentPage +
                                           "\" selected=\"selected\"");
            return temp;
        }
    }
}
