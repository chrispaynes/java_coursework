import java.awt.Color;
import java.util.Random;

public interface Randomize {
  
  public static int newInteger(int bounds) {
    return new Random().nextInt(bounds);
  }
  
  public static Color newRGB() {
    int r, g, b;
    r = newInteger(255);
    g = newInteger(255);
    b = newInteger(255);
    
    return new Color(r, g, b);
  }
}
