package com.arena.graphics;

import com.arena.entity.Entity;

public class AnimatedSprite extends Sprite{

    private boolean started;
    private int updateDelay;
    private int currFrame;
    private int updateCount;
    private final int animationLength;
    private BoundingBox[] collisionBox;

    public static AnimatedSprite player = new AnimatedSprite(32,0,0,SpriteSheet.maleWizard,10,3 );
    public static AnimatedSprite flyMonster = new AnimatedSprite(32,0,0,SpriteSheet.flyMonster,10,4 );

    public AnimatedSprite(int size, int x, int y, SpriteSheet sheet, int updateDelay, int animationLength){
        super(size, x, y, sheet);
        this.updateDelay = updateDelay;
        currFrame = 0;
        updateCount = 0;
        started = false;
        this.animationLength = animationLength;
        collisionBox = new BoundingBox[4];
        for(int i=0; i<4; i++)
            setColisionBox(Entity.Direction.values()[i],0,0,size,size);
    }

    public void setColisionBox(Entity.Direction dir, int x, int y, int height, int width){
        collisionBox[dir.ordinal()] = new BoundingBox(x,y,height,width);
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
                x = (x + WIDTH) % (animationLength*WIDTH);
                load();
            }
            updateCount = (updateCount + 1) % updateDelay;
        }
    }

    public void setAnimation(Entity.Direction direction){
        y = HEIGHT * direction.ordinal();
        updateCount = 0;
    }

    public BoundingBox getCollisionBox() {
        return collisionBox[y / HEIGHT]; // returns collision box for the curr direction, since y = HEIGHT * dir
    }





}
