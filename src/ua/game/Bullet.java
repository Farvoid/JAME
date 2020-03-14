package ua.game;

import java.awt.*;
public class Bullet {

    //Fields
    private double x;
    private double y;
    private double bulletdx;
    private double bulletdy;

    private double distX;
    private double distY;
    private double dist;
    private int r ;

    public static int speed;

    private Color color1;
    //constructor
    public Bullet(){

        x=GamePanel.player.getX();
        y=GamePanel.player.getY();
        speed = 10;

        r =2;

        distX = GamePanel.mouseX - x;
        distY = GamePanel.mouseY - y;
        dist = Math.sqrt(distX * distX + distY*distY);
        bulletdx = distX/dist*speed;
        bulletdy = distY/dist*speed;

        color1 =Color.CYAN;

    }

    //Functions

    public int getR() {
        return r;
    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }

    public void update(){
        y += bulletdy;
        x += bulletdx;

    }

    public void draw(Graphics2D g){
        g.setColor(color1);
        g.fillRect((int)x,(int)y,2,4);
    }

    public boolean remove(){
        if(y<0){
            return true;
        }
        return false;
    }
}

