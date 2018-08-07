package com.arena.entity.mob;

import com.arena.entity.CollidableEntity;
import com.arena.entity.Projectile.Projectile;
import com.arena.entity.Projectile.WimpyProjectile;
import com.arena.entity.Projectile.WizardProjectile;
import com.arena.entity.particle.Particle;
import com.arena.graphics.AnimatedSprite;
import com.arena.graphics.Screen;
import com.arena.graphics.SpriteSheet;

import java.util.Random;

public class WimpyMonster extends Mob{

    Random random = new Random();
    int moveTimer;
    int xa,ya;
    int attackRange;

    public WimpyMonster(int x, int y){
        this.x = x;
        this.y = y;
        this.sprite = new AnimatedSprite(32,0,0,SpriteSheet.flyMonster,10,4 );
        this.sprite.setColisionBox(Direction.DOWN,7,0,21,30);
        this.sprite.setColisionBox(Direction.LEFT,0,2,30,19);
        this.sprite.setColisionBox(Direction.RIGHT,1,1,30,19);
        this.sprite.setColisionBox(Direction.UP,7,0,19,31);
        this.speed = 1;
        this.xp = 1;
        this.xpLvl = 1;
        this.projectileTimer = 75;
        this.attackRange = 200;
        this.hp = 10;
    }

    public void update(){

        updateMovement();

        sprite.update();

        updateShooting();

    }


    private void updateShooting(){
        if(projectileTimer >0)
            projectileTimer--;
        else{
            projectileTimer = 75;

            double dx = level.getPlayerX() - x;
            double dy = level.getPlayerY() - y;

            if( Math.sqrt(Math.pow(dx,2) + Math.pow(dy,2)) < attackRange){
                shoot(Math.atan2(dy,dx));
            }
        }

    }

    public void shoot(double dir){

        Projectile projectile = new WimpyProjectile(x, y , dir, this, new AnimatedSprite(SpriteSheet.wimpyProjectile.SIZE, 0, 0, SpriteSheet.wimpyProjectile, 3, 1));

        /*CollidableEntity.Direction animDir;
        if (dir < 0.785 && dir >= -0.785) {
            animDir = RIGHT;
        } else if (dir < -0.785 && dir >= -2.355) {
            animDir = UP;
        } else if (dir < 2.355 && dir >= 0.785) {
            animDir = DOWN;
        } else {
            animDir = LEFT;
        }
        projectile.setAnimationDir(animDir);*/
        level.addEntity(projectile);
    }

    private void updateMovement(){

        if(moveTimer > 0){
            moveTimer--;
        }
        else {

            moveTimer = 30 + random.nextInt(90);

            xa = random.nextInt(3) - 1;
            ya = random.nextInt(3) - 1;
        }

        if (xa != 0 || ya != 0) {
            move(xa * speed, ya * speed);
        } else {
            sprite.stop();
        }

    }

    public void render(Screen screen){
        screen.renderSprite(x - sprite.WIDTH/2,y - sprite.HEIGHT/2, sprite);
    }

    @Override
    public void handleCollision(CollidableEntity other){
        if(other instanceof WizardProjectile) {
            hp -= ((WizardProjectile) other).getDamage();
            if(hp <= 0) {
                remove();
                Particle p = new Particle((int) x, (int) y, 60, 4, 0xFFFF0000);
                level.addEntity(p);
                ((Projectile) other).getOwner().addXP(1);
            }
        }
        if(other instanceof Player){
           /* int dx = 0;
            int dy = 0;
            if(other.x > x + sprite.WIDTH/2)
                dx = -1;
            else if(other.x < x - sprite.WIDTH/2)
                dx = 1;
            if(other.y > y + sprite.HEIGHT/2)
                dy = -1;
            else if (other.y < y - sprite.HEIGHT/2)
                dy = 1;
            move(dx * 32, dy * 32);*/

            if(other.x > x + sprite.WIDTH/2)
                xa = -1;
            else if(other.x < x - sprite.WIDTH/2)
                xa = 1;
            if(other.y > y + sprite.HEIGHT/2)
                ya = -1;
            else if (other.y < y - sprite.HEIGHT/2)
                ya = 1;

            move(xa*speed*3, ya *speed*3);
        }
    }

}
