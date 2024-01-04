package rpg.characters.entity;

import rpg.gameInterface.PrincipalWindow;

import javax.swing.*;
import java.awt.*;

public class Exploration {

    private JDialog frame;
    private JPanel principalPanel, topPanel, bottomPanel, monsterPanel, secMonsterPanel;
    private JButton botAttack, botEscape;
    private JTextArea explorationInfo;
    private JScrollPane barDes;

    private Person pn;
    private Enemy enemy;

    private static int numExploration = 0;

    public Exploration(PrincipalWindow mw) {

        pn = mw.getPn();

        frame = new JDialog();

        principalPanel = new JPanel(new BorderLayout());
        topPanel = mw.getTopPanel();
        bottomPanel = new JPanel();
        monsterPanel = new JPanel();
        secMonsterPanel = new JPanel();

        explorationInfo = new JTextArea();
        explorationInfo.setEditable(false);
        barDes = new JScrollPane(explorationInfo);
        barDes.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        botAttack = new JButton("Attack");
        botEscape = new JButton("Escape");


    }

    public void startExploration() {

        chooseDificulty();
        createInterface();


    }

    private void chooseDificulty() {

        int randomNum = (int) (Math.random() * 100) + numExploration;
        numExploration++;

        //Generation random enemy.
        enemy = enemy.enemyGeneration(randomNum);

    }

    private void createInterface() {
        //Top panel ready.
        //We add text area to the main panel.
        principalPanel.add(barDes, BorderLayout.CENTER);

        //We add everything necessary from the enemy to the main panel.
        secMonsterPanel.add(enemy.getLabName());
        secMonsterPanel.add(enemy.getHealthBar());

        monsterPanel.setLayout(new BoxLayout(monsterPanel, BoxLayout.Y_AXIS));
        monsterPanel.add(enemy.getImages());
        monsterPanel.add(secMonsterPanel);


        //We make a lower panel with buttons.
        botAttack.addActionListener(e -> attack());
        botEscape.addActionListener(e -> frame.dispose());

        bottomPanel.add(botAttack);
        bottomPanel.add(new JLabel("          "));
        bottomPanel.add(botEscape);

        //Add secondary panels to the main one.
        principalPanel.add(topPanel, BorderLayout.NORTH);
        principalPanel.add(bottomPanel, BorderLayout.SOUTH);
        principalPanel.add(monsterPanel, BorderLayout.EAST);


        frame.add(principalPanel);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setModal(true);
        frame.setVisible(true);


    }

    private Object attack() {
        return null;
    }

}












