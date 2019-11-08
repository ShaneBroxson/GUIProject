/**
 * @file Screen.java
 * @breif Establishes screen details for MoviePlayer.
 * @Author Shane Broxson
 */
package GUIProject;

public class Screen implements ScreenSpec {
    String resolution;
    int refreshrate;
    int responsetime;

    Screen(String reso, int refr, int resp) {
        resolution = reso;
        refreshrate = refr;
        responsetime = resp;
    }

    public String toString(){
        return "Resolution: "+resolution+"\nRefreshrate: "+refreshrate+"\nResponsetime: "+responsetime;
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
//class ProductWidget extends Screen {
//    ProductWidget(String reso, int refr, int resp){
//        super(reso,refr,resp);
//    }
//
//    @Override
//    public String getResolution() {
//        return resolution;
//    }
//
//    @Override
//    public int getRefreshRate() {
//        return refreshrate;
//    }
//
//    @Override
//    public int getResponseTime() {
//        return responsetime;
//    }
//}