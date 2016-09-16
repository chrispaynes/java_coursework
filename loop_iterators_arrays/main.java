import java.util.ArrayList;
import java.util.Iterator;

class Main {
  public static void main(String[] args) {
    
    // creates ArrayList of strings to hold planet names
    ArrayList<String> planets = new ArrayList<String>();
    
    // creates integer iterator for the While loop
    int indexNum = 0;
    
    // adds individual planets to the planets ArrayList
    planets.add("Mercury");
    planets.add("Venus");
    planets.add("Earth");
    planets.add("Mars");
    planets.add("Jupiter");
    planets.add("Saturn");
    planets.add("Uranus");
    planets.add("Neptune");
    planets.add("Pluto");

    System.out.println("*LOOP USING A ITERATOR OBJECT*");
    /** uses Iterator object to loop through arrays:
      * as long as the collection has another element
      * after the current element, the iterator will
      * call next() continue cycling through the collection
      */
    Iterator planetItr = planets.iterator();
    while(planetItr.hasNext()) {
      Object planetElement = planetItr.next();
      System.out.println(planetElement);
    }
    
     System.out.println("\n *LOOP USING A FOR EACH LOOP*");
  
    /** uses the For Loop operator to cycle through
      * the planets ArrayList and print out each planet.
      * The number of times this loop loops is equivalent
      * to the planets ArrayList size
      */
    for(String planet : planets ) {
       System.out.println( planet );
    }

    System.out.println("\n *LOOP USING A WHILE LOOP*");
    
    /** uses the While Loop operator to cycle through
      * the planets ArrayList and print out each planet.
      * This loop uses an indexNum variable initialized
      * at 0. During each cycle, indexNum is incremented by 1.
      * While indexNum is less than the planets
      * ArrayList size, the loop prints out the element
      * matching the current indexNum within the
      * planets ArrayList.
      */
    while(indexNum < planets.size() ) {
      System.out.println(planets.toArray()[indexNum]);
      indexNum++;
    }      
  }
}
