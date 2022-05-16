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

/**
 * Message controller
 * 
 * @author Kalin Johnson
 * @author Nicholas Pederson
 * @version Spring 2022
 * 
 */

public class addMessagesPanel {
	private TextField newMessage;
	private Label displayMessageL;
	private Button addMessage;
	private Button deleteMessage;
	private GUI Gui;

	private Messages myMessages;

	/**
	 * A Contructor of Messages Panel and controller
	 * 
	 * 
	 * @param gui
	 * @param root
	 */
	public addMessagesPanel(GUI gui, BorderPane root) {
		this.Gui = gui;
		myMessages = new Messages();

		// Read in Messages if available

		try {
			// Creating an object of the file for reading the data
			File myMess = new File("D:FileHandlingNewFilef1Mess.txt");
			Scanner myReader = new Scanner(myMess);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				if (myMessages.size == 0) {
					myMessages.current.data = data;
					myMessages.current.prev = myMessages.current;
					myMessages.current.nxt = myMessages.current;
					if (myMessages.current.data != null) {
						myMessages.size++;
					}
				} else {
					addMessage(data, myMessages.current);
					myMessages.current = myMessages.current.nxt;
				}
			}
			myReader.close();
			myMess.delete();
		} catch (FileNotFoundException ex) {
			// System.out.println("An error occurred.");
			// ex.printStackTrace();
		}

		newMessage = new TextField();
		newMessage.setStyle("-fx-font: 16 arial;");
		displayMessageL = new Label(" ");
		displayMessageL.setStyle("-fx-font: 16 arial;");
		addMessage = new Button("+");
		addMessage.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (newMessage.getText() != "") {
					addMessage(newMessage.getText(), myMessages.current);
					clearM();
				}
			}
		});
		deleteMessage = new Button("-");
		deleteMessage.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (myMessages.size > 0) {
					deleteMessage();
				}
				System.out.println(myMessages.size);
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

	/**
	 * Add a message to myMessages
	 * 
	 * @param data
	 * @param currentNode
	 * @return nothing
	 */

	public void addMessage(String data, ListNode currentNode) {
		if (data.equals(null) || data.equals("")) {
			System.out.println("the input can't be empty or null");
		}
		if (myMessages.size == 0) {
			myMessages.current.data = data;
		} else if (myMessages.size == 1) {
			ListNode newNode = new ListNode(data, null, null);
			newNode.prev = myMessages.current;
			newNode.nxt = myMessages.current;
			myMessages.current.nxt = newNode;
			myMessages.current.prev = newNode;
		} else {
			ListNode newNode = new ListNode(data, null, null);
			myMessages.addItem(newNode);
		}
		myMessages.size++;
	}

	/**
	 * Delete a Message from myMessages
	 * 
	 * @param nothing
	 * @return nothing
	 */

	public void deleteMessage() {
		myMessages.deleteItem();
	}

	/**
	 * Display messages run every 3 seconds
	 * 
	 * @param nothing
	 * @return nothing
	 */

	public void displayMessage() {

		myMessages.current = myMessages.current.nxt;
		if (myMessages.size != 0) {
			displayMessageL.setText(myMessages.current.data);
		} else {
			displayMessageL.setText("");
		}
	}

	/**
	 * Clear text field
	 * 
	 * @param nothing
	 * @return nothing
	 */

	public void clearM() {
		newMessage.setText("");
	}

	/**
	 * Write the data out to a file
	 * 
	 * @param nothing
	 * @return nothing
	 */

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
			if (myMessages.current.data != null) {
				int j = myMessages.size;
				for (int i = 0; i < j; i++) {
					myWriter.write(myMessages.current.data + "\n");
					myMessages.current = myMessages.current.nxt;
				}
			}
			myWriter.close();
		} catch (IOException exp) {
			System.out.println("An error occurred.");
			exp.printStackTrace();
		}
	}
}

