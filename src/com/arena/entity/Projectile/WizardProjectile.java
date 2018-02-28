package com.arena.entity.Projectile;

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
        if(!level.tileCollision(x,y,sprite.SIZE,sprite.SIZE,nx,ny)) {
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


    public void render(Screen screen){
        screen.renderSprite((int)x,(int)y, sprite);

    }
}
