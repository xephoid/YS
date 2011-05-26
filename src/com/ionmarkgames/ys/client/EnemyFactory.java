package com.ionmarkgames.ys.client;

import com.ionmarkgames.ys.client.objects.Enemy;
import com.ionmarkgames.ys.client.objects.You;
import com.ionmarkgames.ys.client.objects.enemies.Bug;
import com.ionmarkgames.ys.client.objects.enemies.Clown;
import com.ionmarkgames.ys.client.objects.enemies.Ghost;
import com.ionmarkgames.ys.client.objects.enemies.Snake;
import com.ionmarkgames.ys.client.objects.enemies.Spider;

public class EnemyFactory {

	private GameController controller;
	
	public EnemyFactory(GameController controller) {
		this.controller = controller;
	}
	
	public Enemy getEnemy() {
		YSPanel panel = controller.getCurrentPanel();
		You player = panel.getPlayer();
		switch (controller.getLevel()) {
			case 0:
				return new Bug(panel, player);
			case 1:
				return new Snake(panel, player);
			case 2:
				return new Spider(panel, player);
			case 3:
				return new Ghost(panel, player);
			case 4:
			    return new Clown(panel, player);
		}
		return null;
	}
}
