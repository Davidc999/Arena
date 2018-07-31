package com.arena.entity.particle;

import com.arena.entity.Entity;
import com.arena.graphics.Screen;
import com.arena.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Particle extends Entity{

    private List<Particle> particles = new ArrayList<>();

    private int life;

    protected double xDouble,yDouble,xa, ya;


    public Particle(int x, int y, int life)
    {
        this.x = x;
        this.y = y;
        this.life = life;
        sprite = Sprite.particle_normal;

        this.xa = random.nextGaussian();
        this.ya = random.nextGaussian();
        this.xDouble = x;
        this.yDouble = y;
    }

    public Particle(int x, int y, int life, Sprite sprite)
    {
        this.x = x;
        this.y = y;
        this.life = life;
        this.sprite = sprite;

        this.xa = random.nextGaussian();
        this.ya = random.nextGaussian();
        this.xDouble = x;
        this.yDouble = y;
    }

    public Particle(int x, int y, int life, int amount){

        this(x,y,life); // Note that this does NOT create a new particle, but calls the other constructor just like a regular method. This way we do not discard the first instance created by calling Particle(x,y,life,amount)
        for(int i=0; i < amount-1; i++){
            particles.add(new Particle(x,y,life));
        }
        particles.add(this);
    }

    public Particle(int x, int y, int life, int amount, int color){

        this(x,y,life,new Sprite(3,color)); // Note that this does NOT create a new particle, but calls the other constructor just like a regular method. This way we do not discard the first instance created by calling Particle(x,y,life,amount)

        for(int i=0; i < amount-1; i++){
            particles.add(new Particle(x,y,life,sprite));
        }
        particles.add(this);
    }

    public void update() {
        for(int i=0; i < particles.size(); i++) {
            Particle p = particles.get(i);
            p.xDouble += p.xa;
            p.yDouble += p.ya;
            if(p.life > 0)
            {p.life--;}
            else
            {
                p.remove();
                particles.remove(p);
            }
        }
    }

    public void render(Screen screen) {
        for(int i=0; i < particles.size(); i++) {
            Particle p = particles.get(i);
            screen.renderSprite((int) p.xDouble, (int) p.yDouble, sprite);
        }
    }

}
