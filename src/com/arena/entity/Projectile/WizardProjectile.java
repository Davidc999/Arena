package com.arena.entity.Projectile;

import com.arena.graphics.AnimatedSprite;
import com.arena.graphics.Screen;
import com.arena.graphics.Sprite;

public class WizardProjectile extends Projectile {
    public WizardProjectile(int x, int y, double dir, AnimatedSprite sprite) {
        super(x, y, dir, sprite);
        range = 100;
        damage = 20;
        rateOfFire = 2;
        speed = 8;
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
        x += nx;
        y += ny;
        distanceTraveled += speed;
    }


    public void render(Screen screen){
        screen.renderPlayer((int)x,(int)y, sprite);

    }
}
