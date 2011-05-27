package com.ionmarkgames.ys.client.objects;

import com.ionmarkgames.ys.client.YSPanel;

public class Wall extends Sprite {

    public Wall(YSPanel panel, String imgUrl, int x, int y) {
        super(panel, imgUrl);
        setLocation(x, y);
    }
    @Override
    public void act() {
        // no-op
    }
    
    @Override
    public void visit(Identifiable thing) {
    	// no-op
    }

    @Override
    public boolean passable() {
        return false;
    }
    public boolean isBullet() {
        return false;
    }
    public boolean isPlayer() {
        return false;
    }
    public boolean isEnemy() {
        return false;
    }
    public boolean isWall() {
        return true;
    }

}
