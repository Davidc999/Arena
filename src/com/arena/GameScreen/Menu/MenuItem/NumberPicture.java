package com.arena.GameScreen.Menu.MenuItem;

import com.arena.graphics.Sprite;
import com.arena.graphics.SpriteSheet;

public class NumberPicture extends Picture{

    public NumberPicture(int x, int y, boolean visible, char num){

        super(new Sprite(8,0,0, SpriteSheet.numbers),x,y,visible);
        setnumber(num);

    }

    public void setnumber(char num){
        sprite = charToSprite(num);
    }

    private Sprite charToSprite(char num){
        if(num == '/')
            return new Sprite(8,10,0, SpriteSheet.numbers);
        if(num >= '0' && num <='9')
            return new Sprite(8,num - '0',0, SpriteSheet.numbers);
        if(num > '9')
            return new Sprite(8,9,0, SpriteSheet.numbers);
        // if num < '0'
            return new Sprite(8,0,0, SpriteSheet.numbers);

    }

}
