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

public class addPriorityTaskPanel {
	private TextField newTaskTF;
	private TextField inputPriority;
	private Label topTaskTF;
	private Label topPriorityTF;
	private Button addPTaskB;
	private Button deleteTaskB;
	
	private GUI Gui;
	
	public addPriorityTaskPanel (GUI gui, BorderPane root) {
		this.Gui = gui;
		
		topTaskTF = new Label("Top Priority: " + "\n\nPriority Number:" + "\n");
		
		newTaskTF = new TextField();
		inputPriority = new TextField();
		inputPriority.setPrefColumnCount(3);
		
		addPTaskB = new Button("+");
		addPTaskB.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
			}
		});
		deleteTaskB = new Button("-");
		deleteTaskB.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
			}
		});
		
		BorderPane display = new BorderPane();
		
		root.setTop(display);
		display.setCenter(topTaskTF);
		display.setRight(deleteTaskB);
		root.setLeft(newTaskTF);
		root.setCenter(inputPriority);
		root.setRight(addPTaskB);
		
		BorderPane.setAlignment(deleteTaskB, Pos.TOP_RIGHT);
		BorderPane.setAlignment(topTaskTF, Pos.TOP_LEFT);
		
		topTaskTF.setStyle("-fx-font: 16 arial;");
		
		root.setPadding(new Insets(10, 10, 10, 10));
		display.setPadding(new Insets(10, 0, 10, 0));
		BorderPane.setMargin(inputPriority, new Insets(0, 30, 0, 30));
		
        
        public void addTask(String data) {
    	
         }

          public void deleteTask() {
    	
          }
		
       	}
}
