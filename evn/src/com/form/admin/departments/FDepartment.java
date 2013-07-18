package com.form.admin.departments;

import com.form.FBeans;
import com.form.FSeed;

public class FDepartment extends FSeed
{
    private int id;
    private String code;
    private String name;
    private String space;
    private int parentID;
    private int level;
    private FBeans tranfers;
    
  public FDepartment()
  {
  }
    public int getId() {   return id;  }    public void setId(int id)  { this.id = id;  }
    public String getCode() {   return code;  }    public void setCode(String code)  { this.code = code;  }
    public String getName() { return name; } public void setName(String name){ this.name = name; }
    public int getParentID() {   return parentID;  }    public void setParentID(int parentID)  { this.parentID = parentID;  }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public FBeans getTranfers() {
        return tranfers;
    }

    public void setTranfers(FBeans tranfers) {
        this.tranfers = tranfers;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
