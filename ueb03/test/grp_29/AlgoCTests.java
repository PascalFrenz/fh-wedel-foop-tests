package grp_29;

import mensa.Student;
import org.junit.Test;

import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class AlgoCTests {

    private TestUtils testUtils = new TestUtils();

    private List<Student> twoStudentsOneStep(
            long seasonTime,
            long eatingTime,
            long survivalTime,
            Long minExpectedTime) throws InterruptedException {
        return testUtils.testCWithManyStudentsAndManySteps(
                2,
                1,
                seasonTime,
                eatingTime,
                survivalTime,
                minExpectedTime
        );
    }

    /**
     * C. 3 Studenten. 2 Durchlauf. Test mit einem verhungernden Studenten.
     */
    @Test
    public void testC1() throws InterruptedException {
        List<Student> result = testUtils.testCWithManyStudentsAndManySteps(
                3,
                2,
                100,
                100,
                250,
                500L,
                600L
        );

        long starved = result.stream()
                .filter(Student::isStarved)
                .count();
        assertEquals(1, starved);
    }

    @Test
    public void test_SeasonEqualsEat_ShorterThanSurvival() throws InterruptedException {
        List<Student> result = twoStudentsOneStep(
                500,
                500,
                750,
                1000L
        );

        // Einer wird sterben, einer wird überleben
        assertTrue(result.stream().anyMatch(Student::isStarved));
        assertTrue(result.stream().anyMatch(Predicate.not(Student::isStarved)));
    }

    @Test
    public void test_SeasonEqualsSurvivial_ShorterThanEat() throws InterruptedException {
        List<Student> result = twoStudentsOneStep(
                500,
                600,
                500,
                500L
        );

        // Keiner wird überleben
        assertTrue(result.stream().allMatch(Student::isStarved));
    }

    @Test
    public void test_EatEqualsSurvival_LongerThanSeason() throws InterruptedException {
        List<Student> result = twoStudentsOneStep(
                500,
                600,
                600,
                1100L
        );

        // Einer wird beim Würzen sterben, einer wird überleben
        assertTrue(result.stream().anyMatch(Student::isStarved));
        assertTrue(result.stream().anyMatch(Predicate.not(Student::isStarved)));
    }

    @Test
    public void test_EatEqualsSurvival_ShorterThanSeason() throws InterruptedException {
        List<Student> result = twoStudentsOneStep(
                600,
                500,
                500,
                500L
        );

        // Keiner wird überleben
        assertTrue(result.stream().allMatch(Student::isStarved));
    }

    @Test
    public void test_EatShorterThanSurvival_ShorterThanSeason() throws InterruptedException {
        List<Student> result = twoStudentsOneStep(
                600,
                400,
                500,
                500L
        );

        // Keiner wird überleben
        assertTrue(result.stream().allMatch(Student::isStarved));
    }

    @Test
    public void test_EatEqualsSeason_LongerThanSurvival() throws InterruptedException {
        List<Student> result = twoStudentsOneStep(
                600,
                600,
                500,
                500L
        );

        // Keiner wird überleben
        assertTrue(result.stream().allMatch(Student::isStarved));
    }

    @Test
    public void test_EatEqualsSeason_EqualSurvival() throws InterruptedException {
        List<Student> result = twoStudentsOneStep(
                600,
                600,
                600,
                600L
        );

        // Keiner wird überleben
        assertTrue(result.stream().allMatch(Student::isStarved));
    }
}
