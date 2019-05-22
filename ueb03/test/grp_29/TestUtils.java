package grp_29;

import mensa.AlgorithmType;
import mensa.Student;
import vorgegebenes.TestToolkit;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * Diese Hilfsklasse dient der Kapselung von Testformulierung und Testdurchführung. Sie bietet
 * Instanzmethoden an, die die Parameter der zu testenden Situation annehmen und dann eine
 * Testumgebung erstellen, in welcher das Zeitverhalten der verschiedenen Algorithmen getestet wird.
 * So muss nicht jeder Test unnötig viel Code enthalten sondern muss nur die Rahmenbedingungen
 * definieren.
 *
 * @author Pascal Frenz (inf102893)
 */
public class TestUtils extends TestToolkit {


    public void testBWithTwoStudentsAndOneStep(
            long seasoningTime,
            long eatingTime,
            long expectedTiming) throws InterruptedException {
        // Just a call of the more general method
        this.testBWithManyStudentsAndManySteps(
                2,
                1,
                seasoningTime,
                eatingTime,
                expectedTiming,
                null
        );
    }

    /**
     * Testet das Zeitverhalten des Algorithmus B, ohne dabei eine Ergebnisliste zu erstellen.
     *
     * @param students Anzahl der beteiligten Studenten.
     * @param steps Anzahl der durchzuführenden Schritte.
     * @param seasoningTime Würzzeit.
     * @param eatingTime Esszeit.
     * @param expectedMinimumTime Minimal erwartete Algorithmuslaufzeit.
     * @param expectedMaximumTime Maximal erwartete Algorithmuslaufzeit.
     *
     * @throws InterruptedException Wenn der testausführende Thread unterbrochen wird.
     */
    public void testBWithManyStudentsAndManySteps(
            int students,
            int steps,
            long seasoningTime,
            long eatingTime,
            Long expectedMinimumTime,
            Long expectedMaximumTime) throws InterruptedException {
        // Timer starten
        c.tick();

        // Threads erstellen
        final Thread[] startAll = m.startAll(
                students,
                steps,
                seasoningTime,
                eatingTime,
                0,
                AlgorithmType.B,
                null
        );

        assertEquals("Not all threads created.", students, startAll.length);

        // Auf gestartete Threads warten
        for (Thread t : startAll) {
            t.join();
            System.out.println("Joined: " + t.getName());
        }

        // Timer beenden
        c.tick();

        // Zeit überprüfen
        if (expectedMaximumTime == null) {
            assertTiming(expectedMinimumTime);
        } else {
            assertTiming(expectedMinimumTime, expectedMaximumTime);
        }
    }

    public List<Student> testCWithManyStudentsAndManyStepsNoTiming(
            int students,
            int steps,
            long seasoningTime,
            long eatingTime,
            long survivalTime) throws InterruptedException {

        return this.testCWithManyStudentsAndManySteps(
                students,
                steps,
                seasoningTime,
                eatingTime,
                survivalTime,
                null,
                null
        );
    }

    /**
     * Testet das Zeitverhalten des Algorithmus C. Testet Außerdem, dass die richtige Anzahl an
     * Threads (2n) und Studenten (n) erstellt wird. Dazu wird getestet, ob auch alle Threads wieder
     * beendet werden. Es wird die Liste der erstellten Studenten zurück geliefert, um Eigenschaften
     * nach Ende der Auswertung zu überprüfen.
     *
     * @param students Anzahl der beteiligten Studenten.
     * @param steps Anzahl der durchzuführenden Schritte.
     * @param seasoningTime Würzzeit.
     * @param eatingTime Esszeit.
     * @param expectedMinimumTime Minimal erwartete Algorithmuslaufzeit.
     * @param expectedMaximumTime Maximal erwartete Algorithmuslaufzeit.
     *
     * @return Die erstellte Liste der Studenten.
     *
     * @throws InterruptedException Wenn der testausführende Thread unterbrochen wird.
     */
    public List<Student> testCWithManyStudentsAndManySteps(
            int students,
            int steps,
            long seasoningTime,
            long eatingTime,
            long survivalTime,
            Long expectedMinimumTime,
            Long expectedMaximumTime) throws InterruptedException {
        // Timer starten
        c.tick();

        // Liste mit Studenten besorgen
        final List<Student> studentList = new LinkedList<>();

        // Threads starten
        final Thread[] startAll = m.startAll(
                students,
                steps,
                seasoningTime,
                eatingTime,
                survivalTime,
                AlgorithmType.C,
                studentList
        );

        // Erzeugungen auf Korrektheit überprüfen
        assertEquals("Es wurden nicht alle Threads erzeugt", students, startAll.length);
        assertEquals("Es wurden nicht alle Studenten erzeugt", students, studentList.size());

        // Auf Threads warten
        for (Thread t : startAll) {
            t.join();
            System.out.println("joined " + t.getName());
        }

        // Timer beenden
        c.tick();

        // Timing überprüfen
        if (expectedMinimumTime != null || expectedMaximumTime != null) {
            if (expectedMaximumTime == null) {
                assertTiming(expectedMinimumTime);
            } else if (expectedMinimumTime != null) {
                assertTiming(expectedMinimumTime, expectedMaximumTime);
            } else {
                throw new IllegalArgumentException("Invalider Aufruf!");
            }
        }

        Thread.sleep(EPS); // warten, bis sich auch die Timer beendet haben!
        assertEquals(NUM_NON_DEAMON, getNonDaemonThreads());

        // Liste für spätere Überprüfungen zurückgeben
        return studentList;
    }

    public List<Student> testCWithManyStudentsAndManySteps(
            int students,
            int steps,
            long seasoningTime,
            long eatingTime,
            long survivalTime,
            Long expectedMinimumTime) throws InterruptedException {

        return this.testCWithManyStudentsAndManySteps(
                students,
                steps,
                seasoningTime,
                eatingTime,
                survivalTime,
                expectedMinimumTime,
                null
        );
    }
}
