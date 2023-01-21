/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assigmentfop;
/**
 *
 * @author hp
 */
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 *
 * @author Aireen
 */
public class JobCancelled {
    public static void displayJobCancelled() {
        //extra features: kill_job requests
        
        String[] index=new String[50000];
        String[] date = new String[50000];
        String[] detail = new String[50000];
        String[] det = new String[50000];
        int i=0, m =0;
        try{
            Scanner input = new Scanner(new FileInputStream("C:\\Users\\hp\\Downloads\\extracted_log"));
            
            while(input.hasNextLine()){
                String str = input.nextLine();
                //index[i]= str;
                String[] temp = str.split(" ");
                detail[i] = temp[1];
             
                if(detail[i].equals("_slurm_rpc_kill_job:")){
                    det[m] = temp[2];
                    if(det[m].equals("REQUEST_KILL_JOB")){
                    index[m] = str;
                    String[] b = temp[0].replaceAll("[\\[\\]]", "").split("-");
                    date[m] = b[1];
                    m++;
                    }
                }
                i++;
            }
            
            int num =1, June=0, July=0, Aug=0, Sept=0, Oct=0, Nov=0, Dec=0;
            
            for(int j=0; j<m; j++){
                switch(date[j]){
                    case "06" -> June ++;
                    case "07" -> July++;
                    case "08" -> Aug++;
                    case "09" -> Sept++;
                    case "10" -> Oct++;
                    case "11" -> Nov++;
                    case "12" -> Dec++;
                }
            }
            
            System.out.println("\n\n    No. of jobs Cancelled per month: ");
                System.out.println("+------------------+-----------------------+");
                System.out.println("|      Month       |      No. of Jobs      |");
                System.out.println("+------------------+-----------------------+");
                System.out.printf("|       June       |         %-10s    |\n",June);
                System.out.printf("|       July       |         %-10s    |\n",July);
                System.out.printf("|      August      |         %-10s    |\n",Aug);
                System.out.printf("|     September    |         %-10s    |\n",Sept);
                System.out.printf("|      October     |         %-10s    |\n",Oct);
                System.out.printf("|     November     |         %-10s    |\n",Nov);
                System.out.printf("|     December     |         %-10s    |\n",Dec);
                System.out.println("+------------------+-----------------------+");
                System.out.printf("|      Total       |         %-10s    |\n",m);
                System.out.println("+------------------+-----------------------+");
                System.out.printf("|     Average      |         %-10s    |\n",m/6);
                System.out.println("+------------------+-----------------------+");
            
            input.close();
            
        } catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
    }
}