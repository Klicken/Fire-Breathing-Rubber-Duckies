package Game;

import javafx.scene.media.AudioClip;

/**
 * A class to manage sound.
 */
public class Sound {

    private AudioClip sound;
    private static boolean mute = false;

    public Sound(String path, Double vol, int priority, int cycleCount){

        if(!mute){
        String soundpath = "/resources/sounds/" + path;
        sound = new AudioClip(this.getClass().getResource(soundpath).toExternalForm());
        sound.setVolume(vol);
        sound.setPriority(priority);
        sound.setCycleCount(cycleCount);
        sound.play();
        }

    }

    public static boolean getMute(){
        return mute;
    }

    public static void setMute(boolean b){
        mute = b;
    }

    public void stop(){
        sound.stop();
    }

    public void play(){
        sound.play();
    }

    public boolean soundPlaying(){
        return sound.isPlaying();
    }

}
