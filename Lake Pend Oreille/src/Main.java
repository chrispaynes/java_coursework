import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
  
  public static ArrayList<Double> dataColumn(ArrayList<Double> list, int index, List<String[]> records) {
    for (String[] strings : records) {
      list.add(Double.parseDouble(strings[index]));
    }
    return list;
  }
  
  public static ArrayList<Double> getData(String type) throws IOException {
    ArrayList<Double> windData, airData, pressureData;
    List<String[]> records;
    String inputLine;
    URL dataSource;
    BufferedReader dataStream;
    
    dataSource = new URL(
        "https://raw.githubusercontent.com/lyndadotcom/LPO_weatherdata/master/Environmental_Data_Deep_Moor_2015.txt");
    
    // Creates input stream by connecting to the dataSource URL.
    dataStream = new BufferedReader(new InputStreamReader(dataSource.openStream()));
    
    // Constructs empty list with an initial capacity of ten.
    windData = new ArrayList<Double>();
    airData = new ArrayList<Double>();
    pressureData = new ArrayList<Double>();
    records = new ArrayList<String[]>();
    
    // Appends each text line from the dataStream to windData.
    @SuppressWarnings("unused")
    String header = dataStream.readLine();
    while ((inputLine = dataStream.readLine()) != null) {
      records.add(inputLine.substring(20).split("\\t"));
    }
    
    dataStream.close();
    
    if (type == "Wind_Gust") {
      return dataColumn(windData, 5, records);
    } else if (type == "Air_Temp") {
      return dataColumn(airData, 0, records);
    } else {
      return dataColumn(pressureData, 1, records);
    }
    
  }
  
  public static String mean(ArrayList<Double> a) {
    double sum = 0;
    // Sets DecimalFormat to round down.
    DecimalFormat df = new DecimalFormat();
    df.setRoundingMode(RoundingMode.DOWN);
    
    for (double i : a) {
      sum += i;
    }
    
    return df.format(sum / a.size());
  }
  
  public static double median(ArrayList<Double> a) {
    Collections.sort(a);
    int halfLength = a.size() / 2;
    
    // Sets DecimalFormat to round down.
    DecimalFormat df = new DecimalFormat();
    df.setRoundingMode(RoundingMode.DOWN);
    
    if (halfLength * 2 == a.size()) {
      // Return the average of the two middle double.
      return (a.get(halfLength) + a.get(halfLength - 1)) / 2;
      // Return the middle double.
    } else {
      return a.get(halfLength);
    }
  }
  
  public static void main(String[] args) throws NumberFormatException, IOException {
    ArrayList<Double> windGust, airTemp, pressure;
    
    windGust = getData("Wind_Gust");
    airTemp = getData("Air_Temp");
    pressure = getData("Barametric_Press");
    
    System.out.println("WIND GUST: mean " + mean(windGust) + ", median " + median(windGust));
    System.out.println("AIR TEMP: mean " + mean(airTemp) + ", median " + median(airTemp));
    System.out.println("BAROMETRIC PRESSURE: mean " + mean(pressure) + ", median " + median(pressure));
  }
  
}
