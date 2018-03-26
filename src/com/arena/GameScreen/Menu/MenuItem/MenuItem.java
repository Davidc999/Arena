package com.arena.GameScreen.Menu.MenuItem;

import com.arena.graphics.Screen;
import com.arena.graphics.Sprite;

public abstract class MenuItem {

    boolean visible, enabled;
    int xPos, yPos;

    public MenuItem(int xPos, int yPos, boolean visible, boolean enabled){
        this.xPos = xPos;
        this.yPos = yPos;
        this.visible = visible;
        this.enabled = enabled;
    }

    public void update()
    {

    }

    public void render(Screen screen)
    {

    }

}
