import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

public class CustomMaps extends Mapper {

	String file;
	TreeSet<User> postMap;
	TreeSet<User> answerMap;

	public CustomMaps(String file, TreeSet<User> postMap,
			TreeSet<User> answerMap) {
		this.file = file;
		this.postMap = postMap;
		this.answerMap = answerMap;
	}

	RegexParser regex = new RegexParser();

	public void parse() {
		try

		(BufferedReader br = new BufferedReader(new FileReader(file))) {

			String line = br.readLine();
			while (line != null) {
				String row = regex.getNode("row", line);
				if (row != null) {

					String id = regex.getElement("Id", row);
					String owner = regex.getElement("DisplayName", row);

					if (id != null && owner != null) {

						// checks if the id exists in top ten and adds name
						mapIdPosts(postMap, id, owner);
						mapIdAnswers(answerMap, id, owner);

					}

				}

				line = br.readLine();
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

}
