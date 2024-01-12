package rpg.characters.entity;

import rpg.gameInterface.FinalWindow;
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
    private PrincipalWindow pw;

    public Exploration(PrincipalWindow pw) {
        this.pw = pw;
        pn = pw.getPn();

        frame = new JDialog();

        principalPanel = new JPanel(new BorderLayout());
        topPanel = pw.getTopPanel();
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

    private void attack() {

        int damage;
        pn.fight(enemy);

        explorationInfo.setText(explorationInfo.getText() + pn.getName()
                + " attacks with a force of " + pn.getAttack() + ".\n");

        damage = pn.getAttack() - enemy.getDefense();

        if (damage <= 0) damage = 1;

        explorationInfo.setText(explorationInfo.getText() + enemy.getName()
                + " has received " + damage + " damage due to your defense.\n\n");

        enemy.establishHealth(enemy.getActualHealth());
        if (!enemy.isItsAlive()) {
            enemyDefeated();
        } else {
            enemy.fight(pn);
            explorationInfo.setText(explorationInfo.getText() + enemy.getName()
                    + " attacks with a force of " + enemy.getAttack() + ".\n");

            damage = enemy.getAttack() - pn.getDefense();
            if (damage <= 0) damage = 1;

            explorationInfo.setText(explorationInfo.getText() + pn.getName()
                    + " has received " + damage + " damage due to your defense.\n\n");
            pn.establishHealth(pn.getActualHealth());

            if (!pn.isItsAlive()) derrota();

        }
    }

    private void derrota() {
        FinalWindow f = new FinalWindow(FinalWindow.DEFEAT, pn);
        f.open();
    }

    private void enemyDefeated() {

        botAttack.setEnabled(false);
        botEscape.setText("Exit");

        explorationInfo.setText(explorationInfo.getText() + enemy.getName() + " has been defeated.\n"
                + "You receive " + enemy.getGoldReward() + " gold.\n"
                + "You get " + enemy.getExpReward() + " exp points.");

        pn.upExp(enemy.getExpReward());
        pw.getLabExp().setText("  Exp: " + pn.getExp() + "/" + pn.getNecessaryExp());
        pw.getLabLevel().setText("  Lvl: " + pn.getLevel());
        pw.getLabAttributes().setText(" Dmg: " + pn.getAttack() + "  |  Def: " + pn.getDefense() + "   Health: ");

        pn.setGold(enemy.getGoldReward());
        pw.getLabGold().setText("  Gold: " + pn.getGold());

    }

}












