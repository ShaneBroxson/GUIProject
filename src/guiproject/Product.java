package guiproject;

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
   * Subclass for Product to initially set values.
   *
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
   * Subclass after Sting type is turned into custom type ItemType.
   *
   * @param n string name
   * @param m string manufacturer
   * @param t ItemType type
   */
  Product(String n, String m, ItemType t) {
    name = n;
    manufacturer = m;
    types = t;
  }

  /**
   * Overrides toString() for Product.
   *
   * @return An override to toString method
   */
  public String toString() {
    return "Name: " + name + "\nManufacturer: " + manufacturer + "\nType: " + type;
  }

  /**
   * Returns Product ID.
   *
   * @return Product ID
   */
  public int getId() {
    return id;
  }

  /**
   * Sets Product ID.
   *
   * @param id Sets Product ID
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Returns Product Name.
   *
   * @return Product Name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets Product Name.
   *
   * @param name Sets Product Name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Returns Product Manufacturer.
   *
   * @return Product Manufacturer
   */
  public String getManufacturer() {
    return manufacturer;
  }

  /**
   * Sets Product Manufacturer.
   *
   * @param manufacturer Sets Product Manufacturer
   */
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  /**
   * Returns Product Type (String).
   *
   * @return Product Type
   */
  public String getType() {
    return type;
  }

  /**
   * Sets Product Type (String).
   *
   * @param type Sets Product Type
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Returns Product Types (ItemType).
   *
   * @return Product Types
   */
  public ItemType getTypes() {
    return types;
  }

  /**
   * Sets Product Types (ItemType).
   *
   * @param types Sets Product Types
   */
  public void setTypes(ItemType types) {
    this.types = types;
  }

  /**
   * Returns Product abbreviated ItemType.
   *
   * @return abbreviated ItemType
   */
  public String getAbr() {
    return abr;
  }

  /**
   * Sets the Product abbreviated ItemType.
   *
   * @param abr Sets abbreviated ItemType
   */
  public void setAbr(String abr) {
    this.abr = abr;
  }
}
