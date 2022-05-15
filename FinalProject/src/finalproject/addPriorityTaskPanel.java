package finalproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class addPriorityTaskPanel {
	private TextField newTaskTF;
	private TextField inputPriority;
	private Label topTaskL;
	
	private Button addPTaskB;
	private Button deleteTaskB;
	
	private GUI Gui;
	// Create a new Min Heap of the priority Tasks (// model thingy)
	private PriorityQueue<PriorityTask> priorityTasks = new PriorityQueue<PriorityTask>( 100);
	
	public addPriorityTaskPanel (GUI gui, BorderPane root) {
		this.Gui = gui;	
		
		topTaskL = new Label("Top Priority: " + "\n\nPriority Number:" + "\n");
		
		newTaskTF = new TextField();
		inputPriority = new TextField();
		inputPriority.setPrefColumnCount(3);
		
		addPTaskB = new Button("+");
		addPTaskB.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				addTask( newTaskTF.getText(), Integer.parseInt( inputPriority.getText()));
			}
		});
		deleteTaskB = new Button("-");
		deleteTaskB.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				deleteTask();
			}
		});
		
		BorderPane display = new BorderPane();
		
		root.setTop(display);
		display.setCenter(topTaskL);
		display.setRight(deleteTaskB);
		root.setLeft(newTaskTF);
		root.setCenter(inputPriority);
		root.setRight(addPTaskB);
		
		BorderPane.setAlignment(deleteTaskB, Pos.TOP_RIGHT);
		BorderPane.setAlignment(topTaskL, Pos.TOP_LEFT);
		
		topTaskL.setStyle("-fx-font: 16 arial;");
		
		root.setPadding(new Insets(10, 10, 10, 10));
		display.setPadding(new Insets(10, 0, 10, 0));
		BorderPane.setMargin(inputPriority, new Insets(0, 30, 0, 30));
		
		try {
			// Creating an object of the file for reading the data
			File myPri = new File("D:FileHandlingNewFilef1Prior.txt");
			Scanner myReader = new Scanner(myPri);
			//key = backLogTasks.head.nxt;
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String number = myReader.nextLine();
				int num = Integer.parseInt(number);
				System.out.println(data);
				addTask(data, num);
				//System.out.println(data);
			}
			myReader.close();
		} catch (FileNotFoundException ex) {
			//System.out.println("An error occurred.");
			//ex.printStackTrace();
		}
	}

	public void addTask(String data, int pri) throws IllegalArgumentException{
		if ( pri < 0) { throw new IllegalArgumentException("Priority can't be negative."); }
		//if ( pri < 48 || pri > 57) { throw new IllegalArgumentException("Priority can't be anything except an int."); } //What happening here?
		PriorityTask newTask = new PriorityTask( data, pri);
		priorityTasks.add(newTask);
		displayTask();
	}
	
	public void deleteTask() {
		priorityTasks.poll(); // deletes the top task we could keep track of how many tasks were done
		displayTask();
	}
	
    public void displayTask() {
    	String newTaskText = priorityTasks.isEmpty()?
    		("Top Priority: " + "\n\nPriority Number:" + "\n"):
    			("Top Priority: " + priorityTasks.peek().rdata + 
				"\n\nPriority Number:" + priorityTasks.peek().priority + "\n");
    	topTaskL.setText( newTaskText);
    }
    
    public void writeP() {
		try {
			// Creating an object of a file
			File myBack = new File("D:FileHandlingNewFilef1Back.txt");
			if (myBack.createNewFile()) {
				System.out.println("File created: " + myBack.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		try {

			FileWriter myWriter = new FileWriter("D:FileHandlingNewFilef1Back.txt");
			
			while (priorityTasks.peek() != null) {
				PriorityTask sender = priorityTasks.poll();
				myWriter.write(sender.rdata + "\n" + sender.priority + "\n");
			}
			// Writes this content into the specified file
				//myWriter.write();
				
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException exp) {
			System.out.println("An error occurred.");
			exp.printStackTrace();
		}
	}
}
