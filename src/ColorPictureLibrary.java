
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
<<<<<<< HEAD
	public HashMap<Integer, Picture> colorToPicture;
	public String libraryColorResult = "pictureLibraryResult.csv";
	private int numPictures = 100; // TODO: remember to update this number when we expand the library;
=======
    public HashMap<Integer, Picture> colorToPicture;
    public String libraryColorResult = "203PictureLibrary.csv";
    // private int numPictures = 203;
>>>>>>> branch 'master' of https://github.com/JasmineYJ/MCIT591ColorScheme.git

<<<<<<< HEAD
	// TODO: assign a threshold that we can adjust as long as the variances is less
	// than that, we can assign the color name, see if any exception;

	// Constructor which loop through all pictures and write to CSV. This line will
	// retire once we have everything running.
	public ColorPictureLibrary() {
		ColorNameLibrary cNames = new ColorNameLibrary();
		colorToPicture = new HashMap<Integer, Picture>();
=======
    /**
     * Constructor which loop through all pictures and write to CSV. This line will
     * retire once we have everything running.
     * 
     * @param cNames
     */
    public ColorPictureLibrary(ColorNameLibrary cNames) {
	colorToPicture = new HashMap<Integer, Picture>();
>>>>>>> branch 'master' of https://github.com/JasmineYJ/MCIT591ColorScheme.git

<<<<<<< HEAD
		// read through the picture library and store the result to the CSV file.
		for (Integer i = 1; i <= numPictures; i++) {
			Picture pi = new Picture(i, cNames);
			colorToPicture.put(i, pi);
		}
		writeColorToCSV(colorToPicture);
=======
	/**
	 * Read through stored CSV file which contains 203 pictures and corresponding
	 * color names;
	 */
	File file = new File(libraryColorResult);

	try {
	    Scanner scanner = new Scanner(file);
	    Integer i = 1;
	    while (scanner.hasNextLine()) {
		String colorRow = scanner.nextLine();
		String[] columnData = colorRow.split(",");
		String picName = columnData[0];
		String c1 = columnData[1];
		String c2 = columnData[2];
		String c3 = columnData[3];
		String c4 = columnData[4];
		String c5 = columnData[5];
		String c6 = columnData[6];
		Picture newPic = new Picture(i, c1, c2, c3, c4, c5, c6);
		colorToPicture.put(i, newPic);
		i++;
	    }
	} catch (Exception e) {
	    // TODO: handle exception
	    e.printStackTrace();
	}

	// Retired code: read through the picture library and store the result to the
	// CSV file.
	// for (Integer i = 1; i <= numPictures; i++) {
	// Picture pi = new Picture(i, cNames);
	// colorToPicture.put(i, pi);
	// }
	// printOutColors(colorToPicture);
	// writeColorToCSV(colorToPicture);
    }

    /**
     * Method that returns a similar picture as user input. Should be called by
     * system output.
     * 
     * @param userPicture
     * @return
     */
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
		// System.out.println("benchmark1 now is " + benchmark1);
	    }
	    else if (count > benchmark2) {
		benchmark3 = benchmark2;
		ref3 = ref2;
		ref2 = i;
		benchmark2 = count;
		// System.out.println("benchmark2 now is " + benchmark2);
	    }
	    else if (count > benchmark3) {
		ref3 = i;
		benchmark3 = count;
		// System.out.println("benchmark3 now is " + benchmark3);
	    }
>>>>>>> branch 'master' of https://github.com/JasmineYJ/MCIT591ColorScheme.git
	}

<<<<<<< HEAD
	// Method that returns a similar picture as user input. Should be called by
	// system output.
	public String similarPic(int[][] userPicture) {
		Integer ref = 0;
		int benchmark = 0;
		Picture userP = new Picture(userPicture);
=======
	Picture sP1 = colorToPicture.get(ref1);
	Picture sP2 = colorToPicture.get(ref2);
	Picture sP3 = colorToPicture.get(ref3);
	return sP1.getPictureFileName() + "," + sP2.getPictureFileName() + "," + sP3.getPictureFileName();// TODO:
													  // remember to
													  // integerte
													  // with UX
    }
>>>>>>> branch 'master' of https://github.com/JasmineYJ/MCIT591ColorScheme.git

<<<<<<< HEAD
		for (Integer i : colorToPicture.keySet()) {
			Integer count = 0;
			Picture thisP = colorToPicture.get(i);
=======
// 	Retired Code: print out the colors read;
// 	public void printOutColors(HashMap<Integer, Picture> pictureLib) {
// 	for (Integer i : pictureLib.keySet()) {
//	Picture thisP = pictureLib.get(i);
//	System.out.print(thisP.getC1() + ",");
//	System.out.print(thisP.getC2() + ",");
// 	System.out.print(thisP.getC3() + ",");
// 	System.out.print(thisP.getC4() + ",");
// 	System.out.print(thisP.getC5() + ",");
// 	System.out.println(thisP.getC6());
// 	}
// 	}
>>>>>>> branch 'master' of https://github.com/JasmineYJ/MCIT591ColorScheme.git

<<<<<<< HEAD
			if (thisP.getC1() == userP.getC1() | thisP.getC1() == userP.getC2()) {
				count += 100;
			}

			if (thisP.getC2() == userP.getC2() | thisP.getC2() == userP.getC3()) {
				count += 80;
			}
			if (thisP.getC3() == userP.getC3() | thisP.getC3() == userP.getC4()) { // TODO: Add try-catch block for
																					// single color;
				count += 60;
			}
			if (thisP.getC4() == userP.getC4() | thisP.getC4() == userP.getC5()) { // TODO: Add try-catch block for
																					// single color;
				count += 40;
			}
			if (thisP.getC5() == userP.getC5()) { // TODO: Add try-catch block for single color;
				count += 20;
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
=======
//    	Method that write the resulted hash map to the CSV file.
//    	public void writeColorToCSV(HashMap<Integer, Picture> pictureLib) {
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
>>>>>>> branch 'master' of https://github.com/JasmineYJ/MCIT591ColorScheme.git

}
