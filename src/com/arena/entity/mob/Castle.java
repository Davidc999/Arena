package com.arena.entity.mob;

import com.arena.GameScreen.Menu.MenuItem.Picture;
import com.arena.GameScreen.level.Level;
import com.arena.entity.CastleFloor;
import com.arena.entity.CollidableEntity;
import com.arena.entity.powerup.HealthCrystal;
import com.arena.graphics.BoundingBox;
import com.arena.graphics.Screen;
import com.arena.graphics.Sprite;
import com.arena.graphics.SpriteSheet;

import java.util.ArrayList;
import java.util.Random;

public class Castle extends Mob {

    private int buildTimer, crystalTimer;
    private boolean active;
    private Sprite sprite;
    private ArrayList<CastleFloor> floorList;
    private int crystalCount = 0;
    private Random random = new Random();

    public Castle(int x, int y){
        this.x = x;
        this.y = y;
        buildTimer = 60*(5 + xpLvl);
        crystalTimer = (random.nextInt(3) + 1) * 60;

        sprite = new Sprite(32,0,2, SpriteSheet.castleTowers);
        sprite.setColisionBox(0,0,sprite.WIDTH,sprite.HEIGHT/2);
        floorList = new ArrayList<>();
    }

    @Override
    public void init(Level level){
        this.level = level;
        CastleFloor newFloor = new CastleFloor(x-16,y-32-16,new Sprite(32,0,0,SpriteSheet.castleTowers));
        floorList.add(newFloor);
        level.addEntity(newFloor);
        deActivate();
    }

    @Override
    public void update() {
        handleConstruction();

        if(isActive())
            spawnCrystal();
    }

    public void upgrade(){
        xpLvl++;
        buildTimer = 60*(5 + xpLvl);

        if(xpLvl == (1 << (floorList.size() +2)) -1 -3 + 1) {
            CastleFloor newFloor = new CastleFloor( (int) x - 16, (int) y - 16 - 32 * (floorList.size() + 1), null);
            floorList.add(newFloor);
            level.addEntity(newFloor);
        }

        //Handle sprites
        //Handle tower base:
        sprite = new Sprite(32,((xpLvl+1) %2),2, SpriteSheet.castleTowers);
        for(int floor = 0; floor < floorList.size(); floor++){
            int floorDelay = (1 << (floor +2)) -1 -3 + 1;
            int spriteColorInd = (int)((xpLvl-floorDelay) / Math.pow(2,floor+1)) % 2;
            int spriteRooftopInd = floor == floorList.size()-1 ? 0 : 1;
            floorList.get(floor).setSprite( new Sprite(32,spriteColorInd,spriteRooftopInd,SpriteSheet.castleTowers));
        }

        deActivate();

    }

    public boolean isActive() {
        return active;
    }

    public void render(Screen screen){
        screen.renderSprite((int)x - sprite.WIDTH/2,(int)y - sprite.HEIGHT/2, sprite);
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

        for (CastleFloor floor: floorList) {
            floor.sprite.effectAdjustHue(0.8,'R');
            floor.sprite.effectAdjustHue(0.3,'G');
            floor.sprite.effectAdjustHue(0.3,'B');

        }

    }

    private void spawnCrystal(){
        crystalTimer--;
        if(crystalTimer == 0){
            crystalTimer = crystalTimer = (random.nextInt(3) + 1) * 60;
            if(crystalCount < xpLvl){
                double vy,vx;
                vx = random.nextInt(9)/2.0 - 2;
                vy = -1 - random.nextInt(4);
                HealthCrystal newCrystal = new HealthCrystal(x,y - floorList.size() * sprite.HEIGHT - sprite.HEIGHT/2,vx,vy, (int)y + sprite.HEIGHT,this);
                crystalCount++;
                level.addEntity(newCrystal);
            }

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
        return sprite.getCollisionBox().translate((int)x,(int)y);
    }

    public void decrementCrystals(){
        crystalCount--;
    }


}
