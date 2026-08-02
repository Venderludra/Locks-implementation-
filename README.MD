# COS 226 - Practical 1: Mutual Exclusion Algorithms

## Overview

This project implements and demonstrates three classical two-thread mutual
exclusion (locking) algorithms from *The Art of Multiprocessor Programming*
(Herlihy & Shavit):

1. **LockOne**
2. **LockTwo**
3. **Peterson's Lock**

Each lock is tested against two concurrent threads incrementing a shared
counter, to demonstrate correct behaviour, failure conditions, and how
Peterson's Lock resolves the weaknesses of the first two algorithms.

## How to Run

### 1. Compile

From the project folder (the one containing all the `.java` files), run:

```bash
javac Lock.java LockOne.java LockTwo.java PetersonLock.java ThreadID.java SharedCounter.java Worker.java Main.java
```

(If your terminal supports wildcards, `javac *.java` also works — this
fails in some PowerShell configurations, hence the explicit file list
above.)

### 2. Run

```bash
java Main
```

### 3. What You'll See

The program runs each lock in turn and prints thread activity (waiting,
acquiring, incrementing, releasing) to the console, followed by a final
counter value. Expected final counter value for each successful run is
**10** (2 threads × 5 increments each).

## Files

| File | Purpose |
|---|---|
| `Lock.java` | Interface defining `lock()` and `unlock()` — the contract all lock implementations follow |
| `LockOne.java` | LockOne algorithm — uses a `flag[]` array |
| `LockTwo.java` | LockTwo algorithm — uses a `victim` variable |
| `PetersonLock.java` | Peterson's Lock — combines `flag[]` and `victim` |
| `ThreadID.java` | Assigns each thread a consistent ID (0 or 1); includes `reset()` to reuse IDs across multiple tests |
| `SharedCounter.java` | Shared resource (`counter`) protected by a lock; contains the critical section (`counter++`) |
| `Worker.java` | Thread class that calls `increment()` on the shared counter 5 times |
| `Main.java` | Test harness — runs each lock against two threads and prints results |

## Required Feature Locations

- **LockOne implementation** → `LockOne.java`, lines implementing `lock()`/`unlock()` using `flag[]`
- **LockTwo implementation** → `LockTwo.java`, lines implementing `lock()`/`unlock()` using `victim`
- **Peterson's Lock implementation** → `PetersonLock.java`, lines implementing `lock()`/`unlock()` using `flag[]` **and** `victim`
- **Two-thread demonstration** → `Main.java` (`testLockConcurrent` / `testLockSequential`) and `Worker.java`

## Notes on Test Modes

`Main.java` includes two ways of running a lock against two threads:

- **Concurrent test** (`testLockConcurrent`) — both threads start at the
  same time. This is the standard test and is used for LockTwo and
  Peterson's Lock.
- **Sequential test** (`testLockSequential`) — thread 0 runs to completion
  before thread 1 starts. This is used to demonstrate LockOne succeeding
  under conditions where it doesn't deadlock.

`ThreadID.reset()` is called before every test so that each new pair of
threads is correctly numbered 0 and 1 (see Presenter Notes for why this
is necessary).

## Known Behaviour (Expected, Not Bugs)

- **LockOne under concurrent threads will deadlock** (hang indefinitely).
  This is expected and matches the textbook's description of LockOne's
  weakness. Only run LockOne in sequential mode, or be ready to explain
  the deadlock live if demonstrating the concurrent failure on purpose.
- **LockTwo will deadlock if one thread finishes all its work before the
  other.** This is also expected — see Presenter Notes for the
  explanation.

## Group Members
Liam du Toit
Nico Sibiya
Tshegofatso Kungwane