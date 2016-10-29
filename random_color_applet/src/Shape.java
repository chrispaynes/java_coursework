import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape implements Randomize {
  private int   x;
  private int   y;
  private int   width;
  private int   height;
  private Color color;
  
  public Shape() {
    this(0, 0, 10, 10, Randomize.newRGB());
  }
  
  public abstract void draw(Graphics g, Color n);
  
  public Shape(int x, int y, int w, int h, Color c) {
    this.x = x;
    this.y = y;
    this.width = w;
    this.height = h;
    this.color = c;
  }
  
  public void setX(int x) {
    this.x = x;
  }
  
  public int getX() {
    return x;
  }
  
  public void setY(int y) {
    this.y = y;
  }
  
  public int getY() {
    return y;
  }
  
  public void setWidth(int w) {
    this.width = w;
  }
  
  public int getWidth() {
    return width;
  }
  
  public void setHeight(int h) {
    this.height = h;
  }
  
  public int getHeight() {
    return height;
  }
  
  public Color getColor() {
    return color;
  }
  
  public void setColor(Color color) {
    this.color = color;
  }
  
}
