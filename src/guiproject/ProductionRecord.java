package guiproject;

import java.util.Date;

/**
 * Used to create ProductionRecord based on Product Details.
 *
 * @file ProductionRecord.java
 * @author Shane Broxson
 */
public class ProductionRecord {
  private int produced;
  private int prodNum;
  private int prodID;
  private String serialNum;
  private Date currentDate;
  private String employee;

  /**
   * ProductionRecord subclass containing a Product and count of Product Type produced.
   *
   * @param product Object Product
   * @param produced Number of each based on ItemType used to generate Serial Number
   */
  ProductionRecord(Product product, int produced) {
    this.produced = produced;
    this.prodID = product.getId();
    // create a better counting system for serial num
    String manUpdate = product.getManufacturer().replaceAll(" ", "_").toUpperCase().substring(0, 3);
    serialNum = manUpdate + product.getTypes().itemTypeAbr + String.format("%05d", this.produced);
    currentDate = new Date();
  }

  /**
   * ProductionRecord subclass used when pulling from Database.
   *
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

  /**
   * Overrides toString() for ProductionRecord.
   *
   * @return override to toString method for ProductionRecord
   */
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

  /**
   * Sets the Production Number.
   *
   * @param prodNum Production Number
   */
  public void setProductionNum(int prodNum) {
    this.prodNum = prodNum;
  }

  /**
   * Sets the Product ID.
   *
   * @param prodID Product ID
   */
  public void setProductID(int prodID) {
    this.prodID = prodID;
  }

  /**
   * Sets the Serial Number.
   *
   * @param serialNum Serial Number
   */
  public void setSerialNum(String serialNum) {
    this.serialNum = serialNum;
  }

  /**
   * Sets the Product Date.
   *
   * @param currentDate Product Current Date
   */
  public void setProdDate(Date currentDate) {
    this.currentDate = new Date(currentDate.getTime());
  }

  /**
   * Returns the Production Number.
   *
   * @return Production Number
   */
  public int getProductionNum() {
    return prodNum;
  }

  /**
   * Returns the Product ID.
   *
   * @return Product ID
   */
  public int getProductID() {
    return prodID;
  }

  /**
   * Returns the Product Serial Number.
   *
   * @return Product Serial Number
   */
  public String getSerialNum() {
    return serialNum;
  }

  /**
   * Returns the Product Current Date.
   *
   * @return Product Current Date
   */
  public Date getProdDate() {
    return new Date(currentDate.getTime());
  }

  /**
   * Returns Employee who created Product.
   *
   * @return Logged in Employee
   */
  public String getEmployee() {
    return employee;
  }

  /**
   * Sets the Employee based on Account logged in.
   *
   * @param employee Logged in Employee Username
   */
  public void setEmployee(String employee) {
    this.employee = employee;
  }
}
