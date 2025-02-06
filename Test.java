import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.FileNotFoundException;

//Nursena Kıyga 150122008
//Alper Kaan Arslan 150122059
//Sinem Yenibalcı 150122037

//
public class Test extends Application {

	private static Test instance;
	private static Stage primaryStage;
	private Level1 level1;
	private Level2 level2;
	private Level3 level3;
	private Level4 level4;
	private Level5 level5;

	@Override
	public void start(Stage primaryStage) {

		primaryStage.setTitle("Traffic Game");
		Test.primaryStage = primaryStage;

		// Loads the background image.
		Image backgroundImage = new Image("TermProject.jpg");
		ImageView backgroundImageView = new ImageView(backgroundImage);

		// Creates buttons for starting the game and importing levels.
		Button startGameButton = new Button("Import Level");
		startGameButton.setStyle("-fx-background-color:#191970");
		startGameButton.setTextFill(Color.WHITE);
		startGameButton.setFont(Font.font("Tahoma", 16));
		startGameButton.setOnAction(event -> showLevelButtons(primaryStage, backgroundImageView));

		Button loadGameButton = new Button("Start Game");
		loadGameButton.setStyle("-fx-background-color:#191970");
		loadGameButton.setTextFill(Color.WHITE);
		loadGameButton.setFont(Font.font("Tahoma", 16));
		loadGameButton.setOnAction(event -> loadLevelContent("Level 1"));

		HBox buttonBox = new HBox(100);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.getChildren().addAll(startGameButton, loadGameButton);

		// Creates a stack pane to holds the background image and buttons.
		StackPane uiPane = new StackPane();
		uiPane.getChildren().addAll(backgroundImageView, buttonBox);

		// Creates a scene with the stack pane.
		Scene uiScene = new Scene(uiPane, 800, 800);
		primaryStage.setScene(uiScene);
		primaryStage.show();
	}

	// Shows buttons for selecting levels.
	private void showLevelButtons(Stage primaryStage, ImageView backgroundImageView) {
		Button level1Button = new Button("Level 1");
		level1Button.setStyle(
				"-fx-background-color: INDIANRED;-fx-border-color:DARKSLATEGRAY ; -fx-border-width: 3px;-fx-border-radius: 0.5px;");
		level1Button.setTextFill(Color.WHITE);
		level1Button.setFont(Font.font("Verdana", 16));
		level1Button.setOnAction(event -> loadLevelContent("Level 1"));

		Button level2Button = new Button("Level 2");
		level2Button.setStyle(
				"-fx-background-color: INDIANRED;-fx-border-color:DARKSLATEGRAY ; -fx-border-width: 3px;-fx-border-radius: 0.5px;");
		level2Button.setTextFill(Color.WHITE);
		level2Button.setFont(Font.font("Verdana", 16));
		level2Button.setOnAction(event -> loadLevelContent("Level 2"));

		Button level3Button = new Button("Level 3");
		level3Button.setStyle(
				"-fx-background-color: INDIANRED;-fx-border-color:DARKSLATEGRAY ; -fx-border-width: 3px;-fx-border-radius: 0.5px;");
		level3Button.setTextFill(Color.WHITE);
		level3Button.setFont(Font.font("Verdana", 16));
		level3Button.setOnAction(event -> loadLevelContent("Level 3"));

		Button level4Button = new Button("Level 4");
		level4Button.setStyle(
				"-fx-background-color: INDIANRED;-fx-border-color:DARKSLATEGRAY ; -fx-border-width: 3px;-fx-border-radius: 0.5px;");
		level4Button.setTextFill(Color.WHITE);
		level4Button.setFont(Font.font("Verdana", 16));
		level4Button.setOnAction(event -> loadLevelContent("Level 4"));

		Button level5Button = new Button("Level 5");
		level5Button.setStyle(
				"-fx-background-color: INDIANRED;-fx-border-color:DARKSLATEGRAY ; -fx-border-width: 3px;-fx-border-radius: 0.5px;");
		level5Button.setTextFill(Color.WHITE);
		level5Button.setFont(Font.font("Verdana", 16));
		level5Button.setOnAction(event -> loadLevelContent("Level 5"));

		VBox buttonBox = new VBox(10);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.getChildren().addAll(level1Button, level2Button, level3Button, level4Button, level5Button);
		StackPane uiPane = new StackPane();
		uiPane.getChildren().addAll(backgroundImageView, buttonBox);
		Scene scene = new Scene(uiPane, 800, 800);
		primaryStage.setScene(scene);
	}

	public void loadLevelContent(String selectedLevel) {
		// Checks selected level and load corresponding content.
		try {
			switch (selectedLevel) {
			case "Level 1":
				if (level1 == null) {
					level1 = new Level1();
				}
				primaryStage.getScene().setRoot(level1);
				break;

			case "Level 2":
				if (level2 == null) {
					level2 = new Level2();
				}
				primaryStage.getScene().setRoot(level2);
				break;

			case "Level 3":
				if (level3 == null) {
					level3 = new Level3();
				}
				primaryStage.getScene().setRoot(level3);
				break;

			case "Level 4":
				if (level4 == null) {
					level4 = new Level4();
				}
				primaryStage.getScene().setRoot(level4);
				break;

			case "Level 5":
				if (level5 == null) {
					level5 = new Level5();
				}
				primaryStage.getScene().setRoot(level5);
				break;
			}
		} catch (FileNotFoundException e) {
		}
	}

	// Gets the instance of the Test class.
	public static Test getInstance() {
		if (instance == null) {
			instance = new Test();
		}
		return instance;
	}

	public static void main(String[] args) {
		launch(args);
	}
}

//Represents a screen prompting the player to try again after failing a level.
class TryAgainScreen {

	private CarSpawner carSpawner;
	private Stage stage;

	// Constructor to initialize the TryAgainScreen with a CarSpawner.
	public TryAgainScreen(CarSpawner carSpawner) {
		this.carSpawner = carSpawner;
		this.stage = new Stage();
	}

	public void show() {
		VBox layout = new VBox(10);
		layout.setAlignment(Pos.CENTER);
		Button tryAgainButton = new Button("Try Again");

		tryAgainButton.setOnAction(event -> {
			stage.close();
			carSpawner.restartGame();
		});

		layout.getChildren().addAll(tryAgainButton);
		// Creates a scene with the layout.
		Scene scene = new Scene(layout, 300, 200);

		// Sets the scene for the stage and shows the stage.
		stage.setScene(scene);
		stage.setTitle("Try Again");
		stage.show();
	}

}

//Represents a screen displayed when the player wins a level, allowing them to proceed to the next level or exit the game.
class Win {

	private Stage stage;
	private static int currentLevel=1;
	private Test testInstance = Test.getInstance();

	public Win(CarSpawner carSpawner) {
		this.stage = new Stage();
	}

	public void show() {

		VBox layout = new VBox(10);
		layout.setAlignment(Pos.CENTER);
		Button nextLevelButton = new Button("Next Level");

		// Sets action for the next level button.
		nextLevelButton.setOnAction(event -> {
			stage.close();
			loadNextLevel();
		});

		layout.getChildren().addAll(nextLevelButton);
		Scene scene = new Scene(layout, 300, 200);
		stage.setScene(scene);
		stage.setTitle("Traffic Game");
		stage.show();
	}

	private void loadNextLevel() {

		currentLevel++;

		// Checks if all levels completed.
		if (currentLevel == 6) {

			// If all levels completed, shows congratulations message and exits.
			showCongratsMessageAndExit();
		}
		// Loads the next level based on current level.
		switch (currentLevel) {

		case 2:
			testInstance.loadLevelContent("Level 2");
			break;

		case 3:
			testInstance.loadLevelContent("Level 3");
			break;

		case 4:
			testInstance.loadLevelContent("Level 4");
			break;

		case 5:
			testInstance.loadLevelContent("Level 5");
			break;
		}
	}

	// Shows congratulations message and exits.
	private void showCongratsMessageAndExit() {

		Alert alert = new Alert(Alert.AlertType.INFORMATION);

		alert.setTitle("Congratulations!");
		alert.setHeaderText("You have successfully completed Level 5!");
		alert.setContentText("Well done!");
		alert.showAndWait();

		Platform.exit();
	}
}
