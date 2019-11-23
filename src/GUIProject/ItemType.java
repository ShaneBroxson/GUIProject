/** @Author Shane Broxson */
package GUIProject;

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

  public final String item_type;
  public final String item_type_abr;

  /**
   * Subclass for ItemType containing ItemType and Abbreviated version.
   *
   * @param item_type Full name to ItemType
   * @param item_type_abr Abbreviation of ItemType
   */
  ItemType(String item_type, String item_type_abr) {
    this.item_type = item_type;
    this.item_type_abr = item_type_abr;
  }

  /** @return the full ItemType */
  public String getType() {
    return item_type;
  }

  /** @return abbreviated ItemType */
  public String getAbr() {
    return item_type_abr;
  }
}
