package com.arena.GameScreen.Menu;

import com.arena.GameScreen.Menu.MenuItem.MenuItem;
import com.arena.GameScreen.Menu.MenuItem.SelectableMenuItem;
import com.arena.input.KeyBoard;

import java.util.List;

public abstract class Menu {

    private List<MenuItem> menuItems;
    private List<SelectableMenuItem> selectableMenuItems;
    private KeyBoard input;
    int selectedItem, cursorX, cursorY;

}
