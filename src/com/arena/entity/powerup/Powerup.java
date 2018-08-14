package com.arena.entity.powerup;

import com.arena.entity.CollidableEntity;
import com.arena.entity.Projectile.Projectile;
import com.arena.entity.Projectile.WizardProjectile;
import com.arena.graphics.AnimatedSprite;
import com.arena.graphics.BoundingBox;
import com.arena.graphics.SpriteSheet;

import static com.arena.entity.Entity.Direction.*;
import static com.arena.entity.Entity.Direction.LEFT;

public abstract class Powerup extends CollidableEntity {

    protected AnimatedSprite sprite;
    protected int speed;


    public void move(int xChange, int yChange){

    }

    public void update(){

    }


    public void render(){}

    public BoundingBox getCollisionBox(){
        return sprite.getCollisionBox().translate((int)x-sprite.WIDTH/2,(int)y-sprite.HEIGHT/2);
    }

}
