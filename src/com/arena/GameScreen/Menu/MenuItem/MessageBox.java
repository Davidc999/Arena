package com.arena.GameScreen.Menu.MenuItem;

import com.arena.graphics.Screen;
import com.arena.graphics.Sprite;
import com.arena.graphics.SpriteSheet;

public class MessageBox extends SelectableMenuItem {

    private int width, height;
    private SpriteSheet borderSpriteSheet;
    private String text;
    boolean blocking;

    public MessageBox(int xPos, int yPos,int width, int height, boolean enabled, boolean visible, boolean selectable)
    {
        super(xPos,yPos,enabled,visible, selectable);
        this.width = width / 32;
        this.height = height / 32;
    }

    public void render(Screen screen)
    {
        for(int currX=0; currX<width; currX++){

        }
    }

    public void renderTextAndWait(){

    }

    public void renderText()
    {

    }

}
