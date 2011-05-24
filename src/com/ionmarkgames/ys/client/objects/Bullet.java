package com.ionmarkgames.ys.client.objects;

import com.ionmarkgames.ys.client.YSPanel;

public class Bullet extends Sprite {

    private int speed = 20;
    private You player;
    
    public Bullet(YSPanel panel, int x, int y, GameDir direction) {
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
        
        double part1 = Math.pow(( this.player.getCenterX() - this.getCenterX() ), 2);
		double part2 = Math.pow(( this.player.getCenterY() - this.getCenterY() ), 2);
		double underRadical = part1 + part2;
		
		int futureGridX = (getCenterX() + deltaX) / YSPanel.TILE_WIDTH;
		int futureGridY = (getCenterY() + deltaY) / YSPanel.TILE_HEIGHT;
		
        if (!this.panel.passable(futureGridX, futureGridY)) {
            this.panel.removeSprite(this);
        } else if (this.panel.hasEnemy(futureGridX, futureGridY)) {
        	this.panel.visit(this, futureGridX, futureGridY);
        	this.panel.removeSprite(this);
        } else if ((int)Math.sqrt(underRadical) / increment > 0) {
			this.panel.removeSprite(this);
		} else {
			this.setLocation(getX() + deltaX, getY() + deltaY);
		}
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
