package com.ionmarkgames.ys.client.objects.enemies;

import com.ionmarkgames.ys.client.RestartException;
import com.ionmarkgames.ys.client.YSPanel;
import com.ionmarkgames.ys.client.objects.Enemy;
import com.ionmarkgames.ys.client.objects.GameDir;
import com.ionmarkgames.ys.client.objects.You;

public class Mic extends Enemy {

    private boolean shooting = false;
    
    public Mic(YSPanel panel, You player) {
        super(panel, player, "/images/mic.gif");
        
        this.speed = 20;
        this.health = 15;
        this.power = 2;
    }
    
    @Override
    public void act() throws RestartException {
        if ((player.getX() == this.getX() || player.getY() == this.getY()) && !this.shooting) {
            shoot();
        }
    }
    
    private void shoot() {
        this.shooting = true;
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
        while (!this.move(this.getRandomDirection()));
    }

    public void doneShooting() {
        this.shooting = false;
    }
    
    class Note extends Enemy {
        private Mic parent;
        
        public Note(YSPanel panel, You player, GameDir dir, Mic parent) {
            super(panel, player, "/images/note.gif");
            this.direction = dir;
            this.parent = parent;
            
            this.speed = 10;
            this.health = 100;
            this.power = 2;
            
            this.setLocation(parent.getX(), parent.getY());
        }

        @Override
        public void act() throws RestartException {
            if (!this.move(this.direction)) {
                this.panel.removeSprite(this);
                parent.doneShooting();
            }
        }
    }
}
