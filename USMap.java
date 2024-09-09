import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
/*
 * USMap.java
 * 
 * @author Elaina Pan
 * @since September 4th 2024
 */
 public class USMap {
	 
	public static void main(String[] args) {
		/*
		StdDraw.setPenRadius(0.05);
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.point(0.5, 0.5);
		StdDraw.setPenColor(StdDraw.MAGENTA);
		StdDraw.line(0.2, 0.2, 0.8, 0.2);*/
		
		/** Set up the canvas size and scale */
		StdDraw.setTitle("USMap");
		StdDraw.setCanvasSize(900, 512);
		StdDraw.setXscale(128.0, 65.0);
		StdDraw.setYscale(22.0, 52.0);
		
		/** Draw the cities **/
		StdDraw.setPenRadius(0.006);
		StdDraw.setPenColor(StdDraw.BLACK);
		FileUtils utils = new FileUtils();
		java.util.Scanner cities = utils.openToRead("cities.txt");
		String[] cityNames = new String[1112];
		double[] cityY = new double[1112];
		double[] cityX = new double[1112];
		int counter = 0;
		while(cities.hasNext()) {
			cityY[counter] = cities.nextDouble();
			cityX[counter] = cities.nextDouble();
			cityNames[counter] = cities.nextLine();
			StdDraw.point(cityX[counter], cityY[counter]);
			counter++;
		}
		
		/** Big cities **/
		java.util.Scanner bigCities = utils.openToRead("bigCities.txt");
		StdDraw.setPenColor(StdDraw.RED);
		for(int i=0;i<10;i++) {
			String row = bigCities.nextLine();
			for(int j=0;j<1112;j++) {
				if(row.contains(cityNames[j])) {
					int population = Integer.parseInt(row.substring(row.length()-7).trim());
					double radius = 0.6 * (Math.sqrt(population)/18500);
					StdDraw.setPenRadius(radius);
					StdDraw.point(cityX[j],cityY[j]);
				}
			}
					
		}
		StdDraw.setPenColor(StdDraw.BLUE);
		while(bigCities.hasNext()) {
			String row = bigCities.nextLine();
			for(int j=0;j<1112;j++) {
				if(row.contains(cityNames[j])) {
					int population = Integer.parseInt(row.substring(row.length()-7).trim());
					double radius = 0.6 * (Math.sqrt(population)/18500);
					StdDraw.setPenRadius(radius);
					StdDraw.point(cityX[j],cityY[j]);
				}
			}
		}
	}
}
class FileUtils {
	/**
	 * Opens a file to read using the Scanner class.
	 * @param fileName		name of the file to open
	 * @return 				the scanner object to the file
	 */
	public static java.util.Scanner openToRead(String fileName) {
		java.util.Scanner input = null;
		try {
			input = new java.util.Scanner(new java.io.File(fileName));
		}
		catch (java.io.FileNotFoundException e) {
			System.err.println("ERROR: Cannot open " + fileName + 
						" for reading.");
			System.exit(4);
		}
		return input;
	}
	/**
	 * Opens a file to write using the PrintWriter class
	 * @param fileName		name of the file to pen
	 * @return 				the PrintWriter object to the file
	 */
	public static PrintWriter openToWrite (String fileName) {
		PrintWriter output = null;
		try {
			output = new PrintWriter(new File(fileName));
		}
		catch (FileNotFoundException e) {
			System.err.println("ERROR: Cannot open " + fileName +
							" for writing.");
			System.exit(5);
		}
		return output;
	}
}
