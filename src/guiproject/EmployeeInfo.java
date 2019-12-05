package guiproject;

/**
 * Object for storing information from Employee Table in DB.
 *
 * @author Shane Broxson
 * @file EmployeeInfo.java
 */
public class EmployeeInfo {
  private String name;
  private String username;
  private String email;
  private Boolean management;

  /**
   * EmployeeInfo subclass containing parameters of an Employee.
   *
   * @param name Full name of Employee
   * @param username Username of Employee
   * @param email Email of Employee
   * @param management Determines manager status
   */
  EmployeeInfo(String name, String username, String email, Boolean management) {
    this.name = name;
    this.username = username;
    this.email = email;
    this.management = management;
  }

  /**
   * Returns the Employee name.
   *
   * @return Employee name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the Employee name.
   *
   * @param name Sets Employee name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Returns Employee username.
   *
   * @return Employee username
   */
  public String getUsername() {
    return username;
  }

  /**
   * Sets the Employee username.
   *
   * @param username Sets Employee username
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Returns Employee email.
   *
   * @return Employee email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets Employee email.
   *
   * @param email Sets Employee email
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Returns if Employee is a manager.
   *
   * @return Management status
   */
  public Boolean getManagement() {
    return management;
  }

  /**
   * Sets if Employee is manager.
   *
   * @param management Sets Management status
   */
  public void setManagement(Boolean management) {
    this.management = management;
  }
}
