import mensa.AlgorithmType;
import mensa.Student;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class CTest extends TestToolkit {

    @Test
    public void ifStarvationWorks() throws InterruptedException {

        System.out.println();
        System.out.println("Test C: A Test to check if starvation works at all. Timing tests follow");
        System.out.println();

        // Get the students as list
        final List<Student> students = new LinkedList<>();

        final Thread[] startAll = m.startAll(2, 1, 100, 100, 50, AlgorithmType.C, students);

        Assert.assertEquals("Not all threads created.", 2, startAll.length);
        Assert.assertEquals("Not all students created.", 2, students.size());

        for (Thread t : startAll) {
            t.join();
            System.out.println("joined " + t.getName());
        }

        getNonDaemonThreads();
        Assert.assertEquals(NUM_NON_DEAMON, getNonDaemonThreads());

        // all DEAD
        for (Student s : students) {
            Assert.assertTrue(s.isStarved());
        }

    }

    @Test
    public void ifOnlyOneThreadAtEnd() throws InterruptedException {

        System.out.println();
        System.out.println("Test C: A Test to check if at the end there's only the main thread left. Timer and students should be dead.");
        System.out.println();

        // Get the students as list
        final List<Student> students = new LinkedList<>();

        final Thread[] startAll = m.startAll(2, 1, 100, 100, 50, AlgorithmType.C, students);

        Assert.assertEquals("Not all threads created.", 2, startAll.length);
        Assert.assertEquals("Not all students created.", 2, students.size());

        // Waiting for the studeNTS TO STARVE x.x
        for (Thread t : startAll) {
            t.join();
            System.out.println("joined " + t.getName());
        }

        getNonDaemonThreads();
        Assert.assertEquals(NUM_NON_DEAMON, getNonDaemonThreads());

    }

    // *** Timer Tests ***
    @Test
    public void if_eatingTime_EQ_starvationTime_EQ_seasoningTime_works() throws InterruptedException {

        System.out.println();
        System.out.println("Test C: eatingTime = starvationTime = seasoningTime");
        System.out.println();

        c.tick();

        // Get the students as list
        final List<Student> students = new LinkedList<>();

        final Thread[] startAll = m.startAll(2, 1, 100, 100, 100, AlgorithmType.C, students);

        Assert.assertEquals("Es wurden nicht alle Threads erzeugt", 2, startAll.length);

        for (Thread t : startAll) {
            t.join();
            System.out.println("Joined: " + t.getName());
        }

        c.tick();
        assertTiming(100);

        // all DEAD
        for (Student s : students) {
            Assert.assertTrue(s.isStarved());
        }
    }

    @Test
    public void if_eatingTime_EQ_starvationTime_GT_seasoningTime_works() throws InterruptedException {

        System.out.println();
        System.out.println("Test C: eatingTime = starvationTime > seasoningTime");
        System.out.println();

        c.tick();

        // Get the students as list
        final List<Student> students = new LinkedList<>();

        final Thread[] startAll = m.startAll(2, 1, 50, 100, 100, AlgorithmType.C, students);

        Assert.assertEquals("Es wurden nicht alle Threads erzeugt", 2, startAll.length);

        for (Thread t : startAll) {
            t.join();
            System.out.println("Joined: " + t.getName());
        }

        // Here we need to reset the timer for at least one Thread. Sadly that's too late for the other one,
        // so we have 1 dead student.

        c.tick();
        assertTiming(150);

        // one DEAD
        Assert.assertEquals(1, students.stream().filter(Student::isStarved).count());
    }

    @Test
    public void if_eatingTime_EQ_starvationTime_LT_seasoningTime_works() throws InterruptedException {

        System.out.println();
        System.out.println("Test C: eatingTime = starvationTime < seasoningTime");
        System.out.println();

        c.tick();

        // Get the students as list
        final List<Student> students = new LinkedList<>();

        final Thread[] startAll = m.startAll(2, 1, 200, 100, 100, AlgorithmType.C, students);

        Assert.assertEquals("Es wurden nicht alle Threads erzeugt", 2, startAll.length);

        for (Thread t : startAll) {
            t.join();
            System.out.println("Joined: " + t.getName());
        }

        c.tick();
        assertTiming(100);

        // all DEAD
        for (Student s : students) {
            Assert.assertTrue(s.isStarved());
        }
    }

    @Test
    public void if_eatingTime_GT_starvationTime_EQ_seasoningTime_works() throws InterruptedException {

        System.out.println();
        System.out.println("Test C: eatingTime > starvationTime = seasoningTime");
        System.out.println();

        c.tick();

        // Get the students as list
        final List<Student> students = new LinkedList<>();

        final Thread[] startAll = m.startAll(2, 1, 50, 100, 50, AlgorithmType.C, students);

        Assert.assertEquals("Es wurden nicht alle Threads erzeugt", 2, startAll.length);

        for (Thread t : startAll) {
            t.join();
            System.out.println("Joined: " + t.getName());
        }

        c.tick();
        assertTiming(50);

        // all DEAD
        for (Student s : students) {
            Assert.assertTrue(s.isStarved());
        }
    }

    @Test
    public void if_eatingTime_GT_starvationTime_GT_seasoningTime_works() throws InterruptedException {

        System.out.println();
        System.out.println("Test C: eatingTime > starvationTime > seasoningTime");
        System.out.println();

        c.tick();

        // Get the students as list
        final List<Student> students = new LinkedList<>();

        final Thread[] startAll = m.startAll(2, 1, 25, 100, 50, AlgorithmType.C, students);

        Assert.assertEquals("Es wurden nicht alle Threads erzeugt", 2, startAll.length);

        for (Thread t : startAll) {
            t.join();
            System.out.println("Joined: " + t.getName());
        }

        // The timer is being set back while eating. Depending on the students figuring out the SpicceShakers on time,
        // we can have at most one dead student

        c.tick();
        assertTiming(125, 150);

        // at least one ALIVE
        Assert.assertTrue(students.stream().anyMatch(s -> !s.isStarved()));
    }

    @Test
    public void if_eatingTime_GT_starvationTime_LT_seasoningTime_works1() throws InterruptedException {

        System.out.println();
        System.out.println("Test C: eatingTime > starvationTime < seasoningTime, eatingTime < seasoningTime");
        System.out.println();

        c.tick();

        // Get the students as list
        final List<Student> students = new LinkedList<>();

        final Thread[] startAll = m.startAll(2, 1, 200, 100, 50, AlgorithmType.C, students);

        Assert.assertEquals("Es wurden nicht alle Threads erzeugt", 2, startAll.length);

        for (Thread t : startAll) {
            t.join();
            System.out.println("Joined: " + t.getName());
        }

        c.tick();
        assertTiming(50);

        // all DEAD
        for (Student s : students) {
            Assert.assertTrue(s.isStarved());
        }
    }

    @Test
    public void if_eatingTime_GT_starvationTime_LT_seasoningTime_works2() throws InterruptedException {

        System.out.println();
        System.out.println("Test C: eatingTime > starvationTime < seasoningTime, eatingTime > seasoningTime");
        System.out.println();

        c.tick();

        // Get the students as list
        final List<Student> students = new LinkedList<>();

        final Thread[] startAll = m.startAll(2, 1, 100, 200, 50, AlgorithmType.C, students);

        Assert.assertEquals("Es wurden nicht alle Threads erzeugt", 2, startAll.length);

        for (Thread t : startAll) {
            t.join();
            System.out.println("Joined: " + t.getName());
        }

        c.tick();
        assertTiming(50);

        // all DEAD
        for (Student s : students) {
            Assert.assertTrue(s.isStarved());
        }
    }

    @Test
    public void if_eatingTime_LT_starvationTime_EQ_seasoningTime_works() throws InterruptedException {

        System.out.println();
        System.out.println("Test C: eatingTime < starvationTime = seasoningTime");
        System.out.println();

        c.tick();

        // Get the students as list
        final List<Student> students = new LinkedList<>();

        final Thread[] startAll = m.startAll(2, 1, 100, 50, 100, AlgorithmType.C, students);

        Assert.assertEquals("Es wurden nicht alle Threads erzeugt", 2, startAll.length);

        for (Thread t : startAll) {
            t.join();
            System.out.println("Joined: " + t.getName());
        }

        c.tick();
        assertTiming(100);

        // all DEAD
        for (Student s : students) {
            Assert.assertTrue(s.isStarved());
        }
    }

    @Test
    public void if_eatingTime_LT_starvationTime_GT_seasoningTime_works1() throws InterruptedException {

        System.out.println();
        System.out.println("Test C: eatingTime < starvationTime > seasoningTime, eatingTime > seasoningTime");
        System.out.println();

        c.tick();

        // Get the students as list
        final List<Student> students = new LinkedList<>();

        final Thread[] startAll = m.startAll(2, 1, 50, 100, 500, AlgorithmType.C, students);

        Assert.assertEquals("Es wurden nicht alle Threads erzeugt", 2, startAll.length);

        for (Thread t : startAll) {
            t.join();
            System.out.println("Joined: " + t.getName());
        }

        c.tick();
        assertTiming(200);

        // all ALIVE
        for (Student s : students) {
            Assert.assertFalse(s.isStarved());
        }
    }

    @Test
    public void if_eatingTime_LT_starvationTime_GT_seasoningTime_works2() throws InterruptedException {

        System.out.println();
        System.out.println("Test C: eatingTime < starvationTime > seasoningTime, eatingTime < seasoningTime");
        System.out.println();

        c.tick();

        // Get the students as list
        final List<Student> students = new LinkedList<>();

        final Thread[] startAll = m.startAll(2, 1, 100, 50, 500, AlgorithmType.C, students);

        Assert.assertEquals("Es wurden nicht alle Threads erzeugt", 2, startAll.length);

        for (Thread t : startAll) {
            t.join();
            System.out.println("Joined: " + t.getName());
        }

        c.tick();
        assertTiming(250);

        // all ALIVE
        for (Student s : students) {
            Assert.assertFalse(s.isStarved());
        }
    }

    @Test
    public void if_eatingTime_LT_starvationTime_GT_seasoningTime_works3() throws InterruptedException {

        System.out.println();
        System.out.println("Test C: eatingTime < starvationTime > seasoningTime, eatingTime = seasoningTime");
        System.out.println();

        c.tick();

        // Get the students as list
        final List<Student> students = new LinkedList<>();

        final Thread[] startAll = m.startAll(2, 1, 50, 50, 500, AlgorithmType.C, students);

        Assert.assertEquals("Es wurden nicht alle Threads erzeugt", 2, startAll.length);

        for (Thread t : startAll) {
            t.join();
            System.out.println("Joined: " + t.getName());
        }

        c.tick();
        assertTiming(150);

        // all ALIVE
        for (Student s : students) {
            Assert.assertFalse(s.isStarved());
        }
    }

    @Test
    public void if_eatingTime_LT_starvationTime_LT_seasoningTime_works() throws InterruptedException {

        System.out.println();
        System.out.println("Test C: eatingTime < starvationTime < seasoningTime");
        System.out.println();

        c.tick();

        // Get the students as list
        final List<Student> students = new LinkedList<>();

        final Thread[] startAll = m.startAll(2, 1, 300, 50, 200, AlgorithmType.C, students);

        Assert.assertEquals("Es wurden nicht alle Threads erzeugt", 2, startAll.length);

        for (Thread t : startAll) {
            t.join();
            System.out.println("Joined: " + t.getName());
        }

        c.tick();
        assertTiming(200);

        // all DEAD
        for (Student s : students) {
            Assert.assertTrue(s.isStarved());
        }
    }
}
