import java.util.*;

public class PriorityScheduling 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("********Priority Schedualing (Non-preemption)*********");
        System.out.println("Name : Harmik Sarvaliya");
        System.out.println("Er.no : 230317");

        System.out.print("Enter total number of processes : ");
        int process = sc.nextInt();

        int[] id = new int[process];
        int[] at = new int[process];
        int[] bt = new int[process];
        int[] ct = new int[process];
        int[] tat = new int[process];
        int[] wt = new int[process];
        int[] priority = new int[process];

        System.out.println("Enter Process-id, Arrival-Time, Burst-Time, and Priority for each process(Lowest Valye High Priority):");
        for (int i = 0; i < process; i++) 
        {
            id[i] = sc.nextInt();
            at[i] = sc.nextInt();
            bt[i] = sc.nextInt();
            priority[i] = sc.nextInt();
        }

        // Sorting processes
        for (int i = 0; i < process - 1; i++) 
        {
            for (int j = i + 1; j < process; j++)
            {
                // First sort according to arrival time
                if (at[i] > at[j] || (at[i] == at[j] && priority[i] > priority[j])
                        || (at[i] == at[j] && priority[i] == priority[j] && id[i] > id[j])) {
                    // Swapping variables
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

        // Calculating completion time, turnaround time, and waiting time
        int temp=0;//ct[0] = bt[0] + at[0]; 
        long sumWT = 0, sumTAT = 0;

        for (int i = 0; i < process; i++) {
            ct[i] = Math.max(temp, at[i]) + bt[i];  // Calculate completion time based on the maximum of temp and arrival time
            temp = ct[i];
        
            tat[i] = ct[i] - at[i];
            wt[i] = tat[i] - bt[i];
        
            sumWT += wt[i];
            sumTAT += tat[i];
        }
        float avgWT = (float) sumWT / process;
        float avgTAT = (float) sumTAT / process;

        // Displaying process details and average times
        System.out.println("PROCESS ID | ARRIVAL TIME | BRUST TIME | PRIORITY | FINISH TIME | TURNAROUND TIME | WAITING TIME ");
        for (int i = 0; i < process; i++) {
            System.out.println(id[i] + " \t\t " + at[i] + " \t\t " + bt[i] + " \t " +
                    priority[i] + " \t\t " + ct[i] + " \t\t "+ tat[i] + " \t\t " + wt[i]);
        }
        System.out.println("Average Waiting Time = " + avgWT);
        System.out.println("Average Turnaround Time = " + avgTAT);       
    }
    
}
