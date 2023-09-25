import org.junit.Test;
import static org.junit.Assert.assertEquals;

import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;


/**
 * Sample JUnit test fixture for StringReassembly.
 *
 * @author Beckham Paul
 *
 */
public final class StringReassemblyTest {

    /**
     * Test of combination.
     */
    @Test
    public void testCombination() {
        String s1 = "iamtesting";
        String s2 = "testingalso";
        final int overlap = 7;
        String result = StringReassembly.combination(s1, s2, overlap);
        assertEquals("iamtestingalso", result);
    }
    /**
     * Test of combination.
     */
    @Test
    public void testCombinationTwo() {
        String s1 = "thismakessense";
        String s2 = "senseitmightmake";
        final int overlap = 5;
        String result = StringReassembly.combination(s1, s2, overlap);
        assertEquals("thismakessenseitmightmake", result);
    }
    /**
     * Test of AddToSetAvoidingSubstrings.
     */
    @Test
    public void testAddToSetAvoidingSubstrings() {
        Set<String> set1 = new Set1L<String>();
        set1.add("words");
        set1.add("more testing");
        Set<String> set2 = new Set1L<String>();
        set2.add("words");
        set2.add("more testing");
        String testString = "test";
        StringReassembly.addToSetAvoidingSubstrings(set1, testString);
        assertEquals(set1, set2);
    }
    /**
     * Test of AddToSetAvoidingSubstrings.
     */
    @Test
    public void testAddToSetAvoidingSubstringsTwo() {
        Set<String> set1 = new Set1L<String>();
        set1.add("the dog jumped over the brow cow");
        set1.add("America is a country");
        Set<String> set2 = new Set1L<String>();
        set2.add("the dog jumped over the brow cow");
        set2.add("America is a country");
        String testString = "cow";
        StringReassembly.addToSetAvoidingSubstrings(set1, testString);
        assertEquals(set1, set2);
    }
    /**
     * Test of assemble.
     * Not particularly useful but helpful also testing combination output.
     */
    @Test
    public void testAssemble() {
        SimpleReader inFile = new SimpleReader1L("data/cheer-8-2.txt");
        Set<String> fragments = StringReassembly.linesFromInput(inFile);
        inFile.close();
        StringReassembly.assemble(fragments);
        if (fragments.size() == 1) {
            String text = fragments.removeAny();
            assertEquals(text, "Go Bucks -- Beat Michigan~");
        }
    }
}