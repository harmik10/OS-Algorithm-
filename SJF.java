import java.util.*;
public class SJF
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("********Shortest Job First (SJF)*********");
        System.out.println("Name : Harmik Sarvaliya");
        System.out.println("Er.no : 230317");

        System.out.print("Enter number of processes: ");
        int process = sc.nextInt();

        int id[] = new int[process];
        int at[] = new int[process];
        int bt[] = new int[process];
        int ct[] = new int[process];
        int tat[] = new int[process];
        int wt[] = new int[process];

        System.out.println("Enter the Arrival Time and Brust Time : ");
        for (int i = 0; i < process; i++) 
        {
            id[i] = i + 1; // Initialize id directly
           
            at[i] = sc.nextInt();
            bt[i] = sc.nextInt();
        }

        //core logic
        int[] processActive = new int[process]; // Using an array to track process status
        int[] idct = new int[process]; // Using an array to track completion time
        int executedCount = 0;
        int time = 0;
        while (executedCount < process){
            int minBurstIndex = -1;
            int minbt = Integer.MAX_VALUE;

            for (int i = 0; i < process; i++) {
                if (processActive[i] == 0 && at[i] <= time && bt[i] < minbt) {
                    minbt = bt[i];
                    minBurstIndex = i;
                }
            }
            if (minBurstIndex != -1){
                processActive[minBurstIndex] = 1;
                time += bt[minBurstIndex];
                idct[id[minBurstIndex] - 1] = time;
                executedCount++;
            } 
            else{
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
            //System.out.println(id[i] + "\t\t" + at[i] + "\t\t\t" + bt[i] + "\t\t\t" + ct[i] + "\t\t\t" + tat[i] + "\t\t\t" + wt[i]);
            System.out.println(id[i] + " \t\t " + at[i] + " \t\t " + bt[i]+" \t " + ct[i] + "  \t\t  " + tat[i]+"  \t\t  "+ wt[i]);
        }
        System.out.println("Average Waiting Time: " + avgWaitTime);
        System.out.println("Average Turnaround Time: " + avgtat);
    }
}
