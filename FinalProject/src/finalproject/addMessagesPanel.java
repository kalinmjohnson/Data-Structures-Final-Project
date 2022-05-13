package finalproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class addMessagesPanel implements Runnable {
	private TextField newMessage;
	private Label displayMessageL;
	private Button addMessage;
	private Button deleteMessage;
	private GUI Gui;

	private Messages myMessages = new Messages();
	private ListNode key = myMessages.head;

	public addMessagesPanel(GUI gui, BorderPane root) {
		this.Gui = gui;

		// Read in Messages if available

		try {
			// Creating an object of the file for reading the data
			File myMess = new File("D:FileHandlingNewFilef1Mess.txt");
			Scanner myReader = new Scanner(myMess);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				if (myMessages.size == 0) {
					myMessages.head.data = data;
					myMessages.head.prev = myMessages.head;
					myMessages.head.nxt = myMessages.head;
				} else {
					addMessage(data, key);
				}
			}
			myReader.close();
		} catch (FileNotFoundException ex) {
			//System.out.println("An error occurred.");
			//ex.printStackTrace();
		}

		newMessage = new TextField();
		newMessage.setStyle("-fx-font: 16 arial;");
		displayMessageL = new Label(" ");
		displayMessageL.setStyle("-fx-font: 16 arial;");
		addMessage = new Button("+");
		addMessage.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				addMessage(newMessage.getText(), key);
				clearM();
			}
		});
		deleteMessage = new Button("-");
		deleteMessage.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				deleteMessage(key);
			}
		});

		BorderPane button = new BorderPane();

		root.setLeft(displayMessageL);
		root.setRight(newMessage);
		root.setBottom(button);
		button.setCenter(deleteMessage);
		button.setRight(addMessage);
		BorderPane.setAlignment(deleteMessage, Pos.BOTTOM_RIGHT);

		Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				displayMessage();
			}
		}));
		fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
		fiveSecondsWonder.play();
	}

	public void run() {
		displayMessage();
	}

	public void addMessage(String data, ListNode currentNode) {
		if (data.equals(null) || data.equals("")) {
			System.out.println("the input can't be empty or null");
		}
		if (myMessages.size == 0) {
			// myMessages.head = new ListNode(data, myMessages.head, myMessages.head);
			myMessages = new Messages(data);
		} else {
			ListNode newNode = new ListNode(data, null, null);
			myMessages.addItem(newNode, currentNode);
		}
	}

	public void deleteMessage(ListNode currentNode) {
		myMessages.deleteItem(currentNode);
	}

	public void displayMessage() {
		if (myMessages.size > 1) {
			key = key.nxt;
			displayMessageL.setText(key.data);
		} else {
			key = myMessages.head;
			displayMessageL.setText(key.data);
		}
	}

	public void clearM() {
		newMessage.setText("");
	}

	public void writeM() {
		try {
			// Creating an object of a file
			File myMess = new File("D:FileHandlingNewFilef1Mess.txt");
			if (myMess.createNewFile()) {
				System.out.println("File created: " + myMess.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		try {

			FileWriter myWriter = new FileWriter("D:FileHandlingNewFilef1Mess.txt");
			// Writes this content into the specified file
			if (myMessages.head.data != null) {
				myWriter.write(myMessages.head.data + "\n");
				key = myMessages.head.nxt;
				int j = myMessages.size;
				for (int i = 0; i <= j; i++) {
					myWriter.write(key.data + "\n");
				}
			}
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException exp) {
			System.out.println("An error occurred.");
			exp.printStackTrace();
		}
	}
}
