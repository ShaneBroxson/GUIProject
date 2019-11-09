/**
 * @file Product.java
 * @breif File to initialize Product.
 * @Author Shane Broxson
 */
package GUIProject;

public class Product implements Item {
    private int id;
    private String name;
    private String type;
    private ItemType types;
    private String manufacturer;
    private String abr;

    Product(int i, String n, String t, String m) {
        id = i;
        name = n;
        type = t;
        manufacturer = m;
    }

    Product(String n, String m, ItemType t) {
        name = n;
        manufacturer = m;
        types = t;
    }

    public String toString() {
        return "Name: " + name + "\nManufacturer: " + manufacturer + "\nType: " + type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ItemType getTypes(){
        return types;
    }

    public void setTypes(ItemType types){
        this.types = types;
    }

    public String getAbr(){
        return abr;
    }

    public void setAbr(String abr){
        this.abr = abr;
    }
}

class Widget extends Product {
    Widget(int i, String n, String t, String m) {
        super(i, n, t, m);
    }

    Widget(String n, String m, ItemType t) {
        super(n, m, t);
    }
}