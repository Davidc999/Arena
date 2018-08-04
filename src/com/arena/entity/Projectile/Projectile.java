package com.arena.entity.Projectile;

import com.arena.entity.CollidableEntity;
import com.arena.entity.Entity;
import com.arena.entity.mob.Mob;
import com.arena.graphics.AnimatedSprite;
import com.arena.graphics.BoundingBox;

public abstract class Projectile extends CollidableEntity {

    protected final int xOrigin, yOrigin;
    protected double angle;
    protected AnimatedSprite sprite;
    protected double nx, ny, x, y;
    protected double speed, range, distanceTraveled;
    int damage;
    protected static int rateOfFire;
    protected Mob owner;

    public Projectile(int x, int y, double dir, Mob owner, AnimatedSprite sprite){
        xOrigin = x;
        yOrigin = y;
        angle = dir;
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.owner = owner;
    }

    public void setAnimationDir(CollidableEntity.Direction direction)
    {
        sprite.setAnimation(direction);
        sprite.start();
    }

    public static double getRateOfFire()
    {return rateOfFire;}

    public BoundingBox getCollisionBox(){
        return sprite.getCollisionBox().translate((int)x-sprite.WIDTH/2,(int)y-sprite.HEIGHT/2);
    }

    public Mob getOwner() {
        return owner;
    }

    public int getDamage() {
        return damage;
    }
}
