package com.arena.graphics;

import java.io.File;

public class Sprite {

    public final int WIDTH,HEIGHT;
    protected int x, y;
    public int[] pixels;
    private SpriteSheet sheet;


    public static int alphaColor = 0xffbadbad;

    // Tile sprites
    public static Sprite grass = new Sprite(16,1, 1, SpriteSheet.tiles);
    public static Sprite voidSprite = new Sprite(16,0x1B87E0);

    // Praticle sprites
    public static Sprite particle_normal = new Sprite(3,0xAAAAAA);

    public Sprite(int squareSize, int x, int y, SpriteSheet sheet){
        WIDTH = squareSize;
        HEIGHT = squareSize;
        pixels = new int[squareSize * squareSize];
        this.x = x * squareSize;
        this.y = y * squareSize;
        this.sheet = sheet;
        load();
    }

    public Sprite(int width, int height, int x, int y, SpriteSheet sheet){
        WIDTH = width;
        HEIGHT = height;
        pixels = new int[width * height];
        this.x = x * width;
        this.y = y * height;
        this.sheet = sheet;
        load();
    }

    public Sprite(int squareSize, int colour){
        WIDTH = HEIGHT = squareSize;
        pixels = new int[squareSize * squareSize];
        setColour(colour);
    }

    private void setColour(int colour){
        for (int i = 0; i < WIDTH*HEIGHT; i++){
        pixels[i] = colour;
        }
    }

    protected void load(){
        for (int y =0 ; y<HEIGHT; y++){
            for (int x =0 ; x<WIDTH; x++) {
                pixels[x + y * HEIGHT] = sheet.pixels[x + this.x + (y + this.y) * sheet.SIZE];
            }
        }
    }

}
