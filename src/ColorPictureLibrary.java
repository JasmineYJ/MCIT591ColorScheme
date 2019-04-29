
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
    private int numPictures = 203; // TODO: remember to update this number when we expand the library;

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
	Integer ref1 = 0;
	Integer ref2 = 0;
	Integer ref3 = 0;
	int benchmark1 = 1;
	int benchmark2 = 1;
	int benchmark3 = 1;
	int len = userPicture.length;

	for (Integer i : colorToPicture.keySet()) {
	    Integer count = 0;
	    Picture thisP = colorToPicture.get(i);

	    // System.out.println(thisP.getPictureFileName()); //TODO: Remember to delete

	    for (String s : userPicture) {

		if (thisP.getC1().equals(s) | thisP.getC2().equals(s) | thisP.getC3().equals(s)
			| thisP.getC4().equals(s) | thisP.getC5().equals(s) | thisP.getC6().equals(s)) {
		    count++;
		}

	    }

	    if (count > benchmark1) {
		benchmark2 = benchmark1;
		ref2 = ref1;
		ref1 = i;
		benchmark1 = count;
		// System.out.println("benchmark1 now is " + benchmark1);// TODO: remmeber to
		// delete
	    }

	    else if (count > benchmark2) {
		benchmark3 = benchmark2;
		ref3 = ref2;
		ref2 = i;
		benchmark2 = count;
		// System.out.println("benchmark2 now is " + benchmark2);// TODO: remmeber to
		// delete
	    }

	    else if (count > benchmark3) {
		ref3 = i;
		benchmark3 = count;
		// System.out.println("benchmark3 now is " + benchmark3);// TODO: remmeber to
		// delete
	    }

	}

	Picture sP1 = colorToPicture.get(ref1);
	Picture sP2 = colorToPicture.get(ref2);
	Picture sP3 = colorToPicture.get(ref3);
	return sP1.getPictureFileName() + "," + sP2.getPictureFileName() + "," + sP3.getPictureFileName();// TODO:
													  // remember to
													  // integerte
													  // with UX
    }

    // TODO: replace this section with the section below. Now for test purpose,
    // print out the colors read;
    public void printOutColors(HashMap<Integer, Picture> pictureLib) {
	for (Integer i : pictureLib.keySet()) {
	    System.out.print(i + ","); // TODO: Remember to delete
	    Picture thisP = pictureLib.get(i);
	    System.out.print(thisP.getC1() + ",");
	    System.out.print(thisP.getC2() + ",");
	    System.out.print(thisP.getC3() + ",");
	    System.out.print(thisP.getC4() + ",");
	    System.out.print(thisP.getC5() + ",");
	    System.out.println(thisP.getC6());
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
