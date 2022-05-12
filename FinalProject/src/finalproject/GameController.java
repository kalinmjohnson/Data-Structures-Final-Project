package finalproject;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameController {
	
	private Label HighScore;
	private Label CurrentScore;
	private Label Title;
	private Button startGame;
	private Button stopGame;
	private GUI Gui;
	private GUI gui;
	private Timeline timeline = new Timeline();
	private ObservableList<Node> snake;
	public static final int BLOCK_SIZE = 20;
	public static final int APP_W = 40 * BLOCK_SIZE;
	public static final int APP_H = 40 * BLOCK_SIZE;
	protected boolean isMoved = false;
	protected boolean isRunning = false;

	private Stage PStage;

	public GameController(GUI gui,  BorderPane root, Stage primaryStage, Scene scene) {
		this.Gui = gui;
		
		
		Title = new Label("Play Snake! \nPress Start at the Bottom of the Screen!\nUse W-A-S-D to Control Your Snake\nPress Q to Quit and Exit the Game");
		root.setCenter(Title);
		Title.setStyle("-fx-font: 16 arial;");
		BorderPane.setAlignment(Title, Pos.BOTTOM_LEFT);
		
		HighScore = new Label("HighScore: " + snakeGame.highScore);
		root.setTop(HighScore);
		HighScore.setStyle("-fx-font: 16 arial;");
		
		// Image credit to https://w7.pngwing.com/pngs/866/136/png-transparent-snakes-and-ladders-game-android-animated-snake-s-text-video-game-grass.png
		

		// BackGround Image
		Image img = new Image("https://w7.pngwing.com/pngs/866/136/png-transparent-snakes-and-ladders-game-android-animated-snake-s-text-video-game-grass.png");
		BackgroundImage bImag = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		Background bGround = new Background(bImag);
		root.setBackground(bGround);
		
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
				updateHighScore(snakeGame);
				break;
			}

		});

		startGame = new Button("START");
		startGame.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.setScene(sceneGame);
				startGame();
			}
		});

		root.setBottom(startGame);
		BorderPane.setAlignment(startGame, Pos.BOTTOM_CENTER);
		BorderPane.setMargin(startGame, new Insets(10, 10, 10, 10));
	}
	
	public void updateHighScore(Game snakeGame) {
		HighScore.setText("HighScore: " + snakeGame.highScore);
	}
	

	protected Game snakeGame = new Game(0, 1, Direction.RIGHT);

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
				snakeGame.size++;
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
}
