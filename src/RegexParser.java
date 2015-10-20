import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexParser {

	// method to grab the node name, returns the capturing group which is the
	// attributes in that line
	public String getNode(String patt, String line) {

		String node = null;

		String pattern1 = "<" + patt + "\\s(.*)\\/>";
		Pattern p = Pattern.compile(pattern1);

		Matcher m = p.matcher(line);

		if (m.find()) {

			node = m.group(1);

		}
		return node;
	}

	// method to grab particular attribute values, return the capturing group
	// containing value
	public String getElement(String patt, String line) {

		String element = null;

		// String pattern = "^(?=.*\b" + patt1 + "\\b=\"?([.\\d])+)(?=.*\\b" +
		// patt2 + " \\b=\"?([.\\d])+).*$";

		String pattern = patt + "=[\"\']?([a-zA-Z\\d\\s-]+)[\"\']?";

		Pattern p = Pattern.compile(pattern);

		Matcher m = p.matcher(line);

		if (m.find()) {

			element = m.group(1);

		}

		return element;
	}
}
