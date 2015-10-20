import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class SortByValuesTest {

	SortByValues sort;

	@Before
	public void setup() {

		System.out.println("beginning sort by value test");
		sort = new SortByValues();

	}

	@Test
	public void compareTest() {

		User u1 = new User("45", 5);
		User u2 = new User("98", 2);

		int result = sort.compare(u1, u2);
		assertTrue("Expected to greater than", result == 1);

		int result_2 = sort.compare(u2, u1);
		assertTrue("Expected to less than", result_2 == -1);

	}

}
