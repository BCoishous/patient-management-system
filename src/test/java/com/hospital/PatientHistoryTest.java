package com.hospital;

import com.hospital.history.PatientHistory;
import com.hospital.model.PatientRecord;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for PatientHistory (Part 2 - Doubly Linked List).
 */
public class PatientHistoryTest {

    @Test
    void testGoToNewestAndOldest() {
        PatientHistory history = new PatientHistory();
        history.addRecord(new PatientRecord("2024-01-01", "Cold", "Rest"));
        history.addRecord(new PatientRecord("2024-02-01", "Flu", "Medication"));
        history.addRecord(new PatientRecord("2024-03-01", "Checkup", "All good"));

        history.goToOldest();
        assertEquals("Cold", history.getCurrent().getDiagnosis());

        history.goToNewest();
        assertEquals("Checkup", history.getCurrent().getDiagnosis());
    }

    @Test
    void testForwardAndBackwardNavigation() {
        PatientHistory history = new PatientHistory();
        history.addRecord(new PatientRecord("2024-01-01", "Cold", "Rest"));
        history.addRecord(new PatientRecord("2024-02-01", "Flu", "Medication"));
        history.addRecord(new PatientRecord("2024-03-01", "Checkup", "All good"));

        history.goToOldest();
        history.goForward();

        assertEquals("Flu", history.getCurrent().getDiagnosis());

        history.goBackward();
        assertEquals("Cold", history.getCurrent().getDiagnosis());
    }

    @Test
    void testCannotNavigatePastBoundaries() {
        PatientHistory history = new PatientHistory();
        history.addRecord(new PatientRecord("2024-01-01", "Cold", "Rest"));
        history.addRecord(new PatientRecord("2024-02-01", "Flu", "Medication"));

        history.goToOldest();
        history.goBackward(); // should stay at oldest, not crash
        assertEquals("Cold", history.getCurrent().getDiagnosis());

        history.goToNewest();
        history.goForward(); // should stay at newest, not crash
        assertEquals("Flu", history.getCurrent().getDiagnosis());
    }
}