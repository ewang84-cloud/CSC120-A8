/* This is a stub for the Cafe class */
public class Cafe extends Building implements CafeRequirements{

    private int nCoffeeOunces; 
    private int nSugarPackets;
    private int nCreams;
    private int nCups;
    private boolean hasElevator;

    /**
     * Constructor of the Cafe Class
     * @param name
     * @param address
     * @param nFloors
     * @param nCoffeeOunces
     * @param nSugarPackets
     * @param nCreams
     * @param nCups
     * @param 
     */
    public Cafe(String name, String address, int nFloors, int nCoffeeOunces, int nSugarPackets, int nCreams, int nCups, boolean hasElevator) {
        super(name, address, nFloors);
        this.nCoffeeOunces = nCoffeeOunces;
        this.nSugarPackets = nSugarPackets;
        this.nCreams=nCreams;
        this.nCups=nCups;
        this.hasElevator = hasElevator;
        System.out.println("You have built a cafe: ☕");
    }


    /**
     * A method that sells the coffee
     * @param size the amount of coffee ounces that will be used in this selled coffee
     * @param nSugarPackets the amount of sugar packets that will be used in this selled coffee
     * @param nCream the amount of cream that will be used in this selled coffee
     */
    @Override
    public void sellCoffee(int size, int nSugarPackets, int nCreams){
        if(this.nCoffeeOunces>= size && this.nSugarPackets>=nSugarPackets && this.nCreams>=nCreams && this.nCups>=1){
            this.nCoffeeOunces-=size;
            this.nSugarPackets-=nSugarPackets;
            this.nCreams-=nCreams;
            this.nCups-=1;
            System.out.println("Successfully purchased, your coffee is on the way");
        }
        else{
            System.out.println("No enough materials for your coffee. Restocking now!!");
            restock(100, 100, 100, 50);
            sellCoffee(size, nSugarPackets, nCreams);
        }
    }

    /**
     * overriding the method of goToFloor form the Building super class
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
            throw new RuntimeException("This cafe does not have an elevator. You can only move to adjacent floors using goUp() or goDown().");
        }
        System.out.println("You are now on floor #" + floorNum + " of " + this.name);
            this.activeFloor = floorNum;}
    
    @Override
    public void showOptions() {
        super.showOptions();
        System.out.println(" + sellCoffee(size, flavor) \n + restock(ingredient, amount) \n + printInventory()");
    }

    /**
     * Method for restocking cofe inventory
     * @param nCoffeeOunces the amount of Coffee Ounces that we want to restock
     * @param nSugarPackets the amounf of Sugar Packets that we want to restock
     * @param nCreams the amount of Creams we wants to restock
     * @param nCups the amount of cups that we want to restock
     */
    private void restock(int nCoffeeOunces, int nSugarPackets, int nCreams, int nCups){
        this.nCoffeeOunces += nCoffeeOunces;
        this.nSugarPackets += nSugarPackets;
        this.nCreams += nCreams;
        this.nCups += nCups;
        System.out.println("Restocked: +" + nCoffeeOunces + "oz coffee, +" + nSugarPackets + " sugar, +" + nCreams + " creams, +" + nCups + " cups.");
    }

    


    public static void main(String[] args) {
        new Cafe("Compass", "123 Prospect Street", 2, 100, 100, 100, 100, true);
    }
    
}