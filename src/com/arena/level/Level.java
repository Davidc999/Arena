package com.arena.level;

import com.arena.entity.Entity;
import com.arena.graphics.Screen;
import com.arena.graphics.SpriteSheet;
import com.arena.graphics.TiledSpriteSheet;
import com.arena.level.tile.Tile;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.channels.ByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Level {

    protected int width, height;
    protected int[] tiles;

    private List<Entity> entityList = new ArrayList<>();

    public Level( int width, int height){
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

        for (int i= 0 ; i < entityList.size(); i++ ){
            Entity e = entityList.get(i);
            if(e.isRemoved())
            {
                entityList.remove(e);
            }
            else {
                e.update();
            }
        }

    }

    private void time(){

    }

    public void render(int xScroll, int yScroll, Screen screen){
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
        for (int i= 0 ; i < entityList.size(); i++ ){
            entityList.get(i).render(screen);
        }

    }

    public void addEntity(Entity entity){
        entity.init(this);
        entityList.add(entity);
    }

    public Tile getTile(int x, int y){
        if(x<0 || x >= this.width || y<0 || y >= this.height) { return Tile.voidTile; } // Handle walking out of bounds
        return TiledSpriteSheet.tileSheet.tiles[tiles[x + y * this.width]];
    }

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
}

