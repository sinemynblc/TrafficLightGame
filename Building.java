import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import javafx.scene.layout.Pane;

//Nursena Kıyga 150122008
//Alper Kaan Arslan 150122059
//Sinem Yenibalcı 150122037

//This class creates buildings and adds on the map.
public class Building extends Pane {

	public Building(int type, double rotation, int color, int gridCellX, int gridCellY) {

		// Calculates the real coordinates based on grid cells.
		double realX = gridCellX * (GameBackground.width / GameBackground.numberOfGridCellsX);
		double realY = gridCellY * (GameBackground.height / GameBackground.numberOfGridCellsY);

		// Defines colors for building types.
		Color[] trueColor = new Color[4];
		trueColor[0] = Color.ORANGE;
		trueColor[1] = Color.LIGHTGREEN;
		trueColor[2] = Color.MEDIUMPURPLE;
		trueColor[3] = Color.RED;

		// Creates groups for building types 0,1 and 2.
		// Creates rectangles and circles for building structures.Then styles them.

		if (type == 0) {

			Group g1 = new Group();
			Rectangle r1 = new Rectangle(realX, realY, (GameBackground.width / GameBackground.numberOfGridCellsX) * 2,
					(GameBackground.height / GameBackground.numberOfGridCellsY) * 3);

			r1.setStroke(Color.BLACK);
			r1.setFill(Color.WHITE);
			r1.setArcWidth(10);
			r1.setArcHeight(10);

			Rectangle r2 = new Rectangle(realX + 8, realY + 8,
					(GameBackground.width / GameBackground.numberOfGridCellsX) * 2 - 16.66,
					(GameBackground.height / GameBackground.numberOfGridCellsY) * 2 - 16.66);

			r2.setFill(trueColor[color]);
			r2.setStrokeWidth(3);
			setStroke(color, r2);
			r2.setArcWidth(10);
			r2.setArcHeight(10);

			Rectangle r3 = new Rectangle(realX + 18, realY + 18,
					(GameBackground.width / GameBackground.numberOfGridCellsX) * 2 - 36.6,
					(GameBackground.height / GameBackground.numberOfGridCellsY) * 2 - 36.66);

			r3.setFill(trueColor[color]);
			r3.setStrokeWidth(3);
			setStroke(color, r3);
			r3.setArcWidth(10);
			r3.setArcHeight(10);

			// Adds rectangles to the group.
			g1.getChildren().addAll(r1, r2, r3);
			getChildren().add(g1);

			// Adjusts layout and rotation based on building orientation.
			if (rotation == 90 || rotation == 270) {
				g1.setLayoutX((GameBackground.width / GameBackground.numberOfGridCellsX) * 0.5);
				g1.setLayoutY(-(GameBackground.width / GameBackground.numberOfGridCellsX) * 0.5);
			}
			g1.setRotate(-rotation);

		}

		else if (type == 1) {
			Group g2 = new Group();

			Rectangle r1 = new Rectangle(realX, realY, (GameBackground.width / GameBackground.numberOfGridCellsX) * 2,
					(GameBackground.height / GameBackground.numberOfGridCellsY) * 3);

			r1.setStroke(Color.BLACK);
			r1.setFill(Color.WHITE);
			r1.setArcWidth(10);
			r1.setArcHeight(10);

			Circle c1 = new Circle(realX + 53, realY + 53,
					2 * (GameBackground.height / GameBackground.numberOfGridCellsY) - 58.66);
			c1.setFill(trueColor[color]);
			c1.setStrokeWidth(3);
			setStroke(color, c1);

			Circle c2 = new Circle(realX + 53, realY + 53,
					2 * (GameBackground.height / GameBackground.numberOfGridCellsY) - 66.66);
			c2.setFill(trueColor[color]);
			c2.setStrokeWidth(3);
			setStroke(color, c2);

			// Adds rectangles and circles to the group
			g2.getChildren().addAll(r1, c1, c2);
			getChildren().add(g2);

			// Adjusts layout and rotation based on building orientation
			if (rotation == 90 || rotation == 270) {
				g2.setTranslateX((GameBackground.height / GameBackground.numberOfGridCellsY) * 0.5);
				g2.setTranslateY(-(GameBackground.width / GameBackground.numberOfGridCellsY) * 0.5);
			}
			g2.setRotate(-rotation);

		} else if (type == 2) {
			Rectangle r1 = new Rectangle(realX, realY, GameBackground.width / GameBackground.numberOfGridCellsX,
					GameBackground.width / GameBackground.numberOfGridCellsY);

			setStroke(color, r1);
			r1.setFill(trueColor[color]);
			r1.setArcWidth(10);
			r1.setArcHeight(10);

			// Adds rectangle to the pane
			getChildren().add(r1);
			r1.setRotate(rotation);

		}
	}

	// Uses this method to set the stroke color for a circle based on the provided
	// color index
	private void setStroke(int color, Circle c) {
		if (color == 0)
			c.setStroke(Color.DARKORANGE);
		else if (color == 1)
			c.setStroke(Color.GREEN);
		else if (color == 2)
			c.setStroke(Color.PURPLE);
		else if (color == 3)
			c.setStroke(Color.DARKRED);
	}

	// Uses this method to set the stroke color for a rectangle based on the
	// provided color index.
	private void setStroke(int color, Rectangle r) {
		if (color == 0)
			r.setStroke(Color.DARKORANGE);
		else if (color == 1)
			r.setStroke(Color.GREEN);
		else if (color == 2)
			r.setStroke(Color.PURPLE);
		else if (color == 3)
			r.setStroke(Color.DARKRED);
	}

}
