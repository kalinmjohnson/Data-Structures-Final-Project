package finalproject;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class addMessagesPanel {
	private TextField newMessage;
	private Label displayMessage;
	private Button addMessage;
	private Button deleteMessage;
	// private Button stopRotation;
	private GUI Gui;
	private Messages myMessages = new Messages();

	private ListNode key;
	private boolean cont = true;
	Timer t = new Timer();

	public addMessagesPanel(GUI gui, BorderPane root) {
		this.Gui = gui;

		newMessage = new TextField();
		displayMessage = new Label();
		// displayMessage.setFont(Times New Roman);
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

		root.setLeft(displayMessage);
		root.setRight(newMessage);
		root.setBottom(button);
		button.setCenter(deleteMessage);
		button.setRight(addMessage);
		BorderPane.setAlignment(deleteMessage, Pos.BOTTOM_RIGHT);

	}

	public void addMessage(String data, ListNode currentNode) {
		cont = false;
		if (myMessages.size == 0) {
			// myMessages.head = new ListNode(data, myMessages.head, myMessages.head);
			myMessages = new Messages(data);
		} else {
			ListNode newNode = new ListNode(data, null, null);
			myMessages.addItem(newNode, currentNode);
		}
		myMessages.size++;
		clearM();
		displayMessage();
	}

	public void deleteMessage(ListNode currentNode) {
		cont = false;
		myMessages.deleteItem(currentNode);
		myMessages.size--;
		displayMessage();
	}

	public void displayMessage() {
		key = myMessages.head;
		cont = true;
		int i = 0;

		
		Timer timer = new Timer("Timer");

		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				if (key != key.nxt) {
					key = key.nxt;
					System.out.println(key.data);
					displayMessage.setText(key.data);
				}
			}
		}, 1000, 1000);

		/*
		 * key = key.nxt; System.out.println(key.data);
		 * displayMessage.setText(key.data);
		 */

	}

	public void clearM() {
		newMessage.setText("");
	}
}
