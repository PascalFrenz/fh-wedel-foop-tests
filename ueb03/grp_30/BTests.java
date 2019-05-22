import mensa.AlgorithmType;
import org.junit.Assert;
import org.junit.Test;

public class BTests extends TestToolkit {

    @Test
    public void ifEatEqSeasonWorks() throws InterruptedException {

        System.out.println();
        System.out.println("Test B: Studens 2, maxSteps 1, eatingTime = seasoningTime");
        System.out.println();

        // Start timer
        c.tick();

        // Create students and start
        final Thread[] startAll = m.startAll(2, 1, 100, 100, 0, AlgorithmType.B, null);

        Assert.assertEquals("Not all threads created.", 2, startAll.length);

        // Wait for the students to terminate
        for (Thread t : startAll) {
            t.join();
            System.out.println("Joined: " + t.getName());
        }

        // Check time
        c.tick();
        assertTiming(300);
    }

    @Test
    public void ifEatGTSeasonWorks() throws InterruptedException {

        System.out.println();
        System.out.println("Test B: Studens 2, maxSteps 1, eatingTime > seasoningTime, eatingTime = 2*seasoningTime ");
        System.out.println();

        // Start timer
        c.tick();

        // Create students and start
        final Thread[] startAll = m.startAll(2, 1, 100, 200, 0, AlgorithmType.B, null);

        Assert.assertEquals("Not all threads created.", 2, startAll.length);

        // Wait for the students to terminate
        for (Thread t : startAll) {
            t.join();
            System.out.println("Joined: " + t.getName());
        }

        // Check time
        c.tick();
        assertTiming(400);
    }

    @Test
    public void ifEatGTSeasonWorks2() throws InterruptedException {

        System.out.println();
        System.out.println("Test B: Studens 2, maxSteps 1, eatingTime > seasoningTime, smaller diff between eTime and sTime");
        System.out.println();

        // Start timer
        c.tick();

        // Create students and start
        final Thread[] startAll = m.startAll(2, 1, 100, 125, 0, AlgorithmType.B, null);

        Assert.assertEquals("Not all threads created.", 2, startAll.length);

        // Wait for the students to terminate
        for (Thread t : startAll) {
            t.join();
            System.out.println("Joined: " + t.getName());
        }

        // Check time
        c.tick();
        assertTiming(325);
    }

    @Test
    public void ifEatLTSeasonWorks() throws InterruptedException {

        System.out.println();
        System.out.println("Test B: Studens 2, maxSteps 1, eatingTime < seasoningTime, seasoningTime = 2*eatingTime");
        System.out.println();

        // Start timer
        c.tick();

        // Create students and start
        final Thread[] startAll = m.startAll(2, 1, 200, 100, 0, AlgorithmType.B, null);

        Assert.assertEquals("Not all threads created.", 2, startAll.length);

        // Wait for students to terminate
        for (Thread t : startAll) {
            t.join();
            System.out.println("Joined: " + t.getName());
        }

        // Check time
        c.tick();
        assertTiming(500);
    }

    @Test
    public void ifEatLTSeasonWorks2() throws InterruptedException {

        System.out.println();
        System.out.println("Test B: Studens 2, maxSteps 1, eatingTime < seasoningTime, smaller diff between sT and eT");
        System.out.println();

        // Start timer
        c.tick();

        // Create students
        final Thread[] startAll = m.startAll(2, 1, 125, 100, 0, AlgorithmType.B, null);

        Assert.assertEquals("Not all threads created.", 2, startAll.length);

        // Wait for students to terminate
        for (Thread t : startAll) {
            t.join();
            System.out.println("Joined: " + t.getName());
        }

        // Check time
        c.tick();
        assertTiming(350);
    }

}
