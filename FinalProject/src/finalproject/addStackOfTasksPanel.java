package finalproject;

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

public class addStackOfTasksPanel {
	private TextField enterTaskTF;
	
	private Label[] tasks = new Label[8];
	
	private Button addSTaskB;
	private Button deleteSTaskB;
	private Label title;
	
	// Create a new Stack of tasks to hold all of the back log tasks.
	private BacklogModel backLogTasks = new BacklogModel();
	
	public addStackOfTasksPanel (GUI gui, BorderPane root) {
		
		enterTaskTF = new TextField();
		title = new Label("Backlog of Tasks\n ");
		title.setStyle("-fx-font: 16 arial;");
		
		for ( int i = 0; i < tasks.length; i++) {
			tasks[i] = new Label();
			tasks[i].setStyle("-fx-font: 16 arial;");
		}
		
		addSTaskB = new Button("+");
		addSTaskB.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				addTask(enterTaskTF.getText());
			}
		});
		
		deleteSTaskB = new Button("-");
		deleteSTaskB.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				deleteTask();
			}
		});
		
		
		BorderPane addTasks = new BorderPane();
		GridPane listTasks = new GridPane();
		
		root.setTop(addTasks);
		root.setCenter(listTasks);
		root.setRight(deleteSTaskB);
		addTasks.setCenter(enterTaskTF);
		addTasks.setRight(addSTaskB);
		addTasks.setTop(title);
		
		for ( int i = 0; i < tasks.length; i++) {
			listTasks.add( tasks[i], 0, i);
		}
		
		BorderPane.setAlignment(deleteSTaskB, Pos.TOP_RIGHT);
		BorderPane.setAlignment(enterTaskTF, Pos.TOP_LEFT);
		
		listTasks.setPadding(new Insets(10, 10, 10, 10));
		addTasks.setPadding(new Insets(10, 0, 10, 0));
		BorderPane.setMargin(enterTaskTF, new Insets(0, 10, 0, 0));
	}
	public void addTask(String data) {
	   	backLogTasks.push(data);
	   	displayIt();
	   	clearST();
	}

	public void deleteTask() {
    		backLogTasks.pop();
	    	displayIt();
	}
	
	public void displayIt() {
    		for (int i = 0; i < tasks.length; i++) {
    			String setIt = i < backLogTasks.size? backLogTasks.peek(i): "";
    			tasks[i].setText(setIt);
    		}
    	}
    
    	public void clearST() {
    		enterTaskTF.setText("");
	}
}
