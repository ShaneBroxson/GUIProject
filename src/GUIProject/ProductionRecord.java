/**
 * @file Controller.java
 * @breif Controller class for fxml file.
 * @author Shane Broxson
 */
package GUIProject;

import java.util.Date;

public class ProductionRecord {
    private static int produced;
    private int prodNum;
    private int prodID;
    private String serialNum;
    private Date currentDate;

    ProductionRecord(int pn){
        prodNum = pn;
        prodID = 0;
        serialNum = "0";
        currentDate = new Date();
    }
    ProductionRecord(Product W){

        this.prodID = W.getId();
        //create a better counting system for serial num
        String manUpdate = W.getManufacturer().replaceAll(" ","_").substring(0,3);
        serialNum = manUpdate + W.getAbr() + String.format("%05d", produced++);
        currentDate = new Date();
    }
    ProductionRecord(int prodNum, int prodID, String serialNum, Date currentDate){
        this.prodNum = prodNum;
        this.prodID = prodID;
        this.serialNum = serialNum;
        this.currentDate = currentDate;
    }

    public String toString() {
        return "Prod. Num: " + prodNum + " Product ID: " + prodID + " Serial Num: " + serialNum + " Date: " + currentDate;
    }


    public void setProductionNum(int prodNum){
        this.prodNum = prodNum;
    }
    public void setProductID(int prodID){
        this.prodID = prodID;
    }
    public void setSerialNum(String serialNum){
        this.serialNum = serialNum;
    }
    public void setProdDate(Date currentDate){
        this.currentDate = currentDate;
    }

    public int getProductionNum(){
        return prodNum;
    }
    public int getProductID(){
        return prodID;
    }
    public String getSerialNum(){
        return serialNum;
    }
    public Date getProdDate(){
        return currentDate;
    }
}