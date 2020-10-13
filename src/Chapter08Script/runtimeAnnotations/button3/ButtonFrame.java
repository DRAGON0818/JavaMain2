package Chapter08Script.runtimeAnnotations.button3;

import Chapter08Script.runtimeAnnotations.ActionListenerFor;

import javax.swing.*;
import java.awt.*;

public class ButtonFrame extends JFrame {

    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    private JPanel panel;
    private JButton yellowButton;
    private JButton blueButton;
    private JButton redButton;

    public ButtonFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        panel = new JPanel();
        add(panel);

        yellowButton = new JButton("YELLOW");
        redButton = new JButton("RED");
        blueButton = new JButton("Blue");

        panel.add(yellowButton);
        panel.add(blueButton);
        panel.add(redButton);


    }

    @ActionListenerFor(source = "yellowButton")
    public void yellowBackground() {
        panel.setBackground(Color.yellow);
    }

    @ActionListenerFor(source = "redButton")
    public void redBackGround() {
        panel.setBackground(Color.red);
    }

    @ActionListenerFor(source = "blueButton")
    public void blueBackGround(){
        panel.setBackground(Color.blue);
    }
}
