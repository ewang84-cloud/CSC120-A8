/* This is a stub for the Library class */

import java.util.Hashtable;

public class Library extends Building implements LibraryRequirements{

  private Hashtable<String, Boolean> collection;
  private boolean hasElevator;

  /**
   * Constructor of the Library Class
   * @param name name of the library
   * @param address address of the library
   * @param nFloors how many floors in this library
   */
  public Library(String name, String address, int nFloors, boolean hasElevator) {
    super(name, address, nFloors);
    this.collection = new Hashtable<String, Boolean>();
    this.hasElevator = hasElevator;
    System.out.println("You have built a library: 📖");
  }



  /**
   * Method that adds a title into the collection
   * @param title the one that we wants to add
   */
  @Override
  public void addTitle(String title) {
    if (collection.containsKey(title)) {
        System.out.println("This book is already in the library.");
    } else {
        collection.put(title, true); // true means it's available
        System.out.println(title + " has been added to the library.");
    }
  }



  /**
   * method that removes a title from the collection
   * @param title the title that we wants to remove
   * @return returning the title no matter what happens
   */
  @Override
  public String removeTitle(String title){
    if (collection.containsKey(title)){
      collection.remove(title);
    }
    else{
      System.out.println("This title is not in the collection, cannot be removed");
    }
    return title;
  }



  /**
   * method that could check out a book from the library
   * @param title the title we're checking it out
   */
  @Override
  public void checkOut(String title){
    if(collection.containsKey(title)){
      if(collection.get(title) == true){
        collection.replace(title, false);
        System.out.println("Successfully checked out! Thanks");
      }
      else{
        System.out.println("this book is not available for check out");
      }
    }
    else{
      System.out.println("This book is not in the library's collection");
    }
  }



  /**
   * Method that returns the book to the library
   * @param title the title we're returning to the library
   */
  @Override
  public void returnBook(String title){
    if(collection.containsKey(title)){
      if(collection.get(title) == false){
        collection.replace(title, true);
        System.out.println("Successfully returned! Thanks");
      }
      else{
        System.out.println("This book is already in the collection, check again");
      }
    }
    else{
      System.out.println("This book is not in the library's collection, cannot be returned");
    }
  }



  /**
   * method that will check if the library collection contains the title or not
   * @param title the title that we're chekcing if it's in the collection or not
   */
  @Override
  public boolean containsTitle(String title){
    if (collection.containsKey(title)){
      return true;
    }
    else{
      return false;
    }
  }



  /**
   * the method that will check if the title is available in the library or not
   * @param title the title that we're checking if it's available or not
   * @return returning a boolean telling about whethere the title is available in the library or not
   */
  @Override
  public boolean isAvailable(String title) {
    if (!collection.containsKey(title)) {
      System.out.println("This title is not in the library’s collection.");
      return false;
    }
    return collection.get(title);
  }



  /**
   * Method that prints all the title in the collection
   */
  @Override
  public void printCollection() {
    if (collection.isEmpty()) {
      System.out.println("The library has no books at the moment");
    } 
    else {
      System.out.println("Library Collection:");
      for (String title : collection.keySet()) {
        boolean isAvailable = collection.get(title);
        String status = isAvailable ? "Available" : "Checked Out";
        System.out.println("• " + title + " — " + status);
      }
    }
  }

/**
 * Overriding the method of goToFloor from building superclass
 */
@Override
public void goToFloor(int floorNum) {
    if (this.activeFloor == -1) {
        throw new RuntimeException("You are not inside this Building. Must call enter() before navigating between floors.");
    } 
    if (floorNum < 1 || floorNum > this.nFloors) {
        throw new RuntimeException("Invalid floor number. Valid range for this Building is 1-" + this.nFloors +".");
    }
    if (Math.abs(floorNum - this.activeFloor) > 1 && !this.hasElevator) {
        throw new RuntimeException("This library does not have an elevator. You can only move to adjacent floors using goUp() or goDown().");
    }
    System.out.println("You are now on floor #" + floorNum + " of " + this.name);
    this.activeFloor = floorNum;
    }


/**
 * overriding the showoptions method from the building superclass
 */
@Override
public void showOptions() {
    super.showOptions();
    System.out.println(" + addTitle(title) \n + removeTitle(title) \n + containsTitle(title) \n + isAvailable(title) \n + printCollection()");
}
  
    public static void main(String[] args) {
      new Library("Neilson Library", "3 prospect street", 4, true);
    }
  
  }