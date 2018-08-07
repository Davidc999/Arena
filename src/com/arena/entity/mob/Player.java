package com.arena.entity.mob;

import com.arena.Game;
import com.arena.entity.CollidableEntity;
import com.arena.entity.Projectile.Projectile;
import com.arena.entity.Projectile.WizardProjectile;
import com.arena.entity.particle.Particle;
import com.arena.graphics.AnimatedSprite;
import com.arena.graphics.Screen;
import com.arena.input.KeyBoard;
import com.arena.input.Mouse;
import com.arena.GameScreen.level.Level;

public class Player extends Mob{

    private KeyBoard input;
    private int spriteRed = 0;

    public Player(KeyBoard input, AnimatedSprite animatedSprite, Level level){
        this.input = input;
        this.sprite = animatedSprite;
        this.speed = 1;
        this.hp = 99;
    }

    public Player(int x, int y, KeyBoard input, AnimatedSprite animatedSprite){
        this.x = x;
        this.y = y;
        this.sprite = animatedSprite;
        this.sprite.setColisionBox(Direction.DOWN,3,1,27,31);
        //this.sprite.setColisionBox(Direction.DOWN,3,1,3,3);
        this.sprite.setColisionBox(Direction.LEFT,2,1,28,31);
        this.sprite.setColisionBox(Direction.RIGHT,2,1,28,31);
        this.sprite.setColisionBox(Direction.UP,3,97-32*3,26,31);
        this.input = input;
        this.speed = 1;
        this.hp = 99;
    }

    public void update(){

        if(spriteRed > 0)
        {
            spriteRed--;
            if(spriteRed == 0)
                sprite.restore(1);
        }

        if(input.buildCastle){
            level.buildCastleReq(x,y);
        }

        int xa = 0;
        int ya = 0;

        if (input.up) ya -= speed;
        if (input.down) ya += speed;
        if (input.left) xa -= speed;
        if (input.right) xa += speed;

        if (xa != 0 || ya != 0) {
            move(xa, ya);
        }
        else {
            sprite.stop();
        }

        sprite.update();

        updateShooting();

    }


    public void updateShooting(){
        if(projectileTimer < WizardProjectile.getRateOfFire()) {
            projectileTimer += 1;
        }
        if(Mouse.getButton() == 1)
        {
            double dx = Mouse.getX() - Game.getWindowWidth()/2;
            double dy = Mouse.getY() - Game.getWindowHeight()/2;
            shoot(Math.atan2(dy,dx));
        }
    }

    public void render(Screen screen){
        screen.renderSprite(x - sprite.WIDTH/2,y - sprite.HEIGHT/2, sprite);
    }

    public void addXP(int xp){
        this.xp += xp;
        if(this.xp >= 5 * Math.pow(xpLvl,2))
            levelUp();
    }

    private void levelUp(){
        xpLvl++;
        level.addEntity(new Particle(x, y,130,30,0xffffff00));
    }

    public void handleCollision(CollidableEntity other){
        if(other instanceof Projectile) {
            if(((Projectile) other).getOwner() != this){
                hp -= ((Projectile) other).getDamage();
                sprite.effectPaint(0xffff0000);
                spriteRed = 3;
                if(hp <= 0) {
                    remove();
                    Particle p = new Particle((int) x, (int) y, 60, 4, 0xFFFF0000);
                    level.addEntity(p);
                }
            }
        }
    }
}
