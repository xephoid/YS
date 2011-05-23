package com.ionmarkgames.ys.client.objects;

import com.ionmarkgames.ys.client.YSPanel;

public abstract class Enemy extends Sprite {

    protected You player;
    protected int health = 1;
    protected int power = 0;
    protected int speed = 0;
    
    public Enemy(YSPanel panel, You player, String imgUrl) {
        super(panel, imgUrl);
        this.player = player;
        int x = 0;
        int y = 0;
        while(!this.panel.passable(x, y)) {
            x = (int)(Math.random() * panel.getMapWidth());
            y = (int)(Math.random() * panel.getMapHeight());
        }
        this.setLocation(x * YSPanel.TILE_WIDTH, y * YSPanel.TILE_HEIGHT);
    }
    
    @Override
    public boolean passable() {
        return true;
    }
    
    @Override
    public void visit(Identifiable thing) {
        if (thing.isBullet()) {
        	this.health -= player.getPower();
        	if (health < 1) {
        		this.panel.removeSprite(this);
        	}
        } else if(thing.isPlayer()) {
        	GameDir toMove = GameDir.UP;
        	switch (player.getDirection()) {
        		case UP: 
        			toMove = GameDir.DOWN;
        			break;
        		case DOWN: 
        			toMove = GameDir.UP;
        			break;
        		case RIGHT: 
        			toMove = GameDir.LEFT;
        			break;
        		case LEFT: 
        			toMove = GameDir.RIGHT;
        			break;
        	}
        	this.player.damage(this.power, toMove);
        }
    }
    
    protected boolean moveTowardsTarget(int x, int y) {
    	GameDir ifMoved = GameDir.UP;
		int deltaX = 0;
		int deltaY = 0;
		
		if (this.getX() > x) {
			deltaX = -speed;
			ifMoved = GameDir.LEFT;
		}
		if (this.getX() < x) {
			deltaX = speed;
			ifMoved = GameDir.RIGHT;
		}
		if (this.getY() > y) {
			deltaY = -speed;
			ifMoved = GameDir.UP;
		}
		if (this.getY() < y) {
			deltaY = speed;
			ifMoved = GameDir.DOWN;
		}
		
		int futureGridX = (getCenterX() + deltaX) / YSPanel.TILE_WIDTH;
		int futureGridY = (getCenterY() + deltaY) / YSPanel.TILE_HEIGHT;
		
		if (!this.panel.passable(futureGridX, this.gridY())) {
			deltaX = 0;
		}
		if (!this.panel.passable(gridX(), futureGridY)) {
			deltaY = 0;
		}
		
        if (deltaX != 0 || deltaY != 0) {
        	this.direction = ifMoved;
        	this.setLocation(getX() + deltaX, getY() + deltaY);
        }
		
		return this.getX() == x && this.getY() == y;
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
    
    public int getPower() {
    	return this.power;
    }
}
