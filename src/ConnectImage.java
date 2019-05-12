import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javafx.scene.control.TableView.ResizeFeatures;
/*
 * This class is used to draw output image to connect color bar 
 * and recommended image, which will simply design interface 
 */

public class ConnectImage {
      private int topN;           //topN comes from the user 
      BufferedImage finalImage ;
	
	public BufferedImage getFinalImage() {
		return finalImage;
	}

/*
 * Here, input is user entered number of color wish to see and address of 
 * Recommend image. 
 * 
 *  This method connect image (output from ColorNameLibrary) and Color bar.
 *
 */
	public ConnectImage(int topN, String add) {
		
		// pass parameters' value 
		this.topN = topN;             
		String nameString = add;
		
		// read image 
	    ImageReading image = new ImageReading(nameString);
	    
	   //  read RGB and pixel value
		int[][] testI = image.getImageRGB();
		int pixelNum = image.getPixelNum();
		
		// get buffered image 
		BufferedImage originalImage = image.getImage();
		
		// find width and height 
		int width = originalImage.getWidth();
		int height = originalImage.getHeight();
		
		// Given the recommend image, find the topN color in the recommend image
		KmeansCalculator k = new KmeansCalculator(pixelNum, topN, testI);
        
		/**
		 * this int[][] result stores the top N color's RGB value. Each row is for one
		 * color 
		 */
		 int[][] result = k.calculateColor();
		
		 
		String[] colorNames = new String[topN];
		
		String tempName;
		ColorNameLibrary cN = new ColorNameLibrary();
		
		for (int i = 0; i < topN; i++) {
			tempName = cN.getColorName(result[i][0], result[i][1], result[i][2]);
			colorNames[i] = tempName;}
		
		

		// draw color bar image  where bar is vertically allocated
	   DrawColorOfPicture drawColorOfPicture = new DrawColorOfPicture(result, colorNames, topN, false);
	   BufferedImage colorImage = drawColorOfPicture.getProcessedImage();
	   BufferedImage resizedColorImage = drawColorOfPicture.resize(colorImage, width, height/3);
	   // connect input image and color bar image 
	   finalImage = connectBufferedImage(originalImage, resizedColorImage);
						
				
	}
	
	/*
	 * This method is used to connect two image where put one of them (image1) at top position
	 * and another (image 2) at bottom position.
	 */
	
	
	public BufferedImage connectBufferedImage (BufferedImage image1, BufferedImage image2){
		
		int imageWidth = image1.getWidth();
		int image1Height = image1.getHeight();
		int image2Height = image2.getHeight();
		
		BufferedImage target= new BufferedImage(imageWidth, image1Height+image2Height+15, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) target.getGraphics();
		
		g.drawImage(image1,0,0,null);
		g.drawImage(image2,0,image1Height+15,null);
		
		g.dispose();
		
		return target;
		
	}
	
	
	
	
}
	
	
	
	
	
	
	
	

