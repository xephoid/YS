package com.ionmarkgames.ys.client.objects;

import com.ionmarkgames.ys.client.YSPanel;

public class You extends Sprite {

    //private boolean shooting = false;
    
    public You(YSPanel panel) {
        super(panel, "/images/brain.gif");
        int x = 1;
        int y = 1;
        
        this.setLocation(x * YSPanel.TILE_WIDTH, y * YSPanel.TILE_HEIGHT);
    }
    
    @Override
    public void act() {
        // no-op
    }

    @Override
    public boolean passable() {
        return true;
    }

    public void moveUp() {
        if (this.panel.passable(this.getX() / YSPanel.TILE_WIDTH, (getY() - YSPanel.TILE_HEIGHT) / YSPanel.TILE_HEIGHT)) {
            this.panel.leave(this, getX() / YSPanel.TILE_WIDTH, getY() / YSPanel.TILE_HEIGHT);
            this.setLocation(getX(), getY() - YSPanel.TILE_HEIGHT);
            this.panel.visit(this, getX() / YSPanel.TILE_WIDTH, getY() / YSPanel.TILE_HEIGHT);
        }
    }
    
    public void moveDown() {
        if (this.panel.passable(getX() / YSPanel.TILE_WIDTH, (getY() + YSPanel.TILE_HEIGHT) / YSPanel.TILE_HEIGHT)) {
            this.panel.leave(this, getX() / YSPanel.TILE_WIDTH, getY() / YSPanel.TILE_HEIGHT);
            this.setLocation(getX(), getY() + YSPanel.TILE_HEIGHT);
            this.panel.visit(this, getX() / YSPanel.TILE_WIDTH, getY() / YSPanel.TILE_HEIGHT);
        }
    }
    
    public void moveLeft() {
        if (this.panel.passable((getX() - YSPanel.TILE_WIDTH) / YSPanel.TILE_WIDTH, getY() / YSPanel.TILE_HEIGHT)) {
            this.panel.leave(this, getX() / YSPanel.TILE_WIDTH, getY() / YSPanel.TILE_HEIGHT);
            this.setLocation(getX() - YSPanel.TILE_WIDTH, getY());
            this.panel.visit(this, getX() / YSPanel.TILE_WIDTH, getY() / YSPanel.TILE_HEIGHT);
        }
    }
    
    public void moveRight() {
        if (this.panel.passable((getX() + YSPanel.TILE_WIDTH) / YSPanel.TILE_WIDTH, getY() / YSPanel.TILE_HEIGHT)) {
            this.panel.leave(this, getX() / YSPanel.TILE_WIDTH, getY() / YSPanel.TILE_HEIGHT);
            this.setLocation(getX() + YSPanel.TILE_WIDTH, getY());
            this.panel.visit(this, getX() / YSPanel.TILE_WIDTH, getY() / YSPanel.TILE_HEIGHT);
        }
    }
    
    public void shoot(BulletDir dir) {
        this.panel.addSprite(new Bullet(this.panel, getX(), getY(), dir));
    }

    public boolean isBullet() {
        return false;
    }

    public boolean isPlayer() {
        return true;
    }

    public boolean isEnemy() {
        return false;
    }

    public boolean isWall() {
        return false;
    }
}
