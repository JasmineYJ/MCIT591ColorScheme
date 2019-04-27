
import java.util.*;

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import java.io.*;

/**
 * Recommend similar picture by comparing the color names
 * 
 * @author jasminejian
 *
 */

public class ColorPictureLibrary {
    public HashMap<Integer, Picture> colorToPicture;
    public String libraryColorResult = "pictureLibraryResult.csv";
    private int numPictures = 100; // TODO: remember to update this number when we expand the library;

    // TODO: assign a threshold that we can adjust as long as the variances is less
    // than that, we can assign the color name, see if any exception;

    // Constructor which loop through all pictures and write to CSV. This line will
    // retire once we have everything running.
    public ColorPictureLibrary(ColorNameLibrary cNames) {
	colorToPicture = new HashMap<Integer, Picture>();

	// read through the picture library and store the result to the CSV file.
	for (Integer i = 1; i <= numPictures; i++) {
	    Picture pi = new Picture(i, cNames);
	    colorToPicture.put(i, pi);
	}
	printOutColors(colorToPicture);
//	writeColorToCSV(colorToPicture); TODO: Add this back
    }

    // Method that returns a similar picture as user input. Should be called by
    // system output.
    public String similarPic(String[] userPicture) {
	Integer ref = 0;
	int benchmark = 0;
	int len = userPicture.length;

	for (Integer i : colorToPicture.keySet()) {
	    Integer count = 0;
	    Picture thisP = colorToPicture.get(i);

	    for (int j = 0; j < len; j++) {
		if (thisP.getC1().equals(userPicture[0])) {
		    count ++;
		}
		if (thisP.getC2().equals(userPicture[0])) {
		    count ++;
		}
		if (thisP.getC3().equals(userPicture[0])) {
		    count ++;
		}
		if (thisP.getC4().equals(userPicture[0])) {
		    count ++;
		}
		if (thisP.getC5().equals(userPicture[0])) {
		    count ++;
		}
	    }

	    if (count > benchmark) {
		ref = i;
		benchmark = count;
	    }

	}

	Picture similarPic = colorToPicture.get(ref);
	return similarPic.getPictureFileName();
    }

    //TODO: replace this section with the section below. Now for test purpose, print out the colors read;
    public void printOutColors(HashMap<Integer, Picture> pictureLib) {
		    for (Integer i : pictureLib.keySet()) {
			System.out.print(i + ",");
			Picture thisP = pictureLib.get(i);
			System.out.print(thisP.getC1() + ",");
			System.out.print(thisP.getC2() + ",");
			System.out.print(thisP.getC3() + ",");
			System.out.print(thisP.getC4() + ",");
			System.out.print(thisP.getC5() + ",");
		    }
    }
    
    
//    // Method that write the resulted hash map to the CSV file.
//    public void writeColorToCSV(HashMap<Integer, Picture> pictureLib) {
//	File out = new File(libraryColorResult);
//
//	try (PrintWriter pw = new PrintWriter(out)) {
//
//	    // Prints each line in answers
//	    for (Integer i : pictureLib.keySet()) {
//		pw.print(i + ",");
//		Picture thisP = pictureLib.get(i);
//		pw.print(thisP.getC1() + ",");
//		pw.print(thisP.getC2() + ",");
//		pw.print(thisP.getC3() + ",");
//		pw.print(thisP.getC4() + ",");
//		pw.print(thisP.getC5() + ",");
//	    }
//
//	} catch (IOException e) {
//	    e.printStackTrace();
//	    System.out.println("Could not write the File out.  Check permissions, or contact course staff for help");
//	}
//  }

}
