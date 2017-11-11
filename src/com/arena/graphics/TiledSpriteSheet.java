package com.arena.graphics;

import com.arena.level.tile.Tile;

public class TiledSpriteSheet {

    public Tile[] tiles;

    public static TiledSpriteSheet tileSheet = new TiledSpriteSheet(SpriteSheet.tiles);

    public TiledSpriteSheet(SpriteSheet spriteSheet)
    {
        tiles = new Tile[spriteSheet.SIZE / spriteSheet.TILESIZE * spriteSheet.SIZE / spriteSheet.TILESIZE];

        for(int tileY = 0; tileY < spriteSheet.SIZE / spriteSheet.TILESIZE; tileY++){
            for(int tileX = 0; tileX < spriteSheet.SIZE / spriteSheet.TILESIZE; tileX++){
                tiles[tileX + tileY * (spriteSheet.SIZE / spriteSheet.TILESIZE)] = new Tile(new Sprite(spriteSheet.TILESIZE,tileX, tileY, SpriteSheet.tiles));
            }
        }
    }

}
