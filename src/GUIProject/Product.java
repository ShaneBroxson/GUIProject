package GUIProject;

/**
 * Class to create objects of Product that implements Item.
 *
 * @file Product.java
 * @author Shane Broxson
 */
public class Product implements Item {
  private int id;
  private String name;
  private String type;
  private ItemType types;
  private String manufacturer;
  private String abr;

  /**
   * @param i integer id
   * @param n string name
   * @param t string item type
   * @param m string manufacturer
   */
  Product(int i, String n, String t, String m) {
    id = i;
    name = n;
    type = t;
    manufacturer = m;
  }

  /**
   * @param n string name
   * @param m string manufacturer
   * @param t ItemType type
   */
  Product(String n, String m, ItemType t) {
    name = n;
    manufacturer = m;
    types = t;
  }

  /** @return An override to toString method */
  public String toString() {
    return "Name: " + name + "\nManufacturer: " + manufacturer + "\nType: " + type;
  }

  /** @return Product ID */
  public int getId() {
    return id;
  }

  /** @param id Sets Product ID */
  public void setId(int id) {
    this.id = id;
  }

  /** @return Product Name */
  public String getName() {
    return name;
  }

  /** @param name Sets Product Name */
  public void setName(String name) {
    this.name = name;
  }

  /** @return Product Manufacturer */
  public String getManufacturer() {
    return manufacturer;
  }

  /** @param manufacturer Sets Product Manufacturer */
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  /** @return Product Type */
  public String getType() {
    return type;
  }

  /** @param type Sets Product Type */
  public void setType(String type) {
    this.type = type;
  }

  /** @return Product Types */
  public ItemType getTypes() {
    return types;
  }

  /** @param types Sets Product Types (custom datatype) */
  public void setTypes(ItemType types) {
    this.types = types;
  }

  /** @return abbreviated ItemType */
  public String getAbr() {
    return abr;
  }

  /** @param abr Sets abbreviated ItemType */
  public void setAbr(String abr) {
    this.abr = abr;
  }
}

/**
 * Subclass to extend Product
 *
 * @file Product.java
 * @author Shane Broxson
 */
class Widget extends Product {
  Widget(int i, String n, String t, String m) {
    super(i, n, t, m);
  }

  Widget(String n, String m, ItemType t) {
    super(n, m, t);
  }
}
