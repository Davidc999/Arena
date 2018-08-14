package com.arena.entity.Projectile;

import com.arena.entity.CollidableEntity;
import com.arena.entity.mob.Mob;
import com.arena.entity.mob.Player;
import com.arena.entity.particle.Particle;
import com.arena.graphics.AnimatedSprite;
import com.arena.graphics.Screen;

public class WimpyProjectile extends Projectile{

    public WimpyProjectile(double x, double y, double dir, Mob owner, AnimatedSprite sprite) {
        super(x, y, dir, owner, sprite);
        range = 200;
        damage = 5;
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
        if(other instanceof Player)
            remove();

    }

    public void render(Screen screen){
        screen.renderSprite((int)x - sprite.WIDTH/2,(int)y-sprite.HEIGHT/2, sprite);

    }

}
