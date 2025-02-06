
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

//Nursena Kıyga 150122008
//Alper Kaan Arslan 150122059
//Sinem Yenibalcı 150122037

public class Level3 extends Pane {

	public Level3() throws FileNotFoundException {

		CarSpawner carSpawner;

		File file = new File("level3.txt");
		Scanner scanner = new Scanner(file);

		ArrayList<Path> paths = new ArrayList<>();
		ArrayList<TrafficLight> trafficLights = new ArrayList<>();

		Path path0 = new Path();
		Path path1 = new Path();
		Path path2 = new Path();
		Path path3 = new Path();

		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			String[] parts = line.split(" ");
			switch (parts[0]) {

			case "Metadata":
				double width = Double.parseDouble(parts[1]);
				double height = Double.parseDouble(parts[2]);
				int numberOfGridCellsX = Integer.parseInt(parts[3]);
				int numberOfGridCellsY = Integer.parseInt(parts[4]);
				int pathCount = Integer.parseInt(parts[5]);
				int succesfullyArrived = Integer.parseInt(parts[6]);
				int crashedCars = Integer.parseInt(parts[7]);
				GameBackground background = new GameBackground(width, height, numberOfGridCellsX, numberOfGridCellsY,
						pathCount, succesfullyArrived, crashedCars);
				getChildren().add(background);
				break;

			case "RoadTile":
				int type = Integer.parseInt(parts[1]);
				double rotation = Integer.parseInt(parts[2]);
				int gridCellX = Integer.parseInt(parts[3]);
				int gridCellY = Integer.parseInt(parts[4]);
				RoadTile roadTile = new RoadTile(type, rotation, gridCellX, gridCellY);
				getChildren().add(roadTile);
				break;

			case "Building":
				type = Integer.parseInt(parts[1]);
				rotation = Integer.parseInt(parts[2]);
				int colorIndex = Integer.parseInt(parts[3]);
				int gridCellXIndece = Integer.parseInt(parts[4]);
				int gridCellYIndece = Integer.parseInt(parts[5]);
				Building building = new Building(type, rotation, colorIndex, gridCellXIndece, gridCellYIndece);
				getChildren().add(building);
				break;

			case "TrafficLight":
				double startX = Double.parseDouble(parts[1]);
				double startY = Double.parseDouble(parts[2]);
				double endX = Double.parseDouble(parts[3]);
				double endY = Double.parseDouble(parts[4]);
				TrafficLight trafficLight = new TrafficLight(startX, startY, endX, endY);
				trafficLights.add(trafficLight);
				break;

			case "Path":
				if (Integer.parseInt(parts[1]) == 0)
					PathParts(parts, path0);
				else if (Integer.parseInt(parts[1]) == 1)
					PathParts(parts, path1);
				else if (Integer.parseInt(parts[1]) == 2)
					PathParts(parts, path2);
				else if (Integer.parseInt(parts[1]) == 3)
					PathParts(parts, path3);
				break;

			}
		}
		paths.add(path0);
		paths.add(path1);
		paths.add(path2);
		paths.add(path3);
		carSpawner = new CarSpawner(paths, trafficLights);

		
		getChildren().add(carSpawner);
		getChildren().addAll(trafficLights);

		scanner.close();
	}

	private void PathParts(String[] parts, Path path) {
		if (parts[2].equals("MoveTo")) {
			MoveTo moveTo = new MoveTo(Double.parseDouble(parts[3]), Double.parseDouble(parts[4]));
			path.getElements().addAll(moveTo);
		} else if (parts[2].equals("LineTo")) {
			LineTo lineTo = new LineTo(Double.parseDouble(parts[3]), Double.parseDouble(parts[4]));
			path.getElements().addAll(lineTo);
		}
	}
}
