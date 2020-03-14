package ua.game;

import java.awt.*;

public class EndScreen {
    //Fields
    private String TextEND;
    private String Textscore;
    public static int score;
    private int buttonwidht;
    private int buttonheight;
    private String buttonText;
    private int transp;


    //Constructor
    public EndScreen() {
        TextEND = "L O S E R";
        score = 0;
        Textscore = "Y O U R  P O I N T S  ";
        buttonheight = 40;
        buttonwidht = 400;
        buttonText = "P L A Y  A G A I N";
        transp = 0;


    }

    //Function

    public void update() {
        if(GamePanel.mouseX > GamePanel.WIDTH/2-buttonwidht/2
                && GamePanel.mouseX<GamePanel.WIDTH/2 + buttonwidht/2
                && GamePanel.mouseY>GamePanel.HEIGHT/2+buttonheight*3
                && GamePanel.mouseY<GamePanel.HEIGHT/2+buttonheight*4){
            if(GamePanel.leftMouse){
                GamePanel.state = GamePanel.STATES.MENUE;
                GamePanel.player.healing();
                GamePanel.enemies.clear();
                Wave.waveNumber = 1;
                Player.x = GamePanel.WIDTH/2;
                Player.y = GamePanel.HEIGHT/2;
                EndScreen.score = 0;

            }
            transp = 60;


        }else{
            transp = 0;
        }


    }

    public void draw(Graphics2D g) {
        g.setFont(new Font("consolas", Font.BOLD, 80));
        int lenght = (int) g.getFontMetrics().getStringBounds(TextEND, g).getWidth();
        int height = (int) g.getFontMetrics().getStringBounds(TextEND, g).getHeight();

        g.setColor(Color.YELLOW);
        g.drawString(TextEND, GamePanel.WIDTH / 2 - lenght / 2, GamePanel.HEIGHT / 4);


        g.setFont(new Font("consolas", Font.BOLD, 29));
        String s = Textscore + score;
        int lenght1 = (int) g.getFontMetrics().getStringBounds(s, g).getWidth();
        g.drawString(s, GamePanel.WIDTH / 2 - lenght1 / 2, GamePanel.HEIGHT / 2);

        g.drawRect(GamePanel.WIDTH / 2 - buttonwidht / 2, GamePanel.HEIGHT /2+ buttonheight*2+ buttonheight,
                buttonwidht, buttonheight);
        g.setColor(new Color(239,245,64,transp));
        g.fillRect(GamePanel.WIDTH / 2 - buttonwidht / 2, GamePanel.HEIGHT /2+ buttonheight*2+ buttonheight,
                buttonwidht, buttonheight);
        g.setColor(Color.BLACK);

        int lenght2 = (int) g.getFontMetrics().getStringBounds(buttonText, g).getWidth();
        g.drawString(buttonText, GamePanel.WIDTH / 2 - lenght2 / 2,
                GamePanel.HEIGHT / 2 + buttonheight*4-buttonheight/4);


    }
}
