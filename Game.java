package com.company;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class Game {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private JFrame frame;
    private JPanel panel;
    private Container content;
    private ArrayList<JButton> buttons;
    private int numberPressed;

    public Game()
    {
        makeFrame();
    }

        public void makeFrame()
        {
            frame = new JFrame();

            content = frame.getContentPane();

            panel = new JPanel();
            panel.setLayout(new GridLayout(ROWS, COLS));

            makeButton();

            content.add(panel);

            frame.setSize(new Dimension(500,500));
            frame.setVisible(true);
        }

        public void makeButton()
        {
            buttons = new ArrayList<>();
            for(int x = 0; x < ROWS; x++)
                for(int y = 0; y<COLS; y++)
                {
                    JButton button = new JButton("");
                    button.addActionListener(e-> {play(button);});

                    buttons.add(button);
                    panel.add(button);
                }
    }

    public void play(JButton button)
    {

        if(numberPressed != 1)
        {
            JButton a = new JButton("X");
            a.setFont(new Font("Calibri", Font.PLAIN, 100));
            replace(button, a);
            numberPressed ++;
        }
        else
        {
            JButton b = new JButton("O");
            b.setFont(new Font("Calibri", Font.PLAIN, 100));
            replace(button, b);
            numberPressed =0;
        }
        frame.repaint();
        frame.revalidate();
    }

    public void replace(Component a, Component c)
    {

        int index = panel.getComponentZOrder(a);
        panel.remove(a);
        panel.add(c, index);
    }







}
