import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public abstract class Counter {

	// map hold all post owner ids and counts
	Map<String, Integer> userPosts = new HashMap<String, Integer>();

	// custom sorted holding top 10 users by posts
	TreeSet<User> top10Posts = new TreeSet<User>(new SortByValues());

	// map holding all answer owner ids and count
	Map<String, Integer> userAnswers = new HashMap<String, Integer>();

	// customer sorted set holding top 10 users by answers
	TreeSet<User> top10Answers = new TreeSet<User>(new SortByValues());

	public TreeSet<User> getTopPosts() {

		return top10Posts;
	}

	public TreeSet<User> getTopAnswers() {

		return top10Answers;
	}

	public abstract void parse();

	// method to increment counts and update top10s
	public void updateCount(String id, Map<String, Integer> allUsers,
			TreeSet<User> top10) {

		// get the current count for the user
		Integer value = allUsers.get(id);
		if (value == null) {
			value = 0;
		}

		value++;

		// add the updated count to the hashmap
		allUsers.put(id, value);

		// add user and count to top 10 if the count is greater than the current

		// or if the set has not yet reached the required size
		if (top10.size() < 10 || top10.first().getCount() <= value) {

			top10.remove(new User(id, value - 1));
			top10.add(new User(id, value));
		}

		// remove lowest element with lowest count if the set is greater than 10
		if (top10.size() > 10) {

			top10.pollFirst();
		}
	}

}
