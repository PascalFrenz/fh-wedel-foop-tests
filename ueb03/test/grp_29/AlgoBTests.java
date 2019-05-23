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
    public void test_ExampleB2a() throws InterruptedException {
        testUtils.testBWithTwoStudentsAndOneStep(
                250,
                500,
                1000
        );
    }

    @Test
    public void test_ExampleB2b() throws InterruptedException {
        testUtils.testBWithTwoStudentsAndOneStep(
                200,
                100,
                500
        );
    }

    @Test
    public void test_ExampleB2c() throws InterruptedException {
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
    public void test_ExampleB3() throws InterruptedException {
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
    public void test_SeasonLongerThanEat() throws InterruptedException {
        /*
         * In 3 Schritten Würzen alle Studenten => 300ms
         * Danach muss der letzte Student noch Essen => 50ms
         */
        testUtils.testBWithManyStudentsAndManySteps(
                5,
                1,
                100,
                50,
                350L,
                null
        );
    }

    @Test
    public void test_SeasonLongerThanEat_ManySteps() throws InterruptedException {
        /*
         * In 5 Schritten Würzen alle Studenten => 5 * 500ms = 2500ms
         * Danach muss der letzte Student noch Essen => 50ms
         *
         * Das Scheudulig kann hier die Zeiten etwas variieren, daher eine Bereichsangabe.
         */
        testUtils.testBWithManyStudentsAndManySteps(
                5,
                10,
                100,
                50,
                2550L,
                2850L
        );
    }

    @Test
    public void test_EatLongerThanSeason() throws InterruptedException {
        /*
         * Alle Studenten würzen => 3 * 50ms = 150ms
         * Dann muss der letzte Student fertig essen => 100ms
         * Die anderen Studenten beenden das Essen vor ihm.
         */
        testUtils.testBWithManyStudentsAndManySteps(
                5,
                1,
                50,
                100,
                250L,
                null
        );
    }

    @Test
    public void test_EatLongerThanSeason_ManySteps() throws InterruptedException {
        /*
         * Alle Studenten würzen => 5 * 250ms = 1250ms
         * Dann muss der letzte Student fertig essen => 75ms
         * Die anderen Studenten beenden das Essen vor ihm.
         *
         * Das Scheudulig kann hier die Zeiten etwas variieren, daher eine Bereichsangabe.
         */
        testUtils.testBWithManyStudentsAndManySteps(
                5,
                10,
                50,
                75,
                1325L,
                1450L
        );
    }

    @Test
    public void test_EatAndSeasonEqual() throws InterruptedException {
        /*
         * Alle Studenten Würzen => 3 * 150ms
         * Der Letzte isst fertig => 50ms
         * Die anderen Studenten werden vor ihm fertig.
         */
        testUtils.testBWithManyStudentsAndManySteps(
                5,
                1,
                50,
                50,
                200L,
                null
        );
    }

    @Test
    public void test_EatAndSeasonEqual_ManySteps() throws InterruptedException {
        /*
         * Alle Studenten würzen => 25 * 50ms = 1250ms
         * Dann muss der letzte Student fertig essen => 50ms
         * Die anderen Studenten beenden das Essen vor ihm.
         *
         * Das Scheudulig kann hier die Zeiten etwas variieren, daher eine Bereichsangabe.
         */
        testUtils.testBWithManyStudentsAndManySteps(
                5,
                10,
                50,
                50,
                1300L,
                1350L
        );
    }
}
