package com.ionmarkgames.ys.client;

import com.google.gwt.user.client.ui.RootPanel;
import com.ionmarkgames.ys.client.objects.Enemy;

public class GameController {

	private int level = -1;
	private String playerName;
	private boolean finished = false;
	private RootPanel playArea = RootPanel.get("PlayArea");
	private YSPanel currentPanel;
	private EnemyFactory enemyFactory;
	
	private static final String[] intros = new String[] {
		"You will need to press the <img src='/images/arrowkeys.gif'/> keys to navigate the area below.<br/>  We have also equipped you with a basic defense mechanism.  You can direct it using the <img src='/images/wasdkeys.gif' /> keys.  Please \"defend\" yourself from the <img src='images/bug.gif' />.",
		"Good job.  These next pests aren't so passive."
	};
	
	private static final int[][] mapDimensions = new int[][] {
		new int[] {
			21,21
		},
		new int[] {
			30, 30
		}
	};
	
	private static final String[][] albert = new String[][] {
		new String[] {
		    "What is this?",
			"Huh?",
			"This can't be right",
			"Hello?",
			"I must be dreaming",
			"Is this real?"
		},
		new String[] {
			"What is this?",
			"Huh?",
			"This can't be right",
			"Hello?",
			"I must be dreaming",
			"Is this real?"	
		}
	};
	
	public GameController() {
		this.level = -1;
		this.enemyFactory = new EnemyFactory(this);
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public YSPanel getCurrentPanel() {
		return this.currentPanel;
	}
	
	public void nextLevel() {
		level++;
		YSPanel result = new YSPanel(this);
		result.setPixelSize(mapDimensions[this.level][0] * YSPanel.TILE_WIDTH, mapDimensions[this.level][1] * YSPanel.TILE_HEIGHT);
		result.setMapWidth(mapDimensions[this.level][0]);
		result.setMapHeight(mapDimensions[this.level][1]);
		
		playArea.clear();
		playArea.add(result);
		
		result.setIntro(intros[this.level]);
		
		String aText = albert[this.level][(int)(Math.random() * albert[this.level].length)];
		result.setAlbertText(aText);
		
		this.currentPanel = result;
		result.start();
	}
	
	public void loading() {
		RootPanel.get("Loading").setVisible(true);
	}
	
	public void hidLoading() {
		RootPanel.get("Loading").setVisible(false);
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public boolean isFinished() {
		return finished;
	}
	
	public Enemy getLevelEnemy() {
		return this.enemyFactory.getEnemy();
	}
}