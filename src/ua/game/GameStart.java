package ua.game;

import javax.swing.*;

public class GameStart {

    public static void main(String[] args) {
        GamePanel panel = new GamePanel();


        JFrame startFrame = new JFrame("J(G)ame");
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        startFrame.setContentPane(panel);
        startFrame.pack();//?
        startFrame.setLocationRelativeTo(null);//?
        startFrame.setVisible(true);
        panel.start();



    }
}
