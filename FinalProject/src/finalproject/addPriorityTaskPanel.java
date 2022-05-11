package finalproject;

import java.util.PriorityQueue;

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
	private Label topPriorityTF;
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
				addTask( newTaskTF.getText(), Integer.valueOf( inputPriority.getText()));
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
	}	
   
	public void addTask(String data, int pri) {
		PriorityTask newTask = new PriorityTask( data, pri);
		priorityTasks.add(newTask);
		displayTask();
	}

    public void deleteTask() {
		priorityTasks.poll(); // deletes the top task we could keep track of how many tasks were done
		displayTask();
    }
	
    public void displayTask() {
    	topTaskL.setText( "Top Priority: " + priorityTasks.peek().rdata + 
				"\n\nPriority Number:" + priorityTasks.peek().priority + "\n");
    }
}