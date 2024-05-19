package org.csbk;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.*;
import java.lang.ref.Cleaner;
public class SoundPlayer {
    private Clip clip;
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private Cleaner cleaner = Cleaner.create();
    private Cleaner.Cleanable cleanable;
    private volatile boolean isMuted = false;

    public SoundPlayer() {
        cleanable = cleaner.register(this, () -> {
            if (scheduler != null) {
                scheduler.shutdownNow();
            }
        });
    }

    public void playSound() {
        if (isMuted) return;
        try {
            if (clip == null || !clip.isOpen()) {
                File audioFile = new File("alarm.wav");
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                clip = AudioSystem.getClip();
                clip.open(audioStream);
            }
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stopSoundTemporarily() {
        if (clip != null) {
            clip.stop();
            isMuted = true;
            scheduler.schedule(() -> {
                isMuted = false;
            }, 10, TimeUnit.MINUTES);
        }
    }

    public void stopSound() {
        if (clip != null) {
            clip.stop();
            scheduler.shutdownNow();
        }
    }
}

