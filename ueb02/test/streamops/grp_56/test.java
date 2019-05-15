import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import streamops.StreamOperations;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import streamops.IncomeClass;
import streamops.Person;
import streamops.TestResult;

public class test {



    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void divisors1() {
        Stream<Integer> divisor = StreamOperations.divisors(1);
        Integer[] testArray = {};

        assertArrayEquals(testArray, divisor.toArray());
    }

    @Test
    public void divisors2() {
        Stream<Integer> divisor = StreamOperations.divisors(2);
        Integer[] testArray = {};

        assertArrayEquals(testArray, divisor.toArray());
    }

    @Test
    public void divisors3() {
        Stream<Integer> divisor = StreamOperations.divisors(3);
        Integer[] testArray = {};

        assertArrayEquals(testArray, divisor.toArray());
    }

    @Test
    public void divisors4() {
        Stream<Integer> divisor = StreamOperations.divisors(4);
        Integer[] testArray = {2};

        assertArrayEquals(testArray, divisor.toArray());
    }

    @Test
    public void divisors20() {
        Stream<Integer> divisor = StreamOperations.divisors(20);
        Integer[] testArray = {2, 4, 5, 10};

        assertArrayEquals(testArray, divisor.toArray());
    }

    @Test
    public void divisors105() {
        Stream<Integer> divisor = StreamOperations.divisors(20);
        Integer[] testArray = {2, 4, 5, 10};

        assertArrayEquals(testArray, divisor.toArray());
    }

    @Test
    public void isPrime11() {
        Assert.assertTrue(StreamOperations.isPrime(11));
    }
/*
    @Test
    public void isPrimeMaxInt() {
        Assert.assertTrue(StreamOperations.isPrime(Integer.MAX_VALUE));
    }*/

    @Test
    public void isNotPrime63() {
        Assert.assertFalse(StreamOperations.isPrime(63));
    }

    @Test(expected = AssertionError.class)
    public void isPrime() {
        StreamOperations.isPrime(-1);
    }

    @Test(expected = AssertionError.class)
    public void isPrimeZero(){ StreamOperations.isPrime(0); }

    @Test
    public void isPrimeOne(){ Assert.assertFalse(StreamOperations.isPrime(1)); }

    @Test
    public void isPrimeTwo(){ Assert.assertTrue(StreamOperations.isPrime(2)); }

    @Test
    public void fibs() {
        String[] fibsArray = StreamOperations.fibs().limit(7).map(BigInteger::toString).toArray(String[]::new);

        String[] testArray = {"1", "1", "2", "3", "5", "8", "13"};
        assertArrayEquals(testArray, fibsArray);
    }

    @Test
    public void fibs2() {
        String[] fibsArray = StreamOperations.fibs().limit(33).map(BigInteger::toString).toArray(String[]::new);

        String[] testArray = {"1", "1", "2", "3", "5", "8", "13", "21", "34",
                "55", "89", "144", "233", "377", "610", "987", "1597", "2584",
                "4181", "6765", "10946", "17711", "28657", "46368", "75025",
                "121393", "196418", "317811", "514229", "832040", "1346269",
                "2178309", "3524578"};
        assertArrayEquals(testArray, fibsArray);
    }

    @Test
    public void groupWordsOfSameLength() {
        Set<String> strings1 = new HashSet<>(Arrays.asList("A", "B", "C"));
        Set<String> strings2 = new HashSet<>(Arrays.asList("AA", "BB", "CC"));
        Set<String> strings4 = new HashSet<>(Arrays.asList("AAAA", "BBBB", "CCCC"));

        List<String> stringList = new ArrayList<>(Arrays.asList("A", "B", "C", "AA", "BB", "CC", "AAAA", "BBBB", "CCCC"));

        Map<Integer, Set<String>> expected = new HashMap<>();
        expected.put(1, strings1);
        expected.put(2, strings2);
        expected.put(4, strings4);

        Assert.assertEquals(expected, StreamOperations.groupWordsOfSameLength(stringList.stream()));
    }

    @Test
    public void groupWordsOfSameLength2() {
        Set<String> strings1 = new HashSet<>(Arrays.asList("A", "B", "C"));
        Set<String> strings2 = new HashSet<>(Arrays.asList("AA", "BB", "CC"));
        Set<String> strings4 = new HashSet<>(Arrays.asList("AAAA", "BBBB", "CCCC"));

        List<String> stringList = new ArrayList<>(Arrays.asList("AAAA", "B", "CC", "AA", "BBBB", "C", "A", "BB", "CCCC"));

        Map<Integer, Set<String>> expected = new HashMap<>();
        expected.put(1, strings1);
        expected.put(2, strings2);
        expected.put(4, strings4);

        Assert.assertEquals(expected, StreamOperations.groupWordsOfSameLength(stringList.stream()));
    }

    @Test
    public void groupWordsOfSameLength3() {
        Map<Integer, Set<String>> expected = new HashMap<>();
        List<String> stringList = new ArrayList<>();

        Assert.assertTrue(StreamOperations.groupWordsOfSameLength(stringList.stream()).isEmpty());
    }

    @Test
    public void countCharsOnlyLowerBound(){
        Map<Character, Integer> expected = new HashMap<>();
        Character from = 'r';



        Stream<Character> stream = new ArrayList<>(Arrays.asList('q','r','u','g','r','q')).stream();
        expected.put('r', 2);
        expected.put('u', 1);


        Assert.assertEquals(expected, StreamOperations.countChars(stream, from, null, null));
    }

    @Test
    public void countCharsOnlyUpperBound(){
        Map<Character, Integer> expected = new HashMap<>();
        Character to = 'r';

        Stream<Character> stream = new ArrayList<>(Arrays.asList('q','r','u','g','r','q')).stream();
        expected.put('r', 2);
        expected.put('q', 2);
        expected.put('g', 1);

        Assert.assertEquals(expected, StreamOperations.countChars(stream, null, to, null));
    }

    @Test
    public void countCharsLowerAndUpperBound(){
        Map<Character, Integer> expected = new HashMap<>();
        Character from = 'r';
        Character to = 'v';

        Stream<Character> stream = new ArrayList<>(Arrays.asList('q','r','u','g','r','q','w')).stream();
        expected.put('r', 2);
        expected.put('u', 1);

        Assert.assertEquals(expected, StreamOperations.countChars(stream, from, to, null));
    }

    @Test
    public void countCharsOnlyMaxChars(){
        Map<Character, Integer> expected = new HashMap<>();
        long max = 5;

        Stream<Character> stream = new ArrayList<>(Arrays.asList('q','r','u','g','r','q','w')).stream();
        expected.put('q', 1);
        expected.put('r', 2);
        expected.put('u', 1);
        expected.put('g', 1);

        Assert.assertEquals(expected, StreamOperations.countChars(stream, null, null, max));
    }

    @Test
    public void countCharsOnlyMaxCharsAndLower(){
        Map<Character, Integer> expected = new HashMap<>();
        long max = 5;
        Character from = 'r';

        Stream<Character> stream = new ArrayList<>(Arrays.asList('q','r','u','g','r','q','w')).stream();
        expected.put('r', 2);
        expected.put('u', 1);

        Assert.assertEquals(expected, StreamOperations.countChars(stream, from, null, max));
    }

    @Test
    public void countCharsOnlyMaxCharsAndUpper(){
        Map<Character, Integer> expected = new HashMap<>();
        long max = 5;
        Character to = 'r';

        Stream<Character> stream = new ArrayList<>(Arrays.asList('q','r','u','g','r','q','w')).stream();
        expected.put('g', 1);
        expected.put('q', 1);
        expected.put('r', 2);

        Assert.assertEquals(expected, StreamOperations.countChars(stream, null, to, max));
    }

    @Test
    public void countCharsOnlyMaxCharsAndUpperAndLower(){
        Map<Character, Integer> expected = new HashMap<>();
        long max = 7;
        Character from = 'r';
        Character to = 'v';

        Stream<Character> stream = new ArrayList<>(Arrays.asList('q','r','u','w','r','q','g')).stream();
        expected.put('r', 2);
        expected.put('u', 1);

        Assert.assertEquals(expected, StreamOperations.countChars(stream, from, to, max));
    }


    @Test
    public void stringsToChars() {
        List<Character> expectedList = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e'));
        List<String> input = new ArrayList<>(Arrays.asList("abc", "de"));


        Assert.assertArrayEquals(expectedList.toArray(), StreamOperations.stringsToChars(input.stream()).toArray());
    }


    @Test
    public void digits2num() {
        Stream<Integer> stream = Stream.of(1, 3, 4, 5, 6);
        BigInteger bigInteger = StreamOperations.digits2num(stream);

        String testString = "13456";

        assertEquals(testString, bigInteger.toString());
    }

    @Test
    public void digits2numOneNumber() {
        Stream<Integer> stream = Stream.of(8,3,3,3,3,3,3,3,2,3,3,2,4);
        BigInteger bigInteger = StreamOperations.digits2num(stream);

        String testString = "8333333323324";

        assertEquals(new BigInteger(testString).compareTo(bigInteger), 0);
    }

    @Test
    public void digits2numUnordered() {
        Stream<Integer> stream = Stream.of(8, 4, 9, 2, 4, 7, 1);
        BigInteger bigInteger = StreamOperations.digits2num(stream);

        String testString = "8492471";

        assertEquals(new BigInteger(testString).compareTo(bigInteger), 0);
    }

    @Test
    public void averageTestResult() {
        Stream.Builder<TestResult> results = Stream.builder();
        results.add(new TestResult("1", 3));
        results.add(new TestResult("2", 3));
        results.add(new TestResult("3", 3));
        results.add(new TestResult("1", 4));
        results.add(new TestResult("1", 2));

        double average = StreamOperations.averageTestResult(results.build(), "1");

        assertEquals(3.0d, average, 0.001);
    }

    @Test
    public void averageTestResultDecimal() {
        Stream.Builder<TestResult> results = Stream.builder();
        results.add(new TestResult("1", 3));
        results.add(new TestResult("2", 3));
        results.add(new TestResult("3", 3));
        results.add(new TestResult("1", 5));
        results.add(new TestResult("1", 2));

        double average = StreamOperations.averageTestResult(results.build(), "1");

        assertEquals(3.333d, average, 0.001);
    }

    @Test
    public void averageTestResultNoObjects() {
        Stream.Builder<TestResult> results = Stream.builder();

        double average = StreamOperations.averageTestResult(results.build(), "1");

        assertEquals(0d, average, 0.001);
    }

    @Test
    public void averageTestResultIdNotFound() {
        Stream.Builder<TestResult> results = Stream.builder();
        results.add(new TestResult("1", 3));
        results.add(new TestResult("2", 3));
        results.add(new TestResult("3", 3));
        results.add(new TestResult("1", 5));
        results.add(new TestResult("1", 2));

        double average = StreamOperations.averageTestResult(results.build(), "4");

        assertEquals(0d, average, 0.001);
    }

    @Test
    public void averageTestResultNegative() {
        Stream.Builder<TestResult> results = Stream.builder();
        results.add(new TestResult("1", -3));
        results.add(new TestResult("2", 3));
        results.add(new TestResult("3", 3));
        results.add(new TestResult("1", -10));
        results.add(new TestResult("1", -2));

        double average = StreamOperations.averageTestResult(results.build(), "1");

        assertEquals(-5d, average, 0.001);
    }


    @Test(expected = IllegalArgumentException.class)
    public void evaluateEmptyStreamThrows() {
        Stream<String> input = new ArrayList<String>().stream();

        StreamOperations.evaluate(input, new HashMap<>());

    }

    @Test(expected = IllegalArgumentException.class)
    public void evaluateBrokenIntThrows() {
        Stream<String> input = Stream.of("3,4", "+", "2");
        StreamOperations.evaluate(input, new HashMap<>());

    }

    @Test(expected = IllegalArgumentException.class)
    public void evaluateWrongOperatorThrows() {
        Stream<String> input = Stream.of("3", "$", "2");
        StreamOperations.evaluate(input, new HashMap<>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void evaluateWrongOrderThrows() {
        Stream<String> input = Stream.of("3", "2", "-");
        StreamOperations.evaluate(input, new HashMap<>());
    }

    @Test
    public void evaluateReplacement() {
        Map<String, String> replacement = new HashMap<>();
        replacement.put("fÃ¼nf", "5");
        replacement.put("plus", "+");
        replacement.put("ZWEI", "2");

        Stream<String> input = Stream.of("fÃ¼nf", "+", "ZWEI");

        Assert.assertEquals(new Integer(7), StreamOperations.evaluate(input, replacement));
    }

    @Test(expected = IllegalArgumentException.class)
    public void evaluateEmpty() {
        Stream<String> input = Stream.of("");
        StreamOperations.evaluate(input, new HashMap<>());
    }

    @Test
    public void evaluateLongSuccess() {
        Stream<String> input = Stream.of("3", "-", "10", "%", "4", "/", "2", "*", "2", "+", "7");
        Integer res = StreamOperations.evaluate(input, new HashMap<>());
        
        
        Assert.assertEquals(new Integer(5), res);
    }

    @Test
    public void evaluateLongSuccess2() {
        Stream<String> input = Stream.of("3", "-", "10", "%", "1", "/", "2", "*", "2", "+", "7");

        Assert.assertEquals(new Integer(7), StreamOperations.evaluate(input, new HashMap<>()));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void evaluateFail() {
        Stream<String> input = Stream.of("3", "-", "10", "-");
        Assert.assertEquals(new Integer(7), StreamOperations.evaluate(input, new HashMap<>()));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void evaluateFail2() {
        Stream<String> input = Stream.of("b", "-", "10");
        Assert.assertEquals(new Integer(7), StreamOperations.evaluate(input, new HashMap<>()));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void evaluateFail3() {
        Stream<String> input = Stream.of("3", "-", "+", "4");
        Assert.assertEquals(new Integer(7), StreamOperations.evaluate(input, new HashMap<>()));
    }
    
    @Test
    public void evaluateSuccess() {
        Stream<String> input = Stream.of("3", "-", "7");
        Assert.assertEquals(new Integer(-4), StreamOperations.evaluate(input, new HashMap<>()));
    }
    
    @Test
    public void evaluateSuccess2() {
        Stream<String> input = Stream.of("3", "-", "17");
        Assert.assertEquals(new Integer(-14), StreamOperations.evaluate(input, new HashMap<>()));
    }
    
    @Test
    public void evaluateSuccess3() {
        Stream<String> input = Stream.of("3", "/", "17", "%", "17");
        Assert.assertEquals(new Integer(0), StreamOperations.evaluate(input, new HashMap<>()));
    }
    
    @Test
    public void evaluateSuccess4() {
        Stream<String> input = Stream.of("3", "-", "17", "+", "17");
        Assert.assertEquals(new Integer(3), StreamOperations.evaluate(input, new HashMap<>()));
    }
    
    
    @Test
    public void evaluateReplacementLong() {
        Map<String, String> replacement = new HashMap<>();
        replacement.put("fÃ¼nf", "3");
        replacement.put("plus", "-");
        replacement.put("ZW3EI", "10");
        replacement.put("asdf", "%");
        replacement.put("plu2s", "4");
        replacement.put("ZWEI", "/");
        replacement.put("asdfd", "7");
        replacement.put("d", "2");
        replacement.put("plÃ¶us", "*");
        replacement.put("ZWdEI", "2");
        replacement.put("fÃ¼jjnf", "+");
        replacement.put("pllus", "7");

        Stream<String> input = Stream.of("fÃ¼nf", "plus", "ZW3EI", "asdf", "plu2s", "ZWEI", "d", "plÃ¶us", "ZWdEI", "fÃ¼jjnf", "pllus");

        Assert.assertEquals(new Integer(5), StreamOperations.evaluate(input, replacement));
    }

    @Test(expected = AssertionError.class)
    public void groupPersonsByIncomeIncomesOverlap() {
        IncomeClass incomeClass1 = new IncomeClass(10, 100);
        IncomeClass incomeClass2 = new IncomeClass(100, 1000);
        IncomeClass incomeClass3 = new IncomeClass(1001, 10000);

        Set<IncomeClass> incomeClasses =
                new HashSet<IncomeClass>(Arrays.asList(incomeClass1, incomeClass2, incomeClass3));

        Person person1 = new Person(Person.Gender.FEMALE, "name", "surname", 2222, 3000);
        List<Person> persons = new ArrayList<>(Arrays.asList(person1));

        Set<Person.Gender> genderSet = new HashSet<>(Arrays.asList(Person.Gender.FEMALE, Person.Gender.MALE));

        StreamOperations.groupPersonsByIncome(persons.stream(), genderSet, 1, 300, incomeClasses);
    }

    @Test(expected = AssertionError.class)
    public void groupPersonsByIncomeIncomesOverlap1() {
        IncomeClass incomeClass1 = new IncomeClass(10, 100);
        IncomeClass incomeClass2 = new IncomeClass(101, 1000);
        IncomeClass incomeClass3 = new IncomeClass(1000, 10000);

        Set<IncomeClass> incomeClasses =
                new HashSet<IncomeClass>(Arrays.asList(incomeClass1, incomeClass2, incomeClass3));

        Person person1 = new Person(Person.Gender.FEMALE, "name", "surname", 2222, 3000);
        List<Person> persons = new ArrayList<>(Arrays.asList(person1));

        Set<Person.Gender> genderSet = new HashSet<>(Arrays.asList(Person.Gender.FEMALE, Person.Gender.MALE));

        StreamOperations.groupPersonsByIncome(persons.stream(), genderSet, 1, 300, incomeClasses);
    }

    @Test
    public void groupPersonsByIncomeSimple() {
        IncomeClass incomeClass1 = new IncomeClass(10, 100);
        IncomeClass incomeClass2 = new IncomeClass(101, 1000);


        Set<IncomeClass> incomeClasses =
                new HashSet<>();
        incomeClasses.add(incomeClass1);
        incomeClasses.add(incomeClass2);

        Person person1 = new Person(Person.Gender.FEMALE, "name1", "surname1", 2000, 30);
        Person person2 = new Person(Person.Gender.FEMALE, "name2", "surname2", 2222, 300);
        List<Person> persons = new ArrayList<>(Arrays.asList(person1, person2));

        Set<Person.Gender> genderSet = new HashSet<>(Arrays.asList(Person.Gender.FEMALE, Person.Gender.MALE));


        Map<IncomeClass, Set<Person>> result = new HashMap<>();
        result.put(incomeClass1, new HashSet<>());
        result.put(incomeClass2, new HashSet<>());

        result.get(incomeClass1).add(person1);
        result.get(incomeClass2).add(person2);

        Assert.assertEquals(result, StreamOperations.groupPersonsByIncome(persons.stream(),
                genderSet, 1, 3000, incomeClasses));
    }
    @Test
    public void groupPersonsByIncomeSimple1() {
        IncomeClass incomeClass1 = new IncomeClass(10, 100);
        IncomeClass incomeClass2 = new IncomeClass(101, 1000);
        IncomeClass incomeClass3 = new IncomeClass(1001, 10000);

        Set<IncomeClass> incomeClasses =
                new HashSet<>();
        incomeClasses.add(incomeClass1);
        incomeClasses.add(incomeClass2);
        incomeClasses.add(incomeClass3);

        Person person1 = new Person(Person.Gender.FEMALE, "name1", "surname1", 2222, 30);
        Person person2 = new Person(Person.Gender.FEMALE, "name2", "surname2", 2222, 300);
        Person person3 = new Person(Person.Gender.FEMALE, "name3", "surname3", 2222, 3000);
        Person person4 = new Person(Person.Gender.FEMALE, "name4", "surname4", 2222, 9999);
        List<Person> persons = new ArrayList<>(Arrays.asList(person1, person2, person3, person4));

        Set<Person.Gender> genderSet = new HashSet<>(Arrays.asList(Person.Gender.FEMALE, Person.Gender.MALE));


        Map<IncomeClass, Set<Person>> result = new HashMap<>();
        result.put(incomeClass1, new HashSet<>());
        result.put(incomeClass2, new HashSet<>());
        result.put(incomeClass3, new HashSet<>());

        result.get(incomeClass1).add(person1);
        result.get(incomeClass2).add(person2);
        result.get(incomeClass3).add(person3);
        result.get(incomeClass3).add(person4);

        Assert.assertEquals(result, StreamOperations.groupPersonsByIncome(persons.stream(),
                genderSet, 1, 3000, incomeClasses));

    }

    @Test
    public void groupPersonsByIncomeEmptyIncomeClass() {
        IncomeClass incomeClass1 = new IncomeClass(10, 100);
        IncomeClass incomeClass2 = new IncomeClass(101, 1000);
        IncomeClass incomeClass3 = new IncomeClass(1001, 10000);
        IncomeClass incomeClass4 = new IncomeClass(10001, 100000);

        Set<IncomeClass> incomeClasses =
                new HashSet<>();
        incomeClasses.add(incomeClass1);
        incomeClasses.add(incomeClass2);
        incomeClasses.add(incomeClass3);
        incomeClasses.add(incomeClass4);

        Person person1 = new Person(Person.Gender.FEMALE, "name1", "surname1", 2222, 30);
        Person person2 = new Person(Person.Gender.FEMALE, "name2", "surname2", 2222, 300);
        Person person3 = new Person(Person.Gender.FEMALE, "name3", "surname3", 2222, 3000);
        Person person4 = new Person(Person.Gender.FEMALE, "name4", "surname4", 2222, 9999);
        List<Person> persons = new ArrayList<>(Arrays.asList(person1, person2, person3, person4));

        Set<Person.Gender> genderSet = new HashSet<>(Arrays.asList(Person.Gender.FEMALE, Person.Gender.MALE));


        Map<IncomeClass, Set<Person>> result = new HashMap<>();
        result.put(incomeClass1, new HashSet<>());
        result.put(incomeClass2, new HashSet<>());
        result.put(incomeClass3, new HashSet<>());

        result.get(incomeClass1).add(person1);
        result.get(incomeClass2).add(person2);
        result.get(incomeClass3).add(person3);
        result.get(incomeClass3).add(person4);

        Assert.assertEquals(result, StreamOperations.groupPersonsByIncome(persons.stream(),
                genderSet, 1, 3000, incomeClasses));
    }

    @Test
    public void groupPersonsByIncomeDoublePerson() {
        IncomeClass incomeClass1 = new IncomeClass(10, 100);
        IncomeClass incomeClass2 = new IncomeClass(101, 1000);
        IncomeClass incomeClass3 = new IncomeClass(1001, 10000);

        Set<IncomeClass> incomeClasses =
                new HashSet<>();
        incomeClasses.add(incomeClass1);
        incomeClasses.add(incomeClass2);
        incomeClasses.add(incomeClass3);

        Person person1 = new Person(Person.Gender.FEMALE, "name1", "surname1", 2222, 30);
        Person person2 = new Person(Person.Gender.FEMALE, "name2", "surname2", 2222, 300);
        Person person3 = new Person(Person.Gender.FEMALE, "name3", "surname3", 2222, 3000);
        Person person4 = new Person(Person.Gender.FEMALE, "name4", "surname4", 2222, 9999);
        Person person5 = new Person(Person.Gender.FEMALE, "name4", "surname4", 2222, 9999);
        List<Person> persons = new ArrayList<>(Arrays.asList(person1, person2, person3, person4,person5));

        Set<Person.Gender> genderSet = new HashSet<>(Arrays.asList(Person.Gender.FEMALE, Person.Gender.MALE));


        Map<IncomeClass, Set<Person>> result = new HashMap<>();
        result.put(incomeClass1, new HashSet<>());
        result.put(incomeClass2, new HashSet<>());
        result.put(incomeClass3, new HashSet<>());

        result.get(incomeClass1).add(person1);
        result.get(incomeClass2).add(person2);
        result.get(incomeClass3).add(person3);
        result.get(incomeClass3).add(person4);

        Assert.assertEquals(result, StreamOperations.groupPersonsByIncome(persons.stream(),
                genderSet, 1, 3000, incomeClasses));
    }
    @Test
    public void groupPersonsByIncomePersonWithoutIncome() {
        IncomeClass incomeClass1 = new IncomeClass(10, 100);
        IncomeClass incomeClass2 = new IncomeClass(101, 1000);


        Set<IncomeClass> incomeClasses =
                new HashSet<>();
        incomeClasses.add(incomeClass1);
        incomeClasses.add(incomeClass2);


        Person person1 = new Person(Person.Gender.FEMALE, "name1", "surname1", 2222, 30);
        Person person2 = new Person(Person.Gender.FEMALE, "name2", "surname2", 2222, 300);
        Person person3 = new Person(Person.Gender.FEMALE, "name3", "surname3", 2222, null);

        List<Person> persons = new ArrayList<>(Arrays.asList(person1, person2, person3));

        Set<Person.Gender> genderSet = new HashSet<>(Arrays.asList(Person.Gender.FEMALE, Person.Gender.MALE));

        Map<IncomeClass, Set<Person>> result = new HashMap<>();
        result.put(incomeClass1, new HashSet<>());
        result.put(incomeClass2, new HashSet<>());

        result.get(incomeClass1).add(person1);
        result.get(incomeClass2).add(person2);

        Assert.assertEquals(result, StreamOperations.groupPersonsByIncome(persons.stream(),
                genderSet, 1, 3000, incomeClasses));
    }

    @Test
    public void groupPersonsByIncomePersonWrongZIP() {
        IncomeClass incomeClass1 = new IncomeClass(10, 100);
        IncomeClass incomeClass2 = new IncomeClass(101, 1000);


        Set<IncomeClass> incomeClasses =
                new HashSet<>();
        incomeClasses.add(incomeClass1);
        incomeClasses.add(incomeClass2);


        Person person1 = new Person(Person.Gender.FEMALE, "name1", "surname1", 2222, 30);
        Person person2 = new Person(Person.Gender.FEMALE, "name2", "surname2", 2222, 300);
        Person person3 = new Person(Person.Gender.FEMALE, "name3", "surname3", 1000, 20);
        Person person4 = new Person(Person.Gender.FEMALE, "name3", "surname3", 3001, 20);

        List<Person> persons = new ArrayList<>(Arrays.asList(person1, person2, person3,person4));

        Set<Person.Gender> genderSet = new HashSet<>(Arrays.asList(Person.Gender.FEMALE, Person.Gender.MALE));

        Map<IncomeClass, Set<Person>> result = new HashMap<>();
        result.put(incomeClass1, new HashSet<>());
        result.put(incomeClass2, new HashSet<>());

        result.get(incomeClass1).add(person1);
        result.get(incomeClass2).add(person2);

        Assert.assertEquals(result, StreamOperations.groupPersonsByIncome(persons.stream(),
                genderSet, 2000, 3000, incomeClasses));
    }

    @Test
    public void groupPersonsByIncomePersonWrongGender() {
        IncomeClass incomeClass1 = new IncomeClass(10, 100);
        IncomeClass incomeClass2 = new IncomeClass(101, 1000);
        IncomeClass incomeClass3 = new IncomeClass(1001, 10000);


        Set<IncomeClass> incomeClasses =
                new HashSet<>();
        incomeClasses.add(incomeClass1);
        incomeClasses.add(incomeClass2);
        incomeClasses.add(incomeClass3);


        Person person1 = new Person(Person.Gender.FEMALE, "name1", "surname1", 2222, 30);
        Person person2 = new Person(Person.Gender.FEMALE, "name2", "surname2", 2222, 300);
        Person person3 = new Person(Person.Gender.FEMALE, "name3", "surname3", 2000, 2000);
        Person person4 = new Person(Person.Gender.MALE, "name4", "surname4", 3000, 2000);

        List<Person> persons = new ArrayList<>(Arrays.asList(person1, person2, person3, person4));

        Set<Person.Gender> genderSet = new HashSet<>(Collections.singletonList(Person.Gender.FEMALE));

        Map<IncomeClass, Set<Person>> result = new HashMap<>();
        result.put(incomeClass1, new HashSet<>());
        result.put(incomeClass2, new HashSet<>());
        result.put(incomeClass3, new HashSet<>());

        result.get(incomeClass1).add(person1);
        result.get(incomeClass2).add(person2);
        result.get(incomeClass3).add(person3);

        Assert.assertEquals(result, StreamOperations.groupPersonsByIncome(persons.stream(),
                genderSet, 2000, 3000, incomeClasses));
    }

    @Test
    public void groupPersonsByIncomeSimple2() {
        IncomeClass incomeClass1 = new IncomeClass(10, 100);
        IncomeClass incomeClass2 = new IncomeClass(101, 1000);
        IncomeClass incomeClass3 = new IncomeClass(1001, 10000);
        IncomeClass incomeClass4 = new IncomeClass(10001, 10001);
        IncomeClass incomeClass5 = new IncomeClass(11001, 11002);

        Set<IncomeClass> incomeClasses =
                new HashSet<>();
        incomeClasses.add(incomeClass1);
        incomeClasses.add(incomeClass2);
        incomeClasses.add(incomeClass3);
        incomeClasses.add(incomeClass3);
        incomeClasses.add(incomeClass4);
        incomeClasses.add(incomeClass5);

        Person person1 = new Person(Person.Gender.FEMALE, "name1", "surname1", 2222, 30);
        Person person2 = new Person(Person.Gender.FEMALE, "name2", "surname2", 2222, 300);
        Person person3 = new Person(Person.Gender.FEMALE, "name3", "surname3", 2222, 3000);
        Person person4 = new Person(Person.Gender.MALE, "name4", "surname4", 2222, 10001);
        List<Person> persons = new ArrayList<>(Arrays.asList(person1, person2, person3, person4));

        Set<Person.Gender> genderSet = new HashSet<>(Arrays.asList(Person.Gender.FEMALE, Person.Gender.MALE));


        Map<IncomeClass, Set<Person>> result = new HashMap<>();
        result.put(incomeClass1, new HashSet<>());
        result.put(incomeClass2, new HashSet<>());
        result.put(incomeClass3, new HashSet<>());
        result.put(incomeClass4, new HashSet<>());

        result.get(incomeClass1).add(person1);
        result.get(incomeClass2).add(person2);
        result.get(incomeClass3).add(person3);
        result.get(incomeClass4).add(person4);

        Assert.assertEquals(result, StreamOperations.groupPersonsByIncome(persons.stream(),
                genderSet, 1, 3000, incomeClasses));

    }


    @Test
    public void testPrime() {
        assertTrue(StreamOperations.isPrime(2));
        assertTrue(StreamOperations.isPrime(3));
        assertTrue(StreamOperations.isPrime(5));
        assertTrue(StreamOperations.isPrime(7));
        assertTrue(StreamOperations.isPrime(11));
        assertTrue(StreamOperations.isPrime(13));
        assertTrue(StreamOperations.isPrime(17));
        assertTrue(StreamOperations.isPrime(19));
        assertFalse(StreamOperations.isPrime(1));
        assertFalse(StreamOperations.isPrime(4));
        assertFalse(StreamOperations.isPrime(6));
        assertFalse(StreamOperations.isPrime(8));
        assertFalse(StreamOperations.isPrime(9));
        assertFalse(StreamOperations.isPrime(10));
        assertFalse(StreamOperations.isPrime(12));
        assertFalse(StreamOperations.isPrime(14));
    }


    @Test
    public void testFib() {
        Stream<BigInteger> fibs = StreamOperations.fibs().limit(10);
        Iterator<BigInteger> it = fibs.iterator();
        assertEquals(1, it.next().intValue());
        assertEquals(1, it.next().intValue());
        assertEquals(2, it.next().intValue());
        assertEquals(3, it.next().intValue());
        assertEquals(5, it.next().intValue());
        assertEquals(8, it.next().intValue());
        assertEquals(13, it.next().intValue());
        assertEquals(21, it.next().intValue());
        assertEquals(34, it.next().intValue());
        assertEquals(55, it.next().intValue());
    }

    @Test
    public void testGroupWordsOfSameLengthContains(){
        String[] a = new String[] {"a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa"};
        Map<Integer, Set<String>> map = StreamOperations.groupWordsOfSameLength(Arrays.stream(a));
        assertTrue(map.containsKey(1));
        assertTrue(map.containsKey(2));
        assertTrue(map.containsKey(3));
        assertTrue(map.containsKey(4));
        assertTrue(map.containsKey(5));
        assertTrue(map.containsKey(6));
        assertTrue(map.containsKey(7));
        assertFalse(map.containsKey(8));
        assertFalse(map.containsKey(9));
        assertFalse(map.containsKey(10));
        assertFalse(map.containsKey(11));
        assertFalse(map.containsKey(12));
    }

    @Test
    public void testGroupWordsOfSameLengthSet(){
        String[] a = new String[] {"a", "aa", "aaa", "aaaa", "aaaaa", "bbb", "ccc"};
        Map<Integer, Set<String>> map = StreamOperations.groupWordsOfSameLength(Arrays.stream(a));
        System.out.println(map);
        assertEquals(3, map.get(3).size());
        assertEquals(1, map.get(1).size());
    }

    @Test
    public void testGroupWordsOfSameLengthWord(){
        String[] a = new String[] {"a", "aa", "aa", "aaa", "aaaa", "aaaaa", "bbb", "ccc"};
        Map<Integer, Set<String>> map = StreamOperations.groupWordsOfSameLength(Arrays.stream(a));
        System.out.println(map);
        assertEquals(3, map.get(3).size());
        assertEquals(1, map.get(1).size());
        assertEquals(1, map.get(2).size());
    }
    
    @Test
    public void testCountChars() {
        Stream<Character> str = Stream.of('a', 'c', 'c', 'c', 'b', 'b', 'd', 'e', 'e', 'd', 'e', 'd', 'd', 'e', 'e');
        Map<Character, Integer> map = StreamOperations.countChars(str, 'a', 'e', null);
        Integer a = map.get('a');
        assertEquals(new Integer(1), a);
        a = map.get('b');
        assertEquals(new Integer(2), a);
        a = map.get('c');
        assertEquals(new Integer(3), a);
        a = map.get('d');
        assertEquals(new Integer(4), a);
        a = map.get('e');
        assertEquals(new Integer(5), a);
    
    }
    
    @Test
    public void testCountCharsLimit() {
        Stream<Character> str = Stream.of('a', 'c', 'c', 'c', 'b', 'b', 'd', 'e', 'e', 'd', 'e', 'd', 'd', 'e', 'e');
        Map<Character, Integer> map = StreamOperations.countChars(str, 'a', 'e', new Long(4));
        Integer a = map.get('a');
        assertEquals(new Integer(1), a);
        a = map.get('b');
        assertEquals(null, a);
        a = map.get('c');
        assertEquals(new Integer(3), a);
        a = map.get('d');
        assertEquals(null, a);
        a = map.get('e');
        assertEquals(null, a);
    }
    
    @Test
    public void testCountCharsFromB() {
        Stream<Character> str = Stream.of('a', 'c', 'c', 'c', 'b', 'b', 'd', 'e', 'e', 'd', 'e', 'd', 'd', 'e', 'e');
        Map<Character, Integer> map = StreamOperations.countChars(str, 'b', 'e', null);
        Integer a = map.get('a');
        assertEquals(null, a);
        a = map.get('b');
        assertEquals(new Integer(2), a);
        a = map.get('c');
        assertEquals(new Integer(3), a);
        a = map.get('d');
        assertEquals(new Integer(4), a);
        a = map.get('e');
        assertEquals(new Integer(5), a);
    }
    
    @Test
    public void testCountCharsToD() {
        Stream<Character> str = Stream.of('a', 'c', 'c', 'c', 'b', 'b', 'd', 'e', 'e', 'd', 'e', 'd', 'd', 'e', 'e');
        Map<Character, Integer> map = StreamOperations.countChars(str, 'a', 'd', null);
        Integer a = map.get('a');
        assertEquals(new Integer(1), a);
        a = map.get('b');
        assertEquals(new Integer(2), a);
        a = map.get('c');
        assertEquals(new Integer(3), a);
        a = map.get('d');
        assertEquals(new Integer(4), a);
        a = map.get('e');
        assertEquals(null, a);
    }
    
    @Test
    public void testCountCharsToDLimit() {
        Stream<Character> str = Stream.of('a', 'c', 'c', 'c', 'b', 'b', 'd', 'e', 'e', 'd', 'e', 'd', 'd', 'e', 'e');
        Map<Character, Integer> map = StreamOperations.countChars(str, 'a', 'd', new Long(5));
        Integer a = map.get('a');
        assertEquals(new Integer(1), a);
        a = map.get('b');
        assertEquals(new Integer(1), a);
        a = map.get('c');
        assertEquals(new Integer(3), a);
        a = map.get('d');
        assertEquals(null, a);
        a = map.get('e');
        assertEquals(null, a);
    }
    
    
    @Test
    public void testCountCharsFromBlimit() {
        Stream<Character> str = Stream.of('a', 'c', 'c', 'c', 'b', 'b', 'd', 'e', 'e', 'd', 'e', 'd', 'd', 'e', 'e');
        Map<Character, Integer> map = StreamOperations.countChars(str, 'b', 'e', new Long(3));
        Integer a = map.get('a');
        assertEquals(null, a);
        a = map.get('b');
        assertEquals(null, a);
        a = map.get('c');
        assertEquals(new Integer(2), a);
        a = map.get('d');
        assertEquals(null, a);
        a = map.get('e');
        assertEquals(null, a);
    }

    @Test
    public void testStringsToChars() {
        Stream<String> input = Stream.of("abc", "bbc", "aer");
        Object[] ch = StreamOperations.stringsToChars(input).toArray();
        Object[] exp = new Character[] {'a', 'b', 'c', 'b', 'b', 'c', 'a', 'e', 'r'};
        assertArrayEquals(exp, ch);
    }
    
    @Test
    public void testDigits2num() {
        Stream<Integer> input = Stream.of(3, 4, 5, 2);
        assertEquals(new BigInteger("3452"), StreamOperations.digits2num(input));
    }
    
    
    @Test
    public void testAverageTestResult() {
        Stream<TestResult> st = Stream.of(new TestResult("a", 2.0), new TestResult("a", 3.0), new TestResult("a", 4.0));
        Double a = StreamOperations.averageTestResult(st, "a");
        assertTrue(3 == a);
    }
    
    @Test
    public void testAverageTestResultNoSuiteableMachine() {
        Stream<TestResult> st = Stream.of(new TestResult("a", 2.0), new TestResult("a", 3.0), new TestResult("a", 4.0));
        Double a = StreamOperations.averageTestResult(st, "b");
        assertTrue(0 == a);
    }
    
    @Test
    public void testGroupPersonByIncomeClassOneClass(){
        Stream<Person> pStream = Stream.of(new Person(Person.Gender.MALE, "hala", "lo1", 22880, 4000),
                                            new Person(Person.Gender.FEMALE, "hald", "lo2", 22880, 4000),
                                            new Person(Person.Gender.MALE, "half", "lo3", 22880, 2000),
                                            new Person(Person.Gender.MALE, "halg", "lo4", 22880, 1000),
                                            new Person(Person.Gender.FEMALE, "hahl", "lo5", 22880, 5000),
                                            new Person(Person.Gender.MALE, "halj", "lo6", 22880, 4500),
                                            new Person(Person.Gender.MALE, "halk", "lo7", 22880, 4200),
                                            new Person(Person.Gender.FEMALE, "halkl", "lo8", 22880, 4800),
                                            new Person(Person.Gender.MALE, "hall", "lo9", 22880, 4900));
        Set<IncomeClass> iclasses = new HashSet<IncomeClass>();
        IncomeClass ic = new IncomeClass(4000, 4900);
        iclasses.add(ic);
        Set<Person.Gender> gs = new HashSet<Person.Gender>();
        gs.add(Person.Gender.MALE);
        gs.add(Person.Gender.FEMALE);
        Map<IncomeClass, Set<Person>> res = StreamOperations.groupPersonsByIncome(pStream, gs, 0, 22880, iclasses);
        assertEquals(6, res.get(ic).size());
    }
    
    @Test
    public void testGroupPersonByIncomeClassTwoClasses(){
        Stream<Person> pStream = Stream.of(new Person(Person.Gender.MALE, "hala", "lo1", 22880, 4000),
                                            new Person(Person.Gender.FEMALE, "hald", "lo2", 22880, 4000),
                                            new Person(Person.Gender.MALE, "half", "lo3", 22880, 2000),
                                            new Person(Person.Gender.MALE, "halg", "lo4", 22880, 1000),
                                            new Person(Person.Gender.FEMALE, "hahl", "lo5", 22880, 5000),
                                            new Person(Person.Gender.MALE, "halj", "lo6", 22880, 4500),
                                            new Person(Person.Gender.MALE, "halk", "lo7", 22880, 4200),
                                            new Person(Person.Gender.FEMALE, "halkl", "lo8", 22880, 4800),
                                            new Person(Person.Gender.MALE, "hall", "lo9", 22880, 4900));
        Set<IncomeClass> iclasses = new HashSet<IncomeClass>();
        IncomeClass ic1 = new IncomeClass(4000, 4900);
        IncomeClass ic2 = new IncomeClass(1000, 2000);
        iclasses.add(ic1);
        iclasses.add(ic2);
        Set<Person.Gender> gs = new HashSet<Person.Gender>();
        gs.add(Person.Gender.MALE);
        gs.add(Person.Gender.FEMALE);
        Map<IncomeClass, Set<Person>> res = StreamOperations.groupPersonsByIncome(pStream, gs, 0, 22880, iclasses);
        assertEquals(6, res.get(ic1).size());
        assertEquals(2, res.get(ic2).size());
    }
    
    @Test
    public void testGroupPersonByIncomeClassFilterZip(){
        Stream<Person> pStream = Stream.of(new Person(Person.Gender.MALE, "hala", "lo1", 22880, 4000),
                                            new Person(Person.Gender.FEMALE, "hald", "lo2", 22880, 4000),
                                            new Person(Person.Gender.MALE, "half", "lo3", 22880, 2000),
                                            new Person(Person.Gender.MALE, "halg", "lo4", 22880, 1000),
                                            new Person(Person.Gender.FEMALE, "hahl", "lo5", 22880, 5000),
                                            new Person(Person.Gender.MALE, "halj", "lo6", 22880, 4500),
                                            new Person(Person.Gender.MALE, "halk", "lo7", 22880, 4200),
                                            new Person(Person.Gender.FEMALE, "halkl", "lo8", 22880, 4800),
                                            new Person(Person.Gender.MALE, "hall", "lo9", 22880, 4900));
        Set<IncomeClass> iclasses = new HashSet<IncomeClass>();
        IncomeClass ic1 = new IncomeClass(4000, 4900);
        IncomeClass ic2 = new IncomeClass(1000, 2000);
        iclasses.add(ic1);
        iclasses.add(ic2);
        Set<Person.Gender> gs = new HashSet<Person.Gender>();
        gs.add(Person.Gender.MALE);
        gs.add(Person.Gender.FEMALE);
        Map<IncomeClass, Set<Person>> res = StreamOperations.groupPersonsByIncome(pStream, gs, 0, 20000, iclasses);
        assertFalse(res.containsKey(ic1));
        assertFalse(res.containsKey(ic2));
    }
    
    
    @Test
    public void testGroupPersonByIncomeClassGender(){
        Stream<Person> pStream = Stream.of(new Person(Person.Gender.MALE, "hala", "lo1", 22880, 4000),
                                            new Person(Person.Gender.FEMALE, "hald", "lo2", 22880, 4000),
                                            new Person(Person.Gender.MALE, "half", "lo3", 22880, 2000),
                                            new Person(Person.Gender.MALE, "halg", "lo4", 22880, 1000),
                                            new Person(Person.Gender.FEMALE, "hahl", "lo5", 22880, 5000),
                                            new Person(Person.Gender.MALE, "halj", "lo6", 22880, 4500),
                                            new Person(Person.Gender.MALE, "halk", "lo7", 22880, 4200),
                                            new Person(Person.Gender.FEMALE, "halkl", "lo8", 22880, 4800),
                                            new Person(Person.Gender.MALE, "hall", "lo9", 22880, 4900));
        Set<IncomeClass> iclasses = new HashSet<IncomeClass>();
        IncomeClass ic1 = new IncomeClass(4000, 4900);
        IncomeClass ic2 = new IncomeClass(1000, 2000);
        iclasses.add(ic1);
        iclasses.add(ic2);
        Set<Person.Gender> gs = new HashSet<Person.Gender>();
        gs.add(Person.Gender.MALE);
        Map<IncomeClass, Set<Person>> res = StreamOperations.groupPersonsByIncome(pStream, gs, 0, 22880, iclasses);
        assertEquals(4, res.get(ic1).size());
        assertEquals(2, res.get(ic2).size());
    }
}
