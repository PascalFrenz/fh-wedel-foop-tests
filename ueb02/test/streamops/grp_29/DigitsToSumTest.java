package streamops.grp_29;

import org.junit.Test;
import streamops.StreamOperations;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;


public class DigitsToSumTest {

    @Test(expected = AssertionError.class)
    public void testDigits2Num_Assertion() {
        Integer[] input = new Integer[]{2,4,6,7,2,10};
        StreamOperations.digits2num(Arrays.stream(input));
    }

    @Test
    public void testDigits2Num_01() {
        Integer[] input = new Integer[]{2,4,6,7,2};
        BigInteger result = StreamOperations.digits2num(Arrays.stream(input));

        assertEquals(BigInteger.valueOf(24672), result);
    }

    @Test
    public void testDigits2Num_02() {
        Stream<Integer> stream = Stream.of(9,8,7,6,5,4,4,3,2,1,6,7,8,9,4,5,6,7);
        BigInteger expected = new BigInteger("987654432167894567");
        BigInteger result = StreamOperations.digits2num(stream);

        assertEquals(expected, result);
    }

    @Test
    public void testDigits2Num_03() {
        Stream<Integer> stream = Stream.of(0);
        BigInteger expected = new BigInteger("0");
        BigInteger result = StreamOperations.digits2num(stream);

        assertEquals(expected, result);
    }
}
