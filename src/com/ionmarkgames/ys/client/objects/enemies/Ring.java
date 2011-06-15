package com.ionmarkgames.ys.client.objects.enemies;

import com.ionmarkgames.ys.client.RestartException;
import com.ionmarkgames.ys.client.YSPanel;
import com.ionmarkgames.ys.client.objects.Enemy;
import com.ionmarkgames.ys.client.objects.You;

public class Ring extends Enemy {

	private int wait = 0;
	
	public Ring(YSPanel panel, You player) {
		super(panel, player, "/images/ring.gif");
		
		this.power = 5;
		this.speed = 5;
		this.health = 15;
	}
	
	@Override
	public void act() throws RestartException {
		if (wait > 0) {
			wait--;
			this.move(direction);
		} else {
			direction = this.getPlayerDirection();
			if (move(direction)) {
				wait = 3;
			} else {
				direction = this.getRandomDirection();
				wait = (int)(Math.random() * 5) * 4;
			}
		}
	}
}
