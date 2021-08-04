package model;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioClip {
	
	private Clip clip;
	private Thread thread;

	public AudioClip(File audioFile) {	
		AudioInputStream ais = null;
		try {
			ais = AudioSystem.getAudioInputStream(audioFile);
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try
        {
            // Retrieve the object of class Clip from the Data Line.
        	AudioFormat format = ais.getFormat();
        	DataLine.Info info = new DataLine.Info(Clip.class, format);
        	this.clip = (Clip) AudioSystem.getLine(info);

            // Load the audio input stream into memory for future play-back.
            this.clip.open(ais);
        }
        catch (LineUnavailableException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
	}
	
	public void start()
    {
        Runnable r = new Runnable() {
            public void run()
            {
            	clip.setFramePosition(0);
                clip.start();
            }
        };
        thread = new Thread(r);
        thread.start();
    }
	
}
