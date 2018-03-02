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
    protected int projectileTimer = 0;

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
        if(projectileTimer < WizardProjectile.getRateOfFire())
        { }
        else {
            projectileTimer = 0;
            Projectile projectile = new WizardProjectile(x - animatedSprite.SIZE / 2, y - animatedSprite.SIZE / 2, dir, new AnimatedSprite(32, 0, 0, SpriteSheet.arrow, 3, 4));

            Entity.Direction animDir;
            if (dir < 0.785 && dir >= -0.785) {
                animDir = RIGHT;
            } else if (dir < -0.785 && dir >= -2.355) {
                animDir = UP;
            } else if (dir < 2.355 && dir >= 0.785) {
                animDir = DOWN;
            } else {
                animDir = LEFT;
            }
            projectile.setAnimationDir(animDir);
            level.addEntity(projectile);
        }
    }

    // Note that going over c%2 and c/2 actually looks at all corners of out sprite.
// The amount we multiply by sets a distance from the center of the sprite that we want to check for.
// This is sort of a 'bounding box'. The reason c%2 has 0, for example is that the sprite connecting the ocean
// And the grass has half of it looking like grass, and so we actually allow our player sprite to walk onto that tile
// right up to the center of the player sprite. This of course won't work in general, and we may need another solution pretty soon.
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
