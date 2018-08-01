package com.arena.GameScreen.Menu.MenuItem;

public abstract class SelectableMenuItem extends MenuItem {

    boolean enabled;

    public void highlight()
    {

    }

    public SelectableMenuItem(int xPos, int yPos, boolean visible, boolean enabled){
        super(xPos, yPos, visible);
        enabled = false;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void selectAction()
    {

    }

}
