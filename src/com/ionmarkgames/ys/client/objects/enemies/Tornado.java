package com.ionmarkgames.ys.client.objects.enemies;

import com.ionmarkgames.ys.client.RestartException;
import com.ionmarkgames.ys.client.YSPanel;
import com.ionmarkgames.ys.client.objects.Enemy;
import com.ionmarkgames.ys.client.objects.You;

public class Tornado extends Enemy {

	public Tornado(YSPanel panel, You player) {
		super(panel, player, "/images/tornado.gif");
		this.power = 2;
		this.health = 2;
		this.speed = 10;
		
		this.direction = this.getRandomDirection();
	}
	
	@Override
	public void act() throws RestartException {
		if (!this.move(this.direction)) {
			this.direction = this.getRandomDirection();
		}
	}
}
