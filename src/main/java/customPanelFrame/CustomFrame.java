package customPanelFrame;

import javax.swing.*;

public class CustomFrame extends JFrame {

    public CustomFrame(int width, int height, String title, boolean isMain) {
        setSize(width, height);
        setTitle(title);
        setLocationRelativeTo(null);

        if(isMain) this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
