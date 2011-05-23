package com.ionmarkgames.ys.client.objects.enemies;

import com.ionmarkgames.ys.client.YSPanel;
import com.ionmarkgames.ys.client.objects.Enemy;
import com.ionmarkgames.ys.client.objects.You;

public class Bug extends Enemy {

	public Bug(YSPanel panel, You player) {
		super(panel, player, "/images/bug.gif");
		this.health = 1;
		this.power = 1;
	}

	@Override
	public void act() {
		// no-op
	}
}
