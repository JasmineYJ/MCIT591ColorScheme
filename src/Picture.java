/**
 * I'm not exactly sure if i want to keep this. 
 * @author jasminejian
 *
 */

public class Picture {
    private String c1;
    private String c2;
    private String c3;
    private String c4;
    private String c5;
    private String pictureFileName;
    
    public Picture(String cOne, String cTwo, String cThree, String cFour, String cFive, int picRef) {
	c1 = cOne;
	c2 = cTwo;
	c3 = cThree;
	c4 = cFour;
	c5 = cFive;
	pictureFileName = "p"+picRef+".jpg";
    }
    
}
