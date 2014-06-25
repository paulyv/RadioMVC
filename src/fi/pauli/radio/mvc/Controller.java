package fi.pauli.radio.mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

	private int comboBoxIndex = 0;
	private View view;
	private RadioPlayer rp;
	private Channels ch = new Channels();
	private boolean playing = false;

	public Controller(View view) {
		this.view = view;
		rp = new RadioPlayer();
		view.addPlayBtnListener(new PlayBtnListener());
		view.addStopBtnListener(new StopBtnListener());

	}

	// Play button actions
	class PlayBtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (playing == false) {
				comboBoxIndex = view.getChannelBoxIndex();
				rp.play(ch.getChannelUrl(comboBoxIndex));
				view.setDisplayText(ch.getCurrentChannel(comboBoxIndex));
				playing = true;

				// Start thread to check state and to change set text
				Thread t1 = new Thread(new Runnable() {
					@Override
					public void run() {
						while (true) {
							try {
								Thread.sleep(20);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							if (rp.getState() == 200) {
								view.setStateText("Buffering..");
							}
							if (rp.getState() == 600) {
								view.setStateText("Playing");
							}
							if (rp.getState() == 500) {
								view.setStateText("Stopped");
							}

						}
					}

				});
				t1.start();
			}
		}
	}

	// Stop button actions
	class StopBtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			rp.stop();
			playing = false;
		}
	}

}
