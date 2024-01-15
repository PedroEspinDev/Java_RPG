package rpg.gameInterface;

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

        swordImage = new JLabel(new ImageIcon("./images/espada.png"));
        shieldImage = new JLabel(new ImageIcon("./images/escudo.png"));
        potionImage = new JLabel(new ImageIcon("./images/pocion.png"));
        mapImage = new JLabel(new ImageIcon("./images/mapa.png"));

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
    public void openStore(){
        mountInterface();
        frame.setVisible(true);
    }

    private void mountInterface() {
        //We prepare top area.
        principalPanel.add(topPanel, BorderLayout.NORTH);

        //We prepare central area.
        addObject(swordPanel, swordImage, descriptionSword, swordButton);
        addObject(shieldPanel, shieldImage, descriptionShield, shieldButton);
        addObject(potionPanel, potionImage, descriptionPotion, potionButton);
        addObject(mapPanel, mapImage, descriptionMap, mapButton);

        principalPanel.add(panelStore, BorderLayout.CENTER);

        //We prepare inferior area.
        bottomPanel.add(exitButton);
        principalPanel.add(bottomPanel, BorderLayout.SOUTH);

        frame.setSize(600,600);
        frame.setLocationRelativeTo(null);
        frame.setModal(true);
        frame.add(principalPanel);

    }

    private void addObject(JPanel objectPanel, JLabel image, JLabel description, JButton button) {

        image.setAlignmentX(Component.CENTER_ALIGNMENT);
        description.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        objectPanel.setLayout(new BoxLayout(objectPanel, BoxLayout.Y_AXIS));
        objectPanel.add(image);
        objectPanel.add(description);
        objectPanel.add(button);

        panelStore.add(objectPanel);

    }
}
