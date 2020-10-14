package Chapter08Script.runtimeAnnotations;

import Chapter08Script.runtimeAnnotations.button3.ButtonFrame;

import javax.script.SimpleScriptContext;
import javax.swing.*;
import java.awt.*;

public class AnnotationsTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            ButtonFrame frame = new ButtonFrame();
            frame.setTitle("Title");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
