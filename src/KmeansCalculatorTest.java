import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class KmeansCalculatorTest {

	@Test
	void testFirstPathCenter() {
		/**
		 * "test_image.jpg" is the name of the image used for testing
		 * the test image is 453 x 777 pixels
		 */
		ImageReading i = new ImageReading("test_image.jpg");
		int[][] testI = i.getImageRGB();
		int pixelNum = i.getPixelNum();
		KmeansCalculator k = new KmeansCalculator(pixelNum, 5, testI);
		int[][] firstCenter = k.firstPathCenter();
		for (int x = 0; x < firstCenter.length; x++) {
			for (int y = 0; y < 3; y++) {
				int value = firstCenter[x][y];
				assertTrue("Out of color max value", 256 >= value);
				assertTrue("Out of color min value", 0 <= value);
			}
		}
	}

	@Test
	void testLablePixels() {
		/**
		 * "test_image.jpg" is the name of the image used for testing
		 */
		ImageReading i = new ImageReading("test_image.jpg");
		int[][] testI = i.getImageRGB();
		int pixelNum = i.getPixelNum();
		KmeansCalculator k = new KmeansCalculator(pixelNum, 5, testI);
		int[][] firstCenter = k.firstPathCenter();
		int[] label = k.lablePixels(firstCenter);
		for (int x = 0; x < pixelNum; x++) {
			int value = label[x];
			assertTrue("Wrong label", 5 >= value);
			assertTrue("Wrong label", 0 <= value);
		}
	}

	@Test
	void testCalculateCenter() {
		/**
		 * "test_image.jpg" is the name of the image used for testing
		 */
		ImageReading i = new ImageReading("test_image.jpg");
		int[][] testI = i.getImageRGB();
		int pixelNum = i.getPixelNum();
		KmeansCalculator k = new KmeansCalculator(pixelNum, 5, testI);
		int[][] firstCenter = k.firstPathCenter();
		int[] label = k.lablePixels(firstCenter);
		int[][] result = new int[5][3];
		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 3; y++) {
				int value = firstCenter[x][y];
				assertTrue("Out of color max value", 256 >= value);
				assertTrue("Out of color min value", 0 <= value);
			}
		}
	}
	
	@Test
	void testGetRGBPattern() {
		/**
		 * "test_image.jpg" is the name of the image used for testing
		 */
		ImageReading i = new ImageReading("test_image.jpg");
		int[][] testI = i.getImageRGB();
		int pixelNum = i.getPixelNum();
		KmeansCalculator k = new KmeansCalculator(pixelNum, 5, testI);
		int[] testColor = {202,135,200};
		int[] label = k.getRGBPattern(testColor);
		assertEquals(label[0],3);
		assertEquals(label[1],1);
		assertEquals(label[2],2);
	}
	
	@Test
	void testCalculateColor() {
		/**
		 * "test_image.jpg" is the name of the image used for testing
		 */
		ImageReading i = new ImageReading("test_image.jpg");
		int[][] testI = i.getImageRGB();
		int pixelNum = i.getPixelNum();
		KmeansCalculator k = new KmeansCalculator(pixelNum, 5, testI);
		int[][] result = k.calculateColor();
		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 3; y++) {
				int value = result[x][y];
				assertTrue("Out of color max value", 256 >= value);
				assertTrue("Out of color min value", 0 <= value);
			}
		}
	}
}
