package ui;

import javax.sound.sampled.*;
import java.io.*;
import java.net.MalformedURLException;

public class Song implements Runnable{
	    private Clip clip;
	    public Song(String fileName) {
	        // specify the sound to play
	        // (assuming the sound can be played by the audio system)
	        // from a wave File
	        try {
	            File file = new File(fileName);
	            if (file.exists()) {
	                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
	             // load the sound into memory (a Clip)
	                clip = AudioSystem.getClip();
	                clip.open(sound);
	            }
	            else {
	                throw new RuntimeException("Sound: file not found: " + fileName);
	            }
	        }
	        catch (MalformedURLException e) {
	            e.printStackTrace();
	            throw new RuntimeException("Sound: Malformed URL: " + e);
	        }
	        catch (UnsupportedAudioFileException e) {
	            e.printStackTrace();
	            throw new RuntimeException("Sound: Unsupported Audio File: " + e);
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	            throw new RuntimeException("Sound: Input/Output Error: " + e);
	        }
	        catch (LineUnavailableException e) {
	            e.printStackTrace();
	            throw new RuntimeException("Sound: Line Unavailable Exception Error: " + e);
	        }
	    }

		@Override
		public void run() {
			// TODO Auto-generated method stub
			clip.setFramePosition(0);  // Must always rewind!
			clip.loop(Clip.LOOP_CONTINUOUSLY);
	        clip.start();
		}
}
