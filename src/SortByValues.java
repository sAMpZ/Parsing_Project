import java.util.Comparator;

//customer sorter for top 10 tree sets
public class SortByValues implements Comparator<User> {

	@Override
	public int compare(User e1, User e2) {

		// compare the integer counts
		Integer res = e1.getCount().compareTo(e2.getCount());

		// first line tries to ensure there are no id duplicates but not working
		if (e1.getId().equals(e2.getId())) {
			return res;

		} else {
			return res != 0 ? res : 1;
		}
	}

}
