package com.ionmarkgames.ys.client.objects.enemies;

import com.ionmarkgames.ys.client.YSPanel;
import com.ionmarkgames.ys.client.objects.Enemy;
import com.ionmarkgames.ys.client.objects.You;

public class Snake extends Enemy {

	private boolean waiting = true;
	private int threshold = 0;
	private int thresholdMax = 100;
	
	public Snake(YSPanel panel, You player) {
		super(panel, player, "/images/snake.gif");
		
		this.power = 1;
		this.speed = 5;
		this.health = 1;
		
		this.thresholdMax = (int) (Math.random() * 100);
	}
	
	@Override
	public void act() {
		if (waiting) {
			threshold++;
			if (threshold > thresholdMax) {
				this.direction = this.getRandomDirection();
				waiting = false;
			}
		} else {
			if (!this.move(this.direction)) {
				this.waiting = true;
				threshold = 0;
				this.thresholdMax = (int) (Math.random() * 100);
			}
		}
	}
}
