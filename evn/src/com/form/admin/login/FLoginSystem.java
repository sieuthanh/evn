package com.form.admin.login;
import com.form.FSeed;

public class FLoginSystem  extends FSeed
{
    private int id;
    private int appID;
    private String username;
    private String password;
    private String password_;
    private String oldpassword;
    private int active;
    private int daysLive;
    private int language;
    
    
    
  
  public FLoginSystem()
  {
  }
    public int getId() {   return id;  }    public void setId(int id)  { this.id = id;  }
    public int getAppID() {   return appID;  }    public void setAppID(int appID)  { this.appID = appID;  }
    public String getUsername() { return username; } public void setUsername(String username){ this.username = username; }
    public String getPassword() { return password; } public void setPassword(String password){ this.password = password; }
    public int getActive() {   return active;  }    public void setActive(int active)  { this.active = active;  }
    public int getDaysLive() {   return daysLive;  }    public void setDaysLive(int daysLive)  { this.daysLive = daysLive;  }

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public String getPassword_() {
        return password_;
    }

    public void setPassword_(String password_) {
        this.password_ = password_;
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }
}
