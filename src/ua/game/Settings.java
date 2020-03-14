package ua.game;

import javax.swing.*;
import java.awt.*;

public class Settings {
    //fields
    private int buttonWidth;
    private int buttonHeight;
    private String textColor;
    private int stroke1;
    private int stroke2;
    private int stroke3;

    public static boolean Fontred;
    public static boolean Fontmagenta;
    public static boolean Fontgreen;




    //constructor
    public  Settings(){

        buttonHeight = 100;
        buttonWidth  = 100;
         textColor = "B A C K  C O L O R";

         stroke1 = 1;
         stroke2 = 1;
         stroke3 = 1;

         Fontred = false;
         Fontgreen = false;
         Fontmagenta = false;

    }



    //Function
    public void update() {
        if (GamePanel.mouseX > GamePanel.WIDTH / 2 - buttonWidth / 2
                && GamePanel.mouseX < GamePanel.WIDTH / 2 + buttonWidth / 2
                && GamePanel.mouseY > GamePanel.HEIGHT / 2 - buttonHeight / 2
                && GamePanel.mouseY < GamePanel.HEIGHT / 2 + buttonHeight / 2) {
            stroke1 = 3;
            if (GamePanel.leftMouse) {
                GameBack.numbercolar = 1;
            }


        } else {
            stroke1 = 1;
        }
        if (GamePanel.mouseX > GamePanel.WIDTH / 2 - buttonWidth * 2
                && GamePanel.mouseX < GamePanel.HEIGHT / 2 - buttonWidth * 2 + buttonWidth
                && GamePanel.mouseY > GamePanel.HEIGHT / 2 - buttonHeight / 2
                && GamePanel.mouseY < GamePanel.HEIGHT / 2 + buttonHeight / 2) {
            stroke2 = 3;
            if (GamePanel.leftMouse) {
                GameBack.numbercolar = 2;
            }
            }   else {
                stroke2 = 1;
            }
            if (GamePanel.mouseX > GamePanel.WIDTH / 2 + buttonWidth
                    && GamePanel.mouseX < GamePanel.HEIGHT / 2 + buttonWidth * 2
                    && GamePanel.mouseY > GamePanel.HEIGHT / 2 - buttonHeight / 2
                    && GamePanel.mouseY < GamePanel.HEIGHT / 2 + buttonHeight / 2) {
                stroke3 = 3;
                if (GamePanel.leftMouse) {
                    GameBack.numbercolar = 3;
                }

            } else {
                stroke3 = 1;
            }



    }


    public void draw(Graphics2D g){
        //Text

        int longht = (int) g.getFontMetrics().getStringBounds(textColor,g).getWidth();
        g.setColor(Color.BLACK);
        g.drawString(textColor,GamePanel.WIDTH/2-longht/2,GamePanel.HEIGHT/2-buttonHeight);

        //center
        g.setStroke(new BasicStroke(stroke1));
        g.setColor(Color.red);
        g.fillRect(GamePanel.WIDTH/2-buttonWidth/2,GamePanel.HEIGHT/2-buttonHeight/2,buttonWidth,buttonHeight);

        g.setColor(Color.BLACK);
        g.drawRect(GamePanel.WIDTH/2-buttonWidth/2,GamePanel.HEIGHT/2-buttonHeight/2,buttonWidth,buttonHeight);


        //right

        g.setColor(new Color(0x00CC00) );
        g.fillRect(GamePanel.WIDTH/2-buttonWidth*2,GamePanel.HEIGHT/2-buttonHeight/2,buttonWidth,buttonHeight);

        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(stroke2));//
        g.drawRect(GamePanel.WIDTH/2-buttonWidth*2,GamePanel.HEIGHT/2-buttonHeight/2,buttonWidth,buttonHeight);

        //left
        g.setStroke(new BasicStroke(stroke3));
        g.setColor(new Color(0x8207AA));
        g.fillRect(GamePanel.WIDTH/2+buttonWidth,GamePanel.HEIGHT/2-buttonHeight/2,buttonWidth,buttonHeight);

        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(stroke3));//
        g.drawRect(GamePanel.WIDTH/2+buttonWidth,GamePanel.HEIGHT/2-buttonHeight/2,buttonWidth,buttonHeight);

        g.setStroke (new BasicStroke(1));
    }
}
