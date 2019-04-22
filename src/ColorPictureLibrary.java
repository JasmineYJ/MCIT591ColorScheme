import java.util.*;
import java.io.*;

public class ColorPictureLibrary {
    public HashMap<Integer, Picture> colorToPicture;
    private int numPictures = 100; // TODO: remember to update this number when we expand the library;


    // TODO: to call the color reader from Yuhong and get the RGB value;
    // TODO: assign a threshold that we can adjust as long as the variances is less
    // than that, we can assign the color name, see if any exception;
    // TODO: maybe we can have two threshold, if the first one doesn't work, we can
    // use the second one, see how long it takes to calculte.

    public ColorPictureLibrary() {
	ColorNameLibrary cNames = new ColorNameLibrary();
	colorToPicture = new HashMap<Integer, Picture>();
	
	// read through the picture library.
	for (Integer i = 1; i <= numPictures; i++) {
	    Picture pi = new Picture(i, cNames);
	    colorToPicture.put(i, pi);
	}
	writeColorToCSV(colorToPicture);
    }

    // write to the CSV file one by one.
    public void writeColorToCSV (HashMap<Integer, Picture> pictureLib) {
	File out = new File("pictureLibraryResult.csv");

	try (PrintWriter pw = new PrintWriter(out)) {

	    // Prints each line in answers
	    for (Integer i : pictureLib.keySet()) {
		pw.print(i+",");
		Picture thisP = pictureLib.get(i);
		pw.print(thisP.getC1()+",");
		pw.print(thisP.getC2()+",");
		pw.print(thisP.getC3()+",");
		pw.print(thisP.getC4()+",");
		pw.print(thisP.getC5()+",");
	    }

	} catch (IOException e) {
	    e.printStackTrace();
	    System.out.println("Could not write the File out.  Check permissions, or contact course staff for help");
	}
    }

}
