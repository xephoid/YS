package com.ionmarkgames.ys.client.objects;

import com.ionmarkgames.ys.client.YSPanel;

public class You extends Sprite {
	
	private int power = 1;
	private int range = 1;
	private int health = 1;
	private int damage = 0;
	private GameDir damageDir;

    public You(YSPanel panel) {
        super(panel, "/images/brain.gif");
        int x = 1;
        int y = 1;
        
        this.setLocation(x * YSPanel.TILE_WIDTH, y * YSPanel.TILE_HEIGHT);
    }
    
    @Override
    public void act() {
    	if (this.damage > 0) {
    		this.health -= this.damage;
    		switch(this.damageDir) {
    			case UP :
    				this.moveUp();
    				break;
    			case DOWN :
    				this.moveDown();
    				break;
    			case RIGHT :
    				this.moveRight();
    				break;
    			case LEFT :
    				this.moveLeft();
    				break;
    		}
    		
    		if (this.health < 1) {
    			// TODO: restart level
    		}
    		
    		this.damage = 0;
    	}
    }
    
    @Override
    public void visit(Identifiable thing) {
    	if (thing.isEnemy()) {
    		Enemy enemy = (Enemy) thing;
    		
    		this.damage(enemy.getPower(), enemy.getDirection());
    	}
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
            this.direction = GameDir.UP;
        }
    }
    
    public void moveDown() {
        if (this.panel.passable(getX() / YSPanel.TILE_WIDTH, (getY() + YSPanel.TILE_HEIGHT) / YSPanel.TILE_HEIGHT)) {
            this.panel.leave(this, getX() / YSPanel.TILE_WIDTH, getY() / YSPanel.TILE_HEIGHT);
            this.setLocation(getX(), getY() + YSPanel.TILE_HEIGHT);
            this.panel.visit(this, getX() / YSPanel.TILE_WIDTH, getY() / YSPanel.TILE_HEIGHT);
            this.direction = GameDir.DOWN;
        }
    }
    
    public void moveLeft() {
        if (this.panel.passable((getX() - YSPanel.TILE_WIDTH) / YSPanel.TILE_WIDTH, getY() / YSPanel.TILE_HEIGHT)) {
            this.panel.leave(this, getX() / YSPanel.TILE_WIDTH, getY() / YSPanel.TILE_HEIGHT);
            this.setLocation(getX() - YSPanel.TILE_WIDTH, getY());
            this.panel.visit(this, getX() / YSPanel.TILE_WIDTH, getY() / YSPanel.TILE_HEIGHT);
            this.direction = GameDir.LEFT;
        }
    }
    
    public void moveRight() {
        if (this.panel.passable((getX() + YSPanel.TILE_WIDTH) / YSPanel.TILE_WIDTH, getY() / YSPanel.TILE_HEIGHT)) {
            this.panel.leave(this, getX() / YSPanel.TILE_WIDTH, getY() / YSPanel.TILE_HEIGHT);
            this.setLocation(getX() + YSPanel.TILE_WIDTH, getY());
            this.panel.visit(this, getX() / YSPanel.TILE_WIDTH, getY() / YSPanel.TILE_HEIGHT);
            this.direction = GameDir.RIGHT;
        }
    }
    
    public void shoot(GameDir dir) {
        this.panel.addSprite(new Bullet(this.panel, getX(), getY(), dir));
    }
    
    public void damage(int amount, GameDir dir) {
    	this.damage += amount;
    	this.damageDir = dir;
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

	public void setPower(int power) {
		this.power = power;
	}

	public int getPower() {
		return power;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public int getRange() {
		return range;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getHealth() {
		return health;
	}
}
