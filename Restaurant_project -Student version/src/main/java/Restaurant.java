import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        if(getCurrentTime().isAfter(openingTime)&&getCurrentTime().isBefore(closingTime)){
            displayDetails();
            return true;
        }
        else return false;
     }

     public LocalTime getCurrentTime(){ return  LocalTime.now(); }

     public List<Item> getMenu() {
        return menu;
     }
    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }
    public String getName() {
        return name;
    }

    public int getOrderPrice(List<String> itemNames){
        int orderPrice =0;
        List<Item> items = this.getMenu();
        Map<String,Item> itemMap = new HashMap<>();
        for(Item item: items) {
            itemMap.put(item.getName(),item);
        }
        for(String itemName: itemNames) {
            if(itemMap.containsKey(itemName)) {
                orderPrice += itemMap.get(itemName).getPrice();
            }
        }
        return orderPrice;
   
	}
}
