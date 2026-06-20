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
    Tile[] tile;
    int mapTileNum[][];
    
    
    public TileManager(GamePanel gp){
        
        this.gp = gp;
        
        tile = new Tile[10];
        mapTileNum =new int [gp.maxScreenCol][gp.maxScreenRow];
        
        getTileImage();
        loadMap();
        
    }
    
    public void getTileImage(){
        
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Grass.png"));
            
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Wall.png"));
            
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Water.png"));
            
            
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
     public void loadMap(){
         
         try{
             InputStream is = getClass().getResourceAsStream("/Maps/Maps01.txt");
             BufferedReader br = new BufferedReader(new InputStreamReader(is));
         
         int col = 0;
         int row = 0;
         
         while(col < gp.maxScreenCol && row < gp.maxScreenRow){
             
             String line = br.readLine();
             
             while(col < gp.maxScreenCol){
                 
                 String numbers[] = line.split(" ");
                 
                 int num = Integer.parseInt(numbers[col]);
                 
                 mapTileNum[col][row] = num;
                 col++;
                 
             }
             if(col == gp.maxScreenCol){
                 col = 0;
                 row++;
                 
             
                 
             }
         }
         br.close();
         
         }catch(Exception e){
             
         }
     }
    
    public void draw(Graphics2D g2){
        
       int col = 0;
       int row = 0;
       int X = 0;
       int Y = 0;
       
       while(col < gp.maxScreenCol && row < gp.maxScreenRow){
           
           int tileNum = mapTileNum[col][row];
           
           g2.drawImage(tile[tileNum].image, X, Y, gp.tileSize, gp.tileSize, null);
           col++;
           X += gp.tileSize;
           
           if(col == gp.maxScreenCol){
               col = 0;
               X = 0;
               row++;
               Y +=gp.tileSize;
           }
       }
               
      
      
    }

  
    }
    
