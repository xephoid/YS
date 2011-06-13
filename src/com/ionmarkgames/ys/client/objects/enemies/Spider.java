package com.ionmarkgames.ys.client.objects.enemies;

import com.ionmarkgames.ys.client.YSPanel;
import com.ionmarkgames.ys.client.objects.Enemy;
import com.ionmarkgames.ys.client.objects.You;

public class Spider extends Enemy {

	private enum Mode {
		WAIT,
		ATTACK,
		RETURN
	}
	
	private static final int MAX_THRESHOLD = 25;
	
	private Mode mode = Mode.WAIT;
	
	private int threshold = 0;
	private int targetX = 0;
	private int targetY = 0;
	
	private int homeX;
	private int homeY;
	
	public Spider(YSPanel panel, You player) {
		super(panel, player, "/images/spider.gif");
		
		this.homeX = this.getX();
		this.homeY = this.getY();
		this.health = 1;
		this.speed = 5;
		this.power = 1;
	}
	
	@Override
	public void act() {
		this.threshold++;
		switch(this.mode) {
			case WAIT:
				double part1 = Math.pow(( this.player.getCenterX() - this.getCenterX() ), 2);
				double part2 = Math.pow(( this.player.getCenterY() - this.getCenterY() ), 2);
				double underRadical = part1 + part2;
				
				if ((int)Math.sqrt(underRadical) / YSPanel.TILE_WIDTH < 4) {
					this.targetX = player.getX();
					this.targetY = player.getY();
					this.threshold = 0;
					this.mode = Mode.ATTACK;
				}
				break;
			case ATTACK:
				if (threshold > MAX_THRESHOLD || this.moveTowardsTarget(this.targetX, this.targetY)) {
					this.mode = Mode.RETURN;
					threshold = 0;
				}
				break;
			case RETURN:
				if (threshold > MAX_THRESHOLD || this.moveTowardsTarget(homeX, homeY)) {
					this.mode = Mode.WAIT;
					threshold = 0;
				}
				break;
		}
	}
}
