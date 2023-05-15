package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;


public class ImageWriterTest {


    @Test
    void writeToImage() {

        ImageWriter imageWriter = new ImageWriter("yellow", 801, 501);

        for (int i = 0; i < imageWriter.getNx(); i++) {
            for (int j = 0; j < imageWriter.getNy(); j++) {
                imageWriter.writePixel(i, j, new Color(java.awt.Color.yellow));
                if (i % 50 == 0 || j % 50 == 0)
                    imageWriter.writePixel(i, j, new Color(java.awt.Color.BLUE));

            }
        }
        imageWriter.writeToImage();

    }
}
