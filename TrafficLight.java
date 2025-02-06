import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

//Nursena Kıyga 150122008
//Alper Kaan Arslan 150122059
//Sinem Yenibalcı 150122037

//Represents a traffic light object that consists of a line and a circle indicating the current traffic signal.
//The color of the circle changes between green and red when clicked, simulating a traffic signal.

public class TrafficLight extends Group {

	private Circle circle;
	private boolean isGreen = true;
	private double centerX;
	private double centerY;

	// Constructor to create a traffic light with a line and a circle at specified
	// start and end points.
	public TrafficLight(double startX, double startY, double endX, double endY) {
		Line line = new Line(startX, startY, endX, endY);
		line.setStrokeWidth(1);
		line.setStroke(Color.GREY);

		// Calculates the center coordinates of the circle.
		centerX = (startX + endX) / 2;
		centerY = (startY + endY) / 2;

		// Creates a circle representing the light of the traffic light.
		circle = new Circle(centerX, centerY, 5, Color.GREEN);

		// Sets an event handler to change the color of the circle when clicked.
		circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				changeColor();
			}
		});

		// Adds the line and circle to the group.
		getChildren().addAll(line, circle);
	}

	// Changes the color of the traffic light from green to red and vice versa.
	void changeColor() {
		if (isGreen) {
			circle.setFill(Color.RED);
			isGreen = false;
		} else {
			circle.setFill(Color.GREEN);
			isGreen = true;
		}
	}

	// Gets the color of the traffic light.
	Color  getColor() {
	 return (Color) circle.getFill();
	}
    
}
