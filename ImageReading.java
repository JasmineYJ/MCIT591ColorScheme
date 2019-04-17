import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;


public class ImageReading {

	private BufferedImage image = null;
	private String address;

	public ImageReading(String add) {
		 address = add;
		 
		 try {
			 File inputFile = new File(address);
				image = ImageIO.read(inputFile);		
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		 
}
	

	 
public BufferedImage getImage() {
		return image;
	}



	public void setImage(BufferedImage image) {
		this.image = image;
	}



public int[][][]  getImageRGB() {
	
	int width = image.getWidth();
    int height = image.getHeight();
    
    int[][][] imageRGB =   new int[width][height][3];
	
    for(int i = 0;  i < width; i++) {
		 for(int j = 0; j < height; j++) {
			  
			 int p = image.getRGB(i, j);
			 
			 // get red
			 int r = (p >> 8) & 0xff;
			 imageRGB[i][j][0] = r;
			 
			 //get green
			 int g = (p >>16 ) & 0xff;
			 imageRGB[i][j][1] = g;
			 
			 //get blue
			 int b = p & 0xff;
			 imageRGB[i][j][2] = b;
}
	
    }
    return imageRGB;
}


}