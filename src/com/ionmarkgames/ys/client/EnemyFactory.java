package com.ionmarkgames.ys.client;

import com.ionmarkgames.ys.client.objects.Enemy;
import com.ionmarkgames.ys.client.objects.You;
import com.ionmarkgames.ys.client.objects.enemies.Bug;
import com.ionmarkgames.ys.client.objects.enemies.Clown;
import com.ionmarkgames.ys.client.objects.enemies.Coin;
import com.ionmarkgames.ys.client.objects.enemies.EasyGhost;
import com.ionmarkgames.ys.client.objects.enemies.Failure;
import com.ionmarkgames.ys.client.objects.enemies.Lips;
import com.ionmarkgames.ys.client.objects.enemies.Mic;
import com.ionmarkgames.ys.client.objects.enemies.Person;
import com.ionmarkgames.ys.client.objects.enemies.Ring;
import com.ionmarkgames.ys.client.objects.enemies.Skull;
import com.ionmarkgames.ys.client.objects.enemies.Snake;
import com.ionmarkgames.ys.client.objects.enemies.Spider;
import com.ionmarkgames.ys.client.objects.enemies.Tornado;

public class EnemyFactory {

	private GameController controller;
	
	public EnemyFactory(GameController controller) {
		this.controller = controller;
	}
	
	public Enemy getEnemy() {
		YSPanel panel = controller.getCurrentPanel();
		You player = panel.getPlayer();
		switch (controller.getLevel()) {
			case 0: // entomophobia
				return new Bug(panel, player);
			case 1: // ophidiophobia
				return new Snake(panel, player);
			case 2: // phasmophobia
				return new EasyGhost(panel, player);
			case 3: // arachnophobia
				return new Spider(panel, player);
			case 4: // coulrophobia
			    return new Clown(panel, player);
			case 5: // sociaphobia
				return new Person(panel, player);
			case 6: // acrophobia
				return new Tornado(panel, player);
			case 7: // chrometophobia
				return new Coin(panel, player);
			case 8: // intimacy
			    return new Lips(panel, player);
			case 9: // commitment
				return new Ring(panel, player);
			case 10: // failure
			    return new Failure(panel, player);
			case 11: // glossophobia
			    return new Mic(panel, player);
			case 12: // thanathophobia
				return new Skull(panel, player);
		}
		return null;
	}
}
