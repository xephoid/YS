package com.ionmarkgames.ys.client.ui;

import java.util.Iterator;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class NameInqueryPanel extends HorizontalPanel {

	private TextBox field = new TextBox();
	private Button button = new Button();
	private UICallback<String> callback;
	
	public NameInqueryPanel(UICallback<String> cb) {
		super();
		this.addStyleName("text_area");
		this.callback = cb;
		
		field.setValue("Please enter your name");
		field.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				field.setValue("");
			}
		});
		button.setText("Submit");
		button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				callback.done(field.getValue());
			}
		});
		
		this.add(field);
		this.add(button);
		
		field.getElement().focus();
	}
}