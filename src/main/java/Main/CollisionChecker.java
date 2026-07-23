
package Main;

import Entity.Entity;

public class CollisionChecker {
    
    GamePanel gp;
    
    public CollisionChecker(GamePanel gp){
        this.gp = gp;
        
    }
    
    public void checkTile (Entity entity){
        
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
                
        
        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol =  entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;
        
        int tileNum1, tileNum2;
        
        switch(entity.direction){
       case "up":
    entityTopRow = (entityTopWorldY - entity.Speed)/gp.tileSize;
    tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
    tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
    
    // Se qualquer um dos dois tiles acima da entidade tiver colisão ativada
    if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
        entity.collisionOn = true; // Ativa a colisão na entidade
    }
    break;
            case "down":
                  entityTopRow = (entityBottomWorldY + entity.Speed)/gp.tileSize;
    tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
    tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
    
    // Se qualquer um dos dois tiles acima da entidade tiver colisão ativada
    if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
        entity.collisionOn = true; // Ativa a colisão na entidade
    }
                break;
                
            case "left":
              entityLeftCol = (entityLeftWorldX - entity.Speed)/gp.tileSize;
    tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
    tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
    
    // Se qualquer um dos dois tiles acima da entidade tiver colisão ativada
    if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
        entity.collisionOn = true; // Ativa a colisão na entidade
    }
                break;
                
            case "right":
              entityRightCol = (entityRightWorldX - entity.Speed)/gp.tileSize;
    tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
    tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
    
    // Se qualquer um dos dois tiles acima da entidade tiver colisão ativada
    if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
        entity.collisionOn = true; // Ativa a colisão na entidade
    }
                break;
        }
    }
}
        
    

 
    
    
    

