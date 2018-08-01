package com.arena.GameScreen;

import com.arena.graphics.Screen;

import java.util.ListIterator;
import java.util.Stack;

public class GameScreenManager {

    private static Stack<GameScreen> screens;
    private static GameScreen controlledScreen;


    public static void pushGameScreen(GameScreen gameScreen, boolean isControlled)
    {
        screens.push(gameScreen);
        if(isControlled){ setControlledScreen(gameScreen); }
    }

    public static void popGameScreen()
    {
        screens.pop();
    }

    public static void setControlledScreen(GameScreen gameScreen)
    {
        controlledScreen = gameScreen;
    }

    public static void update()
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
        ListIterator iter = screens.listIterator(screens.size());
        GameScreen currScreen;
        while(iter.hasPrevious())
        {
            currScreen = ((GameScreen)iter.previous());
            if(currScreen.visible) {
            currScreen.render(0,0,screen);
            }

        }
    }


}
