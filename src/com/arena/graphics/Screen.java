package com.arena.graphics;

import com.arena.level.tile.Tile;

import java.util.Random;

public class Screen {

    public int width, height;
    public int[] pixels;
    public final int MAP_SIZE = 64;
    public final int MAP_SIZE_MASK = MAP_SIZE -1;
    public int xOffset, yOffset;
    public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
    private Random random = new Random();

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width*height];

        for (int i = 0 ; i < MAP_SIZE * MAP_SIZE; i++){
            tiles[i] = random.nextInt(0xffffff);
        }
    }

    public void clear(){
        for ( int i = 0 ; i< pixels.length; i++){
            pixels[i] = 0;
        }
    }

    public void renderSprite(int xp, int yp, Sprite sprite) {
        // This method renders a sprite which is at the absolute pixel coordinate (xp,yp) ON THE MAP
        // xOffset and yOffset are the coordinates of the left corner of the CAMERA, and they are removed
        // so that the position of the sprite can be drawn relative to the camera.
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < sprite.HEIGHT; y++) {
            int ya = y + yp; // yp being yPixel, the sprite's offset in pixels on the map. y is inside the sprite.
            for (int x = 0; x < sprite.WIDTH; x++) {
                int xa = x + xp; // xp being xPixel, , the sprite's offset in pixels on the map. x is inside the sprite.
                if(xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
                int pixelColor = sprite.pixels[x+y*sprite.HEIGHT];
                if(pixelColor != Sprite.alphaColor) pixels[xa+ya*width] = pixelColor;
            }
        }
    }

    public void setOffset(int xOffset, int yOffset){
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

}


