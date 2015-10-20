import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CustomPosts extends Counter {

	String file;

	public CustomPosts(String file) {

		this.file = file;
	}

	RegexParser regex = new RegexParser();

	public void parse() {
		try

		(BufferedReader br = new BufferedReader(new FileReader(file))) {

			// reads file line by line and grabs any row with start tag "row"
			String line = br.readLine();
			while (line != null) {
				String row = regex.getNode("row", line);
				if (row != null) {

					// match required attributes adn update the hashmap and set
					// with id and count
					String postType = regex.getElement("PostTypeId", row);
					String owner = regex.getElement("OwnerUserId", row);

					if (postType != null && owner != null) {

						if (postType.equals("1") && !owner.equals("")) {

							updateCount(owner, userPosts, top10Posts);
						}
						if (postType.equals("2") && !owner.equals("")) {

							updateCount(owner, userAnswers, top10Answers);
						}

					}

				}

				line = br.readLine();
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

}
