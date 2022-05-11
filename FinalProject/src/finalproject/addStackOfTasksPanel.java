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
	private Label task1;
	private Label task2;
	private Label task3;
	private Label task4;
	private Label task5;
	private Label task6;
	private Label task7;
	private Label task8;
	private Button addSTaskB;
	private Button deleteSTaskB;
	private Label title;
	
	public addStackOfTasksPanel (GUI gui, BorderPane root) {
		
		enterTaskTF = new TextField();
		title = new Label("Backlog of Tasks\n ");
		title.setStyle("-fx-font: 16 arial;");
		
		task1 = new Label();
		task2 = new Label();
		task3 = new Label();
		task4 = new Label();
		task5 = new Label();
		task6 = new Label();
		task7 = new Label();
		task8 = new Label();
		task1.setStyle("-fx-font: 16 arial;");
		task2.setStyle("-fx-font: 16 arial;");
		task3.setStyle("-fx-font: 16 arial;");
		task4.setStyle("-fx-font: 16 arial;");
		task5.setStyle("-fx-font: 16 arial;");
		task6.setStyle("-fx-font: 16 arial;");
		task7.setStyle("-fx-font: 16 arial;");
		task8.setStyle("-fx-font: 16 arial;");
		
		addSTaskB = new Button("+");
		addSTaskB.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
			}
		});
		
		deleteSTaskB = new Button("-");
		deleteSTaskB.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
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
		listTasks.add(task1, 0, 0);
		listTasks.add(task2, 1, 0);
		listTasks.add(task3, 2, 0);
		listTasks.add(task4, 3, 0);
		listTasks.add(task5, 4, 0);
		listTasks.add(task6, 5, 0);
		listTasks.add(task7, 6, 0);
		listTasks.add(task8, 7, 0);
		
		BorderPane.setAlignment(deleteSTaskB, Pos.TOP_RIGHT);
		BorderPane.setAlignment(enterTaskTF, Pos.TOP_LEFT);
		
		listTasks.setPadding(new Insets(10, 10, 10, 10));
		addTasks.setPadding(new Insets(10, 0, 10, 0));
		BorderPane.setMargin(enterTaskTF, new Insets(0, 10, 0, 0));
		
		public void addTask(String data) {
	    	
	    }

	    public void deleteTask() {
	    	
	    }
		
	}
}