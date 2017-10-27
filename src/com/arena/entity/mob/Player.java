package com.arena.entity.mob;

import com.arena.graphics.Screen;
import com.arena.graphics.Sprite;
import com.arena.input.KeyBoard;

public class Player extends Mob{

    private KeyBoard input;

    public Player(KeyBoard input, Sprite sprite){
        this.input = input;
        this.sprite = sprite;
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

        if (xa != 0 || ya != 0) move(xa, ya);
    }
    
    public void render(Screen screen){
        screen.renderPlayer(x - sprite.SIZE/2,y - sprite.SIZE/2, sprite);
    }
}
