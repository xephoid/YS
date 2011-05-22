package com.ionmarkgames.ys.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.ionmarkgames.ys.client.objects.BulletDir;
import com.ionmarkgames.ys.client.objects.Enemy;
import com.ionmarkgames.ys.client.objects.Sprite;
import com.ionmarkgames.ys.client.objects.Wall;
import com.ionmarkgames.ys.client.objects.You;
import com.ionmarkgames.ys.client.ui.MessagePanel;
import com.ionmarkgames.ys.client.ui.UICallback;

public class YSPanel extends AbsolutePanel {
    
	public static final int MAP_WIDTH = 51;
	public static final int MAP_HEIGHT = 36;
	
    public static final int TILE_HEIGHT = 20;
    public static final int TILE_WIDTH = 20;
    private static final int TICKER_TIME = 50;
    
    private List<Sprite> sprites = new ArrayList<Sprite>();
    private List<Enemy> enemies = new ArrayList<Enemy>();
    private Sprite[][] grid;
    private TextArea keyHandler;
    private You player;
    
    private GameController control;
    private String intro;
    private String albertText;
    
    private Timer ticker = new Timer() {
        @Override
        public void run() {
            tick();
        }
    };
    
    public YSPanel(GameController controller) {
    	control = controller;
    	this.addStyleName("level_" + control.getLevel());
        this.keyHandler = new TextArea();
        keyHandler.addKeyDownHandler(new KeyDownHandler() {
            public void onKeyDown(KeyDownEvent event) {
                switch (event.getNativeKeyCode()) {
                    case KeyCodes.KEY_UP:
                        player.moveUp();
                        break;
                    case KeyCodes.KEY_DOWN:
                        player.moveDown();
                        break;
                    case KeyCodes.KEY_RIGHT:
                        player.moveRight();
                        break;
                    case KeyCodes.KEY_LEFT:
                        player.moveLeft();
                        break;
                }
            }
        });
        keyHandler.addKeyPressHandler(new KeyPressHandler() {
            public void onKeyPress(KeyPressEvent event) {
                switch(event.getCharCode()) {
                    case 'w':
                    case 'W':
                        player.shoot(BulletDir.UP);
                        break;
                    case 'a':
                    case 'A':
                        player.shoot(BulletDir.LEFT);
                        break;
                    case 's':
                    case 'S':
                        player.shoot(BulletDir.DOWN);
                        break;
                    case 'd':
                    case 'D':
                        player.shoot(BulletDir.RIGHT);
                        break;
                }
            }
        });
    }
    
    public void start() {
    	
    	control.loading();
    	
        this.grid = new Sprite[MAP_WIDTH][MAP_HEIGHT];
        this.generateMap();
        
        this.player = new You(this);
        this.visit(player, player.getX() / TILE_WIDTH, player.getY() / TILE_WIDTH);
        
        for (int i = 0; i < 10; i++) {
        	Enemy poo = new Enemy(this, player);
        	this.visit(poo, poo.getX() / TILE_WIDTH, poo.getY() / TILE_WIDTH);
        	this.addSprite(poo);
        }
        
        control.hidLoading();
        
        MessagePanel msg = new MessagePanel(this.intro, new UICallback<Boolean>() {
			@Override
			public void failed() {
			}
			
			@Override
			public void done(Boolean obj) {
				add(keyHandler);
				keyHandler.getElement().focus();
				ticker.scheduleRepeating(TICKER_TIME);
			}
		});
        
        msg.animate();
    }
    
    public void tick() {
    	keyHandler.getElement().focus();
        for(Sprite sprite : this.sprites) {
            sprite.act();
        }
    }
    
    private void clearGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = null;
            }
        }
    }
    
    public boolean visit(Sprite sprite, int x, int y) {
        if (this.grid[x][y] != null) {
            this.grid[x][y].visit(sprite);
        } else {
            this.grid[x][y] = sprite;
        }
        return true;
    }
    
    public void leave(Sprite sprite, int x, int y) {
        this.grid[x][y] = null;
    }
    
    private void generateMap() {
        MapBuilder builder = new MapBuilder();
        boolean[][] map = builder.buildRandomMap(MAP_WIDTH, MAP_HEIGHT);
        for (int i = 0; i < map.length; i++) {
        	int len = map[i].length;
            for (int j = 0; j < len; j++) {
                if (!map[i][j]) {
                    Sprite wall = new Wall(this, i * TILE_WIDTH, j * TILE_HEIGHT);
                    this.sprites.add(wall);
                    this.visit(wall, i, j);
                }
            }
        }
    }
    
    public boolean passable(int x, int y) {
        if (x < 0 || y < 0 || x >= MAP_WIDTH || y >= MAP_HEIGHT) {
            return false;
        }
        return this.grid[x][y] == null || this.grid[x][y].passable();
    }
    
    public void removeSprite(Sprite sprite) {
        if (this.sprites.remove(sprite)) {
            this.leave(sprite, sprite.getX() / TILE_WIDTH, sprite.getY() / TILE_HEIGHT);
            sprite.remove();
            if (sprite.isEnemy()) {
            	this.enemies.remove((Enemy) sprite);
            	if (this.enemies.size() < 1) {
            		this.ticker.cancel();
            		this.remove(this.keyHandler);
            		this.albertMessage();
            	}
            }
        }
    }
    
    public void addSprite(Sprite sprite) {
        this.sprites.add(sprite);
        if (sprite.isEnemy()) {
        	Enemy enemy = (Enemy) sprite;
        	this.enemies.add(enemy);
        }
    }

    private void albertMessage() {
    	RootPanel messageSpace = RootPanel.get("MessageArea");
    	messageSpace.clear();
    	HTML message = new HTML(this.albertText);
    	message.addStyleName("albert_message");
    	message.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				control.nextLevel();
			}
		});
    	messageSpace.add(message);
    }

	public You getPlayer() {
		return this.player;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public void setAlbertText(String albertText) {
		this.albertText = albertText;
	}
}
