package com.ionmarkgames.ys.client.objects;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.ionmarkgames.ys.client.YSPanel;

public class Bullet extends Sprite {

    private BulletDir direction;
    private int speed = 10;
    private You player;
    
    public Bullet(YSPanel panel, int x, int y, BulletDir direction) {
        super(panel, "/images/bullet.gif");
        this.direction = direction;
        this.setLocation(x, y);
        this.player = panel.getPlayer();
    }
    
    @Override
    public void act() {
        int deltaX = 0;
        int deltaY = 0;
        int increment = YSPanel.TILE_WIDTH;
        switch(this.direction) {
            case DOWN:
                deltaY = speed;
                increment = YSPanel.TILE_HEIGHT;
                break;
            case UP:
                deltaY = -speed;
                increment = YSPanel.TILE_HEIGHT;
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
        
        double part1 = Math.pow(( (this.player.getX() + 10) - (this.getX() + 10) ), 2);
		double part2 = Math.pow(( (this.player.getY() + 10) - (this.getY() + 10) ), 2);
		double underRadical = part1 + part2;
		if ((int)Math.sqrt(underRadical) / increment > 0) {
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
