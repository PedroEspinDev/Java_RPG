package rpg.characters.entity;

public class Player extends Entity {

    private int level, gold, exp, necessaryExp;


    public Player(String name, int attack, int defense, double MaxHealth) {
        super(name, attack, defense, MaxHealth);
        level = 1;
        gold = 0;
        exp = 0;
        necessaryExp = 10;
    }

    public int getLevel() {
        return level;
    }

    public void upLevel() {
        level++;
        setAttack(getAttack() + 2);
        setDefense(getDefense() + 1);
        setMaxHealth(getMaxHealth() * 1.1);
        getHealthBar().setMaximum((int) getMaxHealth());
        setActualHealth((int) getMaxHealth());
        establishHealth((int) getMaxHealth());
        necessaryExp += (necessaryExp + 5);
    }

    public int getExp() {
        return exp;
    }

    public void upExp(int amount) {
        exp += amount;
        if (exp >= necessaryExp) upLevel();
    }

    public int getNecessaryExp() {
        return necessaryExp;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}
