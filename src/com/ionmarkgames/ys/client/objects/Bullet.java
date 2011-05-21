package com.ionmarkgames.ys.client.objects;

import com.ionmarkgames.ys.client.YSPanel;

public class Bullet extends Sprite {

    private BulletDir direction;
    private int speed = 10;
    
    public Bullet(YSPanel panel, int x, int y, BulletDir direction) {
        super(panel, "/images/bullet.gif");
        this.direction = direction;
        this.setLocation(x, y);
    }
    
    @Override
    public void act() {
        int deltaX = 0;
        int deltaY = 0;
        switch(this.direction) {
            case DOWN:
                deltaY = speed;
                break;
            case UP:
                deltaY = -speed;
                break;
            case RIGHT:
                deltaX = speed;
                break;
            case LEFT:
                deltaX = -speed;
                break;
        }
        
        if (!this.panel.passable((getX() + deltaX) / YSPanel.TILE_WIDTH, (getY() + deltaY) / YSPanel.TILE_HEIGHT)) {
            this.panel.visit(this, (getX() + deltaX) / YSPanel.TILE_WIDTH, (getY() + deltaY) / YSPanel.TILE_HEIGHT);
            this.panel.removeSprite(this);
        }

        this.setLocation(getX() + deltaX, getY() + deltaY);
    }

    @Override
    public boolean passable() {
        return true;
    }

    public boolean isBullet() {
        return true;
    }

    public boolean isPlayer() {
        return false;
    }

    public boolean isEnemy() {
        return false;
    }

    public boolean isWall() {
        return false;
    }

}
