import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

//Nursena Kıyga 150122008
//Alper Kaan Arslan 150122059
//Sinem Yenibalcı 150122037

//Represents a road tile in the game environment. This class is responsible for creating different types of road tiles based on the given parameters.
public class RoadTile extends Pane {

	public RoadTile(int type, double rotation, int gridCellX, int gridCellY) {

		// Calculates the real coordinates based on grid cell positions.
		double realX = gridCellX * (GameBackground.width / GameBackground.numberOfGridCellsX);
		double realY = gridCellY * (GameBackground.height / GameBackground.numberOfGridCellsY);

		switch (type) {
		case 0:

			// Creates a group to hold the rectangle and center line.
			Group group = new Group();
			Rectangle rectangle = new Rectangle(realX, realY + 5,
					GameBackground.width / GameBackground.numberOfGridCellsX,
					GameBackground.height / GameBackground.numberOfGridCellsY - 10);

			rectangle.setFill(Color.WHITE);

			Line centerLine0 = new Line(realX, realY + GameBackground.height / GameBackground.numberOfGridCellsY / 2,
					realX + GameBackground.width / GameBackground.numberOfGridCellsX,
					realY + GameBackground.height / GameBackground.numberOfGridCellsY / 2);
			centerLine0.setStroke(Color.BLACK);
			centerLine0.setStrokeWidth(1);

			// Adds the rectangle and center line to the group
			group.getChildren().addAll(rectangle, centerLine0);
			getChildren().add(group);
			group.setRotate(rotation);

			break;
		case 1:
			Rectangle rectangle2 = new Rectangle(realX, realY, GameBackground.width / GameBackground.numberOfGridCellsX,
					GameBackground.height / GameBackground.numberOfGridCellsY);
			rectangle2.setFill(Color.TRANSPARENT);
			Rectangle rectangle3 = new Rectangle(realX, realY, GameBackground.width / GameBackground.numberOfGridCellsX,
					GameBackground.height / GameBackground.numberOfGridCellsY);
			rectangle3.setFill(Color.TRANSPARENT);

			Arc arc1 = new Arc(realX, realY + GameBackground.height / GameBackground.numberOfGridCellsY,
					GameBackground.width / GameBackground.numberOfGridCellsX - 5,
					GameBackground.height / GameBackground.numberOfGridCellsY - 5, 0, 90); // Create an arc
			arc1.setFill(Color.WHITE);
			arc1.setType(ArcType.ROUND);

			Arc arc2 = new Arc(realX, realY + GameBackground.height / GameBackground.numberOfGridCellsY, 5, 5, 0, 90);
			arc2.setFill(new Color(0.33, 0.67, 1.0, 0.5));
			arc2.setType(ArcType.ROUND);

			Arc arc = new Arc(realX, realY + GameBackground.height / GameBackground.numberOfGridCellsY,
					GameBackground.height / GameBackground.numberOfGridCellsY / 2,
					GameBackground.height / GameBackground.numberOfGridCellsY / 2, 0, 90);
			arc.setType(ArcType.OPEN);
			arc.setFill(Color.TRANSPARENT);
			arc.setStroke(Color.BLACK);
			arc.getStrokeWidth();

			// Calculates new coordinates for the smaller arc.
			realX = GameBackground.height / GameBackground.numberOfGridCellsY / 2 - arc.getStrokeWidth();
			realY = (realY + GameBackground.height / GameBackground.numberOfGridCellsY) + arc.getStrokeWidth();
			arc.setRadiusX(realX);
			arc.setRadiusY(realX);

			// Creates groups to hold the shapes and add them to the scene.
			Group group2 = new Group();
			Group group3 = new Group();
			group2.getChildren().addAll(rectangle2, arc1, arc2);
			getChildren().add(group2);
			group2.setRotate(-rotation);
			group3.getChildren().addAll(rectangle3, arc);
			group3.setRotate(-rotation);
			getChildren().add(group3);

			break;
		case 2:
			// Creates a group to hold the rectangles.
			Group group4 = new Group();
			Rectangle rectangle4 = new Rectangle(realX, realY, GameBackground.width / GameBackground.numberOfGridCellsX,
					GameBackground.height / GameBackground.numberOfGridCellsY);
			rectangle4.setFill(Color.WHITE);
			getChildren().add(rectangle4);

			Rectangle square1_1 = new Rectangle(realX, realY, 5, 5);
			Rectangle square1_2 = new Rectangle(realX,
					realY + GameBackground.height / GameBackground.numberOfGridCellsY - 5, 5, 5);
			Rectangle square1_3 = new Rectangle(realX + GameBackground.width / GameBackground.numberOfGridCellsX - 5,
					realY, 5, 5);
			Rectangle square1_4 = new Rectangle(realX + GameBackground.width / GameBackground.numberOfGridCellsX - 5,
					realY + GameBackground.height / GameBackground.numberOfGridCellsY - 5, 5, 5);

			// Sets the fill color for each square.
			for (Rectangle square : new Rectangle[] { square1_1, square1_2, square1_3, square1_4 }) {
				square.setFill(new Color(0.33, 0.67, 1.0, 0.5));

			}
			// Adds rectangles and squares to the group.
			group4.getChildren().addAll(rectangle4, square1_1, square1_2, square1_3, square1_4);
			getChildren().add(group4);

			// Rotates the group
			group4.setRotate(rotation);
			break;

		case 3:
			Group group5 = new Group();
			Rectangle rectangle5 = new Rectangle(realX, realY, GameBackground.width / GameBackground.numberOfGridCellsX,
					GameBackground.height / GameBackground.numberOfGridCellsY);
			rectangle5.setFill(Color.TRANSPARENT);

			Rectangle rectangle6 = new Rectangle(realX, realY + 5,
					GameBackground.width / GameBackground.numberOfGridCellsX,
					GameBackground.height / GameBackground.numberOfGridCellsY - 5);
			rectangle6.setFill(Color.WHITE);
			getChildren().add(rectangle6);

			Rectangle square2_1 = new Rectangle(realX + GameBackground.width / GameBackground.numberOfGridCellsX - 5,
					realY + GameBackground.height / GameBackground.numberOfGridCellsY - 5, 5, 5);
			Rectangle square2_2 = new Rectangle(realX,
					realY + GameBackground.height / GameBackground.numberOfGridCellsY - 5, 5, 5);

			// Sets the fill color for the squares and adds them to the scene.
			for (Rectangle square2 : new Rectangle[] { square2_1, square2_2 }) {
				square2.setFill(new Color(0.33, 0.67, 1.0, 0.5));
				getChildren().add(square2);
			}
			// Adds shapes to the group and rotates the group.
			group5.getChildren().addAll(rectangle6, rectangle5, square2_1, square2_2);
			getChildren().add(group5);
			group5.setRotate(rotation);
			break;
		default:
			break;
		}

	}

}
