package com.arena.entity.mob;

import com.arena.Game;
import com.arena.graphics.AnimatedSprite;
import com.arena.graphics.Screen;
import com.arena.graphics.Sprite;
import com.arena.input.KeyBoard;
import com.arena.input.Mouse;
import com.arena.level.Level;

public class Player extends Mob{

    private KeyBoard input;

    public Player(KeyBoard input, AnimatedSprite animatedSprite, Level level){
        this.input = input;
        this.animatedSprite = animatedSprite;
    }

    public Player(int x, int y, KeyBoard input, AnimatedSprite animatedSprite){
        this.x = x;
        this.y = y;
        this.animatedSprite = animatedSprite;
        this.input = input;
    }

    public void update(){

        int xa = 0;
        int ya = 0;

        if (input.up) ya--;
        if (input.down) ya++;
        if (input.left) xa--;
        if (input.right) xa++;

        if (xa != 0 || ya != 0) {
            move(xa, ya);
        }
        else {
            animatedSprite.stop();
        }

        animatedSprite.update();

        updateShooting();

    }

    public void updateShooting(){
        if(Mouse.getButton() == 1)
        {
            double dx = Mouse.getX() - Game.getWindowWidth()/2;
            double dy = Mouse.getY() - Game.getWindowHeight()/2;
            shoot(Math.atan2(dy,dx));
        }
    }

    public void render(Screen screen){
        screen.renderPlayer(x - animatedSprite.SIZE/2,y - animatedSprite.SIZE/2, animatedSprite);
    }
}
