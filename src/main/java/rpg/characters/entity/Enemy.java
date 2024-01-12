package rpg.characters.entity;

import javax.swing.*;
import java.awt.*;

public class Enemy extends Entity {

    private int goldReward;
    private int expReward;

    private JLabel labName, images;

    private static String[] easyNames = {"Wolf", "Skeleton", "Zombie"};
    private static String[] mediumNames = {"Troll", "Golem", "Gargoyle"};
    private static String[] hardNames = {"Magician", "Mummy", "Demon"};

    public Enemy(String name, int attack, int defense, double maxHealth, String difficulty) {
        super(name, attack, defense, maxHealth);

        labName = new JLabel(name);
        labName.setFont(new Font("Roboto", Font.BOLD, 20));
        String imagePath = "./images/" + name.toLowerCase() + ".png";
        images = new JLabel();
        images.setIcon(new ImageIcon(imagePath));

        switch (difficulty) {

            case "easy":
                expReward = (int) (Math.random() * 2 + 1);
                goldReward = (int) (Math.random() * 5 + 1);
                labName.setForeground(Color.BLUE);
                break;
            case "medium":
                expReward = (int) (Math.random() * 6 + 2);
                goldReward = (int) (Math.random() * 20 + 1);
                labName.setForeground(Color.ORANGE);
                break;
            case "hard":
                expReward = (int) (Math.random() * 16 + 5);
                goldReward = (int) (Math.random() * 50 + 1);
                labName.setForeground(Color.RED);
                break;
            default:
                expReward = 500;
                goldReward = 1500;
                labName.setForeground(Color.MAGENTA);
        }

    }

    public static Enemy enemyGeneration(int i) {

        Enemy e;

        int numEnemy = (int) (Math.random() * 3);
        int numHealt = (int) (Math.random() * 30);
        int numAttack = (int) (Math.random() * 5);
        int numDefense = (int) (Math.random() * 2);

        if (i < 80) {
            e = new Enemy(easyNames[numEnemy], numAttack + 1, numDefense, numHealt + 15, "easy");
        } else if (i < 140) {
            e = new Enemy(mediumNames[numEnemy], numAttack + 4, numDefense + 2, numHealt + 30, "medium");
        } else if (i < 200) {
            e = new Enemy(hardNames[numEnemy], numAttack + 8, numDefense + 5, numHealt + 80, "hard");
        } else {
            e = new Enemy("Boss", numAttack + 15, numDefense + 10, numHealt + 150, "finalBoss");
        }
        return e;
    }

    public int getGoldReward() {
        return goldReward;
    }

    public int getExpReward() {
        return expReward;
    }

    public JLabel getLabName() {
        return labName;
    }

    public JLabel getImages() {
        return images;
    }


}
