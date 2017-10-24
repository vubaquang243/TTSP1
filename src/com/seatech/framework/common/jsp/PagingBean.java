package com.seatech.framework.common.jsp;

public class PagingBean {
    public PagingBean() {       
    }
    public void setPaging(int numberOfRow, int currentPage, int rowOnPage){
      this.numberOfRow = numberOfRow;
      this.currentPage = currentPage;
      this.rowOnPage = rowOnPage;     
    }
    private int numberOfRow;
    private int currentPage;
    private int rowOnPage;

    public void setNumberOfRow(int numberOfRow) {
        this.numberOfRow = numberOfRow;
    }

    public int getNumberOfRow() {
        return numberOfRow;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setRowOnPage(int rowOnPage) {
        this.rowOnPage = rowOnPage;
    }

    public int getRowOnPage() {
        return rowOnPage;
    }
}
