
public class Main {

	public static void main(String[] args) {

		String postFile = "/posts.xml";
		String userFile = "/users.xml";

		/*
		 * DOM
		 */
		long DOMStart = System.currentTimeMillis();
		DOMPosts domPostParser = new DOMPosts(postFile);

		// tries to parse to document
		domPostParser.parse();

		if (domPostParser.doc != null) {

			domPostParser.getInfo();
		}

		DOMMaps domNameMap = new DOMMaps(userFile, domPostParser.getTopPosts(),
				domPostParser.getTopAnswers());

		domNameMap.parse();

		if (domNameMap.doc != null) {

			domNameMap.getResults();
		}

		System.out.println("Top 10 Posts using DOM \n");
		domNameMap.printMap(domNameMap.getPostNames());

		System.out.println("Top 10 Answers using DOM \n");
		domNameMap.printMap(domNameMap.getAnswerNames());

		long DOMEnd = System.currentTimeMillis();
		System.out.println("Using the DOM parser execution time was"
				+ (DOMEnd - DOMStart) +"\n");

		/*
		 * SAX
		 */

		long SAXStart = System.currentTimeMillis();

		SAXPosts saxPostParser = new SAXPosts(postFile);

		// tries to parse to document
		saxPostParser.parse();

		SAXMaps saxNameMap = new SAXMaps(userFile, saxPostParser.getTopPosts(),
				saxPostParser.getTopAnswers());

		saxNameMap.parse();

		System.out.println("Top 10 Posts using SAX \n");
		saxNameMap.printMap(saxNameMap.getPostNames());

		System.out.println("Top 10 Answers using SAX \n");
		saxNameMap.printMap(saxNameMap.getAnswerNames());

		long SAXEnd = System.currentTimeMillis();
		System.out.println("Using the SAX parser execution time was"
				+ (SAXEnd - SAXStart)+"\n");

		/*
		 * CUSTOM
		 */

		long CustomStart = System.currentTimeMillis();
		CustomPosts customPostParser = new CustomPosts(postFile);

		customPostParser.parse();

		CustomMaps customMapParser = new CustomMaps(userFile,
				customPostParser.getTopPosts(),
				customPostParser.getTopAnswers());

		customMapParser.parse();
		System.out.println("Top 10 Posts using simple customer parser \n");
		customMapParser.printMap(customMapParser.getPostNames());

		System.out.println("Top 10 Answers using simple customer parser \n");
		customMapParser.printMap(customMapParser.getAnswerNames());

		long CustomEnd = System.currentTimeMillis();
		System.out.println("Using the custom parser execution time was"
				+ (CustomEnd - CustomStart)+"\n");

	}

}
