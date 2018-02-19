package com.arena.entity.Projectile;

import com.arena.graphics.AnimatedSprite;
import com.arena.graphics.Screen;
import com.arena.graphics.Sprite;

public class WizardProjectile extends Projectile {
    public WizardProjectile(int x, int y, double dir, AnimatedSprite sprite) {
        super(x, y, dir, sprite);
        range = 200;
        damage = 20;
        rateOfFire = 15;
        speed = 10;
        nx = speed * Math.cos(angle);
        ny = speed * Math.sin(angle);
    }
    
    public void update(){
        sprite.update();
        move();

    }

    private void move() {
        x += nx;
        y += ny;
    }


    public void render(Screen screen){
        screen.renderPlayer(x,y, sprite);

    }
}
