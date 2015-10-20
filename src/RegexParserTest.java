import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RegexParserTest {

	String xmlLine = "<row Id='7' PostTypeId='1' AcceptedAnswerId='17' CreationDate='2009-04-30T07:57:09.117' Score='12' ViewCount='529' Body='&lt;p&gt;What enterprise virus-scanning systems do you recommend?&lt;/p&gt;&#xA;' OwnerUserId='32' LastActivityDate='2009-04-30T11:51:09.290' Title='What is the best enterprise virus-scanning system?' Tags='&lt;antivirus&gt;' AnswerCount='8' CommentCount='3' FavoriteCount='2' />";
	RegexParser regex = new RegexParser();

	@Before
	public void setup() {

		System.out.println("beginning regex test");

	}

	@Test
	public void getElementTest() {

		String result = regex.getElement("Id", xmlLine);
		assertEquals("7", result);
	}

	@Test
	public void getNodeTest() {

		String result = regex.getNode("row", xmlLine);
		String trueResult = "Id='7' PostTypeId='1' AcceptedAnswerId='17' CreationDate='2009-04-30T07:57:09.117' Score='12' ViewCount='529' Body='&lt;p&gt;What enterprise virus-scanning systems do you recommend?&lt;/p&gt;&#xA;' OwnerUserId='32' LastActivityDate='2009-04-30T11:51:09.290' Title='What is the best enterprise virus-scanning system?' Tags='&lt;antivirus&gt;' AnswerCount='8' CommentCount='3' FavoriteCount='2' ";
		assertEquals(result, trueResult);
	}

}
