package com.ionmarkgames.ys.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.ionmarkgames.ys.client.objects.You;

public class IncreaseStatInquery {

	private RootPanel panel = RootPanel.get("StatsArea");
	private You player;
	private UICallback<Integer> callback;
	
	
	public IncreaseStatInquery(You player, UICallback<Integer> callback) {
		this.player = player;
		this.callback = callback;
		panel.clear();
	}

	
	public void show() {
		HTML label = new HTML("Please select a stat to increase:<br/>");
		Button power = new Button();
		Button range = new Button();
		Button health = new Button();
		
		power.setText("POWER");
		range.setText("RANGE");
		health.setText("HEALTH");
		
		power.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				player.setPower(player.getPower() + 1);
				hide();
			}
		});
		
		range.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				player.setRange(player.getRange() + 1);
				hide();
			}
		});
		
		health.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				player.setMaxHealth(player.getMaxHealth() + 1);
				hide();
			}
		});
		
		panel.add(label);
		panel.add(power);
		panel.add(range);
		panel.add(health);
		panel.setVisible(true);
		power.getElement().focus();
	}
	
	public void hide() {
		panel.setVisible(false);
		callback.done(1);
	}
}
