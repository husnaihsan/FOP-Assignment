/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafx_1;

/**
 *
 * @author hp
 */
import javafx.beans.binding.Bindings;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.Group;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class JobPartition_chart extends Application {
     static String[] Partition = {"Partition=cpu-epyc","Partition=cpu-opteron","Partition=gpu-titan","Partition=gpu-k40c","Partition=gpu-v100s","Partition=gpu-k10"};
     static String[] user = new String[50000];
     static int[] sum = new int[6];
	// launch the application
	 @Override public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Number of Jobs by Partition");
        stage.setWidth(500);
        stage.setHeight(500);
 
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data(Partition[0]+(" ["+sum[0]+"]"), sum[0]),
                new PieChart.Data(Partition[1]+(" ["+sum[1]+"]"), sum[1]),
                new PieChart.Data(Partition[2]+(" ["+sum[2]+"]"), sum[2]),
                new PieChart.Data(Partition[3]+(" ["+sum[3]+"]"), sum[3]),
                new PieChart.Data(Partition[4]+(" ["+sum[4]+"]"), sum[4]),
                new PieChart.Data(Partition[5]+(" ["+sum[5]+"]"), sum[5]));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Number of Jobs by Partition");
        
        ((Group) scene.getRoot()).getChildren().add(chart);
        stage.setScene(scene);
        stage.show();
    }
	// Main Method
	


   public static void displayJobPartition() {
         
         for(int cnt = 0; cnt<Partition.length; cnt++){
        // TODO code application logic here
        try{
            Scanner input = new Scanner(new FileInputStream("C:\\Users\\hp\\Downloads\\extracted_log"));
            int[] num = new int[50000];
            int i=0, k=0;
                     String[] job = new String[50000], user = new String[50000];
                    while(input.hasNextLine()){
                 String str = input.nextLine();
                String[] temp = str.split(" ");
             num[i]= 1;
             
                if(temp[1].equals("sched:")&&temp[2].equals("Allocate")){
                    if(temp[6].equals(Partition[cnt])){
                        
                    String string = temp[4];
                    String[] b = string.split("=");
                    
                        {   user[i] = b[1];
                            job[i]= temp[4];
                            boolean NotSame = true;
                            for(int j=0; j<i; j++){
                                    if(b[1].equals(user[j])){
                                        num[j]++;
                                        NotSame = false;  
                                        break;}
                            }if(NotSame){
                                user[k] = user[i];
                                k++;
                                }
                        }
                    }
                }
                i++;
            }
            String[] temp = Partition[cnt].split("=");
            Partition[cnt] = temp[1];
             displayJob(k,user,num,Partition[cnt],cnt, sum);
            
            input.close();
            }
        
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }}
         launch();
         
          }
   
         static void displayJob(int k, String[] user, int[] num, String Partition, int i, int[] sum){
       
            System.out.println("\n");
            System.out.println("No. of jobs by partition: "+Partition);
            System.out.println("+----------------------+--------------+");
            System.out.println("|         Node         |  Num of Jobs |");
            System.out.println("+----------------------+--------------+");
            for(int j = 0; j<k;j++){
                    System.out.printf("| %-20s | %7d      |\n",user[j],num[j]);
                    sum[i]+=num[j];
                }
            System.out.println("+----------------------+--------------+");
            System.out.printf("|         Total        | %7d      |\n",sum[i]);
            System.out.println("+----------------------+--------------+");
     }
   
}

