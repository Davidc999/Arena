package com.arena.entity.mob;

import com.arena.entity.Entity;
import com.arena.graphics.Sprite;

import static com.arena.entity.Entity.Direction.*;

public abstract class Mob extends Entity {

    protected Sprite sprite;
    protected Direction dir = UP;
    protected boolean moving = false;

    public void move(int xChange, int yChange){
        //
        if(xChange > 0){
            dir = RIGHT;
        }
        if(xChange < 0){
            dir = LEFT;
        }
        if(yChange > 0){
            dir = DOWN;
        }
        if(yChange < 0){
            dir = UP;
        }
        if (!collision()) {
            x += xChange;
            y += yChange;
        }
    }

    public void update(){

    }

    public boolean collision(){
        return false;
    }

    public void render(){}

}
