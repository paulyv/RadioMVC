package fi.pauli.radio.mvc;

public class Channels {

	ReadXML rx = new ReadXML("http://gingrkidd.com/other_files/radiomvc/channels.xml");

	private String[] channelNames;
	private String[] channelUrl;

	public Channels() {
		channelNames = new String[rx.getNamesArray().length];
		channelUrl = new String[rx.getUrlArray().length];

		for (int i = 0; i < channelNames.length; i++) {
			channelNames[i] = rx.getNamesArray()[i];
		}
		for (int i = 0; i < channelUrl.length; i++) {
			channelUrl[i] = rx.getUrlArray()[i];
		}
	}

	public String[] getChannels() {
		return channelNames;
	}

	public String getChannelUrl(int x) {
		return channelUrl[x];
	}

	public String getCurrentChannel(int x) {
		return channelNames[x];
	}

}
