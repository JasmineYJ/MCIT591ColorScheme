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
 * 
 * @author jasminejian
 *
 */

public class ColorNameLibrary {
    public HashMap<String, ColorInt> colorNames;
    private final String colorNamefile = "ColorNames.csv";
    private final double thresholdOne = 0.0;

    public String getColorName(int rValue, int gValue, int bValue) {
	String thisName = "";
	double variance = 1000.0;
	boolean nameFound = false;

	while (nameFound == false) {
	    double thisThreshold = thresholdOne;

	    for (String c : colorNames.keySet()) {

		double thisVariance = sumOfVariance(colorNames.get(c), rValue, gValue, bValue);
		if (thisVariance <= thresholdOne && thisVariance < variance) {
		    thisName = c;
		    variance = thisVariance;
		    nameFound = true;
		}
	    }

	    thisThreshold += 10; // we increase the threshold until we find a proper name for this color;
	}

	return thisName;
    }

    public double sumOfVariance(ColorInt colorInt, int rValue, int gValue, int bValue) {
	double thisVariance = 0;
	thisVariance += (rValue - colorInt.getrValue()) ^ 2;
	thisVariance += (gValue - colorInt.getgValue()) ^ 2;
	thisVariance += (bValue - colorInt.getbValue()) ^ 2;
	return Math.sqrt(thisVariance);
    }

    /*
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
    	String[] colorNames = new String[5];
		String tempName;
		ColorNameLibrary cN = new ColorNameLibrary();
		tempName = cN.getColorName(219, 170, 57);
	}
    
    

}
