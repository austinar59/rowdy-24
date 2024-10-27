package com.example.RestApi;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class PlayClip implements Runnable {
    private String filename;

    public PlayClip(String filename) {
        this.filename = filename;
    }

    @Override
    public void run() {
        // List<Clip> clips = new ArrayList<>();
        try {
            File soundFile = new File(filename);
            if (!soundFile.exists()) {
                System.out.println("File not found: " + filename);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            Clip audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);

            audioClip.start();
            Thread.sleep(audioClip.getMicrosecondLength() / 1);
            audioClip.close();
            audioStream.close();

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}