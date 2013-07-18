package com.form.admin.apps;

import com.form.FSeed;

public class FApp extends FSeed
{
    private int id;
    private String code;
    private String name;
    private String link;
    private int block;
    private String className;
    private int idSwap;
    
  public FApp()
  {
  }
    public int getId() {   return id;  }    public void setId(int id)  { this.id = id;  }
    public String getCode() {   return code;  }    public void setCode(String code)  { this.code = code;  }
    public String getName() { return name; } public void setName(String name){ this.name = name; }
    public String getLink() { return link; } public void setLink(String link){ this.link = link; }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getIdSwap() {
        return idSwap;
    }

    public void setIdSwap(int idSwap) {
        this.idSwap = idSwap;
    }
}
