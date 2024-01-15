package rpg.gameInterface;

import customPanelFrame.CustomFrame;
import rpg.characters.entity.Exploration;
import rpg.characters.entity.Player;

import javax.swing.*;
import java.awt.*;

public class PrincipalWindow {

    private CustomFrame frame;
    private JPanel mainPanel, topPanel, bottomPanel;

    private JLabel labName, labLevel, labExp, labGold, labAttributes;
    private JLabel labImage;

    private JButton exploreButton, storeButton;

    private Player player;

    public PrincipalWindow(Player player) {
        this.player = player;

        frame = new CustomFrame(800, 800, "RPG", true);

        mainPanel = new JPanel(new BorderLayout());
        topPanel = new JPanel();
        bottomPanel = new JPanel();

        labName = new JLabel(player.getName() + "     ");
        labLevel = new JLabel("  Lvl: " + player.getLevel());
        labExp = new JLabel("  Exp: " + player.getExp() + "/" + player.getNecessaryExp());
        labGold = new JLabel("  Gold: " + player.getGold());
        labAttributes = new JLabel(" Dmg: " + player.getAttack() + "  |  Def: " + player.getDefense() + "   Health: ");

        labImage = new JLabel();

        exploreButton = new JButton("Fight");
        storeButton = new JButton("Store");

    }

    public void StartGame() {

        setUpScene();
        frame.setVisible(true);

    }

    private void setUpScene() {

        //Create the superior panel with person data.
        modifyFont();
        topPanel.add(labName);
        topPanel.add(labLevel);
        topPanel.add(labExp);
        topPanel.add(labGold);
        topPanel.add(labAttributes);
        topPanel.add(player.getHealthBar());

        //Create the central image of the windows.
        labImage.setIcon(new ImageIcon("./images/portada1.jpg"));
        mainPanel.add(labImage, BorderLayout.CENTER);

        //Create interaction buttons in bottom panel.
        exploreButton.addActionListener(e -> newExploration());
        storeButton.addActionListener(e -> openStore());
        bottomPanel.add(exploreButton);
        bottomPanel.add(storeButton);

        //Add secondary panels to the main panel.
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        //Add main panel to the frame.
        frame.add(mainPanel);

    }

    private void openStore() {
        Store s = new Store(this);
        s.openStore();
        mainPanel.add(topPanel, BorderLayout.NORTH);
        frame.repaint();


    }

    private void modifyFont() {

        Font myFont = new Font("Roboto", Font.BOLD, 16);
        labName.setFont(myFont);

        labGold.setForeground(Color.BLUE);

    }

    private void newExploration() {
        Exploration ex = new Exploration(this);
        ex.startExploration();
        mainPanel.add(topPanel, BorderLayout.NORTH);
        frame.repaint();
    }

    public Player getPlayer() {
        return player;
    }

    public JPanel getTopPanel() {
        return topPanel;
    }

    public JLabel getLabLevel() {
        return labLevel;
    }

    public JLabel getLabExp() {
        return labExp;
    }

    public JLabel getLabGold() {
        return labGold;
    }

    public JLabel getLabAttributes() {
        return labAttributes;
    }

}
