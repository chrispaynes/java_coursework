
import java.awt.Color;
import java.awt.Graphics;

public class SquareShape extends Shape {
  
  // private static Color color;
  
  public SquareShape(int x, int y, int width, int height, Color color) {
    super(x, y, width, height, color);
  }
  
  public SquareShape() {
    super();
  }
  
  @Override
  public void draw(Graphics g, Color n) {
    g.setColor(n);
    g.fillRect(getX(), getY(), getWidth(), getHeight());
  }
  
}
