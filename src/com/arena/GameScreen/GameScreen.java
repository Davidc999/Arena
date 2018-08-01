package com.arena.GameScreen;

import com.arena.graphics.Screen;

public abstract class GameScreen {

    public boolean visible, enabled;
    protected int xScroll, yScroll;


    abstract public void render(int xScroll, int yScroll,Screen screen);

    abstract public void update();

    public int getxScroll() {
        return xScroll;
    }

    public int getyScroll() {
        return yScroll;
    }
}
