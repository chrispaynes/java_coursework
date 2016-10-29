package random_color_applet;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape implements Randomize {
  private int       x;
  private int       y;
  private final int width;
  private final int height;
  private Color     color;
  
  public Shape() {
    this(10, 10);
  }
  
  public Shape(int w, int h) {
    this.x = 0;
    this.y = 0;
    this.width = w;
    this.height = h;
    this.color = Randomize.newRGB();
  }
  
  public abstract void draw(Graphics g, Color n);
  
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
  
  public int getWidth() {
    return width;
  }
  
  public int getHeight() {
    return height;
  }
  
  public void setColor(Color color) {
    this.color = color;
  }
  
}
