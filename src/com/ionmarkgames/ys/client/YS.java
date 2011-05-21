package com.ionmarkgames.ys.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.ionmarkgames.ys.client.ui.MessagePanel;
import com.ionmarkgames.ys.client.ui.NameInqueryPanel;
import com.ionmarkgames.ys.client.ui.UICallback;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class YS implements EntryPoint, UICallback<String> {

	private YSPanel gamePanel = new YSPanel();
	private RootPanel playArea = RootPanel.get("PlayArea");
    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        playArea.setPixelSize(1020, 720); // 51 x 38
        NameInqueryPanel getName = new NameInqueryPanel(this);
        playArea.add(getName);
    }
    
    public void start() {
        playArea.clear();
        gamePanel.setPixelSize(1020, 720);
        playArea.add(gamePanel);
        gamePanel.start();
    }

	@Override
	public void done(String result) {
		gamePanel.setPlayerName(result);
		playArea.clear();
		
		MessagePanel msg = new MessagePanel("Hello there " +gamePanel.getPlayerName()+ " and welcome to the PelCo initiation home page! <br/><br/>#I will be your handler for the duration of these \"simulations\".  You may call me Broadus.<br/><br/>#I am sure you are eager to begin so lets dive right in.....<br/><br/>#First off you will notice that during the \"simulation\" your avatar will appear as <img src='images/brain.gif' />.<br/><br/>  #We apologize for this crude representation.  <br/>Plans are currently under way to present you as something more appealing.  <br/>Unfortunately due to time and monetary constraints these plans will most likely never come to fruition.", new UICallback<Boolean>() {
			@Override
			public void done(Boolean obj) {
				start();
			}

			@Override
			public void failed() {
			}
		});
		msg.animate();
	}

	@Override
	public void failed() {
		// TODO: handle error?
	}
}
