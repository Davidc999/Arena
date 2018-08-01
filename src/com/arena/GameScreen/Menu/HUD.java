package com.arena.GameScreen.Menu;

import com.arena.GameScreen.Menu.MenuItem.Picture;
import com.arena.GameScreen.Menu.MenuItem.ProgressBar;
import com.arena.graphics.Screen;
import com.arena.graphics.Sprite;
import com.arena.graphics.SpriteSheet;

public class HUD extends Menu{
    public HUD(){
        super();

        menuItems.add(new Picture(new Sprite(36,0,0, SpriteSheet.castleHUDImage),5,5,true));
        menuItems.add(new ProgressBar(5+4,5+30,true,4,30,0xffffff33));
    }


}