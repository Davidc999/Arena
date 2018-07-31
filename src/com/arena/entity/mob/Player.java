package com.arena.entity.mob;

import com.arena.Game;
import com.arena.entity.Projectile.WizardProjectile;
import com.arena.graphics.AnimatedSprite;
import com.arena.graphics.Screen;
import com.arena.input.KeyBoard;
import com.arena.input.Mouse;
import com.arena.level.Level;

public class Player extends Mob{

    private KeyBoard input;

    public Player(KeyBoard input, AnimatedSprite animatedSprite, Level level){
        this.input = input;
        this.sprite = animatedSprite;
        this.speed = 1;
    }

    public Player(int x, int y, KeyBoard input, AnimatedSprite animatedSprite){
        this.x = x;
        this.y = y;
        this.sprite = animatedSprite;
        this.input = input;
        this.speed = 1;
    }

    public void update(){

        if(input.buildCastle){
            level.buildCastleReq(x,y);
        }

        int xa = 0;
        int ya = 0;

        if (input.up) ya -= speed;
        if (input.down) ya += speed;
        if (input.left) xa -= speed;
        if (input.right) xa += speed;

        if (xa != 0 || ya != 0) {
            move(xa, ya);
        }
        else {
            sprite.stop();
        }

        sprite.update();

        updateShooting();

    }


    public void updateShooting(){
        if(projectileTimer < WizardProjectile.getRateOfFire()) {
            projectileTimer += 1;
        }
        if(Mouse.getButton() == 1)
        {
            double dx = Mouse.getX() - Game.getWindowWidth()/2;
            double dy = Mouse.getY() - Game.getWindowHeight()/2;
            shoot(Math.atan2(dy,dx));
        }
    }

    public void render(Screen screen){
        screen.renderSprite(x - sprite.WIDTH/2,y - sprite.HEIGHT/2, sprite);
    }
}
