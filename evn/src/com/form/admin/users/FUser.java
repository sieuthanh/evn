
package com.form.admin.users;

import com.form.FBeans;
import com.form.FSeed;

import com.inf.admin.IConstantsAdmin;

public class FUser  extends FSeed
{
    private int id;
    private int sms;
    private String nameUser;
    private String username;
    private String password;
    private String fullName;
    private String picture;
    private String email;
    private String phone;
    private String address;
    private String description;
    private String dateCreate;
    private String datePassword;
    private String dateLogin;
    private int sex;
    private int departmentID;
    private String departmentName;
    private int role = 1;
    private int privilege=1;
    private int active=1;
    private int period;
    private int groupID;
    private String groupName;
    private FBeans tranfers;
    private String app="";
    private int changePassword;
    private int pageIndex=1;
    private int[] groupsID;
    private String[] menus;
    private String checked ;
    private String area="" ;

  /**
   * Construct an instance of loginSystem
   */
  public FUser()
  {
  }
    public int getId() {   return id;  }    public void setId(int id)  { this.id = id;  }
    public String getUsername() { return username; } public void setUsername(String username){ this.username = username; }
    public String getPassword() { return password; } public void setPassword(String password){ this.password = password; }
    public String getFullName() { return fullName; } public void setFullName(String fullName){ this.fullName = fullName; }
    public String getPicture() {   return picture;  }    public void setPicture(String picture)  { this.picture = picture;  }
    public String getEmail() {   return email;  }    public void setEmail(String email)  { this.email = email;  }
    public String getPhone() { return phone; } public void setPhone(String phone){ this.phone = phone; }
    public String getAddress() { return address; } public void setAddress(String address){ this.address = address; }
    public String getDescription() {   return description;  }    public void setDescription(String description)  { this.description = description;  }
    public String getDateCreate() {   return dateCreate;  }    public void setDateCreate(String dateCreate)  { this.dateCreate = dateCreate;  }
    public String getDateLogin() {   return dateLogin;  }    public void setDateLogin(String dateLogin)  { this.dateLogin = dateLogin;  }
    public String getDatePassword() {   return datePassword;  }    public void setDatePassword(String datePassword)  { this.datePassword = datePassword;  }
    public int getSex() {   return sex;  }    public void setSex(int sex)  { this.sex = sex;  }
    public int getDepartmentID() {   return departmentID;  }    public void setDepartmentID(int departmentID)  { this.departmentID = departmentID;  }
    public String getDepartmentName() { return departmentName; } public void setDepartmentName(String departmentName){ this.departmentName = departmentName; }
    public int getRole() {   return role;  }    public void setRole(int role)  { this.role = role;  }
    public void setroles(int[] roles)  { 
        this.role=0;
        if(roles!=null){
            for(int i=0;i<roles.length;i++){
                this.role+=roles[i]    ;
            }
        }
    }
    public int getPrivilege() {   return privilege;  }   public void setPrivilege(int privilege)  { this.privilege = privilege; }
    public void setPrivileges(int[] privileges)  { 
         this.privilege=0;
         if(privileges!=null){
             for(int i=0;i<privileges.length;i++){
                 this.privilege+=privileges[i]    ;
             }
         }
     }
    public int getActive() {   return active;  }    public void setActive(int active)  { this.active = active;  }
    public int getPeriod() {   return period;  }    public void setPeriod(int period)  { this.period = period;  }
    
    public String getGroupName() { return groupName; } public void setGroupName(String groupName){ this.groupName = groupName; }
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

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

   

    public int[] getGroupsID() {
        return groupsID;
    }

    public void setGroupsID(int[] groupsID) {
        this.groupsID = groupsID;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }


    public String[] getMenus() {
        return menus;
    }

    public void setMenus(String[] menus) {
        this.menus = menus;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public FBeans getTranfers() {
        return tranfers;
    }

    public void setTranfers(FBeans tranfers) {
        this.tranfers = tranfers;
    }

    public int getSms() {
        return sms;
    }

    public void setSms(int sms) {
        this.sms = sms;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
