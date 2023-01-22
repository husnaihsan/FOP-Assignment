/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assigmentfop;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author hp
 */
public class JobCreatedEnded {
    public static void displayJobCreatedEnded() {
        int JuneStart= 0, JulyStart = 0, AugStart = 0, SeptStart = 0, OctStart = 0, NovStart = 0, DecStart = 0;
        int June= 0, July = 0, Aug = 0, Sept = 0, Oct = 0, Nov = 0, Dec = 0;
        int n =0, num=1, m=0;
        // TODO code application logic here
        try{
            String[] index=new String[50000], date = new String[50000], detail = new String[50000], det = new String[50000];
            Scanner input = new Scanner(new FileInputStream("C:\\Users\\hp\\Downloads\\extracted_log"));
            int i=0;
            while(input.hasNextLine()){
                 String str = input.nextLine();
                index[i]= str;
                String[] temp = str.split(" ");
                date[i] = temp[0];
                detail[i] = temp[1];
                
             
                if(detail[i].equals("sched:")){
                    det[i] = temp[2];
                    if(det[i].equals("Allocate")){
                    index[m] = str;
                    String[] b = temp[0].replaceAll("[\\[\\]]", "").split("-");
                    date[m] = b[1];
                    
                  m++;
                        }
                    }
                i++;
            }
            
          for(int j=0; j<m; j++){
                switch(date[j]){
                    case "06" -> JuneStart ++;
                    case "07" -> JulyStart++;
                    case "08" -> AugStart++;
                    case "09" -> SeptStart++;
                    case "10" -> OctStart++;
                    case "11" -> NovStart++;
                    case "12" -> DecStart++;
                }
            }
           
            input.close();

        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        System.out.println("\n");
        String[] index=new String[50000], date = new String[50000], detail = new String[50000], det = new String[50000];
        
        try{
            int i=0;
            Scanner input = new Scanner(new FileInputStream("C:\\Users\\hp\\Downloads\\extracted_log"));
            
            while(input.hasNextLine()){
                 String str = input.nextLine();
                //index[i]= str;
                String[] temp = str.split(" ");
                    detail[i] = temp[1];
             
                if(detail[i].equals("_job_complete:")){
                    det[n] = temp[3];
                    if(det[n].equals("done")){
                    index[n] = str;
                    String[] b = temp[0].replaceAll("[\\[\\]]", "").split("-");
                    date[n] = b[1];
                        //System.out.println(m+". "+index[m] + "  " + date[m]);
                    
                  n++;
                    }
                }
                i++;
            }
             for(int j=0; j<n; j++){
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
            System.out.println("    No. of jobs Created/Completed per month: ");
                System.out.println("+------------------+-----------------------+-----------------------+");
                System.out.println("|      Month       |      Jobs Created     |     Jobs Completed    |");
                System.out.println("+------------------+-----------------------+-----------------------+");
                System.out.printf("|       June       |         %-10s    |         %-10s    |\n",JuneStart,June);
                System.out.printf("|       July       |         %-10s    |         %-10s    |\n",JulyStart,July);
                System.out.printf("|      August      |         %-10s    |         %-10s    |\n",AugStart,Aug);
                System.out.printf("|     September    |         %-10s    |         %-10s    |\n",SeptStart,Sept);
                System.out.printf("|      October     |         %-10s    |         %-10s    |\n",OctStart,Oct);
                System.out.printf("|     November     |         %-10s    |         %-10s    |\n",NovStart,Nov);
                System.out.printf("|     December     |         %-10s    |         %-10s    |\n",DecStart,Dec);
                System.out.println("+------------------+-----------------------+-----------------------+");
                System.out.printf("|      Total       |         %-10s    |         %-10s    |\n",m,n);
                System.out.println("+------------------+-----------------------+-----------------------+");
                System.out.printf("|     Average      |         %-10s    |         %-10s    |\n",m/6,n/6);
                System.out.println("+------------------+-----------------------+-----------------------+");
            
            input.close();

        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }
}
