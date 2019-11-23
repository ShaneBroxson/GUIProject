package GUIProject;

/**
 * Establishes screen details for MoviePlayer.
 *
 * @file Screen.java
 * @author Shane Broxson
 */
public class Screen implements ScreenSpec {
  String resolution;
  int refreshrate;
  int responsetime;

  /**
   * @param reso Screen Resolution
   * @param refr Screen Refresh Rate
   * @param resp Screen Response Time
   */
  Screen(String reso, int refr, int resp) {
    resolution = reso;
    refreshrate = refr;
    responsetime = resp;
  }

  /** @return override to toString method for Screen */
  public String toString() {
    return "Resolution: "
        + resolution
        + "\nRefreshrate: "
        + refreshrate
        + "\nResponsetime: "
        + responsetime;
  }

  @Override
  public String getResolution() {
    return resolution;
  }

  @Override
  public int getRefreshRate() {
    return refreshrate;
  }

  @Override
  public int getResponseTime() {
    return responsetime;
  }
}
