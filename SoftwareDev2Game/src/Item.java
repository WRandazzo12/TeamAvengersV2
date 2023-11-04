import java.util.ArrayList;
import java.util.List;

public class Item { // F2 to rename all instances
    private String name;
    private String desc;
    public Item(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }
    public String getName() {
        return name;
    }
    public String getDesc() {
        return desc;
    }

    public void inspect(){
        System.out.println(this.desc);
    }
    public static String getInvAsString(List<Item> items){
        List<String> itemsNames = new ArrayList<>();
       items.stream().forEach(item -> {
           itemsNames.add(item.getName());
       });
        return String.join(", ",itemsNames);
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}