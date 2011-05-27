package com.ionmarkgames.ys.client.objects.enemies;

import com.ionmarkgames.ys.client.RestartException;
import com.ionmarkgames.ys.client.YSPanel;
import com.ionmarkgames.ys.client.objects.Enemy;
import com.ionmarkgames.ys.client.objects.GameDir;
import com.ionmarkgames.ys.client.objects.Identifiable;
import com.ionmarkgames.ys.client.objects.You;

public class Note extends Enemy {
    private Mic parent;
    private boolean done = false;
    
    public Note(YSPanel panel, You player, GameDir dir, Mic parent) {
        super(panel, player, "/images/note.gif");
        this.direction = dir;
        this.parent = parent;
        
        this.speed = 5;
        this.health = 1;
        this.power = 1;
        
        this.setLocation(parent.getX(), parent.getY());
    }
    
    @Override
    public void visit(Identifiable thing) {
        if (thing.isBullet()) {
            this.panel.removeSprite(this);
            parent.doneShooting();
        }
    }

    @Override
    public void act() throws RestartException {
        if (!this.move(this.direction)) {
            this.panel.removeSprite(this);
            parent.doneShooting();
        }
    }
}