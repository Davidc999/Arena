package com.arena.entity.powerup;

import com.arena.entity.CollidableEntity;
import com.arena.entity.Projectile.Projectile;
import com.arena.entity.Projectile.WimpyProjectile;
import com.arena.entity.Projectile.WizardProjectile;
import com.arena.entity.mob.Castle;
import com.arena.entity.mob.Player;
import com.arena.entity.particle.Particle;
import com.arena.graphics.AnimatedSprite;
import com.arena.graphics.Screen;
import com.arena.graphics.SpriteSheet;

import java.util.Random;

public class HealthCrystal extends Powerup{
    double vx,vy;
    double ay;
    int floor;
    Castle owner;

    public HealthCrystal(double x, double y){
        this.vx = 0;
        this.vy = 0;
        this.ay = 0;
        this.x = x;
        this.y = y;
        this.sprite = new AnimatedSprite(7,13,0,0, SpriteSheet.crystal,3,24 );
        this.sprite.setAnimation(Direction.DOWN);
        this.sprite.start();
    }

    public HealthCrystal(double x, double y, double vx, double vy, int floor, Castle owner){
        this.vx = vx;
        this.vy = vy;
        this.ay = 0.25;
        this.floor = floor;
        this.x = x;
        this.y = y;
        this.sprite = new AnimatedSprite(7,13,0,0, SpriteSheet.crystal,3,24 );
        this.sprite.setAnimation(Direction.DOWN);
        this.sprite.start();
        this.owner = owner;
    }

    public void update(){
        sprite.update();

        vy += ay;
        move();
    }

    private void move(){
        if(y + vy > floor) {
            vy = floor - y;
            vx = 0;
            ay = 0;
        }
        if(!level.tileCollision(x,y,sprite.WIDTH,sprite.HEIGHT,vx,vy)) {
            x += vx;
            y += vy;
        }
        else
        {
            this.vx =0;
            this.vy = 0;
        }
    }

    public void remove(){
        //Remove from level
        removed = true;
        owner.decrementCrystals();
    }

    public void render(Screen screen){
        screen.renderSprite((int)x - sprite.WIDTH/2,(int)y - sprite.HEIGHT/2, sprite);
    }

    @Override
    public void handleCollision(CollidableEntity other){
        if(other instanceof Player){
            remove();
            ((Player) other).modifyHp(5);
        }

    }
}
