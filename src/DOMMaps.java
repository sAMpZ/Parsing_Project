import java.io.IOException;
import java.util.TreeSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMMaps extends Mapper {

	Document doc = null;
	String file;
	TreeSet<User> postMap;
	TreeSet<User> answerMap;

	public DOMMaps(String file, TreeSet<User> postMap, TreeSet<User> answerMap) {
		this.file = file;
		this.postMap = postMap;
		this.answerMap = answerMap;
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

	public void getResults() {

		NodeList nodeList = doc.getElementsByTagName("row");

		int length = nodeList.getLength();

		for (int j = 0; j < length; j++) {
			Element element = (Element) nodeList.item(j);

			String id = element.getAttribute("Id");
			String owner = element.getAttribute("DisplayName");

			if (id != null && owner != null) {

				mapIdPosts(postMap, id, owner);
				mapIdAnswers(answerMap, id, owner);

			}
		}
	}
}
