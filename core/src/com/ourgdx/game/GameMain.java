package com.ourgdx.game;

import com.badlogic.gdx.Game;
import com.ourgdx.game.AssetManagment.GameAssetManager;
import com.ourgdx.game.screens.Menus.MainMenuScreen;
import com.ourgdx.game.screens.Menus.PauseMenuScreen;
import com.ourgdx.game.screens.Menus.PreferencesMenu;
import com.ourgdx.game.screens.Pong.PongScreen;
import com.ourgdx.game.screens.testGame1.TestScreen1;
import com.ourgdx.game.screens.mapEditor.mapEditor;

public class GameMain extends Game {
	// Screen Size
	public static final int SCREEN_WIDTH = 720;
	public static final int SCREEN_HEIGHT = 480;

	// Constants for Screens
	public final static int MAIN_MENU = 0;
	public final static int PREFERENCES = 1;
	public final static int PAUSE = 2;
	public final static int PONG = 3;
	public final static int TEST1 = 4;
	public final static int TEST2 = 5;
	private int previousScreen;

	// Screens
	private PreferencesMenu preferencesMenu;
	private MainMenuScreen mainMenuScreen;
	private PauseMenuScreen pauseMenuScreen;
	private PongScreen pongScreen;
	private TestScreen1 testScreen1;
	private mapEditor mapEditor;


	// preferences
	private AppPreferences preferences;

	// Asset Manager
	public GameAssetManager assets;

	@Override
	public void create () {
		// Create our active preferences file & update just in case
		preferences = new AppPreferences();
		updateActivePreferences();

		// Create our asset manager
		assets = GameAssetManager.instance();

		// Create the mainscreen and make it the current screen
		mainMenuScreen = new MainMenuScreen(this);
		previousScreen = MAIN_MENU;
		setScreen(mainMenuScreen);

	}

	@Override
	public void dispose () {
		// dispose of all screens and all of our assets
		pongScreen.dispose();
		testScreen1.dispose();
		mapEditor.dispose();
		mainMenuScreen.dispose();
		pauseMenuScreen.dispose();
		preferencesMenu.dispose();
		assets.manager.dispose();

	}

	// Get preferences, to be used by child screen
	public AppPreferences getPreferences(){
		return this.preferences;
	}

	// Get previous, to be used for return to the proper menu
	public int getPreviousScreen() {
		return previousScreen;
	}

	// Tells the gameplay screen to implement the current preferences
	public void updateActivePreferences(){
		if (pongScreen != null) {
			pongScreen.updatePreferences(
					preferences.getMusicVolume(),
					preferences.isMusicEnabled(),
					preferences.getSoundVolume(),
					preferences.isSoundEffectsEnabled()
			);
		}
		if (testScreen1 != null) {
			testScreen1.updatePreferences(
					preferences.getMusicVolume(),
					preferences.isMusicEnabled(),
					preferences.getSoundVolume(),
					preferences.isSoundEffectsEnabled()
			);
		}

	}



	public void changeScreen(int screen, int currentScreen){
		previousScreen = currentScreen; // set previous to the screen that called this function

		switch(screen){
			case MAIN_MENU:
				// Make a new screen in case it hasn't been.
				if(mainMenuScreen == null) mainMenuScreen = new MainMenuScreen(this);
				// switch the screen
				this.setScreen(mainMenuScreen);
				break;
			case PREFERENCES:
				if(preferencesMenu == null) preferencesMenu = new PreferencesMenu(this);
				this.setScreen(preferencesMenu);
				break;
			case PAUSE:
				if(pauseMenuScreen == null) pauseMenuScreen = new PauseMenuScreen(this);
				this.setScreen(pauseMenuScreen);
				break;
			case PONG:
				// UNSURE ON THIS ONE following a tutorial
				if(pongScreen == null) pongScreen = new PongScreen(this);
				this.setScreen(testScreen1);
				break;
			case TEST1:
				// UNSURE ON THIS ONE following a tutorial
				if(testScreen1 == null) testScreen1 = new TestScreen1(this);
				this.setScreen(testScreen1);
				break;
			case TEST2:
				// UNSURE ON THIS ONE following a tutorial
				if(mapEditor == null) mapEditor = new mapEditor(this);
				this.setScreen(mapEditor);
				break;
		}
	}

	public void clearGame() {
		pongScreen.dispose();
		testScreen1.dispose();
		mapEditor.dispose();
		pongScreen = null;
		testScreen1 = null;
		mapEditor = null;
	}

}
