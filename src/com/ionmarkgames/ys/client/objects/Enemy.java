package com.ionmarkgames.ys.client.objects;


import com.ionmarkgames.ys.client.YSPanel;

public abstract class Enemy extends Sprite {

    protected You player;
    protected int health = 1;
    protected int power = 0;
    protected int speed = 0;
    
    protected boolean dead = false;
    
    public Enemy(YSPanel panel, You player, String imgUrl) {
        super(panel, imgUrl);
        this.player = player;
        int x = 0;
        int y = 0;
        while(x < 5 || y < 5 || !this.panel.passable(x, y) || this.panel.hasEnemy(x, y)) {
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
        		this.dead = true;
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
		int futureGridX = gridX();
		int futureGridY = gridY();
		
		if (this.getX() > x) {
			deltaX = -speed;
			ifMoved = GameDir.LEFT;
			futureGridX = gridX() - 1;
		}
		if (this.getX() < x) {
			deltaX = speed;
			ifMoved = GameDir.RIGHT;
			futureGridX = gridX() + 1;
		}
		if (this.getY() > y) {
			deltaY = -speed;
			ifMoved = GameDir.UP;
			futureGridY = gridY() - 1;
		}
		if (this.getY() < y) {
			deltaY = speed;
			ifMoved = GameDir.DOWN;
			futureGridY = gridY() + 1;
		}
		
		if (this.gridX() * YSPanel.TILE_WIDTH == this.getX() 
				&& !this.panel.passable(futureGridX, this.gridY())) {
			deltaX = 0;
			futureGridX = gridX();
		}
		if (this.gridY() * YSPanel.TILE_HEIGHT == this.getY() 
				&& !this.panel.passable(gridX(), futureGridY)) {
			deltaY = 0;
			futureGridY = gridY();
		}
		
		if (deltaX != 0 && deltaY != 0) {
		    if (this.gridX() * YSPanel.TILE_WIDTH == this.getX() 
		    		&& !this.panel.passable(futureGridX, futureGridY)) {
		        deltaX = 0;
		        futureGridX = gridX();
		    }
		    if (this.gridY() * YSPanel.TILE_HEIGHT == this.getY() 
		    		&& !this.panel.passable(futureGridX, futureGridY)) {
		        deltaY = 0;
		        futureGridY = gridY();
		    }
		}
		
		if (this.panel.hasEnemy(futureGridX, futureGridY)) {
		    deltaX = 0;
		    deltaY = 0;
		}
		
        if (deltaX != 0 || deltaY != 0) {
        	this.direction = ifMoved;
        	this.setLocation(getX() + deltaX, getY() + deltaY);
        }
		
		return this.getX() == x && this.getY() == y;
	}
    
    protected boolean move(GameDir direction) {
    	switch(direction) {
    		case UP:
    			if (this.gridY() * YSPanel.TILE_HEIGHT == this.getY()) {
    				if (this.panel.passable(gridX(), (this.gridY() - 1))
    						&& !this.panel.hasEnemy(gridX(), gridY() - 1)) {
    					this.setLocation(getX(), getY() - speed);
    				} else {
        				return false;
        			}
    			} else {
    				this.setLocation(getX(), getY() - speed);
    			}
    			break;
    		case DOWN:
    			if (this.gridY() * YSPanel.TILE_HEIGHT == this.getY()) {
    				if (this.panel.passable(gridX(), (this.gridY() + 1))
    						&& !panel.hasEnemy(gridX(), gridY() + 1)) {
    					this.setLocation(getX(), getY() + speed);
    				} else {
    					return false;
    				}
    			} else {
    				this.setLocation(getX(), getY() + speed);
    			}
    			break;
    		case RIGHT:
    			if (this.gridX() * YSPanel.TILE_WIDTH == this.getX()) {
    				if (this.panel.passable((this.gridX() + 1), gridY())
    						&& !panel.hasEnemy(gridX() + 1, gridY())) {
    					this.setLocation(getX() + speed, getY());
    				} else {
    					return false;
    				}
    			} else {
    				this.setLocation(getX() + speed, getY());
    			}
    			break;
    		case LEFT:
    			if (this.gridX() * YSPanel.TILE_WIDTH == this.getX()) {
    				if (this.panel.passable((this.gridX() - 1), gridY())
    						&& !panel.hasEnemy(gridX() - 1, gridY())) {
    					this.setLocation(getX() - speed, getY());
    				} else {
    					return false;
    				}
    			}  else {
    				this.setLocation(getX() - speed, getY());
    			}
    			break;
    		case NONE:
    		default:
    			return false;
    	}
    	return true;
    }
    
    public GameDir getRandomDirection() {
    	int num = (int) (Math.random() * 4);
    	switch (num) {
    		case 0:
    			return GameDir.UP;
    		case 1:
    			return GameDir.DOWN;
    		case 2:
    			return GameDir.RIGHT;
    		default:
    			return GameDir.LEFT;
    	}
    }
    
    public GameDir getPlayerDirection() {
    	if (player.getX() > this.getX() && panel.passable(gridX() + 1, gridY())) {
    		return GameDir.RIGHT;
    	}
    	if (player.getY() < this.getY() && panel.passable(gridX(), gridY() - 1)) {
    		return GameDir.UP;
    	}
    	if (player.getX() < this.getX() && panel.passable(gridX() - 1, gridY())) {
    		return GameDir.LEFT;
    	}
    	if (player.getY() > this.getY() && panel.passable(gridX(), gridY() + 1)) {
    		return GameDir.DOWN;
    	}
    	return GameDir.NONE;
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
    
    public boolean isDead() {
    	return this.dead;
    }
}
