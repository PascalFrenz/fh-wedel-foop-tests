package grp_29;

import org.junit.Test;


public class AlgoBTests {

    private TestUtils testUtils = new TestUtils();

    @Test
    public void test_ExampleB1() throws InterruptedException {
        testUtils.testBWithTwoStudentsAndOneStep(
                500,
                500,
                1500
        );
    }

    @Test
    public void testExampleB2a() throws InterruptedException {
        testUtils.testBWithTwoStudentsAndOneStep(
                250,
                500,
                1000
        );
    }

    @Test
    public void testExampleB2b() throws InterruptedException {
        testUtils.testBWithTwoStudentsAndOneStep(
                200,
                100,
                500
        );
    }

    @Test
    public void testExampleB2c() throws InterruptedException {
        testUtils.testBWithManyStudentsAndManySteps(
                2,
                2,
                200,
                100,
                900L,
                null
        );
    }

    @Test
    public void testExampleB3() throws InterruptedException {
        testUtils.testBWithManyStudentsAndManySteps(
                3,
                3,
                100,
                100,
                900L,
                1200L
        );
    }

    @Test
    public void testAlgorithmB_5_Students_SeasonLongerThanEat() throws InterruptedException {
        /*
         * In 3 Schritten Würzen alle Studenten => 1500ms
         * Danach muss der letzte Student noch Essen => 50ms
         */
        testUtils.testBWithManyStudentsAndManySteps(
                5,
                1,
                500,
                50,
                1550L,
                null
        );
    }

    @Test
    public void testAlgorithmB_5_Students_EatLongerThanSeason() throws InterruptedException {
        /*
         * Alle Studenten würzen => 3 * 50ms = 150ms
         * Dann muss der letzte Student fertig essen => 500ms
         * Die anderen Studenten beenden das Essen vor ihm.
         */
        testUtils.testBWithManyStudentsAndManySteps(
                5,
                1,
                50,
                500,
                650L,
                null
        );
    }

    @Test
    public void testAlgorithmB_5_Students_EqualTiming_LongRunning() throws InterruptedException {
        /*
         * Alle Studenten würzen => 5 * 5000ms = 25000ms
         * Dann muss der letzte Student fertig essen => 5000ms
         * Die anderen Studenten beenden das Essen vor ihm.
         */
        testUtils.testBWithManyStudentsAndManySteps(
                5,
                2,
                5000,
                5000,
                30000L,
                null
        );
    }
}
