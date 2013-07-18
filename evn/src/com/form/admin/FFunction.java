package com.form.admin;
import com.form.FSeed;

public class FFunction extends FSeed
{
    private int id;
    private int pageIndex=1;
    private int type;
    private String nameTable;
    private String columnName;
    private String sqlWhere;
    private int thread1;
    private int thread2;
    
  public FFunction()
  {
  }
    public int getId() {   return id;  }    public void setId(int id)  { this.id = id;  }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getNameTable() {
        return nameTable;
    }

    public void setNameTable(String nameTable) {
        this.nameTable = nameTable;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getSqlWhere() {
        return sqlWhere;
    }

    public void setSqlWhere(String sqlWhere) {
        this.sqlWhere = sqlWhere;
    }

    public int getThread1() {
        return thread1;
    }

    public void setThread1(int thread1) {
        this.thread1 = thread1;
    }

    public int getThread2() {
        return thread2;
    }

    public void setThread2(int thread2) {
        this.thread2 = thread2;
    }
}
