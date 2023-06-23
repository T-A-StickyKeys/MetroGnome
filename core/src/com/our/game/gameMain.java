package com.our.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class gameMain extends Game {
	SpriteBatch batch;
	Texture img;

//	public final static int MAIN_MENU = 0;
//	public final static int PREFERENCES = 1;
//	public final static int PAUSE = 2;
//	public final static int GAME_PLAY = 3;
//	private int previousScreen;
//
//	// Screens
//	private PreferencesScreen preferencesScreen;
//	private MainMenuScreen mainMenuScreen;
//	private PauseMenuScreen pauseMenuScreen;
//	private GameplayScreen gameplayScreen;
//
//	// preferences
//	private AppPreferences preferences;
//
//	// Asset Manager
//	public PongAssetManager assets;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
