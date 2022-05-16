package finalproject;

/**
 * Stack of Tasks Controller.
 * 
 * @author Nicholas
 * @author Kalin
 * @version 5/16/2022
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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

public class addStackOfTasksPanel {
	private TextField enterTaskTF;

	private Label[] tasks = new Label[8];

	private Button addSTaskB;
	private Button deleteSTaskB;
	private Label title;

	// Create a new Stack of tasks to hold all of the back log tasks.
	private BacklogModel backLogTasks = new BacklogModel();
	private ListNode key;

	/**
	 * Constructer for the Stack of Tasks Panel
	 * @param gui
	 * @param root
	 */
	public addStackOfTasksPanel(GUI gui, BorderPane root) {

		enterTaskTF = new TextField();
		title = new Label("Backlog of Tasks\n ");
		title.setStyle("-fx-font: 16 arial;");

		for (int i = 0; i < tasks.length; i++) {
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

		for (int i = 0; i < tasks.length; i++) {
			listTasks.add(tasks[i], 0, i);
		}

		BorderPane.setAlignment(deleteSTaskB, Pos.TOP_RIGHT);
		BorderPane.setAlignment(enterTaskTF, Pos.TOP_LEFT);

		listTasks.setPadding(new Insets(10, 10, 10, 10));
		addTasks.setPadding(new Insets(10, 0, 10, 0));
		BorderPane.setMargin(enterTaskTF, new Insets(0, 10, 0, 0));

		try {
			// Creating an object of the file for reading the data
			File myBack = new File("FileHandlingNewFilef1B.txt");
			Scanner myReader = new Scanner(myBack);
			// key = backLogTasks.head.nxt;
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				System.out.println(data);
				addTask(data);
				// System.out.println(data);
			}
			myReader.close();
		} catch (FileNotFoundException ex) {
			// System.out.println("An error occurred.");
			// ex.printStackTrace();
		}
	}

	/**
	 * Add task to the stack and display it.
	 * @param data - the new task to add to the stack
	 */
	public void addTask(String data) {
		if (data == null || data.equals("")) {
			System.out.println("Data can't be empty or null");
		} else {
			backLogTasks.push(data);
			displayIt();
			clearST();
		}
	}
	
	/**
	 * Delete the top task.
	 */
	public void deleteTask() {
		backLogTasks.pop();
		displayIt();
	}

	/**
	 * Display the 8 top tasks.
	 */
	public void displayIt() {
		for (int i = 0; i < tasks.length; i++) {
			String setIt = i < backLogTasks.size ? backLogTasks.peek(i) : "";
			tasks[i].setText(setIt);
		}
	}

	/**
	 * Clears the entering Text Field
	 */
	public void clearST() {
		enterTaskTF.setText("");
	}

	/**
	 * Write out the stuff to a file.
	 */
	public void writeB() {
		try {
			// Creating an object of a file
			File myBack = new File("FileHandlingNewFilef1B.txt");
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

			FileWriter myWriter = new FileWriter("FileHandlingNewFilef1B.txt");
			// Writes this content into the specified file
			key = backLogTasks.tail;
			if (backLogTasks.size != 0) {
				key = key.prev;
				while (key.prev != null) {
					myWriter.write(key.data + "\n");
					key = key.prev;
				}

			}
			myWriter.close();
		} catch (IOException exp) {
			System.out.println("An error occurred.");
			exp.printStackTrace();
		}
	}

}
