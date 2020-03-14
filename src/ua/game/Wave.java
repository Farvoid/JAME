package ua.game;

import java.awt.*;

public class Wave {
    //fields
    public  static int waveNumber;
    private int waveMultiplier;
    private String waveText;
    private long waveTimer;
    private long waveDelay;
    private long waveTimerDiff;
    private String s;



    //Consctuctor
    public Wave() {
        waveNumber = 1;
        waveMultiplier =6;

        waveTimer = 0;
        waveDelay = 5000;
        waveTimerDiff = 0;


        waveText = "B O Л Н А - ";


    }


    public void createEnemies() {
        int enemyCount = waveNumber * waveMultiplier;



        if (waveNumber < 3) {
            while (enemyCount > 0) {
                int rank = 1;
                int type = 1;
                GamePanel.enemies.add(new Enemy(type, rank));
                enemyCount -= (type * rank)*2;
            }



        }
        if (waveNumber ==3) {
            while (enemyCount > 0) {


                int rank = 2;
                int type = 1;
                GamePanel.enemies.add(new Enemy(type, rank));
                enemyCount -= type * rank;
            }
        }
        if (waveNumber ==4) {
            while (enemyCount > 0) {


                int rank = 1;
                int type = 2;
                GamePanel.enemies.add(new Enemy(type, rank));
                enemyCount -= type * rank;
            }
        }
        if (waveNumber ==5) {
            while (enemyCount > 0) {


                int rank = 1;
                int type = 2;
                GamePanel.enemies.add(new Enemy(type, rank));
                enemyCount -= type * rank;

            }

        }
        if (waveNumber ==5) {



                int rank = 2;
                int type = 2;
                GamePanel.enemies.add(new Enemy(type, rank));


            }
        if(waveNumber ==6){
            GamePanel.state = GamePanel.STATES.WINNER;

        }




        waveNumber++;

    }


    //fuction
    public void update() {
        if (GamePanel.enemies.size() == 0 && waveTimer == 0) {
            waveTimer = System.nanoTime();

        }
        if (waveTimer > 0) {
            waveTimerDiff += (System.nanoTime() - waveTimer) / 1000000;
            waveTimer = System.nanoTime();
        }
        if (waveTimerDiff > waveDelay) {
            createEnemies();
            waveTimer = 0;
            waveTimerDiff = 0;
        }
    }

    public boolean showWave() {
        if (waveTimer != 0) {
            return true;
        } else return false;
    }

    public void draw(Graphics2D g) {
        double divider = waveDelay / 180;
        double alpha = waveTimerDiff / divider;
        alpha = 255 * Math.sin(Math.toRadians(alpha));
        if (alpha < 0) alpha = 0;
        if (alpha > 255) alpha = 255;
        g.setFont(new Font("consolas", Font.BOLD, 20));
        g.setColor(new Color(66, 255, 255, (int) alpha));

        s = waveText + waveNumber;
        if(waveNumber==5){s="Boss";}

        long lenght = (int) g.getFontMetrics().getStringBounds(s, g).getWidth();
        g.drawString(s, GamePanel.WIDTH / 2 - (int) (lenght / 2), GamePanel.HEIGHT / 2);


    }
}
