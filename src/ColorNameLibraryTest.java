import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ColorNameLibraryTest {

    /**
     * Test that the correct color name is given for sample R,G,B values
     */

    @Test
    void testGetColorName() {

	ColorNameLibrary CML = new ColorNameLibrary();
	String colorName1 = CML.getColorName(127, 255, 212); // should be Pale Green
	String colorName2 = CML.getColorName(176, 196, 222); // should be Orchid
	String colorName3 = CML.getColorName(250, 240, 230); // should be Hot Pink

	assertTrue(colorName1.equals("Pale Green"));
	assertTrue(colorName1.equals("Orchid"));
	assertTrue(colorName1.equals("Hot Pink"));

    }

    /**
     * Test the library has the correct number of record;
     */
    
    @Test
    void testGetColorNames() {
	ColorNameLibrary CML = new ColorNameLibrary();
	assertTrue(CML.getColorNames().size() == 140);
    }

}
