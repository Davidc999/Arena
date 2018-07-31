package com.arena.entity.Projectile;

import com.arena.entity.Entity;
import com.arena.entity.mob.Mob;
import com.arena.entity.particle.Particle;
import com.arena.graphics.AnimatedSprite;
import com.arena.graphics.Screen;

public class WizardProjectile extends Projectile {
    public WizardProjectile(int x, int y, double dir, AnimatedSprite sprite) {
        super(x, y, dir, sprite);
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
            Particle p = new Particle((int)x+sprite.WIDTH/2,(int)y + sprite.HEIGHT/2,60,4);
            level.addEntity(p);
        }
    }

    @Override
    public void handleCollision(Entity other){
        if(other instanceof Mob) {
            remove();
        }
    }

    public void render(Screen screen){
        screen.renderSprite((int)x,(int)y, sprite);

    }
}
