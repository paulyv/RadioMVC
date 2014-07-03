package fi.pauli.radio.mvc;

import java.net.URL;

import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.Format;
import javax.media.IncompatibleTimeBaseException;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.PlugInManager;
import javax.media.format.AudioFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RadioPlayer extends JFrame implements ControllerListener {

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
			//Manager.setHint(Manager.CACHING, new Boolean(false)); // this will disable buffering
			player.start();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Could not connect to stream " + channel, "Error", JOptionPane.ERROR_MESSAGE);
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
	
	// Get player decibel
	public String getDecibel(){
		String db;
		db = Float.toString(player.getGainControl().getDB());
		return db;
	}
	
	public void setVolume(float x){
		player.getGainControl().setDB(x);
	}
	
	@Override
	public void controllerUpdate(ControllerEvent arg0) {
		System.out.println(player.getGainControl().getDB());
	}
}
