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
    	if (this.collide(gridX(), gridY())) {
        	return;
        }
    	
        int deltaX = 0;
        int deltaY = 0;
        
        int futureGridX = gridX();
		int futureGridY = gridY();
		
        switch(this.direction) {
            case DOWN:
                if (this.collide(gridX(), gridY() - 1)) {
                	return;
                }
                deltaY = speed;
                futureGridY = gridY() + 1;
                break;
            case UP:
            	if (this.collide(gridX(), gridY() + 1)) {
                	return;
                }
                deltaY = -speed;
                futureGridY = gridY() - 1;
                break;
            case RIGHT:
            	if (this.collide(gridX() - 1, gridY())) {
                	return;
                }
                deltaX = speed;
                futureGridX = gridX() + 1;
                break;
            case LEFT:
            	if (this.collide(gridX() + 1, gridY())) {
                	return;
                }
                deltaX = -speed;
                futureGridX = gridX() - 1;
                break;
        }
        
        double part1 = Math.pow(( this.player.gridX() - this.gridX() ), 2);
		double part2 = Math.pow(( this.player.gridY() - this.gridY() ), 2);
		double underRadical = part1 + part2;
		
        if (!this.panel.passable(futureGridX, futureGridY)) {
            this.panel.removeSprite(this);
        } else if ((int)Math.sqrt(underRadical) > player.getRange() - 1) {
			this.panel.removeSprite(this);
		} else {
			this.setLocation(getX() + deltaX, getY() + deltaY);
		}
    }
    
    public boolean collide(int x, int y) {
    	if (this.panel.hasEnemy(x, y)) {
        	this.panel.visit(this, x, y);
        	this.panel.removeSprite(this);
        	return true;
        }
    	return false;
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
