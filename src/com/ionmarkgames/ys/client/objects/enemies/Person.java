package com.ionmarkgames.ys.client.objects.enemies;

import com.ionmarkgames.ys.client.RestartException;
import com.ionmarkgames.ys.client.YSPanel;
import com.ionmarkgames.ys.client.objects.Enemy;
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
			while (!this.move(this.getRandomDirection()));
			this.panel.visit(this, gridX(), gridY());
		}
	}
}