package com.ionmarkgames.ys.client.objects;

import com.ionmarkgames.ys.client.YSPanel;

public class Enemy extends Sprite {

    private You player;
    private boolean shooting = false;
    
    public Enemy(YSPanel panel, You player) {
        super(panel, "/images/bug.gif");
        this.player = player;
        int x = 0;
        int y = 0;
        while(!this.panel.passable(x, y)) {
            x = (int)(Math.random() * YSPanel.MAP_WIDTH);
            y = (int)(Math.random() * YSPanel.MAP_HEIGHT);
        }
        this.setLocation(x * YSPanel.TILE_WIDTH, y * YSPanel.TILE_HEIGHT);
    }
    
    @Override
    public void act() {
    	// no-op
    }

    @Override
    public boolean passable() {
        return false;
    }
    
    @Override
    public void visit(Identifiable thing) {
        if (thing.isBullet()) {
            this.panel.removeSprite(this);
        }
    }

    public boolean isBullet() {
        return false;
    }

    public boolean isPlayer() {
        return false;
    }

    public boolean isEnemy() {
        return true;
    }

    public boolean isWall() {
        return false;
    }
}
