package ua.game;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;


public class GamePanel extends JPanel implements Runnable {

    //Field
    public static int WIDTH = 1000;
    public static int HEIGHT = 1000;

    public static int mouseX;
    public static int mouseY;
    public static boolean leftMouse;


    private BufferedImage image;
    private Graphics2D g;


    private int FPS;
    private double millistoFPS;
    private long timerFPS;
    private int sleepTime;


    public enum STATES {
        MENUE,
        PLAY,
        ENDSCREEN,
        SETTINGS,
        WINNER


    }

    public static STATES state = STATES.MENUE;

    public static Player player;
    public static ArrayList<Bullet> bullets;
    public static ArrayList<Enemy> enemies;
    public static Wave wave;
    public static Menue menue;
    public static Settings settings;
    public static Winner winner;
    public static EndScreen endscreen;
    public static ArrayList<Bonus> bonus;


    private Thread thread = new Thread(this);
    public static GameBack background;


    //Constructor


    public void start() {


        thread = new Thread(this);
        thread.start();

    }

    public GamePanel() {
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();

        addKeyListener(new Listeners());
        addMouseListener(new Listeners());
        addMouseMotionListener(new Listeners());
    }

    public void run() {


        FPS = 30;
        millistoFPS = 1000 / FPS;
        sleepTime = 0;

        leftMouse = false;
        background = new GameBack();
        player = new Player();
        bullets = new ArrayList<Bullet>();
        enemies = new ArrayList<Enemy>();
        wave = new Wave();
        menue = new Menue();
        winner = new Winner();
        settings = new Settings();
        endscreen = new EndScreen();
        bonus = new ArrayList<Bonus>();


        Toolkit kit = Toolkit.getDefaultToolkit();
        BufferedImage buffered = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g3 = (Graphics2D) buffered.getGraphics();
        g3.setColor(Color.BLACK);

        g3.drawLine(5, 0, 5, 10);
        g3.drawLine(0, 5, 10, 5);
        Cursor Customcursor = kit.createCustomCursor(buffered, new Point(10, 10), "My curcor");
        g3.dispose();


        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        while (true) {
            this.setCursor(Customcursor);

            timerFPS = System.nanoTime();
            if (state.equals(STATES.MENUE)) {

                background.update();
                background.draw(g);
                menue.update();
                menue.draw(g);
                gameDraw();


            }
            if (state.equals(STATES.PLAY)) {
                if (Listeners.Pause % 2 == 0) {
                    gameUpdate();
                    gameRender();


                }
                gameDraw();


            }
            if (state.equals(STATES.SETTINGS)) {
                background.update();
                background.draw(g);
                settings.update();
                settings.draw(g);

                gameDraw();
            }
            if (state.equals(STATES.ENDSCREEN)) {
                background.update();
                background.draw(g);
                endscreen.update();
                endscreen.draw(g);
                gameDraw();
            }
            if(state.equals(STATES.WINNER)){
                background.update();
                background.draw(g);
                winner.update();
                winner.draw(g);
                gameDraw();
            }


            timerFPS = (System.nanoTime() - timerFPS) / 1000000;
            if (millistoFPS > timerFPS) {
                sleepTime = (int) (millistoFPS - timerFPS);

            } else sleepTime = 1;

            try {

                Thread.sleep(sleepTime);
                //System.out.println(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timerFPS = 0;
            sleepTime = 1;


        }


    }

    public void gameRender() {

        // background
        background.draw(g);
        //player
        player.draw(g);
        //bullets
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).draw(g);
        }
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).draw(g);
        }
        //bonus

        for (int i = 0; i < bonus.size(); i++) {
            bonus.get(i).draw(g);
        }


        if (wave.showWave()) {
            wave.draw(g);
        }
    }


    public void gameUpdate() {

        //background
        background.update();
        //player
        player.update();
        //bullets
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).update();
            boolean remove = bullets.get(i).remove();
            if (remove) {
                bullets.remove(i);
                i--;
            }

        }
        //enemi

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
        }
        //bonus

        for (int i = 0; i < bonus.size(); i++) {
            bonus.get(i).update();
        }


        //Bullets enemies collide
        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            double ex = e.getX();
            double ey = e.getY();

            for (int j = 0; j < bullets.size(); j++) {
                Bullet b = bullets.get(j);
                double bx = b.getX();
                double by = b.getY();
                double dx = ex - bx;
                double dy = ey - by;
                double dist = Math.sqrt(dx * dx + dy * dy);
                if ((int) dist <= e.getR() + b.getR()) {
                    e.hit();
                    EndScreen.score += 10;

                    bullets.remove(j);
                    j--;
                    boolean remove = e.remove();
                    if (remove) {
                        Bonus.randomStart = true;
                        int ruletka = Bonus.chanceBonus;
                        System.out.println(Bonus.chanceBonus);
                        enemies.remove(i);
                        //bonus
                        Bonus.spawnX = (int) e.getX();
                        Bonus.spawnY = (int) e.getY();
                        while (Bonus.randomStart) {
                            Random random = new Random();
                            Bonus.chanceBonus = random.nextInt(15);
                            System.out.println("numver" + Bonus.chanceBonus);
                            Bonus.randomStart = false;
                        }
                        if (ruletka < 10) {
                            GamePanel.bonus.add(new Bonus());

                        }
                        i--;
                        break;
                    }

                }
            }


        }
        //Player-enemy collides
        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            double ex = e.getX();
            double ey = e.getY();

            double px = player.getX();
            double py = player.getY();
            double dy = ey - py;
            double dx = ex - px;
            double dist = Math.sqrt(dx * dx + dy * dy);
            if ((int) dist <= e.getR() + player.getR()) {
                e.hit();
                player.hit();
                boolean remove = e.remove();
                if (remove) {

                    enemies.remove(i);


                    i--;
                }
            }
        }
        //Bonus collides
        for (int i = 0; i < bonus.size(); i++) {
            Bonus b = bonus.get(i);
            double bx = Bonus.spawnX;

            double by = Bonus.spawnY;
            double px = player.getX();
            double py = player.getY();

            double distanceX = bx - px;

            double distanceY = by - py;
            double dist1 = Math.sqrt(distanceX * distanceX + distanceY * distanceY);


            if ((int) dist1 <= b.getR() + player.getR()) {


                Bonus.colide = true;
                Random random = new Random();
                int effect = random.nextInt(4) + 1;

                while (Bonus.colide) {
                    if (effect == 1 && Player.speed <= 10) {
                        player.speedboost();

                    }
                    if (effect == 2 && Player.health < 30) {
                        player.doublehealth();
                    }

                    if (effect == 3 && Enemy.bonusdamege < 10) {
                        Enemy.bonusdamege += 1;
                    }

                    Bonus.colide = false;
                    if (effect == 4 && Bullet.speed < 30) {
                        Bullet.speed += +2;
                    }

                }

                GamePanel.bonus.remove(i);
                break;

            }


        }

        //wave update
        wave.update();
    }


    private void gameDraw() {
        Graphics g2 = this.getGraphics();
        g2.drawImage(image, 0, 0, null);

    }


}
