package JavaChoice;

import java.applet.Applet;
import java.awt.Choice;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class JavaCoffeeMenuApplet extends Applet implements ItemListener {
  Choice                 option = null;
  CoffeeBlend            blend1, blend2, blend3, blend4, blend5;
  ArrayList<CoffeeBlend> blends = new ArrayList<CoffeeBlend>() {
                                  {
                                    add(new CoffeeBlend("BALI KINTAMANI HIGHLANDS", "Vienna Roast", 11.99,
                                        "Strawberry, Jackfruit, Boysenberry, Milk Chocolate, Pluot, Tangy"));
                                    add(new CoffeeBlend("DECAF NOCAF ESPRESSO BLEND", "Vienna Roast", 12.99,
                                        "Cane Sugar, Cocoa, Oak, Earthy, Malt, Mild, Lengthy Finish"));
                                    add(new CoffeeBlend("ETHIOPIA WASHED YIRGACHEFFE", "City Roast", 12.99,
                                        "Lemon-Lime, Bergamot, Orange, Clover Honey, Floral"));
                                    add(new CoffeeBlend("MEXICO CESMACH COOP", "City Plus Roast", 11.99,
                                        "Buttermilk, Lemon, Lavender, Rich, Molasses"));
                                    add(new CoffeeBlend("PAPUA NEW GUINEA", "Light Vienna Roast", 11.99,
                                        "Walnut, Apple Juice, Heavy Smooth, Dark Rum"));
                                  }
                                };
  
  public void init() {
    option = new Choice();
    
    for (CoffeeBlend coffeeBlend : blends) {
      option.add(coffeeBlend.getName());
    }
    
    add(option);
    
    option.addItemListener(this);
    
  }
  
  public void paint(Graphics g) {
    int WIDTH = 275;
    g.setFont(new Font("Arial", Font.BOLD, 16));
    g.drawImage(blends.get(option.getSelectedIndex()).getThumbnail(), 10, 10, null);
    g.drawString(option.getSelectedItem() + ":\t$" + blends.get(option.getSelectedIndex()).getPrice(), WIDTH, 90);
    g.drawString(Integer.toString(option.getItemCount()) + " Coffee Blends Available", WIDTH, 50);
    g.drawString(blends.get(option.getSelectedIndex()).getRoastLevel(), WIDTH, 110);
    g.drawString(blends.get(option.getSelectedIndex()).getDescription(), WIDTH, 130);
  }
  
  @Override
  public void itemStateChanged(ItemEvent e) {
    repaint();
  }
  
}
