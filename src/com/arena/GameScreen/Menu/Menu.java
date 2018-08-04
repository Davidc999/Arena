package com.arena.GameScreen.Menu;

import com.arena.Game;
import com.arena.GameScreen.GameScreen;
import com.arena.GameScreen.Menu.MenuItem.MenuItem;
import com.arena.GameScreen.Menu.MenuItem.SelectableMenuItem;
import com.arena.graphics.Screen;
import com.arena.input.KeyBoard;

import java.util.*;

public abstract class Menu extends GameScreen{

    protected ArrayList<MenuItem> menuItems;
    protected ArrayList<SelectableMenuItem> selectableMenuItems;
    protected KeyBoard input;
    protected int selectedItem, cursorX, cursorY;


    //TODO: Handle cursor in future
    public Menu(){
        super();
        menuItems = new ArrayList<>();
        selectableMenuItems = new ArrayList<>();
        selectedItem = 0;
    }

    public void render(Screen screen){
        screen.setOffset(xScroll,yScroll);
        for (MenuItem item: menuItems) {
            item.render(screen);
        }
        for (SelectableMenuItem item: selectableMenuItems) {
            item.render(screen);
        }
    }

    public void update(){
        for (MenuItem item: menuItems) {
            item.update();
        }
        for (SelectableMenuItem item: selectableMenuItems) {
            item.update();
        }
    }

}