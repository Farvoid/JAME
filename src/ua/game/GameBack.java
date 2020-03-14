package ua.game;
import java.awt.*;

public class GameBack {
    //Fields
    public static  Color color;
    public static int  numbercolar;




    //Constructor
    public   GameBack() {
        color = Color.gray;


    }

    //Function
    public void update() {
        switch (numbercolar) {
            case (1):color =Color.red;
                break;
            case (2):color = new Color(0x00CC00);
                break;
            case (3): color =new Color(0x8207AA);
                break;

        }
    }


    public void draw(Graphics2D g){
        g.setColor(color);
        g.fillRect(0, 0 ,GamePanel.WIDTH, GamePanel.HEIGHT);

    }

}
