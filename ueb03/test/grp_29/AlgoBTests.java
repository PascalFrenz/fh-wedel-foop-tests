package grp_29;

import mensa.AlgorithmType;
import org.junit.Assert;
import org.junit.Test;
import vorgegebenes.TestToolkit;

import static org.junit.Assert.assertTrue;


public class AlgoBTests extends TestToolkit {

    @Test
    public void textExample1_10Passes() throws InterruptedException {
        int failedCounter = 0;

        for (int i = 0; i < 10; i++) {
            System.out.println();
            System.out.println("Test B1");
            System.out.println();

            // Zeitmessung starten
            c.tick();

            // 2 Studententhreads erzeugen und starten
            final Thread[] startAll = m.startAll(2, 1, 500, 500, 0, AlgorithmType.B, null);

            Assert.assertEquals("Es wurden nicht alle Threads erzeugt", 2, startAll.length);

            // Warten bis sich alle 2 Studententhreads beenden
            for (Thread t : startAll) {
                t.join();
                System.out.println("Joined: " + t.getName());
            }

            // Zeit nochmals messen
            c.tick();

            try {
                // Timing sicherstellen (mit Toleranz)
                assertTiming(1500);
            } catch (AssertionError e) {
                failedCounter++;
            }
        }

        assertTrue(failedCounter < 3);
        System.out.printf("\nAnzahl der Timing-Fails: [%d]\n", failedCounter);
    }

    @Test
    public void testExample2a_10Passes() throws InterruptedException {
        int failedCounter = 0;

        for (int i = 0; i < 10; i++) {
            System.out.println();
            System.out.println("Test B2a");
            System.out.println();

            c.tick();

            final Thread[] startAll = m.startAll(2, 1, 250, 500, 0, AlgorithmType.B, null);

            Assert.assertEquals("Es wurden nicht alle Threads erzeugt", 2, startAll.length);

            for (Thread t : startAll) {
                t.join();
                System.out.println("Joined: " + t.getName());
            }

            c.tick();
            try {
                assertTiming(1000);
            } catch (AssertionError e) {
                failedCounter++;
            }
        }

        assertTrue(failedCounter < 3);
        System.out.printf("\nAnzahl der Timing-Fails: [%d]\n", failedCounter);
    }

    @Test
    public void testExample2b_10Passes() throws InterruptedException {
        int failedCounter = 0;

        for (int i = 0; i < 10; i++) {
            System.out.println();
            System.out.println("Test B2b");
            System.out.println();

            c.tick();

            final Thread[] startAll = m.startAll(2, 1, 200, 100, 0, AlgorithmType.B, null);

            Assert.assertEquals("Es wurden nicht alle Threads erzeugt", 2, startAll.length);

            for (Thread t : startAll) {
                t.join();
                System.out.println("Joined: " + t.getName());
            }

            c.tick();
            try {
                assertTiming(500);
            } catch (AssertionError e) {
                failedCounter++;
            }
        }

        assertTrue(failedCounter < 3);
        System.out.printf("\nAnzahl der Timing-Fails: [%d]\n", failedCounter);
    }

    @Test
    public void testExample2c_10Passes() throws InterruptedException {
        int failedCounter = 0;

        for (int i = 0; i < 10; i++) {
            System.out.println();
            System.out.println("Test B2c");
            System.out.println();

            c.tick();

            final Thread[] startAll = m.startAll(2, 2, 200, 100, 0, AlgorithmType.B, null);

            Assert.assertEquals("Es wurden nicht alle Threads erzeugt", 2, startAll.length);

            for (Thread t : startAll) {
                t.join();
                System.out.println("Joined: " + t.getName());
            }

            c.tick();
            try {
                assertTiming(900);
            } catch (AssertionError e) {
                failedCounter++;
            }
        }

        assertTrue(failedCounter < 3);
        System.out.printf("\nAnzahl der Timing-Fails: [%d]\n", failedCounter);
    }

    @Test
    public void testExample3_10Passes() throws InterruptedException {
        int failedCounter = 0;

        for (int i = 0; i < 10; i++) {
            System.out.println();
            System.out.println("Test B3");
            System.out.println();

            c.tick();

            final Thread[] startAll = m.startAll(3, 3, 100, 100, 0, AlgorithmType.B, null);

            Assert.assertEquals("Es wurden nicht alle Threads erzeugt", 3, startAll.length);

            for (Thread t : startAll) {
                t.join();
                System.out.println("Joined: " + t.getName());
            }

            c.tick();
            try {
                assertTiming(1000, 1200); // je nach Scheduling
            } catch (AssertionError e) {
                failedCounter++;
            }
        }

        assertTrue(failedCounter < 3);
        System.out.printf("\nAnzahl der Timing-Fails: [%d]\n", failedCounter);
    }

    @Test
    public void testAlgorithmB_5_Students_SeasonLongerThanEat() throws InterruptedException {
        c.tick();

        final Thread[] startAll = m.startAll(5, 1, 500, 50, 0, AlgorithmType.B, null);

        for (Thread thread : startAll) {
            thread.join();
            System.out.println("Joined: " + thread.getName());
        }

        c.tick();

        /*
        * In 3 Schritten Würzen alle Studenten => 1500ms
        * Danach muss der letzte Student noch Essen => 50ms
        */
        assertTiming(1550);
    }

    @Test
    public void testAlgorithmB_5_Students_EatLongerThanSeason() throws InterruptedException {
        c.tick();

        final Thread[] startAll = m.startAll(
                5,
                1,
                50,
                500,
                0,
                AlgorithmType.B,
                null
        );

        for (Thread thread : startAll) {
            thread.join();
            System.out.println("Joined: " + thread.getName());
        }

        c.tick();

        /*
         * Alle Studenten würzen => 3 * 50ms = 150ms
         * Dann muss der letzte Student fertig essen => 500ms
         * Die anderen Studenten beenden das Essen vor ihm.
         */
        assertTiming(650);
    }

    @Test
    public void testAlgorithmB_5_Students_EqualTiming_LongRunning() throws InterruptedException {
        c.tick();

        final Thread[] startAll = m.startAll(
                5,
                2,
                5000,
                5000,
                0,
                AlgorithmType.B,
                null
        );

        for (Thread thread : startAll) {
            thread.join();
            System.out.println("Joined: " + thread.getName());
        }

        c.tick();

        /*
         * Alle Studenten würzen => 3 * 50ms = 150ms
         * Dann muss der letzte Student fertig essen => 500ms
         * Die anderen Studenten beenden das Essen vor ihm.
         */
        assertTiming(30000);
    }
}
