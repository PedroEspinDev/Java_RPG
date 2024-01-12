package rpg.gameInterface;

import customPanelFrame.CustomFrame;
import rpg.characters.entity.Exploration;
import rpg.characters.entity.Person;

import javax.swing.*;
import java.awt.*;

public class PrincipalWindow {

    private CustomFrame frame;
    private JPanel mainPanel, topPanel, bottomPanel;

    private JLabel labName, labLevel, labExp, labGold, labAttributes;
    private JLabel labImage;

    private JButton botExplore;

    private Person pn;

    public PrincipalWindow(Person pn) {
        this.pn = pn;

        frame = new CustomFrame(800, 800, "RPG", true);

        mainPanel = new JPanel(new BorderLayout());
        topPanel = new JPanel();
        bottomPanel = new JPanel();

        labName = new JLabel(pn.getName() + "     ");
        labLevel = new JLabel("  Lvl: " + pn.getLevel());
        labExp = new JLabel("  Exp: " + pn.getExp() + "/" + pn.getNecessaryExp());
        labGold = new JLabel("  Gold: " + pn.getGold());
        labAttributes = new JLabel(" Dmg: " + pn.getAttack() + "  |  Def: " + pn.getDefense() + "   Health: ");

        labImage = new JLabel();

        botExplore = new JButton("FIGHT");

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
        topPanel.add(pn.getHealthBar());

        //Create the central image of the windows.
        labImage.setIcon(new ImageIcon("./images/portada1.jpg"));
        mainPanel.add(labImage, BorderLayout.CENTER);

        //Create interaction buttonsin bottom panel.
        botExplore.addActionListener(e -> newExploration());
        bottomPanel.add(botExplore);

        //Add secondary panels to the main panel.
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        //Add main panel to the frame.
        frame.add(mainPanel);

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

    public Person getPn() {
        return pn;
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
