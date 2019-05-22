package grp_29;

import mensa.AlgorithmType;
import mensa.Student;
import org.junit.Assert;
import org.junit.Test;
import vorgegebenes.TestToolkit;

import java.util.LinkedList;
import java.util.List;

public class OwnAlgoCTests extends TestToolkit {

    /**
     * C. 3 Studenten. 2 Durchlauf. Test mit einem verhungernden Studenten.
     *
     */
    @Test
    public void testC1() throws InterruptedException {

        System.out.println();
        System.out.println("Test C1");
        System.out.println();

        c.tick();

        // Diesmal Liste mit Studenten besorgen
        final List<Student> students = new LinkedList<>();

        final Thread[] startAll = m.startAll(3, 2, 100, 100, 250, AlgorithmType.C, students);

        Assert.assertEquals("Es wurden nicht alle Threads erzeugt", 3, startAll.length);
        Assert.assertEquals("Es wurden nicht alle Studenten erzeugt", 3, students.size());

        for (Thread t : startAll) {
            t.join();
            System.out.println("joined " + t.getName());
        }

        c.tick();
        assertTiming(500,600);

        Thread.sleep(TestToolkit.EPS); // warten, bis sich auch die Timer beendet haben!
        Assert.assertEquals(TestToolkit.NUM_NON_DEAMON, TestToolkit.getNonDaemonThreads());

        // einer soll verhungert sein
        int starved = 0;
        for (Student s : students) {
            starved += s.isStarved() ? 1 : 0;
        }

        Assert.assertEquals(1, starved);

    }

}
