package com.hospital.history;

import com.hospital.model.PatientRecord;

/**
 * Stores and navigates patient history using a Doubly Linked List.
 * Allows forward and backward navigation through visit records.
 */
public class PatientHistory {

    private PatientRecord head;    // oldest record
    private PatientRecord tail;    // newest record
    private PatientRecord current; // where we are currently viewing
    private int size;

    public PatientHistory() {
        head = null;
        tail = null;
        current = null;
        size = 0;
    }

    // Add a new record to the end (most recent)
    public void addRecord(PatientRecord record) {
        if (head == null) {
            head = record;
            tail = record;
        } else {
            tail.next = record;
            record.prev = tail;
            tail = record;
        }
        current = tail;
        size++;
    }

    // Move to the next (newer) record
    public void goForward() {
        if (current == null) {
            System.out.println("⚠️  No records available.");
            return;
        }
        if (current.next == null) {
            System.out.println("⚠️  Already at the most recent record.");
            return;
        }
        current = current.next;
        System.out.println("➡️  Moved forward.");
        displayCurrent();
    }

    // Move to the previous (older) record
    public void goBackward() {
        if (current == null) {
            System.out.println("⚠️  No records available.");
            return;
        }
        if (current.prev == null) {
            System.out.println("⚠️  Already at the oldest record.");
            return;
        }
        current = current.prev;
        System.out.println("⬅️  Moved backward.");
        displayCurrent();
    }

    // Jump to the newest record
    public void goToNewest() {
        if (tail == null) {
            System.out.println("⚠️  No records available.");
            return;
        }
        current = tail;
        System.out.println("⏭️  Jumped to newest record.");
        displayCurrent();
    }

    // Jump to the oldest record
    public void goToOldest() {
        if (head == null) {
            System.out.println("⚠️  No records available.");
            return;
        }
        current = head;
        System.out.println("⏮️  Jumped to oldest record.");
        displayCurrent();
    }

    // Display the current record
    public void displayCurrent() {
        if (current == null) {
            System.out.println("⚠️  No records available.");
            return;
        }
        System.out.println("\n📄 Current Record:");
        System.out.println("  " + current);
    }

    // Display all records from oldest to newest
    public void displayAll() {
        if (head == null) {
            System.out.println("⚠️  No history records found.");
            return;
        }
        System.out.println("\n📋 Full Patient History (" + size + " records):");
        PatientRecord temp = head;
        int count = 1;
        while (temp != null) {
            System.out.println("  " + count + ". " + temp);
            temp = temp.next;
            count++;
        }
    }

    public int getSize() {
        return size;
    }

    public PatientRecord getCurrent() {
        return current;
    }

    public PatientRecord getHead() {
        return head;
    }

    public PatientRecord getTail() {
        return tail;
    }
}