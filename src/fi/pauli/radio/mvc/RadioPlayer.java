package fi.pauli.radio.mvc;

import java.net.URL;

import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.Format;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.PlugInManager;
import javax.media.format.AudioFormat;

public class RadioPlayer implements ControllerListener {

	private Player player;
	private URL channelURL;
	private String channel;

	public RadioPlayer() {

		// Codecs for mp3
		Format input1 = new AudioFormat(AudioFormat.MPEGLAYER3);
		Format input2 = new AudioFormat(AudioFormat.MPEG);
		Format output = new AudioFormat(AudioFormat.LINEAR);

		// Format for mp3
		PlugInManager.addPlugIn("com.sun.media.codec.audio.mp3.JavaDecoder",
				new Format[] { input1, input2 }, new Format[] { output },
				PlugInManager.CODEC);

	}
	
	public void play(String URL){
		channelURL = null;
		this.channel = URL;

		try {
			channelURL = new URL(channel);
			player = Manager.createPlayer(new MediaLocator(channelURL));
			//Manager.setHint(Manager.CACHING, new Boolean(false)); // disable buffering
			player = Manager.createPlayer(new MediaLocator(channelURL));
			player.addControllerListener(this);
			player.start();
		} catch (Exception e) {
			e.printStackTrace();
			player.stop();
			player.close();
			player.deallocate();
		}
	}

	// Stop playing
	public void stop() {
		player.stop();
		player.close();
		player.deallocate();
	}
	
	// Get player state
	public int getState(){
		int state = player.getState();
		return state;
	}

	
	@Override
	public void controllerUpdate(ControllerEvent arg0) {

	}
}
