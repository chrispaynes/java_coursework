package random_color_applet;

import java.util.ArrayList;

public final class Row {
  private int                     index;
  private final int               length;
  private final int               dimension;
  private final ArrayList<Square> collection;
  
  public Row(int dimension, int length, int index) {
    this.length = length;
    this.index = index;
    this.dimension = dimension;
    this.collection = randomizeBoxes(length, index);
  }
  
  private ArrayList<Square> randomizeBoxes(int l, int index) {
    final ArrayList<Square> collection = new ArrayList<Square>();
    
    for (int i = 0; i <= length; i += dimension) {
      final Square box = new Square(dimension);
      collection.add(box);
      box.setX(i);
      box.setY(this.index);
    }
    
    return collection;
  }
  
  public ArrayList<Square> getCollection() {
    return collection;
  }
  
  public void setIndex(int i) {
    this.index = i;
  }
  
}
