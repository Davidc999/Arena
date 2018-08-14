package com.arena.entity;

import com.arena.graphics.Screen;
import com.arena.graphics.Sprite;

public class CastleFloor extends Entity{
    public Sprite sprite;

    public CastleFloor(double x, double y, Sprite sprite){
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    public void render(Screen screen){
        screen.renderSprite((int)x , (int)y ,sprite);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
