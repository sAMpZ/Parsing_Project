import java.io.File;
import java.io.IOException;
import java.util.TreeSet;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXMaps extends Mapper {
	String file;
	TreeSet<User> postMap;
	TreeSet<User> answerMap;

	Document doc = null;

	public SAXMaps(String file, TreeSet<User> postMap, TreeSet<User> answerMap) {
		this.file = file;
		this.postMap = postMap;
		this.answerMap = answerMap;
	}

	// handler to get the display names from user then map them to counts
	DefaultHandler handler = new DefaultHandler() {
		public void startElement(String uri, String localName, String qName,
				Attributes atts) throws SAXException {
			if (qName.equals("row")) {
				String id = atts.getValue("Id");
				String owner = atts.getValue("DisplayName");

				if (id != null && owner != null) {

					mapIdPosts(postMap, id, owner);
					mapIdAnswers(answerMap, id, owner);

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
