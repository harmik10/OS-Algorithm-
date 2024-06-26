import java.util.*;
public class Scheduler_All
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n********CPU Scheduling Algorithms*********");
        System.out.println("Name: Harmik Sarvaliya");
        System.out.println("Er.no: 230317 \n");
        System.out.println("Choose a scheduling algorithm to execute:");
        System.out.println("1. First Come First Serve (FCFS)");
        System.out.println("2. Shortest Job First (SJF)");
        System.out.println("3. Priority Scheduling");
        System.out.println("4. Round Robin (RR)\n");
        System.out.print("Enter your choice (1-4): ");
        int choice = sc.nextInt();
        System.out.println(" ");
        
        
        switch (choice) 
        {
            case 1:
                executeFCFS();
                break;
            case 2:
                executeSJF();
                break;
            case 3:
                executePriorityScheduling();
                break;
            case 4:
                executeRoundRobin();
                break;
            default:
                System.out.println("Invalid choice. Please choose a number between 1 and 4.");
        }
    }

    static void executeFCFS() 
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("********First Come First Serve(FCFS)*********");
        System.out.print("Enter the No.of the Process : ");
        int process = sc.nextInt();

        float avgTAT = 0, avgWT = 0, sumT = 0, sumW = 0;
        float[] at = new float[process];
        float[] bt = new float[process];
        float[] ct = new float[process];
        float[] tat = new float[process];
        float[] wt = new float[process];

        System.out.println("Enter the Arrival Time and Burst Time : ");
        for (int i = 0; i < process; i++) 
        {
            at[i] = sc.nextFloat();
            bt[i] = sc.nextFloat();   
        }
        
        System.out.println("ARRIVAL TIME | BRUST TIME | FINISH TIME | TURNAROUND TIME | WAITING TIME ");
        float temp = 0;
        for (int i = 0; i < process; i++) 
        {
            ct[i] = temp + bt[i];  
            temp = ct[i];
            tat[i] = ct[i] - at[i];
            wt[i] = tat[i] - bt[i];
            sumT += tat[i];
            sumW += wt[i];           
        }
        for (int i = 0; i < process; i++) 
        { 
            System.out.println("       " + at[i] + "       " + bt[i] + "          " + ct[i] + "             " + tat[i] + "             " + wt[i]);
        }
        avgTAT = sumT / process;
        avgWT = sumW / process;
        System.out.println("Average Turnaround Time : " + avgTAT);     
        System.out.println("Average Waiting Time : " + avgWT); 
    }

    static void executeSJF() 
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("********Shortest Job First (SJF)*********");

        System.out.print("Enter number of processes: ");
        int process = sc.nextInt();

        int[] id = new int[process];
        int[] at = new int[process];
        int[] bt = new int[process];
        int[] ct = new int[process];
        int[] tat = new int[process];
        int[] wt = new int[process];

        System.out.println("Enter the Arrival Time and Brust Time : ");
        for (int i = 0; i < process; i++) 
        {
            id[i] = i + 1;
            at[i] = sc.nextInt();
            bt[i] = sc.nextInt();
        }

        int[] processActive = new int[process];
        int[] idct = new int[process];

        int executedCount = 0;
        int time = 0;
        while (executedCount < process) 
        {
            int minBurstIndex = -1;
            int minbt = Integer.MAX_VALUE;

            for (int i = 0; i < process; i++) 
            {
                if (processActive[i] == 0 && at[i] <= time && bt[i] < minbt) 
                {
                    minbt = bt[i];
                    minBurstIndex = i;
                }
            }
            if (minBurstIndex != -1) 
            {
                processActive[minBurstIndex] = 1;
                time += bt[minBurstIndex];
                idct[id[minBurstIndex] - 1] = time;
                executedCount++;
            }else{
                time++;
            }
        }

        float avgWaitTime = 0, avgtat = 0;
        
        for (int i = 0; i < process; ++i) 
        {
            ct[i] = idct[id[i] - 1];
            tat[i] = ct[i] - at[i];
            wt[i] = tat[i] - bt[i];
            avgWaitTime += wt[i];
            avgtat += tat[i];
        }

        avgWaitTime /= process;
        avgtat /= process;

        System.out.println("PROCESS | ARRIVAL TIME | BRUST TIME | FINISH TIME | TURNAROUND TIME | WAITING TIME ");
        for (int i = 0; i < process; ++i) 
        {
            System.out.println(id[i] + " \t\t " + at[i] + " \t\t " + bt[i]+" \t " + ct[i] + "  \t\t  " + tat[i]+"  \t\t  "+ wt[i]);
        }
        System.out.println("Average Waiting Time: " + avgWaitTime);
        System.out.println("Average Turnaround Time: " + avgtat);
    }

    static void executePriorityScheduling() 
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("********Priority Scheduling (Non-preemption)*********");

        System.out.print("Enter total number of processes : ");
        int process = sc.nextInt();

        int[] id = new int[process];
        int[] at = new int[process];
        int[] bt = new int[process];
        int[] ct = new int[process];
        int[] tat = new int[process];
        int[] wt = new int[process];
        int[] priority = new int[process];

        System.out.println("Enter Process-id, Arrival-Time, Burst-Time, and Priority for each process (Lowest Value High Priority):");
        for (int i = 0; i < process; i++) 
        {
            id[i] = sc.nextInt();
            at[i] = sc.nextInt();
            bt[i] = sc.nextInt();
            priority[i] = sc.nextInt();
        }

        for (int i = 0; i < process - 1; i++) 
        {
            for (int j = i + 1; j < process; j++)
            {
                if (at[i] > at[j] || (at[i] == at[j] && priority[i] > priority[j])
                        || (at[i] == at[j] && priority[i] == priority[j] && id[i] > id[j])) 
                {
                    int temp = id[i];
                    id[i] = id[j];
                    id[j] = temp;

                    temp = at[i];
                    at[i] = at[j];
                    at[j] = temp;

                    temp = bt[i];
                    bt[i] = bt[j];
                    bt[j] = temp;

                    temp = priority[i];
                    priority[i] = priority[j];
                    priority[j] = temp;
                }
            }
        }

        int temp = 0;
        long sumWT = 0, sumTAT = 0;

        for (int i = 0; i < process; i++) 
        {
            ct[i] = Math.max(temp, at[i]) + bt[i];
            temp = ct[i];

            tat[i] = ct[i] - at[i];
            wt[i] = tat[i] - bt[i];

            sumWT += wt[i];
            sumTAT += tat[i];
        }

        float avgWT = (float) sumWT / process;
        float avgTAT = (float) sumTAT / process;

        System.out.println("PROCESS ID | ARRIVAL TIME | BRUST TIME | PRIORITY | FINISH TIME | TURNAROUND TIME | WAITING TIME ");
        for (int i = 0; i < process; i++) 
        {
            System.out.println(id[i] + " \t\t " + at[i] + " \t\t " + bt[i] + " \t " +
                    priority[i] + " \t\t " + ct[i] + " \t\t "+ tat[i] + " \t\t " + wt[i]);
        }
        System.out.println("Average Waiting Time = " + avgWT);
        System.out.println("Average Turnaround Time = " + avgTAT);       
    }

    static void executeRoundRobin() 
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("********Round Robin (RR)*********");
    
        System.out.print("Enter total no. of processes: ");
        int process = sc.nextInt();

        int[] id = new int[process];
        int[] at = new int[process];
        int[] bt = new int[process];
        int[] ct = new int[process];
        int[] tat = new int[process];
        int[] wt = new int[process];
        int[] rbt = new int[process];
        int[] key = new int[process];

        System.out.println("\nEnter Arrival Time and Burst Time for each process: ");
        for (int i = 0; i < process; i++) 
        {
            id[i] = i + 1;
            at[i] = sc.nextInt();
            bt[i] = sc.nextInt();
            rbt[i] = bt[i];
            key[i] = 0;
        }

        System.out.print("Enter Time quantum: ");
        int tq = sc.nextInt();

        enQ(0);
        key[0] = 1;
        gt=at[0];

        while (!isQEmpty()) 
        {
            int idx = deQ();
            if (rbt[idx] <= tq) 
            {
                gt += rbt[idx];
                rbt[idx] = 0;
                ct[idx] = gt;
            } 
            else 
            {
                gt += tq;
                rbt[idx] -= tq;
            }

            for (int i = 0; i < process; i++) 
            {
                if (rbt[i] > 0 && at[i] <= gt && key[i] == 0) 
                {
                    enQ(i);
                    key[i] = 1;
                }
            }
            if (rbt[idx] > 0) 
            {
                enQ(idx);
            }
        }

        float avgTAT = 0, avgWT = 0;
        for (int i = 0; i < process; i++)
        {
            tat[i] = ct[i] - at[i];
            wt[i] = tat[i] - bt[i];
            avgTAT += tat[i];
            avgWT += wt[i];
        }

        System.out.println("PROCESS | ARRIVAL TIME | BRUST TIME | FINISH TIME | TURNAROUND TIME | WAITING TIME ");
        for (int i = 0; i < process; i++)
        {
            System.out.println(id[i] + "\t\t" + at[i] + "\t\t" + bt[i] + "\t" + ct[i] + "\t\t" + tat[i] + "\t\t" + wt[i]);
        }

        System.out.printf("\nAverage Turn around time = %.2f", avgTAT / process);
        System.out.printf("\nAverage Waiting time = %.2f\n", avgWT / process);    
    }

    static int gt = 0;
    static LinkedList<Integer> queue = new LinkedList<>();

    static void enQ(int ele) 
    {
        queue.offer(ele);
    }

    static int deQ() 
    {
        return queue.poll();
    }

    static boolean isQEmpty() 
    {
        return queue.isEmpty();
    }
}
