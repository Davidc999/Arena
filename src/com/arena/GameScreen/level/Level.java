package com.arena.GameScreen.level;

import com.arena.GameScreen.GameScreen;
import com.arena.entity.CollidableEntity;
import com.arena.entity.Entity;
import com.arena.entity.mob.Castle;
import com.arena.entity.mob.Player;
import com.arena.graphics.Screen;
import com.arena.graphics.SpriteSheet;
import com.arena.graphics.TiledSpriteSheet;
import com.arena.GameScreen.level.tile.Tile;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Level extends GameScreen{

    protected int width, height;
    protected int[] tiles;
    protected boolean castleBuilt = false;
    protected static Castle castle;
    protected static Player player;

    private ArrayList<CollidableEntity> collidableEntityList = new ArrayList<>();
    private ArrayList<Entity> entityList = new ArrayList<>();

    public Level( int width, int height){
        super();
        this.width = width;
        this.height = height;
        tiles = new int[width * height];
        generateLevel();
    }

    public Level( int width, int height, String path){
        this.width = width;
        this.height = height;
        tiles = new int[width * height];
        loadLevel(path);
    }

    public Level(String path){
        loadLevel(path);
    }

    protected void generateLevel(){

    }

    public void loadLevel(String path){
            try{
                byte[] fileArray;
                fileArray = Files.readAllBytes(Paths.get(path));

                IntBuffer intBuf = ByteBuffer.wrap(fileArray).order(ByteOrder.BIG_ENDIAN).asIntBuffer();
                intBuf.get(tiles);

            }
            catch (IOException x){
                System.out.println("Can't load for some reason!");
            }

    }

    public void update(){

        for (int first = 0; first < collidableEntityList.size() ; first++){
            for(int second = first + 1; second < collidableEntityList.size() ; second++){
                if(collidableEntityList.get(first).isCollidingWith(collidableEntityList.get(second))) {
                    collidableEntityList.get(first).handleCollision(collidableEntityList.get(second));
                    collidableEntityList.get(second).handleCollision(collidableEntityList.get(first));
                }
            }
        }

        for (int i = 0; i < collidableEntityList.size(); i++ ){
            CollidableEntity e = collidableEntityList.get(i);

            e.update();
            if(e.isRemoved())
            {
                collidableEntityList.remove(e);
            }
        }

        for (int i = 0; i < entityList.size(); i++ ){
            Entity e = entityList.get(i);
            e.update();
            if(e.isRemoved())
            {
                entityList.remove(e);
            }

        }

    }

    private void time(){

    }

    public void render(Screen screen){

        xScroll = player.x - screen.width / 2;
        yScroll = player.y - screen.height / 2;

        screen.setOffset(xScroll, yScroll);
        int x0 = (xScroll >> 4) -1;
        int x1 = ((xScroll + screen.width) >> 4) +1;
        int y0 = (yScroll >> 4) -1;
        int y1 = ((yScroll + screen.height) >> 4) +1;

        for (int y = y0; y < y1; y++){
            for (int x = x0; x < x1; x++){
                getTile(x,y).render(x,y,screen);
            }
        }
        for (int i = 0; i < collidableEntityList.size(); i++ ){
            collidableEntityList.get(i).render(screen);
        }

        for (int i = 0; i < entityList.size(); i++ ){
            entityList.get(i).render(screen);
        }

    }

    public void addEntity(CollidableEntity collidableEntity){
        collidableEntity.init(this);
        collidableEntityList.add(collidableEntity);
    }

    public void addEntity(Entity entity){
        entity.init(this);
        entityList.add(entity);
    }

    public void addEntity(Player player){
        player.init(this);
        collidableEntityList.add(player);
        this.player = player;
    }

    public int getPlayerX(){ return player.x;}
    public int getPlayerY(){return  player.y;}

    public Tile getTile(int x, int y){
        if(x<0 || x >= this.width || y<0 || y >= this.height) { return Tile.voidTile; } // Handle walking out of bounds
        return TiledSpriteSheet.tileSheet.tiles[tiles[x + y * this.width]];
    }

    //TODO: Fix tile collision! Mob uses something different, Projectile uses this. Both should use the new bounding boxes...
    public boolean tileCollision(double entityX, double entityY,double entityHeight, int entityWidth, double xChange, double yChange){
        for(int c=0; c<4; c++)
        {
            double xTarget = (entityX+ (c%2)*entityWidth + xChange)/ SpriteSheet.tiles.TILESIZE;
            double yTarget = (entityY+(c/2)*entityHeight+yChange)/SpriteSheet.tiles.TILESIZE;
            if(getTile((int)xTarget,(int)yTarget).solid) {
                return true;
            }
        }
        return false;
    }

    public void buildCastleReq(int x, int y){
        if(!castleBuilt) {
            castle = new Castle(x, y - 32);
            addEntity(castle);
            castleBuilt = true;
        }
        else{ // TODO: Check if eligible for upgrade and if player is close to the castle...
            if(castle.isActive() && player.getXpLevel() > castle.getXpLevel())
                castle.upgrade();
        }

    }

    public static int getCastleLevel(){
        if(castle == null)
            return 0;
        else
            return castle.getXpLevel();
    }

    public void setCastleBuilt(boolean castleBuilt) {
        this.castleBuilt = castleBuilt;
    }

    public boolean getCastleBuilt(){ return castleBuilt;}
}

