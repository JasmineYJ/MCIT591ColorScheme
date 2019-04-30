import java.util.Arrays;

import com.sun.webkit.ThemeClient;

public class SystemOutput {

	public static void main(String[] args) {

		/**
		 * int color is the int N (top N color)
		 */
		int topN = 6; //TODO: change back to 5.
		/**
		 * construct a new ImageReading instance to read image
		 */
		ImageReading image = new ImageReading("test_image.jpg");
		int[][] testI = image.getImageRGB();
		int pixelNum = image.getPixelNum();

		KmeansCalculator k = new KmeansCalculator(pixelNum, topN, testI);

		/**
		 * this int[][] result stores the top N color's RGB value. Each row is for one
		 * color 
		 */
		int[][] result = k.calculateColor();
		// the following few lines are for test only
//		for (int i = 0; i < topN; i++) {
//		System.out.print(result[i][0] + ",");
//		System.out.print(result[i][1] + ",");
//		System.out.print(result[i][2]);
//		System.out.println();
//	}

		/**
		 * the following block is to get name for each color in the int[][] result
		 */
		String[] colorNames = new String[topN];
		
		String tempName;
		ColorNameLibrary cN = new ColorNameLibrary();
		
		
		
		for (int i = 0; i < topN; i++) {
			tempName = cN.getColorName(result[i][0], result[i][1], result[i][2]);
<<<<<<< HEAD
=======
			//System.out.println("The color " + i + " is "+ result[i][0]+","+result[i][1]+","+result[i][2]);
			//System.out.println(tempName);
>>>>>>> branch 'master' of https://github.com/JasmineYJ/MCIT591ColorScheme.git
			colorNames[i] = tempName;
		}
		// the following few lines are for test only
		for(String s: colorNames) {
			//System.out.println(s);
		}
		System.out.println("The color results are "+Arrays.toString(colorNames)+".");//TODO: Remeber to delete
		System.out.println("Compiling the 203 picture library. May take 10-15 Min."); // TODO: Remeber to delete
		ColorPictureLibrary cP = new ColorPictureLibrary(cN);
		
		// the following lines are used for test the return of similar pic;
		System.out.println(cP.similarPic(colorNames));

	}

}
