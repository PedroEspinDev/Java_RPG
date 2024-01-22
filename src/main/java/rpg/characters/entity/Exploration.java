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
    private Player player;
    private Enemy enemy;
    private boolean fightWithBoss = false;
    private static int numExploration = 0;
    private PrincipalWindow pw;

    public Exploration(PrincipalWindow pw) {
        this.pw = pw;
        player = pw.getPlayer();

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

        if (enemy.getName().equals("Final Boss")) fightWithBoss = true;

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
        if (fightWithBoss) botEscape.setEnabled(false);
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
        player.fight(enemy);

        explorationInfo.setText(explorationInfo.getText() + player.getName()
                + " attacks with a force of " + player.getAttack() + ".\n");

        damage = player.getAttack() - enemy.getDefense();

        if (damage <= 0) damage = 1;

        explorationInfo.setText(explorationInfo.getText() + enemy.getName()
                + " has received " + damage + " damage due to your defense.\n\n");

        enemy.establishHealth(enemy.getActualHealth());
        if (!enemy.isItsAlive()) {
            enemyDefeated();
        } else {
            enemy.fight(player);
            explorationInfo.setText(explorationInfo.getText() + enemy.getName()
                    + " attacks with a force of " + enemy.getAttack() + ".\n");

            damage = enemy.getAttack() - player.getDefense();
            if (damage <= 0) damage = 1;

            explorationInfo.setText(explorationInfo.getText() + player.getName()
                    + " has received " + damage + " damage due to your defense.\n\n");
            player.establishHealth(player.getActualHealth());

            if (!player.isItsAlive()) defeat();

        }
    }

    private void defeat() {
        FinalWindow f = new FinalWindow(FinalWindow.DEFEAT, player);
        f.open();
    }

    private void enemyDefeated() {

        botAttack.setEnabled(false);
        botEscape.setText("Exit");

        explorationInfo.setText(explorationInfo.getText() + enemy.getName() + " has been defeated.\n"
                + "You receive " + enemy.getGoldReward() + " gold.\n"
                + "You get " + enemy.getExpReward() + " exp points.");

        player.upExp(enemy.getExpReward());
        pw.getLabExp().setText("  Exp: " + player.getExp() + "/" + player.getNecessaryExp());
        pw.getLabLevel().setText("  Lvl: " + player.getLevel());
        pw.getLabAttributes().setText(" Dmg: " + player.getAttack() + "  |  Def: " + player.getDefense() + "   Health: ");

        // Add the gold reward to the player's existing gold.
        player.setGold(player.getGold() + enemy.getGoldReward());
        pw.getLabGold().setText("  Gold: " + player.getGold());

        if (fightWithBoss) {
            FinalWindow f = new FinalWindow(FinalWindow.VICTORY, player);
            f.open();
        }

    }

    public static int getNumExploration() {
        return numExploration;
    }

    public static void setNumExploration(int numExploration) {
        Exploration.numExploration = numExploration;
    }
}












