package com.arena.GameScreen;

import com.arena.graphics.Screen;

public abstract class GameScreen {

    public boolean visible, enabled;
    protected int xScroll, yScroll;

    public GameScreen(){
        visible = true;
        enabled = true;
        xScroll = 0;
        yScroll = 0;
    }


    abstract public void render(Screen screen);

    abstract public void update();

    public int getxScroll() {
        return xScroll;
    }

    public int getyScroll() {
        return yScroll;
    }
}
