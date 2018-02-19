package com.arena.entity.Projectile;

import com.arena.entity.Entity;
import com.arena.graphics.AnimatedSprite;
import com.arena.graphics.Sprite;

public abstract class Projectile extends Entity {

    protected final int xOrigin, yOrigin;
    protected double angle;
    protected AnimatedSprite sprite;
    protected double nx, ny;
    protected double speed, rateOfFire, range, damage;

    public Projectile(int x, int y, double dir, AnimatedSprite sprite){
        xOrigin = x;
        yOrigin = y;
        angle = dir;
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    public void setAnimationDir(Entity.Direction direction)
    {
        sprite.setAnimation(direction);
        sprite.start();
    }

}
