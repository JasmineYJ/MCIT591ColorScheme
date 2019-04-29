/**
 * Each picture has 5 color names attach to it. Subject to change. But i think
 * even single color should have 5 colors? Black, white, light grey, dark grey
 * etc. TODO: should test out with single color pic && consider add more colors;
 * 
 * @author jasminejian
 */

public class Picture {
    private String c1;
    private String c2;
    private String c3;
    private String c4;
    private String c5;
    private String c6; //TODO: change back to 5
    private String pictureFileName;
    private ColorNameLibrary CML;
    private int numOfColor = 8; // TODO: remember to update this based on UI read; remeber to change it back, need to aggregate this input.
    private int roundOfIteration = 30; // TODO: change this number based on runtime and accuracy;

    public Picture(int picRef, ColorNameLibrary CNL) {
	
	pictureFileName = "Pictures/p" + picRef + ".jpg";  
	//System.out.println(pictureFileName); //TODO: to be deleted
	
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
	    String colorName = CNL.getColorName(rValue, gValue, bValue);
	    if (i1 == 1) {
		c1 = colorName;
	    }
	    if (i1 == 2) {
		c2 = colorName;
	    }
	    if (i1 == 3) {
		c3 = colorName;
	    }
	    if (i1 == 4) {
		c4 = colorName;
	    }
	    if (i1 == 5) {
		c5 = colorName;
	    }
	    if (i1 == 6) {
		c6 = colorName;
	    }
	    
	}
    } 

    // One picture constructor which takes value from Picture Library csv file. 
    public Picture(Integer i, String c1, String c2, String c3, String c4, String c5, String c6) {
	pictureFileName = "Pictures/p" + i + ".jpg";
	this.c1 = c1;
	this.c2 = c2;
	this.c3 = c3;
	this.c4 = c4;
	this.c5 = c5;
	this.c6 = c6;
    }
    
    
    
    // One picture constructor which takes the result from Main result of the user
    // input
    public Picture(int[][] userR) {
	int num = 0;

	for (int i1 = 0; i1 < numOfColor; i1++) {
	    int rValue = userR[i1][0];
	    int gValue = userR[i1][1];
	    int bValue = userR[i1][2];
	    String colorName = CML.getColorName(rValue, gValue, bValue);
	    if (i1 == 1) {
		c1 = colorName;
	    }
	    if (i1 == 2) {
		c2 = colorName;
	    }
	    if (i1 == 3) {
		c3 = colorName;
	    }
	    if (i1 == 4) {
		c4 = colorName;
	    }
	    if (i1 == 5) {
		c5 = colorName;
	    }
	    if (i1 == 6) {
		c6 = colorName;
	    }
 
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

    public String getC6() {
	return c6;
    }

    
    public String getPictureFileName() {
	return pictureFileName;
    }

}
