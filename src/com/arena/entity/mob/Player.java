package com.arena.entity.mob;

import com.arena.graphics.AnimatedSprite;
import com.arena.graphics.Screen;
import com.arena.graphics.Sprite;
import com.arena.input.KeyBoard;

public class Player extends Mob{

    private KeyBoard input;

    public Player(KeyBoard input, AnimatedSprite animatedSprite){
        this.input = input;
        this.animatedSprite = animatedSprite;
    }

    public Player(int x, int y, KeyBoard input){
        this.x = x;
        this.y = y;
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

    }
    
    public void render(Screen screen){
        screen.renderPlayer(x - animatedSprite.SIZE/2,y - animatedSprite.SIZE/2, animatedSprite);
    }
}
