package streamops.grp_29;

import com.google.common.collect.Sets;
import org.junit.Test;
import streamops.IncomeClass;
import streamops.Person;
import streamops.StreamOperations;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class GroupPersonsByIncomeTest {

    private static final IncomeClass LOWEST = new IncomeClass(100, 199);
    private static final IncomeClass MIDDLE = new IncomeClass(200, 299);
    private static final IncomeClass HIGHEST = new IncomeClass(300, 399);

    /*
    @Test
    public void testNoIncomeOverlaps() {
        assertTrue(StreamOperations.noIncomeOverlaps(Sets.newHashSet(LOWEST, MIDDLE)));
        assertTrue(StreamOperations.noIncomeOverlaps(Sets.newHashSet(HIGHEST, MIDDLE)));

        assertFalse(StreamOperations.noIncomeOverlaps(Sets.newHashSet(
                new IncomeClass(0, 100),
                new IncomeClass(99, 200)
        )));

        assertFalse(StreamOperations.noIncomeOverlaps(Sets.newHashSet(
                new IncomeClass(0, 100),
                new IncomeClass(1, 99)
        )));

        assertFalse(StreamOperations.noIncomeOverlaps(Sets.newHashSet(
                new IncomeClass(100, 200),
                new IncomeClass(99, 101)
        )));
    }
    */

    @Test(expected = AssertionError.class)
    public void testNoOverlapsOnIncomeSet_Overlapping() {
        Set<IncomeClass> iClasses = Sets.newHashSet(
                new IncomeClass(100, 200),
                new IncomeClass(201, 300),
                new IncomeClass(150, 199) // This one is overlapping with the first
        );

        Stream<Person> persons = Stream.of(
                new Person(Person.Gender.MALE, "Hans", "Peter", 22020, 199),
                new Person(Person.Gender.MALE, "Heinrich", "Peter", 22020, 250)
        );
        Set<Person.Gender> genders = Collections.singleton(Person.Gender.MALE);

        StreamOperations.groupPersonsByIncome(persons, genders, 22019, 22021, iClasses);
    }

    @Test
    public void testGroupPersonByIncome_IncomeClassNotExisting() {
        List<Person> persons = Arrays.asList(
                new Person(Person.Gender.MALE, "Detlef", "Dietrich", 20249, 201),
                new Person(Person.Gender.FEMALE, "Cordula", "Kornspeicher", 20242, 300)
        );

        Map<IncomeClass, Set<Person>> result = StreamOperations.groupPersonsByIncome(
                persons.stream(),
                Collections.singleton(Person.Gender.MALE),
                20000,
                25000,
                Sets.newHashSet(LOWEST, HIGHEST)
        );

        assertTrue(result.isEmpty());
    }

    @Test
    public void testGroupPersonsByIncome_DoubleEntry() {
        List<Person> persons = Arrays.asList(
                new Person(Person.Gender.FEMALE, "Erika", "Mustermann", 22880, 150),
                new Person(Person.Gender.FEMALE, "Erika", "Mustermann", 22880, 150),
                new Person(Person.Gender.FEMALE, "Maike", "Mustermann", 22880, 170)
        );

        Set<Person> expectedLowest = Sets.newHashSet(persons.get(0), persons.get(2));
        Map<IncomeClass, Set<Person>> expected = Collections.singletonMap(LOWEST, expectedLowest);
        Map<IncomeClass, Set<Person>> result = StreamOperations.groupPersonsByIncome(
                persons.stream(),
                Collections.singleton(Person.Gender.FEMALE),
                22879,
                22881,
                Collections.singleton(LOWEST)
        );

        assertEquals(expected, result);
    }

    @Test
    public void testGroupPersonsByIncome_NullIncomeClass() {
        List<Person> persons = Arrays.asList(
                new Person(Person.Gender.FEMALE, "Erika", "Mustermann", 22880, 150),
                new Person(Person.Gender.FEMALE, "Erika", "Mustermann", 22880, 150),
                new Person(Person.Gender.FEMALE, "Maike", "Mustermann", 22880, 170)
        );

        Map<IncomeClass, Set<Person>> expected = Collections.emptyMap();
        Map<IncomeClass, Set<Person>> result = StreamOperations.groupPersonsByIncome(
                persons.stream(),
                Collections.singleton(Person.Gender.FEMALE),
                22879,
                22881,
                Collections.singleton(null)
        );

        assertEquals(expected, result);
    }

    @Test
    public void testGroupPersonsByIncome() {
        List<Person> persons = Arrays.asList(
                // Korrekter Aufbau
                new Person(Person.Gender.FEMALE, "Erika", "Mustermann", 22880, 150),
                // Korrekter Aufbau
                new Person(Person.Gender.FEMALE, "Maike", "Mustermann", 22880, 170),
                // Doppelter Eintrag
                new Person(Person.Gender.FEMALE, "Erika", "Mustermann", 22880, 150),
                // Kein Einkommen
                new Person(Person.Gender.FEMALE, "Max", "Mustermann", 22880, null),
                // ZIP-Code ausserhalb von Bereich
                new Person(Person.Gender.FEMALE, "Max", "Mustermann", 123456, 150),
                // Geschlecht nicht enthalten
                new Person(Person.Gender.MALE, "Max", "Mustermann", 22880, 150),
                // Korrekter Aufbau
                new Person(Person.Gender.FEMALE, "Max", "Mustermann", 22880, 250)
        );

        Set<Person> expectedInLowest = Sets.newHashSet(persons.get(0), persons.get(1));
        Set<Person> expectedInMiddle = Sets.newHashSet(persons.get(6));

        Map<IncomeClass, Set<Person>> expected = new HashMap<>();
        expected.put(LOWEST, expectedInLowest);
        expected.put(MIDDLE, expectedInMiddle);

        Set<Person.Gender> genders = Collections.singleton(Person.Gender.FEMALE);
        Set<IncomeClass> iClasses = Sets.newHashSet(LOWEST, MIDDLE, HIGHEST);

        Map<IncomeClass, Set<Person>> result = StreamOperations.groupPersonsByIncome(
                persons.stream(),
                genders,
                20000,
                30000,
                iClasses
        );

        assertEquals(2, result.size());
        assertTrue(result.containsKey(LOWEST));
        assertTrue(result.containsKey(MIDDLE));
        assertFalse(result.containsKey(HIGHEST));

        assertEquals(expected, result);
    }
}
