import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ColorNameLibraryTest {

    @Test
    void testGetColorName() {
	/**
	 * Test that the correct color name is given for sample R,G,B values
	 */
	ColorNameLibrary CML = new ColorNameLibrary();
	
	String colorName1 = CML.getColorName(127, 255, 212); // should be Pale Green
	String colorName2 = CML.getColorName(176, 196, 222); // should be Orchid
	String colorName3 = CML.getColorName(250, 240, 230); // should be Hot Pink
	assertTrue(colorName1.equals("Pale Green"));
	assertTrue(colorName1.equals("Orchid"));
	assertTrue(colorName1.equals("Hot Pink"));
	
    }

    @Test
    void testColorNameLibrary() {
	ColorNameLibrary CML = new ColorNameLibrary();
	assertTrue(CML.getColorName().size()==140);
    }

}
