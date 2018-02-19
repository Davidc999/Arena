package com.arena.entity.mob;

import com.arena.entity.Entity;
import com.arena.entity.Projectile.Projectile;
import com.arena.entity.Projectile.WizardProjectile;
import com.arena.graphics.AnimatedSprite;
import com.arena.graphics.Sprite;
import com.arena.graphics.SpriteSheet;
import com.arena.input.Mouse;
import com.arena.level.tile.Tile;

import java.util.ArrayList;
import java.util.List;

import static com.arena.entity.Entity.Direction.*;

public abstract class Mob extends Entity {

    protected AnimatedSprite animatedSprite;
    protected Direction dir = UP;
    protected boolean moving = false;

    protected List<Projectile> projectileList = new ArrayList<>();

    public void move(int xChange, int yChange){
        Direction newDir = DOWN;
        if(xChange > 0){
            newDir = RIGHT;
        }
        if(xChange < 0){
            newDir = LEFT;
        }
        if(yChange > 0){
            newDir = DOWN;
        }
        if(yChange < 0){
            newDir = UP;
        }
        if (dir != newDir) {
            dir = newDir;
            animatedSprite.setAnimation(dir);
        }

        if (!collision(xChange,yChange)) {
            x += xChange;
            animatedSprite.start();
        }

        if (!collision(xChange,yChange)) {
            y += yChange;
            animatedSprite.start();
        }
    }

    public void update(){

    }

    public void shoot(double dir){
        Projectile projectile = new WizardProjectile(x - animatedSprite.SIZE/2, y-animatedSprite.SIZE/2, dir, AnimatedSprite.arrow);

        Entity.Direction animDir;
        if(dir<0.785 && dir>=-0.785)
        {animDir = RIGHT;}
        else if(dir<-0.785 && dir>=-2.355)
        {animDir = UP;}
        else if(dir<2.355 && dir>=0.785)
        {animDir = DOWN;}
        else
        {animDir = LEFT;}
        projectile.setAnimationDir(animDir);
        projectileList.add(projectile);
        level.addEntity(projectile);
    }

    public boolean collision(int xChange, int yChange){
        for(int c=0; c<4; c++)
        {
            if(level.getTile((x+ (c%2)*0 + xChange)/ SpriteSheet.tiles.TILESIZE,(y+(c/2)*10+yChange)/SpriteSheet.tiles.TILESIZE).solid) {
                return true;
            }
        }

        return false;

    }

    public void render(){}

}
