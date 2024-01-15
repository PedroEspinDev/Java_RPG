import rpg.characters.entity.Player;
import rpg.gameInterface.PrincipalWindow;

public class Main {
    public static void main(String[] args) {

        Player hero = new Player("Firulais", 8, 3,100);

        PrincipalWindow game = new PrincipalWindow(hero);

        game.StartGame();

    }

}
