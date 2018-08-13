package anagram.svc;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests logic of the Anagram Generator
 * @author slasisi
 *
 */
public class Test_Anagram {

	AnagramGenerator testAnagram;
	
	@Before
	public void beforeTest() {
		testAnagram = new AnagramGenerator();
	}
	
	@After
	public void afterTest() {
		testAnagram = null;
	}
	
	  @Test
	    public void testAnagram() {
	    			String anagrams = testAnagram.loadWord("advice").getAnagrams();
	    			int length = anagrams.split("\\n").length;
	    			assertTrue(length == 720);
	    			anagrams = testAnagram.loadWord("dove").getAnagrams();
	    			length = anagrams.split("\\n").length;
	    			assertTrue(length == 24);
	    			anagrams = testAnagram.loadWord("love").getAnagrams();
	    			length = anagrams.split("\\n").length;
	    			assertTrue(length == 24);
	    }

}
