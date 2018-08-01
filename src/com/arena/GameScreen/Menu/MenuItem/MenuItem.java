package com.arena.GameScreen.Menu.MenuItem;

import com.arena.graphics.Screen;
import com.arena.graphics.Sprite;

public abstract class MenuItem {

    protected boolean visible;
    protected int xPos, yPos;

    public MenuItem(int xPos, int yPos, boolean visible){
        this.xPos = xPos;
        this.yPos = yPos;
        this.visible = visible;
    }

    abstract public void update();

    abstract public void render(Screen screen);

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
