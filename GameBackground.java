import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//Nursena Kıyga 150122008
//Alper Kaan Arslan 150122059
//Sinem Yenibalcı 150122037

//Represents the background of the game scene, which consists of a grid of cells.
public class GameBackground extends Pane {

	public static double width;// This variable represents the width of the game background.
	public static double height;// This variable represents the height of the game background
	public static int numberOfGridCellsX;
	public static int numberOfGridCellsY;
	public static int pathCount;
	public static int succesfullyArrived;
	public static int crashedCars;

	// Constructor to initialize the background with specified dimensions and grid
	// parameters.
	public GameBackground(double width, double height, int numberOfGridCellsX, int numberOfGridCellsY, int pathCount,
			int succesfullyArrived, int crashedCars) {
		GameBackground.width = width;
		GameBackground.height = height;
		GameBackground.numberOfGridCellsX = numberOfGridCellsX;
		GameBackground.numberOfGridCellsY = numberOfGridCellsY;
		GameBackground.pathCount = pathCount;
		GameBackground.succesfullyArrived = succesfullyArrived;
		GameBackground.crashedCars = crashedCars;

		// Creating grid cells and adding them to the background.
		for (int row = 0; row < numberOfGridCellsY; row++) {
			for (int col = 0; col < numberOfGridCellsX; col++) {
				Rectangle cell = new Rectangle(width / numberOfGridCellsX, height / numberOfGridCellsY);
				cell.setFill(new Color(0.33, 0.67, 1.0, 0.5));
				cell.setLayoutX(col * (width / numberOfGridCellsX));
				cell.setLayoutY(row * (height / numberOfGridCellsY));
				getChildren().add(cell);
			}
		}
	}
}
