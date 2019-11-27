package GUIProject;

import java.util.Date;

/**
 * Used to create ProductionRecord based on Product Details.
 *
 * @file ProductionRecord.java
 * @author Shane Broxson
 */
public class ProductionRecord {
  private static int produced;
  private int prodNum;
  private int prodID;
  private String serialNum;
  private Date currentDate;
  private String employee;

  /** @param pn Production Number */
  ProductionRecord(int pn) {
    prodNum = pn;
    prodID = 0;
    serialNum = "0";
    currentDate = new Date();
  }

  /**
   * @param W Object Product
   * @param produced Number of each based on ItemType used to generate Serial Number
   */
  ProductionRecord(Product W, int produced) {
    this.prodID = W.getId();
    // create a better counting system for serial num
    String manUpdate = W.getManufacturer().replaceAll(" ", "_").toUpperCase().substring(0, 3);
    serialNum = manUpdate + W.getTypes().item_type_abr + String.format("%05d", produced);
    currentDate = new Date();
  }

  /**
   * @param prodNum Production Number
   * @param prodID Production ID
   * @param serialNum Product Serial Number
   * @param currentDate Product Current Date
   */
  ProductionRecord(int prodNum, int prodID, String serialNum, Date currentDate, String employee) {
    this.prodNum = prodNum;
    this.prodID = prodID;
    this.serialNum = serialNum;
    this.currentDate = currentDate;
    this.employee = employee;
  }

  /** @return override to toString method for ProductionRecord */
  public String toString() {
    return "Prod. Num: "
        + prodNum
        + " Product ID: "
        + prodID
        + " Serial Num: "
        + serialNum
        + " Date: "
        + currentDate;
  }

  /** @param prodNum Production Number */
  public void setProductionNum(int prodNum) {
    this.prodNum = prodNum;
  }

  /** @param prodID Product ID */
  public void setProductID(int prodID) {
    this.prodID = prodID;
  }

  /** @param serialNum Serial Number */
  public void setSerialNum(String serialNum) {
    this.serialNum = serialNum;
  }

  /** @param currentDate Product Current Date */
  public void setProdDate(Date currentDate) {
    this.currentDate = currentDate;
  }

  /** @return Production Number */
  public int getProductionNum() {
    return prodNum;
  }

  /** @return Product ID */
  public int getProductID() {
    return prodID;
  }

  /** @return Product Serial Number */
  public String getSerialNum() {
    return serialNum;
  }

  /** @return Product Current Date */
  public Date getProdDate() {
    return currentDate;
  }

  /**
   * @return Logged in Employee
   */
  public String getEmployee() {
    return employee;
  }

  /**
   * @param employee Logged in Employee Username
   */
  public void setEmployee(String employee) {
    this.employee = employee;
  }
}
