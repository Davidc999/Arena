package com.arena.entity;

import com.arena.graphics.BoundingBox;
import com.arena.graphics.Screen;
import com.arena.graphics.Sprite;
import com.arena.GameScreen.level.Level;

public abstract class CollidableEntity extends Entity {

    protected Sprite sprite;


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

    public boolean isCollidingWith(CollidableEntity other){
        return getCollisionBox().isCollision(other.getCollisionBox());
    }

    public void handleCollision(CollidableEntity other){

    }

    public BoundingBox getCollisionBox(){
        return sprite.getCollisionBox().translate(x - sprite.WIDTH/2,y-sprite.HEIGHT/2);
    }
}
