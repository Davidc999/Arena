package com.arena.entity.mob;

import com.arena.entity.Entity;
import com.arena.graphics.Screen;
import com.arena.graphics.Sprite;
import com.arena.graphics.SpriteSheet;

public class Castle extends Entity{

    private int buildTimer;
    private int powerLevel;
    private boolean active;

    public Castle(int x, int y){
        this.x = x;
        this.y = y;
        powerLevel = 0;
        buildTimer = 60*(5 + powerLevel);
        active = false;
        sprite = new Sprite(16,19,0, SpriteSheet.tiles);
        //sprite.effectPaint(0xFF111111);
        //sprite.effectNetOverlay(0xFFFF0000);
        //sprite.effectPaint(Sprite.alphaColor);
        sprite.effectAdjustHue(0.8,'R');
        sprite.effectAdjustHue(0.3,'G');
        sprite.effectAdjustHue(0.3,'B');
    }

    @Override
    public void update() {
        if(buildTimer > 0) {
            buildTimer--;
            sprite.restore(1 - (double)buildTimer / (60 * (5 + powerLevel)));
            if(buildTimer == 0)
                active = true;
        }

    }

    public void upgrade(){
        powerLevel++;
        buildTimer = 60*(5 + powerLevel);
        active = false;
        sprite = new Sprite(16,18,0, SpriteSheet.tiles);
        //sprite.effectPaint(0xFF111111);
        //sprite.effectNetOverlay(0xFFFF0000);
        //sprite.effectPaint(Sprite.alphaColor);
        sprite.effectAdjustHue(0.8,'R');
        sprite.effectAdjustHue(0.3,'G');
        sprite.effectAdjustHue(0.3,'B');
    }

    public boolean isActive() {
        return active;
    }

    public void render(Screen screen){
        screen.renderSprite(x - sprite.WIDTH/2,y - sprite.HEIGHT/2, sprite);
    }

}
