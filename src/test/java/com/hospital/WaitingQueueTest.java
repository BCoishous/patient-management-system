package com.hospital;

import com.hospital.model.Patient;
import com.hospital.queue.WaitingQueue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for WaitingQueue class.
 */
public class WaitingQueueTest {

    @Test
    void testAddPatientIncreasesSize() {
        WaitingQueue queue = new WaitingQueue();
        queue.addPatient(new Patient(1, "John Doe", "Fever"));

        assertEquals(1, queue.getSize());
        assertFalse(queue.isEmpty());
    }

    @Test
    void testServeNextFollowsFIFOOrder() {
        WaitingQueue queue = new WaitingQueue();
        queue.addPatient(new Patient(1, "Alice", "Headache"));
        queue.addPatient(new Patient(2, "Bob", "Fracture"));

        Patient served = queue.serveNext();

        assertEquals(1, served.getPatientId());
        assertEquals("Alice", served.getName());
        assertEquals(1, queue.getSize());
    }

    @Test
    void testServeNextOnEmptyQueueReturnsNull() {
        WaitingQueue queue = new WaitingQueue();

        Patient served = queue.serveNext();

        assertNull(served);
        assertTrue(queue.isEmpty());
    }

    @Test
    void testEmergencyInsertAtFront() {
        WaitingQueue queue = new WaitingQueue();
        queue.addPatient(new Patient(1, "Alice", "Headache"));
        queue.addPatient(new Patient(2, "Bob", "Cold"));

        queue.insertAtPosition(new Patient(3, "Emergency Carl", "Heart Attack"), 1);
        Patient served = queue.serveNext();

        assertEquals(3, served.getPatientId());
        assertEquals("Emergency Carl", served.getName());
    }
}