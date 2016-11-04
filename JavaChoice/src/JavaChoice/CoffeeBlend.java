package JavaChoice;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CoffeeBlend {
  private final String  name;
  private final String  roastLevel;
  private final Double  price;
  private final String  description;
  private BufferedImage thumbnail;
  
  public CoffeeBlend(String name, String roastLevel, Double price, String description) {
    this.name = name;
    this.roastLevel = roastLevel;
    this.price = price;
    this.description = description;
  }
  
  public String getName() {
    return name;
  }
  
  public String getDescription() {
    return description;
  }
  
  public Double getPrice() {
    return price;
  }
  
  public String getRoastLevel() {
    return roastLevel;
  }
  
  private void loadThumbnail() {
    try {
      this.thumbnail = ImageIO.read(new File("../resources/" + name.replace(" ", "-") + ".jpg"));
    } catch (IOException e) {
    }
  }
  
  public BufferedImage getThumbnail() {
    loadThumbnail();
    return thumbnail;
  }
  
}