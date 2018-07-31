package com.arena.graphics;

public class BoundingBox {

    int x, y, height, width;

    public BoundingBox(int x, int y, int height, int width){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public BoundingBox translate(int x, int y){
        return new BoundingBox(this.x + x , this.y + y, this.height, this.width);
    }

    public boolean isCollision(BoundingBox other){
        return (this.x < other.x + other.width) && (this.x + this.width > other.x)
                && (this.y < other.y + other.height) && (this.y + this.height > other.y);
    }
}
