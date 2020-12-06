package audioPlayer;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

/*
 * TODO Player is not working need to work out how to play mp4 audio streams using either java code or some external
 * java library. I am looking forward at using VLCJ but i haven't seen if it will provide the required solution I am looking
 * for or not
 */

public class Player
{
	private String url;
	public Player(String url){
		this.url=url;
	}
	
	public void start() {
		AudioInputStream din = null;
	    try {
	    	System.out.println(this.url);
	        AudioInputStream in = AudioSystem.getAudioInputStream(new URL(this.url));
	        AudioFormat baseFormat = in.getFormat();
	        AudioFormat decodedFormat = new AudioFormat(
	                AudioFormat.Encoding.PCM_SIGNED,
	                baseFormat.getSampleRate(), 16, baseFormat.getChannels(),
	                baseFormat.getChannels() * 2, baseFormat.getSampleRate(),
	                false);
	        din = AudioSystem.getAudioInputStream(decodedFormat, in);
	        DataLine.Info info = new DataLine.Info(SourceDataLine.class, decodedFormat);
	        SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
	        if(line != null) {
	            line.open(decodedFormat);
	            byte[] data = new byte[4096];
	            // Start
	            line.start();

	            int nBytesRead;
	            while ((nBytesRead = din.read(data, 0, data.length)) != -1) {
	                line.write(data, 0, nBytesRead);
	            }
	            // Stop
	            line.drain();
	            line.stop();
	            line.close();
	            din.close();
	        }

	    }
	    catch(Exception e) {
	        e.printStackTrace();
	    }
	    finally {
	        if(din != null) {
	            try { din.close(); } catch(IOException e) { }
	        }
	    }
	}

}

