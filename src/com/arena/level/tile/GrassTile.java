package com.arena.level.tile;

import com.arena.graphics.Screen;
import com.arena.graphics.Sprite;

import java.util.Spliterator;

public class GrassTile extends Tile {

    public GrassTile(Sprite sprite){
        super(sprite);
    }

    public void render(int x, int y, Screen screen){
        screen.renderTile(x << 4, y << 4,this);
    }
}
