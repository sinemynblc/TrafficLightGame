import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Nursena Kıyga 150122008
//Alper Kaan Arslan 150122059
//Sinem Yenibalcı 150122037

// This class handles the initialization of cars, updates their movement and interactions with traffic lights and other cars,and manages the game state including crash count and score.
//Also,it provides functionality to restart the game when needed.
public class CarSpawner extends Pane {
	static ArrayList<Car> cars;
	private ArrayList<Path> paths;
	private ArrayList<TrafficLight> trafficLights;
	private double time = 0;
	private int crashCount;
	private Label crashCountLabel;
	private Label scoreLabel;
	private Car lastCar1 = null; // Last spawned cars on two different paths.
	private Car lastCar2 = null;
	private Region space = new Region(); // Empty region to create spacing between labels.
	private AnimationTimer timer;
	private HBox labelBox;

	// Constructor to initialize the CarSpawner with paths and traffic lights.
	public CarSpawner(ArrayList<Path> paths, ArrayList<TrafficLight> trafficLights) {
		this.paths = paths;
		this.trafficLights = trafficLights;
		CarSpawner.cars = new ArrayList<>();

		// Initializes the crash count and create labels to display the crash count and
		// score.
		crashCountLabel = new Label("Crash Count: " + crashCount + "/" + GameBackground.crashedCars);
		scoreLabel = new Label("Score: " + Car.score + "/" + GameBackground.succesfullyArrived);
		space.setPrefWidth(10);
		labelBox = new HBox(crashCountLabel, space, scoreLabel);
		getChildren().add(labelBox);

		// Creates and starts animation timer to continuously update the game state.
		timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				// Her frame'de güncelle
				update();
			}
		};
		timer.start();
	}

//Updates the game state on each frame.
	private void update() {

		time += 0.03;

		scoreLabel.setText("Score: " + Car.score + "/" + GameBackground.succesfullyArrived);

		// Checks if the player has achieved the target score.
		if (Car.score >= GameBackground.succesfullyArrived) {

			// Removes label box and stop all cars when the game is won
			getChildren().remove(labelBox);

			for (Car car : cars) {
				car.stop();
				car.remove();
			}
			cars.clear();
			getChildren().removeAll(cars);
			timer.stop();
			// Resets crash count and score.
			crashCount = 0;
			Car.score = 0;
			// Show the win screen.
			Win winner = new Win(this);
			winner.show();
			return;
		}
		// Update the movement of each car.
		for (Car currentCar : cars) {

			currentCar.move();

			// Checks for collisions with traffic lights.
			for (TrafficLight currentTrafficLight : trafficLights) {
				if (currentCar.getBoundsInParent().intersects(currentTrafficLight.getBoundsInParent())
						&& currentTrafficLight.getColor() == javafx.scene.paint.Color.RED) {
					currentCar.stop();
					break;
				}
			}
			// Calculates the angle of movement for the current car.
			double deltaX = currentCar.getX() - currentCar.xCoordinate;
			double deltaY = currentCar.getY() - currentCar.yCoordinate;
			double angleInRadians = Math.atan2(deltaY, deltaX);
			double angleInDegrees = (Math.toDegrees(angleInRadians) + 360) % 360;

			// Checks for collisions with other cars.
			for (Car otherCar : cars) {

				double deltaX2 = otherCar.getX() - otherCar.xCoordinate;
				double deltaY2 = otherCar.getY() - otherCar.yCoordinate;
				double angleInRadians2 = Math.atan2(deltaY2, deltaX2);
				double angleInDegrees2 = (Math.toDegrees(angleInRadians2) + 360) % 360;

				if (otherCar != currentCar && currentCar.isCollidingWith(otherCar) && !otherCar.isStopped()
						&& !currentCar.isStopped() && Math.abs(angleInDegrees - angleInDegrees2) > 10
						&& Math.abs(currentCar.getX() - currentCar.xCoordinate) > 10
						&& Math.abs(currentCar.getY() - currentCar.yCoordinate) > 10 && currentCar.isOnPath(otherCar)) {

					currentCar.stop();
					otherCar.stop();
					handleCrash(currentCar, otherCar);

					// Updates crash count if the collision involves new cars.
					if (currentCar != lastCar1 && currentCar != lastCar2 && otherCar != lastCar1
							&& otherCar != lastCar2) {
						crashCount++;
						crashCountLabel.setText("Crash Count: " + crashCount + "/" + GameBackground.crashedCars);

						// Show the try again screen if the crash count exceeds the limit.
						if (crashCount >= GameBackground.crashedCars) {

							timer.stop();
							TryAgainScreen tryAgainScreen = new TryAgainScreen(this);
							tryAgainScreen.show();
							return;

						}
						// Updates the last involved cars.
						lastCar1 = currentCar;
						lastCar2 = otherCar;

					}

					break;
				}
			}

		}

		// Checks for proximity to other cars and stops if necessary.
		for (Car currentCar : cars) {

			double deltaX = currentCar.getX() - currentCar.xCoordinate;
			double deltaY = currentCar.getY() - currentCar.yCoordinate;
			double angleInRadians = Math.atan2(deltaY, deltaX);
			double angleInDegrees = (Math.toDegrees(angleInRadians) + 360) % 360;

			for (Car otherCar : cars) {

				double deltaX2 = otherCar.getX() - otherCar.xCoordinate;
				double deltaY2 = otherCar.getY() - otherCar.yCoordinate;
				double angleInRadians2 = Math.atan2(deltaY2, deltaX2);
				double angleInDegrees2 = (Math.toDegrees(angleInRadians2) + 360) % 360;

				if (currentCar.isTooCloseTo(otherCar) && currentCar != otherCar && otherCar.isStopped()
						&& Math.sqrt(Math.pow(otherCar.getX() - otherCar.xCoordinate, 2)
								+ Math.pow(otherCar.getY() - otherCar.yCoordinate, 2)) > 5
						&& Math.sqrt(Math.pow(currentCar.getX() - currentCar.xCoordinate, 2)
								+ Math.pow(currentCar.getY() - currentCar.yCoordinate, 2)) > 5
						&& (currentCar.isOnPath(otherCar) || (Math.abs(angleInDegrees - angleInDegrees2) != 180
								&& angleInDegrees - angleInDegrees2 < 90 && angleInDegrees - angleInDegrees2 > 90))) {
					currentCar.stop();

					break;

				}

			}
		}
		// Spawns new cars randomly.
		if (time > 3.5 && Math.random() < 0.3) {

			spawnCar(paths);
			time = 0;
		}

	}

//Handles the collision between two cars. Stops both cars and removes them from the scene.
	private void handleCrash(Car car1, Car car2) {
		car1.stop();
		car2.stop();

		// Calculates the midpoint of the collision.
		double crashX = (car1.getX() + car2.getX()) / 2;
		double crashY = (car1.getY() + car2.getY()) / 2;
		double crashRadius = 20;

		// Pauses briefly before removing crashed cars.
		PauseTransition pause1 = new PauseTransition(Duration.seconds(0.5));
		pause1.setOnFinished(event -> {
			car1.stop();
			car2.stop();

			// Removes crashed cars from the scene and car list.
			getChildren().remove(car1);
			cars.remove(car1);

			getChildren().remove(car2);
			cars.remove(car2);

			// Resumes movement of other cars after the crash is cleared.
			for (Car car : cars) {
				double distanceToCrash = Math.sqrt(Math.pow(car.getX() - crashX, 2) + Math.pow(car.getY() - crashY, 2));
				if (distanceToCrash < crashRadius) {
					car.move();
				}
			}
		});
		pause1.play();
	}

	// Spawns a new car on a randomly selected path.
	private void spawnCar(List<Path> paths) {

		Random random = new Random();
		Path path = paths.get(random.nextInt(paths.size()));

		Car newCar = new Car(path);
		cars.add(newCar);

		// Adds the new car to the list and the scene if it's not already present.
		if (!cars.contains(newCar) || cars != null) {
			cars.add(newCar);
			getChildren().add(newCar);
		}

	}

//Restarts the game by resetting the crash count, removing existing cars and labels, and creating a new instance of CarSpawner to initialize the game again.
	public void restartGame() {
		// Removes existing labels and cars
		getChildren().remove(labelBox);
		for (Car car : cars) {
			car.stop();
			car.remove();
		}
		cars.clear();
		getChildren().removeAll(cars);

		// Creates a new instance of CarSpawner to initializes the game again.
		CarSpawner carSpawner = new CarSpawner(this.paths, this.trafficLights);

		crashCount = 0; // Resets crash count.
		Car.score = 0;
		getChildren().add(carSpawner); // Updates the screen.
	}

}
