package random_color_applet;

import java.awt.Color;
import java.awt.Graphics;

public class Square extends Shape {
  
  /**
   * Instantiates a new square with dimensions.
   *
   * @param length
   *          the pixel length of one side
   */
  public Square(int length) {
    super(length, length);
  }
  
  public Square() {
    super();
  }
  
  @Override
  public void draw(Graphics g, Color c) {
    g.setColor(c);
    g.fillRect(getX(), getY(), getWidth(), getHeight());
  }
  
}
