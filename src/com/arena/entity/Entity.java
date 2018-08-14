package com.arena.entity;

import com.arena.graphics.Screen;
import com.arena.GameScreen.level.Level;
import com.arena.graphics.Sprite;

import java.util.Random;

public abstract class Entity {

    public double x, y;
    protected boolean removed = false;
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
}

