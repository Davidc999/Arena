package com.arena.GameScreen.Menu;

import com.arena.Game;
import com.arena.GameScreen.Menu.MenuItem.NumberPicture;
import com.arena.GameScreen.Menu.MenuItem.Picture;
import com.arena.GameScreen.Menu.MenuItem.ProgressBar;
import com.arena.GameScreen.level.Level;
import com.arena.graphics.Sprite;
import com.arena.graphics.SpriteSheet;

public class HUD extends Menu{

    private final int PICTURE_OFFSET = 5;
    private final int TEXT_OFFSET_X = 8;
    private final int TEXT_OFFSET_Y = 8;
    private final int NUMBER_SPRITE_OFFSET;


    public HUD(){
        super();

        // Castle area
        menuItems.add(0, new Picture(new Sprite(36,0,0, SpriteSheet.castleHUDImage),PICTURE_OFFSET,PICTURE_OFFSET,true));
        menuItems.add(1, new ProgressBar(PICTURE_OFFSET+3,PICTURE_OFFSET+29,true,4,30,0xffffff33));

        NUMBER_SPRITE_OFFSET = SpriteSheet.numbers.TILE_WIDTH;
        menuItems.add(2,new NumberPicture(PICTURE_OFFSET+TEXT_OFFSET_X,PICTURE_OFFSET+TEXT_OFFSET_Y,true,'0'));
        menuItems.add(3,new NumberPicture(PICTURE_OFFSET+TEXT_OFFSET_X+NUMBER_SPRITE_OFFSET,PICTURE_OFFSET+TEXT_OFFSET_Y,true,'/'));
        menuItems.add(4,new NumberPicture(PICTURE_OFFSET+TEXT_OFFSET_X+NUMBER_SPRITE_OFFSET*2,PICTURE_OFFSET+TEXT_OFFSET_Y,true,'1'));

        //HP area
        menuItems.add(5, new Picture(new Sprite(9,0,0,SpriteSheet.heartImg),PICTURE_OFFSET + 40,16,true));
        menuItems.add(6,new NumberPicture(PICTURE_OFFSET + 40+6 + NUMBER_SPRITE_OFFSET,16,true,'9'));
        menuItems.add(7,new NumberPicture(PICTURE_OFFSET + 40+6 + NUMBER_SPRITE_OFFSET*2,16,true,'9'));
        menuItems.add(8,new NumberPicture(PICTURE_OFFSET + 40+6 + NUMBER_SPRITE_OFFSET*3,16,true,'/'));
        menuItems.add(9,new NumberPicture(PICTURE_OFFSET + 40+6 + NUMBER_SPRITE_OFFSET*4,16,true,'9'));
        menuItems.add(10,new NumberPicture(PICTURE_OFFSET + 40+6 + NUMBER_SPRITE_OFFSET*5,16,true,'9'));
    }

    public void update(){
        updateCastleBar();
        updateCastleNumbers();
        updateHPNumbers();
    }

    private void updateCastleBar(){
        double prevXPLvl;
        if(Game.player.getXpLevel() == 0)
            prevXPLvl = 0;
        else
            prevXPLvl = 5 * Math.pow(Game.player.getXpLevel() - 1,2);
        double nextLvlXP = 5 * Math.pow(Game.player.getXpLevel(),2);
        ((ProgressBar)menuItems.get(1)).setProgress(1 - (nextLvlXP - Game.player.getXp()) / (nextLvlXP - prevXPLvl));
    }

    private void updateCastleNumbers(){
        ((NumberPicture)menuItems.get(2)).setnumber((char)(Level.getCastleLevel() + '0'));
        ((NumberPicture)menuItems.get(4)).setnumber((char)(Game.player.getXpLevel() + '0'));
    }

    private void updateHPNumbers(){
        ((NumberPicture)menuItems.get(6)).setnumber((char)(Game.player.getHp()/10 + '0'));
        ((NumberPicture)menuItems.get(7)).setnumber((char)(Game.player.getHp()%10 + '0'));
    }

}