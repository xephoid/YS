package com.ionmarkgames.ys.client.objects.enemies;

import com.ionmarkgames.ys.client.RestartException;
import com.ionmarkgames.ys.client.YSPanel;
import com.ionmarkgames.ys.client.objects.Enemy;
import com.ionmarkgames.ys.client.objects.You;

public class Clown extends Enemy {

    private boolean freaking = false;
    private int wait = 0;
    private int waitMax = (int) (Math.random() * 100);
    
    public Clown(YSPanel panel, You player) {
        super(panel, player, "/images/clown.gif");
        this.health = 5;
        this.power = 1;
        this.speed = 10;
    }
    
    @Override
    public void act() throws RestartException {
        if (this.freaking) {
            if (this.gridX() * YSPanel.TILE_WIDTH == this.getX()
                    && this.gridY() * YSPanel.TILE_HEIGHT == this.getY()) {
                this.direction = this.getRandomDirection();
                this.wait++;
                if (this.wait > this.waitMax) {
                    this.freaking = false;
                    this.wait = 0;
                    this.waitMax = (int) (Math.random() * 100);
                }
            }
            this.move(this.direction);
        } else {
            this.wait++;
            if (this.wait >= this.waitMax) {
                this.freaking = true;
                this.wait = 0;
                this.waitMax = 15;
            }
        }
    }
}
