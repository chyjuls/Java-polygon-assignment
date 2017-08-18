/*
 Polygon.java
 Project name: 	Assignment 3
 Programmer:    Pawel Misiak
 Instructor:    SWEETY VARGHESE
 Class:         Java Application Development
 Date:          03/25/2017
 
 Software Development Method
 
 1) Problem  -   Write a Java Swing program that displays a regular polygon and uses two buttons named +1 and -1 to increase or decrease the number of sides of the polygon being displayed, as shown in the following figure. Set the minimum number of sides to 3.

 2) The layout - Program displays 3 sided polygon in the middle of the window with two buttons "+1" and "-1" on the bottom of the window. In case of resizing of the window, the objects will hold their positions.
 
 3) User input - Whenever user presses the "+1" button, the polygon will receive additional side and create a new shape. The number of sides that can be added to the polygon is unlimited. 
	Second button "-1" allows user to take one of the sides out and recreate previously displayed polygon. NOTE: When the polygon has only three (3) sides left and it forms a triangle, the button "-1" will not make any changes.
 */

//used libraries
import javafx.application.Application;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;

public class ThePolygon extends Application 
{
	Stage window;
	Button button, button2;
	int i = 3;
	Polygon polygon = new Polygon();
	
	public static void main(String[] args) 
	{
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception 
	{	
		//Create the title of the window
		window = primaryStage;
		window.setTitle("The Polygon");
		
		//Create layout for buttons
		HBox hBox = new HBox();
		hBox.setSpacing(10);
		hBox.setAlignment(Pos.CENTER);
		
		//Create layout for polygon
		VBox vBox = new VBox();
		vBox.setSpacing(10);
		vBox.setAlignment(Pos.CENTER);
		
		//Create buttons and polygon instances
		button = new Button("+1");
		button2 = new Button("-1");
		polygon = anotherOne(i);
		
		//add objects to their layouts
		hBox.getChildren().add(button);
		hBox.getChildren().add(button2);
		vBox.getChildren().add(polygon);

		//Create a layout for the window and set everything in place
		BorderPane layout = new BorderPane();
		layout.setCenter(vBox);
		layout.setBottom(hBox);		
		
		//Assign the function to "+1" button
		button.setOnAction( e -> 
		{ 
			if (i >= 3)
			{	
					vBox.getChildren().remove(polygon);
					i+=1;
					polygon = anotherOne(i);
					vBox.getChildren().add(polygon);
			}		
		});
		
		//Assign the function to "-1" button
		button2.setOnAction( e -> 
		{ 
			if (i > 3)
			{
					vBox.getChildren().remove(polygon);
					i-=1;
					polygon = anotherOne(i);
					vBox.getChildren().add(polygon);
			}	
		});
		
		//Create the scene and add prepared layout
		Scene scene = new Scene(layout, 400, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	//The method to calculate the shape of the polygon of N sides
	public static Polygon anotherOne(int numberOfSides)
	{
		int i = 0;
		Polygon polygon = new Polygon(); // Creating new instance of the object
		
				for(i = 0; i < numberOfSides; i++)
				{
					int x = 400/ 2; //drawing the polygon is based on a circle
					int y = 400/ 2;
					int radius = (int)(Math.min(400, 400) * 0.4);
					double angle = 2 * Math.PI / numberOfSides;
					double m = (x + radius * Math.cos(i * angle));
					double n = ( y - radius * Math.sin(i * angle));
					polygon.getPoints().addAll(new Double[]{m,n}); // getting points for the new object
				}	//setting stroke and fill to the desired colors
				polygon.setStroke(Color.BLACK);	
				polygon.setFill(Color.WHITE);
				return polygon; //return newly created polygon of N sides
	}
}

