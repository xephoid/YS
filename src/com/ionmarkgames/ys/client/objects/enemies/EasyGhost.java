package com.ionmarkgames.ys.client.objects.enemies;

import com.ionmarkgames.ys.client.YSPanel;
import com.ionmarkgames.ys.client.objects.Enemy;
import com.ionmarkgames.ys.client.objects.You;

public class EasyGhost extends Enemy {

	private int wander = 0;
	
	public EasyGhost(YSPanel panel, You player) {
		super(panel, player, "/images/ghost.gif");
		this.power = 1;
		this.speed = 2;
		this.health = 1;
	}
	
	@Override
	public void act() {
		if (wander < 1) {
			this.direction = this.getRandomDirection();
			wander = 10;
		} else {
			wander--;
			this.move(direction);
		}
	}

	public void teleport(int x, int y) {
		this.setLocation(x, y);
	}

}
