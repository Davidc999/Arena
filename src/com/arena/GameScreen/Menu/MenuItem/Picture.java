package com.arena.GameScreen.Menu.MenuItem;

import com.arena.graphics.Screen;
import com.arena.graphics.Sprite;

public class Picture extends MenuItem{

    public Sprite sprite;

    public Picture(Sprite sprite,int x, int y, boolean visible){
        super(x,y,visible);
        this.sprite = sprite;
    }



    @Override
    public void update() {

    }

    @Override
    public void render(Screen screen) {
        screen.renderSprite(xPos,yPos,sprite);
    }
}
