import rpg.characters.entity.Person;
import rpg.gameInterface.PrincipalWindow;

public class Main {
    public static void main(String[] args) {

        Person hero = new Person("Firulais", 8, 3,100);

        PrincipalWindow game = new PrincipalWindow(hero);

        game.StartGame();

    }
}
