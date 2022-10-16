package com.arena.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteSheet {

    private String path;
    public final int HEIGHT, WIDTH;
    public final int TILE_HEIGHT, TILE_WIDTH;
    public int[] pixels;
    //static File directory = new File("res/textures/spritesheet.png");
    public static SpriteSheet tiles = new SpriteSheet("/textures/overworld.png",336, 16);
    public static SpriteSheet maleWizard = new SpriteSheet("/textures/malewizard.png",128, 16);
    public static SpriteSheet arrow = new SpriteSheet("/textures/Arrow.png",128, 32);
    public static SpriteSheet msgBox = new SpriteSheet("/textures/msgbox.png",96, 32);
    public static SpriteSheet flyMonster = new SpriteSheet("/textures/FlyMonster.png",128,32);
    public static SpriteSheet wimpyProjectile = new SpriteSheet("/textures/wimpyProjectile_s.png",6,10);
    public static SpriteSheet castleHUDImage = new SpriteSheet("/textures/castlepic.png",36,36);
    public static SpriteSheet numbers = new SpriteSheet("/textures/numbers.png",88,8);
    public static SpriteSheet castleTowers = new SpriteSheet("/textures/edited_tower.png",32*4,32);
    public static SpriteSheet heartImg = new SpriteSheet("/textures/heart.png",9,9);
    //public static SpriteSheet crystal = new SpriteSheet("/textures/Crystal_edited.png",336,117,14,26);
    public static SpriteSheet crystal = new SpriteSheet("/textures/crystal.png",72,96,12,24);

    public SpriteSheet(String path, int size, int tileSize){
        this.path = path;
        HEIGHT = size;
        WIDTH = size;
        TILE_HEIGHT = tileSize;
        TILE_WIDTH = tileSize;
        pixels = new int[WIDTH * HEIGHT];
        load();
    }

    public SpriteSheet(String path, int width, int height, int tileWidth, int tileHeight){
        this.path = path;
        HEIGHT = height;
        WIDTH = width;
        TILE_HEIGHT = tileHeight;
        TILE_WIDTH = tileWidth;
        pixels = new int[WIDTH * HEIGHT];
        load();
    }

    private void load(){
        try {
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0,0, w, h, pixels, 0 , w);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
