package com.arena.entity;

import com.arena.graphics.Screen;
import com.arena.level.Level;

import java.util.Random;

public abstract class Entity {

    public int x, y;
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random();
    public enum Direction {
        UP,
        RIGHT,
        DOWN,
        LEFT;
    }


    public void update(){
    }

    public void render(Screen screen){

    }

    public void remove(){
        //Remove from level
        removed = true;
    }

    public boolean isRemoved(){
        return removed;
    }
}
