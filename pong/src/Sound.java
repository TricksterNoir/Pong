import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Sound {
    private static Clip backgroundMusicClip;

    public static void playBackgroundMusic(String audioFilePath) {
        try {
            URL audioUrl = Sound.class.getClassLoader().getResource(audioFilePath);
            if (audioUrl != null) {
                File audioFile = new File(audioUrl.getPath());
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                backgroundMusicClip = AudioSystem.getClip();
                backgroundMusicClip.open(audioStream);
                backgroundMusicClip.setLoopPoints(0, -1);
                FloatControl volumeControl = (FloatControl) backgroundMusicClip.getControl(FloatControl.Type.MASTER_GAIN);
                float volume = -9.7f;
                volumeControl.setValue(volume);
                backgroundMusicClip.loop(Clip.LOOP_CONTINUOUSLY);
                backgroundMusicClip.start();
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public static void generalSounds(String audioFilePath) {
        try {

            URL audioUrl = Sound.class.getClassLoader().getResource(audioFilePath);
            if (audioUrl != null) {
                File audioFile = new File(audioUrl.getPath());
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                float volume = -1.5f;
                volumeControl.setValue(volume);
                clip.start();
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void stopSound(){
        if (backgroundMusicClip != null && backgroundMusicClip.isRunning()) {
            backgroundMusicClip.stop();
        }
    }
}

