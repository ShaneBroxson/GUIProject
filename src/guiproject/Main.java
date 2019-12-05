package guiproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main file. GUI Application to track production.
 *
 * @author Shane Broxson
 * @file Main.java
 */
public class Main extends Application {

  /**
   * Initializes GUI interface and set resource location.
   *
   * @param primaryStage Initialize Primary Scene for GUI
   * @throws Exception Unable to find Correct FXML file
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("../Resources/LoginPage.fxml"));
    primaryStage.setTitle("GUI Program");
    primaryStage.setScene(new Scene(root, 600, 400));
    primaryStage.show();
  }

  /**
   * Launches the Program.
   *
   * @param args Starts program.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
