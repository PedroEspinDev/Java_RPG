package rpg.gameInterface;

import rpg.characters.entity.Exploration;
import rpg.characters.entity.Player;

import javax.swing.*;
import java.awt.*;

public class Store {
    private JDialog frame;
    private JPanel principalPanel, topPanel, bottomPanel, panelStore;
    private JPanel swordPanel, shieldPanel, potionPanel, mapPanel;
    private JLabel swordImage, shieldImage, potionImage, mapImage;
    private JLabel descriptionSword, descriptionShield, descriptionPotion, descriptionMap;
    private static JButton swordButton, shieldButton, potionButton, mapButton;
    private static boolean spendSword = false, spendShield = false, spendPotion = false, spendMap = false;
    private JButton exitButton;
    private PrincipalWindow pw;
    private Player player;

    public Store(PrincipalWindow pw) {
        this.pw = pw;
        player = pw.getPlayer();
        frame = new JDialog();
        principalPanel = new JPanel(new BorderLayout());
        topPanel = pw.getTopPanel();
        bottomPanel = new JPanel();
        panelStore = new JPanel(new GridLayout(2, 2));

        swordPanel = new JPanel();
        shieldPanel = new JPanel();
        potionPanel = new JPanel();
        mapPanel = new JPanel();

        swordImage = new JLabel(new ImageIcon("./images/sword.png"));
        shieldImage = new JLabel(new ImageIcon("./images/shield.png"));
        potionImage = new JLabel(new ImageIcon("./images/potion.png"));
        mapImage = new JLabel(new ImageIcon("./images/map.png"));

        descriptionSword = new JLabel("Sword - 100 Gold.");
        descriptionShield = new JLabel("Shield - 100 Gold.");
        descriptionPotion = new JLabel("Healt Potion - 50 Gold.");
        descriptionMap = new JLabel("Map - 10 Gold.");

        swordButton = new JButton("Buy");
        shieldButton = new JButton("Buy");
        potionButton = new JButton("Buy");
        mapButton = new JButton("Buy");

        exitButton = new JButton("Exit");
    }

    public void openStore() {
        mountInterface();
        frame.setVisible(true);
    }

    private void mountInterface() {
        //We prepare top area.
        principalPanel.add(topPanel, BorderLayout.NORTH);

        //We prepare central area.
        addObject(swordPanel, swordImage, descriptionSword, swordButton, "sword", spendSword);
        addObject(shieldPanel, shieldImage, descriptionShield, shieldButton, "shield", spendShield);
        addObject(potionPanel, potionImage, descriptionPotion, potionButton, "potion", spendPotion);
        addObject(mapPanel, mapImage, descriptionMap, mapButton, "map", spendMap);

        principalPanel.add(panelStore, BorderLayout.CENTER);

        //We prepare inferior area.
        exitButton.addActionListener(e -> frame.dispose());
        bottomPanel.add(exitButton);
        principalPanel.add(bottomPanel, BorderLayout.SOUTH);

        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setModal(true);
        frame.add(principalPanel);
    }

    private void addObject(JPanel objectPanel, JLabel image, JLabel description, JButton button, String name, boolean spend) {

        image.setAlignmentX(Component.CENTER_ALIGNMENT);
        description.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        if (spend) button.setEnabled(false);
        button.addActionListener(e -> buyObject(button, name));

        objectPanel.setLayout(new BoxLayout(objectPanel, BoxLayout.Y_AXIS));
        objectPanel.add(image);
        objectPanel.add(description);
        objectPanel.add(button);

        panelStore.add(objectPanel);
    }

    private void buyObject(JButton button, String name) {

        switch (name) {
            case "sword":
                if (player.getGold() >= 100) {
                    player.setAttack(player.getAttack() + 3);
                    pw.getLabAttributes().setText(" Dmg: " + player.getAttack() + "  |  Def: " + player.getDefense() + "   Health: ");
                    player.setGold(player.getGold() - 100);
                    pw.getLabGold().setText("  Gold: " + player.getGold());
                    button.setEnabled(false);
                    spendSword = true;
                }
                break;
            case "shield":
                if (player.getGold() >= 100) {
                    player.setDefense(player.getDefense() + 2);
                    pw.getLabAttributes().setText(" Dmg: " + player.getAttack() + "  |  Def: " + player.getDefense() + "   Health: ");
                    player.setGold(player.getGold() - 100);
                    pw.getLabGold().setText("  Gold: " + player.getGold());
                    button.setEnabled(false);
                    spendMap = true;
                }
                break;
            case "potion":
                if (player.getGold() >= 50) {
                    player.setActualHealth((int) player.getMaxHealth());
                    player.establishHealth(player.getActualHealth());
                    player.setGold(player.getGold() - 50);
                    pw.getLabGold().setText("  Gold: " + player.getGold());
                    button.setEnabled(false);
                    spendPotion = true;
                }
                break;
            //In this case we increase the exploration counter of the Exploration class by 250,
            //being greater than 200, it takes us to the fight with the final boss.
            case "map":
                if (player.getGold() >= 10) {
                    Exploration.setNumExploration(250);
                    player.setGold(player.getGold() - 10);
                    pw.getLabGold().setText("  Gold: " + player.getGold());
                    button.setEnabled(false);
                    spendMap = true;
                }
                break;
        }
    }
}
