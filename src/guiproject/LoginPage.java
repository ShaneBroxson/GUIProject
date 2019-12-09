package guiproject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Start Scene for Login Page and Authentication.
 *
 * @author Shane Broxson
 * @file LoginPage.java
 */
public class LoginPage {

  @FXML private TextField userName;
  @FXML private PasswordField passWord;
  @FXML private Label failedLogin;
  private static final String JDBC_DRIVER = "org.h2.Driver";
  private static final String DB_URL = "jdbc:h2:./res/GUI_DB";
  private Connection conn = null;
  private Statement stmt = null;

  /** Method run at start of program. */
  public void initialize() {
    failedLogin.setVisible(false);
  }

  /**
   * Checks if login matches database, if it does changes scene to ProductionTracker.
   *
   * @param event ChangeScene event
   */
  @FXML
  void loginBtn(ActionEvent event) throws SQLException {
    String userName = this.userName.getText();
    String passWord = this.passWord.getText();
    // check to saved database match
    try {
      Class.forName(JDBC_DRIVER);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    try {
      conn = DriverManager.getConnection(DB_URL);
      stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM EMPLOYEE");
      while (rs.next()) {
        //  System.out.println(rs.getString(2));
        if (rs.getString(2).equals(userName) && rs.getString(4).equals(reverseString(passWord))) {
          boolean management = rs.getBoolean(5);
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource("../Resources/GUIProject.fxml"));
          try {
            loader.load();
          } catch (IOException e) {
            e.printStackTrace();
          }
          Controller c = loader.getController();
          c.sendUserInfo(userName, management);
          Parent contParent =
              FXMLLoader.load(getClass().getResource("../Resources/GUIProject.fxml"));
          Scene contScene = new Scene(contParent);

          Stage controllerWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
          controllerWindow.setScene(contScene);
          controllerWindow.show();
        }
      }
      failedLogin.setVisible(true);
    } catch (SQLException | IOException ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Reverses inputted Password.
   *
   * @param id Inputted Password
   * @return Encrypted Testcase
   */
  private static String reverseString(String id) {
    if (id.isEmpty()) {
      return id;
    }
    // Calling Function Recursively
    return reverseString(id.substring(1)) + id.charAt(0);
  }
}
