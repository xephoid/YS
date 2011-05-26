package com.ionmarkgames.ys.client;

import com.google.gwt.user.client.ui.RootPanel;
import com.ionmarkgames.ys.client.objects.Enemy;
import com.ionmarkgames.ys.client.objects.You;

public class GameController {

	private int level = -1;
	private String playerName;
	private boolean finished = false;
	private RootPanel playArea = RootPanel.get("PlayArea");
	private YSPanel currentPanel;
	private EnemyFactory enemyFactory;
	
	private int playerPower = 1;
	private int playerRange = 1;
	private int playerHealth = 1;
	
	private static final String[] intros = new String[] {
		"You will need to press the <img src='/images/arrowkeys.gif'/> keys to navigate the area below.<br/>  We have also equipped you with a basic defense mechanism.  You can direct it using the <img src='/images/wasdkeys.gif' /> keys.  Please \"defend\" yourself from the <img src='images/bug.gif' />.",
		"Well done.  You are catching on.  Let's practice some more \"self defense.\"",
		"Good job, you may notice these are getting a bit trickier.  This is entirely intended.",
		"Excellent.  You may have questions about what it is your doing right now.  For legal reasons we have chosen not to tell you.",
		"In this next area keep in mind some obsticals in the \"simulation\" may take more time to overcome."
	};
	
	private static final int[][] mapDimensions = new int[][] {
		new int[] { // Level 1
			21,21
		},
		new int[] { // Level 2
			30, 30
		},
		new int[] { // Level 3
			30, 30
		},
		new int[] { // Level 4
			30, 30
		},
		new int[] { // Level 5
		    30, 30
	    },
	};
	
	private static final String[] walls = new String[] {
		"/images/wall.gif",
		"/images/bush.gif",
		"/images/web.gif",
		"/images/grave1.gif",
		"/images/circus.gif"
	};
	
	private static final String[][] albert = new String[][] {
		new String[] {  // 1. Denial
		    "What is this?",
			"Huh?",
			"This can't be right.",
		},
		new String[] { // 2. Denial
			"Hello?",
			"I must be dreaming.",
			"Is this real?"	
		},
		new String[] { // 3. Isolation
			"I don't want to be bothered right now.",
			"It must be nothing.",
			"Please leave.",
		},
		new String[] { // 4. Isolation
			"I'm to busy for this right now.",
			"Don't you have something better to do?",
			"..."
		},
		new String[] { // 5. Anger
		    "Are you listening to me?",
		    "STOP!",
		    "Go away.",
		},
        new String[] { // 6. Anger 
	        "LEAVE ME ALONE!",
	        "Stop or else.",
	        "YOU SUCK!",
		},
        new String[] { // 7. Anger
	        "What is your problem?",
	        "I HATE YOU!",
	        "This is stupid."
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
		result.setWallImageUrl(walls[level]);
		
		playArea.clear();
		playArea.add(result);
		
		result.setIntro(intros[this.level]);
		
		String aText = albert[this.level][(int)(Math.random() * albert[this.level].length)];
		result.setAlbertText(aText);
		
		this.currentPanel = result;
		result.start();
	}
	
	public void persistPlayer(You player) {
		this.playerPower = player.getPower();
		this.playerRange = player.getRange();
		this.playerHealth = player.getMaxHealth();
	}
	
	public void updatePlayer(You player) {
		player.setPower(this.playerPower);
		player.setRange(this.playerRange);
		player.setMaxHealth(this.playerHealth);
		player.resetHealth();
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