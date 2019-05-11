
import java.awt.Color;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JFrame;

/**
 * This class is utilizing K-means algorithm to calculate the top N colors for
 * an given image. The input information will be the integer array which stores
 * every pixels' RGB values from user's image, the total number of pixels in
 * this image, as well as the integer N, which is input by user. The output will
 * be a 2D integer array, with N rows, 3 columns, each column holds the value of
 * R, G, B.
 */

public class KmeansCalculator {
	private int totalPixels;
	private int numOfCenters;
	private int[][] imageRGBs;

	/**
	 * This is the constructor for this class, transfer the user input N, number of
	 * total pixels into the class, also put every pixel's RGB values into a 2d
	 * integer array
	 */
	public KmeansCalculator(int totalPixels, int numOfCenters, int[][] inputRGBs) {
		this.totalPixels = totalPixels;
		this.numOfCenters = numOfCenters;
		imageRGBs = inputRGBs;
	}

	/**
	 * This is the method to generate the initial center point for each cluster,
	 * it's generated by randomly selecting N colors and put into a 2d integer
	 * array. Before put each initial color, check if there is already a same color
	 * in the array, if there is, replace it with another distance color found in
	 * the image.
	 */
	public int[][] firstPathCenter() {
		int[] firstCenter = new int[numOfCenters];
		int random;

		Random r = new Random();
		for (int ii = 0; ii < numOfCenters; ii++) {
			random = r.nextInt(totalPixels);
			firstCenter[ii] = random;
			ii++;
		}
		HashMap<Integer, Integer> centerColor = new HashMap<Integer, Integer>();
		int[] singleRGB;
		Color currentColor;
		int RGB;
		boolean sameColorExist = true;
		/**
		 * put the selected color, which associated with the random integer, into a
		 * HashMap. If a same color already exist in the HashMap, then need to find
		 * another distinct color
		 */
		for (int i = 0; i < numOfCenters; i++) {
			singleRGB = new int[3];
			singleRGB[0] = imageRGBs[firstCenter[i]][0];
			singleRGB[1] = imageRGBs[firstCenter[i]][1];
			singleRGB[2] = imageRGBs[firstCenter[i]][2];
			currentColor = new Color(singleRGB[0], singleRGB[1], singleRGB[2]);
			RGB = currentColor.getRGB();

			if (!centerColor.containsValue(RGB)) {
				centerColor.put(i, RGB);
			} else {
				while (sameColorExist) {
					for (int j = 0; j < totalPixels; j++) {
						singleRGB = new int[3];
						singleRGB[0] = imageRGBs[j][0];
						singleRGB[1] = imageRGBs[j][1];
						singleRGB[2] = imageRGBs[j][2];
						currentColor = new Color(singleRGB[0], singleRGB[1], singleRGB[2]);
						RGB = currentColor.getRGB();
						if (!centerColor.containsValue(RGB)) {
							centerColor.put(i, RGB);
							firstCenter[i] = j;
							sameColorExist = false;
						}
					}
				}
			}
		}
		int[][] firstCenters = new int[numOfCenters][3];
		for (int k : centerColor.keySet()) {
			firstCenters[k][0] = imageRGBs[firstCenter[k]][0];
			firstCenters[k][1] = imageRGBs[firstCenter[k]][1];
			firstCenters[k][2] = imageRGBs[firstCenter[k]][2];
		}
		return firstCenters;
	}

	/**
	 * This is the method to generate the RGB value pattern. Label the max value
	 * from RGB as 3, the min value as 1, and the medium value as 2.
	 */
	public int[] getRGBPattern(int[] rgb) {
		int[] RGBPattern = new int[3];
		int[] rgbCopy = new int[3];
		for (int i = 0; i < 3; i++) {
			rgbCopy[i] = rgb[i];
		}
		Arrays.sort(rgb);
		for (int i = 0; i < 3; i++) {
			int temp = rgbCopy[i];
			for (int j = 0; j < 3; j++) {
				if (temp == rgb[j]) {
					RGBPattern[i] = j + 1;
				}
			}
		}
		return RGBPattern;
	}

	/**
	 * This Method label each pixel based on their "color Euclidean distance" from
	 * the cluster centers, as well as their "pattern distance" from the center
	 * color. The "pattern distance" is utilized to get the distinct color from an
	 * image, where this distinct color has a small portion; also used to minimize
	 * the chance of getting a grey color. So each pixel will be associated with a
	 * color it's closest to
	 */
	public int[] lablePixels(int[][] centers) {
		int[] label = new int[totalPixels];
		double minDistance;
		for (int i = 0; i < totalPixels; i++) {
			int labelInt = 0;
			minDistance = 100.00 * (256.0 * 256.0);
			int[] target = new int[3];
			target[0] = imageRGBs[i][0];
			target[1] = imageRGBs[i][1];
			target[2] = imageRGBs[i][2];
			int[] targetPattern = getRGBPattern(target);
			for (int j = 0; j < numOfCenters; j++) {
				int[] centerColor = new int[3];
				centerColor[0] = centers[j][0];
				centerColor[1] = centers[j][1];
				centerColor[2] = centers[j][2];
				int[] centerPattern = getRGBPattern(centerColor);
				/**
				 * get the distance between this two points
				 */
				int temp = 0;
				int distance = 0;
				for (int x = 0; x < 3; x++) {
					temp = (int) Math.pow((target[x] - centerColor[x]), 2)
							+ (int) Math.pow((targetPattern[x] - centerPattern[x]), 2) * 25000;
					distance = distance + temp;
				}
				/**
				 * find the nearest center point
				 */
				if (distance < minDistance) {
					minDistance = distance;
					labelInt = j;
				}
			}
			label[i] = labelInt;
		}
		return label;
	}

	/**
	 * This method is to re-calculate(update) the center of each cluster, after each
	 * cluster is formed or updated
	 */
	public int[][] calculateCenter(int[] label) {
		int[][] newCenter = new int[numOfCenters][3];
		for (int i = 0; i < numOfCenters; i++) {
			int count = 0;
			double[] calculate = new double[3];
			calculate[0] = calculate[1] = calculate[2] = 0.00;
			/**
			 * loop through all the pixels
			 */
			for (int j = 0; j < totalPixels; j++) {
				if (label[j] == i) {
					calculate[0] = calculate[0] + imageRGBs[j][0];
					calculate[1] = calculate[1] + imageRGBs[j][1];
					calculate[2] = calculate[2] + imageRGBs[j][2];
					count = count + 1;
				}
			}
			/**
			 * divide the sum of coordinates by the number of points, thus the mean value of
			 * the points is created
			 */
			newCenter[i][0] = (int) (calculate[0] / count);
			newCenter[i][1] = (int) (calculate[1] / count);
			newCenter[i][2] = (int) (calculate[2] / count);
		}
		return newCenter;
	}

	/**
	 * This method put every method needed for calculation together;
	 */
	public int[][] calculateColor() {
		int[][] firstCenter = firstPathCenter();
		int[] label = lablePixels(firstCenter);
		int i = 0;
		int[][] result = new int[numOfCenters][3];

		/**
		 * int iterationRounds controls how many rounds of iteration in the calculation
		 * process; The bigger int iterationRounds is, the more accurate the result
		 * would be, but at the same time, the longer the run time would be. The int
		 * iteratoinRounds here is dependent on the size of the image which is to be
		 * calculated;
		 */
		int iterationRounds = 0;

		if (totalPixels < 1000 * 1000) {
			iterationRounds = 15;
		} else if (totalPixels >= 1000 * 1000 && totalPixels <= 3000 * 3000) {
			iterationRounds = 8;
		} else {
			iterationRounds = 3;
		}

		while (i < iterationRounds) {
			result = calculateCenter(label);
			label = lablePixels(result);
			i++;
		}
		return result;
	}

	public static void main(String[] args) {
		/**
		 * Here is to test the k-means calculator; int color is the int N (top N color)
		 */
		int topN = 5;
		/**
		 * construct a new ImageReading instance to read image
		 */
		ImageReading image = new ImageReading("test_image_2.jpg");
		int[][] testI = image.getImageRGB();
		int pixelNum = image.getPixelNum();

		KmeansCalculator k = new KmeansCalculator(pixelNum, topN, testI);

		int[][] result = k.calculateColor();

		/**
		 * construct a rectangle to display the calculated color.
		 */
		ColorRectangle rects = new ColorRectangle(result, 180, 400, topN);
		JFrame frame = new JFrame("Rectangles");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(rects);
		frame.setSize(360, 300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
