import java.util.*;
class RoundRobinCS {
    static int gt = 0; // gt-executed time of cpu
    static LinkedList<Integer> queue = new LinkedList<>();

    static void enQ(int ele) {
        queue.offer(ele);
    }

    static int deQ() {
        return queue.poll();
    }

    static boolean isQEmpty() {
        return queue.isEmpty();
    }

    public static void main(String[] args) {

        System.out.println("********Round Robin(RR)*********");
        System.out.println("Name : Harmik Sarvaliya");
        System.out.println("Er.no : 230317");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter total no. of processes: ");
        int process = sc.nextInt();

        int[] id = new int[process];
        int[] at = new int[process];
        int[] bt = new int[process];
        int[] ct = new int[process];
        int[] tat = new int[process];
        int[] wt = new int[process];
        int[] rbt = new int[process]; // remaining burst time
        int[] key = new int[process]; // determines whether process is present in queue or not

        System.out.println("\nEnter Arrival Time and Burst Time for each process: ");
        for (int i = 0; i < process; i++) {
            id[i] = i + 1;
            at[i] = sc.nextInt();
            bt[i] = sc.nextInt();
            rbt[i] = bt[i];
            key[i] = 0;
        }

        System.out.print("Enter Time quantum: ");
        int tq = sc.nextInt();

        System.out.print("Enter Context Switch Time: ");
        int contextSwitchTime = sc.nextInt();

        enQ(0); // Enqueue the first process ID (pID starts from 1 but LinkedList index starts from 0)
        key[0] = 1; // Mark the first process as present in the queue
        gt = at[0];
        int lastExecuted = -1; // Index of the last executed process

        while (!isQEmpty()) {
            int idx = deQ(); // Dequeue a process ID

            // Context Switch
            if (lastExecuted != -1 && lastExecuted != idx) {
                gt += contextSwitchTime;
            }

            int remainingTime = rbt[idx]; // Store the remaining burst time before execution

            if (remainingTime <= tq) {
                gt += remainingTime;
                rbt[idx] = 0;
                ct[idx] = gt;
            } else {
                gt += tq;
                rbt[idx] -= tq;
            }

            lastExecuted = idx; // Update the last executed process

            // Enqueue processes that have arrived and have remaining burst time
            for (int i = 0; i < process; i++) {
                if (rbt[i] > 0 && at[i] <= gt && key[i] == 0) {
                    enQ(i); // Enqueue the process ID
                    key[i] = 1; // Mark the process as present in the queue
                }
            }
            // Enqueue the current process again if it still has remaining burst time
            if (rbt[idx] > 0) {
                enQ(idx);
            }

        }

        float avgTAT = 0, avgWT = 0;
        for (int i = 0; i < process; i++) {
            tat[i] = ct[i] - at[i];
            wt[i] = tat[i] - bt[i];
            avgTAT += tat[i];
            avgWT += wt[i];
        }

        System.out.println("PROCESS | ARRIVAL TIME | BRUST TIME | FINISH TIME | TURNAROUND TIME | WAITING TIME ");
        for (int i = 0; i < process; i++) {
            System.out.println(id[i] + "\t\t" + at[i] + "\t\t" + bt[i] + "\t" + ct[i] + "\t\t" + tat[i] + "\t\t" + wt[i]);
        }

        System.out.printf("\nAverage Turn around time = %.2f", avgTAT / process);
        System.out.printf("\nAverage Waiting time = %.2f\n", avgWT / process);
    }
}
