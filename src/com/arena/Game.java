package com.arena;

import com.arena.GameScreen.GameScreenManager;
import com.arena.GameScreen.Menu.HUD;
import com.arena.entity.mob.Player;
import com.arena.entity.mob.mobSpawner;
import com.arena.graphics.AnimatedSprite;
import com.arena.graphics.Screen;
import com.arena.input.KeyBoard;
import com.arena.input.Mouse;
import com.arena.GameScreen.level.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable{

    private static final long serialVersionUID =1L;

    private static int width = 500;
    private static int height = width / 16 * 9;
    private static int scale = 2;

    private Thread thread;
    private JFrame frame;
    private KeyBoard keyBoard;
    //private Level level;
    private GameScreenManager gameScreenManager;

    private boolean running = false;

    private Screen screen;

    public static Player player;

    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    public static int getWindowWidth(){
        return width*scale;
    }

    public static int getWindowHeight(){
        return height*scale;
    }

    public Game(){
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);

        screen = new Screen(width, height);
        frame = new JFrame();

        //setting the keyboard
        keyBoard = new KeyBoard();
        addKeyListener(keyBoard);

        //Setup levels:
        //level = new RandomLevel(64,64);

        gameScreenManager = new GameScreenManager();
        Level level = new Level(50,50,"res/levels/map2.map");
        player = new  Player(25*16,25*16,keyBoard, AnimatedSprite.player);
        level.addEntity(new mobSpawner(60*4, 28*16, 28*16, 1));
        //level.addEntity(new WimpyMonster(28*16,28*16));
        level.addEntity(player);
        gameScreenManager.pushGameScreen(level,true);
        gameScreenManager.pushGameScreen(new HUD(),false);


        Mouse mouse = new Mouse();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    public synchronized void start(){
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop(){
        running = false;
        try {
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void run(){
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();

        final double ns = 1000000000.0 / 60.0;
        double delta = 0;

        // how many frames
        int frames = 0;
        int updates = 0;
        requestFocus();

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1){
                update();
                updates ++;
                delta --;
            }
            render();
            frames ++;

            if (System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println(updates + " ups, " + frames + " fps");
                frames = 0;
                updates = 0;
            }
        }
        stop();
    }



    int x = 0 ,y = 0;
    // game logic
    public void update(){
        keyBoard.update();
        //player.update();
        //level.update();
        gameScreenManager.update();
    }



    // graphics
    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();
        gameScreenManager.render(screen);

        for ( int i =0 ; i < pixels.length ; i++){
            pixels[i] = screen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics();
//        g.setColor(new Color(40, 70, 80));
//        g.fillRect(0,0, getWidth(), getHeight());
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        //remove current graphics that we are done with
        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle("myGame");
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.start();


    }

}
