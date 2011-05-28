package com.ionmarkgames.ys.client.objects.enemies;

import com.ionmarkgames.ys.client.YSPanel;
import com.ionmarkgames.ys.client.objects.Enemy;
import com.ionmarkgames.ys.client.objects.You;

public class Ghost extends Enemy {

	public Ghost(YSPanel panel, You player) {
		super(panel, player, "/images/ghost.gif");
		this.power = 1;
		this.speed = 2;
		this.health = 1;
	}
	
	@Override
	public void act() {
		this.moveTowardsTarget(player.getX(), player.getY());
	}

	public void teleport(int x, int y) {
		this.setLocation(x, y);
	}
}
