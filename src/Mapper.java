import java.util.Iterator;
import java.util.TreeSet;

public class Mapper {

	// sets to hold the final top 10 names and counts
	TreeSet<User> postNames = new TreeSet<User>(new SortByValues());
	TreeSet<User> answerNames = new TreeSet<User>(new SortByValues());

	public TreeSet<User> getPostNames() {
		return postNames;
	}

	public TreeSet<User> getAnswerNames() {
		return answerNames;
	}

	// method that looks for id in top10 and sets the name and count in the new
	// set
	public void mapIdPosts(TreeSet<User> top10Posts, String id, String name) {

		Iterator it = top10Posts.iterator();
		while (it.hasNext()) {
			User pair = (User) it.next();
			if (id.equals(pair.getId())) {

				postNames.add(new User(name, pair.getCount()));
			}

		}

	}

	// method that looks for id in top10 and sets the name and count in the new
	// set
	public void mapIdAnswers(TreeSet<User> top10Answers, String id, String name) {

		Iterator it = top10Answers.iterator();
		while (it.hasNext()) {
			User pair = (User) it.next();
			if (id.equals(pair.getId())) {

				answerNames.add(new User(name, pair.getCount()));
			}

		}

	}

	// method to print results of final top10s
	public void printMap(TreeSet<User> top10) {
		Iterator it = top10.iterator();
		while (it.hasNext()) {
			User pair = (User) it.next();
			System.out.println(pair.getId() + " = " + pair.getCount());
			it.remove();
		}
	}

}
