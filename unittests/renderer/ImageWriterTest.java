package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;


public class ImageWriterTest {


    @Test
    void writeToImage() {

        ImageWriter imageWriter = new ImageWriter("yellow", 801, 501);

        for (int i = 0; i < imageWriter.getNx(); i++) {
            for (int j = 0; j < imageWriter.getNy(); j++) {
                Color backgroundColor = new Color(java.awt.Color.yellow);
                imageWriter.writePixel(i, j, backgroundColor);
                if (i % 50 == 0 || j % 50 == 0) {
                    Color linesColor = new Color(java.awt.Color.BLUE);
                    imageWriter.writePixel(i, j, linesColor);
                }
            }
        }
        imageWriter.writeToImage();

    }
}
