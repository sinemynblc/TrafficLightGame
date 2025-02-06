import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.geometry.Point2D;

//Nursena Kıyga 150122008
//Alper Kaan Arslan 150122059
//Sinem Yenibalcı 150122037

//This class represents a car object that moves along a specified path in a JavaFX scene.
//The class provides functionality for creating and managing cars, including their movement, collision detection, and removal from the scene.

public class Car extends Group {

	private Rectangle car;
	private final int carSpeed = 80;
	private ArrayList<Rectangle> cars = new ArrayList<>();
	private PathTransition transition;
	private double x;
	private double y;
	private Path path;
	double yCoordinate;
	double xCoordinate;
	private List<PathElement> elements;
	Point2D sceneCoords;
	private boolean stopped = false;
	public static int score;
	private boolean alreadyFinished = false;

	public Car(Path path) {
		// Initializes PathTransition
		transition = new PathTransition();
		this.path = path;
		elements = path.getElements();

		// Creates cars in a rectangular shape.
		car = new Rectangle(15.0, 8.0, Color.DARKBLUE);
		car.setWidth(15.0);
		car.setHeight(8.0);
		cars.add(car);

		// Sets initial position of the car to the starting point of the path
		PathElement element = elements.get(0);
		MoveTo moveTo = (MoveTo) element;
		xCoordinate = moveTo.getX();
		yCoordinate = moveTo.getY();
		car.setX(xCoordinate);
		car.setY(yCoordinate);

		// Adds the car to the scene.
		getChildren().add(car);

		// Sets transition properties.
		transition.setNode(this);
		transition.setPath(path);
		transition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		transition.setDuration(Duration.seconds(calculateLength(path) / carSpeed)); // Calculates duration based on path
																					// length and car speed
		transition.setInterpolator(Interpolator.LINEAR);
		transition.setCycleCount(1);
		transition.play();

		// Updates the current position of the car during the transition by converting
		// its local coordinates to scene coordinates.
		transition.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
			sceneCoords = car.localToScene(car.getX(), car.getY());
			x = sceneCoords.getX();
			y = sceneCoords.getY();
		});
		// Sets actions to be performed when the transition finishes
		transition.setOnFinished(event -> {
			if (!alreadyFinished) {
				score++; // Increments score when car reaches the end of the path for the first time.
				alreadyFinished = true;
			}

			transition.stop(); // Stops the transition
			getChildren().remove(car); // Removes the car from the scene.
			// CarSpawner.cars.remove(car);

		});

	}

	// Pauses the transition, effectively stopping the movement of the car.
	void stop() {
		transition.pause();
		stopped = true;
	}

	// Resumes the transition, allowing the car to continue its movement.
	void move() {
		transition.play();
		stopped = false;
	}

	// Gets the current x-coordinate of the car on the scene.
	double getX() {
		return x;
	}

	// Gets the current y-coordinate of the car on the scene.
	double getY() {
		return y;
	}

	// Calculates the distance between the two cars using the distance formula.
	public boolean isTooCloseTo(Car otherCar) {
		double distance = Math
				.sqrt(Math.pow(this.getX() - otherCar.getX(), 2) + Math.pow(this.getY() - otherCar.getY(), 2));

		return distance < 28; // Eğer mesafe 20 birimden azsa true döner
	}

	// Checks if the car is currently stopped.
	public boolean isStopped() {
		return stopped;
	}

	// Calculates the length of the provided path.
	public double calculateLength(Path path) {

		double totalLength = 0;// Initializes the total length of the path to zero.
		double startX = 0; // Initializes the starting x-coordinate to zero.
		double startY = 0; // Initializes the starting y-coordinate to zero.

		for (PathElement element : elements) {

			if (element instanceof MoveTo) {
				// If the element is a MoveTo, updates the starting coordinates.
				MoveTo moveTo = (MoveTo) element;
				startX = moveTo.getX();
				startY = moveTo.getY();

			} else if (element instanceof LineTo) {
				// If the element is a LineTo, calculates the length of the line segment and
				// adds it to the total length.
				LineTo lineTo = (LineTo) element;
				double deltaX = lineTo.getX() - startX;
				double deltaY = lineTo.getY() - startY;
				totalLength += Math.sqrt(deltaX * deltaX + deltaY * deltaY);
				startX = lineTo.getX();
				startY = lineTo.getY();

			}

		}

		return totalLength;
	}

	// Checks if this car is colliding with another car.Calculates the distance
	// between the two cars using the distance formula.
	public boolean isCollidingWith(Car otherCar) {
		double distance = Math
				.sqrt(Math.pow(this.getX() - otherCar.getX(), 2) + Math.pow(this.getY() - otherCar.getY(), 2));
		return distance < 15;

	}

	// Checks if this car is on the same path as another car.
	public boolean isOnPath(Car otherCar) {

		if (otherCar.path == this.path) {
			return true;
		}
		// Checks for intersection between the shape of the other car and the path of
		// this car(CurrentCar).
		Shape intersection = Shape.intersect(otherCar.car, this.path);

		return !intersection.getBoundsInLocal().isEmpty();

	}

	// Removes the car from the scene.
	public void remove() {
		getChildren().remove(car);
	}

}
