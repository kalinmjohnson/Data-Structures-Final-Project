package finalproject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

/**
 * Driver Class called GUI that constructs and runs the interface
 * 
 * @author Kalin Johnson
 * @author Nicholas Pederson
 * @version Spring 2022
 * 
 */

public class GUI extends Application {
	
	/**
	 * Start Function
	 * @param primary Stage
	 * @return doesn't return anything
	 * 
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage primaryStage) {
		try {

			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 800, 800);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Our Command Center");

			// Messages Stuff

			BorderPane addMPane = new BorderPane();
			root.setTop(addMPane);
			BorderPane.setAlignment(addMPane, Pos.TOP_CENTER);

			addMPane.setMinSize(100, 50);
			addMPane.setPadding(new Insets(10, 10, 10, 10));

			addMessagesPanel aMessagePanel = new addMessagesPanel(this, addMPane);

			// Priority Task Stuff and Stack of Tasks BorderPane

			BorderPane addPTSTPane = new BorderPane();
			root.setLeft(addPTSTPane);
			BorderPane.setAlignment(addPTSTPane, Pos.BOTTOM_CENTER);

			// Priority Task Stuff

			BorderPane addPTPane = new BorderPane();
			addPTSTPane.setTop(addPTPane);
			BorderPane.setAlignment(addPTPane, Pos.TOP_CENTER);

			addMPane.setMinSize(100, 50);
			addMPane.setPadding(new Insets(10, 10, 10, 10));

			addPriorityTaskPanel aPTPanel = new addPriorityTaskPanel(this, addPTPane);

			// Stack of Tasks or Backlog of Tasks Stuff

			
			  BorderPane addSTPane = new BorderPane(); addPTSTPane.setCenter(addSTPane);
			  BorderPane.setAlignment(addSTPane, Pos.BOTTOM_CENTER);
			  
			  addSTPane.setMinSize(100, 50); addSTPane.setPadding(new Insets(30, 10, 10,
			  10));
			  
			  addStackOfTasksPanel aSTPanel = new addStackOfTasksPanel(this, addSTPane);
			 

			// Game Stuff on primary stage

			BorderPane addGame = new BorderPane();
			root.setRight(addGame);
			addGame.setPrefSize(400, 400);
			BorderPane.setAlignment(addGame, Pos.TOP_CENTER);

			addGame.setMinSize(100, 50);
			addGame.setPadding(new Insets(10, 10, 10, 10));

			GameController aGameController = new GameController(this, addGame, primaryStage, scene);

			// Game Stuff on SceneGame

			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent e) {
					System.out.println("exiting...");
					aMessagePanel.writeM();
					aGameController.writeG();
					aSTPanel.writeB();
					aPTPanel.writeP();
				}
			});

			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Main Function
	 * @param not a variable
	 * @return doesn't return anything
	 * 
	 */

	public static void main(String[] args) {
		launch(args);
	}
}

