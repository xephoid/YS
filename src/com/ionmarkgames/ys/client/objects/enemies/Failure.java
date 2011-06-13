package com.ionmarkgames.ys.client.objects.enemies;

import com.ionmarkgames.ys.client.RestartException;
import com.ionmarkgames.ys.client.YSPanel;
import com.ionmarkgames.ys.client.objects.Enemy;
import com.ionmarkgames.ys.client.objects.Identifiable;
import com.ionmarkgames.ys.client.objects.You;

public class Failure extends Enemy {
	
	private int targetX;
	private int targetY;

	public Failure(YSPanel panel, You player) {
		super(panel, player, "/images/f.gif");
		
		this.power = 2;
		this.health = 15;
		this.speed = 5;
		
		this.targetX = this.getX();
		this.targetY = this.getY();
	}
	
	@Override
	public void act() throws RestartException {
		if (this.targetX != this.getX() || this.targetY != this.getY()) {
			this.moveTowardsTarget(targetX, targetY);
		}
	}
	
	@Override
	public void visit(Identifiable thing) {
		super.visit(thing);
		if (this.health > 0 && thing.isBullet()) {
			this.targetX = player.getX();
			this.targetY = player.getY();
		}
	}
}
