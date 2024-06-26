# OS-Algorithm-

Operating system scheduling algorithms determine the order in which processes are executed by the CPU. Here are some common scheduling algorithms:

1. First-Come, First-Served (FCFS)
Description: Processes are executed in the order they arrive in the ready queue.
Pros: Simple to implement.
Cons: Can cause long wait times, especially for short processes behind long ones (convoy effect).

3. Shortest Job Next (SJN)
Description: Processes with the shortest burst time are executed first.
Pros: Minimizes average waiting time.
Cons: Requires knowledge of future process lengths; can cause starvation for long processes.

5. Priority Scheduling
Description: Each process is assigned a priority, and the CPU executes the highest priority process first.
Pros: Flexibility in assigning process importance.
Cons: Can cause starvation for low-priority processes; aging can be used to mitigate this.

6. Round Robin (RR)
Description: Each process is given a fixed time slice (quantum) and is executed in a cyclic order.
Pros: Fair allocation of CPU time; good for time-sharing systems.
Cons: Context switching overhead; performance depends on the length of the time quantum.

7. Shortest Remaining Time (SRT)
Description: Preemptive version of SJN, where the process with the shortest remaining time is executed next.
Pros: Can be more efficient than SJN.
Cons: High overhead due to frequent context switches; requires precise knowledge of remaining times.

These scheduling algorithms balance various factors like fairness, efficiency, and complexity to manage CPU time effectively and ensure optimal system performance.
