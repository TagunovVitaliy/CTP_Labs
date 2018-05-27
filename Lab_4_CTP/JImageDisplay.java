import javax.swing.JComponent;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Graphics;

public class JImageDisplay extends JComponent{
    private BufferedImage image;
    /**
     * @param width - Picture width
     * @param height - Picture height
     */
    JImageDisplay(int width, int height){
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        super.setPreferredSize(new Dimension(width, height));
    }
    /**
     * @param g - Displaying an image on the screen
     */
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
    }

    // Image cleanup
    public void clearImage(){
        int[] rgbArray = new int[image.getWidth()];
        image.setRGB(0, 0, image.getWidth()-1, image.getHeight()-1, rgbArray, 0, 0);
    }

    // Setting the color to pixel
    /**
     * @param x - The abscissa of the point
     * @param y - The ordinate of the point
     * @param rgbColor - The color of the point
     */
    public void drawPixel(int x, int y, int rgbColor){
        image.setRGB(x, y, rgbColor);
    }
}