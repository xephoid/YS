package com.ionmarkgames.ys.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MessagePanel extends VerticalPanel {

	private Boolean tf = true;
	private Button ok = new Button();
	private String message;
	private int textIndex = 0;
	private UICallback<Boolean> callback;
	private HTML content = new HTML("");
	
	private Timer textIncrementer = new Timer() {
		@Override
		public void run() {
			addText();
		}
	};
	
	public MessagePanel(String msg, UICallback<Boolean> cb) {
		this.message = msg;
		this.callback = cb;
		ok.setText("OK");
		content.addStyleName("broadus_text");
		this.add(content);
		RootPanel.get("MessageArea").clear();
		RootPanel.get("MessageArea").add(this);
	}
	
	public void animate() {
		this.textIncrementer.scheduleRepeating(75);
	}
	
	public void close() {
		this.setVisible(false);
		this.callback.done(this.tf);
	}
	
	public void addText() {
		if (this.message.length() > textIndex) {
			String txt = this.content.getHTML();
			String toAdd = "";
			if ("<".equals("" + this.message.charAt(textIndex))) {
				int tmp = textIndex;
				while(!">".equals("" + this.message.charAt(tmp))) {
					toAdd += this.message.charAt(tmp);
					tmp++;
				}
				toAdd += ">";
				textIndex = tmp;
			} else if ("#".equals("" + this.message.charAt(textIndex))) {
				// no-op
			} else {
				toAdd += this.message.charAt(textIndex);
			}
			this.content.setHTML(txt + toAdd);
			textIndex++;
		} else {
			ok.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					ok.setVisible(false);
					close();
				}
			});
			this.add(ok);
			this.textIncrementer.cancel();
			this.textIndex = 0;
		}
		
	}
}
