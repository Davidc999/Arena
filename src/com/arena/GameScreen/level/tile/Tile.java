package com.arena.GameScreen.level.tile;

import com.arena.graphics.Screen;
import com.arena.graphics.Sprite;

public class Tile {
    public int x, y;
    public Sprite sprite;
    public boolean solid;

    public static Tile grass = new GrassTile(Sprite.grass);
    public static Tile voidTile = new VoidTile(Sprite.voidSprite);

    public Tile(Sprite sprite){
        this.sprite = sprite;
        solid = false;
    }

    public void render(int x, int y, Screen screen){
        screen.renderSprite(x * sprite.WIDTH, y * sprite.HEIGHT,this.sprite);
    }

}
