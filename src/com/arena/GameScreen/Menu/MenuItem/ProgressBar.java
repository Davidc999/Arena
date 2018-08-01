package com.arena.GameScreen.Menu.MenuItem;

import com.arena.Game;
import com.arena.graphics.Screen;

public class ProgressBar extends MenuItem{

    private int height, width, color;
    private double progress;

    public ProgressBar(int xPos, int yPos, boolean visible, int height, int width, int color){
        super(xPos,yPos,visible);
        this.height = height;
        this.width = width;
        this.color = color;
    }

    @Override
    public void update() {
        double prevXPLvl;
        if(Game.player.getXpLevel() == 0)
            prevXPLvl = 0;
        else
            prevXPLvl = 5 * Math.pow(Game.player.getXpLevel() - 1,2);
        double nextLvlXP = 5 * Math.pow(Game.player.getXpLevel(),2);
        progress = 1 - (nextLvlXP - Game.player.getXp()) / (nextLvlXP - prevXPLvl);
    }

    @Override
    public void render(Screen screen) {
        screen.renderRectangle(xPos,yPos,height,(int)(width * progress),color);
    }

    public void setProgress(double progress) {
        if(progress >=0 && progress < 1)
            this.progress = progress;
        if(progress < 0)
            this.progress = 0;
        if(progress > 1)
            this.progress = 1;
    }
}
