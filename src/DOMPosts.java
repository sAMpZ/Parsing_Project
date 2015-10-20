import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMPosts extends Counter {

	String file;

	Document doc = null;

	public DOMPosts(String file) {

		this.file = file;
	}

	public void parse() {

		try {

			// creates document builder factory object
			DocumentBuilderFactory dBFactory = DocumentBuilderFactory
					.newInstance();

			DocumentBuilder db = dBFactory.newDocumentBuilder();

			doc = db.parse(file);

			// make sure that if elements have been split across more than line
			// its correctly parsed
			doc.getDocumentElement().normalize();

		} catch (FactoryConfigurationError fce) {
			fce.printStackTrace();
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public void getInfo() {

		// get nodes with given name
		NodeList nodeList = doc.getElementsByTagName("row");

		int length = nodeList.getLength();

		for (int j = 0; j < length; j++) {
			Element element = (Element) nodeList.item(j);

			// grab required attributes and update hashmap and sorted set with
			// id and count
			String postId = element.getAttribute("PostTypeId");
			String owner = element.getAttribute("OwnerUserId");
			if (postId != null) {
				if (owner != null) {
					if (postId.equals("1") && !owner.equals("")) {

						updateCount(owner, userPosts, top10Posts);
					}
					if (postId.equals("2") && !owner.equals("")) {

						updateCount(owner, userAnswers, top10Answers);
					}

				}
			}

		}
	}

}
