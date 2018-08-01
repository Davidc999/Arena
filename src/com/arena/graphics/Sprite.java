package com.arena.graphics;

public class Sprite {

    public final int WIDTH,HEIGHT;
    protected int x, y;
    public int[] pixels, origPixels;
    private SpriteSheet sheet;
    private BoundingBox collisionBox;


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
        origPixels = new int[squareSize * squareSize];
        this.x = x * squareSize;
        this.y = y * squareSize;
        this.sheet = sheet;
        load();
        setColisionBox(0,0,squareSize,squareSize);
    }

    public Sprite(int width, int height, int x, int y, SpriteSheet sheet){
        WIDTH = width;
        HEIGHT = height;
        pixels = new int[width * height];
        origPixels = new int[width * width];
        this.x = x * width;
        this.y = y * height;
        this.sheet = sheet;
        load();
        setColisionBox(0,0, height,width);
    }

    public Sprite(int squareSize, int colour){
        WIDTH = HEIGHT = squareSize;
        pixels = new int[squareSize * squareSize];
        origPixels = new int[squareSize * squareSize];
        setColour(colour);
        setColisionBox(0,0,squareSize,squareSize);
    }

    private void setColour(int colour){
        for (int i = 0; i < WIDTH*HEIGHT; i++){
        pixels[i] = colour;
        origPixels[i] = colour;
        }
    }

    protected void load(){
        for (int y =0 ; y<HEIGHT; y++){
            for (int x =0 ; x<WIDTH; x++) {
                pixels[x + y * HEIGHT] = sheet.pixels[x + this.x + (y + this.y) * sheet.SIZE];
                origPixels[x + y * HEIGHT] = sheet.pixels[x + this.x + (y + this.y) * sheet.SIZE];
            }
        }
    }

    public void setColisionBox(int x, int y, int height, int width){
        collisionBox = new BoundingBox(x,y,height,width);
    }

    public void effectPaint(int color){
        for (int y =0 ; y<HEIGHT; y++){
            for (int x =0 ; x<WIDTH; x++) {
                if(pixels[x + y * HEIGHT] != alphaColor)
                    pixels[x + y * HEIGHT] = color;
            }
        }
    }

    public void effectNetOverlay(int color){
        for (int y =0 ; y<HEIGHT; y++){
            for (int x =y%2 ; x<WIDTH; x+=2) {
                if(pixels[x + y * HEIGHT] != alphaColor)
                    pixels[x + y * HEIGHT] = color;
            }
        }
    }

    public void effectAdjustHue(double amount, char RGB){
        for (int y =0 ; y<HEIGHT; y++){
            for (int x =0 ; x<WIDTH; x++) {
                if(pixels[x + y * HEIGHT] != alphaColor) {
                    pixels[x + y * HEIGHT] = adjustHue(pixels[x + y * HEIGHT] ,amount,RGB);
                }
            }
        }
    }

    private int adjustHue(int origColor, double amount, char RGB){
        int byteInd = 0;
        switch (RGB){
            case 'R':
                byteInd = 2;
                break;
            case 'G':
                byteInd = 1;
                break;
            default:
                break;
        }
        int colorVal;
        colorVal = getByte(origColor,byteInd);
        colorVal = (int)(colorVal * amount);
        if(colorVal > 0xff)
            colorVal = 0xff;
        return setByte(origColor ,byteInd,colorVal);

    }

    private int getByte(int color, int byteInd){ // 3 2 1 0 = ARGB for int
        int mask = (1<<8) - 1;
        return (color >> (byteInd * 8)) & mask;
    }

    private int setByte(int color,int byteInd, int byteVal){
        int mask = (1<<8) - 1;
        mask = ~(mask << (byteInd * 8));
        return color & mask | (byteVal << (byteInd * 8));
    }

    public void restore(double heightPercent){
        for (int y = HEIGHT-1 ; y >= (int)((HEIGHT -1)*(1-heightPercent)); y--){
            for (int x =0 ; x<WIDTH; x++) {
                pixels[x + y * HEIGHT] = origPixels[x + y * HEIGHT];
            }
        }
    }

    public BoundingBox getCollisionBox() {
        return collisionBox;
    }
}
