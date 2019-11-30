package GUIProject;

/**
 * Extension for ItemType Audio.
 *
 * @file AudioPlayer.java
 * @author Shane Broxson
 */
public class AudioPlayer extends Product implements MultimediaControl {
    private String audioSpecification;
    private String mediaType;

    /**
     * @param n                  Product Name
     * @param m                  Product Manufacturer
     * @param audioSpecification AudioSpecification
     * @param mediaType          Media Type
     */
    AudioPlayer(String n, String m, String audioSpecification, String mediaType) {
        super(n, m, ItemType.AUDIO);
        this.audioSpecification = audioSpecification;
        this.mediaType = mediaType;
    }

    public void play() {
        System.out.println("Playing");
    }

    public void stop() {
        System.out.println("Stopping");
    }

    public void previous() {
        System.out.println("Previous");
    }

    public void next() {
        System.out.println("Next");
    }

    /**
     * @return override to toString method for AudioPlayer
     */
    public String toString() {
        System.out.println(super.toString());
        return "Supported Audio Formats: "
                + audioSpecification
                + "\nSupported Playlist Formats: "
                + mediaType;
    }
}
