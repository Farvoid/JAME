package ua.game;

import java.awt.*;

public class Menue {
    //Fields
    private int buttonWidth;
    private int buttonHeight;
    private Color color1button;
    String buttonText;
    int transp;
    int transpsetting;


    String buttonTextSetting;
    private String Gamename;




    //Construction
    public  Menue(){
        buttonWidth =250;
        buttonHeight= 50;
        color1button= Color.YELLOW;
        buttonText = "P L A Y";


        buttonTextSetting = "S E T T I N G S";
        Gamename = "J G A M E";

    }

    //Function
    public  void update(){
        //play
        if(GamePanel.mouseX > GamePanel.WIDTH/2-buttonWidth/2
                && GamePanel.mouseX<GamePanel.WIDTH/2 + buttonWidth/2
                && GamePanel.mouseY>GamePanel.HEIGHT/2-buttonHeight/2
                && GamePanel.mouseY<GamePanel.HEIGHT/2+buttonHeight/2){
             transp = 60;
            if(GamePanel.leftMouse){
                   Listeners.Pause = 2;
                GamePanel.state = GamePanel.STATES.PLAY;

            }

        }else{
            transp=0;
        }
        //settings
        if (GamePanel.mouseX > GamePanel.WIDTH/2-buttonWidth/2
                && GamePanel.mouseX<GamePanel.WIDTH/2 + buttonWidth/2

                && GamePanel.mouseY>GamePanel.HEIGHT/2+buttonHeight/2+buttonHeight/4
                && GamePanel.mouseY<GamePanel.HEIGHT/2+buttonHeight+buttonHeight
                ){


            transpsetting=60;
            if(GamePanel.leftMouse){
                GamePanel.state = GamePanel.STATES.SETTINGS;
            }
        }else{
            transpsetting=0;

        }

    }


    public void draw(Graphics2D g){
        //button play
        g.setColor(Color.red);
        g.setFont(new Font("consolas",Font.BOLD,60));
        long lenghtname = (int)g.getFontMetrics().getStringBounds(Gamename,g).getWidth();
        g.drawString(Gamename,GamePanel.WIDTH/2-(int)(lenghtname/2),100);


        g.setColor(color1button);
        g.setStroke(new BasicStroke(3));
        g.drawRect(GamePanel.WIDTH/2-buttonWidth/2,GamePanel.HEIGHT/2-buttonHeight/2,buttonWidth,buttonHeight);
        g.setColor(new Color(255,255,25 ,transp));
        g.fillRect(GamePanel.WIDTH/2-buttonWidth/2,GamePanel.HEIGHT/2-buttonHeight/2,buttonWidth,buttonHeight);

        g.setColor(new Color(255,255,25));
        g.setFont(new Font("consolas",Font.BOLD,29));
        long lenght = (int)g.getFontMetrics().getStringBounds(buttonText,g).getWidth();
        g.drawString(buttonText,GamePanel.WIDTH/2-(int)(lenght/2),GamePanel.HEIGHT/2+ buttonHeight/4);

        //setting

        g.drawRect((GamePanel.WIDTH/2-buttonWidth/2),
                GamePanel.HEIGHT/2+buttonHeight/2+buttonHeight/4,buttonWidth,buttonHeight);

        g.setFont(new Font ("consolas",Font.BOLD,29));
        long lenghtsetting = (int)g.getFontMetrics().getStringBounds(buttonTextSetting,g).getWidth();
        g.drawString(buttonTextSetting,GamePanel.WIDTH/2-(int)lenghtsetting/2,
                GamePanel.HEIGHT/2+buttonHeight+buttonHeight/2);
        g.setColor(new Color(255,255,25 ,transpsetting));
        g.fillRect((GamePanel.WIDTH/2-buttonWidth/2),
                GamePanel.HEIGHT/2+buttonHeight/2+buttonHeight/4,buttonWidth,buttonHeight);

        g.setStroke(new BasicStroke(1));

    }

}
