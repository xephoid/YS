package com.ionmarkgames.ys.client.ui;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;

public class LifePanel extends HorizontalPanel {

	public LifePanel() {
	}
	
	public void update(int health) {
		this.clear();
		for (int i = 0 ; i < health - 1; i++) {
			this.add(new Image("/images/brain.gif"));
		}
	}
}
