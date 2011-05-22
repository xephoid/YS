package com.ionmarkgames.ys.client;

import com.google.gwt.user.client.ui.RootPanel;

public class GameController {

	private int level = -1;
	private String playerName;
	private boolean finished = false;
	private RootPanel playArea = RootPanel.get("PlayArea");
	
	private static final String[] intros = new String[] {
		"You will need to press the <img src='/images/arrowkeys.gif'/> keys to navigate the area below.<br/>  We have also equipped you with a basic defense mechanism.  You can direct it using the <img src='/images/wasdkeys.gif' /> keys.  Please \"defend\" yourself from the <img src='images/bug.gif' />.",
		"Good job.  These next pests aren't so passive."
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
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public void nextLevel() {
		level++;
		YSPanel result = new YSPanel(this);
		result.setPixelSize(1020, 720);
		
		playArea.clear();
		playArea.add(result);
		
		result.setIntro(intros[this.level]);
		
		String aText = albert[this.level][(int)(Math.random() * albert[this.level].length)];
		result.setAlbertText(aText);
		
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
}
