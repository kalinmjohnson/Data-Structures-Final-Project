package finalproject;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.util.Duration;

public class GameController {
	
	private Label HighScore;
	private Label CurrentScore;
	private Label Title;
	private Button startGame;
	private Button stopGame;
	private GUI Gui;

	public GameController(GUI gui,  BorderPane root, Game snakeGame) {
		this.Gui = gui;

		Title = new Label("Play Snake! \nPress Play at the Bottom of the Screen!\nPress W-A-S-D to Control Your Snake\nPress Q to Quit and Exit the Game");
		root.setBottom(Title);
		Title.setStyle("-fx-font: 16 arial;");
		
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
	}
	
	public void updateHighScore(Game snakeGame) {
		HighScore.setText("HighScore: " + snakeGame.highScore);
	}
}
