import java.applet.Applet;
import java.awt.Graphics;

//http://www.javapractices.com/topic/TopicAction.do?Id=62
//http://stackoverflow.com/questions/26638620/java-add-rectangles-to-arraylist-and-then-draw-them

/*
 * Java applet that fills the applet's drawing area with square boxes and randomly chosen colors.
 * 
 */
public class RandomColor extends Applet {
  SquareShape box = new SquareShape(0, 0, 20, 20, Randomize.newRGB());
  
  public void init() {
  }
  
  public void paint(Graphics g) {
    
    int width = 100;
    int height = 100;
    
    this.setSize(width, height);
    
    for (int i = 0; i <= width; i += 10) {
      box.draw(g, Randomize.newRGB());
      box.setX(i);
      for (int j = 0; j <= width; j += 10) {
        box.draw(g, Randomize.newRGB());
        box.setY(j);
      }
    }
  }
  
}
