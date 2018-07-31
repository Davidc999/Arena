package com.arena.entity.mob;

import com.arena.entity.Entity;

public class mobSpawner extends Entity {

    int spawnTimer, spawnTimerInit;
    int mobIndex;

    public mobSpawner(int x, int y, int mobIndex){
        spawnTimer = -1;
        spawnTimerInit = -1;
        this.x = x;
        this.y = y;
        this.mobIndex = mobIndex;
    }

    public mobSpawner(int spawnTimer, int x, int y, int mobIndex){
        spawnTimer = spawnTimer;
        spawnTimerInit = spawnTimer;
        this.x = x;
        this.y = y;
        this.mobIndex = mobIndex;
    }

    @Override
    public void update(){
        if(spawnTimerInit != -1){
            if(spawnTimer >0)
                spawnTimer--;
            else{
                spawnTimer = spawnTimerInit;
                spawn();
            }
        }
    }


    public void spawn(){
        switch (mobIndex){
            case 1:
                level.addEntity(new WimpyMonster(x,y));
                break;
        }
    }
}
