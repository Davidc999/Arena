package com.arena.graphics;

import com.arena.GameScreen.level.tile.Tile;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class TiledSpriteSheet {

    public Tile[] tiles;
    public Set<Integer> solidTiles = new HashSet<>();

    public static TiledSpriteSheet tileSheet = new TiledSpriteSheet(SpriteSheet.tiles);

    public TiledSpriteSheet(SpriteSheet spriteSheet)
    {
        solidTiles.addAll( Arrays.asList(70,0,1,2,21,23,42,43,44));

        tiles = new Tile[spriteSheet.WIDTH / spriteSheet.TILE_WIDTH * spriteSheet.HEIGHT / spriteSheet.TILE_HEIGHT];

        for(int tileY = 0; tileY < spriteSheet.HEIGHT / spriteSheet.TILE_HEIGHT; tileY++){
            for(int tileX = 0; tileX < spriteSheet.WIDTH / spriteSheet.TILE_WIDTH; tileX++){
                tiles[tileX + tileY * (spriteSheet.WIDTH / spriteSheet.TILE_WIDTH)] = new Tile(new Sprite(spriteSheet.TILE_WIDTH,tileX, tileY, SpriteSheet.tiles));
                //TODO: This is hardcoded solidifying. Replace me.
                if(solidTiles.contains(tileX + tileY * (spriteSheet.WIDTH / spriteSheet.TILE_WIDTH)))
                    tiles[tileX + tileY * (spriteSheet.WIDTH / spriteSheet.TILE_WIDTH)].solid = true;
            }
        }
    }

}
