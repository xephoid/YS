package com.ionmarkgames.ys.client.objects.enemies;

import com.ionmarkgames.ys.client.RestartException;
import com.ionmarkgames.ys.client.YSPanel;
import com.ionmarkgames.ys.client.objects.Enemy;
import com.ionmarkgames.ys.client.objects.GameDir;
import com.ionmarkgames.ys.client.objects.Identifiable;
import com.ionmarkgames.ys.client.objects.You;

public class Person extends Enemy {

	public Person(YSPanel panel, You player) {
		super(panel, player, "/images/guy.gif");
		
		this.power = 1;
		this.health = 7;
		this.speed = 20;
	}
	
	@Override
	public void act() throws RestartException {
		// no-op
	}

	@Override
	public void visit(Identifiable thing) {
		super.visit(thing);
		if (this.health > 0 && thing.isBullet()) {
			this.panel.leave(this, gridX(), gridY());
			this.doge();
			this.panel.visit(this, gridX(), gridY());
		}
	}
	
	public void doge() {
		if (player.getX() == this.getX()) {
			if (!this.move(GameDir.LEFT) || !this.move(GameDir.RIGHT)) { 
				if (player.getY() > this.getY()) {
					this.move(GameDir.UP);
				} else {
					this.move(GameDir.DOWN);
				}
			}
		} else if (player.getY() == this.getY()) {
			if (!this.move(GameDir.UP) || !this.move(GameDir.DOWN)) {
				if (player.getX() > this.getX()) {
					this.move(GameDir.LEFT);
				} else {
					this.move(GameDir.RIGHT);
				}
			}
		}
	}
}