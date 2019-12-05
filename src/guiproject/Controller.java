package guiproject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * Controller class for Main fxml file.
 *
 * @author Shane Broxson
 * @file Controller.java
 */
public class Controller {

  @FXML private TableView<ProductionRecord> productionLog;
  @FXML private TableView<Product> productChoice;
  @FXML private ComboBox<Integer> quantityComboBox;
  @FXML private TextField manufacturerInput;
  @FXML private TextField productNameInput;
  @FXML private ChoiceBox<String> itemTypeChoiceBox;
  @FXML private TableView<Product> productsTable;
  @FXML private Tab managerTab;
  @FXML private Label notManager;
  @FXML private TableView<EmployeeInfo> employeeTable;
  @FXML private TextField fullName;
  @FXML private PasswordField newPass;
  @FXML private Label genUser;
  @FXML private Label genEmail;
  @FXML private Label generatedUsername;
  @FXML private Label generatedEmail;
  @FXML private CheckBox managerAccount;
  @FXML private Label incorrectFormat;
  @FXML private Label addProductError;
  @FXML private Label recordProductionError;
  @FXML private Label productionRecorded;
  @FXML private Label addProductSuccess;
  @FXML private Label noProductSelected;
  static LoggedEmployee log_emp;
  private final String jdbcDriver = "org.h2.Driver";
  private final String dbUrl = "jdbc:h2:./res/GUI_DB";
  // create a password and load from file
  // move h2 file to res folder and change dependencies
  // find out problem with showing new dates
  // create a remove or filter ability with tables

  private Connection conn = null;
  private Statement stmt = null;

  /** Runs on initial startup of Controller. */
  public void initialize() {
    genUser.setVisible(false);
    genEmail.setVisible(false);
    generatedUsername.setVisible(false);
    generatedEmail.setVisible(false);
    managerTab.setDisable(true);
    notManager.setVisible(false);
    incorrectFormat.setVisible(false);
    addProductError.setVisible(false);
    recordProductionError.setVisible(false);
    productionRecorded.setVisible(false);
    addProductSuccess.setVisible(false);
    noProductSelected.setVisible(false);
    // Populates quantity_comboBox in the produce tab of GUI.
    for (int i = 1; i <= 10; i++) {
      quantityComboBox.getItems().add(i);
    }
    // Adds Item Types to ChoiceBox
    for (ItemType items : ItemType.values()) {
      itemTypeChoiceBox.getItems().add(items.getType());
    }
    // Combobox properties
    quantityComboBox.getSelectionModel().selectFirst();
    quantityComboBox.setEditable(true);

    // Initialize Main Observable Lists
    ObservableList<Product> prodTableList = productChoice.getItems();
    ObservableList<Product> prodTableList2 = productsTable.getItems();
    ObservableList<ProductionRecord> productionRecord = productionLog.getItems();
    ObservableList<EmployeeInfo> employeeList = employeeTable.getItems();

    // Connect to Database
    try {
      Class.forName(jdbcDriver);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    try {
      conn = DriverManager.getConnection(dbUrl);
      stmt = conn.createStatement();

      // Create Table Columns for Produce Tab in GUI
      TableColumn<Product, Integer> colId = new TableColumn<>("ID");
      colId.setCellValueFactory(new PropertyValueFactory<>("id"));
      colId.setMinWidth(54);
      TableColumn<Product, String> colName = new TableColumn<>("Name");
      colName.setCellValueFactory(new PropertyValueFactory<>("name"));
      colName.setMinWidth(100);
      TableColumn<Product, String> colType = new TableColumn<>("Type");
      colType.setCellValueFactory(new PropertyValueFactory<>("type"));
      colType.setMinWidth(100);
      TableColumn<Product, String> colMan = new TableColumn<>("Manufacturer");
      colMan.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
      colMan.setMinWidth(150);
      productChoice.getColumns().addAll(colId, colName, colType, colMan);

      // Create Table Columns for Product Line in GUI
      TableColumn<Product, Integer> colId2 = new TableColumn<>("ID");
      colId2.setCellValueFactory(new PropertyValueFactory<>("id"));
      colId2.setMinWidth(50);
      colId2.setPrefWidth(50);
      TableColumn<Product, String> colName2 = new TableColumn<>("Name");
      colName2.setCellValueFactory(new PropertyValueFactory<>("name"));
      colName2.setMinWidth(80);
      TableColumn<Product, String> colType2 = new TableColumn<>("Type");
      colType2.setCellValueFactory(new PropertyValueFactory<>("type"));
      colType2.setMinWidth(100);
      TableColumn<Product, String> colMan2 = new TableColumn<>("Manufacturer");
      colMan2.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
      colMan2.setMinWidth(140);
      productsTable.getColumns().addAll(colId2, colName2, colType2, colMan2);

      // Create Table Columns for Production Log in GUI
      TableColumn<ProductionRecord, Integer> colProdNum = new TableColumn<>("Production Number");
      colProdNum.setCellValueFactory(new PropertyValueFactory<>("ProductionNum"));
      TableColumn<ProductionRecord, Integer> colProdID = new TableColumn<>("Product ID");
      colProdID.setCellValueFactory(new PropertyValueFactory<>("ProductID"));
      TableColumn<ProductionRecord, String> colSerialNum = new TableColumn<>("Serial Number");
      colSerialNum.setCellValueFactory(new PropertyValueFactory<>("SerialNum"));
      TableColumn<ProductionRecord, Timestamp> colCurrentDate = new TableColumn<>("Date Produced");
      colCurrentDate.setCellValueFactory(new PropertyValueFactory<>("ProdDate"));
      TableColumn<ProductionRecord, String> colEmp = new TableColumn<>("Employee");
      colEmp.setCellValueFactory(new PropertyValueFactory<>("employee"));
      productionLog
          .getColumns()
          .addAll(colProdNum, colProdID, colSerialNum, colCurrentDate, colEmp);

      // Create Table Columns for Employees In Management tab of GUI
      TableColumn<EmployeeInfo, String> colUname = new TableColumn<>("Employee Name");
      colUname.setCellValueFactory(new PropertyValueFactory<>("name"));
      TableColumn<EmployeeInfo, String> colUsername = new TableColumn<>("Username");
      colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
      TableColumn<EmployeeInfo, String> colEmail = new TableColumn<>("Email");
      colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
      TableColumn<EmployeeInfo, Boolean> colIsManagement = new TableColumn<>("Manager");
      colIsManagement.setCellValueFactory(new PropertyValueFactory<>("management"));
      employeeTable.getColumns().addAll(colUname, colUsername, colEmail, colIsManagement);

      stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCT");
      while (rs.next()) {

        prodTableList.add(
            new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
        prodTableList2.add(
            new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
      }

      stmt = conn.createStatement();
      rs = stmt.executeQuery("SELECT * FROM PRODUCTIONRECORD");
      while (rs.next()) {
        productionRecord.add(
            new ProductionRecord(
                rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getTimestamp(4), rs.getString(5)));
      }

      stmt = conn.createStatement();
      rs = stmt.executeQuery("SELECT NAME, USERNAME , EMAIL , MANAGEMENT FROM EMPLOYEE");
      while (rs.next()) {
        //        System.out.println(rs.getString(1));
        employeeList.add(
            new EmployeeInfo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getBoolean(4)));
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  @FXML
  void generated_info(ActionEvent event) {
    String firstName = fullName.getText();
    String passWord = newPass.getText();
    Boolean manager = managerAccount.isSelected();
    Employee employee = new Employee(firstName, passWord, manager);
    if (employee.getUsername().equals("default") || employee.getPassword().equals("pw")) {
      incorrectFormat.setVisible(true);
      genEmail.setVisible(false);
      generatedEmail.setVisible(false);
      genUser.setVisible(false);
      generatedUsername.setVisible(false);
    } else {
      incorrectFormat.setVisible(false);
      generatedUsername.setText(employee.getUsername());
      generatedUsername.setVisible(true);
      genUser.setVisible(true);
      generatedEmail.setText(employee.getEmail());
      generatedEmail.setVisible(true);
      genEmail.setVisible(true);
      String usernameCheck = employee.getUsername();
      int nameCount = 0;
      try {
        Class.forName(jdbcDriver);
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
      try {
        conn = DriverManager.getConnection(dbUrl);
        stmt = conn.createStatement();
        ResultSet rs =
            stmt.executeQuery("SELECT NAME, USERNAME , EMAIL , MANAGEMENT FROM EMPLOYEE");
        while (rs.next()) {
          if (rs.getString(2).contains(usernameCheck)) {
            nameCount += 1;
          }
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
      try {
        String username = employee.getUsername();
        if (nameCount != 0) {
          username += Integer.toString(nameCount);
        }
        String tempPass = employee.getPassword();
        String sql =
            "INSERT INTO EMPLOYEE (NAME, USERNAME "
                + ", EMAIL , PASSWORD , MANAGEMENT) VALUES (?, ?, ?, ?, ?)";
        try {
          PreparedStatement stmtp = conn.prepareStatement(sql);
          stmtp.setString(1, employee.getName());
          stmtp.setString(2, username);
          stmtp.setString(3, employee.getEmail());
          stmtp.setString(4, reverseString(tempPass));
          stmtp.setBoolean(5, employee.getManagement());
          stmtp.executeUpdate();
          stmtp.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      } catch (NullPointerException ex) {
        ex.printStackTrace();
      }
      try {
        Class.forName(jdbcDriver);
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
      try {
        conn = DriverManager.getConnection(dbUrl);
        stmt = conn.createStatement();
        ResultSet rs =
            stmt.executeQuery("SELECT NAME, USERNAME , EMAIL , MANAGEMENT FROM EMPLOYEE");
        while (rs.next()) {
          if (rs.last()) {
            ObservableList<EmployeeInfo> employeeList = employeeTable.getItems();
            employeeList.add(
                new EmployeeInfo(
                    rs.getString(1), rs.getString(2), rs.getString(3), rs.getBoolean(4)));
          }
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Reverses inputted password for some security.
   *
   * @param id Inputted Password
   * @return Encrypted Password
   */
  private static String reverseString(String id) {
    if (id.isEmpty()) {
      System.out.println("String is Empty");
      return id;
    }
    // Calling Function Recursively
    return reverseString(id.substring(1)) + id.charAt(0);
  }

  /**
   * Pulls information sent from LoginPage.
   *
   * @param userName Logged in Users Username
   * @param isManagement Determines if Logged In user is Management
   */
  void sendUserInfo(String userName, Boolean isManagement) {
    log_emp = new LoggedEmployee(userName, isManagement);
  }

  @FXML
  void managementAccess(ActionEvent event) {
    if (log_emp.getManagement()) {
      managerTab.setDisable(false);
    } else {
      notManager.setVisible(true);
    }
  }

  /** Adds product to available products to produce. */
  public void addProduct() {
    String manInput = manufacturerInput.getText();
    String prodNameInput = productNameInput.getText();
    String itemType = itemTypeChoiceBox.getValue();
    if (manInput == null
        || prodNameInput == null
        || itemType == null
        || manInput.equals("")
        || prodNameInput.equals("")
        || itemType.equals("")) {
      addProductError.setVisible(true);
      productionRecorded.setVisible(false);
      recordProductionError.setVisible(false);
      addProductSuccess.setVisible(false);
    } else {
      addProductSuccess.setVisible(true);
      addProductError.setVisible(false);
      productionRecorded.setVisible(false);
      recordProductionError.setVisible(false);
      try {
        String sql = "INSERT INTO PRODUCT (NAME, TYPE , MANUFACTURER) VALUES (?, ? , ?)";
        try {
          PreparedStatement pstmt = conn.prepareStatement(sql);
          pstmt.setString(1, prodNameInput);
          pstmt.setString(2, itemType);
          pstmt.setString(3, manInput);
          pstmt.executeUpdate();
          pstmt.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      } catch (NullPointerException ex) {
        ex.printStackTrace();
      }
      try {
        Class.forName(jdbcDriver);
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
      try {
        conn = DriverManager.getConnection(dbUrl);
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCT");

        while (rs.next()) {
          if (rs.last()) {
            ObservableList<Product> prodTableList2 = productsTable.getItems();
            ObservableList<Product> prodTableList = productChoice.getItems();
            prodTableList.add(
                new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            prodTableList2.add(
                new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
          }
        }
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
    }
  }

  /**
   * Logs User out of Production Log Tracker.
   *
   * @param event Button click
   * @throws IOException Class not found
   */
  public void logging_out(ActionEvent event) throws IOException {
    Parent contParent = FXMLLoader.load(getClass().getResource("../Resources/LoginPage.fxml"));
    Scene contScene = new Scene(contParent);

    Stage controllerWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
    controllerWindow.setScene(contScene);
    controllerWindow.show();
  }

  /** Records production to ProductionRecord. */
  public void record_prod() {
    // pulls information from table row and places into object Product
    Product testing =
        productChoice.getItems().get(productChoice.getSelectionModel().getFocusedIndex());
    System.out.println(testing.getType());
    // change string to enum type
    ItemType typeSwitch = null;
    switch (testing.getType()) {
      case "AUDIO":
        typeSwitch = ItemType.AUDIO;
        break;
      case "VISUAL":
        typeSwitch = ItemType.VISUAL;
        break;
      case "AUDIO_MOBILE":
        typeSwitch = ItemType.AUDIO_MOBILE;
        break;
      case "VISUAL_MOBILE":
        typeSwitch = ItemType.VISUAL_MOBILE;
        break;
      default:
        System.out.println("Invalid String - Unable to Define ItemType");
    }
    Product productProduced = new Widget(testing.getName(), testing.getManufacturer(), typeSwitch);
    ObservableList selectedItems = productChoice.getSelectionModel().getSelectedItems();
    System.out.println(selectedItems.get(0));
    noProductSelected.setVisible(false);
    recordProductionError.setVisible(false);
    productionRecorded.setVisible(false);
    addProductError.setVisible(false);
    String quantityString = String.valueOf(quantityComboBox.getValue());
    if (quantityComboBox.getValue() == null
        || quantityString.equals("")
        || !quantityString.matches("[^a-zA-Z]")
        || selectedItems.get(0) == null) {
      if (selectedItems.get(0) == null) {
        noProductSelected.setVisible(true);
      }
      if (quantityComboBox.getValue() == null
          || quantityString.equals("")
          || !quantityString.matches("[^a-zA-Z]")) {
        recordProductionError.setVisible(true);
        productionRecorded.setVisible(false);
        addProductError.setVisible(false);
      }
    } else {
      recordProductionError.setVisible(false);
      addProductError.setVisible(false);
      noProductSelected.setVisible(false);
      // gets value from combobox
      int quantity = Integer.parseInt(quantityString);
      // Initiates count for Serial Number
      for (int i = 0; i < quantity; i++) {
        int itemCount = 0;
        ObservableList<ProductionRecord> productionRecord = productionLog.getItems();
        for (ProductionRecord record : productionRecord) {
          assert typeSwitch != null;
          String comparison = record.getSerialNum().substring(3, 5);
          if (typeSwitch.itemTypeAbr.equals(comparison)) {
            itemCount++;
          }
        }
        int prodNum = productionRecord.size();
        // ProductionRecord prodRec = new ProductionRecord(testing, itemCount++);
        // Check Product productProduced to find number of ItemType
        ProductionRecord prodRec = new ProductionRecord(productProduced, itemCount);
        // System.out.println(prodRec.getSerialNum());
        try {
          Timestamp ts = new Timestamp(prodRec.getProdDate().getTime());
          String sql =
              "INSERT INTO PRODUCTIONRECORD (PRODUCTION_NUM, PRODUCT_ID , SERIAL_NUM,"
                  + " DATE_PRODUCED, RECORDED_EMPLOYEE) VALUES (? , ? , ? , ? , ?)";
          // Test Case
          // System.out.println("THE SQL STATEMNT: " + sql);
          productionRecorded.setVisible(true);
          try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, prodNum);
            stmt.setInt(2, testing.getId());
            stmt.setString(3, prodRec.getSerialNum());
            stmt.setTimestamp(4, ts);
            stmt.setString(5, log_emp.getUserName());
            stmt.executeUpdate();
            stmt.close();
          } catch (SQLException e) {
            e.printStackTrace();
          }
        } catch (NullPointerException ex) {
          ex.printStackTrace();
        }
        try {
          Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
          e.printStackTrace();
        }
        try {
          conn = DriverManager.getConnection(dbUrl);
          stmt = conn.createStatement();
          ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCTIONRECORD");
          while (rs.next()) {
            if (rs.last()) {
              productionRecord.add(
                  new ProductionRecord(
                      rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4), rs.getString(5)));
            }
          }
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
      }
    }
  }
}
