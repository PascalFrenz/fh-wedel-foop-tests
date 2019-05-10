package streamops.grp_29;

import org.junit.Test;
import streamops.StreamOperations;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;


public class EvaluateTest {

    @Test(expected = IllegalArgumentException.class)
    public void testEvaluate_EmptyStream() {
        Map<String, String> replacement = new HashMap<>();
        Stream<String> evaluationStream = Stream.of();

        StreamOperations.evaluate(evaluationStream, replacement);
    }

    @Test
    public void testEvaluate_NoReplacement() {
        Map<String, String> replacement = new HashMap<>();
        Stream<String> evaluationStream = Stream.of("5", "+", "5", "-", "13", "*", "4", "/", "2");

        Integer expected = (((5 + 5) - 13) * 4) / 2;
        Integer result = StreamOperations.evaluate(evaluationStream, replacement);

        assertEquals(expected, result);
    }

    @Test
    public void testEvaluate_NumberReplacement() {
        Map<String, String> replacement = new HashMap<>();
        replacement.put("Fünf", "5");
        replacement.put("fünf", "5");
        replacement.put("Drei", "3");

        Stream<String> evaluationStream = Stream.of("Fünf", "+", "fünf", "-", "Drei", "*", "4", "/",
                "2"
        );

        Integer expected = (((5 + 5) - 3) * 4) / 2;
        Integer result = StreamOperations.evaluate(evaluationStream, replacement);

        assertEquals(expected, result);
    }

    @Test
    public void testEvaluate_NumberAndOperatorReplacement() {
        Map<String, String> replacement = new HashMap<>();
        replacement.put("Fünf", "5");
        replacement.put("fünf", "5");
        replacement.put("Drei", "3");
        replacement.put("plus", "+");
        replacement.put("durch", "/");

        Stream<String> evaluationStream = Stream.of("Fünf", "plus", "fünf", "-", "Drei", "*", "4",
                "durch", "2"
        );

        Integer expected = (((5 + 5) - 3) * 4) / 2;
        Integer result = StreamOperations.evaluate(evaluationStream, replacement);

        assertEquals(expected, result);
    }

    @Test
    public void testEvaluate_NonIntegerResult() {
        Map<String, String> replacement = new HashMap<>();

        Stream<String> testStream = Stream.of("7", "/", "2");
        Integer expected = 7 / 2;
        Integer result = StreamOperations.evaluate(testStream, replacement);

        assertEquals(expected, result);
    }

    @Test
    public void testEvaluate_ModuloOperation() {
        Map<String, String> replacement = new HashMap<>();

        Stream<String> testStream = Stream.of("7", "%", "2");
        Integer expected = 7 % 2;
        Integer result = StreamOperations.evaluate(testStream, replacement);

        assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEvaluate_DecimalInput() {
        Map<String, String> replacement = new HashMap<>();
        Stream<String> evaluationStream = Stream.of("5.5", "+", "5", "-", "3", "*", "4", "/", "2");

        StreamOperations.evaluate(evaluationStream, replacement);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEvaluate_WrongNumberInput() {
        Map<String, String> replacement = new HashMap<>();
        Stream<String> evaluationStream = Stream.of("abc", "+", "5", "-", "3", "*", "4", "/", "2");

        StreamOperations.evaluate(evaluationStream, replacement);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEvaluate_EmptyNumberInput() {
        Map<String, String> replacement = new HashMap<>();
        Stream<String> evaluationStream = Stream.of("", "+", "5", "-", "3", "*", "4", "/", "2");

        StreamOperations.evaluate(evaluationStream, replacement);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEvaluate_WrongOperatorInput() {
        Map<String, String> replacement = new HashMap<>();
        Stream<String> evaluationStream = Stream.of("5", "a", "5", "-", "3", "*", "4", "/", "2");

        StreamOperations.evaluate(evaluationStream, replacement);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEvaluate_EmptyOperatorInput() {
        Map<String, String> replacement = new HashMap<>();
        Stream<String> evaluationStream = Stream.of("5", "", "5", "-", "3", "*", "4", "/", "2");

        StreamOperations.evaluate(evaluationStream, replacement);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEvaluate_WrongStreamOrderUnexpectedNumber() {
        Map<String, String> replacement = new HashMap<>();
        Stream<String> evaluationStream = Stream.of("5", "2", "5", "-", "3", "*", "4", "/", "2");

        StreamOperations.evaluate(evaluationStream, replacement);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEvaluate_WrongStreamOrderUnexpectedOperator() {
        Map<String, String> replacement = new HashMap<>();
        Stream<String> evaluationStream = Stream.of("5", "+", "+", "2", "3", "*", "4", "/", "2");

        StreamOperations.evaluate(evaluationStream, replacement);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEvaluate_WrongStreamOrderNotEnoughInput() {
        Map<String, String> replacement = new HashMap<>();
        Stream<String> evaluationStream = Stream.of("5", "+", "+", "2", "3", "*", "4", "/");

        StreamOperations.evaluate(evaluationStream, replacement);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEvaluate_OperatorAtBeginning() {
        Map<String, String> replacement = new HashMap<>();
        Stream<String> evaluationStream = Stream.of("+", "5", "-", "3");

        StreamOperations.evaluate(evaluationStream, replacement);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEvaluate_OperatorAtEnd() {
        Map<String, String> replacement = new HashMap<>();
        Stream<String> evaluationStream = Stream.of("5", "*", "5", "-", "3", "/");

        StreamOperations.evaluate(evaluationStream, replacement);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEvaluate_TwoOperatorsAtEnd() {
        Map<String, String> replacement = new HashMap<>();
        Stream<String> evaluationStream = Stream.of("5", "*", "5", "-", "3", "/", "*");

        StreamOperations.evaluate(evaluationStream, replacement);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEvaluate_SpaceInOperand() {
        Map<String, String> replacement = new HashMap<>();
        Stream<String> evaluationStream = Stream.of(" 5", "*", "5", "-", "3");

        StreamOperations.evaluate(evaluationStream, replacement);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEvaluate_SpaceInOperator() {
        Map<String, String> replacement = new HashMap<>();
        Stream<String> evaluationStream = Stream.of("5", " *", "5", "-", "3");

        StreamOperations.evaluate(evaluationStream, replacement);
    }
}
