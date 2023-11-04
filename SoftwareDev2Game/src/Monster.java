import java.util.ArrayList;
import java.util.List;

public class Monster {
    private String name;
    private String desc;
    private String atk;
    private String hp;
    public Monster(String monsterName, String monsterDesc, String ATK, String HP) {
        this.name = monsterName;
        this.desc = monsterDesc;
        this.atk = ATK;
        this.hp = HP;
    }
    public String getName(){
        return name;
    }
    public String getDesc(){
        return desc;
    }
    public String getAtk(){
        return atk;
    }
    public String getHp(){
        return hp;
    }

    public static String getMonsterAsString(List<Monster> monsters){
        List<String> monsterNames = new ArrayList<>();
        monsters.stream().forEach(monster -> {
            monsterNames.add(monster.getName());
        });
        return String.join(",",monsterNames);
    }


    @Override
    public String toString() {
        return "Monster{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", atk=" + atk +
                ", hp=" + hp +
                '}';
    }
}
