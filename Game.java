package com.company;
import java.awt.*;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.*;

public class Game {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private JFrame frame;
    private JPanel panel;
    private Container content;
    private ArrayList<JButton> buttons;
    private Hashtable<Integer, String> selectedButtons = new Hashtable<>();
    private int numberPressed;
    private boolean isX, isO;

    public Game() {
        makeFrame();
        isX = false;
        isO = false;
    }

    private void makeFrame() {
        frame = new JFrame();

        content = frame.getContentPane();

        panel = new JPanel();
        panel.setLayout(new GridLayout(ROWS, COLS));

        makeButton();

        content.add(panel);

        frame.setSize(new Dimension(500, 500));
        frame.setVisible(true);
    }

    private void makeButton() {
        buttons = new ArrayList<>();
        for (int x = 0; x < ROWS; x++)
            for (int y = 0; y < COLS; y++) {
                JButton button = new JButton("");
                button.addActionListener(e -> {
                    play(button);
                });

                buttons.add(button);
                panel.add(button);
            }
    }

    private void play(JButton button) {

        if (numberPressed != 1) {
            JButton a = new JButton("X");
            a.setFont(new Font("Calibri", Font.PLAIN, 100));
            replace(button, a);
            buttons.remove(button);
            numberPressed++;
        } else {
            JButton b = new JButton("O");
            b.setFont(new Font("Calibri", Font.PLAIN, 100));
            replace(button, b);
            buttons.remove(button);
            numberPressed = 0;
        }
        if (!win() && buttons.isEmpty()) {
            Object[] options = {"Restart", "Quit"};
            int option = JOptionPane.showOptionDialog(frame, "NOBODY WINS!\nClick Restart to continue", "Sad", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
            if (option == JOptionPane.YES_OPTION) {
                makeFrame();
                numberPressed = 0;
            } else {
                System.exit(0);
            }
        }
        if(win() && isX) {

            JOptionPane.showMessageDialog(frame, "Player 1, you win!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
        }
        if(win() && isO)
        {
            JOptionPane.showMessageDialog(frame, "Player 2, you win!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
        }

        frame.repaint();
        frame.revalidate();}

    private void replace(JButton a, JButton c) {
        int index = panel.getComponentZOrder(a);
        panel.remove(a);
        panel.add(c, index);
        selectedButtons.put(index, c.getText());
    }


    private boolean win() {
        //check horizontal
        if (selectedButtons.containsKey(0) && selectedButtons.containsKey(1) && selectedButtons.containsKey(2)) {
            if (selectedButtons.get(0).equals(selectedButtons.get(1)) && selectedButtons.get(1).equals(selectedButtons.get(2))) {
                choosePlayer(selectedButtons.get(0));
                 return true;} }
        if (selectedButtons.containsKey(3) && selectedButtons.containsKey(4) && selectedButtons.containsKey(5)) {
            if (selectedButtons.get(3).equals(selectedButtons.get(4)) && selectedButtons.get(4).equals(selectedButtons.get(5))) {
                choosePlayer(selectedButtons.get(3));
               return true;
            }
        }
        if (selectedButtons.containsKey(6) && selectedButtons.containsKey(7) && selectedButtons.containsKey(8)) {
            if (selectedButtons.get(6).equals(selectedButtons.get(7)) && selectedButtons.get(7).equals(selectedButtons.get(8))) {
                choosePlayer(selectedButtons.get(6));
               return true;
            }
        }
        //check vertical
        if (selectedButtons.containsKey(0) && selectedButtons.containsKey(3) && selectedButtons.containsKey(6)) {
            if (selectedButtons.get(0).equals(selectedButtons.get(3)) && selectedButtons.get(3).equals(selectedButtons.get(6))) {
                choosePlayer(selectedButtons.get(0));
                return true;
            }
        }
        if (selectedButtons.containsKey(1) && selectedButtons.containsKey(4) && selectedButtons.containsKey(7)) {
            if (selectedButtons.get(1).equals(selectedButtons.get(4)) && selectedButtons.get(4).equals(selectedButtons.get(7))) {
                choosePlayer(selectedButtons.get(1));
                return true;
            }
        }
        if (selectedButtons.containsKey(2) && selectedButtons.containsKey(5) && selectedButtons.containsKey(8)) {
            if (selectedButtons.get(2).equals(selectedButtons.get(5)) && selectedButtons.get(5).equals(selectedButtons.get(8))) {
                choosePlayer(selectedButtons.get(2));
               return true;
            }
        }
        //check diagonal
        if (selectedButtons.containsKey(0) && selectedButtons.containsKey(4) && selectedButtons.containsKey(8)) {
            if (selectedButtons.get(0).equals(selectedButtons.get(4)) && selectedButtons.get(4).equals(selectedButtons.get(8))) {
                choosePlayer(selectedButtons.get(0));
                return true;
            }
        }
        if (selectedButtons.containsKey(2) && selectedButtons.containsKey(4) && selectedButtons.containsKey(6)) {
            if (selectedButtons.get(2).equals(selectedButtons.get(4)) && selectedButtons.get(4).equals(selectedButtons.get(6))) {
                choosePlayer(selectedButtons.get(2));
                return true;
            }
        }

            return false;
    }

        private void choosePlayer(String a )
    {
        if(a.equals("X"))
        {
            isX = true;
        }
        else
        {
            isO = true;
        }
    }
}
