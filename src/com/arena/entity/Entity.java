package com.arena.entity;

import com.arena.graphics.BoundingBox;
import com.arena.graphics.Screen;
import com.arena.graphics.Sprite;
import com.arena.level.Level;

import java.util.Random;

public abstract class Entity {

    public int x, y;
    protected Sprite sprite;
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random();
    public enum Direction {
        DOWN,
        LEFT,
        RIGHT,
        UP;
    }


    public void update(){
    }

    public void render(Screen screen){

    }

    public void init(Level level){
        this.level = level;
    }

    public void remove(){
        //Remove from level
        removed = true;
    }

    public boolean isRemoved(){
        return removed;
    }

    public boolean isCollidingWith(Entity other){
        return getCollisionBox().isCollision(other.getCollisionBox());
    }

    public void handleCollision(Entity other){

    }

    public BoundingBox getCollisionBox(){
        return sprite.getCollisionBox().translate(x,y);
    }
}
