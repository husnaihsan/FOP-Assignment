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
import java.util.Scanner;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
 
public class JobCancelled_chart extends Application {
    static String[] index=new String[50000], date = new String[50000], detail = new String[50000], det = new String[50000];
    static int i=0, m =0;
    static int June= 0, July = 0, Aug = 0, Sept = 0, Oct = 0, Nov = 0, Dec = 0;
 
@Override public void start(Stage stage) {
        stage.setTitle("Line Chart Sample");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Month"); 
        yAxis.setLabel("Number of jobs");
        
        final LineChart<String,Number> lineChart = 
                new LineChart<String,Number>(xAxis,yAxis);
        
        lineChart.setTitle("Number of Job Cancelled");
                                
        XYChart.Series series = new XYChart.Series();
        series.setName("Jobs Cancelled");
        
        series.getData().add(new XYChart.Data("June", June));
        series.getData().add(new XYChart.Data("July", July));
        series.getData().add(new XYChart.Data("August", Aug));
        series.getData().add(new XYChart.Data("September", Sept));
        series.getData().add(new XYChart.Data("October", Oct));
        series.getData().add(new XYChart.Data("November", Nov));
        series.getData().add(new XYChart.Data("December", Dec));      
        
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);
       
        stage.setScene(scene);
        stage.show();
        
    }
     public static void displayJobCancelled() {
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
        launch();
    }
}
