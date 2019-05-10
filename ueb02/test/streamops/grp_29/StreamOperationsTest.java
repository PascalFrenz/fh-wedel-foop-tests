package streamops.grp_29;

import org.junit.Test;
import streamops.StreamOperations;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class StreamOperationsTest {

    private static boolean testDivisorsMethod(int n, List<Integer> expectation) {
        Set<Integer> expected = new HashSet<>(expectation);
        Set<Integer> found = StreamOperations.divisors(n).collect(Collectors.toSet());

        return expected.containsAll(found) && found.containsAll(expected);
    }

    @Test(expected = AssertionError.class)
    public void testDivisors_Assertion() {
        StreamOperations.divisors(0);
    }

    @Test(expected = AssertionError.class)
    public void testIsPrime_Assertion() {
        StreamOperations.isPrime(0);
    }

    @Test
    public void testDivisors() {
        assertTrue(testDivisorsMethod(2, Collections.emptyList()));
        assertTrue(testDivisorsMethod(5, Collections.emptyList()));
        assertTrue(testDivisorsMethod(7, Collections.emptyList()));
        assertTrue(testDivisorsMethod(10, Arrays.asList(2, 5)));
        assertTrue(testDivisorsMethod(65537, Collections.emptyList()));
    }

    @Test
    public void testIsPrime() {
        assertFalse(StreamOperations.isPrime(1));

        assertTrue(StreamOperations.isPrime(2));
        assertTrue(StreamOperations.isPrime(3));
        assertTrue(StreamOperations.isPrime(5));
        assertTrue(StreamOperations.isPrime(7));
        assertTrue(StreamOperations.isPrime(11));
        assertTrue(StreamOperations.isPrime(13));
        assertTrue(StreamOperations.isPrime(65537));

        assertFalse(StreamOperations.isPrime(4));
        assertFalse(StreamOperations.isPrime(6));
        assertFalse(StreamOperations.isPrime(10));
        assertFalse(StreamOperations.isPrime(20));
        assertFalse(StreamOperations.isPrime(100));
        assertFalse(StreamOperations.isPrime(98732402));
    }

    @Test
    public void testFibs() {
        List<Integer> fibs = Arrays.asList(1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610,
                                           987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368,
                                           75025, 121393, 196418, 317811, 514229, 832040, 1346269);

        List<BigInteger> generatedFibs = StreamOperations
                .fibs()
                .limit(fibs.size())
                .collect(Collectors.toList());

        for (int i = 0; i < fibs.size(); i++) {
            BigInteger fib = BigInteger.valueOf(fibs.get(i));
            assertEquals(fib, generatedFibs.get(i));
        }
    }

    @Test
    public void testStringsToChars() {
        Stream<String> input = Stream.of("ab", "cda", "dex");

        Character[] expected = new Character[]{'a', 'b', 'c', 'd', 'a', 'd', 'e', 'x'};
        Character[] result = StreamOperations.stringsToChars(input).toArray(Character[]::new);

        assertEquals(expected.length, result.length);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testStringsToChars_NoStringGiven() {
        Stream<String> input = Stream.of();

        Character[] expected = new Character[0];
        Character[] result = StreamOperations.stringsToChars(input).toArray(Character[]::new);

        assertArrayEquals(expected, result);
    }

    @Test
    public void testStringsToChars_EmptyString() {
        Stream<String> input = Stream.of("");

        Character[] expected = new Character[0];
        Character[] result = StreamOperations.stringsToChars(input).toArray(Character[]::new);

        assertArrayEquals(expected, result);
    }

    @Test
    public void testCountChars() {
        Map<Character, Integer> expected = new HashMap<>();
        expected.put('a', 5);
        expected.put('b', 5);
        expected.put('c', 5);
        Stream<Character> testStream = StreamOperations.stringsToChars(
                Stream.of("aaaaabbbbbccccc")
        );

        Map<Character, Integer> result =
                StreamOperations.countChars(testStream, 'a', 'c', null);

        assertTrue(result.containsKey('a'));
        assertEquals(expected.get('a'), result.get('a'));

        assertTrue(result.containsKey('b'));
        assertEquals(expected.get('b'), result.get('b'));

        assertTrue(result.containsKey('c'));
        assertEquals(expected.get('c'), result.get('c'));

        assertFalse(result.containsKey('d'));

    }

    @Test
    public void testCountChars_boundsAreInclusive() {
        Map<Character, Integer> expected = new HashMap<>();
        expected.put('b', 1);
        expected.put('c', 1);
        expected.put('d', 1);

        Stream<Character> testStream = Stream.of('b', 'c', 'd');
        Map<Character, Integer> result =
                StreamOperations.countChars(testStream, 'b', 'd', null);

        assertEquals(expected, result);
    }

    @Test
    public void testCountChars_FromIsNull() {
        Map<Character, Integer> expected = new HashMap<>();
        expected.put('a', 5);
        expected.put('b', 5);
        expected.put('c', 5);
        Stream<Character> testStream = StreamOperations.stringsToChars(
                Stream.of("aaaaabbbbbccccc")
        );

        Map<Character, Integer> result =
                StreamOperations.countChars(testStream, null, 'c', null);

        assertTrue(result.containsKey('a'));
        assertEquals(expected.get('a'), result.get('a'));

        assertTrue(result.containsKey('b'));
        assertEquals(expected.get('b'), result.get('b'));

        assertTrue(result.containsKey('c'));
        assertEquals(expected.get('c'), result.get('c'));

        assertFalse(result.containsKey('d'));
    }
}