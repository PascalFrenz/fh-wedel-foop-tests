package grp_29;

import mensa.Student;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class AlgoCTests {

    private TestUtils testUtils = new TestUtils();

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

}
