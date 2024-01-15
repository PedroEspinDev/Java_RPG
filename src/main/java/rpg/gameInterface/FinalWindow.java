package rpg.gameInterface;

import rpg.characters.entity.Player;

import javax.swing.*;
import java.awt.*;

public class FinalWindow {

    private JTextArea textArea;
    private JLabel image;
    private JButton exitButton;
    private ImageIcon imagePath;
    private JDialog frame;
    private JPanel principalPanel;
    private int condition;
    private Player pn;
    public static final int VICTORY = 0;
    public static final int DEFEAT = 1;

    public FinalWindow(int condition, Player pn) {

        frame = new JDialog();
        principalPanel = new JPanel(new BorderLayout());

        textArea = new JTextArea();
        exitButton = new JButton("Finish");

        this.condition = condition;
        this.pn = pn;

        if (condition == VICTORY) imagePath = new ImageIcon("./images/victory.jpeg");
        else imagePath = new ImageIcon("./images/defeated.jpeg");

        image = new JLabel(imagePath);
    }

    public void open() {

        prepareMessage();
        assembleScene();
        frame.setVisible(true);
    }

    private void prepareMessage() {

        String finalMenssage;
        if (condition == VICTORY) {
            finalMenssage = "Congratulations, you have managed to defeat the final boss! \n"
                    + "With wounds in your body that you can never erase.\n"
                    + "You were able to defeat all those enemies, but...at what cost?\n"
                    + pn.getName() + " you managed to bring with you " + pn.getGold() + " gold coins, spend them wisely.";
        } else {
            finalMenssage = "The castle monsters defeated you and took your soul.\n"
                    + "You were a disgrace to the region and your reign, your name will soon be forgotten.\n"
                    + "Now your body will be guarded by death, until the end of time.\n"
                    + "All is not lost yet, make a pact with death to recover your soul, do you dare?";

        }

        textArea.setText(finalMenssage);
    }

    private void assembleScene() {

        //We add image to the northern part.
        principalPanel.add(image, BorderLayout.NORTH);

        //We add a text area to the center of the screen.
        textArea.setEditable(false);
        principalPanel.add(textArea, BorderLayout.CENTER);

        //We add an exit button to the south part.
        exitButton.addActionListener(e ->  System.exit(0));
        principalPanel.add(exitButton, BorderLayout.SOUTH);

        frame.add(principalPanel);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setModal(true);

    }
}
