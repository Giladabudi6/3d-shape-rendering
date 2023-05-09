package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;


public class ImageWriterTest {


    @Test
    void writeToImage() {

        ImageWriter imageWriter = new ImageWriter("yellow", 800, 500);

        for (int i = 0; i < imageWriter.getNx(); i++) {
            for (int j = 0; j < imageWriter.getNy(); j++) {
                imageWriter.writePixel(i, j, new Color(java.awt.Color.yellow));
            }
        }

        for (int i = 0; i < imageWriter.getNx(); i+=50) {
            for (int j = 0; j < imageWriter.getNy(); j+=50) {
                imageWriter.writePixel(i, j, new Color(java.awt.Color.BLUE));
            }
        }

        imageWriter.writeToImage();

    }
}
