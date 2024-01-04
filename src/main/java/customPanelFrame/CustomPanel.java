package customPanelFrame;

import javax.swing.*;
import java.awt.*;

public class CustomPanel extends JPanel {

    public static final int FLOWLAYOUT = 0;
    public static final int BORDERLAYOUT = 1;
    public static final int GRIDLAYOUT = 2;

    public CustomPanel(int DefaultLayout) {

        if (DefaultLayout == 1) setLayout(new BorderLayout());
        if (DefaultLayout == 1) setLayout(new GridLayout());

    }

}
