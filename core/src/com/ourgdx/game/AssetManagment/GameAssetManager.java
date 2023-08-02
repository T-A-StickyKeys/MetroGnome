package com.ourgdx.game.AssetManagment;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.BitmapFontLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.assets.loaders.SkinLoader.SkinParameter;


public class GameAssetManager {

	public final com.badlogic.gdx.assets.AssetManager manager;
	private static GameAssetManager INSTANCE = null;

	
	private GameAssetManager(){
		manager = new com.badlogic.gdx.assets.AssetManager();
	}
	public static GameAssetManager instance() {
		if (INSTANCE == null){
			INSTANCE = new GameAssetManager();
		}
		return INSTANCE;
	}


	public void loadAsset(Asset a){
		if (a.parameter() == null){
			manager.load(a.location(), a.type());
		}
		else if (a.type() == Skin.class){
			SkinParameter param = new SkinParameter(a.parameter());
			manager.load(a.location(), a.type(), param);
		}
		else if (a.type() == BitmapFont.class){
			BitmapFontLoader.BitmapFontParameter param = new BitmapFontLoader.BitmapFontParameter();
			param.atlasName = a.parameter();
			manager.load(a.location(), a.type(), param);
		}
	}
	public void unloadAsset(Asset toUnload){
		manager.unload(toUnload.location());
	}


	public void loadAll(Asset[] assets){
		for (Asset a : assets){
			loadAsset(a);
		}
	}
	public void unloadAll(Asset[] assets){
		for (Asset a : assets){
			unloadAsset(a);
		}
	}

	public void finish(){
		manager.finishLoading();
	}
	public <T> T get(Asset a){
		return manager.get(a.location());
	}

}

//public class PongAssetManager {
//	// THE manager
//	public final AssetManager manager = new AssetManager();
//
//	// All OF ASSETS FILE LOCATIONS
//	// (Assuming the files are located in what libGDX calls internal)
//	// Sound effects
//	public final String hitPaddle = "soundFX/hitPaddle.wav";
//	public final String hitWall = "soundFX/hitWall.wav";
//	public final String score = "soundFX/score.wav";
//	public final String serve = "soundFX/serve.wav";
//	// Music
//	//public final String bgMusic = "music/totallyOriginalVideoGameMusic.mp3";
//	public final String bgMusic = "music/anything.mp3";
//	// Skin and Text
//	public final String text = "skin/commodore-64.fnt";
//	public final String skin = "skin/uiskin.json";
//	public final String atlas = "skin/uiskin.atlas";
//	// Textures
//	public final String paddle = "images/paddlePlain.png";
//	public final String ball = "images/ballPlain.png";
//
//	// LOAD & UNLOAD FUNCTIONS
//	// loading brings them into usable memory, unloading  clear them.
//
//	public void loadFonts(){
//		// A bitmap font needs and atlas of the bitmaps images
//		BitmapFontLoader.BitmapFontParameter param = new BitmapFontLoader.BitmapFontParameter();
//		param.atlasName = atlas;
//		manager.load(text, BitmapFont.class, param);
//	}
//
//	public void unloadFonts(){
//		manager.unload(text);
//	}
//
//	public void loadImages(){
//		manager.load(paddle, Texture.class);
//		manager.load(ball, Texture.class);
//	}
//
//	public void unloadImages(){
//		manager.unload(paddle);
//		manager.unload(ball);
//	}
//
//	public void loadSkin(){
//		SkinParameter params = new SkinParameter(atlas);
//		manager.load(skin, Skin.class, params);
//	}
//
//	public void unloadSkin() {
//		manager.unload(skin);
//	}
//
//	public void loadMusic(){
//		manager.load(bgMusic, Music.class);
//	}
//
//	public void unloadMusic() {
//		manager.unload(bgMusic);
//	}
//
//	public void loadSounds(){
//		manager.load(hitPaddle, Sound.class);
//		manager.load(hitWall, Sound.class);
//		manager.load(score, Sound.class);
//		manager.load(serve, Sound.class);
//	}
//
//	public void unloadSounds() {
//		manager.unload(hitPaddle);
//		manager.unload(hitWall);
//		manager.unload(score);
//		manager.unload(serve);
//	}
//
//
//}
