package streamops.grp_29;

import org.junit.Assert;
import org.junit.Test;
import streamops.StreamOperations;
import streamops.TestResult;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;


public class AveraveTestResultTest {

    @Test
    public void testAverageTestResult_NoResultsFromMachine() {
        Stream<TestResult> testResults = Stream.of(
                new TestResult("FirstMachine", 3.3),
                new TestResult("FirstMachine", 5.5),
                new TestResult("ThirdMachine", 2.2),
                new TestResult("FirstMachine", 0.9)
        );

        Assert.assertEquals(0,
                StreamOperations.averageTestResult(testResults, "SecondMachine"), 0.001
        );
    }

    @Test
    public void testAverageTestResult_MixedMachineResults() {
        Stream<TestResult> testResults = Stream.of(
                new TestResult("FirstMachine", 3.3),
                new TestResult("FirstMachine", 5.5),
                new TestResult("ThirdMachine", 2.2),
                new TestResult("FirstMachine", 0.9),
                new TestResult("ThirdMachine", 9)
        );

        assertEquals((3.3 + 5.5 + 0.9) / 3,
                StreamOperations.averageTestResult(testResults, "FirstMachine"), 0.001
        );
    }

    @Test
    public void testAverageTestResult_OnlyOneMachine() {
        Stream<TestResult> testResults = Stream.of(
                new TestResult("FirstMachine", 3.3),
                new TestResult("FirstMachine", 5.5),
                new TestResult("FirstMachine", 2.2),
                new TestResult("FirstMachine", 0.9),
                new TestResult("FirstMachine", 9)
        );

        assertEquals((3.3 + 5.5 + 0.9 + 2.2 + 9) / 5,
                StreamOperations.averageTestResult(testResults, "FirstMachine"), 0.001
        );
    }

}
