import java.util.*;
import java.io.*;

public class ColorPictureLibrary {
    public HashMap<String, Picture> colorToPicture;
    private int numPictures = 100; // TODO: remember to update this number when we expand the library;


    // TODO: to call the color reader from Yuhong and get the RGB value;
    // TODO: assign a threshold that we can adjust as long as the variances is less
    // than that, we can assign the color name, see if any exception;
    // TODO: maybe we can have two threshold, if the first one doesn't work, we can
    // use the second one, see how long it takes to calculte.

    public ColorPictureLibrary() {
	ColorNameLibrary cNames = new ColorNameLibrary();
	colorToPicture = new HashMap<String, Picture>();

	for (int i = 1; i <= 100; i++) {
	    Picture pi = new Picture(i);
	    
	    // 21 to XX are copied from KmeansCalculator Main; check and see if we can wrap
	    // them into a simple method;
	    ImageReading reader = new ImageReading(pi.);
	    int[][] colorResult = reader.getImageRGB();
	    int pixelNum = reader.getPixelNum();
	    KmeansCalculator kC = new KmeansCalculator(pixelNum, numOfColor, colorResult);

	    int[][] firstCenter = kC.firstPathCenter();
	    int[] label = kC.lablePixels(firstCenter);

	    /**
	     * int[][] is the result containing the top N colors. Each row stores a color
	     */

	    int[][] result = new int[numOfColor][3];

	    for (int i = 0; i < 5; i++) {
		for (int i1 = 0; i1 < numOfColor; i1++) {
		    int rValue = result[i1][0];
		    int gValue = result[i1][1];
		    int bValue = result[i1][2];
		    String colorName = cNames.getColorName(rValue, gValue, bValue);

		}
	    }

	    /**
	     * int num is the round of iteration. The bigger this number is, the more
	     * accurate the result would be, but at the same time, the longer the run time
	     * would be.
	     */

	    int num = 0;
	    while (num < roundOfIteration) {
		result = kC.calculateCenter(label);
		label = kC.lablePixels(result);
		num++;
	    }

	    getColorName

	    pictureLibraryResult = new ArrayList<>();

	    for (int i1 = 0; i1 < numOfColor; i1++) {
		String prettyPrint = "";
		prettyPrint += result[i1][0] + ",";
		prettyPrint += result[i1][1] + ",";
		prettyPrint += result[i1][2];
	    }

	    // TODO: call the CSV writer print the result color to the CSV file
	}
    }

    /**
     * Method used to write the color we read into the CSV file
     */

    public class writeColorToCSV {
	// ArrayList for holding the top N color to each picture. Each index corresponds
	// to color file name minus 1 (i.e. color.get(2) will correspond to picture 3)

	// Initializes the answers ArrayList with null Strings
	for(
	int ii = 0;ii<numPictures;ii++)
	{

	}

    /**
     * Writes a .csv of the answers
     */
    public void writeAnswers() {
	File out = new File("pictureLibraryResult.csv");

	try (PrintWriter pw = new PrintWriter(out)) {

	    // Prints each line in answers
	    for (String s : colorToPicture.keySet()) {
		pw.println(s);
	    }

	} catch (IOException e) {
	    e.printStackTrace();
	    System.out.println("Could not write the File out.  Check permissions, or contact course staff for help");
	}
    }

}
