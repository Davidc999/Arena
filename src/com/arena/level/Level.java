package com.arena.level;

import com.arena.graphics.Screen;
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

public class Level {

    protected int width, height;
    protected int[] tiles;

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
                System.out.println("Can't save for some reason!");
            }

    }

    public void update(){

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
    }

    public Tile getTile(int x, int y){
        if(x<0 || x >= this.width || y<0 || y >= this.height) { return Tile.voidTile; } // Handle walking out of bounds
        return TiledSpriteSheet.tileSheet.tiles[tiles[x + y * this.width]];
    }
}

