import java.util.*;

public class FCFS
{
    public static void main(String[] args) 
    {
        System.out.println("Name : Harmik Sarvaliya");
        System.out.println("Er.no : 230317");
        calculateFCFS();
    
    }
    static void calculateFCFS()
    {
        Scanner sc =new Scanner(System.in);
        float avgTAT=0,avgWT=0, sumT=0,sumW=0;
        
        System.out.println("********First Come First Serve(FCFS)*********");
        int process;
              
        System.out.print("Enter the No.of the Process : ");
        process=sc.nextInt();

        float at[] =new float[process];
        float bt[] =new float[process];
        float ct[]=new float[process];
        float tat[]=new float[process];
        float wt[]=new float[process];

        System.out.println("Enter the Arrival Time and Brust Time : ");
        for(int i=0;i<process;i++)
        {
            at[i]=sc.nextFloat();
            bt[i]=sc.nextFloat();   
        }
        System.out.println("ARRIVAL TIME | BRUST TIME | FINISH TIME | TURNAROUND TIME | WAITING TIME "); //at the end
        
        float temp=0;
        for(int i=0;i<process;i++)
        {
            //Finish Time 
            ct[i]=temp + bt[i];  
            temp=ct[i];
            //For TAT and WT 
            tat[i]=ct[i]-at[i];
            wt[i]=tat[i]-bt[i];
            //Addition of all Arraye elements 
            sumT += tat[i];
            sumW += wt[i];           
        }
        for(int i=0;i<process;i++)
        { 
            System.out.println("       " + at[i] + "       " + bt[i]+"          " + ct[i] + "             " + tat[i]+"             "+ wt[i]);
        }
        
        avgTAT= sumT/process;
        avgWT =sumW/process;
        System.out.println("Average Turnaround Time : " + avgTAT);     
        System.out.println("Average Waiting Time : " + avgWT); 
    }
}