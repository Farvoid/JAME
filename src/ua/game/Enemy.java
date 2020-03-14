package ua.game;

import java.awt.*;
import java.util.Random;


public class Enemy {
    private double x;
    private double y;
    private int r;

    private double health;

    private double speed;
    private double dx;
    private double dy;

    private int type;
    private int rank;
    private int SCORE;
    public  static int bonusdamege = 0;

    private Color color;
    private  double angle = Math.toRadians(Math.random() * 360);

    //Constructor
    public Enemy(int type, int rank) {

        this.type = type;
        this.rank = rank;
        switch (type) {
            case (1):color = Color.green;


                switch (rank) {

                    case (1):
                        x = Math.random() * GamePanel.WIDTH;

                        speed = 5;
                         r = 10;
                         health = 6;

                        dx = Math.sin(angle) * speed;
                        dy = Math.cos(angle) * speed;
                        break;
                    case(2):

                        x = Math.random() * GamePanel.WIDTH;

                        speed = 10;
                        r = 2;
                        health = 10;

                        dx = Math.sin(angle) * speed;
                        dy = Math.cos(angle) * speed;
                        break;

                }
                break;

                 case(2): color = Color.yellow;
                 switch (rank){
                     case (1):
                         x = Math.random() * GamePanel.WIDTH;

                         speed =20;
                         r = 40;
                         health = 80;

                         dx = Math.sin(angle) * speed;
                         dy = Math.cos(angle) * speed;
                         break;
                     case (2):
                         x = Math.random() * GamePanel.WIDTH;

                         speed =2;
                         r = 100;
                         health = 1000;

                         dx = Math.sin(angle) * speed;
                         dy = Math.cos(angle) * speed;

                 }

        }
    }
   //Functiom
    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public int getR(){
        return r;
    }


    public boolean remove(){
        if (health <=0){
            return true;
        }
        return  false;
    }

    public void hit (){

        health=health-1-bonusdamege;
    }

    public void update() {
        x += dx;
        y += dy;
        if (x < 0 && dx < 0) dx = -dx;
        if (x > GamePanel.WIDTH && dx >0) dx = -dx;
        if (y < 0 && dy < 0) dy = -dy;
        if (y > GamePanel.HEIGHT && dy > 0) dy=-dy;

    }


    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillOval((int)(x-r), (int)(y-r), 2*r, 2*r);
         g.setStroke(new BasicStroke(5));
         g.setColor(Color.orange);
         g.drawOval((int)(x-r), (int)(y-r), 2*r, 2*r);
         g.setStroke(new BasicStroke(1));

    }
}
