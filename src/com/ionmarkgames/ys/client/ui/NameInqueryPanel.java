package com.ionmarkgames.ys.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.ionmarkgames.ys.client.GameController;

public class NameInqueryPanel extends HorizontalPanel {

	private TextBox field = new TextBox();
	private Button button = new Button();
	private UICallback<String> callback;
	
	public NameInqueryPanel(UICallback<String> cb) {
		super();
		this.addStyleName("text_area");
		this.callback = cb;
		
		field.setValue("Please enter your name");
		field.addKeyPressHandler(new KeyPressHandler() {
			
			public void onKeyPress(KeyPressEvent event) {
				if (event.getUnicodeCharCode() == 13) {
				   doTheDone(field.getValue());
				}
			}
		});
		field.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				field.setValue("");
			}
		});
		button.setText("Submit");
		button.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
			    doTheDone(field.getValue());
			}
		});
		
		this.add(field);
		this.add(button);
		
		field.getElement().focus();
	}
	
	public void doTheDone(String name) {
	    if ("zekealicious".equals(name)) {
            MessagePanel.TEXT_SPEED = 1;
        }
        if ("glossophobia".equals(name)) {
            GameController.START_LEVEL = 10;
            MessagePanel.TEXT_SPEED = 1;
        }
        if ("thanathophobia".equals(name)) {
            GameController.START_LEVEL = 11;
            MessagePanel.TEXT_SPEED = 1;
        }
        if ("commitment".equals(name)) {
            GameController.START_LEVEL = 8;
            MessagePanel.TEXT_SPEED = 1;
        }
        callback.done(SafeHtmlUtils.htmlEscape(name));
	}
}
