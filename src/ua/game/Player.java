package ua.game;

import java.awt.*;

public class Player {

    //fields
    public static double x;
    public static double y;
    private int r;
    public static  int speed;
    public static int health;
    public static int bonushealth;


    private double dx;
    private double dy;



    private Color color2;
    private Color color1;

    public static boolean up, down, left, right;

    public static  boolean isFiring;


    //constructor
    public Player() {
        up = false;
        down = false;
        right = false;
        left = false;
        isFiring = false;

        x = GamePanel.WIDTH / 2;
        y = GamePanel.HEIGHT / 2;

        r = 7;

        speed = 5;

        dx = 0;
        dy = 0;

        health = 3;

        color1 = Color.red;
        color2 = Color.ORANGE;
    }

    //Function
    public double getX(){
        return x;

    }
    public double getY(){
        return y;
    }
    public int getR(){return r;}

    public void hit (){
        health--;
    }
    public void healing(){health =3;}
    public void doublehealth(){health=health*2;}
    public void speedboost(){speed +=+1; }


    public void update() {




        if(health==0){
            GamePanel.state = GamePanel.STATES.ENDSCREEN;

        }
        if(isFiring){
            GamePanel.bullets.add(new Bullet());
        }
        if (up && y > r) {
            dy = -speed;
        }
        if (down && y < GamePanel.HEIGHT - r) {
            dy = speed;

        }
        if (left && x > r) {
            dx = -speed;
        }
        if (right && x < GamePanel.WIDTH - r) {
            dx = speed;

        }
        if (up && left || up && right || down && left || down && right) {
            double angel = Math.toRadians(45);
            dy = dy * Math.sin(angel);
            dx = dx * Math.cos(angel);

        }
        y+=dy;
        x+=dx;

        dy=0;
        dx=0;
    }

    public void draw(Graphics2D g) {
        g.setColor(color1);
        g.fillOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
        g.setStroke(new BasicStroke(3));//
        g.setColor(color1.darker());
        g.drawOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
        g.setStroke(new BasicStroke(1));

        String Healtext = "Health"+health;
        int lenght = (int)g.getFontMetrics().getStringBounds(Healtext,g).getWidth();
        g.setColor(Color.BLACK);
        g.setFont(new Font("consolas", Font.BOLD, 30));
        g.drawString(Healtext,GamePanel.WIDTH/2-lenght,GamePanel.HEIGHT);

    }


}
