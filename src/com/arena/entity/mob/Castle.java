package com.arena.entity.mob;

import com.arena.GameScreen.Menu.MenuItem.Picture;
import com.arena.entity.CollidableEntity;
import com.arena.graphics.BoundingBox;
import com.arena.graphics.Screen;
import com.arena.graphics.Sprite;
import com.arena.graphics.SpriteSheet;

import java.util.ArrayList;

public class Castle extends Mob {

    private int buildTimer;
    private boolean active;
    private Sprite sprite;
    private ArrayList<Picture> floorList;

    public Castle(int x, int y){
        this.x = x;
        this.y = y;
        buildTimer = 60*(5 + xpLvl);

        sprite = new Sprite(32,0,2, SpriteSheet.castleTowers);
        floorList = new ArrayList<>();
        floorList.add(new Picture(new Sprite(32,0,0,SpriteSheet.castleTowers),x-16,y-32-16,true));
        deActivate();
    }

    @Override
    public void update() {
        handleConstruction();
    }

    public void upgrade(){
        xpLvl++;
        buildTimer = 60*(5 + xpLvl);

        if(xpLvl == (1 << (floorList.size() +2)) -1 -3 + 1)
            floorList.add(new Picture(null,x-16,y-16-32*(floorList.size()+1),true));

        //Handle sprites
        //Handle tower base:
        sprite = new Sprite(32,((xpLvl+1) %2),2, SpriteSheet.castleTowers);
        for(int floor = 0; floor < floorList.size(); floor++){
            int floorDelay = (1 << (floor +2)) -1 -3 + 1;
            int spriteColorInd = (int)((xpLvl-floorDelay) / Math.pow(2,floor+1)) % 2;
            int spriteRooftopInd = floor == floorList.size()-1 ? 0 : 1;
            floorList.get(floor).sprite = new Sprite(32,spriteColorInd,spriteRooftopInd,SpriteSheet.castleTowers);
        }

        deActivate();

    }

    public boolean isActive() {
        return active;
    }

    public void render(Screen screen){
        screen.renderSprite(x - sprite.WIDTH/2,y - sprite.HEIGHT/2, sprite);
        for (Picture floor: floorList) {
            floor.render(screen);
        }
    }

    private void deActivate(){
        //Other deactivation effects I considered...
        //sprite.effectPaint(0xFF111111);
        //sprite.effectNetOverlay(0xFFFF0000);
        //sprite.effectPaint(Sprite.alphaColor);

        active = false;
        sprite.effectAdjustHue(0.8,'R');
        sprite.effectAdjustHue(0.3,'G');
        sprite.effectAdjustHue(0.3,'B');

        for (Picture floor: floorList) {
            floor.sprite.effectAdjustHue(0.8,'R');
            floor.sprite.effectAdjustHue(0.3,'G');
            floor.sprite.effectAdjustHue(0.3,'B');

        }

    }

    private void handleConstruction(){
        if(buildTimer > 0) {
            buildTimer--;

            double constructionProgress = 1 - (double)buildTimer / (60 * (5 + xpLvl));
            int floorUnderConstruction = (int)(constructionProgress * (floorList.size() + 1));
            if(floorUnderConstruction == 0)
                sprite.restore(constructionProgress * (floorList.size() + 1));
            else if(floorUnderConstruction <= floorList.size())
                floorList.get(floorUnderConstruction-1).sprite.restore((constructionProgress - (double)floorUnderConstruction/(floorList.size() + 1)) * (floorList.size() + 1));

            if(buildTimer == 0)
                active = true;
        }
    }

    public BoundingBox getCollisionBox(){
        return sprite.getCollisionBox().translate(x,y);
    }


}
