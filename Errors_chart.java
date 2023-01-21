/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafx_1;

/**
 *
 * @author hp
 */
// Java program to create a pie chart
// with some specified data
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.collections.FXCollections;

public class Errors_chart extends Application {

     static String[] user = new String[50000];
     static int[] num = new int[50000];
	// launch the application
	public void start(Stage stage)
	{
		// set title for the stage
		stage.setTitle("Number of errors by users");

		// piechart data
		PieChart.Data data[] = new PieChart.Data[26];

		// string and integer data
		
		for (int i = 0; i < 26; i++) {
			data[i] = new PieChart.Data(user[i], num[i]);
		}

		// create a pie chart
		PieChart pie_chart = new
				PieChart(FXCollections.observableArrayList(data));

		// create a Group
		Group group = new Group(pie_chart);

		// create a scene
		Scene scene = new Scene(group, 500, 300);

		// set the scene
		stage.setScene(scene);

		stage.show();
	}


	// Main Method
	
    public static void main(String args[]) {
        System.out.println("Number of Errors:\n");
        try{
            
            Scanner input = new Scanner(new FileInputStream("C:\\Users\\hp\\Downloads\\extracted_log"));
            int i=0, k=0, sum=0;
            
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
         
        
        launch(args);
    }
}