import java.util.*;


public class TextBasedGame {

    private Scanner scanner;

    public TextBasedGame() {
        scanner = new Scanner(System.in);
    }

    public Player generatePlayer(){
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your name: ");
        String playerName = input.nextLine();
        Player player = new Player(playerName,"Room1",new ArrayList<>());
        return player;
    }

    public void playGame(Map<String,Room> rooms) {
        Player player = generatePlayer();
        System.out.println("Hello " + player.getName());
        boolean running = true;
        while (running) {
            Room room = rooms.get(player.getCurrentRoomName());
            System.out.println("You are in " + room.getName());
            System.out.println("Description: " + room.getDescription());
            System.out.println("Available connections: " + room.getConnections().keySet());
            System.out.println("Items in the room : " + Item.getInvAsString(room.getItems()));
            System.out.println("Items in your inventory : " + Item.getInvAsString(player.getInventory()));
            System.out.println("Puzzles in the room : " + Puzzle.getPuzzleAsString(room.getPuzzles()));
            System.out.println("Monsters in the room : " + Monster.getMonsterAsString(room.getMonsters()));
            System.out.println();
            for (String direction : room.getConnections().keySet()) {
                System.out.print(direction + " ");
            }
            System.out.println("Enter the direction you want to go (move : N, S, W, E), 'quit' to exit or : ");
            System.out.println("For items uses these commands(drop, pickup, inspect : itemName)");
            System.out.println();
            String input = scanner.nextLine();
            input = input.toLowerCase();
            String[] inputParts = input.split(" ");
            String command = inputParts[0];
            System.out.println();
            switch (command){
                case "quit":
                    running = false;
                    System.out.println("Coward");
                    break;
                case "move":
                    String[] movement = input.split(" ");
                    String roomName = room.getConnectedRoomName(movement[1]);
                    System.out.println(roomName);
                    if(roomName != null){
                        player.setCurrentRoomName(roomName);
                    }else {
                        System.out.println("You can't go that way.");
                        System.out.println();
                    }
                    break;
                case "pickup":
                    String[] argumentsPickup = input.split(" "); // Extract the item name
                    Item itemBeingPickedUp = room.pickUpItem(argumentsPickup[1]);
                    if(itemBeingPickedUp != null){
                        player.addItem(itemBeingPickedUp);
                    }else{
                        System.out.println("Item is not in room");
                        System.out.println();
                    }
                    break;
                case "drop":
                    String[] argumentsDrop = input.split(" "); // Extract the item name
                    Item itemBeingDropped = player.dropItem(argumentsDrop[1]);
                    if (itemBeingDropped != null) {
                        room.addItemToRoom(itemBeingDropped);
                    } else {
                        System.out.println("Item is not in inventory");
                        System.out.println();
                    }
                    break;
                case "inspect":
                    String[] inspectItems = input.split(" ");
                    Item itemInRoom = room.checkIfItemInRoom(inspectItems[1]);
                    Item itemInInventory = player.checkIfItemInInventory(inspectItems[1]);
                    if(itemInRoom == null && itemInInventory == null){
                        System.out.println("This item is not in your inventory or not in the room");
                        break;
                    }
                    if(itemInRoom != null){
                        System.out.println("Item Desc: " + itemInRoom);
                        break;
                    }
                    if(itemInInventory != null){
                        System.out.println("Item Desc " + itemInInventory);
                        break;
                    }
                    break;
                case "explore":
                    System.out.println(room.getDescription() + " " + room.getItems() + " " + room.getPuzzles());
                    break;
                case "say":
                    // need to get puzzles in room (make new method for puzzle)
                    if(room.getPuzzles() == null || room.getPuzzles().isEmpty()){
                        System.out.println("There is no puzzle in this room");
                        break;
                    }
                    final String userInput = input;
                    room.getPuzzles().forEach(puzzle -> {
                        if(userInput.equals(puzzle.getAns().toLowerCase())){
                            System.out.println("This puzzle has been solved!");
                            //remove puzzle logic
                            // unlock room logic
                        }else{
                            System.out.println("You answered wrong :" + userInput);
                            // put property on puzzle for attempts
                        }
                    });
                    break;
                case "inventory":
                    if(input.equals("inventory")){
                        System.out.println("Items in your backpack : " + Item.getInvAsString(player.getInventory()));
                        break;
                    }
                case "commands":
                    if(input.equals("commands")){
                        System.out.println("commands available are : ");
                    }
                case "fight":
                    if(input.equals("fight")){
                        System.out.println("You are now fighting monster");
                        // fight logic
                    }
                    break;
                default:
                    System.out.println("Invalid command. Try again.");
                    System.out.println();
                    break;
            }
        }
    }
    public static void main(String[] args) {
        TextBasedGame game = new TextBasedGame();
        Map<String,Monster> monsters = MonsterReader.readMonstersFromFile("monsters.txt");
        Map<String,Puzzle> puzzles = PuzzleReader.readPuzzlesFromFile("puzzles.txt");
        Map<String,Item> items = ItemReader.readItemsFromFile("items.txt");
//        items.entrySet().forEach(i->{
//            System.out.println("This item is  " + i.getKey());
//        });
        Map<String,Room> rooms = MapReader.loadMapFromFile("map.txt",items,puzzles,monsters);
//        rooms.entrySet().forEach(r->{
//            System.out.println("This Room is  " + r.getValue());
//        });
        game.playGame(rooms);
    }
}
