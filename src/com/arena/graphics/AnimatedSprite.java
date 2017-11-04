package com.arena.graphics;

import com.arena.entity.Entity;

public class AnimatedSprite extends Sprite{

    private boolean started;
    private int updateDelay;
    private int currFrame;
    private int updateCount;
    private final int animationLength;

    public static AnimatedSprite player = new AnimatedSprite(32,0,0,SpriteSheet.maleWizard,10,3 );

    public AnimatedSprite(int size, int x, int y, SpriteSheet sheet, int updateDelay, int animationLength){
        super(size, x, y, sheet);
        this.updateDelay = updateDelay;
        currFrame = 0;
        updateCount = 0;
        started = false;
        this.animationLength = animationLength;
    }

    public void start() {
        started = true;
    }

    public void stop() {
        started = false;
    }

    public void update(){
        if(started) {
            if (updateCount == 0) {
                x = (x + SIZE) % (animationLength*SIZE);
                load();
            }
            updateCount = (updateCount + 1) % updateDelay;
        }
    }

    public void setAnimation(Entity.Direction direction){
        y = SIZE * direction.ordinal();
        updateCount = 0;
    }





}
