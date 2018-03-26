package com.arena.GameScreen.Menu.MenuItem;

import com.arena.GameScreen.Menu.MenuItem.MenuItem;

public abstract class SelectableMenuItem extends MenuItem {

    boolean selectable;

    public void highlight()
    {

    }

    public SelectableMenuItem(int xPos, int yPos, boolean visible, boolean enabled, boolean selectable){
        super(xPos, yPos, visible, enabled);
        selectable = false;
    }


    public void selectAction()
    {

    }

}
