package GUIProject;

public class LoggedEmployee {
  private String userName;
  private Boolean isManagement;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Boolean getManagement() {
    return isManagement;
  }

  public void setManagement(Boolean management) {
    isManagement = management;
  }

  LoggedEmployee(String userName, Boolean isManagement) {
    this.userName = userName;
    this.isManagement = isManagement;
  }
}
