import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Item[] store = setupStore();
        ArrayList<Item> items = new ArrayList<>();
        items = createCart(args, store);
        boolean test = true;
        int expectedSize = 0;

        try{
            expectedSize = Integer.parseInt(args[0]);}
        catch (NumberFormatException e){
            System.out.println("Cart size must be an integer");
            test = false;
        }

        if (test) {
            printInOrder(items, expectedSize);
            emptyCartReverseOrder(items);
        }
    }
    public static Item[] setupStore () {
        Item[] store = new Item[5];
        store[0] = new Item ("Mango", 2.00);
        store[1] = new Item ("Blueberries", 3.50);
        store[2] = new Item ("Watermelon", 5.75);
        store[3] = new Item ("Grapes", 4.25);
        store[4] = new Item ("Orange", 1.00);
        return store;
    }

    public static ArrayList<Item> createCart(String[] args, Item[] store) {
        ArrayList<Item> cart = new ArrayList<>();
        for(int i = 1; i < args.length; i++){
            int item = 0;
            boolean invalid = false;
            try{
                item = Integer.parseInt(args[i]);}
            catch(NumberFormatException e){
                invalid = true;
            }
            if(item < store.length && !invalid){
                cart.add(store[item]);
            }
            else{
                System.out.println("Invalid item: " + args[i]);
            }
        }
        return cart;
    }

    public static void printInOrder(ArrayList<Item> cart, int expectedSize){
        System.out.println("\nReceipt");
        System.out.println("====================");
        System.out.println("Item     ->     Price");
        double subtotal = 0;
        for(int i = 0; i < expectedSize; i++){
            Item item;
            try{
                item = cart.get(i);
            }
            catch(IndexOutOfBoundsException e){
                System.out.println("Expected " + expectedSize + " valid items");
                break;
            }
            String itemName = item.getItemName();
            double price = item.getItemPrice();
            System.out.println(itemName + "            " + price);
            subtotal += price;
        }
        System.out.println("====================");
        System.out.printf("Subtotal: %.2f", subtotal);
        double tax = subtotal * 0.05;
        System.out.printf("\nSales Tax: %.2f", tax);
        double total = subtotal + tax;
        System.out.printf("\nTotal: %.2f", total);
    }

    public static void emptyCartReverseOrder(ArrayList<Item> cart){
        System.out.println("\n\nRemoving items in \"Last To First Out\" order:");
        for(int i = cart.size()-1; i >= 0; i--){
            Item item = cart.get(i);
            String itemName = item.getItemName();
            System.out.println("Removing: " + itemName);
        }
        System.out.println("Items Removed");
    }
}