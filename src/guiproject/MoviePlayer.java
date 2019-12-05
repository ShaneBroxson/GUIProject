package guiproject;

/**
 * Establishes what information MoviePlayer holds.
 *
 * @file MoviePlayer.java
 * @author Shane Broxson
 */
public class MoviePlayer extends Product implements MultimediaControl {
  private Screen screen;
  private MonitorType monitorType;

  /**
   * MoviePlayer subclass that extends Product with Screen Type and Monitor Type.
   *
   * @param n Product Name for super of Product
   * @param m Product Manufacturer for super of Product
   * @param scr Screen Type
   * @param mon Monitor Type
   */
  public MoviePlayer(String n, String m, Screen scr, MonitorType mon) {
    super(n, m, ItemType.VISUAL);
    monitorType = mon;
    screen = scr;
  }

  public void play() {
    System.out.println("Playing Movie");
  }

  public void stop() {
    System.out.println("Stopping Movie");
  }

  public void previous() {
    System.out.println("Previous Movie");
  }

  public void next() {
    System.out.println("Next Movie");
  }

  /**
   * Overrides toString() for MoviePlayer.
   *
   * @return override of toString method for MoviePlayer
   */
  public String toString() {
    System.out.println(super.toString());
    return "Screen\n" + screen.toString() + "\nMonitor Type: " + monitorType.toString();
  }
}
