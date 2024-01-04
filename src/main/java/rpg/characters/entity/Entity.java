package rpg.characters.entity;

import rpg.characters.entity.Interface.IAttackable;

import javax.swing.*;
import java.awt.*;

public class Entity implements IAttackable {
    private String name;
    private int actualHealth, attack, defense;
    private double maxHealth;
    private boolean itsAlive;
    private JProgressBar healthBar;

    public Entity(String name, int attack, int defense, double maxHealth) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.maxHealth = maxHealth;
        actualHealth = (int) maxHealth;
        itsAlive = true;
        healthBar = new JProgressBar(0, (int) maxHealth);
        healthBar.setPreferredSize(new Dimension(150, 25));

        establishHealth(actualHealth);
    }

    public void establishHealth(int health) {

        healthBar.setValue(health);
        healthBar.setForeground(Color.RED);
        healthBar.setStringPainted(true);
        healthBar.setString(actualHealth + "/" + (int) maxHealth);


    }

    @Override
    public void fight(IAttackable enemy) {
        enemy.takeDamage(attack);

    }

    @Override
    public void takeDamage(int amount) {
        if (itsAlive) {
            int totalAmount = amount - defense;
            if (totalAmount <= 0) totalAmount = 1;
            actualHealth -= totalAmount;
            if (actualHealth <= 0) itsAlive = false;
        }
    }

    public String getName() {
        return name;
    }

    public int getActualHealth() {
        return actualHealth;
    }

    public void setActualHealth(int actualHealth) {
        this.actualHealth = actualHealth;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public boolean isItsAlive() {
        return itsAlive;
    }

    public void setItsAlive(boolean itsAlive) {
        this.itsAlive = itsAlive;
    }

    public JProgressBar getHealthBar() {
        return healthBar;
    }
}
