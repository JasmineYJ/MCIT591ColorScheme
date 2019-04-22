/**
 * Each picuture has 5 color names attach to it.
 * @author jasminejian
 */

public class Picture {
    private String c1;
    private String c2;
    private String c3;
    private String c4;
    private String c5;
    private String pictureFileName;
    private ColorNameLibrary CML;
    private int numOfColor = 5; // TODO: remember to update this based on UI read;
    private int roundOfIteration = 30; // TODO: change this number based on runtime and accuracy;

    public Picture(int picRef, ColorNameLibrary CML) {
	pictureFileName = "p" + picRef + ".jpg";

	ImageReading reader = new ImageReading(pictureFileName);
	int[][] colorResult = reader.getImageRGB();
	int pixelNum = reader.getPixelNum();
	KmeansCalculator kC = new KmeansCalculator(pixelNum, numOfColor, colorResult);

	int[][] firstCenter = kC.firstPathCenter();
	int[] label = kC.lablePixels(firstCenter);

	// int[][] is the result containing the top N colors. Each row stores a color
	int[][] result = new int[numOfColor][3];

	int num = 0;
	while (num < roundOfIteration) {
		result = kC.calculateCenter(label);
		label = kC.lablePixels(result);
		num++;
	}
	
	for (int i1 = 0; i1 < numOfColor; i1++) {
	    int rValue = result[i1][0];
	    int gValue = result[i1][1];
	    int bValue = result[i1][2];
	    String colorName = CML.getColorName(rValue, gValue, bValue);
	    if (i1==1) {c1=colorName;}
	    if (i1==2) {c2=colorName;}
	    if (i1==3) {c3=colorName;}
	    if (i1==4) {c4=colorName;}
	    else {c5=colorName;}
	}
    }

    public String getC1() {
        return c1;
    }

    public String getC2() {
        return c2;
    }

    public String getC3() {
        return c3;
    }

    public String getC4() {
        return c4;
    }

    public String getC5() {
        return c5;
    }

    public String getPictureFileName() {
        return pictureFileName;
    }

    
}
