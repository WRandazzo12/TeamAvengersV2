import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private String currentRoomName;
    private List<Item> inventory;

    public Player(String name, String currentRoomName, List<Item> inventory) {
        this.name = name;
        this.currentRoomName = currentRoomName;
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrentRoomName() {
        return currentRoomName;
    }

    public void setCurrentRoomName(String currentRoomName) {
        this.currentRoomName = currentRoomName;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    public Item dropItem(String itemName) {
        Item item = checkIfItemInInventory(itemName);
        if(item == null){
            return null;
        }else{
            inventory.remove(item);
//            inventory.removeIf(i->i.getName().toLowerCase().equals(itemName));
            return item;
        }
    }

    public Item checkIfItemInInventory(String itemName){
        Item item = inventory.stream().filter(i->i.getName().toLowerCase().equals(itemName.toLowerCase())).findFirst().orElse(null);
        return item;
    }

    public void addItem(Item item){
        inventory.add(item);
    }

}
