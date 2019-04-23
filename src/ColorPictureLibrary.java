
import java.util.*;
import java.io.*;

public class ColorPictureLibrary {
    public HashMap<Integer, Picture> colorToPicture;
    public String libraryColorResult = "pictureLibraryResult.csv";
    private int numPictures = 100; // TODO: remember to update this number when we expand the library;

    // TODO: assign a threshold that we can adjust as long as the variances is less
    // than that, we can assign the color name, see if any exception;

    // Constructor which loop through all pictures and write to CSV. This line will retire once we have everything running.
    public ColorPictureLibrary() {
	ColorNameLibrary cNames = new ColorNameLibrary();
	colorToPicture = new HashMap<Integer, Picture>();

	// read through the picture library and store the result to the CSV file.
	for (Integer i = 1; i <= numPictures; i++) {
	    Picture pi = new Picture(i, cNames);
	    colorToPicture.put(i, pi);
	}
	writeColorToCSV(colorToPicture);
    }

    // Method that returns a similar picture as user input. Should be called by system output. 
    public String similarPic(int[][] userPicture) {
	Integer ref = 0;
	int benchmark = 0;
	Picture userP = new Picture(userPicture);

	for (Integer i : colorToPicture.keySet()) {
	    Integer count = 0;
	    Picture thisP = colorToPicture.get(i);

	    if (thisP.getC1() == userP.getC1() | thisP.getC1() == userP.getC2() ) {
		count+=100;
	    }
	    
	    if (thisP.getC2() == userP.getC2() | thisP.getC2() == userP.getC3()) {
		count+=80;
	    }
	    if (thisP.getC3() == userP.getC3() | thisP.getC3() == userP.getC4()) { //TODO: Add try-catch block for single color;
		count+=60;
	    }
	    if (thisP.getC4() == userP.getC4() | thisP.getC4() == userP.getC5()) { //TODO: Add try-catch block for single color;
		count+=40;
	    }
	    if (thisP.getC5() == userP.getC5()) { //TODO: Add try-catch block for single color;
		count+=20;
	    }
  
	    if (count > benchmark) {
		ref = i;
		benchmark = count;
	    }
	}

	Picture similarPic = colorToPicture.get(ref);
	return similarPic.getPictureFileName();
    }

    // Method that write the resulted hash map to the CSV file.
    public void writeColorToCSV(HashMap<Integer, Picture> pictureLib) {
	File out = new File(libraryColorResult);

	try (PrintWriter pw = new PrintWriter(out)) {

	    // Prints each line in answers
	    for (Integer i : pictureLib.keySet()) {
		pw.print(i + ",");
		Picture thisP = pictureLib.get(i);
		pw.print(thisP.getC1() + ",");
		pw.print(thisP.getC2() + ",");
		pw.print(thisP.getC3() + ",");
		pw.print(thisP.getC4() + ",");
		pw.print(thisP.getC5() + ",");
	    }

	} catch (IOException e) {
	    e.printStackTrace();
	    System.out.println("Could not write the File out.  Check permissions, or contact course staff for help");
	}
    }

}
