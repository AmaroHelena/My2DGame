package Entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Entity {
    
    // Coordenadas posicionais da entidade no plano 2D
    public int worldX, worldY;
    
    // Vetor de velocidade de deslocamento por frame
    public int Speed;
    
    // Buffers de imagem para armazenamento dos sprites direcionais
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    
    // Estado de orientação direcional da entidade
    public String direction;
    
    // Contadores para controle e sincronização da animação por quadros
    public int spriteCounter = 0;
    public int spriteNum = 1; // Corrigido de 'sprinteNum' para 'spriteNum'
    
    public Rectangle solidArea;
    public boolean collisionOn = false;
}
