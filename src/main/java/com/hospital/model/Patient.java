package com.hospital.model;

/**
 * Represents a patient in the hospital system.
 * Used by both the WaitingQueue and the PatientHistory system.
 */
public class Patient {

    private final int patientId;
    private final String name;
    private final String reasonForVisit;

    public Patient(int patientId, String name, String reasonForVisit) {
        this.patientId = patientId;
        this.name = name;
        this.reasonForVisit = reasonForVisit;
    }

    public int getPatientId()      { return patientId; }
    public String getName()        { return name; }
    public String getReasonForVisit() { return reasonForVisit; }

    @Override
    public String toString() {
        return String.format("[ID: %d] %s — Reason: %s", patientId, name, reasonForVisit);
    }
}