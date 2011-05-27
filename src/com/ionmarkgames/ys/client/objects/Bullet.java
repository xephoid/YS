package com.ionmarkgames.ys.client.objects;

import com.ionmarkgames.ys.client.YSPanel;

public class Bullet extends Sprite {

    private int speed = 20;
    private You player;
    
    public Bullet(YSPanel panel, You player, GameDir direction) {
        super(panel, "/images/bullet_" + (player.getPower() > 8 ? 8 : player.getPower()) + ".gif");
        this.direction = direction;
        this.setLocation(player.getX(), player.getY());
        this.player = player;
    }
    
    @Override
    public void act() {
    	if (this.panel.hasEnemy(gridX(), gridY())) {
        	this.panel.visit(this, gridX(), gridY());
        	this.panel.removeSprite(this);
        	return;
        }
    	
        int deltaX = 0;
        int deltaY = 0;
        
        int futureGridX = gridX();
		int futureGridY = gridY();
		
        int increment = YSPanel.TILE_WIDTH;
        switch(this.direction) {
            case DOWN:
                deltaY = speed;
                increment = YSPanel.TILE_HEIGHT;
                futureGridY = gridY() + 1;
                break;
            case UP:
                deltaY = -speed;
                increment = YSPanel.TILE_HEIGHT;
                futureGridY = gridY() - 1;
                break;
            case RIGHT:
                deltaX = speed;
                futureGridX = gridX() + 1;
                break;
            case LEFT:
                deltaX = -speed;
                futureGridX = gridX() - 1;
                break;
        }
        
        double part1 = Math.pow(( this.player.getCenterX() - this.getCenterX() ), 2);
		double part2 = Math.pow(( this.player.getCenterY() - this.getCenterY() ), 2);
		double underRadical = part1 + part2;
		
        if (!this.panel.passable(futureGridX, futureGridY)) {
            this.panel.removeSprite(this);
        } else if ((int)Math.sqrt(underRadical) / increment > player.getRange() - 1) {
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
