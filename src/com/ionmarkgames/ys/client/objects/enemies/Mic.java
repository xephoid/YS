package com.ionmarkgames.ys.client.objects.enemies;

import com.ionmarkgames.ys.client.RestartException;
import com.ionmarkgames.ys.client.YSPanel;
import com.ionmarkgames.ys.client.objects.Enemy;
import com.ionmarkgames.ys.client.objects.GameDir;
import com.ionmarkgames.ys.client.objects.You;

public class Mic extends Enemy {

    private boolean shooting = false;
    private Note bullet;
    
    public Mic(YSPanel panel, You player) {
        super(panel, player, "/images/mic.gif");
        
        this.speed = 20;
        this.health = 15;
        this.power = 2;
    }
    
    @Override
    public void act() throws RestartException {
        if (!this.shooting && (player.getX() == this.getX() || player.getY() == this.getY())) {
            shoot();
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
        
        if (!this.move(this.getRandomDirection())) {
            this.shooting = true;
            this.panel.addSprite(new Note(panel, player, shootDir, this));
        }
    }

    public void doneShooting() {
        this.shooting = false;
    }
}
