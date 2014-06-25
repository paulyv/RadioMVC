package fi.pauli.radio.mvc;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class ReadXML extends JFrame {

	private URL osoite;
	private URLConnection connection;
	private Document xmlDoc;

	// Constructor to create InputStream connection from the passed URL string
	public ReadXML(String x) {

		try {
			osoite = new URL(x);
			connection = osoite.openConnection();
			xmlDoc = getDocument(connection.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,
					"Could not connect to server. Try again later.",
					"Connection Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}

	// Parse XML
	public Document getDocument(InputStream stream) {

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			return builder.parse(stream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	// Return URL addresses from XML file
	public String[] getUrlArray() {
		NodeList listOfNodes = xmlDoc.getElementsByTagName("Url");
		String[] urls = new String[listOfNodes.getLength()];
		for (int i = 0; i < urls.length; i++) {
			urls[i] = listOfNodes.item(i).getTextContent();
		}
		return urls;

	}

	// Returns names of the channels from XML file
	public String[] getNamesArray() {
		NodeList listOfNodes = xmlDoc.getElementsByTagName("Name");
		String[] names = new String[listOfNodes.getLength()];
		for (int i = 0; i < names.length; i++) {
			names[i] = listOfNodes.item(i).getTextContent();
		}
		return names;
	}
}
