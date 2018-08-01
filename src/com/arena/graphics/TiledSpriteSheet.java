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

        tiles = new Tile[spriteSheet.SIZE / spriteSheet.TILESIZE * spriteSheet.SIZE / spriteSheet.TILESIZE];

        for(int tileY = 0; tileY < spriteSheet.SIZE / spriteSheet.TILESIZE; tileY++){
            for(int tileX = 0; tileX < spriteSheet.SIZE / spriteSheet.TILESIZE; tileX++){
                tiles[tileX + tileY * (spriteSheet.SIZE / spriteSheet.TILESIZE)] = new Tile(new Sprite(spriteSheet.TILESIZE,tileX, tileY, SpriteSheet.tiles));
                //TODO: This is hardcoded solidifying. Replace me.
                if(solidTiles.contains(tileX + tileY * (spriteSheet.SIZE / spriteSheet.TILESIZE)))
                    tiles[tileX + tileY * (spriteSheet.SIZE / spriteSheet.TILESIZE)].solid = true;
            }
        }
    }

}
