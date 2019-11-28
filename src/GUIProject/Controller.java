package GUIProject;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.*;

/**
 * Controller class for Main fxml file.
 *
 * @author Shane Broxson
 * @file Controller.java
 */
public class Controller {

  @FXML private TableView<ProductionRecord> productionLog;
  @FXML private TableView<Product> productChoice;
  @FXML private ComboBox<Integer> quantity_comboBox;
  @FXML private TextField manufacturer_input;
  @FXML private TextField product_name_input;
  @FXML private ChoiceBox<String> item_type_ChoiceBox;
  @FXML private TableView<Product> products_table;
    @FXML
    private Tab manager_tab;
    @FXML
    private Label not_manager;
    @FXML
    private TableView<EmployeeInfo> employee_table;
    @FXML
    private TextField full_name;
    @FXML
    private PasswordField new_pass;
    @FXML
    private Label gen_user;
    @FXML
    private Label gen_email;
    @FXML
    private Label generated_username;
    @FXML
    private Label generated_email;
    @FXML
    private CheckBox manager_account;
    @FXML
    private Label incorrect_format;
    @FXML
    private Label add_product_error;
    @FXML
    private Label record_production_error;
  public static LoggedEmployee log_emp;
  private final String JDBC_DRIVER = "org.h2.Driver";
  private final String DB_URL = "jdbc:h2:./res/GUI_DB";

  private Connection conn = null;
  private Statement stmt = null;

  public void initialize() {
    gen_user.setVisible(false);
    gen_email.setVisible(false);
    generated_username.setVisible(false);
    generated_email.setVisible(false);
    manager_tab.setDisable(true);
    not_manager.setVisible(false);
    incorrect_format.setVisible(false);
    add_product_error.setVisible(false);
    record_production_error.setVisible(false);
    // Populates quantity_comboBox in the produce tab of GUI.
    for (int i = 1; i <= 10; i++) {
      quantity_comboBox.getItems().add(i);
    }
    // Adds Item Types to ChoiceBox
    for (ItemType items : ItemType.values()) {
      item_type_ChoiceBox.getItems().add(items.getType());
    }
    // Combobox properties
    quantity_comboBox.getSelectionModel().selectFirst();
    quantity_comboBox.setEditable(true);

    // Initialize Main Observable Lists
    ObservableList<Product> prodTableList = productChoice.getItems();
    ObservableList<Product> prodTableList2 = products_table.getItems();
    ObservableList<ProductionRecord> productionRecord = productionLog.getItems();
    ObservableList<EmployeeInfo> employeeList = employee_table.getItems();

    // Connect to Database
    try {
      Class.forName(JDBC_DRIVER);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    try {
      conn = DriverManager.getConnection(DB_URL);
      stmt = conn.createStatement();

      // Create Table Columns for Produce Tab in GUI
      TableColumn<Product, Integer> col_id = new TableColumn<>("ID");
      col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
      col_id.setMinWidth(54);
      TableColumn<Product, String> col_name = new TableColumn<>("Name");
      col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
      col_name.setMinWidth(100);
      TableColumn<Product, String> col_type = new TableColumn<>("Type");
      col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
      col_type.setMinWidth(100);
      TableColumn<Product, String> col_man = new TableColumn<>("Manufacturer");
      col_man.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
      col_man.setMinWidth(150);
      productChoice.getColumns().addAll(col_id, col_name, col_type, col_man);

      // Create Table Columns for Product Line in GUI
      TableColumn<Product, Integer> col_id2 = new TableColumn<>("ID");
      col_id2.setCellValueFactory(new PropertyValueFactory<>("id"));
      col_id2.setMinWidth(50);
      col_id2.setPrefWidth(50);
      TableColumn<Product, String> col_name2 = new TableColumn<>("Name");
      col_name2.setCellValueFactory(new PropertyValueFactory<>("name"));
      col_name2.setMinWidth(80);
      TableColumn<Product, String> col_type2 = new TableColumn<>("Type");
      col_type2.setCellValueFactory(new PropertyValueFactory<>("type"));
      col_type2.setMinWidth(100);
      TableColumn<Product, String> col_man2 = new TableColumn<>("Manufacturer");
      col_man2.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
      col_man2.setMinWidth(140);
      products_table.getColumns().addAll(col_id2, col_name2, col_type2, col_man2);

      // Create Table Columns for Production Log in GUI
      TableColumn<ProductionRecord, Integer> col_prodNum = new TableColumn<>("Production Number");
      col_prodNum.setCellValueFactory(new PropertyValueFactory<>("ProductionNum"));
      TableColumn<ProductionRecord, Integer> col_prodID = new TableColumn<>("Product ID");
      col_prodID.setCellValueFactory(new PropertyValueFactory<>("ProductID"));
      TableColumn<ProductionRecord, String> col_serialNum = new TableColumn<>("Serial Number");
      col_serialNum.setCellValueFactory(new PropertyValueFactory<>("SerialNum"));
      TableColumn<ProductionRecord, Timestamp> col_currentDate = new TableColumn<>("Date Produced");
      col_currentDate.setCellValueFactory(new PropertyValueFactory<>("ProdDate"));
      TableColumn<ProductionRecord, String> col_emp = new TableColumn<>("Employee");
      col_emp.setCellValueFactory(new PropertyValueFactory<>("employee"));
      productionLog
              .getColumns()
              .addAll(col_prodNum, col_prodID, col_serialNum, col_currentDate, col_emp);

      // Create Table Columns for Employees In Management tab of GUI
      TableColumn<EmployeeInfo, String> col_uname = new TableColumn<>("Employee Name");
      col_uname.setCellValueFactory(new PropertyValueFactory<>("name"));
      TableColumn<EmployeeInfo, String> col_username = new TableColumn<>("Username");
      col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
      TableColumn<EmployeeInfo, String> col_email = new TableColumn<>("Email");
      col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
      TableColumn<EmployeeInfo, Boolean> col_isManagement = new TableColumn<>("Manager");
      col_isManagement.setCellValueFactory(new PropertyValueFactory<>("management"));
      employee_table.getColumns().addAll(col_uname, col_username, col_email, col_isManagement);

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
    String f_name = full_name.getText();
    String p_word = new_pass.getText();
    Boolean manager = manager_account.isSelected();
    Employee employee = new Employee(f_name, p_word, manager);
    if (employee.getUsername().equals("default") || employee.getPassword().equals("pw")) {
      incorrect_format.setVisible(true);
      gen_email.setVisible(false);
      generated_email.setVisible(false);
      gen_user.setVisible(false);
      generated_username.setVisible(false);
    } else {
      incorrect_format.setVisible(false);
      generated_username.setText(employee.getUsername());
      generated_username.setVisible(true);
      gen_user.setVisible(true);
      generated_email.setText(employee.getEmail());
      generated_email.setVisible(true);
      gen_email.setVisible(true);
      String usernameCheck = employee.getUsername();
      int nameCount = 0;
      try {
        Class.forName(JDBC_DRIVER);
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
      try {
        conn = DriverManager.getConnection(DB_URL);
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
        String user_n = employee.getUsername();
        if (nameCount != 0) {
          user_n += Integer.toString(nameCount);
        }
        String tempPass = employee.getPassword();
        String sql =
                "INSERT INTO EMPLOYEE (NAME, USERNAME , EMAIL , PASSWORD , MANAGEMENT) VALUES ('"
                        + employee.getName()
                        + "', '"
                        + user_n
                        + "', '"
                        + employee.getEmail()
                        + "', '"
                        + reverseString(tempPass)
                        + "', '"
                        + employee.getManagement()
                        + "');";
        try {
          stmt.execute(sql);
        } catch (SQLException e) {
          e.printStackTrace();
        }
      } catch (NullPointerException ex) {
        ex.printStackTrace();
      }
      try {
        Class.forName(JDBC_DRIVER);
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
      try {
        conn = DriverManager.getConnection(DB_URL);
        stmt = conn.createStatement();
        ResultSet rs =
                stmt.executeQuery("SELECT NAME, USERNAME , EMAIL , MANAGEMENT FROM EMPLOYEE");
        while (rs.next()) {
          if (rs.last()) {
            ObservableList<EmployeeInfo> employeeList = employee_table.getItems();
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
   * @param user_name Logged in Users Username
   * @param is_Management Determines if Logged In user is Management
   */
  void sendUserInfo(String user_name, Boolean is_Management) {
    log_emp = new LoggedEmployee(user_name, is_Management);
  }

  @FXML
  void managementAccess(ActionEvent event) {
    if (log_emp.getManagement()) {
      manager_tab.setDisable(false);
    } else {
      not_manager.setVisible(true);
    }
  }

  public void addProduct() {
    String man_input = manufacturer_input.getText();
    String prod_name_input = product_name_input.getText();
    String item_type = item_type_ChoiceBox.getValue();
    if (man_input == null || prod_name_input == null || item_type == null) {
      add_product_error.setVisible(true);
    } else {
      add_product_error.setVisible(false);
      try {
        String sql =
                "INSERT INTO PRODUCT (NAME, TYPE , MANUFACTURER) VALUES ('"
                        + prod_name_input
                        + "', '"
                        + item_type
                        + "', '"
                        + man_input
                        + "')";
        try {
          stmt.execute(sql);
        } catch (SQLException e) {
          e.printStackTrace();
        }
      } catch (NullPointerException ex) {
        ex.printStackTrace();
      }
      try {
        Class.forName(JDBC_DRIVER);
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
      try {
        conn = DriverManager.getConnection(DB_URL);
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCT");

        while (rs.next()) {
          if (rs.last()) {
            ObservableList<Product> prodTableList2 = products_table.getItems();
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
      if (quantity_comboBox.getValue() == null
              || String.valueOf(quantity_comboBox.getValue()).equals("")) {
      record_production_error.setVisible(true);
    } else {
      record_production_error.setVisible(false);
      // gets value from combobox
      String quantityString = String.valueOf(quantity_comboBox.getValue());
      int quantity = Integer.parseInt(quantityString);
      // Initiates count for Serial Number
      for (int i = 0; i < quantity; i++) {
        int itemCount = 0;
        ObservableList<ProductionRecord> productionRecord = productionLog.getItems();
        for (ProductionRecord record : productionRecord) {
          assert typeSwitch != null;
          String comparison = record.getSerialNum().substring(3, 5);
          if (typeSwitch.item_type_abr.equals(comparison)) {
            itemCount++;
          }
        }
        int prodNum = productionRecord.size();
        // ProductionRecord prodRec = new ProductionRecord(testing, itemCount++);
        // Check Product productProduced to find number of ItemType
        ProductionRecord prodRec = new ProductionRecord(productProduced, itemCount);
        System.out.println(prodRec.getSerialNum());
        try {
          Timestamp ts = new Timestamp(prodRec.getProdDate().getTime());
          String sql =
                  "INSERT INTO PRODUCTIONRECORD (PRODUCTION_NUM, PRODUCT_ID , SERIAL_NUM, DATE_PRODUCED, RECORDED_EMPLOYEE) VALUES ('"
                          + prodNum
                          + "', '"
                          + testing.getId()
                          + "', '"
                          + prodRec.getSerialNum()
                          + "', '"
                          + ts
                          + "', '"
                          + log_emp.getUserName()
                          + "')";
          // Test Case
          System.out.println("THE SQL STATEMNT: " + sql);
          try {
            stmt.execute(sql);
          } catch (SQLException e) {
            e.printStackTrace();
          }
        } catch (NullPointerException ex) {
          ex.printStackTrace();
        }
        try {
          Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
          e.printStackTrace();
        }
        try {
          conn = DriverManager.getConnection(DB_URL);
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
