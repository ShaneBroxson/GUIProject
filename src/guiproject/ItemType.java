package guiproject;

/**
 * Custom Enum for possible product ItemType choices.
 *
 * @file ItemType.java
 * @author Shane Broxson
 */
public enum ItemType {
  AUDIO("AUDIO", "AU"),
  VISUAL("VISUAL", "VI"),
  AUDIO_MOBILE("AUDIO_MOBILE", "AM"),
  VISUAL_MOBILE("VISUAL_MOBILE", "VM");

  public final String itemType;
  public final String itemTypeAbr;

  /**
   * Subclass for ItemType containing ItemType and Abbreviated version.
   *
   * @param itemType Full name to ItemType
   * @param itemTypeAbr Abbreviation of ItemType
   */
  ItemType(String itemType, String itemTypeAbr) {
    this.itemType = itemType;
    this.itemTypeAbr = itemTypeAbr;
  }

  /**
   * Returns the full ItemType.
   *
   * @return the full ItemType
   */
  public String getType() {
    return itemType;
  }

  /**
   * Returns the abbreviated ItemType.
   *
   * @return abbreviated ItemType
   */
  public String getAbr() {
    return itemTypeAbr;
  }
}
