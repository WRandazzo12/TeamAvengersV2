import java.io.*;
import java.util.*;

public class ItemReader {
    public static Map<String,Item> readItemsFromFile(String filePath) {
        Map<String,Item> items = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                String itemName = parts[0].trim();
                String itemDesc = parts[1];
                Item item = new Item(itemName,itemDesc);
                items.put(itemName,item);
            }
        } catch (IOException e) {
            System.err.println("Error reading the items file: " + e.getMessage());
        }
        return items;
    }
}