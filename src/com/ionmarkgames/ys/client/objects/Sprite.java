package com.ionmarkgames.ys.client.objects;

import com.google.gwt.user.client.ui.Image;
import com.ionmarkgames.ys.client.YSPanel;

public abstract class Sprite implements Identifiable {

    protected YSPanel panel;
    private Image image;
    
    private int x = 0;
    private int y = 0;
    
    protected Sprite(YSPanel panel, String imgUrl) {
        this.panel = panel;
        this.image = new Image(imgUrl);
        this.image.getElement().setAttribute("onmousedown", "if (event.preventDefault) event.preventDefault();");
        this.panel.add(this.image);
    }
    
    protected Image getImage() {
        return this.image;
    }
    
    protected void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
        this.panel.setWidgetPosition(this.image, this.x, this.y);
    }
    
    public int getX() {
        return this.x; 
    }
    
    public int getY() {
        return this.y; 
    }
    
    public void remove() {
        this.panel.remove(this.image);
    }
    
    /**
     * Overwrite to do something useful.
     * 
     * @param sprite
     */
    public void visit(Identifiable thing) {
    }
    
    public abstract void act();
    public abstract boolean passable();
}
