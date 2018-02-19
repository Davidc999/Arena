package com.arena.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteSheet {

    private String path;
    public final int SIZE;
    public final int TILESIZE;
    public int[] pixels;
    //static File directory = new File("res/textures/spritesheet.png");
    public static SpriteSheet tiles = new SpriteSheet("/textures/overworld.png",336, 16);
    public static SpriteSheet maleWizard = new SpriteSheet("/textures/malewizard.png",128, 16);
    public static SpriteSheet arrow = new SpriteSheet("/textures/Arrow.png",128, 32);

    public SpriteSheet(String path, int size, int tileSize){
        this.path = path;
        SIZE = size;
        TILESIZE = tileSize;
        pixels = new int[SIZE * SIZE];
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
