/**
 * @file Controller.java
 * @breif Controller class for fxml file.
 * @author Shane Broxson
 */
package GUIProject;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class Controller {

    @FXML
    private TableView<ProductionRecord> productionLog;

    @FXML
    private TableView<Product> productChoice;

    @FXML
    private ComboBox<Integer> quantity_comboBox;

    @FXML
    private TextField manufacturer_input;

    @FXML
    private TextField product_name_input;

    @FXML
    private ChoiceBox<String> item_type_ChoiceBox;

    @FXML
    private TableView<Product> products_table;

    private final String JDBC_DRIVER = "org.h2.Driver";
    private final String DB_URL = "jdbc:h2:./res/GUI_DB";

    private Connection conn = null;
    private Statement stmt = null;

    public void initialize() {

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
            col_id.setMinWidth(60);
            TableColumn<Product, String> col_name = new TableColumn<>("Name");
            col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_name.setMinWidth(100);
            TableColumn<Product, String> col_type = new TableColumn<>("Type");
            col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
            col_type.setMinWidth(100);
            TableColumn<Product, String> col_man = new TableColumn<>("Manufacturer");
            col_man.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
            col_man.setMinWidth(155);
            productChoice.getColumns().addAll(col_id, col_name, col_type, col_man);

            // Create Table Columns for Product Line in GUI
            TableColumn<Product, Integer> col_id2 = new TableColumn<>("ID");
            col_id2.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_id2.setMinWidth(40);
            col_id2.setPrefWidth(40);
            TableColumn<Product, String> col_name2 = new TableColumn<>("Name");
            col_name2.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_name2.setMinWidth(80);
            TableColumn<Product, String> col_type2 = new TableColumn<>("Type");
            col_type2.setCellValueFactory(new PropertyValueFactory<>("type"));
            col_type2.setMinWidth(80);
            TableColumn<Product, String> col_man2 = new TableColumn<>("Manufacturer");
            col_man2.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
            col_man2.setMinWidth(117);
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
            productionLog.getColumns().addAll(col_prodNum, col_prodID, col_serialNum, col_currentDate);


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
                productionRecord.add(new ProductionRecord(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getTimestamp(4)));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void addProduct() {
        String man_input = manufacturer_input.getText();
        String prod_name_input = product_name_input.getText();
        String item_type = item_type_ChoiceBox.getValue();

        try {
            String sql = "INSERT INTO PRODUCT (NAME, TYPE , MANUFACTURER) VALUES ('"
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

    public void record_prod() {
        // pulls information from table row and places into object Product
        Product testing = productChoice.getItems().get(productChoice.getSelectionModel().getFocusedIndex());

        // change string to enum type
        ItemType typeSwitch = null;
        switch (testing.getType()) {
            case "AUDIO":
                typeSwitch = ItemType.AUDIO;
                break;
            case "VISUAL":
                typeSwitch = ItemType.VISUAL;
                break;
            case "Audio Mobile":
                typeSwitch = ItemType.AUDIO_MOBILE;
                break;
            case "Visual Mobile":
                typeSwitch = ItemType.VISUAL_MOBILE;
                break;
            default:
                System.out.println("Invalid String - Unable to Define ItemType");
        }
        Product productProduced = new Widget(testing.getName(), testing.getManufacturer(), typeSwitch);

        // gets value from combobox
        String quantityString = String.valueOf(quantity_comboBox.getValue());
        int quantity = Integer.parseInt(quantityString);

        int itemCount = 0; //create better counter
        for (int i = 0; i < quantity; i++) {
            ObservableList<ProductionRecord> productionRecord = productionLog.getItems();
            int prodNum = productionRecord.size();
            // ProductionRecord prodRec = new ProductionRecord(testing, itemCount++);
            ProductionRecord prodRec = new ProductionRecord(productProduced);
            System.out.println(prodRec.getSerialNum());
            try {
                Timestamp ts = new Timestamp(prodRec.getProdDate().getTime());
                String sql = "INSERT INTO PRODUCTIONRECORD (PRODUCTION_NUM, PRODUCT_ID , SERIAL_NUM, DATE_PRODUCED) VALUES ('"
                        + prodNum
                        + "', '"
                        + testing.getId()
                        + "', '"
                        + prodRec.getSerialNum()
                        + "', '"
                        + ts
                        + "')";
                //Test Case
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
                                new ProductionRecord(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4)));
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }
}