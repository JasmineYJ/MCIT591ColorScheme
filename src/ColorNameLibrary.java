import java.io.*;
import java.util.*;
import java.awt.Color;

/**
 * This class has the following methods: (1) public ColorNameLibrary(): read
 * from the color names file and assign color name to the new color. Because
 * color itself is a primitive type, so we created a class called colorInt, so
 * that we can store the color as an object and keep it into a hash map for
 * looping; (2)public String getColorName(int rValue, int gValue, int bValue):
 * this method is to get the color Name for given RGB value, given that the
 * value won't be an exact match, we allow a variance of certain threshold, if
 * we can't find a name for this given color within the threshold, we increment
 * the threshold until we find one; this method will be called by the picture
 * library class also, so that we can get the top five color name from the the
 * repository.
 */

public class ColorNameLibrary {
	public HashMap<String, ColorInt> colorNames;
	private final String colorNamefile = "ColorNames.csv";

	public String getColorName(int rValue, int gValue, int bValue) {
		String thisName = "";
		double minDist = 100.00 * (256.0 * 256.0);

		/**
		 * Loop through the colorNames Hashmap to find the closet color name
		 */
		for (String c : colorNames.keySet()) {
			double dist = distance(colorNames.get(c), rValue, gValue, bValue);
			if (dist < minDist) {
				minDist = dist;
				thisName = c;
			}
		}

		return thisName;
	}

	public double distance(ColorInt colorInt, int rValue, int gValue, int bValue) {
		double dist = 0;
		dist += (int) Math.pow(rValue - colorInt.getrValue(), 2);
		dist += (int) Math.pow(gValue - colorInt.getgValue(), 2);
		dist += (int) Math.pow(bValue - colorInt.getbValue(), 2);
		return dist;
	}

	/**
	 * Read from the csv file; HashMap = (Color Name, Color); Color = Name, rValue,
	 * gValue and bValue;
	 */

	public ColorNameLibrary() {
		File file = new File(colorNamefile);
		colorNames = new HashMap<String, ColorInt>();

		try {
			Scanner scanner = new Scanner(file);
			scanner.nextLine(); // skip the first row

			while (scanner.hasNextLine()) {
				String colorRow = scanner.nextLine();
				String[] columnData = colorRow.split(",");
				String tempName = columnData[0];
				int tempR = Integer.valueOf(columnData[1]);
				int tempG = Integer.valueOf(columnData[2]);
				int tempB = Integer.valueOf(columnData[3]);
				ColorInt newColor = new ColorInt(tempName, tempR, tempG, tempB);
				colorNames.put(tempName, newColor);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public HashMap<String, ColorInt> getColorNames() {
		return colorNames;
	}

	public static void main(String[] args) {
		ColorNameLibrary cN = new ColorNameLibrary();
		String tempName = cN.getColorName(147, 139, 142);
		System.out.println(tempName);
	}

}
