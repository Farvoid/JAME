package ua.game;

import java.awt.*;

public class Winner {
    private int buttonwidht;
    private int buttonheight;
    private String Winner;
    private String yourscore;
    private String tryagain;
    private int trasp;

    public Winner() {
        Winner = "W I N N E R";
        yourscore = "Y O U R  P O I N T S  ";
        tryagain = "N E W  G A M E";
        buttonheight = 80;
        buttonwidht = 300;
        trasp = 0;

    }

    public void update() {
        if (GamePanel.mouseX > GamePanel.WIDTH / 2 - buttonwidht / 2
                && GamePanel.mouseX < GamePanel.WIDTH / 2 + buttonwidht / 2
                && GamePanel.mouseY > GamePanel.HEIGHT - buttonheight * 2
                && GamePanel.mouseY < GamePanel.HEIGHT - buttonheight * 1) {
            if (GamePanel.leftMouse) {
                GamePanel.state = GamePanel.STATES.MENUE;
                GamePanel.player.healing();
                GamePanel.enemies.clear();
                Wave.waveNumber = 1;
                Player.x = GamePanel.WIDTH/2;
                Player.y = GamePanel.HEIGHT/2;
                EndScreen.score= 0;

            }
            trasp = 60;


        } else {
            trasp = 0;
        }

    }

    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("consals", Font.PLAIN, 80));
        int lenght = (int) g.getFontMetrics().getStringBounds(Winner, g).getWidth();
        g.drawString(Winner, GamePanel.WIDTH / 2 - lenght / 2, GamePanel.HEIGHT / 2 - buttonheight * 3);
        String s = yourscore + EndScreen.score;

        g.setFont(new Font("consals", Font.PLAIN, 40));
        int lenght1 = (int) g.getFontMetrics().getStringBounds(s, g).getWidth();
        g.drawString(s, GamePanel.WIDTH / 2 - lenght1 / 2, GamePanel.HEIGHT / 2 - buttonheight / 2);

        g.setFont(new Font("consals", Font.BOLD, 20));
        int lenght2 = (int) g.getFontMetrics().getStringBounds(tryagain, g).getWidth();
        g.drawString(tryagain, GamePanel.WIDTH / 2 - lenght2 / 2, GamePanel.HEIGHT - buttonheight - buttonheight / 2 + buttonheight / 10);
        g.setColor(new Color(8, 26, 255, trasp));
        g.fillRect(GamePanel.WIDTH / 2 - buttonwidht / 2, GamePanel.HEIGHT - buttonheight * 2, buttonwidht, buttonheight);

        g.setStroke(new BasicStroke(4));
        g.setColor(Color.BLACK);
        g.drawRect(GamePanel.WIDTH / 2 - buttonwidht / 2, GamePanel.HEIGHT - buttonheight * 2, buttonwidht, buttonheight);
        g.setStroke(new BasicStroke(1));


    }

}
