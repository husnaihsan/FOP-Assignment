/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafx_1;

/**
 *
 * @author hp
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
 
 
public class TimeTaken_graph extends Application {
 static int i=0,j=0,k=0, 
         Dur_24=0, Dur_22 = 0, Dur_20 = 0, Dur_18 = 0, Dur_16 = 0, Dur_14 = 0,
                    Dur_12=0, Dur_10=0, Dur_8=0, Dur_6=0, Dur_4=0, Dur_2=0, Dur_0=0;
    @Override public void start(Stage stage) {
        stage.setTitle("Line Chart Sample");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Range Hour"); 
        yAxis.setLabel("Number of jobs");
        
        final LineChart<String,Number> lineChart = 
                new LineChart<String,Number>(xAxis,yAxis);
                
        lineChart.setTitle("Execution Time of jobs submitted to UMHPC");
                                
        XYChart.Series series = new XYChart.Series();
        series.setName("jobs submitted");
        
        series.getData().add(new XYChart.Data("0-2", Dur_0));
        series.getData().add(new XYChart.Data("2-4", Dur_2));
        series.getData().add(new XYChart.Data("4-6", Dur_4));
        series.getData().add(new XYChart.Data("6-8", Dur_6));
        series.getData().add(new XYChart.Data("8-10", Dur_8));
        series.getData().add(new XYChart.Data("10-12", Dur_10));
        series.getData().add(new XYChart.Data("12-14", Dur_12));
        series.getData().add(new XYChart.Data("14-16", Dur_14));
        series.getData().add(new XYChart.Data("16-18", Dur_16));
        series.getData().add(new XYChart.Data("18-20", Dur_18));
        series.getData().add(new XYChart.Data("20-22", Dur_20));
        series.getData().add(new XYChart.Data("22-24", Dur_22));
        series.getData().add(new XYChart.Data(">24", Dur_24));
        
        
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);
       
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        
                // TODO code application logic here
        String[] index=new String[50000], detail = new String[50000], b = new String[50000], month = new String[50000], mon = new String[50000];
        String[] date = new String[50000],start = new String[50000],end = new String[50000], JobID1 = new String[50000], JobID2 = new String[50000];
        double[] time = new double[50000];
        String[] total= new String[50000];
        try{
            
            Scanner input = new Scanner(new FileInputStream("C:\\Users\\hp\\Downloads\\extracted_log"));
            
            while(input.hasNextLine()){
                 String str = input.nextLine();
                index[i]= str;
                String[] temp = str.split(" ");
                detail[i] = temp[1];
                
                String string = temp[0];
   
                b = string.replaceAll("[\\[\\]]", "").split("T");
                date[i] = b[0]+" "+b[1];
                month = b[0].split("-");
                mon[i] = month[1];
                
                if(detail[i].equals("sched:") && temp[2].equals("Allocate")){  
                    start[j] = date[i];
                    String ID = temp[3].replaceAll("[^0-9]","");
                    JobID1[j] = ID;
                    j++;}
                
                if(detail[i].equals("_job_complete:")&&temp[3].equals("done")){
                    end[k] = date[i];
                    String ID2 = temp[2].replaceAll("[^0-9]","");
                    JobID2[k] = ID2;
                    k++;
                }
                
                    i++;
            }
            System.out.println("+--------+-------+-----------------------------+-----------------------------+----------------+-----------+");
            System.out.printf("| %6s | %-5s |    %-24s |    %-24s | %-15s| %-10s|\n", "No.", "JobID", "End", "Start", "Time Taken", "Time(ms)");
            System.out.println("+--------+-------+-----------------------------+-----------------------------+----------------+-----------+");
            {
                int count = 0;
                long sum=0;
                long[] a = new long[50000];
                for(int l=0; l<k; l++){
                for(int m=0; m<j; m++){
                        if(JobID2[l].equals(JobID1[m])){
                            int cnt = 0;
                            a[cnt] =AverageTime(end[l], start[m]);
                            total[cnt] = Convert(a[cnt]);
                            sum += a[cnt];
                            if(a[cnt]>=86400000)
                                Dur_24++;
                            else if(a[cnt]>=79200000)
                                Dur_22++;
                            else if(a[cnt]>=72000000)
                                Dur_20++;
                            else if(a[cnt]>=64800000)
                                Dur_18++;
                            else if(a[cnt]>=57600000)
                                Dur_16++;
                            else if(a[cnt]>=50400000)
                                Dur_14++;
                            else if(a[cnt]>=43200000)
                                Dur_12++;
                            else if(a[cnt]>=36000000)
                                Dur_10++;
                            else if(a[cnt]>=28800000)
                                Dur_8++;
                            else if(a[cnt]>=21600000)
                                Dur_6++;
                            else if(a[cnt]>=14400000)
                                Dur_4++;
                            else if(a[cnt]>=7200000)
                                Dur_2++;
                            else
                                Dur_0++;
                            
                            System.out.printf("|%6d. | %-5s |   %-25s |   %-25s | %-15s| %-10d|\n",count+1, JobID1[m], end[l], start[m], total[cnt], a[cnt]);
                            count++;
                            cnt++;
                            break;
                    }
                }
            }
                System.out.println("+--------+-------+-----------------------------+-----------------------------+----------------+-----------+");
                
                        long average = sum/(count+1);
                        
                        System.out.println("\n\n   Average Execution Time: ");
                        System.out.println("+-----------------+------------------------+");
                        System.out.println("|    Range Hour   |    No. of Jobs         |");
                        System.out.println("+-----------------+------------------------+");
                        System.out.printf("|       >24       |         %-10s     |\n",Dur_24);
                        System.out.printf("|      22-24      |         %-10s     |\n",Dur_22);
                        System.out.printf("|      20-22      |         %-10s     |\n",Dur_20);
                        System.out.printf("|      18-20      |         %-10s     |\n",Dur_18);
                        System.out.printf("|      16-18      |         %-10s     |\n",Dur_16);
                        System.out.printf("|      14-16      |         %-10s     |\n",Dur_14);
                        System.out.printf("|      12-14      |         %-10s     |\n",Dur_12);
                        System.out.printf("|      10-12      |         %-10s     |\n",Dur_10);
                        System.out.printf("|       8-10      |         %-10s     |\n",Dur_8);
                        System.out.printf("|       6-8       |         %-10s     |\n",Dur_6);
                        System.out.printf("|       4-6       |         %-10s     |\n",Dur_4);
                        System.out.printf("|       2-4       |         %-10s     |\n",Dur_2);
                        System.out.printf("|       0-2       |         %-10s     |\n",Dur_0);
                        System.out.println("+-----------------+------------------------+");
                        System.out.printf("|   Total Time    |                        |\n");
                        System.out.println("+-----------------+------------------------+");
                        System.out.printf("|       ms        |       %-10s     |\n",sum);
                        System.out.printf("| DD:HH:mm:ss.SSS |     %-10s    |\n",Convert(sum));
                        System.out.println("+-----------------+------------------------+");
                        System.out.printf("|     Average     |                        |\n");
                        System.out.println("+-----------------+------------------------+");
                        System.out.printf("|       ms        |        %-10s      |\n",sum/(count+1));
                        System.out.printf("|  HH:mm:ss.SSS   |       %-10s     |\n",Convert(average));
                        System.out.println("+-----------------+------------------------+");
                        System.out.printf("|       Max       |                        |\n");
                        System.out.println("+-----------------+------------------------+");
                        System.out.printf("|       ms        |        %-10s      |\n",Max(a));
                        System.out.printf("| DD:HH:mm:ss.SSS |      %-10s     |\n",Convert(Max(a)));
                        System.out.println("+-----------------+------------------------+");
                        System.out.println("");
                      
             
            }
            input.close();
        }
        
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        launch(args);
    }
    
   static long AverageTime(String end_date,String start_date){
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                  long difference_In_Time = 0;
                   try{
                   Date d1 = sdf.parse(start_date);
			Date d2 = sdf.parse(end_date);

			// Calculate time difference
			// in milliseconds
			difference_In_Time = d2.getTime() - d1.getTime();
                   }
                   catch (ParseException e) {
			e.printStackTrace();
		}
         return difference_In_Time;
   }
    static long Max(long[] total){
       long max = 0;
       for(int i = 0;i<total.length; i++){
       max = Math.max(max,total[i]);
       }
       return max;}
    
   static String Convert(long time){
       String result;
       long diffMiliSecond = time % 1000;
        long diffSeconds = (time / 1000) % 60;
        long diffMinutes = (time / (1000 * 60))% 60;
        long diffHours = (time	/ (1000 * 60 * 60)) % 24;
        long diffDays = (time / (1000 * 60 * 60 * 24)) % 365;

        // Print the date difference in
        // years, in days, in hours, in
        // minutes, and in seconds

        result = diffDays+ ":"+diffHours + ":" + diffMinutes
                + ":" + diffSeconds+ "." +diffMiliSecond;
        return result;
   }
   
        
} 
