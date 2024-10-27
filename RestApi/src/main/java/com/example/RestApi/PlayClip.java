package com.example.RestApi;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class PlayClip implements Runnable {
    private String filename;

    public PlayClip(String filename) {
        this.filename = filename;
    }

    @Override
    public void run() {
        try {
            // Load the file from resources
            URL soundFileUrl = getClass().getClassLoader().getResource("rowdyKeys/" + filename);
            if (soundFileUrl == null) {
                System.out.println("File not found in resources: " + filename);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFileUrl);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            Clip audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);

            audioClip.start();
            Thread.sleep(audioClip.getMicrosecondLength() / 1000);
            audioClip.close();
            audioStream.close();

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}