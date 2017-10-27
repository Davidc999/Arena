package com.arena.entity.mob;

import com.arena.entity.Entity;
import com.arena.graphics.AnimatedSprite;
import com.arena.graphics.Sprite;

import static com.arena.entity.Entity.Direction.*;

public abstract class Mob extends Entity {

    protected AnimatedSprite animatedSprite;
    protected Direction dir = UP;
    protected boolean moving = false;

    public void move(int xChange, int yChange){
        Direction newDir = DOWN;
        if(xChange > 0){
            newDir = RIGHT;
        }
        if(xChange < 0){
            newDir = LEFT;
        }
        if(yChange > 0){
            newDir = DOWN;
        }
        if(yChange < 0){
            newDir = UP;
        }
        if (dir != newDir) {
            dir = newDir;
            animatedSprite.setAnimation(dir);
        }

        if (!collision()) {
            x += xChange;
            y += yChange;
            animatedSprite.start();
        }
    }

    public void update(){

    }

    public boolean collision(){
        return false;
    }

    public void render(){}

}
