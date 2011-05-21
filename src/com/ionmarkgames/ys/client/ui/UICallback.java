package com.ionmarkgames.ys.client.ui;

public interface UICallback<ReturnObject> {

	public void done(ReturnObject obj);
	public void failed();
}
