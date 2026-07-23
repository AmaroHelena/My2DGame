package Tile;

import Main.GamePanel;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

public class TileManager {
    
    GamePanel gp;
    public Tile[] tile;            // Array de cache para armazenar os tipos de tiles disponíveis
    public int mapTileNum[][];    // Matriz bidimensional que representa o mapa (grade de colunas e linhas)
    
    public TileManager(GamePanel gp){
        
        this.gp = gp;
        
        // Inicializa o catálogo com capacidade para até 10 tipos de tiles
        tile = new Tile[10];
        
        // Instancia a matriz com base nas dimensões de tela definidas no GamePanel
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        
        getTileImage(); // Carrega as texturas dos tiles na memória
        loadMap("/Maps/World01.txt");     // Processa o arquivo de texto e popula a matriz do mapa
    }
    
    /**
     * Carrega as imagens dos assets e associa a cada índice do array de Tiles.
     */
    public void getTileImage(){
        try {
            // Índice 0: Grama
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Grass.png"));
            
            // Índice 1: Parede / Obstáculo
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Wall.png"));
           tile[1].collision = true;
            
            
            // Índice 2: Água
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Water.png"));
              tile[2].collision = true;
            
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Earth.jpeg"));
            
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Tree.jpeg"));
            
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Sand.jpeg"));
            
            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Tree_pink.jpeg"));
            
            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Tree_yellow.jpeg"));
            
        } catch(IOException e) {
            e.printStackTrace(); // Log de erro caso algum asset falhe ao ser carregado
        }
    }
    
    /**
     * Realiza o parsing do arquivo de texto recebido por parâmetro para preencher a matriz do mapa.
     */
    public void loadMap(String filePath) {
    try {
        InputStream is = getClass().getResourceAsStream("/Maps/World01.txt");;
        if (is == null) {
            System.out.println("ERRO: O arquivo " + filePath + " não foi encontrado!");
            return;
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        
        int col = 0;
        int row = 0;
        
        while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
            
            String line = br.readLine();
            if (line == null) {
                break; // Para se o arquivo terminar antes
            }
            
            // Divide a linha pelos espaços
            String numbers[] = line.split(" ");
            
            // PROTEÇÃO: O loop vai correr apenas até ao tamanho real da linha do ficheiro
            while(col < gp.maxWorldCol && col < numbers.length) {
                
                // Ignora espaços duplos ou vazios que possam existir por erro de digitação
                if (!numbers[col].trim().isEmpty()) {
                    int num = Integer.parseInt(numbers[col].trim());
                    mapTileNum[col][row] = num;
                    
                    // Mostra no console o que está a ler (podes apagar depois se quiseres)
                    System.out.print(num + " ");
                }
                col++; 
            }
            
            System.out.println(); // Pula a linha no console de teste
            
            // Vai para a próxima linha do mapa
            col = 0;
            row++;
        }
        br.close(); 
        
    } catch(Exception e) {
        System.out.println("Erro ao processar o mapa estruturado:");
        e.printStackTrace(); 
    }
}
    /**
     * Renderiza os tiles na tela com base nas posições lógicas da matriz.
     */
   public void draw(Graphics2D g2) {
    int worldCol = 0;
    int worldRow = 0;
   
    // loop corre por todo o mapa do mundo
    while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
        
        int tileNum = mapTileNum[worldCol][worldRow];
        
        // Posição do bloco no mundo (em pixels)
        int worldX = worldCol * gp.tileSize;
        int worldY = worldRow * gp.tileSize;
        
        // Calcula onde o bloco deve ser desenhado na tela em relação ao jogador
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        
        // Renderiza apenas os blocos que estão visíveis na tela para economizar memória (Opcional, mas recomendado)
        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
           worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
           worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
           worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            
            
            g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
        
        worldCol++;
        
        if(worldCol == gp.maxWorldCol) {
            worldCol = 0;
            worldRow++;
        }
    }
}
}