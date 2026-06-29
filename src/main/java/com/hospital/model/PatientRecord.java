package com.hospital.model;

/**
 * Represents a single historical visit record.
 * Used in the PatientHistory system to track a patient's medical history.
 */
public class PatientRecord {

    private final String visitDate;
    private final String diagnosis;
    private final String treatmentNotes;

    public PatientRecord next;
    public PatientRecord prev;

    public PatientRecord(String visitDate, String diagnosis, String treatmentNotes) {
        this.visitDate = visitDate;
        this.diagnosis = diagnosis;
        this.treatmentNotes = treatmentNotes;
        this.next = null;
        this.prev = null;
    }

    public String getVisitDate()      { return visitDate; }
    public String getDiagnosis()      { return diagnosis; }
    public String getTreatmentNotes() { return treatmentNotes; }

    @Override
    public String toString() {
        return String.format("Date: %-12s | Diagnosis: %-25s | Treatment: %s",
                visitDate, diagnosis, treatmentNotes);
    }
}