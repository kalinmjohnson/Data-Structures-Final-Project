package finalproject;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

public class GUI extends Application {

	// Set up Attributes for GUI

	private GUI gui;
	private Timeline timeline = new Timeline();
	private ObservableList<Node> snake;
	public static final int BLOCK_SIZE = 20;
	public static final int APP_W = 40 * BLOCK_SIZE;
	public static final int APP_H = 40 * BLOCK_SIZE;
	protected boolean isMoved = false;
	protected boolean isRunning = false;

	private Label HighScore;
	private Label CurrentScore;
	private Button startGame;
	private Button stopGame;
	private GUI Gui;
	private Stage PStage;
	
	protected Game snakeGame = new Game(0, 0, Direction.RIGHT);

	
	
	
	
	
	public Parent createContent() {

		this.Gui = gui;
		Pane root = new Pane();

		root.setPrefSize(APP_W, APP_H);

		Group snakeBody = new Group();
		snake = snakeBody.getChildren();

		startGame = new Button("START");
		startGame.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				startGame();
			}
		});

		stopGame = new Button("STOP");
		stopGame.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				stopGame();
			}
		});

		HighScore = new Label("HighScore: " + snakeGame.highScore);
		HighScore.setTextFill(Color.color(1, 1, 1));
		CurrentScore = new Label("Current Score: " + snakeGame.size);
		CurrentScore.setTextFill(Color.color(1, 1, 1));

		/*
		 * root.add(startGame, 0, 0); root.add(stopGame, 1, 0); root.add(HighScore, 2,
		 * 0); root.add(CurrentScore, 3, 0);
		 */

		// BackGround Image
		Image img = new Image("https://www.lawnstarter.com/blog/wp-content/uploads/2017/08/bermuda.jpg");
		BackgroundImage bImag = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
		Background bGround = new Background(bImag);
		root.setBackground(bGround);

		// Food
		Rectangle food = new Rectangle(BLOCK_SIZE, BLOCK_SIZE);
		Image imga = new Image("https://i.pinimg.com/originals/7f/f1/86/7ff186ab829539d7d2ca0eb06537b952.png");
		food.setFill(new ImagePattern(imga));
		food.setTranslateX((int) (Math.random() * (APP_W - BLOCK_SIZE)) / BLOCK_SIZE * BLOCK_SIZE);
		food.setTranslateY((int) (Math.random() * (APP_H - BLOCK_SIZE)) / BLOCK_SIZE * BLOCK_SIZE);

		KeyFrame frame = new KeyFrame(Duration.seconds(0.1), e -> {
			if (!isRunning)
				return;
			boolean toRemove = snake.size() > 1;

			Node tail = toRemove ? snake.remove(snake.size() - 1) : snake.get(0);

			double tailX = tail.getTranslateX();
			double tailY = tail.getTranslateY();

			// Move snake
			switch (snakeGame.direction) {
			case UP:
				tail.setTranslateX(snake.get(0).getTranslateX());
				tail.setTranslateY(snake.get(0).getTranslateY() - BLOCK_SIZE);
				break;
			case DOWN:
				tail.setTranslateX(snake.get(0).getTranslateX());
				tail.setTranslateY(snake.get(0).getTranslateY() + BLOCK_SIZE);
				break;
			case LEFT:
				tail.setTranslateX(snake.get(0).getTranslateX() - BLOCK_SIZE);
				tail.setTranslateY(snake.get(0).getTranslateY());
				break;
			case RIGHT:
				tail.setTranslateX(snake.get(0).getTranslateX() + BLOCK_SIZE);
				tail.setTranslateY(snake.get(0).getTranslateY());
				break;
			}

			isMoved = true;
			if (toRemove)
				snake.add(0, tail);

			// Collision detection (NB: the Tail it's the Head below -_^)
			for (Node node : snake) {
				if (node != tail && node.getTranslateX() == tail.getTranslateX()
						&& node.getTranslateY() == tail.getTranslateY())
					restartGame();
			}

			// Check if snake is over the region of game
			if (tail.getTranslateX() < 0 || tail.getTranslateX() > APP_W || tail.getTranslateY() < 0
					|| tail.getTranslateY() > APP_H)
				restartGame();

			// Eat food ?
			if (tail.getTranslateX() == food.getTranslateX() && tail.getTranslateY() == food.getTranslateY()) {
				food.setTranslateX((int) (Math.random() * (APP_W - BLOCK_SIZE)) / BLOCK_SIZE * BLOCK_SIZE);
				food.setTranslateY((int) (Math.random() * (APP_H - BLOCK_SIZE)) / BLOCK_SIZE * BLOCK_SIZE);

				Rectangle newNode = new Rectangle(BLOCK_SIZE, BLOCK_SIZE);
				newNode.setTranslateX(tailX);
				newNode.setTranslateY(tailY);

				snake.add(newNode);
			}

		});

		timeline.getKeyFrames().add(frame);
		timeline.setCycleCount(Timeline.INDEFINITE);

		root.getChildren().addAll(food, snakeBody);
		return root;
	}

	public void restartGame() {
		stopGame();
		startGame();
	}

	public void startGame() {
		snakeGame.direction = Direction.RIGHT;
		Rectangle head = new Rectangle(BLOCK_SIZE, BLOCK_SIZE);
		snake.add(head);
		timeline.play();
		isRunning = true;
	}

	public void stopGame() {
		isRunning = false;
		timeline.stop();
		if (snakeGame.size > snakeGame.highScore) {
			snakeGame.highScore = snakeGame.size;
		}
		snakeGame.size = 0;
		snake.clear();
	}

	public void directionUP() {
		if (snakeGame.direction != Direction.DOWN)
			snakeGame.direction = Direction.UP;
	}

	public void directionDOWN() {
		if (snakeGame.direction != Direction.UP)
			snakeGame.direction = Direction.DOWN;
	}

	public void directionRIGHT() {
		if (snakeGame.direction != Direction.RIGHT)
			snakeGame.direction = Direction.LEFT;
	}

	public void directionLEFT() {
		if (snakeGame.direction != Direction.LEFT)
			snakeGame.direction = Direction.RIGHT;
	}

	/*
	 * public class addPriorityTaskPanel(GUI gui) { this.gui = gui; }
	 * 
	 * public class addStackOfTaskPanel(GUI gui) { this.gui = gui; }
	 */

	// Main running functions

	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage primaryStage) {
		try {

			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 800, 800);
			primaryStage.setScene(scene);

			ColumnConstraints col1 = new ColumnConstraints();
			col1.setPercentWidth(25);
			ColumnConstraints col2 = new ColumnConstraints();
			col2.setPercentWidth(25);
			ColumnConstraints col3 = new ColumnConstraints();
			col3.setPercentWidth(25);
			ColumnConstraints col4 = new ColumnConstraints();
			col4.setPercentWidth(25);

			//root.getColumnConstraints().addAll(col1, col2, col3, col4);

			RowConstraints row1 = new RowConstraints();
			row1.setPercentHeight(25);
			RowConstraints row2 = new RowConstraints();
			row2.setPercentHeight(25);
			RowConstraints row3 = new RowConstraints();
			row3.setPercentHeight(25);
			RowConstraints row4 = new RowConstraints();
			row4.setPercentHeight(25);

			//root.getRowConstraints().addAll(row1, row2, row3, row4);

			root.setMinSize(10, 10);
			root.setPadding(new Insets(10, 10, 10, 10));
			//root.setVgap(5);
			//root.setHgap(5);

			primaryStage.setTitle("Our Command Center");

			// Messages Stuff

			BorderPane addMPane = new BorderPane();
			root.setTop(addMPane);
			BorderPane.setAlignment(addMPane, Pos.TOP_CENTER);
			//root.add(addMPane, 0, 0);
			/*GridPane.setColumnIndex(addMPane, 0);
			GridPane.setColumnSpan(addMPane, 4);
			GridPane.setRowIndex(addMPane, 0);
			GridPane.setRowSpan(addMPane, 1);*/
			// addMPane.setPrefSize(1000, 200);

			addMPane.setMinSize(100, 50);
			addMPane.setPadding(new Insets(10, 10, 10, 10));

			addMessagesPanel aMessagePanel = new addMessagesPanel(this, addMPane);

			// Game Stuff on primary stage

			BorderPane addGame = new BorderPane();
			root.setRight(addGame);
			addGame.setPrefSize(400,  400);
			BorderPane.setAlignment(addGame, Pos.TOP_CENTER);
			//root.add(addGame, 2, 2);
			//addGame.setPrefSize(1000, 200);

			addGame.setMinSize(100, 50);
			addGame.setPadding(new Insets(10, 10, 10, 10));
			/*GridPane.setColumnIndex(addMPane, 2);
			GridPane.setColumnSpan(addMPane, 2);
			GridPane.setRowIndex(addMPane, 2);
			GridPane.setRowSpan(addMPane, 2);*/

			GameController aGameController = new GameController(this, addGame, snakeGame);
			
			Scene sceneGame;
			sceneGame = new Scene(createContent());
			sceneGame.setOnKeyPressed(e -> {

				switch (e.getCode()) {
				case W:
					if (snakeGame.direction != Direction.DOWN)
						snakeGame.direction = Direction.UP;
					break;
				case S:
					if (snakeGame.direction != Direction.UP)
						snakeGame.direction = Direction.DOWN;
					break;
				case A:
					if (snakeGame.direction != Direction.RIGHT)
						snakeGame.direction = Direction.LEFT;
					break;
				case D:
					if (snakeGame.direction != Direction.LEFT)
						snakeGame.direction = Direction.RIGHT;
					break;
				case Q:
					stopGame();
					primaryStage.setScene(scene);
					aGameController.updateHighScore(snakeGame);
					break;
				}

			});

			startGame = new Button("START");
			//startGame.setOnAction(e -> primaryStage.setScene(sceneGame));
			startGame.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					primaryStage.setScene(sceneGame);
					startGame();
				}
			});
			
			root.setBottom(startGame);
			BorderPane.setAlignment(startGame, Pos.BOTTOM_CENTER);

			primaryStage.setScene(scene);

			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
