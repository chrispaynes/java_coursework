package random_color_applet;

import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 * The Class RandomColor creates a Java applet that fills the applet's drawing area with square boxes and
 * randomly chosen colors.
 * 
 */
public class RandomColor extends Applet {
  
  /**
   * Creates a table of squares and fills each square with random colors.
   *
   * @param g the graphics object
   * @param width the applet window's pixel width
   * @param height the applet window's pixel height
   * @param squareSize the pixel size for one side of the square
   */
  public final void createTable(Graphics g, int width, int height, int squareSize) {
    for (int i = 0; i <= height; i += squareSize) {
      Row row = new Row(squareSize, width, i);
      for (Square s : row.getCollection()) {
        s.draw(g, Randomize.newRGB());
      }
      row.setIndex(squareSize);
    }
  }
  
  public void init() {
  }
  
  /*
   * Paints the table to the applet window based on its dimensions.
   * 
   */
  public void paint(Graphics g) {
    final Dimension appletSize = this.getSize();
    final int appletHeight = appletSize.height;
    final int appletWidth = appletSize.width;
    final int squareSize = 20;
    
    createTable(g, appletWidth, appletHeight, squareSize);
    
  }
  
}
