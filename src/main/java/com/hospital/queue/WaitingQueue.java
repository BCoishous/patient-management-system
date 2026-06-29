package com.hospital.queue;

import com.hospital.model.Patient;

/**
 * Simulates a hospital waiting room using a Queue (FIFO).
 * Built manually using a linked node structure — no Java built-in Queue used.
 */
public class WaitingQueue {

    // Internal node class — each node holds a patient and points to the next
    private static class Node {
        Patient patient;
        Node next;

        Node(Patient patient) {
            this.patient = patient;
            this.next = null;
        }
    }

    private Node front; // next patient to be served
    private Node rear;  // last patient who joined
    private int size;

    public WaitingQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    // Add a patient to the back of the queue
    public void addPatient(Patient patient) {
        Node newNode = new Node(patient);
        if (rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
        System.out.println("✅ Added to queue: " + patient);
    }

    // Remove and return the next patient (front of queue)
    public Patient serveNext() {
        if (isEmpty()) {
            System.out.println("⚠️  No patients in the queue.");
            return null;
        }
        Patient served = front.patient;
        front = front.next;
        if (front == null) rear = null;
        size--;
        System.out.println("🏥 Now serving: " + served);
        return served;
    }

    // Insert a patient at a specific position (for emergency cases)
    public void insertAtPosition(Patient patient, int position) {
        if (position <= 1 || isEmpty()) {
            Node newNode = new Node(patient);
            newNode.next = front;
            front = newNode;
            if (rear == null) rear = newNode;
            size++;
            System.out.println("🚨 Emergency insert at front: " + patient);
            return;
        }

        Node newNode = new Node(patient);
        Node current = front;
        for (int i = 1; i < position - 1 && current.next != null; i++) {
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;
        if (newNode.next == null) rear = newNode;
        size++;
        System.out.println("🚨 Emergency insert at position " + position + ": " + patient);
    }

    // Print all patients currently in the queue
    public void printQueue() {
        if (isEmpty()) {
            System.out.println("⚠️  The waiting room is empty.");
            return;
        }
        System.out.println("\n📋 Current Waiting Room (" + size + " patients):");
        Node current = front;
        int position = 1;
        while (current != null) {
            System.out.println("  " + position + ". " + current.patient);
            current = current.next;
            position++;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }
}