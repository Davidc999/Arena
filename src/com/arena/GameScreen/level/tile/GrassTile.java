package com.arena.GameScreen.level.tile;

import com.arena.graphics.Screen;
import com.arena.graphics.Sprite;

public class GrassTile extends Tile {

    public GrassTile(Sprite sprite){
        super(sprite);
    }

    public void render(int x, int y, Screen screen){
        screen.renderSprite(x << 4, y << 4,this.sprite);
    }
}
