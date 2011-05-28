package com.ionmarkgames.ys.client.objects.enemies;

import java.util.ArrayList;
import java.util.List;

import com.ionmarkgames.ys.client.RestartException;
import com.ionmarkgames.ys.client.YSPanel;
import com.ionmarkgames.ys.client.objects.Enemy;
import com.ionmarkgames.ys.client.objects.You;

public class Skull extends Enemy {
	
	private List<Ghost> children = new ArrayList<Ghost>();
	private List<Ghost> died = new ArrayList<Ghost>();
	
	private int recoil = 0;

	public Skull(YSPanel panel, You player) {
		super(panel, player, "/images/skull_red.gif");
		
		this.speed = 0;
		this.health = 20;
		this.power = 20;
	}
	
	@Override
	public void act() throws RestartException {
		if (this.children.size() < 5 && recoil < 1) {
			this.direction = this.getRandomDirection();
			
			int spawnX = this.gridX();
			int spawnY = this.gridY();
			
			switch(direction) {
				case UP:
					spawnY--;
					break;
				case DOWN:
					spawnY++;
					break;
				case LEFT:
					spawnX--;
					break;
				case RIGHT:
					spawnX++;
					break;
			}
			
			if (panel.passable(spawnX, spawnY) && !panel.hasEnemy(spawnX, spawnY)) {
				Ghost newChild = new Ghost(panel, player);
				panel.addSprite(newChild);
				newChild.teleport(spawnX * YSPanel.TILE_WIDTH, spawnY * YSPanel.TILE_HEIGHT);
				children.add(newChild);
				recoil = 6;
			}
		} else {
			for (Ghost child : this.children) {
				if (child.isDead()) {
					this.died.add(child);
				}
			}
			for (Ghost toRemove : died) {
				this.children.remove(toRemove);
			}
			died.clear();
		}
		if (recoil > 0) {
			recoil--;
		}
	}
}
