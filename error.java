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
public class error {
     public static void displayError() {
        // TODO code application logic here
        System.out.println("Number of Errors:");
        try{
            String[] user = new String[50000];
            Scanner input = new Scanner(new FileInputStream("C:\\Users\\hp\\Downloads\\extracted_log"));
            int i=0, k=0, sum=0;
            int[] num = new int[50000];
            while(input.hasNextLine()){
                 String str = input.nextLine();
                String[] temp = str.split(" ");
             num[i]= 1;
                if(temp[1].equals("error:")){
                    if(temp[2].equals("This")){
                        
                    String string = temp[5];
                    String[] b = string.replaceAll("[\\'\\,]", "").split("=");
                    
                        {   user[i] = b[1];
                            boolean NotSame = true;
                            for(int j=0; j<i; j++){
                                    if(b[1].equals(user[j])){
                                        num[j]++;
                                        NotSame = false;
                                        break;}
                            }if(NotSame){
                                num[i]++;
                                user[k] = user[i];
                                k++;}
                        }
                    }}
                
                
                i++;
            }
            System.out.println("+-----+----------------------+--------------+");
            System.out.println("| No. |      Username        | Num of error |");
            System.out.println("+-----+----------------------+--------------+");
            for(int j = 0; j<k;j++){
                    System.out.printf("|  %2d.| %-20s | %7d      |\n",(j+1),user[j],num[j]);
                    sum+=num[j];
                }
            System.out.println("+-----+----------------------+--------------+");
            System.out.printf("|     |        Total         | %7d      |\n",sum);
            System.out.println("+-----+----------------------+--------------+");
            input.close();

        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
     }
}