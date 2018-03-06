package ch.fhnw.richards.lecture06;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AddressFormatterTest {
	private ArrayList<TestCase> testCases = new ArrayList<>();
	private AddressFormatter testObject;

	private static class TestCase {
		String in;
		ArrayList<String> out = new ArrayList<>();
		int maxLength;
		
		public TestCase(String in, int maxLength, String... out) {
			this.in = in;
			this.maxLength = maxLength;
			for (String o : out) this.out.add(o);
		}
	}
	
	/**
	 * Create our test cases
	 */
	@Before
	public void setUp() throws Exception {
		testObject = new AddressFormatter();

		testCases = new ArrayList<>();
		
		// Edge cases
		testCases.add(new TestCase("", 5)); // Empty string, empty output
		testCases.add(new TestCase("asdf asdf", 3, "asd", "f", "asd", "f")); // max length 1 too short
		testCases.add(new TestCase("asdf asdf", 4, "asdf", "asdf")); // max length exactly correct
		testCases.add(new TestCase("asdf asdf", 5, "asdf", "asdf")); // max length 1 too generous

		// No split character
		testCases.add(new TestCase("Supercalifragilisticexpialidocious", 25, "Supercalifragilisticexpia", "lidocious"));
		testCases.add(new TestCase("Supercalifragilisticexpialidocious", 6, "Superc", "alifra", "gilist", "icexpi", "alidoc", "ious"));
		
		// Test all other split characters
		testCases.add(new TestCase("asdf,asdf", 4, "asdf", "asdf"));
		testCases.add(new TestCase("asdf.asdf", 4, "asdf", "asdf"));
		testCases.add(new TestCase("asdf;asdf", 4, "asdf", "asdf"));
		testCases.add(new TestCase("asdf:asdf", 4, "asdf", "asdf"));
		testCases.add(new TestCase("asdf/asdf", 4, "asdf", "asdf"));
		
		// Some standard splits
		testCases.add(new TestCase("Frau Evangeline Seraphinia O'Donoghue", 25, "Frau Evangeline", "Seraphinia O'Donoghue"));
		testCases.add(new TestCase("Frau Evangeline Seraphinia O'Donoghue", 15, "Frau Evangeline", "Seraphinia", "O'Donoghue"));
		testCases.add(new TestCase("Herr Gosukonda Venkata Satyanarayana Sastry", 25, "Herr Gosukonda Venkata", "Satyanarayana Sastry"));
		testCases.add(new TestCase("Herr Gosukonda Venkata Satyanarayana Sastry", 15, "Herr Gosukonda", "Venkata", "Satyanarayana", "Sastry"));
	}

	@Test
	public void testSplitLine() {
		for (TestCase testCase : testCases) {
			try {
				ArrayList<String> actualResult = testObject.splitLine(testCase.in, testCase.maxLength);
				ArrayList<String> expectedResult = testCase.out;
				assertEquals(actualResult.size(), expectedResult.size());
				for (int j = 0; j < actualResult.size(); j++) {
					assertEquals(actualResult.get(j), expectedResult.get(j));
				}
			} catch (Exception e) {
				fail();
			}
		}
	}
}
