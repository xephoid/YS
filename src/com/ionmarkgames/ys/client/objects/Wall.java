package com.ionmarkgames.ys.client.objects;

import com.ionmarkgames.ys.client.YSPanel;

public class Wall extends Sprite {

    public Wall(YSPanel panel, int x, int y) {
        super(panel, "images/wall.gif");
        setLocation(x, y);
    }
    @Override
    public void act() {
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
