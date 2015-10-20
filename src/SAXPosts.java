import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXPosts extends Counter {

	String file;

	Document doc = null;

	public SAXPosts(String file) {

		this.file = file;
	}

	//handler to obtain the values needed, updates the map and sets if the values are found
	DefaultHandler handler = new DefaultHandler() {
		public void startElement(String uri, String localName, String qName,
				Attributes atts) throws SAXException {
			if (qName.equals("row")) {
				String postType = atts.getValue("PostTypeId");
				String owner = atts.getValue("OwnerUserId");
				if (owner != null && postType != null) {

					if (postType.equals("1") && !owner.equals("")) {

						updateCount(owner, userPosts, top10Posts);
					}
					if (postType.equals("2") && !owner.equals("")) {

						updateCount(owner, userAnswers, top10Answers);
					}
				}
			}
		}
	};

	public void parse() {
		try {
			SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
			parser.parse(new File(file), handler);
		}

		catch (ParserConfigurationException pce) {
			pce.printStackTrace();

		} catch (SAXException se) {
			se.printStackTrace();

		} catch (IOException ioe) {
			ioe.printStackTrace();

		}

	}
}
