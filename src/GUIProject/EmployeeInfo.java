package GUIProject;

public class EmployeeInfo {
    private String name;
    private String username;
    private String email;
    private Boolean management;

    EmployeeInfo(String name, String username, String email, Boolean management) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.management = management;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getManagement() {
        return management;
    }

    public void setManagement(Boolean management) {
        this.management = management;
    }
}
