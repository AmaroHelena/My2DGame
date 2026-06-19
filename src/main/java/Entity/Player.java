package Entity;

import Main.GamePanel;
import Main.KeyHandler;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * Representa a entidade controlável do jogador. Gerencia entrada de dados,
 * física de movimento e animação de sprites.
 */
public class Player extends Entity {

    // Atributos de controle da animação por quadros (Frames)
    public int spriteCounter = 0;
    public int spriteNum = 1;

    // Dependências do motor do jogo
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImagem();
    }

    /**
     * Define o estado e os parâmetros físicos iniciais do jogador.
     */
    public void setDefaultValues() {
        X = 100;
        Y = 100;
        Speed = 4;
        direction = "down"; // Vetor de direção inicial
    }

    /**
     * Carrega as texturas e buffers de imagem dos sprites a partir do diretório
     * de recursos.
     */
    public void getPlayerImagem() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/Imagem/menina_costas_2.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Imagem/menina_costas_3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/Imagem/menina_frente_2.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Imagem/menina_frente_3.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/Imagem/menina_esquerda_1_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Imagem/menina_esquerda_1_3.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Imagem/menina_lado_2_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Imagem/menina_lado_2_2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Atualiza a lógica de estado do jogador (Executado a cada ciclo do Game
     * Loop).
     */
    public void update() {

        // Verifica se há alguma entrada ativa de movimento nas teclas direcionais
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {

            // Atualização dos vetores posicionais e estados de direção
            if (keyH.upPressed == true) {
                direction = "up";
                Y -= Speed;
            } else if (keyH.downPressed == true) {
                direction = "down";
                Y += Speed;
            } else if (keyH.leftPressed == true) {
                direction = "left";
                X -= Speed;
            } else if (keyH.rightPressed == true) {
                direction = "right";
                X += Speed;
            }

            // Temporizador de frames para alternância de sprites da animação
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0; // Reseta o contador de ciclo
            }
        }
    }

    /**
     * Renderiza o sprite ativo do jogador na tela com base na direção e quadro
     * de animação.
     */
    public void draw(Graphics2D g2) {

        BufferedImage imagem = null;

        // Máquina de estados para seleção do sprite correto
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    imagem = up1;
                }
                if (spriteNum == 2) {
                    imagem = up2;
                }
                break;

            case "down":
                if (spriteNum == 1) {
                    imagem = down1;
                }
                if (spriteNum == 2) {
                    imagem = down2;
                }
                break;

            case "left":
                if (spriteNum == 1) {
                    imagem = left1;
                }
                if (spriteNum == 2) {
                    imagem = left2;
                }
                break;

            case "right":
                if (spriteNum == 1) {
                    imagem = right1;
                }
                if (spriteNum == 2) {
                    imagem = right2;
                }
                break;
        }

        // Desenha a imagem final processada nas coordenadas atuais
        g2.drawImage(imagem, X, Y, gp.tileSize, gp.tileSize, null);
    }
}
