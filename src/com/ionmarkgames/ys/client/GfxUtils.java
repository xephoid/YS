package com.ionmarkgames.ys.client;

public class GfxUtils {

	
	public static final int toGirdX(int x) {
		return x / YSPanel.TILE_WIDTH; 
	}
	
	public static final int toGridY(int y) {
		return y / YSPanel.TILE_HEIGHT;
	}
}
