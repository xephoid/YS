package com.ionmarkgames.ys.client.objects.enemies;

import com.ionmarkgames.ys.client.RestartException;
import com.ionmarkgames.ys.client.YSPanel;
import com.ionmarkgames.ys.client.objects.Enemy;
import com.ionmarkgames.ys.client.objects.Identifiable;
import com.ionmarkgames.ys.client.objects.You;

public class Failure extends Enemy {

    public Failure(YSPanel panel, You player) {
        super(panel, player, "f.gif");
        
        this.power = 1;
        this.health = 15;
        this.speed = 0;
    }
    
    @Override
    public void act() throws RestartException {
        // no-op
    }

    @Override
    public void visit(Identifiable thing) {
        super.visit(thing);
        if (this.health > 0 && thing.isBullet()) {
            int x = 0;
            int y = 0;
            while(x < 5 || y < 5 || !this.panel.passable(x, y) || this.panel.hasEnemy(x, y)) {
                x = (int)(Math.random() * panel.getMapWidth());
                y = (int)(Math.random() * panel.getMapHeight());
            }
            this.setLocation(x * YSPanel.TILE_WIDTH, y * YSPanel.TILE_HEIGHT);
            this.panel.visit(this, x, y);
        }
    }
}
