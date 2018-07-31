package com.arena.entity.mob;

import com.arena.entity.Entity;
import com.arena.entity.Projectile.Projectile;
import com.arena.entity.particle.Particle;
import com.arena.graphics.AnimatedSprite;
import com.arena.graphics.Screen;

import java.util.Random;

public class WimpyMonster extends Mob{

    Random random = new Random();
    int moveTimer;
    int xa,ya;

    public WimpyMonster(int x, int y){
        this.x = x;
        this.y = y;
        this.sprite = AnimatedSprite.flyMonster;
        this.speed = 1;
        this.xp = 1;
        this.xpLvl = 1;
    }

    public void update(){

        if(moveTimer > 0){
            moveTimer--;
        }
        else {

            moveTimer = 30 + random.nextInt(90);

            xa = random.nextInt(3) - 1;
            ya = random.nextInt(3) - 1;
        }

            if (xa != 0 || ya != 0) {
                move(xa * speed, ya * speed);
            } else {
                sprite.stop();
            }

            sprite.update();

            updateShooting();

    }


    public void updateShooting(){

    }

    public void render(Screen screen){
        screen.renderSprite(x - sprite.WIDTH/2,y - sprite.HEIGHT/2, sprite);
    }

    @Override
    public void handleCollision(Entity other){
        if(other instanceof Projectile) {
            remove();
            Particle p = new Particle((int) x + sprite.WIDTH / 2, (int) y + sprite.HEIGHT / 2, 60, 4, 0xFFFF0000);
            level.addEntity(p);
        }
    }

}
