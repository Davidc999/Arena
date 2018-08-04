package com.arena.GameScreen;

import com.arena.graphics.Screen;

import java.util.ListIterator;
import java.util.Stack;

public class GameScreenManager {

    private Stack<GameScreen> screens;
    private GameScreen controlledScreen;


    public GameScreenManager(){
        screens = new Stack<>();
    }

    public void pushGameScreen(GameScreen gameScreen, boolean isControlled)
    {
        screens.push(gameScreen);
        if(isControlled){ setControlledScreen(gameScreen); }
    }

    public void popGameScreen()
    {
        screens.pop();
    }

    public void setControlledScreen(GameScreen gameScreen)
    {
        controlledScreen = gameScreen;
    }

    public void update()
    {
        ListIterator iter = screens.listIterator();
        GameScreen currScreen;
        while(iter.hasNext())
        {
            currScreen = (GameScreen)iter.next();
            if(currScreen.enabled) {
                currScreen.update();
            }

        }

    }

    public void render(Screen screen)
    {
        ListIterator iter = screens.listIterator();
        GameScreen currScreen;
        while(iter.hasNext())
        {
            currScreen = ((GameScreen)iter.next());
            if(currScreen.visible) {
            currScreen.render(screen);
            }

        }
    }


}
