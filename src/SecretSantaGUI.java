/***********************************************
 * Back up GUI version of application
 *
 ***********************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SecretSantaGUI {
    SecretSanta ss;
    private final int winWidth = 500, winHeight = 300;
    JFrame stage;
    JTextField nameTF;
    JLabel nameLbl = new JLabel("Please enter your full name");
    JLabel secretName = new JLabel("");
    JButton acceptBtn = new JButton("Get Secret Santa");

    public SecretSantaGUI() {
        ss = new SecretSanta();
        ss.createConfigFile(ss.getFinalPairs(), "out.txt");

        stage = new JFrame("Secret Santa");
        stage.setSize(winWidth, winHeight);
        stage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        stage.setVisible(true);
        stage.setLayout(new FlowLayout(0, 10, 10));

        nameTF = new JTextField(10);
        nameTF.setEditable(true);
        nameTF.setFocusable(true);

        stage.add(nameLbl);
        stage.add(nameTF);
        stage.add(acceptBtn);
        stage.add(secretName);

        acceptBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        // new  thread for fun
                        String secretSanta = ss.pickName(nameTF.getText());
                        secretName.setText(secretSanta);
                    }
                });
            }
        }); // set listner

        // need to make it so that once somebody has gone, they can't go again

    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // seperate thread
                SecretSantaGUI stage = new SecretSantaGUI();
            }
        });
    }

}
