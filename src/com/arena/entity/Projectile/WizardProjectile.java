package com.arena.entity.Projectile;

import com.arena.entity.CollidableEntity;
import com.arena.entity.Entity;
import com.arena.entity.mob.Castle;
import com.arena.entity.mob.Mob;
import com.arena.entity.mob.Player;
import com.arena.entity.particle.Particle;
import com.arena.graphics.AnimatedSprite;
import com.arena.graphics.Screen;
import com.arena.graphics.SpriteSheet;

public class WizardProjectile extends Projectile {



    public WizardProjectile(int x, int y, double dir, Mob owner) {
        super(x, y, dir, owner, new AnimatedSprite(32, 0, 0, SpriteSheet.arrow, 3, 4));
        sprite.setColisionBox(Direction.DOWN,12,9,8,23);
        sprite.setColisionBox(Direction.LEFT,0,12,22,8);
        sprite.setColisionBox(Direction.RIGHT,9,83-64,23,8);
        sprite.setColisionBox(Direction.UP,12,96-32*3,9,23);
        range = 200;
        damage = 20;
        rateOfFire = 20;
        speed = 5;
        nx = speed * Math.cos(angle);
        ny = speed * Math.sin(angle);
    }
    
    public void update(){
        sprite.update();
        move();

        if(distanceTraveled>range)
        {remove();}
    }

    private void move() {
        if(!level.tileCollision(x,y,sprite.WIDTH,sprite.HEIGHT,nx,ny)) {
            x += nx;
            y += ny;
            distanceTraveled += speed;
        }
        else
        {
            remove();
            Particle p = new Particle((int)x,(int)y,60,4);
            level.addEntity(p);
        }
    }

    public void handleCollision(CollidableEntity other){
        if(other instanceof Mob && !(other instanceof Player) && !(other instanceof Castle)) {
            remove();
        }
    }

    public void render(Screen screen){
        screen.renderSprite((int)x - sprite.WIDTH/2,(int)y-sprite.HEIGHT/2, sprite);

    }
}
