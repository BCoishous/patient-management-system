# Patient Management System

A console app in Java that simulates a hospital waiting room and patient history browser. The waiting room uses a Queue and the history system uses a Doubly Linked List.

---

## Running the Program

```bash
mvn compile
mvn exec:java
```

## Running Tests

```bash
mvn test
```

---

## Part 1 – Waiting Room Queue

**Why was a Queue appropriate for the waiting room?**
A waiting room runs on the idea that whoever shows up first gets seen first.
You add to the back and remove from the front so the order is always preserved.

**The FIFO Principle**
FIFO stands for First In First Out. The patient who has been waiting the longest
is always next. In the code addPatient() puts someone at the rear and serveNext()
pulls from the front. That gap between the two is the waiting room.

**What if we used a Stack instead?**
A Stack is LIFO, last in first out. The last person to walk in gets served first.
In a waiting room that means whoever just arrived jumps ahead of everyone already
sitting there. People who came in early could wait forever.

**Ways to Improve This System**
Adding urgency levels would help a lot so someone with chest pain isn't stuck
behind a sprained ankle. Timestamps on each patient would let staff see who has
been waiting too long. Saving the queue to a database would mean a restart
doesn't wipe everyone out. A proper UI would also make it usable for actual
reception staff.

---

## Part 2 – Patient History System

**Why a Doubly Linked List for patient history?**
Doctors don't read records start to finish every time. They jump around, go back
a couple visits, come forward again. A DLL makes that natural because every node
knows both its previous and next record. You can move freely in either direction
without starting over from the beginning.

**What happens if you forget to update the pointers?**
The list breaks and you won't always know it right away. If you add a record and
forget to set prev, backward navigation just stops at that point and hits null.
If you forget next you lose everything after that node. No error gets thrown, the
data just vanishes from navigation. Both pointers need to be updated every time
you touch the list or things go wrong quietly.

**DLL vs Array vs ArrayList**
Arrays have a fixed size so you'd have to guess upfront how many visits a patient
will ever have which isn't realistic. ArrayLists resize on their own but going
backwards means tracking an index yourself and inserting in the middle is slow
because everything has to shift over. The DLL just updates two pointers per
operation and naturally supports going both directions. For a history browser
it makes the most sense.

---

## Unit Tests

**Test 1 — testAddPatientIncreasesSize**
Checks that adding a patient bumps the size up and flips isEmpty to false.
Sanity check on the add operation.

**Test 2 — testServeNextFollowsFIFOOrder**
Adds Alice then Bob, calls serveNext, and checks that Alice comes out first.
If this fails the queue isn't behaving like a queue.

**Test 3 — testServeNextOnEmptyQueueReturnsNull**
Calls serveNext on an empty queue. Should return null and not crash.
Tests the empty queue guard.

**Test 4 — testEmergencyInsertAtFront**
Inserts an emergency patient at position 1 after two people are already waiting.
Checks the emergency patient is served before the others.

**Test 5 — testGoToNewestAndOldest**
Jumps to the oldest record and checks the diagnosis then jumps to newest and
checks again. Verifies the head and tail pointers are set correctly.

**Test 6 — testForwardAndBackwardNavigation**
Starts at the oldest record, steps forward one and checks the diagnosis, then
steps back and checks again. Makes sure next and prev are wired up right.

**Test 7 — testCannotNavigatePastBoundaries**
Tries to go backward from the oldest record and forward from the newest.
Neither should crash or move the pointer somewhere invalid.
