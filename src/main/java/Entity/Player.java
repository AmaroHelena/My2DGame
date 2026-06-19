package Entity;

import Main.GamePanel;
import Main.KeyHandler;
import java.awt.Color;
import java.awt.Graphics2D;

public class Player extends Entity {
    
    GamePanel gp;
    KeyHandler keyH;
    
    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        
        setDefaultValues();
        
    }
    
    public void setDefaultValues(){
        
        X = 100;
        Y = 100;
        Speed = 4;
        
    }
    
    public void update(){
        
        // Estrutura condicional para movimentação direcional não-simultânea
        if (keyH.upPressed) {
            Y -= Speed;
        } else if (keyH.downPressed) {
            Y += Speed;
        } else if (keyH.leftPressed) {
           X -= Speed;
        } else if (keyH.rightPressed) {
           X += Speed;
        }
    }
    
   public void draw(Graphics2D g2) {
        // Desenha o jogador (Retângulo branco representando temporariamente o Sprite)
        g2.setColor(Color.white);
        g2.fillRect(X, Y, gp.tileSize, gp.tileSize);
    }
}



