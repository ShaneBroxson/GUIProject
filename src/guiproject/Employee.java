package guiproject;

/**
 * Employee Username and Password Generation and Validation.
 *
 * @author Shane Broxson
 * @file Employee.java
 */
class Employee {
  private String name;
  private String username;
  private String password;
  private String email;
  private Boolean management;

  /**
   * Employee subclass for checking new employee information and setting it if valid.
   *
   * @param name Employee Name %f(first last)
   * @param password Imputed Employee Password
   */
  Employee(String name, String password, Boolean management) {
    boolean passCheck = false;
    // isValidPassword(password);
    passCheck = isValidPassword(password);
    if (passCheck) {
      this.password = password;
    } else {
      this.password = "pw";
    }

    if (checkName(name)) {
      setEmail(name);
      setUsername(name);
      this.name = name;
      this.management = management;
    } else {
      this.username = "default";
      this.email = "user@oracleacademy.Test";
      this.name = name;
    }
  }

  /**
   * Overrides toString() method for Employee class.
   *
   * @return Overrides toString method for Employee
   */
  public String toString() {
    return "Employee Details\n"
        + "Name : "
        + this.name
        + "\nUsername : "
        + this.username
        + "\nEmail : "
        + this.email
        + "\nInitial Password : "
        + this.password;
  }

  /**
   * Creates Username for Employee based on their full name.
   *
   * @param name Employee Name %f(first last)
   */
  private void setUsername(String name) {
    // this.username = name.replaceAll(" ", ".");
    StringBuilder sb = new StringBuilder(name.toLowerCase());
    for (int i = 0; i < sb.length(); i++) {
      String s = " ";
      char c = s.charAt(0);
      if (sb.charAt(i) == c) {
        sb.delete(1, i + 1);
      }
    }
    this.username = sb.toString();
  }

  /**
   * Checks if the name was inputted in the correct format.
   *
   * @param name Employee Name %f(first last)
   * @return If name is inputted in correct format returns true, otherwise false
   */
  private boolean checkName(String name) {
    for (int i = 0; i < name.length(); i++) {
      char ch = name.charAt(i);
      if (Character.isWhitespace(ch)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Sets the email based on the inputted Employee full name.
   *
   * @param name Employee Name %f(first last)
   */
  private void setEmail(String name) {
    StringBuilder sb = new StringBuilder(name);

    String s = " ";
    char c = s.charAt(0);

    int i;
    for (i = 0; i < sb.length(); i++) {
      if (sb.charAt(i) == c) {
        sb.replace(i, i + 1, ".");
        String change = sb.toString().toLowerCase();
        sb.replace(0, sb.length(), change);
        // sb.delete(1,i+1);
        break;
      }
    }
    sb.append("@oracleacademy.Test");
    this.email = sb.toString();
  }

  /**
   * Checks if the inputted Employee password is valid.
   *
   * @param password Employee Password
   * @return If password meets security requirements then returns true, otherwise returns false
   */
  private boolean isValidPassword(String password) {
    boolean capFlag = false;
    boolean lowerFlag = false;
    boolean specialFlag = false;
    for (int i = 0; i < password.length(); i++) {
      char ch = password.charAt(i);
      if (Character.isUpperCase(ch)) {
        capFlag = true;
      }
      if (Character.isLowerCase(ch)) {
        lowerFlag = true;
      }
      if (!password.matches("[^a-zA-Z0-9 ]")) {
        specialFlag = true;
      }
      if (capFlag && lowerFlag && specialFlag) {
        return true;
      }
    }
    return false;
  }

  /**
   * Return for the Employee full name.
   *
   * @return Full Name
   */
  public String getName() {
    return name;
  }

  /**
   * Return for the Employee username.
   *
   * @return Generated Username
   */
  public String getUsername() {
    return username;
  }

  /**
   * Return for the Employee password.
   *
   * @return User password
   */
  public String getPassword() {
    return password;
  }

  /**
   * Return for the Employee email.
   *
   * @return User email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Returns whether or not a Employee is a manager.
   *
   * @return Management status
   */
  public Boolean getManagement() {
    return management;
  }
}
