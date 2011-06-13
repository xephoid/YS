package com.ionmarkgames.ys.client.objects.enemies;

import com.ionmarkgames.ys.client.YSPanel;
import com.ionmarkgames.ys.client.objects.Enemy;
import com.ionmarkgames.ys.client.objects.You;

public class Spider extends Enemy {
	
	private int homeX;
	private int homeY;
	
	public Spider(YSPanel panel, You player) {
		super(panel, player, "/images/spider.gif");
		
		this.homeX = this.getX();
		this.homeY = this.getY();
		this.health = 1;
		this.speed = 2;
		this.power = 1;
	}
	
	@Override
	public void act() {
		if ((player.getX() == this.getX() || player.getY() == this.getY())) {
			this.moveTowardsTarget(player.getX(), player.getY());
		} else {
			this.moveTowardsTarget(homeX, homeY);
		}
	}
}
