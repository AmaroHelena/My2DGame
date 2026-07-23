package Tile;

import java.awt.image.BufferedImage;

/**
 * Representa um bloco (tile) individual do cenário do jogo.
 * Funciona como um modelo de dados (Data Object) para armazenar as propriedades de cada tipo de bloco.
 */
public class Tile {
    
    // Armazena a textura/imagem rasterizada que será renderizada na tela
    public BufferedImage image;
    
    // Define se o bloco possui propriedades de colisão (ex: paredes, água intransponível)
    // Inicializado como 'false' por padrão (passável)
    public boolean collision = false;
    
}