package ua.game;


import java.awt.*;
import java.util.Random;


public class Bonus {

    ///fields
    public static int spawnX;
    public static int spawnY;
    public static boolean randomStart;
    public static int chanceBonus;
    private int radius;

    public static boolean colide;


    //constructor
    public Bonus() {
        radius = 10;




    }

    public int getR() {
        return radius;
    }


    //Function



    public void update() {





    }


    //bonus hit;
    //bonus score;


    //bonus health
    public void draw(Graphics2D g) {
        g.setColor(Color.black);
        g.fillOval(spawnX, spawnY, 2 * radius, 2 * radius);

    }
}

