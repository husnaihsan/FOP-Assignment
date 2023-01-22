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
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
 
public class ErrorType_chart extends Application {
        static String search1 = "error: This association";
        static String search2 = "Job epilog failed";
        static String search3 = "lookup failure";
        static String search4 = "invalid node specified";
        static String search5 = "not responding";
        static String search6 = "Socket timed out";
        static String search7 = "error Invalid job id specified";
        static String search8 = "Security violation";
        static int count1=0 , count2=0 , count3=0 , count4=0 , count5=0 , count6=0 , count7=0 , count8=0;
 
    @Override public void start(Stage stage) {
        stage.setTitle("Error Type");
        final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis yAxis = new CategoryAxis();
        final BarChart<Number,String> bc = 
            new BarChart<Number,String>(xAxis,yAxis);
        bc.setTitle("Error Type Summary");
        xAxis.setLabel("Number of Errors");  
        xAxis.setTickLabelRotation(90);
        yAxis.setLabel("Types");        
 
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Error Types");       
        series1.getData().add(new XYChart.Data(count1, search1));
        series1.getData().add(new XYChart.Data(count2,search2));
        series1.getData().add(new XYChart.Data(count3, search3));
        series1.getData().add(new XYChart.Data(count4, search4));
        series1.getData().add(new XYChart.Data(count5, search5));
        series1.getData().add(new XYChart.Data(count6, search6));
        series1.getData().add(new XYChart.Data(count7, search7));
        series1.getData().add(new XYChart.Data(count8, search8));
        
        
        Scene scene  = new Scene(bc,800,600);
        bc.getData().addAll(series1);
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        
        System.out.println("Other errors and their categories"); //extra features: error types
        
        
        try{
            Scanner input = new Scanner(new FileInputStream("C:\\Users\\hp\\Downloads\\extracted_log"));
            
            while(input.hasNextLine()){
                String str = input.nextLine();
                //index[i]= str;
                
                
                if(str.contains(search1))
                    count1++;
                else if(str.contains(search2))
                    count2++;
                else if(str.contains(search3))
                    count3++;
                else if(str.contains(search4))
                    count4++;
                else if(str.contains(search5))
                    count5++;
                else if(str.contains(search6))
                    count6++;
                else if(str.contains(search7))
                    count7++;
                else if(str.contains(search8))
                    count8++;
                
            }
            
            int total=count1+count2+count3+count4+count5+count6+count7+count8;
            
            System.out.println("\nClassification of Errors by respective types: ");
                System.out.println("+------------------------------+-----------------------+");
                System.out.println("|            Types             |      No. of Errors    |");
                System.out.println("+------------------------------+-----------------------+");
                System.out.printf("|          Association         |         %-10s    |\n",count1);
                System.out.printf("|           Job epilog         |         %-10s    |\n",count2);
                System.out.printf("|        Lookup failure        |         %-10s    |\n",count3);
                System.out.printf("|         Invalid node         |         %-10s    |\n",count4);
                System.out.printf("|        Not responding        |         %-10s    |\n",count5);
                System.out.printf("|        Socket timed out      |         %-10s    |\n",count6);
                System.out.printf("|   Invalid job ID specified   |         %-10s    |\n",count7);
                System.out.printf("|      Security violation      |         %-10s    |\n",count8);
                System.out.println("+------------------------------+-----------------------+");
                System.out.printf("|           Total              |         %-10s    |\n",total);
                System.out.println("+------------------------------+-----------------------+");
                System.out.printf("|           Average            |         %-10s    |\n",total/8);
                System.out.println("+------------------------------+-----------------------+");
            
            input.close();
            
        } catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
        
        launch(args);
    }
}