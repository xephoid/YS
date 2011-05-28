package com.ionmarkgames.ys.client.objects.enemies;

import com.ionmarkgames.ys.client.RestartException;
import com.ionmarkgames.ys.client.YSPanel;
import com.ionmarkgames.ys.client.objects.Enemy;
import com.ionmarkgames.ys.client.objects.GameDir;
import com.ionmarkgames.ys.client.objects.You;

public class Mic extends Enemy {

    enum Mode {
    	WAITING,
    	RUNNING,
    	SHOOTING
    }
    
    private Mode mode;
    
    private boolean shot = false;
    
    public Mic(YSPanel panel, You player) {
        super(panel, player, "/images/mic.gif");
        
        this.speed = 5;
        this.health = 15;
        this.power = 2;
        
        this.mode = Mode.WAITING;
    }
    
    @Override
    public void act() throws RestartException {
    	switch (mode) {
    		case WAITING :
    			if ((player.getX() == this.getX() || player.getY() == this.getY())) {
    				this.direction = this.getRandomDirection();
    				this.mode = Mode.RUNNING;
    	        }
    			break;
    		case RUNNING :
    			if (this.move(direction)) {
    				if (this.getX() == this.gridX() * YSPanel.TILE_WIDTH 
    						&& this.getY() == this.gridY() * YSPanel.TILE_HEIGHT) {
    					this.mode = Mode.WAITING;
    				}
    			} else {
    				this.mode = Mode.SHOOTING;
    			}
    			break;
    		case SHOOTING :
    			if (!shot) {
    				shoot();
    			}
    			break;
    	}
    }
    
    private void shoot() {
        GameDir shootDir = GameDir.UP;
        
        if (this.player.getX() > this.getX()) { 
            shootDir = GameDir.RIGHT;
        }
        
        if (this.player.getX() < this.getX()) { 
            shootDir = GameDir.LEFT;
        }
        
        if (this.player.getY() > this.getY()) { 
            shootDir = GameDir.DOWN;
        }
        
        if (this.player.getY() < this.getY()) { 
            shootDir = GameDir.UP;
        }
        
        this.panel.addSprite(new Note(panel, player, shootDir, this));
        shot = true;
    }

    public void doneShooting() {
    	this.mode = Mode.WAITING;
    	this.shot = false;
    }
}
