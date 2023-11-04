This is an overview of the files being read and their attributes (Subject to change)

Map.txt is read like this
(roomName:roomDesc:connections:items:puzzles:monsters) to add (visitedRoom?:SafeRoom/HealingRooms)

items.txt is read like this
(itemName:itemDesc) to add (itemBuff,itemEquipable?,itemConsumable?)

puzzles.txt is read like this
(puzzleName:puzzlePrompt:puzzleAns:puzzleHint) to add (remove from room feature)

monsters.txt is read like this
(monsterName:monsterDesc:monsterATK:monsterHP) to add (?)


Key things to look at 
If we make an addition to any text file the associated reader must include the new attribute 

EX.
 String[] parts = line.split(":");
                String monsterName = parts[0].trim();
                String monsterDesc = parts[1].trim();
                String monsterAtk = parts[2].trim();
                String monsterHP = parts[3].trim();
                Monster monster = new Monster(monsterName,monsterDesc,monsterAtk,monsterHP);

would then look like
 String[] parts = line.split(":");
                String monsterName = parts[0].trim();
                String monsterDesc = parts[1].trim();
                String monsterAtk = parts[2].trim();
                String monsterHP = parts[3].trim();
		String monsterBanter = parts[4].trim();
                Monster monster = new Monster(monsterName,monsterDesc,monsterAtk,monsterHP,monsterBanter); 

This is the case for all reading classes

Things to add :
Fight class : shield, attack, skil, dodge
Monster interaction
Remove puzzle after correct input is applied
Remove monster after hp <= 0
mapReader class : Safe room attributed, vitisted rooms, healing rooms
itemReader class : itemBuff,itemEquipable,itemConsumable?,consumableEffect
puzzleReader class : removeFromRoom
monsterReader class : ?

commands : jump,Analyze(inspect for monsters), save/load, start, help(display all commands), grap(pickup with an option of keep or discard), Scan(basically a modified explore), equip,consume(basically use)

Things to work on :
map layout
puzzles with proper names, answers, prompts and hints
monster with proper names,descs,hp,atk,randomization
items with proper names,descs,hp,atk,randomization




