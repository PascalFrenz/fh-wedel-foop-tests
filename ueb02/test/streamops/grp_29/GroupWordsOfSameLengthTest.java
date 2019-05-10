package streamops.grp_29;

import com.google.common.collect.Sets;
import org.junit.Test;
import streamops.StreamOperations;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class GroupWordsOfSameLengthTest {

    @Test
    public void testGroupWordsOfSameLength_nullElements() {
        Stream<String> testStream = Stream.of("ab", "cd", null, "ef");

        Map<Integer, Set<String>> expected = new HashMap<>();
        expected.put(2, Sets.newHashSet("ab", "cd", "ef"));

        Map<Integer, Set<String>> result = StreamOperations.groupWordsOfSameLength(testStream);

        assertTrue(result.containsKey(2));
        assertEquals(expected.get(2), result.get(2));
    }

    @Test
    public void testGroupWordsOfSameLength_EasyTest() {
        Map<Integer, Set<String>> expected = new HashMap<>();
        expected.put(1, Sets.newHashSet("a", "b", "c"));
        expected.put(3, Sets.newHashSet("abc", "123", "def"));
        Stream.Builder<String> testStream = Stream.builder();
        testStream.add("a").add("b").add("c").add("abc").add("123").add("def");

        Map<Integer, Set<String>> result =
                StreamOperations.groupWordsOfSameLength(testStream.build());

        assertFalse(result.containsKey(0));
        assertFalse(result.containsKey(2));
        result.forEach((length, stringSet) -> {
            assertTrue(expected.containsKey(length));
            assertEquals(expected.get(length), stringSet);
        });
    }

    @Test
    public void testGroupWordsOfSameLength_CrazyInput() {
        Map<Integer, Set<String>> expected = new HashMap<>();
        expected.put(0, Sets.newHashSet(""));
        expected.put(9, Sets.newHashSet("123456789", "ldkrogjel"));
        Stream.Builder<String> testStream = Stream.builder();
        testStream.add("").add("123456789").add("ldkrogjel");

        Map<Integer, Set<String>> result =
                StreamOperations.groupWordsOfSameLength(testStream.build());

        assertFalse(result.containsKey(1));
        assertFalse(result.containsKey(2));
        result.forEach((length, stringSet) -> {
            assertTrue(expected.containsKey(length));
            assertEquals(expected.get(length), stringSet);
        });
    }
}
