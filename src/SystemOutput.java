
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
		 * this int[][] stores the top N color's RGB value. Each row is for one color
		 */
		int[][] result = k.calculateColor();
		
	}

	
    
}
