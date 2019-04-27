
public class SystemOutput {

	public static void main(String[] args) {

		/**
		 * int color is the int N (top N color)
		 */
		int topN = 5;
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
			tempName = cN.getColorName(result[i][0], result[i][0], result[i][0]);
			colorNames[i] = tempName;
			// this line is for test only
			System.out.println(tempName);
		}

	}

}
