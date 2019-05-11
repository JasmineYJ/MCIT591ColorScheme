import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class KmeansCalculatorTest {

	@Test
	void testFirstPathCenter() {
		/**
		 * "test_image.jpg" is the name of the image used for testing the test image is
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
		int[] testColor = { 212, 180, 196 };
		int pixelNum = i.getPixelNum();
		KmeansCalculator k = new KmeansCalculator(pixelNum, 5, testI);
		int[] pattern = k.getRGBPattern(testColor);
		assertEquals(pattern[0], 3);
		assertEquals(pattern[1], 1);
		assertEquals(pattern[2], 2);
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
}
