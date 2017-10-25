package com.arena.entity.mob;

import com.arena.entity.Entity;
import com.arena.graphics.Sprite;

public abstract class Mob extends Entity {

    protected Sprite sprite;
    protected int dir = 0; //0 - north 1 - east 2 - south 3 - west
    protected boolean moving = false;

    public void move(){

    }

    public void update(){

    }

    public boolean collision(){
        return false;
    }

    public void render(){}

}
