package com.form.admin.groups;

import com.form.FBeans;
import com.form.FSeed;
import com.inf.admin.IConstantsAdmin;

public class FGroup extends FSeed
{
    private int id;
    private String code;
    private String name;
    private int parentID;
    private int role=1;
    private int privilege=1;
    private int changePassword;
    private String dateCreate;
    private String selected;
    private String spase;
    private String app="";
    private String[] menus;
    private FBeans tranfers;
  public FGroup()
  {
  }
    public int getId() {   return id;  }    public void setId(int id)  { this.id = id;  }
    public String getCode() {   return code;  }    public void setCode(String code)  { this.code = code;  }
    public String getName() { return name; } public void setName(String name){ this.name = name; }
    public int getParentID() {   return parentID;  }    public void setParentID(int parentID)  { this.parentID = parentID;  }
    public int getRole() {   return role;  }    public void setRole(int role)  { this.role = role;  }
    public void setroles(int[] roles)  { 
        this.role=0;
        if(roles!=null){
            for(int i=0;i<roles.length;i++){
                this.role+=roles[i]    ;
            }
        }
    }
    public int getPrivilege() {   return privilege;  }    public void setPrivilege(int privilege)  { this.privilege = privilege;  }
    public void setPrivileges(int[] privileges)  { 
        this.privilege=0;
        if(privileges!=null){
            for(int i=0;i<privileges.length;i++){
                this.privilege+=privileges[i]    ;
            }
        }
    }
    public String getDateCreate() { return dateCreate; } public void setDateCreate(String dateCreate){ this.dateCreate = dateCreate; }
    public String getApp() {   return app;  }   public void setApp(String app)  { this.app = app; }
    public void setApps(int[] apps)  { 
         this.app="_";
         if(apps!=null){
             for(int i=0;i<apps.length;i++){
                 this.app+= IConstantsAdmin.APP_SEPARATE_ + apps[i] + IConstantsAdmin.APP_SEPARATE_;
             }
         }
     }

    public int getChangePassword() {
        return changePassword;
    }

    public void setChangePassword(int changePassword) {
        this.changePassword = changePassword;
    }

   
    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String getSpase() {
        return spase;
    }

    public void setSpase(String spase) {
        this.spase = spase;
    }

    public String[] getMenus() {
        return menus;
    }

    public void setMenus(String[] menus) {
        this.menus = menus;
    }

    public FBeans getTranfers() {
        return tranfers;
    }

    public void setTranfers(FBeans tranfers) {
        this.tranfers = tranfers;
    }
}
