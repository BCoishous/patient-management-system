package com.hospital.ui;

import com.hospital.history.PatientHistory;
import com.hospital.model.Patient;
import com.hospital.model.PatientRecord;
import com.hospital.queue.WaitingQueue;

import java.util.Scanner;

/**
 * Ties together the WaitingQueue and PatientHistory via a console menu.
 */
public class Main {

    static WaitingQueue queue = new WaitingQueue();
    static PatientHistory history = new PatientHistory();
    static Scanner scanner = new Scanner(System.in);
    static int nextPatientId = 1;

    public static void main(String[] args) {
        loadSampleHistory();
        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addPatient();
                case "2" -> queue.serveNext();
                case "3" -> emergencyInsert();
                case "4" -> queue.printQueue();
                case "5" -> history.goForward();
                case "6" -> history.goBackward();
                case "7" -> history.goToNewest();
                case "8" -> history.goToOldest();
                case "9" -> history.displayCurrent();
                case "10" -> history.displayAll();
                case "0" -> {
                    System.out.println("👋 Exiting Patient Management System. Goodbye!");
                    running = false;
                }
                default -> System.out.println("⚠️  Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║     PATIENT MANAGEMENT SYSTEM        ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║  WAITING ROOM                        ║");
        System.out.println("║  1. Add patient to queue             ║");
        System.out.println("║  2. Serve next patient               ║");
        System.out.println("║  3. Emergency insert                 ║");
        System.out.println("║  4. View waiting room                ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║  PATIENT HISTORY                     ║");
        System.out.println("║  5. Next record                      ║");
        System.out.println("║  6. Previous record                  ║");
        System.out.println("║  7. Newest record                    ║");
        System.out.println("║  8. Oldest record                    ║");
        System.out.println("║  9. Current record                   ║");
        System.out.println("║  10. View all history                ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║  0. Exit                             ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.print("Choose an option: ");
    }

    private static void addPatient() {
        System.out.print("Enter patient name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter reason for visit: ");
        String reason = scanner.nextLine().trim();
        queue.addPatient(new Patient(nextPatientId++, name, reason));
    }

    private static void emergencyInsert() {
        System.out.print("Enter patient name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter reason for visit: ");
        String reason = scanner.nextLine().trim();
        System.out.print("Enter position to insert at: ");
        int position = Integer.parseInt(scanner.nextLine().trim());
        queue.insertAtPosition(new Patient(nextPatientId++, name, reason), position);
    }

    private static void loadSampleHistory() {
        history.addRecord(new PatientRecord("2024-01-10", "Common Cold", "Rest and fluids"));
        history.addRecord(new PatientRecord("2024-02-14", "Sprained Ankle", "Ice and elevation"));
        history.addRecord(new PatientRecord("2024-03-05", "Hypertension", "Prescribed Lisinopril"));
        history.addRecord(new PatientRecord("2024-04-18", "Allergic Reaction", "Prescribed antihistamine"));
        history.addRecord(new PatientRecord("2024-05-22", "Migraine", "Prescribed Sumatriptan"));
        history.addRecord(new PatientRecord("2024-06-30", "Diabetes Check", "Adjusted insulin dosage"));
        history.addRecord(new PatientRecord("2024-07-11", "Fractured Wrist", "Cast applied for 6 weeks"));
        history.addRecord(new PatientRecord("2024-08-19", "Chest Pain", "ECG normal, referred to cardiology"));
        history.addRecord(new PatientRecord("2024-09-25", "Ear Infection", "Prescribed amoxicillin"));
        history.addRecord(new PatientRecord("2024-10-31", "Annual Checkup", "All vitals normal"));
    }
} 