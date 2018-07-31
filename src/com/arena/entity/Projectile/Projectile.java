package com.arena.entity.Projectile;

import com.arena.entity.Entity;
import com.arena.graphics.AnimatedSprite;
import com.arena.graphics.BoundingBox;
import com.arena.graphics.Sprite;

public abstract class Projectile extends Entity {

    protected final int xOrigin, yOrigin;
    protected double angle;
    protected AnimatedSprite sprite;
    protected double nx, ny, x, y;
    protected double speed, range, damage, distanceTraveled;
    protected static int rateOfFire;

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

    public static double getRateOfFire()
    {return rateOfFire;}

    public BoundingBox getCollisionBox(){
        return sprite.getCollisionBox().translate((int)x,(int)y);
    }

}
