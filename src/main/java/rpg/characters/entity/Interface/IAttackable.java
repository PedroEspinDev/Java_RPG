package rpg.characters.entity.Interface;

public interface IAttackable {

    public void fight(IAttackable enemy);

    public void takeDamage(int amount);

}
