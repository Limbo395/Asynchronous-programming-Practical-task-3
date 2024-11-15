# Asynchronous-programming-Practical-task-3

## **Project Description**
This project demonstrates the use of the Fork/Join Framework in Java to solve computational tasks with two approaches to task balancing: **Work Stealing** and **Work Dealing**. The program calculates the minimum value in a randomly generated matrix and measures the time taken for both approaches.

## **Features**
- **Work Stealing:** Tasks are dynamically reassigned to idle threads.
- **Work Dealing:** Tasks are distributed equally among threads at the start.
- Time measurement for both approaches.

## **How to Compile and Run**

1. Compile the Main Program
Run the following command from the root directory:

```javac -d out src/main/java/forkjoin/*.java src/main/java/utils/*.java src/main/java/forkjoin/Main.java```
2. Compile the Tests
Run the following command to compile test classes:

```javac -d out -cp out src/test/java/tests/*.java```
3. Run the Main Program
Execute the program to test both approaches:

```java -cp out main.java.forkjoin.Main```
4. Run Individual Tests
You can run each test individually:

WorkStealingTest:
```java -cp out test.java.tests.WorkStealingTest```

WorkDealingTest:
```java -cp out test.java.tests.WorkDealingTest```

MainTest:
```java -cp out test.java.tests.MainTest```

## **Understanding the Difference Between Work Stealing and Work Dealing**

Work Stealing

- Dynamic Task Distribution: Tasks are initially distributed to threads, but idle threads can “steal” tasks from busy threads’ queues.
- Performance: This approach minimizes idle time for threads but may add overhead due to task stealing.
- Use Case: Suitable for uneven or unpredictable workloads.

Work Dealing

- Static Task Distribution: Tasks are evenly divided among threads at the beginning, with no reassignment.
- Performance: This approach has lower overhead but may lead to idle threads if tasks are unevenly distributed.
- Use Case: Best for tasks of roughly equal size and duration.

## **Explanation of Execution Time Difference Between Work Stealing and Work Dealing**

The observed difference in execution time between the Work Stealing and Work Dealing approaches is due to how tasks are distributed and processed in the Fork/Join Framework:
1. Work Stealing:
- This approach dynamically redistributes tasks among worker threads that are idle. While it is highly efficient for load balancing, the constant monitoring and task-stealing mechanism introduce additional overhead.
- For smaller datasets or tasks that are already well-balanced, the cost of task-stealing might outweigh its benefits, leading to slightly longer execution times.
2. Work Dealing:
- Tasks are distributed evenly among worker threads at the start, avoiding the overhead of dynamic load balancing. If the workload is evenly divisible and predictable, this approach can be faster because there is no need for threads to “steal” tasks dynamically.
- However, it can suffer from inefficiencies if the workload is unbalanced or unpredictable, as some threads might finish earlier and remain idle.

Conclusion

In this case, Work Dealing performed better because:
- The workload (matrix processing) was evenly divisible.
- There was minimal need for dynamic redistribution of tasks.

For larger and less predictable workloads, Work Stealing might outperform Work Dealing by ensuring all threads remain busy. The choice of approach depends on the nature of the problem and the workload characteristics.

Example Results:
Approach	      Time (nanoseconds)
Work Stealing	  834958
Work Dealing	  468917