
//user class where a user has a name and a count of posts/answers
public class User {

	String id;
	Integer count;

	
	public User(String id, int count) {
		this.id = id;
		this.count = count;
	}

	public String getId() {
		return id;
	}
	
	public Integer getCount() {
		return count;

	}
}
